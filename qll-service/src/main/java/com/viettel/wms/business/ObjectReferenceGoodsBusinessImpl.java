package com.viettel.wms.business;
 
import com.viettel.wms.bo.ObjectReferenceGoodsBO;
import com.viettel.wms.dao.ObjectReferenceGoodsDAO;
import com.viettel.wms.dto.ObjectReferenceDetailDTO;
import com.viettel.wms.dto.ObjectReferenceGoodsDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("objectReferenceGoodsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ObjectReferenceGoodsBusinessImpl extends BaseFWBusinessImpl<ObjectReferenceGoodsDAO,ObjectReferenceGoodsDTO, ObjectReferenceGoodsBO> implements ObjectReferenceGoodsBusiness {

    @Autowired
    private ObjectReferenceGoodsDAO objectReferenceGoodsDAO;
    

     
    public ObjectReferenceGoodsBusinessImpl() {
        tModel = new ObjectReferenceGoodsBO();
        tDAO = objectReferenceGoodsDAO;
    }

    @Override
    public ObjectReferenceGoodsDAO gettDAO() {
        return objectReferenceGoodsDAO;
    }

    @Override
    public long count() {
        return objectReferenceGoodsDAO.count("ObjectReferenceGoodsBO", null);
    }

	@Override
	public List<ObjectReferenceGoodsDTO> getGoodsInfoBeforeWarrantyByCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceGoodsDAO.getGoodsInfoBeforeWarrantyByCode(code);
	}

	@Override
	public List<ObjectReferenceGoodsDTO> getGoodsInfoAfterWarrantyByCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceGoodsDAO.getGoodsInfoAfterWarrantyByCode(code);
	}

	@Override
	public List<ObjectReferenceGoodsDTO> getGoodsInfoFromConstructionByCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceGoodsDAO.getGoodsInfoFromConstructionByCode(code);
	}

	@Override
	public List<ObjectReferenceGoodsDTO> getGoodsInfoFromDepartmentByCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceGoodsDAO.getGoodsInfoFromDepartmentByCode(code);
	}

	@Override
	public List<ObjectReferenceGoodsDTO> getGoodsInfoFromLoanByCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceGoodsDAO.getGoodsInfoFromLoanByCode(code);
	}

    

    
}
