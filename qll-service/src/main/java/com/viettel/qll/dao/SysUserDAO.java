package com.viettel.qll.dao;

import java.util.List;

import com.viettel.qll.bo.SysUserBO;
import com.viettel.qll.dto.SysUserDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("sysUserDAO")
public class SysUserDAO extends BaseFWDAOImpl<SysUserBO, Long> {

    public SysUserDAO() {
        this.model = new SysUserBO();
    }

    public SysUserDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<SysUserDTO> getInfoUser(SysUserDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append(" T1.SYS_USER_ID sysUserId ");
		stringBuilder.append(",T1.EMAIL email ");
		stringBuilder.append(",T1.EMPLOYEE_CODE employeeCode ");
		stringBuilder.append(",T1.FULL_NAME fullName ");
		stringBuilder.append(",T1.LOGIN_NAME loginName ");			
		stringBuilder.append(",T1.PHONE_NUMBER phoneNumber ");
		stringBuilder.append(",T1.SYS_GROUP_ID sysGroupId ");
		stringBuilder.append(",T2.GROUP_NAME_LEVEL3 sysGroupName ");
    	stringBuilder.append(" FROM SYS_USER T1 join SYS_GROUP T2 on T1.SYS_GROUP_ID=T2.SYS_GROUP_ID ");    	
    	stringBuilder.append(" WHERE T1.LOGIN_NAME=:loginName and T1.PASSWORD=:password ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
	
			query.addScalar("sysUserId", new LongType());
			query.addScalar("email", new StringType());
			query.addScalar("employeeCode", new StringType());
			query.addScalar("fullName", new StringType());
			query.addScalar("loginName", new StringType());
			query.addScalar("phoneNumber", new StringType());
			query.addScalar("sysGroupId", new FloatType());
			query.addScalar("sysGroupName", new StringType());
			query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));    
    	

		
		if (StringUtils.isNotEmpty(obj.getLoginName())) {
			query.setParameter("loginName", obj.getLoginName());
		}
	
		if (StringUtils.isNotEmpty(obj.getPassword())) {
			query.setParameter("password", obj.getPassword());
		}
		
		return query.list();
	}  
    
    
    public List<SysUserDTO> getSysUserByEmployee(String loginName) {
    	StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append(" T1.SYS_USER_ID sysUserId ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.EMAIL email ");
		stringBuilder.append(",T1.EMPLOYEE_CODE employeeCode ");
		stringBuilder.append(",T1.FULL_NAME fullName ");
		stringBuilder.append(",T1.LOGIN_NAME loginName ");			
		stringBuilder.append(",T1.PHONE_NUMBER phoneNumber ");
		stringBuilder.append(",T1.SYS_GROUP_ID departmentId ");
		stringBuilder.append(",T2.name sysGroupName ");
    	stringBuilder.append(" FROM SYS_USER T1 left join SYS_GROUP T2 on T1.SYS_GROUP_ID=T2.SYS_GROUP_ID ");    	
    	stringBuilder.append(" WHERE T1.LOGIN_NAME=:loginName");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
	
		query.addScalar("sysUserId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("email", new StringType());
		query.addScalar("employeeCode", new StringType());
		query.addScalar("fullName", new StringType());
		query.addScalar("loginName", new StringType());
		query.addScalar("phoneNumber", new StringType());
		query.addScalar("departmentId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));    
		query.setParameter("loginName", loginName);

		return query.list();
	}  
	
    @SuppressWarnings("unchecked")
	public List<SysUserDTO> infoSysUserByEmployee(String employee) {
    	StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append(" T1.SYS_USER_ID sysUserId ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.EMAIL email ");
		stringBuilder.append(",T1.EMPLOYEE_CODE employeeCode ");
		stringBuilder.append(",T1.FULL_NAME fullName ");
		stringBuilder.append(",T1.LOGIN_NAME loginName ");			
		stringBuilder.append(",T1.PHONE_NUMBER phoneNumber ");
		stringBuilder.append(",T1.SYS_GROUP_ID departmentId ");
		stringBuilder.append(",T2.name sysGroupName ");
    	stringBuilder.append(" FROM SYS_USER T1 left join SYS_GROUP T2 on T1.SYS_GROUP_ID=T2.SYS_GROUP_ID ");    	
    	stringBuilder.append(" WHERE T1.EMPLOYEE_CODE=:employeeCode");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
	
		query.addScalar("sysUserId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("email", new StringType());
		query.addScalar("employeeCode", new StringType());
		query.addScalar("fullName", new StringType());
		query.addScalar("loginName", new StringType());
		query.addScalar("phoneNumber", new StringType());
		query.addScalar("departmentId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));    
		query.setParameter("employeeCode", employee);

		return query.list();
	}  
	
}
