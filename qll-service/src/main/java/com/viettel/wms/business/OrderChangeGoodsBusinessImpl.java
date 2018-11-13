//package com.viettel.wms.business;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.viettel.ktts.vps.VpsPermissionChecker;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.bo.OrderChangeGoodsBO;
//import com.viettel.wms.constant.Constants;
//import com.viettel.wms.dao.OrderChangeGoodsDAO;
//import com.viettel.wms.dao.OrderChangeGoodsDetailDAO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDTO;
//import com.viettel.wms.dto.OrderChangeGoodsDetailDTO;
//import com.viettel.wms.dto.OrderGoodsExelDTO;
//import com.viettel.wms.dto.StockGoodsTotalDTO;
//
//@Service("orderChangeGoodsBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class OrderChangeGoodsBusinessImpl
//		extends
//		BaseFWBusinessImpl<OrderChangeGoodsDAO, OrderChangeGoodsDTO, OrderChangeGoodsBO>
//		implements OrderChangeGoodsBusiness {
//	protected final Logger log = Logger.getLogger(OrderChangeGoodsBusinessImpl.class);
//	@Autowired
//	private OrderChangeGoodsDAO orderChangeGoodsDAO;
//
//	@Autowired
//	private OrderChangeGoodsDetailDAO orderChangeGoodsDetailDAO;
//
//	@Autowired
//	OrderChangeGoodsDetailBusinessImpl businessImpl;
//
//	@Autowired
//	private GoodsBusinessImpl goodsBusinessImpl;
//	
//	List<OrderGoodsExelDTO> lstErrorOrder;
//	
//	 @Value("${folder_upload}")
//		private String folder2Upload;
//
//	public OrderChangeGoodsBusinessImpl() {
//		tDAO = orderChangeGoodsDAO;
//		tModel = new OrderChangeGoodsBO();
//	}
//
//	@Override
//	public OrderChangeGoodsDAO gettDAO() {
//		return orderChangeGoodsDAO;
//	}
//
//	@Override
//	public long count() {
//		return orderChangeGoodsDAO.count("OrderChangeGoodsBO", null);
//	}
//
//	/*--Begin 
//		Tìm kiếm bản ghi yêu cầu thay đổi
//	 */
//	public DataListDTO doSearch(OrderChangeGoodsDTO obj) {
//		List<OrderChangeGoodsDTO> ls = orderChangeGoodsDAO.doSearch(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//
//	/*
//	 * kết thúc tìm kiếm -- End
//	 */
//
//	/*--Begin 
//	  Tạo mới yêu cầu thay đổi, bản ghi đã import từ file excel vào bảng WMS_OWNER.ORDER_CHANGE_GOODS và WMS_OWNER.ORDER_CHANGE_GOODS_DETAIL  
//	 */
//	@Override
//	@Transactional
//	public Long saveImportChange(OrderChangeGoodsDTO obj,HttpServletRequest request) throws Exception  {
//		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.CREATE, Constants.AdResourceKey.CHANGE_ORDER, obj.getStockId(),request)){
//			throw new IllegalArgumentException("Bạn không có quyền thêm mới yêu cầu thay đổi !");
//		}
//			if(checkInsertUpdate(obj)){
//				GoodsDTO dto = new GoodsDTO();
//				List<GoodsDTO> lstGoods = goodsBusinessImpl.getGoodsForOrder(dto);
//				List<String> lstCode = new ArrayList<>();
//				for (int j = 0; j < lstGoods.size(); j++) {
//					lstCode.add(lstGoods.get(j).getCode());
//				}
//				Long ids = orderChangeGoodsDAO.saveObject(obj.toModel());
//				if (ids != null) {
//					for (int i = 0; i < obj.listorderChangeGoodsDetailDTO().size(); i++) {
//							GoodsDTO goodsDTO1 =orderChangeGoodsDetailDAO.checkIsSerial(obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode());
//							GoodsDTO goodsDTO2 =orderChangeGoodsDAO.selectNameByCode(obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode());
//							GoodsDTO goodsDTO=orderChangeGoodsDAO.selectNameByCode(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode());
//							if (lstCode.contains(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode().trim())) {
//								obj.listorderChangeGoodsDetailDTO().get(i).setNewGoodsId(goodsDTO.getGoodsId());
//								obj.listorderChangeGoodsDetailDTO().get(i).setOrderChangeGoodsId(ids);
//								obj.listorderChangeGoodsDetailDTO().get(i).setGoodsUnitId(goodsDTO2.getGoodsUnitId());
//								obj.listorderChangeGoodsDetailDTO().get(i).setGoodsType(goodsDTO2.getGoodsType());
//								obj.listorderChangeGoodsDetailDTO().get(i).setGoodsTypeName(goodsDTO2.getGoodsTypeName());
//								obj.listorderChangeGoodsDetailDTO().get(i).setGoodsId(goodsDTO2.getGoodsId());
//								obj.listorderChangeGoodsDetailDTO().get(i).setGoodsState("1");
//								obj.listorderChangeGoodsDetailDTO().get(i).setGoodsStateName("Bình Thường");
//								obj.listorderChangeGoodsDetailDTO().get(i).setNewGoodsName(goodsDTO.getName());
//								obj.listorderChangeGoodsDetailDTO().get(i).setNewGoodsCode(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode().trim());
//								checkInsert(obj,i);
//								businessImpl.save(obj.listorderChangeGoodsDetailDTO().get(i));
//							} 
//							
//						}
//					}
//				return ids;
//			}
//			return 1l;
//	}
//	public void checkInsert(OrderChangeGoodsDTO obj,int i){
//		GoodsDTO goodsDTO1=new GoodsDTO();
//		goodsDTO1 =orderChangeGoodsDetailDAO.checkIsSerial(obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode());
//		if(Integer.parseInt(goodsDTO1.getIsSerial())==1){
//			obj.listorderChangeGoodsDetailDTO().get(i).setAmountChange((double) 1);
//			obj.listorderChangeGoodsDetailDTO().get(i).setGoodsIsSerial("1");
//		}else if(Integer.parseInt(goodsDTO1.getIsSerial())==0){
//			StockGoodsTotalDTO stockGoodsTotalDTO=orderChangeGoodsDAO.sumAmountChange(obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode());
//				obj.listorderChangeGoodsDetailDTO().get(i).setAmountChange(stockGoodsTotalDTO.getAmount());
//		}
//	}
//
//	/*
//	 * kết thúc tạo mới -- End
//	 */
//
//	/*
//	 * Begin -- Update yêu cầu thay đổi, bản ghi đã import từ file excel vào
//	 * bảng WMS_OWNER.ORDER_CHANGE_GOODS và WMS_OWNER.ORDER_CHANGE_GOODS_DETAIL
//	 */
//	@Transactional
//	public Long updateImportChange(OrderChangeGoodsDTO obj,KttsUserSession objUser) {
//		if(!obj.getCreatedBy().equals(objUser.getSysUserId())){
//			throw new  IllegalArgumentException("Người dùng hiện tại không có quyền sửa bản ghi này !");
//		}
//		if (checkInsertUpdate(obj)) {
//			Long delete =orderChangeGoodsDAO.deleteObj(obj);
//			Long ids = orderChangeGoodsDAO.updateObject(obj.toModel());
//			if (ids != null && delete!=null) {
//				GoodsDTO dto = new GoodsDTO();
//				List<GoodsDTO> lstGoods = goodsBusinessImpl.getGoodsForOrder(dto);
//				List<String> lstCode = new ArrayList<>();
//				for (int j = 0; j < lstGoods.size(); j++) {
//					lstCode.add(lstGoods.get(j).getCode());
//				}
//				for (int i = 0; i < obj.listorderChangeGoodsDetailDTO().size(); i++) {
//					GoodsDTO goodsDTO1=new GoodsDTO();
//					goodsDTO1 =orderChangeGoodsDetailDAO.checkIsSerial(obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode());
//					GoodsDTO goodsDTO2=orderChangeGoodsDAO.selectNameByCode(obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode());
//					GoodsDTO goodsDTO=orderChangeGoodsDAO.selectNameByCode(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode());
//						if (lstCode.contains(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode().trim())) {
//							obj.listorderChangeGoodsDetailDTO().get(i).setOrderChangeGoodsId(obj.getOrderChangeGoodsId());
//							obj.listorderChangeGoodsDetailDTO().get(i).setNewGoodsName(goodsDTO.getName());
//							obj.listorderChangeGoodsDetailDTO().get(i).setGoodsType(goodsDTO2.getGoodsType());
//							obj.listorderChangeGoodsDetailDTO().get(i).setGoodsUnitId(goodsDTO2.getGoodsUnitId());
//							obj.listorderChangeGoodsDetailDTO().get(i).setGoodsTypeName(goodsDTO2.getGoodsTypeName());
//							obj.listorderChangeGoodsDetailDTO().get(i).setGoodsId(goodsDTO2.getGoodsId());
//							obj.listorderChangeGoodsDetailDTO().get(i).setGoodsState("1");
//							obj.listorderChangeGoodsDetailDTO().get(i).setGoodsStateName("Bình Thường");
//							obj.listorderChangeGoodsDetailDTO().get(i).setNewGoodsCode(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode().trim());
//							checkInsert(obj,i);
//							businessImpl.save(obj.listorderChangeGoodsDetailDTO().get(i));
//						} 
//					}
//				}
//			return ids;
//		}
//		return 1l;
//	}
//
//	/* kết thúc update */
//	/* End */
//	@Override
//	public List<OrderChangeGoodsDetailDTO> doSearchForAutoImport(
//			OrderChangeGoodsDetailDTO obj) {
//		return orderChangeGoodsDAO.doSearchForAutoImport(obj);
//	}
//
//	
//	/**
//     * Fill dữ liệu khi vào grid popup update khi click theo id
//     * @param obj
//     * @return
//     */
//	@Override
//	public DataListDTO doSearchGoodsForImportReq(OrderChangeGoodsDTO obj) {
//		// TODO Auto-generated method stub
//		List<OrderChangeGoodsDTO> ls = orderChangeGoodsDAO
//				.doSearchGoodsForImportReq(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//	
//	public List<OrderChangeGoodsDTO> getOrderChangeById(Long id) {
//		// TODO Auto-generated method stub
//		return orderChangeGoodsDAO.getOrderChangeById(id);
//	}
//	
//	public boolean checkInsertUpdate(OrderChangeGoodsDTO obj){
//		GoodsDTO dto = new GoodsDTO();
//		int flag=1;
//		List<GoodsDTO> lstGoods = goodsBusinessImpl.getGoodsForOrder(dto);
//		
//		List<String> lstCode = new ArrayList<>();
//		for (int j = 0; j < lstGoods.size(); j++) {
//			lstCode.add(lstGoods.get(j).getCode().trim());
//		}
//		
//		if(obj.listorderChangeGoodsDetailDTO().size()<=0){
//			throw new IllegalArgumentException("Không có dữ liệu chi tiết hàng hóa thay đổi");
//		}else{
//			for (int i = 0; i < obj.listorderChangeGoodsDetailDTO().size(); i++) {
//				OrderChangeGoodsDetailDTO oderchange = new OrderChangeGoodsDetailDTO();
//				List<OrderChangeGoodsDetailDTO> lstDTO = orderChangeGoodsDetailDAO.checkSerial(oderchange);
//				List<String> lstSerial1 = new ArrayList<>();
//				for (int p = 0; p < lstDTO.size(); p++) {
//					lstSerial1.add(lstDTO.get(p).getSerial());
//				}
//				boolean check = false;
//				boolean check1 = false;
//					GoodsDTO goodsDTO=new GoodsDTO(); 
//					GoodsDTO goodsDTO1=	orderChangeGoodsDetailDAO.checkIsSerial(obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode().trim());
//					StockGoodsTotalDTO stockGoodsTotalDTO=new StockGoodsTotalDTO();
//					stockGoodsTotalDTO=orderChangeGoodsDAO.sumAmountChange(obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode());
//					if(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode()!=null){
//						goodsDTO =orderChangeGoodsDetailDAO.checkIsSerial(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode().trim());
//					}
//					if(Integer.parseInt(goodsDTO1.getIsSerial())==1){
//						if(obj.listorderChangeGoodsDetailDTO().get(i).getSerial()==null){
//							throw new IllegalArgumentException("Cần nhập Serial thay đổi (hàng thứ"+(i+1)+")");
//						}else if(obj.listorderChangeGoodsDetailDTO().get(i).getSerial()!=null){
//							check =orderChangeGoodsDetailDAO.checkDuplicateSerial(obj.getListorderChangeGoodsDetailDTO().get(i).getGoodsCode().toUpperCase().trim(),obj.getListorderChangeGoodsDetailDTO().get(i).getSerial().toUpperCase().trim());
//							if(!check){
//								throw new IllegalArgumentException("Serial "+obj.listorderChangeGoodsDetailDTO().get(i).getSerial()+" không phù hợp với mã hàng "+obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode());
//							}
//								for(int k=i+1;k< obj.listorderChangeGoodsDetailDTO().size();k++){
//									if(obj.listorderChangeGoodsDetailDTO().get(i).getSerial().trim().equals(obj.listorderChangeGoodsDetailDTO().get(k).getSerial()!=null?(obj.listorderChangeGoodsDetailDTO().get(k).getSerial().trim()):null)){
//										throw new IllegalArgumentException("Trùng serial thay đổi trong danh sách");
//										}
//									}
//								
//							
//						}
//						if(obj.getListorderChangeGoodsDetailDTO().get(i).getNewGoodsCode()!=null&&obj.getListorderChangeGoodsDetailDTO().get(i).getNewGoodsCode()!=""){
//							if(goodsDTO!=null&&Integer.parseInt(goodsDTO.getIsSerial())==0){
//								throw new IllegalArgumentException("Không thể thay đổi hàng hóa từ có quản lý serial sang không quản lý serial");
//							}
//						}
//					}else if(Integer.parseInt(goodsDTO1.getIsSerial())==0){
//								if(obj.listorderChangeGoodsDetailDTO().get(i).getSerial()!=null&&obj.listorderChangeGoodsDetailDTO().get(i).getSerial()!=""){
//									throw new IllegalArgumentException("Mã "+obj.listorderChangeGoodsDetailDTO().get(i).getGoodsCode()+" không có quản lý serial, không được nhập serial thay đổi ");
//						}
//					} 
//					if(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode()==null){
//						throw new IllegalArgumentException("Cần nhập mã hàng sau thay đổi");
//					}else if(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode()!=null){
//						if(obj.getListorderChangeGoodsDetailDTO().get(i).getNewSerial()!=null){
//							check1=orderChangeGoodsDetailDAO.checkDuplicateSerial(obj.getListorderChangeGoodsDetailDTO().get(i).getNewGoodsCode().trim(),obj.getListorderChangeGoodsDetailDTO().get(i).getNewSerial().trim());
//						}
////						if(goodsDTO!=null&&Integer.parseInt(goodsDTO.getIsSerial())==1){
////							if(obj.getListorderChangeGoodsDetailDTO().get(i).getNewGoodsCode()!=null&&obj.getListorderChangeGoodsDetailDTO().get(i).getNewGoodsCode()!=""){
////								throw new IllegalArgumentException("Không thể thay đổi hàng hóa từ không quản lý serial sang có quản lý serial");
////							}
////						}
//					}
//					if(stockGoodsTotalDTO.getAmount()==null){
//						throw new IllegalArgumentException("Mã hàng "+obj.getListorderChangeGoodsDetailDTO().get(i).getGoodsCode().trim()+" không tồn tại trong kho tồn!");
//					}
//					if(!lstCode.contains(obj.getListorderChangeGoodsDetailDTO().get(i).getGoodsCode().trim())){
//						throw new IllegalArgumentException("Mã hàng "+obj.getListorderChangeGoodsDetailDTO().get(i).getGoodsCode().trim()+" không tồn tại!");
//					}
//					 if(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode()!=null){
//						if (!lstCode.contains(obj.listorderChangeGoodsDetailDTO().get(i).getNewGoodsCode().trim())) {
//							throw new IllegalArgumentException("Mã hàng sau thay đổi không tồn tại");
//						}
//						flag=0;
//					}
//					
//					if(goodsDTO!=null&&Integer.parseInt(goodsDTO.getIsSerial())==1 ){
//					if((obj.listorderChangeGoodsDetailDTO().get(i).getNewSerial()!=null&&obj.listorderChangeGoodsDetailDTO().get(i).getNewSerial()!="")&&flag==0){
//							if(check1){
//								throw new IllegalArgumentException("Serial sau thay đổi của mã hàng sau thay đổi đã tồn tại");
//							}
//							for(int k=i+1;k< obj.listorderChangeGoodsDetailDTO().size();k++){
//								if(obj.listorderChangeGoodsDetailDTO().get(i).getNewSerial().trim().equals(obj.listorderChangeGoodsDetailDTO().get(k).getNewSerial()!=null?(obj.listorderChangeGoodsDetailDTO().get(k).getNewSerial().trim()):null)){
//									throw new IllegalArgumentException("Trùng serial sau thay đổi trong danh sách");
//									}
//								}
//							
//							
//							}else{
//								throw new IllegalArgumentException("Mã hàng sau thay đổi có quản lý serial cần nhập serial sau thay đổi");
//							}
//						}else if(goodsDTO!=null&&Integer.parseInt(goodsDTO.getIsSerial())==0){
//							if((obj.listorderChangeGoodsDetailDTO().get(i).getNewSerial()!=null&&obj.listorderChangeGoodsDetailDTO().get(i).getNewSerial()!="")&&flag==0){
//								throw new IllegalArgumentException("Mã hàng sau thay đổi không có quản lý serial không được nhập serial sau thay đổi");
//						     }
//						}
//						/*else{
//							obj.listorderChangeGoodsDetailDTO().get(i).setNewSerial(obj.listorderChangeGoodsDetailDTO().get(i).getSerial().trim());
//							boolean checkDuplicateSerial=orderChangeGoodsDetailDAO.checkDuplicateSerial(obj.getListorderChangeGoodsDetailDTO().get(i).getNewGoodsCode().trim(),
//									obj.getListorderChangeGoodsDetailDTO().get(i).getNewSerial().trim());
//							if(checkDuplicateSerial){
//								throw new IllegalArgumentException("Serial sau thay đổi của mã hàng sau thay đổi đổi đã tồn tại");
//							}
//					}*/
//					
//			
//			}
//			return true;
//		}
//		
//	}
//	
//	
//	public String exportOrderChangeExcelError(OrderChangeGoodsDetailDTO errorObj) throws Exception{
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
//			XSSFCell cell = row.getCell(7);
//			
//			if(cell ==null){
//				cell = row.createCell(7);
//			}
//			if(!cell.getStringCellValue().isEmpty()){
//				cell.setCellValue(cell.getStringCellValue()+","+errorObj.getLstErrorOrderGoods().get(i).getDetailError());
//			}else{
//				cell.setCellValue(errorObj.getLstErrorOrderGoods().get(i).getDetailError());
//			}
//		}
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar + "ThayDoiHangHoa.xlsx");
//		
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//		
//		String path = UEncrypt.encryptFileUploadPath("ThayDoiHangHoa.xlsx");
//		return path;
//		
//	}
//	@Override
//	public List<OrderChangeGoodsDTO> doSearchForCheckAll(OrderChangeGoodsDTO obj) {
//		// TODO Auto-generated method stub
//		return orderChangeGoodsDAO.doSearchForCheckAll(obj);
//	}
//	
//}
