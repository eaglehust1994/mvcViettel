/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.Common.rest;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.Common.CommonBussiness.CommonBusinessImpl;
import com.viettel.Common.CommonBussiness.UserRoleBusinessImpl;


public class CommonRsServiceImpl implements CommonRsService {

	
	@Value("${folder_upload2}")
	private String folderUpload;

	@Value("${folder_upload}")
	private String folderTemp;

	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;
	@Context HttpServletRequest request;
//    protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    CommonBusinessImpl commonBusinessImpl;
    
//    @Autowired
//    StockTransBusinessImpl stockTransBusinessImpl;
//    
// 
//
//    
//    @Autowired
//    DepartmentBusinessImpl departmentBusinessImpl;
//    
//    @Autowired
//    StockBusinessImpl stockBusinessImpl;
    @Autowired
	private UserRoleBusinessImpl userRoleBusinessImpl;
    
    private final static String Config_Code_One="{VALUE}{ORG}/{YY}/";
    private final static String Config_Code_Two="{VALUE}_{STOCK}/{YY}/";
    
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
//    	String numberStr=appParamBusinessImpl.getCode(tableName, param);
//    	code=param+numberStr;
//    	return Response.ok(code).build();
//    }
    
    
    
    
    private model getTable(String value){
    	
    	switch (value) {
		case "YCNK":{
			model obj= new model();
			obj.setTableName("WMS_OWNER_KTTS.\"ORDER\"");
			obj.setValue(Config_Code_One);
			return obj;
		}
		case "YCXK":{
			model obj= new model();
			obj.setTableName("WMS_OWNER_KTTS.\"ORDER\"");
			obj.setValue(Config_Code_One);
			return obj;
		}
		case "PNK":{
			model obj= new model();
			obj.setTableName("WMS_OWNER_KTTS.\"STOCK_TRANS\"");
			obj.setValue(Config_Code_One);
			return obj;
		}
		case "PXK":{
			model obj= new model();
			obj.setTableName("WMS_OWNER_KTTS.\"STOCK_TRANS\"");
			obj.setValue(Config_Code_One);
			return obj;
		}
		case "YCTD":{
			model obj= new model();
			obj.setTableName("WMS_OWNER_KTTS.\"ORDER_CHANGE_GOODS\"");
			obj.setValue(Config_Code_One);
			return obj;
		}
		default:
			return null;
		}
    	
    	
    }

    public class model{
    	private String value;
    	public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		private String tableName;
    }
    


	@Override
	public Response exportExcelTemplate(String fileName) throws Exception {
		String l ="abc";
		String a = l;
		String strReturn = commonBusinessImpl.exportExcelTemplate(fileName);
		return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
	}
    
}
