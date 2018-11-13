package com.viettel.asset.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.viettel.asset.bo.LongTermAssetCost;
import com.viettel.asset.bo.LongTermAssetEntity;
import com.viettel.asset.dto.LongTermAssetEntityDto;
import com.viettel.asset.dto.MerEntityDto;
import com.viettel.asset.dto.ReportS32LongTermAssetEntityMerDto;
import java.util.Optional;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

@Repository("longTermAssetEntityDao")
public class LongTermAssetEntityDao extends HibernateDao<LongTermAssetEntity, Long> {

    public List<LongTermAssetEntity> getByLongTermAssetId(Long longTermAssetId) throws Exception {
        List<LongTermAssetEntity> lst = find(LongTermAssetEntity.Columns.LONG_TERM_ASSET_ID, longTermAssetId);
        return lst;
    }

    @SuppressWarnings("unchecked")
    public List<ReportS32LongTermAssetEntityMerDto> getReportS32LongTermAssetEntity(Long longTermAssetId) {
        String sql = "select rownum as num,"
                + "ltae.quantity as quantity,"
                + "ltae.original_price as originalPrice,"
                + "c.name as merName,"
                + "cu.name as merUnitName"
                + " from  LONG_TERM_ASSET_ENTITIES ltae "
                + " inner join LONG_TERM_ASSETs lta on ltae.LONG_TERM_ASSET_ID=lta.LONG_TERM_ASSET_ID "
                + " inner join MER_ENTITY m on ltae.MER_ENTITY_ID=m.MER_ENTITY_ID "
                + " inner join CAT_MERCHANDISE c on c.MERCHANDISE_ID = m.mer_id "
                + " left join CAT_UNIT cu on c.unit_id=cu.UNIT_ID "
                + " where lta.LONG_TERM_ASSET_ID=:longTermAssetId";
        SQLQuery query = currentSession().createSQLQuery(sql);
        query.setParameter("longTermAssetId", longTermAssetId);
        query.setResultTransformer(Transformers.aliasToBean(ReportS32LongTermAssetEntityMerDto.class));

        query.addScalar("num", StandardBasicTypes.STRING);
        query.addScalar("quantity", StandardBasicTypes.LONG);
        query.addScalar("originalPrice", StandardBasicTypes.LONG);
        query.addScalar("merName", StandardBasicTypes.STRING);
        query.addScalar("merUnitName", StandardBasicTypes.STRING);
        return query.list();

    }

    @SuppressWarnings("unchecked")
    public List<LongTermAssetEntityDto> getSerial(Long longTermAssetId) {
        String sql = "Select LTAE.LONG_TERM_ASSET_ENTITY_ID longTermAssetEntityId,"
                + " me.SERIAL_NUMBER serialNumber,"
                + " cu.NAME unit,"
                + " cm.NAME name,"
                + " cm.CODE code,"
                + " LTAE.QUANTITY * LTAE.ORIGINAL_PRICE  total,"
                + " LTAE.QUANTITY quantity,"
                + " LTAE.ORIGINAL_PRICE originalPrice"
                + " From LONG_TERM_ASSET_ENTITIES LTAE "
                + " join Mer_entity me on LTAE.MER_ENTITY_ID = me.MER_ENTITY_ID"
                + " join CAT_MERCHANDISE cm on me.MER_ID=cm.MERCHANDISE_ID "
                + " left join CAT_UNIT cu on cu.UNIT_ID=cm.UNIT_ID WHERE cm.IS_DEVICE=1"
                + " AND LTAE.LONG_TERM_ASSET_ID =:longTermAssetId and  me.SERIAL_NUMBER is not null and substr(me.SERIAL_NUMBER,0,1) <> '-'";
        SQLQuery query = currentSession().createSQLQuery(sql);
        query.setParameter("longTermAssetId", longTermAssetId);
        query.setResultTransformer(Transformers.aliasToBean(LongTermAssetEntityDto.class));

        query.addScalar("longTermAssetEntityId", StandardBasicTypes.LONG);
        query.addScalar("serialNumber", StandardBasicTypes.STRING);
        query.addScalar("quantity", StandardBasicTypes.DOUBLE);
        query.addScalar("originalPrice", StandardBasicTypes.LONG);
        query.addScalar("name", StandardBasicTypes.STRING);
        query.addScalar("unit", StandardBasicTypes.STRING);
        query.addScalar("code", StandardBasicTypes.STRING);
        query.addScalar("total", StandardBasicTypes.DOUBLE);

        return query.list();

    }

    @SuppressWarnings("unchecked")
    public List<LongTermAssetEntityDto> getNoneSerial(Long longTermAssetId) {
        String sql = "Select LTAE.LONG_TERM_ASSET_ENTITY_ID longTermAssetEntityId,"
                + " me.SERIAL_NUMBER serialNumber,"
                + " cu.NAME unit,"
                + " cm.NAME name,"
                + " cm.CODE code,"
                + " LTAE.QUANTITY * LTAE.ORIGINAL_PRICE  total,"
                + " LTAE.QUANTITY quantity,"
                + " LTAE.ORIGINAL_PRICE originalPrice"
                + " From LONG_TERM_ASSET_ENTITIES LTAE "
                + " join Mer_entity me on LTAE.MER_ENTITY_ID = me.MER_ENTITY_ID"
                + " join CAT_MERCHANDISE cm on me.MER_ID=cm.MERCHANDISE_ID "
                + " left join CAT_UNIT cu on cu.UNIT_ID=cm.UNIT_ID WHERE cm.IS_DEVICE=0"
                + " AND LTAE.LONG_TERM_ASSET_ID =:longTermAssetId and ( me.SERIAL_NUMBER is  null or substr(me.SERIAL_NUMBER,0,1) = '-')";
        SQLQuery query = currentSession().createSQLQuery(sql);
        query.setParameter("longTermAssetId", longTermAssetId);
        query.setResultTransformer(Transformers.aliasToBean(LongTermAssetEntityDto.class));

        query.addScalar("longTermAssetEntityId", StandardBasicTypes.LONG);
        query.addScalar("serialNumber", StandardBasicTypes.STRING);
        query.addScalar("quantity", StandardBasicTypes.DOUBLE);
        query.addScalar("originalPrice", StandardBasicTypes.LONG);
        query.addScalar("name", StandardBasicTypes.STRING);
        query.addScalar("unit", StandardBasicTypes.STRING);
        query.addScalar("code", StandardBasicTypes.STRING);
        query.addScalar("total", StandardBasicTypes.DOUBLE);

        return query.list();

    }

    @Deprecated
    //Chuyển sang truy vẫn từ bảng CONSTR_ACCEPT_MER_LIST
    public List<MerEntityDto> getListMerEntityByConstructId(Long constructId, Optional<MerEntityDto> dto) {
        String sql = "SELECT M.MER_ID merId"
                + ",M.MER_ENTITY_ID merEntityId"
                + ",M.SERIAL_NUMBER serialNumber"
                + ",M.MER_WEIGHT merWeight"
                + ",M.VND_UNIT_PRICE vndUnitPrice"
                + ",CM.CODE code"
                + ",CM.NAME name"
                + ",CM.UNIT_ID unitId"
                + " FROM CAT_MERCHANDISE CM INNER JOIN MER_ENTITY M ON CM.MERCHANDISE_ID = M.MER_ID"
                + " WHERE  (M.MER_WEIGHT IS NOT NULL AND M.MER_WEIGHT <> 0) AND (M.VND_UNIT_PRICE IS NOT NULL AND M.VND_UNIT_PRICE <> 0)";

        StringBuilder stringBuilder = new StringBuilder(sql);
        java.util.Map<String, Object> params = new java.util.HashMap();
        if (constructId != null) {
            stringBuilder.append(" AND M.CONSTRUCTION_ID = :constructionId");
            params.put("constructionId", constructId);
        }
        if (dto!=null) {

            if (dto.get().getLongTermAssetId() != null) {
                stringBuilder.append(" AND M.LONG_TERM_ASSET_ID = :longTermAssetId ");
                params.put("longTermAssetId", dto.get().getLongTermAssetId());
            }
        }

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

        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }

        return query.list();
    }
    
    public List<MerEntityDto> getListConstrAccepMerListByConstructId(Long constructId, Optional<MerEntityDto> dto) {
        String sql = "SELECT M.MER_ID merId"
                + ",M.MER_ENTITY_ID merEntityId"
                + ",M.SERIAL_NUMBER serialNumber"
              //  + ",M.MER_WEIGHT merWeight"
                +" ,cm.quantity merWeight"
//                + ",M.VND_UNIT_PRICE vndUnitPrice"
                 +",M.ORIGINAL_PRICE as vndUnitPrice"
                + ",CM.CODE code"
                + ",CM.NAME name"
                + ",CM.UNIT_ID unitId"
                + ",CU.name unitName"
                + " FROM CONSTR_MERCHANDISE cm, MER_ENTITY M"
                + " INNER JOIN CAT_MERCHANDISE CM   ON CM.MERCHANDISE_ID = M.MER_ID"
                + " LEFT JOIN CAT_UNIT cu on cu.UNIT_ID=cm.UNIT_ID and cu.IS_ACTIVE=1"
                + " WHERE  (cm.quantity IS NOT NULL AND cm.quantity <> 0) AND (M.original_price IS NOT NULL AND M.original_price <> 0)"
                + " AND M.IS_TEMP=0 and M.MER_ENTITY_ID=cm.MER_ENTITY_ID";

        StringBuilder stringBuilder = new StringBuilder(sql);
        java.util.Map<String, Object> params = new java.util.HashMap();
        if (constructId != null) {
            stringBuilder.append(" AND CM.CONSTRUCT_ID = :constructionId");
            params.put("constructionId", constructId);
        }
        if (dto!=null) {

            if (dto.get().getLongTermAssetId() != null) {
                stringBuilder.append(" AND M.LONG_TERM_ASSET_ID = :longTermAssetId ");
                params.put("longTermAssetId", dto.get().getLongTermAssetId());
            }
        }

        SQLQuery query = currentSession().createSQLQuery(stringBuilder.toString());

        query.addScalar("merId", LongType.INSTANCE);
        query.addScalar("merEntityId", LongType.INSTANCE);
        query.addScalar("serialNumber", StringType.INSTANCE);
        query.addScalar("merWeight", new DoubleType());
        query.addScalar("vndUnitPrice", LongType.INSTANCE);
        query.addScalar("code", StringType.INSTANCE);
        query.addScalar("name", StringType.INSTANCE);
        query.addScalar("unitId", LongType.INSTANCE);
        query.addScalar("unitName", StringType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(MerEntityDto.class));

        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }

        return query.list();
    }

}
