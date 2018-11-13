package com.viettel.qll.dto;

import com.viettel.qll.bo.TblGanNhiemVuBO;
import com.viettel.service.base.dto.BaseFWDTOImpl;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.viettel.erp.utils.CustomJsonDateDeserializer;
import com.viettel.erp.utils.CustomJsonDateSerializer;
import com.google.common.collect.Lists;
import com.viettel.erp.constant.ApplicationConstants;

/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_GAN_NHIEM_VUBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblGanNhiemVuDTO extends QllBaseDTO<TblGanNhiemVuBO> {

	private java.lang.Long ganNvId;
	private java.lang.String idUser;
	private java.lang.String fullname;
	private java.lang.Long idQlCvPtk;
	private java.lang.String tenNhiemVu;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGiaoNv;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGiaoNvFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayGiaoNvTo;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayHoanThanh;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayHoanThanhFrom;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	private java.util.Date ngayHoanThanhTo;
	private java.lang.String trangThai;
	private java.lang.String tenCongViec;

	private long mission1,mission2,mission3,mission4,mission5,missionTotal;
	private long newMission1,newMission2,newMission3,newMission4,newMission5,newMissionTotal;
	private List<Long> listIdQlCvPtk = Lists.newArrayList();
	private List<String> listTenCongViec = Lists.newArrayList();
	
	private String mttMaViTri,soHdCdt;
	public long getMissionTotal() {
		return missionTotal;
	}

	public long getNewMission1() {
		return newMission1;
	}

	public List<Long> getListIdQlCvPtk() {
		return listIdQlCvPtk;
	}

	public void setListIdQlCvPtk(List<Long> listIdQlCvPtk) {
		this.listIdQlCvPtk = listIdQlCvPtk;
	}

	public List<String> getListTenCongViec() {
		return listTenCongViec;
	}

	public void setListTenCongViec(List<String> listTenCongViec) {
		this.listTenCongViec = listTenCongViec;
	}

	public void setNewMission1(long newMission1) {
		this.newMission1 = newMission1;
	}

	public long getNewMission2() {
		return newMission2;
	}

	public void setNewMission2(long newMission2) {
		this.newMission2 = newMission2;
	}

	public long getNewMission3() {
		return newMission3;
	}

	public void setNewMission3(long newMission3) {
		this.newMission3 = newMission3;
	}

	public long getNewMission4() {
		return newMission4;
	}

	public void setNewMission4(long newMission4) {
		this.newMission4 = newMission4;
	}

	public long getNewMission5() {
		return newMission5;
	}

	public void setNewMission5(long newMission5) {
		this.newMission5 = newMission5;
	}

	public long getNewMissionTotal() {
		return newMissionTotal;
	}

	public void setNewMissionTotal(long newMissionTotal) {
		this.newMissionTotal = newMissionTotal;
	}

	public void setMissionTotal(long missionTotal) {
		this.missionTotal = missionTotal;
	}

	public long getMission1() {
		return mission1;
	}

	public void setMission1(long mission1) {
		this.mission1 = mission1;
	}

	public long getMission2() {
		return mission2;
	}

	public void setMission2(long mission2) {
		this.mission2 = mission2;
	}

	public long getMission3() {
		return mission3;
	}

	public void setMission3(long mission3) {
		this.mission3 = mission3;
	}

	public long getMission4() {
		return mission4;
	}

	public void setMission4(long mission4) {
		this.mission4 = mission4;
	}

	public long getMission5() {
		return mission5;
	}

	public void setMission5(long mission5) {
		this.mission5 = mission5;
	}
	
	
	public String getMttMaViTri() {
		return mttMaViTri;
	}

	public void setMttMaViTri(String mttMaViTri) {
		this.mttMaViTri = mttMaViTri;
	}

	public String getSoHdCdt() {
		return soHdCdt;
	}

	public void setSoHdCdt(String soHdCdt) {
		this.soHdCdt = soHdCdt;
	}


	private int start;
	private int maxResult;

    @Override
    public TblGanNhiemVuBO toModel() {
        TblGanNhiemVuBO tblGanNhiemVuBO = new TblGanNhiemVuBO();
        tblGanNhiemVuBO.setGanNvId(this.ganNvId);
        tblGanNhiemVuBO.setIdUser(this.idUser);
        tblGanNhiemVuBO.setFullname(this.fullname);
        tblGanNhiemVuBO.setIdQlCvPtk(this.idQlCvPtk);
        tblGanNhiemVuBO.setTenNhiemVu(this.tenNhiemVu);
        tblGanNhiemVuBO.setNgayGiaoNv(this.ngayGiaoNv);
        tblGanNhiemVuBO.setNgayHoanThanh(this.ngayHoanThanh);
        tblGanNhiemVuBO.setTrangThai(this.trangThai);
        tblGanNhiemVuBO.setTenCongViec(this.tenCongViec);
        return tblGanNhiemVuBO;
    }

    public java.lang.String getTenCongViec() {
		return tenCongViec;
	}

	public void setTenCongViec(java.lang.String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}

	@Override
     public Long getFWModelId() {
        return ganNvId;
    }
   
    @Override
    public String catchName() {
        return getGanNvId().toString();
    }
	
	@JsonProperty("ganNvId")
    public java.lang.Long getGanNvId(){
		return ganNvId;
    }
	
    public void setGanNvId(java.lang.Long ganNvId){
		this.ganNvId = ganNvId;
    }	
	
	@JsonProperty("idUser")
    public java.lang.String getIdUser(){
		return idUser;
    }
	
    public void setIdUser(java.lang.String idUser){
		this.idUser = idUser;
    }	
	
	@JsonProperty("fullname")
    public java.lang.String getFullname(){
		return fullname;
    }
	
    public void setFullname(java.lang.String fullname){
		this.fullname = fullname;
    }	
	
	@JsonProperty("idQlCvPtk")
    public java.lang.Long getIdQlCvPtk(){
		return idQlCvPtk;
    }
	
    public void setIdQlCvPtk(java.lang.Long idQlCvPtk){
		this.idQlCvPtk = idQlCvPtk;
    }	
	
	@JsonProperty("tenNhiemVu")
    public java.lang.String getTenNhiemVu(){
		return tenNhiemVu;
    }
	
    public void setTenNhiemVu(java.lang.String tenNhiemVu){
		this.tenNhiemVu = tenNhiemVu;
    }	
	
	@JsonProperty("ngayGiaoNv")
    public java.util.Date getNgayGiaoNv(){
		return ngayGiaoNv;
    }
	
    public void setNgayGiaoNv(java.util.Date ngayGiaoNv){
		this.ngayGiaoNv = ngayGiaoNv;
    }	
	
	public java.util.Date getNgayGiaoNvFrom() {
    	return ngayGiaoNvFrom;
    }
	
    public void setNgayGiaoNvFrom(java.util.Date ngayGiaoNvFrom) {
    	this.ngayGiaoNvFrom = ngayGiaoNvFrom;
    }
	
	public java.util.Date getNgayGiaoNvTo() {
    	return ngayGiaoNvTo;
    }
	
    public void setNgayGiaoNvTo(java.util.Date ngayGiaoNvTo) {
    	this.ngayGiaoNvTo = ngayGiaoNvTo;
    }
	
	@JsonProperty("ngayHoanThanh")
    public java.util.Date getNgayHoanThanh(){
		return ngayHoanThanh;
    }
	
    public void setNgayHoanThanh(java.util.Date ngayHoanThanh){
		this.ngayHoanThanh = ngayHoanThanh;
    }	
	
	public java.util.Date getNgayHoanThanhFrom() {
    	return ngayHoanThanhFrom;
    }
	
    public void setNgayHoanThanhFrom(java.util.Date ngayHoanThanhFrom) {
    	this.ngayHoanThanhFrom = ngayHoanThanhFrom;
    }
	
	public java.util.Date getNgayHoanThanhTo() {
    	return ngayHoanThanhTo;
    }
	
    public void setNgayHoanThanhTo(java.util.Date ngayHoanThanhTo) {
    	this.ngayHoanThanhTo = ngayHoanThanhTo;
    }
	
	@JsonProperty("trangThai")
    public java.lang.String getTrangThai(){
		return trangThai;
    }
	
    public void setTrangThai(java.lang.String trangThai){
		this.trangThai = trangThai;
    }	
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((ganNvId == null) ? 0 : ganNvId.hashCode());
		result = prime * result + ((idQlCvPtk == null) ? 0 : idQlCvPtk.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((tenNhiemVu == null) ? 0 : tenNhiemVu.hashCode());
		result = prime * result + ((trangThai == null) ? 0 : trangThai.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TblGanNhiemVuDTO other = (TblGanNhiemVuDTO) obj;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (ganNvId == null) {
			if (other.ganNvId != null)
				return false;
		} else if (!ganNvId.equals(other.ganNvId))
			return false;
		if (idQlCvPtk == null) {
			if (other.idQlCvPtk != null)
				return false;
		} else if (!idQlCvPtk.equals(other.idQlCvPtk))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (tenNhiemVu == null) {
			if (other.tenNhiemVu != null)
				return false;
		} else if (!tenNhiemVu.equals(other.tenNhiemVu))
			return false;
		if (trangThai == null) {
			if (other.trangThai != null)
				return false;
		} else if (!trangThai.equals(other.trangThai))
			return false;
		return true;
	}
	
//	@JsonProperty("text")
//	public String getText() {
//		if (this.value != null && this.name != null) {
//			if (!this.value.contains(ApplicationConstants.SEARCH_MORE) && !this.name.contains(ApplicationConstants.SEARCH_MORE)) {
//				return this.value != null ? this.value + " - " + this.name : this.name;
//			} else {
//				return ApplicationConstants.SEARCH_MORE;
//			}
//		} else {
//			return this.name;
//		}
//	}
//	
//	public void setText(String text) {
//		this.text = text;
//	}
}
