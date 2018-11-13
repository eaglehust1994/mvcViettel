/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.ProjInvestProjectBO;
import com.viettel.erp.bo.VConstructionHcqtBO;
import com.viettel.erp.dto.ProjInvestProjectSearchDTO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.erp.dto.VConstructionsHcqtSearchDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("projInvestProjectDAO")
public class ProjInvestProjectDAO extends BaseFWDAOImpl<ProjInvestProjectBO, Long> {

    public ProjInvestProjectDAO() {
        this.model = new ProjInvestProjectBO();
    }

    public ProjInvestProjectDAO(Session session) {
        this.session = session;
    }
    public List<ProjInvestProjectBO> getAll() {

		Query q = getSession().createQuery("from proj_invest_project");

	    q.setMaxResults(100);
		return q.setCacheable(true).list();
	}
    public List<ProjInvestProjectBO> doSearch(ProjInvestProjectSearchDTO dto, TotalNumDTO totalNum) {

		String hql = Joiner.on(" ").join("from proj_invest_project p where 1=1 ",
				StringUtils.isNotEmpty(dto.getCode()) ? " and upper( p.code) like :code " : "",
				StringUtils.isNotEmpty(dto.getName()) ? " and upper(p.name) like :name" : "",
				dto.getStatusCode() != null ? " and p.statusCode =:statusCode " : "");
				//dto.getType()!= null ? " and p.type = " + dto.getType() : "");
		
		Query q = getSession().createQuery(hql);
		Query queryCount = getSession().createQuery("SELECT count(*) " +hql);
		
		
		if (StringUtils.isNotEmpty(dto.getCode())) {
			q.setParameter("code", "%"+ dto.getCode().trim().toUpperCase() +"%");
			queryCount.setParameter("code",  "%"+dto.getCode().trim().toUpperCase() +"%");
		}
		
		if (StringUtils.isNotEmpty(dto.getName())) {
			q.setParameter("name", "%"+ dto.getName().trim().toUpperCase() +"%");
			queryCount.setParameter("name",  "%"+dto.getName().trim().toUpperCase() +"%");
		}
		if (dto.getStatusCode()!= null) {
			q.setParameter("statusCode",  dto.getStatusCode());
			queryCount.setParameter("statusCode",  dto.getStatusCode());
		}
		
		
		totalNum.setNum(Integer.valueOf(queryCount.uniqueResult().toString()));
		
		if(!dto.getIsSize()){
			q.setFirstResult((dto.getPage().intValue() -1) * dto.getPageSize().intValue() );
			q.setMaxResults(dto.getPageSize().intValue());	
		}else{
			q.setMaxResults(10);
		}
		return q.list();
		//return q.setCacheable(true).list();
	}
}
