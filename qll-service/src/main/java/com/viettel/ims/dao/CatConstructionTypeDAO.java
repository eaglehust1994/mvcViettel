package com.viettel.ims.dao;

import java.util.List;

import com.viettel.ims.bo.CatConstructionTypeBO;
import com.viettel.ims.dto.CatConstructionTypeDTO;

import com.viettel.ims.dto.TaskQuotaDTO;

//import com.viettel.erp.utils.FilterUtilities;
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
@Repository("catConstructionTypeDAO")
public class CatConstructionTypeDAO extends
		BaseFWDAOImpl<CatConstructionTypeBO, Long> {

	public CatConstructionTypeDAO() {
		this.model = new CatConstructionTypeBO();
	}

	public CatConstructionTypeDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<CatConstructionTypeDTO> doSearch(CatConstructionTypeDTO criteria) {
		StringBuilder stringBuilder = getAllQuery();
		stringBuilder.append("WHERE STATUS = 1");

		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

	
		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedUser", new LongType());

		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			query.setParameter("key", "%" + criteria.getKeySearch() + "%");
			queryCount.setParameter("key", "%" + criteria.getKeySearch() + "%");
		}

		query.setResultTransformer(Transformers
				.aliasToBean(CatConstructionTypeDTO.class));
		List ls = query.list();
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1)
					* criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		return ls;
	}

	public CatConstructionTypeDTO findByCode(String value) {
		StringBuilder stringBuilder = getAllQuery();
		stringBuilder
				.append("WHERE upper(T1.CODE) = upper(:value)");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedUser", new LongType());

		query.setParameter("value", value);
		query.setResultTransformer(Transformers
				.aliasToBean(CatConstructionTypeDTO.class));

		return (CatConstructionTypeDTO) query.uniqueResult();
	}

	public List<CatConstructionTypeDTO> getForAutoComplete(
			CatConstructionTypeDTO obj) {
		StringBuilder stringBuilder = getAllQuery();
		stringBuilder.append(" Where STATUS = 1");
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			stringBuilder.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		stringBuilder.append(" ORDER BY NAME");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedUser", new LongType());
		query.setResultTransformer(Transformers
				.aliasToBean(CatConstructionTypeDTO.class));

		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("key","%"+ obj.getKeySearch()+"%");
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CatConstructionTypeDTO> getForComboBox(CatConstructionTypeDTO obj){
		String sql = "SELECT CAT_CONSTRUCTION_TYPE_ID catConstructionTypeId "
				+ " ,NAME name" + " ,CODE code"
				+ " FROM CTCT_CAT_OWNER.CAT_CONSTRUCTION_TYPE"
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
				.aliasToBean(TaskQuotaDTO.class));

		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("key","%"+ obj.getKeySearch()+"%");
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public CatConstructionTypeDTO getById(Long id) {
		StringBuilder stringBuilder = getAllQuery();
		stringBuilder
				.append("WHERE T1.IS_DELETED = 'N' AND T1.CAT_CONSTRUCTION_TYPE_ID = :catConstructionTypeId ");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("status", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("createdUser", new LongType());
		query.addScalar("updatedUser", new LongType());

		query.setParameter("catConstructionTypeId", id);
		query.setResultTransformer(Transformers
				.aliasToBean(CatConstructionTypeDTO.class));

		return (CatConstructionTypeDTO) query.uniqueResult();
	}

	public StringBuilder getAllQuery() {
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder
				.append("T1.CAT_CONSTRUCTION_TYPE_ID catConstructionTypeId ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.DESCRIPTION description ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.CREATED_USER createdUser ");
		stringBuilder.append(",T1.UPDATED_USER updatedUser ");

		stringBuilder.append("FROM CTCT_CAT_OWNER.CAT_CONSTRUCTION_TYPE T1 ");
		return stringBuilder;
	}
}
