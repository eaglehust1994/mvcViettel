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
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.CatPartnersBO;
import com.viettel.erp.dto.CatPartnersDTO;
import com.viettel.erp.dto.CatPartnersSearchDTO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("catPartnersDAO")
public class CatPartnersDAO extends BaseFWDAOImpl<CatPartnersBO, Long> {

    public CatPartnersDAO() {
        this.model = new CatPartnersBO();
    }

    public CatPartnersDAO(Session session) {
        this.session = session;
    }
    
    public List<CatPartnersBO> doSearch(CatPartnersSearchDTO dto, TotalNumDTO totalNum) {

    	String hql  = Joiner.on(" ").join("from cat_partners p where 1=1 ",
				StringUtils.isNotEmpty(dto.getPartnerName()) ? " and upper(p.partnerName) like :partnerName ":"",
				StringUtils.isNotEmpty(dto.getName()) ? " and upper(p.name) like  :name": "",
				StringUtils.isNotEmpty(dto.getStationCodeExpected()) ? " and upper(p.stationCodeExpected) like :stationCodeExpected ": "",
				StringUtils.isNotEmpty(dto.getAddress()) ? " and upper(p.address )like :address": "",
				StringUtils.isNotEmpty(dto.getPhone()) ? " and p.phone like :phone" : "",
				StringUtils.isNotEmpty(dto.getFax()) ? " and p.fax like :fax ": "",
				StringUtils.isNotEmpty(dto.getTaxCode()) ? " and upper(p.taxCode) like  :taxCode": "");
    	

		Query q = getSession().createQuery(hql);
		Query queryCount = getSession().createQuery("SELECT count(*) " +hql);
		
		
		if (StringUtils.isNotEmpty(dto.getPartnerName())) {
			q.setParameter("partnerName", "%"+ dto.getPartnerName().trim().toUpperCase() +"%");
			queryCount.setParameter("partnerName",  "%"+dto.getPartnerName().trim().toUpperCase() +"%");
		}
		if (StringUtils.isNotEmpty(dto.getName())) {
			q.setParameter("name", "%"+ dto.getName().trim().toUpperCase() +"%");
			queryCount.setParameter("name",  "%"+dto.getName().trim().toUpperCase() +"%");
		}
		if (StringUtils.isNotEmpty(dto.getStationCodeExpected())) {
			q.setParameter("stationCodeExpected", "%"+ dto.getStationCodeExpected().trim().toUpperCase() +"%");
			queryCount.setParameter("stationCodeExpected",  "%"+dto.getStationCodeExpected().trim().toUpperCase() +"%");
		}
		if (StringUtils.isNotEmpty(dto.getAddress())) {
			q.setParameter("address", "%"+ dto.getAddress().trim().toUpperCase() +"%");
			queryCount.setParameter("address",  "%"+dto.getAddress().trim().toUpperCase() +"%");
		}
		if (StringUtils.isNotEmpty(dto.getPhone())) {
			q.setParameter("phone", "%"+ dto.getPhone().trim().toUpperCase() +"%");
			queryCount.setParameter("phone",  "%"+dto.getPhone().trim().toUpperCase() +"%");
		}
		if (StringUtils.isNotEmpty(dto.getFax())) {
			q.setParameter("fax", "%"+ dto.getFax().trim().toUpperCase() +"%");
			queryCount.setParameter("fax",  "%"+dto.getFax().trim().toUpperCase() +"%");
		}
		if (StringUtils.isNotEmpty(dto.getTaxCode())) {
			q.setParameter("taxCode", "%"+ dto.getTaxCode().trim().toUpperCase() +"%");
			queryCount.setParameter("taxCode",  "%"+dto.getTaxCode().trim().toUpperCase() +"%");
		}
		
		
		
		
		
		totalNum.setNum(Integer.valueOf(queryCount.uniqueResult().toString()));
		
		if(!dto.getIsSize()){
			q.setFirstResult((dto.getPage().intValue() -1) * dto.getPageSize().intValue() );
			q.setMaxResults(dto.getPageSize().intValue());	
		}else{
			q.setMaxResults(10);
		}
		return q.setCacheable(true).list();
	}
    
    public List<CatPartnersDTO> getPartnersName() {
		String sql = "SELECT CONSTR_CONSTRUCTIONS.CONSTRT_CODE, ESTIMATES_WORK_ITEMS.WORK_ITEM_CODE, ESTIMATES_WORK_ITEMS.WORK_ITEM_NAME,ESTIMATES_WORK_ITEMS.UNIT, ESTIMATES_WORK_ITEMS.WORK_AMOUNT,ESTIMATES_WORK_ITEMS.UNIT_PRICE,CONSTR_CONSTRUCTIONS.IS_ACCEPTED FROM ESTIMATES_WORK_ITEMS LEFT JOIN CONSTR_CONSTRUCTIONS ON CONSTR_CONSTRUCTIONS.CONSTRUCT_ID = ESTIMATES_WORK_ITEMS.CONSTRUCTION_ID ";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.addScalar("partnerName", StringType.INSTANCE);
		
//		query.setParameter("roll", a);
		query.setResultTransformer(Transformers.aliasToBean(CatPartnersDTO.class));
		return query.list();
	}
    
    public List<CatPartnersDTO> getForAutoComplete(CatPartnersDTO obj) {
    	String sql = "SELECT PARTNER_ID partnerId"	
				+" ,PARTNER_NAME partnerName"		
				+" ,ADDRESS address"		
				+" FROM CAT_PARTNERS"
				+" WHERE IS_ACTIVE = '1' ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		obj.setSetIsSize(true);
		stringBuilder.append(obj.getSetIsSize() ? " AND ROWNUM <=10" : "");
//		System.out.println("TEST + "+ obj +" = "+ obj.getName() + "  " + obj.getAddress());
		stringBuilder.append(StringUtils.isNotEmpty(obj.getName()) ? " AND (upper(PARTNER_NAME) LIKE upper(:partnerName)" + (StringUtils.isNotEmpty(obj.getAddress()) ? " OR upper(ADDRESS) LIKE upper(:address)" : "") + ")" : (StringUtils.isNotEmpty(obj.getAddress()) ? "AND upper(ADDRESS) LIKE upper(:address)" : ""));
		stringBuilder.append(" ORDER BY PARTNER_NAME");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("partnerId", LongType.INSTANCE);
		query.addScalar("partnerName", StringType.INSTANCE);
		query.addScalar("address", StringType.INSTANCE);
	
		query.setResultTransformer(Transformers.aliasToBean(CatPartnersDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("partnerName", "%" + obj.getName() + "%");
		}
		
		if (StringUtils.isNotEmpty(obj.getAddress())) {
			query.setParameter("address", "%" + obj.getAddress() + "%");
		}

		return query.list();
	}
    
}
