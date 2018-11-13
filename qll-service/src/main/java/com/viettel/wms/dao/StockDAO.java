///*
// * Copyright (C) 2011 Viettel Telecom. All rights reserved.
// * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.viettel.wms.dao;
//
//import com.viettel.wms.bo.StockBO;
//import com.viettel.wms.dto.AppParamDTO;
//import com.viettel.wms.dto.DomainDataDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDTO;
//import com.viettel.wms.dto.StockCellDTO;
//import com.viettel.wms.dto.StockDTO;
//import com.viettel.wms.dto.SysUserQLKDTO;
//import com.viettel.service.base.dao.BaseFWDAOImpl;
//import com.viettel.wms.utils.ValidateUtils;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.transform.Transformers;
//import org.hibernate.type.DateType;
//import org.hibernate.type.LongType;
//import org.hibernate.type.StringType;
//import org.springframework.stereotype.Repository;
//
///**
// * @author TruongBX3
// * @version 1.0
// * @since 08-May-15 4:07 PM
// */
//@Repository("stockDAO")
//public class StockDAO extends BaseFWDAOImpl<StockBO, Long> {
//
//	public StockDAO() {
//		this.model = new StockBO();
//	}
//
//	public StockDAO(Session session) {
//		this.session = session;
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<StockDTO> doSearch(StockDTO obj) {
//		StringBuilder sql = new StringBuilder("SELECT ST.STOCK_ID stockId," + "ST.CODE code," + "ST.NAME name,"
//				+ "ST.STATUS status, " + "ST.PARENT_ID parentId," + "ST.PATH path," + "ST.DESCRIPTION description,"
//				+ "ST.DEPARTMENT_ID departmentId," + "ST.DEPARTMENT_NAME departmentName," + "ST.TYPE type,"
//				+ "ST.CREATED_BY createdBy,"+"ST.CREATED_DATE createdDate,"
//				+ "ST.LEVEL_STOCK levelST" +", AP.NAME nameStock, AP1.NAME levelStock"+ " FROM CAT_OWNER.STOCK ST JOIN CTCT_CAT_OWNER.APP_PARAM AP "
//						+ "ON(ST.TYPE = AP.CODE AND AP.PAR_TYPE like 'STOCK_TYPE') JOIN CTCT_CAT_OWNER.APP_PARAM AP1 ON(ST.LEVEL_STOCK = AP1.CODE and AP1.PAR_TYPE like 'STOCK_LEVEL')  WHERE 1=1");
//		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
//			sql.append(" AND (upper(ST.CODE) LIKE upper(:keySearch) OR upper(ST.NAME) LIKE upper(:keySearch))");
//		}
//
//		if (StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())) {
//			sql.append(" AND ST.STATUS = :status");
//		}
//
//		if (obj.getDepartmentId()!=null) {
//			sql.append(" AND ST.DEPARTMENT_ID =:departmentId ");
//		}
//
//		sql.append(" ORDER BY ST.CODE");
//
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
//
//		query.addScalar("stockId", new LongType());
//		query.addScalar("createdBy", new LongType());
//		query.addScalar("createdDate", new DateType());
//		query.addScalar("code", new StringType());
//		query.addScalar("name", new StringType());
//		query.addScalar("status", new StringType());
//		query.addScalar("parentId", new LongType());
//		query.addScalar("path", new StringType());
//		query.addScalar("description", new StringType());
//		query.addScalar("departmentId", new LongType());
//		query.addScalar("departmentName", new StringType());
//		query.addScalar("type", new StringType());
//		query.addScalar("levelST", new StringType());
//		query.addScalar("nameStock", new StringType());
//		query.addScalar("levelStock", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
//			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
//			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
//		}
//		if (StringUtils.isNotEmpty(obj.getStatus()) && !"2".equals(obj.getStatus())) {
//			query.setParameter("status", obj.getStatus());
//			queryCount.setParameter("status", obj.getStatus());
//		}
//		if (obj.getDepartmentId()!=null) {
//			query.setParameter("departmentId", obj.getDepartmentId());
//			queryCount.setParameter("departmentId", obj.getDepartmentId());
//		}
//
//
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
//	public StockDTO getbycode(String code) {
//		StringBuilder sql = new StringBuilder("SELECT STOCK_ID stockId," + "CODE code," + "NAME name,"
//				+ "STATUS status, " + "PARENT_ID parentId," + "PATH path," + "DESCRIPTION description,"
//				+ "DEPARTMENT_ID departmentId," + "DEPARTMENT_NAME departmentName," + "TYPE type," + "LEVEL_STOCK levelST "
//				+ " FROM CAT_OWNER.STOCK WHERE upper(CODE)=upper(:code)");
//		SQLQuery query = getSession().createSQLQuery(sql.toString());
//
//		query.addScalar("stockId", new LongType());
//		query.addScalar("code", new StringType());
//		query.addScalar("name", new StringType());
//		query.addScalar("status", new StringType());
//		query.addScalar("parentId", new LongType());
//		query.addScalar("path", new StringType());
//		query.addScalar("description", new StringType());
//		query.addScalar("departmentId", new LongType());
//		query.addScalar("departmentName", new StringType());
//		query.addScalar("type", new StringType());
//		query.addScalar("levelST", new StringType());
//
//		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//
//		query.setParameter("code", code);
//
//		return (StockDTO) query.uniqueResult();
//	}
//
//	
//	@SuppressWarnings("unchecked")
//	public List<StockDTO> getForAutoCompleteStockDomain(StockDTO obj) {
//		String sql = "SELECT ST.STOCK_ID stockId"	
//			+ " ,ST.NAME name"		
//			+ " ,ST.CODE code"
//			+ " FROM CAT_OWNER.STOCK ST"
//			+ " WHERE ST.STATUS=1 ";
//		
//		StringBuilder stringBuilder = new StringBuilder(sql);
//		
//			if(StringUtils.isNotEmpty(obj.getName())){
//			stringBuilder.append(" AND (upper(ST.NAME) LIKE upper(:name) escape '&' OR upper(ST.CODE) LIKE upper(:name) escape '&')");
//			
//		}
//		if(obj.getListId().size()>0){
//			stringBuilder.append(" AND ST.STOCK_ID in (:listId)");
//		} else {
//			stringBuilder.append(" AND ST.STOCK_ID is null ");
//		}
//		
//		stringBuilder.append(" ORDER BY ST.CODE");
//		
//		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
//		
//		query.addScalar("stockId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//	
//		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//
//		if (StringUtils.isNotEmpty(obj.getName())) {
//			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//		}
//		if (StringUtils.isNotEmpty(obj.getCode())) {
//			query.setParameter("value", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
//		}
//
//		if(obj.getListId().size()>0){
//			query.setParameterList("listId", obj.getListId());
//		}
//		return query.list();
//	}
//	
//	public List<StockDTO> getForAutoCompleteStock(StockDTO obj) {
//		String sql = "SELECT ST.STOCK_ID stockId"	
//			+ " ,ST.NAME name"		
//			+ " ,ST.CODE code"
//			+ " FROM CAT_OWNER.STOCK ST"
//			+ " WHERE ST.STATUS=1 ";
//		
//		StringBuilder stringBuilder = new StringBuilder(sql);
//		
//		if (obj.getIsSize()){
//			stringBuilder.append(" AND ROWNUM <=10 ");
//			if(StringUtils.isNotEmpty(obj.getName())){
//			stringBuilder.append(" AND (upper(ST.NAME) LIKE upper(:name) escape '&' OR upper(ST.CODE) LIKE upper(:name) escape '&')");
//			}
//			
//		}
//		
//		stringBuilder.append(" ORDER BY ST.CODE");
//		
//		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
//		
//		query.addScalar("stockId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//	
//		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//
//		if (StringUtils.isNotEmpty(obj.getName())) {
//			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//		}
//		if (StringUtils.isNotEmpty(obj.getCode())) {
//			query.setParameter("value", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
//		}
//
//		return query.list();
//	}
//	
//	public List<StockDTO> getStocksForAutocomplete(StockDTO obj){
//
//		StringBuilder sql = new StringBuilder("SELECT ST.STOCK_ID stockId"	
//				+ " ,ST.NAME name"		
//				+ " ,ST.CODE code"
//				+ " FROM CAT_OWNER.STOCK ST"
//				+ " WHERE ST.STATUS=1 ");
//  		
//  		
//  		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//  		sqlCount.append(sql.toString());
//  		sqlCount.append(")");
//  		
//  		SQLQuery query= getSession().createSQLQuery(sql.toString());
//  		SQLQuery queryCount= getSession().createSQLQuery(sqlCount.toString());
//  	
//  		query.addScalar("stockId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//  		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//  		
//  		if(obj.getPage()!=null && obj.getPageSize()!=null){
//  			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
//	  		query.setMaxResults(obj.getPageSize().intValue());
//  		}
//  		
//  		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//  		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//  		return query.list();
//  	}
//	public List<StockDTO> getStocksForAutocompleteDropDown(StockDTO obj){
//		String sql = "SELECT ST.STOCK_ID stockId"	
//				+ " ,ST.NAME name"		
//				+ " ,ST.CODE code, DP.NAME departmentName "
//				+ " FROM CAT_OWNER.STOCK ST "
//				+ " JOIN CAT_OWNER.DEPARTMENT DP ON DP.DEPARTMENT_ID = ST.DEPARTMENT_ID "
//				+ " WHERE ST.STATUS=1 ";
//		StringBuilder stringBuilder = new StringBuilder(sql);
//		
//		if(StringUtils.isNotEmpty(obj.getName())){
//			stringBuilder.append( " AND (upper(ST.NAME) LIKE upper(:name) escape '&' OR upper(ST.CODE) LIKE upper(:name) escape '&')");
//		}
//  		
//  		SQLQuery query= getSession().createSQLQuery(stringBuilder.toString());
//  	
//  		query.addScalar("stockId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//		query.addScalar("departmentName", new StringType());
//  		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//  		
//  		if(obj.getPage()!=null && obj.getPageSize()!=null){
//  			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
//	  		query.setMaxResults(obj.getPageSize().intValue());
//  		}
//  		
//  		if (StringUtils.isNotEmpty(obj.getName())) {
//			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//		}
//  		return query.list();
//  	}
//
////	Tìm kiếm kho hàng trong popup
//	@SuppressWarnings("unchecked")
//	public List<StockDTO> doSearchStockInPopUp(StockDTO obj) {
//		String sql = "SELECT ST.STOCK_ID stockId, "	
//			+ "ST.NAME name,"		
//			+ "ST.CODE code,"
//			+ "dp.NAME departmentName "
//			+ "FROM CAT_OWNER.STOCK ST "
//			+ "LEFT JOIN CAT_OWNER.DEPARTMENT dp ON ST.DEPARTMENT_ID = dp.DEPARTMENT_ID "
//			+ "WHERE ST.STATUS=1 ";
//		
//		StringBuilder stringBuilder = new StringBuilder(sql);
//		
//			if(StringUtils.isNotEmpty(obj.getName())){
//				stringBuilder.append( " AND (upper(ST.NAME) LIKE upper(:name) escape '&' OR upper(ST.CODE) LIKE upper(:name) escape '&')");
//			}
//		
//		stringBuilder.append(" ORDER BY ST.CODE");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(stringBuilder.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(stringBuilder.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("stockId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//		query.addScalar("departmentName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//
//		if (StringUtils.isNotEmpty(obj.getName())) {
//			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//			queryCount.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//		}
//		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//		return query.list();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<StockDTO> doSearchStockInPopUpDomain(StockDTO obj) {
//		String sql = "SELECT ST.STOCK_ID stockId, "	
//			+ "ST.NAME name,"		
//			+ "ST.CODE code,"
//			+ "dp.NAME departmentName "
//			+ "FROM CAT_OWNER.STOCK ST "
//			+ "LEFT JOIN CAT_OWNER.DEPARTMENT dp ON ST.DEPARTMENT_ID = dp.DEPARTMENT_ID "
//			+ "WHERE ST.STATUS=1 ";
//		
//		StringBuilder stringBuilder = new StringBuilder(sql);
//		
//			if(StringUtils.isNotEmpty(obj.getName())){
//				stringBuilder.append( " AND (upper(ST.NAME) LIKE upper(:name) escape '&' OR upper(ST.CODE) LIKE upper(:name) escape '&')");
//			}
//			
//			if(obj.getListId().size()>0){
//				stringBuilder.append(" AND ST.STOCK_ID in (:listId) ");
//			} else {
//				stringBuilder.append(" AND ST.STOCK_ID is null ");
//			}
//		
//		stringBuilder.append(" ORDER BY ST.CODE");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(stringBuilder.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(stringBuilder.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("stockId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//		query.addScalar("departmentName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//
//		if (StringUtils.isNotEmpty(obj.getName())) {
//			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//			queryCount.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//		}
//		if(obj.getListId().size()>0){
//			query.setParameterList("listId", obj.getListId());
//			queryCount.setParameterList("listId", obj.getListId());
//		}
//		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//		return query.list();
//	}
//	
//	//Lấy danh sách hàng hóa trong popup
//    @SuppressWarnings("unchecked")
//    public List<GoodsDTO> doSearchGoodsInPopup(GoodsDTO obj){
//		StringBuilder sql = new StringBuilder("SELECT g.GOODS_ID goodsId"	
//			+ " ,g.NAME name"		
//			+ " ,g.CODE code"
//			+ " ,g.UNIT_TYPE unitType"
//			+ " ,g.UNIT_TYPE_NAME unitTypeName"
//			+ " FROM CAT_OWNER.GOODS g"
//			+ " WHERE 1=1 ");
//		
//		if(StringUtils.isNotEmpty(obj.getName())){
//			sql.append( " AND (upper(g.NAME) LIKE upper(:name) escape '&' OR upper(g.CODE) LIKE upper(:name) escape '&')");
//		}
//		sql.append("ORDER BY g.CODE ");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//		query.addScalar("unitType", new LongType());
//		query.addScalar("unitTypeName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
//		if(StringUtils.isNotEmpty(obj.getName())){
//			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//			queryCount.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//		}
//		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//		
//		return query.list();
//	}
//    
////  Tìm kiếm hàng hóa trong popup
//    @SuppressWarnings("unchecked")
//    public List<GoodsDTO> doSearchGoods(GoodsDTO obj){
//		StringBuilder sql = new StringBuilder("SELECT g.GOODS_ID goodsId"	
//			+ " ,g.NAME name"		
//			+ " ,g.CODE code"
//			+ " ,g.UNIT_TYPE unitType"
//			+ " ,g.UNIT_TYPE_NAME unitTypeName"
//			+ " FROM CAT_OWNER.GOODS g"
//			+ " WHERE 1=1 ");
//		
//		if (obj.getIsSize()){
//			sql.append(" AND ROWNUM <=10 ");
//			if((StringUtils.isNotEmpty(obj.getName()))||(StringUtils.isNotEmpty(obj.getCode()))){
//				sql.append(" AND (upper(ST.NAME) LIKE upper(:name) escape '&' OR upper(ST.CODE) LIKE upper(:name) escape '&')");
//			}
//		}
//		else{
//			if((StringUtils.isNotEmpty(obj.getName()))||(StringUtils.isNotEmpty(obj.getCode()))){
//				sql.append( " AND (upper(g.NAME) LIKE upper(:name) escape '&' OR upper(g.CODE) LIKE upper(:name) escape '&')");
//			}
//		}
//		
//		sql.append("ORDER BY g.CODE ");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//
//		query.addScalar("goodsId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//		query.addScalar("unitType", new LongType());
//		query.addScalar("unitTypeName", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
//		
//		if((StringUtils.isNotEmpty(obj.getName()))||(StringUtils.isNotEmpty(obj.getCode()))){
//			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//		}
//		
//		return query.list();
//	}
//    
//    public StockDTO doSearchStockById(StockDTO obj){
//		StringBuilder sql = new StringBuilder("SELECT ST.STOCK_ID stockId"	
//				+ " ,ST.NAME name"		
//				+ " ,ST.CODE code"
//				+ " FROM CAT_OWNER.STOCK ST"
//				+ " WHERE ST.STATUS=1 AND ST.STOCK_ID = :stockId ");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//
//		query.addScalar("stockId", new LongType());
//		query.addScalar("name", new StringType());
//		query.addScalar("code", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//		
//		query.setParameter("stockId", obj.getStockId());
//		return (StockDTO) query.uniqueResult();
//	}
//    
//    public List<StockDTO> getListByNameOrCode(StockDTO obj){
//    	String sql = "SELECT ST.STOCK_ID stockId, "	
//    			+ "ST.NAME name,"		
//    			+ "ST.CODE code,"
//    			+ "dp.NAME departmentName "
//    			+ "FROM CAT_OWNER.STOCK ST "
//    			+ "LEFT JOIN CAT_OWNER.DEPARTMENT dp ON ST.DEPARTMENT_ID = dp.DEPARTMENT_ID "
//    			+ "WHERE ST.STATUS=1 ";
//    		
//    		StringBuilder stringBuilder = new StringBuilder(sql);
//    		
//    			if(StringUtils.isNotEmpty(obj.getName())){
//    				stringBuilder.append( " AND (upper(ST.NAME) LIKE upper(:name) escape '&' OR upper(ST.CODE) LIKE upper(:name) escape '&')");
//    			}
//    		
//    		stringBuilder.append(" ORDER BY ST.CODE");
//    		
//    		
//    		SQLQuery query= getSession().createSQLQuery(stringBuilder.toString());
//    		
//    		query.addScalar("stockId", new LongType());
//    		query.addScalar("name", new StringType());
//    		query.addScalar("code", new StringType());
//    		query.addScalar("departmentName", new StringType());
//    		
//    		query.setResultTransformer(Transformers.aliasToBean(StockDTO.class));
//
//    		if (StringUtils.isNotEmpty(obj.getName())) {
//    			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
//    		}
//    		return query.list();
//    	
//    }
//    
//    public DomainDataDTO getDomainData(){
//    	StringBuilder sql=new StringBuilder("SELECT DOMAIN_TYPE_ID domainTypeId from VPS_OWNER.DOMAIN_TYPE where CODE='KTTS_LIST_STOCK'");
//    	SQLQuery query= getSession().createSQLQuery(sql.toString());
//    	query.addScalar("domainTypeId", new LongType());
//    	query.setResultTransformer(Transformers.aliasToBean(DomainDataDTO.class));
//    	return (DomainDataDTO) query.uniqueResult();
//    }
//    
//    public List<StockCellDTO> getChangeAreaByStock(StockDTO obj){
//    	StringBuilder sql = new StringBuilder("SELECT CODE code"	
//				+ ",DESCRIPTION description,"
//				+ "STOCK_ID stockId  "		
//				+ " FROM CAT_OWNER.STOCK_CELL "
//				+ " WHERE  STOCK_ID = :stockId ");
//    	SQLQuery query= getSession().createSQLQuery(sql.toString());
//
//    	query.addScalar("stockId", new LongType());
//		query.addScalar("description", new StringType());
//		query.addScalar("code", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(StockCellDTO.class));
//		
//		query.setParameter("stockId", obj.getStockId());
//		return query.list();
//    	
//    }
//   	public Long deleteStockId(StockDTO obj) {
//
//   		try {
//   			String sql2 = "delete from CAT_OWNER.\"STOCK_CELL\" where STOCK_ID = :stockId";
//   			SQLQuery query2 = getSession().createSQLQuery(sql2);
//   			query2.setParameter("stockId",obj.getStockId());
//   			query2.executeUpdate();
//   			return 1L;
//   		} catch (Exception e) {
//   			e.getMessage();
//   			return 0L;
//   		}
//   	}
//}
