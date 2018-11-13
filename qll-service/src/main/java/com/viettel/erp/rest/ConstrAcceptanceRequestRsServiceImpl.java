/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.ConstrAcceptanceRequestBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrAcceptanceRequestDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class ConstrAcceptanceRequestRsServiceImpl implements ConstrAcceptanceRequestRsService {
	
	static Logger LOGGER = LoggerFactory.getLogger(ConstrAcceptanceRequestRsServiceImpl.class);

    @Autowired
    ConstrAcceptanceRequestBusinessImpl constrAcceptanceRequestBusinessImpl;
    
    @Autowired
    CatFileInvoiceBusinessImpl  catFileInvoiceBusinessImpl;
    
    @Autowired
    ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
    
    @Autowired
  	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;
    
    @Override
	public Response deleteConstrAcceptanceReq(List<String> listCode) {
    	String res = constrAcceptanceRequestBusinessImpl.deleteConstrAcceptanceReq(listCode);
    	HashMap<Integer, String> hmap = new HashMap<Integer, String>();
    	hmap.put(1, res);
    	if (res == null || "".equals(res)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(hmap).build();
        }
	}

    
    @Override
	public Response listConstrAcceptanceReq(ConstrAcceptanceRequestDTO obj) {
    	List<ConstrAcceptanceRequestDTO> ls = constrAcceptanceRequestBusinessImpl.listConstrAcceptanceReq(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

    @Override
    public Response getConstrAcceptanceRequest() {
        List<ConstrAcceptanceRequestDTO> ls = constrAcceptanceRequestBusinessImpl.getAll();
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
    public Response getConstrAcceptanceRequestById(Long id) {
        ConstrAcceptanceRequestDTO obj = (ConstrAcceptanceRequestDTO) constrAcceptanceRequestBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateConstrAcceptanceRequest(ConstrAcceptanceRequestDTO obj) {
        Long id = constrAcceptanceRequestBusinessImpl.update(obj);
        if (id == 0L) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addConstrAcceptanceRequest(ConstrAcceptanceRequestDTO obj) {
    	try {
    		
    		if(obj.getCode() == null) {
        		String code = constrAcceptanceRequestBusinessImpl.autoGenCode();
        		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
        		
        	    CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("CONSTR_ACCEPTANCE_REQUEST");
                Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
        		
        		
            	constrCompleteRecordMap.setDataTableName("CONSTR_ACCEPTANCE_REQUEST");
            	constrCompleteRecordMap.setDataTableId("CONST_ACCEPTANCE_REQUEST_ID");
            	constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
            	constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
            	constrCompleteRecordMap.setStatus(0L);
            	constrCompleteRecordMap.setCreatedDate(new Date());
            	constrCompleteRecordMap.setConstructionId(obj.getConstructId());
            	constrCompleteRecordMap.setLevelOrder(1l);
            	constrCompleteRecordMap.setCode(code);
            	obj.setConstrCompleteRecordsMap(constrCompleteRecordMap);
            	obj.setCode(code);
            	obj.setCreatedDate(new Date());
            	Long id = constrAcceptanceRequestBusinessImpl.saveTable(obj);
                HashMap<Integer, Long> hm = new HashMap<>();
                hm.put(1, id);
                if (id == 0l) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                } else {
                    return Response.ok(Response.Status.CREATED).build();
                }
        	}else {
        		Long id = constrAcceptanceRequestBusinessImpl.update(obj);
        		if(obj.getStatusCa() == 0L){
                	String nameTable = "CONSTR_ACCEPTANCE_REQUEST";
                	qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getConstAcceptanceRequestId(), nameTable);
                }
                if (id == 0l) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                } else {
                	return Response.ok().build();
                }
        	}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
    	
    	
    	
    }

    @Override
    public Response deleteConstrAcceptanceRequest(Long id) {
        ConstrAcceptanceRequestDTO obj = (ConstrAcceptanceRequestDTO) constrAcceptanceRequestBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            constrAcceptanceRequestBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }


	@Override
	public Response appro(approDTO dto) {
		Long id = constrAcceptanceRequestBusinessImpl.appro(dto);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(id).build();
        }
	}


	@Override
	public Response removeConstrAcceptanceRequest(ConstrAcceptanceRequestDTO obj_) throws Exception {
		   ConstrAcceptanceRequestDTO obj = (ConstrAcceptanceRequestDTO) constrAcceptanceRequestBusinessImpl.getOneById(obj_.getConstAcceptanceRequestId());	
		   if (obj == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	        	ConstrCompleteRecordsMapDTO constrCompleteRecordsMapDTO = (ConstrCompleteRecordsMapDTO) constrCompleteRecordsMapBusinessImpl.getOneById(obj_.getConstrCompReMapId());
	 		    obj.setConstrCompleteRecordsMap(constrCompleteRecordsMapDTO);
	            constrAcceptanceRequestBusinessImpl.deleteTable(obj);
	            return Response.ok(Response.Status.NO_CONTENT).build();
	        }
	}

	
	
}
