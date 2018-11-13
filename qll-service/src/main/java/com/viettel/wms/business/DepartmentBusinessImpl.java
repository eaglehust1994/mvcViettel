package com.viettel.wms.business;
 
import com.viettel.wms.bo.DepartmentBO;
import com.viettel.wms.dao.DepartmentDAO;
import com.viettel.wms.dto.DepartmentDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("departmentBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DepartmentBusinessImpl extends BaseFWBusinessImpl<DepartmentDAO,DepartmentDTO, DepartmentBO> implements DepartmentBusiness {

    @Autowired
    private DepartmentDAO departmentDAO;
    

     
    public DepartmentBusinessImpl() {
        tModel = new DepartmentBO();
        tDAO = departmentDAO;
    }

    @Override
    public DepartmentDAO gettDAO() {
        return departmentDAO;
    }

    @Override
    public long count() {
        return departmentDAO.count("DepartmentBO", null);
    }

    
    public List<DepartmentDTO> getall(DepartmentDTO obj){
    	return departmentDAO.getall(obj);
    }
    
    
    @Override
	public List<DepartmentDTO> getDeptForAutocomplete(DepartmentDTO obj) {
		// TODO Auto-generated method stub
		return departmentDAO.getForAutoCompleteDept(obj);
	}
    
    
    public DepartmentDTO getOne(Long id){
    	return departmentDAO.getOne(id);
    }
}
