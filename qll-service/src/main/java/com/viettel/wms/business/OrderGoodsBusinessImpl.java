//package com.viettel.wms.business;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
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
//import com.viettel.wms.bo.OrderGoodsBO;
//import com.viettel.wms.dao.GoodsTypeDAO;
//import com.viettel.wms.dao.ManufacturerDAO;
//import com.viettel.wms.dao.OrderDAO;
//import com.viettel.wms.dao.OrderGoodsDAO;
//import com.viettel.wms.dao.OrderGoodsDetailDAO;
//import com.viettel.wms.dao.ProducingCountryDAO;
//import com.viettel.wms.dao.StockTransDetailDAO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.GoodsTypeDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;
//import com.viettel.wms.dto.OrderDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDetailDTO;
//import com.viettel.wms.dto.OrderGoodsExelDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//import com.viettel.wms.dto.StockTransDetailSerialDTO;
//import com.viettel.wms.utils.ValidateUtils;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//
//import org.apache.commons.lang3.StringUtils;
//
//
//@Service("orderGoodsBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class OrderGoodsBusinessImpl extends BaseFWBusinessImpl<OrderGoodsDAO, OrderGoodsDTO, OrderGoodsBO> implements OrderGoodsBusiness {
//	static Logger LOGGER = LoggerFactory.getLogger(OrderGoodsBusinessImpl.class);
//	@Autowired
//	private OrderGoodsDAO orderGoodsDAO;
//	List<OrderGoodsExelDTO> lstErrorOrder;
//	private GoodsDTO objGoods;
//	
//	@Autowired
//	private OrderDAO orderDAO;
//
//	@Autowired
//	private GoodsTypeDAO goodsTypeDAO;
//    @Autowired
//    private ManufacturerDAO manufacturerDAO;
//    
//    @Autowired
//    private ProducingCountryDAO producingCountryDAO;
//	@Autowired
//	private OrderGoodsDetailDAO orderGoodsDetailDAO;
//	@Autowired
//	private StockTransDetailDAO stockTransDetailDAO;
//	@Autowired
//	private GoodsBusinessImpl goodsBusinessImpl;
//
//	@Value("${folder_upload}")
//	private String folder2Upload;
//
//	public OrderGoodsBusinessImpl() {
//		tModel = new OrderGoodsBO();
//		tDAO = orderGoodsDAO;
//	}
//
//	@Override
//	public OrderGoodsDAO gettDAO() {
//		return orderGoodsDAO;
//	}
//	
//	
//
//	@Override
//	public long count() {
//		return orderGoodsDAO.count("OrderGoodsBO", null);
//	}
//
//	//Tim kieu hang hoa cho yeu cau - NgocCX
//	@Override
//	public DataListDTO doSearchGoodsForImportReq(OrderGoodsDTO obj) {
//		// TODO Auto-generated method stub
//		List<OrderGoodsDTO> ls = orderGoodsDAO.doSearchGoodsForImportReq(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//
//	//Kiem tra row co du lieu khong
//	public static boolean isRowEmpty(Row row) {
//		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
//			Cell cell = row.getCell(c);
//			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
//				return false;
//		}
//		return true;
//	}
//
//	//Import vi tri - NgocCX
//	@Override
//	@Transactional
//	public List<OrderGoodsDetailDTO> importCells(String fileInput, Long id) throws Exception {
//		lstErrorOrder = new ArrayList<>();
//		List<OrderGoodsDetailDTO> workLst = Lists.newArrayList();
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
//				if(!ValidateUtils.checkIfRowIsEmpty(row)){
//				count++;
//				if (count >= 3) {
//
//					boolean checkColumn1 = true;
//					boolean checkColumn4 = true;
//					boolean checkColumn5 = true;
//					String code = "";
//					String serial = "";
//					String amount = "";
//					String cellCode = "";
//
//					for (Cell cell : row) {
//
//						// Create object OrderGoodsExelDTO
//						OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
//
//						// Check format file exel
//						if (cell.getColumnIndex() == 1) {
//							code = formatter.formatCellValue(cell);
//							checkColumn1 = checkDataFromFileExel(code.trim(), count, cell.getColumnIndex(), orderErrorFormat);
//						} else if (cell.getColumnIndex() == 3) {
//							serial = formatter.formatCellValue(cell);
//						} else if (cell.getColumnIndex() == 4) {
//							amount = formatter.formatCellValue(cell);
//							checkColumn4 = checkDataFromFileExel(amount.trim(), count, cell.getColumnIndex(),
//									orderErrorFormat);
//						} else if (cell.getColumnIndex() == 5) {
//							cellCode = formatter.formatCellValue(cell);
//							checkColumn5 = checkDataFromFileExel(cellCode.trim(), count, cell.getColumnIndex(),
//									orderErrorFormat);
//						}
//
//					}
//					
//					
//					if(!checkDataFromFileExelInDB(code, serial, id)){
//						OrderGoodsExelDTO orderErrorFormat1 = new OrderGoodsExelDTO();
//						orderErrorFormat1.setLineError(String.valueOf(count));
//						orderErrorFormat1.setDetailError("Không có hàng hóa cho mã hàng và serial này");
//						lstErrorOrder.add(orderErrorFormat1);
//					}
//
//					if (checkColumn1 && checkColumn4 && checkColumn5) {
//
//						
//						OrderGoodsDetailDTO newObj = new OrderGoodsDetailDTO();
//						newObj.setGoodsCode(String.valueOf(formatter.formatCellValue(row.getCell(1))).trim());
//						if(!String.valueOf(formatter.formatCellValue(row.getCell(3))).isEmpty()){
//							newObj.setSerial(String.valueOf(formatter.formatCellValue(row.getCell(3))).trim());
//						}
//						if(String.valueOf(formatter.formatCellValue(row.getCell(3))).trim().isEmpty()){
//							newObj.setSerial(null);
//						}
//						newObj.setAmount(Double.parseDouble(String.valueOf(formatter.formatCellValue(row.getCell(4))).trim()));
//						newObj.setCellCode(String.valueOf(formatter.formatCellValue(row.getCell(5))).trim());
//						
//						GoodsDTO gDto = new GoodsDTO();
//						gDto = goodsBusinessImpl.getGoodByCode(newObj.getGoodsCode().trim());
//						newObj.setGoodsName(gDto.getName());
//						newObj.setGoodsId(gDto.getGoodsId());
//						workLst.add(newObj);
//
//					}
//
//				}
//			}
//			}
//			workbook.close();
//			
//			
//
//		} catch (NullPointerException pointerException) {
//			// pointerException.printStackTrace();
//			LOGGER.error(pointerException.getMessage(), pointerException);
//		} catch (Exception e) {
//			// e.printStackTrace();
//			LOGGER.error(e.getMessage(), e);
//		}
//		
//		
//		List<OrderGoodsDTO> listGoodsForOrder =getGoodsDetail(id);
//
//		String err="";
//		for(int i = 0; i<listGoodsForOrder.size();i++){
//			Double x = listGoodsForOrder.get(i).getAmount();
//			Double amount=0D;
//		OrderGoodsDetailDTO	ObjDetail=listGoodsForOrder.get(i).getListOrderGoodsDetailDTO().get(0);
//			for(int j = 0;j<workLst.size();j++){
//				if(listGoodsForOrder.get(i).getGoodsCode().equals(workLst.get(j).getGoodsCode())){
//					amount=amount+workLst.get(j).getAmount();
//					workLst.get(j).setManufacturerId(ObjDetail.getManufacturerId());
//					workLst.get(j).setManufacturerName(ObjDetail.getManufacturerName());
//					workLst.get(j).setProducingCountryId(ObjDetail.getProducingCountryId());
//					workLst.get(j).setProducingCountryName(ObjDetail.getProducingCountryName());
//					workLst.get(j).setPartNumber(ObjDetail.getPartNumber());
//					workLst.get(j).setContractCode(ObjDetail.getContractCode());
//					workLst.get(j).setOrderId(id);
//					workLst.get(j).setOrderGoodsId(ObjDetail.getOrderGoodsId());
//					workLst.get(j).setPrice(ObjDetail.getPrice());
//				}
//			}
//			
//			
//			
//			if(amount>x){
//					if(StringUtils.isNotEmpty(err)){
//						err=err+";"+listGoodsForOrder.get(i).getGoodsCode();
//					} else {
//					err=listGoodsForOrder.get(i).getGoodsCode();
//					}
//				} else if(amount<x){
//					OrderGoodsDetailDTO detailDTO=new OrderGoodsDetailDTO();
//					if(listGoodsForOrder.get(i).getListOrderGoodsDetailDTO().size()==0){
//						detailDTO.setCellCode(null);
//						detailDTO.setOrderId(id);
//						detailDTO.setGoodsCode(listGoodsForOrder.get(i).getGoodsCode());
//						detailDTO.setGoodsId(listGoodsForOrder.get(i).getGoodsId());
//						detailDTO.setGoodsName(listGoodsForOrder.get(i).getGoodsName());
//						detailDTO.setOrderGoodsId(listGoodsForOrder.get(i).getOrderGoodsId());
//						workLst.add(detailDTO);
//					} else {
//						workLst.add(listGoodsForOrder.get(i).getListOrderGoodsDetailDTO().get(0));
//					}
//				} 
//		}
//		
//		if(StringUtils.isNotEmpty(err)){
//			throw new IllegalArgumentException("Hàng hóa "+err+" vượt quá số lượng trong yêu cầu!");
//		}
//		
//		String filePathError = UEncrypt.encryptFileUploadPath(fileInput);
//		OrderGoodsDetailDTO objError = new OrderGoodsDetailDTO();
//		objError.setLstErrorOrderGoods(lstErrorOrder);
//		objError.setFilePathError(filePathError);
//		
//		
//		workLst.add(objError);
//
//		return workLst;
//	}
//
//	//Kiem tr du lieu trong file excel - import vi tri - NgocCX
//	public boolean checkDataFromFileExel(String data, int rowIndex, int columnIndex,
//			OrderGoodsExelDTO orderErrorFormat) {
//		if (columnIndex == 1) {
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Mã hàng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		} else if (columnIndex == 4) {
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Số lượng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (!StringUtils.isNumeric(data.replace(".", ""))) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Số lượng không phải kiểu số");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		} else {
//			if (columnIndex == 5) {
//				if (data.isEmpty()) {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Mã vị trí đang để trống");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	public boolean checkDataFromFileExelInDB(String code, String serial, Long id) {
//		List<OrderGoodsDetailDTO> lstGoods = orderGoodsDetailDAO.getGoodsByCodeAndSerial(code.trim(), serial.trim(), id);
//		if (lstGoods.isEmpty()) {
//			return false;
//		}
//		return true;
//	}
//
//	/*@Override
//	public DataListDTO doSearchGoodsForExportOrder(OrderGoodsDTO obj) {
//		List<OrderGoodsDTO> ls = orderGoodsDAO.doSearchGoodsForExportOrder(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}*/
//
//	
//	//get good for ExportOrder
//	@Override
//	public DataListDTO doSearchGoodsForExportOrder(OrderGoodsDTO obj) {
//		List<OrderGoodsDTO> listReturn = Lists.newArrayList();
//		List<OrderGoodsDTO> ls = orderGoodsDAO.doSearchGoodsForExportOrder(obj);
//		OrderGoodsDTO ab = new OrderGoodsDTO();
//		for (Iterator<OrderGoodsDTO> interator = ls.iterator(); interator
//				.hasNext();) {
//			OrderGoodsDTO wi = interator.next();
//
//			if (ab.getOrderGoodsId() == null) {
//				ab = wi;
//				listReturn.add(ab);
//
//			}
//			if (ab.getOrderGoodsId()
//					.compareTo(wi.getOrderGoodsId()) != 0) {
//				ab = wi;
//				listReturn.add(ab);
//			}
//			if (ab.getOrderGoodsId()
//					.compareTo(wi.getOrderGoodsId()) == 0 && StringUtils.isNotEmpty(wi.getSerial())) {
//				OrderGoodsDetailDTO orderGoodsDetailDTO = new OrderGoodsDetailDTO();
//				orderGoodsDetailDTO.setOrderGoodsId(wi
//						.getOrderGoodsId());
//				orderGoodsDetailDTO.setOrderGoodsDetailId(wi
//						.getOrderGoodsDetailId());
//				orderGoodsDetailDTO.setOrderId(wi.getOrderId());
//				orderGoodsDetailDTO.setGoodsId(wi.getGoodsId());
//				orderGoodsDetailDTO.setSerial(wi.getSerial());
//				orderGoodsDetailDTO.setContractCode(wi.getContractCode());
//				orderGoodsDetailDTO.setPartNumber(wi.getPartNumber());
//				orderGoodsDetailDTO.setManufacturerName(wi
//						.getManufacturerName());
//				orderGoodsDetailDTO.setManufacturerId(wi
//						.getManufacturerId());
//				orderGoodsDetailDTO.setProducingCountryId(wi
//						.getProducingCountryId());
//				orderGoodsDetailDTO.setProducingCountryName(wi
//						.getProducingCountryName());
//				orderGoodsDetailDTO.setPrice(wi.getPrice());
//
//				ab.getListDetailSerial().add(orderGoodsDetailDTO);
//			}
//		}
//		DataListDTO data = new DataListDTO();
//		   data.setData(listReturn);
//		   data.setTotal(obj.getTotalRecord());
//		   data.setSize(obj.getTotalRecord());
//		   data.setStart(1);
//		return data;
//	}
//	//import hang hoa cho xuat yeu cau
//	@Override
//	public List<OrderGoodsDTO> importGoods(String fileInput) throws Exception {
//
//
//		lstErrorOrder = new ArrayList<>();
//		List<OrderGoodsDTO> workLst = Lists.newArrayList();
//		try {
//
//			File f = new File(fileInput);
//
//			XSSFWorkbook workbook = new XSSFWorkbook(f);
//			XSSFSheet sheet = workbook.getSheetAt(0);
//			
//			DataFormatter formatter = new DataFormatter();
//			int count = 0;
//			int a=1;
//			for (Row row : sheet) {
//				count++;
//				// Create object OrderGoodsExelDTO
//				OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
//				if (count >= 3) {
//					
//					boolean checkColumn1 = true;
//					boolean checkColumn3 = true;
//					boolean checkColumn4 = true;
//					boolean checkColumn5 = true;
//					/*String goodsName="";
//					String goodsUnitName=""; 
//					String code = "";
//					String serial = "";
//					String newGoodsCode="";
//					String newSerial="";*/
//					String code = "";
//					String serial = "";
//					String amount = "";
//					String goodsState = "";
//					int lag =0;
//					for (Cell cell : row) {
//
//						// Check format file exel
//						if (cell.getColumnIndex() == 1) {
//							code = formatter.formatCellValue(cell).toUpperCase().trim();
//						}else if(cell.getColumnIndex()==3){
//							serial = formatter.formatCellValue(cell);
//						}
//						else if (cell.getColumnIndex() == 4) {
//							amount = formatter.formatCellValue(cell);
//						}else if(cell.getColumnIndex() == 5){
//							goodsState = formatter.formatCellValue(cell).toUpperCase().trim();
//							
//						}
//
//					}
//					
//					if(count==2){
//						if(code==""&&serial==""&&amount==""&&goodsState==""){
//							orderErrorFormat.setLineError(String.valueOf(count));
//							orderErrorFormat.setColumnError(String.valueOf(1));
//							orderErrorFormat.setDetailError("File excel trống");
//							lstErrorOrder.add(orderErrorFormat);
//						}
//					}if(code==""&&serial==""&&amount==""&&goodsState==""){
//						
//					}
//					else{
//						checkColumn1 =  checkDataFromFileExelNK(code.trim(), count, 1, orderErrorFormat);
//						//checkColumn3 = checkDataFromFileExelNK(serial, count, 3, orderErrorFormat);
//						GoodsDTO goodDto = new GoodsDTO();
//						goodDto = goodsBusinessImpl.getGoodByCode(code.trim());
//						if(goodDto != null){
//						if(goodDto.getIsSerial().equals("1")){
//							checkColumn3 = checkDataFromFileExelNK(serial.trim(), count, 3,
//									orderErrorFormat);
//						}
//						}
//						checkColumn4 = checkDataFromFileExelNK(amount.trim(), count, 4, orderErrorFormat);
//
//						checkColumn5 = checkDataFromFileExelNK(goodsState.trim(), count, 5, orderErrorFormat);
//						
//						if (checkColumn1 == true &&checkColumn3 == true && checkColumn4 == true
//								&& checkColumn5 == true) {
//							
//							OrderGoodsDTO newObj = new OrderGoodsDTO();
//							GoodsDTO gDto = new GoodsDTO();
//
//							newObj.setGoodsCode(code);
//							gDto = goodsBusinessImpl.getGoodByCode(newObj.getGoodsCode());
//
//							newObj.setGoodsName(gDto.getName());
//							if(goodDto.getIsSerial().equals("1")){
//								newObj.setManufacturerId(gDto.getManufacturerId());
//								newObj.setProducingCountryId(gDto.getProducingCountryId());
//								if(newObj.getManufacturerId()!=null){
//									newObj.setManufacturerName(manufacturerDAO.getAllNameById(newObj.getManufacturerId()).getName());
//								}
//								if(newObj.getProducingCountryId()!=null){
//									newObj.setProducingCountryName(producingCountryDAO.getAllNameById(newObj.getProducingCountryId()).getName());
//								}
//								newObj.setSerial(serial);
//
//							}
//							
//							//newObj.setPartNumber(String.valueOf(Math.round(row.getCell(5).getNumericCellValue())));
//							//newObj.setContractNumber(String.valueOf(row.getCell(6).getNumericCellValue()));
//							newObj.setGoodsIsSerial(gDto.getIsSerial());
//							newObj.setAmount(Double.parseDouble(amount));
//							newObj.setGoodsState(goodsState);
//							newObj.setGoodsUnitName(gDto.getGoodsUnitName());
//							newObj.setGoodsUnitId(gDto.getGoodsUnitId());
//							newObj.setGoodsType(gDto.getGoodsType());
//							workLst.add(newObj);
//						}
//
//					}
//
//				}else if(sheet.getLastRowNum()<3){
//					orderErrorFormat.setLineError(String.valueOf(count));
//					orderErrorFormat.setColumnError(String.valueOf(1));
//					orderErrorFormat.setDetailError("File excel không đúng");
//					lstErrorOrder.add(orderErrorFormat);
//				}
//				
//			}
//			
//			
//			workbook.close();
//
//		} catch (NullPointerException pointerException) {
//			// pointerException.printStackTrace();
//			LOGGER.error(pointerException.getMessage(), pointerException);
//		}catch (Exception e) {
//			// e.printStackTrace();
//			LOGGER.error(e.getMessage(), e);
//		}
//		
//		String filePathError = UEncrypt.encryptFileUploadPath(fileInput);
//		OrderGoodsDTO objError = new OrderGoodsDTO();
//		objError.setLstErrorGoods(lstErrorOrder);
//		objError.setFilePathError(filePathError);
//		for(int i=0;i<objError.getLstErrorGoods().size();i++){
//			for(int j=i+1;j<objError.getLstErrorGoods().size();j++){
//				if(objError.getLstErrorGoods().get(j).getLineError()==objError.getLstErrorGoods().get(i).getLineError()&&
//						objError.getLstErrorGoods().get(i).getColumnError()==objError.getLstErrorGoods().get(j).getColumnError()){
//					objError.getLstErrorGoods().remove(objError.getLstErrorGoods().get(j));
//				}
//			}
//		}
//		workLst.add(objError);
//
//		return workLst;
//	}
//
//	/**
//	 * Validate các trường trong file excel
//	 * 
//	 * @param data
//	 * @param rowIndex
//	 * @param columnIndex
//	 * @param orderErrorFormat
//	 * @return
//	 */
//	
//
//	/**
//	 * Kiểm tra mã hàng trong file excel có tồn tại trong Bảng CAT_OWNER.GOODS?
//	 * 
//	 * @param code
//	 * @return
//	 */
//	public boolean checkDataFromFileExelInDB(String code) {
//		objGoods = new GoodsDTO();
//		objGoods = goodsBusinessImpl.getGoodByCode(code);
//		if (objGoods == null) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean checkDataFromFileExelNK(String data, int rowIndex, int columnIndex,
//			OrderGoodsExelDTO orderErrorFormat) {
//		orderErrorFormat = new OrderGoodsExelDTO();
//		if (columnIndex == 1) {
//			GoodsDTO dto = new GoodsDTO();
//			dto.setCode(data);
//			List<GoodsDTO> lstGoods = goodsBusinessImpl.getGoodsForOrder(dto);
//			if (data.isEmpty()) {
//				
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Mã hàng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (lstGoods.size()==0) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Mã hàng không tồn tại");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (data.length() > 100) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Mã hàng vượt quá 50 ký tự");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		} else if (columnIndex == 3) {
//			List<StockTransDetailSerialDTO> detailSerialDTOs = Lists
//					.newArrayList();
//			detailSerialDTOs = stockTransDetailDAO
//					.getListStockGoodSerialBySerial(data);
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Serial đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (data.length()>100) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Serial vượt quá 100 ký tự");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (detailSerialDTOs.size() == 0) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Serial phải nằm trong kho xuất có status =1 ");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//
//			} 
//		}else if (columnIndex == 4) {
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Số lượng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			
//			if (!StringUtils.isNumeric(data)) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Số lượng sai format không phải kiểu số");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (Integer.parseInt(data)<=0) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Số lượng nhỏ hơn 0");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if(data.contains(".")){
//				if(data.replace(".", "").length()>10){
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Số lượng vượt quá 10 ký tự");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//			}else{
//				if (data.length()>10) {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Số lượng vượt quá 10 ký tự");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//			}
//		}else if (columnIndex == 5) {
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
//				orderErrorFormat.setDetailError("Tình trạng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (!StringUtils.isNumeric(data)) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Tình trạng không phải kiểu số");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			} else if (Integer.parseInt(data) != 1 && Integer.parseInt(data) != 2) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Tình trạng không phải là giá trị 1 hoặc 2");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		}
//
//		return true;
//	}
//
//
//	
//	
//	public boolean duplicateOrderGoods(OrderGoodsDTO ordergoods,OrderDTO copiedOrder){
//		return false;
//	}
//
//	public List<GoodsDTO> getForAutoComplete(GoodsDTO obj) {
//		return orderGoodsDAO.getForAutoComplete(obj);
//	}
//
//	@Override
//	public String exportExcelError(OrderGoodsDetailDTO errorObj) throws Exception {
//		// TODO Auto-generated method stub
//		String filePath = UEncrypt.decryptFileUploadPath(errorObj.getFilePathError());
//		InputStream file = new BufferedInputStream(new FileInputStream(filePath));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(0);
//		
//		for(int i = 0; i<errorObj.getLstErrorOrderGoods().size();i++){
//			XSSFRow row = sheet.getRow(Integer.parseInt(errorObj.getLstErrorOrderGoods().get(i).getLineError())-1);
//			if(row ==null){
//				row = sheet.createRow(Integer.parseInt(errorObj.getLstErrorOrderGoods().get(i).getLineError())-1);
//			}
//			XSSFCell cell = row.getCell(6);
//			
//			if(cell ==null){
//				cell = row.createCell(6);
//			}
//			if(!cell.getStringCellValue().isEmpty()){
//				cell.setCellValue(cell.getStringCellValue()+","+errorObj.getLstErrorOrderGoods().get(i).getDetailError());
//			}else{
//				cell.setCellValue(errorObj.getLstErrorOrderGoods().get(i).getDetailError());
//			}
//		}
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar + "NK_Import_ViTri.xlsx");
//		
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//		
//		String path = UEncrypt.encryptFileUploadPath("NK_Import_ViTri.xlsx");
//		return path;
//	}
//
//
//	public List<OrderGoodsDTO> importOrderGood(String fileInput,Long orderId) throws Exception {
//		lstErrorOrder = new ArrayList<>();
//		List<OrderGoodsDTO> workLst = Lists.newArrayList();
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
//			String goodCode = "";
//			String amount = "";
//			String amountReal = "";
//			String goodsState = "";
//			for (Row row : sheet) {
//				OrderGoodsDTO objOrderGood = new OrderGoodsDTO();
//				OrderGoodsDetailDTO objOrderGoodsDetail = new OrderGoodsDetailDTO();
//				count++;
//				if (count >= 3 && !ValidateUtils.checkIfRowIsEmpty(row)) {
//					boolean checkColumn1 = true;
//					boolean checkColumn3 = true;
//					boolean checkColumn4 = true;
//					boolean checkColumn5 = true;
//					boolean checktotal=true;
//					for (Cell cell : row) {
//						// Check format file exel
//						if (cell.getColumnIndex() == 1) {
//							goodCode = formatter.formatCellValue(cell);
//							objOrderGood.setGoodsCode(goodCode);
//						} else if (cell.getColumnIndex() == 2) {
//							objOrderGood.setGoodsName(formatter.formatCellValue(cell));
//						} else if (cell.getColumnIndex() == 3) {
//							amount = formatter.formatCellValue(cell);
//							if (amount != "" && StringUtils.isNumeric(amount))
//								objOrderGood.setAmount(Double.parseDouble(formatter.formatCellValue(cell)));
//						} else if (cell.getColumnIndex() == 4) {
//							amountReal = formatter.formatCellValue(cell);
//							if (amountReal != "" && StringUtils.isNumeric(amountReal))
//								objOrderGood.setAmountReal(Double.parseDouble(formatter.formatCellValue(cell)));
//						} else if (cell.getColumnIndex() == 5) {
//							goodsState = formatter.formatCellValue(cell);
//							listGoodsCode_GoodsState.add(goodCode +"_"+ goodsState);
//							objOrderGood.setGoodsState(formatter.formatCellValue(cell));
//						}
//						
//						objOrderGood.setOrderId(orderId);
//					}
//					if (objOrderGood.getGoodsCode() == "" && objOrderGood.getAmountReal() == null
//							&& objOrderGood.getGoodsState() == "") {
//
//					} else {
//						OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
//						checkColumn1 = checkDataFromFile1("",goodCode,"",objOrderGood.getGoodsCode(), count, 1, orderErrorFormat);
//						checkColumn3 = checkDataFromFile1("","","",amount, count, 3, orderErrorFormat);
//						checkColumn4 = checkDataFromFile1(goodsState,goodCode,String.valueOf(orderId),amountReal, count, 4, orderErrorFormat);
//						checkColumn5 = checkDataFromFile1("","","",objOrderGood.getGoodsState(), count, 5, orderErrorFormat);
//						StockTransDetailDTO stockTransDetailDTO= new StockTransDetailDTO();
//						stockTransDetailDTO.setGoodsCode(goodCode);
//						if(goodCode != null && orderId != null){
//							
//						}
//						stockTransDetailDTO.setGoodsState(goodsState);
//						if(checkColumn1 && checkColumn5 && !stockTransDetailDAO.checkStockCode(stockTransDetailDTO,orderId)){
//							OrderGoodsExelDTO obj = new OrderGoodsExelDTO();
//							obj.setLineError(String.valueOf(count));
//							obj.setColumnError(String.valueOf(1));
//							obj.setDetailError(" Hàng hóa không tồn tại trong yêu cầu");
//							lstErrorOrder.add(obj);
//							checktotal=false;
//						}if(checkColumn1 && checkColumn5 && stockTransDetailDAO.checkStockCode(stockTransDetailDTO,orderId)){
//							int countGoodsCode = 0;
//							for(int i = 0;i<listGoodsCode_GoodsState.size();i++){
//								if((goodCode+"_"+goodsState).equals(listGoodsCode_GoodsState.get(i))){
//									countGoodsCode = countGoodsCode + 1;
//								}
//							}
//							if(countGoodsCode != 1){
//								OrderGoodsExelDTO obj = new OrderGoodsExelDTO();
//								obj.setLineError(String.valueOf(count));
//								obj.setColumnError(String.valueOf(1));
//								obj.setDetailError("Mã hàng đã tồn tại trong yêu cầu");
//								lstErrorOrder.add(obj);
//								checktotal=false;
//							}
//						}
//											
//						if (checkColumn1 && checkColumn4 && checkColumn5 && checkColumn3 && checktotal) {
//							OrderGoodsDTO orderGood = orderGoodsDAO.doSearchGoodsForFileImport(orderId, goodCode, goodsState);
//							objOrderGood.setGoodsName(orderGood.getGoodsName());
//							objOrderGood.setGoodsStateName(orderGood.getGoodsStateName());
//							objOrderGood.setGoodsUnitId(orderGood.getGoodsUnitId());
//							objOrderGood.setGoodsUnitName(orderGood.getGoodsUnitName());
//							objOrderGood.setAmount(orderGood.getAmount());
//								workLst.add(objOrderGood);
//						} 
//					}
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
//		/*
//		 * if (workLst.size() == 0) { throw new
//		 * IllegalArgumentException("Không có công việc phù hợp với những công việc trên lưới dữ liệu"
//		 * ); }
//		 */
//		String filePathError = UEncrypt.encryptFileUploadPath(fileInput);
//		OrderGoodsDTO objError = new OrderGoodsDTO();
//		objError.setLstErrorGoods(lstErrorOrder);
//		objError.setFilePathError(filePathError);
//		for(int i=0;i<objError.getLstErrorGoods().size();i++){
//            for(int j=i+1;j<objError.getLstErrorGoods().size();j++){
//                if(objError.getLstErrorGoods().get(j).getLineError()==objError.getLstErrorGoods().get(i).getLineError()&&
//                        objError.getLstErrorGoods().get(i).getColumnError()==objError.getLstErrorGoods().get(j).getColumnError()){
//                    objError.getLstErrorGoods().remove(objError.getLstErrorGoods().get(j));
//                }
//            }
//        }
//		/*if (objError.getLstErrorOrderGoods().size() > 0)
//			workLst.add(objError);*/
//		workLst.add(objError);
//		return workLst;
//	}
//	
//	public boolean checkDataFromFile1(String goodState,String code, String amount,String data, int rowIndex, int columnIndex, OrderGoodsExelDTO orderErrorFormat) {
//		if (columnIndex == 1) {
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Mã hàng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		} else if (columnIndex == 3) {
//			if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Số lượng yêu cầu đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			if (!StringUtils.isNumeric(data)) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Số lượng yêu cầu không phải kiểu số");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		} else if (columnIndex == 4) {
//			OrderGoodsDTO orderGood = orderGoodsDAO.doSearchGoodsForFileImport(Long.parseLong(amount), code, goodState);
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
//				orderErrorFormat.setDetailError("Số lượng xuất không phải kiểu số");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//			else
//			{
//				
//				if (StringUtils.isNumeric(amount) && orderGood != null) {
//					if((orderGood.getAmount())<Integer.parseInt(data))
//					{
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Số lượng xuất phải nhỏ hơn hoặc bằng số lượng yêu cầu");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//					}
//					
//				}
//			}
//		} else {
//			if (columnIndex == 5) {
//				if (data.isEmpty()) {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Tình trạng đang để trống");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//				if (!StringUtils.isNumeric(data)) {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Tình trạng không phải kiểu số");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				} else if (Integer.parseInt(data) != 1 && Integer.parseInt(data) != 2) {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Tình trạng không phải giá trị 1 hoặc 2");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public boolean saveGoodsList(List<OrderGoodsDTO> orderGoods) {
//		boolean isSavedGoods = false;
//		for(OrderGoodsDTO og: orderGoods){
//				if(save(og) == 0)	break;
//				else		isSavedGoods = true;
//		}
//		return isSavedGoods;
//	}
//	
//	@Override
//	public String exportExcelTemplate(OrderGoodsDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		
//		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//		String filePath = classloader.getResource("../" + "doc-template").getPath();
//		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "2.XK_TaoPhieuXuat.xlsx"));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(0);
//		
//		List<OrderGoodsDTO> lstOrderGoods = orderGoodsDAO.doSearchGoodsForExportOrder(obj);
//		
//		for(int i=0;i<lstOrderGoods.size();i++){
//			XSSFRow row = sheet.getRow(i+2);
//			XSSFCell cell1 = row.createCell(1);
//			cell1.setCellValue(lstOrderGoods.get(i).getGoodsCode());
//			XSSFCell cell2 = row.createCell(2);
//			cell2.setCellValue(lstOrderGoods.get(i).getGoodsName());
//			XSSFCell cell3 = row.createCell(3);
//			cell3.setCellValue(lstOrderGoods.get(i).getAmount());
//			XSSFCell cell4 = row.createCell(4);
//			cell4.setCellValue(lstOrderGoods.get(i).getAmount());
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
//	
//	public List<OrderGoodsDTO> getGoodsDetail(Long orderId){
//
//		List<OrderGoodsDTO> listReturn = Lists.newArrayList();
//		List<OrderGoodsDTO> ls = orderGoodsDAO.getGoodDetail(orderId);
//		OrderGoodsDTO ab = new OrderGoodsDTO();
//		for (Iterator<OrderGoodsDTO> interator = ls.iterator(); interator
//				.hasNext();) {
//			OrderGoodsDTO wi = interator.next();
//
//			if (ab.getOrderGoodsId() == null) {
//				ab = wi;
//				listReturn.add(ab);
//
//			}
//			if (ab.getOrderGoodsId().compareTo(wi.getOrderGoodsId()) != 0) {
//				ab = wi;
//				listReturn.add(ab);
//			}
//			if (ab.getOrderGoodsId().compareTo(wi.getOrderGoodsId()) == 0) {
//				OrderGoodsDetailDTO goodsDetailDTO = new OrderGoodsDetailDTO();
//				goodsDetailDTO.setOrderGoodsDetailId(wi.getOrderGoodsDetailId());
//				goodsDetailDTO.setOrderGoodsId(wi.getOrderGoodsId());
//				goodsDetailDTO.setSerial(wi.getSerial());
//				goodsDetailDTO.setContractCode(wi.getContractCode());
//				goodsDetailDTO.setPartNumber(wi.getPartNumber());
//				goodsDetailDTO.setManufacturerId(wi.getManufacturerId());
//				goodsDetailDTO.setProducingCountryId(wi.getProducingCountryId());
//				goodsDetailDTO.setManufacturerName(wi.getManufacturerName());
//				goodsDetailDTO.setProducingCountryName(wi.getProducingCountryName());
//				goodsDetailDTO.setPrice(wi.getPrice());
//				goodsDetailDTO.setGoodsCode(wi.getGoodsCode());
//				goodsDetailDTO.setGoodsName(wi.getGoodsName());
//				goodsDetailDTO.setGoodsId(wi.getGoodsId());
//				ab.getListOrderGoodsDetailDTO().add(goodsDetailDTO);
//			}
//		}
//
//		return listReturn;
//	
//		
//		
//	}
//
//	@Override
//	public List<OrderGoodsDTO> getGoodDetail(Long orderId) {
//		// TODO Auto-generated method stub
//		return orderGoodsDAO.getGoodDetail(orderId);
//	}
//}
