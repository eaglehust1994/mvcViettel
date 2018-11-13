package com.viettel.erp.business;
 
import com.viettel.erp.bo.WareExpCmdBO;
import com.viettel.erp.dao.WareExpCmdDAO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.WareExpCmdDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("wareExpCmdBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WareExpCmdBusinessImpl extends BaseFWBusinessImpl<WareExpCmdDAO,WareExpCmdDTO, WareExpCmdBO> implements WareExpCmdBusiness {

    @Autowired
    private WareExpCmdDAO wareExpCmdDAO;
    

     
    public WareExpCmdBusinessImpl() {
        tModel = new WareExpCmdBO();
        tDAO = wareExpCmdDAO;
    }

    @Override
    public WareExpCmdDAO gettDAO() {
        return wareExpCmdDAO;
    }

    @Override
    public long count() {
        return wareExpCmdDAO.count("WareExpCmdBO", null);
    }

	public String deleteWareExpCmd(List<String> listID) {
		return wareExpCmdDAO.deleteWareExpCmd(listID);
	}

	public List<WareExpCmdDTO> getWareExpCmdByConstrt(ConstrConstructionsDTO obj) {
		return wareExpCmdDAO.getWareExpCmdByConstrt(obj);
	}

	public List<WareExpCmdDTO> getwareExpCmdByPxk(List<String> listPxk) {
		return wareExpCmdDAO.getwareExpCmdByPxk(listPxk);
	}

    

    
}
