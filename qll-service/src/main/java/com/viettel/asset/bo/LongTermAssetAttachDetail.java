package com.viettel.asset.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LONG_TERM_ASSET_ATTACH_DETAIL")
public class LongTermAssetAttachDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3788347279638840555L;

	@Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "LTA_ATTACH_DETAIL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    @Column(name = "LTA_ATTACH_DETAIL_ID")
    private Long ltaAttachDetailId;
	
	@Column(name = "CONTENT")
    private String content;
	
	@Column(name = "LONG_TERM_ASSET_ID")
	private Long longTermAssetId;

	public Long getLongTermAssetId() {
		return longTermAssetId;
	}

	public void setLongTermAssetId(Long longTermAssetId) {
		this.longTermAssetId = longTermAssetId;
	}

	public Long getLtaAttachDetailId() {
		return ltaAttachDetailId;
	}

	public void setLtaAttachDetailId(Long ltaAttachDetailId) {
		this.ltaAttachDetailId = ltaAttachDetailId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
