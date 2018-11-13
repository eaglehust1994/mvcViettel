///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.business.StockTransDetailBusinessImpl;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//import com.viettel.ktts2.common.BusinessException;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.ktts2.common.UFile;
//import com.viettel.ktts2.common.UString;
//import com.viettel.service.base.dto.DataListDTO;
//
//import java.io.InputStream;
//import java.util.Collections;
//import java.util.List;
//
//import javax.activation.DataHandler;
//import javax.servlet.http.HttpServletRequest;
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
//public class StockTransDetailRsServiceImpl implements StockTransDetailRsService {
//
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
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
//	StockTransDetailBusinessImpl stockTransDetailBusinessImpl;
//
//	@Override
//	public Response getStockTransDetail() {
//		List<StockTransDetailDTO> ls = stockTransDetailBusinessImpl.getAll();
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
//	public Response getStockTransDetailById(Long id) {
//		StockTransDetailDTO obj = (StockTransDetailDTO) stockTransDetailBusinessImpl.getOneById(id);
//		if (obj == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(obj).build();
//		}
//	}
//
//	@Override
//	public Response updateStockTransDetail(StockTransDetailDTO obj) {
//		Long id = stockTransDetailBusinessImpl.update(obj);
//		if (id == 0l) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok().build();
//		}
//
//	}
//
//	@Override
//	public Response addStockTransDetail(StockTransDetailDTO obj) {
//		Long id = stockTransDetailBusinessImpl.save(obj);
//		if (id == 0l) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(Response.Status.CREATED).build();
//		}
//	}
//
//	@Override
//	public Response deleteStockTransDetail(Long id) {
//		StockTransDetailDTO obj = (StockTransDetailDTO) stockTransDetailBusinessImpl.getOneById(id);
//		if (obj == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			stockTransDetailBusinessImpl.delete(obj);
//			return Response.ok(Response.Status.NO_CONTENT).build();
//		}
//	}
//
//	@Override
//	public Response doSearchGoodsForImportNote(StockTransDetailDTO obj) {
//		// TODO Auto-generated method stub
//		DataListDTO data = stockTransDetailBusinessImpl.doSearchGoodsForImportNote(obj);
//		if (data == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(data).build();
//		}
//	}
//
//	@Override
//	public Response getGoodsInfoFromAlternativeStockByCode(String code) {
//		// TODO Auto-generated method stub
//		List<StockTransDetailDTO> lst = stockTransDetailBusinessImpl.getGoodsInfoFromAlternativeStockByCode(code);
//		if (lst == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(lst).build();
//		}
//	}
//
//	/**
//	 * search thong tin quan ly phieu xuat kho
//	 */
//	@Override
//	public Response doSearchGoodsForExportNote(StockTransDetailDTO obj) {
//		// TODO Auto-generated method stub
//		DataListDTO data = stockTransDetailBusinessImpl.doSearchGoodsForExportNote(obj);
//		if (data == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(data).build();
//		}
//	}
//
//	@Override
//	public Response getStockTransDetail(Long id) {
//		StockTransDetailDTO data = stockTransDetailBusinessImpl.getStockTransDetail(id);
//		if (data == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(data).build();
//		}
//	}
//
//	@Override
//	public Response addExportNote(List<StockTransDetailDTO> os) {
//		boolean isCreatedExportNote = stockTransDetailBusinessImpl.createNote(os);
//		if (!isCreatedExportNote) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok().build();
//		}
//	}
//
//	// xu ly import bản ghi từ excel
//	@Override
//	public Response exportStockTrans(Attachment attachments, HttpServletRequest request) throws Exception {
//		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
//		Long stockTransId=new Long(request.getParameter("stockTransId"));
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
//				return Response.ok(stockTransDetailBusinessImpl.exportStockTrans(folderUpload + filePath,stockTransId)).build();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//			}
//
//		} catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//
//	}
//	/**
//	 * xu ly import tu excel trong quan ly phieu xuat 
//	 */
//	@Override
//	public Response importUpdateStockTrans(Attachment attachments, HttpServletRequest request) throws Exception {
//		String folderParam = UString.getSafeFileName(request.getParameter("folderUpdate"));
//		Long orderId=new Long(request.getParameter("orderId"));
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
//				return Response.ok(stockTransDetailBusinessImpl.importUpdateStockTrans(folderUpload + filePath,orderId)).build();
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
//	// kết thúc update import
//
//	@Override
//	public Response exportStockTransExcelError(GoodsDTO errorObj)
//			throws Exception {
//		// TODO Auto-generated method stub
//				try {
//					String strReturn = stockTransDetailBusinessImpl.exportStockTransExcelError(errorObj);
//					return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//				} catch (Exception e) {
//				}
//				return null;
//	}
//	
//	@Override
//	public Response  getListStockTransDetail(Long id){
//		return Response.ok(stockTransDetailBusinessImpl.getListStockTransDetail(id)).build();
//	}
//
//	@Override
//	public Response exportExcelTemplate(StockTransDetailDTO obj) throws Exception {
//			String strReturn = stockTransDetailBusinessImpl.exportExcelTemplate(obj);
//			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//	}
//}
