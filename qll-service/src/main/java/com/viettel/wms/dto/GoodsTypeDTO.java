/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;

import java.util.List;

import com.viettel.erp.utils.JsonDateDeserializer;
import com.viettel.erp.utils.JsonDateSerializerDate;
import com.viettel.wms.bo.GoodsTypeBO;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author TuanNB
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "GOODS_TYPEBO")
public class GoodsTypeDTO extends wmsBaseDTO<GoodsTypeBO> {

	private java.lang.Long goodsTypeId;
	private java.lang.String code;
	private java.lang.String name;
	private java.lang.String status;
	private java.lang.String goodsState;
	private java.lang.String typeKpi;
	private java.lang.String reponseStatus;
	private List<Long> listStockId;
	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date startDate;

	@JsonSerialize(using = JsonDateSerializerDate.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private java.util.Date endDate;
    public java.util.Date getStartDate() {
		return startDate;
	}



	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}



	public java.util.Date getEndDate() {
		return endDate;
	}



	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}



	@Override
    public GoodsTypeBO toModel() {
    	GoodsTypeBO goodsTypeBO = new GoodsTypeBO();
        goodsTypeBO.setGoodsTypeId(this.goodsTypeId);
        goodsTypeBO.setCode(this.code);;
        goodsTypeBO.setName(this.name);
        goodsTypeBO.setStatus(this.status);
        return goodsTypeBO;
    }

   
	
	public java.lang.String getTypeKpi() {
		return typeKpi;
	}



	public void setTypeKpi(java.lang.String typeKpi) {
		this.typeKpi = typeKpi;
	}



	public java.lang.String getCode() {
		return code;
	}
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	
	public java.lang.Long getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(java.lang.Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public java.lang.String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(java.lang.String goodsState) {
		this.goodsState = goodsState;
	}

	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getStatus() {
		return status;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getReponseStatus() {
		return reponseStatus;
	}
	public void setReponseStatus(java.lang.String reponseStatus) {
		this.reponseStatus = reponseStatus;
	}

	public List<Long> getListStockId() {
		return listStockId;
	}
	public void setListStockId(List<Long> listStockId) {
		this.listStockId = listStockId;
	}



	@Override
     public Long getFWModelId() {
        return goodsTypeId;
    }
   
    @Override
    public String catchName() {
        return getGoodsTypeId().toString();
    }
   
}
