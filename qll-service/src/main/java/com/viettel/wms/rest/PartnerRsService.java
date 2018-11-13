package com.viettel.wms.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.viettel.wms.dto.PartnerDTO;

public interface PartnerRsService {
	 	@POST
	    @Path("/getForAutoComplete")
	    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public Response getForAutoComplete(PartnerDTO pat);
	 	
		@POST
	    @Path("/getForAutoCompleteII")
	    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public Response getForAutoCompleteII(String code);
		
		@POST
	    @Path("/getPartnerByCode")
	    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public Response getPartnerByCode(String code);
		
		@POST
	    @Path("/getPartnerById")
	    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public Response getPartnerById(Long id);
}
