package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TaskBO;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TaskDTO;

import java.math.BigDecimal;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("taskDAO")
public class TaskDAO extends BaseFWDAOImpl<TaskBO, Long> {

    public TaskDAO() {
        this.model = new TaskBO();
    }

    public TaskDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<TaskDTO> doSearch(TaskDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append(" t1.TASK_ID taskId ");
		stringBuilder.append(",t1.ID_TASK_GROUP idTaskGroup ");
		stringBuilder.append(",t1.STATUS status ");
		stringBuilder.append(",t1.END_TIME endTime ");
		stringBuilder.append(",t1.START_TIME startTime ");
		stringBuilder.append(",t1.CREATE_TASK_CYCLE createTaskCycle ");
		stringBuilder.append(",t1.NOTE_TASK noteTask ");
    	stringBuilder.append(",t2.TASK_GROUP_NAME taskGroupName ");
    	stringBuilder.append(",t2.TASK_GROUP_CONTENT taskGroupContent ");
    	stringBuilder.append(",t2.DEPARTMENT department ");
    	stringBuilder.append(",t2.DEPARTMENT_ID departmentId");
    	stringBuilder.append(" FROM TASK t1 join TASK_GROUP t2 on t1.ID_TASK_GROUP=t2.TASK_GROUP_ID ");    	
    	stringBuilder.append(" WHERE 1=1 ");

		if (null != obj.getTaskId()) {
			stringBuilder.append(" AND t1.TASK_ID = :taskId ");
		}
		
		if (null != obj.getStatus()) {
			stringBuilder.append(" AND t1.STATUS = :status ");
		}
		
		if (null != obj.getEndTime()) {
			stringBuilder.append(" AND t1.END_TIME <= :endTime ");
		}
		
		if (null != obj.getStartTime()) {
			stringBuilder.append(" AND t1.END_TIME >= :startTime ");
		}
			
		if (null != obj.getDepartmentId()) {
			stringBuilder.append(" AND t2.DEPARTMENT_ID = :departmentId ");
		}
	
		if (StringUtils.isNotEmpty(obj.getDepartment())) {
			stringBuilder.append("AND (UPPER(T2.DEPARTMENT) LIKE UPPER(:department) ESCAPE '&') ");
		}
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
    	
			
			query.addScalar("taskId", new LongType());
			query.addScalar("idTaskGroup", new LongType());
			query.addScalar("status", new LongType());
			query.addScalar("endTime", new DateType());
			query.addScalar("startTime", new DateType());
			query.addScalar("createTaskCycle", new LongType());
			query.addScalar("noteTask", new StringType());
			query.addScalar("taskGroupName", new StringType());
			query.addScalar("taskGroupContent", new StringType());
			query.addScalar("department", new StringType());
			query.addScalar("departmentId", new LongType());
			
		if (null != obj.getTaskId()) {
			query.setParameter("taskId", obj.getTaskId());
			queryCount.setParameter("taskId", obj.getTaskId());
		}
		if (null != obj.getIdTaskGroup()) {
			query.setParameter("idTaskGroup", obj.getIdTaskGroup());
			queryCount.setParameter("idTaskGroup", obj.getIdTaskGroup());
		}
		if (null != obj.getStatus()) {
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}
		
		if (null != obj.getEndTime()) {
			query.setTimestamp("endTime", obj.getEndTime());
			queryCount.setTimestamp("endTime", obj.getEndTime());
		}
		
		if (null != obj.getStartTime()) {
			query.setTimestamp("startTime", obj.getStartTime());
			queryCount.setTimestamp("startTime", obj.getStartTime());
		}
		
		if (null != obj.getCreateTaskCycle()) {
			query.setParameter("createTaskCycle", obj.getCreateTaskCycle());
			queryCount.setParameter("createTaskCycle", obj.getCreateTaskCycle());
		}
		if (StringUtils.isNotEmpty(obj.getDepartment())) {
			query.setParameter("department", "%" + ValidateUtils.validateKeySearch(obj.getDepartment()) + "%");
			queryCount.setParameter("department", "%" + ValidateUtils.validateKeySearch(obj.getDepartment()) + "%");
		}
		if (null != obj.getDepartmentId()) {
			query.setParameter("departmentId", obj.getDepartmentId());
			queryCount.setParameter("departmentId", obj.getDepartmentId());
		}
    
		query.setResultTransformer(Transformers.aliasToBean(TaskDTO.class));    	
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}  
    public Long updateTaskStatus(TaskDTO obj) {
		try {

			String sql2 = "update TASK set STATUS=:status where TASK_ID=:taskId ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("status", obj.getStatus());
			query2.setParameter("taskId", obj.getTaskId());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
}
