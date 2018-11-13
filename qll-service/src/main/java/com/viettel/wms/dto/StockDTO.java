/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import com.google.common.collect.Lists;
import com.viettel.wms.bo.StockBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author PhongPV
 */
@XmlRootElement(name = "STOCKBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockDTO extends wmsBaseDTO<StockBO> {

private static final long serialVersionUID = 3344516509594190609L;
private java.util.Date updatedDate;
private java.lang.Long stockId;
private java.lang.String code;
private java.lang.String name;
private java.lang.String status;
private java.lang.Long parentId;
private java.lang.String path;
private java.lang.String description;
private java.lang.Long departmentId;
private java.lang.String departmentName;
private java.lang.Long updatedBy;
private java.lang.String type;
private java.lang.String levelST;
private java.lang.Long createdBy;
private java.util.Date createdDate;
private java.lang.String codeCell;
private java.lang.String descriptionCell;
private java.lang.String nameStock;
private java.lang.String levelStock;
private String text;
private DomainDataDTO domainDataDTO;
private List<StockCellDTO> lstStockCell;
private List<Long> listStockId=Lists.newArrayList();
public List<Long> getListStockId() {
	return listStockId;
}

public void setListStockId(List<Long> listStockId) {
	this.listStockId = listStockId;
}

public String getText() {
	return this.code+"-" + this.name;
}

public void setText(String text) {
	this.text = text;
}

private List<Long> listId;
private List<SignVofficeDTO> listSignVoffice= Lists.newArrayList();

    @Override
    public StockBO toModel() {
        StockBO stockBO = new StockBO();
        stockBO.setUpdatedDate(this.updatedDate);
        stockBO.setStockId(this.stockId);
        stockBO.setCode(this.code);
        stockBO.setName(this.name);
        if (this.status == null || this.status.equalsIgnoreCase("1")) {
			stockBO.setStatus("1");
		} else {
			stockBO.setStatus(this.status);
		}
        stockBO.setParentId(this.parentId);
        stockBO.setPath(this.path);
        stockBO.setDescription(this.description);
        stockBO.setDepartmentId(this.departmentId);
        stockBO.setDepartmentName(this.departmentName);
        stockBO.setUpdatedBy(this.updatedBy);
        stockBO.setType(this.type);
        stockBO.setLevel(this.levelST);
        stockBO.setCreatedBy(this.createdBy);
        stockBO.setCreatedDate(this.createdDate);
        return stockBO;
    }

    public java.util.Date getUpdatedDate(){
    return updatedDate;
    }
    public void setUpdatedDate(java.util.Date updatedDate)
    {
    this.updatedDate = updatedDate;
    }
    
    public java.lang.Long getStockId(){
    return stockId;
    }
    public void setStockId(java.lang.Long stockId)
    {
    this.stockId = stockId;
    }
    
    public java.lang.String getCode(){
    return code;
    }
    public void setCode(java.lang.String code)
    {
    this.code = org.apache.commons.lang3.StringUtils.isNotEmpty(code) ? code.toUpperCase():null;
    }
    
    public java.lang.String getName(){
    return name;
    }
    public void setName(java.lang.String name)
    {
    this.name = name;
    }
    
    public java.lang.String getStatus(){
    return status;
    }
    public void setStatus(java.lang.String status)
    {
    this.status = status;
    }
    
    public java.lang.Long getParentId(){
    return parentId;
    }
    public void setParentId(java.lang.Long parentId)
    {
    this.parentId = parentId;
    }
    
    public java.lang.String getPath(){
    return path;
    }
    public void setPath(java.lang.String path)
    {
    this.path = path;
    }
    
    public java.lang.String getDescription(){
    return description;
    }
    public void setDescription(java.lang.String description)
    {
    this.description = description;
    }
    
    @Override
     public Long getFWModelId() {
        return stockId;
    }
   
    @Override
    public String catchName() {
        return getStockId().toString();
    }
    public java.lang.Long getDepartmentId(){
    return departmentId;
    }
    public void setDepartmentId(java.lang.Long departmentId)
    {
    this.departmentId = departmentId;
    }
    
    public java.lang.String getDepartmentName(){
    return departmentName;
    }
    public void setDepartmentName(java.lang.String departmentName)
    {
    this.departmentName = departmentName;
    }
    
    public java.lang.Long getUpdatedBy(){
    return updatedBy;
    }
    public void setUpdatedBy(java.lang.Long updatedBy)
    {
    this.updatedBy = updatedBy;
    }
    
    public java.lang.String getType(){
    return type;
    }
    public void setType(java.lang.String type)
    {
    this.type = type;
    }
    
    public java.lang.Long getCreatedBy(){
    return createdBy;
    }
    public void setCreatedBy(java.lang.Long createdBy)
    {
    this.createdBy = createdBy;
    }
    
    public java.util.Date getCreatedDate(){
    return createdDate;
    }
    public void setCreatedDate(java.util.Date createdDate)
    {
    this.createdDate = createdDate;
    }

	public java.lang.String getLevelST() {
		return levelST;
	}

	public void setLevelST(java.lang.String levelST) {
		this.levelST = levelST;
	}

	public java.lang.String getCodeCell() {
		return codeCell;
	}

	public void setCodeCell(java.lang.String codeCell) {
		this.codeCell = codeCell;
	}

	public java.lang.String getDescriptionCell() {
		return descriptionCell;
	}

	public void setDescriptionCell(java.lang.String descriptionCell) {
		this.descriptionCell = descriptionCell;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public java.lang.String getNameStock() {
		return nameStock;
	}

	public void setNameStock(java.lang.String nameStock) {
		this.nameStock = nameStock;
	}

	public java.lang.String getLevelStock() {
		return levelStock;
	}

	public void setLevelStock(java.lang.String levelStock) {
		this.levelStock = levelStock;
	}

	public List<Long> getListId() {
		return listId;
	}

	public void setListId(List<Long> listId) {
		this.listId = listId;
	}

	public List<SignVofficeDTO> getListSignVoffice() {
		return listSignVoffice;
	}

	public void setListSignVoffice(List<SignVofficeDTO> listSignVoffice) {
		this.listSignVoffice = listSignVoffice;
	}

	public DomainDataDTO getDomainDataDTO() {
		return domainDataDTO;
	}

	public void setDomainDataDTO(DomainDataDTO domainDataDTO) {
		this.domainDataDTO = domainDataDTO;
	}

	public List<StockCellDTO> getLstStockCell() {
		return lstStockCell;
	}

	public void setLstStockCell(List<StockCellDTO> lstStockCell) {
		this.lstStockCell = lstStockCell;
	}

	
	
    
   
}
