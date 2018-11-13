package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.dao.EstimatesWorkItemsDAO;
import com.viettel.erp.dto.AbDetailPriceNewDTO;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("estimatesWorkItemsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EstimatesWorkItemsBusinessImpl extends BaseFWBusinessImpl<EstimatesWorkItemsDAO,EstimatesWorkItemsDTO, EstimatesWorkItemsBO> implements EstimatesWorkItemsBusiness {

    @Autowired
    private EstimatesWorkItemsDAO estimatesWorkItemsDAO;
    

     
    public EstimatesWorkItemsBusinessImpl() {
        tModel = new EstimatesWorkItemsBO();
        tDAO = estimatesWorkItemsDAO;
    }

    @Override
    public EstimatesWorkItemsDAO gettDAO() {
        return estimatesWorkItemsDAO;
    }

    @Override
    public long count() {
        return estimatesWorkItemsDAO.count("EstimatesWorkItemsBO", null);
    }

//ngoccx
    @Override
	public List<EstimatesWorkItemsDTO> getEstimatesWorkItemsSearchAccept(EstimatesWorkItemsDTO obj) {
		// TODO Auto-generated method stub
		return estimatesWorkItemsDAO.getEstimatesWorkItemsSearchAccept(obj);
	}

    
    //tungpv
    @Override
	public List<EstimatesWorkItemsDTO> doSearchEstimatesWorkItems(EstimatesWorkItemsDTO criteria) {
		return estimatesWorkItemsDAO.doSearchEstimatesWorkItems(criteria);
	}

    @Override
	public List<EstimatesWorkItemsDTO> getAllEstimatesWorkItems(Long id) {
		return estimatesWorkItemsDAO.getAllEstimatesWorkItems(id);
	}
    
    @Override
	public List<EstimatesWorkItemsDTO> doSearchByWorkItemsAcceptanceId(WorkItemsAcceptanceDTO workItemsAcceptanceId) {
		return estimatesWorkItemsDAO.doSearchByWorkItemsAcceptanceId(workItemsAcceptanceId);
	}
    //end tungpv
    
    //minhpvn -- import bieu mau
    public List<EstimatesWorkItemsDTO> getDataImport(EstimatesWorkItemsDTO obj) {
    	return estimatesWorkItemsDAO.doSearchSceneGenerateWorkListCongTrinh(obj,"THUA");
 	}
 	//trong hop dong--minhpvn
 	public List<EstimatesWorkItemsDTO> getDataImportIn(EstimatesWorkItemsDTO obj) {
 		obj.setTypeworkitemIncontract(true);
 		List<EstimatesWorkItemsDTO> ls =  estimatesWorkItemsDAO.doSearchSceneGenerateWorkListCongTrinh(obj,"EXPORT");
 		for (EstimatesWorkItemsDTO object : ls) {
			if(object.getType() == 2){
				object.setWorkAmount(0d);
			}
			object.changeData();
		}
 		
 		return ls;
 	}
 	//ngoai hop dong--minhpvn
 	public List<EstimatesWorkItemsDTO> getDataImportOut(EstimatesWorkItemsDTO obj) {
 		obj.setTypeworkitemIncontract(false);
 		obj.setType(2L);
 		List<EstimatesWorkItemsDTO> ls =  estimatesWorkItemsDAO.doSearchSceneGenerateWorkListCongTrinh(obj,"EXPORT");
 		for (EstimatesWorkItemsDTO object : ls) {
			if(object.getType() == 2){
				object.setWorkAmount(0d);
			}
			object.changeData();
		}
 		
 		return ls;
 	}
    
 	
    //dodt
    public List<EstimatesWorkItemsDTO> getWorkItem(EstimatesWorkItemsDTO obj) {
 		return Lists.newArrayList(Iterables.transform(estimatesWorkItemsDAO.getWorkItem(obj), arg0 -> arg0.toDTO()));
 	}
 	//trong hop dong
 	public List<EstimatesWorkItemsDTO> getWorkItemIn(EstimatesWorkItemsDTO obj) {
 		obj.setTypeworkitemIncontract(true);
 		return Lists.newArrayList(Iterables.transform(estimatesWorkItemsDAO.getWorkItem(obj), arg0 -> arg0.toDTO()));
 	}
 	//ngoai hop dong
 	public List<EstimatesWorkItemsDTO> getWorkItemOut(EstimatesWorkItemsDTO obj) {
 		obj.setTypeworkitemIncontract(false);
 		obj.setTypeworkitem(2L);
 		return Lists.newArrayList(Iterables.transform(estimatesWorkItemsDAO.getWorkItem(obj), arg0 -> arg0.toDTO()));
 	}
 	//end dodt

 	//nha
	@Override
	public List<EstimatesWorkItemsDTO> getByConstructionId(Long id) {		
		return estimatesWorkItemsDAO.getByConstructionId(id);
	}
	
	

	@Override
	public List<EstimatesWorkItemsDTO> getBieu4(EstimatesWorkItemsDTO obj) {
		return estimatesWorkItemsDAO.getBieu4(obj);
	}
	
 	 @Override
 	public List<EstimatesWorkItemsDTO> getAllEstimatesWorkInsideContract(EstimatesWorkItemsDTO obj) {
 		return estimatesWorkItemsDAO.getAllEstimatesWorkInsideContract(obj);
 	}
 	 
 	 @Override
 	 public List<EstimatesWorkItemsDTO> getAllEstimatesWorkOutsideContract(EstimatesWorkItemsDTO obj) {
 		 return estimatesWorkItemsDAO.getAllEstimatesWorkOutsideContract(obj);
 	 }

 	 
 	@Override
	 public List<EstimatesWorkItemsDTO> getAllEstimatesWorkOutsideContractForEvaluate(EstimatesWorkItemsDTO obj){
 		return estimatesWorkItemsDAO.getAllEstimatesWorkOutsideContractForEvaluate(obj);
 	}
 	
 	@Override
	 public List<EstimatesWorkItemsDTO> getAllEstimatesWorkInsideContractForEvaluate(EstimatesWorkItemsDTO obj){
		return estimatesWorkItemsDAO.getAllEstimatesWorkInsideContractForEvaluate(obj);
	}
    
 	public List<EstimatesWorkItemsDTO> getWorkItemDone(EstimatesWorkItemsDTO obj) {
		return estimatesWorkItemsDAO.getWorkItemDone(obj);
	}

	public List<EstimatesWorkItemsDTO> getWorkItemNotDone(EstimatesWorkItemsDTO obj) {
		return estimatesWorkItemsDAO.getWorkItemNotDone(obj);
	}

	public Long pauseWorkItem(List<Long> listWorkItem) {
		return estimatesWorkItemsDAO.pauseWorkItem(listWorkItem);
	}
	
	public List<EstimatesWorkItemsDTO> getWorkItemDetail(EstimatesWorkItemsDTO obj){
		return estimatesWorkItemsDAO.getWorkItemDetail(obj);
	}

	/*dong*/
	@Override
	public List<EstimatesWorkItemsDTO> getBieu2(Long id) {
		return estimatesWorkItemsDAO.getbieu2(id);
	}
	/*dong end*/

	@Override
	public EstimatesWorkItemsDTO exportEstimateWorkItem(EstimatesWorkItemsDTO dto) {
		// TODO Auto-generated method stub
		return estimatesWorkItemsDAO.exportEstimateWorkItem(dto);
	}

	@Override
	public List<EstimatesWorkItemsDTO> getBieu5(Long id) {
		// TODO Auto-generated method stub
		return estimatesWorkItemsDAO.getbieu5(id);
	}

	@Override
	public List<EstimatesWorkItemsDTO> getAllEstimatesWorkContract(EstimatesWorkItemsDTO obj) {
		// TODO Auto-generated method stub
		return estimatesWorkItemsDAO.getAllEstimatesWorkContract(obj);
	}

	public List<AbDetailPriceNewDTO> getExportBieu5(Long constructId) {
		// TODO Auto-generated method stub
		return estimatesWorkItemsDAO.getExportBieu5(constructId);
	}

	@Override
	public List<EstimatesWorkItemsDTO> doSearchSceneGenerateWorkListCongTrinh(EstimatesWorkItemsDTO obj) {
		// TODO Auto-generated method stub
		List<EstimatesWorkItemsDTO> ls =  estimatesWorkItemsDAO.doSearchSceneGenerateWorkListCongTrinh(obj,"ADD_NEW");
		for (EstimatesWorkItemsDTO object : ls) {
			if(object.getType() == 2){
				object.setWorkAmount(0d);
			}
			object.changeData();
		}
		return ls;
	}
	
	@Override
	public void updateStatus(Long estimatesWorkItemId) {
		estimatesWorkItemsDAO.updateStatus(estimatesWorkItemId);
	}
}
