///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.io.InputStream;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import javax.activation.DataHandler;
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.core.Response;
//
//import org.apache.cxf.jaxrs.ext.multipart.Attachment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import com.viettel.asset.filter.session.UserSession;
//import com.viettel.ktts2.common.BusinessException;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.ktts2.common.UFile;
//import com.viettel.ktts2.common.UString;
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.business.OrderChangeGoodsBusinessImpl;
//import com.viettel.wms.business.OrderChangeGoodsDetailBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
//import com.viettel.wms.dto.OrderChangeGoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;
//import com.viettel.wms.dto.StockTransDTO;
//
///**
// *
// * @author HungLQ9
// */
//public class OrderChangeGoodsRsServiceImpl implements OrderChangeGoodsRsService {
//
//	@Context
//	HttpServletRequest request;
//	@Autowired
//	private UserRoleBusinessImpl userRoleBusinessImpl;
//
//	@Value("${folder_upload2}")
//	private String folderUpload;
//
//	@Value("${folder_upload}")
//	private String folderTemp;
//
//	@Value("${default_sub_folder_upload}")
//	private String defaultSubFolderUpload;
//
//	@Value("${allow.file.ext}")
//	private String allowFileExt;
//	@Value("${allow.folder.dir}")
//	private String allowFolderDir;
//
//	@Autowired
//	OrderChangeGoodsBusinessImpl orderChangeGoodsBusinessImpl;
//
//	@Autowired
//	OrderChangeGoodsDetailBusinessImpl orderChangeGoodsDetailBusinessImpl;
//
//	@Autowired
//	ServletContext context;
//
//	@Override
//	public Response getOrderChangeGoods() {
//		List<OrderChangeGoodsDTO> ls = orderChangeGoodsBusinessImpl.getAll();
//		if (ls == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			DataListDTO data = new DataListDTO();
//			data.setData(ls);
//			data.setTotal(ls.size());
//			data.setSize(ls.size());
//			data.setStart(1);
//			return Response.ok(data).build();
//		}
//	}
//
//	@Override
//	public Response getOrderChangeGoodsById(Long id) {
//		OrderChangeGoodsDTO obj = (OrderChangeGoodsDTO) orderChangeGoodsBusinessImpl
//				.getOneById(id);
//		if (obj == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(obj).build();
//		}
//	}
//
//	@Override
//	public Response updateOrderChangeGoods(OrderChangeGoodsDTO obj) {
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
//		Long ids;
//		try {
//			obj.setUpdateBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCreatedDeptId(objUser.getVpsUserInfo().getDepartmentId());
//			obj.setCreatedDeptName(objUser.getVpsUserInfo().getDepartmentName());
//			obj.setUpdatedDate(new Date());
//			ids = orderChangeGoodsBusinessImpl.updateImportChange(obj,objUser);
//			return Response.ok(Response.Status.CREATED).build();
//		} catch (IllegalArgumentException e) {
//			return Response.ok()
//					.entity(Collections.singletonMap("error", e.getMessage()))
//					.build();
//		}
//
//	}
//
//	@Override
//	public Response addOrderChangeGoods(OrderChangeGoodsDTO obj)
//			throws Exception {
//
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
//		Long ids;
//		try {
//			obj.setCreatedBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCreatedDeptId(objUser.getVpsUserInfo().getDepartmentId());
//			obj.setCreatedDeptName(objUser.getVpsUserInfo().getDepartmentName());
//			obj.setSignState("1");
//			ids = orderChangeGoodsBusinessImpl.saveImportChange(obj,request);
//				return Response.ok(Response.Status.CREATED).build();
//		} catch (IllegalArgumentException e) {
//			return Response.ok()
//					.entity(Collections.singletonMap("error", e.getMessage()))
//					.build();
//		}
//
//	}
//
//	@Override
//	public Response deleteOrderChangeGoods(OrderChangeGoodsDTO obj) {
//		// // OrderChangeGoodsDTO obj = (OrderChangeGoodsDTO)
//		// orderChangeGoodsBusinessImpl.getOneById(obj.getOrderChangeGoodsId());
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
//		Long id;
//		try
//		{
//		obj.setStatus("3");
//		obj.setCancelUserId(objUser.getVpsUserInfo().getSysUserId());
//		 id = orderChangeGoodsBusinessImpl.update(obj);
//		 return Response.ok(Response.Status.NO_CONTENT).build();
//		}catch(IllegalArgumentException e){
//			return Response.ok()
//					.entity(Collections.singletonMap("error", e.getMessage()))
//					.build();
//		}
//
//	}
//
//	@Override
//	public Response doSearch(OrderChangeGoodsDTO obj) {
//		// TODO Auto-generated method stub
//		DataListDTO data = orderChangeGoodsBusinessImpl.doSearch(obj);
//		return Response.ok(data).build();
//	}
//
//	@Override
//	public Response importGoods(Attachment attachments,
//			HttpServletRequest request) throws Exception {
//		String folderParam = UString.getSafeFileName(request
//				.getParameter("folder"));
//		String filePathReturn;
//		String filePath;
//		if (UString.isNullOrWhitespace(folderParam)) {
//			folderParam = defaultSubFolderUpload;
//		} else {
//			if (!isFolderAllowFolderSave(folderParam)) {
//				throw new BusinessException(
//						"folder khong nam trong white list: folderParam="
//								+ folderParam);
//			}
//		}
//
//		DataHandler dataHandler = attachments.getDataHandler();
//
//		// get filename to be uploaded
//		MultivaluedMap<String, String> multivaluedMap = attachments
//				.getHeaders();
//		String fileName = UFile.getFileName(multivaluedMap);
//
//		if (!isExtendAllowSave(fileName)) {
//			throw new BusinessException(
//					"File extension khong nam trong list duoc up load, file_name:"
//							+ fileName);
//		}
//		// write & upload file to server
//		try (InputStream inputStream = dataHandler.getInputStream();) {
//			filePath = UFile.writeToFileServerATTT2(inputStream, fileName,
//					folderParam, folderUpload);
//			filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
//		} catch (Exception ex) {
//			throw new BusinessException("Loi khi save file", ex);
//		}
//
//		try {
//
//			try {
//
//				return Response.ok(
//						orderChangeGoodsDetailBusinessImpl
//								.importGoods(folderUpload + filePath)).build();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				return Response
//						.ok()
//						.entity(Collections.singletonMap("error",
//								e.getMessage())).build();
//			}
//
//		} catch (IllegalArgumentException e) {
//			return Response.ok()
//					.entity(Collections.singletonMap("error", e.getMessage()))
//					.build();
//		}
//
//	}
//
//	private boolean isFolderAllowFolderSave(String folderDir) {
//		return UString.isFolderAllowFolderSave(folderDir, allowFolderDir);
//
//	}
//
//	private boolean isExtendAllowSave(String fileName) {
//		return UString.isExtendAllowSave(fileName, allowFileExt);
//	}
//
//	@Override
//	public Response doSearchForAutoImport(OrderChangeGoodsDetailDTO obj) {
//		return Response.ok(
//				orderChangeGoodsBusinessImpl.doSearchForAutoImport(obj))
//				.build();
//	}
//
//	@Override
//	public Response doSearchGoodsForImportReq(OrderChangeGoodsDTO obj) {
//		DataListDTO data = orderChangeGoodsBusinessImpl
//				.doSearchGoodsForImportReq(obj);
//
//		if (data == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(data).build();
//		}
//	}
//
//	@Override
//	public Response getOrderChangeById(Long id) {
//		// TODO Auto-generated method stub
//		List<OrderChangeGoodsDTO> data = orderChangeGoodsBusinessImpl
//				.getOrderChangeById(id);
//		if (data.size()==0) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(data).build();
//		}
//	}
//
//	@Override
//	public Response exportOrderChangeExcelError(
//			OrderChangeGoodsDetailDTO errorObj) throws Exception {
//		// TODO Auto-generated method stub
//		try {
//			String strReturn = orderChangeGoodsBusinessImpl
//					.exportOrderChangeExcelError(errorObj);
//			return Response.ok(Collections.singletonMap("fileName", strReturn))
//					.build();
//		} catch (Exception e) {
//		}
//		return null;
//	}
//	
//	@Override
//	public Response doSearchForCheckAll(OrderChangeGoodsDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		return Response.ok(orderChangeGoodsBusinessImpl.doSearchForCheckAll(obj)).build();
//	}
//
//
//}
