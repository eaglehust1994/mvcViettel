package com.viettel.asset.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.common.base.Joiner;
import com.viettel.asset.bo.CatAssetCode;
import com.viettel.asset.bo.LongTermAsset;
import com.viettel.asset.bo.MerHandOverInfo;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.asset.dto.CatAssetCodeSearchDto;
import com.viettel.asset.dto.CatStationDto;
import com.viettel.asset.dto.ConstrConstructionDto;
import com.viettel.asset.dto.LongTermAssetAutoCompleteDto;
import com.viettel.asset.dto.LongTermAssetDto;
import com.viettel.asset.dto.MerEntityDto;
import com.viettel.asset.dto.MerHandOverEntityDto;
import com.viettel.asset.dto.SysGroupDto;
import com.viettel.asset.dto.search.ConstructionAcceptanceDto;
import com.viettel.asset.dto.search.MerEntityConstructionAcceptanceDto;
import com.viettel.asset.dto.search.VConstructionOfferSlip;
import com.viettel.asset.dto.service.PagingDto;
import com.viettel.asset.dto.search.AssetHandOverDto;
import com.viettel.asset.dto.search.AssetHandOverSearchDto;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.DatabaseException;
import com.viettel.ktts2.common.Page;
import com.viettel.ktts2.common.UDate;
import com.viettel.ktts2.common.UString;

@Repository("longTermAssetDao")
public class LongTermAssetDao extends HibernateDao<LongTermAsset, Long> {
	private static final Long LTA_START_INDEX=30001L;
	@Value("${catAsset.attachTypeKey}")
	private Long attachAssetTypeKey;

	@Value("${catAssetVourcher.attachTypeKey}")
	private Long attachVourcherTypeKey;
	//hanhls1: add Ngày bắt đầu sử dụng handover để hình thành tài sản cố định
	@Value("${asset.startUsingHandOverForLta}")
	private String startUsingHandOverForLta;

	public LongTermAsset getByLotaCode(String lotaCode) throws DatabaseException {
		if (UString.isNullOrWhitespace(lotaCode)) {
			return null;
		}
		String[] arrayProperty={LongTermAsset.Columns.LOTA_CODE,LongTermAsset.Columns.IS_ACTIVE};
		Object[] arrValue={lotaCode,1l};
//		List<LongTermAsset> lst = find(LongTermAsset.Columns.LOTA_CODE, lotaCode);
		List<LongTermAsset> lst = find(arrayProperty, arrValue);
		if (lst == null || lst.isEmpty()) {
			return null;
		}
		/* Không thể xảy ra vì có Constrain Unique Key với LOTA_CODE */
		if (lst.size() > 1) {
			throw new DatabaseException("Dữ liệu lỗi: có 2 bản ghi trong LONG_TERM_ASSETS có cùng LOTA_CODE");
		}
		return lst.get(0);
	}

	/*
	 * Hanhls1: comment : su dung ham nay tren server luon \
	 * thay the bang ham getNextLotaIndex(long catAssetCodeId)
	 */
	/*public LongTermAssetDto getNewLotaIndex(LongTermAssetDto obj) throws DatabaseException {
		String sql = "SELECT MAX(LOTA_INDEX) lotaIndex" + " FROM LONG_TERM_ASSETS"
				+ " WHERE CAT_ASSET_CODE_ID = :catAssetCodeId";
		StringBuilder stringBuilder = new StringBuilder(sql);

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("lotaIndex", LongType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));

		query.setParameter("catAssetCodeId", obj.getCatAssetCodeId());

		return (LongTermAssetDto) query.uniqueResult();
	}*/

	public List<ConstrConstructionDto> getConstrConstructionForAutoComplete(AutocompleteSearchDto obj)
			throws DatabaseException {
		String sql = "SELECT CONSTRUCT_ID constructId" + " ,CONSTRT_NAME constrtName" + " ,CONSTRT_CODE constrtCode"
				+ " FROM CONSTR_CONSTRUCTIONS" + " WHERE IS_ACTIVE = 1";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <= 20");
		stringBuilder.append(StringUtils.isNotEmpty(obj.getKeySearch())
				? " AND (upper(CONSTRT_NAME) LIKE upper(:keySearch)" + (StringUtils.isNotEmpty(obj.getKeySearch())
						? " OR upper(CONSTRT_CODE) LIKE upper(:keySearch)" : "") + ")"
				: (StringUtils.isNotEmpty(obj.getKeySearch()) ? "AND upper(CONSTRT_CODE) LIKE upper(:keySearch)" : ""));
		stringBuilder.append(obj.getParentId() != null ? " AND STATION_ID = :parentId" : "");
		stringBuilder.append(" ORDER BY CONSTRT_NAME");

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constrtName", StringType.INSTANCE);
		query.addScalar("constrtCode", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(ConstrConstructionDto.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch() + "%");
		}
		if (obj.getParentId() != null) {
			query.setParameter("parentId", obj.getParentId());
		}

		return query.list();
	}

	public List<CatStationDto> getStationForAutoComplete(AutocompleteSearchDto obj) throws DatabaseException {
		String sql = "SELECT ID id" + " ,ADDRESS address" + " ,STATION_CODE stationCode" + " ,DESCRIPTION description"
				+ " FROM CAT_STATION" + " WHERE IS_ACTIVE = 1";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <= 20");
		stringBuilder.append(
				StringUtils.isNotEmpty(obj.getKeySearch()) ? " AND upper(STATION_CODE) LIKE upper(:keySearch)" : "");
		stringBuilder.append(" ORDER BY STATION_CODE");

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("id", LongType.INSTANCE);
		query.addScalar("address", StringType.INSTANCE);
		query.addScalar("stationCode", StringType.INSTANCE);
		query.addScalar("description", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(CatStationDto.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch() + "%");
		}

		return query.list();
	}

	public List<SysGroupDto> getSysGroupForAutoComplete(AutocompleteSearchDto obj) throws DatabaseException {
		String sql = "SELECT GROUP_ID groupId" + " ,NAME name" + " ,GROUP_CODE groupCode" + " FROM SYS_GROUP"
				+ " WHERE STATUS = 1";
		StringBuilder stringBuilder = new StringBuilder(sql);
		stringBuilder.append(" AND ROWNUM <= 20");
		stringBuilder
				.append(StringUtils.isNotEmpty(obj.getKeySearch()) ? " AND upper(NAME) LIKE upper(:keySearch)" : "");
		stringBuilder.append(obj.getParentId() != null ? " AND PARENT_ID = :parentId" : "");
		stringBuilder.append(" ORDER BY NAME");

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("groupId", LongType.INSTANCE);
		query.addScalar("name", StringType.INSTANCE);
		query.addScalar("groupCode", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(SysGroupDto.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch() + "%");
		}
		if (obj.getParentId() != null) {
			query.setParameter("parentId", obj.getParentId());
		}

		return query.list();
	}

	public List<MerEntityDto> getConstructionAcceptance(MerEntityDto obj) throws DatabaseException {
		String sql = "SELECT WL.EXECUTE_QUANTITY merWeight" + ",WI.UNIT_PRICE vndUnitPrice" + ",WI.WORK_ITEM_CODE code"
				+ ",WI.WORK_ITEM_NAME name " + "FROM CONSTRUCTION_ACCEPTANCE CA "
				+ "INNER JOIN CONSTR_ACCEPT_WORK_LIST WL ON CA.CONSTRUCTION_ACCEPTANCE_ID = WL.CONSTRUCTION_ACCEPTANCE_ID "
				+ "INNER JOIN ESTIMATES_WORK_ITEMS WI ON WL.ESTIMATES_WORK_ITEM_ID = WI.ESTIMATES_WORK_ITEM_ID "
				+ "WHERE 1 = 1 AND (WL.EXECUTE_QUANTITY IS NOT NULL AND WL.EXECUTE_QUANTITY <> 0) AND (WI.UNIT_PRICE IS NOT NULL AND WI.UNIT_PRICE <> 0)";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(obj.getConstructionId() != null ? " AND CA.CONSTRUCT_ID = :constructionId" : "");

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("merWeight", new DoubleType());
		query.addScalar("vndUnitPrice", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("name", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(MerEntityDto.class));

		if (obj.getConstructionId() != null) {
			query.setParameter("constructionId", obj.getConstructionId());
		}

		return query.list();
	}

	// Hanhls1 - added 20170316
	public ConstructionAcceptanceDto getConstructionAcceptanceById(Long id) {
		String sql = "SELECT " + " ca.construction_acceptance_id as constructionAcceptanceId,"
				+ " ca.CODE as code, vct.station_id as stationId, vct.station_code as stationCode"
				+ ", vct.constr_code as constrCode, vct.constr_name as constrName, vct.construct_id as constructId "
				+ ", vct.invest_project_name as investProjectName,vct.invest_project_code as investProjectCode,vct.invest_project_id as investProjectId,"
				+ " ca.CREATED_DATE as handoverDate, ca.CODE as handoverCode " + " from CONSTRUCTION_ACCEPTANCE ca "
				+ " left join V_CONSTRUCTION_TSCD vct on ca.CONSTRUCT_ID= vct.CONSTRUCT_ID "
				+ " WHERE ca.IS_ACTIVE=1 and ca.status_ca=2 and ca.LONG_TERM_ASSET_ID is null"
				+ "	and ca.construction_acceptance_id=:constructionAcceptanceId ";

		SQLQuery query = currentSession().createSQLQuery(sql);
		query.addScalar("constructionAcceptanceId", StandardBasicTypes.LONG);
		query.addScalar("code", StandardBasicTypes.STRING);
		query.addScalar("stationId", StandardBasicTypes.LONG);
		query.addScalar("stationCode", StandardBasicTypes.STRING);
		query.addScalar("constructId", StandardBasicTypes.LONG);
		query.addScalar("constrCode", StandardBasicTypes.STRING);
		query.addScalar("constrName", StandardBasicTypes.STRING);
		query.addScalar("investProjectName", StandardBasicTypes.STRING);
		query.addScalar("investProjectCode", StandardBasicTypes.STRING);
		query.addScalar("investProjectId", StandardBasicTypes.LONG);
		query.addScalar("handoverDate", StandardBasicTypes.DATE);
		query.addScalar("handoverCode", StandardBasicTypes.STRING);

		query.setResultTransformer(Transformers.aliasToBean(ConstructionAcceptanceDto.class));

		query.setParameter("constructionAcceptanceId", id);
		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<ConstructionAcceptanceDto> lst = query.list();
		if (lst.isEmpty()) {
			throw new BusinessException("Khong tim thay constructionAccptance voi id =" + id);
		}
		return lst.get(0);

	}

	//Không sử dụng hàm này nữa
	//20170427
	@Deprecated
	public List<MerEntityDto> getDataConstructionAcceptance(Long constructionAcceptanceId) throws DatabaseException {
		String sql = "SELECT WL.EXECUTE_QUANTITY merWeight" + ",WI.UNIT_PRICE vndUnitPrice" + ",WI.WORK_ITEM_CODE code"
				+ ",WI.WORK_ITEM_NAME name " + "FROM CONSTRUCTION_ACCEPTANCE CA "
				+ "INNER JOIN CONSTR_ACCEPT_WORK_LIST WL ON CA.CONSTRUCTION_ACCEPTANCE_ID = WL.CONSTRUCTION_ACCEPTANCE_ID "
				+ "INNER JOIN ESTIMATES_WORK_ITEMS WI ON WL.ESTIMATES_WORK_ITEM_ID = WI.ESTIMATES_WORK_ITEM_ID "
				+ "WHERE 1 = 1 AND (WL.EXECUTE_QUANTITY IS NOT NULL AND WL.EXECUTE_QUANTITY <> 0) AND (WI.UNIT_PRICE IS NOT NULL AND WI.UNIT_PRICE <> 0)";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(constructionAcceptanceId != null
				? " AND CA.CONSTRUCTION_ACCEPTANCE_ID = :constructionAcceptanceId" : "");

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("merWeight", new DoubleType());
		query.addScalar("vndUnitPrice", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("name", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(MerEntityDto.class));

		if (constructionAcceptanceId != null) {
			query.setParameter("constructionAcceptanceId", constructionAcceptanceId);
		}

		return query.list();
	}
	/*
	 * 2017-04-25: 
	 * chuyen lay chi phi B cap tu bang
	 * getChiPhiDataConstructionAcceptance
	 */
	public List<MerEntityConstructionAcceptanceDto> getChiPhiDataConstructionAcceptance(Long constructionAcceptanceId) throws DatabaseException {
		String sql = "SELECT WL.EXECUTE_QUANTITY merWeight" + ",WI.UNIT_PRICE vndUnitPrice" + ",WI.WORK_ITEM_CODE code"
				+ ",WI.WORK_ITEM_NAME name " + "FROM CONSTRUCTION_ACCEPTANCE CA "
				+ "INNER JOIN CONSTR_ACCEPT_WORK_LIST WL ON CA.CONSTRUCTION_ACCEPTANCE_ID = WL.CONSTRUCTION_ACCEPTANCE_ID "
				+ "INNER JOIN ESTIMATES_WORK_ITEMS WI ON WL.ESTIMATES_WORK_ITEM_ID = WI.ESTIMATES_WORK_ITEM_ID "
				+ "WHERE 1 = 1 AND (WL.EXECUTE_QUANTITY IS NOT NULL AND WL.EXECUTE_QUANTITY <> 0) AND (WI.UNIT_PRICE IS NOT NULL AND WI.UNIT_PRICE <> 0)";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(constructionAcceptanceId != null
				? " AND CA.CONSTRUCTION_ACCEPTANCE_ID = :constructionAcceptanceId" : "");

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("merWeight", StandardBasicTypes.DOUBLE);
		query.addScalar("vndUnitPrice", StandardBasicTypes.DOUBLE);
		query.addScalar("code", StandardBasicTypes.STRING);
		query.addScalar("name", StandardBasicTypes.STRING);

		query.setResultTransformer(Transformers.aliasToBean(MerEntityConstructionAcceptanceDto.class));

		if (constructionAcceptanceId != null) {
			query.setParameter("constructionAcceptanceId", constructionAcceptanceId);
		}

		return query.list();
	}

	public List<LongTermAssetDto> getAllAsset(LongTermAssetDto obj) throws DatabaseException {
		String sql = "SELECT " + " DISTINCT LTA.LONG_TERM_ASSET_ID longTermAssetId,"
				+ "LTA.CAT_ASSET_CODE_ID catAssetCodeId," + "LTA.LOTA_CODE lotaCode," + "LTA.LOTA_INDEX lotaIndex,"
				+ "LTA.CONSTRUCT_ID constructId," + "LTA.STATION_ID stationId," + "LTA.EMPLOYEE_ID employeeId,"
				+ "LTA.GROUP_ID groupId," + "LTA.USE_GROUP_ID useGroupId," + "LTA.ORIGINAL_PRICE originalPrice,"
				+ "LTA.RESIDUAL_PRICE residualPrice," + "LTA.DEPRECIATION_START_DATE depreciationStartDate,"
				+ "LTA.DEPRECIATION_TIME depreciationTime," + "LTA.DEPRECIATION_RATE depreciationRate,"
				+ "LTA.DEPRECIATED_TIME depreciatedTime," + "LTA.DEPRECIATIED_VALUE depreciatiedValue,"
				+ "LTA.LOTA_TYPE lotaType," + "LTA.IS_ACTIVE isActive," + "LTA.CREATOR_ID creatorId,"
				+ "LTA.CREATED_GROUP_ID createdGroupId," + "LTA.CREATOR_DATE creatorDate," + "LTA.UPDATOR_ID updatorId,"
				+ "LTA.UPDATED_GROUP_ID updatedGroupId," + "LTA.UPDATOR_DATE updatorDate,"
				+ "LTA.WH_EXP_REQ_ID whExpReqId," + "LTA.VOUCHER_TYPE voucherType,"
				+ "LTA.CREATED_SOURCE createdSource," + "LTA.LOTA_STATUS lotaStatus,"
				+ "LTA.HANDOVER_CODE handoverCode," + "LTA.IS_SENT_ERP isSentErp," + "CS.STATION_CODE stationCode,"
				+ "CC.CONSTRT_NAME constructName," + "CAC.assetNameName assetNameName,"
				+ "CAC.assetSourceName assetSourceName," + "CAC.assetTypeName assetTypeName,"
				+ "CAC.assetGroupName assetGroupName," + "CAC.assetNameId assetNameId,"
				+ "CAC.assetSourceId assetSourceId," + "CAC.assetTypeId assetTypeId," + "CAC.assetGroupId assetGroupId,"
				+ "SG1.NAME groupName," + "SG2.NAME useGroupName," + "UAD.DOCUMENT_NAME attachName"
				+ " FROM LONG_TERM_ASSETS LTA"
				+ " LEFT JOIN  (Select  CAAC5.assetNameName, CAAC5.assetSourceName, CAAC5.assetTypeName, CAAC6.CAAC_NAME assetGroupName, CAAC5.assetNameId, "
				+ " CAAC5.assetSourceId, CAAC5.assetTypeId, CAAC6.CAT_ASSET_CODE_ID assetGroupId FROM"
				+ " (Select CAAC3.assetNameName,CAAC3.assetSourceName,CAAC3.assetNameId,CAAC3.assetSourceId,"
				+ "	CAAC4.CAT_ASSET_CODE_ID assetTypeId,CAAC4.CAAC_NAME assetTypeName,CAAC4.CAAC_PARENT_ID  FROM"
				+ " (Select  CAC1.assetNameName assetNameName,CAC2.CAAC_NAME assetSourceName,CAC1.assetNameId assetNameId,"
				+ "	CAC2.CAT_ASSET_CODE_ID assetSourceId,CAC2.CAAC_PARENT_ID FROM"
				+ "	(Select CAT_ASSET_CODE_ID assetNameId, CAAC_NAME assetNameName,CAAC_PARENT_ID parentId "
				+ "	From CAT_ASSET_CODES WHERE CAAC_LEVEL=4) CAC1 Join CAT_ASSET_CODES CAC2 ON CAC1.parentId=CAC2.CAT_ASSET_CODE_ID) "
				+ "	CAAC3 Join CAT_ASSET_CODES CAAC4 ON CAAC4.CAT_ASSET_CODE_ID=CAAC3.CAAC_PARENT_ID) "
				+ "	CAAC5 JOIN  CAT_ASSET_CODES CAAC6 ON CAAC6.CAT_ASSET_CODE_ID=CAAC5.CAAC_PARENT_ID) CAC "
				+ " ON LTA.CAT_ASSET_CODE_ID = CAC.assetNameId " + " LEFT JOIN CAT_STATION CS ON LTA.STATION_ID = CS.ID"
				+ " LEFT JOIN CONSTR_CONSTRUCTIONS CC ON LTA.CONSTRUCT_ID=CC.CONSTRUCT_ID"
				+ " LEFT JOIN SYS_GROUP SG1 ON LTA.GROUP_ID=SG1.GROUP_ID"
				+ " LEFT JOIN SYS_GROUP SG2 ON LTA.USE_GROUP_ID=SG2.GROUP_ID"
				+ " LEFT JOIN LONG_TERM_ASSET_ENTITIES LTAE ON LTA.LONG_TERM_ASSET_ID = LTAE.LONG_TERM_ASSET_ID"
				+ " LEFT JOIN LONG_TERM_ASSET_COSTS LTAC ON LTA.LONG_TERM_ASSET_ID = LTAC.LONG_TERM_ASSET_ID"
				+ " LEFT JOIN UTIL_ATTACHED_DOCUMENTS UAD ON LTA.LONG_TERM_ASSET_ID = UAD.PARENT_ID AND UAD.TYPE = 1611"
				+ " WHERE LTA.IS_ACTIVE = 1";

		HashMap<String,Object> paramMap=new HashMap();
		StringBuilder stringBuilder = new StringBuilder(sql);
		if (null != obj.getLongTermAssetId()) {
			stringBuilder.append(" AND LTA.LONG_TERM_ASSET_ID = :longTermAssetId");
			paramMap.put("longTermAssetId", obj.getLongTermAssetId());
		}
		if(UString.isNotNullAndWhitespace(obj.getLotaCode())){
			stringBuilder.append(" AND LTA.LOTA_CODE  like :lotaCode");
			paramMap.put("lotaCode", UString.toLikeString(obj.getLotaCode().trim()).toUpperCase());
		}
		if (null != obj.getStationId()) {
			stringBuilder.append(" AND LTA.STATION_ID = :stationId");
			paramMap.put("stationId", obj.getStationId());
		}
		if (null != obj.getConstructId()) {
			stringBuilder.append(" AND LTA.CONSTRUCT_ID = :constructId");
			paramMap.put("constructId", obj.getConstructId());
		}
		if (null != obj.getGroupId()) {
			stringBuilder.append(" AND LTA.GROUP_ID = :groupId");
			paramMap.put("groupId", obj.getGroupId());
		}
		if (null != obj.getUseGroupId()) {
			stringBuilder.append(" AND LTA.USE_GROUP_ID = :useGroupId");
			paramMap.put("useGroupId", obj.getUseGroupId());
		}	
		if (null != obj.getLotaStatus() && obj.getLotaStatus() != 2) {
			stringBuilder.append(" AND LTA.LOTA_STATUS = :lotaStatus");
			paramMap.put("lotaStatus", obj.getLotaStatus());
		}
		if (StringUtils.isNotEmpty(obj.getCreatedSource()) && !"0".equals(obj.getCreatedSource())) {
			stringBuilder.append(" AND LTA.CREATED_SOURCE = :createdSource");
			paramMap.put("createdSource", obj.getCreatedSource());
		}
		if (null != obj.getLotaType() && obj.getLotaType() != 0) {
			stringBuilder.append(" AND LTA.LOTA_TYPE = :lotaType");
			paramMap.put("lotaType", obj.getLotaType());
		}

		if (null != obj.getAssetNameId()) {
			stringBuilder.append(" AND LTA.CAT_ASSET_CODE_ID = :assetNameId");
			paramMap.put("assetNameId", obj.getAssetNameId());
		} else if (null != obj.getAssetSourceId()) {
			stringBuilder.append(" AND CAC.assetSourceId = :assetSourceId");
			paramMap.put("assetSourceId", obj.getAssetSourceId());
		} else if (null != obj.getAssetTypeId()) {
			stringBuilder.append(" AND CAC.assetTypeId = :assetTypeId");
			paramMap.put("assetTypeId", obj.getAssetTypeId());
		} else if (null != obj.getAssetGroupId()) {
			stringBuilder.append(" AND CAC.assetGroupId = :assetGroupId");
			paramMap.put("assetGroupId", obj.getAssetGroupId());
		}

		if (null != obj.getVoucherType() && obj.getVoucherType() != 0) {
			stringBuilder.append(" AND LTA.VOUCHER_TYPE = :voucherType");
			paramMap.put("voucherType", obj.getVoucherType());
		}

		if (null != obj.getDateUsingFrom()) {
			stringBuilder.append(" AND LTA.DEPRECIATION_START_DATE >= :dateUsingFrom");
			paramMap.put("dateUsingFrom", obj.getDateUsingFrom());
		}

		if (null != obj.getDateUsingTo()) {
			stringBuilder.append(" AND LTA.DEPRECIATION_START_DATE <= :dateUsingTo");
			paramMap.put("dateUsingTo", obj.getDateUsingTo());
		}

		if (null != obj.getIsSentErp() && obj.getIsSentErp() != 2) {
			stringBuilder.append(" AND LTA.IS_SENT_ERP = :isSentErp");
			paramMap.put("isSentErp", obj.getIsSentErp());
		}
		stringBuilder.append(" ORDER BY LTA.LONG_TERM_ASSET_ID");

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("longTermAssetId", LongType.INSTANCE);
		query.addScalar("catAssetCodeId", LongType.INSTANCE);
		query.addScalar("lotaCode", StringType.INSTANCE);
		query.addScalar("lotaIndex", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("stationId", LongType.INSTANCE);
		query.addScalar("employeeId", LongType.INSTANCE);
		query.addScalar("groupId", LongType.INSTANCE);
		query.addScalar("useGroupId", LongType.INSTANCE);
		query.addScalar("originalPrice", LongType.INSTANCE);
		query.addScalar("residualPrice", LongType.INSTANCE);
		query.addScalar("depreciationStartDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("depreciationTime", LongType.INSTANCE);
		query.addScalar("depreciationRate", LongType.INSTANCE);
		query.addScalar("depreciatedTime", LongType.INSTANCE);
		query.addScalar("depreciatiedValue", LongType.INSTANCE);
		query.addScalar("lotaType", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("creatorId", LongType.INSTANCE);
		query.addScalar("createdGroupId", LongType.INSTANCE);
		query.addScalar("creatorDate", LongType.INSTANCE);
		query.addScalar("updatorId", LongType.INSTANCE);
		query.addScalar("updatedGroupId", LongType.INSTANCE);
		query.addScalar("updatorDate", LongType.INSTANCE);
		query.addScalar("whExpReqId", LongType.INSTANCE);
		query.addScalar("voucherType", LongType.INSTANCE);
		query.addScalar("createdSource", StringType.INSTANCE);
		query.addScalar("lotaStatus", LongType.INSTANCE);
		query.addScalar("handoverCode", StringType.INSTANCE);
		query.addScalar("isSentErp", LongType.INSTANCE);

		query.addScalar("stationCode", StringType.INSTANCE);
		query.addScalar("constructName", StringType.INSTANCE);
		query.addScalar("assetNameName", StringType.INSTANCE);
		query.addScalar("assetSourceName", StringType.INSTANCE);
		query.addScalar("assetTypeName", StringType.INSTANCE);
		query.addScalar("assetGroupName", StringType.INSTANCE);
		query.addScalar("groupName", StringType.INSTANCE);
		query.addScalar("useGroupName", StringType.INSTANCE);
		query.addScalar("assetNameId", LongType.INSTANCE);
		query.addScalar("assetSourceId", LongType.INSTANCE);
		query.addScalar("assetTypeId", LongType.INSTANCE);
		query.addScalar("assetGroupId", LongType.INSTANCE);
		query.addScalar("attachName", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));
		for(String key:paramMap.keySet()){
			query.setParameter(key,paramMap.get(key));
		}		
		return query.list();
	}

	public LongTermAssetDto getDetailAsset(Long id) throws DatabaseException {
		String sql = "SELECT " + " LTA.LONG_TERM_ASSET_ID longTermAssetId," + "LTA.CAT_ASSET_CODE_ID catAssetCodeId,"
				+ "LTA.LOTA_CODE lotaCode," + "LTA.LOTA_INDEX lotaIndex," + "LTA.CONSTRUCT_ID constructId,"
				+ "LTA.STATION_ID stationId," + "LTA.EMPLOYEE_ID employeeId," + "LTA.GROUP_ID groupId,"
				+ "LTA.USE_GROUP_ID useGroupId," + "LTA.ORIGINAL_PRICE originalPrice,"
				+ "LTA.RESIDUAL_PRICE residualPrice," + "LTA.DEPRECIATION_START_DATE depreciationStartDate,"
				+ "LTA.DEPRECIATION_TIME depreciationTime," + "LTA.DEPRECIATION_RATE depreciationRate,"
				+ "LTA.DEPRECIATED_TIME depreciatedTime," + "LTA.DEPRECIATIED_VALUE depreciatiedValue,"
				+ "LTA.LOTA_TYPE lotaType," + "LTA.IS_ACTIVE isActive," + "LTA.CREATOR_ID creatorId,"
				+ "LTA.CREATED_GROUP_ID createdGroupId," + "LTA.CREATOR_DATE creatorDate," + "LTA.UPDATOR_ID updatorId,"
				+ "LTA.UPDATED_GROUP_ID updatedGroupId," + "LTA.UPDATOR_DATE updatorDate,"
				+ "LTA.WH_EXP_REQ_ID whExpReqId," + "LTA.VOUCHER_TYPE voucherType,"
				+ "LTA.CREATED_SOURCE createdSource," + "LTA.LOTA_STATUS lotaStatus,"
				+ "LTA.HANDOVER_CODE handoverCode," + "LTA.IS_SENT_ERP isSentErp," + "CS.STATION_CODE stationCode,"
				+ "CC.CONSTRT_NAME constructName," + "CAC.assetNameName assetNameName,"
				+ "CAC.assetNameCode assetNameCode," + "CAC.assetSourceName assetSourceName,"
				+ "CAC.assetSourceCode assetSourceCode," + "CAC.assetTypeName assetTypeName,"
				+ "CAC.assetTypeCode assetTypeCode," + "CAC.assetGroupName assetGroupName,"
				+ "CAC.assetGroupCode assetGroupCode," + "CAC.assetNameId assetNameId,"
				+ "CAC.assetSourceId assetSourceId," + "CAC.assetTypeId assetTypeId," + "CAC.assetGroupId assetGroupId,"
				+ "SG1.NAME groupName," + "SG2.NAME useGroupName," + "UAD.DOCUMENT_NAME attachName"
				+ " FROM LONG_TERM_ASSETS LTA"
				+ " LEFT JOIN  (Select CAAC5.assetNameName, CAAC5.assetNameCode, CAAC5.assetSourceName, CAAC5.assetSourceCode, CAAC5.assetTypeName, CAAC5.assetTypeCode, CAAC6.CAAC_NAME assetGroupName, CAAC6.CAAC_CODE assetGroupCode, CAAC5.assetNameId, "
				+ " CAAC5.assetSourceId, CAAC5.assetTypeId, CAAC6.CAT_ASSET_CODE_ID assetGroupId FROM"
				+ " (Select CAAC3.assetNameName, CAAC3.assetNameCode, CAAC3.assetSourceName, CAAC3.assetSourceCode, CAAC3.assetNameId, CAAC3.assetSourceId,"
				+ "	CAAC4.CAT_ASSET_CODE_ID assetTypeId, CAAC4.CAAC_NAME assetTypeName, CAAC4.CAAC_CODE assetTypeCode, CAAC4.CAAC_PARENT_ID FROM"
				+ " (Select CAC1.assetNameName assetNameName, CAC1.assetNameCode assetNameCode, CAC2.CAAC_NAME assetSourceName, CAC2.CAAC_CODE assetSourceCode,CAC1.assetNameId assetNameId,"
				+ "	CAC2.CAT_ASSET_CODE_ID assetSourceId,CAC2.CAAC_PARENT_ID FROM"
				+ "	(Select CAT_ASSET_CODE_ID assetNameId, CAAC_NAME assetNameName, CAAC_CODE assetNameCode, CAAC_PARENT_ID parentId "
				+ "	From CAT_ASSET_CODES WHERE CAAC_LEVEL=4) CAC1 Join CAT_ASSET_CODES CAC2 ON CAC1.parentId=CAC2.CAT_ASSET_CODE_ID) "
				+ "	CAAC3 Join "
				+ "CAT_ASSET_CODES CAAC4 ON CAAC4.CAT_ASSET_CODE_ID=CAAC3.CAAC_PARENT_ID) "
				+ "	CAAC5 JOIN  CAT_ASSET_CODES CAAC6 ON CAAC6.CAT_ASSET_CODE_ID=CAAC5.CAAC_PARENT_ID) CAC "
				+ " ON LTA.CAT_ASSET_CODE_ID = CAC.assetNameId " + " LEFT JOIN CAT_STATION CS ON LTA.STATION_ID = CS.ID"
				+ " LEFT JOIN CONSTR_CONSTRUCTIONS CC ON LTA.CONSTRUCT_ID=CC.CONSTRUCT_ID"
				+ " LEFT JOIN SYS_GROUP SG1 ON LTA.GROUP_ID=SG1.GROUP_ID"
				+ " LEFT JOIN SYS_GROUP SG2 ON LTA.USE_GROUP_ID=SG2.GROUP_ID"
				+ " LEFT JOIN LONG_TERM_ASSET_ENTITIES LTAE ON LTA.LONG_TERM_ASSET_ID = LTAE.LONG_TERM_ASSET_ID"
				+ " LEFT JOIN LONG_TERM_ASSET_COSTS LTAC ON LTA.LONG_TERM_ASSET_ID = LTAC.LONG_TERM_ASSET_ID"
				+ " LEFT JOIN UTIL_ATTACHED_DOCUMENTS UAD ON LTA.LONG_TERM_ASSET_ID = UAD.PARENT_ID AND UAD.TYPE = 1611"
				+ " WHERE LTA.IS_ACTIVE = 1 AND LTA.LONG_TERM_ASSET_ID = :longTermAssetId";

		StringBuilder stringBuilder = new StringBuilder(sql);

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("longTermAssetId", LongType.INSTANCE);
		query.addScalar("catAssetCodeId", LongType.INSTANCE);
		query.addScalar("lotaCode", StringType.INSTANCE);
		query.addScalar("lotaIndex", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("stationId", LongType.INSTANCE);
		query.addScalar("employeeId", LongType.INSTANCE);
		query.addScalar("groupId", LongType.INSTANCE);
		query.addScalar("useGroupId", LongType.INSTANCE);
		query.addScalar("originalPrice", LongType.INSTANCE);
		query.addScalar("residualPrice", LongType.INSTANCE);
		query.addScalar("depreciationStartDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("depreciationTime", LongType.INSTANCE);
		query.addScalar("depreciationRate", LongType.INSTANCE);
		query.addScalar("depreciatedTime", LongType.INSTANCE);
		query.addScalar("depreciatiedValue", LongType.INSTANCE);
		query.addScalar("lotaType", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("creatorId", LongType.INSTANCE);
		query.addScalar("createdGroupId", LongType.INSTANCE);
		query.addScalar("creatorDate", DateType.INSTANCE);
		query.addScalar("updatorId", LongType.INSTANCE);
		query.addScalar("updatedGroupId", LongType.INSTANCE);
		query.addScalar("updatorDate", DateType.INSTANCE);
		query.addScalar("whExpReqId", LongType.INSTANCE);
		query.addScalar("voucherType", LongType.INSTANCE);
		query.addScalar("createdSource", StringType.INSTANCE);
		query.addScalar("lotaStatus", LongType.INSTANCE);
		query.addScalar("handoverCode", StringType.INSTANCE);
		query.addScalar("isSentErp", LongType.INSTANCE);

		query.addScalar("stationCode", StringType.INSTANCE);
		query.addScalar("constructName", StringType.INSTANCE);
		query.addScalar("assetNameName", StringType.INSTANCE);
		query.addScalar("assetNameCode", StringType.INSTANCE);
		query.addScalar("assetSourceName", StringType.INSTANCE);
		query.addScalar("assetSourceCode", StringType.INSTANCE);
		query.addScalar("assetTypeName", StringType.INSTANCE);
		query.addScalar("assetTypeCode", StringType.INSTANCE);
		query.addScalar("assetGroupName", StringType.INSTANCE);
		query.addScalar("assetGroupCode", StringType.INSTANCE);
		query.addScalar("groupName", StringType.INSTANCE);
		query.addScalar("useGroupName", StringType.INSTANCE);
		query.addScalar("assetNameId", LongType.INSTANCE);
		query.addScalar("assetSourceId", LongType.INSTANCE);
		query.addScalar("assetTypeId", LongType.INSTANCE);
		query.addScalar("assetGroupId", LongType.INSTANCE);
		query.addScalar("attachName", StringType.INSTANCE);

		query.setParameter("longTermAssetId", id);

		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));
		query.setMaxResults(1);
		return (LongTermAssetDto) query.uniqueResult();
	}

	public List<LongTermAssetDto> findLoteIndex(Long catAssetCodeId) {
		String sql = "SELECT " + "LOTA_INDEX lotaIndex" + " FROM LONG_TERM_ASSETS "
				+ " WHERE IS_ACTIVE = 1 AND CAT_ASSET_CODE_ID = :catAssetCodeId";

		StringBuilder stringBuilder = new StringBuilder(sql);
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("lotaIndex", LongType.INSTANCE);
		query.setParameter("catAssetCodeId", catAssetCodeId);
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));

		return query.list();
	}
	
	/*
	 * Thực hiện việc set mã và lota_index cho LONG_TERM_ASSET_ID
	 */
	public void setLtaIndexAndCode(Long longTermAssetId, Long catAssetCodeId){
		String updateSql="   UPDATE LONG_TERM_ASSETs set LOTA_INDEX= "
        +" (select NVL(max(LOTA_INDEX)+1,:startIndex) FROM LONG_TERM_ASSETs WHERE IS_ACTIVE=1 and CAT_ASSET_CODE_ID=:catAssetCodeId) ,"
        +" LOTA_CODE= (select CAAC_FULL_CODE FROM CAT_ASSET_CODES WHERE CAT_ASSET_CODE_ID=:catAssetCodeId)|| (select NVL(max(LOTA_INDEX)+1,:startIndex) FROM LONG_TERM_ASSETs WHERE IS_ACTIVE=1 and CAT_ASSET_CODE_ID=:catAssetCodeId)"
        +" WHERE LONG_TERM_ASSET_ID=:longTermAssetId ";
		currentSession().flush();//flush session de chay duoc cau sqlf
		SQLQuery query=currentSession().createSQLQuery(updateSql);
		query.setParameter("longTermAssetId", longTermAssetId);
		query.setParameter("catAssetCodeId", catAssetCodeId);		
		query.setParameter("startIndex", LTA_START_INDEX);
		
		query.executeUpdate();						
	}
	
	/*
	 * Lấy lotaIndex max của tên tài sản hiện tại
	 * Nếu không có thì trả về 0
	 */
	public Long getNextLotaIndex(Long catAssetCodeId,Long startIndex){
		String selectMaxLotaIndexQuery=" select NVL(max(LOTA_INDEX)+1,:startIndex) FROM LONG_TERM_ASSETs WHERE IS_ACTIVE=1 and CAT_ASSET_CODE_ID=:catAssetCodeId";
		SQLQuery query=currentSession().createSQLQuery(selectMaxLotaIndexQuery); 
		if(startIndex!=null){
			query.setParameter("startIndex", startIndex);
		}else{
			query.setParameter("startIndex", LTA_START_INDEX);
		}
		query.setParameter("catAssetCodeId", catAssetCodeId);
		return ((BigDecimal) query.uniqueResult()).longValue();
	}
	

	public LongTermAsset getByLongTermAssetId(Long longTermAssetId){
		LongTermAsset obj = find(longTermAssetId);
		return obj;
	}
	

	@SuppressWarnings("unchecked")
	public List<LongTermAssetAutoCompleteDto> filterLongTermAsset(AutocompleteSearchDto searchForm) {
		String sql = "select lta.lota_Code as lotaCode," + "lta.LONG_TERM_ASSET_ID as longTermAssetId,"
				+ "caac.CAAC_NAME as caacName " + " from LONG_TERM_ASSETs lta "
				+ " inner join cat_asset_codes caac on lta.CAT_ASSET_CODE_ID=caac.CAT_ASSET_CODE_ID "
				+ " where lta.is_active=1 and lta.lota_code like :lotaCode " + " order by lta.lota_code";

		SQLQuery query = currentSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetAutoCompleteDto.class));
		query.setMaxResults(searchForm.getPageSize());
		if (UString.isNotNullAndWhitespace(searchForm.getKeySearch())) {
			String search = UString.trim(searchForm.getKeySearch().toUpperCase());
			query.setParameter("lotaCode", UString.toLikeString(search));
		}

		query.addScalar("lotaCode", StandardBasicTypes.STRING);
		query.addScalar("longTermAssetId", StandardBasicTypes.LONG);
		query.addScalar("caacName", StandardBasicTypes.STRING);
		return query.list();

	}

	public LongTermAssetDto getmaxLotaIndex() {
		String sql = "SELECT MAX(LOTA_INDEX) lotaIndex" + " FROM LONG_TERM_ASSETS" + " WHERE IS_ACTIVE = 1";
		StringBuilder stringBuilder = new StringBuilder(sql);

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("lotaIndex", LongType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));

		return (LongTermAssetDto) query.uniqueResult();
	}

	/**
	 * Cap nhat bien ban ban giao COnstruction_acceptance ve trang thai da hinh thanh tai san
	 * @param constructioId
	 * @param longTermAssetId
	 */
	public void updateConstructonAcceptToStatusAssetCreated(Long constructioId, Long longTermAssetId) {
		String sql = "UPDATE CONSTRUCTION_ACCEPTANCE SET LONG_TERM_ASSET_ID=:longTermAssetId where CONSTRUCT_ID=:CONSTRUCT_ID";
		SQLQuery query = currentSession().createSQLQuery(sql);
		query.setParameter("CONSTRUCT_ID", constructioId);
		query.setParameter("longTermAssetId", longTermAssetId);
		query.executeUpdate();
	}
	/**
	 * Cap nhat bien ban ban giao COnstruction_acceptance ve trang thai chua hinh thanh tai san
	 * @param longTermAssetId
	 */
	public void updateConstructonAcceptToStatusNotAssetCreated( Long longTermAssetId) {
		String sql = "UPDATE CONSTRUCTION_ACCEPTANCE SET LONG_TERM_ASSET_ID=null where LONG_TERM_ASSET_ID=:longTermAssetId";
		SQLQuery query = currentSession().createSQLQuery(sql);		
		query.setParameter("longTermAssetId", longTermAssetId);
		query.executeUpdate();
	}
	
	/**
	 * Cap nhat bien ban ban giao MER_HAND_OVER_INFO ve trang thai chua hinh thanh tai san
	 * @param handoverCode:
	 */
	public void updateMerhandOverInfoToStatusNotAssetCreated( String handoverCode) {
		String sql = "UPDATE MER_HAND_OVER_INFO SET IS_TO_ASSET=null where IS_TO_ASSET is not null and CODE= :code";
		SQLQuery query = currentSession().createSQLQuery(sql);		
		query.setParameter("code", handoverCode);
		query.executeUpdate();
	}

	/**
	 * Cập nhật danh sách biển bản bàn giao về chưa hình thành tài sản
	 * @param lstHandOverId
	 */
	public void updateListLTAEntityToStatusUpgradeToStatus( Long upgradeStatus,Long longTermAssetId) {
		
		String sql = "UPDATE LONG_TERM_ASSET_ENTITIES SET UPGRADE_STATUS=:upgradeStatus where UPGRADE_STATUS is not null and LONG_TERM_ASSET_ID =  :longTermAssetId";
		SQLQuery query = currentSession().createSQLQuery(sql);		
		query.setParameter("longTermAssetId", longTermAssetId);
		query.setParameter("upgradeStatus", upgradeStatus);
		query.executeUpdate();
	}
	

	/*
	 * Lay so luong bien ban ban giao qua xay lap chua hinh thanh tai san
	 */
	public Long countNoteCreatedByBuilding() throws Exception {
		// TODO Auto-generated method stub
		String sql = "Select count(ca.CONSTRUCTION_ACCEPTANCE_ID) from CONSTRUCTION_ACCEPTANCE ca left join V_CONSTRUCTION_TSCD s "
				+ "on ca.CONSTRUCT_ID= s.CONSTRUCT_ID "
				+ "WHERE ca.IS_ACTIVE=1 and ca.LONG_TERM_ASSET_ID is  null and ca.status_ca=2";
		SQLQuery query = currentSession().createSQLQuery(sql);
		BigDecimal result = (BigDecimal) query.uniqueResult();
		return result.longValue();
	}
	
	
	/*
	 * Bổ sung status_ca=2
	 * Lay so luong bien ban ban giao khong qua xay lap chua hinh thanh tai san
	 */
	public Long countNoteNotCreatedByBulding() throws Exception{
		String sql="Select count(MO.HAND_OVER_ID) from MER_HAND_OVER_INFO MO  inner join WARE_EXP_NOTE we on we.DELIVERY_NOTE_ID=MO.HAND_OVER_ID "
       +" inner join CAT_WAREHOUSE cwh  on we.SOURCE_WH_ID=cwh.WAREHOUSE_ID and (cwh.CODE not like '%THUHOI' and (cwh.VIRTUAL_TYPE <> 1 or cwh.VIRTUAL_TYPE is null)) "				
				+ " WHERE MO.IS_ACTIVE=1 and MO.IS_ACCEPTED=1 and MO.TYPE =1 and mo.status_ca=2 and mo.type_delivery_note =1 and MO.CONSTRUCTION_ID IS NULL AND (MO.IS_TO_ASSET is  null or MO.IS_TO_ASSET ='')"
				+ " and MO.CREATED_DATE > to_date(:startUsingHandOverForLta,'yyyy-MM-dd')";
		
		SQLQuery query = currentSession().createSQLQuery(sql);
		query.setParameter("startUsingHandOverForLta", startUsingHandOverForLta);
		BigDecimal result=(BigDecimal)query.uniqueResult();
		return result.longValue();	
	}

	@SuppressWarnings("unchecked")
	public List<LongTermAssetDto> getForAutoComplete(LongTermAssetDto obj) {
		String sql = "SELECT LONG_TERM_ASSET_ID AS longTermAssetId" + " ,LOTA_CODE AS lotaCode"
				+ " FROM LONG_TERM_ASSETS" + " WHERE 1 = 1";

		StringBuilder stringBuilder = new StringBuilder(sql);

		if (StringUtils.isNotEmpty(obj.getLotaCode())) {
			stringBuilder.append(" AND UPPER(LOTA_CODE) LIKE UPPER(:lotaCode)");
		}

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("longTermAssetId", LongType.INSTANCE);
		query.addScalar("lotaCode", StringType.INSTANCE);

		if (StringUtils.isNotEmpty(obj.getLotaCode())) {
			query.setParameter("lotaCode", "%" + obj.getLotaCode() + "%");
		}

		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));

		return query.list();
	}

	public LongTermAssetDto checkLotaCodeUsing(String lotaCode) {
		String sql = "SELECT " + "LOTA_CODE lotaCode" + " FROM LONG_TERM_ASSETS "
				+ " WHERE IS_ACTIVE = 1 AND LOTA_CODE = :lotaCode";

		StringBuilder stringBuilder = new StringBuilder(sql);
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("lotaCode", StringType.INSTANCE);
		query.setParameter("lotaCode", lotaCode);
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));

		return (LongTermAssetDto) query.uniqueResult();
	}

	/**
	 * Tìm kiếm paging biên bản bàn giao (cả qua xây lắp và không qua xây lắp
	 * @param searchForm
	 * @return
	 */
	public Page<AssetHandOverDto> searchHandOverPaginate(AssetHandOverSearchDto searchForm) {
		StringBuilder sbByConstructionSelect = new StringBuilder();
		StringBuilder sbByConstructionCount = new StringBuilder();
		StringBuilder sbByConstructionWhere = new StringBuilder();

		// SELECT
		sbByConstructionCount.append(" select count(CONSTRUCTION_ACCEPTANCE_ID) as ID");
		sbByConstructionSelect.append(
				"select a.CREATED_DATE as handoverDate, a.CODE as handoverCode, a.CONSTRUCTION_ACCEPTANCE_ID as id,1 as type");
		sbByConstructionSelect.append(" ,cct.CONSTR_CODE as constrCode,cct.station_Code stationCode");
		sbByConstructionSelect.append(" ,null as receiverName, null as deliveryGroupName ");
		// loai bien ban giao cho tai san qua xay lap																						

		// FROM
		sbByConstructionWhere.append(" from CONSTRUCTION_ACCEPTANCE a ");
		sbByConstructionWhere
				.append(" left join V_CONSTRUCTION_TSCD cct on cct.is_active=1 and cct.CONSTRUCT_ID=a.CONSTRUCT_ID ");
		sbByConstructionWhere.append(" where a.LONG_TERM_ASSET_ID  is  null  and a.status_ca = 2 and a.is_active=1");

		StringBuilder sbNotByConstructionSelect = new StringBuilder();
		StringBuilder sbNotByConstructionCount = new StringBuilder();
		StringBuilder sbNotByConstructionWhere = new StringBuilder();
		// SELECT
		sbNotByConstructionSelect.append(
				"select mo.HAND_OVER_DATE as handoverDate, mo.CODE as handoverCode, MO.HAND_OVER_ID as id , 2 as type");
		sbNotByConstructionSelect.append(" ,null as constrCode, null as stationCode");
		sbNotByConstructionSelect.append(" ,mo.receiver_name as receiverName, s.name as deliveryGroupName ");// loai
		sbNotByConstructionCount.append("select COUNT(HAND_OVER_ID) as ID ");
		// FROM
		sbNotByConstructionWhere.append(" from MER_HAND_OVER_INFO MO ");
		sbNotByConstructionWhere.append(" left join SYS_GROUP s on MO.DELIVERY_GROUP_ID=S.GROUP_ID ");

		// WHERE
		sbNotByConstructionWhere.append(
				" WHERE MO.IS_ACTIVE=1 and MO.IS_ACCEPTED=1 and MO.TYPE =1 and MO.CONSTRUCTION_ID IS NULL AND (MO.IS_TO_ASSET is  null or MO.IS_TO_ASSET ='') ");

		java.util.Map<String, Object> paramsConstr = new HashMap<>();
		// List<Object> paramsMerHandOver=new ArrayList<>();

		if (searchForm.getFromHandoverDate() != null) {
			// TODO: APPROVAL_DATE
			sbByConstructionWhere.append(" and a.CREATED_DATE>=:fromHandoverDate");
			sbNotByConstructionWhere.append(" and MO.HAND_OVER_DATE>=:fromHandoverDate");
			paramsConstr.put("fromHandoverDate", searchForm.getFromHandoverDate());
		}
		if (searchForm.getToHandoverDate() != null) {
			sbByConstructionWhere.append(" and a.CREATED_DATE<=:toHandoverDate");
			sbNotByConstructionWhere.append(" and MO.HAND_OVER_DATE<=:toHandoverDate");
			paramsConstr.put("toHandoverDate", UDate.toNextDate(searchForm.getToHandoverDate()));
		}
		if (UString.isNotNullAndWhitespace(searchForm.getHandoverCode())) {
			sbByConstructionWhere.append(" and a.CODE like :handoverCode");
			sbNotByConstructionWhere.append(" and MO.CODE like :handoverCode");
			paramsConstr.put("handoverCode", UString.toLikeString(searchForm.getHandoverCode().toUpperCase()));
		}
		String queryString = sbByConstructionSelect.toString() + sbByConstructionWhere.toString() + " union "
				+ sbNotByConstructionSelect.toString() + sbNotByConstructionWhere.toString()
				+ " order by handoverDate desc";
		String countQueryByConstrString = sbByConstructionCount.toString() + sbByConstructionWhere.toString();
		String countQueryNotByConstrString = sbNotByConstructionCount.toString() + sbNotByConstructionWhere.toString();

		SQLQuery query = currentSession().createSQLQuery(queryString);
		SQLQuery countQueryByConstr = currentSession().createSQLQuery(countQueryByConstrString);
		SQLQuery countQueryNotByConstr = currentSession().createSQLQuery(countQueryNotByConstrString);
		for (String str : paramsConstr.keySet()) {
			query.setParameter(str, paramsConstr.get(str));
			countQueryByConstr.setParameter(str, paramsConstr.get(str));
			countQueryNotByConstr.setParameter(str, paramsConstr.get(str));
		}

		query.addScalar("handoverDate", StandardBasicTypes.DATE);
		query.addScalar("handoverCode", StandardBasicTypes.STRING);
		query.addScalar("id", StandardBasicTypes.LONG);
		query.addScalar("constrCode", StandardBasicTypes.STRING);
		query.addScalar("stationCode", StandardBasicTypes.STRING);

		query.addScalar("receiverName", StandardBasicTypes.STRING);
		query.addScalar("deliveryGroupName", StandardBasicTypes.STRING);
		query.addScalar("type", StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(AssetHandOverDto.class));
		if (searchForm.getSize() > 0 && searchForm.getPage() > 0) {
			query.setMaxResults(searchForm.getSize());
			query.setFirstResult(searchForm.getFirstResult());
		}

		List<AssetHandOverDto> lstCount = query.list();
		int count = ((BigDecimal) countQueryByConstr.uniqueResult()).intValue()
				+ ((BigDecimal) countQueryNotByConstr.uniqueResult()).intValue();
		Page<AssetHandOverDto> page = new Page();
		page.setRows(lstCount);
		page.setPage(searchForm.getPage());
		page.setTotalRow(count);
		return page;

	}
	

	/**
	 * Tìm kiếm biên bản bàn giao không qua xây lắp paginae
	 * 20170524- bổ sung điều kiện Status_ca=2 cho biên bản bàn giao không qu xây lắp
	 * @param searchForm
	 * @return
	 */
	public Page<AssetHandOverDto> searchHandOverNotByConstructionPaginate(AssetHandOverSearchDto searchForm) {


		StringBuilder sbNotByConstructionSelect = new StringBuilder();
		StringBuilder sbNotByConstructionCount = new StringBuilder();
		StringBuilder sbNotByConstructionWhere = new StringBuilder();
		// SELECT
		sbNotByConstructionSelect.append(
				"select mo.HAND_OVER_DATE as handoverDate, mo.CODE as handoverCode, MO.HAND_OVER_ID as id , 2 as type");
		sbNotByConstructionSelect.append(" ,null as constrCode, null as stationCode");
		sbNotByConstructionSelect.append(" ,mo.receiver_name as receiverName, s.name as deliveryGroupName ");// loai
		sbNotByConstructionCount.append("select COUNT(HAND_OVER_ID) as ID ");
		// FROM
		sbNotByConstructionWhere.append(" from MER_HAND_OVER_INFO MO ");
		sbNotByConstructionWhere.append(" left join SYS_GROUP s on MO.DELIVERY_GROUP_ID=S.GROUP_ID ");
		sbNotByConstructionWhere.append(" inner join WARE_EXP_NOTE we on we.DELIVERY_NOTE_ID=MO.HAND_OVER_ID ");
		sbNotByConstructionWhere.append(" inner join CAT_WAREHOUSE cwh  on we.SOURCE_WH_ID=cwh.WAREHOUSE_ID and (cwh.CODE not like '%THUHOI' and (cwh.VIRTUAL_TYPE <> 1 or cwh.VIRTUAL_TYPE is null)) ");

		// WHERE
		//TYPE=1: bàn giao từ phiếu xuất
		//type_delivery_note=1 -> xuát đến đơn vị
		sbNotByConstructionWhere.append(
				" WHERE MO.IS_ACTIVE=1 and MO.IS_ACCEPTED=1 and MO.STATUS_CA=2 and MO.TYPE =1 and mo.type_delivery_note=1 and MO.CONSTRUCTION_ID IS NULL AND (MO.IS_TO_ASSET is  null or MO.IS_TO_ASSET ='') ");
	
		java.util.Map<String, Object> paramsConstr = new HashMap<>();
		// List<Object> paramsMerHandOver=new ArrayList<>();
		
		//Thêm điều kiện ngày bắt đầu (prop:asset.startUsingHandOverForLta)
		sbNotByConstructionWhere.append("and MO.CREATED_DATE >= to_date(:startUsingHandOverForLta,'yyyy-MM-dd')");		
		paramsConstr.put("startUsingHandOverForLta",startUsingHandOverForLta);
		//end thêm điều kiện ngày bắt đầu từ file cấu hình 
		
		if (searchForm.getFromHandoverDate() != null) {
			// TODO: APPROVAL_DATE
			sbNotByConstructionWhere.append(" and MO.HAND_OVER_DATE>=:fromHandoverDate");
			paramsConstr.put("fromHandoverDate", searchForm.getFromHandoverDate());
		}
		if (searchForm.getToHandoverDate() != null) {
			sbNotByConstructionWhere.append(" and MO.HAND_OVER_DATE<=:toHandoverDate");
			paramsConstr.put("toHandoverDate", UDate.toNextDate(searchForm.getToHandoverDate()));
		}
		if (UString.isNotNullAndWhitespace(searchForm.getHandoverCode())) {
			sbNotByConstructionWhere.append(" and MO.CODE like :handoverCode");
			paramsConstr.put("handoverCode", UString.toLikeString(searchForm.getHandoverCode().toUpperCase()));
		}
		if(searchForm.getUserSession()!=null){
			if(searchForm.getUserSession().getGroupId()!=null){
				sbNotByConstructionWhere.append(" and (s.path like :pathGroup or mo.delivery_group_id=:deliveryGroupId) ");
				paramsConstr.put("pathGroup", UString.toLikeString("/"+searchForm.getUserSession().getGroupId()+"/"));
				paramsConstr.put("deliveryGroupId", searchForm.getUserSession().getGroupId());
			}
		}
		String queryString = 
				sbNotByConstructionSelect.toString() + sbNotByConstructionWhere.toString()
				+ " order by handoverDate desc,id desc";
		String countQueryNotByConstrString = sbNotByConstructionCount.toString() + sbNotByConstructionWhere.toString();

		SQLQuery query = currentSession().createSQLQuery(queryString);
		SQLQuery countQueryNotByConstr = currentSession().createSQLQuery(countQueryNotByConstrString);
		for (String str : paramsConstr.keySet()) {
			query.setParameter(str, paramsConstr.get(str));
			countQueryNotByConstr.setParameter(str, paramsConstr.get(str));
		}

		query.addScalar("handoverDate", StandardBasicTypes.DATE);
		query.addScalar("handoverCode", StandardBasicTypes.STRING);
		query.addScalar("id", StandardBasicTypes.LONG);
		query.addScalar("constrCode", StandardBasicTypes.STRING);
		query.addScalar("stationCode", StandardBasicTypes.STRING);

		query.addScalar("receiverName", StandardBasicTypes.STRING);
		query.addScalar("deliveryGroupName", StandardBasicTypes.STRING);
		query.addScalar("type", StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(AssetHandOverDto.class));
		if (searchForm.getSize() > 0 && searchForm.getPage() > 0) {
			query.setMaxResults(searchForm.getSize());
			query.setFirstResult(searchForm.getFirstResult());
		}

		List<AssetHandOverDto> lstCount = query.list();
		int count = 
				 ((BigDecimal) countQueryNotByConstr.uniqueResult()).intValue();
		Page<AssetHandOverDto> page = new Page();
		page.setRows(lstCount);
		page.setPage(searchForm.getPage());
		page.setTotalRow(count);
		return page;

	}
	
	
	
	/**
	 * Tìm kiếm biên bản bàn giao không qua xây lắp paginae nâng cấp trmaj
	 * 20170524- bổ sung điều kiện Status_ca=2 cho biên bản bàn giao không qu xây lắp
	 * @param searchForm
	 * @return
	 */
	public Page<AssetHandOverDto> searchHandOverUpgradeConstructionPaginate(AssetHandOverSearchDto searchForm) {
		StringBuilder sbNotByConstructionSelect = new StringBuilder();
		StringBuilder sbNotByConstructionCount = new StringBuilder();
		StringBuilder sbNotByConstructionWhere = new StringBuilder();
		// SELECT
		sbNotByConstructionSelect.append(
				"select mo.HAND_OVER_DATE as handoverDate, mo.CODE as handoverCode, MO.HAND_OVER_ID as id , 2 as type");
		sbNotByConstructionSelect.append(" ,null as constrCode, null as stationCode");
		sbNotByConstructionSelect.append(" ,mo.receiver_name as receiverName, s.name as deliveryGroupName ");// loai
		sbNotByConstructionCount.append("select COUNT(HAND_OVER_ID) as ID ");
		// FROM
		sbNotByConstructionWhere.append(" from MER_HAND_OVER_INFO MO ");
		sbNotByConstructionWhere.append(" left join SYS_GROUP s on MO.DELIVERY_GROUP_ID=S.GROUP_ID ");
		sbNotByConstructionWhere.append(" inner join WARE_EXP_NOTE we on we.DELIVERY_NOTE_ID=MO.HAND_OVER_ID ");
		sbNotByConstructionWhere.append(" inner join CAT_WAREHOUSE cwh  on we.SOURCE_WH_ID=cwh.WAREHOUSE_ID and (cwh.CODE not like '%THUHOI' and (cwh.VIRTUAL_TYPE <> 1 or cwh.VIRTUAL_TYPE is null)) ");

		// WHERE
		//TYPE=1: bàn giao từ phiếu xuất
		//type_delivery_note=2 -> xuát đến công trình, bỏ  qua trang thai ky
		sbNotByConstructionWhere.append(
				" WHERE MO.IS_ACTIVE=1 and MO.IS_ACCEPTED=1  and MO.TYPE =1 and mo.type_delivery_note=2 and MO.CONSTRUCTION_ID IS NOT NULL AND (MO.IS_TO_ASSET is  null or MO.IS_TO_ASSET ='') ");

		java.util.Map<String, Object> paramsConstr = new HashMap<>();
		// List<Object> paramsMerHandOver=new ArrayList<>();

		if (searchForm.getFromHandoverDate() != null) {
			// TODO: APPROVAL_DATE
			sbNotByConstructionWhere.append(" and MO.HAND_OVER_DATE>=:fromHandoverDate");
			paramsConstr.put("fromHandoverDate", searchForm.getFromHandoverDate());
		}
		if (searchForm.getToHandoverDate() != null) {
			sbNotByConstructionWhere.append(" and MO.HAND_OVER_DATE<=:toHandoverDate");
			paramsConstr.put("toHandoverDate", UDate.toNextDate(searchForm.getToHandoverDate()));
		}
		if (UString.isNotNullAndWhitespace(searchForm.getHandoverCode())) {
			sbNotByConstructionWhere.append(" and MO.CODE like :handoverCode");
			paramsConstr.put("handoverCode", UString.toLikeString(searchForm.getHandoverCode().toUpperCase()));
		}
		if(searchForm.getUserSession()!=null){
			if(searchForm.getUserSession().getGroupId()!=null){
				sbNotByConstructionWhere.append(" and (s.path like :pathGroup or mo.delivery_group_id=:deliveryGroupId) ");
				paramsConstr.put("pathGroup", UString.toLikeString("/"+searchForm.getUserSession().getGroupId()+"/"));
				paramsConstr.put("deliveryGroupId", searchForm.getUserSession().getGroupId());
			}
		}
		String queryString = 
				sbNotByConstructionSelect.toString() + sbNotByConstructionWhere.toString()
				+ " order by handoverDate desc,id desc";
		String countQueryNotByConstrString = sbNotByConstructionCount.toString() + sbNotByConstructionWhere.toString();

		SQLQuery query = currentSession().createSQLQuery(queryString);
		SQLQuery countQueryNotByConstr = currentSession().createSQLQuery(countQueryNotByConstrString);
		for (String str : paramsConstr.keySet()) {
			query.setParameter(str, paramsConstr.get(str));
			countQueryNotByConstr.setParameter(str, paramsConstr.get(str));
		}

		query.addScalar("handoverDate", StandardBasicTypes.DATE);
		query.addScalar("handoverCode", StandardBasicTypes.STRING);
		query.addScalar("id", StandardBasicTypes.LONG);
		query.addScalar("constrCode", StandardBasicTypes.STRING);
		query.addScalar("stationCode", StandardBasicTypes.STRING);

		query.addScalar("receiverName", StandardBasicTypes.STRING);
		query.addScalar("deliveryGroupName", StandardBasicTypes.STRING);
		query.addScalar("type", StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(AssetHandOverDto.class));
		if (searchForm.getSize() > 0 && searchForm.getPage() > 0) {
			query.setMaxResults(searchForm.getSize());
			query.setFirstResult(searchForm.getFirstResult());
		}

		List<AssetHandOverDto> lstCount = query.list();
		int count = 
				 ((BigDecimal) countQueryNotByConstr.uniqueResult()).intValue();
		Page<AssetHandOverDto> page = new Page();
		page.setRows(lstCount);
		page.setPage(searchForm.getPage());
		page.setTotalRow(count);
		return page;
	}
	/*
	 * Tìm kiếm biên bà giao qua xây lắp paginate
	 */
	public Page<AssetHandOverDto> searchHandOverByConstructionPaginate(AssetHandOverSearchDto searchForm) {
		StringBuilder sbByConstructionSelect = new StringBuilder();
		StringBuilder sbByConstructionCount = new StringBuilder();
		StringBuilder sbByConstructionWhere = new StringBuilder();

		// SELECT
		sbByConstructionCount.append(" select count(CONSTRUCTION_ACCEPTANCE_ID) as ID");
		sbByConstructionSelect.append(
				"select a.CREATED_DATE as handoverDate, a.CODE as handoverCode, a.CONSTRUCTION_ACCEPTANCE_ID as id,1 as type");
		sbByConstructionSelect.append(" ,cct.CONSTR_CODE as constrCode,cct.station_Code stationCode");
		sbByConstructionSelect.append(" ,null as receiverName, null as deliveryGroupName ");
		// loai bien ban giao cho tai san qua xay lap																						

		// FROM
		sbByConstructionWhere.append(" from CONSTRUCTION_ACCEPTANCE a ");
		sbByConstructionWhere
				.append(" left join V_CONSTRUCTION_TSCD cct on cct.is_active=1 and cct.CONSTRUCT_ID=a.CONSTRUCT_ID ");
		sbByConstructionWhere.append(" where a.LONG_TERM_ASSET_ID  is  null  and a.status_ca = 2 and a.is_active=1");

		

		

		java.util.Map<String, Object> paramsConstr = new HashMap<>();
		// List<Object> paramsMerHandOver=new ArrayList<>();

		if (searchForm.getFromHandoverDate() != null) {
			// TODO: APPROVAL_DATE
			sbByConstructionWhere.append(" and a.CREATED_DATE>=:fromHandoverDate");
			paramsConstr.put("fromHandoverDate", searchForm.getFromHandoverDate());
		}
		if (searchForm.getToHandoverDate() != null) {
			sbByConstructionWhere.append(" and a.CREATED_DATE<=:toHandoverDate");
			paramsConstr.put("toHandoverDate", UDate.toNextDate(searchForm.getToHandoverDate()));
		}
		if (UString.isNotNullAndWhitespace(searchForm.getHandoverCode())) {
			sbByConstructionWhere.append(" and a.CODE like :handoverCode");
			paramsConstr.put("handoverCode", UString.toLikeString(searchForm.getHandoverCode().toUpperCase()));
		}
		String queryString = sbByConstructionSelect.toString() + sbByConstructionWhere.toString() 
				
				+ " order by handoverDate desc,id desc";
		String countQueryByConstrString = sbByConstructionCount.toString() + sbByConstructionWhere.toString();

		SQLQuery query = currentSession().createSQLQuery(queryString);
		SQLQuery countQueryByConstr = currentSession().createSQLQuery(countQueryByConstrString);
		for (String str : paramsConstr.keySet()) {
			query.setParameter(str, paramsConstr.get(str));
			countQueryByConstr.setParameter(str, paramsConstr.get(str));
		}

		query.addScalar("handoverDate", StandardBasicTypes.DATE);
		query.addScalar("handoverCode", StandardBasicTypes.STRING);
		query.addScalar("id", StandardBasicTypes.LONG);
		query.addScalar("constrCode", StandardBasicTypes.STRING);
		query.addScalar("stationCode", StandardBasicTypes.STRING);

		query.addScalar("receiverName", StandardBasicTypes.STRING);
		query.addScalar("deliveryGroupName", StandardBasicTypes.STRING);
		query.addScalar("type", StandardBasicTypes.LONG);
		query.setResultTransformer(Transformers.aliasToBean(AssetHandOverDto.class));
		if (searchForm.getSize() > 0 && searchForm.getPage() > 0) {
			query.setMaxResults(searchForm.getSize());
			query.setFirstResult(searchForm.getFirstResult());
		}

		List<AssetHandOverDto> lstCount = query.list();
		int count = ((BigDecimal) countQueryByConstr.uniqueResult()).intValue();
				
		Page<AssetHandOverDto> page = new Page();
		page.setRows(lstCount);
		page.setPage(searchForm.getPage());
		page.setTotalRow(count);
		return page;

	}

	/*
	 * Lấy ds mer_entity đã hình thành tài sản
	 */
	public List<LongTermAssetDto> getListMerInLtaDetailByHandOverId(Long handoverId) {

		String sql= "select  "
				+ " lta.national_name as nationalName"
				+ " ,lta.made_year as madeYear"
				+ " ,lta.description as description"
				+ " ,lta.lota_code as lotaCode"
				+ ",lta.lota_index as lotaIndex"
				+ ",lta.group_id as groupId"
				+ ",sg1.name as groupName"
				+ ",sg2.name as useGroupName"
				+ ",sg2.group_id as useGroupId"
				+ ",lta.long_term_asset_id as longTermAssetId"
				+ ",u.document_name as attachName"
				+ ",u.attach_Id as attachId"
				+ ",CM.NAME name"
				+ ",CM.CODE code"
				+ ",CM.IS_DEVICE isDevice"
				+ ",ME.SERIAL_NUMBER serialNumber"
				+ ",me.mer_entity_id as merEntityId"
				+ ",NVL(ltae.quantity,0) count"
				+ ",NVL(ltae.ORIGINAL_PRICE,0) originalPrice"
				+ ",m.HAND_OVER_ID as handOverId"
				+ ",lta.created_source createdSource"
				+ ",lta.voucher_Type voucherType"
				+ ",caac.CAAC_NAME as caacName"
				+ "	, caac.CAT_ASSET_CODE_ID catAssetCodeId"
				+ " from LONG_TERM_ASSET_ENTITIES ltae"
				+ " left join LONG_TERM_ASSETs lta on ltae.LONG_TERM_ASSET_ID=lta.LONG_TERM_ASSET_ID"
				+ " left join CAT_ASSET_CODEs caac on lta.CAT_ASSET_CODE_ID =caac.CAT_ASSET_CODE_ID"
				+ " left join MER_ENTITY me on ltae.MER_ENTITY_ID=me.MER_ENTITY_ID"
				+ " INNER JOIN CAT_MERCHANDISE CM ON ME.MER_ID = CM.MERCHANDISE_ID"
				+ " left join MER_HAND_OVER_INFO m on m.CODE=lta.HANDOVER_CODE"
				+ " left join SYS_GROUP sg1 on sg1.GROUP_ID=lta.GROUP_ID and sg1.status=1"
				+ " left join SYS_GROUP sg2 on sg2.GROUP_ID=lta.USE_GROUP_ID and sg2.status=1"
				+ " left join UTIL_ATTACHED_DOCUMENTS u on u.PARENT_ID=lta.LONG_TERM_ASSET_ID and u.type=:attachType"
				+ " where "
				+ " m.HAND_OVER_ID=:handOverId";
		
		

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
		query.addScalar("nationalName", new StringType());
		query.addScalar("madeYear", new LongType());		
		query.addScalar("lotaCode", new StringType());
		query.addScalar("lotaIndex", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("groupId", new LongType());
		query.addScalar("groupName", new StringType());
		query.addScalar("useGroupId", new LongType());
		query.addScalar("useGroupName", new StringType());
		query.addScalar("attachName", new StringType());
		query.addScalar("handOverId",new LongType());
		query.addScalar("createdSource",new StringType());
		query.addScalar("voucherType",new LongType());
		query.addScalar("caacName",new StringType());
		query.addScalar("catAssetCodeId",new LongType());
		query.addScalar("attachId",new LongType());
		
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));
	
		query.setParameter("handOverId", handoverId);		
		query.setParameter("attachType", this.attachAssetTypeKey);
		return query.list();
					
	}
	
	/*
	 * Lấy ds vat tu chua hinh thanh ts va nam trong bien ban ban giao
	 */	
	public List<LongTermAssetDto> getListMerNotCreateLtaByhandOverId(Long handoverId){
		String sql = "SELECT MHE.MER_ENTITY_ID merEntityId"	
				+ " ,CM.NAME name"
				+ " ,CM.CODE code "
				+ " ,CM.IS_DEVICE isDevice"
				+ " ,ME.SERIAL_NUMBER serialNumber"
				+ "	,ME.LONG_TERM_ASSET_ID longTermAssetId"
				+ " ,NVL(MHE.COUNT,0) count"
				+ " ,MHE.HAND_OVER_ID handOverId"
				+ " ,NVL(wee.ORIGINAL_PRICE,0) originalPrice"		
				+ " FROM MER_HAND_OVER_ENTITY MHE"
				+ " JOIN MER_HAND_OVER_INFO MHO ON MHO.HAND_OVER_ID = MHE.HAND_OVER_ID"
				+ " INNER JOIN MER_ENTITY ME ON MHE.MER_ENTITY_ID = ME.MER_ENTITY_ID"
				+ " INNER JOIN CAT_MERCHANDISE CM ON ME.MER_ID = CM.MERCHANDISE_ID"
				+ " INNER JOIN ware_exp_note_mer_entity wee on wee.MER_ENTITY_ID = MHE.mer_entity_id and wee.DELIVERY_NOTE_ID = MHO.DELIVERY_NOTE_ID"
				+ " WHERE MHE.HAND_OVER_ID = :handOverId AND (ME.LONG_TERM_ASSET_ID IS NULL )";
		
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
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));
	
		query.setParameter("handOverId", handoverId);
		return query.list();
	}
	/*
	 * Hanhls1:
	 * Xóa LtaEntity với handoverId
	 */
	public void deleteLTAEntityNotByConstructInAMerHandoverAndChange(MerHandOverInfo handOverInfo, Set<Long> lstLtaIdNotChange,Long voucherType) {
		String sql="DELETE  FROM LONG_TERM_ASSET_ENTITIES ltae "
				+ " WHERE exists (select s.LONG_TERM_ASSET_ID FROM  LONG_TERM_ASSETs s WHERE ltae.LONG_TERM_ASSET_ID=s.LONG_TERM_ASSET_ID AND  s.HANDOVER_CODE=:handOverCode and s.voucher_type=:voucherType )";
		SQLQuery query=currentSession().createSQLQuery(sql);
		query.setParameter("handOverCode", handOverInfo.getCode());
		query.setParameter("voucherType", voucherType);
		
		
		query.executeUpdate();				
	}
	/*
	 * Xóa những LTA không bị đánh dấu là giữ nguyên trong BBBD (MER_HAND_OVER_INFO)
	 */
	public void deleteLTANotByConstructInAMerHandoverAndChange(MerHandOverInfo handOverInfo, Set<Long> lstLtaIdNotChange,Long voucherType) {
		StringBuilder sb=new StringBuilder("UPDATE LONG_TERM_ASSETs s set s.IS_ACTIVE=0 Where s.HANDOVER_CODE=:handOverCode and s.voucher_type=:voucherType ");
		if(lstLtaIdNotChange!=null&& lstLtaIdNotChange.size()>0){
			sb.append(" and s.LONG_TERM_ASSET_ID not in :lstLtaNotChange " );
		}
				
//		String sql;
		SQLQuery query=currentSession().createSQLQuery(sb.toString());
		query.setParameter("handOverCode", handOverInfo.getCode());
		query.setParameter("voucherType", voucherType);
		if(lstLtaIdNotChange!=null&& lstLtaIdNotChange.size()>0){
			query.setParameterList("lstLtaNotChange", lstLtaIdNotChange);
		}
		
		query.executeUpdate();				
	}

	/*
	 * hanhls1
	 * Timf kiếm danh sách lta paging
	 * 
	 */
	public Page<LongTermAssetDto> searchLtaPaginate(LongTermAssetDto obj, PagingDto pageInfo) {
		String sqlSelect = "SELECT " + " DISTINCT LTA.LONG_TERM_ASSET_ID longTermAssetId,"
				+ "LTA.CAT_ASSET_CODE_ID catAssetCodeId," 
				+ "LTA.LOTA_CODE lotaCode," 
				+ "LTA.LOTA_INDEX lotaIndex,"
				+ "LTA.CONSTRUCT_ID constructId," 
				+ "LTA.STATION_ID stationId," + "LTA.EMPLOYEE_ID employeeId,"
				+ "LTA.GROUP_ID groupId," + "LTA.USE_GROUP_ID useGroupId," + "LTA.ORIGINAL_PRICE originalPrice,"
				+ "LTA.RESIDUAL_PRICE residualPrice," + "LTA.DEPRECIATION_START_DATE depreciationStartDate,"
				+ "LTA.DEPRECIATION_TIME depreciationTime," + "LTA.DEPRECIATION_RATE depreciationRate,"
				+ "LTA.DEPRECIATED_TIME depreciatedTime," + "LTA.DEPRECIATIED_VALUE depreciatiedValue,"
				+ "LTA.LOTA_TYPE lotaType," + "LTA.IS_ACTIVE isActive," + "LTA.CREATOR_ID creatorId,"
				+ "LTA.CREATED_GROUP_ID createdGroupId," + "LTA.CREATOR_DATE creatorDate," + "LTA.UPDATOR_ID updatorId,"
				+ "LTA.UPDATED_GROUP_ID updatedGroupId," + "LTA.UPDATOR_DATE updatorDate,"
				+ "LTA.WH_EXP_REQ_ID whExpReqId," + "LTA.VOUCHER_TYPE voucherType,"
				+ "LTA.CREATED_SOURCE createdSource," + "LTA.LOTA_STATUS lotaStatus,"
				+ "LTA.HANDOVER_CODE handoverCode," + "LTA.IS_SENT_ERP isSentErp," + "CS.STATION_CODE stationCode,"
				+ "CC.CONSTRT_NAME constructName," + "CAC.assetNameName assetNameName,"
				+ "CAC.assetSourceName assetSourceName," + "CAC.assetTypeName assetTypeName,"
				+ "CAC.assetGroupName assetGroupName," + "CAC.assetNameId assetNameId,"
				+ "CAC.assetSourceId assetSourceId," + "CAC.assetTypeId assetTypeId," + "CAC.assetGroupId assetGroupId,"
				+ "SG1.NAME groupName," 
				+ "SG2.NAME useGroupName, "
				+"LTA.UPGRADE_STATUS upgradeStatus"
				+ ",LTA.IS_OFFER_SLIP isOfferSlip ";
		
		
		String sqlCount="Select count(DISTINCT(LTA.LONG_TERM_ASSET_ID))";
				String sqlQWhere= " FROM LONG_TERM_ASSETS LTA"
				+ " LEFT JOIN  (Select  CAAC5.assetNameName, CAAC5.assetSourceName, CAAC5.assetTypeName, CAAC6.CAAC_NAME assetGroupName, CAAC5.assetNameId, "
				+ " CAAC5.assetSourceId, CAAC5.assetTypeId, CAAC6.CAT_ASSET_CODE_ID assetGroupId FROM"
				+ " (Select CAAC3.assetNameName,CAAC3.assetSourceName,CAAC3.assetNameId,CAAC3.assetSourceId,"
				+ "	CAAC4.CAT_ASSET_CODE_ID assetTypeId,CAAC4.CAAC_NAME assetTypeName,CAAC4.CAAC_PARENT_ID  FROM"
				+ " (Select  CAC1.assetNameName assetNameName,CAC2.CAAC_NAME assetSourceName,CAC1.assetNameId assetNameId,"
				+ "	CAC2.CAT_ASSET_CODE_ID assetSourceId,CAC2.CAAC_PARENT_ID FROM"
				+ "	(Select CAT_ASSET_CODE_ID assetNameId, CAAC_NAME assetNameName,CAAC_PARENT_ID parentId "
				+ "	From CAT_ASSET_CODES WHERE CAAC_LEVEL=4) CAC1 Join CAT_ASSET_CODES CAC2 ON CAC1.parentId=CAC2.CAT_ASSET_CODE_ID) "
				+ "	CAAC3 Join CAT_ASSET_CODES CAAC4 ON CAAC4.CAT_ASSET_CODE_ID=CAAC3.CAAC_PARENT_ID) "
				+ "	CAAC5 JOIN  CAT_ASSET_CODES CAAC6 ON CAAC6.CAT_ASSET_CODE_ID=CAAC5.CAAC_PARENT_ID) CAC "
				+ " ON LTA.CAT_ASSET_CODE_ID = CAC.assetNameId " 
				
				+ " LEFT JOIN CONSTR_CONSTRUCTIONS CC ON LTA.CONSTRUCT_ID=CC.CONSTRUCT_ID"
				+ " LEFT JOIN CAT_STATION CS ON CC.STATION_ID = CS.ID"
				+ " LEFT JOIN SYS_GROUP SG1 ON LTA.GROUP_ID=SG1.GROUP_ID"
				+ " LEFT JOIN SYS_GROUP SG2 ON LTA.USE_GROUP_ID=SG2.GROUP_ID"
				+ " LEFT JOIN LONG_TERM_ASSET_ENTITIES LTAE ON LTA.LONG_TERM_ASSET_ID = LTAE.LONG_TERM_ASSET_ID"
				+ " LEFT JOIN LONG_TERM_ASSET_COSTS LTAC ON LTA.LONG_TERM_ASSET_ID = LTAC.LONG_TERM_ASSET_ID"
				+ " WHERE LTA.IS_ACTIVE = 1 ";

		HashMap<String,Object> paramMap=new HashMap();
		StringBuilder stringBuilder = new StringBuilder(sqlQWhere);
		if (null != obj.getLongTermAssetId()) {
			stringBuilder.append(" AND LTA.LONG_TERM_ASSET_ID = :longTermAssetId");
			paramMap.put("longTermAssetId", obj.getLongTermAssetId());
		}
		if(UString.isNotNullAndWhitespace(obj.getLotaCode())){
			stringBuilder.append(" AND LTA.LOTA_CODE  like :lotaCode");
			paramMap.put("lotaCode", UString.toLikeString(obj.getLotaCode().trim()).toUpperCase());
		}
		if (null != obj.getStationId()) {
			stringBuilder.append(" AND LTA.STATION_ID = :stationId");
			paramMap.put("stationId", obj.getStationId());
		}
		if (null != obj.getConstructId()) {
			stringBuilder.append(" AND LTA.CONSTRUCT_ID = :constructId");
			paramMap.put("constructId", obj.getConstructId());
		}
		if (null != obj.getGroupId()) {
			stringBuilder.append(" AND LTA.GROUP_ID = :groupId");
			paramMap.put("groupId", obj.getGroupId());
		}
		if (null != obj.getUseGroupId()) {
			stringBuilder.append(" AND LTA.USE_GROUP_ID = :useGroupId");
			paramMap.put("useGroupId", obj.getUseGroupId());
		}	
		if (null != obj.getLotaStatus() && obj.getLotaStatus() != 2) {
			stringBuilder.append(" AND LTA.LOTA_STATUS = :lotaStatus");
			paramMap.put("lotaStatus", obj.getLotaStatus());
		}
		if (StringUtils.isNotEmpty(obj.getCreatedSource()) && !"0".equals(obj.getCreatedSource())) {
			stringBuilder.append(" AND LTA.CREATED_SOURCE = :createdSource");
			paramMap.put("createdSource", obj.getCreatedSource());
		}
		if (null != obj.getLotaType() && obj.getLotaType() != 0) {
			stringBuilder.append(" AND LTA.LOTA_TYPE = :lotaType");
			paramMap.put("lotaType", obj.getLotaType());
		}

		if (null != obj.getAssetNameId()) {
			stringBuilder.append(" AND LTA.CAT_ASSET_CODE_ID = :assetNameId");
			paramMap.put("assetNameId", obj.getAssetNameId());
		} else if (null != obj.getAssetSourceId()) {
			stringBuilder.append(" AND CAC.assetSourceId = :assetSourceId");
			paramMap.put("assetSourceId", obj.getAssetSourceId());
		} else if (null != obj.getAssetTypeId()) {
			stringBuilder.append(" AND CAC.assetTypeId = :assetTypeId");
			paramMap.put("assetTypeId", obj.getAssetTypeId());
		} else if (null != obj.getAssetGroupId()) {
			stringBuilder.append(" AND CAC.assetGroupId = :assetGroupId");
			paramMap.put("assetGroupId", obj.getAssetGroupId());
		}

		if (null != obj.getVoucherType() && obj.getVoucherType() != 0) {
			stringBuilder.append(" AND LTA.VOUCHER_TYPE = :voucherType");
			paramMap.put("voucherType", obj.getVoucherType());
		}

		if (null != obj.getDateUsingFrom()) {
			stringBuilder.append(" AND LTA.DEPRECIATION_START_DATE >= :dateUsingFrom");
			paramMap.put("dateUsingFrom", obj.getDateUsingFrom());
		}

		if (null != obj.getDateUsingTo()) {
			stringBuilder.append(" AND LTA.DEPRECIATION_START_DATE <= :dateUsingTo");
			paramMap.put("dateUsingTo", obj.getDateUsingTo());
		}

		if (null != obj.getIsSentErp() && obj.getIsSentErp() != -1) {
			stringBuilder.append(" AND LTA.IS_SENT_ERP = :isSentErp");
			paramMap.put("isSentErp", obj.getIsSentErp());
		}
		SQLQuery countQuery = currentSession().createSQLQuery(sqlCount+ stringBuilder.toString());
		stringBuilder.append(" ORDER BY LTA.LONG_TERM_ASSET_ID");

		SQLQuery query = currentSession().createSQLQuery(sqlSelect+ stringBuilder.toString());

		query.addScalar("longTermAssetId", LongType.INSTANCE);
		query.addScalar("catAssetCodeId", LongType.INSTANCE);
		query.addScalar("lotaCode", StringType.INSTANCE);
		query.addScalar("lotaIndex", LongType.INSTANCE);
		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("stationId", LongType.INSTANCE);
		query.addScalar("employeeId", LongType.INSTANCE);
		query.addScalar("groupId", LongType.INSTANCE);
		query.addScalar("useGroupId", LongType.INSTANCE);
		query.addScalar("originalPrice", LongType.INSTANCE);
		query.addScalar("residualPrice", LongType.INSTANCE);
		query.addScalar("depreciationStartDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("depreciationTime", LongType.INSTANCE);
		query.addScalar("depreciationRate", LongType.INSTANCE);
		query.addScalar("depreciatedTime", LongType.INSTANCE);
		query.addScalar("depreciatiedValue", LongType.INSTANCE);
		query.addScalar("lotaType", LongType.INSTANCE);
		query.addScalar("isActive", LongType.INSTANCE);
		query.addScalar("creatorId", LongType.INSTANCE);
		query.addScalar("createdGroupId", LongType.INSTANCE);
		query.addScalar("creatorDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("updatorId", LongType.INSTANCE);
		query.addScalar("updatedGroupId", LongType.INSTANCE);
		query.addScalar("updatorDate", StandardBasicTypes.TIMESTAMP);
		query.addScalar("whExpReqId", LongType.INSTANCE);
		query.addScalar("voucherType", LongType.INSTANCE);
		query.addScalar("createdSource", StringType.INSTANCE);
		query.addScalar("lotaStatus", LongType.INSTANCE);
		query.addScalar("handoverCode", StringType.INSTANCE);
		query.addScalar("isSentErp", LongType.INSTANCE);

		query.addScalar("stationCode", StringType.INSTANCE);
		query.addScalar("constructName", StringType.INSTANCE);
		query.addScalar("assetNameName", StringType.INSTANCE);
		query.addScalar("assetSourceName", StringType.INSTANCE);
		query.addScalar("assetTypeName", StringType.INSTANCE);
		query.addScalar("assetGroupName", StringType.INSTANCE);
		query.addScalar("groupName", StringType.INSTANCE);
		query.addScalar("useGroupName", StringType.INSTANCE);
		query.addScalar("assetNameId", LongType.INSTANCE);
		query.addScalar("assetSourceId", LongType.INSTANCE);
		query.addScalar("assetTypeId", LongType.INSTANCE);
		query.addScalar("assetGroupId", LongType.INSTANCE);
		query.addScalar("upgradeStatus", LongType.INSTANCE);
		query.addScalar("isOfferSlip", LongType.INSTANCE);		
//		query.addScalar("attachName", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetDto.class));
		for(String key:paramMap.keySet()){
			countQuery.setParameter(key, paramMap.get(key));
			query.setParameter(key,paramMap.get(key));
		}		
		if(pageInfo!=null){
			query.setMaxResults(pageInfo.getPageSize());
			query.setFirstResult(pageInfo.getFirstResult());
		}
		List<LongTermAssetDto> lst=query.list();
		int total=((BigDecimal)countQuery.uniqueResult()).intValue();
		Page<LongTermAssetDto> page=new Page<>();
		page.setRows(lst);
                if(pageInfo!=null){
                    page.setPage(pageInfo.getPage());
                }
		page.setTotalRow(total);
		return page;
	}

	/**
	 * Lay thong tin constructionInfo tu ma bien ban giao
	 * @param handoverCode
	 * @return null if not find
	 */
	public ConstructionAcceptanceDto getConstructionInfoFromConstructionAcceptanceCode(String handoverCode) {
		// TODO Auto-generated method stub
		String sql=Joiner.on(" ").join("SELECT c.constr_code constrCode, c.invest_project_name as investProjectName,c.invest_project_code as investProjectCode,c.invest_project_id as investProjectId  FROM V_CONSTRUCTION_TSCD c", "INNER JOIN CONSTRUCTION_ACCEPTANCE CA ON C.CONSTRUCT_ID = CA.CONSTRUCT_ID AND CA.IS_ACTIVE=1 AND  CA.CODE=:handoverCode ");
		SQLQuery query=currentSession().createSQLQuery(sql);
		query.addScalar("investProjectName",StandardBasicTypes.STRING);
		query.addScalar("investProjectCode",StandardBasicTypes.STRING);
		query.addScalar("investProjectId",StandardBasicTypes.LONG);
		query.addScalar("constrCode",StandardBasicTypes.STRING);
		
		query.setResultTransformer(Transformers.aliasToBean(ConstructionAcceptanceDto.class));
		query.setParameter("handoverCode", handoverCode);
		List lst=query.list();
		if(lst.size()>0){
			return (ConstructionAcceptanceDto)lst.get(0);
		}
		return null;
		
		
	}

	/**
	 * Layas danh sachs id biene banr ban giao hinh thanh tai san nang cap
	 * @param longTermAssetId
	 * @return
	 */
	public List<Long> getListMerHandOverUpgradeLtaByLtaId(Long longTermAssetId,List<Long> lstUpgradeStatus) {
		StringBuilder sqlBuilder=new StringBuilder("SELECT Distinct HAND_OVER_UPGRADE_ID handOverId from LONG_TERM_ASSET_ENTITIES ltae where ltae.UPGRADE_STATUS is not null   and ltae.LONG_TERM_ASSET_ID =:longTermAssetId");
		if(lstUpgradeStatus!=null&&!lstUpgradeStatus.isEmpty()){
			sqlBuilder.append("  and ltae.UPGRADE_STATUS in :lstUpgradeStatus");
		}
	
		SQLQuery query=currentSession().createSQLQuery(sqlBuilder.toString());
		query.setParameter("longTermAssetId", longTermAssetId);
		if(lstUpgradeStatus!=null&&!lstUpgradeStatus.isEmpty()){
			query.setParameterList("lstUpgradeStatus", lstUpgradeStatus);
		}
		query.addScalar("handOverId",StandardBasicTypes.LONG);
		return query.list();
		
	}
	/**
	 * Cập nhật danh sách biển bản bàn giao về chưa hình thành tài sản
	 * @param lstHandOverId
	 */
	public void updateMerhandOverInfoToStatusNotAssetCreatedByHandOverId( List<Long> lstHandOverId) {
		if(lstHandOverId.isEmpty()){
			return;
		}
		String sql = "UPDATE MER_HAND_OVER_INFO SET IS_TO_ASSET=null where IS_TO_ASSET is not null and hand_over_id in  :lstHandOverId";
		SQLQuery query = currentSession().createSQLQuery(sql);		
		query.setParameterList("lstHandOverId", lstHandOverId);
		query.executeUpdate();
	}
	
	/**
	 * Cập nhật danh sách biển bản bàn giao về chưa hình thành tài sản
	 * @param lstHandOverId
	 */
	public void deleteListLTAEntityInUpgradeProcess(Long longTermAssetId,List<Long> lstHandOverId) {
		if(lstHandOverId==null||lstHandOverId.isEmpty()){
			return;
		}
		String sql = "DELETE LONG_TERM_ASSET_ENTITIES  where UPGRADE_STATUS is not null and LONG_TERM_ASSET_ID =  :longTermAssetId and HAND_OVER_UPGRADE_ID in :lstHandOverId";
		SQLQuery query = currentSession().createSQLQuery(sql);		
		query.setParameter("longTermAssetId", longTermAssetId);
		query.setParameterList("lstHandOverId", lstHandOverId);		
		query.executeUpdate();
	}
	
	
	
	/**
	 * Tìm kiếm công trình for autocomplete
	 * @param obj
	 * @return
	 * @throws DatabaseException
	 */
	public List<VConstructionOfferSlip> autocompleteConstrFromOfferSlip(AutocompleteSearchDto obj){
		String sql = "SELECT CONSTRUCT_ID constructId" + " ,CONSTR_NAME constructName" + " ,CONSTR_CODE constructCode"
				+ ", INVEST_PROJECT_CODE investProjectCode, STATION_CODE stationCode"
				+ " FROM V_CONSTRUCTION_TSCD" + " WHERE IS_ACTIVE = 1";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <= :pageSize");
		stringBuilder.append(StringUtils.isNotEmpty(obj.getKeySearch())
				? " AND (upper(CONSTR_NAME) LIKE upper(:keySearch)" + (StringUtils.isNotEmpty(obj.getKeySearch())
						? " OR upper(CONSTR_CODE) LIKE upper(:keySearch)" : "") + ")"
				: (StringUtils.isNotEmpty(obj.getKeySearch()) ? "AND upper(CONSTR_CODE) LIKE upper(:keySearch)" : ""));
		//stringBuilder.append(obj.getParentId() != null ? " AND STATION_ID = :parentId" : "");
		stringBuilder.append(" ORDER BY CONSTR_NAME");

		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("constructId", LongType.INSTANCE);
		query.addScalar("constructName", StringType.INSTANCE);
		query.addScalar("investProjectCode", StringType.INSTANCE);
		query.addScalar("constructCode", StringType.INSTANCE);
		query.addScalar("stationCode", StringType.INSTANCE);

		query.setResultTransformer(Transformers.aliasToBean(VConstructionOfferSlip.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch() + "%");
		}
		
		query.setParameter("pageSize", obj.getPageSize());

		return query.list();
	}
	
}
