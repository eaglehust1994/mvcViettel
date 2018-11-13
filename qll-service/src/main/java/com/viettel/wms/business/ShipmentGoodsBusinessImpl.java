//package com.viettel.wms.business;
// 
//import com.viettel.wms.bo.ShipmentGoodsBO;
//import com.viettel.wms.dao.ShipmentGoodsDAO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderGoodsExelDTO;
//import com.viettel.wms.dto.ShipmentGoodsDTO;
//import com.viettel.wms.dto.TaxDTO;
//import com.viettel.wms.utils.ValidateUtils;
//import com.google.common.collect.Lists;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//
//
//@Service("shipmentGoodsBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class ShipmentGoodsBusinessImpl extends BaseFWBusinessImpl<ShipmentGoodsDAO,ShipmentGoodsDTO, ShipmentGoodsBO> implements ShipmentGoodsBusiness {
//
//	List<OrderGoodsExelDTO> lstErrorOrder;
//	
//	static Logger LOGGER = LoggerFactory.getLogger(ShipmentGoodsBusinessImpl.class);
//	
//    @Autowired
//    private ShipmentGoodsDAO shipmentGoodsDAO;
//    
//    @Autowired
//    private GoodsBusinessImpl goodsBusinessImpl;
//    
//    @Value("${folder_upload}")
//	private String folder2Upload;
//     
//    public ShipmentGoodsBusinessImpl() {
//        tModel = new ShipmentGoodsBO();
//        tDAO = shipmentGoodsDAO;
//    }
//
//    @Override
//    public ShipmentGoodsDAO gettDAO() {
//        return shipmentGoodsDAO;
//    }
//
//    @Override
//    public long count() {
//        return shipmentGoodsDAO.count("ShipmentGoodsBO", null);
//    }
//    // Hàm hiển thị danh sách hàng hóa trong màn định lượng
//    public List<ShipmentGoodsDTO> doSearchMap(ShipmentGoodsDTO obj) {
//    	return shipmentGoodsDAO.doSearchMap(obj);
//    }
//    //End
//    //Hàm getForAutoComplete
//	public List<ShipmentGoodsDTO> getForAutoComplete(ShipmentGoodsDTO obj) {
//		return shipmentGoodsDAO.getForAutoComplete(obj);
//	}
//	//End
//	//Tim kiem hang hoa nhap tu nha cung cap - NgocCX
//	@Override
//	public List<ShipmentGoodsDTO> getGoodsInfoByCode(String code) {
//		// TODO Auto-generated method stub
//		return shipmentGoodsDAO.getGoodsInfoByCode(code);
//	}
//	//End
//	//Import định lượng hàng hóa
//	@Override
//	public List<ShipmentGoodsDTO> importQuantitative(String fileInput, Long id) throws Exception {
//		// TODO Auto-generated method stub
//		lstErrorOrder = new ArrayList<>();
//		List<ShipmentGoodsDTO> workLst = Lists.newArrayList();
//		try {
//			
//			File f = new File(fileInput);
//
//			XSSFWorkbook workbook = new XSSFWorkbook(f);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//
//			DataFormatter formatter = new DataFormatter();
//			int count = 0;
//
//			for (Row row : sheet) {
//				count++;
//				if (count >= 3&& !ValidateUtils.checkIfRowIsEmpty(row)) {
//
//					boolean checkColumn1 = true;
//					boolean checkColumn4 = true;
//					boolean checkColumn3 = true;
//					String goodsCode = "";
//					String estimatePrice = "";
//					String amount = "";
//					String goodsName = "";
//
//					OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
//					for (Cell cell : row) {
//						// Create object OrderGoodsExelDTO
//
//						// Check format file exel
//						if (cell.getColumnIndex() == 1) {
//							goodsCode = formatter.formatCellValue(cell);
//						} else if (cell.getColumnIndex() == 4) {
//							estimatePrice = formatter.formatCellValue(cell);
//						}
//						else if (cell.getColumnIndex() == 3) {
//							amount = formatter.formatCellValue(cell);
//						}
//						else if (cell.getColumnIndex() == 2) {
//							goodsName = formatter.formatCellValue(cell);
//						}
//
//					}
//					if(goodsCode==""&&goodsName==""&&amount==""&&estimatePrice==""){
//						if(count==3){
//							for(Cell cell:row){
//								 orderErrorFormat = new OrderGoodsExelDTO();
//								if (cell.getColumnIndex() == 1) {
//									goodsCode = formatter.formatCellValue(cell);
//									checkColumn1 = checkDataFromFileExel(goodsCode, count, 1, orderErrorFormat, id);
//								} else if (cell.getColumnIndex() == 4) {
//									estimatePrice = formatter.formatCellValue(cell);
//									checkColumn4 = checkDataFromFileExel(estimatePrice, count, 4, orderErrorFormat, id);
//								}
//								else if (cell.getColumnIndex() == 3) {
//									amount = formatter.formatCellValue(cell);
//									checkColumn3 = checkDataFromFileExel(amount, count, 3, orderErrorFormat, id);
//								}
//								else if (cell.getColumnIndex() == 2) {
//									goodsName = formatter.formatCellValue(cell);
//									}
//							}
//						}
//						else{
//							break;
//						}
//					}
//					else{
//						for(Cell cell:row){
//						 orderErrorFormat = new OrderGoodsExelDTO();
//						if (cell.getColumnIndex() == 1) {
//							goodsCode = formatter.formatCellValue(cell);
//							checkColumn1 = checkDataFromFileExel(goodsCode, count, 1, orderErrorFormat, id);
//						} else if (cell.getColumnIndex() == 4) {
//							estimatePrice = formatter.formatCellValue(cell);
//							checkColumn4 = checkDataFromFileExel(estimatePrice, count, 4, orderErrorFormat, id);
//						}
//						else if (cell.getColumnIndex() == 3) {
//							amount = formatter.formatCellValue(cell);
//							checkColumn3 = checkDataFromFileExel(amount, count, 3, orderErrorFormat, id);
//						}
//						else if (cell.getColumnIndex() == 2) {
//							goodsName = formatter.formatCellValue(cell);
//							}
//					}
//					}
//					if (checkColumn1 && checkColumn4&&checkColumn3) {
//						ShipmentGoodsDTO newObj = new ShipmentGoodsDTO();
//						DecimalFormat df = new DecimalFormat("#.##");
//						newObj.setShipmentId(id);
//						List<ShipmentGoodsDTO> lstShipmentGoods = shipmentGoodsDAO.doSearchMap(newObj);
//						for(int i=0;i<lstShipmentGoods.size();i++){
//							if(lstShipmentGoods.get(i).getGoodsCode().equals(formatter.formatCellValue(row.getCell(1)).toUpperCase().trim())){
//						newObj.setGoodsCode(formatter.formatCellValue(row.getCell(1)).toUpperCase().trim());
//						newObj.setGoodsName(lstShipmentGoods.get(i).getGoodsName().trim());
//						newObj.setAmount(lstShipmentGoods.get(i).getAmount());
//						newObj.setEstimatePrice(Double.parseDouble(df.format(row.getCell(4).getNumericCellValue())));
//							}
//						}
//						workLst.add(newObj);
//					}
//
//				}
//			}
//
//			workbook.close();
//
//		} catch (NullPointerException pointerException) {
//			// pointerException.printStackTrace();
//			LOGGER.error(pointerException.getMessage(), pointerException);
//		} catch (Exception e) {
//			// e.printStackTrace();
//			LOGGER.error(e.getMessage(), e);
//		}
//		
//		String filePathError = UEncrypt.encryptFileUploadPath(fileInput);
//		ShipmentGoodsDTO objError = new ShipmentGoodsDTO();
//		objError.setLstErrorGoods(lstErrorOrder);
//		objError.setFilePathError(filePathError);
//		
//		workLst.add(objError);
//
//		return workLst;
//	}
//	//End
//	//Hàm checkDataFromFileExel 
//	boolean check = false;
//	String goodCode = "";
//	public boolean checkDataFromFileExel(String data, int rowIndex, int columnIndex,
//			OrderGoodsExelDTO orderErrorFormat,Long id) {
//		
//		if (columnIndex == 1) {
//			ShipmentGoodsDTO shipmentGoodsDto = new ShipmentGoodsDTO();
//			shipmentGoodsDto.setShipmentId(id);
//			List<ShipmentGoodsDTO> lstShipmentGoods = shipmentGoodsDAO.doSearchMap(shipmentGoodsDto);
//			List<String> lstShipmentGoodsCode = new ArrayList<>();
//			for(int i = 0; i<lstShipmentGoods.size();i++){
//				lstShipmentGoodsCode.add(lstShipmentGoods.get(i).getGoodsCode());
//			}
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Mã hàng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			
//			if (!lstShipmentGoodsCode.contains(data.toUpperCase().trim())) {
//				check =false;
//				goodCode = "";
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Mã hàng không thuộc lô hàng");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			else{
//				goodCode =data;
//				check=true;
//			}
//		} else if (columnIndex == 4) {
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Định lượng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (!isDouble(data)) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Định lượng không phải kiểu số dương");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (Double.parseDouble(data)<=0) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Định lượng nhỏ hơn hoặc bằng 0");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		}
//		else if (columnIndex == 3) {
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Số lượng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (!isDouble(data)) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Số lượng không phải kiểu số dương");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (Integer.parseInt(data)<=0) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Số lượng nhỏ hơn hoặc bằng không");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		}
//		
//		return true;
//	}
////End
//	// export file biểu mẫu
//	@Override
//	public String exportExcelTemplate(ShipmentGoodsDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		
//		
//		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//		String filePath = classloader.getResource("../" + "doc-template").getPath();
//		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "Import_DinhLuong.xlsx"));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(0);
//		
//		XSSFCellStyle style=workbook.createCellStyle();
//		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
//		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
//		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
//		
//		XSSFFont defaultFont = workbook.createFont();
//		defaultFont.setFontHeightInPoints((short) 10);
//		defaultFont.setFontName("Times New Roman");
//		defaultFont.setColor(IndexedColors.BLACK.getIndex());
//		defaultFont.setBold(false);
//		defaultFont.setItalic(false);
//		
//		CellStyle cellStyle = workbook.createCellStyle();
//		cellStyle.setBorderBottom(BorderStyle.THIN);
//		cellStyle.setBorderTop(BorderStyle.THIN);
//		cellStyle.setBorderLeft(BorderStyle.THIN);
//		cellStyle.setBorderRight(BorderStyle.THIN);
//		cellStyle.setFont(defaultFont);
//		cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
//		
//		List<ShipmentGoodsDTO> lstShipmentGoods = shipmentGoodsDAO.doSearchMap(obj);
//		for(int i=0;i<lstShipmentGoods.size();i++){
//			XSSFRow row = sheet.getRow(i+2);
//			XSSFCell cell1 = row.createCell(1);
//			cell1.setCellStyle(cellStyle);
//			cell1.setCellValue(lstShipmentGoods.get(i).getGoodsCode());
//			XSSFCell cell2 = row.createCell(2);
//			cell2.setCellStyle(cellStyle);
//			cell2.setCellValue(lstShipmentGoods.get(i).getGoodsName());
//			XSSFCell cell3 = row.createCell(3);
//			cell3.setCellStyle(cellStyle);
//			if(lstShipmentGoods.get(i).getAmount() != null){
//			cell3.setCellValue(lstShipmentGoods.get(i).getAmount());
//			}
//		}
//		
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar + "Import_DinhLuong.xlsx");
//		
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//		
//		String path = UEncrypt.encryptFileUploadPath("Import_DinhLuong.xlsx");
//		return path;
//	}
////End
//	//Xuất file lỗi
//	@Override
//	public String exportExcelError(GoodsDTO errorObj) throws Exception {
//		// TODO Auto-generated method stub
//		String filePath = UEncrypt.decryptFileUploadPath(errorObj.getFilePathError());
//		InputStream file = new BufferedInputStream(new FileInputStream(filePath));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(0);
//		XSSFFont defaultFont = workbook.createFont();
//		defaultFont.setFontHeightInPoints((short) 10);
//		defaultFont.setFontName("Times New Roman");
//		defaultFont.setColor(IndexedColors.BLACK.getIndex());
//		defaultFont.setBold(false);
//		defaultFont.setItalic(false);
//		
//		for(int i = 0; i<errorObj.getLstErrorGoods().size();i++){
//			XSSFRow row = sheet.getRow(Integer.parseInt(errorObj.getLstErrorGoods().get(i).getLineError())-1);
//			if(row ==null){
//				row = sheet.createRow(Integer.parseInt(errorObj.getLstErrorGoods().get(i).getLineError())-1);
//			}
//			XSSFCell cell = row.getCell(5);
//			
//			if(cell ==null){
//				cell = row.createCell(5);
//			}
//			if(!cell.getStringCellValue().isEmpty()){
//				cell.setCellValue(cell.getStringCellValue()+","+errorObj.getLstErrorGoods().get(i).getDetailError());
//			}else{
//				cell.setCellValue(errorObj.getLstErrorGoods().get(i).getDetailError());
//			}
//		}
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar + "Import_DinhLuong_Err.xlsx");
//		
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//		
//		String path = UEncrypt.encryptFileUploadPath("Import_DinhLuong_Err.xlsx");
//		return path;
//	}
//	//End
//	// Hàm check Double
//		private boolean isDouble(String str) {
//			try {
//				Double.parseDouble(str);
//				return true;
//			} catch (NumberFormatException e) {
//				return false;
//			}
//
//		}
//
//		// End
//// Hàm thêm mới hàng hóa của lô hàng trong bảng ShipmentGoods
//	@Override
//	public Long addShipmentGoodsList(List<ShipmentGoodsDTO> obj) {
//		Long id = 0l;
//		for(ShipmentGoodsDTO sg : obj){
//			id = save(sg);
//			if(id == 0)	break;
//		}
//		return id;
//	}
//	//End
//	public List<ShipmentGoodsDTO> getShipmentGoodsPrice(ShipmentGoodsDTO obj) {
//    	return shipmentGoodsDAO.getShipmentGoodsPrice(obj);
//    }
//	//Xóa hàng hóa của lô hàng trong bảng shipmentGoods
//	public void deleteGoods(Long shipmentGoodsId){
//		shipmentGoodsDAO.deleteGoods(shipmentGoodsId);
//	}
//	//End
//}
