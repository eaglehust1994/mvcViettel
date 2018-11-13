/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;
import com.viettel.wms.bo.SysUserQLKBO;
//import com.viettel.wms.dto.GoodsDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.wms.dto.SysUserQLKDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("sysUserwmsDAO")
public class SysUserQLKDAO extends BaseFWDAOImpl<SysUserQLKBO, Long> {

    public SysUserQLKDAO() {
        this.model = new SysUserQLKBO();
    }

    public SysUserQLKDAO(Session session) {
        this.session = session;
    }
    
	 @SuppressWarnings("unchecked")
	public List<SysUserQLKDTO> users(SysUserQLKDTO obj){

	  		StringBuilder sql = new StringBuilder("SELECT o.LOGIN_NAME loginName, "
	  				+ "o.EMPLOYEE_CODE employeeCode, "
	  				+ "o.FULL_NAME fullName, "
	  				+ "o.EMAIL email, "
	  				+ "o.SYS_USER_ID sysUserId, "
	  				+ "o.PHONE_NUMBER phoneNumber "
	  				+ "FROM VPS_OWNER.\"SYS_USER\" o "
	  			);
	  		
	  		
	  		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
	  		sqlCount.append(sql.toString());
	  		sqlCount.append(")");
	  		
	  		SQLQuery query= getSession().createSQLQuery(sql.toString());
	  		SQLQuery queryCount= getSession().createSQLQuery(sqlCount.toString());
	  	
	  		query.addScalar("sysUserId", new LongType());
	  		query.addScalar("loginName", new StringType());
	  		query.addScalar("employeeCode", new StringType());
	  		query.addScalar("fullName", new StringType());
	  		query.addScalar("email", new StringType());
	  		query.addScalar("phoneNumber", new StringType());
	  		query.setResultTransformer(Transformers.aliasToBean(SysUserQLKDTO.class));
	  		
	  		if(obj.getPage()!=null && obj.getPageSize()!=null){
	  			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		  		query.setMaxResults(obj.getPageSize().intValue());
	  		}
	  		
	  		
	  		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
	  		return query.list();
	  	}
	 
	 public List<SysUserQLKDTO> usersDropdown(SysUserQLKDTO obj){

	  		StringBuilder sql = new StringBuilder("SELECT o.LOGIN_NAME loginName, "
	  				+ "o.EMPLOYEE_CODE employeeCode, "
	  				+ "o.FULL_NAME fullName, "
	  				+ "o.EMAIL email, "
	  				+ "o.SYS_USER_ID sysUserId, "
	  				+ "o.PHONE_NUMBER phoneNumber "
	  				+ "FROM VPS_OWNER.\"SYS_USER\" o "
	  			);
	  		
	  		StringBuilder stringBuilder = new StringBuilder(sql);
			
			if(StringUtils.isNotEmpty(obj.getName())){
				stringBuilder.append( " WHERE upper(o.LOGIN_NAME) LIKE upper(:name) escape '&'");
			}
	  		
	  		
	  		SQLQuery query= getSession().createSQLQuery(stringBuilder.toString());
	  	
	  		query.addScalar("sysUserId", new LongType());
	  		query.addScalar("loginName", new StringType());
	  		query.addScalar("employeeCode", new StringType());
	  		query.addScalar("fullName", new StringType());
	  		query.addScalar("email", new StringType());
	  		query.addScalar("phoneNumber", new StringType());
	  		query.setResultTransformer(Transformers.aliasToBean(SysUserQLKDTO.class));
	  		
	  		if(obj.getPage()!=null && obj.getPageSize()!=null){
	  			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		  		query.setMaxResults(obj.getPageSize().intValue());
	  		}
	  		if (StringUtils.isNotEmpty(obj.getName())) {
				query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
			}
	  		return query.list();
	  	}
	 
	 @SuppressWarnings("unchecked")
		public List<SysUserQLKDTO> getForAutoComplete(SysUserQLKDTO obj) {
			String sql = "SELECT su.SYS_USER_ID sysUserId"	
				+ " ,su.LOGIN_NAME loginName"		
				+ " ,su.EMPLOYEE_CODE employeeCode,"
				+ " (CASE WHEN  su.PHONE_NUMBER is null THEN su.FULL_NAME ELSE su.FULL_NAME ||'-'|| su.PHONE_NUMBER END) fullName"
				+ " FROM VPS_OWNER.SYS_USER su"
				+ " WHERE su.STATUS=1 ";
			
			StringBuilder stringBuilder = new StringBuilder(sql);
				stringBuilder.append(" AND ROWNUM <=10 ");
				if(StringUtils.isNotEmpty(obj.getFullName())){
					stringBuilder.append(" AND upper(su.FULL_NAME) LIKE upper(:fullName) escape '&' OR upper(su.EMPLOYEE_CODE) LIKE upper(:value) escape '&')");
					}
				if(StringUtils.isNotEmpty(obj.getName())){
				stringBuilder.append(" AND (upper(su.FULL_NAME) LIKE upper(:name) escape '&' OR upper(su.EMPLOYEE_CODE) LIKE upper(:name) escape '&')");
					}
			
			
	  		
			stringBuilder.append(" ORDER BY su.EMPLOYEE_CODE");
			
			SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
			
			query.addScalar("sysUserId", new LongType());
			query.addScalar("loginName", new StringType());
			query.addScalar("employeeCode", new StringType());
			query.addScalar("fullName", new StringType());
		
			query.setResultTransformer(Transformers.aliasToBean(SysUserQLKDTO.class));

			if (StringUtils.isNotEmpty(obj.getName())) {
				query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
			}
			if (StringUtils.isNotEmpty(obj.getFullName())) {
				query.setParameter("fullName", "%" + ValidateUtils.validateKeySearch(obj.getFullName()) + "%");
			}

			return query.list();
		}

	 
//		Tìm kiếm người dùng trong popup
		@SuppressWarnings("unchecked")
		public List<SysUserQLKDTO> doSearchUserInPopup(SysUserQLKDTO obj) {
			StringBuilder sql = new StringBuilder("SELECT o.LOGIN_NAME loginName, "
	  				+ "o.EMPLOYEE_CODE employeeCode, "
	  				+ "o.FULL_NAME fullName, "
	  				+ "o.EMAIL email, "
	  				+ "o.SYS_USER_ID sysUserId, "
	  				+ "o.PHONE_NUMBER phoneNumber "
	  				+ "FROM VPS_OWNER.\"SYS_USER\" o "
	  			);
	  		
			
			if(StringUtils.isNotEmpty(obj.getName())){
				sql.append( " WHERE upper(o.FULL_NAME) LIKE upper(:name) escape '&' OR upper(o.EMPLOYEE_CODE) LIKE upper(:name) escape '&'");
			}
	  		
	  		
			StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
	  		sqlCount.append(sql.toString());
	  		sqlCount.append(")");
	  		
	  		SQLQuery query= getSession().createSQLQuery(sql.toString());
	  		SQLQuery queryCount= getSession().createSQLQuery(sqlCount.toString());
	  	
	  		query.addScalar("sysUserId", new LongType());
	  		query.addScalar("loginName", new StringType());
	  		query.addScalar("employeeCode", new StringType());
	  		query.addScalar("fullName", new StringType());
	  		query.addScalar("email", new StringType());
	  		query.addScalar("phoneNumber", new StringType());
	  		query.setResultTransformer(Transformers.aliasToBean(SysUserQLKDTO.class));
	  		
	  			
	  		if (StringUtils.isNotEmpty(obj.getName())) {
				query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
				queryCount.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
			}
	  		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
	  		query.setMaxResults(obj.getPageSize().intValue());
	  		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
	  		return query.list();
		}
		
		
		public SysUserQLKDTO getUserInfoByLoginName(String loginName){
			/*StringBuilder sql = new StringBuilder("SELECT "
					+ "SU.SYS_USER_ID sysUserId"
					+ ",SU.LOGIN_NAME loginName"
					+ ",SU.FULL_NAME fullName"
					+ ",SU.PASSWORD password"
					+ ",SU.EMPLOYEE_CODE employeeCode"
					+ ",SU.EMAIL email"
					+ ",SU.PHONE_NUMBER phoneNumber"
					+ ",SU.STATUS status"
					+ ",SU.SYS_GROUP_ID departmentId"
					+ ",DEP.NAME departmentName"
					+ " FROM VPS_OWNER.SYS_USER SU"
					+ " LEFT JOIN VPS_OWNER.SYS_GROUP DEP ON DEP.SYS_GROUP_ID=SU.SYS_GROUP_ID"
					+ " WHERE SU.LOGIN_NAME=:loginName");
			
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
			query.addScalar("sysUserId", new LongType());
	  		query.addScalar("loginName", new StringType());
	  		query.addScalar("employeeCode", new StringType());
	  		query.addScalar("fullName", new StringType());
	  		query.addScalar("email", new StringType());
	  		query.addScalar("phoneNumber", new StringType());
	  		query.addScalar("password", new StringType());
	  		query.addScalar("status", new StringType());
	  		query.addScalar("departmentId", new LongType());
	  		query.addScalar("departmentName", new StringType());
	  		
	  		
	  		query.setResultTransformer(Transformers.aliasToBean(SysUserQLKDTO.class));
	  		query.setParameter("loginName", loginName);
	  		return (SysUserQLKDTO) query.uniqueResult();*/
			return null;
		}
}
