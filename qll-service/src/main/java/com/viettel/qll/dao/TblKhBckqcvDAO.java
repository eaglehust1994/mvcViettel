package com.viettel.qll.dao;


import java.util.Date;

import java.util.List;
import com.viettel.qll.bo.TblKhBckqcvBO;
import com.viettel.qll.dto.TblKhBckqcvDTO;
import com.viettel.qll.dto.TblKhQlvcDTO;

import java.math.BigDecimal;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("tblKhBckqcvDAO")
public class TblKhBckqcvDAO extends BaseFWDAOImpl<TblKhBckqcvBO, Long> {

    public TblKhBckqcvDAO() {
        this.model = new TblKhBckqcvBO();
    }

    public TblKhBckqcvDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<TblKhBckqcvDTO> doSearch(TblKhBckqcvDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder(" select  ");
		stringBuilder.append(" T1.KH_BCKQCV_ID khBckqcvId ");
		stringBuilder.append(",T1.NGAY ngay ");
		stringBuilder.append(",T1.THANG thang ");
		stringBuilder.append(",T1.NAM nam ");
		stringBuilder.append(",T1.THOI_GIAN thoiGian ");
		stringBuilder.append(",T1.MA_DON_VI maDonVi ");
		stringBuilder.append(",T1.TEN_DON_VI tenDonVi ");
		stringBuilder.append(",T1.SAN_LUONG sanLuong ");
		stringBuilder.append(",T1.DOANH_THU doanhThu ");
		stringBuilder.append(",T1.HCQT hcqt ");
		stringBuilder.append(",T1.LOAI loai ");
		stringBuilder.append(",T1.KHOI khoi ");
		stringBuilder.append(",T1.THUOC thuoc ");
    	
    	stringBuilder.append(" FROM  TBL_KH_BCKQCV T1 ");    	
    	stringBuilder.append(" WHERE 1=1 ");
		
		if (StringUtils.isNotEmpty(obj.getThang())) {
			stringBuilder.append(" AND (UPPER(T1.THANG) LIKE UPPER(:thang) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getNam())) {
			stringBuilder.append(" AND (UPPER(T1.NAM) LIKE UPPER(:nam) ESCAPE '&') ");
		}
		
		if (null != obj.getThoiGianFrom() && obj.getThoiGianTo() == null) {
			stringBuilder.append(" AND T1.THOI_GIAN >= :thoiGianFrom ");
		}
		if (null != obj.getThoiGianTo() && null != obj.getThoiGianFrom()) {
			stringBuilder.append(" AND (T1.THOI_GIAN between :thoiGianFrom and :thoiGianTo) ");
		}
		if (StringUtils.isNotEmpty(obj.getMaDonVi())) {
			stringBuilder.append("AND (UPPER(T1.MA_DON_VI) LIKE UPPER(:maDonVi) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getTenDonVi())) {
			stringBuilder.append("AND (UPPER(T1.TEN_DON_VI) LIKE UPPER(:tenDonVi) ESCAPE '&') ");
		}
	
		if (StringUtils.isNotEmpty(obj.getLoai())) {
			stringBuilder.append("AND (UPPER(T1.LOAI) LIKE UPPER(:loai) ESCAPE '&') ");
		}
		stringBuilder.append(" order by T1.KH_BCKQCV_ID desc ");
		StringBuilder sqlCount = new StringBuilder( "SELECT COUNT(*) FROM ( " );
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(" ) ");
    
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
			
			query.addScalar("khBckqcvId", new LongType());
			query.addScalar("ngay", new StringType());
			query.addScalar("thang", new StringType());
			query.addScalar("nam", new StringType());
			query.addScalar("thoiGian", new DateType());
			query.addScalar("maDonVi", new StringType());
			query.addScalar("tenDonVi", new StringType());
			query.addScalar("sanLuong", new LongType());
			query.addScalar("doanhThu", new LongType());
			query.addScalar("hcqt", new LongType());
			query.addScalar("loai", new StringType());
			query.addScalar("khoi", new StringType());
			query.addScalar("thuoc", new StringType());
			
			query.setResultTransformer(Transformers.aliasToBean(TblKhBckqcvDTO.class));
    	
		
	
		if (StringUtils.isNotEmpty(obj.getThang())) {
			query.setParameter("thang", "%" + ValidateUtils.validateKeySearch(obj.getThang()) + "%");
			queryCount.setParameter("thang", "%" + ValidateUtils.validateKeySearch(obj.getThang()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getNam())) {
			query.setParameter("nam", "%" + ValidateUtils.validateKeySearch(obj.getNam()) + "%");
			queryCount.setParameter("nam", "%" + ValidateUtils.validateKeySearch(obj.getNam()) + "%");
		}
		
		if (null != obj.getThoiGianFrom() && obj.getThoiGianTo() == null) {
			query.setTimestamp("thoiGianFrom", obj.getThoiGianFrom());
			queryCount.setTimestamp("thoiGianFrom", obj.getThoiGianFrom());
		}
		if (null != obj.getThoiGianTo() && null != obj.getThoiGianFrom()) {
			query.setTimestamp("thoiGianFrom", obj.getThoiGianFrom());
			queryCount.setTimestamp("thoiGianFrom", obj.getThoiGianFrom());

			query.setTimestamp("thoiGianTo", obj.getThoiGianTo());
			queryCount.setTimestamp("thoiGianTo", obj.getThoiGianTo());
		}
		
		if (StringUtils.isNotEmpty(obj.getMaDonVi())) {
			query.setParameter("maDonVi", "%" + ValidateUtils.validateKeySearch(obj.getMaDonVi()) + "%");
			queryCount.setParameter("maDonVi", "%" + ValidateUtils.validateKeySearch(obj.getMaDonVi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenDonVi())) {
			query.setParameter("tenDonVi", "%" + ValidateUtils.validateKeySearch(obj.getTenDonVi()) + "%");
			queryCount.setParameter("tenDonVi", "%" + ValidateUtils.validateKeySearch(obj.getTenDonVi()) + "%");
		}
	
		if (StringUtils.isNotEmpty(obj.getLoai())) {
			query.setParameter("loai", "%" + ValidateUtils.validateKeySearch(obj.getLoai()) + "%");
			queryCount.setParameter("loai", "%" + ValidateUtils.validateKeySearch(obj.getLoai()) + "%");
		}
    	
		 	
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	} 
    
    
    
	protected final static String THUC_TE="TT";
	protected final static String KE_HOACH="KH";
	protected final static String DV = "1";
	protected final static String KDV ="2";
	protected static Date date = new Date();
    @SuppressWarnings("deprecation")
	protected static int nowDate = date.getYear() + 1900;
	protected final static String NAM = Integer.toString(nowDate);
	// biểu đồ ngày theo đơn vị và khối đơn vị
    @SuppressWarnings("unchecked")
	public List<TblKhBckqcvDTO> getForDeptDay(TblKhBckqcvDTO obj){
    	String sql = "select T1.SAN_LUONG sanLuong, T1.DOANH_THU doanhThu, T1.HCQT hcqt , T1.THOI_GIAN thoiGian from TBL_KH_BCKQCV T1 "
    			+ " where  T1.LOAI=:loai ";
    	StringBuilder stringBuilder = new StringBuilder(sql);
    	if(StringUtils.isNotEmpty(obj.getMaDonVi())){
    		stringBuilder.append(" and T1.MA_DON_VI=:maDonVi ");
    	}
    	if(StringUtils.isNotEmpty(obj.getThang())){
    		stringBuilder.append(" and T1.THANG=:thang ");
    	}
    	
    	stringBuilder.append(" order by T1.THOI_GIAN ");
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
    	query.addScalar("sanLuong", new LongType());
    	query.addScalar("doanhThu", new LongType());
    	query.addScalar("hcqt", new LongType());
    	query.addScalar("thoiGian", new DateType());
    	query.setResultTransformer(Transformers.aliasToBean(TblKhBckqcvDTO.class));
    	
    	query.setParameter("loai", THUC_TE);
    	if(StringUtils.isNotEmpty(obj.getMaDonVi())){
    		query.setParameter("maDonVi", obj.getMaDonVi());
    	}
    	if(StringUtils.isNotEmpty(obj.getThang())){
    		query.setParameter("thang", obj.getThang());
    	}
    
    	
    	return query.list();
    }
    
    
     // biểu đồ tháng theo đơn vị và khối đơn vị
  
    
    @SuppressWarnings("unchecked")
	public List<TblKhBckqcvDTO> getForDeptMonth(TblKhBckqcvDTO obj){
  
    	String sql = " select T1.sanLuongKh,T1.doanhThuKh,T1.hcqtKh,T2.sanLuong,T2.doanhThu,T2.hcqt,T1.thang from "
    			+ "	(select sum(SAN_LUONG) sanLuongKh, sum(DOANH_THU) doanhThuKh, sum(HCQT) hcqtKh, THANG thang from TBL_KH_BCKQCV  where NAM=:nam and LOAI=:loaiKh ";
    	StringBuilder stringBuilder = new StringBuilder(sql);
    	if(StringUtils.isNotEmpty(obj.getMaDonVi())){
    		stringBuilder.append(" and MA_DON_VI=:maDonVi ");
    	} 
    	stringBuilder.append("  group by THANG order by THANG) T1 join ");
    	stringBuilder.append(" ( select sum(SAN_LUONG) sanLuong, sum(DOANH_THU) doanhThu, sum(HCQT) hcqt, THANG thang from TBL_KH_BCKQCV ");
    	stringBuilder.append(" where NAM=:nam and LOAI=:loaiTt ");
    	if(StringUtils.isNotEmpty(obj.getMaDonVi())){
    		stringBuilder.append(" and MA_DON_VI=:maDonVi ");
    	}
    	stringBuilder.append("  group by THANG order by THANG) T2 on T1.thang=T2.thang ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
    	query.addScalar("sanLuongKh", new LongType());
    	query.addScalar("doanhThuKh", new LongType());
    	query.addScalar("hcqtKh", new LongType());
    	query.addScalar("sanLuong", new LongType());
    	query.addScalar("doanhThu", new LongType());
    	query.addScalar("hcqt", new LongType());
    	query.addScalar("thang", new StringType());
    	query.setResultTransformer(Transformers.aliasToBean(TblKhBckqcvDTO.class));
    	
    	query.setParameter("nam", NAM);
    	query.setParameter("loaiKh", KE_HOACH);
    	query.setParameter("loaiTt", THUC_TE);
    	if(StringUtils.isNotEmpty(obj.getMaDonVi())){
    		query.setParameter("maDonVi", obj.getMaDonVi());
    	}
    	return query.list();		
    }
    // biểu đồ đơn vị theo tháng

    
    @SuppressWarnings("unchecked")
	public List<TblKhBckqcvDTO> getForMonth(TblKhBckqcvDTO obj){
    	String sql =" select T5.sanLuongKh,T5.doanhThuKh,T5.hcqtKh,T5.maDonVi,T6.sanLuong,T6.doanhThu,T6.hcqt from "
 
    				+" (select T1.sanLuongKh, T1.doanhThuKh, T1.hcqtKh,T1.maDonVi from " 
    				+" (select sum(SAN_LUONG) sanLuongKh, sum(DOANH_THU) doanhThuKh, sum(HCQT) hcqtKh, MA_DON_VI maDonVi from TBL_KH_BCKQCV   where NAM=:nam and LOAI=:loaiKh and THUOC=:thuoc ";
    	StringBuilder stringBuilder = new StringBuilder(sql);
    	if(StringUtils.isNotEmpty(obj.getThang())){
    		stringBuilder.append(" and THANG=:thang ");
    	}
    	stringBuilder.append(" group by MA_DON_VI) T1 join (select DISTINCT MA_DON_VI,KHOI from TBL_KH_BCKQCV where THUOC=:thuoc ) T2 on T2.MA_DON_VI=T1.maDonVi order by T2.KHOI) T5 join ");
    	stringBuilder.append(" (select T3.sanLuong, T3.doanhThu, T3.hcqt,T3.maDonVi from ");
    	stringBuilder.append(" (select sum(SAN_LUONG) sanLuong, sum(DOANH_THU) doanhThu, sum(HCQT) hcqt, MA_DON_VI maDonVi from TBL_KH_BCKQCV   where NAM=:nam and LOAI=:loaiTt and THUOC=:thuoc ");
    	if(StringUtils.isNotEmpty(obj.getThang())){
    		stringBuilder.append(" and THANG=:thang ");
    	}
    	stringBuilder.append(" group by MA_DON_VI) T3 ");
    	stringBuilder.append(" join (select DISTINCT MA_DON_VI,KHOI from TBL_KH_BCKQCV where THUOC=:thuoc ) T4 on T4.MA_DON_VI=T3.maDonVi order by T4.KHOI) T6 on T5.maDonVi=T6.maDonVi ");

    	
    	
    	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
    	query.addScalar("sanLuongKh", new LongType());
    	query.addScalar("doanhThuKh", new LongType());
    	query.addScalar("hcqtKh", new LongType());
    	query.addScalar("sanLuong", new LongType());
    	query.addScalar("doanhThu", new LongType());
    	query.addScalar("hcqt", new LongType());
    	query.addScalar("maDonVi", new StringType());
    	query.setResultTransformer(Transformers.aliasToBean(TblKhBckqcvDTO.class));
    	
    	query.setParameter("nam", NAM);
    	query.setParameter("loaiKh", KE_HOACH);
    	query.setParameter("loaiTt", THUC_TE);
    	query.setParameter("thuoc", DV);
    	if(StringUtils.isNotEmpty(obj.getThang())){
    		query.setParameter("thang", obj.getThang());
    	}
    	
    	return query.list();
    }
    
    
    
    public Long deleteList(List<Long> obj) {
		try {

			String sql2 = "delete from TBL_KH_BCKQCV  where KH_BCKQCV_ID in (:khBckqcvId) ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameterList("khBckqcvId", obj);
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    
    @SuppressWarnings("unchecked")
	public List<TblKhBckqcvDTO> getForAutoCompleteMonth(TblKhBckqcvDTO obj) {
		String sql = "SELECT distinct " + "T1.THANG thang " + " FROM TBL_KH_BCKQCV T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getThang())) {
			stringBuilder.append(" AND (upper(T1.THANG) LIKE upper(:thang) escape '&' )");
		}
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("thang", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblKhBckqcvDTO.class));

		if (StringUtils.isNotEmpty(obj.getThang())) {
			query.setParameter("thang", "%" + ValidateUtils.validateKeySearch(obj.getThang()) + "%");
		}

		return query.list();
	}
    @SuppressWarnings("unchecked")
	public List<TblKhBckqcvDTO> getForAutoCompleteMaDv(TblKhBckqcvDTO obj) {
		String sql = "SELECT distinct T1.MA_DON_VI maDonVi,T1.TEN_DON_VI tenDonVi  FROM TBL_KH_BCKQCV T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		
		if (StringUtils.isNotEmpty(obj.getDepartment())) {
			stringBuilder.append(
					" AND (upper(T1.MA_DON_VI) LIKE upper(:department) escape '&' OR upper(T1.TEN_DON_VI) LIKE upper(:department) escape '&') "
					);
		}
		stringBuilder.append(" ORDER BY T1.MA_DON_VI ");
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("maDonVi", new StringType());
		query.addScalar("tenDonVi", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblKhBckqcvDTO.class));

		if (StringUtils.isNotEmpty(obj.getDepartment())) {
			query.setParameter("department", "%" + ValidateUtils.validateKeySearch(obj.getDepartment()) + "%");
		}
		return query.list();
	}
	
}
