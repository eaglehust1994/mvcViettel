package com.viettel.asset.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.viettel.asset.bo.LongTermAssetAttachDetail;
import com.viettel.asset.bo.LongTermAssetCost;
import com.viettel.asset.dto.LongTermAssetAttachDetailDto;
import com.viettel.asset.dto.LongTermAssetCostDto;

@Repository("longTermAssetAttachDetailDao")
public class LongTermAssetAttachDetailDao extends HibernateDao<LongTermAssetAttachDetail, Long> {

	@Value("${ltaAttachDetail.attachTypeKey}")
	private Long longTermAssetAttachDetailTypeKey;
	public List<LongTermAssetAttachDetailDto> getLtaAttachDetail(LongTermAssetCostDto obj) {
		String sql = "SELECT LTAAD.LTA_ATTACH_DETAIL_ID ltaAttachDetailId"
				+ ",LTAAD.LONG_TERM_ASSET_ID  longTermAssetId"
				+" ,LTAAD.CONTENT content"
				+ " ,UAD.DOCUMENT_NAME attachName"
				+ " ,UAD.ATTACH_ID attachId"				
				+ " FROM LONG_TERM_ASSET_ATTACH_DETAIL LTAAD "
				+ " LEFT JOIN UTIL_ATTACHED_DOCUMENTS UAD ON LTAAD.LTA_ATTACH_DETAIL_ID = UAD.PARENT_ID AND UAD.TYPE = :attachType"
				+ " WHERE 1 = 1";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(obj.getLongTermAssetId() != null ? " AND LTAAD.LONG_TERM_ASSET_ID = :longTermAssetId" : "");
		
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("longTermAssetId", LongType.INSTANCE);		
		query.addScalar("ltaAttachDetailId", LongType.INSTANCE);
		query.addScalar("content", StringType.INSTANCE);
		
		
		query.addScalar("attachName", StringType.INSTANCE);
		query.addScalar("attachId", LongType.INSTANCE);			
		
		
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetAttachDetailDto.class));
		
		if (obj.getLongTermAssetId() != null){
			query.setParameter("longTermAssetId", obj.getLongTermAssetId());
		}
		query.setParameter("attachType", longTermAssetAttachDetailTypeKey);
		
		return query.list();
	}


	
	
}
