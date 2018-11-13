//package com.viettel.wms.business;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.service.base.dto.DataListDTO;
//import com.viettel.wms.bo.StockBO;
//import com.viettel.wms.constant.Constants;
//import com.viettel.wms.dao.StockDAO;
//import com.viettel.wms.dto.DomainDataDTO;
//import com.viettel.wms.dto.GoodsDTO;
//import com.viettel.wms.dto.StockCellDTO;
//import com.viettel.wms.dto.StockDTO;
//
//@Service("stockBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class StockBusinessImpl extends BaseFWBusinessImpl<StockDAO, StockDTO, StockBO> implements StockBusiness {
//
//	@Autowired
//	private StockDAO stockDAO;
//
//	@Autowired
//	private CommonBusiness commonBusiness;
//	
//	@Autowired
//	private StockCellBusinessImpl stockCellBusiness;
//	@Autowired
//	DomainDataBusinessImpl domainDataBusinessImpl;
//	public StockBusinessImpl() {
//		tModel = new StockBO();
//		tDAO = stockDAO;
//	}
//
//	@Override
//	public StockDAO gettDAO() {
//		return stockDAO;
//	}
//
//	public long count() {
//		return 0;
//	}
//
//	public DataListDTO doSearch(StockDTO obj) {
//		List<StockDTO> ls = stockDAO.doSearch(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//
//	@SuppressWarnings("unused")
//	public Boolean checkCode(String code, Long stockId) {		
//		StockDTO obj = stockDAO.getbycode(code);
//		if (stockId == null) {
//			if (obj == null) {
//				return true;
//			} else {
//				return false;
//			}
//		} else {
//			if (obj == null) {
//				return true;
//			} else if (obj != null && obj.getStockId().longValue() == stockId) {
//				return true;
//			} else {
//				return false;
//			}
//		}
//
//	}
//	
//	@Transactional
//	 public Long createStock(StockDTO obj) throws Exception{
//			
//			boolean check=checkCode(obj.getCode(),null);
//			if(!check){
//				throw new IllegalArgumentException("Mã kho đã tồn tại !");
//			}
//			Long ids=stockDAO.saveObject(obj.toModel());
//			if(ids!=null){
//				
//				DomainDataDTO domainDataDto=new DomainDataDTO();
//				domainDataDto.setDomainTypeId(this.getDomainData().getDomainTypeId());
//				domainDataDto.setDataName(obj.getName());
//				domainDataDto.setParentId(obj.getParentId());
//				domainDataDto.setPath(obj.getParentId()+"/"+ids);
//				domainDataBusinessImpl.save(domainDataDto);
//			}
//			return ids;
//		}
//	@Transactional
//	 public Long updateStock(StockDTO obj,KttsUserSession objUser) throws Exception{
//				if(!objUser.getSysUserId().equals(obj.getCreatedBy())){
//					throw new  IllegalArgumentException("Người dùng hiện tại không có quyền sửa bản ghi này !");
//			}
//			
//			boolean check=checkCode(obj.getCode(), obj.getStockId());
//			if(!check){
//				throw new  IllegalArgumentException("Mã kho đã tồn tại !");
//			}
//			Long ids=stockDAO.updateObject(obj.toModel());
//			if(ids!=null){
//				DomainDataDTO domainDataDto=new DomainDataDTO();
//				domainDataDto.setDomainTypeId(this.getDomainData().getDomainTypeId());
//				domainDataDto.setDataName(obj.getName());
//				domainDataDto.setParentId(obj.getParentId());
//				domainDataDto.setPath(obj.getParentId()+"/"+ids);
//				/*obj.setDomainDataDTO(domainDataDto);*/
//				domainDataBusinessImpl.save(domainDataDto);
//			}
//			return ids;
//		} 
//	 
//	  public Long deleteStock(StockDTO obj,KttsUserSession objUser){
//			if(!objUser.getSysUserId().equals(obj.getCreatedBy())){
//				throw new  IllegalArgumentException("Người dùng hiện tại không có quyền xóa bản ghi này !");
//			}
//			
//			return stockDAO.updateObject(obj.toModel());
//		}
//
//	@Override
//	public long getTotal() {
//		return stockDAO.count("AdClientBO", null);
//	}
//
//
//	public List<StockDTO> getForAutoCompleteStock(StockDTO obj) {
//		return stockDAO.getForAutoCompleteStock(obj);
//	}
//
//	@Override
//	public List<StockDTO> getStocksForAutocomplete(StockDTO obj) {
//		// TODO Auto-generated method stub
//		return stockDAO.getStocksForAutocomplete(obj);
//	}
//
//	@Override
//	public List<StockDTO> getStocksForAutocompleteDropDown(StockDTO obj) {
//		// TODO Auto-generated method stub
//		return stockDAO.getStocksForAutocompleteDropDown(obj);
//	}
//	
//	public DataListDTO doSearchStockInPopUp(StockDTO obj) {
//		List<StockDTO> ls = stockDAO.doSearchStockInPopUp(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//	
//	public DataListDTO doSearchGoods(GoodsDTO obj) {
//		List<GoodsDTO> ls = stockDAO.doSearchGoodsInPopup(obj);
//		DataListDTO data = new DataListDTO();
//		data.setData(ls);
//		data.setTotal(obj.getTotalRecord());
//		data.setSize(obj.getTotalRecord());
//		data.setStart(1);
//		return data;
//	}
//	
//	 public DataListDTO doSearchGoodsInPopup(GoodsDTO obj) {
//			List<GoodsDTO> ls = stockDAO.doSearchGoodsInPopup(obj);
//			DataListDTO data = new DataListDTO();
//			data.setData(ls);
//			data.setTotal(obj.getTotalRecord());
//			data.setSize(obj.getTotalRecord());
//			data.setStart(1);
//			return data;
//		}
//	 
//	 public List<StockDTO> getListByNameOrCode(StockDTO obj){
//		 return stockDAO.getListByNameOrCode(obj);
//	 }
//	 
//	 
//	 public List<StockDTO> getForAutoCompleteStockDomain(StockDTO obj,HttpServletRequest request){
//		 List<Long> listId= commonBusiness.getListDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK, request);
//		 obj.setListId(listId);
//		 return stockDAO.getForAutoCompleteStockDomain(obj);
//	 }
//	 
//	 
//	 public DataListDTO doSearchStockInPopUpDomain(StockDTO obj,HttpServletRequest request) {
//		 	List<Long> listId= commonBusiness.getListDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK, request);
//		 	obj.setListId(listId);
//			List<StockDTO> ls = stockDAO.doSearchStockInPopUpDomain(obj);
//			DataListDTO data = new DataListDTO();
//			data.setData(ls);
//			data.setTotal(obj.getTotalRecord());
//			data.setSize(obj.getTotalRecord());
//			data.setStart(1);
//			return data;
//		}
//	 public DomainDataDTO getDomainData(){
//		 return stockDAO.getDomainData();
//	 }
//	 
//	public List<StockCellDTO> getChangeAreaByStock(StockDTO obj){
//		return stockDAO.getChangeAreaByStock(obj);
//	}
//	
//	@Transactional
//	public Long saveListStockCell(StockDTO obj){
//		Long ids=stockDAO.deleteStockId(obj);
//		if(ids!=null){
//			for(int i=0;i<obj.getLstStockCell().size();i++){
//				obj.getLstStockCell().get(i).setStockId(obj.getStockId());
//				stockCellBusiness.save(obj.getLstStockCell().get(i));
//			}
//		}
//		return 1l;
//		
//	}
//}
