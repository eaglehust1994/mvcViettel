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

import com.viettel.qll.bo.TblDutCapLoiCqBO;
import com.viettel.qll.dto.TblDutCapLoiCqDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblDutCapLoiCqDAO")
public class TblDutCapLoiCqDAO extends BaseFWDAOImpl<TblDutCapLoiCqBO, Long> {

	public TblDutCapLoiCqDAO() {
		this.model = new TblDutCapLoiCqBO();
	}

	public TblDutCapLoiCqDAO(Session session) {
		this.session = session;
	}

	public List<TblDutCapLoiCqDTO> doSearchDutCap(TblDutCapLoiCqDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_DUT_CAP_LOI_CQ_ID tblDutCapLoiCqId," + "THANG thang,"
				+ "KHU_VUC khuVuc," + "MA_TINH maTinh," + "NV_NHAN_GK nvNhanGk," + "MA_NV maNv,"
				+ "SO_LAN_DUT soLanDut," + "TIEN_PHAT tienPhat" + " from  TBL_DUT_CAP_LOI_CQ where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(
					" AND (upper(MA_NV) LIKE upper(:keySearch) escape '&') or (upper(MA_TINH) LIKE upper(:keySearch) escape '&') "
							+ " or (upper(NV_NHAN_GK) LIKE upper(:keySearch) escape '&') or (upper(KHU_VUC) LIKE upper(:keySearch) escape '&')  ");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And upper(MA_NV) like upper(:maNv) ");

		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			sql.append(" And  upper(MA_TINH) like upper(:maTinh) ");
		}
		if (StringUtils.isNotEmpty(obj.getKhuVuc())) {
			sql.append(" and upper(KHU_VUC) like upper(:khuVuc)");
		}
		if (StringUtils.isNotEmpty(obj.getNvNhanGk())) {
			sql.append(" and upper(NV_NHAN_GK) like upper(:nvNhanGk)");
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

		query.addScalar("tblDutCapLoiCqId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("khuVuc", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("nvNhanGk", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("soLanDut", new LongType());
		query.addScalar("tienPhat", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblDutCapLoiCqDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			query.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");
			queryCount.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getKhuVuc())) {
			query.setParameter("khuVuc", "%" + ValidateUtils.validateKeySearch(obj.getKhuVuc()) + "%");
			queryCount.setParameter("khuVuc", "%" + ValidateUtils.validateKeySearch(obj.getKhuVuc()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getNvNhanGk())) {
			query.setParameter("nvNhanGk", "%" + ValidateUtils.validateKeySearch(obj.getNvNhanGk()) + "%");
			queryCount.setParameter("nvNhanGk", "%" + ValidateUtils.validateKeySearch(obj.getNvNhanGk()) + "%");
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

	public Long deleteObj(TblDutCapLoiCqDTO obj) {
		try {
			String sql2 = "delete from TBL_DUT_CAP_LOI_CQ  where  MA_TINH=:maTinh and MA_NV=:maNv and THANG=:thang";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maTinh", obj.getMaTinh());
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
