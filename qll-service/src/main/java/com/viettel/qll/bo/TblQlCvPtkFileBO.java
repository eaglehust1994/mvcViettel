package com.viettel.qll.bo;

import com.viettel.qll.dto.TblQlCvPtkFileDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.erp.bo.TblQlCvPtkFileBO")
@Table(name = "TBL_QL_CV_PTK_FILE")
/**
 *
 * @author: hailh10
 */
public class TblQlCvPtkFileBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_QL_CV_FILE_SEQ") })
	@Column(name = "TBL_QL_CV_PTK_FILE_ID", length = 22)
	private java.lang.Long tblQlCvPtkFileId;
	@Column(name = "NAME", length = 2000)
	private java.lang.String name;
	@Column(name = "PATH_FILE", length = 2000)
	private java.lang.String pathFile;
	@Column(name = "ID_CV_PTK", length = 22)
	private java.lang.Long idCvPtk;
	@Column(name = "FILE_TYPE", length = 100)
	private java.lang.String fileType;
	@Column(name = "CREATED_AT", length = 7)
	private java.util.Date createdAt;
	@Column(name = "CREATED_BY", length = 2000)
	private java.lang.String createdBy;
	@Column(name = "UPDATED_AT", length = 7)
	private java.util.Date updatedAt;
	@Column(name = "UPDATED_BY", length = 2000)
	private java.lang.String updatedBy;

	
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

	public java.lang.String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public java.lang.Long getTblQlCvPtkFileId(){
		return tblQlCvPtkFileId;
	}
	
	public void setTblQlCvPtkFileId(java.lang.Long tblQlCvPtkFileId)
	{
		this.tblQlCvPtkFileId = tblQlCvPtkFileId;
	}
	
	public java.lang.String getPathFile(){
		return pathFile;
	}
	
	public void setPathFile(java.lang.String pathFile)
	{
		this.pathFile = pathFile;
	}
	
	public java.lang.Long getIdCvPtk(){
		return idCvPtk;
	}
	
	public void setIdCvPtk(java.lang.Long idCvPtk)
	{
		this.idCvPtk = idCvPtk;
	}
	
	public java.lang.String getFileType(){
		return fileType;
	}
	
	public void setFileType(java.lang.String fileType)
	{
		this.fileType = fileType;
	}
	
	public java.util.Date getCreatedAt(){
		return createdAt;
	}
	
	public void setCreatedAt(java.util.Date createdAt)
	{
		this.createdAt = createdAt;
	}
   
    @Override
    public TblQlCvPtkFileDTO toDTO() {
        TblQlCvPtkFileDTO tblQlCvPtkFileDTO = new TblQlCvPtkFileDTO(); 
        tblQlCvPtkFileDTO.setTblQlCvPtkFileId(this.tblQlCvPtkFileId);
        tblQlCvPtkFileDTO.setName(this.name);
        tblQlCvPtkFileDTO.setPathFile(this.pathFile);		
        tblQlCvPtkFileDTO.setIdCvPtk(this.idCvPtk);		
        tblQlCvPtkFileDTO.setFileType(this.fileType);		
        tblQlCvPtkFileDTO.setCreatedAt(this.createdAt);	
        tblQlCvPtkFileDTO.setCreatedBy(this.createdBy);
        tblQlCvPtkFileDTO.setUpdatedAt(this.updatedAt);
        tblQlCvPtkFileDTO.setUpdatedBy(this.updatedBy);
        return tblQlCvPtkFileDTO;
    }
}
