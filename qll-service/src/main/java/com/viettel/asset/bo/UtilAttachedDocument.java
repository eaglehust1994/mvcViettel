package com.viettel.asset.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "UTIL_ATTACHED_DOCUMENTS")
public class UtilAttachedDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String ATTACH_ID = "attachId";
        public static final String DOCUMENT_NAME = "documentName";
        public static final String DOCUMENT_PATH = "documentPath";
        public static final String TYPE = "type";
        public static final String PARENT_ID = "parentId";
        public static final String CREATED_DATE = "createdDate";
        public static final String LEVEL_ID = "levelId";
        public static final String SIGNER = "signer";
        public static final String CODE = "code";
        public static final String SIGNED_DATE = "signedDate";
        public static final String DESCRIPTION = "description";
        public static final String USER_ID = "userId";
        public static final String FILE_TYPE = "fileType";
        public static final String DOCUMEN_TYPE_ID = "documenTypeId";
        public static final String DOCUMENT_TYPE_NAME = "documentTypeName";

    }

    public interface Constants {

        public static final String TABLE_NAME = "UTIL_ATTACHED_DOCUMENTS";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "UTIL_ATTACHED_DOCUMENTS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "ATTACH_ID")
    private Long attachId;
    @Column(name = "DOCUMENT_NAME")
    private String documentName;
    @Column(name = "DOCUMENT_PATH")
    private String documentPath;
    @Column(name = "TYPE")
    private Long type;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "LEVEL_ID")
    private Long levelId;
    @Column(name = "SIGNER")
    private String signer;
    @Column(name = "CODE")
    private String code;
    @Column(name = "SIGNED_DATE")
    private Date signedDate;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "FILE_TYPE")
    private Long fileType;
    @Column(name = "DOCUMEN_TYPE_ID")
    private Long documenTypeId;
    @Column(name = "DOCUMENT_TYPE_NAME")
    private String documentTypeName;

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
