package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblDonGiaTramBO;
import com.viettel.qll.dto.TblDonGiaTramDTO;
import com.viettel.qll.dto.TblDutCapLoiCqDTO;
import com.viettel.qll.dto.TblPhatKhacDTO;

//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
@Repository("tblDonGiaTramDAO")
public class TblDonGiaTramDAO extends BaseFWDAOImpl<TblDonGiaTramBO, Long> {

	public TblDonGiaTramDAO() {
		this.model = new TblDonGiaTramBO();
	}

	public TblDonGiaTramDAO(Session session) {
		this.session = session;
	}

	public List<TblDonGiaTramDTO> doSearchDonGiaTram(TblDonGiaTramDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_DON_GIA_TRAM_ID tblDonGiaTramId," + "VUNG_LUONG vungLuong,"
				+ "DIA_HINH diaHinh," + "DON_GIA donGia," + "GHI_CHU ghiChu," + "LOAI_TRAM loaiTram," + "PL pl"
				+ " from TBL_DON_GIA_TRAM  where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(
					" AND (upper(LOAI_TRAM) LIKE upper(:keySearch) escape '&') or (upper(DIA_HINH) LIKE upper(:keySearch) escape '&')"
							+ "or (upper(DON_GIA) LIKE upper(:keySearch) escape '&')or (upper(VUNG_LUONG) LIKE upper(:keySearch) escape '&')"
							+ "or (upper(PL) LIKE upper(:keySearch) escape '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getVungLuong())) {
			sql.append(" And upper(VUNG_LUONG) like upper(:vungLuong) ");

		}

		if (StringUtils.isNotEmpty(obj.getDiaHinh())) {
			sql.append(" And upper(DIA_HINH) like upper(:diaHinh) ");

		}
		if (obj.getDonGia() != null && (!obj.getDonGia().equals(""))) {
			sql.append(" And DON_GIA = :donGia ");

		}
		if (StringUtils.isNotEmpty(obj.getLoaiTram())) {
			sql.append(" And upper(LOAI_TRAM) like upper(:loaiTram) ");

		}
		if (obj.getPl() != null && (!obj.getPl().equals(""))) {
			sql.append(" And upper(PL) like upper(:pl) ");
		}

		// sql.append(" And rownum<20");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblDonGiaTramId", new LongType());
		query.addScalar("vungLuong", new StringType());
		query.addScalar("diaHinh", new StringType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("loaiTram", new StringType());
		query.addScalar("ghiChu", new StringType());
		query.addScalar("pl", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(TblDonGiaTramDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getVungLuong())) {
			query.setParameter("vungLuong", "%" + ValidateUtils.validateKeySearch(obj.getVungLuong()) + "%");
			queryCount.setParameter("vungLuong", "%" + ValidateUtils.validateKeySearch(obj.getVungLuong()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getLoaiTram())) {
			query.setParameter("loaiTram", "%" + ValidateUtils.validateKeySearch(obj.getLoaiTram()) + "%");
			queryCount.setParameter("loaiTram", "%" + ValidateUtils.validateKeySearch(obj.getLoaiTram()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDiaHinh())) {
			query.setParameter("diaHinh", "%" + ValidateUtils.validateKeySearch(obj.getDiaHinh()) + "%");
			queryCount.setParameter("diaHinh", "%" + ValidateUtils.validateKeySearch(obj.getDiaHinh()) + "%");

		}
		if (obj.getDonGia() != null && (!obj.getDonGia().equals(""))) {
			query.setParameter("donGia", obj.getDonGia());
			queryCount.setParameter("donGia", obj.getDonGia());

		}
		if (obj.getPl() != null && (!obj.getPl().equals(""))) {
			query.setParameter("pl", "%" + ValidateUtils.validateKeySearch(obj.getPl().toString()) + "%");
			queryCount.setParameter("pl", "%" + ValidateUtils.validateKeySearch(obj.getPl().toString()) + "%");

		}
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}

	public Long deleteObj(TblDonGiaTramDTO obj) {
		try {
			String sql2 = "delete from TBL_DON_GIA_TRAM  where  LOAI_TRAM=:loaiTram and VUNG_LUONG=:vungLuong and DIA_HINH=:diaHinh and PL=:pl ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("loaiTram", obj.getLoaiTram());
			query2.setParameter("vungLuong", obj.getVungLuong());
			query2.setParameter("diaHinh", obj.getDiaHinh());
			query2.setParameter("pl", obj.getPl());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

}
