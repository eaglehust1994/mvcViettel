package com.viettel.qll.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.mchange.lang.LongUtils;
import com.viettel.qll.bo.TblUsersBO;
import com.viettel.qll.dto.TblNhanVienDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblRoleUserDTO;
import com.viettel.qll.dto.TblUsersDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

/**
 * @author hailh10
 */
@Repository("tblUsersDAO")
public class TblUsersDAO extends BaseFWDAOImpl<TblUsersBO, Long> {

	public TblUsersDAO() {
		this.model = new TblUsersBO();
	}

	public TblUsersDAO(Session session) {
		this.session = session;
	}
	
	public List<TblUsersDTO> doSearch(TblUsersDTO obj) {
		StringBuilder sql = new StringBuilder("Select  us.TBL_USERS_ID tblUsersId,"
				+ "us.FULL_NAME fullName,"
				+ "us.DON_VI donVi,"
				+ "us.USER_CODE userCode,"
				+ "us.Chuc_DANH chucDanh,"
				+ "us.PHONG_BAN phongBan  "
				+ " from TBL_USERS us  where 1=1 ");

		if(StringUtils.isNotEmpty(obj.getDonVi())){
			sql.append(" and upper(us.DON_VI) like upper(:donVi)  escape '&'");
		}
		
		if(StringUtils.isNotEmpty(obj.getUserCode())){
			sql.append(" and upper(us.USER_CODE) = upper(:userCode)  ");
		}
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("fullName", new StringType());
		query.addScalar("tblUsersId", new LongType());
		query.addScalar("donVi", new StringType());
		query.addScalar("userCode", new StringType());
		query.addScalar("chucDanh", new StringType());
		query.addScalar("phongBan", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblUsersDTO.class));

		if(StringUtils.isNotEmpty(obj.getDonVi())){
			query.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
			queryCount.setParameter("donVi", "%" + ValidateUtils.validateKeySearch(obj.getDonVi()) + "%");
			
		}
		
		if(StringUtils.isNotEmpty(obj.getUserCode())){
			query.setParameter("userCode",obj.getUserCode());
			queryCount.setParameter("userCode", obj.getUserCode());
		}
		
		if (obj.getPage() != null && obj.getPageSize() != null) {
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
		}
		obj.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());

		return query.list();
	}
	
	public List<TblUsersDTO> getRoles(TblUsersDTO obj) {
		StringBuilder sql = new StringBuilder("select r.ROLE_NAME tblRolesName "
				+ "from TBL_ROLE_USER ru  join TBL_ROLES r on r.TBL_ROLES_ID=ru.TBL_ROLES_ID where ru.TBL_USERS_ID =:tblUsersId");


		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("tblRolesName", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblUsersDTO.class));
		query.setParameter("tblUsersId", obj.getTblUsersId());
		
		return query.list();
	}

	

	public List<TblUsersDTO> getUserRoles(String code) {
		StringBuilder sql = new StringBuilder("Select us.FULL_NAME fullName,r.ROLE_CODE roleCode,us.DON_VI donVi "
				+ " from TBL_USERS us left join TBL_ROLE_USER ru ON us.TBL_USERS_ID=ru.TBL_USERS_ID"
				+ " left join  TBL_ROLES r ON r.TBL_ROLES_ID=ru.TBL_ROLES_ID " + " where us.USER_CODE=:code");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("fullName", new StringType());
		query.addScalar("roleCode", new StringType());
		query.addScalar("donVi", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblUsersDTO.class));
		query.setParameter("code", code);

		return query.list();
	}
	


	// @SuppressWarnings("unchecked")
	// public TblUsersDTO getById(Long id) {
	// StringBuilder stringBuilder = new StringBuilder("SELECT ");
	// stringBuilder.append("T1.TBL_USERS_ID tblUsersId ");
	// stringBuilder.append(",T1.USER_NAME userName ");
	// stringBuilder.append(",T1.PWD pwd ");
	// stringBuilder.append(",T1.USER_CODE userCode ");
	// stringBuilder.append(",T1.IS_ACTIVE isActive ");
	//
	// stringBuilder.append("FROM TBL_USERS T1 ");
	// stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND T1.TBL_USERS_ID =
	// :tblUsersId ");
	//
	// SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
	//
	// query.addScalar("tblUsersId", new LongType());
	// query.addScalar("userName", new StringType());
	// query.addScalar("pwd", new StringType());
	// query.addScalar("userCode", new StringType());
	// query.addScalar("isActive", new LongType());
	//
	// query.setParameter("tblUsersId", id);
	// query.setResultTransformer(Transformers.aliasToBean(TblUsersDTO.class));
	//
	// return (TblUsersDTO) query.uniqueResult();
	// }
	
	@SuppressWarnings("unchecked")
	public List<TblUsersDTO> getForAutoCompleteNV(TblUsersDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_USERS_ID tblUsersId," + "USER_CODE userCode,"
				+ "FULL_NAME fullName " 
				+ " from TBL_USERS  where 1=1 ");

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		
		if (StringUtils.isNotEmpty(obj.getUserCode())) {
			stringBuilder.append(
					" AND (upper(USER_CODE) LIKE upper(:userCode) escape '&' OR upper(FULL_NAME) LIKE upper(:userCode) escape '&')");
		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			stringBuilder.append(
					" AND upper(DON_VI) =upper(:donVi)");
		}

		stringBuilder.append(" ORDER BY TBL_USERS_ID");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("tblUsersId", new LongType());
		query.addScalar("userCode", new StringType());
		query.addScalar("fullName", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblUsersDTO.class));
		

		if (StringUtils.isNotEmpty(obj.getUserCode())) {
			query.setParameter("userCode", "%" + ValidateUtils.validateKeySearch(obj.getUserCode()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDonVi())) {
			query.setParameter("donVi", obj.getDonVi());
		}

		return query.list();
	}
	private final static String DEP_PTK = "KCQ_CTCT_QT";
	@SuppressWarnings("unchecked")
	public List<TblUsersDTO> getForAutoCompleteNVPtk(TblUsersDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT  TBL_USERS_ID tblUsersId," + "USER_CODE userCode,"
				+ "FULL_NAME fullName " 
				+ " from TBL_USERS  where DON_VI=:donVi and 1=1 and IS_ACTIVE is null ");

		StringBuilder stringBuilder = new StringBuilder(sql);

		stringBuilder.append(" AND ROWNUM <=10 ");
		
		if (StringUtils.isNotEmpty(obj.getUserCode())) {
			stringBuilder.append(
					" AND (upper(USER_CODE) LIKE upper(:userCode) escape '&' OR upper(FULL_NAME) LIKE upper(:userCode) escape '&')");
		}
		

		stringBuilder.append(" ORDER BY TBL_USERS_ID");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("tblUsersId", new LongType());
		query.addScalar("userCode", new StringType());
		query.addScalar("fullName", new StringType());
		
		query.setResultTransformer(Transformers.aliasToBean(TblUsersDTO.class));
		

		if (StringUtils.isNotEmpty(obj.getUserCode())) {
			query.setParameter("userCode", "%" + ValidateUtils.validateKeySearch(obj.getUserCode()) + "%");
		}
		
			query.setParameter("donVi",DEP_PTK);
	

		return query.list();
	}
}
