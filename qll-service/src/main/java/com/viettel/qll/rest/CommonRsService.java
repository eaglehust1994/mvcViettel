/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.qll.rest;

import java.text.ParseException;

import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import com.viettel.wms.dto.CommonDTO;

/**
 *
 * @author HungLQ9
 */
public interface CommonRsService {



    @POST
    @Path("/getCharTwoMonth")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	Response getCharTwoMonth(CommonDTO obj) throws ParseException;

 

}
