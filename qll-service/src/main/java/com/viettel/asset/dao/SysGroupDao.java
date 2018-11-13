package com.viettel.asset.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.viettel.asset.bo.SysGroup;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.ktts2.common.UString;

import java.util.List;

@Repository("sysGroupDao")
public class SysGroupDao extends HibernateDao<SysGroup, Long> {

	@SuppressWarnings("unchecked")
	public List<SysGroup> filterStartWidth(AutocompleteSearchDto searchForm){
		Criteria cri = currentSession().createCriteria(SysGroup.class);
		if(UString.isNotNullAndWhitespace(searchForm.getKeySearch())){
			String search = UString.trim(searchForm.getKeySearch());
			Disjunction disjunction=Restrictions.disjunction();
			disjunction.add(Restrictions.ilike(SysGroup.Columns.GROUP_CODE,search,MatchMode.START));
			disjunction.add(Restrictions.ilike(SysGroup.Columns.NAME,search,MatchMode.START));
			cri.add(disjunction);
		}
		cri.add(Restrictions.eq(SysGroup.Columns.STATUS,SysGroup.Constants.ACTIVE));
		cri.setMaxResults(searchForm.getPageSize());
		cri.addOrder(Order.asc(SysGroup.Columns.GROUP_CODE));
		return cri.list();		
	}

	@SuppressWarnings("unchecked")
	public List<SysGroup> filterAll(AutocompleteSearchDto searchForm, List<Long> ids) {
		Criteria cri = currentSession().createCriteria(SysGroup.class);
		if(UString.isNotNullAndWhitespace(searchForm.getKeySearch())){
			String search = UString.trim(searchForm.getKeySearch().toUpperCase());
			Disjunction disjunction=Restrictions.disjunction();
			disjunction.add(Restrictions.like(SysGroup.Columns.GROUP_CODE,search,MatchMode.ANYWHERE));
			cri.add(disjunction);
		}
		if(!ids.isEmpty()){
			cri.add(Restrictions.not(Restrictions.in(SysGroup.Columns.GROUP_ID, ids)));
		}
		cri.add(Restrictions.eq(SysGroup.Columns.STATUS,SysGroup.Constants.ACTIVE));
		cri.setMaxResults(searchForm.getPageSize()-ids.size());
		cri.addOrder(Order.asc(SysGroup.Columns.GROUP_CODE));
		return cri.list();	
	}
	
}
