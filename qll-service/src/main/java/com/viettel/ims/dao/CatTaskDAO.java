package com.viettel.ims.dao;

import java.util.List;

import com.viettel.ims.bo.CatTaskBO;
import com.viettel.ims.dto.CatConstructionTypeDTO;
import com.viettel.ims.dto.CatTaskDTO;

import java.lang.reflect.Field;

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
@Repository("catTaskDAO")
public class CatTaskDAO extends BaseFWDAOImpl<CatTaskBO, Long> {

    public CatTaskDAO() {
        this.model = new CatTaskBO();
    }

    public CatTaskDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<CatTaskDTO> doSearch(CatTaskDTO criteria) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE STATUS=1 ");
    	
    	if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
    	

    	if (criteria.getCatWorkItemTypeId() != null) {
			stringBuilder
					.append(" AND CAT_WORK_ITEM_TYPE_ID = :catWorkItemTypeId ");
		}
		
    	
    
    	StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

    	
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("status", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("catWorkItemTypeId", new LongType());
//		query.addScalar("catWorkItemTypeName", new StringType());
		query.addScalar("catUnitId", new LongType());
//		query.addScalar("catUnitName", new StringType());
		query.addScalar("catTaskId", new LongType());
		query.addScalar("updatedUser", new LongType());
		
    	
		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			query.setParameter("key", "%" + criteria.getKeySearch() + "%");
			queryCount.setParameter("key", "%" + criteria.getKeySearch() + "%");
		}
		
		if (criteria.getCatWorkItemTypeId() != null) {
			query.setParameter("catWorkItemTypeId", criteria.getCatWorkItemTypeId());
			queryCount.setParameter("catWorkItemTypeId", criteria.getCatWorkItemTypeId());
		}

		query.setResultTransformer(Transformers
				.aliasToBean(CatTaskDTO.class));
		List ls = query.list();
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1)
					* criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		return ls;
	}  
	
	public CatTaskDTO findByCode(String value) {
		StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE CODE = :code");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("status", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("catWorkItemTypeId", new LongType());
		query.addScalar("catUnitId", new LongType());
		query.addScalar("catTaskId", new LongType());
		query.addScalar("updatedUser", new LongType());
    	
		query.setParameter("code", value);    	
		query.setResultTransformer(Transformers.aliasToBean(CatTaskDTO.class));    	

		return (CatTaskDTO) query.uniqueResult();
	}

	public List<CatTaskDTO> getForAutoComplete(CatTaskDTO obj) {
				
		
		StringBuilder stringBuilder = getSelectAllQuery();
		
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			stringBuilder.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}		
		stringBuilder.append(" ORDER BY NAME");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("status", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("catWorkItemTypeId", new LongType());
		query.addScalar("catUnitId", new LongType());
		query.addScalar("catTaskId", new LongType());
		query.addScalar("updatedUser", new LongType());
	
		query.setResultTransformer(Transformers.aliasToBean(CatTaskDTO.class));

		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("key","%"+ obj.getKeySearch()+"%");
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public CatTaskDTO getById(Long id) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("FROM CAT_TASK T1 ");    	
    	stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND T1.CAT_TASK_ID = :catTaskId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("status", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("catWorkItemTypeId", new LongType());
		query.addScalar("catUnitId", new LongType());
		query.addScalar("catTaskId", new LongType());
		query.addScalar("updatedUser", new LongType());
    	
		query.setParameter("catTaskId", id);
		query.setResultTransformer(Transformers.aliasToBean(CatTaskDTO.class));
    	
		return (CatTaskDTO) query.uniqueResult();
	}
	
	public StringBuilder getSelectAllQuery(){
		StringBuilder stringBuilder = new StringBuilder("select ");
		stringBuilder.append("T1.CREATED_USER createdUser ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.DESCRIPTION description ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.CAT_WORK_ITEM_TYPE_ID catWorkItemTypeId ");
		stringBuilder.append(",T1.CAT_UNIT_ID catUnitId ");
		stringBuilder.append(",T1.CAT_TASK_ID catTaskId ");
		stringBuilder.append(",T1.UPDATED_USER updatedUser ");
    	stringBuilder.append("FROM CAT_TASK T1 ");    	
    	return stringBuilder;
	}
}
