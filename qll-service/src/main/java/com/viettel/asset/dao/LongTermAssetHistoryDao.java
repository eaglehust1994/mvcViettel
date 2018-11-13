package com.viettel.asset.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.viettel.asset.bo.LongTermAssetHistory;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.DatabaseException;
import com.viettel.asset.dto.AssetReoportS23Dto;
import com.viettel.asset.dto.AssetReportS21DetailDto;
import com.viettel.asset.dto.AssetReportS21SearchDto;
import com.viettel.asset.dto.LongTermAssetHistoryReportS23Dto;
import com.viettel.asset.dto.ReportIncreaseDecreaseDto;
import com.viettel.ktts2.common.UString;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import org.hibernate.Session;
import org.hibernate.hql.internal.antlr.SqlStatementParserTokenTypes;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;
import org.apache.ignite.cache.query.SqlQuery;
import org.hibernate.Query;
import org.hibernate.SQLQuery;



@Repository("longTermAssetHistoryDao")
public class LongTermAssetHistoryDao extends HibernateDao<LongTermAssetHistory, Long> {
	@SuppressWarnings("unchecked")
	public List<AssetReportS21DetailDto> getDataReportS21(AssetReportS21SearchDto dto){
		
		Session session=currentSession();
		String sql="select LOTACODE as lotaCode, "
				+ "NUM as num,"
				+ "INCREASEVOUCHERCODE as increaseVoucherCode,"
				+ "INCREASEVOUCHERDATE as increaseVoucherDate, "
				+ "ORIGINALPRICE as originalPrice,"
				+ "DEPRECIATIONSTARTDATE as depreciationStartDate,"
				+ "DEPRECIATERATEYEARLYPERCENT as depreciateRateYearlyPercent,"
				+ "DEPRECIATERATEYEARLYVALUE as depreciateRateYearlyValue,"
				+ "DEPRECIATIEDVALUE as depreciatiedValue ,"
				+ "DECREASEVOUCHERCODE as decreaseVoucherCode,"
				+ "DECREASEVOUCHERDATE as decreaseVoucherDate,"
				+ "DECREASEREASONCHANGE as decreaseReasonChange,"
				+ "CAACNAME as caacName ,"
				+ "NATIONALNAME as nationalName"
				+ " from table (PKG_LONG_TERM_ASSET_REPORT.EXPORT_S21_1(:fullCode,:startDate,:endDate,:groupId))";
		
		SQLQuery query=session.createSQLQuery(sql);
		query.setParameter("fullCode", UString.trim(dto.getCaacFullCode()));
		query.setParameter("groupId", dto.getGroupId());
		query.setParameter("startDate", dto.getFromDate());
		query.setParameter("endDate", dto.getToDate());
		query.setResultTransformer(Transformers.aliasToBean(AssetReportS21DetailDto.class));
		query.addScalar("lotaCode",StandardBasicTypes.STRING);
		query.addScalar("num",StandardBasicTypes.LONG);
		query.addScalar("increaseVoucherCode",StandardBasicTypes.STRING);
		query.addScalar("increaseVoucherDate",StandardBasicTypes.DATE);
		query.addScalar("originalPrice",StandardBasicTypes.LONG);
		query.addScalar("depreciationStartDate",StandardBasicTypes.TIMESTAMP);
		query.addScalar("depreciateRateYearlyPercent",StandardBasicTypes.STRING);
		query.addScalar("depreciateRateYearlyValue",StandardBasicTypes.LONG);
		query.addScalar("depreciatiedValue",StandardBasicTypes.LONG);
		query.addScalar("decreaseVoucherCode",StandardBasicTypes.STRING);
		query.addScalar("decreaseVoucherDate",StandardBasicTypes.DATE);
		query.addScalar("decreaseReasonChange",StandardBasicTypes.STRING);
		query.addScalar("caacName",StandardBasicTypes.STRING);
		query.addScalar("nationalName",StandardBasicTypes.STRING);
		return query.list();
	}

	public LongTermAssetHistory getDepreciatedRow(Long longTermAssetId, Long depreciatedMonth, Long depreciatedYear) throws DatabaseException {
		List<LongTermAssetHistory> lst = find(new String[] {LongTermAssetHistory.Columns.LONG_TERM_ASSET_ID,
				LongTermAssetHistory.Columns.DEPRECIATED_MONTH,
				LongTermAssetHistory.Columns.DEPRECIATED_YEAR},
				new Object[] {longTermAssetId, depreciatedMonth, depreciatedYear});
		if (lst == null || lst.isEmpty()) {
			return null;
		}
		/* Không thể xảy ra vì có Constrain Unique Key với cặp LONG_TERM_ASSET_ID, DEPRECIATED_MONTH, DEPRECIATED_YEAR */
		if (lst.size() > 1) {
			throw new DatabaseException("Dữ liệu lỗi: có 2 bản ghi trong LONG_TERM_ASSET_HISTORIES có cùng cặp LONG_TERM_ASSET_ID, DEPRECIATED_MONTH, DEPRECIATED_YEAR");
		}
		return lst.get(0);
	}
	

	
	@SuppressWarnings("unchecked")
	public List<AssetReoportS23Dto> getDataReportS23(AssetReportS21SearchDto dto){
		
		Session session=currentSession();
		String sql="select VOUCHERCODE as voucherCode, "
				+ "VOUCHERDATE as voucherDate,"
				+ " ORIGINALPRICE as originalPrice,"
				+ " DEPRECIATIONSTARTDATE as depreciationStartDate,"
				+ "DEPRECIATEDVALUE as depreciatedValue,"
				+ " REASONCHANGE as reasonChange,"
				+ "DEPRECIATEDYEARVALUE as depreciatedYearValue,"
				+ " DEPRECIATEDYEAR as depreciatedYear,"
				+ "DEPRECIATEDMONTH as depreciatedMonth,"
				+ "DEPRECIATEDMONTHVALUE as depreciatedMonthValue,"
				+ " LTAHTYPE as ltahType,"
				+ "LONGTERMASSETHISTORYID as longTermAssetHistoryId"								
				+ " from table (PKG_LONG_TERM_ASSET_REPORT.EXPORT_S23(:longTermAssetId,:startDate,:endDate,:groupId))";
		
		SQLQuery query=session.createSQLQuery(sql);
		query.setParameter("longTermAssetId", dto.getLongTermAssetId());
		query.setParameter("startDate", dto.getFromDate());
		query.setParameter("endDate", dto.getToDate());
		query.setParameter("groupId", dto.getGroupId());
		
		query.setResultTransformer(Transformers.aliasToBean(AssetReoportS23Dto.class));
		
		query.addScalar("voucherCode",StandardBasicTypes.STRING);
		query.addScalar("voucherDate",StandardBasicTypes.DATE);
		query.addScalar("originalPrice",StandardBasicTypes.LONG);
		query.addScalar("depreciationStartDate",StandardBasicTypes.DATE);
		query.addScalar("depreciatedValue",StandardBasicTypes.LONG);
		query.addScalar("reasonChange",StandardBasicTypes.STRING);		
		query.addScalar("depreciatedYearValue",StandardBasicTypes.LONG);
		query.addScalar("depreciatedYear",StandardBasicTypes.LONG);		
		query.addScalar("depreciatedMonth",StandardBasicTypes.LONG);
		query.addScalar("depreciatedMonthValue",StandardBasicTypes.LONG);
		query.addScalar("ltahType",StandardBasicTypes.LONG);
		query.addScalar("longTermAssetHistoryId",StandardBasicTypes.LONG);
		
		return query.list();
	}

	//Lấy dữ liệu bản ghi đầu tiên của history gàn khoảng thời gian
	public LongTermAssetHistoryReportS23Dto getIncreaseReportS23InPreviousTime(AssetReportS21SearchDto dto) {
		Session session=currentSession();
		String sql ="select"
				+ " s.name as useGroupName,"
				+ "s.address as groupAddress,"
				+ "s.name as groupName,"
				+ "ltah.lota_code as lotaCode,"
				+ "ltah.handover_code as handoverCode,"
				+ "ltah.ltah_date as handoverDate,"
				+ "lta.depreciation_Start_date as depreciationStartDate,"
				+ "cac.caac_name as caacName,"
				+ "cac.caac_full_code as caacFullCode,"
				+ "lta.national_name as nationalName,"
				+ "lta.description as description,"
				+ "lta.made_year as madeYear "
				+ " from LONG_TERM_ASSET_HISTORIES ltah "
				+ "inner join LONG_TERM_ASSETS lta on ltah.LONG_TERM_ASSET_ID= lta.LONG_TERM_ASSET_ID and lta.LOTA_TYPE =1  "
				+ "inner join CAT_ASSET_CODEs cac on cac.CAT_ASSET_CODE_ID = lta.CAT_ASSET_CODE_ID "
				+ " left join SYS_GROUP s on ltah.USE_GROUP_ID = s.group_id and s.status=1 "
				+ " left join SYS_GROUP s2 on ltah.GROUP_ID = s2.group_id and s2.status=1 "
				+ " where ltah.ltah_type=2 and ltah_date <=:startDate "
				+ " and ltah.LONG_TERM_ASSET_ID = :longTermAssetId and ltah.use_group_id =:groupId"
				+ " and rownum=1 "
				+ " order by ltah_date desc  ";
		SQLQuery query=session.createSQLQuery(sql);
		query.setParameter("longTermAssetId", dto.getLongTermAssetId());
		query.setParameter("startDate", dto.getFromDate());		
		query.setParameter("groupId", dto.getGroupId());	
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetHistoryReportS23Dto.class));
		query.addScalar("useGroupName",StandardBasicTypes.STRING);
		query.addScalar("groupAddress",StandardBasicTypes.STRING);
		query.addScalar("groupName",StandardBasicTypes.STRING);
		query.addScalar("lotaCode",StandardBasicTypes.STRING);
		query.addScalar("handoverCode",StandardBasicTypes.STRING);
		query.addScalar("handoverDate",StandardBasicTypes.DATE);
		query.addScalar("depreciationStartDate",StandardBasicTypes.DATE);
		query.addScalar("caacName",StandardBasicTypes.STRING);
		query.addScalar("caacFullCode",StandardBasicTypes.STRING);
		query.addScalar("nationalName",StandardBasicTypes.STRING);
		query.addScalar("madeYear",StandardBasicTypes.LONG);
		query.addScalar("description",StandardBasicTypes.STRING);
		@SuppressWarnings("rawtypes")
		List lst=query.list();
		if(lst.isEmpty()){
			
			throw new BusinessException("error.not.has.handoverOfAsset");
		}else 
			return (LongTermAssetHistoryReportS23Dto)lst.get(0);
		
	}
	//Lấy dữ liệu báo cáo dựa trên id lịch sử
		public LongTermAssetHistoryReportS23Dto getIncreaseReportS23ByHistoryId(Long historyId) {
			Session session=currentSession();
			String sql ="select"
					+ " s.name as useGroupName,"
					+ "s.address as groupAddress,"
					+ "s.name as groupName,"
					+ "ltah.lota_code as lotaCode,"
					+ "ltah.handover_code as handoverCode,"
					+ "ltah.ltah_date as handoverDate,"
					+ "lta.depreciation_Start_date as depreciationStartDate,"
					+ "cac.caac_name as caacName,"
					+ "cac.caac_full_code as caacFullCode,"
					+ "lta.national_name as nationalName,"
					+ "lta.description as description,"
					+ "lta.made_year as madeYear "
					+ " from LONG_TERM_ASSET_HISTORIES ltah "
					+ "inner join LONG_TERM_ASSETS lta on ltah.LONG_TERM_ASSET_ID= lta.LONG_TERM_ASSET_ID "
					//+ "and lta.LOTA_TYPE =1  "
					+ "inner join CAT_ASSET_CODEs cac on cac.CAT_ASSET_CODE_ID = lta.CAT_ASSET_CODE_ID "
					+ " left join SYS_GROUP s on ltah.USE_GROUP_ID = s.group_id and s.status=1 "
					+ " left join SYS_GROUP s2 on ltah.GROUP_ID = s2.group_id and s2.status=1 "
					+ " where ltah.LONG_TERM_ASSET_HISTORY_ID= :historyId"	;			
			SQLQuery query=session.createSQLQuery(sql);
			query.setParameter("historyId", historyId);
		
			query.setResultTransformer(Transformers.aliasToBean(LongTermAssetHistoryReportS23Dto.class));
			query.addScalar("useGroupName",StandardBasicTypes.STRING);
			query.addScalar("groupAddress",StandardBasicTypes.STRING);
			query.addScalar("groupName",StandardBasicTypes.STRING);
			query.addScalar("lotaCode",StandardBasicTypes.STRING);
			query.addScalar("handoverCode",StandardBasicTypes.STRING);
			query.addScalar("handoverDate",StandardBasicTypes.DATE);
			query.addScalar("depreciationStartDate",StandardBasicTypes.DATE);
			query.addScalar("caacName",StandardBasicTypes.STRING);
			query.addScalar("caacFullCode",StandardBasicTypes.STRING);
			query.addScalar("nationalName",StandardBasicTypes.STRING);
			query.addScalar("madeYear",StandardBasicTypes.LONG);
			query.addScalar("description",StandardBasicTypes.STRING);
			@SuppressWarnings("rawtypes")
			List lst=query.list();
			if(lst.isEmpty()){
				throw new BusinessException("error.not.has.handoverOfAsset");
			}else 
				return (LongTermAssetHistoryReportS23Dto)lst.get(0);
			
		}
	
		@SuppressWarnings("unchecked")
		public List<ReportIncreaseDecreaseDto> getDataReportTscdOrganizeBase(AssetReportS21SearchDto dto){
			Session session=currentSession();
//			StoredProcedureQuery storedProcedureQuery=session.createStoredProcedureCall("");
	        String sql = "select "
	        		+ " t.STT as stt"
	        		+ " , t.INCREASE_ORIGINAL_PRICE as increaseOriginalPrice"
	        		+ " , t.DECREASE_ORIGINAL_PRICE as decreaseOriginalPrice"
	        		+ " , t.DEPRECIATED_VALUE as depreciatedValue"
	        		
					+ " , t.START_ORIGINAL_PRICE as startOriginalPrice"
					+ " , t.START_RESIDUAL_PRICE as startResidualPrice"
					+ " , t.START_DEPRECIATED_VALUE as startDepreciatedValue"
					+ " , t.END_ORIGINAL_PRICE as endOriginalPrice"
					+ " , t.END_RESIDUAL_PRICE as endResidualPrice"
					+ " , t.END_DEPRECIATED_VALUE as endDepreciatedValue"
					
					+ " , t.START_ORIGINAL_PRICE_SYNC as startOriginalPriceSync"
					+ " , t.START_RESIDUAL_PRICE_SYNC as startResidualPriceSync"
					+ " , t.START_DEPRECIATED_VALUE_SYNC as startDepreciatedValueSync"
					+ " , t.END_ORIGINAL_PRICE_SYNC as endOriginalPriceSync"
					+ " , t.END_RESIDUAL_PRICE_SYNC as endResidualPriceSync"
					+ " , t.END_DEPRECIATED_VALUE_SYNC as endDepreciatedValueSync"
					
					+ " , t.START_ORIGINAL_PRICE_TOTAL as startOriginalPriceTotal"
					+ " , t.START_RESIDUAL_PRICE_TOTAL as startResidualPriceTotal"
					+ " , t.START_DEPRECIATED_VALUE_TOTAL as startDepreciatedValueTotal"
					+ " , t.END_ORIGINAL_PRICE_TOTAL as endOriginalPriceTotal"
					+ " , t.END_RESIDUAL_PRICE_TOTAL as endResidualPriceTotal"
					+ " , t.END_DEPRECIATED_VALUE_TOTAL as endDepreciatedValueTotal"
					
					+ " , t.CAAC_NAME as caacName"
					+ " , t.CAAC_LEVEL as caacLevel"
					+ " , t.CODE as code "
					+ " ,t. DEPRECIATION_TIME as depreciationTime"
	        		
	        		+ " from table(PKG_LONG_TERM_ASSET_RP_VALUE.EXPORT_REPORT_TS_ERP_ORGANI(?,?,?)) t";
	        		
	        SQLQuery query=session.createSQLQuery(sql);
	        query.setLong(0,dto.getGroupId());
	        query.setDate(1,new java.sql.Date(dto.getFromDate().getTime())  );
	        query.setDate(2,new java.sql.Date(dto.getToDate().getTime())  );
//	         query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	         query.setResultTransformer(Transformers.aliasToBean(ReportIncreaseDecreaseDto.class));
	         
	         query.addScalar("stt",StandardBasicTypes.STRING);
	         query.addScalar("increaseOriginalPrice",StandardBasicTypes.LONG);
	         query.addScalar("decreaseOriginalPrice",StandardBasicTypes.LONG);
	         query.addScalar("depreciatedValue",StandardBasicTypes.LONG);
	         
	         query.addScalar("startOriginalPrice",StandardBasicTypes.LONG);
	         query.addScalar("startResidualPrice",StandardBasicTypes.LONG);
	         query.addScalar("startDepreciatedValue",StandardBasicTypes.LONG);
	         query.addScalar("endOriginalPrice",StandardBasicTypes.LONG);
	         query.addScalar("endResidualPrice",StandardBasicTypes.LONG);
	         query.addScalar("endDepreciatedValue",StandardBasicTypes.LONG);
	         
	         query.addScalar("startOriginalPriceSync",StandardBasicTypes.LONG);
	         query.addScalar("startResidualPriceSync",StandardBasicTypes.LONG);
	         query.addScalar("startDepreciatedValueSync",StandardBasicTypes.LONG);
	         query.addScalar("endOriginalPriceSync",StandardBasicTypes.LONG);
	         query.addScalar("endResidualPriceSync",StandardBasicTypes.LONG);
	         query.addScalar("endDepreciatedValueSync",StandardBasicTypes.LONG);
	         
	         query.addScalar("startOriginalPriceTotal",StandardBasicTypes.LONG);
	         query.addScalar("startResidualPriceTotal",StandardBasicTypes.LONG);
	         query.addScalar("startDepreciatedValueTotal",StandardBasicTypes.LONG);
	         query.addScalar("endOriginalPriceTotal",StandardBasicTypes.LONG);
	         query.addScalar("endResidualPriceTotal",StandardBasicTypes.LONG);
	         query.addScalar("endDepreciatedValueTotal",StandardBasicTypes.LONG);
	         
	         query.addScalar("caacName",StandardBasicTypes.STRING);
	         query.addScalar("caacLevel",StandardBasicTypes.LONG);
	         query.addScalar("code",StandardBasicTypes.STRING);
	         query.addScalar("depreciationTime",StandardBasicTypes.LONG);
	         List data = query.list();
	         return data;
		}
		
		@SuppressWarnings("unchecked")
		public List<ReportIncreaseDecreaseDto> exportAssetAllDonVi(AssetReportS21SearchDto dto){
			Session session=currentSession();
//			StoredProcedureQuery storedProcedureQuery=session.createStoredProcedureCall("");
	        String sql = "select "
	        		+ " t.STT as stt"
	        		+ " , t.INCREASE_ORIGINAL_PRICE as increaseOriginalPrice"
	        		+ " , t.DECREASE_ORIGINAL_PRICE as decreaseOriginalPrice"
	        		+ " , t.DEPRECIATED_VALUE as depreciatedValue"
	        		
					+ " , t.START_ORIGINAL_PRICE as startOriginalPrice"
					+ " , t.START_RESIDUAL_PRICE as startResidualPrice"
					+ " , t.START_DEPRECIATED_VALUE as startDepreciatedValue"
					+ " , t.END_ORIGINAL_PRICE as endOriginalPrice"
					+ " , t.END_RESIDUAL_PRICE as endResidualPrice"
					+ " , t.END_DEPRECIATED_VALUE as endDepreciatedValue"
					
					+ " , t.START_ORIGINAL_PRICE_SYNC as startOriginalPriceSync"
					+ " , t.START_RESIDUAL_PRICE_SYNC as startResidualPriceSync"
					+ " , t.START_DEPRECIATED_VALUE_SYNC as startDepreciatedValueSync"
					+ " , t.END_ORIGINAL_PRICE_SYNC as endOriginalPriceSync"
					+ " , t.END_RESIDUAL_PRICE_SYNC as endResidualPriceSync"
					+ " , t.END_DEPRECIATED_VALUE_SYNC as endDepreciatedValueSync"
					
					+ " , t.START_ORIGINAL_PRICE_TOTAL as startOriginalPriceTotal"
					+ " , t.START_RESIDUAL_PRICE_TOTAL as startResidualPriceTotal"
					+ " , t.START_DEPRECIATED_VALUE_TOTAL as startDepreciatedValueTotal"
					+ " , t.END_ORIGINAL_PRICE_TOTAL as endOriginalPriceTotal"
					+ " , t.END_RESIDUAL_PRICE_TOTAL as endResidualPriceTotal"
					+ " , t.END_DEPRECIATED_VALUE_TOTAL as endDepreciatedValueTotal"
					
					+ " , t.CAAC_NAME as caacName"
					+ " , t.CAAC_LEVEL as caacLevel"
					+ " , t.CODE as code,"
					
					+ " t.group_name as groupName, "
					+ " t.group_code as groupCode, "
					+ " t.group_id as groupId "
	        		+ " from table(PKG_LONG_TERM_ASSET_RP_VALUE.EXPORT_REPORT_TS_ERP_YEARLY(?,?,?)) t";
	        		
	        SQLQuery query=session.createSQLQuery(sql);	        
	        query.setDate(0,new java.sql.Date(dto.getFromDate().getTime())  );
	        query.setDate(1,new java.sql.Date(dto.getToDate().getTime())  );
	        query.setLong(2,dto.getLotaType() );
//	         query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	         query.setResultTransformer(Transformers.aliasToBean(ReportIncreaseDecreaseDto.class));
	         
	         query.addScalar("stt",StandardBasicTypes.STRING);
	         query.addScalar("increaseOriginalPrice",StandardBasicTypes.LONG);
	         query.addScalar("decreaseOriginalPrice",StandardBasicTypes.LONG);
	         query.addScalar("depreciatedValue",StandardBasicTypes.LONG);
	         
	         query.addScalar("startOriginalPrice",StandardBasicTypes.LONG);
	         query.addScalar("startResidualPrice",StandardBasicTypes.LONG);
	         query.addScalar("startDepreciatedValue",StandardBasicTypes.LONG);
	         query.addScalar("endOriginalPrice",StandardBasicTypes.LONG);
	         query.addScalar("endResidualPrice",StandardBasicTypes.LONG);
	         query.addScalar("endDepreciatedValue",StandardBasicTypes.LONG);
	         
	         query.addScalar("startOriginalPriceSync",StandardBasicTypes.LONG);
	         query.addScalar("startResidualPriceSync",StandardBasicTypes.LONG);
	         query.addScalar("startDepreciatedValueSync",StandardBasicTypes.LONG);
	         query.addScalar("endOriginalPriceSync",StandardBasicTypes.LONG);
	         query.addScalar("endResidualPriceSync",StandardBasicTypes.LONG);
	         query.addScalar("endDepreciatedValueSync",StandardBasicTypes.LONG);
	         
	         query.addScalar("startOriginalPriceTotal",StandardBasicTypes.LONG);
	         query.addScalar("startResidualPriceTotal",StandardBasicTypes.LONG);
	         query.addScalar("startDepreciatedValueTotal",StandardBasicTypes.LONG);
	         query.addScalar("endOriginalPriceTotal",StandardBasicTypes.LONG);
	         query.addScalar("endResidualPriceTotal",StandardBasicTypes.LONG);
	         query.addScalar("endDepreciatedValueTotal",StandardBasicTypes.LONG);
	         
	         query.addScalar("caacName",StandardBasicTypes.STRING);
	         query.addScalar("caacLevel",StandardBasicTypes.LONG);
	         query.addScalar("code",StandardBasicTypes.STRING);
	         
	         query.addScalar("groupName",StandardBasicTypes.STRING);
	         query.addScalar("groupCode",StandardBasicTypes.STRING);
	         query.addScalar("groupId",StandardBasicTypes.LONG);
	         List data = query.list();
	         return data;
		}
	
}
