
package com.viettel.wms.dao;

import com.viettel.wms.bo.StockGoodsTotalBO;
import com.viettel.wms.dto.StockGoodsTotalDTO;
import com.viettel.wms.utils.ValidateUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author TUANB
 * @version 1.0
 * @since 29-Nov-17 
 */
@Repository("stockGoodsTotalDAO")
public class StockGoodsTotalDAO extends BaseFWDAOImpl<StockGoodsTotalBO, Long> {

	public StockGoodsTotalDAO() {
		this.model = new StockGoodsTotalBO();
	}

	public StockGoodsTotalDAO(Session session) {
		this.session = session;
	}

	public List<StockGoodsTotalDTO> doSearchIdOfGoodStateAndStock() {
		StringBuilder sql = new StringBuilder("SELECT sgt.GOODS_ID goodsId,"
				+ "sgt.GOODS_STATE goodsState," + "sgt.STOCK_ID stockId "
				+ "FROM WMS_OWNER_KTTS.STOCK_GOODS_TOTAL sgt ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("stockId", new LongType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockGoodsTotalDTO.class));

		return query.list();

	}

	public StockGoodsTotalDTO getGood(Long stockId, Long goodsId,
			String goodsStateName, String goodsCode, String goodsName) {
		StringBuilder sql = new StringBuilder(
				"SELECT sgt.AMOUNT amount, sgt.GOODS_ID goodsId, sgt.AMOUNT_ISSUE amountIssue "
						+ "FROM WMS_OWNER_KTTS.STOCK_GOODS_TOTAL sgt "
						+ "WHERE sgt.STOCK_ID = :stockId  AND (upper(sgt.GOODS_STATE_NAME) = upper(:goodsStateName)) AND sgt.GOODS_CODE = :goodsCode AND sgt.GOODS_NAME = :goodsName");
		if (goodsId != null) {
			sql.append(" AND sgt.GOODS_ID = :goodsId");
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("amount", new DoubleType());
		query.addScalar("amountIssue", new DoubleType());
		query.addScalar("goodsId", new LongType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockGoodsTotalDTO.class));

		query.setParameter("stockId", stockId);
		if (goodsId != null) {
			query.setParameter("goodsId", goodsId);
		}
		query.setParameter("goodsStateName", goodsStateName);
		query.setParameter("goodsCode", goodsCode);
		query.setParameter("goodsName", goodsName);

		return (StockGoodsTotalDTO) query.uniqueResult();

	}

	public boolean updateStockGoodsTotal(StockGoodsTotalDTO stockGoodsTotalDTO) {
		StringBuilder sql = new StringBuilder(
				"UPDATE WMS_OWNER_KTTS.STOCK_GOODS_TOTAL sgt "
						+ "SET "
						+ "sgt.AMOUNT = :newAmount, "
						+ "sgt.AMOUNT_ISSUE = :newAmountIssue, "
						+ "sgt.CHANGE_DATE = :newDate "
						+ "WHERE sgt.STOCK_ID = :stockId AND sgt.GOODS_ID = :goodsId AND (upper(sgt.GOODS_STATE_NAME) = upper(:goodsStateName))");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.setParameter("newAmount", stockGoodsTotalDTO.getAmount());
		query.setParameter("newAmountIssue", stockGoodsTotalDTO.getAmountIssue());
		query.setParameter("newDate", new Date());
		query.setParameter("stockId", stockGoodsTotalDTO.getStockId());
		query.setParameter("goodsId", stockGoodsTotalDTO.getGoodsId());
		query.setParameter("goodsStateName", stockGoodsTotalDTO.getGoodsStateName());

		return (query.executeUpdate() > 0) ? true : false;
	}

	// Tìm kiếm các bản ghi trong bảng Stock_Goods_Total -- TUANNB
	@SuppressWarnings("unchecked")
	public List<StockGoodsTotalDTO> doSearch(StockGoodsTotalDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT "
						+ "sg.STOCK_ID stockId,"
						+ "sg.GOODS_ID goodsId,"
						+ "sg.GOODS_CODE goodsCode,"
						+ "gt.NAME goodsTypeName,"
						+ "sg.GOODS_TYPE goodsType,"
						+ "sg.GOODS_NAME goodsName,"
						+ "sg.GOODS_STATE_NAME goodsStateName,"
						+ "sg.GOODS_UNIT_NAME goodsUnitName,"
						+ "sg.AMOUNT amount "
						+ "FROM WMS_OWNER_KTTS.STOCK_GOODS_TOTAL sg "
						+ "LEFT JOIN CAT_OWNER.GOODS_TYPE gt ON sg.GOODS_TYPE = gt.GOODS_TYPE_ID "
						+ "WHERE 1=1");

		if((obj.getListStockId()!=null && !obj.getListStockId().isEmpty())){
		   sql.append(" AND sg.STOCK_ID IN :listStockId");
		}
		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(sg.GOODS_NAME) LIKE upper(:name) OR upper(sg.GOODS_CODE) LIKE upper(:name))");
		}
		if (StringUtils.isNotEmpty(obj.getGoodsState())
				&& !"0".equals(obj.getGoodsState())) {
			sql.append(" AND sg.GOODS_STATE = :goodsState");
		}
		if ((obj.getListGoodsType() != null && !obj.getListGoodsType()
				.isEmpty())) {
			sql.append(" AND gt.GOODS_TYPE_ID IN (:listGoodsType)");
		}

		sql.append(" ORDER BY sg.GOODS_TYPE, sg.GOODS_CODE");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("stockId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("goodsType", new LongType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("amount", new DoubleType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockGoodsTotalDTO.class));

		if((obj.getListStockId()!=null && !obj.getListStockId().isEmpty())){
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount.setParameterList("listStockId", obj.getListStockId());
		}
		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name",
					"%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
			queryCount.setParameter("name",
					"%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getGoodsState())
				&& !"0".equals(obj.getGoodsState())) {
			query.setParameter("goodsState", obj.getGoodsState());
			queryCount.setParameter("goodsState", obj.getGoodsState());
		}

		if ((obj.getListGoodsType() != null)
				&& !obj.getListGoodsType().isEmpty()) {
			query.setParameterList("listGoodsType", obj.getListGoodsType());
			queryCount
					.setParameterList("listGoodsType", obj.getListGoodsType());
		}
		query.setFirstResult((obj.getPage().intValue() - 1)
				* obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<StockGoodsTotalDTO> doSearchTotal(StockGoodsTotalDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT  SGT.STOCK_GOODS_TOTAL_ID stockGoodsTotalId,"
						+ " SGT.GOODS_CODE goodsCode,"
						+ " SGT.GOODS_NAME goodsName,"
						+ " SGT.STOCK_ID stockId,"
						+ " SGT.GOODS_STATE goodsState,"
						+ " SGT.GOODS_STATE_NAME goodsStateName,"
						+ " SGT.GOODS_UNIT_NAME goodsUnitName,"
						+ " SGT.GOODS_TYPE_NAME goodsTypeName,"
						+ "ST.NAME name, "
						+ " SGT.AMOUNT amount, "
						+ " SGS.SERIAL serial,"
						+ " SGS.CONTRACT_CODE contractCode,"
						+ " SGS.PART_NUMBER partNumber,"
						+ " SGS.MANUFACTURER_NAME manufacturerName,"
						+ " SGS.PRODUCING_COUNTRY_NAME producingCountryName"
						+ " FROM WMS_OWNER_KTTS.STOCK_GOODS_TOTAL SGT LEFT JOIN CAT_OWNER.STOCK ST ON ( SGT.STOCK_ID = ST.STOCK_ID) INNER JOIN WMS_OWNER_KTTS.STOCK_GOODS SG ON (SGT.GOODS_ID = SG.GOODS_ID)"
						+ "INNER JOIN WMS_OWNER_KTTS.STOCK_GOODS_SERIAL SGS ON (SGS.GOODS_ID = SGT.GOODS_ID)"
						+ "WHERE 1=1");
		if (StringUtils.isNotEmpty(obj.getGoodsState())
				&& !"0".equals(obj.getGoodsState())) {
			sql.append(" AND  SGT.GOODS_STATE = :goodsState");
		}

		if ((obj.getGoodsCode() != null && !obj.getGoodsCode().isEmpty())) {
			sql.append(" AND  SGT.GOODS_CODE = :goodsCode");
		}

		if ((obj.getStockName() != null && !obj.getStockName().isEmpty())) {
			sql.append(" AND ST.NAME = :name");
		}

		if ((obj.getListGoodsType() != null && !obj.getListGoodsType()
				.isEmpty())) {
			sql.append(" AND  SGT.GOODS_TYPE IN (:listGoodsType)");
		}

		sql.append(" ORDER BY  SGT.GOODS_CODE");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("stockGoodsTotalId", new LongType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("goodsUnitName", new StringType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("stockId", new LongType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("serial", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("partNumber", new StringType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("producingCountryName", new StringType());

		query.addScalar("name", new StringType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockGoodsTotalDTO.class));

		if (StringUtils.isNotEmpty(obj.getGoodsState())
				&& !"0".equals(obj.getGoodsState())) {
			query.setParameter("goodsState", obj.getGoodsState());
			queryCount.setParameter("goodsState", obj.getGoodsState());
		}

		if ((obj.getGoodsCode() != null && !obj.getGoodsCode().isEmpty())) {
			query.setParameter("goodsCode", obj.getGoodsCode());
			queryCount.setParameter("goodsCode", obj.getGoodsCode());
		}

		if ((obj.getStockName() != null && !obj.getStockName().isEmpty())) {
			query.setParameter("name", obj.getStockName());
			queryCount.setParameter("name", obj.getStockName());
		}

		if ((obj.getListGoodsType() != null)
				&& !obj.getListGoodsType().isEmpty()) {
			query.setParameterList("listGoodsType", obj.getListGoodsType());
			queryCount
					.setParameterList("listGoodsType", obj.getListGoodsType());
		}
		query.setFirstResult((obj.getPage().intValue() - 1)
				* obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<StockGoodsTotalDTO> doSearchStockGood(StockGoodsTotalDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT  SGT.STOCK_GOODS_TOTAL_ID stockGoodsTotalId,"
						+ "SGT.GOODS_ID goodsId,"
						+ "SGT.STOCK_CODE stockCode,"
						+ "SGT.STOCK_NAME stockName,"
						+ "SGT.GOODS_CODE goodsCode,"
						+ "SGT.GOODS_NAME goodsName,"
						+ "SGT.GOODS_TYPE goodsType,"
						+ "SGT.GOODS_STATE_NAME goodsStateName,"
						+ "SGT.AMOUNT amount,"
						+ "SGT.GOODS_STATE goodsState,"
						+ "SGT.GOODS_TYPE_NAME goodsTypeName,"
//						+ "SGS.SERIAL serial,"
//						+ "SGS.CONTRACT_CODE contractCode,"
//						+ "SGS.PART_NUMBER partNumber,"
//						+ "SGS.MANUFACTURER_NAME manufacturerName,"
						+ "SGT.STOCK_ID stockId,"
						+ "SGT.GOODS_IS_SERIAL goodsIsSerial"
//						+ "SGS.PRODUCING_COUNTRY_NAME producingCountryName "
						+ " FROM WMS_OWNER_KTTS.STOCK_GOODS_TOTAL SGT "
//						+ "INNER JOIN WMS_OWNER_KTTS.STOCK_GOODS_SERIAL SGS ON (SGS.GOODS_ID = SGT.GOODS_ID)"
						+ "INNER JOIN CAT_OWNER.STOCK ST ON ST.STOCK_ID = SGT.STOCK_ID "
						+ "INNER JOIN CAT_OWNER.GOODS CG ON CG.GOODS_ID = SGT.GOODS_ID"
//						+ "INNER JOIN CAT_OWNER.GOODS_TYPE GT ON GT.GOODS_TYPE_ID = SGT.GOODS_TYPE"
						+ " WHERE 1=1");
		 if (StringUtils.isNotEmpty(obj.getGoodsState())
				&& !"0".equals(obj.getGoodsState())) {
			sql.append(" AND  SGT.GOODS_STATE = :goodsState");
		} 

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(SGT.GOODS_NAME) LIKE upper(:name) OR upper(SGT.GOODS_CODE) LIKE upper(:name))");
		}

		if ((obj.getListStockId() != null && !obj.getListStockId()
				.isEmpty())) {
			sql.append(" AND  SGT.STOCK_ID IN (:listStockId)");
		}

		if ((obj.getListGoodsType() != null && !obj.getListGoodsType()
				.isEmpty())) {
			sql.append(" AND  SGT.GOODS_TYPE IN (:listGoodsType)");
		}

		sql.append(" ORDER BY  SGT.STOCK_NAME");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("stockGoodsTotalId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsType", new LongType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockId", new LongType());
		query.addScalar("goodsIsSerial", new StringType());

//		query.addScalar("serial", new StringType());
//		query.addScalar("contractCode", new StringType());
//		query.addScalar("partNumber", new StringType());
//		query.addScalar("manufacturerName", new StringType());
//		query.addScalar("producingCountryName", new StringType());

		query.setResultTransformer(Transformers
				.aliasToBean(StockGoodsTotalDTO.class));

		 if (StringUtils.isNotEmpty(obj.getGoodsState())
				&& !"0".equals(obj.getGoodsState())) {
			query.setParameter("goodsState", obj.getGoodsState());
			queryCount.setParameter("goodsState", obj.getGoodsState());
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name",
					"%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
			queryCount.setParameter("name",
					"%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}

		if ((obj.getListStockId() != null)
				&& !obj.getListStockId().isEmpty()) {
			query.setParameterList("listStockId", obj.getListStockId());
			queryCount
					.setParameterList("listStockId", obj.getListStockId());
		}
		
			
		

		if ((obj.getListGoodsType() != null)
				&& !obj.getListGoodsType().isEmpty()) {
			query.setParameterList("listGoodsType", obj.getListGoodsType());
			queryCount
					.setParameterList("listGoodsType", obj.getListGoodsType());
		}
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	public StockGoodsTotalDTO getAmountIssueGoodsTotal(Long goodsId,
			Long stockId, String goodsState) {
		try{
			StringBuilder sql = new StringBuilder(
					"SELECT sg.STOCK_GOODS_TOTAL_ID stockGoodsTotalId,"
							+ "sg.AMOUNT_ISSUE amountIssue, "
							+ "sg.AMOUNT amount, "
							+  "sg.GOODS_ID goodsId, "
							+ "sg.STOCK_ID stockId, "
							+ "sg.GOODS_STATE goodsState, "
							+ "sg.GOODS_STATE_NAME goodsStateName "
							+ "FROM WMS_OWNER_KTTS.STOCK_GOODS_TOTAL sg "
							+ "WHERE 1=1 "
							+ "AND sg.GOODS_ID = :goodsId "
							+ "AND sg.STOCK_ID = :stockId"
							+ " AND sg.GOODS_STATE = :goodsState");
		
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("amount", new DoubleType());
			query.addScalar("amountIssue", new DoubleType());
			query.addScalar("stockGoodsTotalId", new LongType());
			query.addScalar("goodsId", new LongType());
			query.addScalar("goodsState", new StringType());
			query.addScalar("goodsStateName", new StringType());
			query.addScalar("stockId", new LongType());
			
			query.setResultTransformer(Transformers
					.aliasToBean(StockGoodsTotalDTO.class));
			
			query.setParameter("goodsState", goodsState);
			query.setParameter("goodsId", goodsId);
			query.setParameter("stockId", stockId);
			
			return (StockGoodsTotalDTO) query.uniqueResult();
			
		}catch(Exception er){
				if(er instanceof NonUniqueResultException){
					throw new IllegalArgumentException("Xuất hiện hàng hóa tồn tại kép trong kho");
				}else{
					return null;
				}		
		}
	}

	public void updateAmount(StockGoodsTotalDTO obj, boolean flag) {
		StringBuilder sql = new StringBuilder("UPDATE WMS_OWNER_KTTS.STOCK_GOODS_TOTAL sgt SET ");
		if (flag) {
			sql.append(" sgt.AMOUNT_ISSUE=:amount ");
		} else {
			sql.append(" sgt.AMOUNT=:amount ");
		}
		sql.append(", sgt.CHANGE_DATE=SYSDATE WHERE sgt.STOCK_GOODS_TOTAL_ID=:id");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		if (flag) {
			query.setParameter("amount", obj.getAmountIssue());
		} else {
			query.setParameter("amount", obj.getAmount());
		}
		query.setParameter("id", obj.getStockGoodsTotalId());
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<StockGoodsTotalDTO> getCheckStockGood(StockGoodsTotalDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT  SGT.STOCK_GOODS_TOTAL_ID stockGoodsTotalId,"
						+ "SGT.GOODS_ID goodsId,"
						+ "SGT.STOCK_CODE stockCode,"
						+ "SGT.STOCK_NAME stockName,"
						+ "SGT.GOODS_CODE goodsCode,"
						+ "SGT.GOODS_NAME goodsName,"
						+ "SGT.GOODS_TYPE goodsType,"
						+ "SGT.GOODS_STATE_NAME goodsStateName,"
						+ "SGT.AMOUNT amount,"
						+ "SGT.GOODS_STATE goodsState,"
						+ "SGT.GOODS_TYPE_NAME goodsTypeName,"
						+ "SGT.STOCK_ID stockId,"
						+ "SGT.GOODS_IS_SERIAL goodsIsSerial"
						+ " FROM WMS_OWNER_KTTS.STOCK_GOODS_TOTAL SGT "
						+ "INNER JOIN CAT_OWNER.STOCK ST ON ST.STOCK_ID = SGT.STOCK_ID "
						+ "INNER JOIN CAT_OWNER.GOODS CG ON CG.GOODS_ID = SGT.GOODS_ID"
						+ " WHERE 1=1");
		if (StringUtils.isNotEmpty(obj.getGoodsState())
				&& !"0".equals(obj.getGoodsState())) {
			sql.append(" AND  SGT.GOODS_STATE = :goodsState");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(SGT.GOODS_NAME) LIKE upper(:name) OR upper(SGT.GOODS_CODE) LIKE upper(:name))");
		}

		if ((obj.getListStockId() != null && !obj.getListStockId()
				.isEmpty())) {
			sql.append(" AND  SGT.STOCK_ID IN (:listStockId)");
		}

		if ((obj.getListGoodsType() != null && !obj.getListGoodsType()
				.isEmpty())) {
			sql.append(" AND  SGT.GOODS_TYPE IN (:listGoodsType)");
		}

		sql.append(" ORDER BY  SGT.STOCK_NAME");

	

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("stockGoodsTotalId", new LongType());
		query.addScalar("goodsId", new LongType());
		query.addScalar("goodsState", new StringType());
		query.addScalar("goodsStateName", new StringType());
		query.addScalar("goodsCode", new StringType());
		query.addScalar("goodsName", new StringType());
		query.addScalar("goodsType", new LongType());
		query.addScalar("amount", new DoubleType());
		query.addScalar("goodsTypeName", new StringType());
		query.addScalar("stockCode", new StringType());
		query.addScalar("stockName", new StringType());
		query.addScalar("stockId", new LongType());
		query.addScalar("goodsIsSerial", new StringType());


		query.setResultTransformer(Transformers
				.aliasToBean(StockGoodsTotalDTO.class));

		if (StringUtils.isNotEmpty(obj.getGoodsState())
				&& !"0".equals(obj.getGoodsState())) {
			query.setParameter("goodsState", obj.getGoodsState());
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name",
					"%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}

		if ((obj.getListStockId() != null)
				&& !obj.getListStockId().isEmpty()) {
			query.setParameterList("listStockId", obj.getListStockId());
		}
		
		if ((obj.getListGoodsType() != null)
				&& !obj.getListGoodsType().isEmpty()) {
			query.setParameterList("listGoodsType", obj.getListGoodsType());
		}

		return query.list();
	}
}




