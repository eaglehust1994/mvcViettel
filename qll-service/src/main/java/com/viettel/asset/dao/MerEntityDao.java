package com.viettel.asset.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.viettel.asset.bo.MerEntity;
import com.viettel.asset.dto.MerEntityDto;
import com.viettel.asset.dto.MerHandOverInfoDto;

@Repository("merEntityDao")
public class MerEntityDao extends HibernateDao<MerEntity, Long> {
	
	public List<MerEntityDto> doSearch(MerEntityDto obj) {
		String sql = "SELECT M.MER_ID merId"
				+ ",M.MER_ENTITY_ID merEntityId"
				+ ",M.SERIAL_NUMBER serialNumber"
				+ ",M.MER_WEIGHT merWeight"
				+ ",M.VND_UNIT_PRICE vndUnitPrice"
				+ ",CM.CODE code"		
				+ ",CM.NAME name"
				+ ",CM.UNIT_ID unitId"
				+" FROM CAT_MERCHANDISE CM INNER JOIN MER_ENTITY M ON CM.MERCHANDISE_ID = M.MER_ID"
				+" WHERE 1 = 1 AND (M.MER_WEIGHT IS NOT NULL AND M.MER_WEIGHT <> 0) AND (M.VND_UNIT_PRICE IS NOT NULL AND M.VND_UNIT_PRICE <> 0)";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(obj.getConstructionId() != null ? " AND M.CONSTRUCTION_ID = :constructionId" : "");
		stringBuilder.append(obj.getLongTermAssetId() != null ? " AND M.LONG_TERM_ASSET_ID = :longTermAssetId" : "");
		
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("merId", LongType.INSTANCE);
		query.addScalar("merEntityId", LongType.INSTANCE);
		query.addScalar("serialNumber", StringType.INSTANCE);
		query.addScalar("merWeight", new DoubleType());
		query.addScalar("vndUnitPrice", LongType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("name", StringType.INSTANCE);
		query.addScalar("unitId", LongType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(MerEntityDto.class));
		
		if (obj.getConstructionId() != null){
			query.setParameter("constructionId", obj.getConstructionId());
		}
		if (obj.getLongTermAssetId() != null){
			query.setParameter("longTermAssetId", obj.getLongTermAssetId());
		}
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<MerEntityDto> getMerEntityId(Long id) {
		String sql = "SELECT MER_ENTITY_ID merEntityId FROM MER_ENTITY WHERE LONG_TERM_ASSET_ID =:longTermAssetId";
		StringBuilder stringBuilder = new StringBuilder(sql);
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("merEntityId", LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(MerEntityDto.class));
		query.setParameter("longTermAssetId", id);
		
		return query.list();
	}
	
	
}
