package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TaskGroupBO;
import com.viettel.qll.dto.TaskGroupDTO;


import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;


import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("taskGroupDAO")
public class TaskGroupDAO extends BaseFWDAOImpl<TaskGroupBO, Long> {

    public TaskGroupDAO() {
        this.model = new TaskGroupBO();
    }

    public TaskGroupDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<TaskGroupDTO> doSearch(TaskGroupDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder("select ");
		stringBuilder.append(" T1.TASK_GROUP_ID taskGroupId ");
		stringBuilder.append(",T1.TASK_GROUP_NAME taskGroupName ");
		stringBuilder.append(",T1.TASK_GROUP_CONTENT taskGroupContent ");
		stringBuilder.append(",T1.DEPARTMENT department ");
		stringBuilder.append(",T1.PERIODIC periodic ");
		stringBuilder.append(",T1.END_TASK_GROUP endTaskGroup ");
		stringBuilder.append(",T1.START_TASK_GROUP startTaskGroup ");
		stringBuilder.append(",T1.WARNING_TASK_GROUP warningTaskGroup ");
		stringBuilder.append(",T1.WARNING_CYCLE warningCycle ");
		stringBuilder.append(",T1.WARNING_EMAIL warningEmail ");
    
    	stringBuilder.append(" FROM TASK_GROUP T1 ");    	
    	stringBuilder.append("WHERE 1=1 ");
    	
    	if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			stringBuilder.append(" AND (upper(T1.TASK_GROUP_NAME) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.DEPARTMENT) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(T1.PERIODIC) LIKE upper(:keySearch) escape '&')");
		}

		if (null != obj.getTaskGroupId()) {
			stringBuilder.append("AND T1.TASK_GROUP_ID = :taskGroupId ");
		}
		if (StringUtils.isNotEmpty(obj.getTaskGroupName())) {
			stringBuilder.append("AND (UPPER(T1.TASK_GROUP_NAME) LIKE UPPER(:taskGroupName) ESCAPE '&') ");
		}
		
		if (StringUtils.isNotEmpty(obj.getDepartment())) {
			stringBuilder.append("AND (UPPER(T1.DEPARTMENT) LIKE UPPER(:department) ESCAPE '&') ");
		}
		
		if (null != obj.getDepartmentId()) {
			stringBuilder.append("AND T1.DEPARTMENT_ID = :departmentId ");
		}
		
		if (null != obj.getPeriodic()) {
			stringBuilder.append("AND T1.PERIODIC = :periodic ");
		}
		if (null != obj.getEndTaskGroup()) {
			stringBuilder.append("AND T1.END_TASK_GROUP = :endTaskGroup ");
		}
		if (null != obj.getStartTaskGroup()) {
			stringBuilder.append("AND T1.START_TASK_GROUP = :startTaskGroup ");
		}
		if (null != obj.getWarningTaskGroup()) {
			stringBuilder.append("AND T1.WARNING_TASK_GROUP = :warningTaskGroup ");
		}
		if (null != obj.getWarningCycle()) {
			stringBuilder.append("AND T1.WARNING_CYCLE = :warningCycle ");
		}
		
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
    	
			
			query.addScalar("taskGroupId", new LongType());
			query.addScalar("taskGroupName", new StringType());
			query.addScalar("taskGroupContent", new StringType());
			query.addScalar("department", new StringType());
			query.addScalar("periodic", new LongType());
			query.addScalar("endTaskGroup", new LongType());
			query.addScalar("startTaskGroup", new LongType());
			query.addScalar("warningTaskGroup", new LongType());
			query.addScalar("warningCycle", new LongType());
			query.addScalar("warningEmail", new StringType());
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (null != obj.getTaskGroupId()) {
			query.setParameter("taskGroupId", obj.getTaskGroupId());
			queryCount.setParameter("taskGroupId", obj.getTaskGroupId());
		}
		if (StringUtils.isNotEmpty(obj.getTaskGroupName())) {
			query.setParameter("taskGroupName", "%" + ValidateUtils.validateKeySearch(obj.getTaskGroupName()) + "%");
			queryCount.setParameter("taskGroupName", "%" + ValidateUtils.validateKeySearch(obj.getTaskGroupName()) + "%");
		}
		
		if (StringUtils.isNotEmpty(obj.getDepartment())) {
			query.setParameter("department", "%" + ValidateUtils.validateKeySearch(obj.getDepartment()) + "%");
			queryCount.setParameter("department", "%" + ValidateUtils.validateKeySearch(obj.getDepartment()) + "%");
		}
		if (null != obj.getDepartmentId()) {
			query.setParameter("departmentId", obj.getDepartmentId());
			queryCount.setParameter("departmentId", obj.getDepartmentId());
		}
		
		if (null != obj.getPeriodic()) {
			query.setParameter("periodic", obj.getPeriodic());
			queryCount.setParameter("periodic", obj.getPeriodic());
		}
		if (null != obj.getEndTaskGroup()) {
			query.setParameter("endTaskGroup", obj.getEndTaskGroup());
			queryCount.setParameter("endTaskGroup", obj.getEndTaskGroup());
		}
		if (null != obj.getStartTaskGroup()) {
			query.setParameter("startTaskGroup", obj.getStartTaskGroup());
			queryCount.setParameter("startTaskGroup", obj.getStartTaskGroup());
		}
		if (null != obj.getWarningTaskGroup()) {
			query.setParameter("warningTaskGroup", obj.getWarningTaskGroup());
			queryCount.setParameter("warningTaskGroup", obj.getWarningTaskGroup());
		}
		if (null != obj.getWarningCycle()) {
			query.setParameter("warningCycle", obj.getWarningCycle());
			queryCount.setParameter("warningCycle", obj.getWarningCycle());
		}
		
    	
		query.setResultTransformer(Transformers.aliasToBean(TaskGroupDTO.class));    	
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}  
    public Long deleteList(List<Long> obj) {
		try {

			String sql2 = "delete from TASK_GROUP  where TASK_GROUP_ID in (:taskGroupId) ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameterList("taskGroupId", obj);
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    
    @SuppressWarnings("unchecked")
	public List<TaskGroupDTO> getAutoCompleteDept(TaskGroupDTO obj){
    	StringBuilder sql = new StringBuilder(""); 
    	sql.append("Select DISTINCT DEPARTMENT department, DEPARTMENT_ID departmentId from TASK_GROUP where 1=1 AND ROWNUM <=10 ");
    	
    	if(StringUtils.isNotEmpty(obj.getAutoDept())){
    		sql.append(" AND (UPPER(DEPARTMENT) LIKE UPPER(:autoDept) ESCAPE '&') ");
    	}
    	sql.append(" order by DEPARTMENT");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.addScalar("department", new StringType());
    	query.addScalar("departmentId", new LongType());
    	query.setResultTransformer(Transformers.aliasToBean(TaskGroupDTO.class));
    	
    	if(StringUtils.isNotEmpty(obj.getAutoDept())){
    		query.setParameter("autoDept", "%" + ValidateUtils.validateKeySearch(obj.getAutoDept()) + "%");
    	}
    	return query.list();
    }
    @SuppressWarnings("unchecked")
   	public TaskGroupDTO getDeptId(TaskGroupDTO obj){
       	StringBuilder sql = new StringBuilder(""); 
       	sql.append("Select DEPARTMENT_ID departmentId from TASK_GROUP where TASK_GROUP_ID = :taskGroupId ");
       	
       	SQLQuery query = getSession().createSQLQuery(sql.toString());
       	
       	query.addScalar("departmentId", new LongType());
       	query.setResultTransformer(Transformers.aliasToBean(TaskGroupDTO.class));
       	query.setParameter("taskGroupId", obj.getTaskGroupId());
       
       	return (TaskGroupDTO) query.uniqueResult();
       }

}
