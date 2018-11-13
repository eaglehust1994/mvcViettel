package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.TblPhiBanHangBO;
import com.viettel.qll.dto.TblPhiBanHangDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblPhiBanHangDAO")
public class TblPhiBanHangDAO extends BaseFWDAOImpl<TblPhiBanHangBO, Long> {

    public TblPhiBanHangDAO() {
        this.model = new TblPhiBanHangBO();
    }

    public TblPhiBanHangDAO(Session session) {
        this.session = session;
    }	
    
    public List<TblPhiBanHangDTO> doSearchPhiBanHang(TblPhiBanHangDTO obj){
    	StringBuilder sql = new StringBuilder(" SELECT TBL_PHI_BAN_HANG_ID tblPhiBanHangId,"
    			+ "THANG thang, "
    			+ "MA_TINH maTinh, "
    			+ "MA_NV maNv, "
    			+ "PHI_TRUOC_THUE phiTruocThue,"
    			+ "PHI_SAU_THUE phiSauThue,"
    			+ "TEN_CTV tenCtv,"
    			+ "TONG_THUE_BAO tongThueBao,"
    			+ "USER_BAN_HANG userBanHang "
    			+ "FROM TBL_PHI_BAN_HANG "
				+ "WHERE HOAT_DONG = 1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(MA_TINH) LIKE upper(:keySearch) escape '&')"
					+ "AND (upper(MA_NV) LIKE upper(:keySearch) escape '&')"
					+ "AND (upper(USER_BAN_HANG) LIKE upper(:keySearch) escape '&')"
					+ "AND (upper(TEN_CTV) LIKE upper(:keySearch) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getUserBanHang())) {
			sql.append(" And  upper(USER_BAN_HANG) like upper(:userBanHang) ");

		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			sql.append(" And  upper(MA_TINH) like upper(:maTinh) ");

		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And  upper(MA_NV) like upper(:maNv) ");

		}
		if (StringUtils.isNotEmpty(obj.getTenCtv())) {
			sql.append(" And  upper(TEN_CTV) like upper(:tenCtv)");

		}

		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(THANG, 'MM/yyyy'))in(:exNam)");
		}
		sql.append(" ORDER BY TBL_PHI_BAN_HANG_ID");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblPhiBanHangId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("phiTruocThue", new FloatType());
		query.addScalar("phiSauThue", new FloatType());
		query.addScalar("tenCtv", new StringType());
		query.addScalar("tongThueBao", new LongType());
		query.addScalar("userBanHang", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(TblPhiBanHangDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		
		if (StringUtils.isNotEmpty(obj.getUserBanHang())) {
			query.setParameter("userBanHang", "%" + ValidateUtils.validateKeySearch(obj.getUserBanHang()) + "%");
			queryCount.setParameter("userBanHang", "%" + ValidateUtils.validateKeySearch(obj.getUserBanHang()) + "%");

		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			query.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");
			queryCount.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenCtv())) {
			query.setParameter("tenCtv", "%" + ValidateUtils.validateKeySearch(obj.getTenCtv()) + "%");
			queryCount.setParameter("tenCtv", "%" + ValidateUtils.validateKeySearch(obj.getTenCtv()) + "%");


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
    
    public long countPhiBanHangByMaNvAndThang(String maNv, String thang) {
        //getting session object from session factory
        String hql = "from TblPhiBanHang kicn where upper(kicn.MA_NV) = upper(:maNv) and kicn.THANG = :thang";
        SQLQuery query = getSession().createSQLQuery(hql);
        query.setParameter("maNv", maNv);
        query.setParameter("thang", thang);
        List result = query.list();
        return result.size();
    }
    public Long deleteObj(TblPhiBanHangDTO obj) {
		try {
			String sql2 = "delete from TBL_PHI_BAN_HANG  where  MA_NV=:maNv  and THANG=:thang";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maNv", obj.getMaNv());
			query2.setParameter("thang", obj.getThang());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

}
