///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.asset.filter.session.UserSession;
//import com.viettel.ktts2.common.BusinessException;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.ktts2.common.UFile;
//import com.viettel.ktts2.common.UString;
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.wms.business.OrderBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
//import com.viettel.wms.dto.CommonDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderDTO;
//import com.viettel.wms.dto.ShipmentDTO;
//import com.viettel.service.base.dto.DataListDTO;
//
//import java.io.InputStream;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import javax.activation.DataHandler;
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.core.Response;
//
//import org.apache.cxf.jaxrs.ext.multipart.Attachment;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
///**
// *
// * @author HungLQ9
// */
//public class OrderRsServiceImpl implements OrderRsService {
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
//	@Context HttpServletRequest request;
//	
//	@Autowired
//	private UserRoleBusinessImpl userRoleBusinessImpl;
//
//	//  protected final Logger log = Logger.getLogger(UserRsService.class);
//    @Autowired
//    OrderBusinessImpl orderBusinessImpl;
//
//    @Override
//    public Response getOrder() {
//        List<OrderDTO> ls = orderBusinessImpl.getAll();
//        if (ls == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            DataListDTO data = new DataListDTO();
//            data.setData(ls);
//            data.setTotal(ls.size());
//            data.setSize(ls.size());
//            data.setStart(1);
//            return Response.ok(data).build();
//        }
//    }
//
//    @Override
//    public Response getOrderById(Long id) {
//        OrderDTO obj = (OrderDTO) orderBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(obj).build();
//        }
//    }
//
//    @Override
//    public Response updateOrder(OrderDTO obj) {
//        Long id = orderBusinessImpl.update(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok().build();
//        }
//
//    }
//
//    @Override
//    public Response addOrder(OrderDTO obj) {
//        Long id = orderBusinessImpl.save(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(Response.Status.CREATED).build();
//        }
//    }
//
//    @Override
//    public Response deleteOrder(Long id) {
//        OrderDTO obj = (OrderDTO) orderBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            orderBusinessImpl.delete(obj);
//            return Response.ok(Response.Status.NO_CONTENT).build();
//        }
//    }
//
//	@Override
//	public Response doSearchImportRequirement(OrderDTO obj) {
//		// TODO Auto-generated method stub
//        DataListDTO data;
//		try{
//		data= orderBusinessImpl.doSearchImportRequirement(obj,request);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(data).build();
//        }
//		}catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//
//	@Override
//	public Response doSearchForCreateImportNote(OrderDTO obj) {
//		// TODO Auto-generated method stub
//        DataListDTO data;
//		try{
//		data= orderBusinessImpl.doSearchForCreateImportNote(obj,request);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(data).build();
//        }
//		}catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//
//	@Override
//	public Response getOrderDetail(Long id) {
//		// TODO Auto-generated method stub
//		OrderDTO data = orderBusinessImpl.getOrderDetail(id);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//	}
//
//	
//	@Override
//	public Response doSearchExportRequirement(OrderDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		try{
//		DataListDTO data = orderBusinessImpl.doSearchExportRequirement(obj,request);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//        }catch (IllegalArgumentException e) {
//            return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//        }
//}
//	
//
//
//
//
//	@Override
//	public Response getOderByShipment(OrderDTO obj) {
//		OrderDTO data = orderBusinessImpl.getOderByShipment(obj);
//        return Response.ok(data).build();
//	}
//
//	@Override
//	public Response saveImportReq(OrderDTO obj) throws Exception{
//		// TODO Auto-generated method stub
//		try{
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			obj.setCreatedBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCreatedByName(objUser.getVpsUserInfo().getFullName());
//			obj.setCreatedDeptedId(objUser.getVpsUserInfo().getDepartmentId());
//			obj.setCreatedDeptedName(objUser.getVpsUserInfo().getDepartmentName());
//			obj.setCreatedDate(new Date());
//			Long id = orderBusinessImpl.saveImportReq(obj, request);
//	        if (id == null) {
//	            return Response.status(Response.Status.BAD_REQUEST).build();
//	        } else {
//	        	return Response.ok(Response.Status.CREATED).build();
//	        }
//		}catch(BusinessException e){
//			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//		}catch (IllegalArgumentException e) {
//            return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//        }
//		
//	}
//
//	@Override
//	public Response removeOrder(OrderDTO obj) {
//		// TODO Auto-generated method stub
//		try {
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			obj.setCancelBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCancelByName(objUser.getVpsUserInfo().getFullName());
//			Boolean bool = orderBusinessImpl.removeOrder(obj,objUser);
//			if (!bool) {
//	            return Response.status(Response.Status.BAD_REQUEST).build();
//	        } else {
//	        	return Response.ok().entity(Collections.singletonMap("ok",bool)).build();
//	        }
//		}catch(IllegalArgumentException e){
//			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//		}
//	}
//
//
//
//	@Override
//	public Response exportSerial(OrderDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		String path = orderBusinessImpl.exportDocSerial(obj);
//		if(path !=null){
//		return Response.ok(Collections.singletonMap("fileName", path)).build();
//		}else{
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		}
//	}
//
//	@Override
//	public Response updateImportReq(OrderDTO obj) throws Exception{
//		// TODO Auto-generated method stub
//		try {
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			Long id = orderBusinessImpl.updateImportReq(obj,objUser,request);
//			if(id == 0l){
//				return Response.status(Response.Status.BAD_REQUEST).build();
//			}else{
//				return Response.ok().entity(Collections.singletonMap("ok",id)).build();
//			}
//		}catch(IllegalArgumentException e){
//			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//		}
//	}
//
//	@Override
//	public Response rejectOrder(OrderDTO obj) {
//		// TODO Auto-generated method stub
//		try {
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			obj.setCancelBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCancelByName(objUser.getVpsUserInfo().getFullName());
//			Boolean bool = orderBusinessImpl.rejectOrder(obj,objUser);
//			if (!bool) {
//	            return Response.status(Response.Status.BAD_REQUEST).build();
//	        } else {
//	        	return Response.ok(Response.Status.CREATED).build();
//	        }
//		}catch(IllegalArgumentException e){
//			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//		}
//	}
//
//	@Override
//	public Response repExportReq(OrderDTO obj) {
//		// TODO Auto-generated method stub
//		Long idnewOrder = orderBusinessImpl.replicateExportOrder(obj);
//		if (idnewOrder == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(idnewOrder).build();
//        }
//	}
//
//	@Override
//	public Response getOrder(Long id) {
//		OrderDTO order = orderBusinessImpl.getOrder(id);
//		if (order == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(Response.Status.CREATED).build();
//        }
//	}
//
//	@Override
//	public Response removeExportOrder(OrderDTO obj) {
//		boolean bool = orderBusinessImpl.removeExportOrder(obj);
//		if (!bool) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(Response.Status.CREATED).build();
//        }
//	}
//
//	@Override
//	public Response importGoods(Attachment attachments, HttpServletRequest request) throws Exception {
//		// TODO Auto-generated method stub
//		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
//		String filePathReturn;
//		String filePath;
//		if (UString.isNullOrWhitespace(folderParam)) {
//			folderParam = defaultSubFolderUpload;
//		} else {
//			if (!isFolderAllowFolderSave(folderParam)) {
//				throw new BusinessException("folder khong nam trong white list: folderParam=" + folderParam);
//			}
//		}
//
//		DataHandler dataHandler = attachments.getDataHandler();
//
//		// get filename to be uploaded
//		MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
//		String fileName = UFile.getFileName(multivaluedMap);
//
//		if (!isExtendAllowSave(fileName)) {
//			throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
//		}
//		// write & upload file to server
//		try (InputStream inputStream = dataHandler.getInputStream();) {
//			filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
//			filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
//		} catch (Exception ex) {
//			throw new BusinessException("Loi khi save file", ex);
//		}
//	
//			
//		try {
//			
//			try {
//				
//				return Response.ok(orderBusinessImpl.importGoods(folderUpload + filePath)).build();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//			}
//
//		} catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
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
//	public Response exportExcelTemplate() throws Exception {
//		// TODO Auto-generated method stub
//		try{
//			String strReturn = orderBusinessImpl.exportExcelTemplate();
//			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//		}catch (Exception e) {
//		} 
//		return null;
//	}
//
//	@Override
//	public Response exportExcelError(GoodsDTO errorObj) throws Exception {
//		// TODO Auto-generated method stub
//		try{
//			String strReturn = orderBusinessImpl.exportExcelError(errorObj);
//			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//		}catch (Exception e) {
//		} 
//		return null;
//	}
//
//	@Override
//	public Response exportExcelErrorXK(GoodsDTO errorObj) throws Exception {
//		// TODO Auto-generated method stub
//		try{
//			String strReturn = orderBusinessImpl.exportExcelErrorXK(errorObj);
//			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//		}catch (Exception e) {
//		} 
//		return null;
//	}
//	
//	@Override
//	public Response updateExportReq(OrderDTO obj) {
//		return Response.ok(orderBusinessImpl.updateExportRequest(obj)).build();
//	}
//
//	@Override
//	public Response getGoodsDetailByOrderId(OrderDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		return Response.ok(orderBusinessImpl.getGoodsDetailByOrderId(obj)).build();
//	}
//
//	@Override
//	public Response getAllImportRequirement(OrderDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		return Response.ok(orderBusinessImpl.getAllImportRequirement(obj,request)).build();
//	}
//
//	@Override
//	public Response getAllExportStatement(OrderDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		return Response.ok(orderBusinessImpl.getAllExportStatement(obj)).build();
//	}
//
//	@Override
//	public Response getOderByShipmentId(OrderDTO obj) {
//		OrderDTO data = orderBusinessImpl.getOderByShipmentId(obj);
//		if (data == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(data).build();
//		}
//
//	}
//
//
//}
