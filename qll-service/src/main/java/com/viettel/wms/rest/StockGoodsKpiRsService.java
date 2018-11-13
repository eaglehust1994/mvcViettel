/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.KpiStorageAmountDTO;
import com.viettel.wms.dto.KpiStorageTimeDTO;
import com.viettel.wms.dto.StockGoodsSerialDTO;
import com.viettel.wms.dto.StockGoodsTotalDTO;

/**
 *
 * @author VietLT
 */
public interface StockGoodsKpiRsService {
	
	@POST
    @Path("/doSearchKpiTime")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearchKpiTime(KpiStorageTimeDTO obj) throws Exception;
	
	@POST
    @Path("/doSearchKpiAmount")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearchKpiAmount(KpiStorageAmountDTO obj) throws Exception;
    
	
}
