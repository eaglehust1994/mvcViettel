/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.bo.SettlementRightBO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.erp.dto.UserEmployeeDTO;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.erp.utils.Folder;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("catEmployeeDAO")
public class CatEmployeeDAO extends BaseFWDAOImpl<CatEmployeeBO, String> {

	@Value("${completionPartner.attachType}")
	private Long attachType;

	@Value("${completionPartner.folder}")
	private String folder;

	@Value("${folder_upload}")
	private String folderUpload;

	public CatEmployeeDAO() {
		this.model = new CatEmployeeBO();
	}

	public CatEmployeeDAO(Session session) {
		this.session = session;
	}

	public List<CatEmployeeDTO> getEmployeeByRole(CatEmployeeDTO dto) {
			StringBuilder sql = new StringBuilder();
			sql.append("select CAT_EMPLOYEE.FULL_NAME fullName, " + "CAT_EMPLOYEE.ID id ,"
					+ "SETTLEMENT_RIGHT.ROLEID roleid " + "from CAT_EMPLOYEE " + "inner join SETTLEMENT_RIGHT "
					+ "on CAT_EMPLOYEE.ID = SETTLEMENT_RIGHT.EMPLOYEE_ID " + "inner join CONSTR_CONSTRUCTIONS "
					+ "on CONSTR_CONSTRUCTIONS.CONSTRUCT_ID = SETTLEMENT_RIGHT.CONSTRUCT_ID " + "");
			sql.append(" where CONSTR_CONSTRUCTIONS.CONSTRUCT_ID = :constructId"
					+ " order by SETTLEMENT_RIGHT.IS_CURRENT DESC ");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);
			query.addScalar("roleid", LongType.INSTANCE);

			query.setParameter("constructId", dto.getConstructId());

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	public List<CatEmployeeDTO> getListEmployee(CatEmployeeDTO dto) {
			StringBuilder sql = new StringBuilder();
			sql.append("select CAT_EMPLOYEE.FULL_NAME fullName, " + "CAT_EMPLOYEE.ID id, "
					+ "USER_EMPLOYEE.USER_ID userId " + "from CAT_EMPLOYEE " + "inner join USER_EMPLOYEE "
					+ "on USER_EMPLOYEE.EMPLOYEE_ID = CAT_EMPLOYEE.ID " + "");
			sql.append(" where CAT_EMPLOYEE.TYPE = :type");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);

			query.setParameter("type", dto.getType());

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	public List<CatEmployeeDTO> doSearch(CatEmployeeDTO criteria) {
			StringBuilder sql = new StringBuilder();
			sql.append("select " + "E.ID id, " + "E.CODE code, " + "E.FULL_NAME fullName, " + "E.EMAIL email, "
					+ "E.PHONE_NUMBER phoneNumber, " + "E.BIRTHDAY birthday, " + "E.GROUP_ID groupId, "
					+ "E.IS_ACTIVE isActive, " + "E.POSITION_ID positionId, " + "E.IMAGE_PATH imagePath, "
					+ "E.CREATOR_ID creatorId, " + "E.CREATOR_GROUP_ID creatorGroupId, " + "E.ROLE role, "
					+ "E.ENABLE_EMAIL_WARNING enableEmailWarning, " + "E.POSITION_NAME positionName, "
					+ "E.ORGANIZATION_ID organizationId, " + "E.VOFFICE_ACCOUNT vofficeAccount, "
					+ "E.IDENTIFY_NUMBER identifyNumber, " + "E.PARTNER_ID partnerOrgId, " + "E.USER_ID userId, "
					+ "SYS_USER.LOGIN_NAME userName, " + "E.IMAGE_PATH imagePath, "
					+ "CAT_PARTNERS.PARTNER_NAME partnerOrgName, " + "E.TYPE type, " + "UAD.DOCUMENT_PATH documentPath "
					+ "from CAT_EMPLOYEE E "
					// + "inner join USER_EMPLOYEE on USER_EMPLOYEE.EMPLOYEE_ID
					// = E.ID "
					+ "inner join CAT_PARTNERS on CAT_PARTNERS.PARTNER_ID = E.PARTNER_ID "
					+ "left join SYS_USER on SYS_USER.USER_ID = E.USER_ID "
					+ "left join UTIL_ATTACHED_DOCUMENTS UAD on UAD.PARENT_ID = E.ID " + "WHERE E.IS_ACTIVE = 1 AND CAT_PARTNERS.STATUS = 2 ");

			// VietDB - them tim kiem theo code. xin dung xoa
			if (StringUtils.isNotEmpty(criteria.getCode())) {
				sql.append(" AND upper(E.CODE) LIKE upper(:code) ");
			}
			// END VIETDB
			if (StringUtils.isNotEmpty(criteria.getFullName())) {
				sql.append(" AND upper(E.FULL_NAME) LIKE upper(:fullName) ");
			}
			if (StringUtils.isNotEmpty(criteria.getEmail())) {
				sql.append(" AND upper(E.EMAIL) LIKE upper(:email) ");
			}
			if (criteria.getPartnerOrgId() != null) {
				sql.append(" AND E.PARTNER_ID = :partnerOrgId ");
			}
			if (StringUtils.isNotEmpty(criteria.getPartnerOrgName())) {
				sql.append(" AND upper(CAT_PARTNERS.PARTNER_NAME) LIKE upper(:partnerName) ");
			}
			sql.append(" order by E.ID DESC");
			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("id", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("email", StringType.INSTANCE);
			query.addScalar("phoneNumber", StringType.INSTANCE);
			query.addScalar("birthday", StandardBasicTypes.TIMESTAMP);
			query.addScalar("groupId", LongType.INSTANCE);
			query.addScalar("isActive", LongType.INSTANCE);
			query.addScalar("positionId", LongType.INSTANCE);
			query.addScalar("imagePath", StringType.INSTANCE);
			query.addScalar("creatorId", LongType.INSTANCE);
			query.addScalar("creatorGroupId", LongType.INSTANCE);
			query.addScalar("role", LongType.INSTANCE);
			query.addScalar("enableEmailWarning", LongType.INSTANCE);
			query.addScalar("positionName", StringType.INSTANCE);
			query.addScalar("organizationId", LongType.INSTANCE);
			query.addScalar("vofficeAccount", StringType.INSTANCE);
			query.addScalar("identifyNumber", StringType.INSTANCE);
			query.addScalar("partnerOrgId", LongType.INSTANCE);
			query.addScalar("userId", LongType.INSTANCE);
			query.addScalar("userName", StringType.INSTANCE);
			query.addScalar("imagePath", StringType.INSTANCE);
			query.addScalar("partnerOrgName", StringType.INSTANCE);
			query.addScalar("type", LongType.INSTANCE);
			query.addScalar("documentPath", StringType.INSTANCE);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			if (StringUtils.isNotEmpty(criteria.getCode())) {
				query.setParameter("code", "%" + criteria.getCode().trim() + "%");
			}
			if (StringUtils.isNotEmpty(criteria.getFullName())) {
				query.setParameter("fullName", "%" + criteria.getFullName().trim() + "%");
			}
			if (StringUtils.isNotEmpty(criteria.getEmail())) {
				query.setParameter("email", "%" + criteria.getEmail().trim() + "%");
			}
			if (criteria.getPartnerOrgId() != null) {
				query.setParameter("partnerOrgId", criteria.getPartnerOrgId());
			}
			if (StringUtils.isNotEmpty(criteria.getPartnerOrgName())) {
				query.setParameter("partnerName", "%" + criteria.getPartnerOrgName().trim() + "%");
			}

			return query.list();
	}

	// Tungpv
	public List<CatEmployeeDTO> getListEmployeeByRole(SettlementRightDTO rightDTO) {
			StringBuilder sql = new StringBuilder();
			sql.append("select distinct CE.FULL_NAME fullName, CE.ID id,  SR.IS_CURRENT isCurrent "
					+ "from CAT_EMPLOYEE CE " + "inner join SETTLEMENT_RIGHT SR on CE.ID = SR.EMPLOYEE_ID "
					// + "inner join V_CONSTRUCTION_HCQT VCH on VCH.CONSTRUCT_ID
					// = SR.CONSTRUCT_ID "
					+ "inner join ROLE_CA RC on RC.ROLEID = SR.ROLEID ");

			if (rightDTO.getConstructId() != null) {
				sql.append(" WHERE SR.CONSTRUCT_ID =  " + rightDTO.getConstructId());
			}
			if (rightDTO.getRoleid() != null) {
				sql.append(" AND RC.ROLEID = " + rightDTO.getRoleid());
			}

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);
			query.addScalar("isCurrent", LongType.INSTANCE);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	// NgocCX
	public List<CatEmployeeDTO> getEmployeeNameAndIdByRole(SettlementRightDTO rightDTO) {
			StringBuilder sql = new StringBuilder();
			sql.append("select CAT_EMPLOYEE.FULL_NAME fullName,CAT_EMPLOYEE.ID id,CAT_EMPLOYEE.USER_ID userId " + " from CAT_EMPLOYEE "
					+ "inner join SETTLEMENT_RIGHT " + "on CAT_EMPLOYEE.ID = SETTLEMENT_RIGHT.EMPLOYEE_ID "
					+ " where 1=1 ");
			if(rightDTO.getConstructId() != null) {
				sql.append("  and SETTLEMENT_RIGHT.CONSTRUCT_ID = :constructId");
			}
//			sql.append(" order by SETTLEMENT_RIGHT.IS_CURRENT desc ");
			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);
			if(rightDTO.getConstructId() != null) {
				query.setParameter("constructId", rightDTO.getConstructId());
			}

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	public List<SettlementRightBO> getListEmployeeByConstruction(Long constructId) {

		Query q = getSession().createQuery(Joiner.on(" ").join(
				"from settlementright s join fetch s.catemployee join fetch s.roleca join fetch s.constrconstructions c   where 1=1   ",
				constructId != null ? " and c.constructId = :constructId " : ""));
		if (constructId != null) {
			q.setParameter("constructId", constructId);
		}
		q.setMaxResults(200);
		return q.list();
	}

	public List<CatEmployeeDTO> getEmployeeNameRole(SettlementRightDTO obj) {
			StringBuilder sql = new StringBuilder();
			sql.append("select CAT_EMPLOYEE.FULL_NAME fullName,ROLE_CA.ROLENAME " + " from CAT_EMPLOYEE "
					+ "inner join SETTLEMENT_RIGHT " + "on CAT_EMPLOYEE.ID = SETTLEMENT_RIGHT.EMPLOYEE_ID "
					+ "inner join CONSTR_CONSTRUCTIONS "
					+ "on CONSTR_CONSTRUCTIONS.CONSTRUCT_ID = SETTLEMENT_RIGHT.CONSTRUCT_ID" + " inner join ROLE_CA "
					+ "on ROLE_CA.ROLEID = SETTLEMENT_RIGHT.ROLEID ");
			if(obj.getConstructId() != null) {
				sql.append(" where CONSTR_CONSTRUCTIONS.CONSTRUCT_ID = :constructId");
			}

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);
			if(obj.getConstructId() != null) {
				query.setParameter("constructId", obj.getConstructId());
			}

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	// ChuongNV
	@SuppressWarnings("unchecked")
	public List<CatEmployeeDTO> getEmployeeByListID(String stringEmployee) {

		String[] word = stringEmployee.split(",");
		List<String> list=Lists.newArrayList();

		for (int i = 0; i < word.length; i++) {
			Integer a = i + 1;
			list.add(word[i]);
			list.add(a.toString());
		}

		StringBuffer sql = new StringBuffer(
				"select ce.id, ce.code code,ce.full_Name fullName,ce.email email,ce.position_name positionName from cat_employee ce where 1=1 and id IN (:stringEmployee)  order by decode(id,:list)");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		query.addScalar("id", StringType.INSTANCE);
		query.addScalar("code", StringType.INSTANCE);
		query.addScalar("fullName", StringType.INSTANCE);
		query.addScalar("email", StringType.INSTANCE);
		query.addScalar("positionName", StringType.INSTANCE);
		
		query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));
		query.setParameterList("stringEmployee", word);
		query.setParameterList("list", list);
		List<CatEmployeeDTO> listCat = query.list();
		return listCat;
	}

	// End ChuongNV
	public boolean checkCMT(String identifyNumber) {

		StringBuffer sql = new StringBuffer(
				"select IDENTIFY_NUMBER identifyNumber from CAT_EMPLOYEE WHERE IDENTIFY_NUMBER = :identifyNumber");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("identifyNumber", StringType.INSTANCE);
		query.setParameter("identifyNumber", identifyNumber);
		query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));
		if (query.list().size() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkUpdateCMT(String identifyNumber,String id) {
		StringBuffer sql = new StringBuffer(
				"select IDENTIFY_NUMBER identifyNumber from CAT_EMPLOYEE WHERE ID != :id AND IDENTIFY_NUMBER = :identifyNumber");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("identifyNumber", StringType.INSTANCE);
		query.setParameter("identifyNumber", identifyNumber);
		query.setParameter("id", id);
		query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));
		if (query.list().size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	@Transactional
	public String saveStrId(CatEmployeeDTO model) throws Exception {
		Session session = getSession();
		if (checkCMT(model.getIdentifyNumber())) {
			String catEmployeeIdString = (String) session.save(model.toModel());
			UserEmployeeDTO userEmployee = new UserEmployeeDTO();
			userEmployee.setEmployeeId(Long.parseLong(catEmployeeIdString));
			userEmployee.setUserId(model.getUserId());
			session.save(userEmployee.toModel());
			// add utilAttackDocument
			model.getUtilAttachedDocuments().setParentId(Long.parseLong(catEmployeeIdString));
			model.getUtilAttachedDocuments().setCreatedDate(new Date());
			model.getUtilAttachedDocuments().setType(attachType);
			model.getUtilAttachedDocuments().setDocumentPath(UEncrypt.decryptFileUploadPath(model.getUtilAttachedDocuments().getDocumentPath()));

		//	String documentPath = FileServiceImpl.fileUploadPath.substring(folderUpload.length() + 1);
		//	model.getUtilAttachedDocuments().setDocumentPath(documentPath);
		//	model.getUtilAttachedDocuments().setDocumentName(FileServiceImpl.fileUploadName);
		//	System.out.println("create documentPath =  " + documentPath + " id = " + catEmployeeIdString);

			/*Long utilAttachedDocuments = (Long) */session.save(model.getUtilAttachedDocuments().toModel());
			return catEmployeeIdString;
		} else {
			return null;
		}
	}

	public List<CatEmployeeDTO> getListEmployeeByCurrent(SettlementRightDTO rightDTO) {
			StringBuilder sql = new StringBuilder();
			sql.append("select CAT_EMPLOYEE.FULL_NAME fullName,CAT_EMPLOYEE.ID id" + " from CAT_EMPLOYEE "
					+ "inner join SETTLEMENT_RIGHT " + "on CAT_EMPLOYEE.ID = SETTLEMENT_RIGHT.EMPLOYEE_ID "
					+ "inner join CONSTR_CONSTRUCTIONS "
					+ "on CONSTR_CONSTRUCTIONS.CONSTRUCT_ID = SETTLEMENT_RIGHT.CONSTRUCT_ID" + " inner join ROLE_CA "
					+ "on ROLE_CA.ROLEID = SETTLEMENT_RIGHT.ROLEID where 1=1 ");
			
			if(rightDTO.getRoleid() != null) {
				sql.append(" AND ROLE_CA.ROLEID = :roleId");
			}
			if(rightDTO.getConstructId() != null) {
				sql.append(" AND CONSTR_CONSTRUCTIONS.CONSTRUCT_ID = :constructId");
			}
			
			sql.append(" ORDER BY SETTLEMENT_RIGHT.IS_CURRENT DESC");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);
			if(rightDTO.getRoleid() != null) {
				query.setParameter("roleId", rightDTO.getRoleid());
			}
			if(rightDTO.getConstructId() != null) {
				query.setParameter("constructId", rightDTO.getConstructId());
			}

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	public List<CatEmployeeDTO> getAllEmployee(CatEmployeeDTO dto, TotalNumDTO totalNum) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT " + "E.ID id, " + "E.CODE code, " + "E.FULL_NAME fullName, " + "E.EMAIL email, "
					+ "E.PHONE_NUMBER phoneNumber, " + "E.BIRTHDAY birthday, " + "E.GROUP_ID groupId, "
					+ "E.IS_ACTIVE isActive, " + "E.POSITION_ID positionId, " + "E.IMAGE_PATH imagePath, "
					+ "E.CREATOR_ID creatorId, " + "E.CREATOR_GROUP_ID creatorGroupId, " + "E.ROLE role, "
					+ "E.ENABLE_EMAIL_WARNING enableEmailWarning, " + "E.POSITION_NAME positionName, "
					+ "E.ORGANIZATION_ID organizationId, " + "E.VOFFICE_ACCOUNT vofficeAccount, "
					+ "E.IDENTIFY_NUMBER identifyNumber, " + "E.PARTNER_ID partnerOrgId, " + "E.USER_ID userId, "
				    + "E.TYPE type " + "FROM CAT_EMPLOYEE E "
					+ "WHERE E.IS_ACTIVE = 1 ");

			StringBuilder sqlcount = new StringBuilder();
			sqlcount.append("SELECT count(*)"
					 +"FROM CAT_EMPLOYEE E "
					 + "WHERE E.IS_ACTIVE = 1 ");
			if (StringUtils.isNotEmpty(dto.getCode())) {
				sql.append(" AND upper(E.CODE) LIKE upper(:code) ");
				sqlcount.append(" AND upper(E.CODE) LIKE upper(:code) ");
			}
			if (StringUtils.isNotEmpty(dto.getFullName())) {
				sql.append(" AND upper(E.FULL_NAME) LIKE upper(:fullName) ");
				sqlcount.append(" AND upper(E.FULL_NAME) LIKE upper(:fullName) ");
			}
			if (StringUtils.isNotEmpty(dto.getEmail())) {
				sql.append(" AND upper(E.EMAIL) LIKE upper(:email) ");
				sqlcount.append(" AND upper(E.EMAIL) LIKE upper(:email) ");
			}
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			SQLQuery querycount = getSession().createSQLQuery(sqlcount.toString());
			query.addScalar("id", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("email", StringType.INSTANCE);
			query.addScalar("phoneNumber", StringType.INSTANCE);
			query.addScalar("birthday", StandardBasicTypes.TIMESTAMP);
			query.addScalar("groupId", LongType.INSTANCE);
			query.addScalar("isActive", LongType.INSTANCE);
			query.addScalar("positionId", LongType.INSTANCE);
			query.addScalar("imagePath", StringType.INSTANCE);
			query.addScalar("creatorId", LongType.INSTANCE);
			query.addScalar("creatorGroupId", LongType.INSTANCE);
			query.addScalar("role", LongType.INSTANCE);
			query.addScalar("enableEmailWarning", LongType.INSTANCE);
			query.addScalar("positionName", StringType.INSTANCE);
			query.addScalar("organizationId", LongType.INSTANCE);
			query.addScalar("vofficeAccount", StringType.INSTANCE);
			query.addScalar("identifyNumber", StringType.INSTANCE);
			query.addScalar("partnerOrgId", LongType.INSTANCE);
			query.addScalar("userId", LongType.INSTANCE);
			query.addScalar("imagePath", StringType.INSTANCE);
			query.addScalar("type", LongType.INSTANCE);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));
			
			
			

			if (StringUtils.isNotEmpty(dto.getCode())) {
				query.setParameter("code", "%" + dto.getCode() + "%");
				querycount.setParameter("code", "%" + dto.getCode() + "%");
			}
			if (StringUtils.isNotEmpty(dto.getFullName())) {
				query.setParameter("fullName", "%" + dto.getFullName() + "%");
				querycount.setParameter("fullName", "%" + dto.getFullName() + "%");
			}
			if (StringUtils.isNotEmpty(dto.getEmail())) {
				query.setParameter("email", "%" + dto.getEmail() + "%");
				querycount.setParameter("email", "%" + dto.getEmail() + "%");
			}
			
			
			
			query.setFirstResult((dto.getPage().intValue() -1) * dto.getPageSize().intValue() );
			query.setMaxResults(dto.getPageSize().intValue());
			totalNum.setNum(Integer.valueOf(querycount.uniqueResult().toString()));
			totalNum.setRownum((dto.getPage().intValue() -1) * dto.getPageSize().intValue());
			
			return query.list();
	}

	public List<CatEmployeeDTO> doSearchEmployeeViettel(CatEmployeeDTO criteria) {
			StringBuilder sql = new StringBuilder();
			sql.append("select " + "E.ID id, " + "E.CODE code, " + "E.FULL_NAME fullName, " + "E.EMAIL email, "
					+ "E.PHONE_NUMBER phoneNumber, " + "E.BIRTHDAY birthday, " + "E.GROUP_ID groupId, "
					+ "E.IS_ACTIVE isActive, " + "E.POSITION_ID positionId, " + "E.IMAGE_PATH imagePath, "
					+ "E.CREATOR_ID creatorId, " + "E.CREATOR_GROUP_ID creatorGroupId, " + "E.ROLE role, "
					+ "E.ENABLE_EMAIL_WARNING enableEmailWarning, " + "E.POSITION_NAME positionName, "
					+ "E.ORGANIZATION_ID organizationId, " + "E.VOFFICE_ACCOUNT vofficeAccount, "
					+ "E.IDENTIFY_NUMBER identifyNumber, " + "E.PARTNER_ID partnerOrgId, " + "E.USER_ID userId, "
					+ "E.IMAGE_PATH imagePath, " + "E.TYPE type " + "from CAT_EMPLOYEE E "
					+ "WHERE E.IS_ACTIVE = 1 AND E.ROLE = 1  ");
			if (StringUtils.isNotEmpty(criteria.getCode())) {
				sql.append(" AND upper(E.CODE) LIKE upper(:code) ");
			}
			if (StringUtils.isNotEmpty(criteria.getFullName())) {
				sql.append(" AND upper(E.FULL_NAME) LIKE upper(:fullName) ");
			}
			if (StringUtils.isNotEmpty(criteria.getEmail())) {
				sql.append(" AND upper(E.EMAIL) LIKE upper(:email) ");
			}
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			query.addScalar("id", StringType.INSTANCE);
			query.addScalar("code", StringType.INSTANCE);
			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("email", StringType.INSTANCE);
			query.addScalar("phoneNumber", StringType.INSTANCE);
			query.addScalar("birthday", StandardBasicTypes.TIMESTAMP);
			query.addScalar("groupId", LongType.INSTANCE);
			query.addScalar("isActive", LongType.INSTANCE);
			query.addScalar("positionId", LongType.INSTANCE);
			query.addScalar("imagePath", StringType.INSTANCE);
			query.addScalar("creatorId", LongType.INSTANCE);
			query.addScalar("creatorGroupId", LongType.INSTANCE);
			query.addScalar("role", LongType.INSTANCE);
			query.addScalar("enableEmailWarning", LongType.INSTANCE);
			query.addScalar("positionName", StringType.INSTANCE);
			query.addScalar("organizationId", LongType.INSTANCE);
			query.addScalar("vofficeAccount", StringType.INSTANCE);
			query.addScalar("identifyNumber", StringType.INSTANCE);
			query.addScalar("partnerOrgId", LongType.INSTANCE);
			query.addScalar("userId", LongType.INSTANCE);
			query.addScalar("imagePath", StringType.INSTANCE);
			query.addScalar("type", LongType.INSTANCE);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			if (StringUtils.isNotEmpty(criteria.getCode())) {
				query.setParameter("code", "%" + criteria.getCode() + "%");
			}
			if (StringUtils.isNotEmpty(criteria.getFullName())) {
				query.setParameter("fullName", "%" + criteria.getFullName() + "%");
			}
			if (StringUtils.isNotEmpty(criteria.getEmail())) {
				query.setParameter("email", "%" + criteria.getEmail() + "%");
			}
			return query.list();
	}

	public List<CatEmployeeDTO> getAutoData(CatEmployeeDTO obj) {
			StringBuilder sql = new StringBuilder();
			sql.append("select CAT_EMPLOYEE.FULL_NAME fullName," + "CAT_EMPLOYEE.EMAIL email, " + "CAT_EMPLOYEE.ID id "
					+ " from CAT_EMPLOYEE " + " where  ROWNUM <= 50");
			
			if(StringUtils.isNotEmpty(obj.getKeySearch())){
				sql.append(" AND UPPER(CAT_EMPLOYEE.FULL_NAME) like UPPER(:fullName) ");
			}
			
			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("email", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);
			if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("fullName", "%"+obj.getKeySearch()+"%");
			}
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	public List<CatEmployeeDTO> getListEmployeeByRoleConstructId(CatEmployeeDTO obj) {
		StringBuilder sql = new StringBuilder();
		sql.append("select CAT_EMPLOYEE.FULL_NAME fullName," + " CAT_EMPLOYEE.ID id" + " from CAT_EMPLOYEE"
				+ " inner join SETTLEMENT_RIGHT " + " on CAT_EMPLOYEE.ID = SETTLEMENT_RIGHT.EMPLOYEE_ID"
				+ " inner join ROLE_CA" + " on ROLE_CA.ROLEID = SETTLEMENT_RIGHT.ROLEID" 
				+ " where 1=1 ");
		
		if(obj.getRoleid() != null) {
			sql.append(" AND ROLE_CA.ROLEID = :roleId");
		}
		if(obj.getConstructId() != null) {
			sql.append(" AND SETTLEMENT_RIGHT.CONSTRUCT_ID =:constructId");
		}
		sql.append(" ORDER BY SETTLEMENT_RIGHT.IS_CURRENT DESC");

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("fullName", StringType.INSTANCE);
		query.addScalar("id", StringType.INSTANCE);
		if(obj.getRoleid() != null) {
			query.setParameter("roleId", obj.getRoleid());
		}
		if(obj.getConstructId() != null) {
			query.setParameter("constructId", obj.getConstructId());
		}

		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

		return query.list();
	}

	public CatEmployeeDTO getEmployeeIdByUserId(Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT CAT_EMPLOYEE.ID id, CAT_EMPLOYEE.FULL_NAME fullName FROM CAT_EMPLOYEE ");
		if(userId != null) {
			sql.append("WHERE CAT_EMPLOYEE.USER_ID = :userid AND ROWNUM = 1");
		}
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", StringType.INSTANCE);
		query.addScalar("fullName", StringType.INSTANCE);
		if(userId != null) {
			query.setParameter("userid", userId);
		}
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));
		return (CatEmployeeDTO) query.uniqueResult();
	}

	public CatEmployeeDTO getRoleID(Long catEmployeeId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SETTLEMENT_RIGHT.ROLEID roleID FROM SETTLEMENT_RIGHT WHERE SETTLEMENT_RIGHT.EMPLOYEE_ID= :employeeId AND ROWNUM = 1");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("roleID", LongType.INSTANCE);
		query.setParameter("employeeId", catEmployeeId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));
		return (CatEmployeeDTO) query.uniqueResult();
	}

	public List<CatEmployeeDTO> getAutoDataPartner(CatEmployeeDTO obj) {
			StringBuilder sql = new StringBuilder();
			sql.append("select CAT_PARTNERS.PARTNER_NAME partnerName," + "CAT_PARTNERS.PARTNER_ID partnerId "
					+ " from CAT_PARTNERS "
					+ " inner join cat_employee on CAT_PARTNERS.PARTNER_ID=cat_employee.PARTNER_ID "
					+ " where  ROWNUM <= 50");

			if(StringUtils.isNotEmpty(obj.getKeySearch())){
				sql.append(" AND (UPPER(CAT_PARTNERS.PARTNER_NAME) like UPPER(:partnerName)) ");
			}
			
			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("partnerName", StringType.INSTANCE);
			query.addScalar("partnerId", LongType.INSTANCE);
			if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("partnerName", "%"+obj.getKeySearch()+"%");
			}
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	public List<CatEmployeeDTO> getAutoDataUnit(CatEmployeeDTO obj) {
			StringBuilder sql = new StringBuilder();
			sql.append("select SYS_GROUP.NAME groupName," + "SYS_GROUP.GROUP_ID groupId " + " from SYS_GROUP "
					+ " inner join cat_employee on SYS_GROUP.GROUP_ID=cat_employee.GROUP_ID "
					+ " where  ROWNUM <= 50");
			if(StringUtils.isNotEmpty(obj.getKeySearch())){
				sql.append(" AND (UPPER(SYS_GROUP.NAME) like UPPER(:groupName)) ");
			}
			
			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("groupName", StringType.INSTANCE);
			query.addScalar("groupId", LongType.INSTANCE);
			if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("groupName", "%"+obj.getKeySearch()+"%");
			}
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

	public List<CatEmployeeDTO> getAutoDataEmail(CatEmployeeDTO obj) {
			StringBuilder sql = new StringBuilder();
			sql.append("select " + "CAT_EMPLOYEE.EMAIL email, " + "CAT_EMPLOYEE.ID id " + " from CAT_EMPLOYEE "
					+ " where  ROWNUM <= 50");

			if(StringUtils.isNotEmpty(obj.getKeySearch())){
				sql.append(" AND (UPPER(CAT_EMPLOYEE.EMAIL) like UPPER(:email)) ");
			}
			
			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("email", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);
			if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("email", "%"+obj.getKeySearch()+"%");
			}
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));

			return query.list();
	}

//dodt_start edit search employee view auth HSHC
	public List<CatEmployeeDTO> DoSearch(CatEmployeeDTO obj, TotalNumDTO totalNum) {
			
			String sql1 = "(select a.ID id, a.full_name fullName,a.email email,b.name groupName,cp.partner_name partnerName,a.type type  "
					+ "from cat_employee a join sys_Group b on a.group_id=b.group_id left join cat_partners cp on "
					+ "cp.partner_id=a.partner_id and b.status=1 and a.is_active=1 where( a.type is  null or a.type = 1) union select "
					+ "a.ID id,a.full_name fullName,a.email email,null  groupName,cp.partner_name partnerName,a.type type  "
					+ "from cat_employee a left join cat_partners cp on cp.partner_id=a.partner_id where a.is_active=1 "
					+ "and a.type=2)  where 1 = 1";
			
			StringBuilder sqlcount = new StringBuilder();
			sqlcount.append("SELECT count(*) from " + sql1);
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from " + sql1);
			
			
			if (obj.getGroupName() != null && !"".equals(obj.getGroupName())) {
				sql.append(" and (UPPER(groupName) like UPPER('%" + obj.getGroupName().trim() + "%'))");
				sqlcount.append(" and (UPPER(groupName) like UPPER('%" + obj.getGroupName().trim() + "%'))");
			}
			if (obj.getPartnerName() != null && !"".equals(obj.getPartnerName())) {
				sql.append(" and (UPPER(partnerName) like UPPER('%" + obj.getPartnerName().trim() + "%'))");
				sqlcount.append(" and (UPPER(partnerName) like UPPER('%" + obj.getPartnerName().trim() + "%'))");
			}
			if (obj.getEmail() != null && !"".equals(obj.getEmail())) {
				sql.append(" and (UPPER(email) like UPPER('%" + obj.getEmail().trim() + "%'))");
				sqlcount.append(" and (UPPER(email) like UPPER('%" + obj.getEmail().trim() + "%'))");
			}
			if (obj.getFullName() != null && !"".equals(obj.getFullName())) {
				sql.append(" and (UPPER(fullName) like UPPER('%" + obj.getFullName().trim() + "%'))");
				sqlcount.append(" and (UPPER(fullName) like UPPER('%" + obj.getFullName().trim() + "%'))");
			}
			
			sql.append("  order by type");

			SQLQuery query = getSession().createSQLQuery(sql.toString());
			SQLQuery querycount = getSession().createSQLQuery(sqlcount.toString());
			query.addScalar("fullName", StringType.INSTANCE);
			query.addScalar("email", StringType.INSTANCE);
			query.addScalar("id", StringType.INSTANCE);
			query.addScalar("type", LongType.INSTANCE);
			query.addScalar("partnerName", StringType.INSTANCE);
			query.addScalar("groupName", StringType.INSTANCE);

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(CatEmployeeDTO.class));
			
			
			query.setFirstResult((obj.getPage().intValue() -1) * obj.getPageSize().intValue() );
			query.setMaxResults(obj.getPageSize().intValue());
			totalNum.setNum(Integer.valueOf(querycount.uniqueResult().toString()));
			totalNum.setRownum((obj.getPage().intValue() -1) * obj.getPageSize().intValue());

			return query.list();
	}
//end dodt
}
