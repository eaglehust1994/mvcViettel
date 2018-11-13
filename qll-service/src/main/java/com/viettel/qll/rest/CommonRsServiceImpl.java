/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.qll.rest;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.org.eclipse.jdt.core.dom.SwitchCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.asset.filter.session.UserSession;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.qll.business.KqHdTkBusinessImpl;
import com.viettel.wms.dto.CommonDTO;

import com.viettel.wms.dto.StockDTO;


public class CommonRsServiceImpl implements CommonRsService {

	
	@Value("${folder_upload2}")
	private String folderUpload;

	@Value("${folder_upload}")
	private String folderTemp;

	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;
	@Context HttpServletRequest request;
//    protected final Logger log = Logger.getLogger(UserRsService.class);
  
	@Autowired
	KqHdTkBusinessImpl kqHdTkBusinessImpl;
   
	
	@Override
	public Response getCharTwoMonth(CommonDTO obj) throws ParseException {
		return Response.ok(kqHdTkBusinessImpl.getCharTwoMonth(obj,request)).build();
	}



}
