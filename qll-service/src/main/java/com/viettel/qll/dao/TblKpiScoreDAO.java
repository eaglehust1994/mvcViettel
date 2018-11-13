package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import com.viettel.qll.bo.TblKpiScoreBO;

import com.viettel.qll.dto.TblKpiScoreDTO;


import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;



import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("tblKpiScoreDAO")
public class TblKpiScoreDAO extends BaseFWDAOImpl<TblKpiScoreBO, Long> {

    public TblKpiScoreDAO() {
        this.model = new TblKpiScoreBO();
    }

    public TblKpiScoreDAO(Session session) {
        this.session = session;
    }	
    

    public List<TblKpiScoreDTO> getListAllDepartment(TblKpiScoreDTO obj){
//    	List<TblKpiScoreDTO> listKPIScores = new ArrayList<>();
    	StringBuilder sql = new StringBuilder("");
    	sql.append(" select DEPARTMENTID departmentid");
    	sql.append(" ,DEPARTMENTNAME  departmentname ");
    	sql.append(" ,sum(SCOREBONUS) scorebonus ");
    	sql.append(" ,sum(SCOREPENALTY) scorepenalty");
    	sql.append(" ,sum(SCOREBONUS_CONFIRM) scorebonusConfirm ");
    	sql.append(" ,sum(SCOREPENALTY_CONFIRM) scorepenaltyConfirm ");
    	sql.append(" from TBL_KPI_SCORE  where 1=1 ");
    	
    	if (obj.getDepartmentid()!=null){
    		sql.append(" AND DEPARTMENTID= :departmentid ");
    	}
    	if(StringUtils.isNotEmpty(obj.getDepartmentname())){
			sql.append(" AND DEPARTMENTNAME=:departmentname ");
		}
		if (null != obj.getCreateddateFrom() && obj.getCreateddateTo() == null) {
			 sql.append(" AND EXTRACT(MONTH FROM TO_DATE(CREATEDDATE, 'DD-MON-RR')) = :month ");
			 
			  sql.append(" AND EXTRACT(YEAR FROM TO_DATE(CREATEDDATE, 'DD-MON-RR')) = :year " );
		}

		if (null != obj.getCreateddateFrom() && obj.getCreateddateTo() != null) {
			sql.append(" AND EXTRACT(MONTH FROM TO_DATE(CREATEDDATE, 'DD-MON-RR')) = :month ");
			  sql.append(" AND EXTRACT(YEAR FROM TO_DATE(CREATEDDATE, 'DD-MON-RR')) = :year " );
		}

		sql.append(" group by DEPARTMENTID,DEPARTMENTNAME ");
    	sql.append(" order by DEPARTMENTNAME ");
    	StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
//		try {
//			ResultSet cursor = DataController.getInstance().getStatement().executeQuery(sql.toString());
//			while(cursor.next()){
//				long departmentid = cursor.getLong(0);
//				
//				
//				//tao object
//				TblKpiScoreDTO item = new TblKpiScoreDTO();
//				item.setDepartmentid(departmentid);
//				listKPIScores.add(item);
//				//add vao danh sach
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
	
		
		query.addScalar("departmentid", new LongType()); 
		query.addScalar("departmentname", new StringType());
		query.addScalar("scorebonus", new FloatType());
		query.addScalar("scorepenalty", new FloatType());
		query.addScalar("scorebonusConfirm", new FloatType());
		query.addScalar("scorepenaltyConfirm", new FloatType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblKpiScoreDTO.class));
		
		if (obj.getDepartmentid()!=null){
			 query.setParameter("departmentid", obj.getDepartmentid()); 
			 queryCount.setParameter("departmentid", obj.getDepartmentid());
    	}
		if(StringUtils.isNotEmpty(obj.getDepartmentname())){
			query.setParameter("departmentname", obj.getDepartmentname());
			queryCount.setParameter("departmentname", obj.getDepartmentname());
		}
		if (null != obj.getCreateddateFrom() && obj.getCreateddateTo() == null) {
			LocalDate localDate = obj.getCreateddateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            query.setParameter("month", month);
            queryCount.setParameter("month", month);
            query.setParameter("year", year);
            queryCount.setParameter("year", year);
		}
		if (null != obj.getCreateddateFrom() && obj.getCreateddateTo() != null) {
			LocalDate localDate = obj.getCreateddateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            query.setParameter("month", month);
            queryCount.setParameter("month", month);
            query.setParameter("year", year);
            queryCount.setParameter("year", year);
		}
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
		
//		return listKPIScores;
    }
    
    public List<TblKpiScoreDTO> getInfoDetail(TblKpiScoreDTO obj){
//    	List<TblKpiScoreDTO> listKPIScores = new ArrayList<>();
    	StringBuilder sql = new StringBuilder("");
    	sql.append(" select KPIID kpiid ");
    	sql.append(",DEPARTMENTID departmentid ");
    	sql.append(",DEPARTMENTNAME departmentname ");
    	sql.append(",SCOREBONUS scorebonus ");
    	sql.append(",SCOREPENALTY scorepenalty ");
    	sql.append(",SCOREBONUS_CONFIRM scorebonusConfirm ");
    	sql.append(",SCOREPENALTY_CONFIRM scorepenaltyConfirm ");
    	sql.append(",REASON reason ");
    	sql.append(",USERIDCREATED useridcreated ");
    	sql.append(",USERNAMECREATED usernamecreated ");
    	sql.append(",FULLNAMECREATED fullnamecreated ");
    	sql.append(",USERID_MODIFIED useridModified ");
    	sql.append(",USERNAME_MODIFIED usernameModified ");
    	sql.append(",FULLNAME_MODIFIED fullnameModified ");
    	sql.append(",DEPARTMENTIDCREATED departmentidcreated ");
    	sql.append(",DEPARTMENTNAMECREATED departmentnamecreated ");
    	sql.append(",DEPARTMENTID_MODIFIED departmentidModified ");
    	sql.append(",DEPARTMENTNAME_MODIFIED departmentnameModified ");
    	sql.append(",REASON_CONFIRM reasonConfirm");
    	sql.append(",to_char(CREATEDDATE,'dd-MM-yyyy hh24:mi:ss') created_date ");
    	sql.append(",to_char(MODIFIEDDATE,'dd-MM-yyyy hh24:mi:ss') modified_date ");
    
    	sql.append("from TBL_KPI_SCORE  where 1=1  ");
    	
		if(obj.getDepartmentid()!=null){
			sql.append(" AND DEPARTMENTID=:departmentid ");
		}
		if(StringUtils.isNotEmpty(obj.getDepartmentname())){
			sql.append(" AND DEPARTMENTNAME=:departmentname ");
		}
    	if(obj.getUseridcreated()!=null){
    		sql.append(" AND USERIDCREATED=:useridcreated");
    	}
    	if(obj.getDepartmentidcreated()!=null){
    		sql.append(" AND DEPARTMENTIDCREATED=:departmentidcreated");
    	}
    	if(StringUtils.isNotEmpty(obj.getDepartmentnamecreated())){
			sql.append(" AND DEPARTMENTNAMECREATED=:departmentnamecreated ");
		}
    	if(obj.getScorebonus()!=null){
    		sql.append(" AND SCOREBONUS!=:scorebonus ");
    	}
    	if(obj.getScorepenalty()!=null){
    		sql.append(" AND SCOREPENALTY!=:scorepenalty");
    	}
    	if (null != obj.getCreateddateFrom() && obj.getCreateddateTo() == null) {
			 sql.append(" AND EXTRACT(MONTH FROM TO_DATE(CREATEDDATE, 'DD-MON-RR')) = :month ");			 
			  sql.append(" AND EXTRACT(YEAR FROM TO_DATE(CREATEDDATE, 'DD-MON-RR')) = :year " );
		}

		if (null != obj.getCreateddateFrom() && obj.getCreateddateTo() != null) {
			sql.append(" AND EXTRACT(MONTH FROM TO_DATE(CREATEDDATE, 'DD-MON-RR')) = :month ");
			  sql.append(" AND EXTRACT(YEAR FROM TO_DATE(CREATEDDATE, 'DD-MON-RR')) = :year " );
		}
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
//		try {
//			ResultSet cursor = DataController.getInstance().getStatement().executeQuery("select KPIID, DEPARTMENTID from TBL_KPI_SCORE ");
//			while(cursor.next()){
//				cursor.getLong("KPIID");
//				long kpiid = cursor.getLong(0);
//				long departmentid = cursor.getLong(1);
//				
//				
//				//tao object
//				TblKpiScoreDTO item = new TblKpiScoreDTO();
//				item.setKpiid(kpiid);
//				item.setDepartmentid(departmentid);
//				listKPIScores.add(item);
//				//add vao danh sach
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		
		query.addScalar("kpiid", new LongType());
		query.addScalar("departmentid", new LongType());
		query.addScalar("departmentname", new StringType());
		query.addScalar("scorebonus", new FloatType());
		query.addScalar("scorepenalty", new FloatType());
		query.addScalar("reason", new StringType());
		query.addScalar("useridcreated", new LongType());
		query.addScalar("usernamecreated", new StringType());
		query.addScalar("fullnamecreated", new StringType());
		
		query.addScalar("scorebonusConfirm", new FloatType());
		query.addScalar("scorepenaltyConfirm", new FloatType());
	
		query.addScalar("useridModified", new LongType());
		query.addScalar("usernameModified", new StringType());
		query.addScalar("fullnameModified", new StringType());
		
		query.addScalar("departmentidcreated", new LongType());
		query.addScalar("departmentnamecreated", new StringType());
		
		query.addScalar("departmentidModified", new LongType());
		query.addScalar("departmentnameModified", new StringType());
		query.addScalar("reasonConfirm", new StringType());
		
		query.addScalar("created_date", new StringType());
		query.addScalar("modified_date", new StringType());

		
		query.setResultTransformer(Transformers.aliasToBean(TblKpiScoreDTO.class));
		
		if(obj.getDepartmentid()!=null){
			query.setParameter("departmentid", obj.getDepartmentid());
			queryCount.setParameter("departmentid", obj.getDepartmentid());
		}
		if(StringUtils.isNotEmpty(obj.getDepartmentname())){
			query.setParameter("departmentname", obj.getDepartmentname());
			queryCount.setParameter("departmentname", obj.getDepartmentname());
		}
    	if(obj.getUseridcreated()!=null){
    		query.setParameter("useridcreated", obj.getUseridcreated());
    		queryCount.setParameter("useridcreated", obj.getUseridcreated());
    	}
    	if(obj.getDepartmentidcreated()!=null){
    		query.setParameter("departmentidcreated", obj.getDepartmentidcreated());
    		queryCount.setParameter("departmentidcreated", obj.getDepartmentidcreated());
    	}
    	if(StringUtils.isNotEmpty(obj.getDepartmentnamecreated())){
			query.setParameter("departmentnamecreated", obj.getDepartmentnamecreated());
			queryCount.setParameter("departmentnamecreated", obj.getDepartmentnamecreated());
		}
    	if(obj.getScorebonus()!=null){
    		query.setParameter("scorebonus", obj.getScorebonus());
    		queryCount.setParameter("scorebonus", obj.getScorebonus());
    	}
    	if(obj.getScorepenalty()!=null){
    		query.setParameter("scorepenalty", obj.getScorepenalty());
    		queryCount.setParameter("scorepenalty", obj.getScorepenalty());
    	}
		if (null != obj.getCreateddateFrom() && obj.getCreateddateTo() == null) {
			LocalDate localDate = obj.getCreateddateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            query.setParameter("month", month);
            queryCount.setParameter("month", month);
            query.setParameter("year", year);
            queryCount.setParameter("year", year);
		}
		if (null != obj.getCreateddateFrom() && obj.getCreateddateTo() != null) {
			LocalDate localDate = obj.getCreateddateFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            query.setParameter("month", month);
            queryCount.setParameter("month", month);
            query.setParameter("year", year);
            queryCount.setParameter("year", year);
            
		}
		
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
//		return listKPIScores;
    }
    
	
	
	public List<TblKpiScoreDTO> getNotifyByDepartment(TblKpiScoreDTO obj){
	
		StringBuilder sql = new StringBuilder("");
    	sql.append(" select KPIID kpiid ");
    	sql.append(",DEPARTMENTID departmentid ");
    	sql.append(",DEPARTMENTNAME departmentname ");
    	sql.append(",SCOREBONUS scorebonus ");
    	sql.append(",SCOREPENALTY scorepenalty ");
    	sql.append(",SCOREBONUS_CONFIRM scorebonusConfirm ");
    	sql.append(",SCOREPENALTY_CONFIRM scorepenaltyConfirm ");
    	sql.append(",REASON reason ");
    	sql.append(",USERIDCREATED useridcreated ");
    	sql.append(",USERNAMECREATED usernamecreated ");
    	sql.append(",FULLNAMECREATED fullnamecreated ");
    	sql.append(",USERID_MODIFIED useridModified ");
    	sql.append(",USERNAME_MODIFIED usernameModified ");
    	sql.append(",FULLNAME_MODIFIED fullnameModified ");
    	sql.append(",DEPARTMENTIDCREATED departmentidcreated ");
    	sql.append(",DEPARTMENTNAMECREATED departmentnamecreated ");
    	sql.append(",DEPARTMENTID_MODIFIED departmentidModified ");
    	sql.append(",DEPARTMENTNAME_MODIFIED departmentnameModified ");
    	sql.append(",to_char(CREATEDDATE,'dd-MM-yyyy hh24:mi:ss') created_date ");
    	sql.append(",to_char(MODIFIEDDATE,'dd-MM-yyyy hh24:mi:ss') modified_date ");
    	sql.append(" from TBL_KPI_SCORE  ");
 
		
    	sql.append(" where DEPARTMENTID=:departmentid ");
    	if (null != obj.getModifieddate()) {
			sql.append("AND MODIFIEDDATE >= :modifieddate ");
		}
		sql.append(" order by to_char(MODIFIEDDATE,'dd-MM-yyyy hh24:mi:ss') desc ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		
		
		query.addScalar("kpiid", new LongType());
		query.addScalar("departmentid", new LongType());
		query.addScalar("departmentname", new StringType());
		query.addScalar("scorebonus", new FloatType());
		query.addScalar("scorepenalty", new FloatType());
		query.addScalar("reason", new StringType());
		query.addScalar("useridcreated", new LongType());
		query.addScalar("usernamecreated", new StringType());
		query.addScalar("fullnamecreated", new StringType());
		
		query.addScalar("scorebonusConfirm", new FloatType());
		query.addScalar("scorepenaltyConfirm", new FloatType());
	
		query.addScalar("useridModified", new LongType());
		query.addScalar("usernameModified", new StringType());
		query.addScalar("fullnameModified", new StringType());
		
		query.addScalar("departmentidcreated", new LongType());
		query.addScalar("departmentnamecreated", new StringType());
		
		query.addScalar("departmentidModified", new LongType());
		query.addScalar("departmentnameModified", new StringType());
		query.addScalar("created_date", new StringType());
		query.addScalar("modified_date", new StringType());

		
		query.setResultTransformer(Transformers.aliasToBean(TblKpiScoreDTO.class));
		
		query.setParameter("departmentid", obj.getDepartmentid());
		if (null != obj.getModifieddate()) {
			query.setParameter("modifieddate", obj.getModifieddate());
		}
	
		return query.list();
	}
	
	public List<TblKpiScoreDTO> getAutoDepartmentKPI(TblKpiScoreDTO obj){
		StringBuilder sql = new StringBuilder("");
		sql.append("Select distinct DEPARTMENTNAME departmentname from TBL_KPI_SCORE where 1=1");
		sql.append(" AND ROWNUM<= 10 ");
		if(StringUtils.isNotEmpty(obj.getDepartmentname())){
			sql.append(" AND (upper(DEPARTMENTNAME) LIKE upper(:departmentname) escape '&' )");
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("departmentname", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblKpiScoreDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getDepartmentname())) {
			query.setParameter("departmentname", "%" + ValidateUtils.validateKeySearch(obj.getDepartmentname()) + "%");
		}

		return query.list();
	}
	
	public List<TblKpiScoreDTO> getAutoDepartmentCreatedKPI(TblKpiScoreDTO obj){
		StringBuilder sql = new StringBuilder("");
		sql.append("Select distinct DEPARTMENTNAMECREATED departmentnamecreated from TBL_KPI_SCORE where 1=1");
		sql.append(" AND ROWNUM<= 10 ");
		if(StringUtils.isNotEmpty(obj.getDepartmentnamecreated())){
			sql.append(" AND (upper(DEPARTMENTNAMECREATED) LIKE upper(:departmentnamecreated) escape '&' )");
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("departmentnamecreated", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblKpiScoreDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getDepartmentnamecreated())) {
			query.setParameter("departmentnamecreated", "%" + ValidateUtils.validateKeySearch(obj.getDepartmentnamecreated()) + "%");
		}

		return query.list();
	}
	
	public void updateDecided(TblKpiScoreDTO obj){
		try{
			StringBuilder sql = new StringBuilder("");
			sql.append(" update TBL_KPI_SCORE set SCOREBONUS_CONFIRM=:scorebonusConfirm, ");
			sql.append(" SCOREPENALTY_CONFIRM=:scorepenaltyConfirm, ");
			sql.append(" USERID_MODIFIED=:useridModified, ");
			sql.append(" USERNAME_MODIFIED=:usernameModified, ");
			sql.append(" FULLNAME_MODIFIED=:fullnameModified, ");
			sql.append(" DEPARTMENTID_MODIFIED=:departmentidModified, ");
			sql.append(" DEPARTMENTNAME_MODIFIED=:departmentnameModified, ");
			sql.append(" MODIFIEDDATE=:modifieddate, ");
			sql.append(" REASON_CONFIRM=:reasonConfirm ");
			sql.append("  where KPIID=:kpiid ");
			
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.setParameter("scorebonusConfirm", obj.getScorebonusConfirm());
			query.setParameter("scorepenaltyConfirm", obj.getScorepenaltyConfirm());
			query.setParameter("useridModified", obj.getUseridModified());
			query.setParameter("usernameModified", obj.getUsernameModified());
			query.setParameter("fullnameModified", obj.getFullnameModified());
			query.setParameter("departmentidModified", obj.getDepartmentidModified());
			query.setParameter("departmentnameModified", obj.getDepartmentnameModified());
			query.setParameter("modifieddate", obj.getModifieddate());
			query.setParameter("reasonConfirm", obj.getReasonConfirm());
			query.setParameter("kpiid", obj.getKpiid());
			query.executeUpdate();
		}catch (Exception e) {
			e.getMessage();
			
		}
	}
	
}
