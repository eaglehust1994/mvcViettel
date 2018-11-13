package com.viettel.qll.dto;

import com.google.common.collect.Lists;
import com.viettel.qll.bo.TblKhQlvcBO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 *
 * @author hailh10
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "TBL_KH_QLVCBO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TblKhQlvcDTO extends QllBaseDTO<TblKhQlvcBO> {

	private java.lang.Long khQlvcId;
	private java.lang.String thang;
	private java.lang.String nam;
	private java.lang.String maDonVi;
	private java.lang.String tenDonVi;
	private java.lang.String maNvGiaoViec;
	private java.lang.String tenNvGiaoViec;
	private java.lang.Long tongNv;
	private java.lang.Long tongCham;
	private String name;
	private String department;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	private java.lang.String employeeCode;
	private java.lang.String employeeRole;
	
	public java.lang.String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(java.lang.String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public java.lang.String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(java.lang.String employeeRole) {
		this.employeeRole = employeeRole;
	}
	

	private List<Long> listTongNvHoanThanh = Lists.newArrayList();
	private List<Long> listTongNv = Lists.newArrayList();
	private List<Long> listTongCham = Lists.newArrayList();
	private List<Float> listTile = Lists.newArrayList();
	public List<Long> getListTongNvHoanThanh() {
		return listTongNvHoanThanh;
	}

	public void setListTongNvHoanThanh(List<Long> listTongNvHoanThanh) {
		this.listTongNvHoanThanh = listTongNvHoanThanh;
	}
	public List<Float> getListTile() {
		return listTile;
	}

	public void setListTile(List<Float> listTile) {
		this.listTile = listTile;
	}

	private List<String> listThang = Lists.newArrayList();
	private List<String> listNam = Lists.newArrayList();
	private List<String> listMaDonVi = Lists.newArrayList();


	private int start;
	private int maxResult;

    @Override
    public TblKhQlvcBO toModel() {
        TblKhQlvcBO tblKhQlvcBO = new TblKhQlvcBO();
        tblKhQlvcBO.setKhQlvcId(this.khQlvcId);
        tblKhQlvcBO.setThang(this.thang);
        tblKhQlvcBO.setNam(this.nam);
        tblKhQlvcBO.setMaDonVi(this.maDonVi);
        tblKhQlvcBO.setTenDonVi(this.tenDonVi);
        tblKhQlvcBO.setMaNvGiaoViec(this.maNvGiaoViec);
        tblKhQlvcBO.setTenNvGiaoViec(this.tenNvGiaoViec);
        tblKhQlvcBO.setTongNv(this.tongNv);
        tblKhQlvcBO.setTongCham(this.tongCham);
        return tblKhQlvcBO;
    }

    @Override
     public Long getFWModelId() {
        return khQlvcId;
    }
   
    @Override
    public String catchName() {
        return getKhQlvcId().toString();
    }
	
	@JsonProperty("khQlvcId")
    public java.lang.Long getKhQlvcId(){
		return khQlvcId;
    }
	
    public void setKhQlvcId(java.lang.Long khQlvcId){
		this.khQlvcId = khQlvcId;
    }	
	
	@JsonProperty("thang")
    public java.lang.String getThang(){
		return thang;
    }
	
    public void setThang(java.lang.String thang){
		this.thang = thang;
    }	
	
	@JsonProperty("nam")
    public java.lang.String getNam(){
		return nam;
    }
	
    public void setNam(java.lang.String nam){
		this.nam = nam;
    }	
	
	@JsonProperty("maDonVi")
    public java.lang.String getMaDonVi(){
		return maDonVi;
    }
	
    public void setMaDonVi(java.lang.String maDonVi){
		this.maDonVi = maDonVi;
    }	
	
	@JsonProperty("tenDonVi")
    public java.lang.String getTenDonVi(){
		return tenDonVi;
    }
	
    public void setTenDonVi(java.lang.String tenDonVi){
		this.tenDonVi = tenDonVi;
    }	
	
	@JsonProperty("maNvGiaoViec")
    public java.lang.String getMaNvGiaoViec(){
		return maNvGiaoViec;
    }
	
    public void setMaNvGiaoViec(java.lang.String maNvGiaoViec){
		this.maNvGiaoViec = maNvGiaoViec;
    }	
	
	@JsonProperty("tenNvGiaoViec")
    public java.lang.String getTenNvGiaoViec(){
		return tenNvGiaoViec;
    }
	
    public void setTenNvGiaoViec(java.lang.String tenNvGiaoViec){
		this.tenNvGiaoViec = tenNvGiaoViec;
    }	
	
	@JsonProperty("tongNv")
    public java.lang.Long getTongNv(){
		return tongNv;
    }
	
    public void setTongNv(java.lang.Long tongNv){
		this.tongNv = tongNv;
    }	
	
	@JsonProperty("tongCham")
    public java.lang.Long getTongCham(){
		return tongCham;
    }
	
    public void setTongCham(java.lang.Long tongCham){
		this.tongCham = tongCham;
    }
    
    
	
	public List<Long> getListTongNv() {
		return listTongNv;
	}

	public void setListTongNv(List<Long> listTongNv) {
		this.listTongNv = listTongNv;
	}

	public List<Long> getListTongCham() {
		return listTongCham;
	}

	public void setListTongCham(List<Long> listTongCham) {
		this.listTongCham = listTongCham;
	}

	public List<String> getListThang() {
		return listThang;
	}

	public void setListThang(List<String> listThang) {
		this.listThang = listThang;
	}

	public List<String> getListNam() {
		return listNam;
	}

	public void setListNam(List<String> listNam) {
		this.listNam = listNam;
	}
	
	public List<String> getListMaDonVi() {
		return listMaDonVi;
	}

	public void setListMaDonVi(List<String> listMaDonVi) {
		this.listMaDonVi = listMaDonVi;
	}
	@JsonProperty("start")
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((employeeCode == null) ? 0 : employeeCode.hashCode());
		result = prime * result + ((employeeRole == null) ? 0 : employeeRole.hashCode());
	
		result = prime * result + ((listMaDonVi == null) ? 0 : listMaDonVi.hashCode());
		result = prime * result + ((listNam == null) ? 0 : listNam.hashCode());
		result = prime * result + ((listThang == null) ? 0 : listThang.hashCode());
		result = prime * result + ((listTile == null) ? 0 : listTile.hashCode());
		result = prime * result + ((listTongCham == null) ? 0 : listTongCham.hashCode());
		result = prime * result + ((listTongNv == null) ? 0 : listTongNv.hashCode());
		result = prime * result + ((listTongNvHoanThanh == null) ? 0 : listTongNvHoanThanh.hashCode());
		result = prime * result + ((maDonVi == null) ? 0 : maDonVi.hashCode());
		result = prime * result + ((maNvGiaoViec == null) ? 0 : maNvGiaoViec.hashCode());
		result = prime * result + maxResult;
		result = prime * result + ((nam == null) ? 0 : nam.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + start;
		result = prime * result + ((tenDonVi == null) ? 0 : tenDonVi.hashCode());
		result = prime * result + ((tenNvGiaoViec == null) ? 0 : tenNvGiaoViec.hashCode());
		result = prime * result + ((thang == null) ? 0 : thang.hashCode());
		result = prime * result + ((tongCham == null) ? 0 : tongCham.hashCode());
		result = prime * result + ((tongNv == null) ? 0 : tongNv.hashCode());
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
		TblKhQlvcDTO other = (TblKhQlvcDTO) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (employeeCode == null) {
			if (other.employeeCode != null)
				return false;
		} else if (!employeeCode.equals(other.employeeCode))
			return false;
		if (employeeRole == null) {
			if (other.employeeRole != null)
				return false;
		} else if (!employeeRole.equals(other.employeeRole))
			return false;
		if (listMaDonVi == null) {
			if (other.listMaDonVi != null)
				return false;
		} else if (!listMaDonVi.equals(other.listMaDonVi))
			return false;
		if (listNam == null) {
			if (other.listNam != null)
				return false;
		} else if (!listNam.equals(other.listNam))
			return false;
		if (listThang == null) {
			if (other.listThang != null)
				return false;
		} else if (!listThang.equals(other.listThang))
			return false;
		if (listTile == null) {
			if (other.listTile != null)
				return false;
		} else if (!listTile.equals(other.listTile))
			return false;
		if (listTongCham == null) {
			if (other.listTongCham != null)
				return false;
		} else if (!listTongCham.equals(other.listTongCham))
			return false;
		if (listTongNv == null) {
			if (other.listTongNv != null)
				return false;
		} else if (!listTongNv.equals(other.listTongNv))
			return false;
		if (listTongNvHoanThanh == null) {
			if (other.listTongNvHoanThanh != null)
				return false;
		} else if (!listTongNvHoanThanh.equals(other.listTongNvHoanThanh))
			return false;
		if (maDonVi == null) {
			if (other.maDonVi != null)
				return false;
		} else if (!maDonVi.equals(other.maDonVi))
			return false;
		if (maNvGiaoViec == null) {
			if (other.maNvGiaoViec != null)
				return false;
		} else if (!maNvGiaoViec.equals(other.maNvGiaoViec))
			return false;
		if (maxResult != other.maxResult)
			return false;
		if (nam == null) {
			if (other.nam != null)
				return false;
		} else if (!nam.equals(other.nam))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (start != other.start)
			return false;
		if (tenDonVi == null) {
			if (other.tenDonVi != null)
				return false;
		} else if (!tenDonVi.equals(other.tenDonVi))
			return false;
		if (tenNvGiaoViec == null) {
			if (other.tenNvGiaoViec != null)
				return false;
		} else if (!tenNvGiaoViec.equals(other.tenNvGiaoViec))
			return false;
		if (thang == null) {
			if (other.thang != null)
				return false;
		} else if (!thang.equals(other.thang))
			return false;
		if (tongCham == null) {
			if (other.tongCham != null)
				return false;
		} else if (!tongCham.equals(other.tongCham))
			return false;
		if (tongNv == null) {
			if (other.tongNv != null)
				return false;
		} else if (!tongNv.equals(other.tongNv))
			return false;
		return true;
	}

	@JsonProperty("maxResult")
	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	

	
}
