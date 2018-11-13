package com.viettel.asset.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.lang3.StringUtils;
import org.opensaml.saml.saml2.core.impl.GetCompleteUnmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.viettel.asset.bo.BusinessLog;
import com.viettel.asset.bo.CatAssetCode;
import com.viettel.asset.bo.LongTermAsset;
import com.viettel.asset.bo.LongTermAssetEntity;
import com.viettel.asset.bo.LongTermAssetHistory;
import com.viettel.asset.bo.MerEntity;
import com.viettel.asset.bo.MerHandOverEntity;
import com.viettel.asset.bo.MerHandOverInfo;
import com.viettel.asset.dao.CatAssetCodeDao;
import com.viettel.asset.dao.CatMerchandiseDao;
import com.viettel.asset.dao.LongTermAssetDao;
import com.viettel.asset.dao.LongTermAssetEntityDao;
import com.viettel.asset.dao.LongTermAssetHistoryDao;
import com.viettel.asset.dao.MerEntityDao;
import com.viettel.asset.dao.MerHandOverEntityDao;
import com.viettel.asset.dao.MerHandOverInfoDao;
import com.viettel.asset.dto.AssetReportS21DetailDto;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.asset.dto.BusinessLogDto;
import com.viettel.asset.dto.CatAssetCodeDto;
import com.viettel.asset.dto.ConstrConstructionDto;
import com.viettel.asset.dto.LongTermAssetDto;
import com.viettel.asset.dto.LongTermAssetEntityDto;
import com.viettel.asset.dto.LongTermAssetHistoryDto;
import com.viettel.asset.dto.MerEntityDto;
import com.viettel.asset.dto.MerHandOverEntityDto;
import com.viettel.asset.dto.MerHandOverInfoDto;
import com.viettel.asset.dto.search.AssetHandOverDto;
import com.viettel.asset.dto.search.AssetHandOverSearchDto;
import com.viettel.asset.dto.search.AssetMerHandoverEntityDto;
import com.viettel.asset.filter.session.UserSession;
import com.viettel.erp.bo.UtilAttachedDocumentsBO;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dao.UtilAttachedDocumentsDAO;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.erp.utils.Folder;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.ResponseMessage;
//import com.viettel.ktts2.common.UBarCode;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.common.UString;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class MerHandOverInfoBusiness{

	
	private static final Long VOUCHER_TYPE_NOT_BY_CONSTRUION = 2l;
	private static final Long CAAC_NAME_LEVEL = 4l;

	
	@Autowired
	MerHandOverInfoDao merHandOverInfoDao;

	@Autowired
	LongTermAssetDao longTermAssetDao;

	@Autowired
	CatMerchandiseDao catMerChandiseDao;

	@Autowired
	LongTermAssetEntityDao longTermAssetEntityDao;

	@Autowired
	MerEntityDao merEntityDao;

	@Autowired
	MerHandOverEntityDao merHandOverEntityDao;

	@Autowired
	CatAssetCodeDao catAssetCodeDao;

	@Autowired
	UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;
	@Autowired
	UtilAttachedDocumentsDAO utilAttachedDocumentsDAO;

	@Value("${folder_upload}")
	private String folderUpload;

	@Value("${catAsset.attachTypeKey}")
	private Long attachAssetTypeKey;

	@Value("${catAssetVourcher.attachTypeKey}")
	private Long attachVourcherTypeKey;

	/**
	 * Khoong su dung nua
	 * 
	 * @param obj
	 * @param size
	 * @return
	 */
	@Deprecated
	public List<MerHandOverInfoDto> getMerHandOverInfoAutoComplete(MerHandOverInfoDto obj, Boolean size) {
		return merHandOverInfoDao.getMerHandOverInfoAutoComplete(obj, size);
	}

	/**
	 * Laays thoong tin bien ban ban giao tu merHandOverId
	 * 
	 * @param id
	 * @return
	 */
	public MerHandOverInfoDto getMerHandOverbyId(Long id) {

		return merHandOverInfoDao.getMerHandOverbyId(id);
	}
	/**
	 * Lấy thông tin biên bản bàn giao kèm công trình để upgrade công trình
	 * @param id
	 * @return
	 */
	public MerHandOverInfoDto loadMerHandOverNotByConstrForUpgrade(Long id) {
		return merHandOverInfoDao.loadMerHandOverNotByConstrForUpgrade(id);
	}

	public Object getMerHandOverEntitybyId(Long id) {
		return merHandOverInfoDao.getMerHandOverEntitybyHandOverIdForLta(id);
	}

	public List<CatAssetCodeDto> getCaacName(AutocompleteSearchDto obj) {
		return merHandOverInfoDao.getCaacName(obj);
	}

	public Object getGruopCaac(AutocompleteSearchDto obj) {
		return merHandOverInfoDao.getGruopCaac(obj);
	}

	/*
	 * Hanhls1 addLongTermAssetNot By construction sua tu ham` cu. chua tuning
	 * het 20170405
	 */
	public List<LongTermAssetDto> addLongTermAssetNotByConstruction(List<LongTermAssetDto> lstLongTermAsset)
			throws Exception {
		Long totals;
		Long catAssetCodeId = null;
		Long handOverId;
		String hashUpload = null;
		String attachName = null;
		HashMap<Long, List<LongTermAssetDto>> assetMap = new HashMap<>();

		MerHandOverInfo mm = null;
		List<MerHandOverEntityDto> ltsMerHandOverEntity = null;
		List<LongTermAssetDto> lstReturn = new ArrayList<>();

		// Day ds tai san vao cac map tuong ung voi lotaIndex
		for (LongTermAssetDto asset : lstLongTermAsset) {
			if (assetMap.get(asset.getLotaIndex()) != null) {
				assetMap.get(asset.getLotaIndex()).add(asset);
			} else {
				List<LongTermAssetDto> assetTemp = new ArrayList<>();
				assetTemp.add(asset);
				assetMap.put(asset.getLotaIndex(), assetTemp);
			}
		}
		for (Map.Entry<Long, List<LongTermAssetDto>> entry : assetMap.entrySet()) {

			List<LongTermAssetDto> dataMap = entry.getValue();
			LongTermAsset entity = new LongTermAsset();

			for (LongTermAssetDto ls : dataMap) {
				catAssetCodeId = ls.getCatAssetCodeId();
				handOverId = ls.getHandOverId();
				hashUpload = ls.getHasUpload();
				attachName = ls.getAttachName();
				if (mm == null) {
					mm = merHandOverInfoDao.find(handOverId);
					ltsMerHandOverEntity = merHandOverInfoDao.getMerHandOverEntitybyId(handOverId);
					if (mm == null) {
						throw new BusinessException(
								"handOverId:" + handOverId + ", khong map voi bien ban ban giao nao");
					}
					mm.setIsToAsset(1l);
					merHandOverInfoDao.update(mm);
				}
				// Insert LongTermAsset
				entity.setUseGroupId(mm.getDeliveryGroupId());
				entity.setUseGroupId(ls.getUseGroupId());
				entity.setCatAssetCodeId(ls.getCatAssetCodeId());
				// Lay thoi gian khau hao tu dong tai san
				CatAssetCodeDto dongTS = catAssetCodeDao.getDongTSByChildIdAndLevel(entity.getCatAssetCodeId(), 4l);
				if (dongTS.getDepreciationTime() != null && dongTS.getDepreciationTime() != 0) {
					entity.setDepreciationTime(dongTS.getDepreciationTime());
					entity.setDepreciationRate(100 / dongTS.getDepreciationTime());
				}
				entity.setGroupId(ls.getGroupId());
				entity.setCreatedSource(ls.getCreatedSource());
				entity.setVoucherType(ls.getVoucherType());
				// Khong set lotaIndex luon
				// hanhls1: bo sung them nationalName, madeYear,
				entity.setMadeYear(ls.getMadeYear());
				entity.setDescription(ls.getDescription());
				entity.setNationalName(ls.getNationalName());
				break;
			}

			totals = 0l;
			// value originalPrice
			for (LongTermAssetDto finOP : dataMap) {
				if (catAssetCodeId.equals(finOP.getCatAssetCodeId())) {
					for (MerHandOverEntityDto result : ltsMerHandOverEntity) {
						if (finOP.getMerEntityId().equals(result.getMerEntityId())) {
							Long total = (long) (result.getCount().doubleValue()
									* result.getOriginalPrice().longValue());
							totals = totals + total;
						}
					}
				}
			}

			// Kiem tra voucherType
			CatAssetCodeDto nhom = catAssetCodeDao.getNhomTSByChildIdAndLevel(catAssetCodeId, CAAC_NAME_LEVEL);
			if (nhom.getIsFixedAsset() != null && nhom.getIsFixedAsset() == 1) {
				entity.setLotaType(1L);
			} else {
				if (totals >= 30000000L) {
					entity.setLotaType(1L);// lota_type la tai san co dinh
				} else {
					entity.setLotaType(2L);// lota_type la tai san cong dung cu
				}
			}

			entity.setHandoverCode(mm.getCode());
			// Cap nhat ngay su dung
			entity.setDepreciationStartDate(mm.getHandOverDate());
			entity.setOriginalPrice(totals);
			entity.setIsActive(1l);
			entity.setLotaStatus(1l);
			entity.setIsSentErp(0l);

			Long id = longTermAssetDao.insert(entity);
			LongTermAssetDto dto = new LongTermAssetDto(entity);
			dto.setLongTermAssetId(id);
			lstReturn.add(dto);

			// thuc hien set ma cho LTA;
			longTermAssetDao.setLtaIndexAndCode(id, entity.getCatAssetCodeId());

			LongTermAssetDto longtermasset = new LongTermAssetDto();
			longtermasset.setHasUpload(hashUpload);
			longtermasset.setAttachName(attachName);

			if (StringUtils.isNotEmpty(longtermasset.getHasUpload())
					&& StringUtils.isNotEmpty(longtermasset.getAttachName())) {
				String documentPath = UEncrypt.decryptFileUploadPath(longtermasset.getAttachName());
				String documentName = UString.extractFileNameFromPath(documentPath);
				utilAttachedDocumentsBusinessImpl.insert(documentName, id, documentPath, attachAssetTypeKey);

			}
			for (LongTermAssetDto ls : dataMap) {
				// Update LongTermAssetId to MerEntity
				MerEntity merEntity = merEntityDao.find(ls.getMerEntityId());
				merEntity.setLongTermAssetId(entity.getLongTermAssetId());
				merEntityDao.update(merEntity);
				MerHandOverEntityDto objMer = new MerHandOverEntityDto();
				objMer.setMerEntityId(ls.getMerEntityId());
				objMer.setHandOverId(ls.getHandOverId());

				// MerHandOverEntityDto merhandentity =
				// merHandOverEntityDao.getDataEntity(objMer);
				MerHandOverEntityDto merhandentity = ltsMerHandOverEntity.stream()
						.filter(t -> t.getMerEntityId() != null && t.getMerEntityId().equals(ls.getMerEntityId()))
						.findFirst().get();
				// getMerEntityIdInlist(ltsMerHandOverEntity,ls.getMerEntityId());
				//Long count = (new Double(merhandentity.getCount()));
				// Insert LongTermAssetEntity
				LongTermAssetEntity longTermAssetEntity = new LongTermAssetEntity();
				longTermAssetEntity.setLongTermAssetId(entity.getLongTermAssetId());
				longTermAssetEntity.setMerEntityId(ls.getMerEntityId());
				longTermAssetEntity.setOriginalPrice(merhandentity.getOriginalPrice());
				longTermAssetEntity.setQuantity(merhandentity.getCount());
				longTermAssetEntityDao.insert(longTermAssetEntity);
			}

		}

		return lstReturn;
	}

	// private MerHandOverEntityDto
	// getMerEntityIdInlist(List<MerHandOverEntityDto> lst,Long id){
	// if(id==null){
	// return null;
	// }
	// return (MerHandOverEntityDto)lst.stream().filter(x ->
	// id.equals(x.getMerEntityId())).findFirst().get();
	// }

	/*
	 * Cap nhat tai san khong qua xay lap(khi chua gui tai chinh
	 */
	public List<LongTermAssetDto> updateLongTermAssetNotByConstruction(List<LongTermAssetDto> lstLongTermAsset)
			throws Exception {
		Long totals;
		Long catAssetCodeId = null;
		Long handOverId = null;
		String hashUpload = null;
		String attachName = null;
		String attachIdEncrypted = null;
		List<LongTermAssetDto> lstReturn = new ArrayList<>();
		HashMap<Long, List<LongTermAssetDto>> assetMap = new HashMap<>();

		MerHandOverInfo mm = null;
		List<MerHandOverEntityDto> ltsMerHandOverEntity = null;
		java.util.Set<Long> lstLtaIdNotChange = new HashSet<>();
		// Day ds tai san vao cac map tuong ung voi lotaIndex
		for (LongTermAssetDto asset : lstLongTermAsset) {
			if (asset.getLongTermAssetId() != null) {
				lstLtaIdNotChange.add(asset.getLongTermAssetId());
			}
			if (mm == null) {
				handOverId = asset.getHandOverId();
				hashUpload = asset.getHasUpload();
				attachName = asset.getAttachName();
				attachIdEncrypted = asset.getAttachIdEncrypted();
				mm = merHandOverInfoDao.find(handOverId);

				if (mm == null) {
					throw new BusinessException("handOverId:" + handOverId + ", khong map voi bien ban ban giao nao");
				}
				ltsMerHandOverEntity = merHandOverInfoDao.getMerHandOverEntitybyHandOverId(handOverId);
				mm.setIsToAsset(1l);
				merHandOverInfoDao.update(mm);
			}
			if (assetMap.get(asset.getLotaIndex()) != null) {
				assetMap.get(asset.getLotaIndex()).add(asset);

			} else {
				List<LongTermAssetDto> assetTemp = new ArrayList<>();
				assetTemp.add(asset);
				assetMap.put(asset.getLotaIndex(), assetTemp);
			}
		}

		longTermAssetDao.deleteLTAEntityNotByConstructInAMerHandoverAndChange(mm, lstLtaIdNotChange,
				VOUCHER_TYPE_NOT_BY_CONSTRUION);
		longTermAssetDao.deleteLTANotByConstructInAMerHandoverAndChange(mm, lstLtaIdNotChange,
				VOUCHER_TYPE_NOT_BY_CONSTRUION);
		for (Map.Entry<Long, List<LongTermAssetDto>> entry : assetMap.entrySet()) {

			List<LongTermAssetDto> dataMap = entry.getValue();
			LongTermAsset entity = new LongTermAsset();

			for (LongTermAssetDto ls : dataMap) {
				if (ls.getLongTermAssetId() != null) {
					entity = longTermAssetDao.find(ls.getLongTermAssetId());
				}
				catAssetCodeId = ls.getCatAssetCodeId();
				// Insert LongTermAsset
				// entity.setUseGroupId(mm.getDeliveryGroupId());
				entity.setUseGroupId(ls.getUseGroupId());
				entity.setGroupId(ls.getGroupId());
				entity.setCatAssetCodeId(ls.getCatAssetCodeId());
				CatAssetCodeDto dongTS = catAssetCodeDao.getDongTSByChildIdAndLevel(entity.getCatAssetCodeId(), 4l);
				if (dongTS.getDepreciationTime() != null && dongTS.getDepreciationTime() != 0) {
					entity.setDepreciationTime(dongTS.getDepreciationTime());
					entity.setDepreciationRate(100 / dongTS.getDepreciationTime());
				}
				entity.setCreatedSource(ls.getCreatedSource());
				entity.setVoucherType(ls.getVoucherType());

				// Khong set lotaIndex luon
				// hanhls1: bo sung them nationalName, madeYear,
				entity.setMadeYear(ls.getMadeYear());
				entity.setDescription(ls.getDescription());
				entity.setNationalName(ls.getNationalName());
				break;
			}

			if (mm == null) {
				throw new BusinessException("handOverId:" + handOverId + ", khong map voi bien ban ban giao nao");
			}
			totals = 0l;
			// value originalPrice
			for (LongTermAssetDto finOP : dataMap) {
				if (catAssetCodeId.equals(finOP.getCatAssetCodeId())) {
					for (MerHandOverEntityDto result : ltsMerHandOverEntity) {
						if (finOP.getMerEntityId().equals(result.getMerEntityId())) {
							Long total = (long) (result.getCount().doubleValue()
									* result.getOriginalPrice().longValue());
							totals = totals + total;
						}
					}
				}
			}
			// Kiem tra voucherType
			CatAssetCodeDto nhom = catAssetCodeDao.getNhomTSByChildIdAndLevel(catAssetCodeId, CAAC_NAME_LEVEL);
			if (nhom.getIsFixedAsset() != null && nhom.getIsFixedAsset() == 1) {
				entity.setLotaType(1L);
			} else {
				if (totals >= 30000000L) {
					entity.setLotaType(1L);// lota_type la tai san co dinh
				} else {
					entity.setLotaType(2L);// lota_type la tai san cong dung cu
				}
			}
			entity.setHandoverCode(mm.getCode());
			entity.setDepreciationStartDate(mm.getHandOverDate());
			entity.setOriginalPrice(totals);
			entity.setIsActive(1l);
			entity.setLotaStatus(1l);
			entity.setIsSentErp(0l);
			Long id;
			boolean isCreateNewLta = false;
			if (entity.getLongTermAssetId() == null) {
				id = longTermAssetDao.insert(entity);
				isCreateNewLta = true;
				longTermAssetDao.setLtaIndexAndCode(id, entity.getCatAssetCodeId());
			} else {
				longTermAssetDao.update(entity);
				id = entity.getLongTermAssetId();
			}
			;

			LongTermAssetDto longtermasset = new LongTermAssetDto();
			longtermasset.setHasUpload(hashUpload);
			longtermasset.setAttachName(attachName);
			Long attachId;
			if (UString.isNotNullAndWhitespace(attachIdEncrypted)) {
				attachId = Long.parseLong(UEncrypt.decryptFileUploadPath(attachIdEncrypted));
				if (isCreateNewLta) {
					UtilAttachedDocumentsBO utilAttachedDocumentsBO = utilAttachedDocumentsDAO
							.get(UtilAttachedDocumentsBO.class, attachId);
					UtilAttachedDocumentsDTO dto = utilAttachedDocumentsBO.toDTO();// new
																					// UtilAttachedDocumentsDTO(utilAttachedDocumentsBO);
					dto.setAttachId(null);
					dto.setParentId(id);
					utilAttachedDocumentsDAO.save(dto.toModel());
				}
			} else if (StringUtils.isNotEmpty(longtermasset.getHasUpload())
					&& StringUtils.isNotEmpty(longtermasset.getAttachName())) {
				String documentPath = UEncrypt.decryptFileUploadPath(longtermasset.getAttachName());
				String documentName = UString.extractFileNameFromPath(documentPath);
				utilAttachedDocumentsBusinessImpl.insert(documentName, id, documentPath, attachAssetTypeKey);
			}

			LongTermAssetDto dto = new LongTermAssetDto(entity);
			dto.setLongTermAssetId(id);

			lstReturn.add(dto);
			for (LongTermAssetDto ls : dataMap) {
				// Update LongTermAssetId to MerEntity
				MerEntity merEntity = merEntityDao.find(ls.getMerEntityId());
				merEntity.setLongTermAssetId(entity.getLongTermAssetId());
				merEntityDao.update(merEntity);
				MerHandOverEntityDto objMer = new MerHandOverEntityDto();
				objMer.setMerEntityId(ls.getMerEntityId());
				objMer.setHandOverId(ls.getHandOverId());
				// MerHandOverEntityDto merhandentity =
				// merHandOverEntityDao.getDataEntity(objMer);
				MerHandOverEntityDto merhandentity = ltsMerHandOverEntity.stream()
						.filter(t -> t.getMerEntityId() != null && t.getMerEntityId().equals(ls.getMerEntityId()))
						.findFirst().get();
				// getMerEntityIdInlist(ltsMerHandOverEntity,ls.getMerEntityId());

				//Long count = (new Double(merhandentity.getCount())).longValue();
				// Insert LongTermAssetEntity
				LongTermAssetEntity longTermAssetEntity = new LongTermAssetEntity();
				longTermAssetEntity.setLongTermAssetId(entity.getLongTermAssetId());
				longTermAssetEntity.setMerEntityId(ls.getMerEntityId());
				longTermAssetEntity.setOriginalPrice(merhandentity.getOriginalPrice());
				longTermAssetEntity.setQuantity(merhandentity.getCount());
				longTermAssetEntityDao.insert(longTermAssetEntity);
			}
		}

		return lstReturn;
	}

	@Deprecated
	public LongTermAssetDto addLongTermAsset(List<LongTermAssetDto> obj) throws Exception {
		Long totals = 0l;
		Long catAssetCodeId = null;
		Long handOverId = null;
		String hashUpload = null;
		String attachName = null;
		HashMap<Long, List<LongTermAssetDto>> assetMap = new HashMap<>();
		for (LongTermAssetDto asset : obj) {
			if (assetMap.get(asset.getLotaIndex()) != null) {
				assetMap.get(asset.getLotaIndex()).add(asset);
			} else {
				List<LongTermAssetDto> assetTemp = new ArrayList<>();
				assetTemp.add(asset);
				assetMap.put(asset.getLotaIndex(), assetTemp);
			}
		}
		for (Map.Entry<Long, List<LongTermAssetDto>> entry : assetMap.entrySet()) {
			// Long key = entry.getKey();
			List<LongTermAssetDto> dataMap = entry.getValue();
			for (LongTermAssetDto checkLota : dataMap) {
				LongTermAssetDto checkUsing = longTermAssetDao.checkLotaCodeUsing(checkLota.getLotaCode());
				if (checkUsing != null) {
					LongTermAssetDto objreturn = new LongTermAssetDto();
					objreturn.setLotaCode(checkLota.getLotaCode());
					objreturn.setCheckUsing(String.format("Mã tài sản %s", checkLota.getLotaCode(), " đã tồn tại"));
					return objreturn;
				}
			}
			LongTermAsset entity = new LongTermAsset();

			for (LongTermAssetDto ls : dataMap) {
				catAssetCodeId = ls.getCatAssetCodeId();
				handOverId = ls.getHandOverId();
				hashUpload = ls.getHasUpload();
				attachName = ls.getAttachName();
				MerHandOverInfoDto mm = merHandOverInfoDao.getGroupIdbyId(handOverId);
				// Insert LongTermAsset
				entity.setUseGroupId(mm.getGroupId());
				entity.setCatAssetCodeId(ls.getCatAssetCodeId());
				entity.setGroupId(ls.getGroupId());
				entity.setCreatedSource(ls.getCreatedSource());
				entity.setVoucherType(ls.getVoucherType());
				entity.setLotaIndex(ls.getLotaIndex());
				entity.setLotaCode(ls.getLotaCode());
				break;
			}
			// update MER_HAND_OVER_INFO trường isToAsset = 1
			MerHandOverInfo updatedata = merHandOverInfoDao.find(handOverId);
			updatedata.setIsToAsset(1l);
			merHandOverInfoDao.insert(updatedata);
			// value originalPrice
			for (LongTermAssetDto finOP : dataMap) {
				if (catAssetCodeId == finOP.getCatAssetCodeId()) {
					List<MerHandOverEntityDto> results = merHandOverInfoDao.getMerHandOverEntitybyId(handOverId);
					for (MerHandOverEntityDto result : results) {
						if (finOP.getMerEntityId().equals(result.getMerEntityId())) {
							Long total = (long) (result.getCount().doubleValue()
									* result.getOriginalPrice().longValue());
							totals = totals + total;
						}
					}
				}
			}
			MerHandOverInfo merHandOverInfo = merHandOverInfoDao.find(handOverId);
			if (totals >= 30000000L) {
				entity.setLotaType(1L);
			} else {
				entity.setLotaType(2L);
			}

			entity.setHandoverCode(merHandOverInfo.getCode());
			entity.setOriginalPrice(totals);
			entity.setIsActive(1l);
			entity.setLotaStatus(1l);
			entity.setIsSentErp(0l);

			Long id = longTermAssetDao.insert(entity);
			LongTermAssetDto longtermasset = new LongTermAssetDto();
			longtermasset.setHasUpload(hashUpload);
			longtermasset.setAttachName(attachName);

			if (StringUtils.isNotEmpty(longtermasset.getHasUpload())
					&& StringUtils.isNotEmpty(longtermasset.getAttachName())) {
				// String documentPath = Folder.getFolderSubfix(folderUpload);
				// documentPath = documentPath + File.separatorChar +
				// longtermasset.getAttachName();
				String documentPath = UEncrypt.decryptFileUploadPath(longtermasset.getAttachName());
				String documentName = UString.extractFileNameFromPath(documentPath);

				utilAttachedDocumentsBusinessImpl.insert(documentName, id, documentPath, attachAssetTypeKey);

			}
			for (LongTermAssetDto ls : dataMap) {
				// Update LongTermAssetId to MerEntity
				MerEntity merEntity = merEntityDao.find(ls.getMerEntityId());
				merEntity.setLongTermAssetId(entity.getLongTermAssetId());
				merEntityDao.update(merEntity);
				MerHandOverEntityDto objMer = new MerHandOverEntityDto();
				objMer.setMerEntityId(ls.getMerEntityId());
				objMer.setHandOverId(ls.getHandOverId());
				MerHandOverEntityDto merhandentity = merHandOverEntityDao.getDataEntity(objMer);
				//Long count = (new Double(merhandentity.getCount())).longValue();
				// Insert LongTermAssetEntity
				LongTermAssetEntity longTermAssetEntity = new LongTermAssetEntity();
				longTermAssetEntity.setLongTermAssetId(entity.getLongTermAssetId());
				longTermAssetEntity.setMerEntityId(ls.getMerEntityId());
				longTermAssetEntity.setOriginalPrice(merhandentity.getOriginalPrice());
				longTermAssetEntity.setQuantity(merhandentity.getCount());
				longTermAssetEntityDao.insert(longTermAssetEntity);
			}
		}

		return new LongTermAssetDto();
	}
	
	

	/**
	 * Lấy thông tin biên bản bàn giao hình thành không qua xây lắp
	 * @param ltaId: id của tài sản cố định 
	 * @return
	 */
	public Object getMerHandOverInfoByLtaId(Long ltaId) {
		LongTermAsset lta = longTermAssetDao.getByLongTermAssetId(ltaId);
		MerHandOverInfoDto handOverInfo = merHandOverInfoDao.getMerHandOverbyCode(lta.getHandoverCode());
		return handOverInfo;

	}

	/**
	 * Su dung cho chuc nang cu
	 * 
	 * @param id
	 * @return
	 */
	@Deprecated
	public Object getDataMerHandOverId(Long id) {
		MerHandOverInfoDto objreturn = new MerHandOverInfoDto();
		List<MerEntityDto> merEntitys = merEntityDao.getMerEntityId(id);
		if (merEntitys.size() == 0) {
			objreturn.setCheckUsing(String.format("Tài sản không tồn tại !"));
			return objreturn;
		}
		Long handOverId = null;
		for (MerEntityDto merEntity : merEntitys) {
			List<MerHandOverEntityDto> merHandOverEntitys = merHandOverEntityDao
					.getHanOverId(merEntity.getMerEntityId());
			if (merHandOverEntitys.size() == 0) {
				objreturn.setCheckUsing(String.format("Tài sản không tồn tại !"));
				return objreturn;
			}
			for (MerHandOverEntityDto merHandOverEntity : merHandOverEntitys) {
				handOverId = merHandOverEntity.getHandOverId();
				break;
			}
		}
		// TODO:Hanhls1 - tại sao không bắn lỗi nếu như không tìm thấy tài sản
		// nào tương ứng
		// MerHandOverInfoDto merhandinfo = new MerHandOverInfoDto();
		objreturn.setHandOverId(handOverId);
		objreturn.setLongTermAssetId(id);
		return merHandOverInfoDao.getMerHandOverbyUpdate(objreturn);
	}

	@Deprecated
	public Object updateLongTermAsset(List<LongTermAssetDto> obj) throws Exception {
		Double totals = 0d;
		Long lotaIndex;
		String loteCode;
		Long catAssetCodeId;
		Long handOverId = null;
		Long longTermAssetId = null;
		Boolean isCheck = false;
		String hashUpload = null;
		String attachName = null;
		MerHandOverEntityDto merHandOverEntity = new MerHandOverEntityDto();
		for (LongTermAssetDto ls : obj) {
			if (ls.getCatAssetCodeId() == null) {
				isCheck = true;
			}
			handOverId = ls.getHandOverId();
			longTermAssetId = ls.getLongTermAssetId();
			merHandOverEntity.setLongTermAssetId(longTermAssetId);
			merHandOverEntity.setHandOverId(handOverId);
			if (StringUtils.isNotEmpty(ls.getAttachName())) {
				hashUpload = ls.getHasUpload();
				attachName = ls.getAttachName();
			}
			break;
		}
		LongTermAsset entity = longTermAssetDao.find(longTermAssetId);
		catAssetCodeId = entity.getCatAssetCodeId();
		for (LongTermAssetDto ls : obj) {
			entity.setUseGroupId(ls.getUseGroupId());
			entity.setCreatedSource(ls.getCreatedSource());
			entity.setVoucherType(ls.getVoucherType());
			entity.setGroupId(ls.getGroupId());
			break;
		}
		// remove LongTermAsset in MerEntity
		List<MerEntityDto> results = merHandOverInfoDao.getMerHandOverEntityOld(merHandOverEntity);
		for (MerEntityDto result : results) {
			MerEntity merentity = merEntityDao.find(result.getMerEntityId());
			merentity.setLongTermAssetId(null);
			merEntityDao.update(merentity);
		}
		List<LongTermAssetEntityDto> longtermassetEntitys = merHandOverInfoDao
				.getLongTermAssetEntity(merHandOverEntity);
		for (LongTermAssetEntityDto longtermassetEntity : longtermassetEntitys) {
			LongTermAssetEntity delLongtermasset = longTermAssetEntityDao
					.find(longtermassetEntity.getLongTermAssetEntityId());
			longTermAssetEntityDao.delete(delLongtermasset);
		}
		LongTermAsset longtermasset = longTermAssetDao.find(longTermAssetId);
		loteCode = longtermasset.getLotaCode();
		lotaIndex = longtermasset.getLotaIndex();
		// TH xoa het hang xoa trong tai san
		if (isCheck) {
			longTermAssetDao.delete(longtermasset);
			return new LongTermAssetDto();
		}

		// value originalPrice
		for (LongTermAssetDto finOP : obj) {
			if (catAssetCodeId == finOP.getCatAssetCodeId()) {
				List<MerHandOverEntityDto> resultupdates = merHandOverInfoDao.getMerHandOverEntitybyId(handOverId);
				for (MerHandOverEntityDto resultupdate : resultupdates) {
					if (finOP.getMerEntityId().equals(resultupdate.getMerEntityId())) {
						Long total = (long) (resultupdate.getCount().doubleValue()
								* resultupdate.getOriginalPrice().longValue());
						totals = totals + total;
					}
				}
			}
		}
		MerHandOverInfo merHandOverInfo = merHandOverInfoDao.find(handOverId);
		entity.setLotaIndex(lotaIndex);
		entity.setLotaCode(loteCode);
//		entity.setOriginalPrice(totals.longValue());
		if (totals >= 30000000L) {
			entity.setLotaType(1L);
		} else {
			entity.setLotaType(2L);
		}
		entity.setLotaStatus(1l);
		entity.setHandoverCode(merHandOverInfo.getCode());
		entity.setOriginalPrice(totals.longValue());
		entity.setIsActive(1l);
		entity.setLotaStatus(1l);
		entity.setIsSentErp(0l);
		Long id = longTermAssetDao.insert(entity);
		LongTermAssetDto longtermassetupdate = new LongTermAssetDto();
		if (StringUtils.isNotEmpty(attachName)) {
			longtermassetupdate.setHasUpload(hashUpload);
			longtermassetupdate.setAttachName(attachName);
			// Cap nhat du lieu vao bang UTIL_ATTACHED_DOCUMENTS
			if (StringUtils.isNotEmpty(longtermassetupdate.getHasUpload())
					&& StringUtils.isNotEmpty(longtermassetupdate.getAttachName())) {
				String documentPath = UEncrypt.decryptFileUploadPath(longtermassetupdate.getAttachName());
				String documentName = UString.extractFileNameFromPath(documentPath);

				if (longTermAssetId != null) {
					UtilAttachedDocumentsDTO objAttVourcher = utilAttachedDocumentsBusinessImpl
							.findByParentIdAndType(longTermAssetId, attachAssetTypeKey);
					if (objAttVourcher != null) {
						objAttVourcher.setDocumentName(documentName);
						objAttVourcher.setDocumentPath(documentPath);
						objAttVourcher.setParentId(id);

						utilAttachedDocumentsBusinessImpl.update(objAttVourcher);
					} else {
						utilAttachedDocumentsBusinessImpl.insert(documentName, id, documentPath, attachAssetTypeKey);
					}
				} else {
					utilAttachedDocumentsBusinessImpl.insert(documentName, id, documentPath, attachAssetTypeKey);
				}
			} else if (StringUtils.isNotEmpty(longtermassetupdate.getAttachName())) {
				String documentPath = UEncrypt.decryptFileUploadPath(longtermassetupdate.getAttachName());
				String documentName = UString.extractFileNameFromPath(documentPath);
				if (longTermAssetId != null) {
					UtilAttachedDocumentsDTO objAttVourcher = utilAttachedDocumentsBusinessImpl
							.findByParentIdAndType(longTermAssetId, attachAssetTypeKey);
					if (objAttVourcher != null) {
						objAttVourcher.setDocumentName(documentName);
						objAttVourcher.setDocumentPath(documentPath);
						objAttVourcher.setParentId(id);

						utilAttachedDocumentsBusinessImpl.update(objAttVourcher);
					} else {
						utilAttachedDocumentsBusinessImpl.insert(documentName, id, documentPath, attachAssetTypeKey);
					}
				}
			}
		}

		for (LongTermAssetDto ls : obj) {
			// Update LongTermAssetId to MerEntity
			MerEntity merEntity = merEntityDao.find(ls.getMerEntityId());
			merEntity.setLongTermAssetId(entity.getLongTermAssetId());
			merEntityDao.update(merEntity);
			// Insert LongTermAssetEntity
			MerHandOverEntityDto objMer = new MerHandOverEntityDto();
			objMer.setMerEntityId(ls.getMerEntityId());
			objMer.setHandOverId(ls.getHandOverId());
			MerHandOverEntityDto merhandentity = merHandOverEntityDao.getDataEntity(objMer);
//			Long count = (new Double(merhandentity.getCount())).longValue();
			LongTermAssetEntity longTermAssetEntity = new LongTermAssetEntity();
			longTermAssetEntity.setLongTermAssetId(entity.getLongTermAssetId());
			longTermAssetEntity.setMerEntityId(ls.getMerEntityId());
			longTermAssetEntity.setOriginalPrice(totals.longValue());
			longTermAssetEntity.setQuantity(merhandentity.getCount());
			longTermAssetEntityDao.insert(longTermAssetEntity);
		}
		return new LongTermAssetDto();
	}

	@Deprecated
	public Object getMerHandOverEntityUpdate(MerHandOverEntityDto obj) {
		return merHandOverInfoDao.getMerHandOverEntityUpdate(obj);
	}

	@Deprecated
	public Object getMerHandOverEntityUpdateByHandOverId(MerHandOverEntityDto obj) {
		return merHandOverInfoDao.getMerHandOverEntityUpdateByHandOverId(obj);
	}

	/*
	 * Lay dto voi full_code va lotaIndex
	 */
	@Deprecated
	public LongTermAssetDto getCaacFullCodeTemp(Long catAssetCodeId) {
		// Lay mac dinh la 300001
		Long maxLotaIndex = longTermAssetDao.getNextLotaIndex(catAssetCodeId, null);
		CatAssetCode caacFullCode = catAssetCodeDao.find(catAssetCodeId);
		LongTermAssetDto dto = new LongTermAssetDto();
		dto.setLotaIndex(maxLotaIndex);
		dto.setAssetGroupCode(caacFullCode.getCaacFullCode());
		return dto;
	}

	@Deprecated
	public LongTermAssetDto getCaacCodeFull(Long id) {
		Long lotaIndex;
		List<LongTermAssetDto> findLotaIndexs = longTermAssetDao.findLoteIndex(id);
		/**
		 * Hanhls1 : comment nếu với tên tài sản chưa có index thì
		 * lotaIndex=30001l.
		 */
		if (findLotaIndexs.size() == 1) {
			lotaIndex = 30001L;
		} else if (findLotaIndexs.size() == 0) {
			lotaIndex = 30001L;
		} else {
			LongTermAssetDto maxLotaIndex = longTermAssetDao.getmaxLotaIndex();
			lotaIndex = maxLotaIndex.getLotaIndex().longValue() + findLotaIndexs.size();
		}
		CatAssetCodeDto caacFullCode = (CatAssetCodeDto) merHandOverInfoDao.getCaacFullCodebyId(id);
		LongTermAssetDto obj = new LongTermAssetDto();
		obj.setLotaIndex(lotaIndex);
		obj.setAssetGroupCode(caacFullCode.getCaacFullCode());
		return obj;
	}

	/**
	 * Lay cac vật tư (list CatAssetCode đã hình thành tài sản cố định
	 * gồm vật tư đã hình thành + vật tư chưa hình thành (vật tư chưa hình thành là thiết bị vật tư nằm trong biên bản bàn giao nhưng chưa hình thành tài sản)
	 * @param handoverId
	 * @return
	 * @throws Exception
	 */
	public Object getListMerInLtaDetailByHandOverId(Long handoverId) throws Exception {
		List<LongTermAssetDto> lst = longTermAssetDao.getListMerInLtaDetailByHandOverId(handoverId);
		List<LongTermAssetDto> lstNotCreateLta = longTermAssetDao.getListMerNotCreateLtaByhandOverId(handoverId);
		lst.addAll(lstNotCreateLta);
		for (LongTermAssetDto dto : lst) {
			if (dto.getAttachId() != null) {
				dto.setAttachIdEncrypted(UEncrypt.encryptFileUploadPath(dto.getAttachId().toString()));
			}
		}

		return lst;

	}

	/**
	 * Lay thong tin bien ban ban giao de view tu handOverCode
	 * 
	 * @param handOverCode
	 * @return
	 */
	public AssetHandOverDto getMerHandOverInfoDetail(String handOverCode) throws Exception {
		// TODO Auto-generated method stub
		AssetHandOverDto dto = merHandOverInfoDao.getMerHandOverInfoByCodeForView(handOverCode);
		if (dto == null) {
			throw new BusinessException("asset.handOver.notExistHandOver", handOverCode);
		}
		// List<AssetMerHandoverEntityDto>
		// lstEntity=merHandOverEntityDao.getAssetMerHandOverEntityByMerHandOverId(dto.getId());
		// dto.setLstMerHandOverEntity(lstEntity);

		return dto;
	}

	/**
	 * lấy danh sách tài sản trong biên bản bàn giao cho chức năng view
	 * 
	 * @param handOverCode:ma
	 *            phieu
	 * @return
	 */
	public List<AssetMerHandoverEntityDto> getMerHandOverEntityByHandOverCode(String handOverCode) {
		// TODO Auto-generated method stub		
		return merHandOverInfoDao.getMerHandOverEntityByHandOverCode(handOverCode);
	}

	/**
	 * Export 
	 * @param handOverCode
	 * @return: file docx duoc ma hoa ve base64
	 * @throws Exception
	 */
	public String exportMerHandOverDoc(String handOverCode) throws Exception {
		// TODO Auto-generated method stub
		String pathToTemplate = "HandOverTpl_deliveryNote_cum.docx";
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		String path = filePath + "/" + pathToTemplate;
		
		try (InputStream in = new FileInputStream(path)) {
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			IContext context = report.createContext();
            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.load("entities", AssetMerHandoverEntityDto.class, true);
            
            metadata.addFieldAsImage("barCode");
            AssetHandOverDto dto=getMerHandOverInfoDetail(handOverCode);
            context.put("item", dto);
            Calendar cal=Calendar.getInstance();
            cal.setTime(dto.getHandoverDate());
            context.put("handOverDay",cal.get(Calendar.DAY_OF_MONTH));
            context.put("handOverMonth",cal.get(Calendar.MONTH)+1);
            context.put("handOverYear",cal.get(Calendar.YEAR));
            //context.put("tenCN", "TEST");
//            byte[] bytestest=UBarCode.createBarcode(dto.getHandoverCode());
            byte[] bytestest=null;
            
            IImageProvider logo = new ByteArrayImageProvider(bytestest);
            logo.setUseImageSize(true);

            context.put("barCode", logo);
            
            List<AssetMerHandoverEntityDto> lstEntity= merHandOverInfoDao.getMerHandOverEntityByHandOverCode(handOverCode);            
           
            for(int i=0;i<lstEntity.size();i++){
            	lstEntity.get(i).setRowNum(i+1);
            }
            context.put("entities", lstEntity);
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                /*Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);		
				report.convert(context, options, out);*/
                report.process(context, out);
                out.close();

                return Base64.getEncoder().encodeToString(out.toByteArray());

            }
		}
		
//		return "";
	}
	/**
	 * export biên bàn bàn giao dạng pdf
	 * @param handOverCode
	 * @return: file pdf duoc ma hoa ve base64
	 * @throws Exception
	 */
	public String exportMerHandOverPdf(String handOverCode) throws Exception {
		// TODO Auto-generated method stub
		String pathToTemplate = "HandOverTpl_deliveryNote_cum.docx";
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		String path = filePath + "/" + pathToTemplate;
		
		try (InputStream in = new FileInputStream(path)) {
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			IContext context = report.createContext();
            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.load("entities", AssetMerHandoverEntityDto.class, true);
            
           
            
            metadata.addFieldAsImage("barCode");
            AssetHandOverDto dto=getMerHandOverInfoDetail(handOverCode);
            context.put("item", dto);
            Calendar cal=Calendar.getInstance();
            cal.setTime(dto.getHandoverDate());
            context.put("handOverDay",cal.get(Calendar.DAY_OF_MONTH));
            context.put("handOverMonth",cal.get(Calendar.MONTH)+1);
            context.put("handOverYear",String.valueOf(cal.get(Calendar.YEAR)));
            //context.put("tenCN", "TEST");
//            byte[] bytestest=UBarCode.createBarcode(dto.getHandoverCode());
            byte[] bytestest=null;
          
            IImageProvider logo = new ByteArrayImageProvider(bytestest);
            logo.setUseImageSize(true);
            context.put("barCode", logo);
            
            
            List<AssetMerHandoverEntityDto> lstEntity= merHandOverInfoDao.getMerHandOverEntityByHandOverCode(handOverCode);            
           
            
                   
            for(int i=0;i<lstEntity.size();i++){
            	lstEntity.get(i).setRowNum(i+1);
            }
            context.put("entities", lstEntity);
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);		
				report.convert(context, options, out);
                return Base64.getEncoder().encodeToString(out.toByteArray());

            }
		}
	}

}
