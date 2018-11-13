///*
// * Copyright (C) 2011 Viettel Telecom. All rights reserved.
// * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.viettel.wms.dao;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.transform.Transformers;
//import org.hibernate.type.TimestampType;
//import org.hibernate.type.DoubleType;
//import org.hibernate.type.LongType;
//import org.hibernate.type.StringType;
//import org.springframework.stereotype.Repository;
//
//import com.viettel.service.base.dao.BaseFWDAOImpl;
//import com.viettel.service.base.utils.DateUtil;
//import com.viettel.wms.bo.OrderChangeGoodsBO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;
//import com.viettel.wms.dto.StockGoodsDTO;
//import com.viettel.wms.dto.StockGoodsTotalDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.utils.ValidateUtils;
//
///**
// * @author TruongBX3
// * @version 1.0
// * @since 08-May-15 4:07 PM
// */
//@Repository("orderChangeGoodsDAO")
//public class OrderChangeGoodsDAO extends BaseFWDAOImpl<OrderChangeGoodsBO, Long> {
//
//	public OrderChangeGoodsDAO() {
//		this.model = new OrderChangeGoodsBO();
//	}
//
//	public OrderChangeGoodsDAO(Session session) {
//		this.session = session;
//	}
//
//	/**
//	 * Xóa tất cả bản ghi trong ORDER_CHANGE_GOODS_DETAIL theo ORDER_CHANGE_GOODS_ID  trước khi update
//	 * @param obj
//	 * @return
//	 */
//	public Long deleteObj(OrderChangeGoodsDTO obj) {
//
//		try {
//			String sql2 = "delete from WMS_OWNER_KTTS.\"ORDER_CHANGE_GOODS_DETAIL\" where ORDER_CHANGE_GOODS_ID = :orderChangGoodsId";
//			SQLQuery query2 = getSession().createSQLQuery(sql2);
//			query2.setParameter("orderChangGoodsId",obj.getOrderChangeGoodsId());
//			query2.executeUpdate();
//			return 1L;
//		} catch (Exception e) {
//			e.getMessage();
//			return 0L;
//		}
//	}
//	
//	/**
//	 * Lấy dữ liệu từ bảng theo ORDER_CHANGE_GOODS_ID
//	 * @param id
//	 * @return
//	 */
//	public List<OrderChangeGoodsDTO> getOrderChangeById(Long id){
//		StringBuilder sql = new StringBuilder("SELECT DISTINCT OCG.ORDER_CHANGE_GOODS_ID orderChangeGoodsId,"
//				+ "OCG.CODE code," + "OCG.STOCK_ID stockId," + "OCG.STATUS status," + "OCG.DESCRIPTION description,"
//				+ "OCG.CREATED_DATE createdDate," + "OCG.SIGN_STATE signState," + "OCG.CREATED_BY createdBy,"
//				+ "OCG.CREATED_DEPT_ID createdDeptId," + "OCG.CREATED_DEPT_NAME createdDeptName,"
//				+ "OCG.CANCEL_REASON_NAME cancelReasonName,"+"OCG.CANCEL_DESCRIPTION cancelDescription,"
//				+ "OCG.CANCEL_USER_ID cancelUserId,"+"OCG.CANCEL_DATE cancelDate,"+ "ORCGD.AMOUNT_CHANGE amountChange,"
//				+ "US.FULL_NAME fullName," + "OCG.UPDATED_DATE updatedDate," + "OCG.UPDATED_BY updateBy,"
//				+ "ORCGD.GOODS_NAME goodsName," + "ST.NAME stockName ," + "ST.CODE stockCode "
//				+ " FROM WMS_OWNER_KTTS.ORDER_CHANGE_GOODS OCG LEFT JOIN WMS_OWNER_KTTS.ORDER_CHANGE_GOODS_DETAIL ORCGD ON (OCG.ORDER_CHANGE_GOODS_ID = ORCGD.ORDER_CHANGE_GOODS_ID) "
//				+ " INNER JOIN CAT_OWNER.STOCK ST ON (OCG.STOCK_ID = ST.STOCK_ID)"
//				+ " INNER JOIN VPS_OWNER.SYS_USER US ON (OCG.CREATED_BY =US.SYS_USER_ID) "
//				+ " INNER JOIN VPS_OWNER.SYS_GROUP DPM ON (DPM.SYS_GROUP_ID = OCG.CREATED_DEPT_ID) " + "WHERE  OCG.ORDER_CHANGE_GOODS_ID=:id");
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		query.addScalar("orderChangeGoodsId", new LongType());
//		query.addScalar("code", new StringType());
//		query.addScalar("stockId", new LongType());
//		query.addScalar("description", new StringType());
//		query.addScalar("createdDate", new TimestampType());
//		query.addScalar("cancelReasonName", new StringType());
//		query.addScalar("signState", new StringType());
//		query.addScalar("status", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("stockName", new StringType());
//		query.addScalar("createdDeptId", new LongType());
//		query.addScalar("createdDeptName", new StringType());
//		query.addScalar("updatedDate", new TimestampType());
//		query.addScalar("updateBy", new LongType());
//		query.addScalar("fullName", new StringType());
//		query.addScalar("stockCode", new StringType());
//		query.addScalar("createdBy", new LongType());
//		query.addScalar("cancelDescription", new StringType());
//		query.addScalar("cancelUserId", new LongType());
//		query.addScalar("cancelDate", new TimestampType());
//		query.addScalar("amountChange", new DoubleType());
//		
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderChangeGoodsDTO.class));
//		query.setParameter("id", id);
//		
//		return  query.list();
//	}
//
//	/**
//	 * Fill dữ liệu khi load page và khi tìm kiếm 
//	 * @param obj
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public List<OrderChangeGoodsDTO> doSearch(OrderChangeGoodsDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT DISTINCT OCG.ORDER_CHANGE_GOODS_ID orderChangeGoodsId,"
//				+ "OCG.CODE code," + "OCG.STOCK_ID stockId," + "OCG.STATUS status," + "OCG.DESCRIPTION description,"
//				+ "OCG.CREATED_DATE createdDate," + "OCG.SIGN_STATE signState," + "OCG.CREATED_BY createdBy,"
//				+ "OCG.CREATED_DEPT_ID createdDeptId," + "OCG.CREATED_DEPT_NAME createdDeptName,"
//				+ "OCG.CANCEL_REASON_NAME cancelReasonName,"+"OCG.CANCEL_DESCRIPTION cancelDescription,"
//				+ "OCG.CANCEL_USER_ID cancelUserId,"+"OCG.CANCEL_DATE cancelDate,"
//				+ "US.FULL_NAME fullName," + "OCG.UPDATED_DATE updatedDate," + "OCG.UPDATED_BY updateBy,"
//				 + "ST.NAME stockName ," + "ST.CODE stockCode "
//				+ " FROM WMS_OWNER_KTTS.ORDER_CHANGE_GOODS OCG "
//				+ " INNER JOIN CAT_OWNER.STOCK ST ON (OCG.STOCK_ID = ST.STOCK_ID)"
//				+ " INNER JOIN VPS_OWNER.SYS_USER US ON (OCG.CREATED_BY =US.SYS_USER_ID) "
//				+ " INNER JOIN VPS_OWNER.SYS_GROUP DPM ON (DPM.SYS_GROUP_ID = OCG.CREATED_DEPT_ID) " + "WHERE 1=1");
//		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
//			sql.append(" AND (upper(OCG.CODE) LIKE upper(:keySearch) escape '&')  ");
//		}
//		if (obj.getListStatus() != null) {
//			sql.append(" AND  OCG.STATUS IN (:listStatus)");
//		}
//
//		if (obj.getListStockId()!=null&&!obj.getListStockId().isEmpty()) {
//			sql.append(" AND  OCG.STOCK_ID IN (:listStockId)");
//		}
//		if (obj.getCreatedBy()!=null) {
//			sql.append(" AND  OCG.CREATED_BY  =:createdBy");
//		}
//		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() == null)) {
//			sql.append(" AND OCG.CREATED_DATE >= :createdDateFrom");
//		}
//		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
//			sql.append(" AND OCG.CREATED_DATE between :createdDateFrom and :createdDateTo");
//		}
//
//		sql.append(" ORDER BY OCG.ORDER_CHANGE_GOODS_ID DESC");
//
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
//
//		query.addScalar("orderChangeGoodsId", new LongType());
//		query.addScalar("code", new StringType());
//		query.addScalar("stockId", new LongType());
//		query.addScalar("description", new StringType());
//		query.addScalar("createdDate", new TimestampType());
//		query.addScalar("cancelReasonName", new StringType());
//		query.addScalar("signState", new StringType());
//		query.addScalar("status", new StringType());
//		query.addScalar("stockName", new StringType());
//		query.addScalar("createdDeptId", new LongType());
//		query.addScalar("createdDeptName", new StringType());
//		query.addScalar("updatedDate", new TimestampType());
//		query.addScalar("updateBy", new LongType());
//		query.addScalar("fullName", new StringType());
//		query.addScalar("stockCode", new StringType());
//		query.addScalar("createdBy", new LongType());
//		query.addScalar("cancelDescription", new StringType());
//		query.addScalar("cancelUserId", new LongType());
//		query.addScalar("cancelDate", new TimestampType());
//		
//		/*
//		 * query.addScalar("name",new StringType());
//		 * query.addScalar("departmentId",new StringType());
//		 */
//
//		
//		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
//			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
//			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
//		}
//		if (StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())) {
//			query.setParameter("status", obj.getStatus());
//			queryCount.setParameter("status", obj.getStatus());
//		}
//		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() == null)) {
//			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
//			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
//		}
//		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
//			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
//			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
//
//			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
//			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
//		}
//
//		if (obj.getCreatedBy()!=null) {
//			query.setParameter("createdBy", obj.getCreatedBy());
//			queryCount.setParameter("createdBy", obj.getCreatedBy());
//		}
//		if (obj.getListStockId() != null&&!obj.getListStockId().isEmpty()) {
//			query.setParameterList("listStockId", obj.getListStockId());
//			queryCount.setParameterList("listStockId", obj.getListStockId());
//		}
//
//		if (obj.getListStatus() != null) {
//			query.setParameterList("listStatus", obj.getListStatus());
//			queryCount.setParameterList("listStatus", obj.getListStatus());
//		}
//		query.setResultTransformer(Transformers.aliasToBean(OrderChangeGoodsDTO.class));
//
//		if (obj.getPage() != null && obj.getPageSize() != null) {
//			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//			query.setMaxResults(obj.getPageSize().intValue());
//		}
//
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//		return query.list();
//	}
//
//	/**
//	 * search khi thêm nhanh 1 mặt hàng trong create và update
//	 * @param obj
//	 * @return
//	 */
//	public List<OrderChangeGoodsDetailDTO> doSearchForAutoImport(OrderChangeGoodsDetailDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT GOODS_CODE goodsCode,"
//				+ "GOODS_NAME goodsName,"
//				+ "GOODS_UNIT_NAME goodsUnitName,"
//				+ "AMOUNT_CHANGE amountChange,"
//				+ "GOODS_STATE_NAME goodsStateName,"
//				+ "NEW_GOODS_CODE newGoodsCode,"
//				+ "NEW_SERIAL newSerial,"
//				+ "SERIAL serial"
//				+ " FROM WMS_OWNER_KTTS.ORDER_CHANGE_GOODS_DETAIL"
//				+ " WHERE 1=1");
//		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
//			sql.append(" AND ((upper(GOODS_CODE) LIKE upper(:keySearch)) "
//					+ "OR (upper(GOODS_NAME) LIKE upper(:keySearch)))  ");
//		}
//
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
//
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("amountChange", new DoubleType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("newGoodsCode", new StringType());
//		query.addScalar("newSerial", new StringType());
//		query.addScalar("serial", new StringType());
//		
//
//		query.setResultTransformer(Transformers.aliasToBean(OrderChangeGoodsDTO.class));
//		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
//			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
//			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
//		}
//		if (obj.getPage() != null && obj.getPageSize() != null) {
//			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//			query.setMaxResults(obj.getPageSize().intValue());
//		}
//
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//
//		return query.list();
//	}
//
//	 /**
//     * Fill dữ liệu khi vào grid popup update khi click
//     * @param obj
//     * @return
//     */
//	public List<OrderChangeGoodsDTO> doSearchGoodsForImportReq(OrderChangeGoodsDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT OCG.ORDER_CHANGE_GOODS_ID orderChangeGoodsId,"
//				+ "ORCGD.ORDER_CHANGE_GOODS_DETAIL_ID orderChangeGoodsDetailId,"
//				+ "ORCGD.GOODS_CODE goodsCode," + "ORCGD.AMOUNT_CHANGE amountChange,"
//				+ "ORCGD.GOODS_UNIT_NAME goodsUnitName," +"ORCGD.SERIAL serial,"
//				+ "ORCGD.GOODS_NAME goodsName," + "ORCGD.GOODS_STATE_NAME goodsStateName,"
//				+ "ORCGD.NEW_GOODS_CODE newGoodsCode," + "ORCGD.NEW_SERIAL newSerial "
//				+ " FROM WMS_OWNER_KTTS.ORDER_CHANGE_GOODS_DETAIL ORCGD JOIN  WMS_OWNER_KTTS.ORDER_CHANGE_GOODS OCG ON (OCG.ORDER_CHANGE_GOODS_ID = ORCGD.ORDER_CHANGE_GOODS_ID)"
//				+ " WHERE OCG.ORDER_CHANGE_GOODS_ID =:orderChangeGoodsId");
//
//		sql.append(" ORDER BY ORCGD.ORDER_CHANGE_GOODS_ID DESC");
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
//		query.addScalar("orderChangeGoodsId",new LongType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("goodsCode", new StringType());
//		query.addScalar("amountChange", new DoubleType());
//		query.addScalar("goodsUnitName", new StringType());
//		query.addScalar("goodsStateName", new StringType());
//		query.addScalar("newGoodsCode", new StringType());
//		query.addScalar("newSerial", new StringType());
//		query.addScalar("serial", new StringType());
//		
//		
//		query.setResultTransformer(Transformers.aliasToBean(OrderChangeGoodsDTO.class));
//		
//		query.setParameter("orderChangeGoodsId", obj.getOrderChangeGoodsId());
//		queryCount.setParameter("orderChangeGoodsId", obj.getOrderChangeGoodsId());
//		
//		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//		
//		return query.list();
//	}
//	
//	public StockGoodsTotalDTO sumAmountChange(String code){
//		StringBuilder sql = new StringBuilder("SELECT sum(OCGD.AMOUNT) amount FROM WMS_OWNER_KTTS.STOCK_GOODS_TOTAL OCGD where OCGD.GOODS_CODE=:code ");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("amount", new DoubleType());
//
//		query.setResultTransformer(Transformers.aliasToBean(StockGoodsTotalDTO.class));
//
//		query.setParameter("code", code);
//
//		return (StockGoodsTotalDTO) query.uniqueResult();
//	}
//	
//
//	/**
//	 * check all data in grid
//	 * @param obj
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public List<OrderChangeGoodsDTO> doSearchForCheckAll(OrderChangeGoodsDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT DISTINCT OCG.ORDER_CHANGE_GOODS_ID orderChangeGoodsId,"
//				+ "OCG.CODE code," + "OCG.STOCK_ID stockId," + "OCG.STATUS status," + "OCG.DESCRIPTION description,"
//				+ "OCG.CREATED_DATE createdDate," + "OCG.SIGN_STATE signState," + "OCG.CREATED_BY createdBy,"
//				+ "OCG.CREATED_DEPT_ID createdDeptId," + "OCG.CREATED_DEPT_NAME createdDeptName,"
//				+ "OCG.CANCEL_REASON_NAME cancelReasonName,"+"OCG.CANCEL_DESCRIPTION cancelDescription,"
//				+ "OCG.CANCEL_USER_ID cancelUserId,"+"OCG.CANCEL_DATE cancelDate,"
//				+ "US.FULL_NAME fullName," + "OCG.UPDATED_DATE updatedDate," + "OCG.UPDATED_BY updateBy,"
//				+ "ORCGD.GOODS_NAME goodsName," + "ST.NAME stockName ," + "ST.CODE stockCode "
//				+ " FROM WMS_OWNER_KTTS.ORDER_CHANGE_GOODS OCG LEFT JOIN WMS_OWNER_KTTS.ORDER_CHANGE_GOODS_DETAIL ORCGD ON (OCG.ORDER_CHANGE_GOODS_ID = ORCGD.ORDER_CHANGE_GOODS_ID) "
//				+ " INNER JOIN CAT_OWNER.STOCK ST ON (OCG.STOCK_ID = ST.STOCK_ID)"
//				+ " INNER JOIN VPS_OWNER.SYS_USER US ON (OCG.CREATED_BY =US.SYS_USER_ID) "
//				+ " INNER JOIN CAT_OWNER.DEPARTMENT DPM ON (DPM.DEPARTMENT_ID = OCG.CREATED_DEPT_ID) " + "WHERE 1=1");
//		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
//			sql.append(" AND (upper(OCG.CODE) LIKE upper(:keySearch) escape '&')  ");
//		}
//		if (obj.getListStatus() != null) {
//			sql.append(" AND  OCG.STATUS IN (:listStatus)");
//		}
//
//		if (obj.getListStockId()!=null&&!obj.getListStockId().isEmpty()) {
//			sql.append(" AND  OCG.STOCK_ID IN (:listStockId)");
//		}
//		if (obj.getCreatedBy()!=null) {
//			sql.append(" AND  OCG.CREATED_BY  =:createdBy");
//		}
//		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() == null)) {
//			sql.append(" AND OCG.CREATED_DATE >= :createdDateFrom");
//		}
//		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
//			sql.append(" AND OCG.CREATED_DATE between :createdDateFrom and :createdDateTo");
//		}
//
//		sql.append(" ORDER BY OCG.ORDER_CHANGE_GOODS_ID DESC");
//
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
//
//		query.addScalar("orderChangeGoodsId", new LongType());
//		query.addScalar("code", new StringType());
//		query.addScalar("stockId", new LongType());
//		query.addScalar("description", new StringType());
//		query.addScalar("createdDate", new TimestampType());
//		query.addScalar("cancelReasonName", new StringType());
//		query.addScalar("signState", new StringType());
//		query.addScalar("status", new StringType());
//		query.addScalar("goodsName", new StringType());
//		query.addScalar("stockName", new StringType());
//		query.addScalar("createdDeptId", new LongType());
//		query.addScalar("createdDeptName", new StringType());
//		query.addScalar("updatedDate", new TimestampType());
//		query.addScalar("updateBy", new LongType());
//		query.addScalar("fullName", new StringType());
//		query.addScalar("stockCode", new StringType());
//		query.addScalar("createdBy", new LongType());
//		query.addScalar("cancelDescription", new StringType());
//		query.addScalar("cancelUserId", new LongType());
//		query.addScalar("cancelDate", new TimestampType());
//		
//		/*
//		 * query.addScalar("name",new StringType());
//		 * query.addScalar("departmentId",new StringType());
//		 */
//
//		
//		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
//			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
//			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
//		}
//		if (StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())) {
//			query.setParameter("status", obj.getStatus());
//			queryCount.setParameter("status", obj.getStatus());
//		}
//		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() == null)) {
//			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
//			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
//		}
//		if ((obj.getCreatedDateFrom() != null) && (obj.getCreatedDateTo() != null)) {
//			query.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
//			queryCount.setTimestamp("createdDateFrom", obj.getCreatedDateFrom());
//
//			query.setTimestamp("createdDateTo", obj.getCreatedDateTo());
//			queryCount.setTimestamp("createdDateTo", obj.getCreatedDateTo());
//		}
//
//		if (obj.getCreatedBy()!=null) {
//			query.setParameter("createdBy", obj.getCreatedBy());
//			queryCount.setParameter("createdBy", obj.getCreatedBy());
//		}
//		if (obj.getListStockId() != null&&!obj.getListStockId().isEmpty()) {
//			query.setParameterList("listStockId", obj.getListStockId());
//			queryCount.setParameterList("listStockId", obj.getListStockId());
//		}
//
//		if (obj.getListStatus() != null) {
//			query.setParameterList("listStatus", obj.getListStatus());
//			queryCount.setParameterList("listStatus", obj.getListStatus());
//		}
//		query.setResultTransformer(Transformers.aliasToBean(OrderChangeGoodsDTO.class));
//
//
//		return query.list();
//	}
//	public GoodsDTO selectNameByCode(String code){
//		StringBuilder sql = new StringBuilder("SELECT G.GOODS_ID goodsId,G.NAME name,G.UNIT_TYPE goodsUnitId,G.GOODS_TYPE goodsType,GT.NAME goodsTypeName"
//				+ " FROM CAT_OWNER.GOODS G  JOIN CAT_OWNER.GOODS_TYPE GT ON GT.GOODS_TYPE_ID = G.GOODS_TYPE  where G.CODE=:code ");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("goodsUnitId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("goodsType", new StringType());
//		query.addScalar("goodsTypeName", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
//
//		query.setParameter("code", code);
//
//		return (GoodsDTO) query.uniqueResult();
//	}
//
//}
