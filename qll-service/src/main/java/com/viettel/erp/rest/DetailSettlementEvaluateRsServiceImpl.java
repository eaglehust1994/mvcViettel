/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstrGroundHandoverBusinessImpl;
import com.viettel.erp.business.DetailSettlementEvaluateBusinessImpl;
import com.viettel.erp.business.DetailSettlementProposalBusinessImpl;
import com.viettel.erp.business.EstimatesWorkItemsBusinessImpl;
import com.viettel.erp.dto.DetailSettlementEvaluateDTO;
import com.viettel.erp.dto.DetailSettlementProposalDTO;
import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
import com.viettel.erp.dto.EstimatesItemsChildDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import net.sf.jxls.transformer.XLSTransformer;

/**
 *
 * @author HungLQ9
 */
public class DetailSettlementEvaluateRsServiceImpl implements DetailSettlementEvaluateRsService {
	@Value("${folder_upload}")
	private String folder2Upload;
	@Autowired
	DetailSettlementEvaluateBusinessImpl detailSettlementEvaluateBusinessImpl;
	@Autowired
	EstimatesWorkItemsBusinessImpl estimatesWorkItemsBusinessImpl;
	@Autowired
	DetailSettlementProposalBusinessImpl detailSettlementProposalBusinessImpl;
	@Autowired
	ConstrGroundHandoverBusinessImpl constrGroundHandoverBusinessImpl;
	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	static Logger LOGGER = LoggerFactory.getLogger(DetailSettlementEvaluateRsServiceImpl.class);
	@Override
	public Response getDetailSettlementEvaluate() {
		List<DetailSettlementEvaluateDTO> ls = detailSettlementEvaluateBusinessImpl.getAll();
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			DataListDTO data = new DataListDTO();
			data.setData(ls);
			data.setTotal(ls.size());
			data.setSize(ls.size());
			data.setStart(1);
			return Response.ok(data).build();
		}
	}

	@Override
	public Response getAllbyConstructId(DetailSettlementEvaluateDTO obj) {
		System.out.println(Math.toDegrees((double)('n' / 12 * 26)));
		List<DetailSettlementEvaluateDTO> ls = detailSettlementEvaluateBusinessImpl.getAllbyConstructId(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getDetailSettlementEvaluateById(Long id) {
		DetailSettlementEvaluateDTO obj = (DetailSettlementEvaluateDTO) detailSettlementEvaluateBusinessImpl
				.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateDetailSettlementEvaluate(DetailSettlementEvaluateDTO obj) {
		Long id = detailSettlementEvaluateBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addOrUpdateDetailSettlementEvaluate(DetailSettlementEvaluateDTO obj) {
		if (null == obj.getDetailSettlementEvaluateId()) {
			Long id = detailSettlementEvaluateBusinessImpl.save(obj);
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(Response.Status.CREATED).build();
			}
		} else {
			Long id = detailSettlementEvaluateBusinessImpl.update(obj);
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(Response.Status.CREATED).build();
			}
		}
	}

	@Override
	public Response deleteDetailSettlementEvaluate(Long id) {
		boolean check = detailSettlementEvaluateBusinessImpl.checkStatusDatabase(id);
		if (check) {
			return Response.status(Response.Status.FOUND).build();
		} else {
			DetailSettlementEvaluateDTO obj = (DetailSettlementEvaluateDTO) detailSettlementEvaluateBusinessImpl
					.getOneById(id);
			if (obj == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
					try {
						detailSettlementEvaluateBusinessImpl.deleteDetailSettlementEvaluate(obj.getConstructId());
						detailSettlementEvaluateBusinessImpl.deleteFromRecomap(id);
						return Response.ok(Response.Status.NO_CONTENT).build();
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
						return Response.status(Response.Status.BAD_REQUEST).build();
					}
			}
		}
	}

	@Override
	public Response addAll(DetailSettlementEvaluateDTO Evaluate) throws Exception {
		Long constructionId = Evaluate.getConstructId();
		DetailSettlementProposalDTO propo = new DetailSettlementProposalDTO();
		propo.setConstructId(Evaluate.getConstructId());
		List<DetailSettlementEvaluateDTO> obj = detailSettlementEvaluateBusinessImpl.getAllbyConstructId(Evaluate);
		List<DetailSettlementProposalDTO> listPropo = detailSettlementProposalBusinessImpl
				.getAllDetailSettlementProposal(propo);
		List<EstimatesWorkItemsDTO> listAcc = Evaluate.getListAcc();
		List<EstimatesDetailAnalystDTO> listAna = Evaluate.getListEstDetail();
		//checklist đề nghị tồn tại
		
		if (listPropo.size() > 0) {
			if (null == listPropo.get(0).getEvaluatePersonId()) {
				return Response.ok().entity(Collections.singletonMap("error", "Người giao việc thẩm định không tồn tại!")).build();
			} 
			
			if (null == Evaluate.getDetailSettlementEvaluateId()) {
				//check tồn tại bản ghi nào chưa
				if (obj.size() == 0L) {
						String code = constrGroundHandoverBusinessImpl.getCode("DETAIL_SETTLEMENT_EVALUATE",
								"QLHC_TDQT");
						Evaluate.setCode(code);
						Long id = detailSettlementEvaluateBusinessImpl.addManyTable(Evaluate, listAcc, listAna);
						
						try {
							constrCompleteRecordsMapBusinessImpl.insert(constructionId, "DETAIL_SETTLEMENT_EVALUATE",
									"DETAIL_SETTLEMENT_EVALUATE_ID", id, Evaluate.getCreatedUserId(), code);
						} catch (Exception e) {
							LOGGER.error(e.getMessage(), e);
						}
					} else {
						return Response.ok().entity(Collections.singletonMap("error", "Không thể tạo nhiều hơn 1 bản ghi thẩm định quyết toán cho 1 công trình")).build();
					}
		} else {
			if(Evaluate.getCreatedUserId().longValue() != obj.get(0).getCreatedUserId().longValue()){
				return Response.ok().entity(Collections.singletonMap("error", "Bạn không phải là người được giao việc thẩm định nên không có quyền sửa chứng từ này!")).build();
			}
			if (1 == obj.get(0).getStatusCa() || 2 == obj.get(0).getStatusCa()) {
				// check trạng thái duyệt
				return Response.ok().entity(Collections.singletonMap("error", "Không được sửa bản ghi trong trạng thái trình duyệt và đã duyệt!")).build();
			} else {
				detailSettlementEvaluateBusinessImpl.addManyTable(Evaluate, listAcc, listAna);
			}
		}
	} else {
		return Response.ok().entity(Collections.singletonMap("error", "Chưa có đề nghị quyết toán!")).build();
		}
		return Response.ok(Response.Status.CREATED).build();
	}

	@Override
	public Response getAllEstimatesWorkOutsideContractForEvaluate(EstimatesWorkItemsDTO obj) {
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl
				.getAllEstimatesWorkOutsideContractForEvaluate(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			for (EstimatesWorkItemsDTO l : ls) {
				if(null == l.getEvaluateQuantity() && l.getExecuteQuantity() != null){
					l.setEvaluateQuantity(l.getExecuteQuantity().doubleValue());
					}
				if(null == l.getEvaluateUnitPrice()&& l.getSettleUnitPrice() != null){
					l.setEvaluateUnitPrice(l.getSettleUnitPrice().doubleValue());
				}

//				if (l.getWorkAmount() != null && l.getUnitPrice() != null) {
//					l.setAllotmentAmount(l.getWorkAmount() * l.getUnitPrice().doubleValue());
//				}

//				if (l.getExecuteQuantity() != null && l.getSettleUnitPrice() != null) {
//					l.setAdvanceAmount(l.getExecuteQuantity() * l.getSettleUnitPrice().doubleValue());
//				}

//				if (l.getEvaluateQuantity() != null && l.getEvaluateUnitPrice() != null) {
//					l.setApprovalAmount(l.getEvaluateQuantity() * l.getEvaluateUnitPrice().doubleValue());
//				}

//				if (l.getAdvanceAmount() != null && l.getApprovalAmount() != null) {
//					l.setRevaluationAmount(Math.abs(l.getApprovalAmount() - l.getAdvanceAmount()));
//				}
			}
			return Response.ok(ls).build();
		}

	}

	@Override
	public Response getAllEstimatesWorkInsideContractForEvaluate(EstimatesWorkItemsDTO obj) {
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl
				.getAllEstimatesWorkInsideContractForEvaluate(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			for (EstimatesWorkItemsDTO l : ls) {
				if(null == l.getEvaluateQuantity() && l.getExecuteQuantity() != null){
				l.setEvaluateQuantity(l.getExecuteQuantity().doubleValue());
				}
				if(null == l.getEvaluateUnitPrice()&& l.getSettleUnitPrice() != null){
					l.setEvaluateUnitPrice(l.getSettleUnitPrice().doubleValue());
				}

//				if (l.getWorkAmount() != null && l.getUnitPrice() != null) {
//					l.setAllotmentAmount(l.getWorkAmount() * l.getUnitPrice().doubleValue());
//				}
//
//				if (l.getExecuteQuantity() != null && l.getSettleUnitPrice() != null) {
//					l.setAdvanceAmount(l.getExecuteQuantity() * l.getSettleUnitPrice().doubleValue());
//				}
//
//				if (l.getEvaluateQuantity() != null && l.getEvaluateUnitPrice() != null) {
//					l.setApprovalAmount(l.getEvaluateQuantity() * l.getEvaluateUnitPrice().doubleValue());
//				}
//
//				if (l.getAdvanceAmount() != null && l.getApprovalAmount() != null) {
//					l.setRevaluationAmount(Math.abs(l.getApprovalAmount() - l.getAdvanceAmount()));
//				}
			}
			return Response.ok(ls).build();
		}

	}

	@Override
	public Response fail(DetailSettlementEvaluateDTO obj) throws Exception {
		detailSettlementEvaluateBusinessImpl.fail(obj);
		return Response.ok(Response.Status.CREATED).build();
	}

	@Override
	public Response importCT(EstimatesWorkItemsDTO obj) throws Exception {
		try {
			String filename = UEncrypt.decryptFileUploadPath(obj.getPathFile());

			Long constrId = obj.getConstructionId();
			return Response.ok(detailSettlementEvaluateBusinessImpl.importCT(filename, constrId)).build();
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response importKL(EstimatesWorkItemsDTO obj) throws Exception {
		try {
			String path = UEncrypt.decryptFileUploadPath(obj.getPathFile());
			return Response.ok(detailSettlementEvaluateBusinessImpl.importKL(path)).build();
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	private void saveWorkbook(Workbook resultWorkbook, String fileName) throws IOException {
		OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
		resultWorkbook.write(os);
		os.flush();
		os.close();
	}

	@Override
	public Response exPortKLTD(EstimatesWorkItemsDTO obj) throws Exception {
		Map beans = new HashMap();
		List<EstimatesWorkItemsDTO> workitemin = estimatesWorkItemsBusinessImpl
				.getAllEstimatesWorkInsideContractForEvaluate(obj);
		List<EstimatesWorkItemsDTO> workitemout = estimatesWorkItemsBusinessImpl
				.getAllEstimatesWorkOutsideContractForEvaluate(obj);
//		for (EstimatesWorkItemsDTO out : workitemout) {
//			workitemin.add(out);
//		}
		
		for (int i = 0; i < workitemin.size(); i++) {
			workitemin.get(i).setRownum(i + 1);
		}
		
		for(int i = 0 ; i< workitemout.size() ; i++){
			workitemout.get(i).setRownum(i + 1);
        }
		
		beans.put("workitemins", workitemin);
	    beans.put("workitemouts", workitemout);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream is = new BufferedInputStream(new FileInputStream(filePath + "KLTD-template.xlsx"));
		XLSTransformer transformer = new XLSTransformer();
		Workbook resultWorkbook = transformer.transformXLS(is, beans);
		is.close();
		saveWorkbook(resultWorkbook, folder2Upload + "/KLTD-template.xlsx");
		String filename= UEncrypt.encryptFileUploadPath("KLTD-template.xlsx");
		return Response.ok(Collections.singletonMap("fileName", filename)).build();
	}

	@Override
	public Response appro(approDTO obj) {
		Long res = detailSettlementEvaluateBusinessImpl.appro(obj);
		return Response.ok(res).build();
	}

	@Override
	public Response exPortCT(EstimatesWorkItemsDTO obj) throws Exception {
		try {
			
			obj.setProgressTypeAnalyst(2L);
			List<EstimatesWorkItemsDTO> EstimatesWorkItemsList = detailSettlementProposalBusinessImpl.exPortfull(obj.getConstructionId(),1l);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			FileInputStream file = new FileInputStream(filePath + "chiet-tinh-template.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			//set font
			  // CellStyle style=null;

			    XSSFFont defaultFont= workbook.createFont();
			    defaultFont.setFontHeightInPoints((short)10);
			    defaultFont.setFontName("Arial");
			    defaultFont.setColor(IndexedColors.BLACK.getIndex());
			    defaultFont.setBold(false);
			    defaultFont.setItalic(false);

			    XSSFFont font= workbook.createFont();
			    font.setFontHeightInPoints((short)10);
			    font.setFontName("Arial");
			    font.setColor(IndexedColors.WHITE.getIndex());
			    font.setBold(true);
			    font.setItalic(false);
			    
			    XSSFFont fontBoldBlack= workbook.createFont();
			    fontBoldBlack.setFontHeightInPoints((short)10);
			    fontBoldBlack.setFontName("Arial");
			    fontBoldBlack.setColor(IndexedColors.BLACK.getIndex());
			    fontBoldBlack.setBold(true);
			    fontBoldBlack.setItalic(false);
			    
			    CellStyle  cellStyle =	workbook.createCellStyle();
				cellStyle.setBorderBottom(BorderStyle.THIN);
				cellStyle.setBorderTop(BorderStyle.THIN);
				cellStyle.setBorderLeft(BorderStyle.THIN);
				cellStyle.setBorderRight(BorderStyle.THIN);
				
				
			    CellStyle  cellStyleTotalMoney =	workbook.createCellStyle();
			    cellStyleTotalMoney.setBorderBottom(BorderStyle.THIN);
			    cellStyleTotalMoney.setBorderTop(BorderStyle.THIN);
			    cellStyleTotalMoney.setBorderLeft(BorderStyle.THIN);
			    cellStyleTotalMoney.setBorderRight(BorderStyle.THIN);
			    cellStyleTotalMoney.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
				

			    CellStyle  cellStyleBold =	workbook.createCellStyle();
			    cellStyleBold.setBorderBottom(BorderStyle.THIN);
			    cellStyleBold.setBorderTop(BorderStyle.THIN);
			    cellStyleBold.setBorderLeft(BorderStyle.THIN);
			    cellStyleBold.setBorderRight(BorderStyle.THIN);
				cellStyleBold.setFont(fontBoldBlack);
			    /*    style.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
			    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			    style.setAlignment(CellStyle.ALIGN_CENTER);
			    style.setFont(font);*/
			
			//Add these lines     
/*			 List<EstimatesWorkItemsDTO> parentLst= Lists.newArrayList();
			 for(EstimatesWorkItemsDTO
			 EstimatesWorkItems:EstimatesWorkItemsList){
			 if(EstimatesWorkItems.getEstimatesDetailAnalysts().size()>0 && EstimatesWorkItems.getEstimatesDetailAnalysts().get(0).getProgressType()==2){
			 parentLst.add(EstimatesWorkItems);
			 	}
			 }*/
			// Update the value of cell
			int rowIdx = 0;
			for (int i = 0; i < EstimatesWorkItemsList.size(); i++) {
				EstimatesWorkItemsDTO parent = EstimatesWorkItemsList.get(i);
//				if (parent.getEstimatesDetailAnalysts().size() > 0l && parent.getEstimatesDetailAnalysts().get(0).getProgressType()==2) {
					Row parentRow = sheet.createRow(++rowIdx);
				    parentRow.getRowStyle();
				   
					Cell cell0=parentRow.createCell(0);
					cell0.setCellValue(i + 1);
					cell0.setCellStyle(cellStyleBold);
					
					Cell cell1 = parentRow.createCell(1);
					cell1.setCellValue(parent.getWorkItemCode());
					cell1.setCellStyle(cellStyleBold);
					
					Cell cell2 =parentRow.createCell(2);
					cell2.setCellValue(parent.getWorkItemName());
					cell2.setCellStyle(cellStyleBold);
					
					Cell cell3 =parentRow.createCell(3);
					cell3.setCellValue(parent.getUnit());
					cell3.setCellStyle(cellStyleBold);
					
					Cell cell4 =parentRow.createCell(4);
					cell4.setCellStyle(cellStyleBold);
					
					Cell cell5 =parentRow.createCell(5);
					cell5.setCellStyle(cellStyleBold);
					
					Cell cell6 =parentRow.createCell(5);
					cell6.setCellStyle(cellStyleBold);
					
					Cell cell7 =parentRow.createCell(7);
					cell7.setCellStyle(cellStyleBold);
					
//					style=parentRow.getRowStyle();
//					if(style!=null){
//					    style.setBorderLeft(BorderStyle.MEDIUM);
//					    style.setBorderBottom(BorderStyle.MEDIUM);
//					}

					for (int j = 0; j < parent.getEstimatesDetailAnalysts().size(); j++) {
						EstimatesDetailAnalystDTO child = parent.getEstimatesDetailAnalysts().get(j);

						Row childRow = sheet.createRow(++rowIdx);
						
						Cell cellch =	childRow.createCell(0);
						cellch.setCellValue("");
						cellch.setCellStyle(cellStyle);
						
						if (null != child.getCostIngredientCode()) {
						Cell cell =	childRow.createCell(1);
						cell.setCellValue(child.getCostIngredientCode());
						cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(1);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getCostIngredientName()) {
						Cell cell =	childRow.createCell(2);
						cell.setCellValue(child.getCostIngredientName());
						cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(2);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getUnit()) {
						Cell cell =	childRow.createCell(3);
							cell.setCellValue(child.getUnit());
							cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(3);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getNormIndex()) {
						Cell cell =	childRow.createCell(4);
							cell.setCellValue(child.getNormIndex());
							cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(4);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getUnitPrice()) {
						Cell cell=	childRow.createCell(5);
							cell.setCellValue(child.getUnitPrice());
							System.out.println(child.getUnitPrice() +"----");
							cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(5);
							cell.setCellStyle(cellStyle);
						}
						if (child.getCoefficient() != null) {
						Cell cell=	childRow.createCell(6);
							cell.setCellValue(child.getCoefficient());
							cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(6);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getTotalMoneyFormula()) {
						Cell cell =	childRow.createCell(7);
							cell.setCellFormula(child.getTotalMoneyFormula());
							cell.setCellStyle(cellStyleTotalMoney);

						}else{
							Cell cell =	childRow.createCell(7);
							cell.setCellStyle(cellStyle);
						}
					}
					
//				}
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "chiet-tinh-template.xlsx");
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
		String filename= UEncrypt.encryptFileUploadPath("chiet-tinh-template.xlsx");
		return Response.ok(Collections.singletonMap("fileName", filename)).build();

	}

	
	
	public Response checkPoFound(DetailSettlementProposalDTO obj) throws Exception {
		List<DetailSettlementProposalDTO> ls = detailSettlementProposalBusinessImpl.getAllDetailSettlementProposal(obj);
		return Response.ok(ls).build();
	}

	@SuppressWarnings("deprecation")
	@Override
	public Response exPortfull(EstimatesWorkItemsDTO obj) throws Exception {
		try {
			DetailSettlementEvaluateDTO dto=new DetailSettlementEvaluateDTO();
			dto.setConstructId(obj.getConstructionId());
			dto.setContractCode(obj.getContractCode());
			List<EstimatesWorkItemsDTO> parentLst = detailSettlementEvaluateBusinessImpl.exPortfull(obj.getConstructionId(),obj.getContractCode());
			List<DetailSettlementEvaluateDTO> listdto=detailSettlementEvaluateBusinessImpl.getAllbyConstructId(dto);
			if(listdto.size()==0l){
				return Response.ok().entity(Collections.singletonMap("error", "Không export được do chưa có dữ liệu!")).build();
				}
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "BM-QT-02.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			Sheet sheet = workbook.getSheetAt(1);
			Sheet sheet2 = workbook.getSheetAt(0);
//			int count=parentLst.size()+5;
//			sheet2.shiftRows(16, 18, count, true,true);
			//set font
		//	   CellStyle style=null;

			    XSSFFont defaultFont= workbook.createFont();
			    defaultFont.setFontHeightInPoints((short)10);
			    defaultFont.setFontName("Times New Roman");
			    defaultFont.setColor(IndexedColors.BLACK.getIndex());
			    defaultFont.setBold(false);
			    defaultFont.setItalic(false);

			    XSSFFont font= workbook.createFont();
			    font.setFontHeightInPoints((short)10);
			    font.setFontName("Times New Roman");
			    font.setColor(IndexedColors.WHITE.getIndex());
			    font.setBold(true);
			    font.setItalic(false);
			    
			    XSSFFont fontBoldBlack= workbook.createFont();
			    fontBoldBlack.setFontHeightInPoints((short)10);
			    fontBoldBlack.setFontName("Times New Roman");
			    fontBoldBlack.setColor(IndexedColors.BLACK.getIndex());
			    fontBoldBlack.setBold(true);
			    fontBoldBlack.setItalic(false);
			    
			    XSSFFont fontHyperLink=workbook.createFont();
			    fontHyperLink.setFontHeightInPoints((short)10);
			    fontHyperLink.setFontName("Times New Roman");
			    fontHyperLink.setColor(IndexedColors.SKY_BLUE.getIndex());
			    fontHyperLink.setBold(false);
			    fontHyperLink.setItalic(false);
			    fontHyperLink.setUnderline(Font.U_SINGLE);
			    
			    
			    XSSFFont fontBold= workbook.createFont();
			    fontBold.setFontHeightInPoints((short)12);
			    fontBold.setFontName("Times New Roman");
			    fontBold.setColor(IndexedColors.BLACK.getIndex());
			    fontBold.setBold(true);
			    fontBold.setItalic(false);
			    
			    XSSFFont fontItalic= workbook.createFont();
			    fontItalic.setFontHeightInPoints((short)12);
			    fontItalic.setFontName("Times New Roman");
			    fontItalic.setColor(IndexedColors.BLACK.getIndex());
			    fontItalic.setBold(false);
			    fontItalic.setItalic(true);
			    
			    
			    CellStyle  cellStyle =	workbook.createCellStyle();
				cellStyle.setBorderBottom(BorderStyle.THIN);
				cellStyle.setBorderTop(BorderStyle.THIN);
				cellStyle.setBorderLeft(BorderStyle.THIN);
				cellStyle.setBorderRight(BorderStyle.THIN);
				cellStyle.setFont(defaultFont);

			    CellStyle  cellStyleBold =	workbook.createCellStyle();
			    cellStyleBold.setBorderBottom(BorderStyle.THIN);
			    cellStyleBold.setBorderTop(BorderStyle.THIN);
			    cellStyleBold.setBorderLeft(BorderStyle.THIN);
			    cellStyleBold.setBorderRight(BorderStyle.THIN);
				cellStyleBold.setFont(fontBoldBlack);
				
				CellStyle CellStyleHyperLink=workbook.createCellStyle();
				CellStyleHyperLink.setBorderBottom(BorderStyle.THIN);
				CellStyleHyperLink.setBorderTop(BorderStyle.THIN);
				CellStyleHyperLink.setBorderLeft(BorderStyle.THIN);
				CellStyleHyperLink.setBorderRight(BorderStyle.THIN);
				CellStyleHyperLink.setFont(fontHyperLink);
				
				CellStyle  cellStyleRight =	workbook.createCellStyle();
				cellStyleRight.setBorderBottom(BorderStyle.THIN);
				cellStyleRight.setBorderTop(BorderStyle.THIN);
				cellStyleRight.setBorderLeft(BorderStyle.THIN);
				cellStyleRight.setFont(fontBoldBlack);
				
				CellStyle  cellStyleLeft =	workbook.createCellStyle();
				cellStyleLeft.setBorderBottom(BorderStyle.THIN);
				cellStyleLeft.setBorderTop(BorderStyle.THIN);
				cellStyleLeft.setBorderRight(BorderStyle.THIN);
				
				CellStyle cellstyleSP=workbook.createCellStyle();
				cellstyleSP.setFont(fontBold);
				cellstyleSP.setAlignment(CellStyle.ALIGN_CENTER);
//				cellstyleSP.setWrapText(true);
				
				CellStyle cellstyleSP2=workbook.createCellStyle();
				cellstyleSP2.setFont(fontBold);
				cellstyleSP2.setAlignment(CellStyle.ALIGN_LEFT);
//				cellstyleSP2.setWrapText(true);
				
				
				CellStyle cellstyleSP3=workbook.createCellStyle();
				cellstyleSP3.setFont(fontItalic);
				cellstyleSP3.setAlignment(CellStyle.ALIGN_LEFT);
//				cellstyleSP2.setWrapText(true);
				
				
				CellStyle cellstyleSP4 = workbook.createCellStyle();
				cellstyleSP4.setFont(fontItalic);
				cellstyleSP4.setAlignment(CellStyle.ALIGN_RIGHT);
					
			//Add these lines     
			// Update the value of cell
			int rowIdx = 0;
			List<EstimatesWorkItemsDTO> insideItem= Lists.newArrayList(); 
			List<EstimatesWorkItemsDTO> outsideItem= Lists.newArrayList();
			for (int i = 0; i < parentLst.size(); i++) {
				EstimatesWorkItemsDTO parent = parentLst.get(i);
				Row parentRow = sheet.createRow(++rowIdx);
			    parentRow.getRowStyle();
			   
				Cell cell0=parentRow.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(cellStyleBold);
				
				Cell cell1 = parentRow.createCell(1);
				cell1.setCellValue(parent.getWorkItemCode());
				cell1.setCellStyle(cellStyleBold);
				
				Cell cell2 =parentRow.createCell(2);
				cell2.setCellValue(parent.getWorkItemName());
				cell2.setCellStyle(cellStyleBold);
				
				Cell cell3 =parentRow.createCell(3);
				cell3.setCellValue(parent.getUnit());
				cell3.setCellStyle(cellStyleBold);
				
				Cell cell4 =parentRow.createCell(4);
				cell4.setCellStyle(cellStyleBold);
				
				Cell cell5 =parentRow.createCell(5);
				cell5.setCellStyle(cellStyleBold);
				
				Cell cell6 =parentRow.createCell(5);
				cell6.setCellStyle(cellStyleBold);
				
				Cell cell7 =parentRow.createCell(7);
				cell7.setCellStyle(cellStyleBold);
					
				if(parent.getType()==1){
					insideItem.add(parent);
				} else if(parent.getType()==2){
					outsideItem.add(parent);
				}

					for (int j = 0; j < parent.getEstimatesDetailAnalysts().size(); j++) {
						
						String linkAdd;
						
						EstimatesDetailAnalystDTO child = parent.getEstimatesDetailAnalysts().get(j);

						Row childRow = sheet.createRow(++rowIdx);
						
						Cell cellch =	childRow.createCell(0);
						cellch.setCellValue("");
						cellch.setCellStyle(cellStyle);
						
						if (null != child.getCostIngredientCode()) {
						Cell cell =	childRow.createCell(1);
						cell.setCellValue(child.getCostIngredientCode());
						cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(1);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getCostIngredientName()) {
						Cell cell =	childRow.createCell(2);
						cell.setCellValue(child.getCostIngredientName());
						cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(2);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getUnit()) {
						Cell cell =	childRow.createCell(3);
							cell.setCellValue(child.getUnit());
							cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(3);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getNormIndex()) {
						Cell cell =	childRow.createCell(4);
							cell.setCellValue(child.getNormIndex());
							cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(4);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getUnitPrice()) {
						Cell cell=	childRow.createCell(5);
							cell.setCellValue(child.getUnitPrice());
							cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(5);
							cell.setCellStyle(cellStyle);
						}
						if (child.getCoefficient() != null) {
						Cell cell=	childRow.createCell(6);
							cell.setCellValue(child.getCoefficient());
							cell.setCellStyle(cellStyle);
						}else{
							Cell cell =	childRow.createCell(6);
							cell.setCellStyle(cellStyle);
						}
						if (null != child.getTotalMoneyFormula()) {
						Cell cell =	childRow.createCell(7);
							cell.setCellFormula(child.getTotalMoneyFormula());
							cell.setCellStyle(cellStyle);
							if("Gxd".equals(child.getCostIngredientCode())){
								linkAdd=cell.getAddress().toString();
								String CT="'"+sheet.getSheetName()+"'"+"!"+linkAdd;
								parent.setCtAdd(CT);
							}
						}else{
							Cell cell =	childRow.createCell(7);
							cell.setCellStyle(cellStyle);
						}
						
						
					}
					
			}
			List<EstimatesItemsChildDTO> chidInside= detailSettlementEvaluateBusinessImpl.ConvertListItems(insideItem);
			List<EstimatesItemsChildDTO> chidOutside= detailSettlementEvaluateBusinessImpl.ConvertListItems(outsideItem);
			
			int	rowIdSheet1=2;
			Row rowContrac=sheet2.createRow(++rowIdSheet1);
			Cell cellContrac= rowContrac.createCell(1);
			cellContrac.setCellValue("Hợp đồng: "+listdto.get(0).getContractCode());
			cellContrac.setCellStyle(cellstyleSP2);
			Row rowNTTC=sheet2.createRow(++rowIdSheet1);
			Cell cellNTTC=rowNTTC.createCell(1);
			cellNTTC.setCellValue("Nhà thầu thi công: ");
			cellNTTC.setCellStyle(cellstyleSP2);
			Row rowConstr=sheet2.createRow(++rowIdSheet1);
			Cell cellConstr=rowConstr.createCell(1);
			cellConstr.setCellValue("Công trình: "+listdto.get(0).getConstrtName());
			cellConstr.setCellStyle(cellstyleSP2);
			Row rowProject=sheet2.createRow(++rowIdSheet1);
			Cell cellProject=rowProject.createCell(1);
			cellProject.setCellValue("Thuộc dự án:");
			cellProject.setCellStyle(cellstyleSP2);
			
			String cell2= new String();
			String cell3= new String();
			String cell4= new String();
			String cell5 =new String();
			String cell6 =new String();
			String cell10= new String();
			String cell9=new String();
			
			String addinTop9=new String();
			String addinTop10=new String();
			String addinTop11=new String();
			String addinTop12=new String();
			String addinBot9=new String();
			String addinBot10=new String();
			String addinBot11=new String();
			String addinBot12=new String();
			
			rowIdSheet1=rowIdSheet1+2;
			Row childRowIn = sheet2.createRow(++rowIdSheet1);
			
			Cell cellIn0=childRowIn.createCell(0);
			cellIn0.setCellStyle(cellStyleBold);
			cellIn0.setCellValue("A");
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 1, 12));
			Cell cellIn1=childRowIn.createCell(1);
			cellIn1.setCellStyle(cellStyleRight);
			cellIn1.setCellValue("Công việc trong hợp đồng");
			Cell cellIn11=childRowIn.createCell(12);
			cellIn11.setCellStyle(cellStyleLeft);
			for(int i=0;i<chidInside.size();i++){
				childRowIn = sheet2.createRow(++rowIdSheet1);
				Cell cellChildIn0=childRowIn.createCell(0);
				cellChildIn0.setCellStyle(cellStyleBold);
				cellChildIn0.setCellValue(getRomanNumber(i+1));
				Cell cellChildIn1=childRowIn.createCell(1);
				cellChildIn1.setCellValue(chidInside.get(i).getItemName());
				cellChildIn1.setCellStyle(cellStyleBold);
				Cell cellChildIn2=childRowIn.createCell(2);
				cellChildIn2.setCellStyle(cellStyleLeft);
				Cell cellChildIn3=childRowIn.createCell(3);
				cellChildIn3.setCellStyle(cellStyleLeft);
				Cell cellChildIn4=childRowIn.createCell(4);
				cellChildIn4.setCellStyle(cellStyleLeft);
				Cell cellChildIn5=childRowIn.createCell(5);
				cellChildIn5.setCellStyle(cellStyleLeft);
				Cell cellChildIn6=childRowIn.createCell(6);
				cellChildIn6.setCellStyle(cellStyleLeft);
				Cell cellChildIn7=childRowIn.createCell(7);
				cellChildIn7.setCellStyle(cellStyleLeft);
				Cell cellChildIn8=childRowIn.createCell(8);
				cellChildIn8.setCellStyle(cellStyleLeft);
				Cell cellChildIn9=childRowIn.createCell(9);
				cellChildIn9.setCellStyle(cellStyleLeft);
				Cell cellChildIn10=childRowIn.createCell(10);
				cellChildIn10.setCellStyle(cellStyleLeft);
				Cell cellChildIn11=childRowIn.createCell(11);
				cellChildIn11.setCellStyle(cellStyleLeft);
				Cell cellChildIn12=childRowIn.createCell(12);
				cellChildIn12.setCellStyle(cellStyleLeft);
				sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 1, 12));
				
				
				
				List<EstimatesWorkItemsDTO> inside=chidInside.get(i).getEstimatesWorkItemsDTO();
			
				for(int j = 0; j < inside.size(); j++){
					childRowIn = sheet2.createRow(++rowIdSheet1);
					Cell cellSTT=childRowIn.createCell(0);
					cellSTT.setCellValue(j+1);
					cellSTT.setCellStyle(cellStyle);
					if (StringUtils.isNotEmpty(inside.get(j).getWorkItemName())) {
						Cell cell=childRowIn.createCell(1);
						cell.setCellValue(inside.get(j).getWorkItemName());
						cell.setCellStyle(cellStyle);
					}
					if(null != inside.get(j).getUnit()){
						Cell cell=childRowIn.createCell(2);
						cell.setCellValue(inside.get(j).getUnit());
						cell.setCellStyle(cellStyle);
					}
					if(null != inside.get(j).getWorkAmount()){
						Cell cell=childRowIn.createCell(3);
						cell2=cell.getAddress().toString();
						cell.setCellValue(inside.get(j).getWorkAmount().doubleValue());
						cell.setCellStyle(cellStyle);
					}
					if(null != inside.get(j).getExecuteQuantity()){
						Cell cell=childRowIn.createCell(4);
						cell3=cell.getAddress().toString();
						cell.setCellValue(inside.get(j).getExecuteQuantity());
						cell.setCellStyle(cellStyle);
					}
					if (null != inside.get(j).getEvaluateQuantity()) {
						Cell cell =childRowIn.createCell(5);
						cell4=cell.getAddress().toString();
						cell.setCellValue(inside.get(j).getEvaluateQuantity());
						cell.setCellStyle(cellStyle);
					}
					if(null != inside.get(j).getUnitPrice()){
						Cell cell=childRowIn.createCell(6);
						cell5=cell.getAddress().toString();
						cell.setCellValue(inside.get(j).getUnitPrice().doubleValue());
						cell.setCellStyle(cellStyle);
					}
					if (null != inside.get(j).getSettleUnitPrice()) {
						Cell cell =childRowIn.createCell(7);
						cell6=cell.getAddress().toString();
						cell.setCellValue(inside.get(j).getSettleUnitPrice());
						cell.setCellStyle(cellStyle);
					}
					if(null != inside.get(j).getEvaluateUnitPrice() && null != inside.get(j).getCtAdd()){
						Cell cell=childRowIn.createCell(8);
						cell.setCellFormula("HYPERLINK("+'"'+"#"+'"'+"&"+ '"' + inside.get(j).getCtAdd()+'"'+','+inside.get(j).getCtAdd()+')');
						cell.setCellStyle(CellStyleHyperLink);
					}
					if (null != inside.get(j).getWorkAmount() && null != inside.get(j).getUnitPrice()) {
						Cell cell=childRowIn.createCell(9);
						cell.setCellFormula(cell2+"*"+cell5);
						cell.setCellStyle(cellStyle);
						if(j==0 && i==0){
							addinTop9=cell.getAddress().toString();
							addinBot9=cell.getAddress().toString();
						} else if (j== (inside.size()-1) && i==(chidInside.size()-1)){
							addinBot9=cell.getAddress().toString();
						}
					}
					if (null != inside.get(j).getExecuteQuantity() && null != inside.get(j).getSettleUnitPrice()) {
						Cell cell=childRowIn.createCell(10);
						cell9=cell.getAddress().toString();
						cell.setCellFormula(cell3+"*"+cell6);
						cell.setCellStyle(cellStyle);
						if(j==0 && i==0){
							addinTop10=cell9;
							addinBot10=cell9;
						} else if (j== (inside.size()-1) && i==(chidInside.size()-1)){
							addinBot10=cell9;
						}
					}
					if (null != inside.get(j).getEvaluateQuantity() && null != inside.get(j).getEvaluateUnitPrice()) {
						Cell cell=childRowIn.createCell(11);
						cell10=cell.getAddress().toString();
						cell.setCellFormula(inside.get(j).getCtAdd() != null ? cell4+"*"+inside.get(j).getCtAdd(): null);
						cell.setCellStyle(cellStyle);
						if(j==0 && i==0){
							addinTop11=cell10;
							addinBot11=cell10;
						} else if (j== (inside.size()-1) && i==(chidInside.size()-1)){
							addinBot11=cell10;
						}
					}
					if (null != inside.get(j).getEvaluateQuantity() && null != inside.get(j).getEvaluateUnitPrice()) {
						Cell cell=childRowIn.createCell(12);
						cell.setCellFormula(cell10+"-"+cell9);
						cell.setCellStyle(cellStyle);
						if(j==0 && i==0){
							addinTop12=cell.getAddress().toString();
							addinBot12=cell.getAddress().toString();
						} else if (j== (inside.size()-1) && i==(chidInside.size()-1)){
							addinBot12=cell.getAddress().toString();
						}
					}
				}
			}
			
			childRowIn=sheet2.createRow(++rowIdSheet1);
			Cell cellChildIn0=childRowIn.createCell(0);
			cellChildIn0.setCellStyle(cellStyleBold);
			Cell cellChildIn1=childRowIn.createCell(1);
			cellChildIn1.setCellValue("Tổng cộng công việc trong hợp đồng");
			cellChildIn1.setCellStyle(cellStyleBold);
			Cell cellChildIn2=childRowIn.createCell(2);
			cellChildIn2.setCellStyle(cellStyleLeft);
			Cell cellChildIn3=childRowIn.createCell(3);
			cellChildIn3.setCellStyle(cellStyleLeft);
			Cell cellChildIn4=childRowIn.createCell(4);
			cellChildIn4.setCellStyle(cellStyleLeft);
			Cell cellChildIn5=childRowIn.createCell(5);
			cellChildIn5.setCellStyle(cellStyleLeft);
			Cell cellChildIn6=childRowIn.createCell(6);
			cellChildIn6.setCellStyle(cellStyleBold);
			Cell cellChildIn7=childRowIn.createCell(7);
			cellChildIn7.setCellStyle(cellStyleBold);
			Cell cellChildIn8=childRowIn.createCell(8);
			cellChildIn8.setCellStyle(cellStyleBold);
			Cell cellChildIn9=childRowIn.createCell(9);
			
			String sumA9 =cellChildIn9.getAddress().toString();
			cellChildIn9.setCellStyle(cellStyleBold);
			Cell cellChildIn10=childRowIn.createCell(10);
			
			String sumA10 =cellChildIn10.getAddress().toString();
			cellChildIn10.setCellStyle(cellStyleBold);
			Cell cellChildIn11=childRowIn.createCell(11);
			
			String sumA11 =cellChildIn11.getAddress().toString();
			cellChildIn11.setCellStyle(cellStyleBold);
			Cell cellChildIn12=childRowIn.createCell(12);
			
			String sumA12 =cellChildIn12.getAddress().toString();
			if(chidInside.size()>0){
				cellChildIn9.setCellFormula("SUM("+addinTop9+':'+addinBot9+')');
				cellChildIn10.setCellFormula("SUM("+addinTop10+':'+addinBot10+')');
				cellChildIn11.setCellFormula("SUM("+addinTop11+':'+addinBot11+')');
				cellChildIn12.setCellFormula("SUM("+addinTop12+':'+addinBot12+')');
			}
			cellChildIn12.setCellStyle(cellStyleBold);
			
				Row childRowOut = sheet2.createRow(++rowIdSheet1);
//				childRowOut.setRowStyle(style);
				Cell cellOut0=childRowOut.createCell(0);
				cellOut0.setCellStyle(cellStyleBold);
				cellOut0.setCellValue("B");
				Cell cellOut1=childRowOut.createCell(1);
				cellOut1.setCellStyle(cellStyleRight);
				cellOut1.setCellValue("Công việc ngoài hợp đồng");
				Cell cellOut11=childRowOut.createCell(12);
				cellOut11.setCellStyle(cellStyleLeft);
				for(int i=0;i<chidOutside.size();i++){
					childRowOut = sheet2.createRow(++rowIdSheet1);
					Cell cellChildOut0=childRowOut.createCell(0);
					cellChildOut0.setCellStyle(cellStyleBold);
					cellChildOut0.setCellValue(getRomanNumber(i+1));
					Cell cellChildOut1=childRowOut.createCell(1);
					cellChildOut1.setCellStyle(cellStyleRight);
					cellChildOut1.setCellValue(chidOutside.get(i).getItemName());
					Cell cellChildOut2=childRowOut.createCell(2);
					cellChildOut2.setCellStyle(cellStyleLeft);
					Cell cellChildOut3=childRowOut.createCell(3);
					cellChildOut3.setCellStyle(cellStyleLeft);
					Cell cellChildOut4=childRowOut.createCell(4);
					cellChildOut4.setCellStyle(cellStyleLeft);
					Cell cellChildOut5=childRowOut.createCell(5);
					cellChildOut5.setCellStyle(cellStyleLeft);
					Cell cellChildOut6=childRowOut.createCell(6);
					cellChildOut6.setCellStyle(cellStyleLeft);
					Cell cellChildOut7=childRowOut.createCell(7);
					cellChildOut7.setCellStyle(cellStyleLeft);
					Cell cellChildOut8=childRowOut.createCell(8);
					cellChildOut8.setCellStyle(cellStyleLeft);
					Cell cellChildOut9=childRowOut.createCell(9);
					cellChildOut9.setCellStyle(cellStyleLeft);
					Cell cellChildOut10=childRowOut.createCell(10);
					cellChildOut10.setCellStyle(cellStyleLeft);
					Cell cellChildOut11=childRowOut.createCell(11);
					cellChildOut11.setCellStyle(cellStyleLeft);
					Cell cellChildOut12=childRowOut.createCell(12);
					cellChildOut12.setCellStyle(cellStyleLeft);
					sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 1, 12));
					List<EstimatesWorkItemsDTO> outside=chidOutside.get(i).getEstimatesWorkItemsDTO();
				
					for(int j = 0; j < outside.size(); j++){
						childRowOut = sheet2.createRow(++rowIdSheet1);
						Cell cellSTT=childRowOut.createCell(0);
						cellSTT.setCellValue(j+1);
						cellSTT.setCellStyle(cellStyle);
						if (StringUtils.isNotEmpty(outside.get(j).getWorkItemName())) {
							Cell cell=childRowOut.createCell(1);
							cell.setCellValue(outside.get(j).getWorkItemName());
							cell.setCellStyle(cellStyle);
						}
						if(null != outside.get(j).getUnit()){
							Cell cell=childRowOut.createCell(2);
							cell.setCellValue(outside.get(j).getUnit());
							cell.setCellStyle(cellStyle);
						}
						if(null != outside.get(j).getWorkAmount()){
							Cell cell=childRowOut.createCell(3);
							cell2=cell.getAddress().toString();
							cell.setCellValue(outside.get(j).getWorkAmount().doubleValue());
							cell.setCellStyle(cellStyle);
						}
						if(null != outside.get(j).getExecuteQuantity()){
							Cell cell=childRowOut.createCell(4);
							cell3=cell.getAddress().toString();
							cell.setCellValue(outside.get(j).getExecuteQuantity());
							cell.setCellStyle(cellStyle);
						}
						if (null != outside.get(j).getEvaluateQuantity()) {
							Cell cell =childRowOut.createCell(5);
							cell4=cell.getAddress().toString();
							cell.setCellValue(outside.get(j).getEvaluateQuantity());
							cell.setCellStyle(cellStyle);
						}
						if(null != outside.get(j).getUnitPrice()){
							Cell cell=childRowOut.createCell(6);
							cell5=cell.getAddress().toString();
							cell.setCellValue(outside.get(j).getUnitPrice().doubleValue());
							cell.setCellStyle(cellStyle);
						}
						if (null != outside.get(j).getSettleUnitPrice()) {
							Cell cell =childRowOut.createCell(7);
							cell6=cell.getAddress().toString();
							cell.setCellValue(outside.get(j).getSettleUnitPrice());
							cell.setCellStyle(cellStyle);
						}
						if (null != outside.get(j).getCtAdd() && null != outside.get(j).getEvaluateUnitPrice()) {
							Cell cell=childRowOut.createCell(8);
							cell.setCellFormula("HYPERLINK("+'"'+"#"+'"'+"&"+ '"' + outside.get(j).getCtAdd()+'"'+','+outside.get(j).getCtAdd()+')');
							cell.setCellStyle(CellStyleHyperLink);
						}
						if (null != outside.get(j).getWorkAmount() && null != outside.get(j).getUnitPrice()) {
							Cell cell=childRowOut.createCell(9);
							cell.setCellFormula(cell2+"*"+cell5);
							cell.setCellStyle(cellStyle);
							
							if(j==0 && i==0){
								addinTop9=cell.getAddress().toString();
								addinBot9=cell.getAddress().toString();
							} else if (j== (outside.size()-1)){
								addinBot9=cell.getAddress().toString();
							}
						}
						if (null != outside.get(j).getExecuteQuantity() && null != outside.get(j).getSettleUnitPrice()) {
							Cell cell=childRowOut.createCell(10);
							cell9=cell.getAddress().toString();
							cell.setCellFormula(cell3+"*"+cell6);
							cell.setCellStyle(cellStyle);
							
							if(j==0 && i==0){
								addinTop10=cell.getAddress().toString();
								addinBot10=cell.getAddress().toString();
							} else if (j== (outside.size()-1) && i==(chidOutside.size()-1)){
								addinBot10=cell.getAddress().toString();
							}
						}
						if (null != outside.get(j).getEvaluateQuantity() && null != outside.get(j).getEvaluateUnitPrice()) {
							Cell cell=childRowOut.createCell(11);
							cell10=cell.getAddress().toString();
							cell.setCellFormula(outside.get(j).getCtAdd()!=null? cell4+"*"+outside.get(j).getCtAdd():null);
							cell.setCellStyle(cellStyle);
							
							if(j==0 && i==0){
								addinTop11=cell.getAddress().toString();
								addinBot11=cell.getAddress().toString();
							} else if (j== (outside.size()-1) && i==(chidOutside.size()-1)){
								addinBot11=cell.getAddress().toString();
							}
						}
						if (null != outside.get(j).getEvaluateQuantity() && null != outside.get(j).getEvaluateUnitPrice()) {
							Cell cell=childRowOut.createCell(12);
							cell.setCellFormula(cell10+"-"+cell9);
							cell.setCellStyle(cellStyle);
							
							if(j==0 && i==0){
								addinTop12=cell.getAddress().toString();
								addinBot12=cell.getAddress().toString();
							} else if (j== (outside.size()-1) && i==(chidOutside.size()-1)){
								addinBot12=cell.getAddress().toString();
							}
						}
					}
				}
				
				childRowOut = sheet2.createRow(++rowIdSheet1);
				Cell cellChildOut0=childRowOut.createCell(0);
				cellChildOut0.setCellStyle(cellStyleBold);
				Cell cellChildOut1=childRowOut.createCell(1);
				cellChildOut1.setCellStyle(cellStyleBold);
				cellChildOut1.setCellValue("Tổng cộng công việc ngoài hợp đồng");
				Cell cellChildOut2=childRowOut.createCell(2);
				cellChildOut2.setCellStyle(cellStyleLeft);
				Cell cellChildOut3=childRowOut.createCell(3);
				cellChildOut3.setCellStyle(cellStyleLeft);
				Cell cellChildOut4=childRowOut.createCell(4);
				cellChildOut4.setCellStyle(cellStyleLeft);
				Cell cellChildOut5=childRowOut.createCell(5);
				cellChildOut5.setCellStyle(cellStyleLeft);
				Cell cellChildOut6=childRowOut.createCell(6);
				cellChildOut6.setCellStyle(cellStyleBold);
				Cell cellChildOut7=childRowOut.createCell(7);
				cellChildOut7.setCellStyle(cellStyleBold);
				Cell cellChildOut8=childRowOut.createCell(8);
				cellChildOut8.setCellStyle(cellStyleBold);
				Cell cellChildOut9=childRowOut.createCell(9);
//				cellChildOut9.setCellFormula("SUM("+addinTop9+':'+addinBot9+')');
				 String sumB9 =cellChildOut9.getAddress().toString();
				cellChildOut9.setCellStyle(cellStyleBold);
				Cell cellChildOut10=childRowOut.createCell(10);
//				cellChildOut10.setCellFormula("SUM("+addinTop10+':'+addinBot10+')');
				String sumB10 =cellChildOut10.getAddress().toString();
				cellChildOut10.setCellStyle(cellStyleBold);
				Cell cellChildOut11=childRowOut.createCell(11);
//				cellChildOut11.setCellFormula("SUM("+addinTop11+':'+addinBot11+')');
				String sumB11 =cellChildOut11.getAddress().toString();
				cellChildOut11.setCellStyle(cellStyleBold);
				Cell cellChildOut12=childRowOut.createCell(12);
//				cellChildOut12.setCellFormula("SUM("+addinTop12+':'+addinBot12+')');
				String sumB12 =cellChildOut12.getAddress().toString();
				cellChildOut12.setCellStyle(cellStyleBold);
				if(chidOutside.size()>0){
					cellChildOut9.setCellFormula("SUM("+addinTop9+':'+addinBot9+')');
					cellChildOut10.setCellFormula("SUM("+addinTop10+':'+addinBot10+')');
					cellChildOut11.setCellFormula("SUM("+addinTop11+':'+addinBot11+')');
					cellChildOut12.setCellFormula("SUM("+addinTop12+':'+addinBot12+')');
				} 
				
				
				childRowOut = sheet2.createRow(++rowIdSheet1);
				Cell cellSum0=childRowOut.createCell(0);
				cellSum0.setCellStyle(cellStyleBold);
				Cell cellSum1=childRowOut.createCell(1);
				cellSum1.setCellStyle(cellStyleBold);
				cellSum1.setCellValue("Tổng cộng công việc:A+B");
				Cell cellSum2=childRowOut.createCell(2);
				cellSum2.setCellStyle(cellStyleLeft);
				Cell cellSum3=childRowOut.createCell(3);
				cellSum3.setCellStyle(cellStyleLeft);
				Cell cellSum4=childRowOut.createCell(4);
				cellSum4.setCellStyle(cellStyleLeft);
				Cell cellSum5=childRowOut.createCell(5);
				cellSum5.setCellStyle(cellStyleLeft);
				Cell cellSum6=childRowOut.createCell(6);
				cellSum6.setCellStyle(cellStyleBold);
				Cell cellSum7=childRowOut.createCell(7);
				cellSum7.setCellStyle(cellStyleBold);
				Cell cellSum8=childRowOut.createCell(8);
				cellSum8.setCellStyle(cellStyleBold);
				Cell cellSum9=childRowOut.createCell(9);
				cellSum9.setCellFormula("SUM("+sumA9+','+sumB9+')');
				cellSum9.setCellStyle(cellStyleBold);
				Cell cellSum10=childRowOut.createCell(10);
				cellSum10.setCellFormula("SUM("+sumA10+','+sumB10+')');
				cellSum10.setCellStyle(cellStyleBold);
				Cell cellSum11=childRowOut.createCell(11);
				cellSum11.setCellFormula("SUM("+sumA11+','+sumB11+')');
				cellSum11.setCellStyle(cellStyleBold);
				String cellsummoney=cellSum11.getAddress().toString();
				Cell cellSum12=childRowOut.createCell(12);
				cellSum12.setCellFormula("SUM("+sumA12+','+sumB12+')');
				cellSum12.setCellStyle(cellStyleBold);
				
				
					 Calendar cal = Calendar.getInstance();
					int day=cal.getTime().getDate();
					 String month = ((cal.getTime().getMonth() + 1) < 10) ? "0" + (cal.getTime().getMonth() + 1) : String.valueOf((cal.getTime().getMonth() + 1));
					String year= String.valueOf(cal.getTime().getYear()).substring(1);
					Row rowMoney = sheet2.createRow(++rowIdSheet1);
					Cell celltitileMoney = rowMoney.createCell(1);
					celltitileMoney.setCellValue("Bằng chữ:");
					celltitileMoney.setCellStyle(cellstyleSP4);
					Cell cellmoney= rowMoney.createCell(2);
					cellmoney.setCellFormula("VND("+cellsummoney+")");
					cellmoney.setCellStyle(cellstyleSP3);
					sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 2, 12));
					
					++rowIdSheet1;
			Row rowdate=sheet2.createRow(++rowIdSheet1);
			Cell celldate=rowdate.createCell(8);
			celldate.setCellValue(listdto.get(0).getProvinceName()!=null ? listdto.get(0).getProvinceName()+","+"Ngày "+day+" tháng "+month+" năm 20"+year:"Hà Nội"+","+"Ngày "+day+" tháng "+month+" năm 20"+year);
			celldate.setCellStyle(cellstyleSP3);
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 8, 12));
				++rowIdSheet1;
			Row rowfirst = sheet2.createRow(++rowIdSheet1);
			Cell cellfr=rowfirst.createCell(0);
			cellfr.setCellValue("CHỦ ĐẦU TƯ                                                                                                                                                                   NHÀ THẦU THI CÔNG												");
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 0, 12));
			cellfr.setCellStyle(cellstyleSP);
			
			
			Row rowSe = sheet2.createRow(++rowIdSheet1);
			Cell cellSe=rowSe.createCell(0);
			cellSe.setCellValue("ĐẠI DIỆN CHỦ ĐẦU TƯ                NGƯỜI KIỂM TRA                                  NGƯỜI THẨM                                            ĐẠI DIỆN NHÀ THẦU                                             NGƯỜI LẬP												");
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 0, 12));
			cellSe.setCellStyle(cellstyleSP);
			
			Row rowth = sheet2.createRow(++rowIdSheet1);
			Cell cellth=rowth.createCell(0);
			cellth.setCellValue("(Ký, ghi rõ họ tên, đóng dấu)             (Ký, ghi rõ họ và tên)                      (Ký, ghi rõ họ và tên)                              (Ký, ghi rõ họ và tên, đóng dấu)                                   (Ký, ghi rõ họ và tên)												");
			sheet2.addMergedRegion(new CellRangeAddress(rowIdSheet1, rowIdSheet1, 0, 12));
			cellth.setCellStyle(cellstyleSP);
			
			
			file.close();
			File out = new File(folder2Upload + File.separatorChar + "BM-QT-02.xlsx");
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
		String filename= UEncrypt.encryptFileUploadPath("BM-QT-02.xlsx");
		return Response.ok(Collections.singletonMap("fileName",filename)).build();

	}

	@Override
	public Response importCTHD(EstimatesWorkItemsDTO dto) throws Exception {
		try {
			String filename= UEncrypt.decryptFileUploadPath(dto.getPathFile());
			List<EstimatesWorkItemsBO> parentLst = detailSettlementEvaluateBusinessImpl.importCT(filename, dto.getConstructionId());
			System.out.println(parentLst.get(0).getWorkItemName() +"-----------------------");
			detailSettlementEvaluateBusinessImpl.importCTHD(parentLst, dto.getConstructionId());
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response exPortCTHD(EstimatesWorkItemsDTO obj) throws Exception {
		String path = 	UEncrypt.encryptFileUploadPath("CTHD_template.xlsx");
		return Response.ok(Collections.singletonMap("fileName", path)).build();
	}

	@Override
	public Response exPortCVHD(EstimatesWorkItemsDTO obj) throws Exception {
	String path = 	UEncrypt.encryptFileUploadPath("ImportCVHD_Temp.xlsx");
		return Response.ok(Collections.singletonMap("fileName", path)).build();
	}

	@Override
	public Response imPortCVHD(EstimatesWorkItemsDTO dto) throws Exception {
		try {
	
			String filename= UEncrypt.decryptFileUploadPath(dto.getPathFile());
			
			Long constrId = dto.getConstructionId();
			List<EstimatesItemsChildDTO> parentLst = detailSettlementEvaluateBusinessImpl.getdataCVHD(filename, constrId);
			detailSettlementEvaluateBusinessImpl.importCVHD(parentLst, constrId);
			return Response.ok(Response.Status.CREATED).build();
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response getsendPersonName(Long id) {
		return Response.ok(detailSettlementEvaluateBusinessImpl.getsendPerson(id)).build();
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
	public Response updateIsActive(Long detailSettlementEvaluateId) throws Exception {
		// TODO Auto-generated method stub
		detailSettlementEvaluateBusinessImpl.updateIsActive(detailSettlementEvaluateId);
		return null;
	}
	
}
