package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblGanNhiemVuBO;

import com.viettel.qll.dto.TblGanNhiemVuDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;

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
@Repository("tblGanNhiemVuDAO")
public class TblGanNhiemVuDAO extends BaseFWDAOImpl<TblGanNhiemVuBO, Long> {

    public TblGanNhiemVuDAO() {
        this.model = new TblGanNhiemVuBO();
    }

    public TblGanNhiemVuDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<TblGanNhiemVuDTO> doSearch(TblGanNhiemVuDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append("T1.GAN_NV_ID ganNvId ");
		stringBuilder.append(",T1.ID_USER idUser ");
		stringBuilder.append(",T1.FULLNAME fullname ");
		stringBuilder.append(",T1.ID_QL_CV_PTK idQlCvPtk ");
		stringBuilder.append(",T1.TEN_NHIEM_VU tenNhiemVu ");
		stringBuilder.append(",T1.NGAY_GIAO_NV ngayGiaoNv ");
		stringBuilder.append(",T1.NGAY_HOAN_THANH ngayHoanThanh ");
		stringBuilder.append(",T1.TRANG_THAI trangThai,T1.TEN_CONG_VIEC tenCongViec ");
    	
    	stringBuilder.append(" FROM TBL_GAN_NHIEM_VU T1  WHERE 1=1 ");    	
    	if (null!= obj.getIdQlCvPtk()) {
			stringBuilder.append(" AND T1.ID_QL_CV_PTK =:idQlCvPtk ");
		}
    	if (StringUtils.isNotEmpty(obj.getIdUser())) {
			stringBuilder.append(" AND (UPPER(T1.ID_USER) LIKE UPPER(:idUser) ESCAPE '&') ");
		}
    	
    	if (StringUtils.isNotEmpty(obj.getFullname())) {
			stringBuilder.append(" AND (UPPER(T1.FULLNAME) LIKE UPPER(:fullname) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getTenCongViec())) {
			stringBuilder.append(" AND (UPPER(T1.TEN_CONG_VIEC) LIKE UPPER(:tenCongViec) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getTenNhiemVu())) {
			stringBuilder.append(" AND (UPPER(T1.TEN_NHIEM_VU) LIKE UPPER(:tenNhiemVu) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getTrangThai())) {
			stringBuilder.append(" AND (UPPER(T1.TRANG_THAI) LIKE UPPER(:trangThai) ESCAPE '&') ");
		}
    	
		stringBuilder.append(" order by T1.FULLNAME");
    	StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("ganNvId", new LongType());
		query.addScalar("idUser", new StringType());
		query.addScalar("fullname", new StringType());
		query.addScalar("idQlCvPtk", new LongType());
		query.addScalar("tenNhiemVu", new StringType());
		query.addScalar("ngayGiaoNv", new DateType());
		query.addScalar("ngayHoanThanh", new DateType());
		query.addScalar("trangThai", new StringType());
		query.addScalar("tenCongViec", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblGanNhiemVuDTO.class));
		if (obj.getIdQlCvPtk()!=null) {
			query.setParameter("idQlCvPtk", obj.getIdQlCvPtk());
			queryCount.setParameter("idQlCvPtk", obj.getIdQlCvPtk());
		}
		if (StringUtils.isNotEmpty(obj.getIdUser())) {
			query.setParameter("idUser", "%" + ValidateUtils.validateKeySearch(obj.getIdUser()) + "%");
			queryCount.setParameter("idUser", "%" + ValidateUtils.validateKeySearch(obj.getIdUser()) + "%");
		}
		
		if (StringUtils.isNotEmpty(obj.getFullname())) {
			query.setParameter("fullname", "%" + ValidateUtils.validateKeySearch(obj.getFullname()) + "%");
			queryCount.setParameter("fullname", "%" + ValidateUtils.validateKeySearch(obj.getFullname()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenCongViec())) {
			query.setParameter("tenCongViec", "%" + ValidateUtils.validateKeySearch(obj.getTenCongViec()) + "%");
			queryCount.setParameter("tenCongViec", "%" + ValidateUtils.validateKeySearch(obj.getTenCongViec()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTenNhiemVu())) {
			query.setParameter("tenNhiemVu", "%" + ValidateUtils.validateKeySearch(obj.getTenNhiemVu()) + "%");
			queryCount.setParameter("tenNhiemVu", "%" + ValidateUtils.validateKeySearch(obj.getTenNhiemVu()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getTrangThai())) {
			query.setParameter("trangThai", "%" + ValidateUtils.validateKeySearch(obj.getTrangThai()) + "%");
			queryCount.setParameter("trangThai", "%" + ValidateUtils.validateKeySearch(obj.getTrangThai()) + "%");
		}
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		
		
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
		
	}  

    @SuppressWarnings("unchecked")
    public List<TblGanNhiemVuDTO> getMission(TblGanNhiemVuDTO obj){
    	StringBuilder stringBuilder = new StringBuilder("select ");
    	stringBuilder.append(" TEN_NHIEM_VU tenNhiemVu,TRANG_THAI trangThai from TBL_GAN_NHIEM_VU where FULLNAME=:fullname and TRANG_THAI=:trangThai");
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	query.addScalar("tenNhiemVu", new StringType());
    	query.addScalar("trangThai", new StringType());
    	query.setResultTransformer(Transformers.aliasToBean(TblGanNhiemVuDTO.class));
    	query.setParameter("fullname", obj.getFullname());
    	query.setParameter("trangThai", obj.getTrangThai());
    	return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<TblGanNhiemVuDTO> getEmployee(TblGanNhiemVuDTO  obj){
    	String sql ="select distinct T1.FULLNAME fullname,T1.ID_USER idUser,T1.TRANG_THAI trangThai  from TBL_GAN_NHIEM_VU T1 where T1.FULLNAME is not null";
    	StringBuilder stringBuilder = new StringBuilder(sql);
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	query.addScalar("fullname", new StringType());
    	query.addScalar("idUser", new StringType());
    	query.addScalar("trangThai",new StringType());
    	query.setResultTransformer(Transformers.aliasToBean(TblGanNhiemVuDTO.class));
    	return query.list();
    }
    
    public Long deleteList(List<Long> obj) {
		try {

			String sql2 = "delete from TBL_GAN_NHIEM_VU  where GAN_NV_ID in (:ganNvId) ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameterList("ganNvId", obj);
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
  
    public Long updateMission(String mission,String status,long idQlCvPtk){
    	try{
    		String sql1="update TBL_GAN_NHIEM_VU set TRANG_THAI=:trangThai where ID_QL_CV_PTK=:idQlCvPtk and TEN_NHIEM_VU=:tenNhiemVu";
    		SQLQuery query1 = getSession().createSQLQuery(sql1);
    		query1.setLong("idQlCvPtk", idQlCvPtk);
    		query1.setParameter("trangThai", status);
    		query1.setParameter("tenNhiemVu", mission);
    		query1.executeUpdate();
    		return 1L;
    	}catch (Exception e) {
			e.getMessage();
			return 0L;
		}
    }
    
    @SuppressWarnings("unchecked")
	public List<TblGanNhiemVuDTO> getEmpByWorkId(TblGanNhiemVuDTO obj){
    	String sql = "select FULLNAME fullname, NGAY_GIAO_NV ngayGiaoNv, NGAY_HOAN_THANH ngayHoanThanh  FROM TBL_GAN_NHIEM_VU  where ID_QL_CV_PTK=:idQlCvPtk ";
    	StringBuilder stringBuilder = new StringBuilder(sql);
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	query.addScalar("fullname", new StringType());
    	query.addScalar("ngayGiaoNv", new DateType());
    	query.addScalar("ngayHoanThanh", new DateType());
    	
    	query.setResultTransformer(Transformers.aliasToBean(TblGanNhiemVuDTO.class));
    	query.setParameter("idQlCvPtk", obj.getIdQlCvPtk());
    	
    	return query.list();
    }
    
  //tự động số hợp đông cdt
    @SuppressWarnings("unchecked")
	public List<TblGanNhiemVuDTO> getForAutoCompleteTenCv(TblGanNhiemVuDTO obj) {
		String sql = " SELECT distinct T1.TEN_CONG_VIEC tenCongViec  FROM TBL_GAN_NHIEM_VU T1 where 1=1 ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		
		if (StringUtils.isNotEmpty(obj.getTenCongViec())) {
			stringBuilder.append(" AND (upper(T1.TEN_CONG_VIEC) LIKE upper(:tenCongViec) escape '&' ) ");
		}
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("tenCongViec", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblGanNhiemVuDTO.class));

		if (StringUtils.isNotEmpty(obj.getTenCongViec())) {
			query.setParameter("tenCongViec", "%" + ValidateUtils.validateKeySearch(obj.getTenCongViec()) + "%");
		}

		return query.list();
	}
    
    @SuppressWarnings("unchecked")
	public List<TblGanNhiemVuDTO> getDetailInfo (TblGanNhiemVuDTO obj){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t1.FULLNAME fullname ");
    	sql.append(" ,t1.TEN_NHIEM_VU tenNhiemVu "); 	
    	sql.append(" ,t1.NGAY_GIAO_NV ngayGiaoNv ");
    	sql.append(" ,t1.NGAY_HOAN_THANH ngayHoanThanh ");
    	sql.append(" ,t1.TRANG_THAI trangThai ");
    	sql.append(" ,t1.ID_QL_CV_PTK idQlCvPtk ");
    	sql.append(" ,t2.MTT_MA_VI_TRI mttMaViTri ");
    	sql.append(" ,t2.SO_HD_CDT soHdCdt");
    	sql.append(" from TBL_GAN_NHIEM_VU t1 join TBL_QL_CV_PTK t2 on t1.ID_QL_CV_PTK=t2.TBL_QL_CV_PTK_ID where 1=1 ");
    	
    	if (StringUtils.isNotEmpty(obj.getIdUser())) {
    		sql.append(" AND (UPPER(t1.ID_USER) LIKE UPPER(:idUser) ESCAPE '&') ");
		}
    	
    	if (StringUtils.isNotEmpty(obj.getFullname())) {
    		sql.append(" AND (UPPER(t1.FULLNAME) LIKE UPPER(:fullname) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getTenCongViec())) {
			sql.append(" AND (UPPER(t1.TEN_CONG_VIEC) LIKE UPPER(:tenCongViec) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getTenNhiemVu())) {
			sql.append(" AND (UPPER(t1.TEN_NHIEM_VU) LIKE UPPER(:tenNhiemVu) ESCAPE '&') ");
		}
		if (StringUtils.isNotEmpty(obj.getTrangThai())) {
			sql.append(" AND (UPPER(t1.TRANG_THAI) LIKE UPPER(:trangThai) ESCAPE '&') ");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
	
	
		query.addScalar("fullname", new StringType());
		query.addScalar("idQlCvPtk", new LongType());
		query.addScalar("tenNhiemVu", new StringType());
		query.addScalar("ngayGiaoNv", new DateType());
		query.addScalar("ngayHoanThanh", new DateType());
		query.addScalar("trangThai", new StringType());
		query.addScalar("mttMaViTri", new StringType());
		query.addScalar("soHdCdt" , new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblGanNhiemVuDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getIdUser())) {
			query.setParameter("idUser", "%" + ValidateUtils.validateKeySearch(obj.getIdUser()) + "%");
			
		}
		
		if (StringUtils.isNotEmpty(obj.getFullname())) {
			query.setParameter("fullname", "%" + ValidateUtils.validateKeySearch(obj.getFullname()) + "%");
			
		}
		if (StringUtils.isNotEmpty(obj.getTenCongViec())) {
			query.setParameter("tenCongViec", "%" + ValidateUtils.validateKeySearch(obj.getTenCongViec()) + "%");
			
		}
		if (StringUtils.isNotEmpty(obj.getTenNhiemVu())) {
			query.setParameter("tenNhiemVu", "%" + ValidateUtils.validateKeySearch(obj.getTenNhiemVu()) + "%");
			
		}
		if (StringUtils.isNotEmpty(obj.getTrangThai())) {
			query.setParameter("trangThai", "%" + ValidateUtils.validateKeySearch(obj.getTrangThai()) + "%");
			
		}
		
		return query.list();
    }
   
}
