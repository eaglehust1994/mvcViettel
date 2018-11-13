/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ManufacturerBO;
import com.viettel.wms.dto.ICntContractDTO;
import com.viettel.wms.dto.ManufacturerDTO;
import com.viettel.wms.dto.ShipmentDTO;
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
@Repository("manufacturerDAO")
public class ManufacturerDAO extends BaseFWDAOImpl<ManufacturerBO, Long> {

    public ManufacturerDAO() {
        this.model = new ManufacturerBO();
    }

    public ManufacturerDAO(Session session) {
        this.session = session;
    }
    
    //tim id va ten hang san xuat
    public List<ManufacturerDTO> getAllNameAndId(){
    	StringBuilder sql = new StringBuilder("SELECT M.MANUFACTURER_ID manufacturerId, M.NAME name"
				+ " FROM CAT_OWNER.MANUFACTURER M where M.STATUS=1 ");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("manufacturerId", new LongType());
		query.addScalar("name", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ManufacturerDTO.class));

		return query.list();
    }
    //Autocomplete hãng sản xuất
    public List<ManufacturerDTO> getForAutoComplete(ManufacturerDTO obj) {
		String sql = "SELECT SC.MANUFACTURER_ID manufacturerId,"
				   + "SC.NAME manufacturerName"
				   + " FROM CAT_OWNER.MANUFACTURER SC"
				   + " WHERE SC.STATUS=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);
		
		if(StringUtils.isNotEmpty(obj.getManufacturerName())){
			stringBuilder.append(" AND upper(SC.NAME) LIKE upper(:manufacturerName)");
		}
		stringBuilder.append(" ORDER BY SC.NAME");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("manufacturerName", new StringType());
		query.addScalar("manufacturerId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(ManufacturerDTO.class));

		if (StringUtils.isNotEmpty(obj.getManufacturerName())) {
			query.setParameter("manufacturerName", "%" + obj.getManufacturerName() + "%");
		}
		return query.list();
	}
    //End
    //tim id va ten hang san xuat theo id
    public ManufacturerDTO getAllNameById(Long id){
    	StringBuilder sql = new StringBuilder("SELECT M.NAME name"
				+ " FROM CAT_OWNER.MANUFACTURER M where M.STATUS=1 AND M.MANUFACTURER_ID= :manufacturerId");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("name", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(ManufacturerDTO.class));
		
		query.setParameter("manufacturerId",  id);
		
		return (ManufacturerDTO) query.uniqueResult();
    }
}
