package com.viettel.wms.business;
 
import com.viettel.wms.bo.ObjectReferenceGoodsDetailBO;
import com.viettel.wms.dao.ObjectReferenceGoodsDetailDAO;
import com.viettel.wms.dto.ObjectReferenceGoodsDetailDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("objectReferenceGoodsDetailBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ObjectReferenceGoodsDetailBusinessImpl extends BaseFWBusinessImpl<ObjectReferenceGoodsDetailDAO,ObjectReferenceGoodsDetailDTO, ObjectReferenceGoodsDetailBO> implements ObjectReferenceGoodsDetailBusiness {

    @Autowired
    private ObjectReferenceGoodsDetailDAO objectReferenceGoodsDetailDAO;
    

     
    public ObjectReferenceGoodsDetailBusinessImpl() {
        tModel = new ObjectReferenceGoodsDetailBO();
        tDAO = objectReferenceGoodsDetailDAO;
    }

    @Override
    public ObjectReferenceGoodsDetailDAO gettDAO() {
        return objectReferenceGoodsDetailDAO;
    }

    @Override
    public long count() {
        return objectReferenceGoodsDetailDAO.count("ObjectReferenceGoodsDetailBO", null);
    }

	@Override
	public List<ObjectReferenceGoodsDetailDTO> getGoodsDetail(ObjectReferenceGoodsDetailDTO obj) {
		// TODO Auto-generated method stub
		return objectReferenceGoodsDetailDAO.getGoodsDetail(obj);
	}

    

    
}
