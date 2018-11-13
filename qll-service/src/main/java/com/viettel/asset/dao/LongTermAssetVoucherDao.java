package com.viettel.asset.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.viettel.asset.bo.LongTermAssetCost;
import com.viettel.asset.bo.LongTermAssetVoucher;
import com.viettel.asset.dto.LongTermAssetEntityDto;
import com.viettel.asset.dto.LongTermAssetVoucherDto;

@Repository("longTermAssetVoucherDao")
public class LongTermAssetVoucherDao extends HibernateDao<LongTermAssetVoucher, Long> {

	public List<LongTermAssetVoucher> getByLongTermAssetId(Long longTermAssetId) throws Exception{
		List<LongTermAssetVoucher> lst= find(LongTermAssetVoucher.Columns.OBJECT_ID,longTermAssetId);
		return lst;
	}
	
	@SuppressWarnings("unchecked")
	public List<LongTermAssetVoucherDto> getDistri(Long longTermAssetId){
		String sql= "SELECT "
				 +"LTAV.VOUCHER_CODE voucherCode,"
				 +"LTAV.VOUCHER_DATE voucherDate,"
				 +"LTAV.VOUCHER_VALUE voucherValue,"
				 +"LTAC.LOAC_TYPE loacType "
				 +" FROM LONG_TERM_ASSET_VOUCHERS LTAV "
				 +"JOIN LONG_TERM_ASSET_COSTS LTAC ON LTAV.OBJECT_ID = LTAC.LONG_TERM_ASSET_COST_ID "
				 +"WHERE LTAV.VOUCHER_TYPE=1 AND  LTAC.LONG_TERM_ASSET_ID =:longTermAssetId";
		
		SQLQuery query=currentSession().createSQLQuery(sql);
		query.setParameter("longTermAssetId", longTermAssetId);
		query.setResultTransformer(Transformers.aliasToBean(LongTermAssetVoucherDto.class));
		
		query.addScalar("voucherCode",StandardBasicTypes.STRING);
		query.addScalar("voucherDate",StandardBasicTypes.DATE);
		query.addScalar("voucherValue",StandardBasicTypes.LONG);
		query.addScalar("loacType",StandardBasicTypes.LONG);
		
		return query.list();
				
				
	}
}
