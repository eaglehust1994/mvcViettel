///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.business.StockBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
//import com.viettel.wms.dto.AppParamDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.StockCellDTO;
//import com.viettel.wms.dto.StockDTO;
//
///**
// *
// * @author HungLQ9
// */
//public class StockRsServiceImpl implements StockRsService {
//
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
//	@Context
//	HttpServletRequest request;
//	@Autowired
//	private UserRoleBusinessImpl userRoleBusinessImpl;
//	@Autowired
//	StockBusinessImpl stockBusinessImpl;
//
//	@Override
//	public Response getByUserId(Long userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Response doSearch(StockDTO obj) {
//		DataListDTO data = stockBusinessImpl.doSearch(obj);
//		return Response.ok(data).build();
//	}
//
//	@Override
//	public Response remove(StockDTO obj) {
//		obj.setStatus("0");
//		Long id = stockBusinessImpl.update(obj);
//		if (id == 0l) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok().build();
//		}
//	}
//
//	@Override
//	public Response add(StockDTO obj) {
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
//		try {
//			obj.setCreatedBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setCreatedDate(new Date());
//			Long id = stockBusinessImpl.createStock(obj);
//			return Response.ok(Response.Status.CREATED).build();
//		} catch (Exception e) {
//			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//		}
//		
//	}
//
//	@Override
//	public Response update(StockDTO obj) {
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request);
//		try {
//			obj.setUpdatedBy(objUser.getVpsUserInfo().getSysUserId());
//			obj.setUpdatedDate(new Date());
//			obj.setLevelST(obj.getLevelST());
//			Long id = stockBusinessImpl.updateStock(obj, objUser);
//			if (id == 0l) {
//				return Response.status(Response.Status.BAD_REQUEST).build();
//			} else {
//				return Response.ok(id).build();
//			}
//		} catch (Exception e) {
//			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
//		}
//
//		
//	}
//
//	@Override
//	public Response getStock() {
//		List<StockDTO> ls = stockBusinessImpl.getAll();
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
//	public Response getForAutoComplete(AppParamDTO obj) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public Response getForAutoCompleteStock(StockDTO obj) {
//		// TODO Auto-generated method stub
//		return Response.ok(stockBusinessImpl.getForAutoCompleteStock(obj)).build();
//	}
//
//	@Override
//	public Response getStocksForAutocomplete(StockDTO obj) {
//		// TODO Auto-generated method stub
//		List<StockDTO> ls = stockBusinessImpl.getStocksForAutocomplete(obj);
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
//	public Response getStocksForAutocompleteDropDown(StockDTO obj) {
//		// TODO Auto-generated method stub
//		return Response.ok(stockBusinessImpl.getStocksForAutocompleteDropDown(obj)).build();
//	}
//
//	@Override
//	public Response doSearchStockInPopUp(StockDTO obj) {
//		// TODO Auto-generated method stub
//		return Response.ok(stockBusinessImpl.doSearchStockInPopUp(obj)).build();
//	}
//	
//	@Override
//	public Response doSearchGoods(GoodsDTO obj) {
//		// TODO Auto-generated method stub
//		return Response.ok(stockBusinessImpl.doSearchGoods(obj)).build();
//	}
//	
//	@Override
//	public Response doSearchGoodsInPopup(GoodsDTO obj) {
//		DataListDTO data= stockBusinessImpl.doSearchGoodsInPopup(obj);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//	}
//
//
//	@Override
//	public Response getListByNameOrCode(StockDTO obj) {
//		return Response.ok(stockBusinessImpl.getListByNameOrCode(obj)).build();
//	}
//
//	@Override
//	public Response getForAutoCompleteStockDomain(StockDTO obj) {
//		
//		return Response.ok(stockBusinessImpl.getForAutoCompleteStockDomain(obj, request)).build();
//	}
//
//	@Override
//	public Response doSearchStockInPopUpDomain(StockDTO obj) {
//		return Response.ok(stockBusinessImpl.doSearchStockInPopUpDomain(obj, request)).build();
//	}
//
//	@Override
//	public Response getChangeAreaByStock(StockDTO obj) {
//		List<StockCellDTO> data= stockBusinessImpl.getChangeAreaByStock(obj);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//	}
//	@Override
//	public Response saveListStockCell(StockDTO obj) {
//		Long data= stockBusinessImpl.saveListStockCell(obj);
//        if (data == 0l) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//	}
//	
//}
