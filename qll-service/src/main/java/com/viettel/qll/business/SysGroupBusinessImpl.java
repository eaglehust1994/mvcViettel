package com.viettel.qll.business;
 
import java.util.List;

import com.google.common.collect.Lists;
import com.viettel.qll.bo.SysGroupBO;

import com.viettel.qll.dao.SysGroupDAO;

import com.viettel.qll.dto.SysGroupDTO;
import com.viettel.qll.dto.TblDanhMucDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("sysGroupBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SysGroupBusinessImpl extends BaseFWBusinessImpl<SysGroupDAO,SysGroupDTO, SysGroupBO> implements SysGroupBusiness {

    @Autowired
    private SysGroupDAO sysGroupDAO;
     
//    @Autowired
//    private KpiDerpartmentDAO kpiDerpartmentDAO;
    public SysGroupBusinessImpl() {
        tModel = new SysGroupBO();
        tDAO = sysGroupDAO;
    }

    @Override
    public SysGroupDAO gettDAO() {
        return sysGroupDAO;
    }
//    private final static String STATUS="1"; 
//    private final static String GROUPNAME="KCQ Cty Công trình";
//    private final static String GROUPLEVEL="3";

	@Override
	public List<SysGroupDTO> getListDepartment() {
		SysGroupDTO obj = new SysGroupDTO();
		
		List<SysGroupDTO> listData = sysGroupDAO.getListDepartment(obj);
		return listData;
	}
	
	@Override
	public List<SysGroupDTO> getAutoDepartment(SysGroupDTO obj) {
		// TODO Auto-generated method stub
		List<SysGroupDTO> list = sysGroupDAO.getAutoDepartment(obj);
		return list;
	}
	
	
}
