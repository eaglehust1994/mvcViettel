///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import com.viettel.wms.business.StockGoodsTotalBusinessImpl;
//import com.viettel.wms.business.StockTransBusinessImpl;
//import com.viettel.wms.business.StockTransDetailBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
//import com.viettel.wms.constant.Constants;
//import com.viettel.wms.dto.CommonDTO;
//import com.viettel.wms.dto.KpiStorageAmountDTO;
//import com.viettel.wms.dto.OrderDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.StockGoodsTotalDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//import com.viettel.asset.filter.session.UserSession;
//import com.viettel.ktts2.common.BusinessException;
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.service.base.dto.DataListDTO;
//
//import java.beans.IntrospectionException;
//import java.io.File;
//import java.lang.reflect.InvocationTargetException;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.ResponseBuilder;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author HungLQ9
// */
//public class StockTransRsServiceImpl implements StockTransRsService {
//
//	//  protected final Logger log = Logger.getLogger(UserRsService.class);
//    @Autowired
//    StockTransBusinessImpl stockTransBusinessImpl;
//
//    @Autowired
//    StockTransDetailBusinessImpl stockTransDetailBusinessImpl;
//
//    @Autowired
//    StockGoodsTotalBusinessImpl stockGoodsTotalBusinessImpl;
//    
//    @Context
//    HttpServletRequest request;
//	
//	@Autowired
//	private UserRoleBusinessImpl userRoleBusinessImpl;
//    
//    @Override
//    public Response getStockTrans() {
//        List<StockTransDTO> ls = stockTransBusinessImpl.getAll();
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
//    public Response getStockTransById(Long id) {
//        StockTransDTO obj = (StockTransDTO) stockTransBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(obj).build();
//        }
//    }
//
//    @Override
//    public Response updateStockTrans(StockTransDTO obj) {
//        Long id = stockTransBusinessImpl.update(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok().build();
//        }
//
//    }
//
//    @Override
//    public Response addStockTrans(StockTransDTO obj) {
//        Long id = stockTransBusinessImpl.save(obj);
//        if (id == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(Response.Status.CREATED).build();
//        }
//    }
//
//    @Override
//    public Response deleteStockTrans(Long id) {
//        StockTransDTO obj = (StockTransDTO) stockTransBusinessImpl.getOneById(id);
//        if (obj == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            stockTransBusinessImpl.delete(obj);
//            return Response.ok(Response.Status.NO_CONTENT).build();
//        }
//    }
//
//	@Override
//	public Response doSearchImportNote(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		DataListDTO data = stockTransBusinessImpl.doSearchImportNote(obj);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(data).build();
//        }
//	}
//
//	@Override
//	public Response getStockTransDetail(Long id) {
//		// TODO Auto-generated method stub
//		StockTransDTO data = stockTransBusinessImpl.getStockTransDetail(id);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//	}
//
//	@Override
//	public Response saveImportNote(StockTransDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		try{
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			obj.setCreatedBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCreatedByName(objUser.getVpsUserInfo().getFullName());
//			obj.setCreatedDeptId(objUser.getVpsUserInfo().getDepartmentId());
//			obj.setCreatedDeptName(objUser.getVpsUserInfo().getDepartmentName());
//			obj.setCreatedDate(new Date());
//			try {
//				stockTransBusinessImpl.saveImportNote(obj,request);
//				 return Response.ok(Response.Status.CREATED).build();
//			} catch (BusinessException e) {
//				// TODO: handle exception
//				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//			}
//		}catch(BusinessException e){
//			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//		}
//		
//		
//	}
//	//Hàm tìm kiếm báo cáo xuất kho đang đi đường
//	@Override
//	public Response doSearchExport(StockTransDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//				DataListDTO data;
//				try{
//				data= stockTransBusinessImpl.doSearchExport(obj,request);
//		        if (data == null) {
//		            return Response.status(Response.Status.BAD_REQUEST).build();
//		        } else {
//		            return Response.ok(data).build();
//		        }
//				}catch (IllegalArgumentException e) {
//					return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//				}
//	}
////end
//	@Override
//	public Response saveAndRealImportNote(StockTransDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//		obj.setCreatedBy(objUser.getVpsUserInfo().getSysUserId());
//		obj.setCreatedByName(objUser.getVpsUserInfo().getFullName());
//		obj.setCreatedDeptId(objUser.getVpsUserInfo().getDepartmentId());
//		obj.setCreatedDeptName(objUser.getVpsUserInfo().getDepartmentName());
//		obj.setCreatedDate(new Date());
//		try {
//			String returnStr = stockTransBusinessImpl.saveAndRealImportNote(obj,request);
//			if (returnStr == null) {
//	            return Response.status(Response.Status.BAD_REQUEST).build();
//	        } else {
//	            return Response.ok(Response.Status.CREATED).build();
//	        }
//		}catch (IllegalStateException e) {
//			// TODO: handle exception
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}catch (BusinessException e) {
//			// TODO: handle exception
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//
//	@Override
//	public Response realImportNote(StockTransDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		try {
//			String returnStr = stockTransBusinessImpl.realImportNote(obj);
//			if (returnStr == null) {
//	            return Response.status(Response.Status.BAD_REQUEST).build();
//	        } else {
//	        	return Response.ok(Response.Status.CREATED).build();
//	        }
//		}catch (IllegalStateException e) {
//			// TODO: handle exception
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//
//	@Override
//	public Response updateImportNote(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		try{
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			obj.setCancelBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCancelByName(objUser.getVpsUserInfo().getFullName());
//			Long id = stockTransBusinessImpl.updateImportNote(obj,objUser);
//	        if (id == 0l) {
//	            return Response.status(Response.Status.BAD_REQUEST).build();
//	        } else {
//	        	return Response.ok(Response.Status.CREATED).build();
//	        }
//		}catch (IllegalStateException e) {
//			// TODO: handle exception
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//	
//	@Override
//	public Response doSearchExportStatement(StockTransDTO obj) throws Exception{
//		// TODO Auto-generated method stub
//		try{
//				DataListDTO data = stockTransBusinessImpl.doSearchStatement(obj, request);
//		        if (data == null) {
//		            return Response.status(Response.Status.BAD_REQUEST).build();
//		        } else {
//		            return Response.ok(data).build();
//		        }
//	}catch (IllegalArgumentException e) {
//        return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//    }
//	}
//
//	@Override
//	public Response getGoodsInfoFromAlternativeStockCode(String code) {
//		// TODO Auto-generated method stub
//		List<String> lst = stockTransBusinessImpl.getGoodsInfoFromAlternativeStockCode(code);
//		if (lst == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//            return Response.ok(lst).build();
//        }
//	}
//	
//	@Override
//	public Response getStockByOrder(StockTransDTO obj) {
//		StockTransDTO data = stockTransBusinessImpl.getStockByOrder(obj);
//        return Response.ok(data).build();
//	}
//
//
//	@Override
//	public Response removeStockTrans(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		try {
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			obj.setCancelBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCancelByName(objUser.getVpsUserInfo().getFullName());
//			boolean bl = stockTransBusinessImpl.removeImportStockTrans(obj,objUser);
//			if(bl){
//				return Response.ok(Response.Status.CREATED).build();
//			}
//			return null;
//		}catch(IllegalArgumentException e){
//			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//		}
//		
//	}
//	
//	
//	@Override
//	public Response removeExportStockTrans(StockTransDTO obj) {
//		if("2".equals(obj.getSignState())){
//			return Response.ok().entity(Collections.singletonMap("error", "Phiếu đã được ký duyệt")).build();
//		}
//		boolean isCancelOK = stockTransBusinessImpl.removeExportStockTrans(obj);
//		if(!isCancelOK){
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok().build();
//		}
//	}
//
//	
//	@Override
//	public Response realExportNote(StockTransDTO obj) throws Exception {
//		try {
//			KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//			obj.setRealIeUserId(objUser.getVpsUserInfo().getSysUserId().toString());
//			obj.setRealIeUserName(objUser.getVpsUserInfo().getFullName());
//			 stockTransBusinessImpl.realExportNote(obj,request);
//			return Response.ok(obj).build();
//		} catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//		
//	}
//
//	@Override
//	public Response getStockTransForAutoComplete(StockTransDTO obj) {
//		List<StockTransDTO> ls = stockTransBusinessImpl.getStockTransForAutoComplete(obj);
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
//	}
//
//	/**
//	  * xu ly import update quan ly phieu xuat kho -->Huy
//	  */
//	@Override
//	public Response updateImportStockTran(StockTransDTO obj) throws Exception {
//		try {
//		Long update = stockTransBusinessImpl.updateImportStockTran(obj);
//		if(update == 0l){
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(update).build();
//		}
//		}catch (IllegalArgumentException e) {
//			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//		
//	}
//
//	
//	@Override
//	public Response saveInforImport(StockTransDTO obj) throws Exception {
//		
//		try {
//	          return Response.ok(stockTransBusinessImpl.saveInforImport(obj,request)).build();
//		} catch (IllegalArgumentException e) {
//				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
//		}
//	}
//
//	@Override
//	public Response getAllImportNote(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		return Response.ok(stockTransBusinessImpl.getAllImportNote(obj)).build();
//	}
//
//	@Override
//	public Response getAllExportManagement(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		return Response.ok(stockTransBusinessImpl.getAllExportManagement(obj)).build();
//	}
//
//	@Override
//	public Response exportExcelBKVT(List<Long> ids) throws Exception {
//		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//		return Response.ok(Collections.singletonMap("fileName", stockTransBusinessImpl.exportExcelBKVT(ids,objUser.getVpsUserInfo().getFullName()))).build();
//	}
//
//}
