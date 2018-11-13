/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.AbSettlementWorkBusinessImpl;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.dto.AbSettlementWorkDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class AbSettlementWorkRsServiceImpl implements AbSettlementWorkRsService {

	static Logger LOGGER = LoggerFactory.getLogger(AbSettlementWorkRsServiceImpl.class);
	
    @Autowired
    AbSettlementWorkBusinessImpl abSettlementWorkBusinessImpl;
    
    @Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
    
    @Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
    
    @Override
    public Response getAbSettIdByConstrId(Long id) {
        AbSettlementWorkDTO obj = (AbSettlementWorkDTO) abSettlementWorkBusinessImpl.getAbSettIdByConstrId(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }
    
    @Override
    public Response getAbSettlementWork() {
        List<AbSettlementWorkDTO> ls = abSettlementWorkBusinessImpl.getAll();
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
    public Response getAbSettlementWorkById(Long id) {
        AbSettlementWorkDTO obj = (AbSettlementWorkDTO) abSettlementWorkBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateAbSettlementWork(AbSettlementWorkDTO obj) {
        Long id = abSettlementWorkBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addAbSettlementWork(AbSettlementWorkDTO obj) {
    	String code = abSettlementWorkBusinessImpl.autoGenCode();
    	obj.setCode(code);
    	CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("AB_SETTLEMENT_WORK");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		
		ConstrCompleteRecordsMapDTO constrCompleteRecordsMap = new ConstrCompleteRecordsMapDTO();
		constrCompleteRecordsMap.setDataTableName("AB_SETTLEMENT_WORK");
		constrCompleteRecordsMap.setDataTableId("AB_SETTLEMENT_WORK_ID");
		constrCompleteRecordsMap.setCatFileInvoiceId(catFileInvoiceId);
		constrCompleteRecordsMap.setCreatedDate(new Date());
		constrCompleteRecordsMap.setCode(code);
		constrCompleteRecordsMap.setStatus(0L);
		constrCompleteRecordsMap.setCreatedUserId(obj.getCreatedUserId());
		constrCompleteRecordsMap.setConstructionId(obj.getConstructId());
        obj.setConstrCompleteRecordsMap(constrCompleteRecordsMap);
        
        try {
			Long id = abSettlementWorkBusinessImpl.saveTable(obj);
			return Response.ok(id).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

		}
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Override
    public Response deleteAbSettlementWork(Long id) {
        AbSettlementWorkDTO obj = (AbSettlementWorkDTO) abSettlementWorkBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            abSettlementWorkBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
}
