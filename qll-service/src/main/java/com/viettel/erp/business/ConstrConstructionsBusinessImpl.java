package com.viettel.erp.business;

import com.viettel.erp.bo.ConstrConstructionsBO;
import com.viettel.erp.dao.ConstrConstructionsDAO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.PhieuYeuCauNghiemThuDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("constrConstructionsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrConstructionsBusinessImpl
		extends BaseFWBusinessImpl<ConstrConstructionsDAO, ConstrConstructionsDTO, ConstrConstructionsBO>
		implements ConstrConstructionsBusiness {

	@Autowired
	private ConstrConstructionsDAO constrConstructionsDAO;

	public ConstrConstructionsBusinessImpl() {
		tModel = new ConstrConstructionsBO();
		tDAO = constrConstructionsDAO;
	}

	@Override
	public ConstrConstructionsDAO gettDAO() {
		return constrConstructionsDAO;
	}

	@Override
	public long count() {
		return constrConstructionsDAO.count("ConstrConstructionsBO", null);
	}

	public List<ConstrConstructionsDTO> getConstructions(ConstrConstructionsDTO obj) {
		return constrConstructionsDAO.getConstructions(obj);
	}

	public PhieuYeuCauNghiemThuDTO getDataExportFile(PhieuYeuCauNghiemThuDTO obj) {
		return constrConstructionsDAO.getDataExportFile(obj);
	}

	public PhieuYeuCauNghiemThuDTO getDataExportExtra(PhieuYeuCauNghiemThuDTO obj){
		
		return constrConstructionsDAO.getDataExportExtra(obj);
	}
}
