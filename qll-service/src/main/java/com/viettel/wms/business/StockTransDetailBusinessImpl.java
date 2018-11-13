//package com.viettel.wms.business;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFCell;
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
//import com.google.common.collect.Lists;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.bo.StockTransDetailBO;
//import com.viettel.wms.dao.StockTransDetailDAO;
//import com.viettel.wms.dto.CommonDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.OrderGoodsExelDTO;
//import com.viettel.wms.dto.SignVofficeDTO;
//import com.viettel.wms.dto.StockGoodsSerialDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//import com.viettel.wms.dto.StockTransDetailSerialDTO;
//import com.viettel.wms.utils.ValidateUtils;
//
//@Service("stockTransDetailBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class StockTransDetailBusinessImpl
//		extends
//		BaseFWBusinessImpl<StockTransDetailDAO, StockTransDetailDTO, StockTransDetailBO>
//		implements StockTransDetailBusiness {
//	static Logger LOGGER = LoggerFactory
//			.getLogger(StockTransDetailBusinessImpl.class);
//	List<OrderGoodsExelDTO> lstErrorOrder;
//	@Autowired
//	private StockTransDetailDAO stockTransDetailDAO;
//	@Autowired
//	private GoodsBusinessImpl goodsBusinessImpl;
//
//	@Value("${folder_upload}")
//	private String folder2Upload;
//
//	public StockTransDetailBusinessImpl() {
//		tModel = new StockTransDetailBO();
//		tDAO = stockTransDetailDAO;
//	}
//
//	@Override
//	public StockTransDetailDAO gettDAO() {
//		return stockTransDetailDAO;
//	}
//
//	@Override
//	public long count() {
//		return stockTransDetailDAO.count("StockTransDetailBO", null);
//	}
//
//	@Override
//	public DataListDTO doSearchGoodsForImportNote(StockTransDetailDTO obj) {
//		// TODO Auto-generated method stub
//		List<StockTransDetailDTO> ls = stockTransDetailDAO
//				.doSearchGoodsForImportNote(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//
//	public List<StockTransDetailDTO> doSearchGoodsForIExportNote(
//			StockTransDTO ob) {
//		return stockTransDetailDAO.doSearchGoodsForIExportNote(ob);
//	}
//
//	@Override
//	public List<StockTransDetailDTO> getGoodsInfoFromAlternativeStockByCode(
//			String code) {
//		// TODO Auto-generated method stub
//		return stockTransDetailDAO.getGoodsInfoFromAlternativeStockByCode(code);
//	}
//
//	// me
//	public StockTransDetailDTO getStockTransDetail(Long id) {
//		StockTransDetailDTO data = stockTransDetailDAO.getStockTransDetail(id);
//		return data;
//	}
//
//	/**
//	 * search thong tin
//	 */
//	@Override
//	public DataListDTO doSearchGoodsForExportNote(StockTransDetailDTO obj) {
//		// TODO Auto-generated method stub
//		List<StockTransDetailDTO> ls = stockTransDetailDAO
//				.doSearchGoodsForExportNote(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//
//	@Override
//	public boolean createNote(List<StockTransDetailDTO> os) {
//		for (StockTransDetailDTO o : os) {
//			if (save(o) == 0)
//				break;
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * import bản ghi excel thực xuất lên
//	 * @throws Exception 
//	 */
//	public List<StockTransDetailSerialDTO> exportStockTrans(String fileInput,
//			Long stockTransId) throws Exception {
//		StockTransDetailDTO detailDTO=new StockTransDetailDTO();
//		detailDTO.setStockTransId(stockTransId);
//		detailDTO.setGoodsIsSerial("1");
//		lstErrorOrder = new ArrayList<>();
//		List<StockTransDetailSerialDTO> workLst = Lists.newArrayList();
//		List<StockTransDetailDTO> stockTransDetailDTOs=stockTransDetailDAO.doSearchGoodsForIExportNote(detailDTO);
//		if(stockTransDetailDTOs.size()==0){
//			throw new IllegalArgumentException("Không cho phép import Serial với loại phiếu xuất chỉ có vật tư");
//		}
//		try {
//
//			File f = new File(fileInput);
//
//			XSSFWorkbook workbook = new XSSFWorkbook(f);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//
//			DataFormatter formatter = new DataFormatter();
//			int count = 0;
//			for (Row row : sheet) {
//				count++;
//				if (count >= 3 && !ValidateUtils.checkIfRowIsEmpty(row)) {
//						//boolean checkColumn1 = true;
//						boolean checkColumn3 = true;
//						//boolean checkColumn4 = true;
//						boolean checkTotal = true;
//						//String code = "";
//						String serial = "";
//						//String status = "";
//
//					for (Cell cell : row) {
//
//						// Create object OrderGoodsExelDTO
//						OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
//						// if (cell.getColumnIndex() == 1) {
//						// code = formatter.formatCellValue(cell);
//						// checkColumn1 = checkDataFromFileExel(code,
//						// count, cell.getColumnIndex(),
//						// orderErrorFormat, stockTransId);
//						// } else
//						List<StockTransDetailSerialDTO> detailSerialDTOs = Lists
//								.newArrayList();
//						if (cell.getColumnIndex() == 3) {
//							serial = formatter.formatCellValue(cell);
//							checkColumn3 = checkDataFromFileExel(serial, count,
//									cell.getColumnIndex(), orderErrorFormat,
//									stockTransId);
//							if (StringUtils.isNotEmpty(serial)) {
//
//								detailSerialDTOs = stockTransDetailDAO
//										.getListStockGoodSerialBySerial(serial);
//								if (detailSerialDTOs.size() == 0) {
//									orderErrorFormat.setLineError(String
//											.valueOf(count));
//									orderErrorFormat.setColumnError(String
//											.valueOf(3));
//									orderErrorFormat
//											.setDetailError("Serial phải nằm trong kho xuất có status =1 ");
//									lstErrorOrder.add(orderErrorFormat);
//								} else {
//									//int count1 = 0;
//									for (StockTransDetailSerialDTO detailSerialDTO : detailSerialDTOs) {
//										checkTotal = checkDataSerial(
//												detailSerialDTO.getGoodsCode(),
//												detailSerialDTO.getGoodState(),
//												stockTransId, orderErrorFormat,
//												count);
//
//										if (checkTotal) {
//											//count1++;
//											
//											workLst.add(detailSerialDTO);
//
//										}
//
//										if (count == 0) {
//											orderErrorFormat
//													.setLineError(String
//															.valueOf(count));
//											orderErrorFormat
//													.setColumnError(String
//															.valueOf(3));
//											orderErrorFormat
//													.setDetailError("Serial nhập lên phải là serial của những mã hàng trong phiếu có số lượng xuất >0 ");
//											lstErrorOrder.add(orderErrorFormat);
//										}
//									}
//								}
//							}
//
//						}
//
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
//		for(StockTransDetailDTO dto:stockTransDetailDTOs){
//			for(StockTransDetailSerialDTO work:workLst){
//				if(dto.getGoodsCode().equals(work.getGoodsCode()) && dto.getGoodsState().equals(work.getGoodState())){
//					dto.getListDetailSerial().add(work);
//				}
//			}
//			
//			if(dto.getAmountReal().equals(dto.getListDetailSerial().size())){
//				throw new IllegalArgumentException("Hàng hóa "+dto.getGoodsCode()+" chưa được import đủ số lượng Serial!");
//			}
//		}
//		String filePathError = UEncrypt.encryptFileUploadPath(fileInput);
//		StockTransDetailSerialDTO objError = new StockTransDetailSerialDTO();
//		objError.setLstErrorOrderGoods(lstErrorOrder);
//		objError.setFilePathError(filePathError);
//		workLst.add(objError);
//		
//		
//		
//		return workLst;
//	}
//
//	/**
//	 * validate file import
//	 * @param data
//	 * @param rowIndex
//	 * @param columnIndex
//	 * @param orderErrorFormat
//	 * @return
//	 */
//
//	private boolean checkDataSerial(String goodsCode, String goodsState,
//			Long stockTransId, OrderGoodsExelDTO orderErrorFormat,int rowIndex) {
//		boolean check = true;
//		List<BigDecimal> amounts = stockTransDetailDAO.checkGoods(goodsCode,
//				goodsState, stockTransId);
//		if (amounts.size() == 0) {
//			check = false;
//		} else {
//			for (BigDecimal amount : amounts) {
//				Long num = (amount != null ? amount.longValue() : 0l);
//				if (num == 0l) {
//					check = false;
//				}
//			}
//		}
//		
//		return check;
//	}
//
//	private boolean checkDataFromFileExel(String data, int rowIndex,
//			int columnIndex, OrderGoodsExelDTO orderErrorFormat,
//			Long stockTransId) {
//
//			
//			if (columnIndex == 3) {
//			//
//			StockGoodsSerialDTO dto = new StockGoodsSerialDTO();
//			dto.setSerial(data.trim().toUpperCase());
//			Long count = stockTransDetailDAO.checkSearialGood(dto);
//			
//			if(!StringUtils.isNotEmpty(data)){
//				
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat
//						.setDetailError("Serial không được để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			
//			// /
//			if (count == 0l) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat
//						.setDetailError("Serial phải nằm trong kho xuất có status =1");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//
//		}
//
//		return true;
//	}
//
//	//
//
//	/**
//	 * xu ly import update -->huy
//	 */
//	@Override
//	public List<StockTransDetailDTO> importUpdateStockTrans(String fileInput,
//			Long orderId) throws Exception {
//
//		lstErrorOrder = new ArrayList<>();
//		List<StockTransDetailDTO> workLst = Lists.newArrayList();
//		try {
//
//			File f = new File(fileInput);
//
//			XSSFWorkbook workbook = new XSSFWorkbook(f);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//
//			DataFormatter formatter = new DataFormatter();
//			int count = 0;
//			ArrayList<String> listGoodsCode_GoodsState =new  ArrayList<String>();
//			for (Row row : sheet) {
//				count++;
//				if (count >= 3 && !ValidateUtils.checkIfRowIsEmpty(row)) {
//
//					boolean checkColumn1 = true;
//					boolean checkColumn4 = true;
//					boolean checkColumn5 = true;
//					boolean checkColumn3 = true;
//					String code = "";
//					String amountReal = "";
//					String goodsState = "";
//					String amount = "";
//					StockTransDetailDTO orderGood = new StockTransDetailDTO();
//					for (Cell cell : row) {
//
//						// Create object OrderGoodsExelDTO	
//						if (cell.getColumnIndex() == 1) {
//							code = formatter.formatCellValue(cell);
//						} else if (cell.getColumnIndex() == 3) {
//							amount = formatter.formatCellValue(cell);
//						}else if (cell.getColumnIndex() == 4) {
//							amountReal = formatter.formatCellValue(cell);
//						}  else if (cell.getColumnIndex() == 5) {
//							goodsState = formatter.formatCellValue(cell);
//							listGoodsCode_GoodsState.add(code +"_"+ goodsState);
//						}
//					}
//					if (code == "" && amount == ""
//							&& amountReal == "" && goodsState == "") {
//
//					} else {
//						OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
//						checkColumn1 = checkDataFromFileExelUpdate("", "",
//								code, count, 1,
//								orderErrorFormat, orderId);
//						checkColumn3 = checkDataFromFileExelUpdate("", "",
//								amount, count, 3,
//								orderErrorFormat, orderId);
//						if(code != null && goodsState != null){
//							orderGood = stockTransDetailDAO.doSearchGoodsForFileImport(orderId, code, goodsState);
//							StockTransDetailDTO dto = new StockTransDetailDTO();
//							dto.setGoodsCode(code.trim());
//							dto.setGoodsState(goodsState);
//							boolean ChkGoodCode = stockTransDetailDAO.checkStockCode(dto,
//									orderId);
//
//							if (!ChkGoodCode) {
//								OrderGoodsExelDTO obj = new OrderGoodsExelDTO();
//								obj.setLineError(String.valueOf(count));
//								obj.setColumnError(String.valueOf(1));
//								obj.setDetailError("Mã '" + code +"' trạng thái '"+ goodsState
//										+ "' không tồn tại trong yêu cầu");
//								lstErrorOrder.add(obj);
//							}
//						}
//						if(orderGood != null){
//						checkColumn4 = checkDataFromFileExelUpdate(code,
//								String.valueOf(orderGood.getAmountOrder().intValue()), amountReal, count,
//								4, orderErrorFormat,
//								orderId);
//						}
//						checkColumn5 = checkDataFromFileExelUpdate("", "",
//								goodsState, count, 5,
//								orderErrorFormat, orderId);
//						
//					}if(checkColumn1 == true){
//						int countGoodsCode = 0;
//						for(int i = 0;i<listGoodsCode_GoodsState.size();i++){
//							if((code+"_"+goodsState).equals(listGoodsCode_GoodsState.get(i))){
//								countGoodsCode = countGoodsCode + 1;
//							}
//						}
//						if(countGoodsCode != 1){
//							OrderGoodsExelDTO obj = new OrderGoodsExelDTO();
//							obj.setLineError(String.valueOf(count));
//							obj.setColumnError(String.valueOf(1));
//							obj.setDetailError("Mã hàng đã tồn tại trong yêu cầu");
//							lstErrorOrder.add(obj);
//						}
//					}
//					if (checkColumn5 == true && checkColumn4 == true
//							&& checkColumn1 == true && checkColumn3 == true) {
//						StockTransDetailDTO dto = new StockTransDetailDTO();
//						dto.setGoodsCode(row.getCell(1).getStringCellValue()
//								.trim());
//						dto.setGoodsName(orderGood.getGoodsName());
//						dto.setAmountOrder(orderGood.getAmountOrder());
//						dto.setAmountReal(Double
//								.parseDouble(amountReal));
//						dto.setGoodsState(goodsState);
//						dto.setGoodsType(orderGood.getGoodsType());
//						dto.setGoodsTypeName(orderGood.getGoodsTypeName());
//						dto.setGoodsUnitId(orderGood.getGoodsUnitId());
//						dto.setGoodsUnitName(orderGood.getGoodsUnitName());
//						workLst.add(dto);
//
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
//		StockTransDetailDTO objError = new StockTransDetailDTO();
//		objError.setLstErrorOrderGoods(lstErrorOrder);
//		objError.setFilePathError(filePathError);
//		for(int i=0;i<objError.getLstErrorOrderGoods().size();i++){
//            for(int j=i+1;j<objError.getLstErrorOrderGoods().size();j++){
//                if(objError.getLstErrorOrderGoods().get(j).getLineError()==objError.getLstErrorOrderGoods().get(i).getLineError()&&
//                        objError.getLstErrorOrderGoods().get(i).getColumnError()==objError.getLstErrorOrderGoods().get(j).getColumnError()){
//                    objError.getLstErrorOrderGoods().remove(objError.getLstErrorOrderGoods().get(j));
//                }
//            }
//        }
//		workLst.add(objError);
//
//		return workLst;
//	}
//
//	/**
//	 * Validate file import cho update quan ly phieu xuat kho-->huy
//	 * 
//	 * @param amount
//	 * @param data
//	 * @param rowIndex
//	 * @param columnIndex
//	 * @param orderErrorFormat
//	 * @return
//	 */
//	public boolean checkDataFromFileExelUpdate(String code, String amount,
//			String data, int rowIndex, int columnIndex,
//			OrderGoodsExelDTO orderErrorFormat, Long orderId) {
//		if (columnIndex == 1) {
//			StockTransDetailDTO dto = new StockTransDetailDTO();
//			dto.setGoodsCode(data.trim());
//			boolean ChkGoodCode = stockTransDetailDAO.checkStockCode(dto,
//					orderId);
//
//			if (!ChkGoodCode) {
//				/*orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Mã " + data
//						+ " không tồn tại trong yêu cầu");
//				lstErrorOrder.add(orderErrorFormat);*/
//				return false;
//			}
//		} else if (columnIndex == 4) {
//
//			boolean check = stockTransDetailDAO
//					.serialOfGoodsCode(code, orderId);
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Số lượng xuất đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (!StringUtils.isNumeric(data)) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat
//						.setDetailError("Số lượng xuất không phải kiểu số");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			} else if (check) {
//				if (Integer.parseInt(data) != Integer.parseInt(amount)) {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat
//							.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat
//							.setDetailError("Số lượng xuất = Số lượng yêu cầu với mặt hàng có quản lý serial ");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//			} else {
//				if (StringUtils.isNumeric(amount)) {
//					if (!(0 <= Integer.parseInt(data) && Integer.parseInt(data) <= Integer
//							.parseInt(amount))) {
//						orderErrorFormat.setLineError(String.valueOf(rowIndex));
//						orderErrorFormat.setColumnError(String
//								.valueOf(columnIndex));
//						orderErrorFormat
//								.setDetailError("Số lượng xuất phải <= lượng yêu cầu và số lượng yêu cầu phải lớn >= 0");
//						lstErrorOrder.add(orderErrorFormat);
//						return false;
//					}
//				} else {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat
//							.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat
//							.setDetailError("Số lượng yêu cầu phải nhập số nguyên");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//			}
//		} else if (columnIndex == 5) {
//			if (!data.equals("1") && !data.equals("2")) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Sai trạng thái nhập vào");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		}
//		return true;
//	}
//
//	/**
//	 * tao phieu xuat - xuat file excel loi--> anhlt
//	 */
//	@Override
//	public String exportStockTransExcelError(GoodsDTO errorObj)
//			throws Exception {
//		String filePath = UEncrypt.decryptFileUploadPath(errorObj.getFilePathError());
//		InputStream file = new BufferedInputStream(new FileInputStream(filePath));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(0);
//		
//		for(int i = 0; i<errorObj.getLstErrorGoods().size();i++){
//			XSSFRow row = sheet.getRow(Integer.parseInt(errorObj.getLstErrorGoods().get(i).getLineError())-1);
//			if(row ==null){
//				row = sheet.createRow(Integer.parseInt(errorObj.getLstErrorGoods().get(i).getLineError())-1);
//			}
//			XSSFCell cell = row.getCell(7);
//			
//			if(cell ==null){
//				cell = row.createCell(7);
//			}
//			if(!cell.getStringCellValue().isEmpty()){
//				cell.setCellValue(cell.getStringCellValue()+","+errorObj.getLstErrorGoods().get(i).getDetailError());
//			}else{
//				cell.setCellValue(errorObj.getLstErrorGoods().get(i).getDetailError());
//			}
//		}
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar + "2.XK_TaoPhieuXuat.xlsx");
//		
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//		
//		String path = UEncrypt.encryptFileUploadPath("2.XK_TaoPhieuXuat.xlsx");
//		return path;
//	}
//
//	public List<StockTransDetailDTO> getListStockTransDetail(Long id) {
//		List<StockTransDetailDTO> listReturn = Lists.newArrayList();
//		List<StockTransDetailDTO> ls = stockTransDetailDAO
//				.getListStockTransDetail(id);
//		StockTransDetailDTO ab = new StockTransDetailDTO();
//		for (Iterator<StockTransDetailDTO> interator = ls.iterator(); interator
//				.hasNext();) {
//			StockTransDetailDTO wi = interator.next();
//
//			if (ab.getStockTransDetailId() == null) {
//				ab = wi;
//				listReturn.add(ab);
//
//			}
//			if (ab.getStockTransDetailId()
//					.compareTo(wi.getStockTransDetailId()) != 0) {
//				ab = wi;
//				listReturn.add(ab);
//			}
//			if (ab.getStockTransDetailId()
//					.compareTo(wi.getStockTransDetailId()) == 0 && StringUtils.isNotEmpty(wi.getSerial())) {
//				StockTransDetailSerialDTO stockTransDetailSerialDTO = new StockTransDetailSerialDTO();
//				stockTransDetailSerialDTO.setStockTransDetailId(wi
//						.getStockTransDetailId());
//				stockTransDetailSerialDTO.setStockTransDetailSerialId(wi
//						.getStockTransDetailSerialId());
//				stockTransDetailSerialDTO.setStockTransId(wi.getStockTransId());
//				stockTransDetailSerialDTO.setGoodsId(wi.getGoodsId());
//				stockTransDetailSerialDTO.setSerial(wi.getSerial());
//				stockTransDetailSerialDTO.setContractCode(wi.getContractCode());
//				stockTransDetailSerialDTO.setPartNumber(wi.getPartNumber());
//				stockTransDetailSerialDTO.setManufacturerName(wi
//						.getManufacturerName());
//				stockTransDetailSerialDTO.setManufacturerId(wi
//						.getManufacturerId());
//				stockTransDetailSerialDTO.setProducingCountryId(wi
//						.getProducingCountryId());
//				stockTransDetailSerialDTO.setProducingCountryName(wi
//						.getProducingCountryName());
//				stockTransDetailSerialDTO.setPrice(wi.getPrice());
//				stockTransDetailSerialDTO.setCellCode(wi.getCellCode());
//
//				ab.getListDetailSerial().add(stockTransDetailSerialDTO);
//			}
//		}
//
//		return listReturn;
//	}
//	@Override
//	public String exportExcelTemplate(StockTransDetailDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		
//		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//		String filePath = classloader.getResource("../" + "doc-template").getPath();
//		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "2.XK_TaoPhieuXuat.xlsx"));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(0);
//		
//		List<StockTransDetailDTO> lstOrderGoods = stockTransDetailDAO.doSearchGoodsForExportNote(obj);
//		
//		for(int i=0;i<lstOrderGoods.size();i++){
//			XSSFRow row = sheet.getRow(i+2);
//			XSSFCell cell1 = row.createCell(1);
//			cell1.setCellValue(lstOrderGoods.get(i).getGoodsCode());
//			XSSFCell cell2 = row.createCell(2);
//			cell2.setCellValue(lstOrderGoods.get(i).getGoodsName());
//			XSSFCell cell3 = row.createCell(3);
//			cell3.setCellValue(lstOrderGoods.get(i).getAmountOrder());
//			XSSFCell cell4 = row.createCell(4);
//			cell4.setCellValue(lstOrderGoods.get(i).getAmountReal());
//			XSSFCell cell5 = row.createCell(5);
//			cell5.setCellValue(lstOrderGoods.get(i).getGoodsState());
//		}
//		
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar + "2.XK_TaoPhieuXuat.xlsx");
//		
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//		
//		String path = UEncrypt.encryptFileUploadPath("2.XK_TaoPhieuXuat.xlsx");
//		return path;
//	}
//}
