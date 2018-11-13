/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockTransBO;
import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.dto.OrderDTO;
import com.viettel.wms.dto.StockTransDTO;/*
import com.viettel.wms.dto.SynchERPDTO;*/
import com.viettel.wms.dto.StockTransDetailDTO;
import com.viettel.wms.dto.SynchERPDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
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
@Repository("stockTransDAO")
public class StockTransDAO extends BaseFWDAOImpl<StockTransBO, Long> {

    public StockTransDAO() {
        this.model = new StockTransBO();
    }

    public StockTransDAO(Session session) {
        this.session = session;
    }
    
    public List<StockTransDTO> doSearchImportNote(StockTransDTO obj){
		StringBuilder sql = new StringBuilder("SELECT st.STOCK_TRANS_ID stockTransId,"
				+ "st.ORDER_ID orderId,"
				+ "st.ORDER_CODE orderCode,"
				+ "st.CODE code,"
				+ "st.TYPE type,"
				+ "s.NAME stockName,"
				+ "s.CODE stockCode,"
				+ "st.CREATED_BY createdBy,"
				+ "st.CREATED_DEPT_ID createdDeptId,"
				+ "st.CREATED_BY_NAME createdByName,"
				+ "st.CREATED_DEPT_NAME createdDeptName,"
				+ "st.SIGN_STATE signState,"
				+ "st.BUSSINESS_TYPE_NAME bussinessTypeName,"
				+ "st.STATUS status "
				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS st "
				+ "INNER JOIN CAT_OWNER.STOCK s ON st.STOCK_ID = s.STOCK_ID "
				+ "WHERE st.TYPE = 1");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(st.CODE) LIKE upper(:code) escape '&')");
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0) {
			sql.append(" AND st.STOCK_ID IN (:listStockId)");
		}
		if (StringUtils.isNotEmpty(obj.getOrderCode())) {
			sql.append(" AND (upper(st.ORDER_CODE) LIKE upper(:orderCode) escape '&')");
		}
		if (obj.getCreatedDeptName()!=null) {
			sql.append(" AND st.CREATED_DEPT_NAME = :createdDeptName");
		}
		if(obj.getCreatedBy() != null){
			sql.append(" AND st.CREATED_BY = :createdBy");
		}
		if (obj.getCreatedDateFrom() != null) {
			sql.append(" AND st.CREATED_DATE >= :createdDateFrom");
		}
		if (obj.getCreatedDateTo() != null) {
			sql.append(" AND st.CREATED_DATE <= :createdDateTo");
		}
		
		if (obj.getListStatus()!=null && obj.getListStatus().size()>0){
			sql.append(" AND st.STATUS IN (:listStatus)");
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			sql.append(" AND st.SIGN_STATE IN (:listSignState)");
		}
		
		sql.append(" ORDER BY st.STOCK_TRANS_ID DESC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("stockTransId", new LongType());
		query.addScalar("orderId", new LongType());
		query.addScalar("orderCode", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("bussinessTypeName", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDeptName", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDeptId", new LongType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
		if (StringUtils.isNotEmpty(obj.getOrderCode())) {
			query.setParameter("orderCode", "%" + ValidateUtils.validateKeySearch(obj.getOrderCode()) + "%");
			queryCount.setParameter("orderCode", "%" + ValidateUtils.validateKeySearch(obj.getOrderCode()) + "%");
		}
		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if ((obj.getCreatedDateFrom() != null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
		}
		if(obj.getCreatedBy() != null){
			query.setParameter("createdBy", obj.getCreatedBy());
			queryCount.setParameter("createdBy", obj.getCreatedBy());
		}
		if ((obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if (obj.getCreatedDeptName()!=null) {
			query.setParameter("createdDeptName", obj.getCreatedDeptName());
			queryCount.setParameter("createdDeptName", obj.getCreatedDeptName());
		}
		if (obj.getListStatus() != null && obj.getListStatus().size()>0){
			query.setParameterList("listStatus", obj.getListStatus());
			queryCount.setParameterList("listStatus", obj.getListStatus());
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			query.setParameterList("listSignState", obj.getListSignState());
			queryCount.setParameterList("listSignState", obj.getListSignState());
		}
		if(obj.getPage()!=null && obj.getPageSize()!=null){
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
    public List<StockTransDTO> getAllImportNote(StockTransDTO obj){
		StringBuilder sql = new StringBuilder("SELECT st.STOCK_TRANS_ID stockTransId,"
				+ "st.ORDER_ID orderId,"
				+ "st.ORDER_CODE orderCode,"
				+ "st.CODE code,"
				+ "st.TYPE type,"
				+ "s.NAME stockName,"
				+ "s.CODE stockCode,"
				+ "st.CREATED_BY createdBy,"
				+ "st.CREATED_DEPT_ID createdDeptId,"
				+ "st.CREATED_BY_NAME createdByName,"
				+ "st.CREATED_DEPT_NAME createdDeptName,"
				+ "st.SIGN_STATE signState,"
				+ "st.BUSSINESS_TYPE_NAME bussinessTypeName,"
				+ "st.STATUS status "
				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS st "
				+ "INNER JOIN CAT_OWNER.STOCK s ON st.STOCK_ID = s.STOCK_ID "
				+ "WHERE st.TYPE = 1");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(st.CODE) LIKE upper(:code) escape '&')");
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0) {
			sql.append(" AND st.STOCK_ID IN (:listStockId)");
		}
		if (StringUtils.isNotEmpty(obj.getOrderCode())) {
			sql.append(" AND (upper(st.ORDER_CODE) LIKE upper(:orderCode) escape '&')");
		}
		if (obj.getCreatedDeptName()!=null) {
			sql.append(" AND st.CREATED_DEPT_NAME = :createdDeptName");
		}
		if(obj.getCreatedBy() != null){
			sql.append(" AND st.CREATED_BY = :createdBy");
		}
		if (obj.getCreatedDateFrom() != null) {
			sql.append(" AND st.CREATED_DATE >= :createdDateFrom");
		}
		if (obj.getCreatedDateTo() != null) {
			sql.append(" AND st.CREATED_DATE <= :createdDateTo");
		}
		
		if (obj.getListStatus()!=null && obj.getListStatus().size()>0){
			sql.append(" AND st.STATUS IN (:listStatus)");
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			sql.append(" AND st.SIGN_STATE IN (:listSignState)");
		}
		
		sql.append(" ORDER BY st.STOCK_TRANS_ID DESC");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("stockTransId", new LongType());
		query.addScalar("orderId", new LongType());
		query.addScalar("orderCode", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("bussinessTypeName", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDeptName", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDeptId", new LongType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
		}
		if (StringUtils.isNotEmpty(obj.getOrderCode())) {
			query.setParameter("orderCode", "%" + ValidateUtils.validateKeySearch(obj.getOrderCode()) + "%");
		}
		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if ((obj.getCreatedDateFrom() != null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
		}
		if(obj.getCreatedBy() != null){
			query.setParameter("createdBy", obj.getCreatedBy());
		}
		if ((obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if (obj.getCreatedDeptName()!=null) {
			query.setParameter("createdDeptName", obj.getCreatedDeptName());
		}
		if (obj.getListStatus() != null && obj.getListStatus().size()>0){
			query.setParameterList("listStatus", obj.getListStatus());
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			query.setParameterList("listSignState", obj.getListSignState());
		}
		return query.list();
	}
    
    public StockTransDTO getStockTransDetail(Long id){
    	StringBuilder sql = new StringBuilder("SELECT st.STOCK_TRANS_ID stockTransId,"
				+ "st.CODE code,"
				+ "st.ORDER_CODE orderCode,"
				+ "st.TYPE type,"
				+ "st.STOCK_ID stockId,"
				+"st.SHIPPER_NAME shipperName,"
				+ "s.NAME stockName,"
				+ "s.CODE stockCode,"
				+ "st.CREATED_DATE createdDate,"
				+ "st.CREATED_BY createdBy,"
				+ "st.CREATED_BY_NAME createdByName,"
				+ "st.CREATED_DEPT_NAME createdDeptName,"
				+ "st.DESCRIPTION description,"
				+ "st.SIGN_STATE signState,"
				+ "st.STATUS status, "
				+ "st.REAL_IE_TRANS_DATE realIeTransDate, "
				+ "st.CANCEL_BY_NAME cancelByName, "
				+ "st.CANCEL_DATE cancelDate, "
				+ "st.CANCEL_REASON_NAME cancelReasonName, "
				+ "st.CANCEL_DESCRIPTION cancelDescription, "
				+ "st.DEPT_RECEIVE_ID deptReceiveId, "
				+ "st.ORDER_ID orderId "
				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS st " 
				+ "INNER JOIN CAT_OWNER.STOCK s ON st.STOCK_ID = s.STOCK_ID "
				+ "WHERE st.STOCK_TRANS_ID = :stockTransId");
    	SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("stockTransId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("orderCode", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockId", new LongType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("shipperName", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDeptName", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("realIeTransDate", new TimestampType());
		query.addScalar("cancelByName", new StringType());
		query.addScalar("cancelDate", new TimestampType());
		query.addScalar("cancelReasonName", new StringType());
		query.addScalar("cancelDescription", new StringType());
		query.addScalar("orderId", new LongType());		
		query.addScalar("deptReceiveId", new LongType());	
		
		query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
		
		query.setParameter("stockTransId", id);
    	return (StockTransDTO) query.uniqueResult();
    }

    // Tìm kiếm báo cáo xuất kho đang đi đường
    @SuppressWarnings("unchecked")
	public List<StockTransDTO> doSearchExport(StockTransDTO obj){
  		StringBuilder sql = new StringBuilder("SELECT "
  				+ "s.CODE stockCode,"
  				+ "(s.NAME ||' (' || s.CODE || ')') text,"
  				+ "st.ORDER_CODE orderCode,"
  				+ "st.CODE code,"
  				+ "st.REAL_IE_USER_NAME realIeUserName,"
  				+ "s.NAME stockName,"
  				+ "st.REAL_IE_TRANS_DATE realIeTransDate,"
  				+ "st.DEPT_RECEIVE_NAME deptReceiveName,"
  				+ "st.STOCK_RECEIVE_CODE stockReceiveCode "
  				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS st " 
  				+ "INNER JOIN CAT_OWNER.STOCK s ON st.STOCK_ID = s.STOCK_ID "
  				+ "WHERE st.IN_ROAL=1");
  		if((obj.getListStockId()!=null && !obj.getListStockId().isEmpty())){
 		   sql.append(" AND st.STOCK_ID IN :listStockId");
 		}
  		if(StringUtils.isNotEmpty(obj.getLoginName())  ){
			sql.append(" AND (upper(st.REAL_IE_USER_NAME) LIKE upper(:loginName) escape '&')");
		}
  		if(obj.getEndDate()!=null) {
  			sql.append(" AND st.REAL_IE_TRANS_DATE <=:endDate");
  		}
  		if(obj.getStartDate()!=null) {
  			sql.append(" AND st.REAL_IE_TRANS_DATE >=  :startDate");
  		}
  		sql.append(" ORDER BY s.STOCK_ID ASC ");
  		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
  		sqlCount.append(sql.toString());
  		sqlCount.append(")");
  		
  		SQLQuery query= getSession().createSQLQuery(sql.toString());
  		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
  		query.addScalar("stockReceiveCode", new StringType());
  		query.addScalar("realIeUserName", new StringType());
  		query.addScalar("realIeTransDate", new TimestampType());
  		query.addScalar("orderCode", new StringType());
  		query.addScalar("code", new StringType());
  		query.addScalar("stockName", new StringType());
  		query.addScalar("stockCode", new StringType());
  		query.addScalar("text", new StringType());
  		query.addScalar("deptReceiveName", new StringType());
  		
  		
  		query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
  		if((obj.getListStockId()!=null && !obj.getListStockId().isEmpty())){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
  		if(StringUtils.isNotEmpty(obj.getLoginName()) ){
			query.setParameter("loginName", "%"+ ValidateUtils.validateKeySearch(obj.getLoginName())+"%");
			queryCount.setParameter("loginName", "%"+ ValidateUtils.validateKeySearch(obj.getLoginName())+"%");
			}
  		if(obj.getStartDate()!=null && obj.getEndDate()!=null){
			query.setDate("startDate",obj.getStartDate());
			queryCount.setDate("startDate",obj.getStartDate());
			
			query.setDate("endDate",obj.getEndDate());
			queryCount.setDate("endDate",obj.getEndDate());	
		}
  		if(obj.getStartDate()!=null && obj.getEndDate()==null){
			query.setDate("startDate",obj.getStartDate());
			queryCount.setDate("startDate",obj.getStartDate());
		}
  		if(obj.getPage()!=null && obj.getPageSize()!=null){
  			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
  	  		query.setMaxResults(obj.getPageSize().intValue());
  		}
  		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
  		
  		return query.list();
  	}
    	
    public List<String> getGoodsInfoFromAlternativeStockCode(String code){
    	StringBuilder sql = new StringBuilder("SELECT st.CODE code"
    			+ " FROM WMS_OWNER_KTTS.STOCK_TRANS st "
    			+ "WHERE st.TYPE = 2 AND st.STATUS = '3' ");
    	if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
    		sql.append(" AND upper(CODE) LIKE upper(:code)");
    	}
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.addScalar("code", new StringType());
    	if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
    	query.setParameter("code", "%"+code+"%");
    	}
    	return query.list();
    }
    
    public StockTransDTO getStockByOrder(StockTransDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT "
				+ "WOST.STOCK_TRANS_ID stockTransId,"
				+ "WOST.ORDER_ID orderId, "
				+ "WOST.CODE code, "
				+ "WOST.ORDER_CODE orderCode, "
				+ "WOST.CREATED_DATE createdDate, "
				+ "WOST.CREATED_BY createdBy, "
				+ "WOST.CREATED_BY_NAME createdByName, "
				+ "WOST.STATUS status, "
				+ "WOST.DESCRIPTION description "
				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS WOST "
				+ "WHERE 1 = 1 "
				+ " ");
		if(obj.getShipmentId() != null)
		{
			sql.append(" AND WOST.ORDER_ID = (SELECT WOO.ORDER_ID FROM WMS_OWNER_KTTS.\"ORDER\" WOO WHERE WOO.SHIPMENT_ID = :shipmentId )");
		}
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("stockTransId",new LongType());
		query.addScalar("orderId",new LongType());
		query.addScalar("code",new StringType());
		query.addScalar("orderCode",new StringType());
		query.addScalar("createdDate",new TimestampType());
		query.addScalar("createdBy",new LongType());
		query.addScalar("createdByName",new StringType());
		query.addScalar("status",new StringType());
		query.addScalar("description",new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
		
		if(obj.getShipmentId() != null)
		{
			query.setParameter("shipmentId", obj.getShipmentId());
		}
		return (StockTransDTO) query.uniqueResult();
	}
    
    @SuppressWarnings("unchecked")
	public List<StockTransDTO> doSearchExportManagement(StockTransDTO obj) {
    	StringBuilder sql = new StringBuilder("WITH B AS (SELECT STOCK_TRANS_ID,COUNT(*) countSerial FROM STOCK_TRANS_DETAIL_SERIAL WHERE SERIAL IS NOT NULL GROUP BY STOCK_TRANS_ID),"
    			+ " C AS (SELECT STOCK_TRANS_ID,COUNT(*) countSerial FROM STOCK_TRANS_DETAIL WHERE GOODS_IS_SERIAL='1' GROUP BY STOCK_TRANS_ID)"
    			+ "SELECT "
    			+ " st.STOCK_TRANS_ID stockTransId"
    			+ ",st.ORDER_ID orderId"
    			+ ",st.ORDER_CODE orderCode"
    			+ ",st.CODE code"
    			+ ",st.TYPE type"
    			+ ",st.STOCK_ID stockId"
    			+ ",st.STATUS status"
    			+ ",st.SIGN_STATE signState"
    			+ ",st.FROM_STOCK_TRANS_ID fromStockTransId"
    			+ ",st.DESCRIPTION description"
    			+ ",st.CREATED_BY createdBy"
    			+ ",st.CREATED_BY_NAME createdByName"
    			+ ",st.CREATED_DATE createdDate"
    			+ ",st.CREATED_DEPT_ID createdDeptId"
    			+ ",st.CREATED_DEPT_NAME createdDeptName"
    			+ ",st.UPDATED_BY updatedBy"
    			+ ",st.UPDATED_DATE updatedDate"
    			+ ",st.REAL_IE_TRANS_DATE realIeTransDate"
    			+ ",st.REAL_IE_USER_ID realIeUserId"
    			+ ",st.REAL_IE_USER_NAME realIeUserName"
    			+ ",st.SHIPPER_ID shipperId"
    			+ ",st.SHIPPER_NAME shipperName"
    			+ ",st.CANCEL_DATE cancelDate"
    			+ ",st.CANCEL_BY cancelBy"
    			+ ",st.CANCEL_BY_NAME cancelByName"
    			+ ",st.CANCEL_REASON_NAME cancelReasonName"
    			+ ",st.CANCEL_DESCRIPTION cancelDescription"
    			+ ",st.VOFFICE_TRANSACTION_CODE vofficeTransactionCode"
    			+ ",st.SHIPMENT_CODE shipmentCode"
    			+ ",st.CONTRACT_CODE contractCode"
    			+ ",st.PROJECT_CODE projectCode"
    			+ ",st.CUST_ID cusId"
    			+ ",st.BUSSINESS_TYPE_NAME bussinessTypeName"
    			+ ",st.IN_ROAL in_roal"
    			+ ",st.DEPT_RECEIVE_NAME deptReceiveName"
    			+ ",st.DEPT_RECEIVE_ID deptReceiveId"
    			+ ",st.STOCK_RECEIVE_ID stockReceiveId"
    			+ ",st.STOCK_RECEIVE_CODE stockReceiveCode"
    			+ ",s.CODE stockCode"
    			+ ",s.NAME stockName"
    			+ ",NVL(B.countSerial,0) countSerial"
    			+ ",NVL(C.countSerial,0) countSerialDetail"
    			+ ",OD.RECEIVER_ID receiverId"
    			+ ",(CASE WHEN  US.PHONE_NUMBER is null THEN US.FULL_NAME ELSE US.FULL_NAME ||'-'|| US.PHONE_NUMBER END) receiverName"
				+ " FROM WMS_OWNER_KTTS.STOCK_TRANS st "
				+ " INNER JOIN CAT_OWNER.STOCK s ON st.STOCK_ID = s.STOCK_ID "
				+ " LEFT JOIN B ON B.STOCK_TRANS_ID=st.STOCK_TRANS_ID"
				+ " LEFT JOIN C ON C.STOCK_TRANS_ID=st.STOCK_TRANS_ID"
				+ " JOIN \"ORDER\" OD ON OD.ORDER_ID=st.ORDER_ID "
				+ " LEFT JOIN VPS_OWNER.SYS_USER US ON US.SYS_USER_ID=OD.RECEIVER_ID"
				+ " WHERE st.TYPE = 2 ");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(st.CODE) LIKE upper(:keySearch) escape '&')");
		}
		
		if (StringUtils.isNotEmpty(obj.getOrderCode())) {
			sql.append(" AND (upper(st.ORDER_CODE) LIKE upper(:orderCode)  escape '&')");
		}
		
		if (obj.getListStockId() != null && obj.getListStockId().size()>0) {
			sql.append(" AND st.STOCK_ID IN (:listStockId)");
		}
		if(obj.getCreatedBy() !=null){
			sql.append(" AND  st.CREATED_BY = :createdBy ");
		}
		if (obj.getCreatedDeptId() != null) {
			sql.append(" AND st.CREATED_DEPT_ID = :createdDeptId");
		}
		
		if (obj.getCreatedDateFrom() != null) {
			sql.append(" AND st.CREATED_DATE >= :createdDateFrom");
		}
		if (obj.getCreatedDateFrom() == null) {
			sql.append(" AND st.CREATED_DATE < :createdDateTo");
		}
		if (obj.getListStatus() != null && obj.getListStatus().size() >0  ){
			sql.append(" AND st.STATUS IN (:listStatus)");
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			sql.append(" AND st.SIGN_STATE IN (:listSignState)");
		}
		sql.append(" ORDER BY st.STOCK_TRANS_ID DESC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("stockTransId", new LongType());
		query.addScalar("orderId", new LongType());
		query.addScalar("orderCode", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockId", new LongType());
		query.addScalar("status", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("fromStockTransId", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdDeptId", new LongType());
		query.addScalar("createdDeptName", new StringType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("updatedDate", new TimestampType());
		query.addScalar("realIeTransDate", new TimestampType());
		query.addScalar("realIeUserId", new StringType());
		query.addScalar("realIeUserName", new StringType());
		query.addScalar("shipperId", new LongType());
		query.addScalar("shipperName", new StringType());
		query.addScalar("cancelDate", new TimestampType());
		query.addScalar("cancelBy", new LongType());
		query.addScalar("cancelByName", new StringType());
		query.addScalar("cancelReasonName", new StringType());
		query.addScalar("cancelDescription", new StringType());
		query.addScalar("vofficeTransactionCode", new StringType());
		query.addScalar("shipmentCode", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("projectCode", new StringType());
		query.addScalar("cusId", new LongType());
		query.addScalar("bussinessTypeName", new StringType());
		query.addScalar("in_roal", new StringType());
		query.addScalar("deptReceiveName", new StringType());
		query.addScalar("deptReceiveId", new LongType());
		query.addScalar("stockReceiveId", new LongType());
		query.addScalar("stockReceiveCode", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("countSerial", new LongType());
		query.addScalar("countSerialDetail", new LongType());
		query.addScalar("receiverName", new StringType());
		query.addScalar("receiverId", new LongType());

		
		
		query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getOrderCode())) {
			query.setParameter("orderCode", "%" + ValidateUtils.validateKeySearch(obj.getOrderCode()) + "%");
			queryCount.setParameter("orderCode", "%" + ValidateUtils.validateKeySearch(obj.getOrderCode()) + "%");
		}
		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() == null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
		}
//		if(obj.getCreatedByName() !=null){
//			query.setParameter("createdByName", obj.getCreatedByName());
//			queryCount.setParameter("createdByName", obj.getCreatedByName());
//		}
		
		if ((obj.getCreatedDateFrom() == null) && (obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if (obj.getCreatedDeptName() != null) {
			query.setParameter("createdDeptId", obj.getCreatedDeptId());
			queryCount.setParameter("createdDeptId", obj.getCreatedDeptId());
		}
		
		if (obj.getCreatedBy() != null) {
			query.setParameter("createdBy",obj.getCreatedBy());
			queryCount.setParameter("createdBy",obj.getCreatedBy());
		}
		if (obj.getListStatus() != null && obj.getListStatus().size() >0 ){
			query.setParameterList("listStatus", obj.getListStatus());
			queryCount.setParameterList("listStatus", obj.getListStatus());
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			query.setParameterList("listSignState", obj.getListSignState());
			queryCount.setParameterList("listSignState", obj.getListSignState());
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
		
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
    @SuppressWarnings("unchecked")
	public List<StockTransDTO> getAllExportManagement(StockTransDTO obj) {
    	StringBuilder sql = new StringBuilder("WITH B AS (SELECT STOCK_TRANS_ID,COUNT(*) countSerial FROM STOCK_TRANS_DETAIL_SERIAL WHERE SERIAL IS NOT NULL GROUP BY STOCK_TRANS_ID),"
    			+ " C AS (SELECT STOCK_TRANS_ID,COUNT(*) countSerial FROM STOCK_TRANS_DETAIL WHERE GOODS_IS_SERIAL='1' GROUP BY STOCK_TRANS_ID)"
    			+ "SELECT "
    			+ " st.STOCK_TRANS_ID stockTransId"
    			+ ",st.ORDER_ID orderId"
    			+ ",st.ORDER_CODE orderCode"
    			+ ",st.CODE code"
    			+ ",st.TYPE type"
    			+ ",st.STOCK_ID stockId"
    			+ ",st.STATUS status"
    			+ ",st.SIGN_STATE signState"
    			+ ",st.FROM_STOCK_TRANS_ID fromStockTransId"
    			+ ",st.DESCRIPTION description"
    			+ ",st.CREATED_BY createdBy"
    			+ ",st.CREATED_BY_NAME createdByName"
    			+ ",st.CREATED_DATE createdDate"
    			+ ",st.CREATED_DEPT_ID createdDeptId"
    			+ ",st.CREATED_DEPT_NAME createdDeptName"
    			+ ",st.UPDATED_BY updatedBy"
    			+ ",st.UPDATED_DATE updatedDate"
    			+ ",st.REAL_IE_TRANS_DATE realIeTransDate"
    			+ ",st.REAL_IE_USER_ID realIeUserId"
    			+ ",st.REAL_IE_USER_NAME realIeUserName"
    			+ ",st.SHIPPER_ID shipperId"
    			+ ",st.SHIPPER_NAME shipperName"
    			+ ",st.CANCEL_DATE cancelDate"
    			+ ",st.CANCEL_BY cancelBy"
    			+ ",st.CANCEL_BY_NAME cancelByName"
    			+ ",st.CANCEL_REASON_NAME cancelReasonName"
    			+ ",st.CANCEL_DESCRIPTION cancelDescription"
    			+ ",st.VOFFICE_TRANSACTION_CODE vofficeTransactionCode"
    			+ ",st.SHIPMENT_CODE shipmentCode"
    			+ ",st.CONTRACT_CODE contractCode"
    			+ ",st.PROJECT_CODE projectCode"
    			+ ",st.CUST_ID cusId"
    			+ ",st.BUSSINESS_TYPE_NAME bussinessTypeName"
    			+ ",st.IN_ROAL in_roal"
    			+ ",st.DEPT_RECEIVE_NAME deptReceiveName"
    			+ ",st.DEPT_RECEIVE_ID deptReceiveId"
    			+ ",st.STOCK_RECEIVE_ID stockReceiveId"
    			+ ",st.STOCK_RECEIVE_CODE stockReceiveCode"
    			+ ",s.CODE stockCode"
    			+ ",s.NAME stockName"
    			+ ",NVL(B.countSerial,0) countSerial"
    			+ ",NVL(C.countSerial,0) countSerialDetail"
    			+ ",OD.RECEIVER_ID receiverId"
    			+ ",(CASE WHEN  US.PHONE_NUMBER is null THEN US.FULL_NAME ELSE US.FULL_NAME ||'-'|| US.PHONE_NUMBER END) receiverName"
				+ " FROM WMS_OWNER_KTTS.STOCK_TRANS st "
				+ " INNER JOIN CAT_OWNER.STOCK s ON st.STOCK_ID = s.STOCK_ID "
				+ " LEFT JOIN B ON B.STOCK_TRANS_ID=st.STOCK_TRANS_ID"
				+ " LEFT JOIN C ON B.STOCK_TRANS_ID=st.STOCK_TRANS_ID"
				+ " JOIN \"ORDER\" OD ON OD.ORDER_ID=st.ORDER_ID "
				+ " LEFT JOIN VPS_OWNER.SYS_USER US ON US.SYS_USER_ID=OD.RECEIVER_ID"
				+ " WHERE st.TYPE = 2 ");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(st.CODE) LIKE upper(:keySearch) escape '&')");
		}
		
		if (StringUtils.isNotEmpty(obj.getOrderCode())) {
			sql.append(" AND (upper(st.ORDER_CODE) LIKE upper(:orderCode)  escape '&')");
		}
		
		if (obj.getListStockId() != null && obj.getListStockId().size()>0) {
			sql.append(" AND st.STOCK_ID IN (:listStockId)");
		}
		if(obj.getCreatedBy() !=null){
			sql.append(" AND  st.CREATED_BY = :createdBy ");
		}
		if (obj.getCreatedDeptName() != null) {
			sql.append(" AND st.CREATED_DEPT_NAME = :createdDeptName");
		}
		
		if (obj.getCreatedDateFrom() != null) {
			sql.append(" AND st.CREATED_DATE >= :createdDateFrom");
		}
		if (obj.getCreatedDateFrom() == null) {
			sql.append(" AND st.CREATED_DATE < :createdDateTo");
		}
		if (obj.getListStatus() != null && obj.getListStatus().size() >0  ){
			sql.append(" AND st.STATUS IN (:listStatus)");
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			sql.append(" AND st.SIGN_STATE IN (:listSignState)");
		}
		sql.append(" ORDER BY st.STOCK_TRANS_ID DESC");
		
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("stockTransId", new LongType());
		query.addScalar("orderId", new LongType());
		query.addScalar("orderCode", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockId", new LongType());
		query.addScalar("status", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("fromStockTransId", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdDeptId", new LongType());
		query.addScalar("createdDeptName", new StringType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("updatedDate", new TimestampType());
		query.addScalar("realIeTransDate", new TimestampType());
		query.addScalar("realIeUserId", new StringType());
		query.addScalar("realIeUserName", new StringType());
		query.addScalar("shipperId", new LongType());
		query.addScalar("shipperName", new StringType());
		query.addScalar("cancelDate", new TimestampType());
		query.addScalar("cancelBy", new LongType());
		query.addScalar("cancelByName", new StringType());
		query.addScalar("cancelReasonName", new StringType());
		query.addScalar("cancelDescription", new StringType());
		query.addScalar("vofficeTransactionCode", new StringType());
		query.addScalar("shipmentCode", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("projectCode", new StringType());
		query.addScalar("cusId", new LongType());
		query.addScalar("bussinessTypeName", new StringType());
		query.addScalar("in_roal", new StringType());
		query.addScalar("deptReceiveName", new StringType());
		query.addScalar("deptReceiveId", new LongType());
		query.addScalar("stockReceiveId", new LongType());
		query.addScalar("stockReceiveCode", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("countSerial", new LongType());
		query.addScalar("countSerialDetail", new LongType());
		query.addScalar("receiverName", new StringType());
		query.addScalar("receiverId", new LongType());

		
		
		query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getOrderCode())) {
			query.setParameter("orderCode", "%" + ValidateUtils.validateKeySearch(obj.getOrderCode()) + "%");
		}
		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() == null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
		}
//		if(obj.getCreatedByName() !=null){
//			query.setParameter("createdByName", obj.getCreatedByName());
//			queryCount.setParameter("createdByName", obj.getCreatedByName());
//		}
		
		if ((obj.getCreatedDateFrom() == null) && (obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if (obj.getCreatedDeptName() != null) {
			query.setParameter("createdDeptName", obj.getCreatedDeptName());
		}
		
		if (obj.getCreatedBy() != null) {
			query.setParameter("createdBy",obj.getCreatedBy());
		}
		if (obj.getListStatus() != null && obj.getListStatus().size() >0 ){
			query.setParameterList("listStatus", obj.getListStatus());
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			query.setParameterList("listSignState", obj.getListSignState());
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
		}
		
		
		return query.list();
	}
    
    public List<StockTransDTO> doSearchNoteForAutoComplete(StockTransDTO obj) {
  		StringBuilder sql = new StringBuilder("SELECT st.STOCK_TRANS_ID stockTransId,"
  				+ "st.ORDER_CODE orderCode,"
  				+ "st.CODE code "
  				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS st " 
  				+ "INNER JOIN CAT_OWNER.STOCK s ON st.STOCK_ID = s.STOCK_ID "
  				+ "WHERE 1=1  AND st.TYPE = 1");
  		
  		if (StringUtils.isNotEmpty(obj.getCode()) && !obj.getCode().trim().equals("[]") && !obj.getCode().trim().equals("")) {
			sql.append(" AND upper(st.CODE) LIKE upper(:code)");
		}
  		sql.append(" ORDER BY st.STOCK_TRANS_ID DESC");
  		
  		
  		
  		
  		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
  		sqlCount.append(sql.toString());
  		sqlCount.append(")");
  		
  		SQLQuery query= getSession().createSQLQuery(sql.toString());
  		
  		query.addScalar("stockTransId", new LongType());
  		query.addScalar("orderCode", new StringType());
  		query.addScalar("code", new StringType());
  		
 		query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
 		
  		if (StringUtils.isNotEmpty(obj.getCode()) && !obj.getCode().trim().equals("[]") && !obj.getCode().trim().equals("")) {
  			query.setParameter("code", "%" + obj.getCode() + "%");
		}
  		
 
  		return query.list();
    }
    
    public boolean removeImportStockTrans(StockTransDTO obj){
   	 StringBuilder sql = new StringBuilder( "UPDATE "
   				+ " WMS_OWNER_KTTS.STOCK_TRANS st "
   				+ " SET "
   				+ " st.STATUS = :status ");
   				if(obj.getCancelBy()!=null){
   					sql.append(" ,st.CANCEL_BY = :cancelBy ");
   				}
   				if(StringUtils.isNotEmpty(obj.getCancelByName())){
   					sql.append(" ,st.CANCEL_BY_NAME = :cancelByName ");
   				}
   				if(obj.getCancelDate()!=null){
   					sql.append(" ,st.CANCEL_DATE = :cancelDate ");
   				}
   				if(StringUtils.isNotEmpty(obj.getCancelReasonName())){
   					sql.append(" ,st.CANCEL_REASON_NAME = :cancelReasonName ");
   				}
   				
   				sql.append(" WHERE st.STOCK_TRANS_ID = :stockTransId");
   	 
   	 SQLQuery query = getSession().createSQLQuery(sql.toString());
   	 
   	 query.setParameter("status","3");
   	if(obj.getCancelBy()!=null){
   	 query.setLong("cancelBy",obj.getCancelBy());
   	}
	if(StringUtils.isNotEmpty(obj.getCancelByName())){
   	 query.setParameter("cancelByName",obj.getCancelByName());
	}
	if(obj.getCancelDate()!=null){
   	 query.setTimestamp("cancelDate",new Date());
	}
	if(StringUtils.isNotEmpty(obj.getCancelReasonName())){
   	 query.setParameter("cancelReasonName",obj.getCancelReasonName());
	}
   	 query.setLong("stockTransId",obj.getStockTransId());
   	 
   	 query.executeUpdate();
   	 
   	return true;
    }

    public CommonDTO getCharFour(CommonDTO obj){
    	StringBuilder sql= new StringBuilder("SELECT NVL(COUNT(CASE WHEN ST.status =1 AND ST.TYPE =1 THEN 1 ELSE null END),0)ImNotReal  ,"
    			+ " NVL(COUNT(CASE WHEN ST.SIGN_STATE=1 AND ST.TYPE =1 THEN 1 ELSE null END),0) ImNotSign ,"
    			+ " NVL(COUNT(CASE WHEN ST.SIGN_STATE=1 AND ST.TYPE =2 THEN 1 ELSE null END),0) ExNotSign ,"
    			+ " NVL(COUNT(CASE WHEN ST.status=1 AND ST.TYPE =2 THEN 1 ELSE null END),0) ExNotReal ,"
    			+ " NVL(COUNT(CASE WHEN ST.in_roal=1 AND ST.TYPE =2 THEN 1 ELSE null END),0) ExInRoad "
    			+ " FROM  WMS_OWNER_KTTS.STOCK_TRANS ST "
    			+ " LEFT JOIN CAT_OWNER.STOCK STOCK ON STOCK.STOCK_ID=ST.STOCK_ID"
    			+ " WHERE 1=1 ");
    	
    	if(obj.getListStockId().size()>0){
    		sql.append(" AND ST.STOCK_ID in (:listStockId)");
    	}
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("ImNotSign",new LongType());
    	query.addScalar("ImNotReal",new LongType());
    	query.addScalar("ExNotSign",new LongType());
    	query.addScalar("ExNotReal",new LongType());
    	query.addScalar("ExInRoad",new LongType());
    	
    	query.setResultTransformer(Transformers.aliasToBean(CommonDTO.class));
    	
    	if(obj.getListStockId().size()>0){
    		query.setParameterList("listStockId", obj.getListStockId());
    	}
    	
    	return (CommonDTO) query.uniqueResult();
    } 

    public List<CommonDTO> getCharTwoWeek(CommonDTO obj){
    	StringBuilder sql= new StringBuilder("SELECT "
    			+ " NVL(COUNT(CASE WHEN ST.status=2 AND ST.TYPE =1 THEN 1 ELSE null END),0) imported ,"
    			+ " NVL(COUNT(CASE WHEN ST.status=2 AND ST.TYPE =2 THEN 1 ELSE null END),0) exported,"
    			+ " trunc(ST.REAL_IE_TRANS_DATE) day "
    			+ " FROM  WMS_OWNER_KTTS.STOCK_TRANS ST"
    			+ " LEFT JOIN CAT_OWNER.STOCK STOCK ON STOCK.STOCK_ID=ST.STOCK_ID"
    			+ " WHERE  ST.REAL_IE_TRANS_DATE <= sysdate AND ST.REAL_IE_TRANS_DATE > (sysdate-7) ");
    	
    	if(obj.getListStockId().size()>0){
    		sql.append(" AND ST.STOCK_ID in (:listStockId)");
    	}
    	
    	sql.append(" group by trunc(ST.REAL_IE_TRANS_DATE) ORDER BY trunc(ST.REAL_IE_TRANS_DATE) ");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("imported",new LongType());
    	query.addScalar("exported",new LongType());
    	query.addScalar("day",new DateType());
    	
    	query.setResultTransformer(Transformers.aliasToBean(CommonDTO.class));
    	
    	if(obj.getListStockId().size()>0){
    		query.setParameterList("listStockId", obj.getListStockId());
    	}
    	
    	return query.list();
    } 
    
    public List<CommonDTO> getCharTwoMonth(CommonDTO obj) throws ParseException{
    	 LocalDate localDate = LocalDate.now();
    	 System.out.println(localDate.with(TemporalAdjusters.lastDayOfMonth()));
    	 Date firstTime = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.with(TemporalAdjusters.firstDayOfMonth()).toString());
    	 Date lastTime = new SimpleDateFormat("yyyy-MM-dd").parse(localDate.with(TemporalAdjusters.lastDayOfMonth()).toString());
    	StringBuilder sql= new StringBuilder("SELECT "
    			+ " NVL(COUNT(CASE WHEN ST.status=2 AND ST.TYPE =1 THEN 1 ELSE null END),0) imported ,"
    			+ " NVL(COUNT(CASE WHEN ST.status=2 AND ST.TYPE =2 THEN 1 ELSE null END),0) exported,"
    			+ " trunc(ST.REAL_IE_TRANS_DATE) day "
    			+ " FROM  WMS_OWNER_KTTS.STOCK_TRANS ST"
    			+ " LEFT JOIN CAT_OWNER.STOCK STOCK ON STOCK.STOCK_ID=ST.STOCK_ID"
    			+ " WHERE  ST.REAL_IE_TRANS_DATE <= :lastTime AND ST.REAL_IE_TRANS_DATE >= :firstTime ");
    	
    	if(obj.getListStockId().size()>0){
    		sql.append(" AND ST.STOCK_ID in (:listStockId)");
    	}
    	
    	sql.append(" group by trunc(ST.REAL_IE_TRANS_DATE) ORDER BY trunc(ST.REAL_IE_TRANS_DATE) ");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar("imported",new LongType());
    	query.addScalar("exported",new LongType());
    	query.addScalar("day",new DateType());
    	
    	query.setResultTransformer(Transformers.aliasToBean(CommonDTO.class));
    	
    	if(obj.getListStockId().size()>0){
    		query.setParameterList("listStockId", obj.getListStockId());
    	}
    	
    	query.setTimestamp("lastTime", lastTime);
    	query.setTimestamp("firstTime", firstTime);
    	
    	return query.list();
    } 
    
    public List<SynchERPDTO> getListStockTrans(SynchERPDTO obj){
    	StringBuilder sql= new StringBuilder("SELECT ST.STOCK_TRANS_ID id,"
    			+ "ST.CODE code,"
    			+ "STOCK.CODE stockCode,"
    			+ "ST.REAL_IE_USER_NAME realImportExporeName,"
    			+ "ST.REAL_IE_TRANS_DATE realImportExportDate,"
    			+ "ST.CONTRACT_CODE contractCode,"
    			+ "ST.SHIPMENT_CODE shipmentCode,"
    			+ "ST.PROJECT_CODE projectCode,"
//    			+ "ST.constr_code constrCode,"
//    			+ "ST.partner_code partnerCode,"
    			+ "O.BUSSINESS_TYPE bussTypeId,"
    			+ "ST.DEPT_RECEIVE_ID deptReceiveId,"
    			+ "DE.CODE deptReceiveCode,"
    			+ "DE.NAME deptReceiveName,"
    			+ "ST.TYPE type,"
    			+ "STD.GOODS_TYPE goodsType,"
    			+ "STD.GOODS_TYPE_NAME goodsTypeName,"
    			+ "STD.GOODS_ID goodsId,"
    			+ "STD.GOODS_CODE goodsCode,"
    			+ "STD.GOODS_NAME goodsName,"
    			+ "STD.GOODS_IS_SERIAL goodsIsSerial,"
    			+ "STD.GOODS_STATE goodsState,"
    			+ "STD.GOODS_STATE_NAME goodsStateName,"
    			+ "STD.GOODS_UNIT_ID goodsUnitId,"
    			+ "STD.GOODS_UNIT_NAME goodsUnitName,"
    			+ "STD.AMOUNT_ORDER amountOrder,"
    			+ "STD.AMOUNT_REAL amountReal,"
    			+ "STD.TOTAL_PRICE totalPrice"
    			+ " FROM WMS_OWNER_KTTS.STOCK_TRANS ST "
    			+ " JOIN WMS_OWNER_KTTS.STOCK_TRANS_DETAIL STD ON ST.STOCK_TRANS_ID=STD.STOCK_TRANS_ID"
    			+ " JOIN CAT_OWNER.STOCK STOCK ON STOCK.STOCK_ID=ST.STOCK_ID"
    			+ " JOIN WMS_OWNER_KTTS.\"ORDER\" O ON O.ORDER_ID=ST.ORDER_ID "
    			+ " JOIN CAT_OWNER.DEPARTMENT DE ON DE.DEPARTMENT_ID=ST.DEPT_RECEIVE_ID"
    			+ " WHERE 1=1 ");
    	
    	if(obj.getFromDateTime() != null){
    		sql.append(" AND ST.REAL_IE_TRANS_DATE <= :fromDateTime ");
    	}
    	
    	if(obj.getToDateTime() != null){
    		sql.append(" AND ST.REAL_IE_TRANS_DATE >= :toDateTime ");
    	}
    	
    	if(StringUtils.isNotEmpty(obj.getStringtype())){
    		sql.append(" AND ST.TYPE =:stringType");
    	}
    	
    	if(StringUtils.isNotEmpty(obj.getStringtransCode())){
    		sql.append(" AND ST.CODE =:stringtransCode");
    	}
    	
    	
    	SQLQuery query=getSession().createSQLQuery(sql.toString());
    	
    	query.addScalar(" id",new LongType());
    	query.addScalar(" code",new StringType());
    	query.addScalar(" stockCode",new StringType());
    	query.addScalar(" realImportExporeName",new StringType());
    	query.addScalar(" realImportExportDate",new TimestampType());
    	query.addScalar(" contractCode",new StringType());
    	query.addScalar(" shipmentCode",new StringType());
    	query.addScalar(" projectCode",new StringType());
//    	query.addScalar(" constrCode",new StringType());
//    	query.addScalar(" partnerCode",new StringType());
    	query.addScalar(" bussTypeId",new LongType());
    	query.addScalar(" deptReceiveId",new LongType());
    	query.addScalar(" deptReceiveCode",new StringType());
    	query.addScalar(" deptReceiveName",new StringType());
//    	query.addScalar(" totalMoney",new LongType());
    	query.addScalar(" type",new LongType());
    	query.addScalar(" goodsType",new LongType());
    	query.addScalar(" goodsTypeName",new StringType());
    	query.addScalar(" goodsId",new LongType());
    	query.addScalar(" goodsCode",new StringType());
    	query.addScalar(" goodsName",new StringType());
    	query.addScalar(" goodsIsSerial",new LongType());
    	query.addScalar(" goodsState",new LongType());
    	query.addScalar(" goodsStateName",new StringType());
    	query.addScalar(" goodsUnitName",new StringType());
    	query.addScalar(" goodsUnitId",new LongType());
    	query.addScalar(" amountOrder",new DoubleType());
    	query.addScalar(" amountReal",new DoubleType());
    	query.addScalar(" totalPrice",new DoubleType());
    	
    	query.setResultTransformer(Transformers.aliasToBean(SynchERPDTO.class));
    	
    	if(obj.getFromDateTime() != null){
    		query.setTimestamp("fromDateTime", obj.getFromDateTime());
    	}
    	
    	if(obj.getToDateTime() != null){

    		query.setTimestamp("toDateTime", obj.getToDateTime());
    	}
    	
    	if(StringUtils.isNotEmpty(obj.getStringtype())){
    		query.setParameter("stringType", obj.getStringtype());
    	}
    	
    	if(StringUtils.isNotEmpty(obj.getStringtransCode())){
    		query.setParameter("stringtransCode", obj.getStringtransCode());
    	}
    	
    	return query.list();
    }
    
    public boolean updateStatusStockTrans(StockTransDTO obj){
		 StringBuilder sql = new StringBuilder( "UPDATE "
					+ " WMS_OWNER_KTTS.STOCK_TRANS st "
					+ " SET "
					+ " st.STATUS = :status, "
					+ " st.REAL_IE_TRANS_DATE = :realIeTransDate,"
					+ " st.REAL_IE_USER_ID=:userId,"
					+ " st.REAL_IE_USER_NAME=:userName"
					+ " WHERE st.STOCK_TRANS_ID = :stockTransId");
		 
		 SQLQuery query = getSession().createSQLQuery(sql.toString());
		 
		 query.setParameter("status",obj.getStatus());
		 query.setParameter("realIeTransDate",new Date());
		 query.setParameter("userId",obj.getRealIeUserId());
		 query.setParameter("userName",obj.getRealIeUserName());
		 query.setLong("stockTransId",obj.getStockTransId());
		 query.executeUpdate();
		 
		return true;
	 	}
    
    public boolean checkSerialNote(Long stockTransId){
    	StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM STOCK_TRANS_DETAIL_SERIAL WHERE STOCK_TRANS_ID=:stockTransId AND SERIAL IS NOT NULL");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.setParameter("stockTransId", stockTransId);
    	
    	if(((BigDecimal) query.uniqueResult()).intValue() > 0){
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public Double getAmountRealDetail(Long detailId){
    	StringBuilder sql = new StringBuilder("SELECT AMOUNT_REAL FROM STOCK_TRANS_DETAIL WHERE STOCK_TRANS_DETAIL_ID=:detailId ");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.setParameter("detailId", detailId);
    	if(((BigDecimal) query.uniqueResult()) != null){
    		return ((BigDecimal) query.uniqueResult()).doubleValue();
    	}else {
    		return 0D;
    	}
    }
    
    public List<StockTransDTO> checkDupOrderCode(StockTransDTO obj){
	 	 StringBuilder sql = new StringBuilder("SELECT st.STOCK_TRANS_ID stockTransId, "
	 				+ "st.CODE code "
	 				+ "FROM WMS_OWNER_KTTS.STOCK_TRANS st " 
	 				+ "WHERE ");
	 		if (StringUtils.isNotEmpty(obj.getCode())) {
	 			sql.append("(upper(st.CODE) LIKE upper(:code))");
	 		}
	 		
	 		SQLQuery query= getSession().createSQLQuery(sql.toString());
	 		
	 		query.addScalar("stockTransId", new LongType());
	 		query.addScalar("code", new StringType());
	 		
	 		
	 		
	 		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
	 		if (StringUtils.isNotEmpty(obj.getCode())) {
	 			query.setParameter("code", ValidateUtils.validateKeySearch(obj.getCode()));
	 		}
	 		
	 		return query.list();
	   	}
    
    
    
    public List<StockTransDetailDTO> getDataExportBKVT(List<Long> ids){
    	StringBuilder sql = new StringBuilder(" select Std.GOODS_ID goodsId,"
    			+ " Std.Goods_Name goodsName,"
    			+ "Std.Goods_Code goodsCode,"
    			+ "Std.Goods_Unit_Name goodsUnitName,"
    			+" Std.Goods_State_Name goodsStateName,"
    			+" NVL(constr.NAME,Sts.Dept_Receive_Name) deptReceiveName,"
    			+" SUM(std.TOTAL_PRICE) totalPrice"
    			+"  from STOCK_TRANS_DETAIL std"
    			+"  join STOCK_TRANS Sts on Sts.Stock_Trans_Id=Std.Stock_Trans_Id"
    			+ " LEFT JOIN CAT_OWNER.CONSTRUCTION constr ON constr.CODE=Sts.CONTRACT_CODE"
                +" where  std.STOCK_TRANS_ID in (:ids) "
                + " group by Std.GOODS_ID,Std.Goods_Name, Std.Goods_Code, Std.Goods_Unit_Name, Std.Goods_State_Name, NVL(constr.NAME,Sts.Dept_Receive_Name)"
                + " ORDER BY Std.GOODS_ID");
    	
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("totalPrice", new DoubleType());
		query.addScalar("deptReceiveName", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(StockTransDetailDTO.class));
		
		query.setParameterList("ids", ids);
		
		return query.list();
    }
    
    
    public List<StockTransDTO> getDemptReceive(List<Long> ids) {
    	StringBuilder sql = new StringBuilder("SELECT  NVL(NVL(constr.NAME,Sts.Dept_Receive_Name),'') deptReceiveName FROM  STOCK_TRANS Sts "
    			+ " LEFT JOIN CAT_OWNER.CONSTRUCTION constr ON constr.CODE=Sts.CONTRACT_CODE"
    			+ " WHERE Sts.STOCK_TRANS_ID in (:ids)  GROUP BY NVL(NVL(constr.NAME,Sts.Dept_Receive_Name),'') ");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.addScalar("deptReceiveName", new StringType());
    	query.setResultTransformer(Transformers.aliasToBean(StockTransDTO.class));
    	query.setParameterList("ids", ids);
    	return query.list();
    }
    
    public List<BigDecimal> getListTotal(List<Long> ids){
    	StringBuilder sql = new StringBuilder("SELECT SUM(NVL(TOTAL_PRICE,0)) FROM  STOCK_TRANS_DETAIL WHERE STOCK_TRANS_ID in (:ids) GROUP BY GOODS_ID ORDER BY GOODS_ID");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.setParameterList("ids", ids);
    	return query.list();
    }
    
}
    
