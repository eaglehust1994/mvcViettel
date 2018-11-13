package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.TblDonGiaThueBaoBO;
import com.viettel.qll.dto.TblDonGiaThueBaoDTO;
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
@Repository("tblDonGiaThueBaoDAO")
public class TblDonGiaThueBaoDAO extends BaseFWDAOImpl<TblDonGiaThueBaoBO, Long> {

    public TblDonGiaThueBaoDAO() {
        this.model = new TblDonGiaThueBaoBO();
    }

    public TblDonGiaThueBaoDAO(Session session) {
        this.session = session;
    }	
    public int checktinh(String tinh)
    {
    	StringBuilder sql = new StringBuilder();
    	sql.append("select count(2) from TBL_DANH_MUC where LOAI_DANH_MUC='Tỉnh' and TEN_DANH_MUC=:tinh");
    	SQLQuery query = getSession().createSQLQuery(sql.toString());
    	query.setParameter("tinh",ValidateUtils.validateKeySearch(tinh));
    	String count= query.list().toArray()[0].toString();
    	return Integer.parseInt(count);
    }
    public List<TblDonGiaThueBaoDTO> doSearch(TblDonGiaThueBaoDTO obj){
    	StringBuilder sql = new StringBuilder("select DG.TBL_DON_GIA_THUE_BAO_ID tblDonGiaThueBaoId,dg.tinh tinh,DG.VUNG_LUONG vungLuong,"
    			+ "DG.DON_GIA_CHU_DAU_TU donGiaChuDauTu,DG.DON_GIA_MOI donGiaMoi,DG.GHI_CHU ghiChu from TBL_DON_GIA_THUE_BAO DG"
    			+ " where xoa =0 and DG.HOAT_DONG=1");
    	if(StringUtils.isNotEmpty(obj.getTinh()))
    	{
    		sql.append(" and upper (tinh)  LIKE upper(:tinh) escape '&'");
    	}
    	if(StringUtils.isNotEmpty(obj.getVungLuong()))
    	{
    		sql.append(" and upper(VUNG_LUONG)  LIKE upper(:vungLuong) escape '&'");
    	}
    	if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND ((upper(tinh) LIKE upper(:keySearch)) "
					+ " OR (upper(VUNG_LUONG) LIKE upper(:keySearch))) ");
		}
    	sql.append(" order by tinh ");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("tblDonGiaThueBaoId", new LongType());
		query.addScalar("tinh", new StringType());
		query.addScalar("vungLuong", new StringType());
		query.addScalar("donGiaChuDauTu", new FloatType());
		query.addScalar("donGiaMoi", new FloatType());
		query.addScalar("ghiChu", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(TblDonGiaThueBaoDTO.class));

		if (StringUtils.isNotEmpty(obj.getTinh())) {
			query.setParameter("tinh",ValidateUtils.validateKeySearch(obj.getTinh()));
			queryCount.setParameter("tinh",ValidateUtils.validateKeySearch(obj.getTinh()));
		}
		if (StringUtils.isNotEmpty(obj.getVungLuong())) {
			//sql.append(" and EXTRACT(YEAR FROM TO_DATE(zz.THANG, 'MM/yyyy'))in(:exNam)");
			query.setParameter("vungLuong",ValidateUtils.validateKeySearch(obj.getVungLuong()));
			queryCount.setParameter("vungLuong",ValidateUtils.validateKeySearch(obj.getVungLuong()));
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
