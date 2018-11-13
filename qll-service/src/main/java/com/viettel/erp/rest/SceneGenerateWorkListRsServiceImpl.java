/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstrGroundHandoverBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.business.SceneGenerateWorkListBusinessImpl;
import com.viettel.erp.dao.CatFileInvoiceDAO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.SceneGenerateWorkListDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class SceneGenerateWorkListRsServiceImpl implements SceneGenerateWorkListRsService {

    @Autowired
    SceneGenerateWorkListBusinessImpl sceneGenerateWorkListBusinessImpl;
    
    @Autowired
	ConstrGroundHandoverBusinessImpl constrGroundHandoverBusinessImpl;
    
    @Autowired
    ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

    @Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
    
    @Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;
    
    //tungpv
    @Override
    public Response doCRUD(SceneGenerateWorkDTO obj) {
    	if(obj.getSceneGenerateWorkId() != null){
    		SceneGenerateWorkDTO s = sceneGenerateWorkListBusinessImpl.doCRUD(obj);
    		if(obj.getStatusCa() == 0L){
    			String nameTable = "SCENE_GENERATE_WORK";
    			qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getSceneGenerateWorkId(), nameTable);
    		}
    		return Response.ok(s).build();
    	} else {
    		String code= (String) constrGroundHandoverBusinessImpl.getCode("SCENE_GENERATE_WORK", "QLHC_BBHT_PSKL") ;
    		if(StringUtils.isNotEmpty(code)){
    			obj.setCode(code);
    			obj.setCreatedDate(new Date());
    			SceneGenerateWorkDTO dto = sceneGenerateWorkListBusinessImpl.doCRUD(obj);
    			
    			//CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("SCENE_GENERATE_WORK");
    			
    			ConstrCompleteRecordsMapDTO constrCompleteRecordsMap = new ConstrCompleteRecordsMapDTO();
    			constrCompleteRecordsMap.setDataTableName("SCENE_GENERATE_WORK");
    			constrCompleteRecordsMap.setDataTableId("SCENE_GENERATE_WORK_ID");
    			constrCompleteRecordsMap.setDataTableIdValue(dto.getSceneGenerateWorkId());
    			//constrCompleteRecordsMap.setCatFileInvoiceId(18l);
    			constrCompleteRecordsMap.setCatFileInvoiceId(165l);
    			constrCompleteRecordsMap.setCreatedDate(new Date());
    			constrCompleteRecordsMap.setStatus(0L);
    			constrCompleteRecordsMap.setCreatedUserId(obj.getCreatedUserId());
    			constrCompleteRecordsMap.setConstructionId(obj.getConstructId());
    			constrCompleteRecordsMap.setCode(code);
    			constrCompleteRecordsMap.setLevelOrder(1L);
    			constrCompleteRecordsMapBusinessImpl.save(constrCompleteRecordsMap);
    			
	        	return Response.ok(dto).build();
    		} else {
    			return Response.status(Response.Status.BAD_REQUEST).build();
    		}
    	}
    }
    //end tungpv
    
    @Override
    public Response getSceneGenerateWorkList() {
        List<SceneGenerateWorkListDTO> ls = sceneGenerateWorkListBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            DataListDTO data = new DataListDTO();
            data.setData(ls);
            data.setTotal(ls.size());
            data.setSize(ls.size());
            data.setStart(1);
            return Response.ok(data).build();
        }
    }

    @Override
    public Response getSceneGenerateWorkListById(Long id) {
        SceneGenerateWorkListDTO obj = (SceneGenerateWorkListDTO) sceneGenerateWorkListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateSceneGenerateWorkList(SceneGenerateWorkListDTO obj) {
        Long id = sceneGenerateWorkListBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addSceneGenerateWorkList(SceneGenerateWorkListDTO obj) {
        Long id = sceneGenerateWorkListBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteSceneGenerateWorkList(Long id) {
        SceneGenerateWorkListDTO obj = (SceneGenerateWorkListDTO) sceneGenerateWorkListBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            sceneGenerateWorkListBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getAllSceneGenerateWork(Long constructId) {
		List<EstimatesWorkItemsDTO> ls = sceneGenerateWorkListBusinessImpl.getAllSceneGenerateWork(constructId);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

	 @Override
	    public Response doSearchSceneGenerateWork(List<EstimatesWorkItemsDTO> criteria) {
	    	List<EstimatesWorkItemsDTO> obj=Lists.newArrayList();
	    	for(EstimatesWorkItemsDTO criteri:criteria){
	        List<EstimatesWorkItemsDTO> ls = sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWork(criteri);
	        
	        if(ls.size()>0){
	        	for(EstimatesWorkItemsDTO l:ls){
	        		obj.add(l);
	        	}
	        	}
	    	}
	    	  return Response.ok(obj).build();
	    }
	
	@Override
	public Response doSearchSceneGenerateWorkList(SceneGenerateWorkDTO criteria) {
		List<SceneGenerateWorkListDTO> ls = sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWorkList(criteria);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

	@Override
	public Response doCRUDCT(SceneGenerateWorkDTO obj) {
    	if(obj.getSceneGenerateWorkId() != null){
    		SceneGenerateWorkDTO s = sceneGenerateWorkListBusinessImpl.doCRUD(obj);
    		if(obj.getStatusCa() == 0L){
    			String nameTable = "SCENE_GENERATE_WORK";
    			qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getSceneGenerateWorkId(), nameTable);
    		}
    		return Response.ok(s).build();
    	} else {
    		String code= (String) constrGroundHandoverBusinessImpl.getCode("SCENE_GENERATE_WORK", "QLHC_BBHT_PSCT") ;
    		if(StringUtils.isNotEmpty(code)){
    			obj.setCode(code);
    			obj.setCreatedDate(new Date());
    			SceneGenerateWorkDTO dto = sceneGenerateWorkListBusinessImpl.doCRUD(obj);
    			
    			//CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("SCENE_GENERATE_WORK");
    			
    			ConstrCompleteRecordsMapDTO constrCompleteRecordsMap = new ConstrCompleteRecordsMapDTO();
    			constrCompleteRecordsMap.setDataTableName("SCENE_GENERATE_WORK");
    			constrCompleteRecordsMap.setDataTableId("SCENE_GENERATE_WORK_ID");
    			constrCompleteRecordsMap.setDataTableIdValue(dto.getSceneGenerateWorkId());
    			constrCompleteRecordsMap.setCatFileInvoiceId(33l);
    			constrCompleteRecordsMap.setCreatedDate(new Date());
    			constrCompleteRecordsMap.setStatus(0L);
    			constrCompleteRecordsMap.setCreatedUserId(obj.getCreatedUserId());
    			constrCompleteRecordsMap.setConstructionId(obj.getConstructId());
    			constrCompleteRecordsMap.setCode(code);
    			constrCompleteRecordsMap.setLevelOrder(1L);
    			constrCompleteRecordsMapBusinessImpl.save(constrCompleteRecordsMap);
    			
	        	return Response.ok(dto).build();
    		} else {
    			return Response.status(Response.Status.BAD_REQUEST).build();
    		}
    	}
    }
}
