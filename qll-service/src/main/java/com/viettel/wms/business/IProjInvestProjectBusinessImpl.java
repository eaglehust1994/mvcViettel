package com.viettel.wms.business;
 
import com.viettel.wms.bo.IProjInvestProjectBO;
import com.viettel.wms.dao.IProjInvestProjectDAO;
//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.IProjInvestProjectDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("iProjInvestProjectBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IProjInvestProjectBusinessImpl extends BaseFWBusinessImpl<IProjInvestProjectDAO,IProjInvestProjectDTO, IProjInvestProjectBO> implements IProjInvestProjectBusiness {

    @Autowired
    private IProjInvestProjectDAO iProjInvestProjectDAO;
  
    public IProjInvestProjectBusinessImpl() {
        tModel = new IProjInvestProjectBO();
        tDAO = iProjInvestProjectDAO;
    }

    @Override
    public IProjInvestProjectDAO gettDAO() {
        return iProjInvestProjectDAO;
    }

    @Override
    public long count() {
        return iProjInvestProjectDAO.count("IProjInvestProjectBO", null);
    }

    public List<IProjInvestProjectDTO> getForAutoComplete(IProjInvestProjectDTO obj) {
		return iProjInvestProjectDAO.getForAutoComplete(obj);
	}

    public DataListDTO doSearch(IProjInvestProjectDTO obj) {
    	List<IProjInvestProjectDTO> ls = iProjInvestProjectDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public List<String> getFromProjectCode(String code) {
		// TODO Auto-generated method stub
		List<String> lstResult = new ArrayList<String>();
		List<IProjInvestProjectDTO> lst = iProjInvestProjectDAO.getFromProjectCode(code);
		for(int i =0;i<lst.size();i++){
			lstResult.add(lst.get(i).getCode());
		}
		return lstResult;
	}
	public IProjInvestProjectDTO  checkCode(IProjInvestProjectDTO obj){
		return iProjInvestProjectDAO.checkCode(obj);
	}
}
