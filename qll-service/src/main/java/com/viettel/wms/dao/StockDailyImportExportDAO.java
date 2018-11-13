/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockDailyImportExportBO;
import com.viettel.wms.dto.StockDailyImportExportDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.List;

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
 */
@Repository("stockDailyImportExportDAO")
public class StockDailyImportExportDAO extends BaseFWDAOImpl<StockDailyImportExportBO, Long> {

    public StockDailyImportExportDAO() {
        this.model = new StockDailyImportExportBO();
    }

    public StockDailyImportExportDAO(Session session) {
        this.session = session;
    }
    
    
    //Tìm kiếm các bản ghi trong bảng Stock_Daily_Import_Export
    @SuppressWarnings("unchecked")
	public List<StockDailyImportExportDTO> doSearch(StockDailyImportExportDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT DISTINCT st.GOODS_NAME goodsName,"
				+ "st.GOODS_UNIT_NAME goodsUnitName,"
				+ "st.GOODS_CODE goodsCode,"
				+ "st.STOCK_ID stockId,"
				+ "st.GOODS_TYPE_NAME goodsTypeName,"
				+ "st.GOODS_TYPE goodsType,"
				+ "sd.AMOUNT amount,"
				+ "st.STOCK_TRANS_DESCRIPTION stockTransDescription, "
			 	+ "SUM(CASE WHEN (st.STOCK_TRANS_TYPE = '1') THEN st.AMOUNT_TOTAL ELSE 0 END) AS amountTotalImport,"
			 	+ "SUM(CASE WHEN (st.STOCK_TRANS_TYPE = '0') THEN st.AMOUNT_TOTAL ELSE 0 END) AS amountTotalExport,"
				+ "sd.AMOUNT + SUM(CASE WHEN (st.STOCK_TRANS_TYPE = '1') THEN st.AMOUNT_TOTAL ELSE 0 END) - SUM(CASE WHEN (st.STOCK_TRANS_TYPE = '0') THEN st.AMOUNT_TOTAL ELSE 0 END) AS amountFinal "
				+ "FROM WMS_OWNER_KTTS.STOCK_DAILY_IMPORT_EXPORT st "
				+ "INNER JOIN WMS_OWNER_KTTS.STOCK_DAILY_REMAIN sd ON sd.STOCK_ID = st.STOCK_ID AND sd.GOODS_ID = st.GOODS_ID "
				+ "WHERE 1=1");
		sql.append(" AND trunc(sd.REMAIN_DATE) = :startDate-1 ");
		if(obj.getEndDate() != null){
			sql.append(" AND (trunc(st.IE_DATE) BETWEEN :startDate AND :endDate)");
		}
		if(obj.getStartDate()!=null) {
			sql.append(" AND (trunc(st.IE_DATE) >= :startDate )");
		}
		if((obj.getListStockId()!=null && !obj.getListStockId().isEmpty())){
		   sql.append(" AND st.STOCK_ID IN :listStockId");
		}
		
		sql.append(" GROUP BY st.STOCK_ID, st.GOODS_NAME, st.GOODS_CODE, st.GOODS_UNIT_NAME,st.GOODS_TYPE_NAME,st.GOODS_TYPE, sd.AMOUNT,st.STOCK_TRANS_DESCRIPTION ");
		sql.append("ORDER BY st.GOODS_TYPE, st.GOODS_CODE");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("stockId", new LongType());
		query.addScalar("goodsType", new StringType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("amountTotalImport", new DoubleType());
		query.addScalar("amountTotalExport", new DoubleType());
		query.addScalar("amountFinal", new DoubleType());
		query.addScalar("stockTransDescription", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockDailyImportExportDTO.class));

		if((obj.getListStockId()!=null && !obj.getListStockId().isEmpty())){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
		
		if(obj.getStartDate()!=null && obj.getEndDate()==null){
			query.setTimestamp("startDate", obj.getStartDate());
			queryCount.setTimestamp("startDate", obj.getStartDate());
		}
		
		if(obj.getStartDate()!=null && obj.getEndDate()!=null){
			query.setTimestamp("startDate",obj.getStartDate());
			queryCount.setTimestamp("startDate",obj.getStartDate());
			
			query.setTimestamp("endDate",obj.getEndDate());
			queryCount.setTimestamp("endDate",obj.getEndDate());	
		}
		
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
}
