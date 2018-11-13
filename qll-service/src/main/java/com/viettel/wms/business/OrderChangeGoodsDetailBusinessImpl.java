//package com.viettel.wms.business;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//
//import com.google.common.collect.Lists;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.bo.OrderChangeGoodsDetailBO;
//import com.viettel.wms.dao.OrderChangeGoodsDetailDAO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;
//import com.viettel.wms.dto.OrderGoodsExelDTO;
//import com.viettel.wms.utils.ValidateUtils;
//
//@Service("orderChangeGoodsDetailBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class OrderChangeGoodsDetailBusinessImpl
//		extends BaseFWBusinessImpl<OrderChangeGoodsDetailDAO, OrderChangeGoodsDetailDTO, OrderChangeGoodsDetailBO>
//		implements OrderChangeGoodsDetailBusiness {
//
//	static Logger LOGGER = LoggerFactory.getLogger(OrderChangeGoodsBusinessImpl.class);
//	List<OrderGoodsExelDTO> lstErrorOrder;
//
//	@Autowired
//	private OrderChangeGoodsDetailDAO orderChangeGoodsDetailDAO;
//
//	@Autowired
//	private GoodsBusinessImpl goodsBusinessImpl;
//
//	public OrderChangeGoodsDetailBusinessImpl() {
//		tModel = new OrderChangeGoodsDetailBO();
//		tDAO = orderChangeGoodsDetailDAO;
//	}
//
//	@Override
//	public OrderChangeGoodsDetailDAO gettDAO() {
//		return orderChangeGoodsDetailDAO;
//	}
//
//	@Override
//	public long count() {
//		return orderChangeGoodsDetailDAO.count("OrderChangeGoodsDetailBO", null);
//	}
//
//	/**
//	 * Tìm kiếm khi load page và khi tìm kiếm 
//	 * @param obj
//	 * @return
//	 */
//	public DataListDTO doSearch(OrderChangeGoodsDetailDTO obj) {
//		List<OrderChangeGoodsDetailDTO> ls = orderChangeGoodsDetailDAO.doSearch(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//	/**
//	 * update 
//	 * @param obj
//	 * @return
//	 */
//	public Long addUpdate(OrderChangeGoodsDetailDTO obj) {
//		OrderChangeGoodsDTO dto = new OrderChangeGoodsDTO();
//		obj.setOrderChangeGoodsId(dto.getOrderChangeGoodsId());
//		obj.setGoodsCode(dto.getCode());
//		this.save(obj);
//		return 1L;
//	}
//
//	/**
//	 * xử lý file excel import
//	 * @throws Exception 
//	 */
//	@Override
//	public List<OrderChangeGoodsDetailDTO> importGoods(String fileInput) throws Exception {
//
//		lstErrorOrder = new ArrayList<>();
//		List<OrderChangeGoodsDetailDTO> workLst = Lists.newArrayList();
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
//				OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
//				count++;
//				// Create object OrderGoodsExelDTO
//				if (count >= 3&& !ValidateUtils.checkIfRowIsEmpty(row)) {
//					
//					
//					boolean checkCoulumn1 = true;
//					boolean checkCoulumn2 = true;
//					boolean checkCoulumn3 = true;
//					boolean checkCoulumn4 = true;
//					boolean checkCoulumn5 = true;
//					boolean checkCoulumn6 = true;
//					String goodsName="";
//					String goodsUnitName=""; 
//					String code = "";
//					String serial = "";
//					String newGoodsCode="";
//					String newSerial="";
//					/*for (Cell cell : row) {
//						// Check format file exel
//						 orderErrorFormat = new OrderGoodsExelDTO();
//						if (cell.getColumnIndex() == 1) {
//							code = formatter.formatCellValue(cell).toUpperCase().trim();
//						}else if(cell.getColumnIndex()==2){
//							goodsName = formatter.formatCellValue(cell);
//						}else if(cell.getColumnIndex()==3){
//							goodsUnitName = formatter.formatCellValue(cell);
//						}
//						else if (cell.getColumnIndex() == 4) {
//							serial = formatter.formatCellValue(cell);
//						}else if(cell.getColumnIndex() == 5){
//							newGoodsCode = formatter.formatCellValue(cell).toUpperCase().trim();
//							
//						}else if(cell.getColumnIndex() == 6){
//							newSerial = formatter.formatCellValue(cell);
//							
//						}
//
//					}*/
//					
//						/*checkCoulumn1 = checkDataFromFileExel("","","", code, count, 1,
//								orderErrorFormat);
//						checkCoulumn2 = checkDataFromFileExel("","",code, goodsName, count, 2,
//								orderErrorFormat);				
//						checkCoulumn3 = checkDataFromFileExel("","","", goodsUnitName, count, 3,
//								orderErrorFormat);
//						if(code!=""){
//							if(code==null||code==""){
//							checkCoulumn4 = checkDataFromFileExel("","","", serial, count, 4,
//									orderErrorFormat);
//							}else{
//								checkCoulumn4 = checkDataFromFileExel("","",code, serial, count, 4,
//										orderErrorFormat);
//							}
//						}
//						checkCoulumn5 = checkDataFromFileExel("","",code, newGoodsCode, count, 5,
//								orderErrorFormat);
//						checkCoulumn6 = checkDataFromFileExel(serial,newGoodsCode,code, newSerial, count, 6,
//								orderErrorFormat);
//						if(newSerial==""||newSerial==null){
//							serial=newSerial;
//						}*/
//					for (Cell cell : row) {
//						// Check format file exel
//						 orderErrorFormat = new OrderGoodsExelDTO();
//						if (cell.getColumnIndex() == 1) {
//							code = formatter.formatCellValue(cell).toUpperCase().trim();
//							checkCoulumn1 = checkDataFromFileExel("","","", code, count, 1,
//									orderErrorFormat);
//						}else if(cell.getColumnIndex()==2){
//							goodsName = formatter.formatCellValue(cell);
//							checkCoulumn2 = checkDataFromFileExel("","",code, goodsName, count, 2,
//									orderErrorFormat);	
//						}else if(cell.getColumnIndex()==3){
//							goodsUnitName = formatter.formatCellValue(cell);
//							checkCoulumn3 = checkDataFromFileExel("","","", goodsUnitName, count, 3,
//									orderErrorFormat);
//						}
//						else if (cell.getColumnIndex() == 4) {
//							serial = formatter.formatCellValue(cell);
//							if(code!=""){
//								if(code==null||code==""){
//								checkCoulumn4 = checkDataFromFileExel("","","", serial, count, 4,
//										orderErrorFormat);
//								}else{
//									checkCoulumn4 = checkDataFromFileExel("","",code, serial, count, 4,
//											orderErrorFormat);
//								}
//							}
//						}else if(cell.getColumnIndex() == 5){
//							newGoodsCode = formatter.formatCellValue(cell).toUpperCase().trim();
//							checkCoulumn5 = checkDataFromFileExel("","",code, newGoodsCode, count, 5,
//									orderErrorFormat);
//							
//						}else if(cell.getColumnIndex() == 6){
//							newSerial = formatter.formatCellValue(cell);
//							checkCoulumn6 = checkDataFromFileExel(serial,newGoodsCode,code, newSerial, count, 6,
//									orderErrorFormat);
//							if(newSerial==""||newSerial==null){
//								serial=newSerial;
//							
//						}
//
//					}
//					}
//					if(count==3){
//						if(code==""&&goodsName==""&&goodsUnitName==""&&serial==""&&newGoodsCode==""&&newSerial==""){
//							//OrderGoodsExelDTO orderErrorFormat1=new OrderGoodsExelDTO();
//							orderErrorFormat.setLineError(String.valueOf(count));
//							orderErrorFormat.setColumnError(String.valueOf(1));
//							orderErrorFormat.setDetailError("File excel trống");
//							lstErrorOrder.add(orderErrorFormat);
//						}
//					}
//				
//						if (checkCoulumn1 == true &&checkCoulumn2 == true &&checkCoulumn3 == true && checkCoulumn4 == true
//								&& checkCoulumn5 == true&& checkCoulumn6 == true) {
//						OrderChangeGoodsDetailDTO dto = new OrderChangeGoodsDetailDTO();
//						dto.setGoodsCode(formatter.formatCellValue(row.getCell(1)).toUpperCase().trim());
//						GoodsDTO goodslDTO=orderChangeGoodsDetailDAO.getNameByCode(code);
//						if(goodslDTO!=null){
//							dto.setGoodsName(goodslDTO.getName().toUpperCase().trim());
//							dto.setGoodsId(goodslDTO.getGoodsId());
//							dto.setGoodsState(goodslDTO.getGoodsState());
//							dto.setGoodsStateName(goodslDTO.getGoodsStateName());
//							dto.setGoodsType(goodslDTO.getGoodsType());
//							dto.setGoodsTypeName(goodslDTO.getGoodsTypeName());
//							dto.setGoodsUnitId(goodslDTO.getGoodsUnitId());
//							dto.setGoodsUnitName(goodslDTO.getGoodsUnitName());
//							dto.setGoodsIsSerial(goodslDTO.getIsSerial());
//							dto.setGoodsState(goodslDTO.getGoodsState());
//							dto.setGoodsStateName(goodslDTO.getGoodsStateName());
//						}
//						dto.setSerial(formatter.formatCellValue(row.getCell(4)).toUpperCase().trim());
//						dto.setNewGoodsCode(formatter.formatCellValue(row.getCell(5)).toUpperCase().trim());
//						GoodsDTO goodsDTO=new GoodsDTO(); 
//						goodsDTO =orderChangeGoodsDetailDAO.checkIsSerial(newGoodsCode);
//						if(goodsDTO!=null){
//							if(Integer.parseInt(goodsDTO.getIsSerial())==1){
//								if(StringUtils.isEmpty(newSerial)){
//									dto.setNewSerial(formatter.formatCellValue(row.getCell(4)));
//								}else{
//									dto.setNewSerial(formatter.formatCellValue(row.getCell(6)));
//								}
//							}else {
//								dto.setNewSerial(formatter.formatCellValue(row.getCell(6)));
//							}
//						}
//						workLst.add(dto);
//						}
//
//				}else if(sheet.getLastRowNum()<3){
//					//OrderGoodsExelDTO orderErrorFormat2=new OrderGoodsExelDTO();
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
//		OrderChangeGoodsDetailDTO objError = new OrderChangeGoodsDetailDTO();
//		objError.setLstErrorOrderGoods(lstErrorOrder);
//		objError.setFilePathError(filePathError);
//		for(int i=0;i<objError.getLstErrorOrderGoods().size();i++){
//			for(int j=i+1;j<objError.getLstErrorOrderGoods().size();j++){
//				if(objError.getLstErrorOrderGoods().get(j).getLineError()==objError.getLstErrorOrderGoods().get(i).getLineError()&&
//						objError.getLstErrorOrderGoods().get(i).getColumnError()==objError.getLstErrorOrderGoods().get(j).getColumnError()){
//					objError.getLstErrorOrderGoods().remove(objError.getLstErrorOrderGoods().get(j));
//					j--;
//				}
//			}
//		}
//		
//		for(int j=0;j<workLst.size();j++){
//			for(int k=j+1;k<workLst.size();k++){
//				if(workLst.get(j).getGoodsCode().equals(workLst.get(k).getGoodsCode())&&workLst.get(j).getSerial().equals(workLst.get(k).getSerial())){
//					workLst.remove(workLst.get(k));
//					k--;
//				}
//			}
//		}
//		workLst.add(objError);
//
//		return workLst;
//	}
//
//	/**
//	 * Validate file excel khi import
//	 * @param serial
//	 * @param newGoodsCode
//	 * @param code
//	 * @param data
//	 * @param rowIndex
//	 * @param columnIndex
//	 * @param orderErrorFormat
//	 * @return
//	 */
//	public boolean checkDataFromFileExel(String serial,String newGoodsCode,String code, String data, int rowIndex, int columnIndex,
//			OrderGoodsExelDTO orderErrorFormat) {
//		GoodsDTO dto = new GoodsDTO();
//		List<GoodsDTO> lstGoods = goodsBusinessImpl.getGoodsForOrder(dto);
//		List<String> lstCode = new ArrayList<>();
//		for (int i = 0; i < lstGoods.size(); i++) {
//			lstCode.add(lstGoods.get(i).getCode().toUpperCase());
//		}
//		 if (columnIndex == 1) {
//				if(StringUtils.isEmpty(data)||data==""||data==null){
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Mã hàng hóa đang để trống");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}else if (!lstCode.contains(data)) {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Mã hàng " + data + " không tồn tại");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//					}
//			}else if (columnIndex == 4) {
//			
////			if(StringUtils.isEmpty(code)||code==null||code==""){
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
////				orderErrorFormat.setDetailError("Mã hàng hóa đang để trống");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}else 
//				if (StringUtils.isNotEmpty(code)) {
//				GoodsDTO goodsDTO=new GoodsDTO(); 
//				goodsDTO =orderChangeGoodsDetailDAO.checkIsSerial(code.toUpperCase().trim());
//				if(goodsDTO!=null){
//					checkColumn4(code,data,rowIndex,columnIndex,orderErrorFormat);
//				}
//				
//			}
//		} else if (columnIndex == 6) {
//			OrderChangeGoodsDetailDTO obj = new OrderChangeGoodsDetailDTO();
//			List<OrderChangeGoodsDetailDTO> lstDTO = orderChangeGoodsDetailDAO.checkSerial(obj);
//			List<String> lstSerial1 = new ArrayList<>();
//			for (int i = 0; i < lstDTO.size(); i++) {
//				lstSerial1.add(lstDTO.get(i).getSerial());
//			}
//			
//			if (!StringUtils.isEmpty(newGoodsCode)) {
//				GoodsDTO goodsDTO=new GoodsDTO(); 
//				goodsDTO =orderChangeGoodsDetailDAO.checkIsSerial(newGoodsCode.toUpperCase().trim());
//				boolean check1=orderChangeGoodsDetailDAO.checkDuplicateSerial(newGoodsCode.toUpperCase().trim(), data.toUpperCase().trim());
//				if(goodsDTO!=null){
//					checkColumn6(newGoodsCode,data,rowIndex,columnIndex,orderErrorFormat);
//					}
//			}
//		} else if(columnIndex == 5){
//			GoodsDTO goodsDTON=new GoodsDTO(); 
//			goodsDTON =orderChangeGoodsDetailDAO.checkIsSerial(code.toUpperCase().trim());
//			
//			if(goodsDTON!=null){
//				if(StringUtils.isEmpty(data)&&data==""){
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Mã hàng sau thay đổi không được để trống");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}else if(StringUtils.isNotEmpty(data)){
//					checkColumn5( code, data, rowIndex, columnIndex, orderErrorFormat);
//				}
//				
//			}
//		}
//		return true;
//	}
//	/**
//	 * validate cột Serial thay đổi
//	 * @param code
//	 * @param data
//	 * @param rowIndex
//	 * @param columnIndex
//	 * @param orderErrorFormat
//	 * @return
//	 */
//	public boolean checkColumn4(String code,String data,int rowIndex, int columnIndex,
//			OrderGoodsExelDTO orderErrorFormat){
//		GoodsDTO goodsDTO=new GoodsDTO(); 
//		goodsDTO =orderChangeGoodsDetailDAO.checkIsSerial(code.toUpperCase().trim());
//		if (Integer.parseInt(goodsDTO.getIsSerial())==1) {
//						if (StringUtils.isEmpty(data)) {
//							orderErrorFormat.setLineError(String.valueOf(rowIndex));
//							orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//							orderErrorFormat.setDetailError("Mã " + code + " quản lý theo serial, cần nhập serial thay đổi");
//							lstErrorOrder.add(orderErrorFormat);
//							return false;
//						}
//						else {
//							boolean check=orderChangeGoodsDetailDAO.checkDuplicateSerial(code.toUpperCase().trim(),data.toUpperCase().trim());
//							if(!check){
//									orderErrorFormat.setLineError(String.valueOf(rowIndex));
//									orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//									orderErrorFormat.setDetailError("Serial "+data+" không phù hợp với mã hàng "+code);
//									lstErrorOrder.add(orderErrorFormat);
//									return false;
//							}
//						}
//					}else if(Integer.parseInt(goodsDTO.getIsSerial())==0){
//						if(StringUtils.isNotEmpty(data)){
//							orderErrorFormat.setLineError(String.valueOf(rowIndex));
//							orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//							orderErrorFormat.setDetailError("Mã hàng "+code+" không có quản lý serial không được nhập");
//							lstErrorOrder.add(orderErrorFormat);
//							return false;
//						}
//					}else{
//						orderErrorFormat.setLineError(String.valueOf(rowIndex));
//						orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//						orderErrorFormat.setDetailError("Sai tình trạng Serial");
//						lstErrorOrder.add(orderErrorFormat);
//						return false;
//					}
//		return true;
//		
//	}
//	/**
//	 * validate Serial sau thay đổi
//	 * @param newGoodsCode
//	 * @param data
//	 * @param rowIndex
//	 * @param columnIndex
//	 * @param orderErrorFormat
//	 * @return
//	 */
//	public boolean checkColumn6(String newGoodsCode,String data,int rowIndex,int columnIndex,OrderGoodsExelDTO orderErrorFormat){
//		GoodsDTO goodsDTO=new GoodsDTO(); 
//		goodsDTO =orderChangeGoodsDetailDAO.checkIsSerial(newGoodsCode.toUpperCase().trim());
//		boolean check1=orderChangeGoodsDetailDAO.checkDuplicateSerial(newGoodsCode.toUpperCase().trim(), data.toUpperCase().trim());
//		if(Integer.parseInt(goodsDTO.getIsSerial())==1){
//			if(StringUtils.isNotEmpty(data)){
//				if (check1 == true) {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Serial " + data.toUpperCase().trim() + " sau thay đổi đã tồn tại");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//				if (data.length() >= 50) {
//					orderErrorFormat.setLineError(String.valueOf(rowIndex));
//					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//					orderErrorFormat.setDetailError("Serial phải có độ dài nhỏ hơn 50 ký tự.");
//					lstErrorOrder.add(orderErrorFormat);
//					return false;
//				}
//			}else{
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
////				orderErrorFormat.setDetailError("Mã hàng " + newGoodsCode + " quản lý theo serial, cần nhập serial sau thay đổi");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
//			}
//		}else if(Integer.parseInt(goodsDTO.getIsSerial())==0){
//			if(StringUtils.isNotEmpty(data)){
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Mã hàng "+newGoodsCode+" không có quản lý serial không được nhập serial sau thay đổi");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		}else{
//			orderErrorFormat.setLineError(String.valueOf(rowIndex));
//			orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//			orderErrorFormat.setDetailError("Sai tình trạng Serial");
//			lstErrorOrder.add(orderErrorFormat);
//			return false;
//		}
//		return true;
//	}
//	/**
//	 *validate cột mã hàng sau thay đổi
//	 * @param code
//	 * @param data
//	 * @param rowIndex
//	 * @param columnIndex
//	 * @param orderErrorFormat
//	 * @return
//	 */
//	public boolean checkColumn5(String code,String data,int rowIndex,int columnIndex,OrderGoodsExelDTO orderErrorFormat){
//
//		GoodsDTO goodsDTON=new GoodsDTO(); 
//		goodsDTON =orderChangeGoodsDetailDAO.checkIsSerial(code.toUpperCase().trim());
//		GoodsDTO goodsDTO1=new GoodsDTO(); 
//		goodsDTO1 =orderChangeGoodsDetailDAO.checkIsSerial(data.toUpperCase().trim());
//		GoodsDTO goodsDTO2=new GoodsDTO(); 
//		goodsDTO2 =orderChangeGoodsDetailDAO.selectNameByCode(data.toUpperCase().trim());
//		
//		if(Integer.parseInt(goodsDTON.getIsSerial())==0){
//			
//			if(goodsDTO1!=null){
//			if(Integer.parseInt(goodsDTO1.getIsSerial())==1){
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Không thể chuyển từ hàng không quản lý serial sang hàng có quản lý serial");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//				}
//			
//			}
//		}else if (goodsDTO2==null) {
//			orderErrorFormat.setLineError(String.valueOf(rowIndex));
//			orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//			orderErrorFormat.setDetailError("Mã hàng " + data + " sau thay đổi không tồn tại");
//			lstErrorOrder.add(orderErrorFormat);
//			return false;
//		}else if(Integer.parseInt(goodsDTON.getIsSerial())==1){
//			if(Integer.parseInt(goodsDTO1.getIsSerial())==0){
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Không thể chuyển từ hàng quản lý serial sang hàng không quản lý serial");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		}
//		return true;
//	}
//	
//}
