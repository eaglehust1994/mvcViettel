//package com.viettel.wms.business;
//
//import com.viettel.wms.bo.ShipmentBO;
//import com.viettel.wms.constant.Constants;
//import com.viettel.wms.dao.ICntContractDAO;
//import com.viettel.wms.dao.ShipmentDAO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.ManufacturerDTO;
//import com.viettel.wms.dto.OrderGoodsExelDTO;
//import com.viettel.wms.dto.ProducingCountryDTO;
//import com.viettel.wms.dto.ShipmentDTO;
//import com.viettel.wms.dto.ShipmentGoodsDTO;
//import com.viettel.wms.dto.ShipmentGoodsDetailDTO;
//import com.viettel.wms.utils.ValidateUtils;
//import com.google.common.collect.Lists;
//import com.viettel.ktts.vps.VpsPermissionChecker;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.ktts2.dto.KttsUserSession;
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
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFCell;
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
//@Service("shipmentBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class ShipmentBusinessImpl extends
//		BaseFWBusinessImpl<ShipmentDAO, ShipmentDTO, ShipmentBO> implements
//		ShipmentBusiness {
//
//	static Logger LOGGER = LoggerFactory.getLogger(ShipmentBusinessImpl.class);
//	List<OrderGoodsExelDTO> lstErrorOrder;
//	@Autowired
//	DepartmentBusiness departmentBusiness;
//	@Autowired
//	private ShipmentDAO shipmentDAO;
//	@Autowired
//	ICntContractDAO icntContractDao;
//	@Autowired
//	private GoodsBusinessImpl goodsBusinessImpl;
//	@Autowired
//	private ShipmentGoodsBusinessImpl shipmentGoodsBusinessImpl;
//
//	@Autowired
//	private ManufacturerBusinessImpl manufacturerBusinessImpl;
//
//	@Autowired
//	private ProducingCountryBusinessImpl producingCountryBusinessImpl;
//
//	@Autowired
//	private ShipmentTaxBusinessImpl shipmentTaxBusinessImpl;
//
//	@Autowired
//	private TaxBusinessImpl taxBusinessImpl;
//	@Autowired
//	private CommonBusiness commonBusiness;
//	@Autowired
//	private ShipmentGoodsDetailBusinessImpl shipmentGoodsDetailBusinessImpl;
//
//	@Value("${folder_upload}")
//	private String folder2Upload;
//
//	public ShipmentBusinessImpl() {
//		tModel = new ShipmentBO();
//		tDAO = shipmentDAO;
//	}
//
//	@Override
//	public ShipmentDAO gettDAO() {
//		return shipmentDAO;
//	}
//
//	@Override
//	public long count() {
//		return shipmentDAO.count("ShipmentBO", null);
//	}
//// Hàm check mã lô hàng
//	public Boolean checkCode(String code, Long shipmentId) {
//		ShipmentDTO obj = shipmentDAO.getbycode(code);
//
//		if (shipmentId == null) {
//			if (obj == null) {
//				return true;
//			} else {
//				return false;
//			}
//		} else {
//			if (obj == null) {
//				return true;
//			} else if (obj != null
//					&& obj.getShipmentId().longValue() == shipmentId) {
//				return true;
//			} else {
//				return false;
//			}
//		}
//
//	}
////End
//	// Hàm tìm kiếm danh sách lô hàng trong Quản lý danh sách lô hàng
//	public DataListDTO doSearch(ShipmentDTO criteria) {
//		List<ShipmentDTO> ls = shipmentDAO.doSearch(criteria);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(criteria.getTotalRecord());
//		data.setSize(criteria.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//
//	// End
//	// Hàm tìm kiếm danh sách lô hàng trong Định lượng tỷ trọng kỹ thuật
//	public DataListDTO doSearchQuantity(ShipmentDTO criteria,
//			HttpServletRequest request) throws Exception {
//		String err = "";
//		if (criteria.getCreatedDeptId() == null) {
//			List<Long> listId = commonBusiness.getListDomainData(
//					Constants.OperationKey.ESTIMATE,
//					Constants.AdResourceKey.SHIPMENT, request);
//			criteria.setLstCreatedDeptId(listId);
//			List<ShipmentDTO> ls = shipmentDAO.doSearchQuantity(criteria);
//			DataListDTO data = new DataListDTO();
//			data.setData(ls);
//			data.setTotal(criteria.getTotalRecord());
//			data.setSize(criteria.getTotalRecord());
//			data.setStart(1);
//			return data;
//		}
//		if (!VpsPermissionChecker.checkPermissionOnDomainData(
//				Constants.OperationKey.ESTIMATE,
//				Constants.AdResourceKey.SHIPMENT, criteria.getCreatedDeptId(),
//				request)) {
//			err = StringUtils.isNotEmpty(err) ? (err + ";" + criteria
//					.getCreatedDeptId())
//					: ("Bạn không có quyền xem thông tin lô hàng tại đơn vị  " + criteria
//							.getCreatedDeptName());
//		}
//
//		if (StringUtils.isNotEmpty(err)) {
//			throw new IllegalArgumentException(err);
//		}
//		if(criteria.getLstCreatedDeptId().size()==0){
//			List<Long> listId=commonBusiness.getListDomainData(Constants.OperationKey.ESTIMATE, Constants.AdResourceKey.SHIPMENT, request);
//			criteria.setLstCreatedDeptId(listId);
//		}
//		List<ShipmentDTO> ls = shipmentDAO.doSearchQuantity(criteria);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(criteria.getTotalRecord());
//		data.setSize(criteria.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//
//	// End
//	// Begin
//	// Tìm kiếm định giá theo miền dữ liệu
//	public DataListDTO doSearchPrice(ShipmentDTO criteria,
//			HttpServletRequest request) throws Exception {
//		String err = "";
//		if (criteria.getCreatedDeptId() == null) {
//			List<Long> listId = commonBusiness.getListDomainData(
//					Constants.OperationKey.VALUE,
//					Constants.AdResourceKey.SHIPMENT, request);
//			criteria.setLstCreatedDeptId(listId);
//			List<ShipmentDTO> ls = shipmentDAO.doSearchQuantity(criteria);
//			DataListDTO data = new DataListDTO();
//			data.setData(ls);
//			data.setTotal(criteria.getTotalRecord());
//			data.setSize(criteria.getTotalRecord());
//			data.setStart(1);
//			return data;
//		}
//		if (!VpsPermissionChecker.checkPermissionOnDomainData(
//				Constants.OperationKey.VALUE, Constants.AdResourceKey.SHIPMENT,
//				criteria.getCreatedDeptId(), request)) {
//			err = StringUtils.isNotEmpty(err) ? (err + ";" + criteria
//					.getCreatedDeptId())
//					: ("Bạn không có quyền xem thông tin lô hàng tại đơn vị  " + criteria
//							.getCreatedDeptName());
//		}
//
//		if (StringUtils.isNotEmpty(err)) {
//			throw new IllegalArgumentException(err);
//		}
//		if(criteria.getLstCreatedDeptId().size()==0){
//			List<Long> listId=commonBusiness.getListDomainData(Constants.OperationKey.VALUE, Constants.AdResourceKey.SHIPMENT, request);
//			criteria.setLstCreatedDeptId(listId);
//		}
//		List<ShipmentDTO> ls = shipmentDAO.doSearchQuantity(criteria);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(criteria.getTotalRecord());
//		data.setSize(criteria.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//
//	// End
//	//
//	@Override
//	public List<String> searchListShipmentCode(String code) {
//		// TODO Auto-generated method stub
//		return shipmentDAO.searchListShipmentCode(code);
//	}
//	//End
//	// Hàm Import Dữ liệu từ Excel vào Grid
//	@Override
//	public List<GoodsDTO> importGoods(String fileInput) throws Exception {
//		lstErrorOrder = new ArrayList<>();
//		List<GoodsDTO> workLst = Lists.newArrayList();
//		try {
//			File f = new File(fileInput);
//
//			XSSFWorkbook workbook = new XSSFWorkbook(f);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//
//			DataFormatter formatter = new DataFormatter();
//			OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
//			int count = 0;
//			for (Row row : sheet) {
//				count++;
//				if (count >= 3 && !ValidateUtils.checkIfRowIsEmpty(row)) {
//
//					boolean checkColumn1 = true;
//					boolean checkColumn3 = true;
//					boolean checkColumn4 = true;
//					boolean checkColumn6 = true;
//					boolean checkColumn7 = true;
//					String code = "";
//					String amount = "";
//					String name = "";
//					String partNumber = "";
//					String serial = "";
//					String manufacturerId = "";
//					String producingCountryId = "";
//
//					for (Cell cell : row) {
//
//						// Check format file exel
//						if (cell.getColumnIndex() == 1) {
//							code = formatter.formatCellValue(cell);
//						} else if (cell.getColumnIndex() == 2) {
//							name = formatter.formatCellValue(cell);
//						} else if (cell.getColumnIndex() == 3) {
//							manufacturerId = formatter.formatCellValue(cell);
//						} else if (cell.getColumnIndex() == 4) {
//							producingCountryId = formatter
//									.formatCellValue(cell);
//						} else if (cell.getColumnIndex() == 5) {
//							partNumber = formatter.formatCellValue(cell);
//						} else if (cell.getColumnIndex() == 6) {
//							serial = formatter.formatCellValue(cell);
//						} else if (cell.getColumnIndex() == 7) {
//							amount = formatter.formatCellValue(cell);
//						}
//					}
//					if (code == "" && name == "" && amount == ""
//							&& serial == "" && partNumber == ""
//							&& producingCountryId == "" && manufacturerId == "") {
//						if (count == 3) {
//							for (Cell cell : row) {
//								orderErrorFormat = new OrderGoodsExelDTO();
//								if (cell.getColumnIndex() == 1) {
//									code = formatter.formatCellValue(cell);
//									checkColumn1 = checkDataFromFileExel(code,
//											count, 1, orderErrorFormat);
//								} else if (cell.getColumnIndex() == 2) {
//									name = formatter.formatCellValue(cell);
//								} else if (cell.getColumnIndex() == 3) {
//									manufacturerId = formatter
//											.formatCellValue(cell);
//									checkColumn3 = checkDataFromFileExel(
//											manufacturerId, count, 3,
//											orderErrorFormat);
//								} else if (cell.getColumnIndex() == 4) {
//									producingCountryId = formatter
//											.formatCellValue(cell);
//									checkColumn4 = checkDataFromFileExel(
//											producingCountryId, count, 4,
//											orderErrorFormat);
//								} else if (cell.getColumnIndex() == 5) {
//									partNumber = formatter
//											.formatCellValue(cell);
//								} else if (cell.getColumnIndex() == 6) {
//									serial = formatter.formatCellValue(cell);
//									checkColumn6 = checkDataFromFileExel(
//											serial, count, 6, orderErrorFormat);
//								} else if (cell.getColumnIndex() == 7) {
//									amount = formatter.formatCellValue(cell);
//									checkColumn7 = checkDataFromFileExel(
//											amount, count, 7, orderErrorFormat);
//								}
//							}
//						} else {
//							break;
//						}
//					} else {
//						for (Cell cell : row) {
//							orderErrorFormat = new OrderGoodsExelDTO();
//							if (cell.getColumnIndex() == 1) {
//								code = formatter.formatCellValue(cell);
//								checkColumn1 = checkDataFromFileExel(code,
//										count, 1, orderErrorFormat);
//							} else if (cell.getColumnIndex() == 2) {
//								name = formatter.formatCellValue(cell);
//							} else if (cell.getColumnIndex() == 3) {
//								manufacturerId = formatter
//										.formatCellValue(cell);
//								checkColumn3 = checkDataFromFileExel(
//										manufacturerId, count, 3,
//										orderErrorFormat);
//							} else if (cell.getColumnIndex() == 4) {
//								producingCountryId = formatter
//										.formatCellValue(cell);
//								checkColumn4 = checkDataFromFileExel(
//										producingCountryId, count, 4,
//										orderErrorFormat);
//							} else if (cell.getColumnIndex() == 5) {
//								partNumber = formatter.formatCellValue(cell);
//								} else if (cell.getColumnIndex() == 6) {
//								serial = formatter.formatCellValue(cell);
//								checkColumn6 = checkDataFromFileExel(serial,
//										count, 6, orderErrorFormat);
//							} else if (cell.getColumnIndex() == 7) {
//								amount = formatter.formatCellValue(cell);
//								checkColumn7 = checkDataFromFileExel(amount,
//										count, 7, orderErrorFormat);
//							}
//						}
//					}
//					if (checkColumn1 && checkColumn7 && checkColumn6&& checkColumn3 && checkColumn4) {
//						GoodsDTO newObj = new GoodsDTO();
//						List<GoodsDTO> lstGoods = goodsBusinessImpl.getGoodsForOrder(newObj);
//						DecimalFormat df = new DecimalFormat("#.##");
//						for (int i = 0; i < lstGoods.size(); i++) {
//							if (lstGoods.get(i).getCode().equals(formatter.formatCellValue(row.getCell(1)).toUpperCase().trim())) {
//								newObj.setName(lstGoods.get(i).getName());
//								newObj.setOriginPrice(lstGoods.get(i).getOriginPrice());
//								newObj.setCode(formatter.formatCellValue(row.getCell(1)).toUpperCase().trim());
//								if (manufacturerId != "") {
//									newObj.setManufacturerId(Math.round(row.getCell(3).getNumericCellValue()));
//									newObj.setManufacturerName(manufacturerBusinessImpl.getAllNameById(Long.parseLong(manufacturerId)).getName());
//								} else {
//									newObj.setManufacturerId(null);
//									newObj.setManufacturerName(null);
//								}
//								if (producingCountryId != "") {
//									newObj.setProducingCountryId(Math.round(row
//											.getCell(4).getNumericCellValue()));
//									newObj.setProducingCountryName(producingCountryBusinessImpl
//											.getAllNameById(
//													Long.parseLong(producingCountryId))
//											.getName());
//								} else {
//									newObj.setProducingCountryId(null);
//									newObj.setProducingCountryName(null);
//								}
//								newObj.setPartNumber(formatter
//										.formatCellValue(row.getCell(5))
//										.toUpperCase().trim());
//								newObj.setSerial(formatter.formatCellValue(
//										row.getCell(6)).trim());
//								if(isDouble(formatter
//										.formatCellValue(row.getCell(7)))){
//								newObj.setAmount(Double.parseDouble(df
//										.format(row.getCell(7)
//												.getNumericCellValue())));
//								}
//								newObj.setGoodsId(lstGoods.get(i).getGoodsId());
//								newObj.setIsSerial(lstGoods.get(i)
//										.getIsSerial());
//								newObj.setGoodsUnitName(lstGoods.get(i)
//										.getGoodsUnitName());
//								newObj.setUnitType(lstGoods.get(i)
//										.getGoodsUnitId());
//								break;
//							}
//						}
//						workLst.add(newObj);
//					}
//
//				}
//			}
//			workbook.close();
//		} catch (NullPointerException pointerException) {
//			// pointerException.printStackTrace();
//			LOGGER.error(pointerException.getMessage(), pointerException);
//		}
//		catch (Exception e) {
//			// e.printStackTrace();
//			LOGGER.error(e.getMessage(), e);
//		}
//
//		String filePathError = UEncrypt.encryptFileUploadPath(fileInput);
//		GoodsDTO objError = new GoodsDTO();
//		objError.setLstErrorGoods(lstErrorOrder);
//		objError.setFilePathError(filePathError);
//
//		workLst.add(objError);
//		return workLst;
//	}
//
//	// End
//	// Kiem tra du lieu file Import
//	boolean check = false;
//	String goodCode = "";
//
//	public boolean checkDataFromFileExel(String data, int rowIndex,
//			int columnIndex, OrderGoodsExelDTO orderErrorFormat) {
//		GoodsDTO dto = new GoodsDTO();
//		List<GoodsDTO> lstGoods = goodsBusinessImpl.getGoodsForOrder(dto);
//		if (columnIndex == 1) {
//			List<String> lstCode = new ArrayList<>();
//			for (int i = 0; i < lstGoods.size(); i++) {
//				lstCode.add(lstGoods.get(i).getCode().toUpperCase().trim());
//			}
//			if (data.isEmpty()) {
//				goodCode = "";
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat
//						.setColumnError(String.valueOf(columnIndex + 1));
//				orderErrorFormat.setDetailError("Mã hàng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (!lstCode.contains(data.toUpperCase().trim())) {
//				check = false;
//				goodCode = "";
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat
//						.setColumnError(String.valueOf(columnIndex + 1));
//				orderErrorFormat.setDetailError("Mã hàng không tồn tại");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			} else {
//				goodCode = data.toUpperCase().trim();
//				check = true;
//			}
//		}
//
//		else if (columnIndex == 3) {
//			List<ManufacturerDTO> lst = manufacturerBusinessImpl
//					.getAllNameAndId();
//			List<Long> lstMnId = new ArrayList<>();
//			for (int i = 0; i < lst.size(); i++) {
//				lstMnId.add(lst.get(i).getManufacturerId());
//			}
//			if (!(lstMnId.toString()).contains(data.trim())) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat
//						.setColumnError(String.valueOf(columnIndex + 1));
//				orderErrorFormat
//						.setDetailError("ID hãng sản xuất không tồn tại");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		} else if (columnIndex == 4) {
//			List<ProducingCountryDTO> lstp = producingCountryBusinessImpl
//					.getAllNameAndId();
//			List<Long> lstPrId = new ArrayList<>();
//			for (int i = 0; i < lstp.size(); i++) {
//				lstPrId.add(lstp.get(i).getProducingCountryId());
//			}
//			if (!(lstPrId.toString()).contains(data.trim())) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat
//						.setColumnError(String.valueOf(columnIndex + 1));
//				orderErrorFormat
//						.setDetailError("ID nước sản xuất không tồn tại");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		} else if (columnIndex == 6) {
//			if (check && goodCode != "") {
//				checkSerialFileImport(data, rowIndex,columnIndex, orderErrorFormat,lstGoods,check,goodCode);
//				}
//
//		} else if (columnIndex == 7) {
//			if (check && goodCode != "") {
//				checkAmount(data, rowIndex, columnIndex, orderErrorFormat, lstGoods,check,goodCode);
//			}
//		}
//		return true;
//
//	}
//	//Hamf check so luong trong file import
//	public boolean checkAmount(String data, int rowIndex,
//			int columnIndex, OrderGoodsExelDTO orderErrorFormat,List<GoodsDTO> lstGoods,boolean check,String goodsCode){
//		for (int i = 0; i < lstGoods.size(); i++) {
//		if (lstGoods.get(i).getCode().toUpperCase().trim()
//				.contains(goodCode.toUpperCase().trim())) {
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String
//						.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String
//						.valueOf(columnIndex + 1));
//				orderErrorFormat
//						.setDetailError("Số lượng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			} else if (!isDouble(data)) {
//					orderErrorFormat.setLineError(String
//							.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String
//							.valueOf(columnIndex + 1));
//					orderErrorFormat
//							.setDetailError("Số lượng không đúng định dạng");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				} else if (Double.parseDouble(data) <= 0) {
//						orderErrorFormat.setLineError(String
//								.valueOf(rowIndex));
//						orderErrorFormat.setColumnError(String
//								.valueOf(columnIndex + 1));
//						orderErrorFormat
//								.setDetailError("Số lượng nhỏ hơn 0");
//						lstErrorOrder.add(orderErrorFormat);
//						return false;
//					} else if ("1".equals(lstGoods.get(i)
//								.getIsSerial())
//								&& Double.parseDouble(data) != 1) {
//							orderErrorFormat.setLineError(String
//									.valueOf(rowIndex));
//							orderErrorFormat.setColumnError(String
//									.valueOf(columnIndex + 1));
//							orderErrorFormat
//									.setDetailError("Hàng hóa là thiết bị chỉ được phép nhập số lượng =1");
//							lstErrorOrder.add(orderErrorFormat);
//							return false;
//						} else if (Double.parseDouble(data) > 9999999999d) {
//							orderErrorFormat.setLineError(String
//									.valueOf(rowIndex));
//							orderErrorFormat.setColumnError(String
//									.valueOf(columnIndex + 1));
//							orderErrorFormat
//									.setDetailError("Số lượng bạn nhập quá maxLength!");
//							lstErrorOrder.add(orderErrorFormat);
//							return false;
//					}
//			}
//	}
//		return true;
//	}
//	//End
//	//Ham check Serial trong file import
//	public boolean checkSerialFileImport(String data, int rowIndex,int columnIndex, OrderGoodsExelDTO orderErrorFormat,List<GoodsDTO> lstGoods,boolean check,String goodsCode){
//		for (int i = 0; i < lstGoods.size(); i++) {
//			if (lstGoods.get(i).getCode().toUpperCase().trim()
//					.contains(goodCode.toUpperCase().trim())) {
//				if ("1".equals(lstGoods.get(i).getIsSerial())
//						&& data.isEmpty()) {
//					orderErrorFormat.setLineError(String
//							.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String
//							.valueOf(columnIndex + 1));
//					orderErrorFormat
//							.setDetailError("Hàng có quản lý Serial không được trống");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				} else if (!"1".equals(lstGoods.get(i)
//						.getIsSerial().toUpperCase().trim())
//						&& data != "") {
//					orderErrorFormat.setLineError(String
//							.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String
//							.valueOf(columnIndex + 1));
//					orderErrorFormat
//							.setDetailError("Hàng không quản lý Serial không được phép nhập Serial");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//			}
//		}
//		return true;
//		
//	}
//	//End
//	
//	// Hàm check Double
//		public boolean isDouble(String str) {
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
//
//	// Hàm Export ra file Excel lỗi
//	@Override
//	public String exportExcelError(GoodsDTO errorObj) throws Exception {
//		// TODO Auto-generated method stub
//		String filePath = UEncrypt.decryptFileUploadPath(errorObj
//				.getFilePathError());
//		InputStream file = new BufferedInputStream(
//				new FileInputStream(filePath));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(0);
//
//		for (int i = 0; i < errorObj.getLstErrorGoods().size(); i++) {
//			XSSFRow row = sheet.getRow(Integer.parseInt(errorObj
//					.getLstErrorGoods().get(i).getLineError()) - 1);
//			if (row == null) {
//				row = sheet.createRow(Integer.parseInt(errorObj
//						.getLstErrorGoods().get(i).getLineError()) - 1);
//			}
//			XSSFCell cell = row.getCell(8);
//
//			if (cell == null) {
//				cell = row.createCell(8);
//			}
//			if (!cell.getStringCellValue().isEmpty()) {
//				cell.setCellValue(cell.getStringCellValue() + ","
//						+ errorObj.getLstErrorGoods().get(i).getDetailError());
//			} else {
//				cell.setCellValue(errorObj.getLstErrorGoods().get(i)
//						.getDetailError());
//			}
//		}
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar
//				+ "Import_HangHoa_Err.xlsx");
//
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//
//		String path = UEncrypt.encryptFileUploadPath("Import_HangHoa_Err.xlsx");
//		return path;
//	}
//
//	// End
//	// Export file biểu mẫu
//	@Override
//	public String exportExcelTemplate() throws Exception {
//		// TODO Auto-generated method stub
//		List<ManufacturerDTO> lstManufacturer = manufacturerBusinessImpl
//				.getAllNameAndId();
//		List<ProducingCountryDTO> lstProducingCountry = producingCountryBusinessImpl
//				.getAllNameAndId();
//
//		ClassLoader classloader = Thread.currentThread()
//				.getContextClassLoader();
//		String filePath = classloader.getResource("../" + "doc-template")
//				.getPath();
//		InputStream file = new BufferedInputStream(new FileInputStream(filePath
//				+ "Import_HangHoa.xlsx"));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(1);
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
//		for (int i = 0; i < lstProducingCountry.size(); i++) {
//			XSSFRow row = sheet.getRow(i + 1);
//			XSSFCell cell1 = row.createCell(0);
//			cell1.setCellValue(lstProducingCountry.get(i)
//					.getProducingCountryId());
//			cell1.setCellStyle(cellStyle);
//			XSSFCell cell2 = row.createCell(1);
//			cell2.setCellValue(lstProducingCountry.get(i).getName());
//			cell2.setCellStyle(cellStyle);
//
//		}
//		for (int i = 0; i < lstManufacturer.size(); i++) {
//			XSSFRow row = sheet.getRow(i + 1);
//			XSSFCell cell3 = row.createCell(2);
//			cell3.setCellValue(lstManufacturer.get(i).getManufacturerId());
//			cell3.setCellStyle(cellStyle);
//			XSSFCell cell4 = row.createCell(3);
//			cell4.setCellValue(lstManufacturer.get(i).getName());
//			cell4.setCellStyle(cellStyle);
//		}
//
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar
//				+ "Import_HangHoa.xlsx");
//
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//
//		String path = UEncrypt.encryptFileUploadPath("Import_HangHoa.xlsx");
//		return path;
//	}
//
//	
//	//Hàm cập nhật status cho lô hàng
//	@Override
//	public void updateStatus(ShipmentDTO obj) {
//		shipmentDAO.updateStatus(obj);
//
//	}
////End
//	// begin
//	// luu vao 2 bang SHIPMENT_GOODS va SHIPMENT_GOODS_DETAILS
//	@Override
//	public Long saveDetailShipment(ShipmentDTO obj, HttpServletRequest request)
//			throws Exception {
//		if (!VpsPermissionChecker.checkPermissionOnDomainData(
//				Constants.OperationKey.VALUE, Constants.AdResourceKey.SHIPMENT,
//				obj.getCreatedDeptId(), request)) {
//			throw new IllegalArgumentException(
//					"Bạn không có quyền định giá cho lô hàng này !");
//		}
//		Long id = update(obj);
//
//			for (int k = 0; k < obj.getLstShipmentTax().size(); k++) {
//				obj.getLstShipmentTax().get(k).setShipmentId(obj.getShipmentId());
//				if(obj.getStatus() == "3"){
//					shipmentTaxBusinessImpl.save(obj.getLstShipmentTax().get(k));
//				}
//				else{
//					Long idtax = shipmentDAO.deleteShipmentTax(obj);
//					if(idtax != null){
//						shipmentTaxBusinessImpl.save(obj.getLstShipmentTax().get(k));
//					}
//				}
//				
//			}
//			for (int i = 0; i < obj.getLstShipmentGoods().size(); i++) {
//				Long idShipmentGoods = shipmentDAO.deleteShipmentGoods(obj);
//				Long shipmentGoodsId = shipmentGoodsBusinessImpl.save(obj.getLstShipmentGoods().get(i));
//				if (idShipmentGoods != null) {
//					obj.getLstShipmentGoods().get(i).setShipmentId(obj.getShipmentId());
//					Long idShipmentGoodsDetail = shipmentDAO.deleteShipmentGoodsDetail(obj);
//					if (idShipmentGoodsDetail!=null) {
//						if (obj.getLstShipmentDetail() != null) {
//							obj.getLstShipmentDetail().get(i).setShipmentGoodsId(shipmentGoodsId);
//							shipmentGoodsDetailBusinessImpl.save(obj.getLstShipmentDetail().get(i));
//						}
//					}
//				}
//			}
//		
//		return id;
//	}
//	// end
//	// Hàm Xử lý Map hàng hóa cho lô hàng
//	public Long addShipmentGoods(List<ShipmentGoodsDTO> lobj) throws Exception {
//		Long id = (long) 0;
//		ShipmentDTO s = new ShipmentDTO();
//		ShipmentGoodsDTO smg = new ShipmentGoodsDTO();
//		ShipmentGoodsDetailDTO smgd = new ShipmentGoodsDetailDTO();
//		smg.setShipmentId(lobj.get(0).getShipmentId());
//		smgd.setShipmentId(lobj.get(0).getShipmentId());
//		List<ShipmentGoodsDTO> lstObj = shipmentGoodsBusinessImpl
//				.doSearchMap(smg);
//		if (lstObj.size() != 0) {
//			for (ShipmentGoodsDTO o : lstObj) {
//				shipmentGoodsBusinessImpl.deleteGoods(o.getShipmentGoodsId());
//			}
//		}
//		List<ShipmentGoodsDetailDTO> lstdObj = shipmentGoodsDetailBusinessImpl
//				.getDoMapDetail(smgd);
//		if (lstdObj.size() != 0) {
//			for (ShipmentGoodsDetailDTO o : lstdObj) {
//				shipmentGoodsDetailBusinessImpl.deleteGoods(o
//						.getShipmentGoodsDetailId());
//			}
//		}
//		for (ShipmentGoodsDTO obj : lobj) {
//			GoodsDTO g = new GoodsDTO();
//			g.setId(obj.getGoodsId());
//			GoodsDTO lstGoods = goodsBusinessImpl.getGoodById(g);
//			ShipmentGoodsDetailDTO sgd = new ShipmentGoodsDetailDTO();
//			sgd.setGoodsId(obj.getGoodsId());
//			sgd.setSerial(obj.getSerial());
//			sgd.setShipmentId(obj.getShipmentId());
//			if (sgd.getSerial() != null && sgd.getGoodsId() != null) {
//				ShipmentGoodsDetailDTO data = shipmentGoodsDetailBusinessImpl
//						.checkSerial(sgd);
//				if (data != null) {
//					throw new IllegalArgumentException(
//							"Serial của hàng hóa đã tồn tại trong DB");
//				}
//			}
//			if ("1".equals(lstGoods.getIsSerial()) && sgd.getSerial() == null) {
//				throw new IllegalArgumentException(
//						"Hàng quản lý Serial không được để trống trường Serial");
//			}
//			if (!"1".equals(lstGoods.getIsSerial()) && sgd.getSerial() != null) {
//				throw new IllegalArgumentException(
//						"Hàng không quản lý Serial không được nhập trường Serial");
//			}
//			obj.setOriginPrice(lstGoods.getOriginPrice());
//			obj.setTotalOriginPrice(lstGoods.getOriginPrice()*obj.getAmount());
//			id = shipmentGoodsBusinessImpl.save(obj);
//			ShipmentGoodsDetailDTO objS = new ShipmentGoodsDetailDTO();
//			if (id == 0)
//				break;
//			else {
//				objS.setShipmentGoodsId(id);
//				objS.setGoodsId(obj.getGoodsId());
//				objS.setGoodsName(obj.getGoodsName());
//				objS.setGoodsCode(obj.getGoodsCode());
//				if (obj.getManufacturerId() != null) {
//					objS.setManufacturerName(obj.getManufacturerName());
//					objS.setManufacturerId(obj.getManufacturerId());
//				} else {
//					objS.setManufacturerId(lstGoods.getManufacturerId());
//					objS.setManufacturerName(lstGoods.getManufacturerName());
//				}
//				if (obj.getProducingCountryId() != null) {
//					objS.setProducingCountryId(obj.getProducingCountryId());
//					objS.setProducingCountryName(obj.getProducingCountryName());
//				} else {
//					objS.setProducingCountryId(lstGoods.getProducingCountryId());
//					objS.setProducingCountryName(lstGoods
//							.getProducingCountryName());
//				}
//				objS.setUnitTypeId(obj.getUnitTypeId());
//				objS.setUnitTypeName(obj.getUnitTypeName());
//				objS.setSerial(obj.getSerial());
//				objS.setShipmentId(obj.getShipmentId());
//				objS.setPartNumber(obj.getPartNumber());
//				objS.setAmount(obj.getAmount());
//				shipmentGoodsDetailBusinessImpl.save(objS);
//			}
//		}
//		s.setShipmentId(lobj.get(0).getShipmentId());
//		s.setStatus("2");
//		this.updateStatus(s);
//		if (id == 0)
//			return 1l;
//		else
//			return 2l;
//	}
//
//	// End
//	// Hàm xử lý thêm mới lô hàng
//	public Long addShipment(ShipmentDTO obj) throws Exception {
//		boolean check = checkCode(obj.getCode(), null);
//		if (!check) {
//			throw new IllegalArgumentException("Mã lô hàng đã tồn tại");
//		}
//		return shipmentDAO.saveObject(obj.toModel());
//	}
//
//	// End
//	// Hàm xử lý cập nhật lô hàng
//	public Long updateShipment(ShipmentDTO obj, KttsUserSession objUser)
//			throws Exception {
//		if (!obj.getCreatedBy().equals(objUser.getSysUserId())) {
//			throw new IllegalArgumentException(
//					"Người dùng hiện tại không có quyền sửa bản ghi này");
//		}
//		boolean check = checkCode(obj.getCode(), obj.getShipmentId());
//		if (!check) {
//			throw new IllegalArgumentException(obj.getCode().toUpperCase());
//		}
//		return shipmentDAO.updateObject(obj.toModel());
//	}
//
//	// End
//	// Hàm xử lý Hủy lô hàng
//	public Long remove(ShipmentDTO obj, KttsUserSession objUser) {
//		if (!obj.getCreatedBy().equals(objUser.getSysUserId())) {
//			throw new IllegalArgumentException(
//					"Người dùng hiện tại không có quyền sửa bản ghi này");
//		}
//		return shipmentDAO.updateObject(obj.toModel());
//	}
//
//	// End
//	// Hàm xử lý Định lượng hàng hóa cho lô hàng
//	public Long updateShipmentGoods(ShipmentDTO obj, HttpServletRequest request)
//			throws Exception {
//		if (!VpsPermissionChecker.checkPermissionOnDomainData(
//				Constants.OperationKey.ESTIMATE,
//				Constants.AdResourceKey.SHIPMENT, obj.getCreatedDeptId(),
//				request)) {
//			throw new IllegalArgumentException(
//					"Bạn không có quyền định lượng cho lô hàng này ");
//		}
//		Long ids = update(obj);
//			if (ids != null) {
//			for (int i = 0; i < obj.getLstShipmentGoods().size(); i++) {
//				shipmentGoodsBusinessImpl.update(obj.getLstShipmentGoods().get(
//						i));
//			}
//		}
//		return ids;
//	}
//	// End
//}
