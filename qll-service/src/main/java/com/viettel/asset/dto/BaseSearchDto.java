package com.viettel.asset.dto;

import com.viettel.asset.filter.session.UserSession;
import com.viettel.ktts2.common.OrderInfo;

public class BaseSearchDto {

	private String quickSearchQuery;
	private Integer page;
	private Integer size;
	private OrderInfo orderInfo;
	private UserSession userSession;

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String getQuickSearchQuery() {
		return quickSearchQuery;
	}

	public void setQuickSearchQuery(String quickSearchQuery) {
		this.quickSearchQuery = quickSearchQuery;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public int getFirstResult(){
		return (page-1)*size;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

}
