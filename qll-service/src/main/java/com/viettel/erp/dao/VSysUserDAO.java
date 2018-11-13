/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.bo.VSysUserBO;
import com.viettel.erp.dto.VSysUserDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("vSysUserDAO")
public class VSysUserDAO extends BaseFWDAOImpl<VSysUserBO, Long> {

	public VSysUserDAO() {
		this.model = new VSysUserBO();
	}

	public VSysUserDAO(Session session) {
		this.session = session;
	}

	public VSysUserDTO getUserByLoginName(String loginName) {
		/*
		 * Hanhls1 : comment chuyenenr sang left join
		 */
		//Object[] result = (Object[]) getSession().createQuery("select u, c from user u, catemployee c, userEmpl ue where u.loginName = :loginName and u.userId = ue.userId and ue.employeeId = c.id")
				Object[] result = (Object[]) getSession().createQuery("select u, c from user u, catemployee c, userEmpl ue where u.loginName = :loginName and u.userId = ue.userId and ue.employeeId = c.id")
				.setParameter("loginName", loginName).setMaxResults(1).uniqueResult();
		
		VSysUserDTO dto ;
		if (result != null) {
			VSysUserBO sysUserBO = (VSysUserBO) result[0];
			CatEmployeeBO catEmployeeBO = (CatEmployeeBO) result[1];
			
			dto = sysUserBO.toDTO();
			dto.setCatEmployeeId(NumberUtils.toLong(catEmployeeBO.getId()));
			dto.setFullName(catEmployeeBO.getFullName());
		}else{
			VSysUserBO sysYser=(VSysUserBO)getSession().createQuery(" from user u where u.loginName = :loginName").setParameter("loginName", loginName).setMaxResults(1).uniqueResult();
			if(sysYser!=null){
				dto=sysYser.toDTO();
			}else{
				dto=new VSysUserDTO();
			}
		}
		
		return dto;
	}
	
	public Long getGroupId(Long userId){
		StringBuffer sqlCheck = new StringBuffer("select GROUP_ID groupId "
				+ " from V_SYS_USER where USER_ID = :userId");
		SQLQuery query = getSession().createSQLQuery(sqlCheck.toString());
		query.addScalar("groupId", LongType.INSTANCE);
		query.setParameter("userId", userId);
		Long k = (Long) query.uniqueResult();
		return k;
	}
}
