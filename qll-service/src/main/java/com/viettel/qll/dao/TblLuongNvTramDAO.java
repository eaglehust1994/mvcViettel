package com.viettel.qll.dao;

import java.util.List;

import com.google.common.collect.Lists;
import com.viettel.qll.bo.TblLuongNvTramBO;
import com.viettel.qll.dto.TblLoiLapLaiDTO;
import com.viettel.qll.dto.TblLuongNvTramDTO;
import com.viettel.qll.dto.TblPhatXuLySuCoDTO;

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
@Repository("tblLuongNvTramDAO")
public class TblLuongNvTramDAO extends BaseFWDAOImpl<TblLuongNvTramBO, Long> {

    public TblLuongNvTramDAO() {
        this.model = new TblLuongNvTramBO();
    }

    public TblLuongNvTramDAO(Session session) {
        this.session = session;
    }	
    public List<TblLuongNvTramDTO> doSearch(TblLuongNvTramDTO obj) {
    	StringBuilder sql = new StringBuilder("select TBL_LUONG_NV_TRAM_ID tblLuongNvTramId,"
    			+ "HE_SO_DIEU_CHINH heSoDieuChinh,"
    			+ "HO_TEN hoTen,"
    			+ "HUYEN huyen,"
    			+ "KPI_DON_VI kpiDonVi,"
    			+ "LUONG luong,"
    			+ "LUONG_DUY_TRI_TUNG_TRAM luongDuyTriTungTram,"
    			+ "MA_NV maNv,"
    			+ "NGAY_CONG_CHE_DO ngayCongCheDo,"
    			+ "NGAY_CONG_TINH_LUONG ngayCongTinhLuong,"
    			+ "PHAT_LOI_1 phatLoi1,"
    			+ "PHAT_LOI_2 phatLoi2,"
    			+ "PHAT_LOI_3 phatLoi3,"
    			+ "PHAT_LOI_4 phatLoi4,"
    			+ "PHAT_LOI_5 phatLoi5,"
    			+ "PHAT_LOI_6 phatLoi6,"
    			+ "PHAT_LOI_7 phatLoi7,"
    			+ "PHI_BAN_HANG phiBanHang,"
    			+ "SO_TRAM_QUAN_LY soTramQuanLy,"
    			+ "THANG thang,"
    			+ "TINH tinh "
    			+ "from TBL_LUONG_NV_TRAM"
    			+ " where xoa = 0 and hoat_dong = 1 " );
    	

    	if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ((upper(TINH) LIKE upper(:keySearch)) ");
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			sql.append(" And upper(TINH) like upper(:tinh) ");

		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(THANG, 'MM/yyyy'))in(:exNam)");
		}
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("maNv", new StringType());
		query.addScalar("hoTen", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("thang", new StringType());
		query.addScalar("tblLuongNvTramId", new LongType());
		query.addScalar("luong", new FloatType());
		query.addScalar("phatLoi7", new FloatType());
		query.addScalar("phatLoi6", new FloatType());
		query.addScalar("phatLoi5", new FloatType());
		query.addScalar("phatLoi4", new FloatType());
		query.addScalar("phatLoi3", new FloatType());
		query.addScalar("phatLoi2", new FloatType());
		query.addScalar("phatLoi1", new FloatType());
		query.addScalar("phiBanHang", new FloatType());
		query.addScalar("luongDuyTriTungTram", new FloatType());
		query.addScalar("ngayCongCheDo", new FloatType());
		query.addScalar("ngayCongTinhLuong", new FloatType());
		query.addScalar("heSoDieuChinh", new FloatType());
		query.addScalar("kpiDonVi", new FloatType());
		query.addScalar("soTramQuanLy", new StringType());
		query.addScalar("huyen", new StringType());
	
		
		query.setResultTransformer(Transformers.aliasToBean(TblLuongNvTramDTO.class));
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
			queryCount.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
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

	public String tinhluong() {
		// TODO Auto-generated method stub
		return null;
	}
}
