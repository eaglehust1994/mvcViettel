package com.viettel.ims.dao;

import java.util.List;

import com.viettel.ims.bo.SysUserBO;
import com.viettel.ims.dto.CatTaskDTO;
import com.viettel.ims.dto.SysUserDTO;

import java.lang.reflect.Field;

import com.viettel.service.base.dao.BaseFWDAOImpl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
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
	public List<SysUserDTO> doSearch(SysUserDTO criteria) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE 1=1 ");
		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(EMPLOYEE_CODE) like UPPER(:key) OR UPPER(FULL_NAME) like UPPER(:key) escape '&')");
		}
		
		if (null != criteria.getStatus()) {
			stringBuilder.append("AND T1.STATUS = :status ");
		}
		if (StringUtils.isNotEmpty(criteria.getPhoneNumber())) {
			stringBuilder.append("AND UPPER(T1.PHONE_NUMBER) LIKE UPPER(:phoneNumber) ESCAPE '\\' ");
		}
		if (StringUtils.isNotEmpty(criteria.getEmail())) {
			stringBuilder.append("AND UPPER(T1.EMAIL) LIKE UPPER(:email) ESCAPE '\\' ");
		}
		
		if (null != criteria.getSysUserId()) {
			stringBuilder.append("AND T1.SYS_USER_ID = :sysUserId ");
		}
    	
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

//		query.addScalar("totalRecord", new IntegerType());
		query.addScalar("sysGroupId", new DoubleType());
//		query.addScalar("sysGroupName", new StringType());
		query.addScalar("needChangePassword", new LongType());
		query.addScalar("changePasswordDate", new DateType());
		query.addScalar("newId", new LongType());
//		query.addScalar("newName", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("phoneNumber", new StringType());
		query.addScalar("email", new StringType());
		query.addScalar("employeeCode", new StringType());
		query.addScalar("password", new StringType());
		query.addScalar("fullName", new StringType());
		query.addScalar("loginName", new StringType());
		query.addScalar("sysUserId", new LongType());
//		query.addScalar("sysUserName", new StringType());
    	

		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			query.setParameter("key", "%" + criteria.getKeySearch() + "%");
			queryCount.setParameter("key", "%" + criteria.getKeySearch() + "%");
		}
		
		if (null != criteria.getStatus()) {
			query.setParameter("status", criteria.getStatus());
			queryCount.setParameter("status", criteria.getStatus());
		}
		if (StringUtils.isNotEmpty(criteria.getPhoneNumber())) {
			query.setParameter("phoneNumber", "%" + criteria.getPhoneNumber() + "%");
			queryCount.setParameter("phoneNumber", "%" + criteria.getPhoneNumber() + "%");
		}
		if (StringUtils.isNotEmpty(criteria.getEmail())) {
			query.setParameter("email", "%" + criteria.getEmail()+ "%");
			queryCount.setParameter("email", "%" + criteria.getEmail()+ "%");
		}
	
		
		if (null != criteria.getSysUserId()) {
			query.setParameter("sysUserId", criteria.getSysUserId());
			queryCount.setParameter("sysUserId", criteria.getSysUserId());
		}
    	
		query.setResultTransformer(Transformers
				.aliasToBean(SysUserDTO.class));
		List ls = query.list();
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1)
					* criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		return ls;
	}  
	
	public SysUserDTO findByLoginName(String value) {
		StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE upper(T1.LOGIN_NAME) = upper(:value)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("sysGroupId", new DoubleType());
		query.addScalar("needChangePassword", new LongType());
		query.addScalar("changePasswordDate", new DateType());
		query.addScalar("newId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("phoneNumber", new StringType());
		query.addScalar("email", new StringType());
		query.addScalar("employeeCode", new StringType());
		query.addScalar("password", new StringType());
		query.addScalar("fullName", new StringType());
		query.addScalar("loginName", new StringType());
		query.addScalar("sysUserId", new LongType());
    	
		query.setParameter("value", value);    	
		query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));    	

		return (SysUserDTO) query.uniqueResult();
	}
	
	
	public SysUserDTO findByEmployeeCode(String code) {
		StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE upper(T1.EMPLOYEE_CODE) = upper(:code)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("sysGroupId", new DoubleType());
		query.addScalar("needChangePassword", new LongType());
		query.addScalar("changePasswordDate", new DateType());
		query.addScalar("newId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("phoneNumber", new StringType());
		query.addScalar("email", new StringType());
		query.addScalar("employeeCode", new StringType());
		query.addScalar("password", new StringType());
		query.addScalar("fullName", new StringType());
		query.addScalar("loginName", new StringType());
		query.addScalar("sysUserId", new LongType());
    	
		query.setParameter("code", code);    	
		query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));    	

		return (SysUserDTO) query.uniqueResult();
	}

	public List<SysUserDTO> getForAutoComplete(SysUserDTO obj) {
		StringBuilder stringBuilder = getSelectAllQuery();
		stringBuilder.append(" Where STATUS = 1 ");
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(EMPLOYEE_CODE) like UPPER(:key) OR UPPER(FULL_NAME) like UPPER(:key))");
		}	
		stringBuilder.append(" ORDER BY FULL_NAME");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("sysGroupId", new DoubleType());
		query.addScalar("needChangePassword", new LongType());
		query.addScalar("changePasswordDate", new DateType());
		query.addScalar("newId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("phoneNumber", new StringType());
		query.addScalar("email", new StringType());
		query.addScalar("employeeCode", new StringType());
		query.addScalar("password", new StringType());
		query.addScalar("fullName", new StringType());
		query.addScalar("loginName", new StringType());
		query.addScalar("sysUserId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("key", "%" + obj.getKeySearch() + "%");
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public SysUserDTO getById(Long id) {
    	StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.SYS_GROUP_ID sysGroupId ");
		stringBuilder.append(",(SELECT CASE WHEN VALUE IS NULL THEN NAME ELSE (VALUE || ' - ' || NAME) END FROM SYS_GROUP WHERE SYS_GROUP_ID = T1.SYS_GROUP_ID) sysGroupName  ");
		stringBuilder.append(",T1.NEED_CHANGE_PASSWORD needChangePassword ");
		stringBuilder.append(",T1.CHANGE_PASSWORD_DATE changePasswordDate ");
		stringBuilder.append(",T1.NEW_ID newId ");
		stringBuilder.append(",(SELECT CASE WHEN VALUE IS NULL THEN NAME ELSE (VALUE || ' - ' || NAME) END FROM NEW WHERE NEW_ID = T1.NEW_ID) newName  ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.PHONE_NUMBER phoneNumber ");
		stringBuilder.append(",T1.EMAIL email ");
		stringBuilder.append(",T1.EMPLOYEE_CODE employeeCode ");
		stringBuilder.append(",T1.PASSWORD password ");
		stringBuilder.append(",T1.FULL_NAME fullName ");
		stringBuilder.append(",T1.LOGIN_NAME loginName ");
		stringBuilder.append(",T1.SYS_USER_ID sysUserId ");
		stringBuilder.append(",(SELECT CASE WHEN VALUE IS NULL THEN NAME ELSE (VALUE || ' - ' || NAME) END FROM SYS_USER WHERE SYS_USER_ID = T1.SYS_USER_ID) sysUserName  ");

    	stringBuilder.append("FROM SYS_USER T1 ");    	
    	stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND T1.SYS_USER_ID = :sysUserId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("sysGroupId", new DoubleType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("needChangePassword", new LongType());
		query.addScalar("changePasswordDate", new DateType());
		query.addScalar("newId", new LongType());
		query.addScalar("newName", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("phoneNumber", new StringType());
		query.addScalar("email", new StringType());
		query.addScalar("employeeCode", new StringType());
		query.addScalar("password", new StringType());
		query.addScalar("fullName", new StringType());
		query.addScalar("loginName", new StringType());
		query.addScalar("sysUserId", new LongType());
		query.addScalar("sysUserName", new StringType());
    	
		query.setParameter("sysUserId", id);
		query.setResultTransformer(Transformers.aliasToBean(SysUserDTO.class));
    	
		return (SysUserDTO) query.uniqueResult();
	}
	
	public StringBuilder getSelectAllQuery(){
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.SYS_GROUP_ID sysGroupId ");
		stringBuilder.append(",T1.NEED_CHANGE_PASSWORD needChangePassword ");
		stringBuilder.append(",T1.CHANGE_PASSWORD_DATE changePasswordDate ");
		stringBuilder.append(",T1.NEW_ID newId ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.PHONE_NUMBER phoneNumber ");
		stringBuilder.append(",T1.EMAIL email ");
		stringBuilder.append(",T1.EMPLOYEE_CODE employeeCode ");
		stringBuilder.append(",T1.PASSWORD password ");
		stringBuilder.append(",T1.FULL_NAME fullName ");
		stringBuilder.append(",T1.LOGIN_NAME loginName ");
		stringBuilder.append(",T1.SYS_USER_ID sysUserId ");
    	
    	stringBuilder.append("FROM CTCT_CAT_OWNER.SYS_USER T1 ");    	
    	
    	return stringBuilder;
	}
}
