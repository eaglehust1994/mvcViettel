/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.viettel.erp.business.ConstrAcceptWorkListBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstrGroundHandoverBusinessImpl;
import com.viettel.erp.business.DetailSettlementEvaluateBusinessImpl;
import com.viettel.erp.business.DetailSettlementProposalBusinessImpl;
import com.viettel.erp.business.EstimatesDetailAnalystBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.dto.DetailSettlementProposalDTO;
import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
import com.viettel.erp.dto.EstimatesItemsChildDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class DetailSettlementProposalRsServiceImpl implements DetailSettlementProposalRsService {
	@Value("${folder_upload}")
	private String folder2Upload;
	@Autowired
	DetailSettlementProposalBusinessImpl detailSettlementProposalBusinessImpl;

	@Autowired
	DetailSettlementEvaluateBusinessImpl detailSettlementEvaluateBusinessImpl;

	@Autowired
	EstimatesDetailAnalystBusinessImpl estimatesDetailAnalystBusinessImpl;
	@Autowired
	ConstrAcceptWorkListBusinessImpl constrAcceptWorkListBusinessImpl;
	@Autowired
	ConstrGroundHandoverBusinessImpl constrGroundHandoverBusinessImpl;
	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	static Logger LOGGER = LoggerFactory.getLogger(DetailSettlementProposalRsServiceImpl.class);

	@Override
	public Response getDetailSettlementProposal() {
		List<DetailSettlementProposalDTO> ls = detailSettlementProposalBusinessImpl.getAll();
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			DataListDTO data = new DataListDTO();
			data.setData(ls);
			/*
			 * data.setTotal(ls.size()); data.setSize(ls.size());
			 * data.setStart(1);
			 */
			return Response.ok(data).build();
		}
	}

	@Override
	public Response getDetailSettlementProposalById(Long id) {
		DetailSettlementProposalDTO obj = (DetailSettlementProposalDTO) detailSettlementProposalBusinessImpl
				.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response getAllDetailSettlementProposal(DetailSettlementProposalDTO detailSettlementProposalDTO) {
		List<DetailSettlementProposalDTO> ls = detailSettlementProposalBusinessImpl
				.getAllDetailSettlementProposal(detailSettlementProposalDTO);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response updateDetailSettlementProposal(DetailSettlementProposalDTO obj) {
		Long id = detailSettlementProposalBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addDetailSettlementProposal(DetailSettlementProposalDTO obj) {
		Long id = detailSettlementProposalBusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;

	@Override
	public Response saveDetailSettlementProposal(DetailSettlementProposalDTO obj) throws Exception {
		obj.setEvaluateStatus(1L);
		detailSettlementProposalBusinessImpl.update(obj);
		String nameTable = "DETAIL_SETTLEMENT_PROPOSAL";
		if (obj.getStatusCa() == 0L) {
			qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getDetailSettlementProposalId(),
					nameTable);
		}
		return Response.ok(Response.Status.CREATED).build();
	}

	@Override
	public Response deleteDetailSettlementProposal(Long id) {
		boolean check = detailSettlementProposalBusinessImpl.checkStatusDatabase(id);
		if (check) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			DetailSettlementProposalDTO obj = (DetailSettlementProposalDTO) detailSettlementProposalBusinessImpl
					.getOneById(id);
			if (obj == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				obj.setIsActive(0L);
				detailSettlementProposalBusinessImpl.update(obj);
				detailSettlementProposalBusinessImpl.deleteFromRecomap(obj);
				return Response.ok(Response.Status.NO_CONTENT).build();
			}
		}
	}

	@Override
	public Response addAll(DetailSettlementProposalDTO Proposal) throws Exception {
		Long constructionId = Proposal.getConstructId();
		List<EstimatesWorkItemsDTO> listAcc = Proposal.getListAcc();

		String code = constrGroundHandoverBusinessImpl.getCode("DETAIL_SETTLEMENT_PROPOSAL", "QLHC_DNQT");
		Proposal.setCode(code);
		Long id = detailSettlementProposalBusinessImpl.addManyTable(Proposal, listAcc);
		try {
			constrCompleteRecordsMapBusinessImpl.insert(constructionId, "DETAIL_SETTLEMENT_PROPOSAL",
					"DETAIL_SETTLEMENT_PROPOSAL_ID", id, Proposal.getCreatedUserId(), code);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return Response.ok(id).build();
	}

	@Override
	public Response updateManyTable(DetailSettlementProposalDTO Proposal) throws Exception {
		// Long constructionId = Proposal.getConstructId();
		List<EstimatesWorkItemsDTO> listAcc = Proposal.getListAcc();

		String code = constrGroundHandoverBusinessImpl.getCode("DETAIL_SETTLEMENT_PROPOSAL", "QLHC_DNQT");
		Proposal.setCode(code);
		detailSettlementProposalBusinessImpl.updateManyTable(Proposal, listAcc);
		return Response.ok(Response.Status.NO_CONTENT).build();
	}

	@Override
	public Response appro(approDTO obj) {
		Long res = detailSettlementProposalBusinessImpl.appro(obj);
		return Response.ok(res).build();
	}

	@Override
	public Response exPortfull(EstimatesWorkItemsDTO obj) throws Exception {
		try {
			// Map beans = new HashMap();
			DetailSettlementProposalDTO dto = new DetailSettlementProposalDTO();
			dto.setConstructId(obj.getConstructionId());
			dto.setContractCode(obj.getConstrtCode());
			List<EstimatesWorkItemsDTO> parentLst = detailSettlementProposalBusinessImpl
					.exPortfull(obj.getConstructionId(),0l);
			List<DetailSettlementProposalDTO> listdto = detailSettlementProposalBusinessImpl
					.getAllDetailSettlementProposal(dto);
			if(listdto.size()==0l){
			return Response.ok().entity(Collections.singletonMap("error", "Không export được do chưa có dữ liệu!")).build();
			}
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "BM-QT-01.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			Sheet sheet = workbook.getSheetAt(1);
			Sheet sheet2 = workbook.getSheetAt(0);
			// set font
			// CellStyle style = null;

			XSSFFont defaultFont = workbook.createFont();
			defaultFont.setFontHeightInPoints((short) 10);
			defaultFont.setFontName("Times New Roman");
			defaultFont.setColor(IndexedColors.BLACK.getIndex());
			defaultFont.setBold(false);
			defaultFont.setItalic(false);

			XSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short) 10);
			font.setFontName("Times New Roman");
			font.setColor(IndexedColors.WHITE.getIndex());
			font.setBold(true);
			font.setItalic(false);

			XSSFFont fontBoldBlack = workbook.createFont();
			fontBoldBlack.setFontHeightInPoints((short) 10);
			fontBoldBlack.setFontName("Times New Roman");
			fontBoldBlack.setColor(IndexedColors.BLACK.getIndex());
			fontBoldBlack.setBold(true);
			fontBoldBlack.setItalic(false);

			XSSFFont fontHyperLink = workbook.createFont();
			fontHyperLink.setFontHeightInPoints((short) 10);
			fontHyperLink.setFontName("Times New Roman");
			fontHyperLink.setColor(IndexedColors.SKY_BLUE.getIndex());
			fontHyperLink.setBold(false);
			fontHyperLink.setItalic(false);
			fontHyperLink.setUnderline(Font.U_SINGLE);

			XSSFFont fontBold = workbook.createFont();
			fontBold.setFontHeightInPoints((short) 12);
			fontBold.setFontName("Times New Roman");
			fontBold.setColor(IndexedColors.BLACK.getIndex());
			fontBold.setBold(true);
			fontBold.setItalic(false);

			XSSFFont fontItalic = workbook.createFont();
			fontItalic.setFontHeightInPoints((short) 12);
			fontItalic.setFontName("Times New Roman");
			fontItalic.setColor(IndexedColors.BLACK.getIndex());
			fontItalic.setBold(false);
			fontItalic.setItalic(true);

			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellStyle.setFont(defaultFont);

			CellStyle cellStyleBold = workbook.createCellStyle();
			cellStyleBold.setBorderBottom(BorderStyle.THIN);
			cellStyleBold.setBorderTop(BorderStyle.THIN);
			cellStyleBold.setBorderLeft(BorderStyle.THIN);
			cellStyleBold.setBorderRight(BorderStyle.THIN);
			cellStyleBold.setFont(fontBoldBlack);

			CellStyle CellStyleHyperLink = workbook.createCellStyle();
			CellStyleHyperLink.setBorderBottom(BorderStyle.THIN);
			CellStyleHyperLink.setBorderTop(BorderStyle.THIN);
			CellStyleHyperLink.setBorderLeft(BorderStyle.THIN);
			CellStyleHyperLink.setBorderRight(BorderStyle.THIN);
			CellStyleHyperLink.setFont(fontHyperLink);

			CellStyle cellStyleRight = workbook.createCellStyle();
			cellStyleRight.setBorderBottom(BorderStyle.THIN);
			cellStyleRight.setBorderTop(BorderStyle.THIN);
			cellStyleRight.setBorderLeft(BorderStyle.THIN);
			cellStyleRight.setFont(fontBoldBlack);

			CellStyle cellStyleLeft = workbook.createCellStyle();
			cellStyleLeft.setBorderBottom(BorderStyle.THIN);
			cellStyleLeft.setBorderTop(BorderStyle.THIN);
			cellStyleLeft.setBorderRight(BorderStyle.THIN);

			CellStyle cellstyleSP = workbook.createCellStyle();
			cellstyleSP.setFont(fontBold);
			cellstyleSP.setAlignment(CellStyle.ALIGN_CENTER);
			// cellstyleSP.setWrapText(true);

			CellStyle cellstyleSP2 = workbook.createCellStyle();
			cellstyleSP2.setFont(fontBold);
			cellstyleSP2.setAlignment(CellStyle.ALIGN_LEFT);
			// cellstyleSP2.setWrapText(true);

			CellStyle cellstyleSP3 = workbook.createCellStyle();
			cellstyleSP3.setFont(fontItalic);
			cellstyleSP3.setAlignment(CellStyle.ALIGN_LEFT);
			
			CellStyle cellstyleSP4 = workbook.createCellStyle();
			cellstyleSP4.setFont(fontItalic);
			cellstyleSP4.setAlignment(CellStyle.ALIGN_RIGHT);
			// cellstyleSP2.setWrapText(true);

			// Add these lines
			// Update the value of cell
			int rowIdx = 0;
			List<EstimatesWorkItemsDTO> insideItem = Lists.newArrayList();
			List<EstimatesWorkItemsDTO> outsideItem = Lists.newArrayList();
			for (int i = 0; i < parentLst.size(); i++) {
				EstimatesWorkItemsDTO parent = parentLst.get(i);
				Row parentRow = sheet.createRow(++rowIdx);
				parentRow.getRowStyle();

				Cell cell0 = parentRow.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(cellStyleBold);

				Cell cell1 = parentRow.createCell(1);
				cell1.setCellValue(parent.getWorkItemCode());
				cell1.setCellStyle(cellStyleBold);

				Cell cell2 = parentRow.createCell(2);
				cell2.setCellValue(parent.getWorkItemName());
				cell2.setCellStyle(cellStyleBold);

				Cell cell3 = parentRow.createCell(3);
				cell3.setCellValue(parent.getUnit());
				cell3.setCellStyle(cellStyleBold);

				Cell cell4 = parentRow.createCell(4);
				cell4.setCellStyle(cellStyleBold);

				Cell cell5 = parentRow.createCell(5);
				cell5.setCellStyle(cellStyleBold);

				Cell cell6 = parentRow.createCell(5);
				cell6.setCellStyle(cellStyleBold);

				Cell cell7 = parentRow.createCell(7);
				cell7.setCellStyle(cellStyleBold);

				for (int j = 0; j < parent.getEstimatesDetailAnalysts().size(); j++) {

					EstimatesDetailAnalystDTO child = parent.getEstimatesDetailAnalysts().get(j);

					Row childRow = sheet.createRow(++rowIdx);

					Cell cellch = childRow.createCell(0);
					cellch.setCellValue("");
					cellch.setCellStyle(cellStyle);

					if (null != child.getCostIngredientCode()) {
						Cell cell = childRow.createCell(1);
						cell.setCellValue(child.getCostIngredientCode());
						cell.setCellStyle(cellStyle);
					} else {
						Cell cell = childRow.createCell(1);
						cell.setCellStyle(cellStyle);
					}
					if (null != child.getCostIngredientName()) {
						Cell cell = childRow.createCell(2);
						cell.setCellValue(child.getCostIngredientName());
						cell.setCellStyle(cellStyle);
					} else {
						Cell cell = childRow.createCell(2);
						cell.setCellStyle(cellStyle);
					}
					if (null != child.getUnit()) {
						Cell cell = childRow.createCell(3);
						cell.setCellValue(child.getUnit());
						cell.setCellStyle(cellStyle);
					} else {
						Cell cell = childRow.createCell(3);
						cell.setCellStyle(cellStyle);
					}
					if (null != child.getNormIndex()) {
						Cell cell = childRow.createCell(4);
						cell.setCellValue(child.getNormIndex());
						cell.setCellStyle(cellStyle);
					} else {
						Cell cell = childRow.createCell(4);
						cell.setCellStyle(cellStyle);
					}
					if (null != child.getUnitPrice()) {
						Cell cell = childRow.createCell(5);
						cell.setCellValue(child.getUnitPrice());
						cell.setCellStyle(cellStyle);
					} else {
						Cell cell = childRow.createCell(5);
						cell.setCellStyle(cellStyle);
					}
					if (child.getCoefficient() != null) {
						Cell cell = childRow.createCell(6);
						cell.setCellValue(child.getCoefficient());
						cell.setCellStyle(cellStyle);
					} else {
						Cell cell = childRow.createCell(6);
						cell.setCellStyle(cellStyle);
					}
					if (null != child.getTotalMoneyFormula()) {
						Cell cell = childRow.createCell(7);
						cell.setCellFormula(child.getTotalMoneyFormula());
						cell.setCellStyle(cellStyle);
						if ("Gxd".equals(child.getCostIngredientCode())) {
							String linkAdd = cell.getAddress().toString();
							String CT = "'" + sheet.getSheetName() + "'" + "!" + linkAdd;
							parent.setCtAdd(CT);
						}
					} else {
						Cell cell = childRow.createCell(7);
						cell.setCellStyle(cellStyle);
					}
				}

				if (parent.getType() == 1) {
					insideItem.add(parent);
				} else if (parent.getType() == 2) {
					outsideItem.add(parent);
				}
			}

			List<EstimatesItemsChildDTO> chidInside = detailSettlementEvaluateBusinessImpl.ConvertListItems(insideItem);
			List<EstimatesItemsChildDTO> chidOutside = detailSettlementEvaluateBusinessImpl
					.ConvertListItems(outsideItem);

			int rowIdSheet1 = 2;
			Row rowContrac = sheet2.createRow(++rowIdSheet1);
			Cell cellContrac = rowContrac.createCell(1);
			cellContrac.setCellValue("Hợp đồng: " + listdto.get(0).getContractCode());
			cellContrac.setCellStyle(cellstyleSP2);
			Row rowNTTC = sheet2.createRow(++rowIdSheet1);
			Cell cellNTTC = rowNTTC.createCell(1);
			cellNTTC.setCellValue("Nhà thầu thi công: ");
			cellNTTC.setCellStyle(cellstyleSP2);
			Row rowConstr = sheet2.createRow(++rowIdSheet1);
			Cell cellConstr = rowConstr.createCell(1);
			cellConstr.setCellValue("Công trình: " + listdto.get(0).getConstrtCode());
			cellConstr.setCellStyle(cellstyleSP2);
			Row rowProject = sheet2.createRow(++rowIdSheet1);
			Cell cellProject = rowProject.createCell(1);
			cellProject.setCellValue("Thuộc dự án:");
			cellProject.setCellStyle(cellstyleSP2);
			String cell2 = new String();
			String cell3 = new String();
			String cell4 = new String();
			String cell5 = new String();
			String cell6 = new String();
			String cell7 = new String();
			// String cell9=new String();
			String addinTop6 = new String();
			String addinTop7 = new String();
			String addinTop8 = new String();
			String addinBot6 = new String();
			String addinBot7 = new String();
			String addinBot8 = new String();

			rowIdSheet1 = rowIdSheet1 + 2;
			Row childRowIn = sheet2.createRow(++rowIdSheet1);
			Cell cellIn0 = childRowIn.createCell(0);
			cellIn0.setCellStyle(cellStyleBold);
			cellIn0.setCellValue("A");
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 1, 8));
			Cell cellIn1 = childRowIn.createCell(1);
			cellIn1.setCellValue("Công việc trong hợp đồng");
			cellIn1.setCellStyle(cellStyleBold);
			Cell cellIn11 = childRowIn.createCell(8);
			cellIn11.setCellStyle(cellStyleLeft);
			for (int i = 0; i < chidInside.size(); i++) {
				childRowIn = sheet2.createRow(++rowIdSheet1);
				Cell cellChildIn0 = childRowIn.createCell(0);
				cellChildIn0.setCellStyle(cellStyleBold);
				cellChildIn0.setCellValue(getRomanNumber(i + 1));
				Cell cellChildIn1 = childRowIn.createCell(1);
				cellChildIn1.setCellValue(chidInside.get(i).getItemName());
				cellChildIn1.setCellStyle(cellStyleBold);
				Cell cellChildIn2 = childRowIn.createCell(2);
				cellChildIn2.setCellStyle(cellStyleLeft);
				Cell cellChildIn3 = childRowIn.createCell(3);
				cellChildIn3.setCellStyle(cellStyleLeft);
				Cell cellChildIn4 = childRowIn.createCell(4);
				cellChildIn4.setCellStyle(cellStyleLeft);
				Cell cellChildIn5 = childRowIn.createCell(5);
				cellChildIn5.setCellStyle(cellStyleLeft);
				Cell cellChildIn6 = childRowIn.createCell(6);
				cellChildIn6.setCellStyle(cellStyleLeft);
				Cell cellChildIn7 = childRowIn.createCell(7);
				cellChildIn7.setCellStyle(cellStyleLeft);
				Cell cellChildIn8 = childRowIn.createCell(8);
				cellChildIn8.setCellStyle(cellStyleLeft);
				sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 1, 8));

				List<EstimatesWorkItemsDTO> inside = chidInside.get(i).getEstimatesWorkItemsDTO();
				for (int j = 0; j < inside.size(); j++) {
					childRowIn = sheet2.createRow(++rowIdSheet1);
					Cell cellSTT = childRowIn.createCell(0);
					cellSTT.setCellValue(j + 1);
					cellSTT.setCellStyle(cellStyle);
					if (StringUtils.isNotEmpty(inside.get(j).getWorkItemName())) {
						Cell cell = childRowIn.createCell(1);
						cell.setCellValue(inside.get(j).getWorkItemName());
						cell.setCellStyle(cellStyle);
					}
					if (null != inside.get(j).getWorkAmount()) {
						Cell cell = childRowIn.createCell(2);
						cell2 = cell.getAddress().toString();
						cell.setCellValue(inside.get(j).getWorkAmount().doubleValue());
						cell.setCellStyle(cellStyle);
					}
					if (null != inside.get(j).getExecuteQuantity()) {
						Cell cell = childRowIn.createCell(3);
						cell3 = cell.getAddress().toString();
						cell.setCellValue(inside.get(j).getExecuteQuantity());
						cell.setCellStyle(cellStyle);
					}
					if (null != inside.get(j).getUnitPrice()) {
						Cell cell = childRowIn.createCell(4);
						cell4 = cell.getAddress().toString();
						cell.setCellValue(inside.get(j).getUnitPrice().doubleValue());
						cell.setCellStyle(cellStyle);
					}
					if (null != inside.get(j).getSettleUnitPrice()) {
						Cell cell = childRowIn.createCell(5);
						cell5 = cell.getAddress().toString();
						if(inside.get(j).getCtAdd() !=null){
						cell.setCellFormula("HYPERLINK(" + '"' + "#" + '"' + "&" + '"' + inside.get(j).getCtAdd() + '"'
								+ ',' + inside.get(j).getCtAdd() + ')');
						cell.setCellStyle(CellStyleHyperLink);
						}else{
							cell.setCellValue(inside.get(j).getSettleUnitPrice());
							cell.setCellStyle(cellStyle);
						}
						
					}
					if (null != inside.get(j).getWorkAmount() && null != inside.get(j).getUnitPrice()) {
						Cell cell = childRowIn.createCell(6);
						cell6 = cell.getAddress().toString();
						cell.setCellFormula(cell2 + "*" + cell4);
						cell.setCellStyle(cellStyle);

						if (j == 0 && i==0) {
							addinTop6 = cell6;
							addinBot6 = cell6;
						} else if (j == (inside.size() - 1) && i == (chidInside.size()-1)) {
							addinBot6 = cell6;
						}
					}
					if (null != inside.get(j).getExecuteQuantity() && null != inside.get(j).getSettleUnitPrice()) {
						Cell cell = childRowIn.createCell(7);
						cell7 = cell.getAddress().toString();
						cell.setCellFormula(cell3 + "*" + cell5);
						cell.setCellStyle(cellStyle);

						if (j == 0 && i==0) {
							addinTop7 = cell7;
							addinBot7 = cell7;
						} else if (j == (inside.size() - 1) && i == (chidInside.size()-1)) {
							addinBot7 = cell7;
						}
					}
					if (null != inside.get(j).getEvaluateQuantity() && null != inside.get(j).getEvaluateUnitPrice()) {
						Cell cell = childRowIn.createCell(8);
						cell.setCellFormula(cell7 + "-" + cell6);
						cell.setCellStyle(cellStyle);

						if (j == 0 && i==0) {
							addinTop8 = cell.getAddress().toString();
							addinBot8 = cell.getAddress().toString();
						} else if (j == (inside.size() - 1) && i == (chidInside.size()-1)) {
							addinBot8 = cell.getAddress().toString();
						}
					}
				}
			}

			childRowIn = sheet2.createRow(++rowIdSheet1);
			Cell cellChildIn0 = childRowIn.createCell(0);
			cellChildIn0.setCellStyle(cellStyleBold);
			Cell cellChildIn1 = childRowIn.createCell(1);
			cellChildIn1.setCellValue("Tổng cộng công việc trong hợp đồng");
			cellChildIn1.setCellStyle(cellStyleBold);
			Cell cellChildIn2 = childRowIn.createCell(2);
			cellChildIn2.setCellStyle(cellStyleLeft);
			Cell cellChildIn3 = childRowIn.createCell(3);
			cellChildIn3.setCellStyle(cellStyleLeft);
			Cell cellChildIn4 = childRowIn.createCell(4);
			cellChildIn4.setCellStyle(cellStyleLeft);
			Cell cellChildIn5 = childRowIn.createCell(5);
			cellChildIn5.setCellStyle(cellStyleLeft);
			Cell cellChildIn6 = childRowIn.createCell(6);
			String sumA6 = cellChildIn6.getAddress().toString();
			cellChildIn6.setCellStyle(cellStyleBold);
			Cell cellChildIn7 = childRowIn.createCell(7);
			String sumA7 = cellChildIn7.getAddress().toString();
			cellChildIn7.setCellStyle(cellStyleBold);
			Cell cellChildIn8 = childRowIn.createCell(8);
			String sumA8 = cellChildIn8.getAddress().toString();
			if(chidInside.size()>0){
				cellChildIn6.setCellFormula("SUM(" + addinTop6 + ':' + addinBot6 + ')');
				cellChildIn7.setCellFormula("SUM(" + addinTop7 + ':' + addinBot7 + ')');
				cellChildIn8.setCellFormula("SUM(" + addinTop8 + ':' + addinBot8 + ')');
				}
			cellChildIn8.setCellStyle(cellStyleBold);

			Row childRowOut = sheet2.createRow(++rowIdSheet1);
			Cell cellOut0 = childRowOut.createCell(0);
			cellOut0.setCellStyle(cellStyleBold);
			cellOut0.setCellValue("B");
			Cell cellOut1 = childRowOut.createCell(1);
			cellOut1.setCellStyle(cellStyleRight);
			cellOut1.setCellValue("Công việc ngoài hợp đồng");
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 1, 8));
			Cell cellOut11 = childRowOut.createCell(8);
			cellOut11.setCellStyle(cellStyleLeft);

			for (int i = 0; i < chidOutside.size(); i++) {
				childRowOut = sheet2.createRow(++rowIdSheet1);
				Cell cellChildOut0 = childRowOut.createCell(0);
				cellChildOut0.setCellStyle(cellStyleBold);
				cellChildOut0.setCellValue(getRomanNumber(i + 1));
				Cell cellChildOut1 = childRowOut.createCell(1);
				cellChildOut1.setCellStyle(cellStyleRight);
				cellChildOut1.setCellValue(chidOutside.get(i).getItemName());
				Cell cellChildOut2 = childRowOut.createCell(2);
				cellChildOut2.setCellStyle(cellStyleLeft);
				Cell cellChildOut3 = childRowOut.createCell(3);
				cellChildOut3.setCellStyle(cellStyleLeft);
				Cell cellChildOut4 = childRowOut.createCell(4);
				cellChildOut4.setCellStyle(cellStyleLeft);
				Cell cellChildOut5 = childRowOut.createCell(5);
				cellChildOut5.setCellStyle(cellStyleLeft);
				Cell cellChildOut6 = childRowOut.createCell(6);
				cellChildOut6.setCellStyle(cellStyleLeft);
				Cell cellChildOut7 = childRowOut.createCell(7);
				cellChildOut7.setCellStyle(cellStyleLeft);
				Cell cellChildOut8 = childRowOut.createCell(8);
				cellChildOut8.setCellStyle(cellStyleLeft);
				sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 1, 8));
				List<EstimatesWorkItemsDTO> outside = chidOutside.get(i).getEstimatesWorkItemsDTO();
				for (int j = 0; j < outside.size(); j++) {
					childRowOut = sheet2.createRow(++rowIdSheet1);
					Cell cellSTT = childRowOut.createCell(0);
					cellSTT.setCellValue(j + 1);
					cellSTT.setCellStyle(cellStyle);
					if (StringUtils.isNotEmpty(outside.get(j).getWorkItemName())) {
						Cell cell = childRowOut.createCell(1);
						cell.setCellValue(outside.get(j).getWorkItemName());
						cell.setCellStyle(cellStyle);
					}
					if (null != outside.get(j).getWorkAmount()) {
						Cell cell = childRowOut.createCell(2);
						cell2 = cell.getAddress().toString();
						cell.setCellValue(outside.get(j).getWorkAmount().doubleValue());
						cell.setCellStyle(cellStyle);
					}
					if (null != outside.get(j).getExecuteQuantity()) {
						Cell cell = childRowOut.createCell(3);
						cell3 = cell.getAddress().toString();
						cell.setCellValue(outside.get(j).getExecuteQuantity());
						cell.setCellStyle(cellStyle);
					}
					if (null != outside.get(j).getUnitPrice()) {
						Cell cell = childRowOut.createCell(4);
						cell4 = cell.getAddress().toString();
						cell.setCellValue(outside.get(j).getUnitPrice().doubleValue());
						cell.setCellStyle(cellStyle);
					}
					if (null != outside.get(j).getSettleUnitPrice()) {
						Cell cell = childRowOut.createCell(5);
						cell5 = cell.getAddress().toString();
						if(outside.get(j).getCtAdd() !=null){
						cell.setCellFormula("HYPERLINK(" + '"' + "#" + '"' + "&" + '"' + outside.get(j).getCtAdd() + '"'
								+ ',' + outside.get(j).getCtAdd() + ')');
						cell.setCellStyle(CellStyleHyperLink);
						} else{
							cell.setCellValue(outside.get(j).getSettleUnitPrice());
							cell.setCellStyle(cellStyle);
						}
						
					}
					if (null != outside.get(j).getWorkAmount() && null != outside.get(j).getUnitPrice()) {
						Cell cell = childRowOut.createCell(6);
						cell6 = cell.getAddress().toString();
						cell.setCellFormula(cell2 + "*" + cell4);
						cell.setCellStyle(cellStyle);

						if (j == 0 && i==0) {
							addinTop6 = cell6;
							addinBot6 = cell6;
						} else if (j == (outside.size() - 1) && i==(chidOutside.size()-1)) {
							addinBot6 = cell6;
						}
					}
					if (null != outside.get(j).getExecuteQuantity() && null != outside.get(j).getSettleUnitPrice()) {
						Cell cell = childRowOut.createCell(7);
						cell7 = cell.getAddress().toString();
						cell.setCellFormula(cell3 + "*" + cell5);
						cell.setCellStyle(cellStyle);

						if (j == 0 && i==0) {
							addinTop7 = cell7;
							addinBot7 = cell7;
						} else if (j == (outside.size() - 1) && i==(chidOutside.size()-1)) {
							addinBot7 = cell7;
						}
					}
					if (null != outside.get(j).getEvaluateQuantity() && null != outside.get(j).getEvaluateUnitPrice()) {
						Cell cell = childRowOut.createCell(8);
						cell.setCellFormula(cell7 + "-" + cell6);
						cell.setCellStyle(cellStyle);

						if (j == 0 && i==0) {
							addinTop8 = cell.getAddress().toString();
							addinBot8 = cell.getAddress().toString();
						} else if (j == (outside.size() - 1) && i==(chidOutside.size()-1)) {
							addinBot8 = cell.getAddress().toString();
						}
					}
				}
			}

			childRowOut = sheet2.createRow(++rowIdSheet1);
			Cell cellChildOut0 = childRowOut.createCell(0);
			cellChildOut0.setCellStyle(cellStyleBold);
			Cell cellChildOut1 = childRowOut.createCell(1);
			cellChildOut1.setCellStyle(cellStyleBold);
			cellChildOut1.setCellValue("Tổng cộng công việc ngoài hợp đồng");
			Cell cellChildOut2 = childRowOut.createCell(2);
			cellChildOut2.setCellStyle(cellStyleLeft);
			Cell cellChildOut3 = childRowOut.createCell(3);
			cellChildOut3.setCellStyle(cellStyleLeft);
			Cell cellChildOut4 = childRowOut.createCell(4);
			cellChildOut4.setCellStyle(cellStyleLeft);
			Cell cellChildOut5 = childRowOut.createCell(5);
			cellChildOut5.setCellStyle(cellStyleLeft);
			Cell cellChildOut6 = childRowOut.createCell(6);
//			cellChildOut6.setCellFormula("SUM(" + addinTop6 + ':' + addinBot6 + ')');
			String sumB6 = cellChildOut6.getAddress().toString();
			cellChildOut6.setCellStyle(cellStyleBold);
			Cell cellChildOut7 = childRowOut.createCell(7);
//			cellChildOut7.setCellFormula("SUM(" + addinTop7 + ':' + addinBot7 + ')');
			String sumB7 = cellChildOut7.getAddress().toString();
			cellChildOut7.setCellStyle(cellStyleBold);
			Cell cellChildOut8 = childRowOut.createCell(8);
//			cellChildOut8.setCellFormula("SUM(" + addinTop8 + ':' + addinBot8 + ')');
			String sumB8 = cellChildOut8.getAddress().toString();
			cellChildOut8.setCellStyle(cellStyleBold);
			if(chidOutside.size()>0){
			cellChildOut6.setCellFormula("SUM(" + addinTop6 + ':' + addinBot6 + ')');
			cellChildOut7.setCellFormula("SUM(" + addinTop7 + ':' + addinBot7 + ')');
			cellChildOut8.setCellFormula("SUM(" + addinTop8 + ':' + addinBot8 + ')');
			} 
			childRowOut = sheet2.createRow(++rowIdSheet1);
			Cell cellSum0 = childRowOut.createCell(0);
			cellSum0.setCellStyle(cellStyleBold);
			Cell cellSum1 = childRowOut.createCell(1);
			cellSum1.setCellStyle(cellStyleBold);
			cellSum1.setCellValue("Tổng cộng công việc:A+B");
			Cell cellSum2 = childRowOut.createCell(2);
			cellSum2.setCellStyle(cellStyleLeft);
			Cell cellSum3 = childRowOut.createCell(3);
			cellSum3.setCellStyle(cellStyleLeft);
			Cell cellSum4 = childRowOut.createCell(4);
			cellSum4.setCellStyle(cellStyleLeft);
			Cell cellSum5 = childRowOut.createCell(5);
			cellSum5.setCellStyle(cellStyleLeft);
			Cell cellSum6 = childRowOut.createCell(6);
			cellSum6.setCellFormula("SUM(" + sumA6 + ',' + sumB6 + ')');
			cellSum6.setCellStyle(cellStyleBold);
			Cell cellSum7 = childRowOut.createCell(7);
			cellSum7.setCellFormula("SUM(" + sumA7 + ',' + sumB7 + ')');
			cellSum7.setCellStyle(cellStyleBold);
			String cellsummoney=cellSum7.getAddress().toString();
			Cell cellSum8 = childRowOut.createCell(8);
			cellSum8.setCellFormula("SUM(" + sumA8 + ',' + sumB8 + ')');
			cellSum8.setCellStyle(cellStyleBold);

			Calendar cal = Calendar.getInstance();
			int day = cal.getTime().getDate();
			String month = ((cal.getTime().getMonth() + 1) < 10) ? "0" + (cal.getTime().getMonth() + 1)
					: String.valueOf((cal.getTime().getMonth() + 1));
			String year = String.valueOf(cal.getTime().getYear()).substring(1);
			Row rowMoney = sheet2.createRow(++rowIdSheet1);
			Cell celltitileMoney = rowMoney.createCell(1);
			celltitileMoney.setCellValue("Bằng chữ:");
			celltitileMoney.setCellStyle(cellstyleSP4);
			Cell cellmoney= rowMoney.createCell(2);
			cellmoney.setCellFormula("VND("+cellsummoney+")");
			cellmoney.setCellStyle(cellstyleSP3);
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 2, 8));
			++rowIdSheet1;
			Row rowdate = sheet2.createRow(++rowIdSheet1);
			Cell celldate = rowdate.createCell(4);
			celldate.setCellValue(
					listdto.get(0).getProvinceName() + ", " + "Ngày " + day + " tháng " + month + " năm 20" + year);
			celldate.setCellStyle(cellstyleSP3);
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 4, 8));
			++rowIdSheet1;
			Row rowSe = sheet2.createRow(++rowIdSheet1);
			Cell cellSe = rowSe.createCell(0);
			cellSe.setCellValue(
					"ĐẠI DIỆN NHÀ THẦU                                                                                                                   NGƯỜI LẬP");
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 0, 8));
			cellSe.setCellStyle(cellstyleSP);

			Row rowth = sheet2.createRow(++rowIdSheet1);
			Cell cellth = rowth.createCell(0);
			cellth.setCellValue(
					"              (Ký, ghi rõ họ tên)                                                                                                                         (Ký, ghi rõ họ và tên)");
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 0, 8));
			cellth.setCellStyle(cellstyleSP);

			file.close();
			File out = new File(folder2Upload + File.separatorChar + "BM-QT-01.xlsx");
			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();

		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		String path = UEncrypt.encryptFileUploadPath("BM-QT-01.xlsx");
		return Response.ok(Collections.singletonMap("fileName", path)).build();

	}

	private String getRomanNumber(Integer i) {
		switch (i) {
		case 1:{
			String romannumber = "I";
			return romannumber;
		}
		case 2:{
			String romannumber = "II";
			return romannumber;
		}
		case 3:{
			String romannumber = "III";
			return romannumber;
		}
		case 4:{
			String romannumber = "IV";
			return romannumber;
		}
		case 5:{
			String romannumber = "V";
			return romannumber;
		}
		case 6:{
			String romannumber = "VI";
			return romannumber;
		}
		case 7:{
			String romannumber = "VII";
			return romannumber;
		}
		case 8:{
			String romannumber = "VIII";
			return romannumber;
		}
		case 9:{
			String romannumber = "IX";
			return romannumber;
		}
		case 10:
		{
			String romannumber = "X";
			return romannumber;
		}
		case 11:{
			String romannumber = "XI";
			return romannumber;
		}
		case 12:{
			String romannumber = "XII";
			return romannumber;
		}
		default:{
			String romannumber = "N";
			return romannumber;
		}
		}
	}

	@Override
	public Response updateIsActive(Long detailSettlementProposalId) throws Exception {
		// TODO Auto-generated method stub
		detailSettlementProposalBusinessImpl.updateIsActive(detailSettlementProposalId);
		return null;
	}
}
