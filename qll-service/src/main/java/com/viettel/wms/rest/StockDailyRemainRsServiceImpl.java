/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.wms.business.StockDailyRemainBusinessImpl;
import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.dto.StockDailyRemainDTO;

/**
 *
 * @author HungLQ9
 */
public class StockDailyRemainRsServiceImpl implements StockDailyRemainRsService {
	@Value("${folder_upload}")
	private String folder2Upload;
	@Autowired
	StockDailyRemainBusinessImpl stockDailyRemainBusinessImpl;

	static Logger LOGGER = LoggerFactory.getLogger(StockDailyRemainRsServiceImpl.class);

	@Override
	public Response getStockDailyRemain() {
		List<StockDailyRemainDTO> ls = stockDailyRemainBusinessImpl.getAll();
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
	public Response getStockDailyRemainById(Long id) {
		StockDailyRemainDTO obj = (StockDailyRemainDTO) stockDailyRemainBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateStockDailyRemain(StockDailyRemainDTO obj) {
		Long id = stockDailyRemainBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addStockDailyRemain(StockDailyRemainDTO obj) {
		Long id = stockDailyRemainBusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response deleteStockDailyRemain(Long id) {
		StockDailyRemainDTO obj = (StockDailyRemainDTO) stockDailyRemainBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			stockDailyRemainBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response doSearch(StockDailyRemainDTO obj) {
		DataListDTO data = stockDailyRemainBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response exportCard(CommonDTO obj) throws Exception {
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "TheKho.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			int count=0;
			for (StockDailyRemainDTO dto : obj.getListStockDailyRemain()) {
				
				StockDailyRemainDTO objRemain = stockDailyRemainBusinessImpl.getAmount(obj.getStartDate(), dto);
				if (objRemain != null) {
					if(obj.getEndDate()==null) obj.setEndDate(new Date());
					List<StockDailyRemainDTO> ls = stockDailyRemainBusinessImpl.getListForExport(dto,
							objRemain.getAmount().longValue(), obj.getStartDate(), obj.getEndDate());
					
				XSSFSheet	 sheet = workbook.createSheet(objRemain.getGoodsCode() + "-" + objRemain.getGoodsStateName()+"-"+objRemain.getStockCode());
				count++;	
					
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
					
					
					XSSFFont fontTitle = workbook.createFont();
					fontTitle.setFontHeightInPoints((short) 14);
					fontTitle.setFontName("Times New Roman");
					fontTitle.setColor(IndexedColors.BLACK.getIndex());
					fontTitle.setBold(true);
					fontTitle.setItalic(false);

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
					cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
//					cellStyle.setWrapText(true);

					CellStyle cellStyleBackground = workbook.createCellStyle();
					cellStyleBackground.setBorderBottom(BorderStyle.THIN);
					cellStyleBackground.setBorderTop(BorderStyle.THIN);
					cellStyleBackground.setBorderLeft(BorderStyle.THIN);
					cellStyleBackground.setBorderRight(BorderStyle.THIN);
					cellStyleBackground.setFont(fontBoldBlack);
					cellStyleBackground.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
					cellStyleBackground.setAlignment(CellStyle.ALIGN_CENTER);
					cellStyleBackground.setWrapText(true);

					CellStyle cellStyleBold = workbook.createCellStyle();
//					cellStyleBold.setBorderBottom(BorderStyle.THIN);
//					cellStyleBold.setBorderTop(BorderStyle.THIN);
//					cellStyleBold.setBorderLeft(BorderStyle.THIN);
//					cellStyleBold.setBorderRight(BorderStyle.THIN);
					cellStyleBold.setFont(fontTitle);
					cellStyleBold.setAlignment(CellStyle.ALIGN_CENTER);
					cellStyleBackground.setWrapText(true);

					CellStyle cellStyleRight = workbook.createCellStyle();
					cellStyleRight.setBorderBottom(BorderStyle.THIN);
					cellStyleRight.setBorderTop(BorderStyle.THIN);
					cellStyleRight.setBorderLeft(BorderStyle.THIN);
					cellStyleRight.setBorderRight(BorderStyle.THIN);
					cellStyleRight.setFont(defaultFont);
					cellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);

					CellStyle cellStyleLeft = workbook.createCellStyle();
					cellStyleLeft.setFont(defaultFont);
					cellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);
					cellStyleLeft.setWrapText(true);
					
					
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

					int rowIndex = 0;
					/* Create header */
					Row row0 = sheet.createRow(rowIndex);
					Cell cell0_3 = row0.createCell(3);
					cell0_3.setCellValue("Thẻ Kho");
					sheet.addMergedRegion(new CellRangeAddress(rowIndex, 1, 3, 4));
					cell0_3.setCellStyle(cellStyleBold);

					Row row2 = sheet.createRow(++rowIndex);
					Cell cell2_3 = row2.createCell(3);
					cell2_3.setCellStyle(cellStyleLeft);
					cell2_3.setCellValue("Kho hàng");
					Cell cell2_4 = row2.createCell(4);
					cell2_3.setCellStyle(cellStyleLeft);
					cell2_3.setCellValue(objRemain.getStockName());

					Row row3 = sheet.createRow(++rowIndex);
					Cell cell3_3 = row3.createCell(3);
					cell3_3.setCellStyle(cellStyleLeft);
					cell3_3.setCellValue("Mặt hàng");
					Cell cell3_4 = row3.createCell(4);
					cell3_4.setCellStyle(cellStyleLeft);
					cell3_4.setCellValue(objRemain.getGoodsCode() + "-" + objRemain.getGoodsName());

					Row row4 = sheet.createRow(++rowIndex);
					Cell cell4_3 = row4.createCell(3);
					cell4_3.setCellStyle(cellStyleLeft);
					cell4_3.setCellValue("Trạng thái hàng");
					Cell cell4_4 = row4.createCell(4);
					cell4_4.setCellStyle(cellStyleLeft);
					cell4_4.setCellValue(objRemain.getGoodsStateName());

					Row row5 = sheet.createRow(++rowIndex);
					Cell cell5_3 = row5.createCell(3);
					cell5_3.setCellValue("Từ ngày: " + (new SimpleDateFormat("dd/MM/yyyy").format(obj.getStartDate())) + " " + "Đến ngày: "
							+ (obj.getEndDate() != null ? (new SimpleDateFormat("dd/MM/yyyy").format(obj.getEndDate())):(new SimpleDateFormat("dd/MM/yyyy").format(new Date()))));
					sheet.addMergedRegion(new CellRangeAddress(rowIndex, ++rowIndex, 3, 4));
					cell5_3.setCellStyle(cellStyleLeft);

					Cell cell5_6 = row5.createCell(6);
					cell5_6.setCellStyle(cellStyleLeft);
					cell5_6.setCellValue("Ngày tạo");

					Cell cell5_7 = row5.createCell(7);
					cell5_7.setCellValue((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())));
					cell5_7.setCellStyle(cellStyleLeft);

					/* Create header table */

					Row row7 = sheet.createRow(7);
					Row row8 = sheet.createRow(8);
					Row row9 = sheet.createRow(9);

					Cell cell7_0 = row7.createCell(0);
					cell7_0.setCellValue("STT");
					sheet.addMergedRegion(new CellRangeAddress(7, 8, 0, 0));
					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 0, 0), sheet, workbook);
					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 0, 0), sheet, workbook);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 0, 0), sheet, workbook);
					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 0, 0), sheet, workbook);
					cell7_0.setCellStyle(cellStyleBackground);

					Cell cell7_1 = row7.createCell(1);
					cell7_1.setCellValue("Người tạo");
					sheet.addMergedRegion(new CellRangeAddress(7, 8, 1, 1));
					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 1, 1), sheet, workbook);
					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 1, 1), sheet, workbook);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 1, 1), sheet, workbook);
					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 1, 1), sheet, workbook);
					cell7_1.setCellStyle(cellStyleBackground);

					Cell cell7_2 = row7.createCell(2);
					cell7_2.setCellValue("Ngày nhập/xuất");
					sheet.addMergedRegion(new CellRangeAddress(7, 8, 2, 2));
					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 2, 2), sheet, workbook);
					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 2, 2), sheet, workbook);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 2, 2), sheet, workbook);
					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 2, 2), sheet, workbook);
					cell7_2.setCellStyle(cellStyleBackground);

					Cell cell7_3 = row7.createCell(3);
					cell7_3.setCellValue("Mã nhập/xuất");
					sheet.addMergedRegion(new CellRangeAddress(7, 7, 3, 4));
					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(7, 7, 3, 4), sheet, workbook);
					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(7, 7, 3, 4), sheet, workbook);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(7, 7, 3, 4), sheet, workbook);
					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(7, 7, 3, 4), sheet, workbook);
					cell7_3.setCellStyle(cellStyleBackground);

					Cell cell8_3 = row8.createCell(3);
					cell8_3.setCellValue("Nhập");
					cell8_3.setCellStyle(cellStyleBackground);

					Cell cell8_4 = row8.createCell(4);
					cell8_4.setCellStyle(cellStyleBackground);
					cell8_4.setCellValue("Xuất");

					Cell cell7_5 = row7.createCell(5);
					cell7_5.setCellValue("Ghi chú");
					sheet.addMergedRegion(new CellRangeAddress(7, 8, 5, 5));
					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 5, 5), sheet, workbook);
					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 5, 5), sheet, workbook);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 5, 5), sheet, workbook);
					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(7, 8, 5, 5), sheet, workbook);
					cell7_5.setCellStyle(cellStyleBackground);

					Cell cell7_6 = row7.createCell(6);
					cell7_6.setCellValue("Số lượng");
					sheet.addMergedRegion(new CellRangeAddress(7, 7, 6, 8));
					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(7, 7, 6, 8), sheet, workbook);
					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(7, 7, 6, 8), sheet, workbook);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(7, 7, 6, 8), sheet, workbook);
					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(7, 7, 6, 8), sheet, workbook);
					cell7_6.setCellStyle(cellStyleBackground);

					Cell cell8_6 = row8.createCell(6);
					cell8_6.setCellStyle(cellStyleBackground);
					cell8_6.setCellValue("Nhập");

					Cell cell8_7 = row8.createCell(7);
					cell8_7.setCellStyle(cellStyleBackground);
					cell8_7.setCellValue("Xuất");

					Cell cell8_8 = row8.createCell(8);
					cell8_8.setCellStyle(cellStyleBackground);
					cell8_8.setCellValue("Tồn");

					Cell cell9_0_9 = row9.createCell(0);
					cell9_0_9.setCellValue("Tổng tồn đầu kỳ:");
					sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 7));
					RegionUtil.setBorderTop(CellStyle.BORDER_THIN, new CellRangeAddress(9, 9, 0, 7), sheet, workbook);
					RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, new CellRangeAddress(9, 9, 0, 7), sheet, workbook);
					RegionUtil.setBorderRight(CellStyle.BORDER_THIN, new CellRangeAddress(9, 9, 0, 7), sheet, workbook);
					RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, new CellRangeAddress(9, 9, 0, 7), sheet, workbook);
					cell9_0_9.setCellStyle(cellStyleRight);
					
					
					Cell Cell9_8 = row9.createCell(8);
					Cell9_8.setCellStyle(cellStyleRight);
					Cell9_8.setCellValue(objRemain.getAmount());
					rowIndex = 9;

					sheet.autoSizeColumn(0);
					
					
					int stt = 0;
					/* gendata Table */
					for (StockDailyRemainDTO wi : ls) {
						Row rowData = sheet.createRow(++rowIndex);
						Cell cell0 = rowData.createCell(0);
						cell0.setCellStyle(cellStyle);
						cell0.setCellValue(++stt);

						Cell cell1 = rowData.createCell(1);
						cell1.setCellStyle(cellStyle);
						cell1.setCellValue(wi.getCreatedByName());

						Cell cell2 = rowData.createCell(2);
						cell2.setCellStyle(cellStyle);
						cell2.setCellValue(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wi.getRealIeTransDate()));

						Cell cell3 = rowData.createCell(3);
						cell3.setCellStyle(cellStyle);
						cell3.setCellValue(wi.getImCode());

						Cell cell4 = rowData.createCell(4);
						cell4.setCellStyle(cellStyle);
						cell4.setCellValue(wi.getExCode());

						Cell cell5 = rowData.createCell(5);
						cell5.setCellStyle(cellStyle);
						cell5.setCellValue(wi.getDescription());

						Cell cell6 = rowData.createCell(6);
						cell6.setCellStyle(cellStyle);
						cell6.setCellValue(wi.getImAmount());

						Cell cell7 = rowData.createCell(7);
						cell7.setCellStyle(cellStyle);
						cell7.setCellValue(wi.getExAmount());

						Cell cell8 = rowData.createCell(8);
						cell8.setCellStyle(cellStyle);
						cell8.setCellValue(wi.getAmount());

					}
					
					sheet.autoSizeColumn(1);
					sheet.autoSizeColumn(2);
					sheet.autoSizeColumn(3);
					sheet.autoSizeColumn(4);
					sheet.autoSizeColumn(5);
					sheet.autoSizeColumn(6);
					sheet.autoSizeColumn(7);
					sheet.autoSizeColumn(8);
					sheet.autoSizeColumn(9);
					

				}
			}
			
			if(count==0){
				return Response.ok(Collections.singletonMap("error", "Không có dữ liệu khớp để xuất thẻ kho!")).build();	
			}
			
			workbook.removeSheetAt(0);
			file.close();
			File out = new File(folder2Upload + File.separatorChar + "TheKho.xlsx");
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
		String path = UEncrypt.encryptFileUploadPath("TheKho.xlsx");
		return Response.ok(Collections.singletonMap("fileName", path)).build();

	}
}
