/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ims.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import com.viettel.ims.dto.BiddingPackageDTO;


/**
 *
 * @author HungLQ9
 */
public interface BiddingPackageRsService {
	
    @GET
    @Path("/getByUserId/{userId}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getByUserId(@PathParam("userId") Long userId);
    
    @POST
    @Path("/doSearch")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearch(BiddingPackageDTO obj);
    
    @POST
    @Path("/remove")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response remove(BiddingPackageDTO obj);
    
    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response add(BiddingPackageDTO obj) throws Exception;
    
    
    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(BiddingPackageDTO obj) throws Exception;
    
    @GET
    @Path("/getAll")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAll();
    
    @POST
    @Path("/importBiddingPackage")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importBiddingPackage(Attachment attachments, @Context HttpServletRequest request) throws Exception;
    
    @POST
    @Path("/getForAutoComplete")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getForAutoComplete(BiddingPackageDTO obj);
//    
//    
//    @POST
//    @Path("/getForComboBox")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Response getForComboBox(AppParamDTO obj);
//	 @POST
//    @Path("/getForComboBox1")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Response getForComboBox1(AppParamDTO obj);
//    
//    
//    @GET
//    @Path("/getFileDrop")
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Response getFileDrop();

    
}
