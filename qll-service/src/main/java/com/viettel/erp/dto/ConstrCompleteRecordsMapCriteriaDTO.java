/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.dto;

public class ConstrCompleteRecordsMapCriteriaDTO {

    private Long status;
    private Long constructionId;
    private Long signEmployee;
    private String createDateFrom;
    private String createDateTo;
    private Long typeConstruction;
    private Long catFileInvoiceId;

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getSignEmployee() {
        return signEmployee;
    }

    public void setSignEmployee(Long signEmployee) {
        this.signEmployee = signEmployee;
    }

    public String getCreateDateFrom() {
        return createDateFrom;
    }

    public void setCreateDateFrom(String createDateFrom) {
        this.createDateFrom = createDateFrom;
    }

    public String getCreateDateTo() {
        return createDateTo;
    }

    public void setCreateDateTo(String createDateTo) {
        this.createDateTo = createDateTo;
    }

    public Long getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Long constructionId) {
        this.constructionId = constructionId;
    }

	public Long getTypeConstruction() {
		return typeConstruction;
	}

	public void setTypeConstruction(Long typeConstruction) {
		this.typeConstruction = typeConstruction;
	}

	public Long getCatFileInvoiceId() {
		return catFileInvoiceId;
	}

	public void setCatFileInvoiceId(Long catFileInvoiceId) {
		this.catFileInvoiceId = catFileInvoiceId;
	}
	
    
}
