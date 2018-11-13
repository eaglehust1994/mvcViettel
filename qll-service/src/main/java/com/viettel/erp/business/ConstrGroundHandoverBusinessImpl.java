package com.viettel.erp.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.erp.bo.ConstrGroundHandoverBO;
import com.viettel.erp.dao.ConstrGroundHandoverDAO;
import com.viettel.erp.dto.ConstrGroundHandoverDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("constrGroundHandoverBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrGroundHandoverBusinessImpl extends BaseFWBusinessImpl<ConstrGroundHandoverDAO,ConstrGroundHandoverDTO, ConstrGroundHandoverBO> implements ConstrGroundHandoverBusiness {

    @Autowired
    private ConstrGroundHandoverDAO constrGroundHandoverDAO;
    

     
    public ConstrGroundHandoverBusinessImpl() {
        tModel = new ConstrGroundHandoverBO();
        tDAO = constrGroundHandoverDAO;
    }

    @Override
    public ConstrGroundHandoverDAO gettDAO() {
        return constrGroundHandoverDAO;
    }

    @Override
    public long count() {
        return constrGroundHandoverDAO.count("ConstrGroundHandoverBO", null);
    }

	@Override
	public List<ConstrGroundHandoverDTO> getAllConstrGroundHandover(ConstrGroundHandoverDTO dto) {
		return constrGroundHandoverDAO.getAllConstrGroundHandover(dto);
	}

	
	@Override
	public String getCode(String tableName,String value){
		return constrGroundHandoverDAO.getCode(tableName,value);
	}
    
	@Override
	public boolean checkStatusDatabase(Long constrGroundHandoverId){
		return constrGroundHandoverDAO.checkStatusDatabase(constrGroundHandoverId);
	}
	
	
	 @Override
		public String delete(List<Long> ids, String name, String string) {
			return constrGroundHandoverDAO.delete(ids,name,string);
		}
    
	 
	 @Override
		public ConstrGroundHandoverDTO getAllConstrGroundHandoverById(ConstrGroundHandoverDTO dto){
		 return constrGroundHandoverDAO.getAllConstrGroundHandoverById(dto);
	 }
	 
	 @Override
		public Long appro(approDTO obj) {
			// TODO Auto-generated method stub
			return constrGroundHandoverDAO.appro(obj);
		}
	    //end tungpv

	@Override
	public boolean deleteOne(Long id) {
		// TODO Auto-generated method stub
		return constrGroundHandoverDAO.deleteOneEntity(id);
	}
}
