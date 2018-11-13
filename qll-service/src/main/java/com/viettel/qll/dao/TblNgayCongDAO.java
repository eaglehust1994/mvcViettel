package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblNgayCongBO;
import com.viettel.qll.dto.TblNgayCongDTO;
import com.viettel.qll.dto.TblPhatFcDTO;

//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

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
@Repository("tblNgayCongDAO")
public class TblNgayCongDAO extends BaseFWDAOImpl<TblNgayCongBO, Long> {

    public TblNgayCongDAO() {
        this.model = new TblNgayCongBO();
    }

    public TblNgayCongDAO(Session session) {
        this.session = session;
    }	
    public List<TblNgayCongDTO> doSearch(TblNgayCongDTO obj){
    	StringBuilder sql = new StringBuilder(" SELECT zz.TBL_NGAY_CONG_ID tblNgayCongId,"
    			+ "zz.THANG thang, "
    			+ "zz.MA_NV maNv, "
    			+ "zz.HO_VA_TEN hoVaTen, "
    			+ "zz.NGAY_CONG_TINH_LUONG ngayCongTinhLuong, "
    			+ "zz.NGAY_CONG_CHE_DO ngayCongCheDo "

    			+ "FROM TBL_NGAY_CONG zz "
				+ "WHERE zz.HOAT_DONG = 1 and xoa =0 ");	
    	if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(zz.THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(zz.THANG, 'MM/yyyy'))in(:exNam)");
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" AND (upper(zz.MA_NV) LIKE upper(:maNv) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			sql.append(" AND (upper(zz.HO_VA_TEN) LIKE upper(:hoVaTen) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ((upper(zz.MA_NV) LIKE upper(:keySearch)) "
					+ " OR (upper(zz.HO_VA_TEN) LIKE upper(:keySearch))) ");
		}
		
		
		
		sql.append(" ORDER BY zz.TBL_NGAY_CONG_ID");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblNgayCongId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("hoVaTen", new StringType());;
		query.addScalar("ngayCongTinhLuong", new FloatType());
		query.addScalar("ngayCongCheDo", new FloatType());

		query.setResultTransformer(Transformers.aliasToBean(TblNgayCongDTO.class));

		if (StringUtils.isNotEmpty(obj.getExThang())) {
			//sql.append(" and EXTRACT(MONTH FROM TO_DATE(zz.THANG, 'MM/yyyy'))in(:exThang)");
			query.setParameter("exThang",ValidateUtils.validateKeySearch(obj.getExThang()));
			queryCount.setParameter("exThang",ValidateUtils.validateKeySearch(obj.getExThang()));
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			//sql.append(" and EXTRACT(YEAR FROM TO_DATE(zz.THANG, 'MM/yyyy'))in(:exNam)");
			query.setParameter("exNam",ValidateUtils.validateKeySearch(obj.getExNam()));
			queryCount.setParameter("exNam",ValidateUtils.validateKeySearch(obj.getExNam()));
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			//sql.append(" AND (upper(zz.MA_NV) LIKE upper(:maNv) escape '&')");
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			//sql.append(" AND (upper(zz.HO_VA_TEN) LIKE upper(:hoVaTen) escape '&')");
			query.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
			queryCount.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		
		
		
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		
		return query.list();
    }
    public long deletePhatFcByMaNvAndThang(String maNv, String thang){
    	try {
    		StringBuilder sql = new StringBuilder(" DELETE FROM TBL_NGAY_CONG nv WHERE UPPER(nv.MA_NV) = UPPER(:maNv) AND nv.THANG= :thang ");
    		SQLQuery query= getSession().createSQLQuery(sql.toString());		
    		query.setParameter("maNv",maNv.trim());
    		query.setParameter("thang", thang.trim());
    		query.executeUpdate();
    		query.executeUpdate();
    		return 1l;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
    	
    }
}
