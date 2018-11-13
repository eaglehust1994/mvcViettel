/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ProducingCountryBO;
import com.viettel.wms.dto.ManufacturerDTO;
import com.viettel.wms.dto.ProducingCountryDTO;
import com.viettel.wms.dto.StockCellDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("producingCountryDAO")
public class ProducingCountryDAO extends BaseFWDAOImpl<ProducingCountryBO, Long> {

    public ProducingCountryDAO() {
        this.model = new ProducingCountryBO();
    }

    public ProducingCountryDAO(Session session) {
        this.session = session;
    }
    
    public List<ProducingCountryDTO> getAllNameAndId(){
    	StringBuilder sql = new StringBuilder("SELECT P.PRODUCING_COUNTRY_ID producingCountryId, P.NAME name"
				+ " FROM CAT_OWNER.PRODUCING_COUNTRY P WHERE P.STATUS=1 ");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("producingCountryId", new LongType());
		query.addScalar("name", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ProducingCountryDTO.class));

		return query.list();
    }
    //Autocomplete nước sản xuất
    public List<ProducingCountryDTO> getForAutoComplete(ProducingCountryDTO obj) {
		String sql = "SELECT SC.PRODUCING_COUNTRY_ID producingCountryId,"
				   + "SC.NAME producingCountryName"
				   + " FROM CAT_OWNER.PRODUCING_COUNTRY SC"
				   + " WHERE SC.STATUS=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);
		
		if(StringUtils.isNotEmpty(obj.getProducingCountryName())){
			stringBuilder.append(" AND upper(SC.NAME) LIKE upper(:producingCountryName)");
		}
		stringBuilder.append(" ORDER BY SC.NAME");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("producingCountryName", new StringType());
		query.addScalar("producingCountryId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(ProducingCountryDTO.class));

		if (StringUtils.isNotEmpty(obj.getProducingCountryName())) {
			query.setParameter("producingCountryName", "%" + obj.getProducingCountryName() + "%");
		}
		return query.list();
	}
    //End
    
    public ProducingCountryDTO getAllNameById(Long id){
    	StringBuilder sql = new StringBuilder("SELECT P.NAME name"
				+ " FROM CAT_OWNER.PRODUCING_COUNTRY P WHERE P.STATUS=1 AND P.PRODUCING_COUNTRY_ID =:producingCountryId");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("name", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ProducingCountryDTO.class));
		
		query.setParameter("producingCountryId",  id);

		return (ProducingCountryDTO) query.uniqueResult();
    }
}
