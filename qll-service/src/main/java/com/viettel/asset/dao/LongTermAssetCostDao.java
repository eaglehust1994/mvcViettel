package com.viettel.asset.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.viettel.asset.bo.LongTermAssetCost;
import com.viettel.asset.dto.LongTermAssetCostDto;

@Repository("longTermAssetCostDao")
public class LongTermAssetCostDao extends HibernateDao<LongTermAssetCost, Long> {

	@Value("${catAssetVourcher.attachTypeKey}")
	private Long assetVoucherAttachType;
	
	
	public List<LongTermAssetCost> getByLongTermAssetId(Long longTermAssetId) throws Exception{
		List<LongTermAssetCost> lst= find(LongTermAssetCost.Columns.LONG_TERM_ASSET_ID,longTermAssetId);
		return lst;
	}
	
	public List<LongTermAssetCostDto> doSearch(LongTermAssetCostDto obj) throws Exception{
		String sql = "SELECT AC.LONG_TERM_ASSET_COST_ID longTermAssetCostId"
				+ " ,AC.LOAC_TYPE loacType"
				+ " ,AC.LOAC_VALUE loacValue"
				+ " ,AC.LONG_TERM_ASSET_ID longTermAssetCostId"
				+ " ,AV.VOUCHER_CODE voucherCode"
				+ " ,AV.VOUCHER_DATE voucherDate"
				+ " ,AV.VOUCHER_VALUE voucherValue"
				+ " ,AV.VOUCHER_TYPE voucherType"
				+ " ,UAD.DOCUMENT_NAME attachName,"
				+ " UAD.ATTACH_ID attachId"
				+ ",AC.ESTIMATE_LOAC_VALUE estimateLoacValue"
				+ ",AC.CONTENT_COST contentCost"
				+ ",AC.VOUCHER_STATUS voucherStatus"
				+ " FROM LONG_TERM_ASSET_COSTS AC INNER JOIN LONG_TERM_ASSET_VOUCHERS AV ON AC.LONG_TERM_ASSET_COST_ID = AV.OBJECT_ID"
				+ " LEFT JOIN UTIL_ATTACHED_DOCUMENTS UAD ON AV.LONG_TERM_ASSET_VOUCHER_ID = UAD.PARENT_ID AND UAD.TYPE = :attachType"
				+ " WHERE VOUCHER_TYPE = 1";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(obj.getLongTermAssetId() != null ? " AND AC.LONG_TERM_ASSET_ID = :longTermAssetId" : "");
		
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("longTermAssetCostId", LongType.INSTANCE);
		query.addScalar("loacType", LongType.INSTANCE);
		query.addScalar("loacValue", LongType.INSTANCE);
		query.addScalar("longTermAssetCostId", LongType.INSTANCE);
		query.addScalar("voucherCode", StringType.INSTANCE);
		query.addScalar("voucherDate", new DateType());
		query.addScalar("voucherValue", LongType.INSTANCE);
		query.addScalar("voucherType", LongType.INSTANCE);
		query.addScalar("attachName", StringType.INSTANCE);
		query.addScalar("attachId", LongType.INSTANCE);
		
		query.addScalar("estimateLoacValue", LongType.INSTANCE);		
		query.addScalar("contentCost", LongType.INSTANCE);
		query.addScalar("voucherStatus", LongType.INSTANCE);
		
		
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetCostDto.class));
		
		if (obj.getLongTermAssetId() != null){
			query.setParameter("longTermAssetId", obj.getLongTermAssetId());
		}
		query.setParameter("attachType", assetVoucherAttachType);
		
		return query.list();
	}
}
