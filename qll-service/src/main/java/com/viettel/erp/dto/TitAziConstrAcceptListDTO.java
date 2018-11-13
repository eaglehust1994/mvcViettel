/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

import com.google.common.base.Strings;
import com.viettel.erp.bo.TitAziConstrAcceptBO;
import com.viettel.erp.bo.TitAziConstrAcceptListBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.utils.StringUtils;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author thuannht
 */
@XmlRootElement(name = "TIT_AZI_CONSTR_ACCEPT_LISTBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TitAziConstrAcceptListDTO extends BaseFWDTOImpl<TitAziConstrAcceptListBO> {

private java.lang.Long titAziConAcceptListId;
private java.lang.String content;
private java.lang.String unit;
private java.lang.String titleCornerBAdjust;
private java.lang.String azimuthDirectBAdjust;
private java.lang.String titleCornerAAdjust;
private java.lang.String azimuthDirectAAdjust;
private java.lang.String note;
private java.lang.Long titAziConstrAcceptId;

private java.lang.Integer stt;


public java.lang.Integer getStt() {
	return stt;
}

public void setStt(java.lang.Integer stt) {
	this.stt = stt;
}

    @Override
    public TitAziConstrAcceptListBO toModel() {
        TitAziConstrAcceptListBO titAziConstrAcceptListBO = new TitAziConstrAcceptListBO();
        titAziConstrAcceptListBO.setTitAziConAcceptListId(this.titAziConAcceptListId);
        titAziConstrAcceptListBO.setContent(this.content);
        titAziConstrAcceptListBO.setUnit(this.unit);
        titAziConstrAcceptListBO.setTitleCornerBAdjust(this.titleCornerBAdjust);
        titAziConstrAcceptListBO.setAzimuthDirectBAdjust(this.azimuthDirectBAdjust);
        titAziConstrAcceptListBO.setTitleCornerAAdjust(this.titleCornerAAdjust);
        titAziConstrAcceptListBO.setAzimuthDirectAAdjust(this.azimuthDirectAAdjust);
        titAziConstrAcceptListBO.setNote(this.note);
        titAziConstrAcceptListBO.setTitAziConstrAccept(this.titAziConstrAcceptId == null ? null : new TitAziConstrAcceptBO(titAziConstrAcceptId) );
        return titAziConstrAcceptListBO;
    }

    @Override
     public Long getFWModelId() {
        return titAziConAcceptListId;
    }
   
    @Override
    public String catchName() {
        return getTitAziConAcceptListId().toString();
    }
    public java.lang.Long getTitAziConAcceptListId(){
    return titAziConAcceptListId;
    }
    public void setTitAziConAcceptListId(java.lang.Long titAziConAcceptListId)
    {
    this.titAziConAcceptListId = titAziConAcceptListId;
    }
    
    public java.lang.String getContent(){
    return content;
    }
    public void setContent(java.lang.String content)
    {
    this.content = content;
    }
    
    public java.lang.String getUnit(){
    return unit;
    }
    public void setUnit(java.lang.String unit)
    {
    this.unit = unit;
    }
    
    public java.lang.String getTitleCornerBAdjust(){
    return titleCornerBAdjust;
    }
    public void setTitleCornerBAdjust(java.lang.String titleCornerBAdjust)
    {
    this.titleCornerBAdjust = titleCornerBAdjust;
    }
    
    public java.lang.String getAzimuthDirectBAdjust(){
    return azimuthDirectBAdjust;
    }
    public void setAzimuthDirectBAdjust(java.lang.String azimuthDirectBAdjust)
    {
    this.azimuthDirectBAdjust = azimuthDirectBAdjust;
    }
    
    public java.lang.String getTitleCornerAAdjust(){
    return titleCornerAAdjust;
    }
    public void setTitleCornerAAdjust(java.lang.String titleCornerAAdjust)
    {
    this.titleCornerAAdjust = titleCornerAAdjust;
    }
    
    public java.lang.String getAzimuthDirectAAdjust(){
    return azimuthDirectAAdjust;
    }
    public void setAzimuthDirectAAdjust(java.lang.String azimuthDirectAAdjust)
    {
    this.azimuthDirectAAdjust = azimuthDirectAAdjust;
    }
    
    public java.lang.String getNote(){
    return Strings.nullToEmpty(note);
    }
    public void setNote(java.lang.String note)
    {
    this.note = note;
    }
    
    public java.lang.Long getTitAziConstrAcceptId(){
    return titAziConstrAcceptId;
    }
    public void setTitAziConstrAcceptId(java.lang.Long titAziConstrAcceptId)
    {
    this.titAziConstrAcceptId = titAziConstrAcceptId;
    }
    
   
}
