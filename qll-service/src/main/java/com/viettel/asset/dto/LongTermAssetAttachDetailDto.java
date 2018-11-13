package com.viettel.asset.dto;

import com.viettel.asset.bo.LongTermAssetAttachDetail;

public class LongTermAssetAttachDetailDto {
	private Long ltaAttachDetailId;
	private String content;
	private String attachName;
	private String attachIdEncrypted;
	private Long attachId;
	private Long longTermAssetId;
	private String attachDisplayName;
	
	public String getAttachDisplayName() {
		return attachDisplayName;
	}

	public void setAttachDisplayName(String attachDisplayName) {
		this.attachDisplayName = attachDisplayName;
	}

	public LongTermAssetAttachDetail toEntity(){
		LongTermAssetAttachDetail entity=new LongTermAssetAttachDetail();
		entity.setContent(content);
		entity.setLongTermAssetId(longTermAssetId);
		entity.setLtaAttachDetailId(ltaAttachDetailId);
		return entity;
	}
	
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
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getAttachIdEncrypted() {
		return attachIdEncrypted;
	}
	public void setAttachIdEncrypted(String attachIdEncrypted) {
		this.attachIdEncrypted = attachIdEncrypted;
	}
	public Long getAttachId() {
		return attachId;
	}
	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}
}
