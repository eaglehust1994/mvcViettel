package com.viettel.wms.business;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.dto.KpiStorageAmountDTO;
import com.viettel.wms.dto.OrderChangeGoodsDTO;
import com.viettel.wms.dto.OrderGoodsDTO;
import com.viettel.wms.dto.StockTransDTO;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.dto.DataListDTO;

public interface StockTransBusiness {

    long count();
    
    DataListDTO doSearchImportNote(StockTransDTO obj);
    DataListDTO doSearchStatement(StockTransDTO obj,HttpServletRequest request)throws Exception;
    DataListDTO doSearchExport(StockTransDTO obj,HttpServletRequest request) throws Exception;
    StockTransDTO getStockTransDetail(Long id);
    String saveAndRealImportNote(StockTransDTO obj, HttpServletRequest request) throws Exception;
    String realImportNote(StockTransDTO obj) throws Exception;
    Long updateImportNote(StockTransDTO obj,KttsUserSession objUser);
    boolean removeImportStockTrans(StockTransDTO obj,KttsUserSession objUser);
    
    List<StockTransDTO> getStockTransForAutoComplete(StockTransDTO obj);
    List<String> getGoodsInfoFromAlternativeStockCode(String code);
    boolean removeExportStockTrans(StockTransDTO obj);
    boolean saveExportNote(StockTransDTO obj);
    void realExportNote(StockTransDTO obj,HttpServletRequest request) throws Exception;
    public Long updateImportStockTran(StockTransDTO obj);
    
    boolean updateStatusStockTrans(StockTransDTO obj);


	void saveImportNote(StockTransDTO obj, HttpServletRequest request) throws Exception;


	Long saveInforImport(StockTransDTO obj, HttpServletRequest request)
			throws Exception;


	List<StockTransDTO> getAllImportNote(StockTransDTO obj);
	List<StockTransDTO> getAllExportManagement(StockTransDTO obj);
	
}
