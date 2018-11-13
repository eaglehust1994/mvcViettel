package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblKhQlvcBO;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TblKhQlvcDTO;
import com.viettel.qll.dto.TblUsersDTO;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("tblKhQlvcDAO")
public class TblKhQlvcDAO extends BaseFWDAOImpl<TblKhQlvcBO, Long> {

    public TblKhQlvcDAO() {
        this.model = new TblKhQlvcBO();
    }

    public TblKhQlvcDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<TblKhQlvcDTO> doSearch(TblKhQlvcDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append("T1.KH_QLVC_ID khQlvcId ");
		stringBuilder.append(",T1.THANG thang ");
		stringBuilder.append(",T1.NAM nam ");
		stringBuilder.append(",T1.MA_DON_VI maDonVi ");
		stringBuilder.append(",T1.TEN_DON_VI tenDonVi ");
		stringBuilder.append(",T1.MA_NV_GIAO_VIEC maNvGiaoViec ");
		stringBuilder.append(",T1.TEN_NV_GIAO_VIEC tenNvGiaoViec ");
		stringBuilder.append(",T1.TONG_NV tongNv ");
		stringBuilder.append(",T1.TONG_CHAM tongCham ");
    	
    	stringBuilder.append(" FROM TBL_KH_QLVC T1 ");    	
    	stringBuilder.append("WHERE 1=1 ");
    	
		
		if (null != obj.getKhQlvcId()) {
			stringBuilder.append("AND T1.KH_QLVC_ID = :khQlvcId ");
		}
		if (StringUtils.isNotEmpty(obj.getThang())) {
			stringBuilder.append("AND (UPPER(T1.THANG) LIKE UPPER(:thang) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getNam())) {
			stringBuilder.append("AND (UPPER(T1.NAM) LIKE UPPER(:nam) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getMaDonVi())) {
			stringBuilder.append("AND (UPPER(T1.MA_DON_VI) LIKE UPPER(:maDonVi) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getTenDonVi())) {
			stringBuilder.append("AND (UPPER(T1.TEN_DON_VI) LIKE UPPER(:tenDonVi) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getMaNvGiaoViec())) {
			stringBuilder.append("AND (UPPER(T1.MA_NV_GIAO_VIEC) LIKE UPPER(:maNvGiaoViec) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getTenNvGiaoViec())) {
			stringBuilder.append("AND (UPPER(T1.TEN_NV_GIAO_VIEC) LIKE UPPER(:tenNvGiaoViec) ESCAPE '&') ");
		}
		
		
		stringBuilder.append(" order by T1.KH_QLVC_ID desc");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
    
    	
			
			query.addScalar("khQlvcId", new LongType());
			query.addScalar("thang", new StringType());
			query.addScalar("nam", new StringType());
			query.addScalar("maDonVi", new StringType());
			query.addScalar("tenDonVi", new StringType());
			query.addScalar("maNvGiaoViec", new StringType());
			query.addScalar("tenNvGiaoViec", new StringType());
			query.addScalar("tongNv", new LongType());
			query.addScalar("tongCham", new LongType());
			query.setResultTransformer(Transformers.aliasToBean(TblKhQlvcDTO.class));
    	
		
		if (StringUtils.isNotEmpty(obj.getThang())) {
			query.setParameter("thang", "%" + ValidateUtils.validateKeySearch(obj.getThang()) + "%");
			queryCount.setParameter("thang", "%" + ValidateUtils.validateKeySearch(obj.getThang()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getNam())) {
			query.setParameter("nam", "%" + ValidateUtils.validateKeySearch(obj.getNam()) + "%");
			queryCount.setParameter("nam", "%" + ValidateUtils.validateKeySearch(obj.getNam()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaDonVi())) {
			query.setParameter("maDonVi", "%" + ValidateUtils.validateKeySearch(obj.getMaDonVi()) + "%");
			queryCount.setParameter("maDonVi", "%" + ValidateUtils.validateKeySearch(obj.getMaDonVi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenDonVi())) {
			query.setParameter("tenDonVi", "%" + ValidateUtils.validateKeySearch(obj.getTenDonVi()) + "%");
			queryCount.setParameter("tenDonVi", "%" + ValidateUtils.validateKeySearch(obj.getTenDonVi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaNvGiaoViec())) {
			query.setParameter("maNvGiaoViec", "%" + ValidateUtils.validateKeySearch(obj.getMaNvGiaoViec()) + "%");
			queryCount.setParameter("maNvGiaoViec", "%" + ValidateUtils.validateKeySearch(obj.getMaNvGiaoViec()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenNvGiaoViec())) {
			query.setParameter("tenNvGiaoViec", "%" + ValidateUtils.validateKeySearch(obj.getTenNvGiaoViec()) + "%");
			queryCount.setParameter("tenNvGiaoViec", "%" + ValidateUtils.validateKeySearch(obj.getTenNvGiaoViec()) + "%");
		}

		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}

		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}  
    public Long deleteList(List<Long> obj) {
		try {

			String sql2 = "delete from TBL_KH_QLVC  where KH_QLVC_ID in (:khQlvcId) ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameterList("khQlvcId", obj);
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
    
	@SuppressWarnings("unchecked")
	public List<TblKhQlvcDTO> getForAutoCompleteMonth(TblKhQlvcDTO obj) {
		String sql = "SELECT distinct " + "T1.THANG thang " + " FROM TBL_KH_QLVC T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);
		
		if (StringUtils.isNotEmpty(obj.getThang())) {
			stringBuilder.append(" AND (upper(T1.THANG) LIKE upper(:thang) escape '&' )");
		}
		stringBuilder.append(" order by T1.THANG ");
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("thang", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblKhQlvcDTO.class));

		if (StringUtils.isNotEmpty(obj.getThang())) {
			query.setParameter("thang", "%" + ValidateUtils.validateKeySearch(obj.getThang()) + "%");
		}

		return query.list();
	}
    
	@SuppressWarnings("unchecked")
	public List<TblKhQlvcDTO> getForAutoCompleteYear(TblKhQlvcDTO obj) {
		String sql = "SELECT distinct " + "T1.NAM nam " + " FROM TBL_KH_QLVC T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getNam())) {
			stringBuilder.append(" AND (upper(T1.NAM) LIKE upper(:nam) escape '&' )");
		}
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("nam", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblKhQlvcDTO.class));

		if (StringUtils.isNotEmpty(obj.getNam())) {
			query.setParameter("nam", "%" + ValidateUtils.validateKeySearch(obj.getNam()) + "%");
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TblKhQlvcDTO> getForAutoCompleteMaNv(TblKhQlvcDTO obj) {
		String sql = "SELECT distinct T1.MA_NV_GIAO_VIEC maNvGiaoViec,T1.TEN_NV_GIAO_VIEC tenNvGiaoViec  FROM TBL_KH_QLVC T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		if (StringUtils.isNotEmpty(obj.getName())) {
			stringBuilder.append(
					" AND (upper(T1.MA_NV_GIAO_VIEC) LIKE upper(:name) escape '&' OR upper(T1.TEN_NV_GIAO_VIEC) LIKE upper(:name) escape '&') "
					);
		}
		stringBuilder.append(" ORDER BY T1.MA_NV_GIAO_VIEC ");
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("maNvGiaoViec", new StringType());
		query.addScalar("tenNvGiaoViec", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblKhQlvcDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		return query.list();
	}
    
	@SuppressWarnings("unchecked")
	public List<TblKhQlvcDTO> getForAutoCompleteMaDv(TblKhQlvcDTO obj) {
		String sql = "SELECT distinct T1.MA_DON_VI maDonVi,T1.TEN_DON_VI tenDonVi  FROM TBL_KH_QLVC T1 where 1=1 ";

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
		query.setResultTransformer(Transformers.aliasToBean(TblKhQlvcDTO.class));

		if (StringUtils.isNotEmpty(obj.getDepartment())) {
			query.setParameter("department", "%" + ValidateUtils.validateKeySearch(obj.getDepartment()) + "%");
		}
		return query.list();
	}
    //Biểu đồ đơn vị theo tháng,năm 
    @SuppressWarnings("unchecked")
	public List<TblKhQlvcDTO> getChartDept(TblKhQlvcDTO obj){
    	StringBuilder stringBuilder = new StringBuilder(" select sum(T1.TONG_NV) tongNv, sum(T1.TONG_CHAM) tongCham,T1.MA_DON_VI maDonVi "
    			+ " from ( select TONG_NV, TONG_CHAM,MA_DON_VI from TBL_KH_QLVC where 1=1  ");
    			
    	
    	if(StringUtils.isNotEmpty(obj.getMaNvGiaoViec())){
    		stringBuilder.append(" and MA_NV_GIAO_VIEC=:maNvGiaoViec ");
    	}
    	
    	if(StringUtils.isNotEmpty(obj.getNam())){
    		stringBuilder.append(" and NAM=:nam ");
    	}
    	if(obj.getListThang() != null && obj.getListThang().size() > 0){
    		stringBuilder.append(" and THANG IN (:listThang) ");
    	}
    	if(obj.getListMaDonVi() != null && obj.getListMaDonVi().size() > 0){
    		stringBuilder.append(" and MA_DON_VI IN (:listMaDonVi)  ");
    	}
    	stringBuilder.append(" ) T1 ");
    	stringBuilder.append(" group by T1.MA_DON_VI ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	query.addScalar("tongNv", new LongType());
    	
    	query.addScalar("tongCham", new LongType());
    	
    	query.addScalar("maDonVi", new StringType());
    	
    	query.setResultTransformer(Transformers.aliasToBean(TblKhQlvcDTO.class));
    	
    	if(StringUtils.isNotEmpty(obj.getMaNvGiaoViec())){
    		query.setParameter("maNvGiaoViec", obj.getMaNvGiaoViec());
    	}
    	if(StringUtils.isNotEmpty(obj.getNam())){
    		query.setParameter("nam", obj.getNam());
    	}
    	if(obj.getListThang() != null && obj.getListThang().size() > 0){
    		query.setParameterList("listThang", obj.getListThang());
    	}
    	
    	if(obj.getListMaDonVi() != null && obj.getListMaDonVi().size() > 0){
    		query.setParameterList("listMaDonVi", obj.getListMaDonVi());
    	}
    	
	
    	return query.list();
    }

  //Biểu đồ theo  thang
    @SuppressWarnings("unchecked")
	public List<TblKhQlvcDTO> getChartMonth(TblKhQlvcDTO obj){
    	StringBuilder stringBuilder = new StringBuilder(" select sum(T1.TONG_NV) tongNv, sum(T1.TONG_CHAM) tongCham, T1.THANG thang ");
    	stringBuilder.append(" from ( select TONG_NV, TONG_CHAM, THANG from TBL_KH_QLVC T1 where 1=1 ");
    	
    	if(StringUtils.isNotEmpty(obj.getMaNvGiaoViec())){
    		stringBuilder.append(" and MA_NV_GIAO_VIEC=:maNvGiaoViec ");
    	}
    	if(StringUtils.isNotEmpty(obj.getNam())){
    		stringBuilder.append(" and NAM=:nam ");
    	}
    	
    	if(obj.getListMaDonVi()!=null && obj.getListMaDonVi().size()>0){
    		stringBuilder.append(" and T1.MA_DON_VI IN (:listMaDonVi) ");
    	}
    	if(obj.getListThang()!=null && obj.getListThang().size()>0){
    		stringBuilder.append(" and THANG IN (:listThang)  ");
    	}
    	stringBuilder.append("  ) T1 ");
    	stringBuilder.append(" group by T1.THANG order by T1.THANG  "  );
    			
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	query.addScalar("tongNv", new LongType());
    	
    	query.addScalar("tongCham", new LongType());
    	query.addScalar("thang", new StringType());
  
   
    	query.setResultTransformer(Transformers.aliasToBean(TblKhQlvcDTO.class));
    	if(StringUtils.isNotEmpty(obj.getMaNvGiaoViec())){
    		query.setParameter("maNvGiaoViec", obj.getMaNvGiaoViec());
    	}
    	
    	if(StringUtils.isNotEmpty(obj.getNam())){
    		query.setParameter("nam", obj.getNam());
    	}
    	
    	if(obj.getListMaDonVi()!=null && obj.getListMaDonVi().size()>0){ 
    		query.setParameterList("listMaDonVi", obj.getListMaDonVi());
    	}
    	
    	if(obj.getListThang()!=null && obj.getListThang().size()>0){ 
    		query.setParameterList("listThang", obj.getListThang());
    	}
    	return query.list();
    }
    //Biểu đồ theo nhân viên giao viec tháng
//    @SuppressWarnings("unchecked")
//    public List<TblKhQlvcDTO> getChartEmpMonth(TblKhQlvcDTO obj){
//    	String sql = " select sum(T1.TONG_NV) tongNv, sum(T1.TONG_CHAM) tongCham,T1.THANG thang "
//    				+" from TBL_KH_QLVC T1 where 1=1 ";
//    	
//    	StringBuilder stringBuilder = new StringBuilder(sql);
//    	if(StringUtils.isNotEmpty(obj.getTenNvGiaoViec())){
//    		stringBuilder.append(" and T1.TEN_NV_GIAO_VIEC=:tenNvGiaoViec ");
//    	}
//    	if(StringUtils.isNotEmpty(obj.getNam())){
//    		stringBuilder.append(" and T1.NAM=:nam ");
//    	}
//    	if(StringUtils.isNotEmpty(obj.getMaDonVi())){
//    		stringBuilder.append(" and T1.MA_DON_Vi=:maDonVi ");
//    	}
//    	stringBuilder.append(" group by T1.THANG order by T1.THANG ");
//    	
//    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
//    	
//    	query.addScalar("tongNv", new LongType());
//    	
//    	query.addScalar("tongCham", new LongType());
//    	query.addScalar("thang", new StringType());
//    	query.setResultTransformer(Transformers.aliasToBean(TblKhQlvcDTO.class));
//    	
//    	if(StringUtils.isNotEmpty(obj.getTenNvGiaoViec())){
//    		query.setParameter("tenNvGiaoViec", obj.getTenNvGiaoViec());
//    	}
//    	if(StringUtils.isNotEmpty(obj.getNam())){
//    		query.setParameter("nam", obj.getNam());
//    	}
//    	
//    	return query.list();
//    }
//    
//    @SuppressWarnings("unchecked")
//    public List<TblKhQlvcDTO> getChartEmpDetp(TblKhQlvcDTO obj){
//    	String sql = " select sum(T1.TONG_NV) tongNv, sum(T1.TONG_CHAM) tongCham,T1.MA_DON_VI maDonVi "
//    				+" from TBL_KH_QLVC T1 where 1=1 ";
//    	
//    	StringBuilder stringBuilder = new StringBuilder(sql);
//    	if(StringUtils.isNotEmpty(obj.getMaNvGiaoViec())){
//    		stringBuilder.append(" and T1.MA_NV_GIAO_VIEC=:maNvGiaoViec ");
//    	}
//    	if(StringUtils.isNotEmpty(obj.getNam())){
//    		stringBuilder.append(" and T1.NAM=:nam ");
//    	}
//    	
//    	stringBuilder.append(" group by T1.MA_DON_VI  ");
//    	
//    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
//    	
//    	query.addScalar("tongNv", new LongType());
//    	
//    	query.addScalar("tongCham", new LongType());
//    	query.addScalar("maDonVi", new StringType());
//    	query.setResultTransformer(Transformers.aliasToBean(TblKhQlvcDTO.class));
//    	
//    	if(StringUtils.isNotEmpty(obj.getMaNvGiaoViec())){
//    		query.setParameter("maNvGiaoViec", obj.getMaNvGiaoViec());
//    	}
//    	if(StringUtils.isNotEmpty(obj.getNam())){
//    		query.setParameter("nam", obj.getNam());
//    	}
//    	
//    	return query.list();
//    }

    
}
