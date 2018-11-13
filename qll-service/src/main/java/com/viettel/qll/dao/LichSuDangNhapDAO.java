package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.LichSuDangNhapBO;
import com.viettel.qll.dto.LichSuDangNhapDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("lichSuDangNhapDAO")
public class LichSuDangNhapDAO extends BaseFWDAOImpl<LichSuDangNhapBO, Long> {

	public LichSuDangNhapDAO() {
		this.model = new LichSuDangNhapBO();
	}

	public LichSuDangNhapDAO(Session session) {
		this.session = session;
	}
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public List<LichSuDangNhapDTO> doSearch(LichSuDangNhapDTO obj) throws ParseException {
		StringBuilder sql = new StringBuilder("SELECT ID id" + ",USER_CODE userCode" + ",IP_DANG_NHAP ipDangNhap"
				+ ",GIO_DANG_NHAP gioDangNhap" + ",TRANG_THAI trangThai " + " from LICH_SU_DANG_NHAP where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(IP_DANG_NHAP) LIKE upper(:keySearch) escape '&') "
					+ "or (upper(USER_CODE) LIKE upper(:keySearch) escape '&')");
		}

		if (StringUtils.isNotEmpty(obj.getUserCode())) {
			sql.append(" And upper(USER_CODE) like upper(:userCode) ");
		}
		if (StringUtils.isNotEmpty(obj.getIpDangNhap())) {
			sql.append(" And upper(IP_DANG_NHAP) = upper(:ipDangNhap) ");
		}

		if (obj.getGioDangNhapFrom() != null && (obj.getGioDangNhapTo() == null)) {
			sql.append(" AND GIO_DANG_NHAP >= :gioDangNhapFrom");
		}
		if (obj.getGioDangNhapFrom() != null && (obj.getGioDangNhapTo() != null)) {
			Date xx = new Date(obj.getGioDangNhapTo().getTime() + 24 * 3600 * 1000);
			java.sql.Timestamp sq = new java.sql.Timestamp(xx.getTime());
			formatter.format(sq);
			obj.setGioDangNhapTo(xx);
			sql.append(" AND GIO_DANG_NHAP between :gioDangNhapFrom and :gioDangNhapTo ");
			log.debug("ababcabc" + obj.getGioDangNhapTo());
		}
		 sql.append(" order by GIO_DANG_NHAP desc");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("userCode", new StringType());
		query.addScalar("ipDangNhap", new StringType());
		query.addScalar("gioDangNhap", new TimestampType());
		query.addScalar("trangThai", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(LichSuDangNhapDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getUserCode())) {
			query.setParameter("userCode", "%" + ValidateUtils.validateKeySearch(obj.getUserCode()) + "%");
			queryCount.setParameter("userCode", "%" + ValidateUtils.validateKeySearch(obj.getUserCode()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getIpDangNhap())) {
			query.setParameter("ipDangNhap", obj.getIpDangNhap());
			queryCount.setParameter("ipDangNhap",obj.getIpDangNhap() );
		}

		if (obj.getGioDangNhapFrom() != null && (obj.getGioDangNhapTo() == null)) {
			query.setTimestamp("gioDangNhapFrom", obj.getGioDangNhapFrom());
			queryCount.setTimestamp("gioDangNhapFrom", obj.getGioDangNhapFrom());
		}
		if (obj.getGioDangNhapFrom() != null && (obj.getGioDangNhapTo() != null)) {
			query.setTimestamp("gioDangNhapFrom", obj.getGioDangNhapFrom());
			queryCount.setTimestamp("gioDangNhapFrom", obj.getGioDangNhapFrom());

			query.setTimestamp("gioDangNhapTo", obj.getGioDangNhapTo());
			queryCount.setTimestamp("gioDangNhapTo", obj.getGioDangNhapTo());
		}

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}

}
