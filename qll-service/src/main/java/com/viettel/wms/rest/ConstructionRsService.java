package com.viettel.wms.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.viettel.wms.dto.AppParamDTO;
import com.viettel.wms.dto.ConstructionDTO;

public interface ConstructionRsService {
    @POST
    @Path("/getForAutoComplete")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getForAutoComplete(ConstructionDTO cons);
    
    @POST
    @Path("/getForAutoCompleteII")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getForAutoCompleteII( String code );
    
    @POST
    @Path("/getConstructionByCode")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getConstructionByCode( String code );
}
