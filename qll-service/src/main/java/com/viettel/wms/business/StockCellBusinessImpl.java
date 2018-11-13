package com.viettel.wms.business;

import com.google.common.collect.Lists;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.viettel.wms.bo.StockCellBO;
import com.viettel.wms.dao.StockCellDAO;
import com.viettel.wms.dto.OrderGoodsDetailDTO;
import com.viettel.wms.dto.OrderGoodsExelDTO;
import com.viettel.wms.dto.StockCellDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("stockCellBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockCellBusinessImpl extends BaseFWBusinessImpl<StockCellDAO, StockCellDTO, StockCellBO>
		implements StockCellBusiness {
	static Logger LOGGER = LoggerFactory.getLogger(StockCellBusinessImpl.class);
	@Value("${folder_upload}")
	private String folder2Upload;
	@Autowired
	private StockCellDAO stockCellDAO;

	public StockCellBusinessImpl() {
		tModel = new StockCellBO();
		tDAO = stockCellDAO;
	}

	@Override
	public StockCellDAO gettDAO() {
		return stockCellDAO;
	}

	@Override
	public long count() {
		return stockCellDAO.count("StockCellBO", null);
	}

	public DataListDTO doSearch(StockCellDTO obj) {
		List<StockCellDTO> ls = stockCellDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	@Override
	public List<StockCellDTO> importStockCell(String fileInput) {
		List<StockCellDTO> workLst = Lists.newArrayList();
		try {
			File f = new File(fileInput);
			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter formatter = new DataFormatter();
			int count = 0;

			for (Row row : sheet) {
				StockCellDTO obj = new StockCellDTO();
				count++;
				if (count >= 2) {
					for (Cell cell : row) {
						// Check format file exel
						if (cell.getColumnIndex() == 1) {
							obj.setCode(formatter.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 2) {
							obj.setDescription(formatter.formatCellValue(cell));
						}

					}
					//List<StockCellDTO> workLstTmp = checkDataFromFileExelInDB(obj.getCode());

					//for (StockCellDTO objOrderGoodDetail : workLstTmp) {
						workLst.add(obj);
					//}

				}

			}

			workbook.close();

		} catch (

		NullPointerException pointerException) {
			// pointerException.printStackTrace();
			LOGGER.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
		if (workLst.size() == 0) {
			throw new IllegalArgumentException("Không có công việc phù hợp với những công việc trên lưới dữ liệu");
		}
		return workLst;
	}

	public List<StockCellDTO> checkDataFromFileExelInDB(String data) {
		List<StockCellDTO> lstOrderGoodsDetail = stockCellDAO.searchStockCellFromCode(data);
		return lstOrderGoodsDetail;
	}

	@Override
	public String exportStockCellExcel() throws Exception {
		// TODO Auto-generated method stub
		List<StockCellDTO> lstStockCell = stockCellDAO.getAllStockCellCode();
		List<String> lstCode = new ArrayList<>();
		for(int i=0;i<lstStockCell.size();i++){
			lstCode.add(lstStockCell.get(i).getCode());
		}
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "NK_Import_ViTri.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(1);
		
		for(int i=0;i<lstCode.size();i++){
			XSSFRow row = sheet.createRow(i+1);
			XSSFCell cell = row.createCell(0);
			cell.setCellValue(lstCode.get(i));
		}
		
		file.close();
		File out = new File(folder2Upload + File.separatorChar + "NK_Import_ViTri.xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath("NK_Import_ViTri.xlsx");
		return path;
	}

}
