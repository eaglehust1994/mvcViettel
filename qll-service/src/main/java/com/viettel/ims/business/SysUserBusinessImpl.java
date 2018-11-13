package com.viettel.ims.business;
 
import java.util.List;
import com.viettel.ims.bo.SysUserBO;
import com.viettel.ims.dao.SysUserDAO;
import com.viettel.ims.dto.SysUserDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("sysUserBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SysUserBusinessImpl extends BaseFWBusinessImpl<SysUserDAO,SysUserDTO, SysUserBO> implements SysUserBusiness {

    @Autowired
    private SysUserDAO sysUserDAO;
     
    public SysUserBusinessImpl() {
        tModel = new SysUserBO();
        tDAO = sysUserDAO;
    }

    @Override
    public SysUserDAO gettDAO() {
        return sysUserDAO;
    }
	
	@Override
	public SysUserDTO findByLoginName(String name) {
		return sysUserDAO.findByLoginName(name);
	}

	@Override
	public List<SysUserDTO> doSearch(SysUserDTO obj) {
		return sysUserDAO.doSearch(obj);
	}	
	
	@Override
	public List<SysUserDTO> getForAutoComplete(SysUserDTO query) {
		return sysUserDAO.getForAutoComplete(query);
	}

	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		return sysUserDAO.delete(ids, tableName, tablePrimaryKey);	
	}
	
	
	public SysUserDTO getById(Long id) {
		return sysUserDAO.getById(id);
	}
}
