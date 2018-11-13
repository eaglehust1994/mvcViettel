package com.viettel.qll.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import com.viettel.qll.bo.KpiDepartmentBO;
import com.viettel.qll.bo.SysGroupBO;
import com.viettel.qll.dao.KpiDepartmentDAO;
import com.viettel.qll.dao.ConnectionDAO;
import com.viettel.qll.dto.KpiDepartmentDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("kpiDerpartmentBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class KpiDepartmentBusinessImpl extends BaseFWBusinessImpl<KpiDepartmentDAO, KpiDepartmentDTO, KpiDepartmentBO> implements KpiDepartmentBusiness {
	protected final Logger log = Logger.getLogger(KpiDepartmentBusinessImpl.class);

	
	@Autowired
	private KpiDepartmentDAO kpiDepartmentDAO;
	 public KpiDepartmentBusinessImpl() {
	        tModel = new KpiDepartmentBO();
	        tDAO = kpiDepartmentDAO;
	    }

	    @Override
	    public KpiDepartmentDAO gettDAO() {
	        return kpiDepartmentDAO;
	    }
	@Override
	public List<KpiDepartmentDTO> getListDepartment() {
	
		KpiDepartmentDTO obj = new KpiDepartmentDTO();
		List<KpiDepartmentDTO> listData = kpiDepartmentDAO.getListDepartment(obj);
		
		return listData;
	}


}
