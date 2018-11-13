/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.BMaterialAcceptMerListBO;
import com.viettel.erp.dto.BMaterialAcceptMerListDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("bMaterialAcceptMerListDAO")
public class BMaterialAcceptMerListDAO extends BaseFWDAOImpl<BMaterialAcceptMerListBO, Long> {

    public BMaterialAcceptMerListDAO() {
        this.model = new BMaterialAcceptMerListBO();
    }

    public BMaterialAcceptMerListDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked") 
    public List<BMaterialAcceptMerListDTO> getAccpetMerList(Long bMaterialAcceptanceId){
    	System.out.println("mm[[[[[[[[[[[[[[[");
	 StringBuffer sql = new StringBuffer(
	 "SELECT "
	 +"bma.B_MATERIAL_ACCEPTANCE_ID bMaterialAcceptanceId,"
	 +"bmal.CONTRACT_QUANTITY contractQuantity , bmal.B_MAT_ACC_MER_LIST_ID bMatAccMerListId,"		 
	 +"bmal.MATERIAL_NAME materialName, bmal.UNIT unit, bmal.QUANTITY quantity, "
     +"bmal.SPECIFICATION_ORIGIN specificationOrigin, bmal.QUALITY quality "
	 +"FROM "
     +"B_MATERIAL_ACCEPTANCE bma INNER JOIN B_MATERIAL_ACCEPT_MER_LIST bmal "
	 +"ON bma.B_MATERIAL_ACCEPTANCE_ID = bmal.B_MATERIAL_ACCEPTANCE_ID "
     +"WHERE "
	 +"bmal.B_MATERIAL_ACCEPTANCE_ID = :bMaterialAcceptanceId"
	 );
	 SQLQuery query = getSession().createSQLQuery(sql.toString());
	 query.addScalar("bMaterialAcceptanceId", LongType.INSTANCE);
	 query.addScalar("contractQuantity", new DoubleType());
	 query.addScalar("bMatAccMerListId", LongType.INSTANCE);
	 query.addScalar("materialName", StringType.INSTANCE);
	 query.addScalar("unit", StringType.INSTANCE);
	 query.addScalar("quantity", new DoubleType());
	 query.addScalar("specificationOrigin", StringType.INSTANCE);
	 query.addScalar("quality", StringType.INSTANCE);
	 query.setParameter("bMaterialAcceptanceId", bMaterialAcceptanceId);
	 query.setResultTransformer(Transformers.aliasToBean(BMaterialAcceptMerListDTO.class));
	 List<BMaterialAcceptMerListDTO> list =  query.list();
		return list;
 }
    
}
