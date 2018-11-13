/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockGoodsSerialBO;
import com.viettel.wms.dto.StockGoodsSerialDTO;
import com.viettel.wms.dto.StockTransDTO;
import com.viettel.wms.dto.StockTransDetailSerialDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("stockGoodsSerialDAO")
public class StockGoodsSerialDAO extends BaseFWDAOImpl<StockGoodsSerialBO, Long> {

    public StockGoodsSerialDAO() {
        this.model = new StockGoodsSerialBO();
    }

    public StockGoodsSerialDAO(Session session) {
        this.session = session;
    }
    
    
//  Tìm kiếm serial trong bảng StockGoodsSerial - TUANNB
    @SuppressWarnings("unchecked")
	public List<StockGoodsSerialDTO> doSearchFindSerial(StockGoodsSerialDTO obj){
		StringBuilder sql = new StringBuilder("SELECT s.STOCK_ID stockId,"
				+ "st.NAME stockName,"
				+ "s.GOODS_ID goodsId,"
				+ "s.GOODS_CODE goodsCode,"
				+ "s.GOODS_NAME goodsName,"
				+ "s.SERIAL serial,"
				+ "s.AMOUNT amount,"
				+ "s.CONTRACT_CODE contractCode,"
				+ "s.GOODS_STATE goodsState,"
				+ "s.STATUS status "
				+ "FROM WMS_OWNER_KTTS.STOCK_GOODS_SERIAL s "
				+ "INNER JOIN CAT_OWNER.STOCK st ON s.STOCK_ID = st.STOCK_ID "
				+ "WHERE 1=1");
		if(obj.getName()!=null){
			sql.append(" AND (upper(s.GOODS_NAME) LIKE upper(:name) escape '&')");
		}		
		if(obj.getStockName() != null){
			sql.append(" AND (upper(st.NAME) LIKE upper(:stockName) escape '&')");
		}		
		if((obj.getSerialString()!=null && !obj.getSerialString().isEmpty())){
			String str[] = StringUtils.split(obj.getSerialString(), ",");
			sql.append(" AND (");
			for(int i=0;i<str.length;i++){			
				sql.append(" s.SERIAL = '"+ str[i]+"'" );
				if(i<str.length-1){sql.append("OR");}
			}
			sql.append(")");
		}
		
		sql.append(" ORDER BY st.NAME");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("stockId", new LongType());
		query.addScalar("stockName", new StringType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("serial", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("status", new StringType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(StockGoodsSerialDTO.class));
		if(obj.getName()!=null){
			query.setParameter("name", "%"+ ValidateUtils.validateKeySearch(""+obj.getName())+"%");
			queryCount.setParameter("name", "%"+ ValidateUtils.validateKeySearch(""+obj.getName())+"%");
			}
		if(obj.getStockName() != null){
			query.setParameter("stockName", "%"+ ValidateUtils.validateKeySearch(""+obj.getStockName())+"%");
			queryCount.setParameter("stockName", "%"+ ValidateUtils.validateKeySearch(""+obj.getStockName())+"%");
		}
		/*if((obj.getSerialString()!=null && !obj.getSerialString().isEmpty())){
			query.setParameter("serialString", obj.getSerialString());
			queryCount.setParameter("serialString", obj.getSerialString());
			}*/
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
			}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
//	Tìm kiếm lịch sử -- TUANNB
    public List<StockTransDTO> doSearchHistory(StockTransDTO obj){
		StringBuilder sql = new StringBuilder("SELECT st.CREATED_BY_NAME createdByName,"
				+ "st.ORDER_CODE orderCode,"
				+ "st.CODE code,"
				+ "st.BUSSINESS_TYPE_NAME bussinessTypeName,"
				+ "st.REAL_IE_TRANS_DATE realIeTransDate,"
				+ "st.STATUS status "
				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS_DETAIL_SERIAL std "
				+ "INNER JOIN WMS_OWNER_KTTS.STOCK_TRANS st ON std.STOCK_TRANS_ID = st.STOCK_TRANS_ID "
				+ "WHERE 1=1");
		
		if(obj.getGoodsId()!=null){
			sql.append(" AND (std.GOODS_ID LIKE upper(:goodsId))");
		}	
		if(StringUtils.isNotEmpty(obj.getSerial())){
			sql.append(" AND upper(std.SERIAL) LIKE upper(:serial)");
		}
		if (obj.getEndDate() != null)  {
			sql.append(" AND trunc(st.REAL_IE_TRANS_DATE) between :startDate and :endDate");
		}
		if (obj.getStartDate() != null) {
			sql.append(" AND trunc(st.REAL_IE_TRANS_DATE) >= :startDate");
		}
		sql.append(" ORDER BY st.ORDER_CODE");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("realIeTransDate", new TimestampType());
		query.addScalar("orderCode", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("bussinessTypeName", new StringType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("status", new StringType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
		if(obj.getGoodsId()!=null){
			query.setParameter("goodsId", "%"+ ValidateUtils.validateKeySearch(""+obj.getGoodsId())+"%");
			queryCount.setParameter("goodsId", "%"+ ValidateUtils.validateKeySearch(""+obj.getGoodsId())+"%");
			}
		if(StringUtils.isNotEmpty(obj.getSerial())){
			query.setParameter("serial", "%"+ ValidateUtils.validateKeySearch(obj.getSerial())+"%");
			queryCount.setParameter("serial", "%"+ ValidateUtils.validateKeySearch(obj.getSerial())+"%");
			}
		if ((obj.getStartDate() != null) && (obj.getEndDate() != null)) {
			query.setTimestamp("startDate", obj.getStartDate());
			queryCount.setTimestamp("startDate", obj.getStartDate());
			query.setTimestamp("endDate", obj.getEndDate());
			queryCount.setTimestamp("endDate", obj.getEndDate());
		}
		if ((obj.getStartDate() != null) && (obj.getEndDate() == null)) {
			query.setTimestamp("startDate", obj.getStartDate());
			queryCount.setTimestamp("startDate", obj.getStartDate());
		}
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
    public List<StockGoodsSerialDTO> doSearchSerialAndId(){
    	StringBuilder sql = new StringBuilder("SELECT sgs.GOODS_ID goodsId,"
				+ "sgs.SERIAL serial, "
				+ "sgs.STATUS status "
				+ "FROM WMS_OWNER_KTTS.STOCK_GOODS_SERIAL sgs "
    			);
    	
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("goodsId", new LongType());
		query.addScalar("serial", new StringType());
		query.addScalar("status", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockGoodsSerialDTO.class));
		
		return query.list();
    	
    }
    
    public void updateBySerial(StockGoodsSerialDTO stockGoodsSerialDTO){
    	StringBuilder sql = new StringBuilder("UPDATE WMS_OWNER_KTTS.STOCK_GOODS_SERIAL sgr "
				+ "SET "
				+ "sgr.ORDER_ID = :orderId, "
				+ "sgr.GOODS_CODE = :goodsCode, "
				+ "sgr.GOODS_NAME = :goodsName, "
				+ "sgr.GOODS_UNIT_NAME = :goodsUnitName, "
				+ "sgr.AMOUNT = :amount, "
				+ "sgr.GOODS_STATE_NAME = :goodsStateName, "
				+ "sgr.CONTRACT_CODE = :contractCode, "
				+ "sgr.PART_NUMBER = :partNumber, "
				+ "sgr.MANUFACTURER_NAME = :manufacturerName "
				+ "sgr.PRODUCING_COUNTRY_NAME = :producingCountryName "
				+ "WHERE sgr.SERIAL = :serial AND sgr.GOODS_ID = :goodsId"
    			);
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.setParameter("orderId", stockGoodsSerialDTO.getOrderId());
    	query.setParameter("goodsId", stockGoodsSerialDTO.getGoodsId());
    	query.setParameter("goodsCode", stockGoodsSerialDTO.getGoodsCode());
    	query.setParameter("goodsName", stockGoodsSerialDTO.getGoodsName());
    	query.setParameter("goodsUnitName", stockGoodsSerialDTO.getGoodsUnitName());
    	query.setParameter("amount", stockGoodsSerialDTO.getAmount());
    	query.setParameter("goodsStateName", stockGoodsSerialDTO.getGoodsStateName());
    	query.setParameter("contractCode", stockGoodsSerialDTO.getContractCode());
    	query.setParameter("partNumber", stockGoodsSerialDTO.getPartNumber());
    	query.setParameter("manufacturerName", stockGoodsSerialDTO.getManufacturerName());
    	query.setParameter("producingCountryName", stockGoodsSerialDTO.getProducingCountryName());
    	query.setParameter("serial", stockGoodsSerialDTO.getSerial());
    	
    }

    public Long findSerialFromStockTrans(StockTransDetailSerialDTO obj,Long stockId){
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
				+ "FROM WMS_OWNER_KTTS.STOCK_GOODS_SERIAL s "
				+ " WHERE   s.GOODS_ID=:goodsId AND s.GOODS_STATE=:goodsState AND s.STOCK_ID=:stockId AND s.SERIAL=:serial AND s.STATUS=1 ");

		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		
		query.setParameter("goodsId", obj.getGoodsId());
		query.setParameter("stockId",  stockId);
		query.setParameter("goodsState", obj.getGoodState());
		query.setParameter("serial", obj.getSerial());
		return ((BigDecimal) query.uniqueResult()).longValue();
	}
    
    public String updateRealExport(StockTransDetailSerialDTO obj,Long orderId,Long stockId){
    	try {
    		StringBuilder sql = new StringBuilder("UPDATE  WMS_OWNER_KTTS.STOCK_GOODS_SERIAL SET STATUS='3',ORDER_ID=:orderId,CHANGE_DATE=:date   "
    				+ " WHERE   GOODS_ID=:goodsId AND GOODS_STATE=:goodsState AND STOCK_ID=:stockId AND SERIAL=:serial AND STATUS=1 ");

    		
    		SQLQuery query= getSession().createSQLQuery(sql.toString());
    		
    		query.setParameter("goodsId", obj.getGoodsId());
    		query.setParameter("stockId",  stockId);
    		query.setParameter("goodsState", obj.getGoodState());
    		query.setParameter("serial", obj.getSerial());
    		
    		query.setParameter("orderId", orderId);
    		query.setTimestamp("date", new Date());
    		query.executeUpdate();
    		
    		
    		return "";
		} catch (Exception e) {
			return new String("Lỗi xảy ra khi update!");
		}
		
	}
    
    public boolean checkExitSerial(Long stockTransDetailId){
    	StringBuilder sql= new StringBuilder("SELECT COUNT(*) FROM STOCK_TRANS_DETAIL_SERIAL WHERE SERIAL IS NOT NULL AND STOCK_TRANS_DETAIL_ID=:stockTransDetailId");
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	query.setParameter("stockTransDetailId", stockTransDetailId);
    	
    	return (((BigDecimal) query.uniqueResult()).longValue()>0);
    }
    
    public StockGoodsSerialDTO findBySerialAndGoodsId(String serial, Long goodsId){
    	StringBuilder sql = new StringBuilder("SELECT sgs.SERIAL serial,"
				+ "sgs.GOODS_ID goodsId,"
				+ "sgs.GOODS_CODE goodsCode,"
				+ "sgs.GOODS_NAME goodsName,"
				+ "sgs.AMOUNT amount,"
				+ "sgs.CONTRACT_CODE contractCode,"
				+ "sgs.GOODS_STATE goodsState,"
				+ "sgs.STATUS status "
				+ "FROM WMS_OWNER_KTTS.STOCK_GOODS_SERIAL sgs "
				+ "WHERE sgs.SERIAL =:serial AND sgs.GOODS_ID = :goodsId");
    	
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
    	
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("serial", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("status", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockGoodsSerialDTO.class));
		
		query.setParameter("goodsId", goodsId);
		query.setParameter("serial",  serial);
		
		return (StockGoodsSerialDTO) query.uniqueResult();
    	
    }
   
}
