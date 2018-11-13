package com.viettel.ktts2.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.viettel.ktts2.dto.KttsSysUserDto;
import com.viettel.service.base.dao.BaseFWDAOImpl;
@Repository("authenticateDao")
public class AuthenticateDao extends BaseFWDAOImpl{

	@Value("${common.sql.selectUserInfo}")
	private String getUserInfoSql;
	/*
	 * Lấy thông tin người dùng từ databsae
	 */
	public KttsSysUserDto getKttsSysUserById(Long sysUserId) {
		// TODO Auto-generated method stub
		//Session session=getSession();
//		String sql="SELECT sys_user_id as sysUserId"
//				+ ", group_id as groupId"
//				+ ", group_code as groupCode"
//				+ ", group_name as groupName"
//				+ ", voffice_name as vOfficeName"
//				+ " FROM Sys_User su left join Sys_Group s on su.group_id=s.group_id "
//				+ " WHERE ROWNUM =1 and su.SYS_USER_ID =:sysUserId";
		String sql=getUserInfoSql;
		SQLQuery query=getSession().createSQLQuery(sql);
		query.addScalar("sysUserId",StandardBasicTypes.LONG);
		query.addScalar("groupId",StandardBasicTypes.LONG);
		query.addScalar("groupCode",StandardBasicTypes.STRING);
		query.addScalar("groupName",StandardBasicTypes.STRING);
		query.addScalar("vOfficeName",StandardBasicTypes.STRING);
		
		query.setParameter("sysUserId", sysUserId);
		query.setResultTransformer(Transformers.aliasToBean(KttsSysUserDto.class));
		java.util.List<KttsSysUserDto> list=query.list();
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
		
		
				
		//return null;
	}
	
}
