package com.viettel.asset.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.common.UFile;
import com.viettel.ktts2.common.UString;


@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class FileUploadRsService {
	@Value("${folder_upload}")
	private String folder2Upload;
	

	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;
	
	

	@Resource(name = "configSource")
	private MessageSource configSource;

	private static Logger LOGGER = Logger.getLogger(FileUploadRsService.class);
	
	
	
	@POST
    @Path("/downloadFileATTT")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response downloadFileATTT(HttpServletRequest request) throws Exception{
		String fileName = request.getParameter("fileName");
		if (UString.isNotNullAndWhitespace(fileName)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		String filePath= UEncrypt.decryptFileUploadPath(fileName);
		File file = new File(folder2Upload + File.separatorChar + fileName);
		if (!file.exists()) {
			LOGGER.warn("File {} is not found:"+ fileName);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		int lastIndex=filePath.lastIndexOf(File.separatorChar);
		String fileNameReturn=fileName.substring(lastIndex+1);
		return Response.ok((Object) file)
				.header("Content-Disposition", "attachment; filename=\"" + fileNameReturn + "\"")
				.build();						
	}
	
	
	
	
	

	
}
