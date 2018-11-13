///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.business.ShipmentGoodsBusinessImpl;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;
//import com.viettel.wms.dto.ShipmentGoodsDTO;
//import com.viettel.wms.dto.StockDTO;
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
//public class ShipmentGoodsRsServiceImpl implements ShipmentGoodsRsService {
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
//	//  protected final Logger log = Logger.getLogger(UserRsService.class);
//    @Autowired
//    ShipmentGoodsBusinessImpl shipmentGoodsBusinessImpl;
//
//    @Override
//    public Response getShipmentGoods() {
//        List<ShipmentGoodsDTO> ls = shipmentGoodsBusinessImpl.getAll();
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
//    public Response getShipmentGoodsById(Long id) {
//        ShipmentGoodsDTO obj = (ShipmentGoodsDTO) shipmentGoodsBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(obj).build();
//        }
//    }
//
//    @Override
//    public Response updateShipmentGoods(ShipmentGoodsDTO obj) {
//        Long id = shipmentGoodsBusinessImpl.update(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok().build();
//        }
//
//    }
//
//    @Override
//    public Response addShipmentGoods(ShipmentGoodsDTO obj) {
//        Long id = shipmentGoodsBusinessImpl.save(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(Response.Status.CREATED).build();
//        }
//    }
//
//    @Override
//    public Response deleteShipmentGoods(Long id) {
//        ShipmentGoodsDTO obj = (ShipmentGoodsDTO) shipmentGoodsBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            shipmentGoodsBusinessImpl.delete(obj);
//            return Response.ok(Response.Status.NO_CONTENT).build();
//        }
//    }
//
//	@Override
//	public Response doSearchMap(ShipmentGoodsDTO obj) {
//		List<ShipmentGoodsDTO> list = shipmentGoodsBusinessImpl.doSearchMap(obj);
//        if (list == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(list).build();
//        }
//	}
//	
//
//	@Override
//	public Response getForAutoComplete(ShipmentGoodsDTO obj) {
//		// TODO Auto-generated method stub
//		return Response.ok(shipmentGoodsBusinessImpl.getForAutoComplete(obj)).build();
//	}
//
//	@Override
//	public Response getGoodsInfoByCode(String code) {
//		// TODO Auto-generated method stub
//		List<ShipmentGoodsDTO> list = shipmentGoodsBusinessImpl.getGoodsInfoByCode(code);
//        if (list == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(list).build();
//        }
//	}
////Hàm export file biểu mẫu
//	@Override
//	public Response exportExcelTemplate(ShipmentGoodsDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		try{
//			String strReturn = shipmentGoodsBusinessImpl.exportExcelTemplate(obj);
//			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//		}catch (Exception e) {
//		} 
//		return null;
//	}
//	//End
////Hàm import định lượng 
//	@Override
//	public Response importQuantitative(Attachment attachments, HttpServletRequest request, Long id) throws Exception {
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
//		MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
//		String fileName = UFile.getFileName(multivaluedMap);
//
//		if (!isExtendAllowSave(fileName)) {
//			throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
//		}
//		try (InputStream inputStream = dataHandler.getInputStream()) {
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
//				return Response.ok(shipmentGoodsBusinessImpl.importQuantitative(folderUpload + filePath,id)).build();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//			}
//
//		} catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//	//End
//	private boolean isFolderAllowFolderSave(String folderDir) {
//		return UString.isFolderAllowFolderSave(folderDir, allowFolderDir);
//
//	}
//
//	private boolean isExtendAllowSave(String fileName) {
//		return UString.isExtendAllowSave(fileName, allowFileExt);
//	}
////Hàm xuất file excel lỗi
//	@Override
//	public Response exportExcelError(GoodsDTO errorObj) throws Exception {
//		// TODO Auto-generated method stub
//		try{
//			String strReturn = shipmentGoodsBusinessImpl.exportExcelError(errorObj);
//			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//		}catch (Exception e) {
//		} 
//		return null;
//	}
//	//End
////Xóa hàng hóa của lô hàng
//	@Override
//	public Response delete(ShipmentGoodsDTO obj) throws Exception {
//		if (obj == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			shipmentGoodsBusinessImpl.delete(obj);
//			return Response.ok(obj).build();
//		}
//	}
//	//End
//	@Override
//	public Response addShipmentGoodsList(List<ShipmentGoodsDTO> obj) {
//		  Long id = shipmentGoodsBusinessImpl.addShipmentGoodsList(obj);
//	        if (id == 0l) {
//	            return Response.status(Response.Status.BAD_REQUEST).build();
//	        } else {
//	            return Response.ok(Response.Status.CREATED).build();
//	        }
//	}
//
//	@Override
//	public Response getShipmentGoodsPrice(ShipmentGoodsDTO obj)  {
//		List<ShipmentGoodsDTO> list = shipmentGoodsBusinessImpl.getShipmentGoodsPrice(obj);
//        if (list == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(list).build();
//        }
//	}
//}
