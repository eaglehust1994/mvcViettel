package com.viettel.ims.dao;

import java.util.List;

import com.viettel.ims.bo.WorkItemQuotaBO;
import com.viettel.ims.dto.WorkItemQuotaDTO;



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
@Repository("workItemQuotaDAO")
public class WorkItemQuotaDAO extends BaseFWDAOImpl<WorkItemQuotaBO, Long> {

    public WorkItemQuotaDAO() {
        this.model = new WorkItemQuotaBO();
    }

    public WorkItemQuotaDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<WorkItemQuotaDTO> doSearch(WorkItemQuotaDTO criteria) {
    	StringBuilder stringBuilder =getSelectAllQuery();
    	stringBuilder.append(" WHERE 1=1 ");
    	
		
		if (null != criteria.getSysGroupId()) {
			stringBuilder.append("AND T1.SYS_GROUP_ID = :sysGroupId ");
		}
		if (null != criteria.getCatConstructionTypeId()) {	
			stringBuilder.append("AND T1.CAT_CONSTRUCTION_TYPE_ID = :catConstructionTypeId ");
		}
		if (null != criteria.getCatWorkItemTypeId()) {
			stringBuilder.append("AND T1.CAT_WORK_ITEM_TYPE_ID = :catWorkItemTypeId ");
		}
		
		if (null != criteria.getQuotaType()) {
			stringBuilder.append("AND T1.QUOTA_TYPE = :quotaType ");
		}
		
		if (null != criteria.getStatus()) {
			stringBuilder.append("AND T1.STATUS = :status ");
		}
		
		stringBuilder.append(" ORDER BY T1.WORK_ITEM_QUOTA_ID desc");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		
	
		query.addScalar("workItemQuotaId", new LongType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("catConstructionTypeName", new StringType());
		query.addScalar("catWorkItemTypeId", new LongType());
		query.addScalar("catWorkItemTypeName", new StringType());
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
	
	
	
		if (null != criteria.getSysGroupId()) {
			query.setParameter("sysGroupId", criteria.getSysGroupId());
			queryCount.setParameter("sysGroupId", criteria.getSysGroupId());
		}
		if (null != criteria.getCatConstructionTypeId()) {
			query.setParameter("catConstructionTypeId", criteria.getCatConstructionTypeId());
			queryCount.setParameter("catConstructionTypeId", criteria.getCatConstructionTypeId());
		}
		if (null != criteria.getQuotaType()) {
			query.setParameter("quotaType", criteria.getQuotaType());
			queryCount.setParameter("quotaType", criteria.getQuotaType());
		}
		if (null != criteria.getCatWorkItemTypeId()) {
			query.setParameter("catWorkItemTypeId", criteria.getCatWorkItemTypeId());
			queryCount.setParameter("catWorkItemTypeId", criteria.getCatWorkItemTypeId());
		}
		
		if (null != criteria.getStatus()) {
			query.setParameter("status", criteria.getStatus());
			queryCount.setParameter("status", criteria.getStatus());
		}
		
		query.setResultTransformer(Transformers.aliasToBean(WorkItemQuotaDTO.class));    	
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1) * criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		criteria.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
	}  
	
	public WorkItemQuotaDTO findByUniqueKey (WorkItemQuotaDTO obj) {
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.WORK_ITEM_QUOTA_ID workItemQuotaId ");
		stringBuilder.append(",T1.SYS_GROUP_ID sysGroupId ");
		stringBuilder.append(",T1.CAT_CONSTRUCTION_TYPE_ID catConstructionTypeId ");
		stringBuilder.append(",T1.CAT_WORK_ITEM_TYPE_ID catWorkItemTypeId ");
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
    	
    	stringBuilder.append("FROM WORK_ITEM_QUOTA T1 ");    	
    	stringBuilder.append("WHERE T1.STATUS = 1 AND T1.SYS_GROUP_ID = :sysGroupId and "
    			+ "T1.CAT_CONSTRUCTION_TYPE_ID = :catConstructionTypeId and T1.CAT_WORK_ITEM_TYPE_ID = :catWorkItemTypeId");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("workItemQuotaId", new LongType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("catWorkItemTypeId", new LongType());
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
    	
		query.setParameter("sysGroupId", obj.getSysGroupId());
		query.setParameter("catConstructionTypeId", obj.getCatConstructionTypeId());
		query.setParameter("catWorkItemTypeId", obj.getCatWorkItemTypeId());
		query.setResultTransformer(Transformers.aliasToBean(WorkItemQuotaDTO.class));    	

		return (WorkItemQuotaDTO) query.uniqueResult();
	}

	public List<WorkItemQuotaDTO> getForAutoComplete(WorkItemQuotaDTO obj) {
		String sql = "SELECT WORK_ITEM_QUOTA_ID workItemQuotaId"	
			+" ,NAME name"			
			+" ,VALUE value"
			+" FROM WORK_ITEM_QUOTA"
			+" WHERE IS_DELETED = 'N' AND ISACTIVE = 'Y'";			
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			stringBuilder.append(" AND (upper(NAME) LIKE upper(:name) escape '&' OR upper(CODE) LIKE upper(:name) escape '&')");
		}
		if(obj.getCatConstructionTypeId()!=null){
			stringBuilder.append(" AND CAT_CONSTRUCTION_TYPE_ID = catConstructionTypeId");
		}
		stringBuilder.append(" ORDER BY NAME");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("workItemQuotaId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("value", new StringType());
	
		query.setResultTransformer(Transformers.aliasToBean(WorkItemQuotaDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
//			query.setParameter("name", "%" + com.viettel.service.base.utils.StringUtils.replaceSpecialKeySearch(obj.getName()) + "%");
		}
		
		if (obj.getCatConstructionTypeId()!=null) {
			query.setParameter("catConstructionTypeId", obj.getCatConstructionTypeId());
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public WorkItemQuotaDTO getById(Long id) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE T1.WORK_ITEM_QUOTA_ID = :workItemQuotaId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("workItemQuotaId", new LongType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("catConstructionTypeId", new LongType());
		query.addScalar("catConstructionTypeName", new StringType());
		query.addScalar("catWorkItemTypeId", new LongType());
		query.addScalar("catWorkItemTypeName", new StringType());
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
    	
		query.setParameter("workItemQuotaId", id);
		query.setResultTransformer(Transformers.aliasToBean(WorkItemQuotaDTO.class));
    	
		return (WorkItemQuotaDTO) query.uniqueResult();
	}
	
	public StringBuilder getSelectAllQuery(){
		StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append("T1.WORK_ITEM_QUOTA_ID workItemQuotaId ");
		stringBuilder.append(",T1.SYS_GROUP_ID sysGroupId ");
		stringBuilder.append(",T1.CAT_CONSTRUCTION_TYPE_ID catConstructionTypeId ");
		stringBuilder.append(",T1.CAT_WORK_ITEM_TYPE_ID catWorkItemTypeId ");
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
		stringBuilder.append(",T2.NAME catConstructionTypeName ");
		stringBuilder.append(",T3.NAME sysGroupName ");
		stringBuilder.append(",T4.NAME catWorkItemTypeName ");
    	stringBuilder.append(" FROM WORK_ITEM_QUOTA T1 ");    
    	stringBuilder.append(" JOIN CTCT_CAT_OWNER.CAT_CONSTRUCTION_TYPE T2 ON T2.CAT_CONSTRUCTION_TYPE_ID = T1.CAT_CONSTRUCTION_TYPE_ID");
    	stringBuilder.append(" JOIN CTCT_CAT_OWNER.SYS_GROUP T3 ON T3.SYS_GROUP_ID = T1.SYS_GROUP_ID");
    	stringBuilder.append(" JOIN CTCT_CAT_OWNER.CAT_WORK_ITEM_TYPE T4 ON T4.CAT_WORK_ITEM_TYPE_ID = T1.CAT_WORK_ITEM_TYPE_ID");
    	return stringBuilder;
	}
}
