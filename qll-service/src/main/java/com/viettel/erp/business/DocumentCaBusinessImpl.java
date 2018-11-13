package com.viettel.erp.business;
 
import com.viettel.erp.bo.DocumentCaBO;
import com.viettel.erp.dao.DocumentCaDAO;
import com.viettel.erp.dto.DocumentCaDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("documentCaBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DocumentCaBusinessImpl extends BaseFWBusinessImpl<DocumentCaDAO,DocumentCaDTO, DocumentCaBO> implements DocumentCaBusiness {

    @Autowired
    private DocumentCaDAO documentCaDAO;
    

     
    public DocumentCaBusinessImpl() {
        tModel = new DocumentCaBO();
        tDAO = documentCaDAO;
    }

    @Override
    public DocumentCaDAO gettDAO() {
        return documentCaDAO;
    }

    @Override
    public long count() {
        return documentCaDAO.count("DocumentCaBO", null);
    }

    

    
}
