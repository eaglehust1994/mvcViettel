package com.viettel.qll.bo;

import com.viettel.qll.dto.TblTimeWorkDTO;
import com.viettel.service.base.model.BaseFWModelImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@SuppressWarnings("serial")
@Entity(name = "com.viettel.qll.bo.TblTimeWorkBO")
@Table(name = "TBL_TIME_WORK")
/**
 *
 * @author: hailh10
 */
public class TblTimeWorkBO extends BaseFWModelImpl {
     
	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "TBL_TIME_WORK_SEQ") })
	@Column(name = "TBL_TIME_WORK_ID", length = 22)
	private java.lang.Long tblTimeWorkId;
	@Column(name = "GEN_TIME", length = 7)
	private java.util.Date genTime;
	@Column(name = "DERPARTMENT", length = 1500)
	private java.lang.String derpartment;
	@Column(name = "NAME", length = 1500)
	private java.lang.String name;

	
	public java.lang.Long getTblTimeWorkId(){
		return tblTimeWorkId;
	}
	
	public void setTblTimeWorkId(java.lang.Long tblTimeWorkId)
	{
		this.tblTimeWorkId = tblTimeWorkId;
	}
	
	public java.util.Date getGenTime(){
		return genTime;
	}
	
	public void setGenTime(java.util.Date genTime)
	{
		this.genTime = genTime;
	}
	
	public java.lang.String getDerpartment(){
		return derpartment;
	}
	
	public void setDerpartment(java.lang.String derpartment)
	{
		this.derpartment = derpartment;
	}
	
	public java.lang.String getName(){
		return name;
	}
	
	public void setName(java.lang.String name)
	{
		this.name = name;
	}
   
    @Override
    public TblTimeWorkDTO toDTO() {
        TblTimeWorkDTO tblTimeWorkDTO = new TblTimeWorkDTO(); 
        tblTimeWorkDTO.setTblTimeWorkId(this.tblTimeWorkId);		
        tblTimeWorkDTO.setGenTime(this.genTime);		
        tblTimeWorkDTO.setDerpartment(this.derpartment);		
        tblTimeWorkDTO.setName(this.name);		
        return tblTimeWorkDTO;
    }
}
