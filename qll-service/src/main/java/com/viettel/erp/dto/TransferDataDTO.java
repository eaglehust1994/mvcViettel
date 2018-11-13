package com.viettel.erp.dto;

import java.util.List;

import com.viettel.erp.bo.EstimatesWorkItemsBO;

public class TransferDataDTO {

	private List<EstimatesWorkItemsDTO> listIn;
	private List<EstimatesWorkItemsDTO> listOut;
	private List<EstimatesWorkItemsBO> parentLst;

	/**
	 * @return the listIn
	 */
	public List<EstimatesWorkItemsDTO> getListIn() {
		return listIn;
	}

	/**
	 * @param listIn
	 *            the listIn to set
	 */
	public void setListIn(List<EstimatesWorkItemsDTO> listIn) {
		this.listIn = listIn;
	}

	/**
	 * @return the listOut
	 */
	public List<EstimatesWorkItemsDTO> getListOut() {
		return listOut;
	}

	/**
	 * @param listOut
	 *            the listOut to set
	 */
	public void setListOut(List<EstimatesWorkItemsDTO> listOut) {
		this.listOut = listOut;
	}

	/**
	 * @return the parentLst
	 */
	public List<EstimatesWorkItemsBO> getParentLst() {
		return parentLst;
	}

	/**
	 * @param parentLst the parentLst to set
	 */
	public void setParentLst(List<EstimatesWorkItemsBO> parentLst) {
		this.parentLst = parentLst;
	}

}
