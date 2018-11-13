///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.text.ParseException;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.org.eclipse.jdt.core.dom.SwitchCase;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import com.viettel.asset.filter.session.UserSession;
//import com.viettel.ktts2.dto.KttsUserSession;
////import com.viettel.wms.business.AppParamBusinessImpl;
//import com.viettel.wms.business.DepartmentBusinessImpl;
//import com.viettel.wms.business.KpiStorageAmountBusinessImpl;
//import com.viettel.wms.business.KpiStorageTimeBusinessImpl;
//import com.viettel.wms.business.OrderBusinessImpl;
//import com.viettel.wms.business.StockBusinessImpl;
//import com.viettel.wms.business.StockTransBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
////import com.viettel.wms.dao.AppParamDAO;
//import com.viettel.wms.dto.CommonDTO;
//import com.viettel.wms.dto.DepartmentDTO;
//import com.viettel.wms.dto.StockDTO;
//
//
//public class CommonRsServiceImpl implements CommonRsService {
//
//	
//	@Value("${folder_upload2}")
//	private String folderUpload;
//
//	@Value("${folder_upload}")
//	private String folderTemp;
//
//	@Value("${default_sub_folder_upload}")
//	private String defaultSubFolderUpload;
//	@Context HttpServletRequest request;
////    @Autowired
////    AppParamBusinessImpl appParamBusinessImpl;
//    
////    @Autowired
////    StockTransBusinessImpl stockTransBusinessImpl;
////    
////    @Autowired
////    OrderBusinessImpl orderBusinessImpl;
////    
////    @Autowired
////    KpiStorageAmountBusinessImpl kpiStorageAmountBusinessImpl;
////    
////    @Autowired
////    KpiStorageTimeBusinessImpl kpiStorageTimeBusinessImpl;
////    
////    @Autowired
////    DepartmentBusinessImpl departmentBusinessImpl;
////    
////    @Autowired
////    StockBusinessImpl stockBusinessImpl;
//    @Autowired
//	private UserRoleBusinessImpl userRoleBusinessImpl;
//    
//    private final static String Config_Code_One="{VALUE}{ORG}/{YY}/";
//    private final static String Config_Code_Two="{VALUE}_{STOCK}/{YY}/";
//    
//    @Override
//    public Response genCode(CommonDTO obj) {
//    	String code=null;
//    	Calendar cal = Calendar.getInstance();
//    	model table=getTable(obj.getValue());
//    	String year=String.valueOf(cal.getTime().getYear()).substring(1);
//    	String param=table.getValue();
//    	param=param.replaceAll("\\{VALUE\\}", Matcher.quoteReplacement(obj.getValue()));
//    	if(StringUtils.isNotEmpty(obj.getStockValue())){
//    		param=param.replaceAll("\\{STOCK\\}", Matcher.quoteReplacement(obj.getStockValue()));
//    	}else if(obj.getStockId()!=null){
//    		StockDTO stock= (StockDTO) stockBusinessImpl.getOneById(obj.getStockId());
//    		param=param.replaceAll("\\{STOCK\\}", Matcher.quoteReplacement(stock.getCode()));
//    		
//    	}
//    	
//    	KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//    	DepartmentDTO depat=(DepartmentDTO) departmentBusinessImpl.getOne(objUser.getVpsUserInfo().getDepartmentId());
//    	param=param.replaceAll("\\{ORG\\}", Matcher.quoteReplacement(depat.getCode()));
//    		
//    	param=param.replaceAll("\\{YY\\}", year);
//    	String tableName=table.getTableName();
//    	if(!StringUtils.isNotEmpty(tableName)){
//    		return Response.ok(null).build();
//    	} 
////    	String numberStr=appParamBusinessImpl.getCode(tableName, param);
//    	String numberStr = null;
//    	code=param+numberStr;
//    	return Response.ok(code).build();
//    }
//    
//    
//    
//    
//    private model getTable(String value){
//    	
//    	switch (value) {
//		case "YCNK":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"ORDER\"");
//			obj.setValue(Config_Code_One);
//			return obj;
//		}
//		case "YCXK":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"ORDER\"");
//			obj.setValue(Config_Code_One);
//			return obj;
//		}
//		case "PNK":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"STOCK_TRANS\"");
//			obj.setValue(Config_Code_One);
//			return obj;
//		}
//		case "PXK":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"STOCK_TRANS\"");
//			obj.setValue(Config_Code_One);
//			return obj;
//		}
//		case "YCTD":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"ORDER_CHANGE_GOODS\"");
//			obj.setValue(Config_Code_One);
//			return obj;
//		}
//		default:
//			return null;
//		}
//    	
//    	
//    }
//
//    public class model{
//    	private String value;
//    	public String getValue() {
//			return value;
//		}
//		public void setValue(String value) {
//			this.value = value;
//		}
//		public String getTableName() {
//			return tableName;
//		}
//		public void setTableName(String tableName) {
//			this.tableName = tableName;
//		}
//		private String tableName;
//    }
//    
//    @Override
//    public Response getCharFour(CommonDTO obj){
//    	
//    	return Response.ok(stockTransBusinessImpl.getCharFour(obj,request)).build();
//    }
//    
//    @Override
//    public Response getCharThree(CommonDTO obj){
//    	return Response.ok(orderBusinessImpl.getCharThree(obj,request)).build();
//    }
//    
//    @Override
//    public Response getCharOneAmount(CommonDTO obj){
//    	return Response.ok(kpiStorageAmountBusinessImpl.getCharOneAmount(obj,request)).build();
//    }
//
//
//	@Override
//	public Response getCharOneTimes(CommonDTO obj) {
//		return Response.ok(kpiStorageAmountBusinessImpl.getCharOneTimes(obj,request)).build();
//	}
//	
//	@Override
//	public Response getCharTwoWeek(CommonDTO obj) {
//		return Response.ok(stockTransBusinessImpl.getCharTwoWeek(obj,request)).build();
//	}
//	
//	@Override
//	public Response getCharTwoMonth(CommonDTO obj) throws ParseException {
//		return Response.ok(stockTransBusinessImpl.getCharTwoMonth(obj,request)).build();
//	}
//
//
//	@Override
//	public Response exportExcelTemplate(String fileName) throws Exception {
////		String strReturn = appParamBusinessImpl.exportExcelTemplate(fileName);
//		String strReturn = null;
//		return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
//	}
//    
//}
