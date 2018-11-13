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
@Table(name = "BUSINESS_LOGS")
public class BusinessLog implements Serializable{
    
    
    
    private static final long serialVersionUID = 1L;

    public  interface Constants {

        public static final String DB_TABLE_SEPARATOR = ";";

        public  interface BulAction {

            public static final String INSERT = "INSERT";
            public static final String UPDATE = "UPDATE";
            public static final String ACTIVE = "ACTIVE";
            public static final String INACTIVE = "INACTIVE";
            public static final String DELETE = "DELETE";
        }
    }

    public interface Columns {

        public static final String BUSINESS_LOG_ID = "businessLogId";
        public static final String USER_ID = "userId";
        public static final String CREATED_DATE = "createdDate";
        public static final String DB_TABLE = "dbTable";
        public static final String BUL_ACTION = "bulAction";
        public static final String OLD_VALUE = "oldValue";
        public static final String NEW_VALUE = "newValue";
        public static final String DESCRIPTION = "description";
        public static final String MAIN_ID = "mainId";

    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "BUSINESS_LOG_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "BUSINESS_LOG_ID")
    private Long businessLogId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "DB_TABLE")
    private String dbTable;
    @Column(name = "BUL_ACTION")
    private String bulAction;
    @Column(name = "OLD_VALUE")
    private String oldValue;
    @Column(name = "NEW_VALUE")
    private String newValue;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "MAIN_ID")
    private Long mainId;

    public Long getBusinessLogId() {
        return businessLogId;
    }

    public void setBusinessLogId(Long businessLogId) {
        this.businessLogId = businessLogId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDbTable() {
        return dbTable;
    }

    public void setDbTable(String dbTable) {
        this.dbTable = dbTable;
    }

    public String getBulAction() {
        return bulAction;
    }

    public void setBulAction(String bulAction) {
        this.bulAction = bulAction;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

}
