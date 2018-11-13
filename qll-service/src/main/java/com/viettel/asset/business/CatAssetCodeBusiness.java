package com.viettel.asset.business;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bouncycastle.mail.smime.examples.SendSignedAndEncryptedMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.viettel.asset.bo.BusinessLog;
import com.viettel.asset.bo.CatAssetCode;
import com.viettel.asset.dao.CatAssetCodeDao;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.asset.dto.BusinessLogDto;
import com.viettel.asset.dto.CatAssetCodeDto;
import com.viettel.asset.dto.CatAssetCodeSearchDto;
import com.viettel.asset.dto.service.AAssetGroupDto;
import com.viettel.asset.dto.service.AAssetTypeDto;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.Page;
import com.viettel.ktts2.common.ResponseMessage;
import com.viettel.ktts2.common.URestService;
import com.viettel.ktts2.common.UString;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class CatAssetCodeBusiness {

	@Value("${erpUpdateCatAssetCodeGroupUrl}")
	private String erpUpdateCatAssetCodeGroupUrl;
	
	@Value("${erpUpdateCatAssetCodeTypeUrl}")
	private String erpUpdateCatAssetCodeTypeUrl;
	
	static Logger LOGGER = LoggerFactory.getLogger(CatAssetCodeBusiness.class);
	
	@Autowired
	CatAssetCodeDao catAssetCodeDao;

	@Autowired
	BusinessLogBusiness businessLogBusiness;
	@Autowired 
	UserInfoBusiness userInfoBusiness;

	// @Inject
	// private UserSession userSession;

	public CatAssetCode find(Long id) {
		return catAssetCodeDao.find(id);
	}

	public CatAssetCodeDto getDetail(Long id) throws BusinessException {
		CatAssetCode entity = getEntityCheckNull(id);
		CatAssetCodeDto model = new CatAssetCodeDto(entity);
		model.setIsUsing(isUsing(id));
		return model;
	}

	public List<CatAssetCodeDto> search(CatAssetCodeSearchDto searchForm) {
		List<CatAssetCode> lst = catAssetCodeDao.search(searchForm);
		return lst.stream().map(x -> new CatAssetCodeDto(x)).collect(Collectors.toList());
	}

	public Page<CatAssetCodeDto> searchPaginage(CatAssetCodeSearchDto searchForm) {
		Page<CatAssetCode> pageEntity = catAssetCodeDao.searchPaginage(searchForm);
		Page<CatAssetCodeDto> page = new Page<>(pageEntity, x -> new CatAssetCodeDto(x));
		return page;
	}

	public CatAssetCodeDto insert(CatAssetCodeDto form) throws Exception {

		// Validate and calculate Level
		validateInsert(form);

		// Calculate Index and CaacCode
		getNextCode(form);

		CatAssetCode parent = null;
		if (form.getCaacParentId() != null) {
			parent = catAssetCodeDao.find(form.getCaacParentId());
		}
		String fullCode = (parent == null) ? form.getCaacCode() : parent.getCaacFullCode() + form.getCaacCode();
		String parentPath = (parent == null) ? CatAssetCode.Constants.PATH_SEPARATOR : parent.getCaacPath();

		CatAssetCode entity = form.toEntity();
		entity.setCaacFullCode(fullCode);
		entity.setCaacPath(parentPath);
		entity.setIsActive(CatAssetCode.Constants.ACTIVE);

		// entity.setCreatorId(userSession.getUserId());
		entity.setCreatedDate(new Date());
		
		LOGGER.info("send to erp to update ");
		sendCatAssetCodeToErp(entity);

		catAssetCodeDao.insert(entity);

		entity.setCaacPath(entity.getCaacPath() + entity.getCatAssetCodeId() + CatAssetCode.Constants.PATH_SEPARATOR);
		catAssetCodeDao.update(entity);

		/* Insert dữ liệu log */
		BusinessLogDto businessLogModel = initBusinessLog();
		businessLogModel.setBulAction(BusinessLog.Constants.BulAction.INSERT);
		Gson gson = new Gson();
		businessLogModel.setNewValue(gson.toJson(entity));
		businessLogModel.setMainId(entity.getCatAssetCodeId());
		businessLogBusiness.insert(businessLogModel);

		return new CatAssetCodeDto(entity);
	}

	/**
	 * Gửi sang tài chính
	 * @param entity
	 * @throws Exception
	 */
	private void sendCatAssetCodeToErp(CatAssetCode entity) throws Exception{	
		try{
			if(entity.getCaacLevel()==1){
				AAssetGroupDto assetGroupDto=new AAssetGroupDto();
				assetGroupDto.setName(entity.getCaacName());
				//assetGroupDto.setDescription(entity.getCaacName());
				//assetGroupDto.setDescription(entity.get());				
				assetGroupDto.setValue(entity.getCaacFullCode());
				URestService service=new URestService();
				//Trường hợp nhóm
				service.post(erpUpdateCatAssetCodeGroupUrl, assetGroupDto);
			}else if(entity.getCaacLevel()==2){
				/**
				 * Chuyển giải pháp không đồng bộ loại tài sản
				 */
				//Trường hợp loại	
//				AAssetTypeDto assetGroupDto=new AAssetTypeDto();
//				assetGroupDto.setName(entity.getCaacName());
//				assetGroupDto.setMonth(entity.getDepreciationTime()==null?0l:entity.getDepreciationTime());
//				int indexParent=entity.getCaacFullCode().lastIndexOf(entity.getCaacCode());
//				String parentCode=entity.getCaacFullCode().substring(0,indexParent);
//				assetGroupDto.setGroupValue(parentCode);
//				assetGroupDto.setValue(entity.getCaacFullCode());
//				URestService service=new URestService();				
//				service.post(erpUpdateCatAssetCodeTypeUrl, assetGroupDto);
			}else if(3==entity.getCaacLevel()){
				//Trường hợp dòng (tương đương với loại ở ERP
				AAssetTypeDto assetGroupDto=new AAssetTypeDto();
				assetGroupDto.setName(entity.getCaacName());
				assetGroupDto.setMonth(entity.getDepreciationTime()==null?0l:entity.getDepreciationTime());
				//assetGroupDto.setDescription(entity.getCaacName());
				//assetGroupDto.setDescription(entity.get());
				//int indexParent=entity.getCaacFullCode().lastIndexOf(entity.getCaacCode());
				//String parentCode=entity.getCaacFullCode().substring(0,indexParent);
				String parentCode=entity.getCaacFullCode().substring(0, 1);
				assetGroupDto.setGroupValue(parentCode);
				assetGroupDto.setValue(entity.getCaacFullCode());				
				URestService service=new URestService();				
				service.post(erpUpdateCatAssetCodeTypeUrl, assetGroupDto);
			}
		}catch(Exception ex){
			LOGGER.error("Loi khi gui dong bo danh muc ma tai san sang tai chinh",ex);
			throw new BusinessException("Đồng bộ ERP lỗi");
		}
		
		
	}

	public CatAssetCodeDto update(CatAssetCodeDto form) throws Exception {
		if (form == null) {
			throw new BusinessException("error.catAssetCode.input.null");
		}
		CatAssetCode entity = getEntityCheckNull(form.getCatAssetCodeId());

		Gson gson = new Gson();
		String oldValue = gson.toJson(entity);

		String caacName = form.getCaacName();
		if (UString.isNullOrWhitespace(caacName)) {
			throw new BusinessException("error.catAssetCode.caacName.null");
		}

		if (caacName.trim().length() > 1000) {
			throw new BusinessException("error.catAssetCode.caacName.maxlength", 1000);
		}

		entity.setCaacName(caacName);
		entity.setIsFixedAsset(form.getIsFixedAsset());
		entity.setDepreciationTime(form.getDepreciationTime());
		entity.setUseDuration(form.getUseDuration());
		updateInfoOfUser(entity);

		sendCatAssetCodeToErp(entity);
		catAssetCodeDao.update(entity);

		/* Insert dữ liệu log */
		BusinessLogDto businessLogModel = initBusinessLog();
		businessLogModel.setBulAction(BusinessLog.Constants.BulAction.UPDATE);
		businessLogModel.setOldValue(oldValue);
		businessLogModel.setNewValue(gson.toJson(entity));
		businessLogModel.setMainId(entity.getCatAssetCodeId());
		businessLogBusiness.insert(businessLogModel);

		return new CatAssetCodeDto(entity);
	}

	public void delete(Long catAssetCodeId) throws Exception {
		CatAssetCode entity = getEntityCheckNull(catAssetCodeId);
		if (isUsing(entity.getCatAssetCodeId())) {
			throw new BusinessException("error.catAssetCode.isUsing");
		}
		Gson gson = new Gson();
		String oldValue = gson.toJson(entity);

		catAssetCodeDao.delete(entity);

		/* Insert dữ liệu log */
		BusinessLogDto businessLogModel = initBusinessLog();
		businessLogModel.setBulAction(BusinessLog.Constants.BulAction.DELETE);
		businessLogModel.setOldValue(oldValue);
		businessLogModel.setMainId(entity.getCatAssetCodeId());
		businessLogBusiness.insert(businessLogModel);
	}

	public CatAssetCodeDto active(Long catAssetCodeId) throws Exception {
		CatAssetCode entity = getEntityCheckNull(catAssetCodeId);
		if (Objects.equals(entity.getIsActive(), CatAssetCode.Constants.ACTIVE)) {
			throw new BusinessException("error.catAssetCode.alreadyActive", catAssetCodeId);
		}

		Gson gson = new Gson();
		String oldValue = gson.toJson(entity);

		entity.setIsActive(CatAssetCode.Constants.ACTIVE);
		updateInfoOfUser(entity);
		catAssetCodeDao.update(entity);

		/* Insert dữ liệu log */
		BusinessLogDto businessLogModel = initBusinessLog();
		businessLogModel.setBulAction(BusinessLog.Constants.BulAction.ACTIVE);
		businessLogModel.setOldValue(oldValue);
		businessLogModel.setNewValue(gson.toJson(entity));
		businessLogModel.setMainId(entity.getCatAssetCodeId());
		businessLogBusiness.insert(businessLogModel);

		return new CatAssetCodeDto(entity);
	}

	public CatAssetCodeDto inactive(Long catAssetCodeId) throws Exception {
		CatAssetCode entity = getEntityCheckNull(catAssetCodeId);
		if (Objects.equals(entity.getIsActive(), CatAssetCode.Constants.INACTIVE)) {
			throw new BusinessException("error.catAssetCode.alreadyInactive", catAssetCodeId);
		}

		Gson gson = new Gson();
		String oldValue = gson.toJson(entity);

		entity.setIsActive(CatAssetCode.Constants.INACTIVE);
		updateInfoOfUser(entity);
		catAssetCodeDao.update(entity);

		/* Insert dữ liệu log */
		BusinessLogDto businessLogModel = initBusinessLog();
		businessLogModel.setBulAction(BusinessLog.Constants.BulAction.INACTIVE);
		businessLogModel.setOldValue(oldValue);
		businessLogModel.setNewValue(gson.toJson(entity));
		businessLogModel.setMainId(entity.getCatAssetCodeId());
		businessLogBusiness.insert(businessLogModel);
		return new CatAssetCodeDto(entity);
	}

	private void getNextCode(CatAssetCodeDto form) throws BusinessException {
		Integer curIndex = catAssetCodeDao.getCurrentIndex(form.getCaacParentId());
		int nextIndex = getNextIndex(form.getCaacLevel(), curIndex);
		checkThresholdOfIndex(form.getCaacLevel(), nextIndex);
		String code = convertIndexToCode(form.getCaacLevel(), nextIndex);
		form.setCaacIndex((long) nextIndex);
		form.setCaacCode(code);
	}

	private int getNextIndex(Long caacLevel, Integer curIndex) {
		return curIndex == null ? CatAssetCode.Constants.THRESHOLD_MIN_MAP.get(caacLevel) : curIndex + 1;
	}

	private String convertIndexToCode(Long caacLevel, int curIndex) {
		Integer idx = new Integer(curIndex);
		String code;
		if (Objects.equals(caacLevel, CatAssetCode.Constants.CAAC_LEVEL_NHOM)) {
			code = String.valueOf("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(idx - 1));
		} else if (Objects.equals(caacLevel, CatAssetCode.Constants.CAAC_LEVEL_TEN)) {
			code = idx < 10 ? "0" + idx.toString() : idx.toString();
		} else {
			code = idx.toString();
		}
		return code;
	}

	private void checkThresholdOfIndex(Long caacLevel, Integer nextIndex) throws BusinessException {
		if (nextIndex != null && nextIndex > CatAssetCode.Constants.THRESHOLD_MAX_MAP.get(caacLevel)) {
			throw new BusinessException("error.catAssetCode.caacCode.overflow");
		}
	}

	private void validateInsert(CatAssetCodeDto form) throws BusinessException {
		if (form == null) {
			throw new BusinessException("error.catAssetCode.input.null");
		}
		String caacName = form.getCaacName();
		if (UString.isNullOrWhitespace(caacName)) {
			throw new BusinessException("error.catAssetCode.caacName.null");
		}

		if (caacName.trim().length() > 1000) {
			throw new BusinessException("error.catAssetCode.caacName.maxlength", 1000);
		}

		Long level;
		if (form.getCaacParentId() == null) {
			level = CatAssetCode.Constants.CAAC_LEVEL_NHOM;
		} else {
			CatAssetCode parent = catAssetCodeDao.find(form.getCaacParentId());
			if (parent == null) {
				throw new BusinessException("error.catAssetCode.caacParentId.notInDB", form.getCaacParentId());
			} else if (Objects.equals(parent.getCaacLevel(), CatAssetCode.Constants.CAAC_LEVEL_TEN)) {
				throw new BusinessException("error.catAssetCode.caacParentId.isLeafNode", form.getCaacParentId());
			} else {
				level = 1 + parent.getCaacLevel();
			}
		}
		form.setCaacLevel(level);
	}

	private CatAssetCode getEntityCheckNull(Long catAssetCodeId) throws BusinessException {
		if (catAssetCodeId == null) {
			throw new BusinessException("error.catAssetCode.catAssetCodeId.null");
		}
		CatAssetCode entity = catAssetCodeDao.find(catAssetCodeId);
		if (entity == null) {
			throw new BusinessException("error.catAssetCode.catAssetCodeId.notInDB", catAssetCodeId);
		}
		return entity;
	}

	private boolean isUsing(Long id) {
		boolean res = catAssetCodeDao.isExists(CatAssetCode.Columns.CAAC_PARENT_ID, id);
		return res;
	}

	private void updateInfoOfUser(CatAssetCode entity) {
		// entity.setLastUpdaterId(userSession.getUserId());
		entity.setLastModifiedDate(new Date());
	}

	private BusinessLogDto initBusinessLog() {
		BusinessLogDto businessLogModel = new BusinessLogDto();
		businessLogModel.setCreatedDate(new Date());
		businessLogModel.getLstDbTable().add(CatAssetCode.Constants.TABLE_NAME);
		// businessLogModel.setUserId(userSession.getUserId());
		return businessLogModel;
	}

	// Hanhls1 - filter loai tai san
	public List<CatAssetCodeDto> filterCatAssetCodeType(AutocompleteSearchDto dto) {
		List<CatAssetCode> bo = catAssetCodeDao.filterCatAssetCodeType(dto);
		return bo.stream().map(x -> new CatAssetCodeDto(x)).collect(Collectors.toList());

	}
	
	/**
	 * PhuongH1 - Tìm kiếm phục vụ autocomplete
	 * @param dto
	 * @return
	 */
	public List<CatAssetCodeDto> filterCatAssetCodeAutocomplete(CatAssetCodeSearchDto searchForm) {
		return catAssetCodeDao.filterCatAssetCodeAutocomplete(searchForm)
				.stream().map(x -> new CatAssetCodeDto(x))
				.collect(Collectors.toList());
		
	}

	public ResponseMessage isRequiredToBeLTA(Long caacLevel,Long catAssetCodeId) {
		// TODO Auto-generated method stub
		if(caacLevel>1l){//Đẩy lên là id tên tài sản
			CatAssetCodeDto nhom=catAssetCodeDao.getNhomTSByChildIdAndLevel(catAssetCodeId,caacLevel);
			if(nhom.getIsFixedAsset()==null||nhom.getIsFixedAsset()==1){
				return new ResponseMessage(true);
				
			}
			return new ResponseMessage(false);
					
		}else{
			return new ResponseMessage(false);
		}
		
	}

}
