package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblLuongTungTramBO;
import com.viettel.qll.dto.TblLuongNvTramDTO;
import com.viettel.qll.dto.TblLuongTungTramDTO;
import com.viettel.qll.dto.TblPhatXuLySuCoDTO;

//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.service.base.dto.DataListDTO;
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
@Repository("tblLuongTungTramDAO")
public class TblLuongTungTramDAO extends BaseFWDAOImpl<TblLuongTungTramBO, Long> {

    public TblLuongTungTramDAO() {
        this.model = new TblLuongTungTramBO();
    }

    public TblLuongTungTramDAO(Session session) {
        this.session = session;
    }	
    public List<TblLuongTungTramDTO> doSearch(TblLuongTungTramDTO obj) {
    	StringBuilder sql = new StringBuilder("select TBL_LUONG_TUNG_TRAM_ID tblLuongTungTramId,"
    			+ "DIA_HINH diaHinh,DON_GIA donGia,HE_SO_DIEU_CHINH heSoDieuChinh,HO_TEN hoTen,HUYEN huyen,"
    			+ "KI_DON_VI kiDonVi,KPI_TRAM kpiTram,LOAI_TRAM loaiTram,LUONG luong,LUONG_DUY_TRI_TUNG_TRAM luongDuyTriTungTram,"
    			+ "MA_NV maNv,NGAY_CONG_CHE_DO ngayCongCheDo,NGAY_CONG_TINH_LUONG ngayCongTinhLuong,"
    			+ "PHAT_LOI_1 phatLoi1,PHAT_LOI_2 phatLoi2,PHAT_LOI_3 phatLoi3,PHAT_LOI_4 phatLoi4,PHAT_LOI_5 phatLoi5,PHAT_LOI_6 phatLoi6,"
    			+ "THANG thang,TINH tinh,TRAM tram,VUNG_LUONG vungLuong "
    			+ "from TBL_LUONG_TUNG_TRAM"
    			+ " where XOA = 0 and HOAT_DONG = 1 " );
    	

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
		
		query.addScalar("loaiTram", new StringType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("diaHinh", new StringType());
		query.addScalar("vungLuong", new StringType());
		query.addScalar("tram", new StringType());
		query.addScalar("hoTen", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("huyen", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("thang", new StringType());
		query.addScalar("tblLuongTungTramId", new LongType());
		query.addScalar("luong", new FloatType());
		query.addScalar("phatLoi6", new FloatType());
		query.addScalar("phatLoi5", new FloatType());
		query.addScalar("phatLoi4", new FloatType());
		query.addScalar("phatLoi3", new FloatType());
		query.addScalar("phatLoi2", new FloatType());
		query.addScalar("phatLoi1", new FloatType());
		query.addScalar("luongDuyTriTungTram", new FloatType());
		query.addScalar("ngayCongCheDo", new FloatType());
		query.addScalar("ngayCongTinhLuong", new FloatType());
		query.addScalar("heSoDieuChinh", new FloatType());
		query.addScalar("kpiTram", new FloatType());
		query.addScalar("kiDonVi", new FloatType());			
		query.setResultTransformer(Transformers.aliasToBean(TblLuongTungTramDTO.class));
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

	public List tinhluong(TblLuongTungTramDTO obj) {
		StringBuilder sql = new StringBuilder("select distinct '1' hoatDong,'0' xoa,'1' heSoDieuChinh,'1' luong, dl.MA_NV maNv,dl.HUYEN huyen,dl.THANG thang,dm.TEN_DANH_MUC tinh,dl.MA_TRAM tram," + 
				"dl.VUNG vungLuong,dl.LOAI_DIA_HINH diaHinh,dl.PHAN_LOAI_TRAM loaiTram,dg.DON_GIA donGia," + 
				"55-10*kpi.SO_BLOCK_WO_QUA_HAN+45-3*(100-kpi.DIEM_KIEM_TRA_TRAM) kpiTram" + 
				",KI.KI_DON_VI kiDonVi,dl.NGAY_CONG_TINH_LUONG ngayCongTinhLuong,dl.NGAY_CONG_CHE_DO ngayCongCheDo," + 
				"nv.HO_VA_TEN hoTen,pk.UCTT_CHAM_DAN phatLoi1,pk.GIAN_DOAN_THONG_TIN phatLoi2,pk.VI_PHAM_DONG_AO phatLoi3,pk.LOI_RA_VA_NHA_TRAM phatLoi4,pk.GIAN_LAN_XANG_DAU phatLoi5,pk.PHAT_TRUC_TIEP phatLoi6 " + 
				"from TBL_DL_HA_TANG_TRAM dl left join TBL_DON_GIA_TRAM dg on dg.DIA_HINH=dl.LOAI_DIA_HINH  left join TBL_NHAN_VIEN nv on nv.MA_NV= dl.MA_NV " + 
				"left join TBL_PHAT_KPI kpi on kpi.MA_NV= dl.MA_NV left join TBL_DM_KI_DON_VI KI on KI.TINH = dl.MA_TINH " + 
				"left join TBL_PHAT_KHAC pk on pk.MA_TRAM=dl.MA_TRAM left join tbl_danh_muc dm on dm.MA_DANH_MUC=dl.MA_TINH " + 
				"where dg.LOAI_TRAM=dl.PHAN_LOAI_TRAM and dg.VUNG_LUONG=dl.VUNG and dm.MA_DANH_MUC_CHA is null " );
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			sql.append(" And upper(dm.TEN_DANH_MUC) like upper(:tinh) ");

		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(dl.THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(dl.THANG, 'MM/yyyy'))in(:exNam)");
		}
		sql.append("  order by dl.MA_NV,dl.MA_TRAM");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("loaiTram", new StringType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("diaHinh", new StringType());
		query.addScalar("vungLuong", new StringType());
		query.addScalar("tram", new StringType());
		query.addScalar("hoTen", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("huyen", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("thang", new StringType());
		query.addScalar("phatLoi6", new FloatType());
		query.addScalar("phatLoi5", new FloatType());
		query.addScalar("phatLoi4", new FloatType());
		query.addScalar("phatLoi3", new FloatType());
		query.addScalar("phatLoi2", new FloatType());
		query.addScalar("phatLoi1", new FloatType());
		query.addScalar("ngayCongCheDo", new FloatType());
		query.addScalar("ngayCongTinhLuong", new FloatType());
		query.addScalar("kpiTram", new FloatType());
		query.addScalar("kiDonVi", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblLuongTungTramDTO.class));
		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh", "%" + ValidateUtils.validateKeySearch(obj.getTinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			query.setParameter("exThang", obj.getExThang());
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			query.setParameter("exNam", obj.getExNam());
		}
 		return query.list();
		
	}
	 public long deleteLuongnhatram(String thang,String nam,String Tinh){
	    	try {
	    		StringBuilder sql = new StringBuilder(" delete TBL_LUONG_TUNG_TRAM where EXTRACT(YEAR FROM TO_DATE(THANG, 'MM/yyyy'))in(:exNam) and EXTRACT(MONTH FROM TO_DATE(THANG, 'MM/yyyy'))in(:exThang) and UPPER(TINH) = UPPER(:tinh) ");
	    		SQLQuery query= getSession().createSQLQuery(sql.toString());		
	    		query.setParameter("tinh",Tinh.trim());
	    		query.setParameter("exThang", thang.trim());
	    		query.setParameter("exNam", nam.trim());
	    		query.executeUpdate();
	    		query.executeUpdate();
	    		return 1l;
			} catch (Exception e) {
				e.getMessage();
				return 0L;
			}
	    	
	    }

}
