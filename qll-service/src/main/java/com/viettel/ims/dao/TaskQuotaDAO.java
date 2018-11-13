package com.viettel.ims.dao;

import java.util.List;

import com.viettel.ims.bo.TaskQuotaBO;
import com.viettel.ims.dto.TaskQuotaDTO;



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
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("taskQuotaDAO")
public class TaskQuotaDAO extends BaseFWDAOImpl<TaskQuotaBO, Long> {

    public TaskQuotaDAO() {
        this.model = new TaskQuotaBO();
    }

    public TaskQuotaDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<TaskQuotaDTO> doSearch(TaskQuotaDTO criteria) {
		StringBuilder stringBuilder = getSelectAllQuery();
		stringBuilder.append("WHERE 1=1 ");

		
		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		
		if (null != criteria.getTaskQuotaId()) {
			stringBuilder.append("AND T1.TASK_QUOTA_ID = :taskQuotaId ");
		}
		if (StringUtils.isNotEmpty(criteria.getCode())) {
			stringBuilder
					.append("AND UPPER(T1.CODE) LIKE UPPER(:code) ESCAPE '\\' ");
		}
		if (StringUtils.isNotEmpty(criteria.getName())) {
			stringBuilder
					.append("AND UPPER(T1.NAME) LIKE UPPER(:name) ESCAPE '\\' ");
		}
		if (null != criteria.getWorkItemQuotaId()) {
			stringBuilder
					.append("AND T1.WORK_ITEM_QUOTA_ID = :workItemQuotaId ");
		}
		if (null != criteria.getPrice()) {
			stringBuilder.append("AND T1.PRICE = :price ");
		}
	
		if (StringUtils.isNotEmpty(criteria.getDescription())) {
			stringBuilder
					.append("AND UPPER(T1.DESCRIPTION) LIKE UPPER(:description) ESCAPE '\\' ");
		}
		if (null != criteria.getStatus()) {
			stringBuilder.append("AND T1.STATUS = :status ");
		}
		if (null != criteria.getCreatedDate()) {
			stringBuilder.append("AND T1.CREATED_DATE = :createdDate ");
		}
		if (null != criteria.getCreatedDateFrom()) {
			stringBuilder.append("AND T1.CREATED_DATE >= :createdDateFrom ");
		}
		if (null != criteria.getCreatedDateTo()) {
			stringBuilder.append("AND T1.CREATED_DATE <= :createdDateTo ");
		}
		if (null != criteria.getCreatedUserId()) {
			stringBuilder.append("AND T1.CREATED_USER_ID = :createdUserId ");
		}
		if (null != criteria.getCreatedGroupId()) {
			stringBuilder.append("AND T1.CREATED_GROUP_ID = :createdGroupId ");
		}
		if (null != criteria.getUpdatedDate()) {
			stringBuilder.append("AND T1.UPDATED_DATE = :updatedDate ");
		}
		if (null != criteria.getUpdatedDateFrom()) {
			stringBuilder.append("AND T1.UPDATED_DATE >= :updatedDateFrom ");
		}
		if (null != criteria.getUpdatedDateTo()) {
			stringBuilder.append("AND T1.UPDATED_DATE <= :updatedDateTo ");
		}
		if (null != criteria.getUpdatedUserId()) {
			stringBuilder.append("AND T1.UPDATED_USER_ID = :updatedUserId ");
		}
		if (null != criteria.getUpdatedGroupId()) {
			stringBuilder.append("AND T1.UPDATED_GROUP_ID = :updatedGroupId ");
		}
			
		stringBuilder.append("ORDER BY T1.TASK_QUOTA_ID DESC ");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

	
		query.addScalar("taskQuotaId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("workItemQuotaId", new LongType());
//		query.addScalar("workItemQuotaName", new StringType());
		query.addScalar("price", new DoubleType());
		query.addScalar("workDay", new DoubleType());
		query.addScalar("quotaType", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
//		query.addScalar("createdUserName", new StringType());
		query.addScalar("createdGroupId", new LongType());
//		query.addScalar("createdGroupName", new StringType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
//		query.addScalar("updatedUserName", new StringType());
		query.addScalar("updatedGroupId", new LongType());
//		query.addScalar("updatedGroupName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(TaskQuotaDTO.class));
		if (null != criteria.getTaskQuotaId()) {
			query.setParameter("taskQuotaId", criteria.getTaskQuotaId());
		}
		
		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			query.setParameter("key", "%" + criteria.getKeySearch() + "%");
			queryCount.setParameter("key", "%" + criteria.getKeySearch() + "%");
		}
		
		if (StringUtils.isNotEmpty(criteria.getCode())) {
			query.setParameter("code", "%" + criteria.getCode() + "%");
		}
		if (StringUtils.isNotEmpty(criteria.getName())) {
			query.setParameter("name", "%" + criteria.getName() + "%");
		}
		if (null != criteria.getWorkItemQuotaId()) {
			query.setParameter("workItemQuotaId", criteria.getWorkItemQuotaId());
			queryCount.setParameter("workItemQuotaId", criteria.getWorkItemQuotaId());
		}
		if (null != criteria.getPrice()) {
			query.setParameter("price", criteria.getPrice());
		}
		if (null != criteria.getWorkDay()) {
			query.setParameter("workDay", criteria.getWorkDay());
		}
		if (null != criteria.getQuotaType()) {
			query.setParameter("quotaType", criteria.getQuotaType());
		}
		if (StringUtils.isNotEmpty(criteria.getDescription())) {
			query.setParameter("description", "%" + criteria.getDescription()
					+ "%");
		}
		if (null != criteria.getStatus()) {
			query.setParameter("status", criteria.getStatus());
			queryCount.setParameter("status", criteria.getStatus());
		}
		if (null != criteria.getCreatedDate()) {
			query.setParameter("createdDate", criteria.getCreatedDate());
		}
		if (null != criteria.getCreatedDateFrom()) {
			query.setTimestamp("createdDateFrom", criteria.getCreatedDateFrom());
		}
		if (null != criteria.getCreatedDateTo()) {
			query.setTimestamp("createdDateTo", criteria.getCreatedDateTo());
		}
		if (null != criteria.getCreatedUserId()) {
			query.setParameter("createdUserId", criteria.getCreatedUserId());
		}
		if (null != criteria.getCreatedGroupId()) {
			query.setParameter("createdGroupId", criteria.getCreatedGroupId());
		}
		if (null != criteria.getUpdatedDate()) {
			query.setParameter("updatedDate", criteria.getUpdatedDate());
		}
		if (null != criteria.getUpdatedDateFrom()) {
			query.setTimestamp("updatedDateFrom", criteria.getUpdatedDateFrom());
		}
		if (null != criteria.getUpdatedDateTo()) {
			query.setTimestamp("updatedDateTo", criteria.getUpdatedDateTo());
		}
		if (null != criteria.getUpdatedUserId()) {
			query.setParameter("updatedUserId", criteria.getUpdatedUserId());
		}
		if (null != criteria.getUpdatedGroupId()) {
			query.setParameter("updatedGroupId", criteria.getUpdatedGroupId());

		}

		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1)
					* criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		criteria.setTotalRecord(((BigDecimal) queryCount.uniqueResult())
				.intValue());

		return query.list();
	}
	
	public TaskQuotaDTO findByCode(String code) {
		StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE  upper(T1.CODE) = upper(:code)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("taskQuotaId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("workItemQuotaId", new LongType());
		query.addScalar("price", new DoubleType());
		query.addScalar("workDay", new DoubleType());
		query.addScalar("quotaType", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
    	
		query.setParameter("code", code);    	
		query.setResultTransformer(Transformers.aliasToBean(TaskQuotaDTO.class));    	

		return (TaskQuotaDTO) query.uniqueResult();
	}

	public List<TaskQuotaDTO> getForAutoComplete(TaskQuotaDTO obj) {
		String sql = "SELECT TASK_QUOTA_ID taskQuotaId"	
			+" ,NAME name"			
			+" ,VALUE value"
			+" FROM TASK_QUOTA"
			+" WHERE STATUS = 1 ";			
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if(StringUtils.isNotEmpty(obj.getName())){
			stringBuilder.append(" AND (upper(NAME) LIKE upper(:name) escape '&' OR upper(CODE) LIKE upper(:name) escape '&')");
		}
		stringBuilder.append(" ORDER BY NAME");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("taskQuotaId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("value", new StringType());
	
		query.setResultTransformer(Transformers.aliasToBean(TaskQuotaDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" +obj.getName() + "%");
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskQuotaDTO> getForComboBox(TaskQuotaDTO obj){
		String sql = "SELECT CAT_PRODUCING_COUNTRY_ID catProducingCountryId"
				+ " ,NAME name" + " ,CODE code"
				+ " FROM TASK_QUOTA"
				+ " WHERE 1=1";

		
		StringBuilder stringBuilder = new StringBuilder(sql);
		if(obj.getStatus()!=null){
			stringBuilder.append(" AND STATUS = :status ");
		}
		
		if(StringUtils.isNotEmpty(obj.getCode())){
			stringBuilder.append(" AND upper(CODE)=upper(:code) ");
		}
		stringBuilder.append(" ORDER BY CODE");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

		query.addScalar("catProducingCountryId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("code", new StringType());

		query.setResultTransformer(Transformers
				.aliasToBean(TaskQuotaDTO.class));

		
		if(obj.getStatus()!=null){
			query.setParameter("status", obj.getStatus());
		}
		
		if(StringUtils.isNotEmpty(obj.getCode())){
			query.setParameter("code", obj.getCode());
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public TaskQuotaDTO getById(Long id) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE T1.TASK_QUOTA_ID = :taskQuotaId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("taskQuotaId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("workItemQuotaId", new LongType());
		query.addScalar("workItemQuotaName", new StringType());
		query.addScalar("price", new DoubleType());
		query.addScalar("workDay", new DoubleType());
		query.addScalar("quotaType", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdUserName", new StringType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("createdGroupName", new StringType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedUserName", new StringType());
		query.addScalar("updatedGroupId", new LongType());
		query.addScalar("updatedGroupName", new StringType());
    	
		query.setParameter("taskQuotaId", id);
		query.setResultTransformer(Transformers.aliasToBean(TaskQuotaDTO.class));
    	
		return (TaskQuotaDTO) query.uniqueResult();
	}
	
	public StringBuilder getSelectAllQuery(){
		StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append("T1.TASK_QUOTA_ID taskQuotaId ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.WORK_ITEM_QUOTA_ID workItemQuotaId ");
		stringBuilder.append(",T1.PRICE price ");
		stringBuilder.append(",T1.WORK_DAY workDay ");
		stringBuilder.append(",T1.QUOTA_TYPE quotaType ");
		stringBuilder.append(",T1.DESCRIPTION description ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.CREATED_USER_ID createdUserId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.UPDATED_USER_ID updatedUserId ");
		stringBuilder.append(",T1.UPDATED_GROUP_ID updatedGroupId ");
		stringBuilder.append("FROM TASK_QUOTA T1 ");
		return stringBuilder;
	}
}
