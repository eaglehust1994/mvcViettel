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
@Table(name = "CAT_STATION")
public class CatStation implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Columns {

        public static final String ID = "id";
        public static final String STATION_CODE = "stationCode";
        public static final String ADDRESS = "address";
        public static final String DESCRIPTION = "description";
        public static final String IS_ACTIVE = "isActive";
        public static final String PROVINCE_ID = "provinceId";
        public static final String START_POINT_ID = "startPointId";
        public static final String END_POINT_ID = "endPointId";
        public static final String LINE_TYPE_ID = "lineTypeId";
        public static final String LINE_LENGTH = "lineLength";
        public static final String EMISSION_DATE = "emissionDate";
        public static final String SCOPE = "scope";
        public static final String END_POINT_TYPE = "endPointType";
        public static final String IS_INVENTORY = "isInventory";
        public static final String PARENT_ID = "parentId";
        public static final String PRE_PARENT_ID = "preParentId";
        public static final String CAT_STATION_TYPE_ID = "catStationTypeId";
        public static final String IMPORT_FOR_YEAR = "importForYear";
        public static final String IS_ATTACH = "isAttach";
        public static final String ATTACH_ID = "attachId";
        public static final String STATUS_TKTU = "statusTktu";
        public static final String PROCESS_STATUS = "processStatus";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String DISTANCE_ODD = "distanceOdd";
        public static final String USED_DATE = "usedDate";
        public static final String IS_DELETE = "isDelete";
        public static final String DESCRIPT_DEL = "descriptDel";
        public static final String CREATOR_ID = "creatorId";
        public static final String GROUP_ID = "groupId";
        public static final String ADD_TIME = "addTime";
        public static final String UPDATE_DATE = "updateDate";
        public static final String IS_BASE = "isBase";
        public static final String AREA_LOCATION = "areaLocation";
        public static final String START_POINT_TYPE = "startPointType";
        public static final String IS_EVN = "isEvn";
        public static final String CREATE_GROUP_ID = "createGroupId";
        public static final String STATION_BTS_ID = "stationBtsId";
        public static final String OLD_STATION_CODE = "oldStationCode";
        public static final String IS_OFF = "isOff";
        public static final String TYPE = "type";
        public static final String IS_LOCK = "isLock";
        public static final String CAT_STATION_HOUSE_ID = "catStationHouseId";
        public static final String IS_SFONE = "isSfone";
        public static final String UPDATOR_ID = "updatorId";
        public static final String ON_DATE = "onDate";
        public static final String DATE_ON_STATION = "dateOnStation";
        public static final String USER_ON_STATION = "userOnStation";
        public static final String PLAN_ID = "planId";
        public static final String STATUS_CODE = "statusCode";
        public static final String CR_NUMBER = "crNumber";
        public static final String SYNC_TIME = "syncTime";

    }

    public interface  Constants {

        public static final String TABLE_NAME = "CAT_STATION";
    }

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "CAT_STATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "ID")
    private Long id;
    @Column(name = "STATION_CODE")
    private String stationCode;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "PROVINCE_ID")
    private Long provinceId;
    @Column(name = "START_POINT_ID")
    private Long startPointId;
    @Column(name = "END_POINT_ID")
    private Long endPointId;
    @Column(name = "LINE_TYPE_ID")
    private Long lineTypeId;
    @Column(name = "LINE_LENGTH")
    private Long lineLength;
    @Column(name = "EMISSION_DATE")
    private Date emissionDate;
    @Column(name = "SCOPE")
    private Long scope;
    @Column(name = "END_POINT_TYPE")
    private Long endPointType;
    @Column(name = "IS_INVENTORY")
    private Long isInventory;
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "PRE_PARENT_ID")
    private Long preParentId;
    @Column(name = "CAT_STATION_TYPE_ID")
    private Long catStationTypeId;
    @Column(name = "IMPORT_FOR_YEAR")
    private Long importForYear;
    @Column(name = "IS_ATTACH")
    private Long isAttach;
    @Column(name = "ATTACH_ID")
    private Long attachId;
    @Column(name = "STATUS_TKTU")
    private Long statusTktu;
    @Column(name = "PROCESS_STATUS")
    private String processStatus;
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "DISTANCE_ODD")
    private Double distanceOdd;
    @Column(name = "USED_DATE")
    private Date usedDate;
    @Column(name = "IS_DELETE")
    private Long isDelete;
    @Column(name = "DESCRIPT_DEL")
    private String descriptDel;
    @Column(name = "CREATOR_ID")
    private Long creatorId;
    @Column(name = "GROUP_ID")
    private Long groupId;
    @Column(name = "ADD_TIME")
    private Date addTime;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    @Column(name = "IS_BASE")
    private Long isBase;
    @Column(name = "AREA_LOCATION")
    private String areaLocation;
    @Column(name = "START_POINT_TYPE")
    private Long startPointType;
    @Column(name = "IS_EVN")
    private Long isEvn;
    @Column(name = "CREATE_GROUP_ID")
    private Long createGroupId;
    @Column(name = "STATION_BTS_ID")
    private Long stationBtsId;
    @Column(name = "OLD_STATION_CODE")
    private String oldStationCode;
    @Column(name = "IS_OFF")
    private Long isOff;
    /*@Column(name = "TYPE")
    private Long type;*/
    @Column(name = "IS_LOCK")
    private Long isLock;
    @Column(name = "CAT_STATION_HOUSE_ID")
    private Long catStationHouseId;
    @Column(name = "IS_SFONE")
    private Long isSfone;
    @Column(name = "UPDATOR_ID")
    private Long updatorId;
    @Column(name = "ON_DATE")
    private Date onDate;
    @Column(name = "DATE_ON_STATION")
    private Date dateOnStation;
    @Column(name = "USER_ON_STATION")
    private Long userOnStation;
    @Column(name = "PLAN_ID")
    private Long planId;
    @Column(name = "STATUS_CODE")
    private String statusCode;
    @Column(name = "CR_NUMBER")
    private String crNumber;
    @Column(name = "SYNC_TIME")
    private Date syncTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getStartPointId() {
        return startPointId;
    }

    public void setStartPointId(Long startPointId) {
        this.startPointId = startPointId;
    }

    public Long getEndPointId() {
        return endPointId;
    }

    public void setEndPointId(Long endPointId) {
        this.endPointId = endPointId;
    }

    public Long getLineTypeId() {
        return lineTypeId;
    }

    public void setLineTypeId(Long lineTypeId) {
        this.lineTypeId = lineTypeId;
    }

    public Long getLineLength() {
        return lineLength;
    }

    public void setLineLength(Long lineLength) {
        this.lineLength = lineLength;
    }

    public Date getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(Date emissionDate) {
        this.emissionDate = emissionDate;
    }

    public Long getScope() {
        return scope;
    }

    public void setScope(Long scope) {
        this.scope = scope;
    }

    public Long getEndPointType() {
        return endPointType;
    }

    public void setEndPointType(Long endPointType) {
        this.endPointType = endPointType;
    }

    public Long getIsInventory() {
        return isInventory;
    }

    public void setIsInventory(Long isInventory) {
        this.isInventory = isInventory;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getPreParentId() {
        return preParentId;
    }

    public void setPreParentId(Long preParentId) {
        this.preParentId = preParentId;
    }

    public Long getCatStationTypeId() {
        return catStationTypeId;
    }

    public void setCatStationTypeId(Long catStationTypeId) {
        this.catStationTypeId = catStationTypeId;
    }

    public Long getImportForYear() {
        return importForYear;
    }

    public void setImportForYear(Long importForYear) {
        this.importForYear = importForYear;
    }

    public Long getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(Long isAttach) {
        this.isAttach = isAttach;
    }

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public Long getStatusTktu() {
        return statusTktu;
    }

    public void setStatusTktu(Long statusTktu) {
        this.statusTktu = statusTktu;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDistanceOdd() {
        return distanceOdd;
    }

    public void setDistanceOdd(Double distanceOdd) {
        this.distanceOdd = distanceOdd;
    }

    public Date getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(Date usedDate) {
        this.usedDate = usedDate;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public String getDescriptDel() {
        return descriptDel;
    }

    public void setDescriptDel(String descriptDel) {
        this.descriptDel = descriptDel;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getIsBase() {
        return isBase;
    }

    public void setIsBase(Long isBase) {
        this.isBase = isBase;
    }

    public String getAreaLocation() {
        return areaLocation;
    }

    public void setAreaLocation(String areaLocation) {
        this.areaLocation = areaLocation;
    }

    public Long getStartPointType() {
        return startPointType;
    }

    public void setStartPointType(Long startPointType) {
        this.startPointType = startPointType;
    }

    public Long getIsEvn() {
        return isEvn;
    }

    public void setIsEvn(Long isEvn) {
        this.isEvn = isEvn;
    }

    public Long getCreateGroupId() {
        return createGroupId;
    }

    public void setCreateGroupId(Long createGroupId) {
        this.createGroupId = createGroupId;
    }

    public Long getStationBtsId() {
        return stationBtsId;
    }

    public void setStationBtsId(Long stationBtsId) {
        this.stationBtsId = stationBtsId;
    }

    public String getOldStationCode() {
        return oldStationCode;
    }

    public void setOldStationCode(String oldStationCode) {
        this.oldStationCode = oldStationCode;
    }

    public Long getIsOff() {
        return isOff;
    }

    public void setIsOff(Long isOff) {
        this.isOff = isOff;
    }

   /* public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }*/

    public Long getIsLock() {
        return isLock;
    }

    public void setIsLock(Long isLock) {
        this.isLock = isLock;
    }

    public Long getCatStationHouseId() {
        return catStationHouseId;
    }

    public void setCatStationHouseId(Long catStationHouseId) {
        this.catStationHouseId = catStationHouseId;
    }

    public Long getIsSfone() {
        return isSfone;
    }

    public void setIsSfone(Long isSfone) {
        this.isSfone = isSfone;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    public Date getDateOnStation() {
        return dateOnStation;
    }

    public void setDateOnStation(Date dateOnStation) {
        this.dateOnStation = dateOnStation;
    }

    public Long getUserOnStation() {
        return userOnStation;
    }

    public void setUserOnStation(Long userOnStation) {
        this.userOnStation = userOnStation;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCrNumber() {
        return crNumber;
    }

    public void setCrNumber(String crNumber) {
        this.crNumber = crNumber;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

}
