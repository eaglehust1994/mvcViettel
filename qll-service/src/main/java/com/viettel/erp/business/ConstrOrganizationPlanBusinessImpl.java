package com.viettel.erp.business;

import com.espringtran.compressor4j.compressor.FileCompressor;
import com.viettel.erp.bo.ConstrOrganizationPlanBO;
import com.viettel.erp.dao.ConstrOrganizationPlanDAO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.ConstrOrganizationPlanDTO;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.erp.utils.Folder;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service("constrOrganizationPlanBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrOrganizationPlanBusinessImpl
		extends BaseFWBusinessImpl<ConstrOrganizationPlanDAO, ConstrOrganizationPlanDTO, ConstrOrganizationPlanBO>
		implements ConstrOrganizationPlanBusiness {
	
	static Logger LOGGER = LoggerFactory.getLogger(ConstrOrganizationPlanBusinessImpl.class);

	@Autowired
	private ConstrOrganizationPlanDAO constrOrganizationPlanDAO;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	@Value("${constrOrganization.folder}")
	private String folder;

	@Value("${constrOrganization.attachTypeKey}")
	private Long attachTypeKey;

	@Value("${constrOrganization.attachTypeValue}")
	private Long attachTypeValue;

	@Autowired
	UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;

	public ConstrOrganizationPlanBusinessImpl() {
		tModel = new ConstrOrganizationPlanBO();
		tDAO = constrOrganizationPlanDAO;
	}

	@Override
	public ConstrOrganizationPlanDAO gettDAO() {
		return constrOrganizationPlanDAO;
	}

	@Override
	public long count() {
		return constrOrganizationPlanDAO.count("ConstrOrganizationPlanBO", null);
	}

	@Override
	public List<ConstrOrganizationPlanDTO> getAllConstrOrganizationPlan(ConstrOrganizationPlanDTO dto) {
		return constrOrganizationPlanDAO.getAllConstrOrganizationPlan(dto);
	}

	@Override
	public void deleteConstrOrganizationPlan(List<String> listConstrOrgPlanId) {
		constrOrganizationPlanDAO.deleteConstrOrganizationPlan(listConstrOrgPlanId);
	}

	@Override
	public List<CatEmployeeDTO> getEmployee(String contructID) {
		return constrOrganizationPlanDAO.getEmployee(contructID);
	}

	@Override
	public String autoGenCodeConstrOrganizationPlan() {
		return constrOrganizationPlanDAO.autoGenCodeConstrOrganizationPlan();
	}

	@Override
	public boolean checkStatusDatabase(String constrConstrOrganizationPlan) {
		return constrOrganizationPlanDAO.checkStatusDatabase(constrConstrOrganizationPlan);
	}

	@Override
	@Transactional
	public Long addConstrOrganizationPlan(ConstrOrganizationPlanDTO obj) throws Exception {
		//String documentPath = Folder.getFolderSubfix(folder);
		Date date = new Date();
		Long id = 0l;
		Long constructionId = obj.getConstructId();

		if ("creat".equals(obj.getCreatOrUpdate())) {
			obj.setCreatedDate(date);
			obj.setStatusCa(0L);
			obj.setIsActive(1l);
			String code = this.autoGenCodeConstrOrganizationPlan();
			obj.setCode(code);
			id = this.save(obj);
			//documentPath = UEncrypt.decryptFileUploadPath(obj.getListDocumentName().get(0));
			String documentName2 = obj.getListDocumentNameEx().get(0);
			utilAttachedDocumentsBusinessImpl.insert(documentName2, id, UEncrypt.decryptFileUploadPath(obj.getListDocumentName().get(0)), attachTypeKey);
			if (obj.getListDocumentName().size() > 1) {
				for (int i = 1; i < obj.getListDocumentName().size(); i++) {
					/*documentPath = Folder.getFolderSubfix(folder);
					documentPath = documentPath + File.separatorChar + obj.getListDocumentName().get(i);*/
					String documentName = obj.getListDocumentNameEx().get(i);
					utilAttachedDocumentsBusinessImpl.insert(documentName, id, UEncrypt.decryptFileUploadPath(obj.getListDocumentName().get(i)),
							attachTypeValue);
				}
			}
			try {
				constrCompleteRecordsMapBusinessImpl.insert(constructionId, "CONSTR_ORGANIZATION_PLAN",
						"CONSTR_ORG_PLAN_ID", id, obj.getCreatedUserId(), code);
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
			}
		} else if ("update".equals(obj.getCreatOrUpdate())) {
			if (this.checkStatusDatabase(obj.getConstrOrgPlanId() + "")) {
				return new Long(0);
			}
			obj.setIsActive(1l);
			constrOrganizationPlanDAO.updateDataTable(obj);
			id = obj.getConstrOrgPlanId();
			//documentPath = documentPath + File.separatorChar + obj.getListDocumentName().get(0);
			String documentName = obj.getListDocumentNameEx().get(0);
			utilAttachedDocumentsBusinessImpl.updateUtilByParentIdAndType(documentName,
					obj.getConstrOrgPlanId(), UEncrypt.decryptFileUploadPath(obj.getListDocumentName().get(0)), attachTypeKey);
			if (obj.getListDocumentName().size() > 1) {
				for (int i = 1; i < obj.getListDocumentName().size(); i++) {
					/*documentPath = Folder.getFolderSubfix(folder);
					documentPath = documentPath + File.separatorChar + obj.getListDocumentName().get(i);*/
					String documentName1 = obj.getListDocumentNameEx().get(i);
					utilAttachedDocumentsBusinessImpl.insert(documentName1, id, UEncrypt.decryptFileUploadPath(obj.getListDocumentName().get(i)),
							attachTypeValue);
				}
			}
		}
		return id;
	}

	@Override
	public List<ConstrOrganizationPlanDTO> getAllConstrOrganizationPlanChild(ConstrOrganizationPlanDTO dto) {
		// TODO Auto-generated method stub
		return constrOrganizationPlanDAO.getAllConstrOrganizationPlanChild(dto);
	}

}
