package com.viettel.wms.business;
 
import com.viettel.wms.bo.ObjectReferenceBO;
import com.viettel.wms.dao.ObjectReferenceDAO;
//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.ObjectReferenceDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("objectReferenceBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ObjectReferenceBusinessImpl extends BaseFWBusinessImpl<ObjectReferenceDAO,ObjectReferenceDTO, ObjectReferenceBO> implements ObjectReferenceBusiness {

    @Autowired
    private ObjectReferenceDAO objectReferenceDAO;
    

     
    public ObjectReferenceBusinessImpl() {
        tModel = new ObjectReferenceBO();
        tDAO = objectReferenceDAO;
    }

    @Override
    public ObjectReferenceDAO gettDAO() {
        return objectReferenceDAO;
    }

    @Override
    public long count() {
        return objectReferenceDAO.count("ObjectReferenceBO", null);
    }

    public ObjectReferenceDTO getDataKCS(ObjectReferenceDTO obj) {
		return objectReferenceDAO.getDataKCS(obj);
	}

    //Tim kiem hang hoa nhap truoc BHSC
	@Override
	public List<String> getGoodsInfoBeforeWarrantyCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceDAO.getGoodsInfoBeforeWarrantyCode(code);
	}

	//Tim kiem hang hoa nhap sau BHSC
	@Override
	public List<String> getGoodsInfoAfterWarrantyCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceDAO.getGoodsInfoAfterWarrantyCode(code);
	}

	//Tim kiem hang hoa nhap thu hoi tu cong trinh
	@Override
	public List<String> getGoodsInfoFromConstructionCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceDAO.getGoodsInfoFromConstructionCode(code);
	}

	//Tim kiem hang hoa nhap thu hoi tu don vi
	@Override
	public List<String> getGoodsInfoFromDepartmentCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceDAO.getGoodsInfoFromDepartmentCode(code);
	}

	//Tim kiem hang hoa nhap thu hoi tu hang cho muon
	@Override
	public List<String> getGoodsInfoFromLoanCode(String code) {
		// TODO Auto-generated method stub
		return objectReferenceDAO.getGoodsInfoFromLoanCode(code);
	}

    
}
