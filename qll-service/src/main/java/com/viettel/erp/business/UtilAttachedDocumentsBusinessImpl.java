package com.viettel.erp.business;
 
import com.viettel.erp.bo.UtilAttachedDocumentsBO;
import com.viettel.erp.dao.UtilAttachedDocumentsDAO;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("utilAttachedDocumentsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UtilAttachedDocumentsBusinessImpl extends BaseFWBusinessImpl<UtilAttachedDocumentsDAO,UtilAttachedDocumentsDTO, UtilAttachedDocumentsBO> implements UtilAttachedDocumentsBusiness {

    @Autowired
    private UtilAttachedDocumentsDAO utilAttachedDocumentsDAO;
     
    @Value("${constrOrganization.attachTypeValue}")
   	private Long attachTypeValue;
    
    
    public UtilAttachedDocumentsBusinessImpl() {
        tModel = new UtilAttachedDocumentsBO();
        tDAO = utilAttachedDocumentsDAO;
    }

    @Override
    public UtilAttachedDocumentsDAO gettDAO() {
        return utilAttachedDocumentsDAO;
    }

    @Override
    public long count() {
        return utilAttachedDocumentsDAO.count("UtilAttachedDocumentsBO", null);
    }

    @Override
    public Long insert(String documentName,Long parentId, String documentPath, Long type){
    	UtilAttachedDocumentsDTO utilAttachedDocumentsDTO = new UtilAttachedDocumentsDTO();
    	utilAttachedDocumentsDTO.setDocumentName(documentName);
    	utilAttachedDocumentsDTO.setDocumentPath(documentPath);
    	utilAttachedDocumentsDTO.setType(type);
    	utilAttachedDocumentsDTO.setParentId(parentId);
    	utilAttachedDocumentsDTO.setCreatedDate(new Date());
    	
    	return save(utilAttachedDocumentsDTO);
    }

	@Override
	public void updateUtilByParentIdAndType(String documentName, Long parentId, String documentPath, Long type) {
		utilAttachedDocumentsDAO.updateUtilByParentIdAndType(documentName,parentId, documentPath, type);
	}
    
	public UtilAttachedDocumentsDTO findByParentIdAndType(Long parentId, Long type){
		return utilAttachedDocumentsDAO.findByParentIdAndType(parentId, type);
	}

	@Override
	public List<UtilAttachedDocumentsDTO> getListByParentIdAndType(Long parentId) {
		//Long type = attachTypeValue;
		//String type = "(9453,9454)";
		List<UtilAttachedDocumentsDTO> ls = utilAttachedDocumentsDAO.getListByParentIdAndType(parentId);
		return ls;
	}

	@Override
	public void deleteDocument(List<String> listAttachId) {
		utilAttachedDocumentsDAO.deleteDocument(listAttachId);		
	}

	@Override
	public boolean checkType(String attachId) {
		return utilAttachedDocumentsDAO.checkType(attachId);
	}

	@Override
	public List<UtilAttachedDocumentsDTO> getListByParentIdCA(Long parentId) {
		// TODO Auto-generated method stub
		List<UtilAttachedDocumentsDTO> ls = utilAttachedDocumentsDAO.getListByParentIdCA(parentId);
		return ls;
	}

	@Override
	public List<UtilAttachedDocumentsDTO> getListOrganizationByParentId(Long parentId) {
		// TODO Auto-generated method stub
		List<UtilAttachedDocumentsDTO> ls = utilAttachedDocumentsDAO.getListOrganizationByParentId(parentId);
		return ls;
	}

	@Override
	public List<UtilAttachedDocumentsDTO> getListByParentIdAndTypeOrgan(Long parentId) {
		// TODO Auto-generated method stub
		List<UtilAttachedDocumentsDTO> ls = utilAttachedDocumentsDAO.getListByParentIdAndTypeOrgan(parentId);
		return ls;
	}
}
