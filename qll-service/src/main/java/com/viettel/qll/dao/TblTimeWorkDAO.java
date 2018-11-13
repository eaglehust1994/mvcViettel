package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblTimeWorkBO;
import com.viettel.qll.dto.TblTimeWorkDTO;
import java.math.BigDecimal;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ignite.cache.query.SqlQuery;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("tblTimeWorkDAO")
public class TblTimeWorkDAO extends BaseFWDAOImpl<TblTimeWorkBO, Long> {

    public TblTimeWorkDAO() {
        this.model = new TblTimeWorkBO();
    }

    public TblTimeWorkDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<TblTimeWorkDTO> doSearch(TblTimeWorkDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append(" T1.TBL_TIME_WORK_ID tblTimeWorkId ");
		stringBuilder.append(",T1.GEN_TIME genTime ");
		stringBuilder.append(",T1.DERPARTMENT derpartment ");
		stringBuilder.append(",T1.NAME name ");
    	
    	stringBuilder.append(" FROM TBL_TIME_WORK T1 ");    	
    	stringBuilder.append("WHERE 1=1  ");

		if (null != obj.getTblTimeWorkId()) {
			stringBuilder.append("AND T1.TBL_TIME_WORK_ID = :tblTimeWorkId ");
		}
		
		if (null != obj.getGenTimeFrom() && obj.getGenTimeTo() == null) {
			stringBuilder.append(" AND T1.GEN_TIME >= :genTime ");
		}
		if (null != obj.getGenTimeTo() && null != obj.getGenTimeFrom()) {
			stringBuilder.append(" AND (T1.GEN_TIME between :genTimeFrom and :genTimeTo) ");
		}
		
		if (StringUtils.isNotEmpty(obj.getDerpartment())) {
			stringBuilder.append("AND (UPPER(T1.DERPARTMENT) LIKE UPPER(:derpartment) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getName())) {
			stringBuilder.append("AND (UPPER(T1.NAME) LIKE UPPER(:name) ESCAPE '&') ");
		}
		
		stringBuilder.append(" order by T1.TBL_TIME_WORK_ID desc");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");
    	
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		
			query.addScalar("tblTimeWorkId", new LongType());
			query.addScalar("genTime", new DateType());
			query.addScalar("derpartment", new StringType());
			query.addScalar("name", new StringType());
		
			query.setResultTransformer(Transformers.aliasToBean(TblTimeWorkDTO.class));
			
			if (null != obj.getGenTimeFrom() && obj.getGenTimeTo() == null) {
				query.setTimestamp("genTimeFrom", obj.getGenTimeFrom());
				queryCount.setTimestamp("genTimeFrom", obj.getGenTimeFrom());
			}
			if (null != obj.getGenTimeTo() && null != obj.getGenTimeFrom()) {
				query.setTimestamp("genTimeFrom", obj.getGenTimeFrom());
				queryCount.setTimestamp("genTimeFrom", obj.getGenTimeFrom());

				query.setTimestamp("genTimeTo", obj.getGenTimeTo());
				queryCount.setTimestamp("genTimeTo", obj.getGenTimeTo());
			}
			
		if (StringUtils.isNotEmpty(obj.getDerpartment())) {
			query.setParameter("derpartment", "%" + ValidateUtils.validateKeySearch(obj.getDerpartment()) + "%");
			queryCount.setParameter("derpartment", "%" + ValidateUtils.validateKeySearch(obj.getDerpartment()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
			queryCount.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
    	
		  	
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}  
    
    
    public List<TblTimeWorkDTO> reportTimeLate(TblTimeWorkDTO obj){
    	StringBuilder sql = new StringBuilder("select * from (select  Name name,to_char(min(gen_time),'dd/MM/yyyy hh24:mi:ss') timeIn, ");
    	sql.append(" (case when to_char(min(gen_time),'dd/MM/yyyy hh24:mi:ss') > (select DISTINCT concat(to_char(gen_time,'dd/MM/yyyy'),' 08:00:00') day  from tbl_time_work) then 'Đi Muộn' else 'Đúng Giờ' end) statusIn ");
    	sql.append(" , to_char(max(gen_time),'dd/MM/yyyy hh24:mi:ss ') timeOut ");
    	sql.append(" , (case when to_char(max(gen_time),'dd/MM/yyyy hh24:mi:ss') < (select DISTINCT concat(to_char(gen_time,'dd/MM/yyyy'),' 17:30:00') day  from tbl_time_work) then 'Về Sớm' else 'Đúng Giờ' end) statusOut");
    	sql.append(" from tbl_time_work group by name ) T1 where 1=1 ");
    	
    	if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append("AND (UPPER(T1.name) LIKE UPPER(:name) ESCAPE '&') ");
		}
    	if (StringUtils.isNotEmpty(obj.getStatusIn())) {
			sql.append("AND (UPPER(T1.statusIn) LIKE UPPER(:statusIn) ESCAPE '&') ");
		}
    	if (StringUtils.isNotEmpty(obj.getStatusOut())) {
			sql.append("AND (UPPER(T1.statusOut) LIKE UPPER(:statusOut) ESCAPE '&') ");
		}
    	StringBuilder sqlCount = new StringBuilder(" select count(*) from ( ");
    	sqlCount.append(sql.toString());
    	sqlCount.append(" )"); 
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	SQLQuery queryCount =  getSession().createSQLQuery(sqlCount.toString());
//    	query.addScalar("derpartment", new StringType());
    	query.addScalar("name", new StringType());
    	query.addScalar("timeIn", new StringType());
    	query.addScalar("statusIn", new StringType());
    	query.addScalar("timeOut", new StringType());
    	query.addScalar("statusOut", new StringType());
    	
    	query.setResultTransformer(Transformers.aliasToBean(TblTimeWorkDTO.class));
    	
    	if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
			queryCount.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
    	if (StringUtils.isNotEmpty(obj.getStatusIn())) {
			query.setParameter("statusIn", "%" + ValidateUtils.validateKeySearch(obj.getStatusIn()) + "%");
			queryCount.setParameter("statusIn", "%" + ValidateUtils.validateKeySearch(obj.getStatusIn()) + "%");
		}
    	if (StringUtils.isNotEmpty(obj.getStatusOut())) {
			query.setParameter("statusOut", "%" + ValidateUtils.validateKeySearch(obj.getStatusOut()) + "%");
			queryCount.setParameter("statusOut", "%" + ValidateUtils.validateKeySearch(obj.getStatusOut()) + "%");
		}  	
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
    	return query.list();
    }
	
	public void getTruncate(){
		StringBuilder stringBuilder = new StringBuilder(" TRUNCATE TABLE TBL_TIME_WORK ");
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.executeUpdate();
	}
}
