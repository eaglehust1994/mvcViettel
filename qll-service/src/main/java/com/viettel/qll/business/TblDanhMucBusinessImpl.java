package com.viettel.qll.business;

import java.util.List;
import com.viettel.qll.bo.TblDanhMucBO;
import com.viettel.qll.dao.TblDanhMucDAO;
import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.wms.dto.DepartmentDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("tblDanhMucBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblDanhMucBusinessImpl extends BaseFWBusinessImpl<TblDanhMucDAO, TblDanhMucDTO, TblDanhMucBO>
		implements TblDanhMucBusiness {

	@Autowired
	private TblDanhMucDAO tblDanhMucDAO;

	public TblDanhMucBusinessImpl() {
		tModel = new TblDanhMucBO();
		tDAO = tblDanhMucDAO;
	}

	@Override
	public TblDanhMucDAO gettDAO() {
		return tblDanhMucDAO;
	}

	public List<TblDanhMucDTO> getall(TblDanhMucDTO obj) {
		return tblDanhMucDAO.getall(obj);
	}

	@Override
	public List<TblDanhMucDTO> getDeptForAutocomplete(TblDanhMucDTO obj) {
		// TODO Auto-generated method stub
		return tblDanhMucDAO.getForAutoCompleteDept(obj);
	}
	
	@Override
	public List<TblDanhMucDTO> getDeptForAutocomplete1(TblDanhMucDTO obj) {
		// TODO Auto-generated method stub
		return tblDanhMucDAO.getForAutoCompleteDept1(obj);
	}

	@Override
	public List<TblDanhMucDTO> getDeptForAutocomplete1Popup(TblDanhMucDTO obj) {
		// TODO Auto-generated method stub
		return tblDanhMucDAO.getForAutoCompleteDept1Popup(obj);
	}
	@Override
	public List<TblDanhMucDTO> getDeptForAutocomplete2(TblDanhMucDTO obj) {
		// TODO Auto-generated method stub
		return tblDanhMucDAO.getForAutoCompleteDept2(obj);
	}
	
	@Override
	public List<TblDanhMucDTO> getForAutoCompleteDeptFor62DV(TblDanhMucDTO obj) {
		// TODO Auto-generated method stub
		return tblDanhMucDAO.getForAutoCompleteDeptFor62DV(obj);
	}

	@Override
	public List<TblDanhMucDTO> getDeptForAutocomplete3(TblDanhMucDTO obj) {
		// TODO Auto-generated method stub
		return tblDanhMucDAO.getForAutoCompleteDept3(obj);
	}
	
	
}
