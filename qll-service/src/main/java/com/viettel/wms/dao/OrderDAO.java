/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.OrderBO;
//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.dto.ObjectReferenceDTO;
import com.viettel.wms.dto.OrderDTO;
import com.viettel.wms.dto.OrderGoodsDetailDTO;
import com.viettel.wms.dto.SignVofficeDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
@Repository("orderDAO")
public class OrderDAO extends BaseFWDAOImpl<OrderBO, Long> {

    public OrderDAO() {
        this.model = new OrderBO();
    }

    public OrderDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
	public List<OrderDTO> doSearchImportRequirement(OrderDTO obj){
		StringBuilder sql = new StringBuilder("SELECT o.ORDER_ID orderId,"
				+ "o.CODE code,"
				+ "o.TYPE type,"
				+ "o.STOCK_ID stockId,"
				+ "s.NAME stockName,"
				+ "s.CODE stockCode,"
				+ "o.CREATED_DATE createdDate,"
				+ "o.CREATED_BY createdBy,"
				+ "o.CREATED_DEPTED_ID createdDeptedId,"
				+ "o.CREATED_BY_NAME createdByName,"
				+ "o.CREATED_DEPTED_NAME createdDeptedName,"
				+ "o.SIGN_STATE signState,"
				+ "o.BUSSINESS_TYPE bussinessType,"
				+ "o.STATUS status "
				+ "FROM WMS_OWNER_KTTS.\"ORDER\" o " 
				+ "INNER JOIN CAT_OWNER.STOCK s ON o.STOCK_ID = s.STOCK_ID "
				+ "WHERE o.TYPE = 1");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(o.CODE) LIKE upper(:keySearch))");
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0) {
			sql.append(" AND o.STOCK_ID IN (:listStockId)");
		}
		if (obj.getCreatedDeptedName() != null) {
			sql.append(" AND o.CREATED_DEPTED_NAME = :createdDeptedName");
		}
		if(obj.getCreatedBy() != null){
			sql.append(" AND o.CREATED_BY = :createdBy");
		}
		if (obj.getCreatedDateFrom() != null) {
			sql.append(" AND o.CREATED_DATE >= :createdDateFrom");
		}
		if (obj.getCreatedDateTo() != null) {
			sql.append(" AND o.CREATED_DATE <= :createdDateTo");
		}
		if (obj.getListBussinessType()!=null && obj.getListBussinessType().size()>0){
			sql.append(" AND o.BUSSINESS_TYPE IN (:listBussinessType)");
		}
		if (obj.getListStatus()!=null && obj.getListStatus().size()>0){
			sql.append(" AND o.STATUS IN (:listStatus)");
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			sql.append(" AND o.SIGN_STATE IN (:listSignState)");
		}
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			sql.append(" AND ((upper(o.CONSTR_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.CONTRACT_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.PROJECT_CODE) LIKE upper(:otherKey))) ");
		}
		
		sql.append(" ORDER BY o.ORDER_ID DESC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("orderId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDeptedId", new LongType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDeptedName", new StringType());
		query.addScalar("bussinessType", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("status", new StringType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch() + "%");
			queryCount.setParameter("keySearch", "%" + obj.getKeySearch() + "%");
		}
		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		
		if (obj.getCreatedDeptedName() != null) {
			query.setParameter("createdDeptedName", obj.getCreatedDeptedName());
			queryCount.setParameter("createdDeptedName", obj.getCreatedDeptedName());
		}
		if(obj.getCreatedBy() != null){
			query.setParameter("createdBy", obj.getCreatedBy());
			queryCount.setParameter("createdBy", obj.getCreatedBy());
		}
		if (obj.getCreatedDateFrom() != null) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
		}
		
		if (obj.getCreatedDateTo() != null) {
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		
		if (obj.getListBussinessType() !=null && obj.getListBussinessType().size()>0){
			query.setParameterList("listBussinessType", obj.getListBussinessType());
			queryCount.setParameterList("listBussinessType", obj.getListBussinessType());
		}
		if (obj.getListStatus() != null && obj.getListStatus().size()>0){
			query.setParameterList("listStatus", obj.getListStatus());
			queryCount.setParameterList("listStatus", obj.getListStatus());
		}
		
		if (obj.getListStockId() != null && obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			query.setParameterList("listSignState", obj.getListSignState());
			queryCount.setParameterList("listSignState", obj.getListSignState());
		}
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			query.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
			queryCount.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
		}
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    @SuppressWarnings("unchecked")
	public List<OrderDTO> getAllImportRequirement(OrderDTO obj){
		StringBuilder sql = new StringBuilder("SELECT o.ORDER_ID orderId,"
				+ "o.CODE code,"
				+ "o.TYPE type,"
				+ "o.STOCK_ID stockId,"
				+ "s.NAME stockName,"
				+ "s.CODE stockCode,"
				+ "o.CREATED_DATE createdDate,"
				+ "o.CREATED_BY createdBy,"
				+ "o.CREATED_DEPTED_ID createdDeptedId,"
				+ "o.CREATED_BY_NAME createdByName,"
				+ "o.CREATED_DEPTED_NAME createdDeptedName,"
				+ "o.SIGN_STATE signState,"
				+ "o.BUSSINESS_TYPE bussinessType,"
				+ "o.STATUS status "
				+ "FROM WMS_OWNER_KTTS.\"ORDER\" o " 
				+ "INNER JOIN CAT_OWNER.STOCK s ON o.STOCK_ID = s.STOCK_ID "
				+ "WHERE o.TYPE = 1");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(o.CODE) LIKE upper(:keySearch))");
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0) {
			sql.append(" AND o.STOCK_ID IN (:listStockId)");
		}
		if (obj.getCreatedDeptedName() != null) {
			sql.append(" AND o.CREATED_DEPTED_NAME = :createdDeptedName");
		}
		if(obj.getCreatedBy() != null){
			sql.append(" AND o.CREATED_BY = :createdBy");
		}
		if (obj.getCreatedDateFrom() != null) {
			sql.append(" AND o.CREATED_DATE >= :createdDateFrom");
		}
		if (obj.getCreatedDateTo() != null) {
			sql.append(" AND o.CREATED_DATE <= :createdDateTo");
		}
		if (obj.getListBussinessType()!=null && obj.getListBussinessType().size()>0){
			sql.append(" AND o.BUSSINESS_TYPE IN (:listBussinessType)");
		}
		if (obj.getListStatus()!=null && obj.getListStatus().size()>0){
			sql.append(" AND o.STATUS IN (:listStatus)");
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			sql.append(" AND o.SIGN_STATE IN (:listSignState)");
		}
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			sql.append(" AND ((upper(o.CONSTR_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.CONTRACT_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.PROJECT_CODE) LIKE upper(:otherKey))) ");
		}
		
		sql.append(" ORDER BY o.ORDER_ID DESC");
		
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("orderId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDeptedId", new LongType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDeptedName", new StringType());
		query.addScalar("bussinessType", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("status", new StringType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch() + "%");
		}
		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		
		if (obj.getCreatedDeptedName() != null) {
			query.setParameter("createdDeptedName", obj.getCreatedDeptedName());
		}
		if(obj.getCreatedBy() != null){
			query.setParameter("createdBy", obj.getCreatedBy());
		}
		if (obj.getCreatedDateFrom() != null) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
		}
		
		if (obj.getCreatedDateTo() != null) {
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		
		if (obj.getListBussinessType() !=null && obj.getListBussinessType().size()>0){
			query.setParameterList("listBussinessType", obj.getListBussinessType());
		}
		if (obj.getListStatus() != null && obj.getListStatus().size()>0){
			query.setParameterList("listStatus", obj.getListStatus());
		}
		
		if (obj.getListStockId() != null && obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			query.setParameterList("listSignState", obj.getListSignState());
		}
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			query.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
		}
		
		return query.list();
	}
    @SuppressWarnings("unchecked")
	public List<OrderDTO> doSearchForCreateImportNote(OrderDTO obj){
		StringBuilder sql = new StringBuilder("SELECT o.ORDER_ID orderId,"
				+ "o.CODE code,"
				+ "o.TYPE type,"
				+ "o.BUSSINESS_TYPE bussinessType,"
				+ "o.STOCK_ID stockId,"
				+ "s.NAME stockName,"
				+ "s.CODE stockCode,"
				+ "o.CREATED_DATE createdDate,"
				+ "o.CREATED_BY createdBy,"
				+ "o.CREATED_DEPTED_ID createdDeptedId,"
				+ "o.CREATED_BY_NAME createdByName,"
				+ "o.CREATED_DEPTED_NAME createdDeptedName,"
				+ "o.SIGN_STATE signState,"
				+ "o.STATUS status "
				+ "FROM WMS_OWNER_KTTS.\"ORDER\" o " 
				+ "INNER JOIN CAT_OWNER.STOCK s ON o.STOCK_ID = s.STOCK_ID "
				+ "WHERE o.TYPE = 1");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(o.CODE) LIKE upper(:keySearch))");
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0) {
			sql.append(" AND o.STOCK_ID IN (:listStockId)");
		}
		if (obj.getCreatedDeptedName() != null) {
			sql.append(" AND o.CREATED_DEPTED_NAME = :createdDeptedName");
		}
		if(obj.getCreatedBy() != null){
			sql.append(" AND o.CREATED_BY = :createdBy");
		}
		if (obj.getCreatedDateFrom() != null) {
			sql.append(" AND o.CREATED_DATE >= :createdDateFrom");
		}
		if (obj.getCreatedDateTo() != null) {
			sql.append(" AND o.CREATED_DATE <= :createdDateTo");
		}
		if (obj.getListBussinessType()!=null && obj.getListBussinessType().size()>0){
			sql.append(" AND o.BUSSINESS_TYPE IN (:listBussinessType)");
		}
		if (obj.getListStatus()!=null && obj.getListStatus().size()>0){
			sql.append(" AND o.STATUS IN (:listStatus)");
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			sql.append(" AND o.SIGN_STATE IN (:listSignState)");
		}
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			sql.append(" AND ((upper(o.CONSTR_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.CONTRACT_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.PROJECT_CODE) LIKE upper(:otherKey))) ");
		}
		
		sql.append(" ORDER BY o.ORDER_ID DESC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("orderId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDeptedId", new LongType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDeptedName", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("bussinessType", new StringType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (obj.getCreatedDateFrom() != null) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
		}
		if (obj.getCreatedDateTo() != null) {
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if (obj.getCreatedDeptedName() != null) {
			query.setParameter("createdDeptedName", obj.getCreatedDeptedName());
			queryCount.setParameter("createdDeptedName", obj.getCreatedDeptedName());
		}
		if(obj.getCreatedBy() != null){
			query.setParameter("createdBy", obj.getCreatedBy());
			queryCount.setParameter("createdBy", obj.getCreatedBy());
		}
		if(obj.getCreatedBy() != null){
			query.setParameter("createdBy", obj.getCreatedBy());
			queryCount.setParameter("createdBy", obj.getCreatedBy());
		}
		if (obj.getListBussinessType()!=null && obj.getListBussinessType().size()>0){
			query.setParameterList("listBussinessType", obj.getListBussinessType());
			queryCount.setParameterList("listBussinessType", obj.getListBussinessType());
		}
		if (obj.getListStatus()!=null && obj.getListStatus().size()>0){
			query.setParameterList("listStatus", obj.getListStatus());
			queryCount.setParameterList("listStatus", obj.getListStatus());
		}
		if (obj.getListStockId() != null && obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
		if (obj.getListSignState()!=null && obj.getListSignState().size()>0){
			query.setParameterList("listSignState", obj.getListSignState());
			queryCount.setParameterList("listSignState", obj.getListSignState());
		}
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			query.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
			queryCount.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
		}
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
    
	public List<OrderDTO> doSearchExportRequirement(OrderDTO obj){
    	StringBuilder sql = new StringBuilder(" SELECT o.ORDER_ID orderId,"
				+ " o.CODE code,"
				+ " o.TYPE type,"
				+ " o.STOCK_ID stockId,"
				+ " o.IE_STOCK_ID ieStockId, "
				+ " s.NAME stockName,"
				+ " s1.NAME ieStockName,"
				+ " s.CODE stockCode,"
				+ " s1.CODE ieStockCode, "
				+ " o.CREATED_BY_NAME createdByName,"
				+ " o.CREATED_DEPTED_ID createdDeptedId,"
				+ " o.CREATED_DEPTED_NAME createdDeptedName,"
				+ " o.SIGN_STATE signState,"
				+ " o.BUSSINESS_TYPE bussinessType,"
				+ " o.CONTRACT_CODE contractCode,"
				+ " o.DEPT_RECEIVE_NAME deptReceiveName,"
				+ " o.DEPT_RECEIVE_ID deptReceiveId,"
				+ " o.CONSTR_CODE constrCode,"
				+ " c.NAME constructionName," 
				+ " o.PARTNER_ID partnerId,"
				+ " p.NAME partnerName,"
				+ " o.PROJECT_CODE projectCode, "
				+ " proj.NAME projectName, "
				+ " reason.NAME reasonToExport, "
				+ " o.REASON_ID reasonId, "
				+ " o.RECEIVER_ID recieverId,"
				+ " o.EXPECTED_RECIEVED_DATE expectedRecievedDate,"
				+ " o.IE_STOCK_TRANS_ID ieStockTransId,"
				+ " str.CODE stockTransCode,"
				+ " o.CREATED_BY createdBy,"
				+ " o.CANCEL_BY cancelBy,"
				+ " o.CANCEL_REASON_NAME cancelReasonName,"
				+ " o.CANCEL_DESCRIPTION cancelDescription, "
				+ " o.CREATED_DATE createdDate,"
				+ " o.CANCEL_DATE cancelDate,"
				+ " o.DESCRIPTION description,"
				+ " sysUser.FULL_NAME recieverName,"
				+ " sysUser.LOGIN_NAME loginName,"
				+ " o.STATUS status "
				+ " FROM WMS_OWNER_KTTS.\"ORDER\" o " 
				+ " LEFT JOIN CAT_OWNER.STOCK s ON o.STOCK_ID = s.STOCK_ID "
				+ " LEFT JOIN CAT_OWNER.STOCK s1 ON o.IE_STOCK_ID = s1.STOCK_ID "
		        + " LEFT JOIN CAT_OWNER.CONSTRUCTION c  ON c.CODE =  o.CONSTR_CODE"
		        + " LEFT JOIN CAT_OWNER.PARTNER p ON p.PARTNER_ID = o.PARTNER_ID"
		        + " LEFT JOIN IMS_OWNER.I_PROJ_INVEST_PROJECT proj ON proj.CODE = o.PROJECT_CODE"
		        + " LEFT JOIN CAT_OWNER.REASON reason ON reason.REASON_ID = o.REASON_ID"
		        + " LEFT JOIN VPS_OWNER.SYS_USER sysUser ON sysUser.SYS_USER_ID =  o.RECEIVER_ID"
		        + " LEFT JOIN WMS_OWNER_KTTS.STOCK_TRANS str ON str.STOCK_TRANS_ID =  o.IE_STOCK_TRANS_ID"
				+ " WHERE o.TYPE=2 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(o.CODE) LIKE upper(:keySearch) escape '&')");
		}
		
		if (obj.getListStockId() != null && obj.getListStockId().size()>0) {
			sql.append(" AND o.STOCK_ID IN (:listStockId)");
		}
		
		if (obj.getCreatedBy() != null){
			sql.append(" AND o.CREATED_BY = :createdBy ");
		}
		if (obj.getCreatedDeptedId() != null){
			sql.append(" AND o.CREATED_DEPTED_ID = :createdDeptedId ");
		}
		if (obj.getDeptReceiveId() != null){
			sql.append(" AND o.DEPT_RECEIVE_ID = :deptReceiveId ");
		}
		if ((obj.getCreatedDateFrom() != null)) {
				sql.append(" AND o.CREATED_DATE >= :createdDateFrom");
		}
		if(obj.getCreatedDateTo() != null){
			sql.append(" AND o.CREATED_DATE <=:createdDateTo");
		}
		if (obj.getListBussinessType() != null && obj.getListBussinessType().size() >0){
			sql.append(" AND o.BUSSINESS_TYPE IN (:listBussinessType)");
		}
		if (obj.getListStatus() != null && obj.getListStatus().size()>0){
			sql.append(" AND o.STATUS IN (:listStatus)");
		}
		if (obj.getListSignState() != null && obj.getListSignState().size() > 0){
			sql.append(" AND o.SIGN_STATE IN (:listSignState)");
		}
		
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			sql.append(" AND ((upper(o.CONSTR_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.PARTNER_ID) LIKE upper(:otherKey)) "
					+ "OR (upper(o.CONTRACT_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.PROJECT_CODE) LIKE upper(:otherKey))) ");
		}
		
		sql.append(" ORDER BY o.ORDER_ID DESC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("orderId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("ieStockId", new LongType());
		query.addScalar("stockName", new StringType());
		query.addScalar("ieStockName", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("deptReceiveName", new StringType());
		query.addScalar("deptReceiveId", new LongType());
		query.addScalar("createdDeptedName", new StringType());
		query.addScalar("createdDeptedId", new LongType());
		query.addScalar("signState", new StringType());
		query.addScalar("bussinessType", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("reasonId", new LongType());
		query.addScalar("reasonToExport", new StringType());
		query.addScalar("constrCode", new StringType());
		query.addScalar("constructionName", new StringType());
		query.addScalar("partnerId", new LongType());
		query.addScalar("partnerName", new StringType());
		
		query.addScalar("projectCode", new StringType());
		query.addScalar("projectName", new StringType());
		query.addScalar("expectedRecievedDate", new TimestampType());
		
		query.addScalar("stockCode", new StringType());
		query.addScalar("ieStockCode", new StringType());
		query.addScalar("recieverName", new StringType());
		query.addScalar("recieverId", new LongType());
		query.addScalar("loginName", new StringType());
		query.addScalar("ieStockTransId", new LongType());
		query.addScalar("stockTransCode", new StringType());
		
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("cancelBy", new LongType());
		query.addScalar("cancelReasonName", new StringType());
		query.addScalar("cancelDescription", new StringType());
		
		query.addScalar("cancelDate", new TimestampType());
		query.addScalar("description", new StringType());

		
		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		if (obj.getListStockId() != null && obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
		
		if (obj.getDeptReceiveId() != null) {
			query.setParameter("deptReceiveId", obj.getDeptReceiveId());
			queryCount.setParameter("deptReceiveId", obj.getDeptReceiveId());
		}
		
		if (obj.getCreatedDeptedId() != null) {
			query.setParameter("createdDeptedId", obj.getCreatedDeptedId());
			queryCount.setParameter("createdDeptedId", obj.getCreatedDeptedId());
		}
		
		if (obj.getCreatedBy() != null) {
			query.setParameter("createdBy", obj.getCreatedBy());
			queryCount.setParameter("createdBy", obj.getCreatedBy());
		}
		if ((obj.getCreatedDateFrom() != null)) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			
		}
		if(obj.getCreatedDateTo() != null){
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		
		if (obj.getListBussinessType() != null && obj.getListBussinessType().size() >0){
			query.setParameterList("listBussinessType", obj.getListBussinessType());
			queryCount.setParameterList("listBussinessType", obj.getListBussinessType());
		}
		if (obj.getListStatus() != null && obj.getListStatus().size()>0){
			query.setParameterList("listStatus", obj.getListStatus());
			queryCount.setParameterList("listStatus", obj.getListStatus());
		}
		if (obj.getListSignState() != null && obj.getListSignState().size() > 0){
			query.setParameterList("listSignState", obj.getListSignState());
			queryCount.setParameterList("listSignState", obj.getListSignState());
		}
		
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			query.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
			queryCount.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
		}
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
    }
    
    public OrderDTO getOrderDetail(Long id){
		StringBuilder sql = new StringBuilder("WITH B AS (SELECT ORDER_ID,COUNT(*) countSerial FROM ORDER_GOODS_DETAIL WHERE SERIAL IS NOT NULL GROUP BY ORDER_ID) "
				+"SELECT o.ORDER_ID orderId,"
				+ "o.CODE code,"
				+ "o.TYPE type,"
				+ "o.STOCK_ID stockId,"
				+ "s.NAME stockName,"
				+ "s.CODE stockCode,"
				+ "o.CREATED_DATE createdDate,"
				+ "o.CREATED_BY_NAME createdByName,"
				+ "o.CREATED_DEPTED_NAME createdDeptedName,"
				+ "o.SIGN_STATE signState,"
				+ "o.STATUS status, "
				+ "o.SHIPMENT_CODE shipmentCode, "
				+ "o.MAINTAIN_ORDER_CODE maintainOrderCode, "
				+ "o.MAINTAIN_REPORT_CODE maintainReportCode, "
				+ "o.CONS_RETRIEVE_ORDER_CODE consRetrieveOrderCode, "
				+ "o.DEPT_RETRIEVE_ORDER_CODE deptRetrieveOrderCode, "
				+ "o.LOAN_RETRIEVE_ORDER_CODE loanRetrieveOrderCode, "
				+ "st.CODE stockTransCode, "
				+ "o.PROJECT_CODE projectCode, "
				+ "o.PURCHASE_ORDER_NO purchaseOrderNo, "
				+ "o.PURCHASE_ORDER_DATE purchaseOrderDate, "
				+ "o.CER_NO cerNo, "
				+ "o.CER_DATE cerDate, "
				+ "o.SHIPPER_NAME shipperName, "
				+ "o.SHIP_DATE shipDate, "
				+ "o.CREATED_BY createdBy, "
				+ "o.CREATED_BY_NAME createdByName, "
				+ "o.CREATED_DATE createdDate, "
				+ "o.CREATED_DEPTED_ID createdDeptedId, "
				+ "o.CREATED_DEPTED_NAME createdDeptedName, "
				+ "o.DEPT_RECEIVE_NAME deptReceiveName,"
				+ "o.DEPT_RECEIVE_ID deptReceiveId,"
				+ "o.CONTRACT_CODE contractCode, "
				+ "o.DESCRIPTION description, "
				+ "o.CANCEL_BY cancelBy, "
				+ "c.NAME constructionName, " 
				+ "o.RECEIVER_ID recieverId, "
				+ "o.CANCEL_BY_NAME cancelByName, "
				+ "o.CANCEL_DESCRIPTION cancelDescription, "
				+ "o.CANCEL_DATE cancelDate, "
				+ "o.EXPECTED_RECIEVED_DATE expectedRecievedDate, "
				+ "o.CANCEL_REASON_NAME cancelReasonName, "
				+ "o.BUSSINESS_TYPE bussinessType, "
				+ "o.CONSTR_CODE constrCode, "
				+ "sysUser.FULL_NAME recieverName, "
				+"o.PARTNER_ID partnerId, o.REASON_ID reasonId,  "
				+ "o.DESCRIPTION description "
				+ ",B.countSerial countSerial "
				+ "FROM WMS_OWNER_KTTS.\"ORDER\" o " 
				+ "INNER JOIN CAT_OWNER.STOCK s ON o.STOCK_ID = s.STOCK_ID "
				+ "LEFT JOIN WMS_OWNER_KTTS.STOCK_TRANS st ON o.ORDER_ID = st.ORDER_ID "
				+ " LEFT JOIN B ON B.ORDER_ID = o.ORDER_ID "
				+ " LEFT JOIN VPS_OWNER.SYS_USER sysUser ON sysUser.SYS_USER_ID =  o.RECEIVER_ID "
				+ " LEFT JOIN CAT_OWNER.CONSTRUCTION c  ON c.CODE =  o.CONSTR_CODE "
				+ "WHERE o.ORDER_ID = :orderId");
		
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("orderId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDeptedName", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("deptReceiveName", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("shipmentCode", new StringType());
		query.addScalar("maintainOrderCode", new StringType());
		query.addScalar("maintainReportCode", new StringType());
		query.addScalar("consRetrieveOrderCode", new StringType());
		query.addScalar("deptRetrieveOrderCode", new StringType());
		query.addScalar("loanRetrieveOrderCode", new StringType());
		query.addScalar("stockTransCode", new StringType());
		query.addScalar("projectCode", new StringType());
		query.addScalar("purchaseOrderNo", new StringType());
		query.addScalar("purchaseOrderDate", new TimestampType());
		query.addScalar("cerNo", new StringType());
		query.addScalar("cerDate", new TimestampType());
		query.addScalar("shipperName", new StringType());
		query.addScalar("shipDate", new TimestampType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdDeptedId", new LongType());
		query.addScalar("createdDeptedName", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("cancelBy", new LongType());
		query.addScalar("cancelByName", new StringType());
		query.addScalar("cancelDescription", new StringType());
		query.addScalar("cancelDate", new TimestampType());
		query.addScalar("cancelReasonName", new StringType());
		query.addScalar("bussinessType", new StringType());
		query.addScalar("constrCode", new StringType());
		query.addScalar("partnerId", new LongType());
		query.addScalar("reasonId", new LongType());
		query.addScalar("countSerial", new LongType());
		query.addScalar("deptReceiveId", new LongType());
		query.addScalar("recieverName", new StringType());
		query.addScalar("recieverId", new LongType());
		query.addScalar("expectedRecievedDate", new TimestampType());
		query.addScalar("constructionName", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		
		query.setParameter("orderId", id);
		
		return (OrderDTO) query.uniqueResult();
	}
    
    
 @SuppressWarnings("unchecked")
public List<OrderDTO> doSearchExportStatement(OrderDTO obj){
	 StringBuilder sql = new StringBuilder("SELECT o.ORDER_ID orderId,"
				+ "o.CODE code,"
				+ "o.TYPE type,"
				+ "o.STOCK_ID stockId,"
				+ "s.NAME stockName,"
				+ "o.CREATED_DATE createdDate,"
				+ "o.CREATED_BY_NAME createdByName,"
				+ "o.CREATED_DEPTED_NAME createdDeptedName,"
				+ "o.SIGN_STATE signState,"
				+ "o.BUSSINESS_TYPE bussinessType,"
				+ "o.STATUS status "
				+ "FROM WMS_OWNER_KTTS.\"ORDER\" o " 
				+ "INNER JOIN CAT_OWNER.STOCK s ON o.STOCK_ID = s.STOCK_ID "
				+ "WHERE o.type=2");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(o.CODE) LIKE upper(:keySearch))");
		}
		if (obj.getCreatedDateFrom() != null) {
			sql.append(" AND o.CREATED_DATE >= :createdDateFrom");
		}
		if(obj.getCreatedDateTo() != null){
			sql.append(" AND o.CREATED_DATE <= :createdDateTo");
		}
		if (obj.getListBussinessType().size()>0){
			sql.append(" AND o.BUSSINESS_TYPE IN (:listBussinessType)");
		}
		if (obj.getListStatus().size()>0){
			sql.append(" AND o.STATUS IN (:listStatus)");
		}
		if (obj.getListSignState().size()>0){
			sql.append(" AND o.SIGN_STATE IN (:listSignState)");
		}
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			sql.append(" AND ((upper(o.CONSTR_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.PARTNER_ID) LIKE upper(:otherKey)) "
					+ "OR (upper(o.CONTRACT_CODE) LIKE upper(:otherKey)) "
					+ "OR (upper(o.PROJECT_CODE) LIKE upper(:otherKey))) ");
		}
		
		sql.append(" ORDER BY o.ORDER_ID");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("orderId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDeptedName", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("bussinessType", new StringType());
		query.addScalar("status", new StringType());
		
		
		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (obj.getCreatedDateFrom() != null) {
			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
		
		}
		if(obj.getCreatedDateTo() != null){
			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
		}
		if (obj.getListBussinessType().size()>0){
			query.setParameterList("listBussinessType", obj.getListBussinessType());
			queryCount.setParameterList("listBussinessType", obj.getListBussinessType());
		}
		if (obj.getListStatus().size()>0){
			query.setParameterList("listStatus", obj.getListStatus());
			queryCount.setParameterList("listStatus", obj.getListStatus());
		}
		if (obj.getListSignState().size()>0){
			query.setParameterList("listSignState", obj.getListSignState());
			queryCount.setParameterList("listSignState", obj.getListSignState());
		}
		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
			query.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
			queryCount.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
		}
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
  	}
      
 @SuppressWarnings("unchecked")
 public List<OrderDTO> getAllExportStatement(OrderDTO obj){
 	 StringBuilder sql = new StringBuilder("SELECT o.ORDER_ID orderId,"
 				+ "o.CODE code,"
 				+ "o.TYPE type,"
 				+ "o.STOCK_ID stockId,"
 				+ "s.NAME stockName,"
 				+ "o.CREATED_DATE createdDate,"
 				+ "o.CREATED_BY_NAME createdByName,"
 				+ "o.CREATED_DEPTED_NAME createdDeptedName,"
 				+ "o.SIGN_STATE signState,"
 				+ "o.BUSSINESS_TYPE bussinessType,"
 				+ "o.STATUS status "
 				+ "FROM WMS_OWNER_KTTS.\"ORDER\" o " 
 				+ " LEFT JOIN CAT_OWNER.STOCK s ON o.STOCK_ID = s.STOCK_ID "
 				+ " LEFT JOIN CAT_OWNER.DEPARTMENT cd ON cd.DEPARTMENT_ID = o.CREATED_DEPTED_ID "
		        + " LEFT JOIN CAT_OWNER.DEPARTMENT rd ON rd.DEPARTMENT_ID = o.DEPT_RECEIVE_ID "
 				+ "WHERE o.type=2");
 		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
 			sql.append(" AND (upper(o.CODE) LIKE upper(:keySearch))");
 		}
 		if (obj.getCreatedDateFrom() != null) {
 			sql.append(" AND o.CREATED_DATE >= :createdDateFrom");
 		}
 		if(obj.getCreatedDateTo() != null){
 			sql.append(" AND o.CREATED_DATE <= :createdDateTo");
 		}
 		if (obj.getListBussinessType().size()>0){
 			sql.append(" AND o.BUSSINESS_TYPE IN (:listBussinessType)");
 		}
 		if (obj.getCreatedDeptedId() != null){
			sql.append(" AND o.CREATED_DEPTED_ID = :createdDeptedId ");
		}
		if (obj.getDeptReceiveId() != null){
			sql.append(" AND o.DEPT_RECEIVE_ID = :deptReceiveId ");
		}
 		if (obj.getListStockId() != null && obj.getListStockId().size()>0) {
			sql.append(" AND o.STOCK_ID IN (:listStockId)");
		}
 		if (obj.getCreatedBy() != null){
			sql.append(" AND o.CREATED_BY = :createdBy ");
		}
 		if (obj.getListStatus().size()>0){
 			sql.append(" AND o.STATUS IN (:listStatus)");
 		}
 		if (obj.getListSignState().size()>0){
 			sql.append(" AND o.SIGN_STATE IN (:listSignState)");
 		}
 		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
 			sql.append(" AND ((upper(o.CONSTR_CODE) LIKE upper(:otherKey)) "
 					+ "OR (upper(o.PARTNER_ID) LIKE upper(:otherKey)) "
 					+ "OR (upper(o.CONTRACT_CODE) LIKE upper(:otherKey)) "
 					+ "OR (upper(o.PROJECT_CODE) LIKE upper(:otherKey))) ");
 		}
 		
 		sql.append(" ORDER BY o.ORDER_ID");
 		
 		
 		
 		SQLQuery query= getSession().createSQLQuery(sql.toString());
 		
 		query.addScalar("orderId", new LongType());
 		query.addScalar("stockId", new LongType());
 		query.addScalar("code", new StringType());
 		query.addScalar("type", new StringType());
 		query.addScalar("stockName", new StringType());
 		query.addScalar("createdDate", new TimestampType());
 		query.addScalar("createdByName", new StringType());
 		query.addScalar("createdDeptedName", new StringType());
 		query.addScalar("signState", new StringType());
 		query.addScalar("bussinessType", new StringType());
 		query.addScalar("status", new StringType());
 		
 		
 		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
 		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
 			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
 		}
 		if (obj.getCreatedDateFrom() != null) {
 			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
 		
 		}
 		if(obj.getCreatedDateTo() != null){
 			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
 		}
 		if (obj.getListBussinessType().size()>0){
 			query.setParameterList("listBussinessType", obj.getListBussinessType());
 		}
 		if (obj.getListStockId() != null && obj.getListStockId().size()>0){
			query.setParameterList("listStockId", obj.getListStockId());
		}
 		if (obj.getListStatus().size()>0){
 			query.setParameterList("listStatus", obj.getListStatus());
 		}
 		if (obj.getCreatedBy() != null) {
			query.setParameter("createdBy", obj.getCreatedBy());
		}
 		if (obj.getDeptReceiveId() != null) {
			query.setParameter("deptReceiveId", obj.getDeptReceiveId());
		}
		
		if (obj.getCreatedDeptedId() != null) {
			query.setParameter("createdDeptedId", obj.getCreatedDeptedId());
		}
 		if (obj.getListSignState().size()>0){
 			query.setParameterList("listSignState", obj.getListSignState());
 		}
 		if (StringUtils.isNotEmpty(obj.getOtherKey())) {
 			query.setParameter("otherKey", "%" + ValidateUtils.validateKeySearch(obj.getOtherKey()) + "%");
 		}
 		
 		return query.list();
   	}
 public OrderDTO getOderByShipment(OrderDTO obj) {
		StringBuilder sql = new StringBuilder( "SELECT "
				+ " WOO.ORDER_ID orderId, "
				+ " WOO.CODE code, "
				+ " WOO.CREATED_DATE createdDate, "
				+ " WOO.CREATED_BY createdBy, "
				+ " WOO.DESCRIPTION description, "
				+ " WOO.STATUS status "
				+ " FROM WMS_OWNER_KTTS.\"ORDER\" WOO " 
				+ " WHERE 1=1");
		
		if(obj.getShipmentId() != null)
		{
			sql.append(" AND WOO.SHIPMENT_ID = :shipmentId ");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("orderId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		
		if(obj.getShipmentId() != null)
		{
			query.setParameter("shipmentId",obj.getShipmentId());
		}

		return (OrderDTO) query.uniqueResult();
	}
 
 	public boolean removeOrder(OrderDTO obj){
	 StringBuilder sql = new StringBuilder( "UPDATE "
				+ " WMS_OWNER_KTTS.\"ORDER\" o "
				+ " SET "
				+ " o.STATUS = :status, "
				+ " o.CANCEL_BY = :cancelBy, "
				+ " o.CANCEL_BY_NAME = :cancelByName, "
				+ " o.CANCEL_DATE = :cancelDate, "
				+ " o.CANCEL_REASON_NAME = :cancelReasonName "
				+ " WHERE o.ORDER_ID = :orderId");
	 
	 SQLQuery query = getSession().createSQLQuery(sql.toString());
	 
	 query.setParameter("status","4");
	 query.setLong("cancelBy",obj.getCancelBy());
	 query.setParameter("cancelByName",obj.getCancelByName());
	 query.setTimestamp("cancelDate",new Date());
	 query.setParameter("cancelReasonName",obj.getCancelReasonName());
	 query.setLong("orderId",obj.getOrderId());
	 
	 query.executeUpdate();
	 
	return true;
 	}
 	
 	public boolean updateStatusOrder(OrderDTO obj){
 		 StringBuilder sql = new StringBuilder( "UPDATE "
 					+ " WMS_OWNER_KTTS.\"ORDER\" o "
 					+ " SET "
 					+ " o.STATUS = :status "
 					+ " WHERE o.ORDER_ID = :orderId");
 		 
 		 SQLQuery query = getSession().createSQLQuery(sql.toString());
 		 
 		 query.setParameter("status",obj.getStatus());
 		 query.setLong("orderId",obj.getOrderId());
 		 query.executeUpdate();
 		 
 		return true;
 	 	}
 	
 	public boolean rejectOrder(OrderDTO obj){
 		 StringBuilder sql = new StringBuilder( "UPDATE "
 					+ " WMS_OWNER_KTTS.\"ORDER\" o "
 					+ " SET "
 					+ " o.STATUS = :status, "
 					+ " o.CANCEL_BY = :cancelBy, "
 					+ " o.CANCEL_BY_NAME = :cancelByName, "
 					+ " o.CANCEL_DATE = :cancelDate, "
 					+ " o.CANCEL_REASON_NAME = :cancelReasonName "
 					+ " WHERE o.ORDER_ID = :orderId");
 		 
 		 SQLQuery query = getSession().createSQLQuery(sql.toString());
 		 
 		 query.setParameter("status","5");
 		 query.setLong("cancelBy",obj.getCancelBy());
 		 query.setParameter("cancelByName",obj.getCancelByName());
 		 query.setTimestamp("cancelDate",new Date());
 		 query.setParameter("cancelReasonName",obj.getCancelReasonName());
 		 query.setLong("orderId",obj.getOrderId());
 		 
 		 query.executeUpdate();
 		 
 		return true;
 	 	}

	public OrderDTO getOrder(Long id) {
		StringBuilder sql = new StringBuilder("SELECT o.ORDER_ID orderId, "
				+ "o.CODE code, " + "s.NAME stockName, " + "o.STOCK_ID stockId, "
				+ "o.TYPE type,"
				+ "o.CREATED_DATE createdDate,"
			    + "o.CREATED_BY_NAME createdByName,"
				+ "o.CREATED_DEPTED_NAME createdDeptedName,"
				+ "o.SIGN_STATE signState,"
				+ "o.STATUS status, "
				+ "o.SHIPMENT_CODE shipmentCode, "
					+ "o.MAINTAIN_ORDER_CODE maintainOrderCode, "
					+ "o.MAINTAIN_REPORT_CODE maintainReportCode, "
				+ "o.CONS_RETRIEVE_ORDER_CODE consRetrieveOrderCode, "
				+ "o.DEPT_RETRIEVE_ORDER_CODE deptRetrieveOrderCode, "
				+ "o.LOAN_RETRIEVE_ORDER_CODE loanRetrieveOrderCode, "
				+ "o.PROJECT_CODE projectCode, "
				+ "o.PURCHASE_ORDER_NO purchaseOrderNo, "
				+ "o.PURCHASE_ORDER_DATE purchaseOrderDate, "
				+ "o.CER_NO cerNo, "
				+ "o.CER_DATE cerDate, "
				+ "o.SHIPPER_NAME shipperName, "
				+ "o.SHIP_DATE shipDate, "
				+ "o.CREATED_BY_NAME createdByName, "
				+ "o.CREATED_DATE createdDate, "
				+ "o.CREATED_DEPTED_NAME createdDeptedName, "
				+ "o.CONTRACT_CODE contractCode, "
				+ "o.BUSSINESS_TYPE bussinessType "
				 + "FROM WMS_OWNER_KTTS.\"ORDER\" o " 
				+ "INNER JOIN CAT_OWNER.STOCK s ON o.STOCK_ID = s.STOCK_ID " + 
				 "WHERE o.ORDER_ID = :orderId");
		
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("orderId", new LongType());
		query.addScalar("stockId", new LongType());
		query.addScalar("stockName", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDeptedName", new StringType());
		query.addScalar("signState", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("shipmentCode", new StringType());
		query.addScalar("maintainOrderCode", new StringType());
		query.addScalar("maintainReportCode", new StringType());
		query.addScalar("consRetrieveOrderCode", new StringType());
		query.addScalar("deptRetrieveOrderCode", new StringType());
		query.addScalar("loanRetrieveOrderCode", new StringType());
		query.addScalar("projectCode", new StringType());
		query.addScalar("purchaseOrderNo", new StringType());
		query.addScalar("purchaseOrderDate", new TimestampType());
		query.addScalar("cerNo", new StringType());
		query.addScalar("cerDate", new TimestampType());
		query.addScalar("shipperName", new StringType());
		query.addScalar("shipDate", new TimestampType());
		query.addScalar("createdByName", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("createdDeptedName", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("bussinessType", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		
		query.setParameter("orderId", id);
		
		return (OrderDTO) query.uniqueResult();

	}

	public List<CommonDTO> getCharThree(CommonDTO obj){
		StringBuilder sql = new StringBuilder("SELECT  Count(CASE WHEN ST.TYPE=1 AND ST.STATUS=1 THEN 1 else null end ) imStock"
				+ ",Count(CASE WHEN ST.TYPE=2 AND ST.STATUS=1 THEN 1 else null end ) outStock"
				+ ", STOCK.CODE stockCode"
				+ " FROM  WMS_OWNER_KTTS.\"ORDER\" ST "
				+ " LEFT JOIN CAT_OWNER.STOCK STOCK ON STOCK.STOCK_ID=ST.STOCK_ID"
				+ " WHERE 1=1");
		
		if(obj.getListStockId().size()>0){
    		sql.append(" AND ST.STOCK_ID in (:listStockId)");
    	}
		
    	sql.append(" group by ST.STOCK_ID,STOCK.CODE");
		SQLQuery query=getSession().createSQLQuery(sql.toString());
		
		query.addScalar("imStock",new LongType());
    	query.addScalar("outStock",new LongType());
    	query.addScalar("stockCode",new StringType());
    	
		query.setResultTransformer(Transformers.aliasToBean(CommonDTO.class));
    	
		if(obj.getListStockId().size()>0){
    		query.setParameterList("listStockId", obj.getListStockId());
    	}
		
    	
    	return query.list();
	}
	
	public List<OrderDTO> getGoodsDetailByOrderId(OrderDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT og.ORDER_GOODS_ID orderGoodsId,"
				+ "og.ORDER_ID orderId,"
				+ "og.GOODS_TYPE goodType,"
				+ " og.GOODS_TYPE_NAME goodTypeName,"
				+ "og.GOODS_ID goodsId,"
				+ "og.GOODS_CODE goodsCode,"
				+ "og.GOODS_NAME goodsName,"
				+ "og.GOODS_IS_SERIAL isSerial,"
				+ "og.GOODS_STATE goodsState,"
				+ "og.GOODS_UNIT_ID goodsUnitId,"
				+ "og.GOODS_UNIT_NAME goodsUnitName,"
				+ "og.AMOUNT amount,"
				+ "og.TOTAL_PRICE totalPrice,"
				+ "ogd.SERIAL serial,"
				+ "ogd.CONTRACT_CODE contractCode,"
				+ "ogd.MANUFACTURER_ID manufacturerId,"
				+ "ogd.MANUFACTURER_NAME manufacturerName,"
				+ "ogd.PRODUCING_COUNTRY_ID producingCountryId,"
				+ "ogd.PRODUCING_COUNTRY_NAME producingCountryName,"
				+ "ogd.PRICE price "
				+ "FROM WMS_OWNER_KTTS.ORDER_GOODS og "
				+ "INNER JOIN WMS_OWNER_KTTS.\"ORDER\" o  ON o.ORDER_ID = og.ORDER_ID "
				+ "INNER JOIN WMS_OWNER_KTTS.ORDER_GOODS_DETAIL ogd ON upper(ogd.ORDER_GOODS_ID) LIKE upper(og.ORDER_GOODS_ID)  "
				+ "WHERE "
				+ " og.ORDER_ID = :orderId "
				);
		
		
		sql.append(" ORDER BY og.ORDER_GOODS_ID DESC");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		//query.addScalar("goodsId", new LongType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("amount", new DoubleType());
		//query.addScalar("goodsIsSerial", new LongType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("goodsUnitName", new StringType());
		//query.addScalar("originPrice", new StringType());
		query.addScalar("isSerial", new StringType());
		//query.addScalar("goodsType", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("serial", new StringType());


		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
		
		query.setParameter("orderId", obj.getOrderId());
		
		//return (OrderGoodsDetailDTO) query.uniqueResult();
		return query.list();
	}
    
	 @SuppressWarnings("unchecked")
	 public List<OrderDTO> checkDupOrderCode(OrderDTO obj){
	 	 StringBuilder sql = new StringBuilder("SELECT o.ORDER_ID orderId, "
	 				+ "o.CODE code "
	 				+ "FROM WMS_OWNER_KTTS.\"ORDER\" o " 
	 				+ "WHERE ");
	 		if (StringUtils.isNotEmpty(obj.getCode())) {
	 			sql.append("(upper(o.CODE) LIKE upper(:code))");
	 		}
	 			 		
	 		
	 		SQLQuery query= getSession().createSQLQuery(sql.toString());
	 		
	 		query.addScalar("orderId", new LongType());
	 		query.addScalar("code", new StringType());
	 		
	 		
	 		
	 		query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
	 		if (StringUtils.isNotEmpty(obj.getCode())) {
	 			query.setParameter("code", ValidateUtils.validateKeySearch(obj.getCode()));
	 		}
	 		
	 		return query.list();
	   	}
	 
	 public OrderDTO getOderByShipmentId(OrderDTO obj) {
			StringBuilder sql = new StringBuilder( "SELECT WOO.CODE code, "
					+ " WOO.CREATED_DATE createdDate, "
					+ " WOO.DESCRIPTION description, "
					+ " WOO.STATUS status,"
					+ " WOO.CREATED_BY_NAME createdByName"
					+ "  FROM WMS_OWNER_KTTS.\"ORDER\" WOO " 
					+ " WHERE 1=1");
			
			if(obj.getShipmentId() != null)
			{
				sql.append(" AND WOO.SHIPMENT_ID = :shipmentId ");
			}
			
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
			query.addScalar("code", new StringType());
			query.addScalar("createdDate", new TimestampType());
			query.addScalar("description", new StringType());
			query.addScalar("status", new StringType());
			query.addScalar("createdByName", new StringType());

			query.setResultTransformer(Transformers.aliasToBean(OrderDTO.class));
			
			if(obj.getShipmentId() != null)
			{
				query.setParameter("shipmentId",obj.getShipmentId());
			}

			return (OrderDTO) query.uniqueResult();
		}
}
