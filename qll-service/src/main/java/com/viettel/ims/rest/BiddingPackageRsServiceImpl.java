/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ims.rest;

import java.awt.List;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.asset.filter.session.UserSession;
import com.viettel.ims.business.BiddingPackageBusinessImpl;
import com.viettel.ims.dto.BiddingPackageDTO;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.common.UFile;
import com.viettel.ktts2.common.UString;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.dto.DataListDTO;
import  com.viettel.Common.CommonBussiness.UserRoleBusinessImpl;



public class BiddingPackageRsServiceImpl implements BiddingPackageRsService {

	@Context HttpServletRequest request;
	@Autowired
	private UserRoleBusinessImpl userRoleBusinessImpl;
//    protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    BiddingPackageBusinessImpl biddingPackageBusinessImpl;
    
	@Value("${folder_upload2}")
	private String folderUpload;
    
	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;
	
	@Value("${allow.folder.dir}")
	private String allowFolderDir;
	
	@Value("${allow.file.ext}")
	private String allowFileExt;

		@Override
		public Response getByUserId(Long userId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Response doSearch(BiddingPackageDTO obj) {
			DataListDTO data= biddingPackageBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		}

		@Override
		public Response remove(BiddingPackageDTO obj) {
			KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
		
			obj.setUpdatedDate(new Date());
			obj.setStatus(0L);
			Long id= biddingPackageBusinessImpl.deleteAppParam(obj);
			if(id==0l){
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok().build();
			}
		}

		@Override
		public Response add(BiddingPackageDTO obj) throws Exception {
			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
			try {   
					obj.setCreatedUserId(objUser.getSysUserId());
					obj.setCreatedGroupId(objUser.getGroupId());
					obj.setStatus(1L);
					obj.setCreatedDate(new Date());
					Long ids=biddingPackageBusinessImpl.createAppParam(obj);
				if (ids == 0l) {
					return Response.status(Response.Status.BAD_REQUEST).build();
				} else {
					return Response.ok(Response.Status.CREATED).build();
				}
			}catch(IllegalArgumentException e){
				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
			}
		}

		@Override
		public Response update(BiddingPackageDTO obj) throws Exception {
			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
			try {
				obj.setUpdatedUserId(objUser.getSysUserId());
//				obj.setParType(obj.getParType().toUpperCase());
				obj.setCode(obj.getCode().toUpperCase());
				obj.setUpdatedDate(new Date());
				Long ids=biddingPackageBusinessImpl.updateAppParam(obj,objUser);
				if (ids == 0l) {
					return Response.status(Response.Status.BAD_REQUEST).build();
				} else {
					return Response.ok(Response.Status.CREATED).build();
				}
			} catch (IllegalArgumentException e) {
				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
			}
			
		}

		@Override
		public Response getAll() {
			
			DataListDTO data= (DataListDTO) biddingPackageBusinessImpl.getAllObject();
			return Response.ok(data).build();
		}

		@Override
		public Response importBiddingPackage(Attachment attachments,
				HttpServletRequest request) throws Exception {
			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
			String folderParam = UString.getSafeFileName(request.getParameter("folder"));
			String filePathReturn;
			String filePath;
			
			if (UString.isNullOrWhitespace(folderParam)) {
				folderParam = defaultSubFolderUpload;
			} else {
				if (!isFolderAllowFolderSave(folderParam)) {
					throw new BusinessException("folder khong nam trong white list: folderParam=" + folderParam);
				}
			}

			DataHandler dataHandler = attachments.getDataHandler();

			// get filename to be uploaded
			MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
			String fileName = UFile.getFileName(multivaluedMap);

			if (!isExtendAllowSave(fileName)) {
				throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
			}
			// write & upload file to server
			try (InputStream inputStream = dataHandler.getInputStream();) {
				filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
				filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
			} catch (Exception ex) {
				throw new BusinessException("Loi khi save file", ex);
			}

			try {

				try {
					java.util.List<BiddingPackageDTO> result = biddingPackageBusinessImpl.importBiddingPackage(folderUpload + filePath);
					if(result != null){
						
							for (BiddingPackageDTO obj : result) {
								if(obj.getCode() != null){
									obj.setCreatedUserId(objUser.getSysUserId());
									obj.setStatus(1L);
									biddingPackageBusinessImpl.createAppParam(obj);
								}
							}
						
						return Response.ok(result).build();
					}
					else{
						return Response.ok(null).build();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
				}

			} catch (IllegalArgumentException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		}
		
		

		private boolean isFolderAllowFolderSave(String folderDir) {
			return UString.isFolderAllowFolderSave(folderDir, allowFolderDir);

		}
		
		private boolean isExtendAllowSave(String fileName) {
			return UString.isExtendAllowSave(fileName, allowFileExt);
		}

		@Override
		public Response getForAutoComplete(BiddingPackageDTO obj) {
			return Response.ok(biddingPackageBusinessImpl.getForAutoComplete(obj)).build();
		}
//
//		@Override
//		public Response getForComboBox(AppParamDTO obj) {
//			return Response.ok(biddingPackageBusinessImpl.getForComboBox(obj)).build();
//		}
//		@Override
//		public Response getForComboBox1(AppParamDTO obj) {
//			return Response.ok(biddingPackageBusinessImpl.getForComboBox1(obj)).build();
//		}
//		@Override
//		public Response getFileDrop() {
//			//Hieunn
//			//get list filedrop form APP_PARAM with PAR_TYPE = 'SHIPMENT_DOCUMENT_TYPE' and Status=1
//		return Response.ok(biddingPackageBusinessImpl.getFileDrop()).build();
//		}

}
