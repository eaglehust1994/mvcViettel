package com.viettel.qll.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.KpiDepartmentBO;
import com.viettel.qll.dto.KpiDepartmentDTO;

import com.viettel.service.base.dao.BaseFWDAOImpl;
@Repository("kpiDepartmentDAO")
public class KpiDepartmentDAO extends BaseFWDAOImpl<KpiDepartmentBO,Long>{
	
	public KpiDepartmentDAO(){
		this.model= new KpiDepartmentBO();
		
	}
	 public KpiDepartmentDAO(Session session){
		 this.session = session;
	 }
	 @SuppressWarnings("unchecked")
	 public List<KpiDepartmentDTO> getListDepartment(KpiDepartmentDTO obj){
		 StringBuilder sql = new StringBuilder("");
		 sql.append(" select DEPARTMENT_ID derpartmentId, DEPARTMENT_NAME derpartmentName from KPI_DEPARTMENT order by DEPARTMENT_NAME ");
		 
		 SQLQuery query = getSession().createSQLQuery(sql.toString());
		 
		 query.addScalar("derpartmentId", new LongType());
		 query.addScalar("derpartmentName", new StringType());
		 
		 query.setResultTransformer(Transformers.aliasToBean(KpiDepartmentDTO.class));
	 
		 return query.list();
		 
	 }

}
