/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.dto.OrderPatternDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author TuanNB
 */
public interface OrderPatternRsService {

	 	@GET
	    @Path("/orderPattern")
	    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    public Response getOrderPattern();
	 
//	Tìm kiếm dữ liệu
    @POST
    @Path("/doSearch")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response doSearch(OrderPatternDTO obj);
    
//  Xoá dữ liệu
    @POST
	@Path("/remove")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response remove(OrderPatternDTO obj) throws Exception;

//  Thêm mới dữ liệu
    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response add(OrderPatternDTO obj) throws Exception ;
    
//  Sửa dữ liệu
    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(OrderPatternDTO obj) throws Exception;
    
//	Xem chi tiết
    @POST
    @Path("/viewDetail")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response viewDetail(OrderPatternDTO obj);
    
}
