/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package com.viettel.wms.rest;
////
////import com.viettel.ktts2.common.BusinessException;
////import com.viettel.ktts2.common.UEncrypt;
////import com.viettel.ktts2.common.UFile;
////import com.viettel.ktts2.common.UString;
////import com.viettel.wms.business.OrderGoodsBusinessImpl;
////import com.viettel.wms.dto.AppParamDTO;
////import com.viettel.wms.dto.GoodsDTO;
////import com.viettel.wms.dto.OrderGoodsDTO;
////import com.viettel.wms.dto.OrderGoodsDetailDTO;
////import com.viettel.service.base.dto.DataListDTO;
////
////import java.io.File;
////import java.io.InputStream;
////import java.util.ArrayList;
////import java.util.Collections;
////import java.util.HashMap;
////import java.util.List;
////import java.util.Map;
////
////import javax.activation.DataHandler;
////import javax.servlet.http.HttpServletRequest;
////import javax.ws.rs.core.MultivaluedMap;
////import javax.ws.rs.core.Response;
////
////import org.apache.cxf.jaxrs.ext.multipart.Attachment;
////import org.apache.log4j.Logger;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.RequestParam;
////import org.springframework.web.multipart.MultipartFile;
////import org.springframework.web.multipart.MultipartHttpServletRequest;
////
/////**
//// *
//// * @author HungLQ9
//// */
////public class OrderGoodsRsServiceImpl implements OrderGoodsRsService {
////	@Value("${folder_upload2}")
////	private String folderUpload;
////
////	@Value("${folder_upload}")
////	private String folderTemp;
////
////	@Value("${default_sub_folder_upload}")
////	private String defaultSubFolderUpload;
////
////	@Value("${allow.file.ext}")
////	private String allowFileExt;
////	@Value("${allow.folder.dir}")
////	private String allowFolderDir;
////	
////    @Autowired
////    OrderGoodsBusinessImpl orderGoodsBusinessImpl;
////
////    @Override
////    public Response getOrderGoods() {
////        List<OrderGoodsDTO> ls = orderGoodsBusinessImpl.getAll();
////        if (ls == null) {
////            return Response.status(Response.Status.BAD_REQUEST).build();
////        } else {
////            DataListDTO data = new DataListDTO();
////            data.setData(ls);
////            data.setTotal(ls.size());
////            data.setSize(ls.size());
////            data.setStart(1);
////            return Response.ok(data).build();
////        }
////    }
////
////    @Override
////    public Response getOrderGoodsById(Long id) {
////        OrderGoodsDTO obj = (OrderGoodsDTO) orderGoodsBusinessImpl.getOneById(id);
////        if (obj == null) {
////            return Response.status(Response.Status.BAD_REQUEST).build();
////        } else {
////            return Response.ok(obj).build();
////        }
////    }
////
////    @Override
////    public Response updateOrderGoods(OrderGoodsDTO obj) {
////        Long id = orderGoodsBusinessImpl.update(obj);
////        if (id == 0l) {
////            return Response.status(Response.Status.BAD_REQUEST).build();
////        } else {
////            return Response.ok().build();
////        }
////
////    }
////
////    @Override
////    public Response addOrderGoods(OrderGoodsDTO obj) {
////        Long id = orderGoodsBusinessImpl.save(obj);
////        if (id == 0l) {
////            return Response.status(Response.Status.BAD_REQUEST).build();
////        } else {
////            return Response.ok(Response.Status.CREATED).build();
////        }
////    }
////
////    @Override
////    public Response deleteOrderGoods(Long id) {
////        OrderGoodsDTO obj = (OrderGoodsDTO) orderGoodsBusinessImpl.getOneById(id);
////        if (obj == null) {
////            return Response.status(Response.Status.BAD_REQUEST).build();
////        } else {
////            orderGoodsBusinessImpl.delete(obj);
////            return Response.ok(Response.Status.NO_CONTENT).build();
////        }
////    }
////
////	@Override
////	public Response doSearchGoodsForImportReq(OrderGoodsDTO obj) {
////		// TODO Auto-generated method stub
////		DataListDTO data = orderGoodsBusinessImpl.doSearchGoodsForImportReq(obj);
////        if (data == null) {
////            return Response.status(Response.Status.BAD_REQUEST).build();
////        } else {
////        	return Response.ok(data).build();
////        }
////	}
////
////	@Override
////	public Response importCells(Attachment attachments, HttpServletRequest request, Long id) throws Exception {
////	
////			String folderParam = UString.getSafeFileName(request.getParameter("folder"));
////			String filePathReturn;
////			String filePath;
////			if (UString.isNullOrWhitespace(folderParam)) {
////				folderParam = defaultSubFolderUpload;
////			} else {
////				if (!isFolderAllowFolderSave(folderParam)) {
////					throw new BusinessException("folder khong nam trong white list: folderParam=" + folderParam);
////				}
////			}
////
////			DataHandler dataHandler = attachments.getDataHandler();
////
////			// get filename to be uploaded
////			MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
////			String fileName = UFile.getFileName(multivaluedMap);
////
////			if (!isExtendAllowSave(fileName)) {
////				throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
////			}
////			// write & upload file to server
////			try (InputStream inputStream = dataHandler.getInputStream()) {
////				filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
////				filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
////			} catch (Exception ex) {
////				throw new BusinessException("Loi khi save file", ex);
////			}
////		
////				
////			try {
////				List<OrderGoodsDetailDTO> detailDTOs=orderGoodsBusinessImpl.importCells(folderUpload + filePath,id);
////					return Response.ok(detailDTOs).build();
////			} catch (IllegalArgumentException e) {
////				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
////			}
////				
////			
////		}
////	
////	private boolean isFolderAllowFolderSave(String folderDir) {
////		return UString.isFolderAllowFolderSave(folderDir, allowFolderDir);
////
////	}
////
////	private boolean isExtendAllowSave(String fileName) {
////		return UString.isExtendAllowSave(fileName, allowFileExt);
////	}
////
////	@Override
////	public Response doSearchGoodsForExportOrder(OrderGoodsDTO obj) {
////		DataListDTO data = orderGoodsBusinessImpl.doSearchGoodsForExportOrder(obj);
////        	return Response.ok(data).build();
////		//return Response.ok(orderGoodsBusinessImpl.doSearchGoodsForExportOrder(obj)).build();
////	}
////
//	@Override
//	public Response importGoods(Attachment attachments, HttpServletRequest request) throws Exception {
//		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
//		String filePathReturn;
//		String filePath;
//		if (UString.isNullOrWhitespace(folderParam)) {
//			folderParam = defaultSubFolderUpload;
//		} else {
//			if (!isFolderAllowFolderSave(folderParam)) {
//				throw new BusinessException("Folder khong nam trong white list: folderParam=" + folderParam);
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
//			throw new BusinessException("Loi khi save file !!!", ex);
//		}
//	
//			
//		try {
//			
//			try {
//				return Response.ok(orderGoodsBusinessImpl.importGoods(folderUpload + filePath)).build();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//			}
//
//		} catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//	@Override
//	public Response getForAutoComplete(GoodsDTO obj) {
//		return Response.ok(orderGoodsBusinessImpl.getForAutoComplete(obj)).build();
//	}
////
////	@Override
////	public Response exportExcelError(OrderGoodsDetailDTO errorObj) {
////		// TODO Auto-generated method stub
////		try{
////			String strReturn = orderGoodsBusinessImpl.exportExcelError(errorObj);
////			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
////		}catch (Exception e) {
////		} 
////		return null;
////	}
////
////	@Override
////	public Response importOrderGood(Attachment attachments, HttpServletRequest request) throws Exception {
////		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
////		Long orderId =new Long(UString.getSafeFileName(request.getParameter("orderId")));
////		String filePathReturn;
////		String filePath;
////		if (UString.isNullOrWhitespace(folderParam)) {
////			folderParam = defaultSubFolderUpload;
////		} else {
////			if (!isFolderAllowFolderSave(folderParam)) {
////				throw new BusinessException("Folder khong nam trong white list: folderParam=" + folderParam);
////			}
////		}
////
////		DataHandler dataHandler = attachments.getDataHandler();
////
////		// get filename to be uploaded
////		MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
////		String fileName = UFile.getFileName(multivaluedMap);
////
////		if (!isExtendAllowSave(fileName)) {
////			throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
////		}
////		// write & upload file to server
////		try (InputStream inputStream = dataHandler.getInputStream();) {
////			filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
////			filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
////		} catch (Exception ex) {
////			throw new BusinessException("Loi khi save file", ex);
////		}
////	
////			
////		try {
////			
////			try {
////				
////				return Response.ok(orderGoodsBusinessImpl.importOrderGood(folderUpload + filePath,orderId)).build();
////			} catch (Exception e) {
////				// TODO Auto-generated catch block
////				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
////			}
////
////		} catch (IllegalArgumentException e) {
////			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
////		}
////	}
////
////	@Override
////	public Response saveGoods(List<OrderGoodsDTO> orderGoods) throws Exception {
////		return Response.ok(orderGoodsBusinessImpl.saveGoodsList(orderGoods)).build();
////	}
////
////	@Override
////	public Response exportExcelTemplate(OrderGoodsDTO obj) throws Exception {
////			String strReturn = orderGoodsBusinessImpl.exportExcelTemplate(obj);
////			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
////	}
////
////	@Override
////	public Response getGoodDetail(Long orderId) {
////		// TODO Auto-generated method stub
////		return Response.ok(orderGoodsBusinessImpl.getGoodDetail(orderId)).build();
////	}
////	
////	
////}
