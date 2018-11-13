package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblDlDauVaoDayMayBO;
import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
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
@Repository("tblDlDauVaoDayMayDAO")
public class TblDlDauVaoDayMayDAO extends BaseFWDAOImpl<TblDlDauVaoDayMayBO, Long> {

    public TblDlDauVaoDayMayDAO() {
        this.model = new TblDlDauVaoDayMayBO();
    }

    public TblDlDauVaoDayMayDAO(Session session) {
        this.session = session;
    }	
    public List<TblDlDauVaoDayMayDTO> doSearch(TblDlDauVaoDayMayDTO obj){
    	StringBuilder sql = new StringBuilder(" select TBL_DL_DAU_VAO_DAY_MAY_ID tblDlDauVaoDayMayId,DDTBQL_DA_TOA_NHA ddtbqlDaToaNha,DDTBQL_DAY_TB_AON ddtbqlDayTbAon,"
    			+ "DDTBQL_DAY_TBKT ddtbqlDayTbkt,DDTBQL_GPON_ADSL_PSTN_EOC ddtbqlGponAdslPstnEoc,DDTBQL_HUONG_LUONG_DUY_TRI ddtbqlHuongLuongDuyTri,KY_LUONG kyLuong"
    			+ ",DDTBQL_TONG_QUY_DOI ddtbqlTongQuyDoi,DOI_TUONG doiTuong,DON_VI  donVi,GHEP ghep,GHI_CHU ghiChu,HO_VA_TEN hoVaTen "
    			+ ",MA_NV maNv,MA_TINH maTinh,NHOM_TRUONG nhomTruong,TBMTBCS_0_2 tbmtbcs02,TBMTBCS_3 tbmtbcs3,TBMTBCS_4 tbmtbcs4,TBMTBCS_5 tbmtbcs5"
    			+ ",TBMTBCS_6 tbmtbcs6,TBMTBDV_0_2 tbmtbdv02,TBMTBDV_3 tbmtbdv3,TBMTBDV_4 tbmtbdv4,TBMTBDV_5 tbmtbdv5,TBMTBDV_6 tbmtbdv6,TBMTBDV_7 tbmtbdv7"
    			+ ",TBMTBKT_0_2 tbmtbkt02,TBMTBKT_3 tbmtbkt3,TBMTBKT_4 tbmtbkt4,TBMTBKT_5 tbmtbkt5,TBMTBKT_6 tbmtbkt6,TBMTBKT_7 tbmtbkt7,TBMTBT_0_2 tbmtbt02"
    			+ ",TBMTBT_3 tbmtbt3,TBMTBT_4 tbmtbt4,TBMTBT_5 tbmtbt5,TBMTBT_6 tbmtbt6,TBMTBT_7 tbmtbt7,TBMTBWF_0_2 tbmtbwf02,TBMTBWF_3 tbmtbwf3"
    			+ ",TBMTBWF_4 tbmtbwf4,TBMTBWF_5 tbmtbwf5,TBMTBWF_6 tbmtbwf6,TEN_HUYEN tenHuyen   from TBL_DL_DAU_VAO_DAY_MAY zz where HOAT_DONG=1 and xoa=0");	
    	if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(zz.KY_LUONG, 'MM/yyyy'))in(:exThang)");
		}
    	if (StringUtils.isNotEmpty(obj.getExNam())) {
    		sql.append(" and EXTRACT(YEAR FROM TO_DATE(zz.KY_LUONG, 'MM/yyyy'))in(:exNam)");
    	}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" AND (upper(zz.MA_NV) LIKE upper(:maNv) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			sql.append(" AND (upper(zz.HO_VA_TEN) LIKE upper(:hoVaTen) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			sql.append(" AND (upper(zz.DON_VI) LIKE upper(:maTinh) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getTenHuyen())) {
			sql.append(" AND (upper(zz.TEN_HUYEN) LIKE upper(:quanHuyen) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ((upper(zz.MA_NV) LIKE upper(:keySearch)) "
					+ " OR (upper(zz.HO_VA_TEN) LIKE upper(:keySearch)) "
					+ " OR (upper(zz.MA_TINH) LIKE upper(:keySearch)) "
					+ " OR (upper(zz.TEN_HUYEN) LIKE upper(:keySearch))) ");
		}
		
		
		
		sql.append(" ORDER BY zz.TBL_DL_DAU_VAO_DAY_MAY_ID");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblDlDauVaoDayMayId", new LongType());
		query.addScalar("kyLuong", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("hoVaTen", new StringType());
		query.addScalar("tenHuyen", new StringType());
		query.addScalar("donVi", new StringType());
		query.addScalar("maTinh", new StringType());
		query.addScalar("ghep", new StringType());
		query.addScalar("ghiChu", new StringType());
		query.addScalar("doiTuong", new StringType());
		query.addScalar("ddtbqlDayTbkt", new FloatType());
		query.addScalar("ddtbqlDayTbAon", new FloatType());
		query.addScalar("ddtbqlDaToaNha", new FloatType());
		query.addScalar("ddtbqlGponAdslPstnEoc", new FloatType());
		query.addScalar("ddtbqlTongQuyDoi", new FloatType());
		query.addScalar("ddtbqlHuongLuongDuyTri", new FloatType());
		query.addScalar("tbmtbkt02", new FloatType());
		query.addScalar("tbmtbkt3", new FloatType());
		query.addScalar("tbmtbkt4", new FloatType());
		query.addScalar("tbmtbkt5", new FloatType());
		query.addScalar("tbmtbkt6", new FloatType());
		query.addScalar("tbmtbkt7", new FloatType());
		query.addScalar("tbmtbt02", new FloatType());
		query.addScalar("tbmtbt3", new FloatType());
		query.addScalar("tbmtbt4", new FloatType());
		query.addScalar("tbmtbt5", new FloatType());
		query.addScalar("tbmtbt6", new FloatType());
		query.addScalar("tbmtbt7", new FloatType());
		query.addScalar("tbmtbdv02", new FloatType());
		query.addScalar("tbmtbdv3", new FloatType());
		query.addScalar("tbmtbdv4", new FloatType());
		query.addScalar("tbmtbdv5", new FloatType());
		query.addScalar("tbmtbdv6", new FloatType());
		query.addScalar("tbmtbdv7", new FloatType());
		query.addScalar("tbmtbcs02", new FloatType());
		query.addScalar("tbmtbcs3", new FloatType());
		query.addScalar("tbmtbcs4", new FloatType());
		query.addScalar("tbmtbcs5", new FloatType());
		query.addScalar("tbmtbcs6", new FloatType());
		query.addScalar("tbmtbwf02", new FloatType());
		query.addScalar("tbmtbwf3", new FloatType());
		query.addScalar("tbmtbwf4", new FloatType());
		query.addScalar("tbmtbwf5", new FloatType());
		query.addScalar("tbmtbwf6", new FloatType());
		query.addScalar("nhomTruong", new FloatType());
		query.setResultTransformer(Transformers.aliasToBean(TblDlDauVaoDayMayDTO.class));

		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(zz.KY_LUONG, 'MM/yyyy'))in(:exThang)");
			query.setParameter("exThang",ValidateUtils.validateKeySearch(obj.getExThang()));
			queryCount.setParameter("exThang",ValidateUtils.validateKeySearch(obj.getExThang()));
		}
		if(StringUtils.isNotEmpty(obj.getExNam()))
		{
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(zz.KY_LUONG, 'MM/yyyy'))in(:exNam)");
			query.setParameter("exNam",ValidateUtils.validateKeySearch(obj.getExNam()));
			queryCount.setParameter("exNam",ValidateUtils.validateKeySearch(obj.getExNam()));
		}
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" AND (upper(zz.MA_NV) LIKE upper(:maNv) escape '&')");
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getHoVaTen())) {
			sql.append(" AND (upper(zz.HO_VA_TEN) LIKE upper(:hoVaTen) escape '&')");
			query.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
			queryCount.setParameter("hoVaTen", "%" + ValidateUtils.validateKeySearch(obj.getHoVaTen()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			sql.append(" AND (upper(zz.DON_VI) LIKE upper(:maTinh) escape '&')");
			query.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
			queryCount.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenHuyen())) {
			sql.append(" AND (upper(zz.TEN_HUYEN) LIKE upper(:quanHuyen) escape '&')");
			query.setParameter("quanHuyen", "%" + ValidateUtils.validateKeySearch(obj.getTenHuyen()) + "%");
			queryCount.setParameter("quanHuyen", "%" + ValidateUtils.validateKeySearch(obj.getTenHuyen()) + "%");
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
    
}
