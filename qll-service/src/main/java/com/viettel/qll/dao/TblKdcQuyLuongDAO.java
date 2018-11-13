package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.MappingException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.qll.bo.TblKdcQuyLuongBO;
import com.viettel.qll.bo.TblTypeAPxkBO;
import com.viettel.qll.dto.TblKdcLuongCoDongDTO;
import com.viettel.qll.dto.TblKdcQuyLuongDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;
import com.viettel.wms.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblKdcQuyLuongDAO")
public class TblKdcQuyLuongDAO extends BaseFWDAOImpl<TblKdcQuyLuongBO, Long> {

	public TblKdcQuyLuongDAO() {
		this.model = new TblKdcQuyLuongBO();
	}

	public TblKdcQuyLuongDAO(Session session) {
		this.session = session;
	}

	public List<TblKdcQuyLuongDTO> doSearchQuyLuong(TblKdcQuyLuongDTO obj) {
		StringBuilder sql = new StringBuilder(" SELECT TBL_KDC_QUY_LUONG_ID tblKdcQuyLuongId," + "THANG thang, "
				+ "DON_VI donVi, " + "KDC kdc " + "FROM TBL_KDC_QUY_LUONG " + "WHERE HOAT_DONG = 1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(DON_VI) LIKE upper(:keySearch) escape '&')"
					+ "AND (upper(KDC) LIKE upper(:keySearch) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			sql.append(" And  upper(DON_VI) like upper(:donVi) ");

		}
		if (obj.getKdc() != null) {
			sql.append(" And KDC =:kdc ");
		}

		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(THANG, 'MM/yyyy'))in(:exNam)");
		}
		sql.append(" ORDER BY TBL_KDC_QUY_LUONG_ID");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblKdcQuyLuongId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("donVi", new StringType());
		query.addScalar("kdc", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblKdcQuyLuongDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			query.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
			queryCount.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
		}
		if (obj.getKdc() != null) {
			query.setParameter("kdc", obj.getKdc());
			queryCount.setParameter("kdc", obj.getKdc());
		}

		if (StringUtils.isNotEmpty(obj.getExThang())) {
			query.setParameter("exThang", obj.getExThang());
			queryCount.setParameter("exThang", obj.getExThang());
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			query.setParameter("exNam", obj.getExNam());
			queryCount.setParameter("exNam", obj.getExNam());
		}

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	public Long deleteObj(TblKdcQuyLuongDTO obj) {
		try {
			String sql2 = "delete from TBL_KDC_QUY_LUONG  where  DON_VI=:donVi  and THANG=:thang";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("donVi", obj.getDonVi());
			query2.setParameter("thang", obj.getThang());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

	@Transactional
	  public String saveList1(List<TblKdcQuyLuongBO> obj)
	  {
	    try
	    {
	      for (TblKdcQuyLuongBO item : obj){
	    	  getSession().save(item);
	    	  System.out.println("ok");
	      }
	        
	    }
	    catch (SecurityException ex)
	    {
	      return ex.getMessage();
	    }catch (MappingException e) {
	    	return e.getMessage();
		}
	    return "SUCCESS";
	  }
}
