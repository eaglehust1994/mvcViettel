package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblDanhMucBO;
import com.viettel.qll.dto.TblDanhMucDTO;
//import com.viettel.qll.utils.FilterUtilities;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.dto.DepartmentDTO;
import com.viettel.wms.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("tblDanhMucDAO")
public class TblDanhMucDAO extends BaseFWDAOImpl<TblDanhMucBO, Long> {

	public TblDanhMucDAO() {
		this.model = new TblDanhMucBO();
	}

	public TblDanhMucDAO(Session session) {
		this.session = session;
	}
	public Long checkhuyenbytentinh(String tenhuyen,String tentinh)
	{
		StringBuilder sql = new StringBuilder(
				"select count(1)  from TBL_DANH_MUC where upper(:tenhuyen)in(select dm1.TEN_DANH_MUC from TBL_DANH_MUC dm1 ");
		sql.append("where dm1.MA_DANH_MUC_CHA in (select dm2.MA_DANH_MUC from TBL_DANH_MUC dm2 where dm2.ten_danh_muc =:tentinh and MA_DANH_MUC_CHA is null))");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setParameter("tentinh", tentinh);
		query.setParameter("tenhuyen", tenhuyen);
		return ((BigDecimal) query.uniqueResult()).longValue();
	}
	public long checkExistTinhBytenTinh(String tinh,String matinh)
	{
		StringBuilder sql = new StringBuilder(
				"select count(1) from TBL_DANH_MUC where upper(TEN_DANH_MUC)=upper(:tentinh) and MA_DANH_MUC_CHA is null ");
		if(matinh!=null && matinh.trim()!="")
sql.append("and MA_DANH_MUC= :matinh");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.setParameter("tentinh", tinh);
		if(matinh!=null && matinh.trim()!="")
		query.setParameter("matinh", matinh);

		return ((BigDecimal) query.uniqueResult()).longValue();
	}
	public long checkExisttenhuyenBytenhuyen(String tenhuyen,String matinh)
	{
		StringBuilder sql = new StringBuilder(
				"select count(1) from TBL_DANH_MUC where upper(TEN_DANH_MUC)=upper(:tenhuyen) and MA_DANH_MUC_CHA = :madanhmuccha");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.setParameter("tenhuyen", tenhuyen);
		query.setParameter("madanhmuccha", matinh);

		//return ((BigDecimal) query.uniqueResult()).longValue();
		return 1l;
	}
	public long checkExistmatinhBymatinh(String matinh)
	{
		StringBuilder sql = new StringBuilder(
				"select count(1) from TBL_DANH_MUC where upper(MA_DANH_MUC)=upper(:matinh) and MA_DANH_MUC_CHA is null");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.setParameter("matinh", matinh);

		return ((BigDecimal) query.uniqueResult()).longValue();
	}
	@SuppressWarnings("unchecked")
	public List<TblDanhMucDTO> getall(TblDanhMucDTO obj) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT " + "dm.TBL_DANH_MUC_ID id," + " dm.TEN_DANH_MUC tenDanhMuc," + "dm.MA_DANH_MUC maDanhMuc, "
				+ "dm.GIA_TRI giaTri," + "dm.LOAI_DANH_MUC loaiDanhMuc,"
				// + "dm.DANH_MUC_CHA danhMucCha,"
				+ "dm.MA_DANH_MUC_CHA maDanhMucCha " + "from TBL_DANH_MUC dm"
				+ " left join TBL_DANH_MUC dm1 on dm1.TBL_DANH_MUC_ID=dm.MA_DANH_MUC_CHA " + " where 1=1 ");
		if (StringUtils.isNotEmpty(obj.getTenDanhMuc())) {
			sql.append(" AND upper(dm.TEN_DANH_MUC) like upper(:tenDanhMuc)");
		}

		if (StringUtils.isNotEmpty(obj.getMaDanhMuc())) {
			sql.append(" AND upper(dm.MA_DANH_MUC) like upper(:maDanhMuc)");
		}

		if (StringUtils.isNotEmpty(obj.getLoaiDanhMuc())) {
			sql.append(" AND upper(dm.LOAI_DANH_MUC) like upper(:loaiDanhMuc)");
		}

		if (obj.getId() != null) {
			sql.append(" AND (dm.MA_DANH_MUC_CHA = :id OR dm.TBL_DANH_MUC_ID=:id )");
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("tenDanhMuc", new StringType());
		query.addScalar("maDanhMuc", new StringType());
		query.addScalar("giaTri", new StringType());
		query.addScalar("loaiDanhMuc", new StringType());
		query.addScalar("maDanhMucCha", new StringType());
		// query.addScalar("danhMucCha", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(TblDanhMucDTO.class));
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		if (StringUtils.isNotEmpty(obj.getTenDanhMuc())) {
			query.setParameter("tenDanhMuc", "%" + ValidateUtils.validateKeySearch(obj.getTenDanhMuc()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getMaDanhMuc())) {
			query.setParameter("maDanhMuc", "%" + ValidateUtils.validateKeySearch(obj.getMaDanhMuc()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getLoaiDanhMuc())) {
			query.setParameter("loaiDanhMuc", "%" + ValidateUtils.validateKeySearch(obj.getLoaiDanhMuc()) + "%");
		}
		if (obj.getId() != null) {
			query.setParameter("id", obj.getId());
		}
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<TblDanhMucDTO> getForAutoCompleteDept(TblDanhMucDTO obj) {
		String sql = "SELECT " + "dm.TBL_DANH_MUC_ID id," + " dm.TEN_DANH_MUC tenDanhMuc,"
				+ "dm.MA_DANH_MUC maDanhMuc, " + "dm.GIA_TRI giaTri," + "dm.LOAI_DANH_MUC loaiDanhMuc,"
				+ "dm.MA_DANH_MUC_CHA maDanhMucCha " + "from TBL_DANH_MUC dm"
				+ " left join TBL_DANH_MUC dm1 on dm1.TBL_DANH_MUC_ID=dm.MA_DANH_MUC_CHA " + " where 1=1";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getName())) {
			stringBuilder.append(
					" AND (upper(dm.TEN_DANH_MUC) LIKE upper(:name) escape '&' OR upper(dm.MA_DANH_MUC) LIKE upper(:name) escape '&')");
		}

		stringBuilder.append(" ORDER BY dm.MA_DANH_MUC");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("id", new LongType());
		query.addScalar("tenDanhMuc", new StringType());
		query.addScalar("maDanhMuc", new StringType());
		query.addScalar("giaTri", new StringType());
		query.addScalar("loaiDanhMuc", new StringType());
		query.addScalar("maDanhMucCha", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDanhMucDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		// if (StringUtils.isNotEmpty(obj.getMaDanhMuc())) {
		// query.setParameter("name", "%" +
		// ValidateUtils.validateKeySearch(obj.getMaDanhMuc()) + "%");
		// }

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<TblDanhMucDTO> getForAutoCompleteDept1(TblDanhMucDTO obj) {
		String sql = "SELECT " + "dm.TBL_DANH_MUC_ID id," + " dm.TEN_DANH_MUC tenDanhMuc,"
				+ "dm.MA_DANH_MUC maDanhMuc, " + "dm.GIA_TRI giaTri," + "dm.LOAI_DANH_MUC loaiDanhMuc,"
				+ "dm.MA_DANH_MUC_CHA maDanhMucCha " + "from TBL_DANH_MUC dm" + " where MA_DANH_MUC_CHA is null ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getName())) {
			stringBuilder.append(
					" AND (upper(dm.TEN_DANH_MUC) LIKE upper(:name) escape '&' OR upper(dm.MA_DANH_MUC) LIKE upper(:name) escape '&')");
		}

		stringBuilder.append(" ORDER BY dm.MA_DANH_MUC");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("id", new LongType());
		query.addScalar("tenDanhMuc", new StringType());
		query.addScalar("maDanhMuc", new StringType());
		query.addScalar("giaTri", new StringType());
		query.addScalar("loaiDanhMuc", new StringType());
		query.addScalar("maDanhMucCha", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDanhMucDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TblDanhMucDTO> getForAutoCompleteDept1Popup(TblDanhMucDTO obj) {
		String sql = "SELECT " + "dm.TBL_DANH_MUC_ID id," + " dm.TEN_DANH_MUC tenDanhMuc,"
				+ "dm.MA_DANH_MUC maDanhMuc, " + "dm.GIA_TRI giaTri," + "dm.LOAI_DANH_MUC loaiDanhMuc,"
				+ "dm.MA_DANH_MUC_CHA maDanhMucCha " + "from TBL_DANH_MUC dm" + " where MA_DANH_MUC_CHA is null ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getNamePopup())) {
			stringBuilder.append(
					" AND (upper(dm.TEN_DANH_MUC) LIKE upper(:namePopup) escape '&' OR upper(dm.MA_DANH_MUC) LIKE upper(:namePopup) escape '&')");
		}

		stringBuilder.append(" ORDER BY dm.MA_DANH_MUC");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("id", new LongType());
		query.addScalar("tenDanhMuc", new StringType());
		query.addScalar("maDanhMuc", new StringType());
		query.addScalar("giaTri", new StringType());
		query.addScalar("loaiDanhMuc", new StringType());
		query.addScalar("maDanhMucCha", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDanhMucDTO.class));

		if (StringUtils.isNotEmpty(obj.getNamePopup())) {
			query.setParameter("namePopup", "%" + ValidateUtils.validateKeySearch(obj.getNamePopup()) + "%");
		}
		// if (StringUtils.isNotEmpty(obj.getMaDanhMuc())) {
		// query.setParameter("name", "%" +
		// ValidateUtils.validateKeySearch(obj.getMaDanhMuc()) + "%");
		// }

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<TblDanhMucDTO> getForAutoCompleteDept3(TblDanhMucDTO obj) {
		String sql = "SELECT " + "dm.TBL_DANH_MUC_ID id," + " dm.TEN_DANH_MUC tenDanhMuc,"
				+ "dm.MA_DANH_MUC maDanhMuc, " + "dm.GIA_TRI giaTri," + "dm.LOAI_DANH_MUC loaiDanhMuc,"
				+ "dm.MA_DANH_MUC_CHA maDanhMucCha " + "from TBL_DANH_MUC dm left join TBL_DANH_MUC dm1 on dm1.MA_DANH_MUC=dm.MA_DANH_MUC_CHA"
				+ " where dm.MA_DANH_MUC_CHA is not null  ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getName())) {
			stringBuilder.append(" AND (upper(dm.TEN_DANH_MUC) LIKE upper(:name) escape '&' "
					+ "OR upper(dm.MA_DANH_MUC) LIKE upper(:name) escape '&')");
		}

		if(StringUtils.isNotEmpty(obj.getGiaTri())){
			stringBuilder.append(" AND  dm.MA_DANH_MUC_CHA in (select MA_DANH_MUC from TBL_DANH_MUC where TEN_DANH_MUC =:tencha   )  ");
		}
		
		stringBuilder.append(" ORDER BY dm.MA_DANH_MUC");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("id", new LongType());
		query.addScalar("tenDanhMuc", new StringType());
		query.addScalar("maDanhMuc", new StringType());
		query.addScalar("giaTri", new StringType());
		query.addScalar("loaiDanhMuc", new StringType());
		query.addScalar("maDanhMucCha", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDanhMucDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		if(StringUtils.isNotEmpty(obj.getGiaTri())){
			query.setParameter("tencha", obj.getGiaTri());
		}
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TblDanhMucDTO> getForAutoCompleteDept2(TblDanhMucDTO obj) {
		String sql = "SELECT " + "dm.TBL_DANH_MUC_ID id," + " dm.TEN_DANH_MUC tenDanhMuc,"
				+ "dm.MA_DANH_MUC maDanhMuc, " + "dm.GIA_TRI giaTri," + "dm.LOAI_DANH_MUC loaiDanhMuc,"
				+ "dm.MA_DANH_MUC_CHA maDanhMucCha " + "from TBL_DANH_MUC dm left join TBL_DANH_MUC dm1 on dm1.MA_DANH_MUC=dm.MA_DANH_MUC_CHA"
				+ " where dm.MA_DANH_MUC_CHA is not null  ";

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getName())) {
			stringBuilder.append(" AND (upper(dm.TEN_DANH_MUC) LIKE upper(:name) escape '&' "
					+ "OR upper(dm.MA_DANH_MUC) LIKE upper(:name) escape '&')");
		}

		if(StringUtils.isNotEmpty(obj.getMaDanhMuc())){
			stringBuilder.append(" AND  dm.MA_DANH_MUC_CHA=:maDanhMuc ");
		}
		if(StringUtils.isNotEmpty(obj.getGiaTri())){
			stringBuilder.append("    AND  dm.MA_DANH_MUC_CHA in (select MA_DANH_MUC from TBL_DANH_MUC where MA_DANH_MUC_CHA is not null and TEN_DANH_MUC =:tencha   ) ");
		}
		
		stringBuilder.append(" ORDER BY dm.MA_DANH_MUC");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("id", new LongType());
		query.addScalar("tenDanhMuc", new StringType());
		query.addScalar("maDanhMuc", new StringType());
		query.addScalar("giaTri", new StringType());
		query.addScalar("loaiDanhMuc", new StringType());
		query.addScalar("maDanhMucCha", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDanhMucDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		if(StringUtils.isNotEmpty(obj.getMaDanhMuc())){
			query.setParameter("maDanhMuc", obj.getMaDanhMuc());
		}
		if(StringUtils.isNotEmpty(obj.getGiaTri())){
			query.setParameter("tencha", obj.getGiaTri());
		}
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TblDanhMucDTO> getForAutoCompleteDeptFor62DV(TblDanhMucDTO obj) {
		String sql = "SELECT " + "dm.TBL_DANH_MUC_ID id," + " dm.TEN_DANH_MUC tenDanhMuc,"
				+ "dm.MA_DANH_MUC maDanhMuc, " + "dm.GIA_TRI giaTri," + "dm.LOAI_DANH_MUC loaiDanhMuc,"
				+ "dm.MA_DANH_MUC_CHA maDanhMucCha " + "from TBL_DANH_MUC dm left join TBL_DANH_MUC dm1 on dm1.MA_DANH_MUC=dm.MA_DANH_MUC_CHA"
				+ " where dm.MA_DANH_MUC_CHA is not null and  dm.MA_CHI_NHANH is null "; 

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		if (StringUtils.isNotEmpty(obj.getName())) {
			stringBuilder.append(" AND (upper(dm.TEN_DANH_MUC) LIKE upper(:name) escape '&' "
					+ "OR upper(dm.MA_DANH_MUC) LIKE upper(:name) escape '&')");
		}

		if(StringUtils.isNotEmpty(obj.getMaDanhMuc())){
			stringBuilder.append(" AND  dm.MA_DANH_MUC_CHA=:maDanhMuc ");
		}
		
		stringBuilder.append(" ORDER BY dm.MA_DANH_MUC");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		query.addScalar("id", new LongType());
		query.addScalar("tenDanhMuc", new StringType());
		query.addScalar("maDanhMuc", new StringType());
		query.addScalar("giaTri", new StringType());
		query.addScalar("loaiDanhMuc", new StringType());
		query.addScalar("maDanhMucCha", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TblDanhMucDTO.class));

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		if(StringUtils.isNotEmpty(obj.getMaDanhMuc())){
			query.setParameter("maDanhMuc", obj.getMaDanhMuc());
		}
		
		return query.list();
	}
}
