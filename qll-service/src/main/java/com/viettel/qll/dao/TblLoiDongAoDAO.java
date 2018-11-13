package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblLoiDongAoBO;
import com.viettel.qll.dto.TblDmThongTinLoiDayMayDTO;
import com.viettel.qll.dto.TblLoiDongAoDTO;
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
@Repository("tblLoiDongAoDAO")
public class TblLoiDongAoDAO extends BaseFWDAOImpl<TblLoiDongAoBO, Long> {

    public TblLoiDongAoDAO() {
        this.model = new TblLoiDongAoBO();
    }

    public TblLoiDongAoDAO(Session session) {
        this.session = session;
    }	
    
    public List<TblLoiDongAoDTO> doSearch(TblLoiDongAoDTO obj) {
    	StringBuilder sql = new StringBuilder("SELECT TBL_LOI_DONG_AO_ID tblLoiDongAoId, "
				+ "THANG thang, "
				+ "TINH tinh, "
				+ "QUAN_HUYEN quanHuyen, "
				+ "LOAI_DV loaiDv, "
				+ "ACCOUNT_KH accountKh, "
				+ "MA_NV maNv, "
				+ "LOI_NGHIEP_VU loiNghiepVu, "
				+ "LOI_THAI_DO loiThaiDo, "
				+ "LOI_DONG_AO loiDongAo, "
				+ "LOI_TICH_HEN_AO loiTichHenAo, "
				+ "TONG_LOI tongLoi, "
				+ "DON_GIA donGia, "
				+ "THANH_TIEN thanhTien "
				+ "FROM TBL_LOI_DONG_AO where HOAT_DONG =1 ");
		
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" And upper(MA_NV) like upper(:maNv) ");

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
		
		query.addScalar("tblLoiDongAoId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("tinh", new StringType());
		query.addScalar("quanHuyen", new StringType());
		query.addScalar("loaiDv", new StringType());
		query.addScalar("accountKh", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("loiNghiepVu", new FloatType());
		query.addScalar("loiThaiDo", new FloatType());
		query.addScalar("loiDongAo", new FloatType());
		query.addScalar("loiTichHenAo", new FloatType());
		query.addScalar("tongLoi", new FloatType());
		query.addScalar("donGia", new FloatType());
		query.addScalar("thanhTien", new FloatType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblLoiDongAoDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
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
    
    public long deletePhatLoiDongAoByMaNvAndThang(String maNv, String thang){
    	try {
    		StringBuilder sql = new StringBuilder(" DELETE FROM TBL_LOI_DONG_AO nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv)"
    				+ " AND nv.THANG= :thang ");
    		SQLQuery query= getSession().createSQLQuery(sql.toString());		
    		query.setParameter("maNv",maNv.trim());
    		query.setParameter("thang", thang.trim());
    		query.executeUpdate();
    		return 1l;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
    	
    }
}
