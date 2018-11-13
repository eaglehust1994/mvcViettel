/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.springframework.stereotype.Repository;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.VConstructionHcqtBO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.erp.dto.VConstructionsHcqtSearchDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("vConstructionHcqtDAO")
public class VConstructionHcqtDAO extends BaseFWDAOImpl<VConstructionHcqtBO, Long> {

    public VConstructionHcqtDAO() {
        this.model = new VConstructionHcqtBO();
    }

    public VConstructionHcqtDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
	public List<VConstructionHcqtBO> getAllandSearch(VConstructionsHcqtSearchDTO dto , TotalNumDTO totalNum ) {

    	//Query queryCount = getSession().createQuery("SELECT count(*) from V_CONSTRUCTION_HCQT ");
    	
    	String hql = Joiner.on(" ").join("  from v_constructions_hcqt p where 1=1 ",
		
    	dto.getChecktype() == 1 ? " and p.constructId in(select c.vconstructionHcqt.constructId from constructionacceptance c  where c.statusCa = 2)" :"",	
    	StringUtils.isNotEmpty(dto.getConstrtCode()) ? " and upper(p.constrtCode) like :constrtCode": "",
		StringUtils.isNotEmpty(dto.getContractCode()) ? " and upper(p.contractCode) like :contractCode" : "",
		StringUtils.isNotEmpty(dto.getConstrtName()) ? " and upper(p.constrtName) like :constrtName" : "",
		dto.getProjectId()!= null ? " and p.investProjectId =:investProjectId" : "",
		dto.getPartnerId()!= null ? " and p.constructorId =:constructorId": "",
		dto.getConstrType()!=null ? " and p.constrType =:constrType " : "",
		dto.getProvinceId()!=null ? " and p.provinceId =:provinceId " : "");
	 //   dto.getEmployeeId()!=null ? " and p.constructId in(select s.vconstructionhcqt.constructId from settlementright s  where s.catemployee.id = "+ dto.getEmployeeId() +")": "");
    	
		Query q = getSession().createQuery(hql);
		Query queryCount = getSession().createQuery("SELECT count(*) " +hql);
		
		
		if (StringUtils.isNotEmpty(dto.getConstrtCode())) {
			q.setParameter("constrtCode", "%"+ dto.getConstrtCode().trim().toUpperCase() +"%");
			queryCount.setParameter("constrtCode",  "%"+dto.getConstrtCode().trim().toUpperCase() +"%");
		}
		if (StringUtils.isNotEmpty(dto.getContractCode())) {
			q.setParameter("contractCode",  "%"+dto.getContractCode().trim().toUpperCase() +"%");
			queryCount.setParameter("contractCode",  "%"+dto.getContractCode().trim().toUpperCase() +"%");
		}	
		if (StringUtils.isNotEmpty(dto.getConstrtName())) {
			q.setParameter("constrtName",  "%"+dto.getConstrtName().trim().toUpperCase() +"%");
			queryCount.setParameter("constrtName",  "%"+dto.getConstrtName().trim().toUpperCase() +"%");
		}	
		
		
		if (dto.getProjectId()!= null) {
			q.setParameter("investProjectId",  dto.getProjectId());
			queryCount.setParameter("investProjectId",  dto.getProjectId());
		}
		if (dto.getPartnerId()!= null) {
			q.setParameter("constructorId",  dto.getPartnerId());
			queryCount.setParameter("constructorId",  dto.getPartnerId());
		}	
		
		if (dto.getConstrType()!= null) {
			q.setParameter("constrType",  dto.getConstrType());
			queryCount.setParameter("constrType",  dto.getConstrType());
		}	
		if (dto.getProvinceId()!= null) {
			q.setParameter("provinceId",  dto.getProvinceId());
			queryCount.setParameter("provinceId",  dto.getProvinceId());
		}	
		
		
		
		
		
		q.setFirstResult((dto.getPage().intValue() -1) * dto.getPageSize().intValue() );
		q.setMaxResults(dto.getPageSize().intValue());
		totalNum.setNum(Integer.valueOf(queryCount.uniqueResult().toString()));
		totalNum.setRownum((dto.getPage().intValue() -1) * dto.getPageSize().intValue());
		//queryCount.uniqueResult().toString();
		
		//List<VConstructionHcqtBO> abc =q.setCacheable(true).list();
		
		
		return q.setCacheable(true).list();
	}
    
    public String queryCount() {

    	Query queryCount = getSession().createQuery(" SELECT count(*) from v_constructions_hcqt ");
		return queryCount.uniqueResult().toString();
	}
    
    public List<VConstructionHcqtDTO> getContractTotalValue(Long contractId){
    	StringBuilder sql = new StringBuilder("SELECT "
    			+" CONTRACT_TOTAL_VALUE contract_total_value"	
				+" FROM V_CONSTRUCTION_HCQT" );
    	
    	if (contractId != null) {
    		sql.append(" WHERE CONSTRUCT_ID = " + contractId);
		}
    	
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("contract_total_value", DoubleType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(VConstructionHcqtDTO.class));
		
		return query.list();
    }
}
