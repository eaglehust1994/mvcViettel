/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.dto;



import java.io.Serializable;


public class TotalNumDTO implements Serializable{

	private static final long serialVersionUID = -7525957447922763873L;
//tong so ban ghi
	private java.lang.Integer num;
//thu tu ban ghi	
	private java.lang.Integer rownum;
    public TotalNumDTO(Integer num){
    	this.setNum(num);
    }

	/**
	 * @return the num
	 */
	public java.lang.Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(java.lang.Integer num) {
		this.num = num;
	}

	public java.lang.Integer getRownum() {
		return rownum;
	}

	public void setRownum(java.lang.Integer rownum) {
		this.rownum = rownum;
	}


   
}
