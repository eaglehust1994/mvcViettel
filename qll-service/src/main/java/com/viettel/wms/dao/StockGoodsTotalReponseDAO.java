/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockGoodsTotalReponseBO;
import com.viettel.wms.dto.StockGoodsTotalReponseDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TuanNB
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("stockGoodsTotalReponseDAO")
public class StockGoodsTotalReponseDAO extends BaseFWDAOImpl<StockGoodsTotalReponseBO, Long> {

    public StockGoodsTotalReponseDAO() {
        this.model = new StockGoodsTotalReponseBO();
    }

    public StockGoodsTotalReponseDAO(Session session) {
        this.session = session;
    }
    
    //Tìm kiếm các bản ghi trong bảng Stock_Goods_Total_Reponse
    @SuppressWarnings("unchecked")
    public List<StockGoodsTotalReponseDTO> doSearch(StockGoodsTotalReponseDTO obj){
		StringBuilder sql = new StringBuilder("SELECT "
				+ "st.STOCK_ID stockId, "
				+ "st.GOODS_CODE goodsCode,"
				+ "st.GOODS_NAME goodsName,"
				+ "st.GOODS_STATE goodsState,"
				+ "st.GOODS_STATE_NAME goodsStateName,"
				+ "gt.NAME goodsTypeName,"
				+ "st.GOODS_UNIT_ID goodsUnitId,"
				+ "st.GOODS_UNIT_NAME goodsUnitName,"
				+ "st.AMOUNT_REMAIN amountRemain,"
				+ "st.AMOUNT_ORDER amountOrder,"
				+ "st.AMOUNT_ISSUE amountIssue "
				+ "FROM WMS_OWNER_KTTS.STOCK_GOODS_TOTAL_REPONSE st "
				+ "LEFT JOIN CAT_OWNER.GOODS_TYPE gt ON st.GOODS_TYPE = gt.GOODS_TYPE_ID "
				+ "WHERE 1=1 ");
		/*if (obj.getStockId() != null){
    		sql.append(" AND ST.STOCK_ID = :stockId");
    	}*/
		if(StringUtils.isNotEmpty(obj.getKeySearch())  ){
		   sql.append(" AND (upper(st.STOCK_NAME) LIKE upper(:keySearch) OR upper(st.STOCK_CODE) LIKE upper(:keySearch))");
		}
		if(StringUtils.isNotEmpty(obj.getName())  ){
		   sql.append(" AND (upper(st.GOODS_NAME) LIKE upper(:name) OR upper(st.GOODS_CODE) LIKE upper(:name))");
		}
		if(StringUtils.isNotEmpty(obj.getGoodsState()) && !"2".equals(obj.getGoodsState())){
			sql.append(" AND st.GOODS_STATE = :goodsState ");
		}
		
		if((obj.getListGoodsType()!=null && !obj.getListGoodsType().isEmpty())){
			sql.append(" AND gt.GOODS_TYPE_ID IN (:listGoodsTypeReponse)");
		}
		
		if("1".equals(obj.getReponseStatus())){
			sql.append(" AND st.AMOUNT_ISSUE >= 0 ");
		}
		if("0".equals(obj.getReponseStatus())){
			sql.append(" AND st.AMOUNT_ISSUE < 0 ");
		}
		sql.append("ORDER BY st.GOODS_TYPE, st.GOODS_CODE ");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("stockId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("amountRemain", new DoubleType());
		query.addScalar("amountOrder", new DoubleType());
		query.addScalar("amountIssue", new DoubleType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockGoodsTotalReponseDTO.class));
		
		/*if (obj.getStockId() != null){
			query.setParameter("stockId", obj.getStockId());
			queryCount.setParameter("stockId", obj.getStockId());
		}*/
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
			queryCount.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		
		if(StringUtils.isNotEmpty(obj.getGoodsState()) && !"2".equals(obj.getGoodsState())){
			query.setParameter("goodsState", obj.getGoodsState());
			queryCount.setParameter("goodsState", obj.getGoodsState());
		}
		
		
		if((obj.getListGoodsType()!=null) && !obj.getListGoodsType().isEmpty()){
			query.setParameterList("listGoodsTypeReponse", obj.getListGoodsType());
			queryCount.setParameterList("listGoodsTypeReponse", obj.getListGoodsType());
		}
		
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
   
}

