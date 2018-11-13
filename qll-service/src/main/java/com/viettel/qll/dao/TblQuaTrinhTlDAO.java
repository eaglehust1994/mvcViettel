package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.TblQuaTrinhTlBO;
import com.viettel.qll.dto.TblQuaTrinhTlDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblQuaTrinhTlDAO")
public class TblQuaTrinhTlDAO extends BaseFWDAOImpl<TblQuaTrinhTlBO, Long> {

	public TblQuaTrinhTlDAO() {
		this.model = new TblQuaTrinhTlBO();
	}

	public TblQuaTrinhTlDAO(Session session) {
		this.session = session;
	}

	public List<TblQuaTrinhTlDTO> doSearchQTTL(TblQuaTrinhTlDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT T1.TBL_QUA_TRINH_TL_ID tblQuaTrinhTlId " + ",T1.DON_VI donVi "
				+ ",T1.MA_TRAM maTram" + ",T1.MA_QTTL maQttl " + "FROM TBL_QUA_TRINH_TL T1 WHERE 1=1");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(
					" AND (upper(T1.DON_VI) LIKE upper(:keySearch) escape '&') or (upper(T1.MA_TRAM) LIKE upper(:keySearch) escape '&')"
							+ "or (upper(T1.MA_QTTL) LIKE upper(:keySearch) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			sql.append(" and upper(T1.DON_VI) like upper(:donVi)");
		}
		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			sql.append(" and upper(T1.MA_TRAM) like upper(:maTram)");
		}
		if (StringUtils.isNotEmpty(obj.getMaQttl())) {
			sql.append(" and upper(T1.MA_QTTL) like upper(:maQttl)escape '&'");
		}

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblQuaTrinhTlId", new LongType());
		query.addScalar("donVi", new StringType());
		query.addScalar("maTram", new StringType());
		query.addScalar("maQttl", new StringType());

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			query.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
			queryCount.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			query.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");
			queryCount.setParameter("maTram", "%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaQttl())) {
			query.setParameter("maQttl", "%" + ValidateUtils.validateKeySearch(obj.getMaQttl()) + "%");
			queryCount.setParameter("maQttl", "%" + ValidateUtils.validateKeySearch(obj.getMaQttl()) + "%");
		}

		query.setResultTransformer(Transformers.aliasToBean(TblQuaTrinhTlDTO.class));
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	public Long deleteObj(TblQuaTrinhTlDTO obj) {
		try {
			String sql2 = "delete from TBL_QUA_TRINH_TL  where  DON_VI=:donVi and MA_TRAM=:maTram and MA_QTTL=:maQttl";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("donVi", obj.getDonVi());
			query2.setParameter("maTram", obj.getMaTram());
			query2.setParameter("maQttl", obj.getMaQttl());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

}
