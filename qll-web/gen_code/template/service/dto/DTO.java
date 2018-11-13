package com.viettel.ims.dto;

import com.viettel.ims.bo.${tbl.tableNameJV}BO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import java.util.Date;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "${tbl.tableName}BO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ${tbl.tableNameJV}DTO extends BaseFWDTOImpl<${tbl.tableNameJV}BO> {

    <#list  tbl.columnBOs as clm >
	<#if clm.dataTypeJV?contains("Date")>
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	</#if>
	private ${clm.dataTypeJV} ${clm.columnVar};
	<#if clm.dataTypeJV?contains("Date")>
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ${clm.columnVar}From;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ${clm.columnVar}To;
	</#if>
	<#if clm.columnVar?contains("Id") && clm.isKey == false>
	private java.lang.String ${clm.columnVar?replace("Id", "Name")};
	</#if>	
    </#list>
	private int size;
	private int page;
	private String orderBy;
	private String orderType;
	private int totalRecord;
	private String keySearch;//dùng cho autocomplete
	
	public String getKeySearch(){
		return keySearch;
	}
	public void setKeySearch(String key){
		this.keySearch=key;
	}
	

    @Override
    public ${tbl.tableNameJV}BO toModel() {
        ${tbl.tableNameJV}BO ${tbl.tableNameVar}BO = new ${tbl.tableNameJV}BO();
        <#list tbl.columnBOs as clm>
        ${tbl.tableNameVar}BO.set${clm.columnNameJV}(this.${clm.columnVar});		
       </#list>       
        return ${tbl.tableNameVar}BO;
    }

    <#list  tbl.columnBOs as clm >
    <#if clm.isKey == true>   
    @Override
     public Long getFWModelId() {
        return ${clm.columnVar};
    }
   
    @Override
    public String catchName() {
        return get${clm.columnNameJV}().toString();
    }
    </#if>
	
	@JsonProperty("${clm.columnVar}")
    public ${clm.dataTypeJV} get${clm.columnNameJV}(){
		return ${clm.columnVar};
    }
	
    public void set${clm.columnNameJV}(${clm.dataTypeJV} ${clm.columnVar}){
		this.${clm.columnVar} = ${clm.columnVar};
    }
	
	<#if clm.columnVar?contains("Id") && clm.isKey == false>
	@JsonProperty("${clm.columnVar?replace("Id", "Name")}")
    public java.lang.String get${clm.columnNameJV?replace("Id", "Name")}(){
		return ${clm.columnVar?replace("Id", "Name")};
    }
	
    public void set${clm.columnNameJV?replace("Id", "Name")}(java.lang.String ${clm.columnVar?replace("Id", "Name")}){
		this.${clm.columnVar?replace("Id", "Name")} = ${clm.columnVar?replace("Id", "Name")};
    }
	
	</#if>
	<#if clm.columnVar?contains("atedby") && clm.isKey == false>
	@JsonProperty("${clm.columnVar}Name")
    public java.lang.String get${clm.columnNameJV}Name(){
		return ${clm.columnVar}Name;
    }
	
    public void set${clm.columnNameJV}Name(java.lang.String ${clm.columnVar}Name){
		this.${clm.columnVar}Name = ${clm.columnVar}Name;
    }
	
	</#if>
	<#if clm.dataTypeJV?contains("Date")>
	public java.util.Date get${clm.columnNameJV}From() {
    	return ${clm.columnVar}From;
    }
	
    public void set${clm.columnNameJV}From(java.util.Date ${clm.columnVar}From) {
    	this.${clm.columnVar}From = ${clm.columnVar}From;
    }
	
	public java.util.Date get${clm.columnNameJV}To() {
    	return ${clm.columnVar}To;
    }
	
    public void set${clm.columnNameJV}To(java.util.Date ${clm.columnVar}To) {
    	this.${clm.columnVar}To = ${clm.columnVar}To;
    }
	</#if>
    </#list>
	
	@JsonProperty("page")
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@JsonProperty("size")
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@JsonProperty("orderType")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		if("asc".equals(orderType)||"desc".equals(orderType)){
			this.orderType=orderType;
		}else{
			this.orderType="";
		}
				
	}

	@JsonProperty("orderBy")
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	;
	
	public int getTotalRecord(){
		return totalRecord;
	}
	
	public void setTotalRecord(int total){
			totalRecord=total;
	}
}
