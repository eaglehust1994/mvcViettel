package com.viettel.erp.business;
 
import com.google.common.collect.Lists;
import com.viettel.erp.bo.CurrentStateHandoverListBO;
import com.viettel.erp.dao.CurrentStateHandoverListDAO;
import com.viettel.erp.dto.CurrentStateHandoverDTO;
import com.viettel.erp.dto.CurrentStateHandoverListDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("currentStateHandoverListBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentStateHandoverListBusinessImpl extends BaseFWBusinessImpl<CurrentStateHandoverListDAO,CurrentStateHandoverListDTO, CurrentStateHandoverListBO> implements CurrentStateHandoverListBusiness {

    @Autowired
    private CurrentStateHandoverListDAO currentStateHandoverListDAO;
    

     
    public CurrentStateHandoverListBusinessImpl() {
        tModel = new CurrentStateHandoverListBO();
        tDAO = currentStateHandoverListDAO;
    }

    @Override
    public CurrentStateHandoverListDAO gettDAO() {
        return currentStateHandoverListDAO;
    }

    @Override
    public long count() {
        return currentStateHandoverListDAO.count("CurrentStateHandoverListBO", null);
    }

	@Override
	public List<CurrentStateHandoverListDTO> getCurrentStateHandoverByListId(Long id) {
	List<CurrentStateHandoverListBO> list =	currentStateHandoverListDAO.getCurrentStateHandoverByListId(id);
	List<CurrentStateHandoverListDTO> listDTO = Lists.newArrayList();
	System.out.println(list.size());
	for(CurrentStateHandoverListBO obj : list){
		listDTO.add(obj.toDTO());
	}
	return listDTO;
	}

	@Override
	public boolean deleteMutilRecord(List<String> listReportID) {
	return	currentStateHandoverListDAO.deleteMutilRecord(listReportID);
	}
  
    
    

    
}
