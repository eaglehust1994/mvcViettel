package com.viettel.qll.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.qll.bo.TblRolesBO;
import com.viettel.qll.dao.TblRolesDAO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblRolesDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;


@Service("tblRolesBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblRolesBusinessImpl extends BaseFWBusinessImpl<TblRolesDAO,TblRolesDTO, TblRolesBO> implements TblRolesBusiness {

    @Autowired
    private TblRolesDAO tblRolesDAO;
     
    public TblRolesBusinessImpl() {
        tModel = new TblRolesBO();
        tDAO = tblRolesDAO;
    }

    
	public DataListDTO selectRoles() {
		List<TblRolesDTO> ls = tblRolesDAO.selectRoles();
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		return data;
	}
}
