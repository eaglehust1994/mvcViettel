package com.viettel.asset.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.viettel.asset.bo.VSysUser;
import com.viettel.asset.dto.VSysUserDto;
import com.viettel.ktts2.common.BusinessException;

@Repository("vSysUserDao")
public class VSysUserDao extends HibernateDao<VSysUser, Long> {
	public VSysUser getVSysUserByLoginName(String username){
		Criteria cri = currentSession().createCriteria(VSysUser.class);
		cri.add(Restrictions.ilike(VSysUser.Columns.LOGIN_NAME, username,MatchMode.EXACT));
		@SuppressWarnings("unchecked")
		List<VSysUser> lst=cri.list();
		if(lst.isEmpty()||lst.size()>1){
			throw new BusinessException("sysUser.not.exists.username");			
		}
		return lst.get(0);
	}
	
	public VSysUser getCatEmployeeInfoByUserId(Long  userId){
		String sql="select ce.full_name fullName ,ce.id catEmployeeId  from  USER_EMPLOYEE ue "
					+" left join cat_employee ce on ce.id=ue.employee_id"
					+ " where ue.user_id=:userId";
		SQLQuery query=currentSession().createSQLQuery(sql);
		query.setParameter("userId", userId);
		//query.addEntity(VSysUser.class);
		query.addScalar("fullName",StandardBasicTypes.STRING);
		query.addScalar("catEmployeeId",StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(VSysUser.class));
		List<VSysUser> lst=query.list();
		if(lst.isEmpty()){
			return null;
		}
		else if(lst.size()>1){
			throw new BusinessException("sysUser.invalid.duplicate");		
		}
		return lst.get(0);
	}
}
