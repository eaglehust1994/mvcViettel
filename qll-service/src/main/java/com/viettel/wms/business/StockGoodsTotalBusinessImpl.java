//package com.viettel.wms.business;
//
//import com.viettel.wms.bo.StockGoodsTotalBO;
//import com.viettel.wms.constant.Constants;
//import com.viettel.wms.dao.StockGoodsTotalDAO;
//import com.viettel.wms.dto.StockDTO;
//import com.viettel.wms.dto.StockGoodsTotalDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//import com.viettel.ktts.vps.VpsPermissionChecker;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//
//@Service("stockGoodsTotalBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class StockGoodsTotalBusinessImpl
//		extends BaseFWBusinessImpl<StockGoodsTotalDAO, StockGoodsTotalDTO, StockGoodsTotalBO>
//		implements StockGoodsTotalBusiness {
//
//    @Autowired
//    private StockGoodsTotalDAO stockGoodsTotalDAO;
//    
//    @Autowired
//    private StockBusinessImpl stockBusinessImpl;
//     
//    public StockGoodsTotalBusinessImpl() {
//        tModel = new StockGoodsTotalBO();
//        tDAO = stockGoodsTotalDAO;
//    }
//
//    @Override
//    public StockGoodsTotalDAO gettDAO() {
//        return stockGoodsTotalDAO;
//    }
//
//    @Override
//    public long count() {
//        return stockGoodsTotalDAO.count("StockGoodsTotalBO", null);
//    }
//
//	@Override
//	public boolean updateStockGoodsTotal(StockGoodsTotalDTO stockGoodsTotalDTO) {
//		// TODO Auto-generated method stub
//			return stockGoodsTotalDAO.updateStockGoodsTotal(stockGoodsTotalDTO);
//	}
//
//	// tim kiem bao cao ton kho TUANNB
//    public DataListDTO doSearch(StockGoodsTotalDTO obj,HttpServletRequest request) throws Exception{
//    	String err="";
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
//    	   List<StockGoodsTotalDTO> ls = stockGoodsTotalDAO.doSearch(obj);
//    	   DataListDTO data = new DataListDTO();
//    	   data.setData(ls);
//    	   data.setTotal(obj.getTotalRecord());
//    	   data.setSize(obj.getTotalRecord());
//    	   data.setStart(1);
//    	   return data;
//     }
//    //end TUANNB
//    
//	public DataListDTO doSearchTotal(StockGoodsTotalDTO obj) {
//		List<StockGoodsTotalDTO> ls = stockGoodsTotalDAO.doSearchTotal(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//	
//	@Override
//	public StockGoodsTotalDTO getGood(Long stockId, Long goodsId, String goodsStateName, String goodsCode, String goodsName) {
//		// TODO Auto-generated method stub
//		return stockGoodsTotalDAO.getGood(stockId, goodsId, goodsStateName,goodsCode,goodsName);
//	}
//
//	public DataListDTO doSearchStockGood(StockGoodsTotalDTO obj) {
//		List<StockGoodsTotalDTO> ls = stockGoodsTotalDAO.doSearchStockGood(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//	
//	
//	
//	public String updateRemainGoodsNumberInStock(StockTransDetailDTO std){
//					String err="";
//					Long goods_id = std.getGoodsId();
//					String goods_state = std.getGoodsState();
////					if("1".equals(goods_state)){
//						StockGoodsTotalDTO goodsInNote = stockGoodsTotalDAO.getAmountIssueGoodsTotal(goods_id, std.getStockId(), goods_state);
//						if(goodsInNote == null || goodsInNote.getAmount() == null){
//							err="Không còn đủ số lượng để xuất kho";
//						}
//						Double remainNumberOfItems = goodsInNote.getAmount() - std.getAmountReal();
//							if(remainNumberOfItems < 0){
//								err="Không còn đủ số lượng để xuất kho";
//							}else{
//								goodsInNote.setAmount(remainNumberOfItems);
//								stockGoodsTotalDAO.updateAmount(goodsInNote,false);
//							}		
////					}else{
////						err="Hàng hóa ở trạng thái hỏng!";
////					}	
//		return err;
//		}
//	
//	public List<StockGoodsTotalDTO> getCheckStockGood(StockGoodsTotalDTO obj){
//		return stockGoodsTotalDAO.getCheckStockGood(obj);
//	}
//}
