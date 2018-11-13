package com.viettel.ims.dao;

import java.util.List;

import com.viettel.ims.bo.CatWorkItemTypeBO;
import com.viettel.ims.dto.CatWorkItemTypeDTO;
import com.viettel.ims.dto.SysGroupDTO;

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
@Repository("catWorkItemTypeDAO")
public class CatWorkItemTypeDAO extends BaseFWDAOImpl<CatWorkItemTypeBO, Long> {

    public CatWorkItemTypeDAO() {
        this.model = new CatWorkItemTypeBO();
    }

    public CatWorkItemTypeDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<CatWorkItemTypeDTO> doSearch(CatWorkItemTypeDTO criteria) {
    	StringBuilder stringBuilder = getSelectAllQuery();    	
    	stringBuilder.append("where STATUS=1 ");
    	
    	if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) )");
		}
    	
    	if (criteria.getCatConstructionTypeId() != null) {
			stringBuilder
					.append(" AND CAT_CONSTRUCTION_TYPE_ID = :catConstructionTypeId ");
		}else{
			stringBuilder
			.append(" AND CAT_CONSTRUCTION_TYPE_ID = -1 ");
		}
    	

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		
    	
		
			query.addScalar("catWorkItemTypeId", new LongType());
			query.addScalar("name", new StringType());
			query.addScalar("code", new StringType());
			query.addScalar("status", new StringType());
			query.addScalar("description", new StringType());
			query.addScalar("catConstructionTypeId", new LongType());
//			query.addScalar("catConstructionTypeName", new StringType());
			query.addScalar("createdDate", new DateType());
			query.addScalar("updatedDate", new DateType());
			query.addScalar("createdUser", new LongType());
			query.addScalar("updatedUser", new LongType());
		
    	

			if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
				query.setParameter("key", "%" + criteria.getKeySearch() + "%");
				queryCount.setParameter("key", "%" + criteria.getKeySearch() + "%");
			}
			
			if (criteria.getCatConstructionTypeId() != null) {
				query.setParameter("catConstructionTypeId", criteria.getCatConstructionTypeId());
				queryCount.setParameter("catConstructionTypeId", criteria.getCatConstructionTypeId());
			}
			
    	
		query.setResultTransformer(Transformers.aliasToBean(CatWorkItemTypeDTO.class));    	
		List ls = query.list();
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1)
					* criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		criteria.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return ls;
	}  
	//20180224_hoanm1_start
    @SuppressWarnings("unchecked")
	public List<CatWorkItemTypeDTO> doSearchExport(CatWorkItemTypeDTO criteria) {
    	StringBuilder stringBuilder = getSelectAllQuery();    	
    	stringBuilder.append("where STATUS=1 ");
    	
    	if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) )");
		}
    	
    	if (criteria.getCatConstructionTypeId() != null) {
			stringBuilder
					.append(" AND CAT_CONSTRUCTION_TYPE_ID = :catConstructionTypeId ");
		}
    	

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		
    	
		
			query.addScalar("catWorkItemTypeId", new LongType());
			query.addScalar("name", new StringType());
			query.addScalar("code", new StringType());
			query.addScalar("status", new StringType());
			query.addScalar("description", new StringType());
			query.addScalar("catConstructionTypeId", new LongType());
//			query.addScalar("catConstructionTypeName", new StringType());
			query.addScalar("createdDate", new DateType());
			query.addScalar("updatedDate", new DateType());
			query.addScalar("createdUser", new LongType());
			query.addScalar("updatedUser", new LongType());
		
    	

			if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
				query.setParameter("key", "%" + criteria.getKeySearch() + "%");
				queryCount.setParameter("key", "%" + criteria.getKeySearch() + "%");
			}
			
			if (criteria.getCatConstructionTypeId() != null) {
				query.setParameter("catConstructionTypeId", criteria.getCatConstructionTypeId());
				queryCount.setParameter("catConstructionTypeId", criteria.getCatConstructionTypeId());
			}
			
    	
		query.setResultTransformer(Transformers.aliasToBean(CatWorkItemTypeDTO.class));    	
		List ls = query.list();
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1)
					* criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		criteria.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return ls;
	}  
	//20180224_hoanm1_end
	
	public CatWorkItemTypeDTO findByCode(String value) {
		StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE upper(T1.CODE) = upper(:value)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("catWorkItemTypeId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedUser", new LongType());
    	
		query.setParameter("value", value);    	
		query.setResultTransformer(Transformers.aliasToBean(CatWorkItemTypeDTO.class));    	

		return (CatWorkItemTypeDTO) query.uniqueResult();
	}

	public List<CatWorkItemTypeDTO> getForAutoComplete(CatWorkItemTypeDTO obj) {
			
		
		StringBuilder stringBuilder =getSelectAllQuery();
		stringBuilder.append(" Where STATUS = 1");
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key))");
		}
		
		if (obj.getCatConstructionTypeId() != null) {
			stringBuilder
					.append(" AND CAT_CONSTRUCTION_TYPE_ID = :catConstructionTypeId ");
		}else{
			stringBuilder
			.append(" AND CAT_CONSTRUCTION_TYPE_ID = -1 ");
		}
		
		stringBuilder.append(" ORDER BY NAME");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("catWorkItemTypeId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedUser", new LongType());
	
		query.setResultTransformer(Transformers.aliasToBean(CatWorkItemTypeDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("key", "%" + obj.getKeySearch() + "%");
		}
		
		if (obj.getCatConstructionTypeId() != null) {
			query.setParameter("catConstructionTypeId", obj.getCatConstructionTypeId());
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CatWorkItemTypeDTO> getForComboBox(CatWorkItemTypeDTO obj){
		String sql = "CAT_WORK_ITEM_TYPE_ID catWorkItemTypeId "
				+ " ,NAME name" + " ,CODE code"
				+ " FROM CTCT_CAT_OWNER.CAT_WORK_ITEM_TYPE"
				+ " WHERE STATUS = 1";

		StringBuilder stringBuilder = new StringBuilder(sql);
		
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			stringBuilder.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		stringBuilder.append(" ORDER BY CODE");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());

		query.setResultTransformer(Transformers
				.aliasToBean(CatWorkItemTypeDTO.class));

		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("key","%"+ obj.getKeySearch()+"%");
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public CatWorkItemTypeDTO getById(Long id) {
    	StringBuilder stringBuilder = getSelectAllQuery();	
    	stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND T1.CAT_WORK_ITEM_TYPE_ID = :catWorkItemTypeId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("catWorkItemTypeId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("catConstructionTypeName", new StringType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedUser", new LongType());
    	
		query.setParameter("catWorkItemTypeId", id);
		query.setResultTransformer(Transformers.aliasToBean(CatWorkItemTypeDTO.class));
    	
		return (CatWorkItemTypeDTO) query.uniqueResult();
	}
	
	public StringBuilder getSelectAllQuery(){
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CAT_WORK_ITEM_TYPE_ID catWorkItemTypeId ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.DESCRIPTION description ");
		stringBuilder.append(",T1.CAT_CONSTRUCTION_TYPE_ID catConstructionTypeId ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.CREATED_USER createdUser ");
		stringBuilder.append(",T1.UPDATED_USER updatedUser ");
    	
    	stringBuilder.append("FROM CTCT_CAT_OWNER.CAT_WORK_ITEM_TYPE T1 ");    	
    	return stringBuilder;
	}
}
