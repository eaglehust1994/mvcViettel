package com.viettel.erp.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.DetailSettlementProposalBO;
import com.viettel.erp.dao.DetailSettlementProposalDAO;
import com.viettel.erp.dao.EstimatesWorkItemsDAO;
import com.viettel.erp.dto.DetailSettlementProposalDTO;
import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

@Service("detailSettlementProposalBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DetailSettlementProposalBusinessImpl
		extends BaseFWBusinessImpl<DetailSettlementProposalDAO, DetailSettlementProposalDTO, DetailSettlementProposalBO>
		implements DetailSettlementProposalBusiness {

	@Autowired
	private DetailSettlementProposalDAO detailSettlementProposalDAO;
	@Autowired
	private EstimatesWorkItemsDAO dao;

	public DetailSettlementProposalBusinessImpl() {
		tModel = new DetailSettlementProposalBO();
		tDAO = detailSettlementProposalDAO;
	}

	@Override
	public DetailSettlementProposalDAO gettDAO() {
		return detailSettlementProposalDAO;
	}

	@Override
	public long count() {
		return detailSettlementProposalDAO.count("DetailSettlementProposalBO", null);
	}

	@Override
	public String getCode(String tableName, String value) {
		return detailSettlementProposalDAO.getCode(tableName, value);
	}

	public List<DetailSettlementProposalDTO> getAllDetailSettlementProposal(
			DetailSettlementProposalDTO detailSettlementProposalDTO) {
		List<DetailSettlementProposalDTO> list = detailSettlementProposalDAO
				.getAllDetailSettlementProposal(detailSettlementProposalDTO);
		for (DetailSettlementProposalDTO obj : list) {
			if (null != obj.getEvaluateStatus() && obj.getEvaluateStatus().longValue() != 3l) {
				obj.setEvaluateComments(null);
			}
		}
		return list;
	}

	@Override
	public Long addManyTable(DetailSettlementProposalDTO Proposal, List<EstimatesWorkItemsDTO> listAcc)
			throws Exception {
		DetailSettlementProposalBO obj = new DetailSettlementProposalBO();
		obj.setApprovalDate(Proposal.getApprovalDate());
		obj.setBRepresentativeId(Proposal.getBRepresentativeId());
		obj.setCode(Proposal.getCode());
		obj.setConstructId(Proposal.getConstructId());
		obj.setCreatedDate(Proposal.getCreatedDate());
		obj.setCreatedUserId(Proposal.getCreatedUserId());
		obj.setDetailSettlementProposalId(Proposal.getDetailSettlementProposalId());
		obj.setEvaluateComments(Proposal.getEvaluateComments());
		obj.setEvaluateFinishDate(Proposal.getEvaluateFinishDate());
		obj.setEvaluatePersonId(Proposal.getEvaluatePersonId());
		obj.setEvaluateStatus(0L);
		obj.setIsActive(1L);
		obj.setSendPersonId(Proposal.getSendPersonId());
		obj.setStatusCa(0L);
		obj.setCreatedDate(new Date());
		return detailSettlementProposalDAO.addManyTable(obj, listAcc);
	}

	@Override
	public String updateManyTable(DetailSettlementProposalDTO Proposal, List<EstimatesWorkItemsDTO> listAcc)
			throws Exception {
		DetailSettlementProposalBO obj = new DetailSettlementProposalBO();
		obj.setApprovalDate(Proposal.getApprovalDate());
		obj.setBRepresentativeId(Proposal.getBRepresentativeId());
		obj.setCode(Proposal.getCode());
		obj.setConstructId(Proposal.getConstructId());
		obj.setCreatedDate(Proposal.getCreatedDate());
		obj.setCreatedUserId(Proposal.getCreatedUserId());
		obj.setDetailSettlementProposalId(Proposal.getDetailSettlementProposalId());
		obj.setEvaluateComments(Proposal.getEvaluateComments());
		obj.setEvaluateFinishDate(Proposal.getEvaluateFinishDate());
		obj.setEvaluatePersonId(Proposal.getEvaluatePersonId());
		obj.setEvaluateStatus(0L);
		obj.setIsActive(1L);
		obj.setSendPersonId(Proposal.getSendPersonId());
		obj.setStatusCa(0L);
		return detailSettlementProposalDAO.updateManyTable(obj, listAcc);
	}

	@Override
	public boolean checkStatusDatabase(Long detailSettlementProposalId) {
		return detailSettlementProposalDAO.checkStatusDatabase(detailSettlementProposalId);
	}

	@Override
	public Long appro(approDTO obj) {
		// TODO Auto-generated method stub
		return detailSettlementProposalDAO.appro(obj);
	}

	@Override
	public String deleteDetailSettlementProposal(Long id) throws Exception {
		EstimatesWorkItemsDTO obj = new EstimatesWorkItemsDTO();
		obj.setConstructionId(id);
		List<EstimatesWorkItemsDTO> inside = dao.getAllEstimatesWorkInsideContractForEvaluate(obj);
		List<EstimatesWorkItemsDTO> outside = dao.getAllEstimatesWorkOutsideContractForEvaluate(obj);
		inside.addAll(outside);
		return detailSettlementProposalDAO.deleteDetailSettlementProposal(id, inside);
	}

	@Override
	public boolean deleteFromRecomap(DetailSettlementProposalDTO obj) {
		detailSettlementProposalDAO.deleteFromRecomap(obj);
		return true;
	}

	@Override
	public List<EstimatesWorkItemsDTO> exPortfull(Long id,Long flag) {
		List<EstimatesWorkItemsDTO> ListExport = new ArrayList<EstimatesWorkItemsDTO>();
		List<EstimatesWorkItemsDTO> WorkItem = dao.exPortfullPro(id,flag);

		EstimatesWorkItemsDTO ab = new EstimatesWorkItemsDTO();
		EstimatesDetailAnalystDTO Ana;
		for (Iterator<EstimatesWorkItemsDTO> interator = WorkItem.iterator(); interator.hasNext();) {
			EstimatesWorkItemsDTO wi = interator.next();

			if (ab.getEstimatesWorkItemId() == null) {
				ab = new EstimatesWorkItemsDTO();
				ab.setWorkItemCode(wi.getWorkItemCode() == null ? null : wi.getWorkItemCode());
				ab.setWorkItemName(wi.getWorkItemName() == null ? null : wi.getWorkItemName());
				ab.setEvaluateUnitPrice(wi.getEvaluateUnitPrice() == null ? null : wi.getEvaluateUnitPrice());
				ab.setEvaluateQuantity(wi.getEvaluateQuantity() == null ? null : wi.getEvaluateQuantity());
				ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId() == null ? null : wi.getEstimatesWorkItemId());
				ab.setType(wi.getType() == null ? null : wi.getType());
				ab.setWorkAmount(wi.getWorkAmount() == null ? null : wi.getWorkAmount());
				ab.setUnitPrice(wi.getUnitPrice() == null ? null : wi.getUnitPrice());
				ab.setExecuteQuantity(wi.getExecuteQuantity() == null ? null : wi.getExecuteQuantity());
				ab.setSettleUnitPrice(wi.getSettleUnitPrice() == null ? null : wi.getSettleUnitPrice());
				ab.setEstimatesItemChildId(wi.getEstimatesItemChildId() == null ? null : wi.getEstimatesItemChildId());
				ab.setItemName(wi.getItemName() == null ? null : wi.getItemName());
				ab.setItemsCode(wi.getItemsCode() == null ? null : wi.getItemsCode());
				ListExport.add(ab);

			}
			if (ab.getEstimatesWorkItemId().compareTo(wi.getEstimatesWorkItemId()) != 0) {
				ab = new EstimatesWorkItemsDTO();
				ab.setWorkItemCode(wi.getWorkItemCode() == null ? null : wi.getWorkItemCode());
				ab.setWorkItemName(wi.getWorkItemName() == null ? null : wi.getWorkItemName());
				ab.setEvaluateUnitPrice(wi.getEvaluateUnitPrice() == null ? null : wi.getEvaluateUnitPrice());
				ab.setEvaluateQuantity(wi.getEvaluateQuantity() == null ? null : wi.getEvaluateQuantity());
				ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId() == null ? null : wi.getEstimatesWorkItemId());
				ab.setType(wi.getType() == null ? null : wi.getType());
				ab.setWorkAmount(wi.getWorkAmount() == null ? null : wi.getWorkAmount());
				ab.setUnitPrice(wi.getUnitPrice() == null ? null : wi.getUnitPrice());
				ab.setExecuteQuantity(wi.getExecuteQuantity() == null ? null : wi.getExecuteQuantity());
				ab.setSettleUnitPrice(wi.getSettleUnitPrice() == null ? null : wi.getSettleUnitPrice());
				ab.setEstimatesItemChildId(wi.getEstimatesItemChildId() == null ? null : wi.getEstimatesItemChildId());
				ab.setItemName(wi.getItemName() == null ? null : wi.getItemName());
				ab.setItemsCode(wi.getItemsCode() == null ? null : wi.getItemsCode());
				ListExport.add(ab);

			}
			if (ab.getEstimatesWorkItemId().compareTo(wi.getEstimatesWorkItemId()) == 0) {
				Ana = new EstimatesDetailAnalystDTO();
				Ana.setEstimatesWorkItemId(wi.getEstimatesWorkItemId() == null ? null : wi.getEstimatesWorkItemId());
				Ana.setType(wi.getType() == null ? null : wi.getType());
				Ana.setUnit(wi.getUnit() == null ? null : wi.getUnit());

				Ana.setUnitPrice(wi.getUnitPrice4() == null ? null : wi.getUnitPrice4().doubleValue());
				Ana.setNormIndex(wi.getNormIndexCT() == null ? null : wi.getNormIndexCT());
				Ana.setTotalMoney(wi.getTotalMoney() == null ? null : wi.getTotalMoney().doubleValue());
				Ana.setTotalMoneyFormula(wi.getTotalMoneyFormula() == null ? null : wi.getTotalMoneyFormula());
				Ana.setCostIngredientCode(wi.getCostIngredientCode() == null ? null : wi.getCostIngredientCode());
				Ana.setCostIngredientName(wi.getCostIngredientName() == null ? null : wi.getCostIngredientName());
				Ana.setTotalMoneyFormula(wi.getTotalMoneyFormula() == null ? null : wi.getTotalMoneyFormula());
				Ana.setCoefficient(wi.getCoefficient() == null ? null : wi.getCoefficient());

				ab.getEstimatesDetailAnalysts().add(Ana);
			}
		}

		return ListExport;
	}

	@Override
	public boolean updateIsActive(Long detailSettlementProposalId) {
		// TODO Auto-generated method stub
		return detailSettlementProposalDAO.updateIsActive(detailSettlementProposalId);
	}
}
