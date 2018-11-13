package com.viettel.asset.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.google.common.base.Joiner;
import com.viettel.asset.bo.LongTermAsset;
import com.viettel.asset.bo.MerHandOverInfo;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.asset.dto.CatAssetCodeDto;
import com.viettel.asset.dto.ConstrConstructionDto;
import com.viettel.asset.dto.LongTermAssetDto;
import com.viettel.asset.dto.LongTermAssetEntityDto;
import com.viettel.asset.dto.MerEntityDto;
import com.viettel.asset.dto.MerHandOverEntityDto;
import com.viettel.asset.dto.MerHandOverInfoDto;
import com.viettel.asset.dto.search.AssetHandOverDto;
import com.viettel.asset.dto.search.AssetMerHandoverEntityDto;

@Repository("merHandOverInfoDao")
public class MerHandOverInfoDao extends HibernateDao<MerHandOverInfo, Long> {

	@Deprecated
	/**
	 * Su dung code cu theo itsol, hien tai khong dung chuc nang nay
	 * @param obj
	 * @param size
	 * @return
	 */
	public List<MerHandOverInfoDto> getMerHandOverInfoAutoComplete(MerHandOverInfoDto obj, Boolean size) {
		String sql = "SELECT HAND_OVER_ID handOverId"	
				+" ,CODE code"		
				+" FROM MER_HAND_OVER_INFO"
				+" WHERE IS_ACTIVE = 1 AND TYPE = 1 AND CONSTRUCTION_ID IS NULL";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(" AND ROWNUM <= 20");
		stringBuilder.append(" AND upper(CODE) LIKE upper(:code)");				
		stringBuilder.append(" ORDER BY CODE");
		
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("code", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverInfoDto.class));
	
		query.setParameter("code", "%" + obj.getCode() + "%");
		
		return query.list();
	}

	/**
	 * Lấy thông tin tài sản để hình thành không qua xây lắp
	 * @param handOverId: id biên bản bàn giao (bàn giao hình thành mới)
	 * @return
	 */
	public MerHandOverInfoDto getMerHandOverbyId(Long handOverId) {
		String sql = "SELECT MHO.HAND_OVER_ID handOverId"
				+ " ,MHO.CODE code"	
				+ " ,MHO.RECEIVER_NAME receiverName"
				+ "	,MHO.HAND_OVER_DATE handOverDate"
				+ " ,CW.NAME catWarehouseName "
				+ " ,SG.NAME sysGroupName"
				+ ",sg.group_id useGroupId "		
				+ " FROM MER_HAND_OVER_INFO MHO"
				+ " LEFT JOIN WARE_EXP_NOTE WEN ON MHO.DELIVERY_NOTE_ID = WEN.DELIVERY_NOTE_ID"
				+ " LEFT JOIN CAT_WAREHOUSE CW ON WEN.SOURCE_WH_ID = CW.WAREHOUSE_ID"
				+ " LEFT JOIN SYS_GROUP SG ON MHO.DELIVERY_GROUP_ID = SG.GROUP_ID"
				+ " WHERE MHO.HAND_OVER_ID = :id AND MHO.CONSTRUCTION_ID IS NULL";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("catWarehouseName", new StringType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("receiverName", new StringType());
		query.addScalar("handOverDate", new DateType());
		query.addScalar("useGroupId", new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverInfoDto.class));
	
		query.setParameter("id", handOverId);
		
		return (MerHandOverInfoDto) query.uniqueResult();
	}
	
	/**
	 * Lấy thông tin biên bản để nâng cấp
	 * @param handOverId: id biên bản nang cấp
	 * @return
	 */
	public MerHandOverInfoDto loadMerHandOverNotByConstrForUpgrade(Long handOverId) {
		String sql = "SELECT MHO.HAND_OVER_ID handOverId"
				+ " ,MHO.CODE code"	
				+ " ,MHO.RECEIVER_NAME receiverName"
				+ "	,MHO.HAND_OVER_DATE handOverDate"
				+ " ,CW.NAME catWarehouseName "
				+ " ,SG.NAME sysGroupName"//là useGroupName
				+ ",sg.group_id useGroupId"
				+ " , lta.LONG_TERM_ASSET_ID longTermAssetId"
				+ ", lta.LOTA_CODE lotaCode"
				+ ",lta.ORIGINAL_PRICE as originalPrice"
				+ ",lta.IS_SENT_ERP as isSentErp"
				+ ",MHO.is_To_Asset as isToAsset"		
				+ " FROM MER_HAND_OVER_INFO MHO"
				+ " LEFT JOIN WARE_EXP_NOTE WEN ON MHO.DELIVERY_NOTE_ID = WEN.DELIVERY_NOTE_ID"
				+ " LEFT JOIN CAT_WAREHOUSE CW ON WEN.SOURCE_WH_ID = CW.WAREHOUSE_ID"
				+ " LEFT JOIN SYS_GROUP SG ON MHO.DELIVERY_GROUP_ID = SG.GROUP_ID"
				//+ "	LEFT JOIN CONSTRUCTION_ACCEPTANCE ca on ca.IS_ACTIVE=1 and ca.status_ca=2 and ca.CONSTRUCT_ID=MHO.CONSTRUCTION_ID"
				+ " LEFT JOIN LONG_TERM_ASSETS lta on lta.is_active=1  and lta.CONSTRUCT_ID=MHO.CONSTRUCTION_ID and lta.VOUCHER_TYPE="+LongTermAsset.Constants.VOUCHER_TYPE_DTMS //1 là tài sản hình thành qua xây lắp
				+ " WHERE MHO.HAND_OVER_ID = :id ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("catWarehouseName", new StringType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("receiverName", new StringType());
		query.addScalar("handOverDate", new DateType());
		query.addScalar("useGroupId", new LongType());
		query.addScalar("longTermAssetId", new LongType());
		query.addScalar("lotaCode", new StringType());
		query.addScalar("originalPrice", new LongType());
		query.addScalar("isSentErp", new LongType());
		query.addScalar("isToAsset", new LongType());
		
		
		
		
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverInfoDto.class));
	
		query.setParameter("id", handOverId);
		
		return (MerHandOverInfoDto) query.uniqueResult();
	}
	
	/**
	 * Lay merrhandOver info tu handoverCode
	 * @param handoverCode: code cua MER_HAND_OVER_INFO
	 * @return
	 */
	public MerHandOverInfoDto getMerHandOverbyCode(String handoverCode) {
		String sql = "SELECT MHO.HAND_OVER_ID handOverId"
				+ " ,MHO.CODE code"	
				+ " ,MHO.RECEIVER_NAME receiverName"
				+ "	,MHO.HAND_OVER_DATE handOverDate"
				+ " ,CW.NAME catWarehouseName "
				+ " ,SG.NAME sysGroupName"
				+ " , SG.group_id useGroupId"		
				+ " FROM MER_HAND_OVER_INFO MHO"
				+ " INNER JOIN WARE_EXP_NOTE WEN ON MHO.DELIVERY_NOTE_ID = WEN.DELIVERY_NOTE_ID"
				+ " INNER JOIN CAT_WAREHOUSE CW ON WEN.SOURCE_WH_ID = CW.WAREHOUSE_ID"
				+ " INNER JOIN SYS_GROUP SG ON MHO.GROUP_ID = SG.GROUP_ID"
				+ " WHERE MHO.CODE = :handoverCode AND MHO.CONSTRUCTION_ID IS NULL";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("catWarehouseName", new StringType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("receiverName", new StringType());
		query.addScalar("handOverDate", new DateType());
		query.addScalar("useGroupId", new LongType());		
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverInfoDto.class));
	
		query.setParameter("handoverCode", handoverCode);
		
		return (MerHandOverInfoDto) query.uniqueResult();
	}

	/**
	 * Lay thong tin vat tu trong BBBG MERRHAND_OVERINFO, chi lay nhung vat tu chua hinh thanh tai san co dinh
	 * @param id: id bien ban ban giao
	 * @return
	 */
	public List<MerHandOverEntityDto> getMerHandOverEntitybyId(Long merHandOverId) {
		String sql = "SELECT MHE.MER_ENTITY_ID merEntityId"	
				+ " ,CM.NAME name"
				+ " ,CM.CODE code "
				+ " ,CM.IS_DEVICE isDevice"
				+ " ,ME.SERIAL_NUMBER serialNumber"
				+ " ,NVL(MHE.COUNT,0) count"
				+ " ,MHE.HAND_OVER_ID handOverId"
				+ " ,NVL(me.ORIGINAL_PRICE,0) originalPrice"		
				+ " FROM MER_HAND_OVER_ENTITY MHE"
				+ " JOIN MER_HAND_OVER_INFO MHO ON MHO.HAND_OVER_ID = MHE.HAND_OVER_ID"
				+ " INNER JOIN MER_ENTITY ME ON MHE.MER_ENTITY_ID = ME.MER_ENTITY_ID"
				+ " INNER JOIN CAT_MERCHANDISE CM ON ME.MER_ID = CM.MERCHANDISE_ID"
				//+ " INNER JOIN ware_exp_note_mer_entity wee on wee.MER_ENTITY_ID = MHE.mer_entity_id and wee.DELIVERY_NOTE_ID = MHO.DELIVERY_NOTE_ID"
				+ " WHERE MHE.HAND_OVER_ID = :id AND ME.LONG_TERM_ASSET_ID IS NULL";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("merEntityId", new LongType());
		query.addScalar("originalPrice", new LongType());
		query.addScalar("isDevice", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("serialNumber", new StringType());
		query.addScalar("count", new DoubleType());
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverEntityDto.class));
	
		query.setParameter("id", merHandOverId);
		return query.list();
	}
	
	/*
	 * Lay cac tai san nam trong bien ban ban giao
	 */
	public List<MerHandOverEntityDto> getMerHandOverEntitybyHandOverId(Long id) {
		String sql = "SELECT MHE.MER_ENTITY_ID merEntityId"	
				+ " ,CM.NAME name"
				+ " ,CM.CODE code "
				+ " ,CM.IS_DEVICE isDevice"
				+ " ,ME.SERIAL_NUMBER serialNumber"
				+ " ,NVL(MHE.COUNT,0) count"
				+ " ,MHE.HAND_OVER_ID handOverId"
				+ " ,NVL(me.ORIGINAL_PRICE,0) originalPrice"		
				+ " FROM MER_HAND_OVER_ENTITY MHE"
				+ " JOIN MER_HAND_OVER_INFO MHO ON MHO.HAND_OVER_ID = MHE.HAND_OVER_ID"
				+ " INNER JOIN MER_ENTITY ME ON MHE.MER_ENTITY_ID = ME.MER_ENTITY_ID"
				+ " INNER JOIN CAT_MERCHANDISE CM ON ME.MER_ID = CM.MERCHANDISE_ID"
				//+ " INNER JOIN ware_exp_note_mer_entity wee on wee.MER_ENTITY_ID = MHE.mer_entity_id and wee.DELIVERY_NOTE_ID = MHO.DELIVERY_NOTE_ID"
				+ " WHERE MHE.HAND_OVER_ID = :id ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("merEntityId", new LongType());
		query.addScalar("originalPrice", new LongType());
		query.addScalar("isDevice", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("serialNumber", new StringType());
		query.addScalar("count", new DoubleType());
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverEntityDto.class));
	
		query.setParameter("id", id);
		return query.list();
	}
	
	
	
	/**
	 * Lấy danh sách vật tư thiết bị để hình thành tài sản tử biên bản bàn giao từ phiếu xuất
	 * @param id:id bien ban ban giao merhandOverId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AssetMerHandoverEntityDto> getMerHandOverEntitybyHandOverIdForLta(Long id) {
		String sql = "SELECT MHE.MER_ENTITY_ID merEntityId"	
				+ " ,CM.NAME name"
				+ " ,CM.CODE code "
				+ " ,CM.IS_DEVICE isDevice"
				+ " ,ME.SERIAL_NUMBER serialNumber"
				+ " ,NVL(MHE.COUNT,0) count"
				+ " ,MHE.HAND_OVER_ID handOverId"
				+ " ,NVL(ME.ORIGINAL_PRICE,0) originalPrice"
				
				+ " ,ME.MADE_YEAR  madeYear"
				+ " ,CM.DESCRIPTION description"
				+ " , ME.COMPANY_ID companyId"
				+ " , PC.NAME AS companyName"
				+ " , PN.NATIONAL_ID nationalId"
				+ " , PN.NAME AS nationalName"
				+ " FROM MER_HAND_OVER_ENTITY MHE"
				+ " JOIN MER_HAND_OVER_INFO MHO ON MHO.HAND_OVER_ID = MHE.HAND_OVER_ID"				
				+ " INNER JOIN MER_ENTITY ME ON MHE.MER_ENTITY_ID = ME.MER_ENTITY_ID"							
				+ " INNER JOIN CAT_MERCHANDISE CM ON ME.MER_ID = CM.MERCHANDISE_ID"
				+" LEFT JOIN  PRODUCT_NATIONAL PN ON ME.NATIONAL_ID = PN.NATIONAL_ID"
				+ " LEFT JOIN PRODUCT_COMPANY PC ON PC.COMPANY_ID = ME.COMPANY_ID"
				//+ " INNER JOIN ware_exp_note_mer_entity wee on wee.MER_ENTITY_ID = MHE.mer_entity_id and wee.DELIVERY_NOTE_ID = MHO.DELIVERY_NOTE_ID"
				+ " WHERE MHE.HAND_OVER_ID = :handoverId AND ME.LONG_TERM_ASSET_ID IS NULL";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("merEntityId", new LongType());
		query.addScalar("originalPrice", new LongType());
		query.addScalar("isDevice", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("serialNumber", new StringType());
		query.addScalar("count", new DoubleType());
		query.addScalar("madeYear", StandardBasicTypes.LONG);
		query.addScalar("description", StandardBasicTypes.STRING);
		query.addScalar("nationalId", StandardBasicTypes.LONG);
		query.addScalar("nationalName", StandardBasicTypes.STRING);
		query.addScalar("companyId", StandardBasicTypes.LONG);
		query.addScalar("companyName", StandardBasicTypes.STRING);
		query.setResultTransformer(Transformers.aliasToBean(AssetMerHandoverEntityDto.class));
	
		query.setParameter("handoverId", id);
		return query.list();
	}
	
	/**
	 * Lấy thông tin tài sản trong biên bản bàn giao (MerHandOverInfo) từ phiếu xuất
	 * @param handOverCode
	 * @return
	 */
	public List<AssetMerHandoverEntityDto> getMerHandOverEntityByHandOverCode(String handOverCode) {
		String sql = "SELECT MHE.MER_ENTITY_ID merEntityId"	
				+ " ,CM.NAME name"
				+ " ,CM.CODE code "
				+ " ,CM.IS_DEVICE isDevice"
				+ " ,decode(substr(ME.serial_number, 1, 1), '-', NULL, ME.serial_number) serialNumber"
				+ " ,NVL(MHE.COUNT,0) count"
				+ " ,MHE.HAND_OVER_ID handOverId"
				//+ " ,NVL(wee.ORIGINAL_PRICE,0) originalPrice"
				
				+ " ,ME.MADE_YEAR  madeYear"
				+ " ,CM.DESCRIPTION description"
				+ " , ME.COMPANY_ID companyId"
				+ " , PC.NAME AS companyName"
				+ " , PN.NATIONAL_ID nationalId"
				+ " , PN.NAME AS nationalName"
				+ " , me.PART_NUMBER partNumber"
				+ " ,CN.NAME unitName"
				+" ,ME.EXPIRED_WARRANTY_DATE expiredWarrantyDate"
				+ " ,decode(MHE.status_id,3,'Hỏng','Tốt') as statusName"
				+ ",e.station_code as stationCode"
				+ " FROM MER_HAND_OVER_ENTITY MHE"
				+ " JOIN MER_HAND_OVER_INFO MHO ON MHO.HAND_OVER_ID = MHE.HAND_OVER_ID"
				+" left outer join cat_station e on MHE.station_id=e.id "
				+ " INNER JOIN MER_ENTITY ME ON MHE.MER_ENTITY_ID = ME.MER_ENTITY_ID"							
				+ " INNER JOIN CAT_MERCHANDISE CM ON ME.MER_ID = CM.MERCHANDISE_ID"
				+" LEFT JOIN  PRODUCT_NATIONAL PN ON ME.NATIONAL_ID = PN.NATIONAL_ID"
				+ " LEFT JOIN PRODUCT_COMPANY PC ON PC.COMPANY_ID = ME.COMPANY_ID"
				+" LEFT JOIN CAT_UNIT CN on CN.UNIT_ID=CM.UNIT_ID"
				//+ " INNER JOIN ware_exp_note_mer_entity wee on wee.MER_ENTITY_ID = MHE.mer_entity_id and wee.DELIVERY_NOTE_ID = MHO.DELIVERY_NOTE_ID"
				+ " WHERE MHO.CODE = :handOverCode ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("merEntityId", new LongType());
//		query.addScalar("originalPrice", new LongType());
		query.addScalar("isDevice", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("serialNumber", new StringType());
		query.addScalar("count", new DoubleType());
		query.addScalar("madeYear", StandardBasicTypes.LONG);
		query.addScalar("description", StandardBasicTypes.STRING);
		query.addScalar("nationalId", StandardBasicTypes.LONG);
		query.addScalar("nationalName", StandardBasicTypes.STRING);
		query.addScalar("companyId", StandardBasicTypes.LONG);
		query.addScalar("companyName", StandardBasicTypes.STRING);
		query.addScalar("partNumber", StandardBasicTypes.STRING);
		query.addScalar("unitName", StandardBasicTypes.STRING);
		query.addScalar("expiredWarrantyDate", StandardBasicTypes.DATE);
		query.addScalar("statusName", StandardBasicTypes.STRING);
		query.addScalar("stationCode", StandardBasicTypes.STRING);
		
		query.setResultTransformer(Transformers.aliasToBean(AssetMerHandoverEntityDto.class));
	
		query.setParameter("handOverCode", handOverCode);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<CatAssetCodeDto> getCaacName(AutocompleteSearchDto obj) {
		String sql = "SELECT CAT_ASSET_CODE_ID catAssetCodeId"	
				+ " ,CAAC_FULL_CODE caacFullCode"
				+ " ,CAAC_NAME caacName "
				+ " ,CAAC_CODE caacCode"				
				+ " FROM CAT_ASSET_CODES "
				+ " WHERE IS_ACTIVE = 1 AND CAAC_LEVEL = 4 AND ROWNUM <= 20";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		stringBuilder.append(StringUtils.isNotEmpty(obj.getKeySearch()) ? " AND upper(CAAC_NAME) LIKE upper(:keySearch)" : "");
		stringBuilder.append(StringUtils.isNotEmpty(obj.getValue()) ? " AND upper(CAAC_FULL_CODE) LIKE upper(:value)" : "");
		stringBuilder.append(" ORDER BY CAAC_FULL_CODE");	
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("catAssetCodeId", new LongType());
		query.addScalar("caacFullCode", new StringType());
		query.addScalar("caacCode", new StringType());
		query.addScalar("caacName", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(CatAssetCodeDto.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch() + "%");
		}
		if (StringUtils.isNotEmpty(obj.getValue())) {
			query.setParameter("value", "%" + obj.getValue() + "%");
		}
		return query.list();
	}

	public Object getMerHandOverEntityUpdate(MerHandOverEntityDto obj) {
		String sql = "SELECT MHE.MER_ENTITY_ID merEntityId"	
				+ " ,CM.NAME name"
				+ " ,CM.CODE code "
				+ " ,CM.IS_DEVICE isDevice"
				+ " ,ME.SERIAL_NUMBER serialNumber"
				+ "	,ME.LONG_TERM_ASSET_ID longTermAssetId"
				+ " ,NVL(MHE.COUNT,0) count"
				+ " ,MHE.HAND_OVER_ID handOverId"
				+ " ,NVL(ME.ORIGINAL_PRICE,0) originalPrice"		
				+ " FROM MER_HAND_OVER_ENTITY MHE"
				+ " JOIN MER_HAND_OVER_INFO MHO ON MHO.HAND_OVER_ID = MHE.HAND_OVER_ID"
				+ " INNER JOIN MER_ENTITY ME ON MHE.MER_ENTITY_ID = ME.MER_ENTITY_ID"
				+ " INNER JOIN CAT_MERCHANDISE CM ON ME.MER_ID = CM.MERCHANDISE_ID"
				//+ " INNER JOIN ware_exp_note_mer_entity wee on wee.MER_ENTITY_ID = MHE.mer_entity_id and wee.DELIVERY_NOTE_ID = MHO.DELIVERY_NOTE_ID"
				+ " WHERE MHE.HAND_OVER_ID = :id AND (ME.LONG_TERM_ASSET_ID IS NULL OR ME.LONG_TERM_ASSET_ID = :longtermassetId)";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("longTermAssetId", new LongType());
		query.addScalar("merEntityId", new LongType());
		query.addScalar("originalPrice", new LongType());
		query.addScalar("isDevice", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("serialNumber", new StringType());
		query.addScalar("count", new DoubleType());
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverEntityDto.class));
	
		query.setParameter("id", obj.getHandOverId());
		query.setParameter("longtermassetId", obj.getLongTermAssetId());
		return query.list();
	}

	
	public Object getMerHandOverEntityUpdateByHandOverId(MerHandOverEntityDto obj) {
		String sql = "SELECT MHE.MER_ENTITY_ID merEntityId"	
				+ " ,CM.NAME name"
				+ " ,CM.CODE code "
				+ " ,CM.IS_DEVICE isDevice"
				+ " ,ME.SERIAL_NUMBER serialNumber"
				+ "	,ME.LONG_TERM_ASSET_ID longTermAssetId"
				+ " ,NVL(MHE.COUNT,0) count"
				+ " ,MHE.HAND_OVER_ID handOverId"
				+ " ,NVL(me.ORIGINAL_PRICE,0) originalPrice"		
				+ " FROM MER_HAND_OVER_ENTITY MHE"
				+ " JOIN MER_HAND_OVER_INFO MHO ON MHO.HAND_OVER_ID = MHE.HAND_OVER_ID"
				+ " INNER JOIN MER_ENTITY ME ON MHE.MER_ENTITY_ID = ME.MER_ENTITY_ID"
				+ " INNER JOIN CAT_MERCHANDISE CM ON ME.MER_ID = CM.MERCHANDISE_ID"
				//+ " INNER JOIN ware_exp_note_mer_entity wee on wee.MER_ENTITY_ID = MHE.mer_entity_id and wee.DELIVERY_NOTE_ID = MHO.DELIVERY_NOTE_ID"
				+ " WHERE MHE.HAND_OVER_ID = :handOverId ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("longTermAssetId", new LongType());
		query.addScalar("merEntityId", new LongType());
		query.addScalar("originalPrice", new LongType());
		query.addScalar("isDevice", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("serialNumber", new StringType());
		query.addScalar("count", new DoubleType());
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverEntityDto.class));
	
		query.setParameter("handOverId", obj.getHandOverId());
//		query.setParameter("longtermassetId", obj.getLongTermAssetId());
		return query.list();
	}

	@Deprecated
	public List<MerEntityDto> getMerHandOverEntityOld(MerHandOverEntityDto merHandOverEntity) {
		String sql = "SELECT MER_ENTITY_ID merEntityId"					
				+ " FROM MER_ENTITY "															
				+ " WHERE LONG_TERM_ASSET_ID = :longtermassetId";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("merEntityId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(MerEntityDto.class));
		
		query.setParameter("longtermassetId", merHandOverEntity.getLongTermAssetId());
		return  query.list();
	}

	public List<LongTermAssetEntityDto> getLongTermAssetEntity(MerHandOverEntityDto merHandOverEntity) {
		String sql = "SELECT LONG_TERM_ASSET_ENTITY_ID longTermAssetEntityId"					
				+ " FROM LONG_TERM_ASSET_ENTITIES "															
				+ " WHERE LONG_TERM_ASSET_ID = :longtermassetId";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("longTermAssetEntityId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetEntityDto.class));
		
		query.setParameter("longtermassetId", merHandOverEntity.getLongTermAssetId());
		return  query.list();
	}

	/*
	 * Lay merHandOve cho update , su dung cho chuc nang cu
	 */
	@Deprecated
	public Object getMerHandOverbyUpdate(MerHandOverInfoDto merhandinfo) {
		String sql = "SELECT MHO.HAND_OVER_ID handOverId"
				+ " ,MHO.CODE code"	
				+ " ,MHO.RECEIVER_NAME receiverName"
				+ " ,CW.NAME catWarehouseName "
				+ " ,LTA.GROUP_ID idsysGroup"
				+ " ,SG.NAME sysGroupName"
				+ " ,SG2.NAME namesysGroup"
				+ " ,MHO.HAND_OVER_DATE handOverDate"
				+ " ,LTA.CREATED_SOURCE createdSource"
				+ " ,LTA.VOUCHER_TYPE voucherType"
				+ " ,LTA.CAT_ASSET_CODE_ID catAssetCodeId"
				+ "	,UAD.DOCUMENT_NAME attachName"
				+ " FROM MER_HAND_OVER_INFO MHO"
				+ " INNER JOIN WARE_EXP_NOTE WEN ON MHO.DELIVERY_NOTE_ID = WEN.DELIVERY_NOTE_ID"
				+ " INNER JOIN CAT_WAREHOUSE CW ON WEN.SOURCE_WH_ID = CW.WAREHOUSE_ID"
				+ " LEFT JOIN SYS_GROUP SG ON MHO.GROUP_ID = SG.GROUP_ID"
				+ " LEFT JOIN LONG_TERM_ASSETS LTA ON MHO.CODE = LTA.HANDOVER_CODE"
				+ " LEFT JOIN SYS_GROUP SG2 ON LTA.GROUP_ID = SG2.GROUP_ID"
				+ " LEFT JOIN UTIL_ATTACHED_DOCUMENTS UAD ON LTA.LONG_TERM_ASSET_ID = UAD.PARENT_ID AND UAD.TYPE = 1611"
				+ " WHERE MHO.HAND_OVER_ID = :handoverid AND LTA.LONG_TERM_ASSET_ID = :longtermassetid AND MHO.CONSTRUCTION_ID IS NULL";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("handOverId", new LongType());
		query.addScalar("catAssetCodeId", new LongType());
		query.addScalar("voucherType", new LongType());
		query.addScalar("idsysGroup", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("handOverDate", new DateType());
		query.addScalar("catWarehouseName", new StringType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("namesysGroup", new StringType());
		query.addScalar("createdSource", new StringType());
		query.addScalar("receiverName", new StringType());
		query.addScalar("attachName", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverInfoDto.class));
	
		query.setParameter("handoverid", merhandinfo.getHandOverId());
		query.setParameter("longtermassetid", merhandinfo.getLongTermAssetId());
		
		return (MerHandOverInfoDto) query.uniqueResult();
	}

	
	/**
	 * Lay thong tin groupId tu handOverId
	 * @param handOverId
	 * @return
	 */
	@Deprecated
	public MerHandOverInfoDto getGroupIdbyId(Long handOverId) {
		String sql = "SELECT SG.GROUP_ID groupId from MER_HAND_OVER_INFO mho"
				+ " JOIN SYS_GROUP SG ON MHO.GROUP_ID = SG.GROUP_ID"
				+ " WHERE mho.HAND_OVER_ID = :handOverId";
									
		StringBuilder stringBuilder = new StringBuilder(sql);
			
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("groupId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverInfoDto.class));
		
		query.setParameter("handOverId", handOverId);
		return  (MerHandOverInfoDto) query.uniqueResult();
	}

	public Object getGruopCaac(AutocompleteSearchDto obj) {
		String sql = "SELECT CAT_ASSET_CODE_ID catAssetCodeId"	
				+ " ,CAAC_LEVEL caacLevel"
				+ " ,CAAC_NAME caacName "
				+ " ,CAAC_CODE caacCode"				
				+ " FROM CAT_ASSET_CODES "
				+ " WHERE IS_ACTIVE = 1 AND CAAC_LEVEL = 1 AND ROWNUM <= 20";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		stringBuilder.append(StringUtils.isNotEmpty(obj.getKeySearch()) ? " AND upper(CAAC_CODE) LIKE upper(:keySearch)" : "");
		stringBuilder.append(" ORDER BY CAAC_LEVEL");	
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("catAssetCodeId", new LongType());
		query.addScalar("caacLevel", new LongType());
		query.addScalar("caacCode", new StringType());
		query.addScalar("caacName", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(CatAssetCodeDto.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch() + "%");
		}	
		return query.list();
	}

	public Object getCaacFullCodebyId(Long id) {
		String sql = "SELECT CAAC_FULL_CODE caacFullCode"				
				+ " FROM CAT_ASSET_CODES "
				+ " WHERE CAT_ASSET_CODE_ID = :catassetcodeid";
		
		StringBuilder stringBuilder = new StringBuilder(sql);		
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());			
		query.addScalar("caacFullCode", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(CatAssetCodeDto.class));
		
		query.setParameter("catassetcodeid", id);
	
		return query.uniqueResult();
	}
	
	
	/*
	 * Bổ sung status_ca=2
	 * Lay so luong bien ban ban giao khong qua xay lap chua hinh thanh tai san
	 */
	//Chuyển sang LongTermAssetDao để thống nhất 2 loại notify
	@Deprecated 
	public Long countNoteNotCreatedByBulding() throws Exception{
		String sql="Select count(MO.HAND_OVER_ID) from MER_HAND_OVER_INFO MO  inner join WARE_EXP_NOTE we on we.DELIVERY_NOTE_ID=MO.HAND_OVER_ID "
       +" inner join CAT_WAREHOUSE cwh  on we.SOURCE_WH_ID=cwh.WAREHOUSE_ID and (cwh.CODE not like '%THUHOI' and (cwh.VIRTUAL_TYPE <> 1 or cwh.VIRTUAL_TYPE is null)) "				
				+ " WHERE MO.IS_ACTIVE=1 and MO.IS_ACCEPTED=1 and MO.TYPE =1 and mo.status_ca=2 and mo.type_delivery_note =1 and MO.CONSTRUCTION_ID IS NULL AND (MO.IS_TO_ASSET is  null or MO.IS_TO_ASSET ='')";
		SQLQuery query = currentSession().createSQLQuery(sql);
		BigDecimal result=(BigDecimal)query.uniqueResult();
		return result.longValue();	
	}
	
	public AssetHandOverDto getMerHandOverInfoByCodeForView(String code){
		String sql=Joiner.on(" ").join("SELECT MO.HAND_OVER_DATE AS handoverDate"
				,",MO.DESCRIPTION AS REASON"
				,",MO.CODE AS handOverCode"
				,",MO.receiver_Name AS receiverName"
				,",MO.receiver_Position AS receiverPosition"
				,",MO.delivery_Name AS deliveryName"
				,",MO.delivery_Position AS deliveryPosition"
				,",MO.foundation AS foundation"
				,",MO.is_Accepted AS isAccepted"
				,",CMD.exp_Note_Code AS deliveryNoteCode"
				,",s.name as receiverGroupName"
				,",sg2.name as deliveryGroupName"
				,",MO.HAND_OVER_ID as id"
				,",sg2.title_name as deliveryGroupTitleName"
				, "FROM MER_HAND_OVER_INFO MO"
				, "LEFT JOIN SYS_GROUP S ON s.GROUP_ID=MO.GROUP_ID"
				, "LEFT JOIN SYS_GROUP sg2 on mo.DELIVERY_GROUP_ID=sg2.GROUP_ID"
				,"LEFT JOIN WARE_EXP_NOTE WE ON WE.DELIVERY_NOTE_ID=MO.DELIVERY_NOTE_ID"
				,"LEFT JOIN WARE_EXP_CMD CMD ON CMD.EXP_CMD_ID=WE.EXP_CMD_ID"
				,"WHERE MO.CODE =:code AND MO.IS_ACTIVE=1").toString();
		
		SQLQuery query=currentSession().createSQLQuery(sql);
		query.setParameter("code", code);
		query.setResultTransformer(Transformers.aliasToBean(AssetHandOverDto.class));
		query.addScalar("handoverDate",StandardBasicTypes.DATE);
		query.addScalar("reason",StandardBasicTypes.STRING);
		query.addScalar("receiverName",StandardBasicTypes.STRING);
		query.addScalar("receiverPosition",StandardBasicTypes.STRING);
		query.addScalar("deliveryName",StandardBasicTypes.STRING);
		query.addScalar("deliveryPosition",StandardBasicTypes.STRING);
		query.addScalar("foundation",StandardBasicTypes.STRING);
		query.addScalar("isAccepted",StandardBasicTypes.LONG);
		query.addScalar("deliveryNoteCode",StandardBasicTypes.STRING);
		query.addScalar("handoverCode",StandardBasicTypes.STRING);
		query.addScalar("receiverGroupName",StandardBasicTypes.STRING);
		query.addScalar("deliveryGroupName",StandardBasicTypes.STRING);
		query.addScalar("deliveryGroupTitleName",StandardBasicTypes.STRING);
		
		
		query.addScalar("id",StandardBasicTypes.LONG);
		
		
		
		@SuppressWarnings("unchecked")
		List<AssetHandOverDto> lst=query.list();
		if(lst.size()>0){
			return lst.get(0);
		}
		return null;
		
	}

}
