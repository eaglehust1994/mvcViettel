package com.viettel.asset.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatAssetCodeSearchDto extends BaseSearchDto {

	public static final int MAX_QUERY_AUTOCOMPLETE = 20;
	private Long caacLevel;
	private Long caacParentId;
	private String keySearch;
	private Long superParentId;

	public String getKeySearch() {
		return keySearch;
	}

	public void setKeySearch(String keySearch) {
		this.keySearch = keySearch;
	}

	public Long getSuperParentId() {
		return superParentId;
	}

	public void setSuperParentId(Long superParentId) {
		this.superParentId = superParentId;
	}

	public Long getCaacLevel() {
		return caacLevel;
	}

	public void setCaacLevel(Long caacLevel) {
		this.caacLevel = caacLevel;
	}

	public Long getCaacParentId() {
		return caacParentId;
	}

	public void setCaacParentId(Long caacParentId) {
		this.caacParentId = caacParentId;
	}

}
