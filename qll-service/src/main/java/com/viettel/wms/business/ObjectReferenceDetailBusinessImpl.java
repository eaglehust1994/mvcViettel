package com.viettel.wms.business;
 
import com.viettel.wms.bo.ObjectReferenceDetailBO;
import com.viettel.wms.dao.ObjectReferenceDetailDAO;
import com.viettel.wms.dto.ObjectReferenceDetailDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("objectReferenceDetailBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ObjectReferenceDetailBusinessImpl extends BaseFWBusinessImpl<ObjectReferenceDetailDAO,ObjectReferenceDetailDTO, ObjectReferenceDetailBO> implements ObjectReferenceDetailBusiness {

    @Autowired
    private ObjectReferenceDetailDAO objectReferenceDetailDAO;
    

     
    public ObjectReferenceDetailBusinessImpl() {
        tModel = new ObjectReferenceDetailBO();
        tDAO = objectReferenceDetailDAO;
    }

    @Override
    public ObjectReferenceDetailDAO gettDAO() {
        return objectReferenceDetailDAO;
    }

    @Override
    public long count() {
        return objectReferenceDetailDAO.count("ObjectReferenceDetailBO", null);
    }


    

    
}
