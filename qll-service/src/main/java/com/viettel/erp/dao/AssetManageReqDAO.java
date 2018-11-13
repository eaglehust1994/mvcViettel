/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.viettel.erp.bo.AssetManageReqBO;
import com.viettel.erp.dto.AssetManageReqDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("assetManageReqDAO")
public class AssetManageReqDAO extends BaseFWDAOImpl<AssetManageReqBO, Long> {

    public AssetManageReqDAO() {
        this.model = new AssetManageReqBO();
    }

    public AssetManageReqDAO(Session session) {
        this.session = session;
    }
    
    public List<AssetManageReqDTO> getValueSupplies(Long contractId){
    	StringBuilder sql = new StringBuilder("SELECT "
    			+" SUM(NVL(y.quantity,0) *NVL(z.ORIGINAL_PRICE,0)) recoveryMaterialValue"	
				+" FROM asset_manage_req x, asset_manage_req_entity y, mer_entity z"
				+" WHERE x.manage_req_id = y.req_id "
				+ "AND y.mer_entity_id   =z.mer_entity_id "
				+ "AND x.status          =4  " );
		/*SELECT SUM(y.quantity * z.ORIGINAL_PRICE)
	    FROM asset_manage_req x,
	      asset_manage_req_entity y,
	      mer_entity z
	    WHERE  x.manage_req_id   =y.req_id
	    AND y.mer_entity_id   =z.mer_entity_id
	    AND x.status          =4
	    AND x.CONSTRUCT_ID  =?;*/
    	
    	if (contractId != null) {
    		sql.append(" AND x.CONSTRUCT_ID = :contractId");
		}
    	
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("recoveryMaterialValue", LongType.INSTANCE);
		if (contractId != null) {
			query.setParameter("contractId", contractId);
		}
		
		query.setResultTransformer(Transformers.aliasToBean(AssetManageReqDTO.class));
		return query.list();
    }
    
    public List<AssetManageReqDTO> getTotalPrice(Long contractId){
    	StringBuilder sql = new StringBuilder("SELECT "
    			+" SUM(NVL(a.total_price,0)) exportMaterialValue"	
				+" FROM ware_report_exp_price a"
				+ " join ware_exp_note b on (a.exp_note_id = b.delivery_note_id)"
				+"  join constr_constructions c on (b.CONSTRUCTION_ID = c.CONSTRUCT_ID)"
				+ " WHERE b.status = 2 and b.CONSTRUCT_EXP_TYPE = 1 ");
		/*select sum(a.total_price) from ware_report_exp_price a join ware_exp_note b on (a.exp_note_id = b.delivery_note_id)
	    join constr_constructions c on (b.CONSTRUCTION_ID = c.CONSTRUCT_ID)
	    where b.status = 2 and b.CONSTRUCT_EXP_TYPE = 1 and c.CONSTRUCT_ID = 69164445;*/
    	
    	if (contractId != null) {
    		sql.append(" AND c.CONSTRUCT_ID = :contractId");
		}
    	
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("exportMaterialValue", LongType.INSTANCE);
		if (contractId != null) {
			query.setParameter("contractId", contractId);
		}
		query.setResultTransformer(Transformers.aliasToBean(AssetManageReqDTO.class));
		return query.list();
    }
}
