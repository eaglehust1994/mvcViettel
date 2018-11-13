//package com.viettel.wms.business;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Response;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.ss.util.RegionUtil;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.google.common.collect.Lists;
//import com.viettel.ktts.vps.VpsPermissionChecker;
//import com.viettel.ktts2.common.BusinessException;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.bo.StockTransBO;
//import com.viettel.wms.constant.Constants;
////import com.viettel.wms.dao.AppParamDAO;
//import com.viettel.wms.dao.GoodsDAO;
//import com.viettel.wms.dao.GoodsTypeDAO;
//import com.viettel.wms.dao.OrderDAO;
//import com.viettel.wms.dao.OrderGoodsDAO;
//import com.viettel.wms.dao.OrderGoodsDetailDAO;
//import com.viettel.wms.dao.StockDAO;
//import com.viettel.wms.dao.StockGoodsSerialDAO;
//import com.viettel.wms.dao.StockGoodsTotalDAO;
//import com.viettel.wms.dao.StockTransDAO;
//import com.viettel.wms.dao.StockTransDetailDAO;
//import com.viettel.wms.dao.StockTransDetailSerialDAO;
//import com.viettel.wms.dto.AppParamDTO;
//import com.viettel.wms.dto.CommonDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.GoodsTypeDTO;
//import com.viettel.wms.dto.OrderDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDetailDTO;
//import com.viettel.wms.dto.StockDTO;
//import com.viettel.wms.dto.StockDailyRemainDTO;
//import com.viettel.wms.dto.StockGoodsDTO;
//import com.viettel.wms.dto.StockGoodsSerialDTO;
//import com.viettel.wms.dto.StockGoodsTotalDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//import com.viettel.wms.dto.StockTransDetailSerialDTO;
//import com.viettel.wms.dto.SynchERPDTO;
///*import com.viettel.wms.dto.SynchERPDTO;
//*/
//import com.viettel.wms.rest.StockDailyRemainRsServiceImpl;
//
//@Service("stockTransBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class StockTransBusinessImpl extends BaseFWBusinessImpl<StockTransDAO, StockTransDTO, StockTransBO>
//		implements StockTransBusiness {
//	static Logger LOGGER = LoggerFactory.getLogger(StockDailyRemainRsServiceImpl.class);
//	@Value("${folder_upload}")
//	private String folder2Upload;
//	@Autowired
//	private StockTransDAO stockTransDAO;
//	
//	@Autowired
//	private CommonBusiness commonBusiness;
//
//	@Autowired
//	private StockTransDetailDAO stockTransDetailDAO;
//
//	@Autowired
//	private StockTransDetailSerialDAO stockTransDetailSerialDAO;
//
//	@Autowired
//	private OrderGoodsDAO orderGoodsDAO;
//	
////	@Autowired
////	private AppParamDAO appParamDAO;
//
//	@Autowired
//	private OrderGoodsDetailDAO orderGoodsDetailDAO;
//
//	@Autowired
//	private StockGoodsSerialDAO stockGoodsSerialDAO;
//
//	@Autowired
//	private StockGoodsTotalDAO stockGoodsTotalDAO;
//	
//	@Autowired
//	private OrderDAO orderDAO;
//
//	@Autowired
//	private StockTransDetailBusinessImpl stockTransDetailBusinessImpl;
//
//	@Autowired
//	private StockTransDetailSerialBusinessImpl stockTransDetailSerialBusinessImpl;
//
//	@Autowired
//	private StockGoodsBusinessImpl stockGoodsBusinessImpl;
//
//	@Autowired
//	private StockGoodsSerialBusinessImpl stockGoodSerialBusinessImpl;
//
//	@Autowired
//	private StockGoodsTotalBusinessImpl stockGoodsTotalBusinessImpl;
//	
//	@Autowired
//	private OrderBusinessImpl orderBusinessImpl;
//	
//	@Autowired
//	private StockDAO stockDAO;
//	
//	@Autowired
//	private GoodsDAO goodsDAO;
//	
//	@Autowired
//	private GoodsTypeDAO goodsTypeDAO;
//	
//	 @Autowired
//	private StockBusinessImpl stockBusinessImpl;
//	@Autowired
//	private UserRoleBusinessImpl userRoleBusinessImpl;
//
//	    
//
//	public StockTransBusinessImpl() {
//		tModel = new StockTransBO();
//		tDAO = stockTransDAO;
//	}
//
//	@Override
//	public StockTransDAO gettDAO() {
//		return stockTransDAO;
//	}
//
//	@Override
//	public long count() {
//		return stockTransDAO.count("StockTransBO", null);
//	}
//	
//	//Tim kiem ban ghi phieu nhap kho - NgocCX 
//	@Override
//	public DataListDTO doSearchImportNote(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		List<StockTransDTO> ls = stockTransDAO.doSearchImportNote(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//
//	}
//
//	//Chi tiet phieu nhap kho - NgocCX
//	@Override
//	public StockTransDTO getStockTransDetail(Long id) {
//		// TODO Auto-generated method stub
//		StockTransDTO data = stockTransDAO.getStockTransDetail(id);
//		return data;
//	}
//
//	/*Ghi lai phieu nhap kho - NgocCX*/
//	@Override
//	@Transactional
//	public void saveImportNote(StockTransDTO obj,HttpServletRequest request) throws Exception {
//		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.CREATE, Constants.AdResourceKey.STOCK_TRANS, obj.getStockId(), request)){
//			throw new BusinessException("Bạn không có quyền tạo phiếu với kho hàng " +obj.getStockCode());
//		}
//		if(!obj.getSignState().equals("3")){
//			throw new IllegalArgumentException("Yêu cầu chưa được ký duyệt !");
//		}
//		StockTransDTO checkDto = new StockTransDTO();
//		checkDto.setCode(obj.getCode());
//		List<StockTransDTO> lstCheckDto = stockTransDAO.checkDupOrderCode(checkDto);
//		if(lstCheckDto.size()>0){
//			throw new BusinessException("Mã phiếu đã tồn tại");
//		}
//		AppParamDTO objAP = new AppParamDTO();
//		objAP.setStatus("1");
//		objAP.setParType("IMPORT_ORDER_TYPE");
////		List<AppParamDTO> lstAP = appParamDAO.getForComboBox(objAP);
//		List<AppParamDTO> lstAP =new ArrayList<AppParamDTO>();
//		for(int i =0; i<lstAP.size();i++){
//			if(obj.getBussinessType().equals(lstAP.get(i).getCode())){
//				obj.setBussinessTypeName(lstAP.get(i).getName());
//				break;
//			}
//		}
//		obj.setCreatedDate(new Date());
//		
//		obj.setStatus("1");
//		obj.setSignState("1");
//		Long stockTransId = save(obj);
//		List<OrderGoodsDTO> lstGoods = orderGoodsDAO.doSearchGoods(obj.getOrderGoodsDTO());
//		List<StockTransDetailDTO> lstStockTransDetail = new ArrayList<>();
//		List<StockTransDetailSerialDTO> lstStockTransDetailSerial = new ArrayList<>();
//			for (int i = 0; i < lstGoods.size(); i++) {
//				StockTransDetailDTO stDto = new StockTransDetailDTO();
//				stDto.setStockTransId(stockTransId);
//				stDto.setOrderId(lstGoods.get(i).getOrderId());
//				stDto.setGoodsId(lstGoods.get(i).getGoodsId());
//				stDto.setGoodsCode(lstGoods.get(i).getGoodsCode());
//				stDto.setGoodsName(lstGoods.get(i).getGoodsName());
//				stDto.setGoodsUnitName(lstGoods.get(i).getGoodsUnitName());
//				stDto.setAmountOrder(lstGoods.get(i).getAmount());
//				stDto.setAmountReal(lstGoods.get(i).getAmount());
//				stDto.setGoodsState(lstGoods.get(i).getGoodsState());
//				stDto.setGoodsStateName(lstGoods.get(i).getGoodsStateName());
//				stDto.setGoodsIsSerial(lstGoods.get(i).getGoodsIsSerial());
//				stDto.setGoodsUnitId(lstGoods.get(i).getGoodsUnitId());
//				stDto.setGoodsType(lstGoods.get(i).getGoodsType());
//				stDto.setGoodsTypeName(lstGoods.get(i).getGoodsTypeName());
//				stDto.setTotalPrice(lstGoods.get(i).getTotalPrice());
//				lstStockTransDetail.add(stDto);
//
//				Long stockTransDetailId = stockTransDetailBusinessImpl.save(lstStockTransDetail.get(i));
//
//				List<OrderGoodsDetailDTO> lstGoodsDetail = obj.getListOrderGoodsDetailDTO();
//				if(lstGoodsDetail.size()==0){
//					lstGoodsDetail = orderGoodsDetailDAO.doSearchGoodsDetail(lstGoods.get(i).getOrderGoodsId());
//				}
//				for (int j = 0; j < lstGoodsDetail.size(); j++) {
//					if(lstGoodsDetail.get(j).getOrderGoodsId().equals(lstGoods.get(i).getOrderGoodsId())){
//						StockTransDetailSerialDTO stdrDTO = new StockTransDetailSerialDTO();
//						stdrDTO.setStockTransId(stockTransId);
//						stdrDTO.setStockTransDetailId(stockTransDetailId);
//						stdrDTO.setGoodsId(lstGoods.get(i).getGoodsId());
//						stdrDTO.setSerial(lstGoodsDetail.get(j).getSerial());
//						stdrDTO.setContractCode(lstGoodsDetail.get(j).getContractCode());
//						stdrDTO.setPartNumber(lstGoodsDetail.get(j).getPartNumber());
//						stdrDTO.setManufacturerId(lstGoodsDetail.get(j).getManufacturerId());
//						stdrDTO.setProducingCountryId(lstGoodsDetail.get(j).getProducingCountryId());
//						stdrDTO.setManufacturerName(lstGoodsDetail.get(j).getManufacturerName());
//						stdrDTO.setProducingCountryName(lstGoodsDetail.get(j).getProducingCountryName());
//						stdrDTO.setPrice(lstGoodsDetail.get(j).getPrice());
//						stdrDTO.setCellCode(lstGoodsDetail.get(j).getCellCode());
//						stockTransDetailSerialBusinessImpl.save(stdrDTO);
//						lstStockTransDetailSerial.add(stdrDTO);
//					}
//				}
//			}
//
//		OrderDTO oDto = new OrderDTO();
//		oDto.setOrderId(obj.getOrderId());
//		oDto.setStatus("2");
//		orderBusinessImpl.updateStatusOrder(oDto);
//	}
////Hàm xử lý logic Báo cáo phiếu xuất kho đang đi đường
//	@Override
//	@Transactional
//	public DataListDTO doSearchExport(StockTransDTO obj,HttpServletRequest request) throws Exception {
//		// TODO Auto-generated method stub
//		String err="";
//    	for(Long id:obj.getListStockId()){
//    		StockDTO stockDTO=(StockDTO) stockBusinessImpl.getOneById(id);
//    		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,id, request)){
//    			err=StringUtils.isNotEmpty(err)? (err+";"+id):("Bạn không có quyền xem báo cáo tại kho "+id);
//    		}
//    	}
//    	
//    	if(StringUtils.isNotEmpty(err)){
//    		throw new IllegalArgumentException(err);
//    	}
//    	
//    	if(obj.getListStockId().size()==0){
//    		List<Long> listId=commonBusiness.getListDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK, request);
//    		obj.setListStockId(listId);
//    	}
//		List<StockTransDTO> ls = stockTransDAO.doSearchExport(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
////End
//	/*Ghi lai va thuc nhap phieu nhap kho - NgocCX*/
//	@Override
//	@Transactional
//	public String saveAndRealImportNote(StockTransDTO obj,HttpServletRequest request) throws Exception {
//		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.CREATE, Constants.AdResourceKey.STOCK_TRANS, obj.getStockId(), request)){
//			throw new BusinessException("Bạn không có quyền tạo phiếu với kho hàng " +obj.getStockCode());
//		}
//		/*if(!obj.getSignState().equals("3")){
//			throw new IllegalArgumentException("Yêu cầu chưa được ký duyệt !");
//		}*/
//		StockTransDTO checkDto = new StockTransDTO();
//		checkDto.setCode(obj.getCode());
//		List<StockTransDTO> lstCheckDto = stockTransDAO.checkDupOrderCode(checkDto);
//		if(lstCheckDto.size()>0){
//			throw new BusinessException("Mã phiếu đã tồn tại");
//		}
//		AppParamDTO objAP = new AppParamDTO();
//		objAP.setStatus("1");
//		objAP.setParType("IMPORT_ORDER_TYPE");
////		List<AppParamDTO> lstAP = appParamDAO.getForComboBox(objAP);
//		List<AppParamDTO> lstAP = new ArrayList<AppParamDTO>();
//		for(int i =0; i<lstAP.size();i++){
//			if(obj.getBussinessType().equals(lstAP.get(i).getCode())){
//				obj.setBussinessTypeName(lstAP.get(i).getName());
//				break;
//			}
//		}
//		obj.setCreatedDate(new Date());
//		obj.setRealIeTransDate(new Date());
//		obj.setStatus("2");
//		Long stockTransId = save(obj);
//		List<OrderGoodsDTO> lstGoods = orderGoodsDAO.doSearchGoods(obj.getOrderGoodsDTO());
//		List<StockTransDetailDTO> lstStockTransDetail = new ArrayList<>();
//		List<StockTransDetailSerialDTO> lstStockTransDetailSerial = new ArrayList<>();
//		List<StockGoodsDTO> lstStockGoods = new ArrayList<>();
//		List<StockGoodsSerialDTO> lstStockGoodsSerial = new ArrayList<>();
//		List<StockGoodsSerialDTO> lstStockGoodsAll = new ArrayList<>();
//		//Luu vao bang Stock_trans_detail va Stock_trans_detail_serial 
//		for (int i = 0; i < lstGoods.size(); i++) {
//			StockTransDetailDTO stDto = new StockTransDetailDTO();
//			stDto.setStockTransId(stockTransId);
//			stDto.setOrderId(lstGoods.get(i).getOrderId());
//			stDto.setGoodsId(lstGoods.get(i).getGoodsId());
//			stDto.setGoodsType(lstGoods.get(i).getGoodsType());
//			stDto.setGoodsTypeName(lstGoods.get(i).getGoodsTypeName());
//			stDto.setGoodsCode(lstGoods.get(i).getGoodsCode());
//			stDto.setGoodsName(lstGoods.get(i).getGoodsName());
//			stDto.setGoodsUnitId(lstGoods.get(i).getGoodsUnitId());
//			stDto.setGoodsUnitName(lstGoods.get(i).getGoodsUnitName());
//			stDto.setAmountOrder(lstGoods.get(i).getAmount());
//			stDto.setAmountReal(lstGoods.get(i).getAmount());
//			stDto.setGoodsIsSerial(lstGoods.get(i).getGoodsIsSerial());
//			stDto.setGoodsState(lstGoods.get(i).getGoodsState());
//			stDto.setGoodsStateName(lstGoods.get(i).getGoodsStateName());
//			stDto.setTotalPrice(lstGoods.get(i).getTotalPrice());
//			lstStockTransDetail.add(stDto);
//
//			Long stockTransDetailId = stockTransDetailBusinessImpl.save(lstStockTransDetail.get(i));
//
//			List<OrderGoodsDetailDTO> lstGoodsDetail = obj.getListOrderGoodsDetailDTO();
//			if(lstGoodsDetail.size()==0){
//				lstGoodsDetail = orderGoodsDetailDAO.doSearchGoodsDetail(lstGoods.get(i).getOrderGoodsId());
//			}
//			if(lstGoodsDetail.size()>0){
//				for (int j = 0; j < lstGoodsDetail.size(); j++) {
//					if(lstGoodsDetail.get(j).getOrderGoodsId().equals(lstGoods.get(i).getOrderGoodsId())){
//						StockTransDetailSerialDTO stdrDTO = new StockTransDetailSerialDTO();
//						stdrDTO.setStockTransId(stockTransId);
//						stdrDTO.setStockTransDetailId(stockTransDetailId);
//						stdrDTO.setGoodsId(lstGoods.get(i).getGoodsId());
//						stdrDTO.setSerial(lstGoodsDetail.get(j).getSerial());
//						stdrDTO.setContractCode(lstGoodsDetail.get(j).getContractCode());
//						stdrDTO.setPartNumber(lstGoodsDetail.get(j).getPartNumber());
//						stdrDTO.setManufacturerId(lstGoodsDetail.get(j).getManufacturerId());
//						stdrDTO.setProducingCountryId(lstGoodsDetail.get(j).getProducingCountryId());
//						stdrDTO.setManufacturerName(lstGoodsDetail.get(j).getManufacturerName());
//						stdrDTO.setProducingCountryName(lstGoodsDetail.get(j).getProducingCountryName());
//						stdrDTO.setPrice(lstGoodsDetail.get(j).getPrice());
//						stdrDTO.setCellCode(lstGoodsDetail.get(j).getCellCode());
//						stockTransDetailSerialBusinessImpl.save(stdrDTO);
//						lstStockTransDetailSerial.add(stdrDTO);
//						
//						// End Luu vao bang Stock_trans_detail va Stock_trans_detail_serial 
//						
//						//Luu vao bang Stock_Goods_serial, Stock_goods_total, Stock_goods
//						StockGoodsSerialDTO sgsDto = new StockGoodsSerialDTO();
//						sgsDto.setOrderId(lstGoods.get(i).getOrderId());
//						sgsDto.setGoodsId(lstGoods.get(i).getGoodsId());
//						sgsDto.setStockId(obj.getStockId());
//						sgsDto.setGoodsCode(lstGoods.get(i).getGoodsCode());
//						sgsDto.setGoodsName(lstGoods.get(i).getGoodsName());
//						sgsDto.setGoodsUnitId(lstGoods.get(i).getGoodsUnitId());
//						sgsDto.setGoodsUnitName(lstGoods.get(i).getGoodsUnitName());
//						sgsDto.setGoodsType(Long.parseLong(lstGoods.get(i).getGoodsType()));
//						sgsDto.setGoodsTypeName(lstGoods.get(i).getGoodsTypeName());
//						sgsDto.setAmount(lstGoods.get(i).getAmount());
//						sgsDto.setGoodsState(lstGoods.get(i).getGoodsState());
//						sgsDto.setGoodsStateName(lstGoods.get(i).getGoodsStateName());
//						sgsDto.setGoodsIsSerial(lstGoods.get(i).getGoodsIsSerial());
//						sgsDto.setTotalPrice(lstGoods.get(i).getTotalPrice());
//						sgsDto.setSerial(lstGoodsDetail.get(j).getSerial());
//						sgsDto.setContractCode(lstGoodsDetail.get(j).getContractCode());
//						sgsDto.setPartNumber(lstGoodsDetail.get(j).getPartNumber());
//						sgsDto.setManufacturerId(lstGoodsDetail.get(j).getManufacturerId());
//						sgsDto.setProducingCountryId(lstGoodsDetail.get(j).getProducingCountryId());
//						sgsDto.setManufacturerName(lstGoodsDetail.get(j).getManufacturerName());
//						sgsDto.setProducingCountryName(lstGoodsDetail.get(j).getProducingCountryName());
//						//sgsDto.setGoodsIsSerial(lstGoodsDetail.get(j).getGoodsIsSerial());
//						sgsDto.setPrice(lstGoodsDetail.get(j).getPrice());
//						
//						lstStockGoodsAll.add(sgsDto);
//					}
//					
//				}
//			}else{
//				StockGoodsSerialDTO sgsDto = new StockGoodsSerialDTO();
//				sgsDto.setOrderId(lstGoods.get(i).getOrderId());
//				sgsDto.setGoodsId(lstGoods.get(i).getGoodsId());
//				sgsDto.setStockId(obj.getStockId());
//				sgsDto.setGoodsCode(lstGoods.get(i).getGoodsCode());
//				sgsDto.setGoodsName(lstGoods.get(i).getGoodsName());
//				sgsDto.setGoodsType(Long.parseLong(lstGoods.get(i).getGoodsType()));
//				sgsDto.setGoodsTypeName(lstGoods.get(i).getGoodsTypeName());
//				sgsDto.setGoodsUnitId(lstGoods.get(i).getGoodsUnitId());
//				sgsDto.setGoodsUnitName(lstGoods.get(i).getGoodsUnitName());
//				sgsDto.setAmount(lstGoods.get(i).getAmount());
//				sgsDto.setGoodsState(lstGoods.get(i).getGoodsState());
//				sgsDto.setGoodsStateName(lstGoods.get(i).getGoodsStateName());
//				sgsDto.setGoodsIsSerial("0");
//				lstStockGoodsAll.add(sgsDto);
//			}
//			
//		}
//
//		for (int i = 0; i < lstStockGoodsAll.size(); i++) {
//
//			//Insert hang khong serial vao bang Stock_goods
//			if (lstStockGoodsAll.get(i).getGoodsIsSerial().equals("0")) {
//				StockGoodsDTO sgDto = new StockGoodsDTO();
//				sgDto.setOrderId(lstStockGoodsAll.get(i).getOrderId());
//				sgDto.setGoodsId(lstStockGoodsAll.get(i).getGoodsId());
//				sgDto.setStockId(lstStockGoodsAll.get(i).getStockId());
//				sgDto.setGoodsCode(lstStockGoodsAll.get(i).getGoodsCode());
//				sgDto.setGoodsName(lstStockGoodsAll.get(i).getGoodsName());
//				sgDto.setGoodsUnitId(lstStockGoodsAll.get(i).getGoodsUnitId());
//				sgDto.setGoodsUnitName(lstStockGoodsAll.get(i).getGoodsUnitName());
//				sgDto.setGoodsType(lstStockGoodsAll.get(i).getGoodsType());
//				sgDto.setGoodsTypeName(lstStockGoodsAll.get(i).getGoodsTypeName());
//				sgDto.setAmount(lstStockGoodsAll.get(i).getAmount());
//				sgDto.setGoodsState(lstStockGoodsAll.get(i).getGoodsState());
//				sgDto.setGoodsStateName(lstStockGoodsAll.get(i).getGoodsStateName());
//				sgDto.setGoodsIsSerial(lstStockGoodsAll.get(i).getGoodsIsSerial());
//				sgDto.setContractCode(lstStockGoodsAll.get(i).getContractCode());
//				sgDto.setPartNumber(lstStockGoodsAll.get(i).getPartNumber());
//				sgDto.setManufacturerName(lstStockGoodsAll.get(i).getManufacturerName());
//				sgDto.setManufacturerId(lstStockGoodsAll.get(i).getManufacturerId());
//				sgDto.setProducingCountryName(lstStockGoodsAll.get(i).getProducingCountryName());
//				sgDto.setProducingCountryId(lstStockGoodsAll.get(i).getProducingCountryId());
//				sgDto.setPrice(lstStockGoodsAll.get(i).getPrice());
//				sgDto.setTotalPrice(lstStockGoodsAll.get(i).getTotalPrice());
//				sgDto.setImportDate(new Date());
//				sgDto.setImportStockTransId(stockTransId);
//				sgDto.setStatus("1");
//				lstStockGoods.add(sgDto);
//				stockGoodsBusinessImpl.save(sgDto);
//			} else {
//				StockGoodsSerialDTO sgsDto = new StockGoodsSerialDTO();
//				sgsDto.setOrderId(lstStockGoodsAll.get(i).getOrderId());
//				sgsDto.setGoodsId(lstStockGoodsAll.get(i).getGoodsId());
//				sgsDto.setStockId(lstStockGoodsAll.get(i).getStockId());
//				sgsDto.setGoodsCode(lstStockGoodsAll.get(i).getGoodsCode());
//				sgsDto.setGoodsName(lstStockGoodsAll.get(i).getGoodsName());
//				sgsDto.setGoodsUnitId(lstStockGoodsAll.get(i).getGoodsUnitId());
//				sgsDto.setGoodsUnitName(lstStockGoodsAll.get(i).getGoodsUnitName());
//				sgsDto.setGoodsType(lstStockGoodsAll.get(i).getGoodsType());
//				sgsDto.setGoodsTypeName(lstStockGoodsAll.get(i).getGoodsTypeName());
//				sgsDto.setAmount(lstStockGoodsAll.get(i).getAmount());
//				sgsDto.setGoodsState(lstStockGoodsAll.get(i).getGoodsState());
//				sgsDto.setGoodsStateName(lstStockGoodsAll.get(i).getGoodsStateName());
//				sgsDto.setGoodsIsSerial(lstStockGoodsAll.get(i).getGoodsIsSerial());
//				sgsDto.setContractCode(lstStockGoodsAll.get(i).getContractCode());
//				sgsDto.setPartNumber(lstStockGoodsAll.get(i).getPartNumber());
//				sgsDto.setManufacturerName(lstStockGoodsAll.get(i).getManufacturerName());
//				sgsDto.setManufacturerId(lstStockGoodsAll.get(i).getManufacturerId());
//				sgsDto.setProducingCountryName(lstStockGoodsAll.get(i).getProducingCountryName());
//				sgsDto.setProducingCountryId(lstStockGoodsAll.get(i).getProducingCountryId());
//				sgsDto.setSerial(lstStockGoodsAll.get(i).getSerial());
//				sgsDto.setPrice(lstStockGoodsAll.get(i).getPrice());
//				sgsDto.setImportStockTransId(stockTransId);
//				sgsDto.setStatus("1");
//				lstStockGoodsSerial.add(sgsDto);
//			}
//		}
//
//		//Insert hang co serial vao bang Stock_goods_serial
//		if(lstStockGoodsSerial.size()>0){
//			List<StockGoodsSerialDTO> lstSerialAndId = stockGoodsSerialDAO.doSearchSerialAndId();
//			saveToStockGoodsSerial(lstSerialAndId, lstStockGoodsSerial);
//		}
//
//		List<StockGoodsTotalDTO> lstIdOfGoodStateAndStock = stockGoodsTotalDAO.doSearchIdOfGoodStateAndStock();
//		saveToStockGoodsTotal(lstIdOfGoodStateAndStock, lstStockGoods, lstStockGoodsSerial);
//		//End luu vao bang Stock_Goods_serial, Stock_goods_total, Stock_goods
//		
//		//Cap nhat trang thai yeu cau
//		OrderDTO oDto = new OrderDTO();
//		oDto.setOrderId(obj.getOrderId());
//		oDto.setStatus("3");
//		Boolean bl = orderBusinessImpl.updateStatusOrder(oDto);
//		//End cap nhat trang thai yeu cau
//		if(bl){
//			return "SUCCESS";
//		}else{
//			return null;
//		}
//	}
//
//	//Luu vao bang stock_goods_total voi cac tham so list id, trang thai, kho cua hang hoa, list hang hoa co serial, list hang hoa khong serial
//	//Muc dich cua ham nay la de giam so dong code cua phan thuc nhap
//	private void saveToStockGoodsTotal(List<StockGoodsTotalDTO> lstIdOfGoodStateAndStock,
//			List<StockGoodsDTO> lstStockGoods, List<StockGoodsSerialDTO> lstStockGoodsSerial) {
//		List<Long> lstIdOfGood = new ArrayList<>();
//		List<String> lstGoodState = new ArrayList<>();
//		List<Long> lstIdOfStock = new ArrayList<>();
//
//		List<StockGoodsTotalDTO> lstStockGoodsTotal = new ArrayList<>();
//		for (int i = 0; i < lstIdOfGoodStateAndStock.size(); i++) {
//			Long idOfGood;
//			idOfGood = lstIdOfGoodStateAndStock.get(i).getGoodsId();
//			lstIdOfGood.add(idOfGood);
//		}
//		for (int i = 0; i < lstIdOfGoodStateAndStock.size(); i++) {
//			String goodState;
//			goodState = lstIdOfGoodStateAndStock.get(i).getGoodsState();
//			lstGoodState.add(goodState);
//		}
//		for (int i = 0; i < lstIdOfGoodStateAndStock.size(); i++) {
//			Long idOfStock;
//			idOfStock = lstIdOfGoodStateAndStock.get(i).getStockId();
//			lstIdOfStock.add(idOfStock);
//		}
//		if(lstStockGoods.size()>0){
//			for (int i = 0; i < lstStockGoods.size(); i++) {
//				StockGoodsTotalDTO sgtDto = new StockGoodsTotalDTO();
//				sgtDto.setGoodsCode(lstStockGoods.get(i).getGoodsCode());
//				sgtDto.setGoodsName(lstStockGoods.get(i).getGoodsName());
//				sgtDto.setGoodsUnitId(lstStockGoods.get(i).getGoodsUnitId());
//				sgtDto.setGoodsUnitName(lstStockGoods.get(i).getGoodsUnitName());
//				sgtDto.setAmount(lstStockGoods.get(i).getAmount());
//				sgtDto.setAmountIssue(lstStockGoods.get(i).getAmount());
//				sgtDto.setGoodsState(lstStockGoods.get(i).getGoodsState());
//				sgtDto.setGoodsStateName(lstStockGoods.get(i).getGoodsStateName());
//				sgtDto.setGoodsId(lstStockGoods.get(i).getGoodsId());
//				sgtDto.setStockId(lstStockGoods.get(i).getStockId());
//				lstStockGoodsTotal.add(sgtDto);
//			}
//		}
//		if(lstStockGoodsSerial.size()>0){
//			for (int i = 0; i < lstStockGoodsSerial.size(); i++) {
//				StockGoodsTotalDTO sgtDto = new StockGoodsTotalDTO();
//				sgtDto.setGoodsCode(lstStockGoodsSerial.get(i).getGoodsCode());
//				sgtDto.setGoodsName(lstStockGoodsSerial.get(i).getGoodsName());
//				sgtDto.setGoodsUnitId(lstStockGoodsSerial.get(i).getGoodsUnitId());
//				sgtDto.setGoodsUnitName(lstStockGoodsSerial.get(i).getGoodsUnitName());
//				sgtDto.setAmount(lstStockGoodsSerial.get(i).getAmount());
//				sgtDto.setAmountIssue(lstStockGoodsSerial.get(i).getAmount());
//				sgtDto.setGoodsState(lstStockGoodsSerial.get(i).getGoodsState());
//				sgtDto.setGoodsStateName(lstStockGoodsSerial.get(i).getGoodsStateName());
//				sgtDto.setGoodsId(lstStockGoodsSerial.get(i).getGoodsId());
//				sgtDto.setStockId(lstStockGoodsSerial.get(i).getStockId());
//				lstStockGoodsTotal.add(sgtDto);
//			}
//		}
//		/*List<StockGoodsTotalDTO> newLstStockGoodsTotal = new ArrayList<>();
//		List<StockGoodsTotalDTO> newLstStockGoodsTotal2 = new ArrayList<>();
//		newLstStockGoodsTotal2.add(lstStockGoodsTotal.get(0));
//		newLstStockGoodsTotal.add(lstStockGoodsTotal.get(0));
//		for(int k = 0; k < newLstStockGoodsTotal2.size(); k++){
//			for(int j=0; j<lstStockGoodsTotal.size();j++){
//				if(!newLstStockGoodsTotal2.get(k).getAmount().equals(lstStockGoodsTotal.get(j).getAmount())  || !newLstStockGoodsTotal2.get(k).getGoodsId().equals(lstStockGoodsTotal.get(j).getGoodsId())){
//					newLstStockGoodsTotal.add(lstStockGoodsTotal.get(j));
//				}
//			}
//		}
//		lstStockGoodsTotal = newLstStockGoodsTotal;*/
//		
//		//Xoa bot du lieu thua sau khi import vi tri		
//		for(int i = 0; i<lstStockGoodsTotal.size();i++){
//			for(int j= i+1; j<lstStockGoodsTotal.size();j++){
//				if(lstStockGoodsTotal.get(i).getAmount().equals(lstStockGoodsTotal.get(j).getAmount())  && lstStockGoodsTotal.get(i).getGoodsId().equals(lstStockGoodsTotal.get(j).getGoodsId())){
//					lstStockGoodsTotal.remove(j);
//					if(j==lstStockGoodsTotal.size()-1){
//						break;
//					}
//				}
//			}
//		}
//		//End Xoa bot du lieu thua sau khi import vi tri
//		for (int i = 0; i < lstStockGoodsTotal.size(); i++) {
//			
//			StockDTO sdto = new StockDTO();
//			sdto.setStockId(lstStockGoodsTotal.get(i).getStockId());
//			StockDTO stockDTO = stockDAO.doSearchStockById(sdto);
//			lstStockGoodsTotal.get(i).setStockCode(stockDTO.getCode());
//			lstStockGoodsTotal.get(i).setStockName(stockDTO.getName());
//			if(lstStockGoodsTotal.get(i).getGoodsId()==null){
//				StockGoodsTotalDTO goods = stockGoodsTotalBusinessImpl.getGood(lstStockGoodsTotal.get(i).getStockId(),
//						lstStockGoodsTotal.get(i).getGoodsId(), lstStockGoodsTotal.get(i).getGoodsStateName(), lstStockGoodsTotal.get(i).getGoodsCode(),lstStockGoodsTotal.get(i).getGoodsName());
//				if(goods==null){
//					stockGoodsTotalBusinessImpl.save(lstStockGoodsTotal.get(i));
//				}else{
//					lstStockGoodsTotal.get(i).setGoodsId(goods.getGoodsId());
//				}
//			}
//			//Lay thong tin loai hang hoa, hang hoa co theo serial hay khong
//			GoodsDTO gdto = goodsDAO.getGoodById(lstStockGoodsTotal.get(i).getGoodsId());
//			lstStockGoodsTotal.get(i).setGoodsType(Long.parseLong(gdto.getGoodsType()));
//			lstStockGoodsTotal.get(i).setGoodsIsSerial(gdto.getIsSerial());
//			
//			//Lay thong tin ten loai hang hoa
//			GoodsTypeDTO gtdto = goodsTypeDAO.getGoodTypeById(lstStockGoodsTotal.get(i).getGoodsType());
//			lstStockGoodsTotal.get(i).setGoodsTypeName(gtdto.getName());
//			
//			StockGoodsTotalDTO dto = stockGoodsTotalBusinessImpl.getGood(lstStockGoodsTotal.get(i).getStockId(),
//					lstStockGoodsTotal.get(i).getGoodsId(), lstStockGoodsTotal.get(i).getGoodsStateName(), lstStockGoodsTotal.get(i).getGoodsCode(),lstStockGoodsTotal.get(i).getGoodsName());
//			if(dto==null){
//				stockGoodsTotalBusinessImpl.save(lstStockGoodsTotal.get(i));
//			}else{
//				if(dto.getAmount()==null){
//					dto.setAmount(0d);
//				}
//				if(dto.getAmountIssue()==null){
//					dto.setAmountIssue(0d);
//				}
//				if(lstStockGoodsTotal.get(i).getAmount()==null){
//					lstStockGoodsTotal.get(i).setAmount(0d);
//				}
//				lstStockGoodsTotal.get(i).setAmountIssue(dto.getAmountIssue() + lstStockGoodsTotal.get(i).getAmount());
//				lstStockGoodsTotal.get(i).setAmount(dto.getAmount() + lstStockGoodsTotal.get(i).getAmount());
//				stockGoodsTotalBusinessImpl.updateStockGoodsTotal(lstStockGoodsTotal.get(i));
//			}
//			/*if (!lstIdOfGood.contains(lstStockGoodsTotal.get(i).getGoodsId())
//					&& !lstGoodState.contains(lstStockGoodsTotal.get(i).getGoodsState())
//					&& !lstIdOfStock.contains(lstStockGoodsTotal.get(i).getStockId())) {
//				
//			}
//			if (lstIdOfGood.contains(lstStockGoodsTotal.get(i).getGoodsId())
//					&& lstGoodState.contains(lstStockGoodsTotal.get(i).getGoodsState())
//					&& lstIdOfStock.contains(lstStockGoodsTotal.get(i).getStockId())) {
//				// Update Stock_good_total
//				
//				
//			}*/
//		}
//
//	}
//	//Luu vao bang stock_goods_serial voi cac tham so list id, serial cua hang hoa, list hang hoa co serial
//	private void saveToStockGoodsSerial(List<StockGoodsSerialDTO> lstSerialAndId,
//			List<StockGoodsSerialDTO> lstStockGoodsSerial) throws Exception {
//		for (int i = 0; i < lstStockGoodsSerial.size(); i++) {
//			StockGoodsSerialDTO dto = new StockGoodsSerialDTO();
//			if(lstStockGoodsSerial.get(i).getSerial() != null && lstStockGoodsSerial.get(i).getGoodsId() != null){
//				
//				//Lay thong tin loai hang hoa, hang hoa co theo serial hay khong
//				GoodsDTO gdto = goodsDAO.getGoodById(lstStockGoodsSerial.get(i).getGoodsId());
//				lstStockGoodsSerial.get(i).setGoodsType(Long.parseLong(gdto.getGoodsType()));
//				lstStockGoodsSerial.get(i).setGoodsIsSerial(gdto.getIsSerial());
//				
//				OrderDTO odto = orderDAO.getOrderDetail(lstStockGoodsSerial.get(i).getOrderId());
//				if(odto.getShipmentCode()!=null){
//					lstStockGoodsSerial.get(i).setShipmentCode(odto.getShipmentCode());
//				}
//				if(odto.getProjectCode()!=null){
//					lstStockGoodsSerial.get(i).setProjectCode(odto.getProjectCode());
//				}
//				lstStockGoodsSerial.get(i).setImportDate(new Date());
//				
//				dto = stockGoodsSerialDAO.findBySerialAndGoodsId(lstStockGoodsSerial.get(i).getSerial(), lstStockGoodsSerial.get(i).getGoodsId());
//				if (dto==null) {
//					stockGoodSerialBusinessImpl.save(lstStockGoodsSerial.get(i));
//				} else {
//						if (dto.getStatus().trim().equals("1")) {
//							throw new IllegalStateException("Serial đã tồn trong kho");
//						} else {
//							// Update Stock_goods_serial with serial
//							stockGoodSerialBusinessImpl.updateBySerial(lstStockGoodsSerial.get(i));
//						}
//				}
//			}
//		}
//	}
//	//Thuc nhap - NgocCX
//	@Override
//	@Transactional
//	public String realImportNote(StockTransDTO obj) throws Exception {
//		/*if(!obj.getSignState().equals("3")){
//			throw new IllegalArgumentException("Yêu cầu chưa được ký duyệt !");
//		}*/
//		List<StockGoodsDTO> lstStockGoods = new ArrayList<>();
//		List<StockGoodsSerialDTO> lstStockGoodsSerial = new ArrayList<>();
//		List<StockGoodsSerialDTO> lstStockGoodsAll = new ArrayList<>();
//		List<StockTransDetailDTO> lstStockTransDetail = stockTransDetailDAO
//				.doSearchByStockTransId(obj.getStockTransId());
//		for (int i = 0; i < lstStockTransDetail.size(); i++) {
//			List<StockTransDetailSerialDTO> lstStockTransDetailSerial = stockTransDetailSerialDAO
//					.doSearchByStockTransDetailId(lstStockTransDetail.get(i).getStockTransDetailId());
//			if(lstStockTransDetailSerial.size() > 0){
//				for (int j = 0; j < lstStockTransDetailSerial.size(); j++) {
//					StockGoodsSerialDTO sgsDto = new StockGoodsSerialDTO();
//					sgsDto.setOrderId(lstStockTransDetail.get(i).getOrderId());
//					sgsDto.setGoodsId(lstStockTransDetail.get(i).getGoodsId());
//					sgsDto.setStockId(obj.getStockId());
//					sgsDto.setGoodsCode(lstStockTransDetail.get(i).getGoodsCode());
//					sgsDto.setGoodsName(lstStockTransDetail.get(i).getGoodsName());
//					sgsDto.setGoodsUnitId(lstStockTransDetail.get(i).getGoodsUnitId());
//					sgsDto.setGoodsUnitName(lstStockTransDetail.get(i).getGoodsUnitName());
//					sgsDto.setAmount(lstStockTransDetail.get(i).getAmountOrder());
//					sgsDto.setGoodsState(lstStockTransDetail.get(i).getGoodsState());
//					sgsDto.setGoodsStateName(lstStockTransDetail.get(i).getGoodsStateName());
//					sgsDto.setGoodsType(Long.parseLong(lstStockTransDetail.get(i).getGoodsType()));
//					sgsDto.setGoodsTypeName(lstStockTransDetail.get(i).getGoodsTypeName());
//					sgsDto.setGoodsIsSerial(lstStockTransDetail.get(i).getGoodsIsSerial());
//					sgsDto.setTotalPrice(lstStockTransDetail.get(i).getTotalPrice());
//					sgsDto.setSerial(lstStockTransDetailSerial.get(j).getSerial());
//					sgsDto.setContractCode(lstStockTransDetailSerial.get(j).getContractCode());
//					sgsDto.setPartNumber(lstStockTransDetailSerial.get(j).getPartNumber());
//					sgsDto.setManufacturerId(lstStockTransDetailSerial.get(j).getManufacturerId());
//					sgsDto.setProducingCountryId(lstStockTransDetailSerial.get(j).getProducingCountryId());
//					sgsDto.setManufacturerName(lstStockTransDetailSerial.get(j).getManufacturerName());
//					sgsDto.setProducingCountryName(lstStockTransDetailSerial.get(j).getProducingCountryName());
//					sgsDto.setPrice(lstStockTransDetailSerial.get(j).getPrice());
//					
//
//					lstStockGoodsAll.add(sgsDto);
//				}
//			}else{
//				StockGoodsSerialDTO sgsDto = new StockGoodsSerialDTO();
//				sgsDto.setOrderId(lstStockTransDetail.get(i).getOrderId());
//				sgsDto.setGoodsId(lstStockTransDetail.get(i).getGoodsId());
//				sgsDto.setStockId(obj.getStockId());
//				sgsDto.setGoodsCode(lstStockTransDetail.get(i).getGoodsCode());
//				sgsDto.setGoodsName(lstStockTransDetail.get(i).getGoodsName());
//				sgsDto.setGoodsUnitId(lstStockTransDetail.get(i).getGoodsUnitId());
//				sgsDto.setGoodsUnitName(lstStockTransDetail.get(i).getGoodsUnitName());
//				sgsDto.setAmount(lstStockTransDetail.get(i).getAmountOrder());
//				sgsDto.setGoodsState(lstStockTransDetail.get(i).getGoodsState());
//				sgsDto.setGoodsStateName(lstStockTransDetail.get(i).getGoodsStateName());
//				sgsDto.setGoodsType(Long.parseLong(lstStockTransDetail.get(i).getGoodsType()));
//				sgsDto.setGoodsTypeName(lstStockTransDetail.get(i).getGoodsTypeName());
//				sgsDto.setGoodsIsSerial("0");
//				
//				lstStockGoodsAll.add(sgsDto);
//			}
//			
//		}
//
//		for (int i = 0; i < lstStockGoodsAll.size(); i++) {
//
//			if (lstStockGoodsAll.get(i).getGoodsIsSerial().equals("0")) {
//				StockGoodsDTO sgDto = new StockGoodsDTO();
//				sgDto.setOrderId(lstStockGoodsAll.get(i).getOrderId());
//				sgDto.setGoodsId(lstStockGoodsAll.get(i).getGoodsId());
//				sgDto.setStockId(lstStockGoodsAll.get(i).getStockId());
//				sgDto.setGoodsCode(lstStockGoodsAll.get(i).getGoodsCode());
//				sgDto.setGoodsName(lstStockGoodsAll.get(i).getGoodsName());
//				sgDto.setGoodsUnitId(lstStockGoodsAll.get(i).getGoodsUnitId());
//				sgDto.setGoodsUnitName(lstStockGoodsAll.get(i).getGoodsUnitName());
//				sgDto.setGoodsType(lstStockGoodsAll.get(i).getGoodsType());
//				sgDto.setGoodsTypeName(lstStockGoodsAll.get(i).getGoodsTypeName());
//				sgDto.setGoodsIsSerial(lstStockGoodsAll.get(i).getGoodsIsSerial());
//				sgDto.setAmount(lstStockGoodsAll.get(i).getAmount());
//				sgDto.setGoodsState(lstStockGoodsAll.get(i).getGoodsState());
//				sgDto.setGoodsStateName(lstStockGoodsAll.get(i).getGoodsStateName());
//				sgDto.setContractCode(lstStockGoodsAll.get(i).getContractCode());
//				sgDto.setPartNumber(lstStockGoodsAll.get(i).getPartNumber());
//				sgDto.setManufacturerId(lstStockGoodsAll.get(i).getManufacturerId());
//				sgDto.setProducingCountryId(lstStockGoodsAll.get(i).getProducingCountryId());
//				sgDto.setManufacturerName(lstStockGoodsAll.get(i).getManufacturerName());
//				sgDto.setProducingCountryName(lstStockGoodsAll.get(i).getProducingCountryName());
//				sgDto.setPrice(lstStockGoodsAll.get(i).getPrice());
//				sgDto.setTotalPrice(lstStockGoodsAll.get(i).getTotalPrice());
//				sgDto.setStatus("1");
//				lstStockGoods.add(sgDto);
//				stockGoodsBusinessImpl.save(sgDto);
//			} else {
//				StockGoodsSerialDTO sgsDto = new StockGoodsSerialDTO();
//				sgsDto.setOrderId(lstStockGoodsAll.get(i).getOrderId());
//				sgsDto.setGoodsId(lstStockGoodsAll.get(i).getGoodsId());
//				sgsDto.setStockId(lstStockGoodsAll.get(i).getStockId());
//				sgsDto.setGoodsCode(lstStockGoodsAll.get(i).getGoodsCode());
//				sgsDto.setGoodsName(lstStockGoodsAll.get(i).getGoodsName());
//				sgsDto.setGoodsUnitId(lstStockGoodsAll.get(i).getGoodsUnitId());
//				sgsDto.setGoodsUnitName(lstStockGoodsAll.get(i).getGoodsUnitName());
//				sgsDto.setGoodsType(lstStockGoodsAll.get(i).getGoodsType());
//				sgsDto.setGoodsTypeName(lstStockGoodsAll.get(i).getGoodsTypeName());
//				sgsDto.setGoodsIsSerial(lstStockGoodsAll.get(i).getGoodsIsSerial());
//				sgsDto.setAmount(lstStockGoodsAll.get(i).getAmount());
//				sgsDto.setGoodsState(lstStockGoodsAll.get(i).getGoodsState());
//				sgsDto.setGoodsStateName(lstStockGoodsAll.get(i).getGoodsStateName());
//				sgsDto.setContractCode(lstStockGoodsAll.get(i).getContractCode());
//				sgsDto.setPartNumber(lstStockGoodsAll.get(i).getPartNumber());
//				sgsDto.setManufacturerId(lstStockGoodsAll.get(i).getManufacturerId());
//				sgsDto.setProducingCountryId(lstStockGoodsAll.get(i).getProducingCountryId());
//				sgsDto.setManufacturerName(lstStockGoodsAll.get(i).getManufacturerName());
//				sgsDto.setProducingCountryName(lstStockGoodsAll.get(i).getProducingCountryName());
//				sgsDto.setSerial(lstStockGoodsAll.get(i).getSerial());
//				sgsDto.setPrice(lstStockGoodsAll.get(i).getPrice());
//				sgsDto.setStatus("1");
//				lstStockGoodsSerial.add(sgsDto);
//			}
//		}
//
//		if(lstStockGoodsSerial.size()>0){
//		List<StockGoodsSerialDTO> lstSerialAndId = stockGoodsSerialDAO.doSearchSerialAndId();
//		saveToStockGoodsSerial(lstSerialAndId, lstStockGoodsSerial);
//		}
//
//		List<StockGoodsTotalDTO> lstIdOfGoodStateAndStock = stockGoodsTotalDAO.doSearchIdOfGoodStateAndStock();
//		saveToStockGoodsTotal(lstIdOfGoodStateAndStock, lstStockGoods, lstStockGoodsSerial);
//		
//		obj.setStatus("2");
//		updateStatusStockTrans(obj);
//		OrderDTO oDto = new OrderDTO();
//		oDto.setOrderId(obj.getOrderId());
//		oDto.setStatus("3");
//		Boolean bl = orderBusinessImpl.updateStatusOrder(oDto);
//		if(bl){
//			return "SUCCESS";
//		}else{
//			return null;
//		}
//
//	}
//
//	//Cap nhat phieu nhap kho - NgocCX
//	@Override
//	public Long updateImportNote(StockTransDTO obj,KttsUserSession objUser) {
//		// TODO Auto-generated method stub
//		if(!obj.getCreatedBy().equals(objUser.getSysUserId())){
//			throw new  IllegalArgumentException("Người dùng hiện tại không có quyền sửa bản ghi này !");
//		}
//		obj.setCreatedDate(new Date());
//		Long id = update(obj);
//		return id;
//	}
//
//	
//	//Tim kiem phieu xuat kho- HungNP
//	@Override
//	public DataListDTO doSearchStatement(StockTransDTO obj,HttpServletRequest request) throws Exception{
//		// TODO Auto-generated method stub
//		String err="";
//    	for(Long id:obj.getListStockId()){
//    		StockDTO stockDTO=(StockDTO) stockBusinessImpl.getOneById(id);
//    		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,id, request)){
//    			err=StringUtils.isNotEmpty(err)? (err+";"+id):("Bạn không có quyền xem báo cáo tại kho "+id);
//    		}
//    	}
//    	
//    	if(StringUtils.isNotEmpty(err)){
//    		throw new IllegalArgumentException(err);
//    	}
//    	
//    	if(obj.getListStockId().size()==0){
//    		List<Long> listId=commonBusiness.getListDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK, request);
//    		obj.setListStockId(listId);
//    	}
//		List<StockTransDTO> ls = stockTransDAO.doSearchExportManagement(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//
//	public StockTransDTO getStockByOrder(StockTransDTO obj) {
//		return stockTransDAO.getStockByOrder(obj);
//	}
//
//	//Tim kiem hang hoa cho cac ma hang nhap luan chuyen kho - NgocCX
//	@Override
//	public List<String> getGoodsInfoFromAlternativeStockCode(String code) {
//		// TODO Auto-generated method stub
//		return stockTransDAO.getGoodsInfoFromAlternativeStockCode(code);
//	}
//
//	//Xoa phieu nhap kho - NgocCX
//	@Override
//	public boolean removeImportStockTrans(StockTransDTO obj,KttsUserSession objUser) {
//		// TODO Auto-generated method stub
//		if(!obj.getCreatedBy().equals(objUser.getSysUserId())){
//			throw new  IllegalArgumentException("Người dùng hiện tại không có quyền xóa bản ghi này !");
//		}
//		return stockTransDAO.removeImportStockTrans(obj);
//	}
//
//
//	
//
//	@Override
//	public boolean saveExportNote(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	@Transactional
//	public void realExportNote(StockTransDTO obj,HttpServletRequest request) throws Exception {
//		// TODO Auto-generated method stub
//		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.CREATE, Constants.AdResourceKey.STOCK_TRANS, obj.getStockId(), request)){
//			throw new BusinessException("Bạn không có quyền tạo phiếu với kho hàng " +obj.getStockCode());
//		}
//		List<StockTransDetailDTO> sdts = obj.getListStockTransDetailDTO();
//		for (StockTransDetailDTO sdt : sdts) {
//		String err=	stockGoodsTotalBusinessImpl.updateRemainGoodsNumberInStock(sdt);
//			if (StringUtils.isNotEmpty(err)) {
//				throw new IllegalArgumentException(err);
//			}
//			if ("1".equals(sdt.getIsSerial())) {
//				// tim kiem danh sach serials tuong ung lay tu serial ton tai trong ban ghi cua tung phieu
//				List<StockTransDetailSerialDTO> serialsInStockTrans = sdt.getListDetailSerial();
//				if(serialsInStockTrans.size()==0){
//					throw new IllegalArgumentException("Hàng hóa "+sdt.getGoodsCode()+" phải có Serial mới được thực xuất! ");
//				}
//				boolean checkExitSerial=stockGoodsSerialDAO.checkExitSerial(sdt.getStockTransDetailId());
//				for (StockTransDetailSerialDTO serialInStockTrans : serialsInStockTrans) {
//					
//					serialInStockTrans.setStockTransId(obj.getStockTransId());
//					serialInStockTrans.setStockTransDetailId(sdt.getStockTransDetailId());
//					serialInStockTrans.setGoodsId(sdt.getGoodsId());
//					serialInStockTrans.setGoodState(sdt.getGoodsState());
//					if(!checkExitSerial){
//						stockTransDetailSerialDAO.saveObject(serialInStockTrans.toModel());
//					}
//				String r =	updateSerialGoods(serialInStockTrans, sdt.getOrderId(),sdt.getStockId());
//				if(StringUtils.isNotEmpty(r)){
//					throw new IllegalArgumentException(r);
//					}
//				}
//			} else{
//			boolean check=	stockGoodsBusinessImpl.updateNonSerialGoods(sdt);
//			if(!check){
//				throw new IllegalArgumentException("Không đủ số lượng hàng hóa để thực xuất với mã hàng "+sdt.getGoodsCode() );
//			}
//			}
//			OrderDTO orderDTO=new OrderDTO();
//			orderDTO.setStatus("3");
//			orderDTO.setOrderId(obj.getOrderId());
//			if(!orderDAO.updateStatusOrder(orderDTO)){
//				throw new IllegalArgumentException("Lỗi khi update trạng thái yêu cầu");
//			}
//			obj.setRealIeTransDate(new Date());
//			obj.setStatus("2");
//			if(!stockTransDAO.updateStatusStockTrans(obj)){
//				throw new IllegalArgumentException("Lỗi khi update trạng thái phiếu");
//			}
//			
//			
//		}
//	}
//	
//	public String updateSerialGoods(StockTransDetailSerialDTO obj,Long orderId,Long stockId){
//		String err="";
//		if(stockGoodsSerialDAO.findSerialFromStockTrans(obj,stockId)==0l){
//			err="Không tìm thấy serial để xuất !";
//		} else{
//			err=stockGoodsSerialDAO.updateRealExport(obj, orderId,stockId);
//		}
//		
//		return err;
//	}
//	
//	@Override
//	@Transactional
//	public boolean removeExportStockTrans(StockTransDTO obj) {
//		obj.setStatus("3");
//		obj.setCancelDate(new Date());
//		obj.setCancelByName(obj.getCreatedByName());
//		obj.setDescription(obj.getCancelDescription());
//		List<StockTransDetailDTO> sdts = stockTransDetailBusinessImpl.doSearchGoodsForIExportNote(obj);
//		for (StockTransDetailDTO sdt : sdts) {
//			if(sdt.getGoodsId()!= null){
//			Long goods_id = sdt.getGoodsId();
//			StockGoodsTotalDTO goodsInNote = new StockGoodsTotalDTO();
//			if(sdt.getGoodsState() != null){
//				 goodsInNote = stockGoodsTotalDAO.getAmountIssueGoodsTotal(goods_id, sdt.getStockId(), sdt.getGoodsState());
//			}
//			if(goodsInNote.getStockGoodsTotalId()!=null){
//			Double newAmountIssue = (goodsInNote.getAmountIssue()==null? 0l:goodsInNote.getAmountIssue()) + (sdt.getAmountReal()==null? 0l:sdt.getAmountReal());
//			goodsInNote.setAmountIssue(newAmountIssue);
//			stockGoodsTotalDAO.updateAmount(goodsInNote, true);
//			}
//			}
//		}
//		return stockTransDAO.removeImportStockTrans(obj);
//	}
//	
//
//	public CommonDTO getCharFour(CommonDTO obj,HttpServletRequest request) {
//		List<Long> listStockId=commonBusiness.getListDomainData(Constants.OperationKey.VIEW,Constants.AdResourceKey.STOCK, request);
//		obj.setListStockId(listStockId);
//		List<Long> ls = Lists.newArrayList();
//		CommonDTO objReturn = stockTransDAO.getCharFour(obj);
//		ls.add(objReturn.getExInRoad());
//		ls.add(objReturn.getExNotReal());
//		ls.add(objReturn.getExNotSign());
//		ls.add(objReturn.getImNotReal());
//		ls.add(objReturn.getImNotSign());
//
//		long max = Collections.max(ls);
//		// System.out.print(max);
//		objReturn.setImNotRealRate(objReturn.getImNotReal().doubleValue() / max * 100);
//		objReturn.setImNotSignRate(objReturn.getImNotSign().doubleValue() / max * 100);
//		objReturn.setExInRoadRate(objReturn.getExInRoad().doubleValue() / max * 100);
//		objReturn.setExNotSignRate(objReturn.getExNotSign().doubleValue() / max * 100);
//		objReturn.setExNotRealRate(objReturn.getExNotReal().doubleValue() / max * 100);
//		return objReturn;
//	}
//
//	public CommonDTO getCharTwoWeek(CommonDTO obj,HttpServletRequest request) {
//		List<Long> listStockId=commonBusiness.getListDomainData(Constants.OperationKey.VIEW,Constants.AdResourceKey.STOCK, request);
//		obj.setListStockId(listStockId);
//		CommonDTO objReturn = new CommonDTO();
//		List<CommonDTO> ls = stockTransDAO.getCharTwoWeek(obj);
//		if (ls.size() > 0) {
//			for (Iterator<CommonDTO> interator = ls.iterator(); interator.hasNext();) {
//				CommonDTO wi = interator.next();
//				objReturn.getListExported().add(wi.getExported());
//				objReturn.getListImported().add(wi.getImported());
//				objReturn.getListDay().add(wi.getDay());
//			}
//		}
//		return objReturn;
//	}
//	
//	/**
//	 * 
//	 * @param obj
//	 * @return
//	 * @throws ParseException
//	 */
//	 public CommonDTO getCharTwoMonth(CommonDTO obj,HttpServletRequest request) throws ParseException{
//		 List<Long> listStockId=commonBusiness.getListDomainData(Constants.OperationKey.VIEW,Constants.AdResourceKey.STOCK, request);
//		 obj.setListStockId(listStockId);
//		 CommonDTO objReturn= new CommonDTO();
//			List<CommonDTO> ls = stockTransDAO.getCharTwoMonth(obj);
//			if (ls.size() > 0 && ls.size()<10) {
//				for (Iterator<CommonDTO> interator = ls.iterator(); interator.hasNext();) {
//					CommonDTO wi = interator.next();
//					objReturn.getListExported().add(wi.getExported());
//					objReturn.getListImported().add(wi.getImported());
//					objReturn.getListDay().add(wi.getDay());
//				}
//			} else if(ls.size()>=10){
//				Integer index1 =0;
//				Integer index2 =null;
//				Integer index3 =null;
//				Integer index4 =null;
//				Integer index5 =ls.size()-1;
//				index3=index5/2;
//				index4=index3+(index5-index3)/2;
//				index2=index3-index3/2;
//				List<Integer> listIndex= Lists.newArrayList();
//				listIndex.add(index1);
//				listIndex.add(index2);
//				listIndex.add(index3);
//				listIndex.add(index4);
//				listIndex.add(index5);
//				
//				for(Integer index:listIndex){
//					objReturn.getListExported().add(ls.get(index).getExported());
//					objReturn.getListImported().add(ls.get(index).getImported());
//					objReturn.getListDay().add(ls.get(index).getDay());
//				}
//			}
//			return objReturn;
//		 
//		
//	 }
//	 
//	 @Override
//		public List<StockTransDTO> getStockTransForAutoComplete(StockTransDTO obj) {
//			// TODO Auto-generated method stub
//			return stockTransDAO.doSearchNoteForAutoComplete(obj);
//		}
//	 
//
//	
//	 
//	 /**
//	  * xu ly import update -->Huy
//	  */
//	@Override
//	@Transactional
//	public Long updateImportStockTran(StockTransDTO obj) {
//		obj.setUpdatedDate(new Date());
//		obj.setCreatedDate(new Date());
//		Long ids=update(obj);
//		if (ids != null) {
//			for (StockTransDetailDTO transDetailDTO : obj.getListStockTransDetailDTO()) {
//				if(transDetailDTO.getAmountOrder().longValue()<transDetailDTO.getAmountReal().longValue()){
//					throw new IllegalArgumentException("Hàng hóa "+transDetailDTO.getGoodsCode() +" có số lượng xuất nhiều hơn số lượng yêu cầu");
//				}
//				stockTransDetailBusinessImpl.update(transDetailDTO);
//				if(obj.getCountSerial().longValue()==0l){
//					Double amount=stockTransDAO.getAmountRealDetail(transDetailDTO.getStockTransDetailId());
//					StockGoodsTotalDTO goodsInNote = stockGoodsTotalDAO.getAmountIssueGoodsTotal(transDetailDTO.getGoodsId(), transDetailDTO.getStockId(), transDetailDTO.getGoodsState());
//					Double newAmountIssue = goodsInNote.getAmountIssue()-amount+transDetailDTO.getAmountReal();
//					goodsInNote.setAmount(newAmountIssue);
//					stockGoodsTotalDAO.updateAmount(goodsInNote, true);
//				}
//					
//			  for(StockTransDetailSerialDTO detailSerialDTO:transDetailDTO.getListDetailSerial()){
//							stockTransDetailSerialBusinessImpl.update(detailSerialDTO);
//					}
//				}
//			}
//			return ids;
//	}
//	 
//	 public List<SynchERPDTO> getListStockTrans(SynchERPDTO obj){
//		 return stockTransDAO.getListStockTrans(obj);
//	 }
//
//	@Override
//	@Transactional
//	public Long saveInforImport(StockTransDTO obj,HttpServletRequest request) throws Exception {
//		
//		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.CREATE, Constants.AdResourceKey.STOCK_TRANS, obj.getStockId(), request)){
//			throw new IllegalArgumentException("Bạn không có quyền tạo phiếu với kho hàng " +obj.getStockCode());
//		}
//		if(!obj.getSignState().equals("3")){
//			throw new IllegalArgumentException("Yêu cầu chưa được ký duyệt !");
//		}
//		KttsUserSession objUser = userRoleBusinessImpl.getUserSession(request); 
//		StockTransDTO checkDto = new StockTransDTO();
//		checkDto.setCode(obj.getCode());
//		List<StockTransDTO> lstCheckDto = stockTransDAO.checkDupOrderCode(checkDto);
//		if(lstCheckDto.size()>0){
//			throw new BusinessException("Mã phiếu đã tồn tại");
//		}
//		AppParamDTO objAP = new AppParamDTO();
//		objAP.setStatus("1");
//		objAP.setParType("EXPORT_ORDER_TYPE");
////		List<AppParamDTO> lstAP = appParamDAO.getForComboBox(objAP);
//		List<AppParamDTO> lstAP = new ArrayList<AppParamDTO>();
//		for(int i =0; i<lstAP.size();i++){
//			if(obj.getBussinessType().equals(lstAP.get(i).getCode())){
//				obj.setBussinessTypeName(lstAP.get(i).getName());
//				break;
//			}
//		}
//		//obj.setDeptReceiveId(orderDAO.getOrderDetail(obj.getOrderId()).getDeptReceiveId());
//		obj.setCreatedDate(new Date());
//		obj.setCreatedByName(objUser.getFullName());
//		obj.setStatus("1");
//		obj.setSignState("1");
//		Long stockTransId = save(obj);
//		List<OrderGoodsDTO> lstGoods = orderGoodsDAO.doSearchGoods(obj.getOrderGoodsDTO());
//		List<StockTransDetailDTO> lstStockTransDetail = new ArrayList<>();
//		List<StockTransDetailSerialDTO> lstStockTransDetailSerial = new ArrayList<>();
//			for (int i = 0; i < lstGoods.size(); i++) {
//				StockTransDetailDTO stDto = new StockTransDetailDTO();
//				stDto.setStockTransId(stockTransId);
//				stDto.setOrderId(lstGoods.get(i).getOrderId());
//				stDto.setGoodsId(lstGoods.get(i).getGoodsId());
//				stDto.setGoodsCode(lstGoods.get(i).getGoodsCode());
//				stDto.setGoodsName(lstGoods.get(i).getGoodsName());
//				stDto.setGoodsUnitName(lstGoods.get(i).getGoodsUnitName());
//				stDto.setAmountOrder(lstGoods.get(i).getAmount());
//				stDto.setGoodsState(lstGoods.get(i).getGoodsState());
//				stDto.setGoodsStateName(lstGoods.get(i).getGoodsStateName());
//				stDto.setGoodsIsSerial(lstGoods.get(i).getGoodsIsSerial());
//				stDto.setGoodsUnitId(lstGoods.get(i).getGoodsUnitId());
//				stDto.setGoodsType(lstGoods.get(i).getGoodsType());
//				stDto.setGoodsTypeName(lstGoods.get(i).getGoodsTypeName());
//				stDto.setTotalPrice(lstGoods.get(i).getTotalPrice());
//				
//				if(obj.getListOrderGoodsDTO()!=null && obj.getListOrderGoodsDTO().size()>0)
//				{
//					for(int h=0;h<obj.getListOrderGoodsDTO().size();h++)
//					{
//						if(obj.getListOrderGoodsDTO().get(h).getGoodsCode().equalsIgnoreCase(lstGoods.get(i).getGoodsCode()))
//						{
//							stDto.setAmountReal(obj.getListOrderGoodsDTO().get(h).getAmountReal());
//						}
//					}
//				}
//				stDto.setAmountOrder(lstGoods.get(i).getAmount());
//				if(stDto.getAmountOrder().longValue()<stDto.getAmountReal().longValue()){
//					throw new IllegalArgumentException("Hàng hóa "+stDto.getGoodsCode() +" có số lượng xuất nhiều hơn số lượng yêu cầu");
//				}
//				stDto.setGoodsStateName(lstGoods.get(i).getGoodsStateName());
//				lstStockTransDetail.add(stDto);
//				
//				StockGoodsTotalDTO sgtCheck  = null;
//				if(stDto.getGoodsId() != null && obj.getStockId()!= null && lstGoods.get(i).getGoodsState()!= null){
//							 sgtCheck = stockGoodsTotalDAO.getAmountIssueGoodsTotal(stDto.getGoodsId(), obj.getStockId(), lstGoods.get(i).getGoodsState());		
//	 				}else
//						throw new IllegalArgumentException("Hàng hóa tương ứng không tìm thấy trong kho");
//				
//				if(sgtCheck != null){
//						if(sgtCheck.getAmountIssue() - stDto.getAmountReal() >=0){
//							Long stockTransDetailId = stockTransDetailBusinessImpl.save(lstStockTransDetail.get(i));
//							List<OrderGoodsDetailDTO> lstGoodsDetail = orderGoodsDetailDAO
//									.doSearchGoodsDetail(lstGoods.get(i).getOrderGoodsId());
//							for (int j = 0; j < lstGoodsDetail.size(); j++) {
//								if("1".equals(stDto.getGoodsIsSerial()) && !StringUtils.isNotEmpty(lstGoodsDetail.get(j).getSerial())){
//									continue;
//								}
//								StockTransDetailSerialDTO stdrDTO = new StockTransDetailSerialDTO();
//								stdrDTO.setStockTransId(stockTransId);
//								stdrDTO.setStockTransDetailId(stockTransDetailId);
//								stdrDTO.setGoodsId(lstGoods.get(i).getGoodsId());
//								stdrDTO.setSerial(lstGoodsDetail.get(j).getSerial());
//								stdrDTO.setContractCode(lstGoodsDetail.get(j).getContractCode());
//								stdrDTO.setPartNumber(lstGoodsDetail.get(j).getPartNumber());
//								stdrDTO.setManufacturerId(lstGoodsDetail.get(j).getManufacturerId());
//								stdrDTO.setProducingCountryId(lstGoodsDetail.get(j).getProducingCountryId());
//								stdrDTO.setManufacturerName(lstGoodsDetail.get(j).getManufacturerName());
//								stdrDTO.setProducingCountryName(lstGoodsDetail.get(j).getProducingCountryName());
//								stdrDTO.setPrice(lstGoodsDetail.get(j).getPrice());
//
//								if (obj.getListOrderGoodsDetailDTO()!=null) {
//									for (int k = 0; k < obj.getListOrderGoodsDetailDTO().size(); k++) {
//										if (obj.getListOrderGoodsDetailDTO().get(k).getSerial()
//												.equals(lstGoodsDetail.get(j).getSerial())
//												&& obj.getListOrderGoodsDetailDTO().get(k).getGoodsCode()
//														.equals(lstGoods.get(i).getGoodsCode())) {
//											stdrDTO.setCellCode(obj.getListOrderGoodsDetailDTO().get(k).getCellCode());
//										}
//									}
//								}
//								lstStockTransDetailSerial.add(stdrDTO);
//							}
//							sgtCheck.setAmountIssue(sgtCheck.getAmountIssue() - stDto.getAmountReal());
//							stockGoodsTotalDAO.updateAmount(sgtCheck, true);
//							stockTransDetailSerialBusinessImpl.saveList(lstStockTransDetailSerial);
//							OrderDTO oDto = new OrderDTO();
//							oDto.setOrderId(obj.getOrderId());
//							oDto.setStatus("2");
//							orderBusinessImpl.updateStatusOrder(oDto);
//					}else{
//						throw new IllegalArgumentException("Số lượng đáp ứng trong kho của mã hàng không còn đủ để xuất");
//					}
//				}else{
//					throw new IllegalArgumentException("Hàng hóa tương ứng không tìm thấy trong kho");
//				}
//			}
//		return stockTransId;
//	}
//
//	//Cap nhat trang thai ban ghi phieu nhap/xuat - NgocCX
//	@Override
//	public boolean updateStatusStockTrans(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		return stockTransDAO.updateStatusStockTrans(obj);
//	}
//
//	//Lay tat ca phieu nhap kho - NgocCX
//	@Override
//	public List<StockTransDTO> getAllImportNote(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		return stockTransDAO.getAllImportNote(obj);
//	}
//
//	@Override
//	public List<StockTransDTO> getAllExportManagement(StockTransDTO obj) {
//		// TODO Auto-generated method stub
//		return stockTransDAO.getAllExportManagement(obj);
//	}
//	
//	//Bang ke vat tu
//	
//	public String exportExcelBKVT(List<Long> ids,String fullname) throws Exception{
//		
//		try {
//			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//			String filePath = classloader.getResource("../" + "doc-template").getPath();
//			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "BangKeVatTu.xlsx"));
//			XSSFWorkbook workbook = new XSSFWorkbook(file);
//			int count=0;
//			List<StockTransDetailDTO> detailDTOs = stockTransDAO.getDataExportBKVT(ids);	
//			List<StockTransDTO> listDeptReceive=stockTransDAO.getDemptReceive(ids);		
//				XSSFSheet	 sheet = workbook.getSheetAt(0);
//				count++;	
//					
//					// set font
//					// CellStyle style = null;
//
//					
//					
//					
//					XSSFFont defaultFont = workbook.createFont();
//					defaultFont.setFontHeightInPoints((short) 10);
//					defaultFont.setFontName("Times New Roman");
//					defaultFont.setColor(IndexedColors.BLACK.getIndex());
//					defaultFont.setBold(false);
//					defaultFont.setItalic(false);
//
//					XSSFFont font = workbook.createFont();
//					font.setFontHeightInPoints((short) 10);
//					font.setFontName("Times New Roman");
//					font.setColor(IndexedColors.WHITE.getIndex());
//					font.setBold(true);
//					font.setItalic(false);
//
//					XSSFFont fontBoldBlack = workbook.createFont();
//					fontBoldBlack.setFontHeightInPoints((short) 10);
//					fontBoldBlack.setFontName("Times New Roman");
//					fontBoldBlack.setColor(IndexedColors.BLACK.getIndex());
//					fontBoldBlack.setBold(true);
//					fontBoldBlack.setItalic(false);
//					
//					
//					XSSFFont fontTitle = workbook.createFont();
//					fontTitle.setFontHeightInPoints((short) 14);
//					fontTitle.setFontName("Times New Roman");
//					fontTitle.setColor(IndexedColors.BLACK.getIndex());
//					fontTitle.setBold(true);
//					fontTitle.setItalic(false);
//
//					XSSFFont fontHyperLink = workbook.createFont();
//					fontHyperLink.setFontHeightInPoints((short) 10);
//					fontHyperLink.setFontName("Times New Roman");
//					fontHyperLink.setColor(IndexedColors.SKY_BLUE.getIndex());
//					fontHyperLink.setBold(false);
//					fontHyperLink.setItalic(false);
//					fontHyperLink.setUnderline(Font.U_SINGLE);
//
//					XSSFFont fontBold = workbook.createFont();
//					fontBold.setFontHeightInPoints((short) 12);
//					fontBold.setFontName("Times New Roman");
//					fontBold.setColor(IndexedColors.BLACK.getIndex());
//					fontBold.setBold(true);
//					fontBold.setItalic(false);
//
//					XSSFFont fontItalic = workbook.createFont();
//					fontItalic.setFontHeightInPoints((short) 12);
//					fontItalic.setFontName("Times New Roman");
//					fontItalic.setColor(IndexedColors.BLACK.getIndex());
//					fontItalic.setBold(false);
//					fontItalic.setItalic(true);
//
//					CellStyle cellStyle = workbook.createCellStyle();
//					cellStyle.setBorderBottom(BorderStyle.THIN);
//					cellStyle.setBorderTop(BorderStyle.THIN);
//					cellStyle.setBorderLeft(BorderStyle.THIN);
//					cellStyle.setBorderRight(BorderStyle.THIN);
//					cellStyle.setFont(defaultFont);
//					cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
//
//					CellStyle cellStyleBackground = workbook.createCellStyle();
//					cellStyleBackground.setBorderBottom(BorderStyle.THIN);
//					cellStyleBackground.setBorderTop(BorderStyle.THIN);
//					cellStyleBackground.setBorderLeft(BorderStyle.THIN);
//					cellStyleBackground.setBorderRight(BorderStyle.THIN);
//					cellStyleBackground.setFont(fontBoldBlack);
//					cellStyleBackground.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
//					cellStyleBackground.setAlignment(CellStyle.ALIGN_CENTER);
//					cellStyleBackground.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//					cellStyleBackground.setWrapText(true);
//
//					CellStyle cellStyleBold = workbook.createCellStyle();
////					cellStyleBold.setBorderBottom(BorderStyle.THIN);
////					cellStyleBold.setBorderTop(BorderStyle.THIN);
////					cellStyleBold.setBorderLeft(BorderStyle.THIN);
////					cellStyleBold.setBorderRight(BorderStyle.THIN);
//					cellStyleBold.setFont(fontTitle);
//					cellStyleBold.setAlignment(CellStyle.ALIGN_CENTER);
//					cellStyleBold.setWrapText(true);
//
//					CellStyle cellStyleRight = workbook.createCellStyle();
//					cellStyleRight.setBorderBottom(BorderStyle.THIN);
//					cellStyleRight.setBorderTop(BorderStyle.THIN);
//					cellStyleRight.setBorderLeft(BorderStyle.THIN);
//					cellStyleRight.setBorderRight(BorderStyle.THIN);
//					cellStyleRight.setFont(defaultFont);
//					cellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);
//					
//					
//					CellStyle cellStyleCenter = workbook.createCellStyle();
//					cellStyleCenter.setBorderBottom(BorderStyle.THIN);
//					cellStyleCenter.setBorderTop(BorderStyle.THIN);
//					cellStyleCenter.setBorderLeft(BorderStyle.THIN);
//					cellStyleCenter.setBorderRight(BorderStyle.THIN);
//					cellStyleCenter.setFont(defaultFont);
//					cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);
//
//					CellStyle cellStyleLeft = workbook.createCellStyle();
//					cellStyleLeft.setFont(defaultFont);
//					cellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
//					cellStyleLeft.setWrapText(true);
//					
//					
//					CellStyle cellstyleSP = workbook.createCellStyle();
//					cellstyleSP.setFont(fontBold);
//					cellstyleSP.setAlignment(CellStyle.ALIGN_CENTER);
//					// cellstyleSP.setWrapText(true);
//
//					CellStyle cellstyleSP2 = workbook.createCellStyle();
//					cellstyleSP2.setFont(fontBold);
//					cellstyleSP2.setAlignment(CellStyle.ALIGN_LEFT);
//					// cellstyleSP2.setWrapText(true);
//
//					CellStyle cellstyleSP3 = workbook.createCellStyle();
//					cellstyleSP3.setFont(fontItalic);
//					cellstyleSP3.setAlignment(CellStyle.ALIGN_LEFT);
//
//					CellStyle cellstyleSP4 = workbook.createCellStyle();
//					cellstyleSP4.setFont(fontItalic);
//					cellstyleSP4.setAlignment(CellStyle.ALIGN_RIGHT);
//
//					/* Create header */
//					Row row1= sheet.getRow(1);
//					Cell cell1_3=row1.createCell(3);
//					cell1_3.setCellValue(fullname);
//					cell1_3.setCellStyle(cellStyleLeft);
//					Row row2= sheet.getRow(2);
//					Cell cell2_3=row2.createCell(3);
//					cell2_3.setCellValue(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
//					cell2_3.setCellStyle(cellStyleLeft);
//					int countCell=5;
//					
//					
//					
//					//header colum
//					Row row3= sheet.createRow(3);
//					Row row4= sheet.createRow(4);
//					Cell cell3_0 = row3.createCell(0);
//					cell3_0.setCellValue("STT");
//					sheet.addMergedRegion(new CellRangeAddress(3, 4, 0, 0));
//					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 0, 0), sheet, workbook);
//					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 0, 0), sheet, workbook);
//					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 0, 0), sheet, workbook);
//					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 0, 0), sheet, workbook);
//					cell3_0.setCellStyle(cellStyleBackground);
//					
//					Cell cell3_1 = row3.createCell(1);
//					cell3_1.setCellValue("Tên vật tư");
//					sheet.addMergedRegion(new CellRangeAddress(3, 4, 1, 1));
//					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 1, 1), sheet, workbook);
//					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 1, 1), sheet, workbook);
//					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 1, 1), sheet, workbook);
//					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 1, 1), sheet, workbook);
//					cell3_1.setCellStyle(cellStyleBackground);
//					
//					Cell cell3_2 = row3.createCell(2);
//					cell3_2.setCellValue("Mã vật tư");
//					sheet.addMergedRegion(new CellRangeAddress(3, 4, 2, 2));
//					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 2, 2), sheet, workbook);
//					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 2, 2), sheet, workbook);
//					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 2, 2), sheet, workbook);
//					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 2, 2), sheet, workbook);
//					cell3_2.setCellStyle(cellStyleBackground);
//					
//					
//					Cell cell3_3 = row3.createCell(3);
//					cell3_3.setCellValue("Đơn vị tính");
//					sheet.addMergedRegion(new CellRangeAddress(3, 4, 3, 3));
//					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 3, 3), sheet, workbook);
//					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 3, 3), sheet, workbook);
//					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 3, 3), sheet, workbook);
//					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 3, 3), sheet, workbook);
//					cell3_3.setCellStyle(cellStyleBackground);
//					
//					
//					Cell cell3_4 = row3.createCell(4);
//					cell3_4.setCellValue("Trạng thái");
//					sheet.addMergedRegion(new CellRangeAddress(3, 4, 4, 4));
//					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 4, 4), sheet, workbook);
//					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 4, 4), sheet, workbook);
//					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 4, 4), sheet, workbook);
//					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 4, 4), sheet, workbook);
//					cell3_4.setCellStyle(cellStyleBackground);
//					
//					HashMap<String, Integer> params = new HashMap<String, Integer>();
//					
//					for(StockTransDTO dept:listDeptReceive){
//						Cell	cell4_5=row4.createCell(countCell);
//						cell4_5.setCellValue(dept.getDeptReceiveName());
//						cell4_5.setCellStyle(cellStyleBackground);
//						params.put(dept.getDeptReceiveName(), countCell);
//						countCell++;
//					}
//					
//					
//					
//					int rowIndex=5;
//					Long goodsId=0l;
//					String goodsState=null;
//					int stt=1;
//					for(int i=0;i<detailDTOs.size();i++){
//						
//						
//						
//						if(i==0){
//							Row row5= sheet.createRow(rowIndex);
//							Cell cell5_0 = row5.createCell(0);
//							cell5_0.setCellValue(stt);
//							cell5_0.setCellStyle(cellStyleCenter);
//							
//							Cell cell5_1 = row5.createCell(1);
//							cell5_1.setCellValue(detailDTOs.get(i).getGoodsName());
//							cell5_1.setCellStyle(cellStyle);
//							
//							Cell cell5_2 = row5.createCell(2);
//							cell5_2.setCellValue(detailDTOs.get(i).getGoodsCode());
//							cell5_2.setCellStyle(cellStyle);
//							
//							
//							Cell cell5_3 = row5.createCell(3);
//							cell5_3.setCellValue(detailDTOs.get(i).getGoodsUnitName());
//							cell5_3.setCellStyle(cellStyle);
//							
//							
//							Cell cell5_4 = row5.createCell(4);
//							cell5_4.setCellValue(detailDTOs.get(i).getGoodsStateName());
//							cell5_4.setCellStyle(cellStyle);
//							
//							for(int j=5;j<countCell;j++){
//								Cell	cellx=row5.createCell(j);
//								cellx.setCellStyle(cellStyle);
//							}
//							
//							Cell cell5_5 = row5.createCell(params.get(detailDTOs.get(i).getDeptReceiveName()));
//							cell5_5.setCellValue(detailDTOs.get(i).getTotalPrice());
//							cell5_5.setCellStyle(cellStyle);
//							
//							
//							goodsId=detailDTOs.get(i).getGoodsId();
//							goodsState=detailDTOs.get(i).getGoodsStateName();
//						stt++;
//						rowIndex++;
//						} else {
//							if(goodsId==detailDTOs.get(i).getGoodsId().longValue() && goodsState.equals(detailDTOs.get(i).getGoodsStateName())){
//								
//									
//									Row row5= sheet.getRow(rowIndex-1);
//									Cell cell5_5 = row5.createCell(params.get(detailDTOs.get(i).getDeptReceiveName()));
//									cell5_5.setCellValue(detailDTOs.get(i).getTotalPrice());
//									cell5_5.setCellStyle(cellStyle);
//									
//							} else {
//								
//								
//								
//								Row row5= sheet.createRow(rowIndex);
//								
//								
//								
//								Cell cell5_0 = row5.createCell(0);
//								cell5_0.setCellValue(stt);
//								cell5_0.setCellStyle(cellStyleCenter);
//								
//								Cell cell5_1 = row5.createCell(1);
//								cell5_1.setCellValue(detailDTOs.get(i).getGoodsName());
//								cell5_1.setCellStyle(cellStyle);
//								
//								Cell cell5_2 = row5.createCell(2);
//								cell5_2.setCellValue(detailDTOs.get(i).getGoodsCode());
//								cell5_2.setCellStyle(cellStyle);
//								
//								
//								Cell cell5_3 = row5.createCell(3);
//								cell5_3.setCellValue(detailDTOs.get(i).getGoodsUnitName());
//								cell5_3.setCellStyle(cellStyle);
//								
//								
//								Cell cell5_4 = row5.createCell(4);
//								cell5_4.setCellValue(detailDTOs.get(i).getGoodsStateName());
//								cell5_4.setCellStyle(cellStyle);
//								
//									for(int j=5;j<countCell;j++){
//										Cell	cellx=row5.createCell(j);
//										cellx.setCellStyle(cellStyle);
//									}
//								
//								Cell cell5_5 = row5.createCell(params.get(detailDTOs.get(i).getDeptReceiveName()));
//								cell5_5.setCellValue(detailDTOs.get(i).getTotalPrice());
//								cell5_5.setCellStyle(cellStyle);
//								
//								goodsId=detailDTOs.get(i).getGoodsId();
//								goodsState=detailDTOs.get(i).getGoodsStateName();
//								
//								
//								
//								stt++;
//								rowIndex++;
//								
//									
//									
//									
//								
//							}
//							
//							
//							
//						}
//						
//					}
//					
//					Cell cell3_x = row3.createCell(5);
//					cell3_x.setCellValue("Nơi xuất");
//					sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, countCell));
//					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 5, countCell), sheet, workbook);
//					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 5, countCell), sheet, workbook);
//					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 5, countCell), sheet, workbook);
//					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(3, 4, 5, countCell), sheet, workbook);
//					cell3_x.setCellStyle(cellStyleBackground);
//					
//					Cell cell4_x = row4.createCell(countCell);
//					cell4_x.setCellValue("Tổng số");
//					cell4_x.setCellStyle(cellStyleBackground);
//					
//					List<BigDecimal> bigDecimals=stockTransDAO.getListTotal(ids);
//					int rowIndex2=5;
//					for(BigDecimal total:bigDecimals){
//						Row row=sheet.getRow(rowIndex2);
//						Cell cell=row.createCell(countCell);
//						cell.setCellValue(total.doubleValue());
//						cell.setCellStyle(cellStyle);
//						rowIndex2++;
//					}
//					
//					sheet.autoSizeColumn(1);
//					sheet.autoSizeColumn(2);
//					sheet.autoSizeColumn(3);
//					sheet.autoSizeColumn(4);
//					sheet.autoSizeColumn(5);
//			
//			
//			file.close();
//			File out = new File(folder2Upload + File.separatorChar + "BangKeVatTu.xlsx");
//			FileOutputStream outFile = new FileOutputStream(out);
//			System.out.println(out.getCanonicalPath());
//			workbook.write(outFile);
//			workbook.close();
//			outFile.close();
//
//		} catch (FileNotFoundException e) {
//			LOGGER.error(e.getMessage(), e);
//		} catch (IOException e) {
//			LOGGER.error(e.getMessage(), e);
//		}
//		return UEncrypt.encryptFileUploadPath("BangKeVatTu.xlsx");
//	}
//
//}
