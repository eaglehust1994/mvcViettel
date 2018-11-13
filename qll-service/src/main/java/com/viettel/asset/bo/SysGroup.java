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
@Table(name = "SYS_GROUP")
public class SysGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String GROUP_ID = "groupId";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String PARENT_ID = "parentId";
        public static final String PATH = "path";
        public static final String STATUS = "status";
        public static final String GROUP_CODE = "groupCode";
        public static final String FULL_NAME = "fullName";
        public static final String PERMISSION_ALL = "permissionAll";
        public static final String TITLE_NAME = "titleName";
        public static final String ADDRESS = "address";
        public static final String MA_TINH = "maTinh";
        public static final String THE_LEVEL = "theLevel";
        public static final String THE_ORDER = "theOrder";
        public static final String PROVINCE_ID = "provinceId";
        public static final String CAT_EMPLOYEE_ID = "catEmployeeId";
        public static final String CAT_EMPLOYEE_NET_W_ID = "catEmployeeNetWId";
        public static final String IS_OFF = "isOff";
        public static final String ORDER_EXP_TREE = "orderExpTree";
        public static final String LOCK_STATUS = "lockStatus";
        public static final String CHANGE_DATE = "changeDate";
        public static final String ORGANIZATION_ID = "organizationId";

    }

    public interface Constants {

        public static final String TABLE_NAME = "SYS_GROUP";
        public static final Long ACTIVE = 1l;
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SYS_GROUP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "PATH")
    private String path;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "GROUP_CODE")
    private String groupCode;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "PERMISSION_ALL")
    private Long permissionAll;
    @Column(name = "TITLE_NAME")
    private String titleName;
    @Column(name = "ADDRESS")
    private String address;
//    @Column(name = "MA_TINH")
//    private String maTinh;
    @Column(name = "THE_LEVEL")
    private Long theLevel;
    @Column(name = "THE_ORDER")
    private Long theOrder;
    @Column(name = "PROVINCE_ID")
    private Long provinceId;
    @Column(name = "CAT_EMPLOYEE_ID")
    private Long catEmployeeId;
    @Column(name = "CAT_EMPLOYEE_NET_W_ID")
    private Long catEmployeeNetWId;
    @Column(name = "IS_OFF")
    private Long isOff;
    @Column(name = "ORDER_EXP_TREE")
    private Long orderExpTree;
    @Column(name = "LOCK_STATUS")
    private Long lockStatus;
    @Column(name = "CHANGE_DATE")
    private Date changeDate;
    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getPermissionAll() {
        return permissionAll;
    }

    public void setPermissionAll(Long permissionAll) {
        this.permissionAll = permissionAll;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public String getMaTinh() {
//        return maTinh;
//    }
//
//    public void setMaTinh(String maTinh) {
//        this.maTinh = maTinh;
//    }

    public Long getTheLevel() {
        return theLevel;
    }

    public void setTheLevel(Long theLevel) {
        this.theLevel = theLevel;
    }

    public Long getTheOrder() {
        return theOrder;
    }

    public void setTheOrder(Long theOrder) {
        this.theOrder = theOrder;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCatEmployeeId() {
        return catEmployeeId;
    }

    public void setCatEmployeeId(Long catEmployeeId) {
        this.catEmployeeId = catEmployeeId;
    }

    public Long getCatEmployeeNetWId() {
        return catEmployeeNetWId;
    }

    public void setCatEmployeeNetWId(Long catEmployeeNetWId) {
        this.catEmployeeNetWId = catEmployeeNetWId;
    }

    public Long getIsOff() {
        return isOff;
    }

    public void setIsOff(Long isOff) {
        this.isOff = isOff;
    }

    public Long getOrderExpTree() {
        return orderExpTree;
    }

    public void setOrderExpTree(Long orderExpTree) {
        this.orderExpTree = orderExpTree;
    }

    public Long getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Long lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

}
