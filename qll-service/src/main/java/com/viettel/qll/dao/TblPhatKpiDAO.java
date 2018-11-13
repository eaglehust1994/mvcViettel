package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblPhatKpiBO;
import com.viettel.qll.dto.TblDutCapLoiCqDTO;
import com.viettel.qll.dto.TblPhatKpiDTO;
//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

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
@Repository("tblPhatKpiDAO")
public class TblPhatKpiDAO extends BaseFWDAOImpl<TblPhatKpiBO, Long> {

	public TblPhatKpiDAO() {
		this.model = new TblPhatKpiBO();
	}

	public TblPhatKpiDAO(Session session) {
		this.session = session;
	}

	public List<TblPhatKpiDTO> doSearchPhatKPI(TblPhatKpiDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT  TBL_PHAT_KPI_ID tblPhatKpiId," + "THANG thang," + "KV kv," + "MA_TINH maTinh," + "MA_NV maNv,"
						+ "MA_TRAM maTram," + "SO_WO_QUA_HAN soWoQuaHan," + "SO_BLOCK_WO_QUA_HAN soBlockWoQuaHan,"
						+ "DIEM_KIEM_TRA_TRAM diemKiemTraTram" + " from TBL_PHAT_KPI where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(
					" AND (upper(MA_NV) LIKE upper(:keySearch) escape '&') or (upper(MA_TINH) LIKE upper(:keySearch) escape '&') "
							+ " or (upper(KV) LIKE upper(:keySearch) escape '&') or (upper(MA_TRAM) LIKE upper(:keySearch) escape '&')  ");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And upper(MA_NV) like upper(:maNv) ");

		}
		if (StringUtils.isNotEmpty(obj.getdonVi())) {
			sql.append(" And  upper(MA_TINH) like upper(:donVi) ");
		}
		if (StringUtils.isNotEmpty(obj.getKv())) {
			sql.append(" and upper(KV) like upper(:kv)");
		}
		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			sql.append(" and upper(MA_TRAM) like upper(:maTram)");
		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(THANG, 'MM/yyyy'))in(:exNam)");
		}
		// sql.append(" And rownum<20");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblPhatKpiId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("kv", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("maTram", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("soBlockWoQuaHan", new FloatType());
		query.addScalar("soWoQuaHan", new FloatType());
		query.addScalar("diemKiemTraTram", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblPhatKpiDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getdonVi())) {
			query.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getdonVi()) + "%");
			queryCount.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getdonVi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getKv())) {
			query.setParameter("kv", "%" + ValidateUtils.validateKeySearch(obj.getKv()) + "%");
			queryCount.setParameter("kv", "%" + ValidateUtils.validateKeySearch(obj.getKv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			query.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");
			queryCount.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");
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

	public Long deleteObj(TblPhatKpiDTO obj) {
		try {
			String sql2 = "delete from TBL_PHAT_KPI  where  MA_TRAM=:maTram and MA_NV=:maNv and THANG=:thang";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maTram", obj.getMaTram());
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
