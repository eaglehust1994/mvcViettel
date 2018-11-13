/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockCellBO;
import com.viettel.wms.dto.OrderGoodsDTO;
import com.viettel.wms.dto.StockCellDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
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
@Repository("stockCellDAO")
public class StockCellDAO extends BaseFWDAOImpl<StockCellBO, Long> {

    public StockCellDAO() {
        this.model = new StockCellBO();
    }

    public StockCellDAO(Session session) {
        this.session = session;
    }
    
    public List<StockCellDTO> doSearch(StockCellDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT STC.STOCK_CELL_ID stockCellId, STC.CODE code, STC.DESCRIPTION description, STC.STOCK_ID stockId"
				+ " FROM CAT_OWNER.STOCK_CELL STC INNER JOIN CAT_OWNER.STOCK ST ON (ST.STOCK_ID = STC.STOCK_ID) WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(STC.CODE) LIKE upper(:keySearch)");
		}

		sql.append(" ORDER BY CODE");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("stockCellId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("stockId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockCellDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	public StockCellDTO getbycode(String code) {
		StringBuilder sql = new StringBuilder("SELECT STC.STOCK_CELL_ID stockCellId, STC.CODE code, STC.DESCRIPTION description, STC.STOCK_ID stockId"
				+ " FROM CAT_OWNER.STOCK_CELL STC WHERE upper(STC.CODE)=upper(:code)");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("stockCellId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("stockId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(StockCellDTO.class));

		query.setParameter("code", code);

		return (StockCellDTO) query.uniqueResult();
	}
	
	
	public List<StockCellDTO> searchStockCellFromCode(String idCode){
		StringBuilder sql = new StringBuilder("SELECT STC.STOCK_CELL_ID stockCellId, STC.CODE code, STC.DESCRIPTION description"
				+ " FROM CAT_OWNER.STOCK_CELL STC  WHERE STC.CODE =:idcode ");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("stockCellId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("description", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockCellDTO.class));

		query.setParameter("idcode", idCode);
		return query.list();
	}
	
	public List<StockCellDTO> getAllStockCellCode(){
		StringBuilder sql = new StringBuilder("SELECT STC.CODE code"
				+ " FROM CAT_OWNER.STOCK_CELL STC ");
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("code", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockCellDTO.class));

		return query.list();
	}
}
