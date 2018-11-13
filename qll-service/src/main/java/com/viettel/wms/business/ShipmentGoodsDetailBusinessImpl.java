//package com.viettel.wms.business;
// 
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
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
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.wms.bo.ShipmentGoodsDetailBO;
//import com.viettel.wms.dao.ShipmentGoodsDetailDAO;
//import com.viettel.wms.dto.AttachmentDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderGoodsExelDTO;
//import com.viettel.wms.dto.ShipmentGoodsDTO;
//import com.viettel.wms.dto.ShipmentGoodsDetailDTO;
//
//
//@Service("shipmentGoodsDetailBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class ShipmentGoodsDetailBusinessImpl extends BaseFWBusinessImpl<ShipmentGoodsDetailDAO,ShipmentGoodsDetailDTO, ShipmentGoodsDetailBO> implements ShipmentGoodsDetailBusiness {
//
//	
//	static Logger LOGGER = LoggerFactory.getLogger(ShipmentBusinessImpl.class);
//	List<OrderGoodsExelDTO> lstErrorOrder;
//	
//	@Autowired
//	private GoodsBusinessImpl goodsBusinessImpl;
//	
//    @Autowired
//    private ShipmentGoodsDetailDAO shipmentGoodsDetailDAO;
//    
//
//     
//    public ShipmentGoodsDetailBusinessImpl() {
//        tModel = new ShipmentGoodsDetailBO();
//        tDAO = shipmentGoodsDetailDAO;
//    }
//
//    @Override
//    public ShipmentGoodsDetailDAO gettDAO() {
//        return shipmentGoodsDetailDAO;
//    }
//
//    @Override
//    public long count() {
//        return shipmentGoodsDetailDAO.count("ShipmentGoodsDetailBO", null);
//    }
//    //Hàm hiển thị danh sách hàng hóa trong màn hình Cập nhật hàng hóa cho lô hàng
//	public List<ShipmentGoodsDetailDTO> getDoMapDetail(ShipmentGoodsDetailDTO obj) {
//		List<ShipmentGoodsDetailDTO> list=shipmentGoodsDetailDAO.getDoMapDetail(obj);
//		for(ShipmentGoodsDetailDTO o: list){
//			GoodsDTO g= new GoodsDTO();
//			g.setId(o.getGoodsId());
//			GoodsDTO lstGoods = goodsBusinessImpl.getGoodById(g);
//			o.setIsSerial(lstGoods.getIsSerial());
//		}
//		return list;
//	}
////End
//	@Override
//	public List<ShipmentGoodsDetailDTO> getGoodsInfoByCode(String code) {
//		// TODO Auto-generated method stub
//		return shipmentGoodsDetailDAO.getGoodsInfoByCode(code);
//	}
//
//	@Override
//	public List<ShipmentGoodsDetailDTO> getGoodsDetail(ShipmentGoodsDetailDTO obj) {
//		// TODO Auto-generated method stub
//		return shipmentGoodsDetailDAO.getGoodsDetail(obj);
//	}
//
//	public List<ShipmentGoodsDetailDTO> importGoods(String fileInput) {
//
//		lstErrorOrder = new ArrayList<>();
//		List<ShipmentGoodsDetailDTO> workLst = Lists.newArrayList();
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
//				if (count >= 3) {
//
//					boolean checkCoulumn1 = true;
//					boolean checkCoulumn3 = true;
//					boolean checkCoulumn6 = true;
//					String amaunt = "";
//					String code = "";
//					String status = "";
//
//					for (Cell cell : row) {
//
//						// Create object OrderGoodsExelDTO
//						OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
//
//						// Check format file exel
//						if (cell.getColumnIndex() == 1) {
//							code = formatter.formatCellValue(cell);
//							checkCoulumn1 = checkDataFromFileExel(code, count, cell.getColumnIndex(), orderErrorFormat);
//						} else if (cell.getColumnIndex() == 3) {
//							amaunt = formatter.formatCellValue(cell);
//							checkCoulumn1 = checkDataFromFileExel(amaunt, count, cell.getColumnIndex(), orderErrorFormat);
//						} else if (cell.getColumnIndex() == 6) {
//							status=formatter.formatCellValue(cell);
//							checkCoulumn3 = checkDataFromFileExel(status, count,cell.getColumnIndex(), orderErrorFormat);
//						}
//
//					}
//
//					if (checkCoulumn1 == true && checkCoulumn3 == true && checkCoulumn6 == true) {
//						ShipmentGoodsDetailDTO dto = new ShipmentGoodsDetailDTO();
//						dto.setGoodsCode(row.getCell(1).getStringCellValue());
//						dto.setGoodsName(row.getCell(2).getStringCellValue());
//						dto.setManufacturer(row.getCell(3).getStringCellValue());
//						dto.setProducerCountry(row.getCell(4).getStringCellValue());
//						dto.setPartNumber(String.valueOf(Math.round(row.getCell(5).getNumericCellValue())));
//						dto.setSerial(String.valueOf(Math.round(row.getCell(6).getNumericCellValue())));
//						dto.setAmount(row.getCell(7).getNumericCellValue());
//
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
////		if (workLst.size() == 0) {
////			throw new IllegalArgumentException("Không có công việc phù hợp với những công việc trên lưới dữ liệu");
////		}
//
//		ShipmentGoodsDetailDTO objError = new ShipmentGoodsDetailDTO();
//		//objError.setLstErrorOrderGoods(lstErrorOrder);
//
//		workLst.add(objError);
//
//		return workLst;
//	}
//
//	public boolean checkDataFromFileExel(String data, int rowIndex, int columnIndex,
//			OrderGoodsExelDTO orderErrorFormat) {
//		if (columnIndex == 3) {
////			if (Double.parseDouble(data) < 0.0 ) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
////				orderErrorFormat.setDetailError("Số lượng nhỏ hơn 0");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}else
//			if (data == "") {
//				return false;
//			}else if (data.isEmpty()) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Số lượng đang để trống");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		} else if (columnIndex == 1) {
//			GoodsDTO dto = new GoodsDTO();
//			List<GoodsDTO> lstGoods = goodsBusinessImpl.getGoodsForOrder(dto);
//			List<String> lstCode = new ArrayList<>();
//			for (int i = 0; i < lstGoods.size(); i++) {
//				lstCode.add(lstGoods.get(i).getCode());
//			}
//			if(data == ""){
//				return false;
//			}else if (!lstCode.contains(data)) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Mã " + data + " không tồn tại");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//
//		} else if (columnIndex == 6) {
//			if(data == ""){
//				return false;
//			}
//			if (!data.equals("1") && !data.equals("2")) {
//				orderErrorFormat.setLineError(String.valueOf(rowIndex));
//				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
//				orderErrorFormat.setDetailError("Sai trạng thái nhập vào");
//				lstErrorOrder.add(orderErrorFormat);
//				return false;
//			}
//		}
//
//		return true;
//	}
//
//	@Override
//	public List<AttachmentDTO> getGoodsFile(AttachmentDTO obj) {
//		return shipmentGoodsDetailDAO.getGoodsFile(obj);
//	}
//
//	public ShipmentGoodsDetailDTO checkSerial(ShipmentGoodsDetailDTO obj){
//		return shipmentGoodsDetailDAO.checkSerial(obj);
//	}
//	public void deleteGoods(Long shipmentGoodsDetailId){
//		shipmentGoodsDetailDAO.deleteGoods(shipmentGoodsDetailId);
//	}
//}
