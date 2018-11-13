/* 
* Copyright 2011 Viettel Telecom. All rights reserved. 
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.viettel.erp.bo;

import com.viettel.erp.dto.TitAziConstrAcceptListDTO;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name = "titaziconstracceptlist")
@Table(name = "TIT_AZI_CONSTR_ACCEPT_LIST")
@DynamicInsert
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "erp-cache")
/**
 *
 * @author: ThuanNHT
 * @version: 1.0
 * @since: 1.0
 */
public class TitAziConstrAcceptListBO extends BaseFWModelImpl {

	private java.lang.Long titAziConAcceptListId;
	private java.lang.String content;
	private java.lang.String unit;
	private java.lang.String titleCornerBAdjust;
	private java.lang.String azimuthDirectBAdjust;
	private java.lang.String titleCornerAAdjust;
	private java.lang.String azimuthDirectAAdjust;
	private java.lang.String note;

	private TitAziConstrAcceptBO titAziConstrAccept;

	public TitAziConstrAcceptListBO() {
		setColId("titAziConAcceptListId");
		setColName("titAziConAcceptListId");
		setUniqueColumn(new String[] { "titAziConAcceptListId" });
	}

	@Id
	@GeneratedValue(generator = "sequence")
	@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
			@Parameter(name = "sequence", value = "TIT_AZI_CONSTR_ACCEPT_LIST_SEQ") })
	@Column(name = "TIT_AZI_CON_ACCEPT_LIST_ID", length = 22)
	public java.lang.Long getTitAziConAcceptListId() {
		return titAziConAcceptListId;
	}

	public void setTitAziConAcceptListId(java.lang.Long titAziConAcceptListId) {
		this.titAziConAcceptListId = titAziConAcceptListId;
	}

	@Column(name = "CONTENT", length = 400)
	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	@Column(name = "UNIT", length = 400)
	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	@Column(name = "TITLE_CORNER_B_ADJUST", length = 400)
	public java.lang.String getTitleCornerBAdjust() {
		return titleCornerBAdjust;
	}

	public void setTitleCornerBAdjust(java.lang.String titleCornerBAdjust) {
		this.titleCornerBAdjust = titleCornerBAdjust;
	}

	@Column(name = "AZIMUTH_DIRECT_B_ADJUST", length = 400)
	public java.lang.String getAzimuthDirectBAdjust() {
		return azimuthDirectBAdjust;
	}

	public void setAzimuthDirectBAdjust(java.lang.String azimuthDirectBAdjust) {
		this.azimuthDirectBAdjust = azimuthDirectBAdjust;
	}

	@Column(name = "TITLE_CORNER_A_ADJUST", length = 400)
	public java.lang.String getTitleCornerAAdjust() {
		return titleCornerAAdjust;
	}

	public void setTitleCornerAAdjust(java.lang.String titleCornerAAdjust) {
		this.titleCornerAAdjust = titleCornerAAdjust;
	}

	@Column(name = "AZIMUTH_DIRECT_A_ADJUST", length = 400)
	public java.lang.String getAzimuthDirectAAdjust() {
		return azimuthDirectAAdjust;
	}

	public void setAzimuthDirectAAdjust(java.lang.String azimuthDirectAAdjust) {
		this.azimuthDirectAAdjust = azimuthDirectAAdjust;
	}

	@Column(name = "NOTE", length = 1000)
	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}

	@ManyToOne
	@JoinColumn(name = "TIT_AZI_CONSTR_ACCEPT_ID")
	public TitAziConstrAcceptBO getTitAziConstrAccept() {
		return titAziConstrAccept;
	}

	public void setTitAziConstrAccept(TitAziConstrAcceptBO titAziConstrAccept) {
		this.titAziConstrAccept = titAziConstrAccept;
	}

	@Override
	public TitAziConstrAcceptListDTO toDTO() {
		TitAziConstrAcceptListDTO titAziConstrAcceptListDTO = new TitAziConstrAcceptListDTO();
		// set cac gia tri
		titAziConstrAcceptListDTO.setTitAziConAcceptListId(this.titAziConAcceptListId);
		titAziConstrAcceptListDTO.setContent(this.content);
		titAziConstrAcceptListDTO.setUnit(this.unit);
		titAziConstrAcceptListDTO.setTitleCornerBAdjust(this.titleCornerBAdjust);
		titAziConstrAcceptListDTO.setAzimuthDirectBAdjust(this.azimuthDirectBAdjust);
		titAziConstrAcceptListDTO.setTitleCornerAAdjust(this.titleCornerAAdjust);
		titAziConstrAcceptListDTO.setAzimuthDirectAAdjust(this.azimuthDirectAAdjust);
		titAziConstrAcceptListDTO.setNote(this.note);
		// id của bảng cha
		titAziConstrAcceptListDTO.setTitAziConstrAcceptId(
				this.titAziConstrAccept == null ? null : titAziConstrAccept.getTitAziConstrAcceptId());

		return titAziConstrAcceptListDTO;
	}
}
