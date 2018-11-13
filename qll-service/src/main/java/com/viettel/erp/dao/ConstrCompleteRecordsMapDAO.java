/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.commons.io.FileUtils;
import org.apache.commons.jexl2.internal.AbstractExecutor.Set;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.CatEmployeeBO;
import com.viettel.erp.bo.ConstrCompleteRecordsMapBO;
import com.viettel.erp.bo.UtilAttachedDocumentsBO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.ConstrCompleteRecordMapSubDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapCriteriaDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.TheSignCADTO;
import com.viettel.erp.dto.TheSignCAValuesTable;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.erp.utils.EncryptionUtils;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.security.PassTranformer;
//import com.viettel.erp.utils.EncryptionUtils;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.util.PassWordUtil;
import com.viettel.util.ResourceBundleUtils;
/*import com.viettel.voffice.ws_autosign.service.FileAttachTranfer;
import com.viettel.voffice.ws_autosign.service.InforStaff;
import com.viettel.voffice.ws_autosign.service.KTTSAutoSignSystemImpl;
import com.viettel.voffice.ws_autosign.service.KTTSAutoSignSystemImplService;
import com.viettel.voffice.ws_autosign.service.KttsVofficeCommInpuParam;
import com.viettel.voffice.ws_autosign.service.RrEmailParam;
import com.viettel.voffice.ws_autosign.service.StaffBO;*/
import com.viettel.voffice.ws_autosign.service.FileAttachTranfer;
import com.viettel.voffice.ws_autosign.service.KttsVofficeCommInpuParam;
import com.viettel.voffice.ws_autosign.service.Vo2AutoSignSystemImpl;
import com.viettel.voffice.ws_autosign.service.Vo2AutoSignSystemImplService;
import com.viettel.voffice.ws_autosign.service.Vof2EntityUser;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("constrCompleteRecordsMapDAO")
public class ConstrCompleteRecordsMapDAO extends BaseFWDAOImpl<ConstrCompleteRecordsMapBO, Long> {
	
	static Logger LOGGER = LoggerFactory.getLogger(ConstrCompleteRecordsMapDAO.class);

	@Autowired
	private CatEmployeeDAO catEmployeeDAO;
	
	@Autowired
    private EstimatesWorkItemsDAO estimatesWorkItemsDAO;
	
	@Autowired
	private UtilAttachedDocumentsDAO utilAttachedDocumentsDAO;

	@Value("${ca_wsUrl}")
	private String ca_wsUrl;

	@Value("${ca_wsQName_nameSpaceUri}")
	private String ca_wsQName_nameSpaceUri;

	@Value("${ca_wsQName_localPart}")
	private String ca_wsQName_localPart;

	@Value("${ca_appCode}")
	private String ca_appCode;

	@Value("${ca_appPass}")
	private String ca_appPass;

	@Value("${ca_sender}")
	private String ca_sender;

	@Value("${folder_upload}")
	private String folder_upload;

	@Value("${constrOrganization.attachTypeValue}")
	private Long attachTypeValue;

	@Value("${constrOrganization.attachTypeKey}")
	private Long attachTypeKey;
	@Value("${completionDrawing.attachType}")
	private Long attachType;

	@Value("${ca_encrypt_key}")
	private String ca_encrypt_key;

	public static final String SQL_GET_BY_CONSTRUCTION_ID = "select " + "a.CONSTR_COMP_RE_MAP_ID constrCompReMapId, "
			+ "a.DATA_TABLE_NAME dataTableName, " + "a.DATA_TABLE_ID dataTableId, " + "a.CODE code, "
			+ "a.DATA_TABLE_ID_VALUE dataTableIdValue, " + "a.CONSTRUCTION_ID constructionId, "
			+ "a.LEVEL_ORDER levelOrder, " + "a.STATUS status, " + "c.FILE_INVOICE_NAME invoiceName, "
			+ "c.FILE_INVOICE_CODE invoiceCode, " + "r.ROLENAME roleName, " + "a.CREATED_USER_ID createdUserId, "
			+ "su.LOGIN_NAME loginName, " + "TO_CHAR(a.CREATED_DATE, 'DD/MM/YYYY') createdDateStr, "
			+ "ce.FULL_NAME employeeFullname, " + "TO_CHAR(b.APPROVAL_DATE, 'DD/MM/YYYY') signApprovalDate, "
			+ "b.APPROVAL_STATUS signApprovalStatus, " + "b.COMMENTS signComments, "
			+ " typemap.CONSTR_TYPE typeConstruction, " + "  case   when c.cat_file_invoice_id != null then 0 "
			+ "  else  1 end isExistProfile " + " from CONSTR_COMPLETE_RECORDS_MAP a "
			+ " left join APPROVAL_SIGN_MANAGEMENT b on (a.CONSTR_COMP_RE_MAP_ID = b.CONSTR_COMP_RE_MAP_ID) "
			+ " left join ROLE_CA r  on (r.ROLEID = b.ROLEID) "
			+ " join cat_file_invoice c on (a.CAT_FILE_INVOICE_ID = c.CAT_FILE_INVOICE_ID) "
			+ " left join cat_employee ce on (b.EMPLOYEE_ID=ce.ID) "
			+ " left join sys_user su on a.CREATED_USER_ID=su.USER_ID "
			+ " left join FILE_IN_CONSTR_TYPE_MAP typemap on typemap.CAT_FILE_INVOICE_ID = c.CAT_FILE_INVOICE_ID "
			+ " where a.construction_Id = :constructionId" + " and $CONDITION "
			+ " order by c.FILE_INVOICE_NAME, constrCompReMapId desc, b.APPROVAL_ORDER asc ";

	public ConstrCompleteRecordsMapDAO() {
		this.model = new ConstrCompleteRecordsMapBO();
	}

	public ConstrCompleteRecordsMapDAO(Session session) {
		this.session = session;
	}

	/**
	 * getByConstructionId
	 */
	public List<ConstrCompleteRecordsMapDTO> getByConstructionId(long constructionId) {
		String sql = SQL_GET_BY_CONSTRUCTION_ID.replace("$CONDITION", " 1 = 1 ");
		SQLQuery query = getSession().createSQLQuery(sql);

		query.addScalar("constrCompReMapId", new LongType());
		query.addScalar("dataTableName", new StringType());
		query.addScalar("dataTableId", new StringType());
		query.addScalar("dataTableIdValue", new LongType());
		query.addScalar("constructionId", new LongType());
		query.addScalar("levelOrder", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("invoiceName", new StringType());
		query.addScalar("invoiceCode", new StringType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("loginName", new StringType());
		query.addScalar("createdDateStr", new StringType());
		query.addScalar("employeeFullname", new StringType());
		// query.addScalar("createdUserName", new StringType());
		query.addScalar("signApprovalDate", new StringType());
		query.addScalar("signApprovalStatus", new StringType());
		// query.addScalar("catFileInvoiceId", new LongType());
		query.addScalar("signComments", new StringType());
		query.addScalar("typeConstruction", new LongType());
		query.addScalar("isExistProfile", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("roleName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordsMapDTO.class));
		query.setParameter("constructionId", constructionId);

		return query.list();
	}

	// ChuongNV
	public void updateData(Long constrCompReMapId, String flag) {
		StringBuffer sql = new StringBuffer("update CONSTR_COMPLETE_RECORDS_MAP set ");
		if ("Yes".equals(flag)) {
			sql.append(" LEVEL_ORDER = 2 ");
		} else {
			sql.append(" LEVEL_ORDER = 1 ");
		}
		sql.append(" ,status = 0 where CONSTR_COMP_RE_MAP_ID = :constrCompReMapId");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setParameter("constrCompReMapId", constrCompReMapId);
		query.executeUpdate();
	}

	public List<Long> getApproId(Long constrCompReMapId) {
		StringBuffer sql = new StringBuffer(
				"select APPROVAL_SIGN_MANA_ID approvalSignManaId from APPROVAL_SIGN_MANAGEMENT where CONSTR_COMP_RE_MAP_ID = :constrCompReMapId");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("approvalSignManaId", LongType.INSTANCE);
		query.setParameter("constrCompReMapId", constrCompReMapId);

		List<Long> list = query.list();
		return list;
	}

	public TheSignCAValuesTable getValues(String id) {

		StringBuffer selectValue = new StringBuffer(
				"select co.DATA_TABLE_NAME dataTableName, co.DATA_TABLE_ID dataTableID, co.DATA_TABLE_ID_VALUE dataTableIDValue "
				+ "from CONSTR_COMPLETE_RECORDS_MAP co where CONSTR_COMP_RE_MAP_ID = :constrCompReMapId AND ROWNUM = 1");
		SQLQuery query = getSession().createSQLQuery(selectValue.toString());
		query.addScalar("dataTableName", new StringType());
		query.addScalar("dataTableID", new StringType());
		query.addScalar("dataTableIDValue", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TheSignCAValuesTable.class));
		query.setParameter("constrCompReMapId", id);
		return (TheSignCAValuesTable) query.uniqueResult();
	}

	public void updateTableChildrent(TheSignCAValuesTable theSignDto) {
		if (theSignDto.getDataTableName() == null || theSignDto.getDataTableIDValue() == null
				|| theSignDto.getDataTableID() == null) {
			return;
		}
		StringBuffer updateTableChildent = new StringBuffer("update " + theSignDto.getDataTableName()
				+ " set status_ca = 1 where " + theSignDto.getDataTableID() + " = " + theSignDto.getDataTableIDValue());
		SQLQuery query = getSession().createSQLQuery(updateTableChildent.toString());
		query.executeUpdate();

	}

	public void getCheckDataAndUpdateChild(Long constrCompReMapId) {
		StringBuffer sql = new StringBuffer(
				"select DATA_TABLE_NAME dataTableName, DATA_TABLE_ID dataTableId, DATA_TABLE_ID_VALUE dataTableIdValue "
						+ "from CONSTR_COMPLETE_RECORDS_MAP " + "where CONSTR_COMP_RE_MAP_ID = :constrCompReMapId ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("dataTableName", StringType.INSTANCE);
		query.addScalar("dataTableId", StringType.INSTANCE);
		query.addScalar("dataTableIdValue", LongType.INSTANCE);
		query.setParameter("constrCompReMapId", constrCompReMapId);
		query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordsMapDTO.class));
		ConstrCompleteRecordsMapDTO obj = (ConstrCompleteRecordsMapDTO) query.uniqueResult();

		updateChild(obj.getDataTableName(), obj.getDataTableId(), obj.getDataTableIdValue());
	}
	 List<String> WHITE_LIST_TABLE_CLUMN = Arrays.asList(
			"CONSTR_ORGANIZATION_PLAN, CONSTR_ORG_PLAN_ID",
			"CONSTR_WORK_LOGS,CONSTR_WORK_LOGS_ID",
			"CONSTR_WORK_LOGS_LABEL,CONSTR_WO_LOGS_LAB_ID",
			"B_MATERIAL_ACCEPTANCE,B_MATERIAL_ACCEPTANCE_ID",
			"CONSTR_GROUND_HANDOVER,CONSTR_GROUND_HANDOVER_ID",
			"DETAIL_SETTLEMENT_EVALUATE,DETAIL_SETTLEMENT_EVALUATE_ID",
			"DETAIL_SETTLEMENT_PROPOSAL,DETAIL_SETTLEMENT_PROPOSAL_ID",
			"TIT_AZI_CONSTR_ACCEPT,TIT_AZI_CONSTR_ACCEPT_ID","MONITOR_MISSION_ASSIGN,MONITOR_MISSION_ASSIGN_ID"
	);
    @Transactional
	public void updateChild(String dataTableName, String dataTableId, Long dataTableIdValue) {
//    	String strFormater = "UPDATE %s set APPROVAL_DATE = :appDate, STATUS_CA = 2 where %s = :dataTableIdValue";
//    	String sql = String.format(strFormater, dataTableName, dataTableId);
    	if(!WHITE_LIST_TABLE_CLUMN.contains(dataTableName.toUpperCase()+","+dataTableId.toUpperCase())){
    		throw new BusinessException("Truyen table_name khong nam trong white list: table_name="+dataTableName+", columN_name="+dataTableId);
    	}
		StringBuffer sql = new StringBuffer("update " + dataTableName + " set APPROVAL_DATE = :appDate, STATUS_CA = 2 "
				+ " where " + dataTableId + " = :dataTableIdValue ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setParameter("appDate", new Date());
		query.setParameter("dataTableIdValue", dataTableIdValue);
		query.executeUpdate();
		
		
		//dodt_18032017_start nghiem thu cong viec xau dung: update estimate_work_item status = 1 khi duyệt thành công status = 2
		if("WORK_ITEMS_ACCEPTANCE".equals(dataTableName)){
		updateEstimateWorkItem(dataTableIdValue);
		}
		//dodt_18032017_end
	}
	
	
	public void updateEstimateWorkItem(long id){
		WorkItemsAcceptanceDTO dto = new WorkItemsAcceptanceDTO();
		dto.setWorkItemsAcceptanceId(id); 
		List<EstimatesWorkItemsDTO> list = estimatesWorkItemsDAO.doSearchByWorkItemsAcceptanceId(dto);
		List<Long> ls =  new ArrayList<>();
		for(EstimatesWorkItemsDTO es : list){
			ls.add(es.getEstimatesWorkItemId());		
		}
	    Session session = getSession();
		session.createSQLQuery("update ESTIMATES_WORK_ITEMS set ESTIMATES_WORK_ITEMS.STATUS = 1 "
				+ " where ESTIMATES_WORK_ITEMS.ESTIMATES_WORK_ITEM_ID IN (:ID) ").setParameterList("ID", ls) .executeUpdate();
	}

	public void updateConstrCompleteRecordsMap(TheSignCADTO dto) {
		  StringBuffer updateConstrCompleteRecordsMap = new StringBuffer(
		    "update Constr_Complete_Records_Map " );
		  if ("theApproval".equals(dto.getIsSign()) && "OnePerson".equals(dto.getFlag())) {
		   updateConstrCompleteRecordsMap.append("set status = 2  ,LEVEL_ORDER = 1");
		   getCheckDataAndUpdateChild(Long.valueOf(dto.getConstrCompReMapId()));
		  } else if ("theApproval".equals(dto.getIsSign()) && "Two".equals(dto.getFlag())) {
		   updateConstrCompleteRecordsMap.append("set status = 2  ,LEVEL_ORDER = 2");
		   getCheckDataAndUpdateChild(Long.valueOf(dto.getConstrCompReMapId()));
		  } else if ("theApproval".equals(dto.getIsSign()) && "One".equals(dto.getFlag())) {
		   updateConstrCompleteRecordsMap.append("set status = 1  ,LEVEL_ORDER = 2 ");
		  } else if ("theApproval".equals(dto.getIsSign()) && "Three".equals(dto.getFlag())) {
		   updateConstrCompleteRecordsMap.append("set status = 1  ,LEVEL_ORDER = 3 ");
		  } else if ("theApproval".equals(dto.getIsSign()) && "No".equals(dto.getFlag())) {
		   updateConstrCompleteRecordsMap.append("set status = 1  ,LEVEL_ORDER = 1 ");
		  }
		  updateConstrCompleteRecordsMap.append(" where CONSTR_COMP_RE_MAP_ID = :constrCompReMapId");

		  SQLQuery query = getSession().createSQLQuery(updateConstrCompleteRecordsMap.toString());
		  query.setParameter("constrCompReMapId", dto.getConstrCompReMapId());
		  query.executeUpdate();
		 }

	// click button sign CA then update table childrent with status_ca = 1
	// and table ConstrCompleteRecordsMap with status=1
	@SuppressWarnings("unchecked")
	@Transactional
	public void updateTotal(TheSignCADTO dto) {
		TheSignCAValuesTable theSignDto = getValues(dto.getConstrCompReMapId());
		updateTableChildrent(theSignDto);
		updateConstrCompleteRecordsMap(dto);
	}

	public Long checkUserId(String id) {
		StringBuffer sqlCheck = new StringBuffer(
				"select EMPLOYEE_ID employeeId" + " from USER_EMPLOYEE " + "join CONSTR_COMPLETE_RECORDS_MAP "
						+ "on USER_EMPLOYEE.USER_ID = CONSTR_COMPLETE_RECORDS_MAP.CREATED_USER_ID "
						+ "where CONSTR_COMPLETE_RECORDS_MAP.CONSTR_COMP_RE_MAP_ID = :constrCompReMapId");
		SQLQuery query = getSession().createSQLQuery(sqlCheck.toString());
		query.addScalar("employeeId", new LongType());
		query.setParameter("constrCompReMapId", id);
		Long k = (Long) query.uniqueResult();
		if (k == null) {
			k = -1l;
		}
		return k;
	}

	public Long check(String id) {
		StringBuffer sqlCheck = new StringBuffer("select APPROVAL_SIGN_MANA_ID approvalSignManaId "
				+ " from APPROVAL_SIGN_MANAGEMENT where CONSTR_COMP_RE_MAP_ID = :constrCompReMapId");
		SQLQuery query = getSession().createSQLQuery(sqlCheck.toString());
		query.addScalar("approvalSignManaId", new LongType());
		query.setParameter("constrCompReMapId", id);
		List<Long> list = query.list();
		if (list == null || list.size() == 0) {
			return 0l;
		}
		return 1l;
	}

	// End ChuongNV
	/**
	 * filter
	 */
	public List<ConstrCompleteRecordsMapDTO> filter(ConstrCompleteRecordsMapCriteriaDTO criteria) {
		String sql = " 1 = 1 ";
		if (criteria.getSignEmployee() != null) {
			sql += " and b.EMPLOYEE_ID = :signEmployee ";
		}
		if (criteria.getStatus() != null && criteria.getStatus() != 4) {
			sql += " and a.status = :status ";
		}
		if (criteria.getCatFileInvoiceId() != null && criteria.getCatFileInvoiceId() != 0) {
			sql += " and a.CAT_FILE_INVOICE_ID = :catFileInvoiceId ";
		}
		if (criteria.getCreateDateFrom() != null && !criteria.getCreateDateFrom().isEmpty()) {
			sql += " and to_char(a.CREATED_DATE, 'YYYY-MM-DD') >= :createDateForm ";
		}
		if (criteria.getCreateDateTo() != null && !criteria.getCreateDateTo().isEmpty()) {
			sql += " and to_char(a.CREATED_DATE, 'YYYY-MM-DD') <= :createDateTo ";
		}
		sql = SQL_GET_BY_CONSTRUCTION_ID.replace("$CONDITION", sql);
		SQLQuery query = getSession().createSQLQuery(sql);

		query.addScalar("constrCompReMapId", new LongType());
		query.addScalar("dataTableName", new StringType());
		query.addScalar("dataTableId", new StringType());
		query.addScalar("dataTableIdValue", new LongType());
		query.addScalar("constructionId", new LongType());
		query.addScalar("levelOrder", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("invoiceName", new StringType());
		query.addScalar("invoiceCode", new StringType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("loginName", new StringType());
		query.addScalar("createdDateStr", new StringType());
		query.addScalar("employeeFullname", new StringType());
		// query.addScalar("createdUserName", new StringType());
		query.addScalar("signApprovalDate", new StringType());
		query.addScalar("signApprovalStatus", new StringType());
		// query.addScalar("catFileInvoiceId", new LongType());
		query.addScalar("signComments", new StringType());
		query.addScalar("typeConstruction", new LongType());
		query.addScalar("isExistProfile", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("roleName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordsMapDTO.class));
		query.setParameter("constructionId", criteria.getConstructionId());

		if (criteria.getSignEmployee() != null) {
			query.setParameter("signEmployee", criteria.getSignEmployee());
		}
		if (criteria.getStatus() != null && criteria.getStatus() != 4) {
			query.setParameter("status", criteria.getStatus());
		}
		if (criteria.getCatFileInvoiceId() != null && criteria.getCatFileInvoiceId() != 0) {
			query.setParameter("catFileInvoiceId", criteria.getCatFileInvoiceId());
		}
		if (criteria.getCreateDateFrom() != null && !criteria.getCreateDateFrom().isEmpty()) {
			query.setParameter("createDateForm", criteria.getCreateDateFrom());
		}
		if (criteria.getCreateDateTo() != null && !criteria.getCreateDateTo().isEmpty()) {
			query.setParameter("createDateTo", criteria.getCreateDateTo());
		}

		return query.list();
	}

	public List<ConstrCompleteRecordMapSubDTO> getListConstrCompleteRecordMapSubDTO(Long constrCompReMapId) {
			StringBuilder sql = new StringBuilder();
			sql.append("select " + "APPROVAL_SIGN_MANAGEMENT.COMMENTS signComments, "
					+ "APPROVAL_SIGN_MANAGEMENT.APPROVAL_DATE signApprovalDate, "
					+ "APPROVAL_SIGN_MANAGEMENT.APPROVAL_STATUS signApprovalStatus, "
					+ "CAT_EMPLOYEE.FULL_NAME employeeFullname " + "from APPROVAL_SIGN_MANAGEMENT "
					+ "inner join CAT_EMPLOYEE on CAT_EMPLOYEE.ID = APPROVAL_SIGN_MANAGEMENT.EMPLOYEE_ID " + "");

			sql.append("where CONSTR_COMP_RE_MAP_ID = :constrCompReMapId");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("signComments", new StringType());
			query.addScalar("signApprovalDate", new DateType());
			query.addScalar("signApprovalStatus", new StringType());
			query.addScalar("employeeFullname", new StringType());
			query.setParameter("constrCompReMapId", constrCompReMapId);

			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordMapSubDTO.class));

			return query.list();

	}

	/**
	 * Ham trinh ky Voffice 2.0: Lay thong tin nguoi trinh ky, danh sach ky,
	 * file ky tren giao dien Goi webservice trinh ky cua Voffice WS = 1: thanh
	 * cong; else: that bai
	 * 
	 * @param theSignCADTO
	 * @throws Exception
	 */
	@Transactional
	public void choiseCertification(TheSignCADTO theSignCADTO) throws Exception {

		// danh sach nguoi ky van ban
		List<CatEmployeeDTO> listEmployee = catEmployeeDAO.getEmployeeByListID(theSignCADTO.getStringEmployee());
		// thong tin trinh ky
		TheSignCAValuesTable theSignDto = getValues(theSignCADTO.getConstrCompReMapId());

		if (theSignCADTO.getUserId() != null) {
			// Lay thong tin Bang tac dong, IdName, IdValue cua bang
			String tableName = theSignDto.getDataTableName();
			String tableId = theSignDto.getDataTableID();
			Long tableValue = Long.valueOf(theSignDto.getDataTableIDValue()); // gia
																				// tri
																				// truong
																				// ID
																				// cua
																				// table
																				// trinh
																				// ky
			String code = theSignCADTO.getCode();

			String type = "0";

			String strEmailToDb = ""; // (email,printOrder; email,printOrder..);
										// Luu ben DB HCQT
			List<String> lstInputEmail = new ArrayList<String>();
			String email;
			int printOrder = 0;
			if (listEmployee != null) {
				String strEmailToVOfice = "";
				for (int i = 0; i < listEmployee.size(); i++) {
					email = listEmployee.get(i).getEmail().trim();
					strEmailToVOfice += email + ",";
					lstInputEmail.add(email);
					strEmailToDb += email + "," + printOrder++ + ";";
				}
				strEmailToVOfice = StringUtils.substring(strEmailToVOfice, 0, strEmailToVOfice.length() - 1);
				strEmailToDb = StringUtils.substring(strEmailToDb, 0, strEmailToDb.length() - 1);
				System.out.println(strEmailToVOfice);
			}
			

			// Lay userName, pass vOffice
			String vOfficeName = theSignCADTO.getUser();
			String vOfficePass = theSignCADTO.getPassword();
			// transactionCode chinh la ID bang document_CA
			Long transactionCode = ((BigDecimal) getSession()
					.createSQLQuery("Select Document_Ca_Seq.Nextval From Dual ").uniqueResult()).longValue();

			Long status = 1L;

			try {
				// Goi service ben vOffice, chuyen cac tham so tuong ung
				// Tao service ket noi
				Vo2AutoSignSystemImplService sv = new Vo2AutoSignSystemImplService(new URL(ca_wsUrl));
				Vo2AutoSignSystemImpl service = sv.getVo2AutoSignSystemImplPort();

				// Set timeout params
				int connectionTimeOutInMs = 20000; // Thoi gian timeout 10s

				Map<String, Object> requestContext = ((BindingProvider) service).getRequestContext();
				requestContext.put("com.sun.xml.internal.ws.connect.timeout", connectionTimeOutInMs);
				requestContext.put("com.sun.xml.internal.ws.request.timeout", connectionTimeOutInMs);
				requestContext.put("com.sun.xml.ws.request.timeout", connectionTimeOutInMs);
				requestContext.put("com.sun.xml.ws.connect.timeout", connectionTimeOutInMs);

				// Truyen cac tham so cho webservice Voffice
				KttsVofficeCommInpuParam param = new KttsVofficeCommInpuParam();
				param.setAppCode(ca_appCode); // tên app account đăng nhập nhap
												// tren giao dien trinh ky
				param.setAppPass(ca_appPass); // mật khẩu appaccount đăng nhập
												// nhap tren giao dien trinh ky
				param.setAccountName(vOfficeName);

				PassTranformer.setInputKey(ca_encrypt_key);// set key to encrypt
															// password voffice
				param.setAccountPass(PassTranformer.encrypt(vOfficePass));// ma
																			// hoa
																			// password
				param.setTransCode(String.valueOf(transactionCode));
				param.setSender(ca_sender); // tên hệ thống trình kí văn bản. ->
											// QLDTKTTS
				param.setRegisterNumber(code); // ma bien ban trinh ky = Ma BB
												// ben HCQT
				param.setDocTitle(code);
				param.setIsCanVanthuXetduyet(false);// Khong can van thu xet
													// duyet
				param.setHinhthucVanban(14L); // Bien Ban

				// Danh sach file
				// Lay file export pdf (attach hoac export tu man hinh bien ban
				// trinh ky)
				/*List<FileAttachTranfer> lstFileAttach ;*/
				List<FileAttachTranfer> lstFileAttach = getListFileAttach(tableName, tableId, tableValue, type, theSignCADTO);
				param.setLstFileAttach(lstFileAttach);

				// Lay danh sach user ky theo dinh nghia cua Voffice
				List<Vof2EntityUser> vofficeUserLstParam = new ArrayList<Vof2EntityUser>();

				// get list user of voffice
				List<Vof2EntityUser> listVofficeUser = service.getListVof2UserByMail(lstInputEmail);

				Vof2EntityUser user;
				Long index = 1L;

				// Lay ra danh sach user ky theo thu tu
				for (String userEmail : lstInputEmail) {
					user = getVofficeUser(listVofficeUser, userEmail);
					if (user != null) {
						user.setSignImageIndex(index++);
						vofficeUserLstParam.add(user);
					}
				}

				// truyen param danh sach user ky
				param.setLstUserVof2(vofficeUserLstParam);

				// goi webservice trinh ky Voffice
				status = service.vo2RegDigitalDocByEmail(param);
				System.out.println(
						"End call KTTSAutoSignSystemImplService : " + transactionCode + "|| status :" + status);

			} catch (Exception e) {
				status = 99L; // Unknown error
				LOGGER.error(e.getMessage(), e);
			}

			// Sau khi trinh ky xong, update ban ghi vua trinh ky, set status_ca
			// = 1
			if (status == 1) { // Success
				// Update trang thai cua van ban : Chua ky -> Dang ky
				String sqlUpdate = "Update " + tableName + " set status_ca = ?, document_ca_id = ? where " + tableId
						+ " = ? ";
				getSession().createSQLQuery(sqlUpdate).setParameter(0, 1L).setParameter(1, transactionCode)
						.setParameter(2, tableValue).executeUpdate();

				// Update ban ghi dang ky trong constr_complete_record_map
				String sqlUpdateRecord = "Update CONSTR_COMPLETE_RECORDS_MAP set level_order = ?, status = ?  where CONSTR_COMP_RE_MAP_ID "
						+ " = ? ";
				getSession().createSQLQuery(sqlUpdateRecord).setParameter(0, 1L).setParameter(1, 1L).setParameter(2, Long.valueOf(theSignCADTO.getConstrCompReMapId())).executeUpdate();
				
				// Xoa cac bien ban ky cu trong document_ca
				String sqlUpdateDocumentCa = "Update Document_Ca set is_active = 0 where is_active = 1 and Table_Name = ? and Table_Id = ? and Table_Id_Value = ? ";
				getSession().createSQLQuery(sqlUpdateDocumentCa).setParameter(0, tableName).setParameter(1, tableId)
						.setParameter(2, tableValue).executeUpdate();

				// Insert vao DocumentCa
				// 0715 Bo sung them 4 truong thong tin
				
				// Chua biet truong group_Id la truong gi nen khi update se = null. Neu lay duoi client chuyen len thi thong qua TheSignCADTO co the lay duoc
				String sqlInsert = "Insert Into Document_Ca (Document_Ca_Id, Table_Name, Table_Id, Table_Id_Value,"
						+ " Status_Ca, Email, Is_Active, VOFFICE_NAME, CREATED_ID, CREATED_DATE, GROUP_ID, SignFlowId) "
						+ " Values (?,?,?,?,?,?,?, ?, ?, ?, ?, ?) ";
				
				getSession().createSQLQuery(sqlInsert).setParameter(0, transactionCode).setParameter(1, tableName)
						.setParameter(2, tableId).setParameter(3, tableValue).setParameter(4, 1L)
						.setParameter(5, strEmailToDb).setParameter(6, 1L).setParameter(7, vOfficeName)
						.setParameter(8, theSignCADTO.getUserId()).setParameter(9, new Date()).setParameter(10, theSignCADTO.getGroupId())
						.setParameter(11, 1L).executeUpdate();
			}

		}
	}

	/**
	 * Check user is exist in userList
	 * 
	 * @param user
	 * @param userList
	 * @return
	 */
	private boolean isExistInList(Vof2EntityUser user, List<Vof2EntityUser> userList) {
		if (user == null || userList == null)
			return false;
		for (Vof2EntityUser _user : userList) {
			if (_user.getUserId().equals(user.getUserId()))
				return true;
		}
		return false;
	}

	/**
	 * Lay ra user tu danh sach user goi tu WS cua Voffice theo email
	 * 
	 * @param userList
	 * @param email
	 * @return instance of Vof2EntityUser
	 */
	private Vof2EntityUser getVofficeUser(List<Vof2EntityUser> userList, String email) {
		if (userList == null)
			return null;
		for (Vof2EntityUser user : userList) {
			if (user.getStrEmail().trim().equalsIgnoreCase(email.trim()))
				return user;
		}
		return null;
	}

	private static java.util.Set<String> findDuplicates(List<String> listContainingDuplicates) {

		final java.util.Set<String> setToReturn = new HashSet<String>();
		final java.util.Set<String> setTemp = new HashSet<String>();

		for (String yourInt : listContainingDuplicates) {
			if (!setTemp.add(yourInt)) {
				setToReturn.add(yourInt);
			}
		}
		return setToReturn;
	}

	/**
	 * Ham lay ra danh sach file trinh ky. Truong hop 1: export tu bien ban.
	 * Truonng hop 2: lay tu file attach
	 * 
	 * @param tableName
	 * @param tableId
	 * @param tableValue
	 * @param type
	 * @param theSignCA
	 * @return danh sach file trinh ky theo dinh dang Voffice dinh nghia
	 * @throws Exception
	 */
	private List<FileAttachTranfer> getListFileAttach(String tableName, String tableId, Long tableValue, String type,
			TheSignCADTO theSignCA) throws Exception {
		List<FileAttachTranfer> lstFileAttach = new ArrayList<FileAttachTranfer>();
		FileAttachTranfer file ;
		String pathFileContract;
		List<UtilAttachedDocumentsDTO> colAttachment = new ArrayList<UtilAttachedDocumentsDTO>();

		if ("CONSTR_ORGANIZATION_PLAN".equals(tableName)) { 
			colAttachment = utilAttachedDocumentsDAO.getListByParentIdAndType(Long.valueOf(theSignCA.getConstrCompReMapId()));
		} else if ("COMPLETION_DRAWING".equals(tableName)) {
			colAttachment = utilAttachedDocumentsDAO.getListByParentIdAndTypeDrawing(Long.valueOf(theSignCA.getConstrCompReMapId()));
		}
		// lay ra danh sach file attach: Hien dang fix cung theo type cua phuong
		// an to chuc thi cong: chuong xem lai
		if(!"CONSTR_ORGANIZATION_PLAN".equals(tableName) && !"COMPLETION_DRAWING".equals(tableName)){
		theSignCA.setPath(UEncrypt.decryptFileUploadPath(theSignCA.getPath()));
		}
		byte[] array = null;
		pathFileContract = folder_upload + File.separatorChar + theSignCA.getPath();
		pathFileContract = pathFileContract.replaceAll("\\.\\.", "");
		try {
			array = FileUtils.readFileToByteArray(new File(pathFileContract));
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		if (array != null) {
			file = new FileAttachTranfer();
			file.setAttachBytes(array);
			file.setFileName(theSignCA.getNameFile());
			file.setPath(pathFileContract);

			file.setFileSign(1); // 1: file ky chinh; 2: file phu
			lstFileAttach.add(file);
		}

		if (colAttachment != null) {
			UtilAttachedDocumentsDTO utilAttachedDocumentsDTO;
			for (int i = 0; i < colAttachment.size(); i++) {
				utilAttachedDocumentsDTO = colAttachment.get(i);
				file = new FileAttachTranfer();
				pathFileContract = folder_upload  +utilAttachedDocumentsDTO.getDocumentPath();
				pathFileContract = pathFileContract.replaceAll("\\.\\.", "");
				array = null;
				try {
					array = FileUtils.readFileToByteArray(new File(pathFileContract));
					
				} catch (Exception ex) {
					LOGGER.error(ex.getMessage(), ex);
				}
				file.setAttachBytes(array);
				file.setFileName(utilAttachedDocumentsDTO.getDocumentName());
				file.setPath(pathFileContract);
				file.setFileSign(1); // file ky chinh. Cho nay neu co file phu
										// thi set = 2. Chuong xem lai

				// if (utilAttachedDocumentsDTO.getCatFileInvoiceId() != null) {
				// file.setFileSign(2);
				// } else {
				// file.setFileSign(1);
				// }
				lstFileAttach.add(file);
			}
		}
		return lstFileAttach;
	}

	/*public List<ConstrCompleteRecordsMapDTO> getNotify(Long userId) {
		String sql = "select count(DATA_TABLE_NAME) countsl, " + " DATA_TABLE_NAME dataTableName,"
				+ " constr.CONSTRUCT_ID constructionId, " + " constr.CONSTRT_NAME constructName, "
				+ " constr.CONSTRUCTOR_NAME constructorName, " + " constr.PROVINCE_NAME provinceName, "
				+ " constr.CONSTR_TYPE_NAME constrTypeName, " + " constr.CONTRACT_CODE contractCode, "
				+ " constr.CONTRACT_NAME contractName, " + " constr.CONSTRT_ADDRESS constrtAddress, "
				+ " constr.SIGNED_DATE signed_date, " + " constr.CONTRACT_ID contractId, "
				+ " constr.CONSTRT_CODE constrtCode " + " from CONSTR_COMPLETE_RECORDS_MAP recordmap "
				+ " inner join APPROVAL_SIGN_MANAGEMENT approval on approval.CONSTR_COMP_RE_MAP_ID=recordmap.CONSTR_COMP_RE_MAP_ID "
				+ " inner join V_CONSTRUCTION_HCQT constr on constr.CONSTRUCT_ID=recordmap.CONSTRUCTION_ID "
				+ " where approval.APPROVAL_STATUS = 0 and approval.EMPLOYEE_ID=:userId "
				+ " group by(DATA_TABLE_NAME,constr.CONSTRUCT_ID, constr.CONSTRT_NAME,constr.CONSTRUCTOR_NAME,"
				+ "constr.PROVINCE_NAME,constr.CONSTR_TYPE_NAME,constr.CONSTRT_CODE,constr.CONTRACT_CODE,"
				+ " constr.CONTRACT_NAME,constr.CONSTRT_ADDRESS,constr.SIGNED_DATE,constr.CONTRACT_ID)"
				+ " order by recordmap.DATA_TABLE_NAME asc";
		SQLQuery query = getSession().createSQLQuery(sql);

		query.addScalar("dataTableName", new StringType());
		query.addScalar("constructName", new StringType());
		query.addScalar("constructionId", new LongType());
		query.addScalar("countsl", new LongType());
		query.addScalar("constructorName", new StringType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("constrTypeName", new StringType());
		query.addScalar("constrtCode", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("contractName", new StringType());
		query.addScalar("constrtAddress", new StringType());
		query.addScalar("signed_date", new DateType());
		query.addScalar("contractId", new LongType());
		query.setParameter("userId", userId);

		query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordsMapDTO.class));

		return query.list();
	}*/
	
	public List<ConstrCompleteRecordsMapDTO> getNotify(Long userId) {
		String sql = " select count(*) counts ,a.data_table_name dataTableName,a.file_invoice_name fileInvoiceName,cc.constrt_name constructName,cc.CONSTRT_CODE constrtCode, "
				+ " cc.CONSTRUCT_ID constructionId, "
				+ " cc.PROVINCE_NAME provinceName, "
				+ " cc.CONSTR_TYPE_NAME constrTypeName, "
				+ " cc.CONTRACT_CODE contractCode, "
				+ " cc.CONTRACT_NAME contractName, "
				+ " cc.CONSTRT_ADDRESS constrtAddress, "
				+ " cc.SIGNED_DATE signed_date, "
				+ " cc.CONTRACT_ID contractId "
				+ " from CAT_FILE_INVOICE a "
				+ " join CONSTR_COMPLETE_RECORDS_MAP  b on a.cat_file_invoice_id=b.cat_file_invoice_id "
				+ " join APPROVAL_SIGN_MANAGEMENT c on (c.constr_comp_re_map_id=b.constr_comp_re_map_id and c.approval_order=b.leveL_order) "
				+ " join user_employee  ue on ue.employee_id=c.employee_id "
				+ " join sys_user su on su.user_id=ue.user_id "
				+ " join V_CONSTRUCTION_HCQT cc on cc.construct_id=b.construction_id  "
				+ " where su.USER_ID=:userId and c.approval_status=0 and cc.is_active=1 "
				+ " group by a.data_table_name,a.file_invoice_name,cc.constrt_name,cc.CONSTRT_CODE,cc.CONSTRUCT_ID,cc.PROVINCE_NAME,cc.CONSTR_TYPE_NAME,cc.CONTRACT_CODE,cc.CONTRACT_NAME,cc.CONSTRT_ADDRESS,cc.SIGNED_DATE,cc.CONTRACT_ID ";
		SQLQuery query = getSession().createSQLQuery(sql);

		query.addScalar("counts", new LongType());
		query.addScalar("dataTableName", new StringType());
		query.addScalar("fileInvoiceName", new StringType());
		query.addScalar("constructName", new StringType());
		query.addScalar("constrtCode", new StringType());
		query.addScalar("constructionId", new LongType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("constrTypeName", new StringType());
		query.addScalar("contractCode", new StringType());
		query.addScalar("contractName", new StringType());
		query.addScalar("constrtAddress", new StringType());
		query.addScalar("signed_date", new DateType());
		query.addScalar("contractId", new LongType());
		
		query.setParameter("userId", userId);

		query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordsMapDTO.class));

		return query.list();
	
	}

}
