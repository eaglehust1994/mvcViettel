/**
 * 
 */
package com.viettel.Common.rest;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Huy
 *
 */
public interface IFileService {
    
    @POST
    @Path("/uploadATTT")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadATTT(List<Attachment> attachments, @Context HttpServletRequest request);
    
    
    
    @POST
    @Path("/uploadATTT2")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadATTT2(@RequestParam("multipartFile") MultipartFile multipartFile, @Context HttpServletRequest request);
    
    @POST
    @Path("/uploadTemp")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadTemp(List<Attachment> attachments, @Context HttpServletRequest request);
   
    
    @GET
    @Path("/downloadImport")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileImport(@Context HttpServletRequest request)throws Exception;
    
    
    @GET
    @Path("/downloadFileATTT")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileATTT(@Context HttpServletRequest request) throws Exception;

    
}
