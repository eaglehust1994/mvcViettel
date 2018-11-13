package com.viettel.qll.business;
 
import java.util.List;

import com.viettel.qll.bo.SysUserBO;
import com.viettel.qll.dao.SysUserDAO;
import com.viettel.qll.dto.SysGroupDTO;
import com.viettel.qll.dto.SysUserDTO;
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
    @Autowired
    SysGroupBusinessImpl sysGroupBusinessImpl;
    public SysUserBusinessImpl() {
        tModel = new SysUserBO();
        tDAO = sysUserDAO;
    }

    @Override
    public SysUserDAO gettDAO() {
        return sysUserDAO;
    }
	
    public SysUserDTO getSysUserByEmployee(String loginName) {
    	List<SysUserDTO> result = sysUserDAO.getSysUserByEmployee(loginName);
    
		SysUserDTO user = result.size() >0 ? result.get(0) : null;
		
		SysGroupDTO createDept = (SysGroupDTO) sysGroupBusinessImpl.getOneById(user.getDepartmentId());
		if(user != null)
			if(Integer.parseInt(createDept.getGroupLevel())  <= 3){
				user.setDepartmentName(user.getSysGroupName());
			} else{ // neu nguoi tao thuoc don vi co group lever > 3 => lay don vi cha cua group do
				user.setDepartmentName(createDept.getGroupNameLevel3());
				Long parentGroupId = Long.parseLong(createDept.getPath().split("/")[3]);
				user.setDepartmentId(parentGroupId);
			}
		return user;
	}	

    public SysUserDTO infoSysUserByEmployee(String employee) {
    	List<SysUserDTO> result = sysUserDAO.infoSysUserByEmployee(employee);
    
		SysUserDTO user = result.size() >0 ? result.get(0) : null;
		
		SysGroupDTO createDept = (SysGroupDTO) sysGroupBusinessImpl.getOneById(user.getDepartmentId());
		if(user != null)
			if(Integer.parseInt(createDept.getGroupLevel())  <= 3){
				user.setDepartmentName(user.getSysGroupName());
			} else{ // neu nguoi tao thuoc don vi co group lever > 3 => lay don vi cha cua group do
				user.setDepartmentName(createDept.getGroupNameLevel3());
				Long parentGroupId = Long.parseLong(createDept.getPath().split("/")[3]);
				user.setDepartmentId(parentGroupId);
			}
		return user;
	}	
	@Override
	public List<SysUserDTO> getInfoUser(SysUserDTO obj) {
		return sysUserDAO.getInfoUser(obj);
	}	
	

}
