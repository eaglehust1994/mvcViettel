package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblDmKiDonViBO;
import com.viettel.qll.dto.TblDmKiDonViDTO;
import com.viettel.qll.dto.TblDutCapLoiCqDTO;

//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("tblDmKiDonViDAO")
public class TblDmKiDonViDAO extends BaseFWDAOImpl<TblDmKiDonViBO, Long> {

    public TblDmKiDonViDAO() {
        this.model = new TblDmKiDonViBO();
    }

    public TblDmKiDonViDAO(Session session) {
        this.session = session;
    }	
    public List<TblDmKiDonViDTO> doSearchKiDonVi(TblDmKiDonViDTO obj){
    	StringBuilder sql = new StringBuilder(" SELECT ki.TBL_DM_KI_DON_VI_ID tblDmKiDonViId,"
    			+ "ki.THANG thang, "
    			+ "ki.TINH tinh, "
    			+ "ki.KI_DON_VI kiDonVi, "
    			+ "ki.HE_SO heSo "
    			+ "FROM TBL_DM_KI_DON_VI ki "
				+ "WHERE ki.HOAT_DONG = 1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(ki.KI_DON_VI) LIKE upper(:keySearch) escape '&')"
					+ "AND (upper(ki.TINH) LIKE upper(:keySearch) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			sql.append(" And  ki.TINH =:tinh ");

		}

		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(ki.THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(ki.THANG, 'MM/yyyy'))in(:exNam)");
		}
		sql.append(" ORDER BY ki.TBL_DM_KI_DON_VI_ID");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblDmKiDonViId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("kiDonVi", new FloatType());
		query.addScalar("heSo", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblDmKiDonViDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
			queryCount.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getExThang())) {
			query.setParameter("exThang", obj.getExThang());
			queryCount.setParameter("exThang", obj.getExThang());
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			query.setParameter("exNam", obj.getExNam());
			queryCount.setParameter("exNam", obj.getExNam());
		}

		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
    }
    
    public List<TblDmKiDonViDTO> getAll(){
    	StringBuilder sql = new StringBuilder(" SELECT ki.TBL_DM_KI_DON_VI_ID tblDmKiDonViId,"
    			+ "ki.THANG thang, "
    			+ "ki.TINH tinh, "
    			+ "ki.KI_DON_VI kiDonVi, "
    			+ "ki.HE_SO heSo "
    			+ "FROM TBL_DM_KI_DON_VI ki "
				+ "WHERE ki.HOAT_DONG = 1 ");		
		
		sql.append(" ORDER BY ki.TBL_DM_KI_DON_VI_ID");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblDmKiDonViId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("kiDonVi", new FloatType());
		query.addScalar("heSo", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblDmKiDonViDTO.class));
		/*if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());*/
		
		return query.list();
    }
    public List<TblDmKiDonViDTO> countKiDonViByTinh(String tinh,String thang){
    	StringBuilder sql = new StringBuilder(" SELECT ki.TBL_DM_KI_DON_VI_ID tblDmKiDonViId,"
    			+ "ki.THANG thang, "
    			+ "ki.TINH tinh, "
    			+ "ki.KI_DON_VI kiDonVi, "
    			+ "ki.HE_SO heSo "
    			+ "FROM TBL_DM_KI_DON_VI ki "
				+ "WHERE ki.HOAT_DONG = 1 And ki.TINH=:tinh and ki.THANG=:thang");		
		

		sql.append(" ORDER BY ki.TBL_DM_KI_DON_VI_ID");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblDmKiDonViId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("kiDonVi", new FloatType());
		query.addScalar("heSo", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblDmKiDonViDTO.class));
		query.setParameter("tinh", tinh);
		query.setParameter("thang", thang);
		
		
		return query.list();
    }
    
    public Long deleteObj(TblDmKiDonViDTO obj) {
		try {
			String sql2 = "delete from TBL_DM_KI_DON_VI  where  TINH=:tinh  and THANG=:thang";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("tinh", obj.getTinh());
			query2.setParameter("thang", obj.getThang());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
   
}
