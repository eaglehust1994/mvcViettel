package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblPhatRoiMangBO;
import com.viettel.qll.dto.TblPhatRoiMangDTO;
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
@Repository("tblPhatRoiMangDAO")
public class TblPhatRoiMangDAO extends BaseFWDAOImpl<TblPhatRoiMangBO, Long> {

    public TblPhatRoiMangDAO() {
        this.model = new TblPhatRoiMangBO();
    }

    public TblPhatRoiMangDAO(Session session) {
        this.session = session;
    }	
    
    public List<TblPhatRoiMangDTO> doSearch(TblPhatRoiMangDTO obj) {
    	StringBuilder sql = new StringBuilder("select TBL_PHAT_ROI_MANG_ID tblPhatRoiMangId,THANG thang,USER_BH userBh,MA_TINH maTinh,"
    			+ "PHAT_TRUOC_THUE phatTruocThue,PHAT_SAU_THUE phatSauThue,MA_TTNS maTtns,TEN_CTV tenCtv,TONG_THUE_BAO tongThueBao"
    			+ " from TBL_PHAT_ROI_MANG "
    			+ "where xoa =0 and HOAT_DONG=1");
    	

		
		if (StringUtils.isNotEmpty(obj.getUserBh())){
			sql.append(" And upper(USER_BH) like upper(:userbh) ");
		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())){
			sql.append(" And upper(MA_TINH) like upper(:maTinh) ");
		}
		if (StringUtils.isNotEmpty(obj.getMaTtns())){
			sql.append(" And upper(MA_TTNS) like upper(:ttns) ");
		}
		if (StringUtils.isNotEmpty(obj.getTenCtv())){
			sql.append(" And upper(TEN_CTV) like upper(:ctv) ");
		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			sql.append(" and EXTRACT(MONTH FROM TO_DATE(THANG, 'MM/yyyy'))in(:exThang)");
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			sql.append(" and EXTRACT(YEAR FROM TO_DATE(THANG, 'MM/yyyy'))in(:exNam)");
		}
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" And upper(USER_BH) like upper(:keysearch) or");
			sql.append("  upper(MA_TINH) like upper(:keysearch) or ");
			sql.append(" upper(MA_TTNS) like upper(:keysearch) or ");
			sql.append("  upper(TEN_CTV) like upper(:keysearch) ");
		}
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("tblPhatRoiMangId", new LongType());
		query.addScalar("thang", new StringType());
		query.addScalar("userBh", new StringType());
		query.addScalar("tongThueBao", new FloatType());
		query.addScalar("phatTruocThue", new FloatType());
		query.addScalar("phatSauThue", new FloatType());
		query.addScalar("maTtns", new StringType());
		query.addScalar("tenCtv", new StringType());
		query.addScalar("maTinh", new StringType());

		
		query.setResultTransformer(Transformers.aliasToBean(TblPhatRoiMangDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getMaTtns())) {
			query.setParameter("ttns", "%" + ValidateUtils.validateKeySearch(obj.getMaTtns()) + "%");
			queryCount.setParameter("ttns", "%" + ValidateUtils.validateKeySearch(obj.getMaTtns()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaTinh())) {
			query.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");
			queryCount.setParameter("maTinh", "%" + ValidateUtils.validateKeySearch(obj.getMaTinh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getUserBh())) {
			query.setParameter("userbh", "%" + ValidateUtils.validateKeySearch(obj.getUserBh()) + "%");
			queryCount.setParameter("userbh", "%" + ValidateUtils.validateKeySearch(obj.getUserBh()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenCtv())) {
			query.setParameter("ctv", "%" + ValidateUtils.validateKeySearch(obj.getTenCtv()) + "%");
			queryCount.setParameter("ctv", "%" + ValidateUtils.validateKeySearch(obj.getTenCtv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getExThang())) {
			query.setParameter("exThang", obj.getExThang());
			queryCount.setParameter("exThang", obj.getExThang());
		}
		if (StringUtils.isNotEmpty(obj.getExNam())) {
			query.setParameter("exNam", obj.getExNam());
			queryCount.setParameter("exNam", obj.getExNam());
		}
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keysearch", obj.getKeySearch());
			queryCount.setParameter("keysearch", obj.getKeySearch());
		}
		if(obj.getPage()!=null && obj.getPageSize()!=null){
			query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();	
    }
    
    public long deletePhatroimangByMaNvAndThang(String maNv, String thang){
    	try {
    		StringBuilder sql = new StringBuilder(" DELETE FROM TBL_PHAT_ROI_MANG nv WHERE UPPER(nv.USER_BH) = UPPER(:maNv)"
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
