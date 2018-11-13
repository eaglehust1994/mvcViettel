package com.viettel.asset.dto;

import java.util.Date;
import com.viettel.asset.bo.UtilAttachedDocument;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "utilAttachedDocument")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UtilAttachedDocumentDto {
	
	
		private Long attachId;
		private String documentName;
		private String documentPath;
		private Long type;
		private Long parentId;
		private Date createdDate;
		private Long levelId;
		private String signer;
		private String code;
		private Date signedDate;
		private String description;
		private Long userId;
		private Long fileType;
		private Long documenTypeId;
		private String documentTypeName;
	
	public UtilAttachedDocumentDto() {
		
	}
	
	public UtilAttachedDocumentDto(UtilAttachedDocument entity) {
		this.attachId = entity.getAttachId();
		this.documentName = entity.getDocumentName();
		this.documentPath = entity.getDocumentPath();
		this.type = entity.getType();
		this.parentId = entity.getParentId();
		this.createdDate = entity.getCreatedDate();
		this.levelId = entity.getLevelId();
		this.signer = entity.getSigner();
		this.code = entity.getCode();
		this.signedDate = entity.getSignedDate();
		this.description = entity.getDescription();
		this.userId = entity.getUserId();
		this.fileType = entity.getFileType();
		this.documenTypeId = entity.getDocumenTypeId();
		this.documentTypeName = entity.getDocumentTypeName();
	}
	
	public UtilAttachedDocument toEntity() {
		UtilAttachedDocument entity = new UtilAttachedDocument();
		entity.setAttachId(this.attachId);
		entity.setDocumentName(this.documentName);
		entity.setDocumentPath(this.documentPath);
		entity.setType(this.type);
		entity.setParentId(this.parentId);
		entity.setCreatedDate(this.createdDate);
		entity.setLevelId(this.levelId);
		entity.setSigner(this.signer);
		entity.setCode(this.code);
		entity.setSignedDate(this.signedDate);
		entity.setDescription(this.description);
		entity.setUserId(this.userId);
		entity.setFileType(this.fileType);
		entity.setDocumenTypeId(this.documenTypeId);
		entity.setDocumentTypeName(this.documentTypeName);
		return entity;
	}
	
	public void updateEntity(UtilAttachedDocument entity) {
			entity.setDocumentName(this.documentName);
			entity.setDocumentPath(this.documentPath);
			entity.setType(this.type);
			entity.setParentId(this.parentId);
			entity.setCreatedDate(this.createdDate);
			entity.setLevelId(this.levelId);
			entity.setSigner(this.signer);
			entity.setCode(this.code);
			entity.setSignedDate(this.signedDate);
			entity.setDescription(this.description);
			entity.setUserId(this.userId);
			entity.setFileType(this.fileType);
			entity.setDocumenTypeId(this.documenTypeId);
			entity.setDocumentTypeName(this.documentTypeName);
	}
	
		public Long getAttachId() {
			return attachId;
		}
		public void setAttachId(Long attachId) {
			this.attachId = attachId;
		}		
		public String getDocumentName() {
			return documentName;
		}
		public void setDocumentName(String documentName) {
			this.documentName = documentName;
		}		
		public String getDocumentPath() {
			return documentPath;
		}
		public void setDocumentPath(String documentPath) {
			this.documentPath = documentPath;
		}		
		public Long getType() {
			return type;
		}
		public void setType(Long type) {
			this.type = type;
		}		
		public Long getParentId() {
			return parentId;
		}
		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}		
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}		
		public Long getLevelId() {
			return levelId;
		}
		public void setLevelId(Long levelId) {
			this.levelId = levelId;
		}		
		public String getSigner() {
			return signer;
		}
		public void setSigner(String signer) {
			this.signer = signer;
		}		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}		
		public Date getSignedDate() {
			return signedDate;
		}
		public void setSignedDate(Date signedDate) {
			this.signedDate = signedDate;
		}		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}		
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}		
		public Long getFileType() {
			return fileType;
		}
		public void setFileType(Long fileType) {
			this.fileType = fileType;
		}		
		public Long getDocumenTypeId() {
			return documenTypeId;
		}
		public void setDocumenTypeId(Long documenTypeId) {
			this.documenTypeId = documenTypeId;
		}		
		public String getDocumentTypeName() {
			return documentTypeName;
		}
		public void setDocumentTypeName(String documentTypeName) {
			this.documentTypeName = documentTypeName;
		}		
	
}
