package com.viettel.ims.dao;

import java.util.List;

import com.viettel.ims.bo.CatPartnerBO;
import com.viettel.ims.dto.CatPartnerDTO;




//import com.viettel.erp.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("catPartnerDAO")
public class CatPartnerDAO extends BaseFWDAOImpl<CatPartnerBO, Long> {

    public CatPartnerDAO() {
        this.model = new CatPartnerBO();
    }

    public CatPartnerDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<CatPartnerDTO> doSearch(CatPartnerDTO criteria) {
    	StringBuilder stringBuilder = new StringBuilder("select ");
		stringBuilder.append("T1.CAT_PARTNER_ID catPartnerId ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.TAX_CODE taxCode ");
		stringBuilder.append(",T1.FAX fax ");
		stringBuilder.append(",T1.PHONE phone ");
		stringBuilder.append(",T1.ADDRESS address ");
		stringBuilder.append(",T1.REPRESENT represent ");
		stringBuilder.append(",T1.PARTNER_TYPE partnerType ");
		stringBuilder.append(",T1.DESCRIPTION description ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.CREATED_USER_ID createdUserId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.UPDATED_USER_ID updatedUserId ");
		stringBuilder.append(",T1.UPDATED_GROUP_ID updatedGroupId ");
    	
    	stringBuilder.append("FROM CTCT_CAT_OWNER.CAT_PARTNER T1 where 1=1 ");    	
    
    	
		
		
    	if (null != criteria.getKeySearch()) {
			stringBuilder.append("AND (upper(T1.CODE) like upper(:key) or upper(T1.NAME) like upper(:key))");
		}
		if (null != criteria.getPartnerType()) {
			stringBuilder.append("AND T1.PARTNER_TYPE = :partnerType ");
		}
		
		if (null != criteria.getStatus()) {
			stringBuilder.append("AND T1.STATUS = :status ");
		}
		
		stringBuilder.append("ORDER BY T1.CAT_PARTNER_ID DESC");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");
    	

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
			query.addScalar("catPartnerId", new LongType());
			query.addScalar("code", new StringType());
			query.addScalar("name", new StringType());
			query.addScalar("taxCode", new StringType());
			query.addScalar("fax", new StringType());
			query.addScalar("phone", new StringType());
			query.addScalar("address", new StringType());
			query.addScalar("represent", new StringType());
			query.addScalar("partnerType", new LongType());
			query.addScalar("description", new StringType());
			query.addScalar("status", new LongType());
			query.addScalar("createdDate", new DateType());
			query.addScalar("createdUserId", new LongType());
			query.addScalar("createdGroupId", new LongType());
			query.addScalar("updatedDate", new DateType());
			query.addScalar("updatedUserId", new LongType());
			query.addScalar("updatedGroupId", new LongType());
			
			
		
    	
	
		if (null != criteria.getPartnerType()) {
			query.setParameter("partnerType", criteria.getPartnerType());
			queryCount.setParameter("partnerType", criteria.getPartnerType());
		}
		
		if (null != criteria.getStatus()) {
			query.setParameter("status", criteria.getStatus());
			queryCount.setParameter("status", criteria.getStatus());
		}
		
		if (null != criteria.getKeySearch()) {
			query.setParameter("key","%" + criteria.getKeySearch() +"%");
			queryCount.setParameter("key","%" + criteria.getKeySearch() +"%");
		}
		query.setResultTransformer(Transformers.aliasToBean(CatPartnerDTO.class));    	
		
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1) * criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		List ls = query.list();
		criteria.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return ls;
	}  
	
	public CatPartnerDTO findByCode(String code) {
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CAT_PARTNER_ID catPartnerId ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.TAX_CODE taxCode ");
		stringBuilder.append(",T1.FAX fax ");
		stringBuilder.append(",T1.PHONE phone ");
		stringBuilder.append(",T1.ADDRESS address ");
		stringBuilder.append(",T1.REPRESENT represent ");
		stringBuilder.append(",T1.PARTNER_TYPE partnerType ");
		stringBuilder.append(",T1.DESCRIPTION description ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.CREATED_USER_ID createdUserId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.UPDATED_USER_ID updatedUserId ");
		stringBuilder.append(",T1.UPDATED_GROUP_ID updatedGroupId ");
    	
    	stringBuilder.append("FROM CTCT_CAT_OWNER.CAT_PARTNER T1 ");    	
    	stringBuilder.append("WHERE upper(T1.CODE) = upper(:code)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("taxCode", new StringType());
		query.addScalar("fax", new StringType());
		query.addScalar("phone", new StringType());
		query.addScalar("address", new StringType());
		query.addScalar("represent", new StringType());
		query.addScalar("partnerType", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
    	
		query.setParameter("code", code);    	
		query.setResultTransformer(Transformers.aliasToBean(CatPartnerDTO.class));    	

		return (CatPartnerDTO) query.uniqueResult();
	}

	public List<CatPartnerDTO> getForAutoComplete(CatPartnerDTO obj) {
		String sql = "SELECT CAT_PARTNER_ID catPartnerId"	
				+" ,CODE code"
			+" ,NAME name"			
			+",T1.TAX_CODE taxCode "
			+",T1.FAX fax "
			+",T1.PHONE phone "
			+",T1.ADDRESS address "
			+",T1.REPRESENT represent "
			+",T1.PARTNER_TYPE partnerType "
			+",T1.DESCRIPTION description "
			+",T1.STATUS status "
			+",T1.CREATED_DATE createdDate "
			+",T1.CREATED_USER_ID createdUserId "
			+",T1.CREATED_GROUP_ID createdGroupId "
			+",T1.UPDATED_DATE updatedDate "
			+",T1.UPDATED_USER_ID updatedUserId "
			+",T1.UPDATED_GROUP_ID updatedGroupId "
			+" FROM CTCT_CAT_OWNER.CAT_PARTNER T1"
			+" WHERE T1.STATUS=1";			
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		if (null != obj.getPartnerType()) {
			stringBuilder.append("AND PARTNER_TYPE = :partnerType ");
		}
		
		stringBuilder.append(" ORDER BY NAME");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("taxCode", new StringType());
		query.addScalar("fax", new StringType());
		query.addScalar("phone", new StringType());
		query.addScalar("address", new StringType());
		query.addScalar("represent", new StringType());
		query.addScalar("partnerType", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
	
		query.setResultTransformer(Transformers.aliasToBean(CatPartnerDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("key", "%" + obj.getKeySearch() + "%");
		}
		if (null != obj.getPartnerType()) {
			query.setParameter("partnerType", obj.getPartnerType());
		}
		return query.list();
	}
	

	@SuppressWarnings("unchecked")
	public List<CatPartnerDTO> getForComboBox(CatPartnerDTO obj){
		String sqlStr = "SELECT CAT_PARTNER_ID catPartnerId"	
				+" ,NAME name"			
				
				+",T1.TAX_CODE taxCode "
				+",T1.FAX fax "
				+",T1.PHONE phone "
				+",T1.ADDRESS address "
				+",T1.REPRESENT represent "
				+",T1.PARTNER_TYPE partnerType "
				+",T1.DESCRIPTION description "
				+",T1.STATUS status "
				+",T1.CREATED_DATE createdDate "
				+",T1.CREATED_USER_ID createdUserId "
				+",T1.CREATED_GROUP_ID createdGroupId "
				+",T1.UPDATED_DATE updatedDate "
				+",T1.UPDATED_USER_ID updatedUserId "
				+",T1.UPDATED_GROUP_ID updatedGroupId "
				+" FROM CTCT_CAT_OWNER.CAT_PARTNER"
				+" WHERE 1=1";				
			
			StringBuilder sql = new StringBuilder(sqlStr);
		if(obj.getStatus()!=null){
			sql.append(" AND STATUS = :status ");
		}
		
		if(StringUtils.isNotEmpty(obj.getCode())){
			sql.append(" AND upper(CODE)=upper(:code) ");
		}
		
		sql.append(" ORDER BY CODE ");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		query.addScalar("catUnitId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(CatPartnerDTO.class));
		
		if(obj.getStatus()!=null){
			query.setParameter("status", obj.getStatus());
		}
		
		if(StringUtils.isNotEmpty(obj.getCode())){
			query.setParameter("code", obj.getCode());
		}
		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public CatPartnerDTO getById(Long id) {
    	StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CAT_PARTNER_ID catPartnerId ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.TAX_CODE taxCode ");
		stringBuilder.append(",T1.FAX fax ");
		stringBuilder.append(",T1.PHONE phone ");
		stringBuilder.append(",T1.ADDRESS address ");
		stringBuilder.append(",T1.REPRESENT represent ");
		stringBuilder.append(",T1.PARTNER_TYPE partnerType ");
		stringBuilder.append(",T1.DESCRIPTION description ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.CREATED_USER_ID createdUserId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.UPDATED_USER_ID updatedUserId ");
		stringBuilder.append(",T1.UPDATED_GROUP_ID updatedGroupId ");

    	stringBuilder.append("FROM CTCT_CAT_OWNER.CAT_PARTNER T1 ");    	
    	stringBuilder.append("WHERE T1.CAT_PARTNER_ID = :catPartnerId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("taxCode", new StringType());
		query.addScalar("fax", new StringType());
		query.addScalar("phone", new StringType());
		query.addScalar("address", new StringType());
		query.addScalar("represent", new StringType());
		query.addScalar("partnerType", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
    	
		query.setParameter("catPartnerId", id);
		query.setResultTransformer(Transformers.aliasToBean(CatPartnerDTO.class));
    	
		return (CatPartnerDTO) query.uniqueResult();
	}
}
