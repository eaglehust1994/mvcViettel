package com.viettel.Common.CommonBussiness;
 
import com.viettel.Common.CommonBO.UserRoleBO;
import  com.viettel.Common.CommonDAO.UserRoleDAO;
import  com.viettel.Common.CommonDTO.SysUserQLKDTO;
import  com.viettel.Common.CommonDTO.UserRoleDTO;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("userRoleBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserRoleBusinessImpl extends BaseFWBusinessImpl<UserRoleDAO,UserRoleDTO, UserRoleBO> implements UserRoleBusiness {

    @Autowired
    private UserRoleDAO userRoleDAO;
    

     
    public UserRoleBusinessImpl() {
        tModel = new UserRoleBO();
        tDAO = userRoleDAO;
    }

    @Override
    public UserRoleDAO gettDAO() {
        return userRoleDAO;
    }

    @Override
    public long count() {
        return userRoleDAO.count("UserRoleBO", null);
    }
  //Hàm hiển thị danh sách vai trò đã được gán cho người dùng
	@Override
	public DataListDTO doSearchUserRole(UserRoleDTO obj) {
		List<UserRoleDTO> ls = userRoleDAO.doSearchUserRole(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}
	//End
	//Hàm hiển thị danh sách vai trò chưa được gán cho người dùng
	@Override
	public DataListDTO doSearchRole(UserRoleDTO obj) {
		List<UserRoleDTO> ls = userRoleDAO.doSearchRole(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}
//End
	@Override
	public List<UserRoleDTO> doSearchUserRoleData(UserRoleDTO obj) {
		List<UserRoleDTO> ls = userRoleDAO.doSearchUserRoleData(obj);
		return ls;
	}

	public List<UserRoleDTO> getForAutoCompleteUserRoleData(UserRoleDTO obj) {
		return userRoleDAO.getForAutoCompleteUserRoleData(obj);
	}
	//Xóa kho vai trò
	@Override
	public boolean deleteUserRoleData(UserRoleDTO obj) {
		if(obj==null){
			return false;
		}
		userRoleDAO.deleteUserRoleData(obj);
		return true;
	}
	//End
//Hàm bổ sung kho vai trò
	@Override
	public boolean insertUserRoleData(List<UserRoleDTO> obj) {
		UserRoleDTO user= new UserRoleDTO();
		user.setUserRoleId(obj.get(0).getUserRoleId());
		List<UserRoleDTO> lst= userRoleDAO.getAllUserRoleData(user);
		if(obj.get(0).getDomainDataId()==null&&obj.size()==1){
		for(UserRoleDTO o:lst){
			this.deleteUserRoleData(o);
		}
		return true;
		}
		else{
		for(UserRoleDTO o:lst){
				this.deleteUserRoleData(o);
		}
		for(UserRoleDTO o:obj){
			userRoleDAO.insertUserRoleData(o);
		}
		}
		return true;
	}
	//End
	//AutocompleteUser
	public List<SysUserQLKDTO> getForAutoComplete(SysUserQLKDTO obj) {
		return userRoleDAO.getForAutoComplete(obj);
	}
	//End
	public List<SysUserQLKDTO> getForAutoCompleteSysUser(SysUserQLKDTO obj) {
		return userRoleDAO.getForAutoCompleteSysUser(obj);
	}
//Bổ sung vai trò
	@Override
	public boolean insertRole(List<UserRoleDTO> obj) {
		if(obj==null||obj.size()==0){
			return false;
		}
		for(UserRoleDTO o:obj){
			if(o.getSysRoleId()!=null&&o.getSysUserId()!=null){
			UserRoleDTO data=userRoleDAO.findOneUserRole(o.getSysUserId(), o.getSysRoleId());
			if(data!=null){
				o.setIsActive((long)1);
				userRoleDAO.updateRole(o); 
			}
			else
			{
				o.setIsActive((long)1);
				userRoleDAO.insertRole(o);
			}
			}
		}
		return true;
	}
	//End
	
	public List<UserRoleDTO> getAllUserRole(UserRoleDTO obj){
		return userRoleDAO.getAllUserRole(obj);
	}
	
	
	protected static final String USER_SESSION_KEY="kttsUserSession";
	public KttsUserSession getUserSession(HttpServletRequest request){
		KttsUserSession s=(KttsUserSession)request.getSession().getAttribute(USER_SESSION_KEY);
		if(s==null){
			throw new BusinessException("user is not authen");
		}
		return s;
		
	}
}
