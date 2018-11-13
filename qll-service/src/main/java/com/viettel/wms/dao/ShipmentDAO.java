/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.ShipmentBO;
import com.viettel.wms.dto.ShipmentDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("shipmentDAO")
public class ShipmentDAO extends BaseFWDAOImpl<ShipmentBO, Long> {

	public ShipmentDAO() {
		this.model = new ShipmentBO();
	}

	public ShipmentDAO(Session session) {
		this.session = session;
	}
	//Hàm lấy Mã lô hàng
	public ShipmentDTO getbycode(String code) {
		StringBuilder sql = new StringBuilder(
				"SELECT SHIPMENT_ID shipmentId,"
						+ "CODE code"
						+ " FROM WMS_OWNER_KTTS.SHIPMENT WHERE upper(CODE)=upper(:code)");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("shipmentId", new LongType());
		query.addScalar("code", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ShipmentDTO.class));

		query.setParameter("code", code);

		return (ShipmentDTO) query.uniqueResult();
	}
	//End
//Hàm tìm kiếm danh sách lô hàng
	@SuppressWarnings("unchecked")
	public List<ShipmentDTO> doSearch(ShipmentDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT"
						+ " WS.SHIPMENT_ID shipmentId,WS.CODE code,WS.CONTRACT_ID contractId,WS.CONTRACT_CODE contractCode,WS.CREATED_BY createdBy,"
						+ " WS.TYPE	type,WS.PROJ_INVEST_PROJECT_ID	projInvestProjectId,WS.PROJECT_CODE	projectCode,WS.SHIPER	shiper,"
						+ " WS.SHIPER_DATE	shiperDate,WS.SHIP_PLACE	shipPlace,WS.CUSTOMS_PROCEDURE	customsProcedure,WS.DESCRIPTION	description,"
						+ " WS.CREATED_DATE	createdDate,VPS_OWNER.SYS_USER.FULL_NAME	fullName,VPS_OWNER.SYS_GROUP.NAME	createdDeptName,WS.UPDATED_BY	updatedBy,"
						+ " WS.UPDATED_DATE	updatedDate,WS.STATUS	status,WS.CANCEL_DATE	cancelDate,WS.CANCEL_USER_ID	cancelUserId,WS.CANCEL_REASON_NAME	cancelReasonName,"
						+ " WS.CANCEL_DESCRIPTION	cancelDescription,WS.FEE_DESCRIPTION	feeDescription,WS.TOTAL_ORIGIN_MONEY	totalOriginMoney,WS.TOTAL_FEE	totalFee,"
						+ " WS.TOTAL_TAX	totalTax,WS.TOTAL_MONEY	totalMoney,WS.ORDER_CHECK_CODE	orderCheckCode,WS.REPORT_CHECK_CODE	reportCheckCode, WS.CREATED_DEPT_ID createdDeptId "
						+ " FROM WMS_OWNER_KTTS.SHIPMENT WS "
						+ " LEFT JOIN VPS_OWNER.SYS_USER  ON VPS_OWNER.SYS_USER.SYS_USER_ID=WS.CREATED_BY"
						+ " LEFT JOIN VPS_OWNER.SYS_GROUP  ON VPS_OWNER.SYS_GROUP.SYS_GROUP_ID=WS.CREATED_DEPT_ID"
						+ " WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(WS.CODE) LIKE upper(:keySearch) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getKeySearch2())) {
			sql.append(" AND (upper(WS.CONTRACT_CODE) LIKE upper(:keySearch2) escape '&')");
		}
		if (obj.getListStatus()!=null && !obj.getListStatus().isEmpty()) {
			sql.append(" AND WS.STATUS IN (:listStatus)");
		}
		if ("1".equals(obj.getType()) || "2".equals(obj.getType())) {
			sql.append(" AND WS.TYPE = :type");
		}
		if(obj.getCreatedBy()!=null){
			sql.append(" AND WS.CREATED_BY= :createdBy");
		}
		if (obj.getCreatedDeptId()!=null) {
			sql.append(" AND WS.CREATED_DEPT_ID = :createdDeptId");
		}
		sql.append(" ORDER BY WS.CREATED_DATE DESC ");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("totalOriginMoney", new DoubleType());
		query.addScalar("totalFee", new DoubleType());
		query.addScalar("totalTax", new DoubleType());
		query.addScalar("totalMoney", new DoubleType());
		query.addScalar("orderCheckCode", new StringType());
		query.addScalar("reportCheckCode", new StringType());
		query.addScalar("shipmentId", new LongType());
		query.addScalar("createdDeptId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("contractId", new LongType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("projInvestProjectId", new LongType());
		query.addScalar("projectCode", new StringType());
		query.addScalar("shiper", new StringType());
		query.addScalar("shiperDate", new TimestampType());
		query.addScalar("shipPlace", new StringType());
		query.addScalar("customsProcedure", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdDate", new TimestampType());
		query.addScalar("fullName", new StringType());
		query.addScalar("createdDeptName", new StringType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("updatedDate", new TimestampType());
		query.addScalar("status", new StringType());
		query.addScalar("cancelDate", new TimestampType());
		query.addScalar("cancelUserId", new LongType());
		query.addScalar("cancelReasonName", new StringType());
		query.addScalar("cancelDescription", new StringType());
		query.addScalar("feeDescription", new StringType());
		query.addScalar("createdBy", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(ShipmentDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch",
					"%" + ValidateUtils.validateKeySearch(obj.getKeySearch())
							+ "%");
			queryCount.setParameter("keySearch",
					"%" + ValidateUtils.validateKeySearch(obj.getKeySearch())
							+ "%");
		}

		if (StringUtils.isNotEmpty(obj.getKeySearch2())) {
			query.setParameter("keySearch2",
					"%" + ValidateUtils.validateKeySearch(obj.getKeySearch2())
							+ "%");
			queryCount.setParameter("keySearch2",
					"%" + ValidateUtils.validateKeySearch(obj.getKeySearch2())
							+ "%");
		}

		if (obj.getListStatus()!=null && !obj.getListStatus().isEmpty()) {
			query.setParameterList("listStatus", obj.getListStatus());
			queryCount.setParameterList("listStatus", obj.getListStatus());
		}
		if ("1".equals(obj.getType()) || "2".equals(obj.getType())) {
			query.setParameter("type", obj.getType());
			queryCount.setParameter("type", obj.getType());
		}
		if (obj.getCreatedDeptId()!=null) {
			query.setParameter("createdDeptId", obj.getCreatedDeptId());
			queryCount
					.setParameter("createdDeptId", obj.getCreatedDeptId());
		}
		if (obj.getCreatedBy()!=null) {
			query.setParameter("createdBy", obj.getCreatedBy());
			queryCount
					.setParameter("createdBy", obj.getCreatedBy());
		}
		query.setFirstResult((obj.getPage().intValue() - 1)
				* obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}
//End
	//Hàm tìm kiếm danh sách lô hàng màn định lượng
		@SuppressWarnings("unchecked")
		public List<ShipmentDTO> doSearchQuantity(ShipmentDTO obj) {
			StringBuilder sql = new StringBuilder(
					"SELECT"
							+ " WS.SHIPMENT_ID shipmentId,WS.CODE code,WS.CONTRACT_ID contractId,WS.CONTRACT_CODE contractCode,WS.CREATED_BY createdBy,"
							+ " WS.TYPE	type,WS.PROJ_INVEST_PROJECT_ID	projInvestProjectId,WS.PROJECT_CODE	projectCode,WS.SHIPER	shiper,"
							+ " WS.SHIPER_DATE	shiperDate,WS.SHIP_PLACE	shipPlace,WS.CUSTOMS_PROCEDURE	customsProcedure,WS.DESCRIPTION	description,"
							+ " WS.CREATED_DATE	createdDate,VPS_OWNER.SYS_USER.FULL_NAME	fullName,VPS_OWNER.SYS_GROUP.NAME	createdDeptName,WS.UPDATED_BY	updatedBy,"
							+ " WS.UPDATED_DATE	updatedDate,WS.STATUS	status,WS.CANCEL_DATE	cancelDate,WS.CANCEL_USER_ID	cancelUserId,WS.CANCEL_REASON_NAME	cancelReasonName,"
							+ " WS.CANCEL_DESCRIPTION	cancelDescription,WS.FEE_DESCRIPTION	feeDescription,WS.TOTAL_ORIGIN_MONEY	totalOriginMoney,WS.TOTAL_FEE	totalFee,"
							+ " WS.TOTAL_TAX	totalTax,WS.TOTAL_MONEY	totalMoney,WS.ORDER_CHECK_CODE	orderCheckCode,WS.REPORT_CHECK_CODE	reportCheckCode, WS.CREATED_DEPT_ID createdDeptId "
							+ " FROM WMS_OWNER_KTTS.SHIPMENT WS "
							+ " LEFT JOIN VPS_OWNER.SYS_USER  ON VPS_OWNER.SYS_USER.SYS_USER_ID=WS.CREATED_BY"
							+ " LEFT JOIN VPS_OWNER.SYS_GROUP  ON VPS_OWNER.SYS_GROUP.SYS_GROUP_ID=WS.CREATED_DEPT_ID"
							+ " WHERE 1=1 ");
			if (StringUtils.isNotEmpty(obj.getKeySearch())) {
				sql.append(" AND (upper(WS.CODE) LIKE upper(:keySearch) escape '&')");
			}
			if (StringUtils.isNotEmpty(obj.getKeySearch2())) {
				sql.append(" AND (upper(WS.CONTRACT_CODE) LIKE upper(:keySearch2) escape '&')");
			}
			if (obj.getListStatus()!=null && !obj.getListStatus().isEmpty()) {
				sql.append(" AND WS.STATUS IN (:listStatus)");
			}
			if ("1".equals(obj.getType()) || "2".equals(obj.getType())) {
				sql.append(" AND WS.TYPE = :type");
			}
			if(obj.getCreatedBy()!=null){
				sql.append(" AND WS.CREATED_BY= :createdBy");
			}
			if (obj.getLstCreatedDeptId().contains(obj.getCreatedDeptId())) {
				sql.append(" AND WS.CREATED_DEPT_ID = :createdDeptId");
			}
			if(!obj.getLstCreatedDeptId().contains(obj.getCreatedDeptId())){
				sql.append(" AND WS.CREATED_DEPT_ID IN (:lstCreatedDeptId)");
			}
			sql.append(" ORDER BY WS.CREATED_DATE DESC ");

			StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
			sqlCount.append(sql.toString());
			sqlCount.append(")");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

			query.addScalar("totalOriginMoney", new DoubleType());
			query.addScalar("totalFee", new DoubleType());
			query.addScalar("totalTax", new DoubleType());
			query.addScalar("totalMoney", new DoubleType());
			query.addScalar("orderCheckCode", new StringType());
			query.addScalar("reportCheckCode", new StringType());
			query.addScalar("shipmentId", new LongType());
			query.addScalar("createdDeptId", new LongType());
			query.addScalar("code", new StringType());
			query.addScalar("contractId", new LongType());
			query.addScalar("contractCode", new StringType());
			query.addScalar("type", new StringType());
			query.addScalar("projInvestProjectId", new LongType());
			query.addScalar("projectCode", new StringType());
			query.addScalar("shiper", new StringType());
			query.addScalar("shiperDate", new TimestampType());
			query.addScalar("shipPlace", new StringType());
			query.addScalar("customsProcedure", new StringType());
			query.addScalar("description", new StringType());
			query.addScalar("createdDate", new TimestampType());
			query.addScalar("fullName", new StringType());
			query.addScalar("createdDeptName", new StringType());
			query.addScalar("updatedBy", new LongType());
			query.addScalar("updatedDate", new TimestampType());
			query.addScalar("status", new StringType());
			query.addScalar("cancelDate", new TimestampType());
			query.addScalar("cancelUserId", new LongType());
			query.addScalar("cancelReasonName", new StringType());
			query.addScalar("cancelDescription", new StringType());
			query.addScalar("feeDescription", new StringType());
			query.addScalar("createdBy", new LongType());

			query.setResultTransformer(Transformers.aliasToBean(ShipmentDTO.class));

			if (StringUtils.isNotEmpty(obj.getKeySearch())) {
				query.setParameter("keySearch",
						"%" + ValidateUtils.validateKeySearch(obj.getKeySearch())
								+ "%");
				queryCount.setParameter("keySearch",
						"%" + ValidateUtils.validateKeySearch(obj.getKeySearch())
								+ "%");
			}

			if (StringUtils.isNotEmpty(obj.getKeySearch2())) {
				query.setParameter("keySearch2",
						"%" + ValidateUtils.validateKeySearch(obj.getKeySearch2())
								+ "%");
				queryCount.setParameter("keySearch2",
						"%" + ValidateUtils.validateKeySearch(obj.getKeySearch2())
								+ "%");
			}

			if (obj.getListStatus()!=null && !obj.getListStatus().isEmpty()) {
				query.setParameterList("listStatus", obj.getListStatus());
				queryCount.setParameterList("listStatus", obj.getListStatus());
			}
			if ("1".equals(obj.getType()) || "2".equals(obj.getType())) {
				query.setParameter("type", obj.getType());
				queryCount.setParameter("type", obj.getType());
			}
			if (obj.getCreatedBy()!=null) {
				query.setParameter("createdBy", obj.getCreatedBy());
				queryCount
						.setParameter("createdBy", obj.getCreatedBy());
			}
			if (obj.getLstCreatedDeptId().contains(obj.getCreatedDeptId())) {
				query.setParameter("createdDeptId", obj.getCreatedDeptId());
				queryCount.setParameter("createdDeptId", obj.getCreatedDeptId());
			}
			if(!obj.getLstCreatedDeptId().contains(obj.getCreatedDeptId())){
				query.setParameterList("lstCreatedDeptId", obj.getLstCreatedDeptId());
				queryCount.setParameterList("lstCreatedDeptId", obj.getLstCreatedDeptId());
			}
			if (obj.getPage() != null && obj.getPageSize() != null) {
				query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
				query.setMaxResults(obj.getPageSize().intValue());
			}
			obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

			return query.list();
		}
	//End
	//
	public List<String> searchListShipmentCode(String code) {
		StringBuilder sql = new StringBuilder("SELECT s.CODE code"
				+ " FROM WMS_OWNER_KTTS.SHIPMENT s " + "WHERE 1=1");
		if (StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]")
				&& !code.trim().equals("")) {
			sql.append(" AND upper(CODE) LIKE upper(:code)");
		}

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("code", new StringType());
		if (StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]")
				&& !code.trim().equals("")) {
			query.setParameter("code", "%" + code + "%");
		}
		return query.list();
	}
//End
	//Hàm Cập nhật Status của lô hàng
	public void updateStatus(ShipmentDTO obj) {
		int result=0;
		StringBuilder sql = new StringBuilder("update WMS_OWNER_KTTS.SHIPMENT "
				+ "set WMS_OWNER_KTTS.SHIPMENT.STATUS =:status "
				+ " where WMS_OWNER_KTTS.SHIPMENT.SHIPMENT_ID =:shipmentId ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setResultTransformer(Transformers.aliasToBean(ShipmentDTO.class));
		query.setLong("shipmentId", obj.getShipmentId());
		query.setString("status", obj.getStatus());
		result = query.executeUpdate();
	}
//End
	//xoa hang trong ShipmentGoods
	public Long deleteShipmentGoods(ShipmentDTO obj) {

  		try {
  			String sql2 = "delete from SHIPMENT_GOODS where SHIPMENT_ID = :shipmentId";
  			SQLQuery query2 = getSession().createSQLQuery(sql2);
  			query2.setParameter("shipmentId",obj.getShipmentId());
  			query2.executeUpdate();
  			return 1L;
  		} catch (Exception e) {
  			e.getMessage();
  			return 0L;
  		}
  	}
	//end

	//xoa hang trong ShipmentGoodsDetail
  	public Long deleteShipmentGoodsDetail(ShipmentDTO obj) {

  		try {
  			String sql3 = "delete from SHIPMENT_GOODS_DETAIL where SHIPMENT_ID = :shipmentId";
  			SQLQuery query3 = getSession().createSQLQuery(sql3);
  			query3.setParameter("shipmentId",obj.getShipmentId());
  			query3.executeUpdate();
  			return 1L;
  		} catch (Exception e) {
  			e.getMessage();
  			return 0L;
  		}
  	}
  	//end
  	
  	public Long deleteShipmentTax(ShipmentDTO obj) {

  		try {
  			String sql3 = "delete from SHIPMENT_TAX where SHIPMENT_ID = :shipmentId";
  			SQLQuery query3 = getSession().createSQLQuery(sql3);
  			query3.setParameter("shipmentId",obj.getShipmentId());
  			query3.executeUpdate();
  			return 1L;
  		} catch (Exception e) {
  			e.getMessage();
  			return 0L;
  		}
  	}
}
