package com.viettel.wms.business;
 
import com.viettel.wms.bo.TaxBO;
import com.viettel.wms.dao.TaxDAO;
import com.viettel.wms.dto.ReasonDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.wms.dto.TaxDTO;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("taxBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TaxBusinessImpl extends BaseFWBusinessImpl<TaxDAO,TaxDTO, TaxBO> implements TaxBusiness {

    @Autowired
    private TaxDAO taxDAO;
    

     
    public TaxBusinessImpl() {
        tModel = new TaxBO();
        tDAO = taxDAO;
    }

    @Override
    public TaxDAO gettDAO() {
        return taxDAO;
    }

    @Override
    public long count() {
        return taxDAO.count("TaxBO", null);
    }

    @Override
	public long getTotal() {
		return taxDAO.count("AdClientBO", null);
	}
    
	public DataListDTO doSearch(TaxDTO obj){
		   List<TaxDTO> ls = taxDAO.doSearch(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		   return data;
	   }
	public DataListDTO doSearchBytax(TaxDTO obj){
		   List<TaxDTO> ls = taxDAO.doSearchBytax(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		   return data;
	   }
	   
	   public Boolean checkCode(String code,Long taxId){
		   TaxDTO obj = taxDAO.getbycode(code);
		   
		   if(taxId == null){
			   if(obj==null){
				   return true;
			   } else {
				   return false;
			   }
		   } else {
			   if(obj==null){
				   return true;
			   } else if(obj!=null && obj.getTaxId().longValue()==taxId) {
				   return true;
			   } else {
				   return false;
			   }
		   }
		   
	   }
	   public Long updateTax(TaxDTO obj,KttsUserSession objUser) throws Exception{
			if(!objUser.getSysUserId().equals(obj.getCreatedBy())){
				throw new  IllegalArgumentException("Người dùng hiện tại không có quyền sửa bản ghi này !");
		}
		
		boolean check=checkCode(obj.getCode(),  obj.getTaxId());
		if(!check){
			throw new  IllegalArgumentException("Mã thuế đã tồn tại !");
		}
		return taxDAO.updateObject(obj.toModel());
	} 
	   public Long deleteStock(TaxDTO obj,KttsUserSession objUser){
			if(!objUser.getSysUserId().equals(obj.getCreatedBy())){
				throw new  IllegalArgumentException("Người dùng hiện tại không có quyền xóa bản ghi này !");
			}
			return taxDAO.updateObject(obj.toModel());
		}
	   public Long createTax(TaxDTO obj) throws Exception{
			
			boolean check=checkCode(obj.getCode(),null);
			if(!check){
				throw new IllegalArgumentException("Mã thuế đã tồn tại !");
			}
			return taxDAO.saveObject(obj.toModel());
		}
	   
}
