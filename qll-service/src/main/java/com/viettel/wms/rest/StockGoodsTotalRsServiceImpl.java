///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Collections;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//
//import com.google.common.collect.Lists;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.business.StockGoodsSerialBusinessImpl;
//import com.viettel.wms.business.StockGoodsTotalBusinessImpl;
//import com.viettel.wms.dto.StockGoodsSerialDTO;
//import com.viettel.wms.dto.StockGoodsTotalDTO;
//
///**
// *
// * @author HungLQ9
// */
//public class StockGoodsTotalRsServiceImpl implements StockGoodsTotalRsService {
//	@Value("${folder_upload}")
//	private String folder2Upload;
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
//	@Autowired
//	StockGoodsTotalBusinessImpl stockGoodsTotalBusinessImpl;
//	
//	@Autowired
//	StockGoodsSerialBusinessImpl stockGoodsSerialBusinessImpl;
//	
//	@Context
//	HttpServletRequest request;
//
//	static Logger LOGGER = LoggerFactory
//			.getLogger(StockGoodsTotalRsServiceImpl.class);
//
//	@Override
//	public Response getStockGoodsTotal() {
//		List<StockGoodsTotalDTO> ls = stockGoodsTotalBusinessImpl.getAll();
//		if (ls == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			DataListDTO data = new DataListDTO();
//			data.setData(ls);
//			data.setTotal(ls.size());
//			data.setSize(ls.size());
//			data.setStart(1);
//			return Response.ok(data).build();
//		}
//	}
//
//	@Override
//	public Response getStockGoodsTotalById(Long id) {
//		StockGoodsTotalDTO obj = (StockGoodsTotalDTO) stockGoodsTotalBusinessImpl
//				.getOneById(id);
//		if (obj == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(obj).build();
//		}
//	}
//
//	@Override
//	public Response updateStockGoodsTotal(StockGoodsTotalDTO obj) {
//		Long id = stockGoodsTotalBusinessImpl.update(obj);
//		if (id == 0l) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok().build();
//		}
//
//	}
//
//	@Override
//	public Response addStockGoodsTotal(StockGoodsTotalDTO obj) {
//		Long id = stockGoodsTotalBusinessImpl.save(obj);
//		if (id == 0l) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(Response.Status.CREATED).build();
//		}
//	}
//
//	@Override
//	public Response deleteStockGoodsTotal(Long id) {
//		StockGoodsTotalDTO obj = (StockGoodsTotalDTO) stockGoodsTotalBusinessImpl
//				.getOneById(id);
//		if (obj == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			stockGoodsTotalBusinessImpl.delete(obj);
//			return Response.ok(Response.Status.NO_CONTENT).build();
//		}
//	}
//
//	// tim kiem bao cao ton kho tuannb
//	@Override
//	public Response doSearch(StockGoodsTotalDTO obj) throws Exception {
//		DataListDTO data;
//		try {
//			data = stockGoodsTotalBusinessImpl.doSearch(obj, request);
//			return Response.ok(data).build();
//		} catch (IllegalArgumentException e) {
//			return Response.ok()
//					.entity(Collections.singletonMap("error", e.getMessage()))
//					.build();
//		}
//
//	}
//
//	// end tuannb
//
//	public Response doSearchFindSerial(StockGoodsSerialDTO obj) {
//		// TODO Auto-generated method stub
//		DataListDTO data= stockGoodsSerialBusinessImpl.doSearchFindSerial(obj);
//        if (data == null) {
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } else {
//        	return Response.ok(data).build();
//        }
//	}
//	
//	@Override
//	public Response doSearchTotal(StockGoodsTotalDTO obj) {
//		DataListDTO data = stockGoodsTotalBusinessImpl.doSearchTotal(obj);
//		if (data == null) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			return Response.ok(data).build();
//		}
//
//	}
//
//	@Override
//	public Response doSearchStockGood(StockGoodsTotalDTO obj) {
//		DataListDTO data = stockGoodsTotalBusinessImpl.doSearchStockGood(obj);
//		return Response.ok(data).build();
//	}
//
//	public Response exportExcel(StockGoodsTotalDTO obj) throws Exception {
//		try {
//			List<StockGoodsTotalDTO> lsParent = Lists.newArrayList();
//			ClassLoader classloader = Thread.currentThread()
//					.getContextClassLoader();
//			String filePath = classloader.getResource("../" + "doc-template")
//					.getPath();
//			InputStream file = new BufferedInputStream(new FileInputStream(
//					filePath + "TheKho.xlsx"));
//			XSSFWorkbook workbook = new XSSFWorkbook(file);
//
//			// int count=parentLst.size()+5;
//			// sheet2.shiftRows(16, 18, count, true,true);
//			// set font
//			// CellStyle style=null;
//
//			XSSFFont defaultFont = workbook.createFont();
//			defaultFont.setFontHeightInPoints((short) 10);
//			defaultFont.setFontName("Times New Roman");
//			defaultFont.setColor(IndexedColors.BLACK.getIndex());
//			defaultFont.setBold(false);
//			defaultFont.setItalic(false);
//
//			XSSFFont font = workbook.createFont();
//			font.setFontHeightInPoints((short) 10);
//			font.setFontName("Times New Roman");
//			font.setColor(IndexedColors.WHITE.getIndex());
//			font.setBold(true);
//			font.setItalic(false);
//
//			XSSFFont fontBoldBlack = workbook.createFont();
//			fontBoldBlack.setFontHeightInPoints((short) 10);
//			fontBoldBlack.setFontName("Times New Roman");
//			fontBoldBlack.setColor(IndexedColors.BLACK.getIndex());
//			fontBoldBlack.setBold(true);
//			fontBoldBlack.setItalic(false);
//
//			XSSFFont fontHyperLink = workbook.createFont();
//			fontHyperLink.setFontHeightInPoints((short) 10);
//			fontHyperLink.setFontName("Times New Roman");
//			fontHyperLink.setColor(IndexedColors.SKY_BLUE.getIndex());
//			fontHyperLink.setBold(false);
//			fontHyperLink.setItalic(false);
//			fontHyperLink.setUnderline(Font.U_SINGLE);
//
//			XSSFFont fontBold = workbook.createFont();
//			fontBold.setFontHeightInPoints((short) 12);
//			fontBold.setFontName("Times New Roman");
//			fontBold.setColor(IndexedColors.BLACK.getIndex());
//			fontBold.setBold(true);
//			fontBold.setItalic(false);
//
//			XSSFFont fontItalic = workbook.createFont();
//			fontItalic.setFontHeightInPoints((short) 12);
//			fontItalic.setFontName("Times New Roman");
//			fontItalic.setColor(IndexedColors.BLACK.getIndex());
//			fontItalic.setBold(false);
//			fontItalic.setItalic(true);
//
//			CellStyle cellStyle = workbook.createCellStyle();
//			cellStyle.setBorderBottom(BorderStyle.THIN);
//			cellStyle.setBorderTop(BorderStyle.THIN);
//			cellStyle.setBorderLeft(BorderStyle.THIN);
//			cellStyle.setBorderRight(BorderStyle.THIN);
//			cellStyle.setFont(defaultFont);
//
//			CellStyle cellStyleBold = workbook.createCellStyle();
//			cellStyleBold.setBorderBottom(BorderStyle.THIN);
//			cellStyleBold.setBorderTop(BorderStyle.THIN);
//			cellStyleBold.setBorderLeft(BorderStyle.THIN);
//			cellStyleBold.setBorderRight(BorderStyle.THIN);
//			cellStyleBold.setFont(fontBoldBlack);
//
//			CellStyle CellStyleHyperLink = workbook.createCellStyle();
//			CellStyleHyperLink.setBorderBottom(BorderStyle.THIN);
//			CellStyleHyperLink.setBorderTop(BorderStyle.THIN);
//			CellStyleHyperLink.setBorderLeft(BorderStyle.THIN);
//			CellStyleHyperLink.setBorderRight(BorderStyle.THIN);
//			CellStyleHyperLink.setFont(fontHyperLink);
//
//			CellStyle cellStyleRight = workbook.createCellStyle();
//			cellStyleRight.setBorderBottom(BorderStyle.THIN);
//			cellStyleRight.setBorderTop(BorderStyle.THIN);
//			cellStyleRight.setBorderLeft(BorderStyle.THIN);
//			cellStyleRight.setFont(fontBoldBlack);
//
//			CellStyle cellStyleLeft = workbook.createCellStyle();
//			cellStyleLeft.setBorderBottom(BorderStyle.THIN);
//			cellStyleLeft.setBorderTop(BorderStyle.THIN);
//			cellStyleLeft.setBorderRight(BorderStyle.THIN);
//
//			CellStyle cellstyleSP = workbook.createCellStyle();
//			cellstyleSP.setFont(fontBold);
//			cellstyleSP.setAlignment(CellStyle.ALIGN_CENTER);
//			// cellstyleSP.setWrapText(true);
//
//			CellStyle cellstyleSP2 = workbook.createCellStyle();
//			cellstyleSP2.setFont(fontBold);
//			cellstyleSP2.setAlignment(CellStyle.ALIGN_LEFT);
//			// cellstyleSP2.setWrapText(true);
//
//			CellStyle cellstyleSP3 = workbook.createCellStyle();
//			cellstyleSP3.setFont(fontItalic);
//			cellstyleSP3.setAlignment(CellStyle.ALIGN_LEFT);
//			// cellstyleSP2.setWrapText(true);
//
//			CellStyle cellstyleSP4 = workbook.createCellStyle();
//			cellstyleSP4.setFont(fontItalic);
//			cellstyleSP4.setAlignment(CellStyle.ALIGN_RIGHT);
//
//			// Add these lines
//			// Update the value of cell
//			for (StockGoodsTotalDTO parent : lsParent) {
//				Sheet sheet = workbook.createSheet(obj.getGoodsCode()
//						+ obj.getGoodsStateName());
//				int rowIdx = 0;
//				Row Row0 = sheet.createRow(rowIdx);
//
//				Cell cell0_3 = Row0.createCell(3);
//				cell0_3.setCellStyle(cellStyleBold);
//				cell0_3.setCellValue("Thẻ Kho");
//				sheet.addMergedRegion(new CellRangeAddress(rowIdx, ++rowIdx, 3,
//						4));
//				Row row1 = sheet.createRow(++rowIdx);
//				Cell cell1_3 = row1.createCell(3);
//				cell1_3.setCellStyle(cellStyleLeft);
//				cell1_3.setCellValue("Kho hàng");
//				Cell cell1_4 = row1.createCell(4);
//				cell1_4.setCellStyle(cellStyleLeft);
//				cell1_4.setCellValue("");
//
//				Row row2 = sheet.createRow(++rowIdx);
//				Cell cell2_3 = row2.createCell(3);
//				cell2_3.setCellStyle(cellStyleLeft);
//				cell2_3.setCellValue("Mặt hàng");
//				Cell cell2_4 = row2.createCell(4);
//				cell2_4.setCellStyle(cellStyleLeft);
//				cell2_4.setCellValue("");
//
//				Row row3 = sheet.createRow(++rowIdx);
//				Cell cell3_3 = row3.createCell(3);
//				cell3_3.setCellStyle(cellStyleLeft);
//				cell3_3.setCellValue("Trạng thái hàng");
//				Cell cell3_4 = row3.createCell(4);
//				cell3_4.setCellStyle(cellStyleLeft);
//				cell3_4.setCellValue("");
//
//			}
//			file.close();
//			File out = new File(folder2Upload + File.separatorChar
//					+ "TheKho.xlsx");
//			FileOutputStream outFile = new FileOutputStream(out);
//			System.out.println(out.getCanonicalPath());
//			workbook.write(outFile);
//			workbook.close();
//			outFile.close();
//
//		} catch (FileNotFoundException e) {
//			LOGGER.error(e.getMessage(), e);
//		} catch (IOException e) {
//			LOGGER.error(e.getMessage(), e);
//		}
//
//		String filename = UEncrypt.encryptFileUploadPath("TheKho.xlsx");
//		return Response.ok(Collections.singletonMap("fileName", filename))
//				.build();
//	}
//
//	@Override
//	public Response getCheckStockGood(StockGoodsTotalDTO obj) {
//		return Response.ok(stockGoodsTotalBusinessImpl.getCheckStockGood(obj))
//				.build();
//	}
//}
