/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.wms.dao;

import com.viettel.wms.bo.GoodsBO;
import com.viettel.wms.dto.GoodsDTO;
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
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("goodsDAO")
public class GoodsDAO extends BaseFWDAOImpl<GoodsBO, Long> {

    public GoodsDAO() {
        this.model = new GoodsBO();
    }

    public GoodsDAO(Session session) {
        this.session = session;
    }
    @SuppressWarnings("unchecked")
    //tim hang hoa cho autocomplete
	public List<GoodsDTO> getForAutoCompleteGoods(GoodsDTO obj) {
		String sql = "SELECT G.GOODS_ID goodsId"
			+ " ,G.NAME name"	
			+ " ,G.IS_SERIAL isSerial"	
			+ " ,G.MANUFACTURER_NAME manufacturerName"
			+ " ,G.PRODUCING_COUNTRY_NAME producingCountryName"
			+ " ,G.CODE code"
			+ " FROM CAT_OWNER.GOODS G"
			+ " WHERE G.STATUS=1 ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		if (obj.getIsSize()){
			stringBuilder.append(" AND ROWNUM <=10 ");
			if(StringUtils.isNotEmpty(obj.getName())){
			stringBuilder.append(" AND (upper(G.NAME) LIKE upper(:name) escape '&' OR upper(G.CODE) LIKE upper(:name) escape '&')");
			}
		}
		else{
			if(StringUtils.isNotEmpty(obj.getName())){
				stringBuilder.append( " AND upper(G.NAME) LIKE upper(:name) escape '&'");
			}
			if(StringUtils.isNotEmpty(obj.getCode())){
				stringBuilder.append( " AND upper(G.CODE) LIKE upper(:value) escape '&'");
			}
		}
		
		
		stringBuilder.append(" ORDER BY G.CODE");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("isSerial", new StringType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
	
		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("value", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
		}

		return query.list();
	}
    
    //tim hang hoa co hieu luc
	public List<GoodsDTO> doSearchActiveGoods(GoodsDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT G.GOODS_ID goodsId"	
				+ " ,G.NAME name"		
				+ " ,G.CODE code"
				+ " FROM CAT_OWNER.GOODS G"
				+ " WHERE G.STATUS=1 ");
		
		

		sql.append(" ORDER BY G.CODE");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("goodsId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}
	//tim hang hoa de check du lieu file excel import
	public List<GoodsDTO> getGoodsForOrder(GoodsDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT G.GOODS_ID goodsId,"	
				+ " G.UNIT_TYPE goodsUnitId,"
				+ " G.UNIT_TYPE_NAME goodsUnitName,"
				+ " G.NAME name,"
				+" G.ORIGIN_PRICE originPrice,"
				+ " G.IS_SERIAL isSerial,"
				+ " G.GOODS_TYPE goodsType,"
				+ " GT.NAME goodsTypeName,"
				+ " G.STATUS status,"
				+ " G.MANUFACTURER_NAME manufacturerName,"
				+ " G.MANUFACTURER_ID manufacturerId,"
				+ " G.PRODUCING_COUNTRY_NAME producingCountryName,"
				+ " G.PRODUCING_COUNTRY_ID producingCountryId,"
				+ " G.CODE code,"
				+ " G.CREATED_DATE createdDate"
				+ " FROM CAT_OWNER.GOODS G "
				+ " JOIN CAT_OWNER.GOODS_TYPE GT ON GT.GOODS_TYPE_ID = G.GOODS_TYPE "
				+ " WHERE G.STATUS=1 ");
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ((upper(G.CODE) LIKE upper(:keySearch)) "
					+ "OR (upper(G.NAME) LIKE upper(:keySearch)))  ");
		}
		
		if(StringUtils.isNotEmpty(obj.getCode())){
			sql.append(" AND G.CODE=:code");
		}
		
		if(obj.getStockId()!=null){
			sql.append(" AND G.GOODS_ID in (SELECT SG.GOODS_ID goodsId FROM STOCK_GOODS SG WHERE SG.STOCK_ID=:stockId UNION ALL SELECT SGS.GOODS_ID goodsId FROM STOCK_GOODS_SERIAL SGS WHERE SGS.STOCK_ID=:stockId)");
		}
		
		sql.append(" ORDER BY G.CODE");


		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("isSerial", new StringType());
		query.addScalar("goodsType", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("originPrice", new DoubleType());
		query.addScalar("createdDate", new DateType());
		
		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch().trim()) + "%");
		}
		
		if(StringUtils.isNotEmpty(obj.getCode())){
			query.setParameter("code", obj.getCode().trim());
		}

		if(obj.getStockId()!=null){
			query.setParameter("stockId", obj.getStockId());
		}
		
		return query.list();
	}
	
	//tim hang hoa theo ma hang hoa
	public GoodsDTO getGoodByCode(String code) {
		StringBuilder sql = new StringBuilder("SELECT G.GOODS_ID goodsId,"	
				+ " G.UNIT_TYPE goodsUnitId,"
				+ " G.UNIT_TYPE_NAME goodsUnitName,"
				+ " G.NAME name,"
				+" G.ORIGIN_PRICE originPrice,"
				+ " G.IS_SERIAL isSerial,"
				+ " G.GOODS_TYPE goodsType,"
				+ " G.STATUS status,"
				+ " G.MANUFACTURER_NAME manufacturerName,"
				+ " G.MANUFACTURER_ID manufacturerId,"
				+ " G.PRODUCING_COUNTRY_NAME producingCountryName,"
				+ " G.PRODUCING_COUNTRY_ID producingCountryId,"
				+ " G.CODE code"
				+ " FROM CAT_OWNER.GOODS G"
				+ " WHERE G.STATUS=1  And G.CODE =:code ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("isSerial", new StringType());
		query.addScalar("goodsType", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("originPrice", new DoubleType());

		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));

		query.setParameter("code", code);

		return (GoodsDTO) query.uniqueResult();
	}
	
	public GoodsDTO getGoodById(Long id) {
		StringBuilder sql = new StringBuilder("SELECT G.GOODS_ID goodsId,"	
				+ " G.UNIT_TYPE goodsUnitId,"
				+ " G.NAME name,"
				+" G.ORIGIN_PRICE originPrice,"
				+ " G.STATUS status,"
				+ " G.GOODS_TYPE goodsType,"
				+ " G.CODE code,"
				+ " G.MANUFACTURER_NAME manufacturerName,"
				+ " G.PRODUCING_COUNTRY_NAME producingCountryName,"
				+ " G.MANUFACTURER_ID manufacturerId,"
				+ " G.PRODUCING_COUNTRY_ID producingCountryId,"
				+ " G.IS_SERIAL isSerial"
				+ " FROM CAT_OWNER.GOODS G"
				+ " WHERE G.STATUS=1 And G.GOODS_ID =:id ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("goodsId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());
		query.addScalar("isSerial", new StringType());
		query.addScalar("goodsType", new StringType());
		query.addScalar("goodsUnitId", new LongType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("producingCountryId", new LongType());
		query.addScalar("originPrice", new DoubleType());
		

		query.setResultTransformer(Transformers.aliasToBean(GoodsDTO.class));

		query.setParameter("id", id);

		return (GoodsDTO) query.uniqueResult();
	}
	
}

