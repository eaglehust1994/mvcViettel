//package com.viettel.wms.business;
// 
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
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
//import org.springframework.transaction.annotation.Transactional;
//
//import com.google.common.collect.Lists;
//import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
//import com.viettel.erp.dto.EstimatesWorkItemsDTO;
//import com.viettel.ktts.vps.VpsPermissionChecker;
//import com.viettel.ktts2.common.BusinessException;
//import com.viettel.ktts2.common.UEncrypt;
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.bo.OrderBO;
//import com.viettel.wms.constant.Constants;
////import com.viettel.wms.dao.GoodsDAO;
//import com.viettel.wms.dao.GoodsTypeDAO;
//import com.viettel.wms.dao.ManufacturerDAO;
//import com.viettel.wms.dao.OrderDAO;
//import com.viettel.wms.dao.OrderGoodsDAO;
//import com.viettel.wms.dao.OrderGoodsDetailDAO;
//import com.viettel.wms.dao.ProducingCountryDAO;
//import com.viettel.wms.dao.ShipmentDAO;
//import com.viettel.wms.dto.CommonDTO;
//import com.viettel.cat.dto.GoodsDTO;
//import com.viettel.wms.dto.GoodsTypeDTO;
//import com.viettel.wms.dto.ManufacturerDTO;
//import com.viettel.wms.dto.ObjectReferenceDTO;
//import com.viettel.wms.dto.OrderDTO;
//import com.viettel.wms.dto.OrderGoodsDTO;
//import com.viettel.wms.dto.OrderGoodsDetailDTO;
//import com.viettel.wms.dto.OrderGoodsExelDTO;
//import com.viettel.wms.dto.ProducingCountryDTO;
//import com.viettel.wms.dto.SignVofficeDTO;
//import com.viettel.wms.dto.StockCellDTO;
//import com.viettel.wms.dto.StockDTO;
//import com.viettel.wms.utils.ValidateUtils;
//
//import fr.opensagres.xdocreport.core.XDocReportException;
//import fr.opensagres.xdocreport.document.IXDocReport;
//import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
//import fr.opensagres.xdocreport.template.IContext;
//import fr.opensagres.xdocreport.template.TemplateEngineKind;
//import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
//
//
//@Service("orderBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class OrderBusinessImpl extends BaseFWBusinessImpl<OrderDAO,OrderDTO, OrderBO> implements OrderBusiness {
//	
//static Logger LOGGER = LoggerFactory.getLogger(OrderBusinessImpl.class);
//	
//	List<OrderGoodsExelDTO> lstErrorOrder;
//
//	 @Autowired
//	 private CommonBusiness commonBusiness;
//	
//	@Autowired
//    private OrderDAO orderDAO;
//    
//    @Autowired
//    private OrderGoodsDAO orderGoodsDAO;
//    
//    @Autowired
//    private OrderGoodsDetailDAO orderGoodsDetailDAO;
//    
//    @Autowired
//    private OrderGoodsBusinessImpl orderGoodsBusinessImpl;
//    
//    @Autowired
//    private OrderGoodsDetailBusinessImpl orderGoodsDetailBusinessImpl;
//    
////    @Autowired
////    private GoodsBusinessImpl goodsBusinessImpl;
//    
////    @Autowired
////    private GoodsDAO goodsDAO;
//    
//    @Autowired
//    private ManufacturerBusinessImpl manufacturerBusinessImpl;
//    
//    @Autowired
//    private ProducingCountryBusinessImpl producingCountryBusinessImpl;
//    
//    @Autowired
//	private GoodsTypeDAO goodsTypeDAO;
//    
//    @Autowired
//	private ShipmentDAO shipmentDAO;
//    
//    @Autowired
//    private ManufacturerDAO manufacturerDAO;
//    
//    @Autowired
//    private ProducingCountryDAO producingCountryDAO;
//    
//    @Autowired
//   	private StockBusinessImpl stockBusinessImpl;
//   	    
//    
//    @Value("${folder_upload}")
//	private String folder2Upload;
//
//     
//    public OrderBusinessImpl() {
//        tModel = new OrderBO();
//        tDAO = orderDAO;
//    }
//
//    @Override
//    public OrderDAO gettDAO() {
//        return orderDAO;
//    }
//
//    @Override
//    public long count() {
//        return orderDAO.count("OrderBO", null);
//    }
//
//    //Tim kiem yeu cau nhap kho - Quan ly yeu cau nhap kho - NgocCX
//	@Override
//	public DataListDTO doSearchImportRequirement(OrderDTO obj,HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		String err="";
//    	for(Long id:obj.getListStockId()){
//    		StockDTO stockDTO=(StockDTO) stockBusinessImpl.getOneById(id);
//    		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,id, request)){
//    			err=StringUtils.isNotEmpty(err)? (err+";"+id):("Bạn không có quyền xem yêu cầu tại kho "+id);
//    		}
//    	}
//    	
//    	if(StringUtils.isNotEmpty(err)){
//    		throw new IllegalArgumentException(err);
//    	}
//    	
//    	if(obj.getListStockId().size()==0){
//    		List<Long> listId=commonBusiness.getListDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK, request);
//    		obj.setListStockId(listId);
//    	}
//		List<OrderDTO> ls = orderDAO.doSearchImportRequirement(obj);
//		   DataListDTO data = new DataListDTO();
//		   data.setData(ls);
//		   data.setTotal(obj.getTotalRecord());
//		   data.setSize(obj.getTotalRecord());
//		   data.setStart(1);
//		return data;
//	}
//
//	//Tim kiem ban ghi - Viet phieu nhap kho - NgocCX
//	@Override
//	public DataListDTO doSearchForCreateImportNote(OrderDTO obj,HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		String err="";
//    	for(Long id:obj.getListStockId()){
//    		StockDTO stockDTO=(StockDTO) stockBusinessImpl.getOneById(id);
//    		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.VIEW, Constants.AdResourceKey.STOCK,id, request)){
//    			err=StringUtils.isNotEmpty(err)? (err+";"+id):("Bạn không có quyền xem báo cáo tại kho "+id);
//    		}
//    	}
//    	
//    	if(StringUtils.isNotEmpty(err)){
//    		throw new IllegalArgumentException(err);
//    	}
//    	
//    	if(obj.getListStockId().size()==0){
//    		List<Long> listId=commonBusiness.getListDomainData(Constants.OperationKey.VIEW, Constants.AdResourceKey.STOCK, request);
//    		obj.setListStockId(listId);
//    	}
//		List<OrderDTO> ls = orderDAO.doSearchForCreateImportNote(obj);
//		DataListDTO data = new DataListDTO();
//		   data.setData(ls);
//		   data.setTotal(obj.getTotalRecord());
//		   data.setSize(obj.getTotalRecord());
//		   data.setStart(1);
//		return data;
//	}
//
//	//Chi tiet yeu cau nhap kho
//	@Override
//	public OrderDTO getOrderDetail(Long id) {
//		// TODO Auto-generated method stub
//		OrderDTO data = orderDAO.getOrderDetail(id);
//		return data;
//	}
//
//	@Override
//	public DataListDTO doSearchExportStatementManagement(OrderDTO obj) {
//		// TODO Auto-generated method stub
//		 List<OrderDTO> ls = orderDAO.doSearchExportStatement(obj);
//		   DataListDTO data = new DataListDTO();
//		   data.setData(ls);
//		   data.setTotal(obj.getTotalRecord());
//		   data.setSize(obj.getTotalRecord());
//		   data.setStart(1);
//		return data;
//	}
//
//	@Override
//	public DataListDTO doSearchExportRequirement(OrderDTO obj,HttpServletRequest request) throws Exception {
//		// TODO Auto-generated method stub
//		String err="";
//    	for(Long id:obj.getListStockId()){
//    		StockDTO stockDTO=(StockDTO) stockBusinessImpl.getOneById(id);
//    		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,id, request)){
//    			err=StringUtils.isNotEmpty(err)? (err+";"+id):("Bạn không có quyền xem báo cáo tại kho "+id);
//    		}
//    	}
//    	
//    	if(StringUtils.isNotEmpty(err)){
//    		throw new IllegalArgumentException(err);
//    	}
//    	
//    	if(obj.getListStockId().size()==0){
//    		List<Long> listId=commonBusiness.getListDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK, request);
//    		obj.setListStockId(listId);
//    	}
//		List<OrderDTO> ls = orderDAO.doSearchExportRequirement(obj);
//		   DataListDTO data = new DataListDTO();
//		   data.setData(ls);
//		   data.setTotal(obj.getTotalRecord());
//		   data.setSize(obj.getTotalRecord());
//		   data.setStart(1);
//		return data;
//	}
//
//	public OrderDTO getOderByShipment(OrderDTO obj) {
//		return orderDAO.getOderByShipment(obj);
//	}
//
//	//Ghi lai yeu cau nhap/xuat - NgocCX
//	@Override
//	@Transactional
//	public Long saveImportReq(OrderDTO obj,HttpServletRequest request) throws Exception{
//		// TODO Auto-generated method stub
//		String err="";
//    	for(Long id:obj.getListStockId()){
//    		StockDTO stockDTO=(StockDTO) stockBusinessImpl.getOneById(id);
//    		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,id, request)){
//    			err=StringUtils.isNotEmpty(err)? (err+";"+id):("Bạn không có quyền xem báo cáo tại kho "+id);
//    		}
//    	}
//    	
//    	if(StringUtils.isNotEmpty(err)){
//    		throw new IllegalArgumentException(err);
//    	}
//		OrderDTO checkDto = new OrderDTO();
//		checkDto.setCode(obj.getCode());
//		List<OrderDTO> lstCheckDto = orderDAO.checkDupOrderCode(checkDto);
//		if(lstCheckDto.size()>0){
//			throw new BusinessException("Mã yêu cầu đã tồn tại");
//		}
//		obj.setCreatedDate(new Date());
//		String dx = obj.getCode().substring(0, 4);
//		if(obj.getCode().substring(0, 4).equalsIgnoreCase("YCXK"))
//		{
//			obj.setType("2");
//		}
//		else
//		{
//			if(!obj.getShipmentCode().isEmpty() && obj.getShipmentCode()!=null){
//				obj.setShipmentId(shipmentDAO.getbycode(obj.getShipmentCode()).getShipmentId());
//			}
//			obj.setType("1");
//		}
//		obj.setStatus("1");
//		obj.setSignState("1");
//		Long id = save(obj);
//		for(int i = 0;i<obj.getListOrderGoodsDTO().size();i++){
//			obj.getListOrderGoodsDTO().get(i).setOrderId(id);
//			if(obj.getListOrderGoodsDTO().get(i).getGoodsStateName()==null){
//				obj.getListOrderGoodsDTO().get(i).setGoodsStateName("Bình thường");
//			}
//			if(obj.getListOrderGoodsDTO().get(i).getGoodsStateName().toUpperCase().equals("Bình thường".toUpperCase())){
//				obj.getListOrderGoodsDTO().get(i).setGoodsState("1");
//			}else{
//				obj.getListOrderGoodsDTO().get(i).setGoodsState("2");
//			}
//			if(obj.getListOrderGoodsDTO().get(i).getGoodsId()==null){
//				Long goodsId = 0l;
////				GoodsDTO gDto = goodsDAO.getGoodByCode(obj.getListOrderGoodsDTO().get(i).getGoodsCode());
//				GoodsDTO gDto = new GoodsDTO();
//				goodsId = gDto.getGoodsId();
//				obj.getListOrderGoodsDTO().get(i).setGoodsId(goodsId);
//			}
//			
//			
//			//Lay thong tin loai hang hoa, hang hoa co theo serial hay khong
////			GoodsDTO gdtoById = goodsDAO.getGoodById(obj.getListOrderGoodsDTO().get(i).getGoodsId());
//			GoodsDTO gdtoById = new GoodsDTO();
//			obj.getListOrderGoodsDTO().get(i).setGoodsType(gdtoById.getGoodsType());
//			obj.getListOrderGoodsDTO().get(i).setGoodsIsSerial(gdtoById.getIsSerial());
////			obj.getListOrderGoodsDTO().get(i).setGoodsUnitId(gdtoById.getGoodsUnitId());
//			obj.getListOrderGoodsDTO().get(i).setTotalPrice(gdtoById.getOriginPrice()*obj.getListOrderGoodsDTO().get(i).getAmount());
//			//Lay thong tin ten loai hang hoa
//			GoodsTypeDTO gtdto = goodsTypeDAO.getGoodTypeById(Long.parseLong(obj.getListOrderGoodsDTO().get(i).getGoodsType()));
//			obj.getListOrderGoodsDTO().get(i).setGoodsTypeName(gtdto.getName());
//			
//			Long orderGoodsId = orderGoodsBusinessImpl.save(obj.getListOrderGoodsDTO().get(i));
//			if(obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO()!=null){
//				for(int j =0;j<obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().size();j++){
//					obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().get(j).setOrderId(id);
//					obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().get(j).setOrderGoodsId(orderGoodsId);
//					obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().get(j).setPrice(gdtoById.getOriginPrice());
//					orderGoodsDetailBusinessImpl.save(obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().get(j));
//				}
//			}
//		}
//		return id;
//	}
//
//	//Xoa yeu cau nhap kho - NgocCX
//	@Override
//	public boolean removeOrder(OrderDTO obj,KttsUserSession objUser) {
//		// TODO Auto-generated method stub
//		if(!obj.getCreatedBy().equals(objUser.getSysUserId())){
//			throw new  IllegalArgumentException("Người dùng hiện tại không có quyền xóa bản ghi này !");
//		}
//		return orderDAO.removeOrder(obj);
//	}
//
//
//
//	@Override
//	public String exportDocSerial(OrderDTO obj) throws Exception {
//		// TODO Auto-generated method stub
//		OrderDTO order = getOrderDetail(obj.getOrderId());
//		
//		OrderGoodsDTO goodsDTO = new OrderGoodsDTO();
//		goodsDTO.setOrderId(order.getOrderId());
//		List<OrderGoodsDTO> goods = orderGoodsDAO.doSearchGoodsForImportReq(goodsDTO);
//		
//		for(int i=0;i<goods.size();i++){
//			OrderGoodsDetailDTO goodsDetailDTO = new OrderGoodsDetailDTO();
//			goodsDetailDTO.setOrderGoodsId(goods.get(i).getOrderGoodsId());
//			List<OrderGoodsDetailDTO> lst = orderGoodsDetailDAO.doSearchGoodsDetailForImportReq(goodsDetailDTO);
//			
//			goods.get(i).setListOrderGoodsDetailDTO(lst);
//			goods.get(i).setStt(i+1);
//		}
//		
//		try {
//			// 1) Load Docx file by filling Freemarker template engine and cache
//			// it to the registry
//			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//			String filePath = classloader.getResource("../" + "doc-template").getPath();
//
//			InputStream in = new FileInputStream(new File(filePath + "/YeuCauNhapKho_CoSerial.docx"));
//			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
//
//			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)			// for foot notes.
//			FieldsMetadata metadata = report.createFieldsMetadata();
//			
//			metadata.load("goods", OrderGoodsDTO.class, true);
//			// 3) Create context Java model
//			IContext context = report.createContext();
//
//			context.put("item", order);
//			context.put("goods", goods);
//
//			/*
//			 * // 4) Generate report by merging Java model with the Docx
//			 * //OutputStream out = new FileOutputStream( new File(
//			 * "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) ); OutputStream
//			 * out = new FileOutputStream( new File(
//			 * "D:\\WORK\\Test\\BM-TCNT-10.docx" ) ); report.process( context,
//			 * out );
//			 */
//
//			// pdf
//			File fout = new File(folder2Upload +"/" +"Co_serial.docx");
//			OutputStream out = new FileOutputStream(fout);
//			report.process(context, out);
//			out.flush();
//			out.close();
//			String path  =   UEncrypt.encryptFileUploadPath("Co_serial.docx");
//			return path;
//		} catch (IOException e) {
//			LOGGER.error(e.getMessage(), e);
//		} catch (XDocReportException e) {
//			LOGGER.error(e.getMessage(), e);
//		}
//		return null;
//	}
//
//	//Cap nhat yeu cau nhap kho - NgocCX
//	@Override
//	@Transactional
//	public Long updateImportReq(OrderDTO obj,KttsUserSession objUser,HttpServletRequest request) throws Exception{
//		// TODO Auto-generated method stub
//		if(objUser.getSysUserId()!=null){
//			if(!obj.getCreatedBy().equals(objUser.getSysUserId())){
//				throw new  IllegalArgumentException("Người dùng hiện tại không có quyền sửa bản ghi này !");
//			}
//		}
//		String err="";
//    	for(Long id:obj.getListStockId()){
//    		StockDTO stockDTO=(StockDTO) stockBusinessImpl.getOneById(id);
//    		if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,id, request)){
//    			err=StringUtils.isNotEmpty(err)? (err+";"+id):("Bạn không có quyền xem báo cáo tại kho "+id);
//    		}
//    	}
//    	
//    	if(StringUtils.isNotEmpty(err)){
//    		throw new IllegalArgumentException(err);
//    	}
//		if(obj.getListOrderGoodsDTO() != null){
//			OrderGoodsDTO objToDelete = new OrderGoodsDTO();
//			objToDelete.setOrderId(obj.getOrderId());
//			List<OrderGoodsDTO> lstObjToDelete = orderGoodsDAO.doSearchGoodsForImportReq(objToDelete);
//			for(int i = 0;i<lstObjToDelete.size();i++){
//				orderGoodsBusinessImpl.delete(lstObjToDelete.get(i));
//			}
//			/*for(int i = 0; i<obj.getListOrderGoodsDTO().size();i++){
//				obj.getListOrderGoodsDTO().get(i).setOrderId(obj.getOrderId());
//				orderGoodsBusinessImpl.save(obj.getListOrderGoodsDTO().get(i));
//			}*/
//			for(int i = 0;i<obj.getListOrderGoodsDTO().size();i++){
//				obj.getListOrderGoodsDTO().get(i).setOrderId(obj.getOrderId());
//				if(obj.getListOrderGoodsDTO().get(i).getGoodsStateName()==null){
//					obj.getListOrderGoodsDTO().get(i).setGoodsStateName("Bình thường");
//				}
//				if(obj.getListOrderGoodsDTO().get(i).getGoodsStateName().toUpperCase().equals("Bình thường".toUpperCase())){
//					obj.getListOrderGoodsDTO().get(i).setGoodsState("1");
//				}else{
//					obj.getListOrderGoodsDTO().get(i).setGoodsState("0");
//				}
//				if(obj.getListOrderGoodsDTO().get(i).getGoodsId()==null){
//					Long goodsId = 0l;
////					GoodsDTO gDto = goodsDAO.getGoodByCode(obj.getListOrderGoodsDTO().get(i).getGoodsCode());
//					GoodsDTO gDto = new GoodsDTO();
//					goodsId = gDto.getGoodsId();
//					obj.getListOrderGoodsDTO().get(i).setGoodsId(goodsId);
//				}
//				
//				
//				//Lay thong tin loai hang hoa, hang hoa co theo serial hay khong
////				GoodsDTO gdtoById = goodsDAO.getGoodById(obj.getListOrderGoodsDTO().get(i).getGoodsId());
//				GoodsDTO gdtoById = new GoodsDTO();
//				obj.getListOrderGoodsDTO().get(i).setGoodsType(gdtoById.getGoodsType());
//				obj.getListOrderGoodsDTO().get(i).setGoodsIsSerial(gdtoById.getIsSerial());
////				obj.getListOrderGoodsDTO().get(i).setGoodsUnitId(gdtoById.getGoodsUnitId());
//				obj.getListOrderGoodsDTO().get(i).setTotalPrice(gdtoById.getOriginPrice()*obj.getListOrderGoodsDTO().get(i).getAmount());
//				//Lay thong tin ten loai hang hoa
//				GoodsTypeDTO gtdto = goodsTypeDAO.getGoodTypeById(Long.parseLong(obj.getListOrderGoodsDTO().get(i).getGoodsType()));
//				obj.getListOrderGoodsDTO().get(i).setGoodsTypeName(gtdto.getName());
//				
//				Long orderGoodsId = orderGoodsBusinessImpl.save(obj.getListOrderGoodsDTO().get(i));
//				if(obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO()!=null){
//					for(int j =0;j<obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().size();j++){
//						obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().get(j).setOrderId(obj.getOrderId());
//						obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().get(j).setOrderGoodsId(orderGoodsId);
//						obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().get(j).setPrice(gdtoById.getOriginPrice());
//						orderGoodsDetailBusinessImpl.save(obj.getListOrderGoodsDTO().get(i).getListOrderGoodsDetailDTO().get(j));
//					}
//				}
//			}
//		}
//		return update(obj);
//	}
//
//	//Tu choi yeu cau nhap kho - NgocCX
//	@Override
//	public boolean rejectOrder(OrderDTO obj,KttsUserSession objUser) {
//		// TODO Auto-generated method stub
//		if(!obj.getCreatedBy().equals(objUser.getSysUserId())){
//			throw new  IllegalArgumentException("Người dùng hiện tại không có quyền từ chối bản ghi này !");
//		}
//		return orderDAO.rejectOrder(obj);
//	}
//	
//	public boolean removeExportOrder(OrderDTO obj) {
//		 if(obj.getCancelReasonApply().equals( "5") ){
//			 obj.setStatus("4");
//		 }else{
//			 obj.setStatus("5");
//		 }
//		 obj.setCancelDate(new Date());
//		 obj.setCancelByName(obj.getCreatedByName());
//	    
//		 Long isCancel = 0l;
//		 
//		 isCancel = update(obj);
//		 
//		 return (isCancel > 0) ? true: false;
//	}
//	
//	@Override
//	public Long replicateExportOrder(OrderDTO originalOrder) {
//				Long isCopied = 0l;
//				originalOrder.setCreatedDate(new Date());
//				isCopied = save(originalOrder);	
//				if(isCopied > 0) return isCopied;
//				return null;
//		}
//
//	@Override
//	@Transactional
//	public boolean saveExportOrder(OrderDTO obj) {
//		obj.setType("2");
//		//Long newOrderId = getMaxOrderId() + 1;
//		//obj.setCode(orderCodeGenerating(newOrderId, obj.getStockId()));
//		return true;
//	}
//	
//	public OrderDTO getOrder(Long id) {
//		// TODO Auto-generated method stub
//		OrderDTO data = orderDAO.getOrder(id);
//		return data;
//	}
//
//
//	public OrderDTO getOrderByShipment(OrderDTO obj) {
//		//return orderDAO.getOderByShipment(obj);
//		return null;
//	}
//
//	//Import Hang hoa - NgocCX
////	@Override
////	public List<GoodsDTO> importGoods(String fileInput) throws Exception {
////		public List<GoodsDTO> importGoods(String fileInput)  {
////		// TODO Auto-generated method stub
////		lstErrorOrder = new ArrayList<>();
////		List<GoodsDTO> workLst = Lists.newArrayList();
////		try {
////			
////			File f = new File(fileInput);
////
////			XSSFWorkbook workbook = new XSSFWorkbook(f);
////			XSSFSheet sheet = workbook.getSheetAt(0);
////
////			DataFormatter formatter = new DataFormatter();
////			int count = 0;
////
////			for (Row row : sheet) {
////				if(!ValidateUtils.checkIfRowIsEmpty(row)){
////				count++;
////				if (count >= 3) {
////
////					boolean checkColumn1 = true;
////					boolean checkColumn3 = true;
////					boolean checkColumn4 = true;
////					boolean checkColumn7 = true;
////					boolean checkColumn8 = true;
////					boolean checkColumn9 = true;
////					String code = "";
////					String amount = "";
////					String serial = "";
////					String status = "";
////					String manufacturerId = "";
////					String producingCountryId = "";
////
////					for (Cell cell : row) {
////
////						// Create object OrderGoodsExelDTO
////						OrderGoodsExelDTO orderErrorFormat = new OrderGoodsExelDTO();
////
////						// Check format file exel
////						if (cell.getColumnIndex() == 1) {
////							code = formatter.formatCellValue(cell);
////							checkColumn1 = checkDataFromFileExel(code.trim(), count, cell.getColumnIndex(), orderErrorFormat);
////						}else if (cell.getColumnIndex() == 3) {
////							manufacturerId = formatter.formatCellValue(cell);
////							if(!manufacturerId.isEmpty()){
////								if(!StringUtils.isNumeric(manufacturerId)){
////									OrderGoodsExelDTO obj = new OrderGoodsExelDTO();
////									obj.setLineError(String.valueOf(count));
////									obj.setColumnError(String.valueOf(4));
////									obj.setDetailError("ID hãng sản xuất không phải kiểu số");
////									lstErrorOrder.add(obj);
////									checkColumn3 = false;
////								}else{
////									ManufacturerDTO manuObj = manufacturerDAO.getAllNameById(Long.parseLong(manufacturerId));
////									if(manuObj == null){
////										OrderGoodsExelDTO obj = new OrderGoodsExelDTO();
////										obj.setLineError(String.valueOf(count));
////										obj.setColumnError(String.valueOf(4));
////										obj.setDetailError("ID hãng sản xuất không tồn tại");
////										lstErrorOrder.add(obj);
////										checkColumn3 = false;
////									}
////								}
////							}
////						}else if (cell.getColumnIndex() == 4) {
////							producingCountryId = formatter.formatCellValue(cell);
////							if(!producingCountryId.isEmpty()){
////								if(!StringUtils.isNumeric(producingCountryId)){
////									OrderGoodsExelDTO obj = new OrderGoodsExelDTO();
////									obj.setLineError(String.valueOf(count));
////									obj.setColumnError(String.valueOf(5));
////									obj.setDetailError("ID nước sản xuất không phải kiểu số");
////									lstErrorOrder.add(obj);
////									checkColumn4 = false;
////								}else{
////									ProducingCountryDTO produObj = producingCountryDAO.getAllNameById(Long.parseLong(producingCountryId));
////									if(produObj == null){
////										OrderGoodsExelDTO obj = new OrderGoodsExelDTO();
////										obj.setLineError(String.valueOf(count));
////										obj.setColumnError(String.valueOf(5));
////										obj.setDetailError("ID nước sản xuất không tồn tại");
////										lstErrorOrder.add(obj);
////										checkColumn4 = false;
////									}
////								}
////							}
////						} else if (cell.getColumnIndex() == 7) {
////							serial = formatter.formatCellValue(cell);
////							GoodsDTO goodDto = new GoodsDTO();
////							goodDto = goodsBusinessImpl.getGoodByCode(formatter.formatCellValue(row.getCell(1)));
////							if(goodDto !=null){
////								if(goodDto.getIsSerial().equals("1")){
////									checkColumn7 = checkDataFromFileExel(serial.trim(), count, cell.getColumnIndex(), orderErrorFormat);
////								}
////							}
////						} else if (cell.getColumnIndex() == 8) {
////							GoodsDTO goodDto = new GoodsDTO();
////							goodDto = goodsBusinessImpl.getGoodByCode(formatter.formatCellValue(row.getCell(1)));
////								amount = formatter.formatCellValue(cell);
////								if(amount.trim()!=null){
////									if(amount.trim().contains(".")){
////										if(goodDto !=null){
////										if(goodDto.getIsSerial().equals("1")){
////											OrderGoodsExelDTO obj = new OrderGoodsExelDTO();
////											obj.setLineError(String.valueOf(count));
////											obj.setColumnError(String.valueOf(9));
////											obj.setDetailError("Số lượng không được nhập số thập phân");
////											lstErrorOrder.add(obj);
////										}else{
////											checkColumn8 = checkDataFromFileExel(amount.trim(), count, cell.getColumnIndex(), orderErrorFormat);
////										}
////										}
////									}else{
////										checkColumn8 = checkDataFromFileExel(amount.trim(), count, cell.getColumnIndex(), orderErrorFormat);
////									}
////								}else{
////									checkColumn8 = checkDataFromFileExel(amount.trim(), count, cell.getColumnIndex(), orderErrorFormat);
////								}
////						} else if (cell.getColumnIndex() == 9) {
////							status =  formatter.formatCellValue(cell);
////							checkColumn9 = checkDataFromFileExel(status.trim(), count, cell.getColumnIndex(), orderErrorFormat);
////						}
////
////					}
////
////					if (checkColumn1 && checkColumn8 && checkColumn9 && checkColumn7 && checkColumn3 && checkColumn4) {
////						GoodsDTO newObj = new GoodsDTO();
////						newObj.setCode(row.getCell(1).getStringCellValue());
////						newObj.setName(row.getCell(2).getStringCellValue());
////						GoodsDTO gDto = new GoodsDTO();
////						gDto = goodsBusinessImpl.getGoodByCode(newObj.getCode());
////						if(row.getCell(2).getStringCellValue().isEmpty()){
////							newObj.setName(gDto.getName());
////							
////						}
////						newObj.setManufacturerId(Math.round(row.getCell(3).getNumericCellValue()));
////						newObj.setProducingCountryId(Math.round(row.getCell(4).getNumericCellValue()));
////						
////						if(newObj.getManufacturerId()==null || newObj.getManufacturerId()==0){
////							newObj.setManufacturerId(gDto.getManufacturerId());
////							newObj.setManufacturerName(manufacturerDAO.getAllNameById(newObj.getManufacturerId()).getName());
////						}
////						if(newObj.getProducingCountryId()==null || newObj.getProducingCountryId()==0){
////							newObj.setProducingCountryId(gDto.getProducingCountryId());
////							newObj.setProducingCountryName(producingCountryDAO.getAllNameById(newObj.getProducingCountryId()).getName());
////						}
////						
////						if(newObj.getManufacturerId()!=null && newObj.getManufacturerId()!=0){
////							newObj.setManufacturerName(manufacturerDAO.getAllNameById(newObj.getManufacturerId()).getName());
////						}
////						if(newObj.getProducingCountryId()!=null && newObj.getProducingCountryId()!=0){
////							newObj.setProducingCountryName(producingCountryDAO.getAllNameById(newObj.getProducingCountryId()).getName());
////						}
////						
////						newObj.setPartNumber(String.valueOf(Math.round(row.getCell(5).getNumericCellValue())));
////						newObj.setContractNumber(String.valueOf(row.getCell(6).getNumericCellValue()));
////						newObj.setSerial(String.valueOf(row.getCell(7).getStringCellValue()));
////						newObj.setAmount(row.getCell(8).getNumericCellValue());
////						newObj.setStatus(String.valueOf(Math.round(row.getCell(9).getNumericCellValue())));
////						newObj.setGoodsUnitName(goodsBusinessImpl.getGoodByCode(newObj.getCode()).getGoodsUnitName());
////						workLst.add(newObj);
////					}
////
////				}
////			}
////			}
////
////			workbook.close();
////
////		} catch (NullPointerException pointerException) {
////			// pointerException.printStackTrace();
////			LOGGER.error(pointerException.getMessage(), pointerException);
////		} catch (Exception e) {
////			// e.printStackTrace();
////			LOGGER.error(e.getMessage(), e);
////		}
////		/*if (workLst.size() == 0) {
////			throw new IllegalArgumentException("Không có công việc phù hợp với những công việc trên lưới dữ liệu");
////		}*/
////
////		String filePathError = UEncrypt.encryptFileUploadPath(fileInput);
////		GoodsDTO objError = new GoodsDTO();
////		objError.setLstErrorGoods(lstErrorOrder);
////		objError.setFilePathError(filePathError);
////		
////		workLst.add(objError);
////
////		return workLst;
////	}
//
////	public boolean checkDataFromFileExel(String data, int rowIndex, int columnIndex,
////			OrderGoodsExelDTO orderErrorFormat) {
////		
////		if (columnIndex == 1) {
////			GoodsDTO dto = new GoodsDTO();
////			dto.setCode(data);
////			List<GoodsDTO> lstGoods = goodsBusinessImpl.getGoodsForOrder(dto);
////			if (data.isEmpty()) {
////				
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
////				orderErrorFormat.setDetailError("Mã hàng đang để trống");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////			if (lstGoods.size()==0) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
////				orderErrorFormat.setDetailError("Mã hàng không tồn tại");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////			if (data.length() > 100) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
////				orderErrorFormat.setDetailError("Mã hàng vượt quá 50 ký tự");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////		} else if (columnIndex == 7) {
////			if (data.isEmpty()) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
////				orderErrorFormat.setDetailError("Serial đang để trống");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////			if (data.length()>100) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
////				orderErrorFormat.setDetailError("Serial vượt quá 100 ký tự");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////		}else if (columnIndex == 8) {
////			if (data.isEmpty()) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
////				orderErrorFormat.setDetailError("Số lượng đang để trống");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////			
////			if (!StringUtils.isNumeric(data)) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
////				orderErrorFormat.setDetailError("Số lượng sai format không phải kiểu số");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////			if (Integer.parseInt(data)<=0) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
////				orderErrorFormat.setDetailError("Số lượng phải lớn hơn 0");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////			if(data.contains(".")){
////				if(data.replace(".", "").length()>10){
////					orderErrorFormat.setLineError(String.valueOf(rowIndex));
////					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
////					orderErrorFormat.setDetailError("Số lượng vượt quá 10 ký tự");
////					lstErrorOrder.add(orderErrorFormat);
////					return false;
////				}
////			}else{
////				if (data.length()>10) {
////					orderErrorFormat.setLineError(String.valueOf(rowIndex));
////					orderErrorFormat.setColumnError(String.valueOf(columnIndex));
////					orderErrorFormat.setDetailError("Số lượng vượt quá 10 ký tự");
////					lstErrorOrder.add(orderErrorFormat);
////					return false;
////				}
////			}
////		}else if (columnIndex == 9) {
////			if (data.isEmpty()) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex+1));
////				orderErrorFormat.setDetailError("Tình trạng đang để trống");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////			if (!StringUtils.isNumeric(data)) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
////				orderErrorFormat.setDetailError("Tình trạng không phải kiểu số");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			} else if (Integer.parseInt(data) != 1 && Integer.parseInt(data) != 2) {
////				orderErrorFormat.setLineError(String.valueOf(rowIndex));
////				orderErrorFormat.setColumnError(String.valueOf(columnIndex));
////				orderErrorFormat.setDetailError("Tình trạng không phải là giá trị 1 hoặc 2");
////				lstErrorOrder.add(orderErrorFormat);
////				return false;
////			}
////		}
////
////		return true;
////	}
//
//	@Override
//	public DataListDTO doSearchDeliveryOrder(OrderDTO obj) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	
//	public CommonDTO getCharThree(CommonDTO obj,HttpServletRequest request){
//		List<Long> listId=commonBusiness.getListDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK, request);
//		obj.setListStockId(listId);
//		CommonDTO objReturn= new CommonDTO();
//		List<CommonDTO> ls = orderDAO.getCharThree(obj);
//		if (ls.size() > 0) {
//			for (Iterator<CommonDTO> interator = ls.iterator(); interator.hasNext();) {
//				CommonDTO wi = interator.next();
//				objReturn.getListImStock().add(wi.getImStock());
//				objReturn.getListOutStock().add(wi.getOutStock());
//				objReturn.getListStockCode().add(wi.getStockCode());
//			}
//		}
//		return objReturn;
//	}
//
//	@Override
//	public boolean updateStatusOrder(OrderDTO obj) {
//		// TODO Auto-generated method stub
//		return orderDAO.updateStatusOrder(obj);
//	}
//
//	@Override
//	public String exportExcelTemplate() throws Exception {
//		// TODO Auto-generated method stub
//		List<ManufacturerDTO> lstManufacturer = manufacturerBusinessImpl.getAllNameAndId();
//		List<ProducingCountryDTO> lstProducingCountry = producingCountryBusinessImpl.getAllNameAndId();
//		
//		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//		String filePath = classloader.getResource("../" + "doc-template").getPath();
//		InputStream file = new BufferedInputStream(new FileInputStream(filePath + "NK_Import_HangHoa.xlsx"));
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		XSSFSheet sheet = workbook.getSheetAt(1);
//		
//		for(int i=0;i<lstProducingCountry.size();i++){
//			XSSFRow row = sheet.getRow(i+1);
//			XSSFCell cell1 = row.createCell(0);
//			cell1.setCellValue(lstProducingCountry.get(i).getProducingCountryId());
//			XSSFCell cell2 = row.createCell(1);
//			cell2.setCellValue(lstProducingCountry.get(i).getName());
//		}
//		for(int i=0;i<lstManufacturer.size();i++){
//			XSSFRow row = sheet.getRow(i+1);
//			XSSFCell cell3 = row.createCell(2);
//			cell3.setCellValue(lstManufacturer.get(i).getManufacturerId());
//			XSSFCell cell4 = row.createCell(3);
//			cell4.setCellValue(lstManufacturer.get(i).getName());
//		}
//		
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar + "NK_Import_HangHoa.xlsx");
//		
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//		
//		String path = UEncrypt.encryptFileUploadPath("NK_Import_HangHoa.xlsx");
//		return path;
//	}
//
//	@Override
//	public String exportExcelError(GoodsDTO errorObj) throws Exception {
//		// TODO Auto-generated method stub
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
//			XSSFCell cell = row.getCell(10);
//			
//			if(cell ==null){
//				cell = row.createCell(10);
//			}
//			if(!cell.getStringCellValue().isEmpty()){
//				cell.setCellValue(cell.getStringCellValue()+","+errorObj.getLstErrorGoods().get(i).getDetailError());
//			}else{
//				cell.setCellValue(errorObj.getLstErrorGoods().get(i).getDetailError());
//			}
//		}
//		file.close();
//		File out = new File(folder2Upload + File.separatorChar + "NK_Import_HangHoa.xlsx");
//		
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//		
//		String path = UEncrypt.encryptFileUploadPath("NK_Import_HangHoa.xlsx");
//		return path;
//	}
//	
//	public String exportExcelErrorXK(GoodsDTO errorObj) throws Exception {
//		// TODO Auto-generated method stub
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
//		File out = new File(folder2Upload + File.separatorChar + "XK_Import_HangHoa.xlsx");
//		
//		FileOutputStream outFile = new FileOutputStream(out);
//		workbook.write(outFile);
//		workbook.close();
//		outFile.close();
//		
//		String path = UEncrypt.encryptFileUploadPath("XK_Import_HangHoa.xlsx");
//		return path;
//	}
//	
//	@Override
//	public Long updateExportRequest(OrderDTO obj) {
//		// TODO Auto-generated method stub
//		return update(obj);
//	}
//
//	@Override
//	public List<OrderDTO> getGoodsDetailByOrderId(OrderDTO obj) {
//		//long id = obj.getOrderId();
//		return orderDAO.getGoodsDetailByOrderId(obj);
//	}
//
//	//Lay tat ca ban ghi cua yeu cau nhap kho
//	@Override
//	public List<OrderDTO> getAllImportRequirement(OrderDTO obj,HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		if(obj.getListStockId().size()==0){
//    		List<Long> listId=commonBusiness.getListDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK, request);
//    		obj.setListStockId(listId);
//    	}
//		return orderDAO.getAllImportRequirement(obj);
//	}
//
//	@Override
//	public List<OrderDTO> getAllExportStatement(OrderDTO obj) {
//		// TODO Auto-generated method stub
//		return orderDAO.getAllExportStatement(obj);
//	}
//	
//	public OrderDTO getOderByShipmentId(OrderDTO obj) {
//		return orderDAO.getOderByShipmentId(obj);
//	}
//
//
//}
