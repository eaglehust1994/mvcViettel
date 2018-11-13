package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.MappingException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.FloatType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.viettel.qll.bo.TblQltsCongNoVatTuBO;
import com.viettel.qll.bo.TblTypeAPxkBO;
import com.viettel.qll.dto.TblQltsCongNoVatTuDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblTypeAPxkDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblQltsCongNoVatTuDAO")
public class TblQltsCongNoVatTuDAO extends BaseFWDAOImpl<TblQltsCongNoVatTuBO, Long> {

	public TblQltsCongNoVatTuDAO() {
		this.model = new TblQltsCongNoVatTuBO();
	}

	public TblQltsCongNoVatTuDAO(Session session) {
		this.session = session;
	}

	public List<TblQltsCongNoVatTuDTO> getAllQlCongNo(TblQltsCongNoVatTuDTO obj) {
		StringBuilder sql = new StringBuilder("select " + "TBL_QLTS_CONG_NO_VAT_TU_ID tblQltsCongNoVatTuId,"
				+ "DOI_TUONG_NHAN_NO doiTuongNhanNo," + "MA_NV maNv," + "DON_VI donVi," + "SO_PXK soPxk,"
				+ "NGAY_CHUNG_TU ngayChungTu," + "TEN_VAT_TU tenVatTu," + "MA_VAT_TU maVatTu," + "MA_TRAM maTram,"
				+ "HANG_MUC hangMuc," + "DVT dvt," + "GIA gia," + "KLXK_SO_LUONG klxkSoLuong,"
				+ "KLXK_THANH_TIEN klxkThanhTien," + "SNT_SO_LUONG sntSoLuong," + "SNT_THANH_TIEN sntThanhTien,"
				+ "SHHTDM_SO_LUONG shhtdmSoLuong," + "SHHTDM_THANH_TIEN shhtdmThanhTien,HOAT_DONG hoatDong,Xoa xoa,"
				+ "SDTTH_SO_LUONG sdtthSoLuong," + "SDTTH_THANH_TIEN sdtthThanhTien,TYPE type,"
				+ "SMMPBTCCT_SO_LUONG smmpbtcctSoLuong," + "SMMPBTCCT_THANH_TIEN smmpbtcctThanhTien," + "GHI_CHU ghiChu,CHECK_NL checkNl  "
				+ " from TBL_QLTS_CONG_NO_VAT_TU where 1=1 ");
		
		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			sql.append(" and upper(MA_NV) like upper(:maNv) escape '&'");
		}
		if (StringUtils.isNotEmpty(obj.getDoiTuongNhanNo())) {
			sql.append(" and upper(DOI_TUONG_NHAN_NO) like upper(:doiTuongNhanNo) escape '&'");
		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			sql.append(" and upper(DON_VI) like upper(:donVi) escape '&'");
		}
		if (StringUtils.isNotEmpty(obj.getSoPxk())) {
			sql.append(" and upper(SO_PXK) like upper(:soPxk) escape '&'");
		}
		if (obj.getNgayChungTuFrom() != null && (obj.getNgayChungTuTo() == null)) {
			sql.append(" AND NGAY_CHUNG_TU >= :ngayChungTuFrom");
		}
		if ((obj.getNgayChungTuFrom() != null) && (obj.getNgayChungTuTo() != null)) {
			sql.append(" AND NGAY_CHUNG_TU between :ngayChungTuFrom and :ngayChungTuTo");
		}
		if (StringUtils.isNotEmpty(obj.getTenVatTu())) {
			sql.append(" and upper(TEN_VAT_TU) like  upper(:tenVatTu) escape '&'");
		}
		if (StringUtils.isNotEmpty(obj.getMaVatTu())) {
			sql.append(" and upper(MA_VAT_TU) like upper(:maVatTu) escape '&'");
		}
		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			sql.append(" and upper(MA_TRAM) like upper(:maTram) escape '&'");
		}
		if (StringUtils.isNotEmpty(obj.getHangMuc())) {
			sql.append(" and upper(HANG_MUC) like upper(:hangMuc) escape '&'");
		}
		if(obj.getType()!=null){
			sql.append(" and TYPE=:type ");
		}
		sql.append("  order by TBL_QLTS_CONG_NO_VAT_TU_ID ");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblQltsCongNoVatTuId", new LongType());
		query.addScalar("doiTuongNhanNo", new StringType());
		query.addScalar("maNv", new StringType());
		query.addScalar("donVi", new StringType());
		query.addScalar("soPxk", new StringType());
		query.addScalar("ngayChungTu", new TimestampType());
		query.addScalar("tenVatTu", new StringType());
		query.addScalar("maVatTu", new StringType());
		query.addScalar("maTram", new StringType());
		query.addScalar("hangMuc", new StringType());
		query.addScalar("dvt", new StringType());
		query.addScalar("gia", new FloatType());
		query.addScalar("klxkSoLuong", new FloatType());
		query.addScalar("klxkThanhTien", new FloatType());
		query.addScalar("sntSoLuong", new FloatType());
		query.addScalar("sntThanhTien", new FloatType());
		query.addScalar("shhtdmSoLuong", new FloatType());
		query.addScalar("shhtdmThanhTien", new FloatType());
		query.addScalar("sdtthSoLuong", new FloatType());
		query.addScalar("sdtthThanhTien", new FloatType());
		query.addScalar("type", new FloatType());
		query.addScalar("smmpbtcctSoLuong", new FloatType());
		query.addScalar("smmpbtcctThanhTien", new FloatType());
		query.addScalar("ghiChu", new StringType());
		query.addScalar("hoatDong", new BooleanType());
		query.addScalar("xoa", new BooleanType());
		query.addScalar("checkNl", new LongType());
		

		if (StringUtils.isNotEmpty(obj.getMaNv())) {
			
			query.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
			queryCount.setParameter("maNv", "%" + ValidateUtils.validateKeySearch(obj.getMaNv()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDoiTuongNhanNo())) {
			query.setParameter("doiTuongNhanNo", "%" + ValidateUtils.validateKeySearch(obj.getDoiTuongNhanNo()) + "%");
			queryCount.setParameter("doiTuongNhanNo", "%" + ValidateUtils.validateKeySearch(obj.getDoiTuongNhanNo()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			query.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
			queryCount.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getSoPxk())) {
			query.setParameter("soPxk", "%" + ValidateUtils.validateKeySearch(obj.getSoPxk()) + "%");
			queryCount.setParameter("soPxk", "%" + ValidateUtils.validateKeySearch(obj.getSoPxk()) + "%");
		}
		
		if ((obj.getNgayChungTuFrom() != null) && (obj.getNgayChungTuTo() == null)) {
			query.setTimestamp("ngayChungTuFrom", obj.getNgayChungTuFrom());
			queryCount.setTimestamp("ngayChungTuFrom", obj.getNgayChungTuFrom());
		}
		if ((obj.getNgayChungTuFrom() != null) && (obj.getNgayChungTuTo() != null)) {
			query.setTimestamp("ngayChungTuFrom", obj.getNgayChungTuFrom());
			queryCount.setTimestamp("ngayChungTuFrom", obj.getNgayChungTuFrom());

			query.setTimestamp("ngayChungTuTo", obj.getNgayChungTuTo());
			queryCount.setTimestamp("ngayChungTuTo", obj.getNgayChungTuTo());
		}
		
		
		if (StringUtils.isNotEmpty(obj.getTenVatTu())) {
			query.setParameter("tenVatTu","%" + ValidateUtils.validateKeySearch(obj.getTenVatTu()) + "%");
			queryCount.setParameter("tenVatTu","%" + ValidateUtils.validateKeySearch(obj.getTenVatTu()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaVatTu())) {
			query.setParameter("maVatTu", "%" + ValidateUtils.validateKeySearch(obj.getMaVatTu()) + "%");
			queryCount.setParameter("maVatTu", "%" + ValidateUtils.validateKeySearch(obj.getMaVatTu()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getMaTram())) {
			query.setParameter("maTram","%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");
			queryCount.setParameter("maTram","%" + ValidateUtils.validateKeySearch(obj.getMaTram()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getHangMuc())) {
			query.setParameter("hangMuc", "%" + ValidateUtils.validateKeySearch(obj.getHangMuc()) + "%");
			queryCount.setParameter("hangMuc", "%" + ValidateUtils.validateKeySearch(obj.getHangMuc()) + "%");
		}
		if(obj.getType()!=null){
			query.setParameter("type", obj.getType());
			queryCount.setParameter("type", obj.getType());
		}
		query.setResultTransformer(Transformers.aliasToBean(TblQltsCongNoVatTuDTO.class));

		if(obj.getPage() != null && obj.getPageSize() != null){
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return query.list();
	}
	
	public List<TblQltsCongNoVatTuDTO> selectDonVi(TblQltsCongNoVatTuDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT DISTINCT DON_VI donvi FROM TBL_QLTS_CONG_NO_VAT_TU where DON_VI is not null ");
		sql.append(" order by DON_VI ");
//		if (obj.getNgayChungTuFrom() != null && (obj.getNgayChungTuTo() == null)) {
//			sql.append(" AND NGAY_CHUNG_TU >= :ngayChungTuFrom");
//		}
		if ((obj.getNgayChungTuFrom() != null) && (obj.getNgayChungTuTo() != null)) {
			sql.append(" AND NGAY_CHUNG_TU between :ngayChungTuFrom and :ngayChungTuTo");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("donVi", new StringType());
		if ((obj.getNgayChungTuFrom() != null) && (obj.getNgayChungTuTo() != null)) {
			query.setTimestamp("ngayChungTuFrom", obj.getNgayChungTuFrom());
			query.setTimestamp("ngayChungTuTo", obj.getNgayChungTuTo());
		}
		query.setResultTransformer(Transformers.aliasToBean(TblQltsCongNoVatTuDTO.class));
		return query.list();
	}
	
	
	public TblQltsCongNoVatTuDTO sumTenByDonVi(TblQltsCongNoVatTuDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT sum(KLXK_THANH_TIEN) klxkThanhTien,sum(SDTTH_THANH_TIEN) sdtthThanhTien,sum(SHHTDM_THANH_TIEN) shhtdmThanhTien"
				+ ",sum(SMMPBTCCT_THANH_TIEN) smmpbtcctThanhTien,sum(SNT_THANH_TIEN) sntThanhTien "
				+ " FROM TBL_QLTS_CONG_NO_VAT_TU WHERE UPPER(DON_VI) = UPPER(:donVi) ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("klxkThanhTien", new FloatType());
		query.addScalar("sntThanhTien", new FloatType());
		query.addScalar("shhtdmThanhTien", new FloatType());
		query.addScalar("sdtthThanhTien", new FloatType());
		query.addScalar("smmpbtcctThanhTien", new FloatType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblQltsCongNoVatTuDTO.class));
		
		query.setParameter("donVi", obj.getDonVi());

		return (TblQltsCongNoVatTuDTO) query.uniqueResult();
	}
	
	
	public Long deleteObj(TblQltsCongNoVatTuDTO obj) {
		try {
			String sql2 = "delete from TBL_QLTS_CONG_NO_VAT_TU zz where  zz.SO_PXK =:soPxk "
				+ " and zz.MA_VAT_TU=:maVatTu ";
			SQLQuery query2 = getSession().createSQLQuery(sql2);
			query2.setParameter("maVatTu", obj.getMaVatTu());
			query2.setParameter("soPxk", obj.getSoPxk());
			query2.executeUpdate();
			return 1L;
		} catch (Exception e) {
			e.getMessage();
			return 0L;
		}
	}
	
	@Transactional
	  public String saveList1(List<TblQltsCongNoVatTuBO> obj)
	  {
	    try
	    {
	      for (TblQltsCongNoVatTuBO item : obj){
	    	  getSession().save(item);
	    	  System.out.println("ok");
	      }
	        
	    }
	    catch (SecurityException ex)
	    {
	      return ex.getMessage();
	    }catch (MappingException e) {
	    	return e.getMessage();
		}
	    return "SUCCESS";
	  }


}
