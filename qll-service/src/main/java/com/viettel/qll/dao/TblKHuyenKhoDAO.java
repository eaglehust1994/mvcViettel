package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.MappingException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.TblKHuyenKhoBO;
import com.viettel.qll.bo.TblTypeAPxkBO;
import com.viettel.qll.dto.QllBaseDTO;
import com.viettel.qll.dto.TblKHuyenKhoDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblKHuyenKhoDAO")
public class TblKHuyenKhoDAO extends BaseFWDAOImpl<TblKHuyenKhoBO, Long> {

	public TblKHuyenKhoDAO() {
		this.model = new TblKHuyenKhoBO();
	}

	public TblKHuyenKhoDAO(Session session) {
		this.session = session;
	}

	public List<TblKHuyenKhoDTO> doSearchKHuyenKho(TblKHuyenKhoDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_K_HUYEN_KHO_ID tblKHuyenKhoId," + "THANG thang,"
				+ "TINH tinh," + "MA_DON_VI maDonVi," + "TEN_DON_VI tenDonVi," + "NHOM_DOI_TUONG nhomDoiTuong,"
				+ "DE_XUAT_LOAI_HUYEN deXuatLoaiHuyen," + "K_KHO_KHAN kKhoKhan" + " from  TBL_K_HUYEN_KHO where 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(
					" AND (upper(TINH) LIKE upper(:keySearch) escape '&') or (upper(MA_DON_VI) LIKE upper(:keySearch) escape '&') "
							+ " or (upper(NHOM_DOI_TUONG) LIKE upper(:keySearch) escape '&')  ");
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			sql.append(" And upper(TINH) like upper(:tinh) ");

		}
		if (StringUtils.isNotEmpty(obj.getMaDonVi())) {
			sql.append(" And  upper(MA_DON_VI) like upper(:maDonVi) ");
		}
		if (StringUtils.isNotEmpty(obj.getTenDonVi())) {
			sql.append(" and upper(TEN_DON_VI) like upper(:tenDonVi)");
		}
		if (obj.getNhomDoiTuong() != null) {
			sql.append(" and upper(NHOM_DOI_TUONG) like upper(:nhomDoiTuong)");
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

		query.addScalar("tblKHuyenKhoId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("maDonVi", new StringType());
		query.addScalar("tenDonVi", new StringType());
		query.addScalar("nhomDoiTuong", new LongType());
		query.addScalar("deXuatLoaiHuyen", new StringType());
		query.addScalar("kKhoKhan", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblKHuyenKhoDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
			queryCount.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaDonVi())) {
			query.setParameter("maDonVi", "%" + ValidateUtils.validateKeySearch(obj.getMaDonVi()) + "%");
			queryCount.setParameter("maDonVi", "%" + ValidateUtils.validateKeySearch(obj.getMaDonVi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenDonVi())) {
			query.setParameter("tenDonVi", "%" + ValidateUtils.validateKeySearch(obj.getTenDonVi()) + "%");
			queryCount.setParameter("tenDonVi", "%" + ValidateUtils.validateKeySearch(obj.getTenDonVi()) + "%");
		}
		if (obj.getNhomDoiTuong() != null) {
			query.setParameter("nhomDoiTuong", obj.getNhomDoiTuong());
			queryCount.setParameter("nhomDoiTuong", obj.getNhomDoiTuong());
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

	public Long deleteObj(TblKHuyenKhoDTO obj) {
		try {
			String sql2 = "delete from TBL_K_HUYEN_KHO  where  TINH=:tinh and MA_DON_VI=:maDonVi and THANG=:thang and NHOM_DOI_TUONG=:nhomDoiTuong";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("thang", obj.getThang());
			query2.setParameter("tinh", obj.getTinh());
			query2.setParameter("maDonVi", obj.getMaDonVi());
			query2.setParameter("nhomDoiTuong", obj.getNhomDoiTuong());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}

	@Transactional
	  public String saveList1(List<TblKHuyenKhoBO> obj)
	  {
	    try
	    {
	      for (TblKHuyenKhoBO item : obj){
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
