///*
// * Copyright (C) 2011 Viettel Telecom. All rights reserved.
// * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.viettel.wms.dao;
//
//import com.viettel.wms.bo.GoodsTypeBO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.GoodsTypeDTO;
//import com.viettel.wms.dto.StockDTO;
//import com.viettel.wms.utils.ValidateUtils;
//import com.viettel.service.base.dao.BaseFWDAOImpl;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.transform.Transformers;
//import org.hibernate.type.LongType;
//import org.hibernate.type.StringType;
//import org.springframework.stereotype.Repository;
//
///**
// * @author TuanNB
// * @version 1.0
// */
//@Repository("GoodsTypeDAO")
//public class GoodsTypeDAO extends BaseFWDAOImpl<GoodsTypeBO, Long> {
//
//    public GoodsTypeDAO() {
//        this.model = new GoodsTypeBO();
//    }
//
//    public GoodsTypeDAO(Session session) {
//        this.session = session;
//    }
//    
//    @SuppressWarnings("unchecked")
//    public List<GoodsTypeDTO> getList(GoodsTypeDTO obj){
//		StringBuilder sql = new StringBuilder("SELECT GOODS_TYPE_ID goodsTypeId,"
//				+ "NAME name "
//				+ "FROM CAT_OWNER.GOODS_TYPE WHERE STATUS=1 ");
//
//		sql.append(" ORDER BY GOODS_TYPE_ID");
//		
//		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
//		sqlCount.append(sql.toString());
//		sqlCount.append(")");
//		
//		SQLQuery query= getSession().createSQLQuery(sql.toString());
//		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
//		
//		query.addScalar("goodsTypeId", new LongType());
//		query.addScalar("name", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(GoodsTypeDTO.class));
//		
//		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
//		query.setMaxResults(obj.getPageSize().intValue());
//		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
//		
//		return query.list();
//	}
//    @SuppressWarnings("unchecked")
//	public List<GoodsTypeDTO> getGoodTypeList(GoodsTypeDTO obj) {
//		String sql = "SELECT GOODS_TYPE_ID goodsTypeId, CODE code,"
//				+ "NAME name "
//				+ "FROM CAT_OWNER.GOODS_TYPE GT WHERE STATUS=1 ";
//		
//		SQLQuery query = getSession().createSQLQuery(sql);
//		
//		query.addScalar("goodsTypeId", new LongType());
//		query.addScalar("code", new LongType());
//		query.addScalar("name", new StringType());
//		query.setResultTransformer(Transformers.aliasToBean(GoodsTypeDTO.class));
//		return query.list();
//	}
//    
//    public GoodsTypeDTO getGoodTypeById(Long id) {
//		String sql = "SELECT GOODS_TYPE_ID goodsTypeId,"
//				+ "NAME name "
//				+ "FROM CAT_OWNER.GOODS_TYPE GT WHERE STATUS=1 AND GOODS_TYPE_ID =:id";
//		
//		SQLQuery query = getSession().createSQLQuery(sql);
//		
//		query.addScalar("goodsTypeId", new LongType());
//		query.addScalar("name", new StringType());
//		
//		query.setResultTransformer(Transformers.aliasToBean(GoodsTypeDTO.class));
//
//		query.setParameter("id", id);
//
//		return (GoodsTypeDTO) query.uniqueResult();
//	}
//    
//}
