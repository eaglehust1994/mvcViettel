///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.business.ShipmentGoodsDetailBusinessImpl;
//import com.viettel.wms.dto.AttachmentDTO;
//import com.viettel.wms.dto.ShipmentGoodsDetailDTO;
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
//public class ShipmentGoodsDetailRsServiceImpl implements ShipmentGoodsDetailRsService {
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
//	//  protected final Logger log = Logger.getLogger(UserRsService.class);
//    @Autowired
//    ShipmentGoodsDetailBusinessImpl shipmentGoodsDetailBusinessImpl;
//
//    @Override
//    public Response getShipmentGoodsDetail() {
//        List<ShipmentGoodsDetailDTO> ls = shipmentGoodsDetailBusinessImpl.getAll();
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
//    public Response getShipmentGoodsDetailById(Long id) {
//        ShipmentGoodsDetailDTO obj = (ShipmentGoodsDetailDTO) shipmentGoodsDetailBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(obj).build();
//        }
//    }
//
//    @Override
//    public Response updateShipmentGoodsDetail(ShipmentGoodsDetailDTO obj) {
//        Long id = shipmentGoodsDetailBusinessImpl.update(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok().build();
//        }
//
//    }
//
//    @Override
//    public Response addShipmentGoodsDetail(ShipmentGoodsDetailDTO obj) {
//        Long id = shipmentGoodsDetailBusinessImpl.save(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(Response.Status.CREATED).build();
//        }
//    }
//
//    @Override
//    public Response deleteShipmentGoodsDetail(Long id) {
//        ShipmentGoodsDetailDTO obj = (ShipmentGoodsDetailDTO) shipmentGoodsDetailBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            shipmentGoodsDetailBusinessImpl.delete(obj);
//            return Response.ok(Response.Status.NO_CONTENT).build();
//        }
//    }
//// Hàm hiển thị danh sách hàng hóa trong màn hình Cập nhật hàng hóa cho lô hàng
//	@Override
//	public Response getDoMapDetail(ShipmentGoodsDetailDTO obj) {
//		List<ShipmentGoodsDetailDTO> list = shipmentGoodsDetailBusinessImpl.getDoMapDetail(obj);
//		 if (list == null) {
//	            return Response.status(Response.Status.BAD_REQUEST).build();
//	        } else {
//	            return Response.ok(list).build();
//	        }
//	}
////End
//	@Override
//	public Response getGoodsInfoByCode(String code) {
//		// TODO Auto-generated method stub
//		List<ShipmentGoodsDetailDTO> list = shipmentGoodsDetailBusinessImpl.getGoodsInfoByCode(code);
//		if (list == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(list).build();
//        }
//	}
//
//	@Override
//	public Response getGoodsDetail(ShipmentGoodsDetailDTO obj) {
//		// TODO Auto-generated method stub
//		List<ShipmentGoodsDetailDTO> list = shipmentGoodsDetailBusinessImpl.getGoodsDetail(obj);
//		if (list == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(list).build();
//        }
//	}
//	@Override
//	public Response importGoods(Attachment attachments, HttpServletRequest request) throws Exception {
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
//				return Response.ok(shipmentGoodsDetailBusinessImpl.importGoods(folderUpload + filePath)).build();
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
//	public Response getGoodsFile(AttachmentDTO obj) {
//		// TODO Auto-generated method stub
//				List<AttachmentDTO> list = shipmentGoodsDetailBusinessImpl.getGoodsFile(obj);
//				if (list == null) {
//		            return Response.status(Response.Status.BAD_REQUEST).build();
//		        } else {
//		            return Response.ok(list).build();
//		        }
//	}
//
//}
