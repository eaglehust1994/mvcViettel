///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.business.ICntContractBusinessImpl;
//import com.viettel.wms.business.IProjInvestProjectBusinessImpl;
//import com.viettel.wms.business.ShipmentBusinessImpl;
//import com.viettel.wms.business.ShipmentGoodsBusinessImpl;
//import com.viettel.wms.business.ShipmentGoodsDetailBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
//import com.viettel.wms.dao.ICntContractDAO;
//import com.viettel.wms.dao.IProjInvestProjectDAO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.ICntContractDTO;
//import com.viettel.wms.dto.IProjInvestProjectDTO;
//import com.viettel.wms.dto.ShipmentDTO;
//import com.viettel.wms.dto.ShipmentGoodsDTO;
//import com.viettel.wms.dto.ShipmentGoodsDetailDTO;
//import com.viettel.asset.filter.session.UserSession;
//import com.viettel.ktts2.common.BusinessException;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.ktts2.common.UFile;
//import com.viettel.ktts2.common.UString;
//import com.viettel.ktts2.dto.KttsUserSession;
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
//public class ShipmentRsServiceImpl implements ShipmentRsService {
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
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
//	@Autowired
//	ShipmentBusinessImpl shipmentBusinessImpl;
//	@Autowired
//	ShipmentGoodsDetailBusinessImpl shipmentGoodsDetailBusinessImpl;
//	@Autowired
//	ShipmentGoodsBusinessImpl shipmentGoodsBusinessImpl;
//	@Autowired
//	private UserRoleBusinessImpl userRoleBusinessImpl;
//	@Override
//	public Response getShipment() {
//		List<ShipmentDTO> ls = shipmentBusinessImpl.getAll();
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
//	public Response getShipmentById(Long id) {
//		ShipmentDTO obj = (ShipmentDTO) shipmentBusinessImpl.getOneById(id);
//		if (obj == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(obj).build();
//		}
//	}
//	// Hàm update lô hàng
//		@Override
//		public Response updateShipment(ShipmentDTO obj) throws Exception{
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			try{
//			obj.setUpdatedBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCreatedDeptId(objUser.getVpsUserInfo().getDepartmentId());
//			obj.setCreatedDeptName(objUser.getVpsUserInfo().getDepartmentName());
//			obj.setUpdatedDate(new Date());
//			Long id = shipmentBusinessImpl.updateShipment(obj, objUser);
//			if (id == 0l) {
//				return Response.status(Response.Status.BAD_REQUEST).build();
//			} else {
//				return Response.ok(id).build();
//			}
//			}catch (IllegalArgumentException e) {
//				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//			}
//		}
//	//End
//		//Thêm mới lô hàng
//		@Override
//		public Response addShipment(ShipmentDTO obj) throws Exception{
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			try{
//			obj.setCreatedBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCreatedDeptId(objUser.getVpsUserInfo().getDepartmentId());
//			obj.setCreatedDeptName(objUser.getVpsUserInfo().getDepartmentName());
//			obj.setCreatedDate(new Date());
//			obj.setStatus("1");
//			Long id = shipmentBusinessImpl.addShipment(obj);
//
//			if (id == 0l) {
//				return Response.status(Response.Status.BAD_REQUEST).build();
//			} else {
//				return Response.ok(id).build();
//			}
//			}catch(IllegalArgumentException e){
//				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//			}
//		}
//		//End
//		//Hủy lô hàng
//	@Override
//	public Response remove(ShipmentDTO obj){
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
//		try{
//		obj.setCancelUserId(objUser.getSysUserId());
//		obj.setCancelDate(new Date());
//		obj.setStatus("8");
//		Long id = shipmentBusinessImpl.remove(obj, objUser);
//		if (id == 0l) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok().build();
//		}
//		}catch(IllegalArgumentException e){
//			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//		}
//	}
//	//End
//	//Hàm tìm kiếm danh sách lô hàng
//	@Override
//	public Response doSearch(ShipmentDTO obj) {
//		DataListDTO data = shipmentBusinessImpl.doSearch(obj);
//		return Response.ok(data).build();
//	}
//	//End
//	@Override
//	public Response searchListShipmentCode(String code) {
//		// TODO Auto-generated method stub
//		List<String> lst = shipmentBusinessImpl.searchListShipmentCode(code);
//		if (lst == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(lst).build();
//		}
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
//		try {
//
//			try {
//
//				return Response.ok(shipmentBusinessImpl.importGoods(folderUpload + filePath)).build();
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
//	public Response exportExcelError(GoodsDTO errorObj) throws Exception {
//		try {
//			String strReturn = shipmentBusinessImpl.exportExcelError(errorObj);
//			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//		} catch (Exception e) {
//		}
//		return null;
//	}
//
//	@Override
//	public Response exportExcelTemplate() throws Exception {
//		// TODO Auto-generated method stub
//		try {
//			String strReturn = shipmentBusinessImpl.exportExcelTemplate();
//			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//		} catch (Exception e) {
//		}
//		return null;
//	}
//	//Hàm Map hàng hóa cho lô hàng
//	@Override
//	public Response addShipmentGoods(List<ShipmentGoodsDTO> lobj) throws Exception{
//		try{
//		Long id=shipmentBusinessImpl.addShipmentGoods(lobj);
//				if(id==1){
//					return Response.status(Response.Status.BAD_REQUEST).build();
//			}else{
//				return Response.ok(id).build();
//			}
//			}catch (IllegalArgumentException e) {
//				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//			}
//			
//	}
//	//End
//	//Định lượng hàng hóa cho lô hàng
//	public Response updateShipmentGoods(ShipmentDTO obj) throws Exception{
//		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//		try{
//		obj.setUpdatedBy(objUser.getVpsUserInfo().getSysUserId());
//		obj.setUpdatedDate(new Date());
//		Long ids = shipmentBusinessImpl.updateShipmentGoods(obj, request);
//		if(ids==0) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		}
//		else {
//			return Response.ok(ids).build();
//		}
//		}catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//		
//	}
//	//End
//
//	/*@Override
//	public Response updateShipmentGoodsTax(ShipmentDTO obj) {
//		Long id=shipmentBusinessImpl.updateShipmentGoodsTax(obj);
//		if(id==0) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		}
//		else {
//			return Response.ok(id).build();
//		}
//	}*/
//
//	@Override
//	public Response saveDetailShipment(ShipmentDTO obj) throws Exception {
//		/*  KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//		obj.setUpdatedBy(objUser.getVpsUserInfo().getSysUserId());
//		obj.setCreatedDeptId(objUser.getVpsUserInfo().getDepartmentId());
//		obj.setCreatedDeptName(objUser.getVpsUserInfo().getDepartmentName());  */
//		Long id;
//		try {
//			id = shipmentBusinessImpl.saveDetailShipment(obj,request);
//			return Response.ok(id).build();
//		} catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//// Hàm tìm kiếm màn định lượng
//	@Override
//	public Response doSearchQuantity(ShipmentDTO obj) throws Exception {
//		DataListDTO data;
//		try {
//			data = shipmentBusinessImpl.doSearchQuantity(obj,request);
//			return Response.ok(data).build();
//		} catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//	//End
//	
//	//Begin
//	//tìm kiếm định giá
//	@Override
//	public Response doSearchPrice(ShipmentDTO obj) throws Exception {
//		DataListDTO data;
//		try {
//			data = shipmentBusinessImpl.doSearchPrice(obj,request);
//			return Response.ok(data).build();
//		} catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//	//End
//
//	
//
//}
