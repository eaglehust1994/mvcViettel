package com.viettel.asset.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import com.viettel.asset.bo.MerHandOverEntity;
import com.viettel.asset.dto.MerEntityDto;
import com.viettel.asset.dto.MerHandOverEntityDto;
import com.viettel.asset.dto.search.AssetMerHandoverEntityDto;

@Repository("merHandOverEntityDao")
public class MerHandOverEntityDao extends HibernateDao<MerHandOverEntity, Long> {

	/**
	 * Su dung voi chuc nang cu
	 * @param merEntityId
	 * @return
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<MerHandOverEntityDto> getHanOverId(Long merEntityId) {
		String sql = "SELECT MHO.HAND_OVER_ID handOverId FROM MER_HAND_OVER_INFO MHO JOIN MER_HAND_OVER_ENTITY MHE ON MHO.HAND_OVER_ID = MHE.HAND_OVER_ID WHERE MHE.MER_ENTITY_ID =:merEntity";
		StringBuilder stringBuilder = new StringBuilder(sql);
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("handOverId", LongType.INSTANCE);
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverEntityDto.class));
		query.setParameter("merEntity", merEntityId);
		
		return  query.list();
	}

	/*
	 * Khong su dung de lay merhandOver nua
	 */
	@Deprecated
	public MerHandOverEntityDto getDataEntity(MerHandOverEntityDto objMer) {
		String sql = "select NVL(mh.COUNT,0) count , NVL(wee.ORIGINAL_PRICE,0) originalPrice from mer_hand_over_info mi"
				+ " join mer_hand_over_entity mh on mi.HAND_OVER_ID = mh.HAND_OVER_ID"
				+ " join mer_entity me on me.mer_entity_id = mh.mer_entity_id"
				+ " join cat_merchandise cm on me.mer_id = cm.merchandise_id"
				+ "	join ware_exp_note_mer_entity wee on wee.MER_ENTITY_ID = mh.mer_entity_id and wee.DELIVERY_NOTE_ID = mi.DELIVERY_NOTE_ID"
				+ " where mh.mer_entity_id = :merEntity and  mh.HAND_OVER_ID = :handoverid";
																
		StringBuilder stringBuilder = new StringBuilder(sql);
		SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("originalPrice", new LongType());
		query.addScalar("count", new DoubleType());
		query.setResultTransformer(Transformers.aliasToBean(MerHandOverEntityDto.class));
		query.setParameter("merEntity", objMer.getMerEntityId());
		query.setParameter("handoverid", objMer.getHandOverId());
		
		return  (MerHandOverEntityDto) query.uniqueResult();
	}

	public List<AssetMerHandoverEntityDto> getAssetMerHandOverEntityByMerHandOverId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
