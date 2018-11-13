/**
 * 
 */
package com.viettel.erp.dto;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

/**
 * @author thanghv
 *
 */
public class DemoDTO {
	private String code;
	private long statusCa;
	private String constractName;
	private String constructCode;
	private String constractCode;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the constructCode
	 */
	public String getConstructCode() {
		return constructCode;
	}
	/**
	 * @param constructCode the constructCode to set
	 */
	public void setConstructCode(String constructCode) {
		this.constructCode = constructCode;
	}
	/**
	 * @return the constractCode
	 */
	public String getConstractCode() {
		return constractCode;
	}
	/**
	 * @param constractCode the constractCode to set
	 */
	public void setConstractCode(String constractCode) {
		this.constractCode = constractCode;
	}
	
	/**
	 * @return the statusCa
	 */
	public long getStatusCa() {
		return statusCa;
	}
	/**
	 * @param statusCa the statusCa to set
	 */
	public void setStatusCa(long statusCa) {
		this.statusCa = statusCa;
	}
	

	/**
	 * @return the constractName
	 */
	public String getConstractName() {
		return constractName;
	}
	/**
	 * @param constractName the constractName to set
	 */
	public void setConstractName(String constractName) {
		this.constractName = constractName;
	}
}
