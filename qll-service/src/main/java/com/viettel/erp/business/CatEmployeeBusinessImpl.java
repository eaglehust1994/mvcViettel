package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.dao.CatEmployeeDAO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.erp.dto.UserEmployeeDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("catEmployeeBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatEmployeeBusinessImpl extends BaseFWBusinessImpl<CatEmployeeDAO,CatEmployeeDTO, CatEmployeeBO> implements CatEmployeeBusiness {

    @Autowired
    private CatEmployeeDAO catEmployeeDAO;
    

     
    public CatEmployeeBusinessImpl() {
        tModel = new CatEmployeeBO();
        tDAO = catEmployeeDAO;
    }

    @Override
    public CatEmployeeDAO gettDAO() {
        return catEmployeeDAO;
    }
    
    @Override
    public long count() {
        return catEmployeeDAO.count("CatEmployeeBO", null);
    }

	public List<CatEmployeeDTO> getEmployeeByRole(CatEmployeeDTO obj) {
		return catEmployeeDAO.getEmployeeByRole(obj);
	}

	public List<CatEmployeeDTO> getListEmployee(CatEmployeeDTO obj) {
		return catEmployeeDAO.getListEmployee(obj);
	}

	@Override
	public List<CatEmployeeDTO> getListEmployeeByRole(SettlementRightDTO rightDTO) {
		return catEmployeeDAO.getListEmployeeByRole(rightDTO);
	}
	
	//dodt
	public List<SettlementRightDTO> getListEmployeeByConstruction(Long constructId) {
	 		return Lists.newArrayList(Iterables.transform(catEmployeeDAO.getListEmployeeByConstruction(constructId), arg0 -> arg0.toDTO()));
	}

	@Override
	public List<CatEmployeeDTO> getEmployeeNameAndIdByRole(SettlementRightDTO rightDTO) {
		// TODO Auto-generated method stub
		return catEmployeeDAO.getEmployeeNameAndIdByRole(rightDTO);
	}

	public List<CatEmployeeDTO> getEmployeeNameRole(SettlementRightDTO obj) {
		return catEmployeeDAO.getEmployeeNameRole(obj);
	}

	//ChuongNV
	@Override
	public List<CatEmployeeDTO> getEmployeeByListID(String stringEmployee ) {
		return catEmployeeDAO.getEmployeeByListID(stringEmployee);
	}
	//End ChuongNV
	@Override
	public List<CatEmployeeDTO> doSearch(CatEmployeeDTO criteria) {
		return catEmployeeDAO.doSearch(criteria);
	}
	
	@Override
	public List<CatEmployeeDTO> doSearchEmployeeViettel(CatEmployeeDTO criteria) {
		return catEmployeeDAO.doSearchEmployeeViettel(criteria);
	}
	
	@Transactional
	public String saveStrId(CatEmployeeDTO obj)throws Exception {
		return catEmployeeDAO.saveStrId(obj);
	}
	public List<CatEmployeeDTO> getListEmployeeByCurrent(SettlementRightDTO obj) {
		return catEmployeeDAO.getListEmployeeByCurrent(obj);
	}
	
	public List<CatEmployeeDTO> getAllEmployee(CatEmployeeDTO dto, TotalNumDTO totalNum) {
		return catEmployeeDAO.getAllEmployee(dto, totalNum);
	}

	@Override
	public List<CatEmployeeDTO> getListEmployeeByRoleConstructId(CatEmployeeDTO obj) {
		
		return catEmployeeDAO.getListEmployeeByRoleConstructId(obj);
	}

	public List<CatEmployeeDTO> getListMonitorChangeConstruct(CatEmployeeDTO obj) {
		
		return catEmployeeDAO.getListEmployeeByRoleConstructId(obj);
	}
	public List<CatEmployeeDTO> getAutoData(CatEmployeeDTO obj) {
		return catEmployeeDAO.getAutoData(obj);
	}
	//tungpv
	@Override
	public CatEmployeeDTO getEmployeeIdByUserId(Long userId){
		return catEmployeeDAO.getEmployeeIdByUserId(userId);
	}
	
	@Override
	public CatEmployeeDTO getRoleID(Long catEmployeeId){
		return catEmployeeDAO.getRoleID(catEmployeeId);
	}

	public List<CatEmployeeDTO> getAutoDataPartner(CatEmployeeDTO obj) {
		return catEmployeeDAO.getAutoDataPartner(obj);
	}

	public List<CatEmployeeDTO> getAutoDataUnit(CatEmployeeDTO obj) {
		return catEmployeeDAO.getAutoDataUnit(obj);
	}

	public List<CatEmployeeDTO> getAutoDataEmail(CatEmployeeDTO obj) {
		return catEmployeeDAO.getAutoDataEmail(obj);
	}

	public List<CatEmployeeDTO> DoSearch(CatEmployeeDTO obj, TotalNumDTO totalNum) {
		return catEmployeeDAO.DoSearch(obj,totalNum);
	}

	@Override
	public boolean checkUpdateCMT(String identifyNumber,String id) {
		return catEmployeeDAO.checkUpdateCMT(identifyNumber,id);
	}

}
