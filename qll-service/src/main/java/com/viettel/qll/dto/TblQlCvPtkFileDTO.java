package com.viettel.qll.dto;

import com.viettel.qll.bo.TblQlCvPtkFileBO;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_QL_CV_PTK_FILEBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblQlCvPtkFileDTO extends QllBaseDTO<TblQlCvPtkFileBO> {

	private java.lang.Long tblQlCvPtkFileId;
	private java.lang.String name;
	private java.lang.String pathFile;
	private java.lang.Long idCvPtk;
	private java.lang.String fileType;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdAt;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdAtFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date createdAtTo;
	private java.lang.String createdBy;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedAt;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedAtFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date updatedAtTo;
	private java.lang.String updatedBy;
	

    @Override
    public TblQlCvPtkFileBO toModel() {
        TblQlCvPtkFileBO tblQlCvPtkFileBO = new TblQlCvPtkFileBO();
        tblQlCvPtkFileBO.setTblQlCvPtkFileId(this.tblQlCvPtkFileId);
        tblQlCvPtkFileBO.setName(this.name);
        tblQlCvPtkFileBO.setPathFile(this.pathFile);
        tblQlCvPtkFileBO.setIdCvPtk(this.idCvPtk);
        tblQlCvPtkFileBO.setFileType(this.fileType);
        tblQlCvPtkFileBO.setCreatedAt(this.createdAt);
        tblQlCvPtkFileBO.setCreatedBy(this.createdBy);
        tblQlCvPtkFileBO.setUpdatedAt(this.updatedAt);
        tblQlCvPtkFileBO.setUpdatedBy(this.updatedBy);
        return tblQlCvPtkFileBO;
    }

    @Override
     public Long getFWModelId() {
        return tblQlCvPtkFileId;
    }
   
    @Override
    public String catchName() {
        return getTblQlCvPtkFileId().toString();
    }
	
	@JsonProperty("tblQlCvPtkFileId")
    public java.lang.Long getTblQlCvPtkFileId(){
		return tblQlCvPtkFileId;
    }
	
    public void setTblQlCvPtkFileId(java.lang.Long tblQlCvPtkFileId){
		this.tblQlCvPtkFileId = tblQlCvPtkFileId;
    }	
	
	@JsonProperty("pathFile")
    public java.lang.String getPathFile(){
		return pathFile;
    }
	
    public void setPathFile(java.lang.String pathFile){
		this.pathFile = pathFile;
    }	
	
	@JsonProperty("idCvPtk")
    public java.lang.Long getIdCvPtk(){
		return idCvPtk;
    }
	
    public void setIdCvPtk(java.lang.Long idCvPtk){
		this.idCvPtk = idCvPtk;
    }	
	
	@JsonProperty("fileType")
    public java.lang.String getFileType(){
		return fileType;
    }
	
    public void setFileType(java.lang.String fileType){
		this.fileType = fileType;
    }	
	
	@JsonProperty("createdAt")
    public java.util.Date getCreatedAt(){
		return createdAt;
    }
	
    public void setCreatedAt(java.util.Date createdAt){
		this.createdAt = createdAt;
    }	
	
	public java.util.Date getCreatedAtFrom() {
    	return createdAtFrom;
    }
	
    public void setCreatedAtFrom(java.util.Date createdAtFrom) {
    	this.createdAtFrom = createdAtFrom;
    }
	
	public java.util.Date getCreatedAtTo() {
    	return createdAtTo;
    }
	
    public void setCreatedAtTo(java.util.Date createdAtTo) {
    	this.createdAtTo = createdAtTo;
    }
	
    
    public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}

	public java.util.Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(java.util.Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public java.util.Date getUpdatedAtFrom() {
		return updatedAtFrom;
	}

	public void setUpdatedAtFrom(java.util.Date updatedAtFrom) {
		this.updatedAtFrom = updatedAtFrom;
	}

	public java.util.Date getUpdatedAtTo() {
		return updatedAtTo;
	}

	public void setUpdatedAtTo(java.util.Date updatedAtTo) {
		this.updatedAtTo = updatedAtTo;
	}

	public java.lang.String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getText() {
		return text;
	}



	private String text;
	private int start;
	private int maxResult;
    
	@JsonProperty("start")
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JsonProperty("maxResult")
	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
	
	
	public void setText(String text) {
		this.text = text;
	}
}
