package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.LsThaoTacBO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("lsThaoTacDAO")
public class LsThaoTacDAO extends BaseFWDAOImpl<LsThaoTacBO, Long> {

	@Autowired
	private SessionFactory sessionFactory;
	public Session session;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public LsThaoTacDAO() {
		this.model = new LsThaoTacBO();
	}

	public LsThaoTacDAO(Session session) {
		this.session = session;
	}

	@Transactional
	public String saveList1(List<LsThaoTacBO> obj) {
		try {
			for (LsThaoTacBO item : obj) {
				getSession().save(item);
				System.out.println("ok");
			}

		} catch (SecurityException ex) {
			return ex.getMessage();
		} catch (MappingException e) {
			return e.getMessage();
		}
		return "SUCCESS";
	}

	@Transactional
	public long save1(LsThaoTacDTO obj) {
		try {
			getSession().save(obj);
			return 1l;
		} catch (HibernateException he) {
			log.error(he.getMessage(), he);
		}
		return 0l;
	}

	public List<LsThaoTacDTO> doSearch(LsThaoTacDTO obj) throws ParseException {
		StringBuilder sql = new StringBuilder("SELECT ID_THAO_TAC idThaoTac" + ",CHUC_NANG chucNang"
				+ ",USER_CODE userCode" + ",FULL_NAME fullName" + ",IP_ADD ipAdd" + ",NGAY_THAO_TAC ngayThaoTac"
				+ ",MO_TA moTa" + ",THOI_GIAN_THUC_HIEN thoiGianThucHien " + " from LS_THAO_TAC where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (upper(CHUC_NANG) LIKE upper(:keySearch) escape '&') "
					+ "or (upper(USER_CODE) LIKE upper(:keySearch) escape '&')"
					+ "or (upper(FULL_NAME) LIKE upper(:keySearch) escape '&') "
					+ "or (upper(IP_ADD) LIKE upper(:keySearch) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getUserCode())) {
			sql.append(" And upper(USER_CODE) like upper(:userCode) ");
		}
		if (StringUtils.isNotEmpty(obj.getChucNang())) {
			sql.append(" And upper(CHUC_NANG) like upper(:chucNang) ");
		}
		if (StringUtils.isNotEmpty(obj.getIpAdd())) {
			sql.append(" And upper(IP_ADD) = upper(:ipAdd) ");
		}

		if (obj.getNgayThaoTacFrom() != null && (obj.getNgayThaoTacTo() == null)) {
			sql.append(" AND NGAY_THAO_TAC >= :ngayThaoTacFrom");
		}
		if (obj.getNgayThaoTacFrom() != null && (obj.getNgayThaoTacTo() != null)) {
			Date xx=new Date(obj.getNgayThaoTacTo().getTime()+24*3600*1000);
			java.sql.Timestamp sq = new java.sql.Timestamp(xx.getTime());
			formatter.format(sq);
			obj.setNgayThaoTacTo(xx);
			sql.append(" AND NGAY_THAO_TAC between :ngayThaoTacFrom and :ngayThaoTacTo");
			log.debug("ababcabc"+obj.getNgayThaoTacTo());
		}
		 sql.append(" order by NGAY_THAO_TAC desc");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("idThaoTac", new LongType());
		query.addScalar("chucNang", new StringType());
		query.addScalar("userCode", new StringType());
		query.addScalar("fullName", new StringType());
		query.addScalar("ipAdd", new StringType());
		query.addScalar("ngayThaoTac", new TimestampType());
		query.addScalar("moTa", new StringType());
		query.addScalar("thoiGianThucHien", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(LsThaoTacDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getUserCode())) {
			query.setParameter("userCode", "%" + ValidateUtils.validateKeySearch(obj.getUserCode()) + "%");
			queryCount.setParameter("userCode", "%" + ValidateUtils.validateKeySearch(obj.getUserCode()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getChucNang())) {
			query.setParameter("chucNang", "%" + ValidateUtils.validateKeySearch(obj.getChucNang()) + "%");
			queryCount.setParameter("chucNang", "%" + ValidateUtils.validateKeySearch(obj.getChucNang()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getIpAdd())) {
			query.setParameter("ipAdd", obj.getIpAdd());
			queryCount.setParameter("ipAdd",obj.getIpAdd());
		}
		
		if (obj.getNgayThaoTacFrom() != null && (obj.getNgayThaoTacTo() == null)) {
			query.setTimestamp("ngayThaoTacFrom", obj.getNgayThaoTacFrom());
			queryCount.setTimestamp("ngayThaoTacFrom", obj.getNgayThaoTacFrom());
		}
		if (obj.getNgayThaoTacFrom() != null && (obj.getNgayThaoTacTo() != null)) {
			query.setTimestamp("ngayThaoTacFrom", obj.getNgayThaoTacFrom());
			queryCount.setTimestamp("ngayThaoTacFrom", obj.getNgayThaoTacFrom());

			query.setTimestamp("ngayThaoTacTo", obj.getNgayThaoTacTo());
			queryCount.setTimestamp("ngayThaoTacTo", obj.getNgayThaoTacTo());
		}

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}

}
