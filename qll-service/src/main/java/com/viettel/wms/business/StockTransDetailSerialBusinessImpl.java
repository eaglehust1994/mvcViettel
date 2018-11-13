package com.viettel.wms.business;
 
import com.viettel.wms.bo.StockTransDetailSerialBO;
import com.viettel.wms.dao.StockTransDetailSerialDAO;
import com.viettel.wms.dto.StockTransDetailDTO;
import com.viettel.wms.dto.StockTransDetailSerialDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("stockTransDetailSerialBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockTransDetailSerialBusinessImpl extends BaseFWBusinessImpl<StockTransDetailSerialDAO,StockTransDetailSerialDTO, StockTransDetailSerialBO> implements StockTransDetailSerialBusiness {

    @Autowired
    private StockTransDetailSerialDAO stockTransDetailSerialDAO;
    

     
    public StockTransDetailSerialBusinessImpl() {
        tModel = new StockTransDetailSerialBO();
        tDAO = stockTransDetailSerialDAO;
    }

    @Override
    public StockTransDetailSerialDAO gettDAO() {
        return stockTransDetailSerialDAO;
    }

    @Override
    public long count() {
        return stockTransDetailSerialDAO.count("StockTransDetailSerialBO", null);
    }

	@Override
	public DataListDTO doSearchGoodsDetailForImportNote(StockTransDetailSerialDTO obj) {
		// TODO Auto-generated method stub
		List<StockTransDetailSerialDTO> ls = stockTransDetailSerialDAO.doSearchGoodsDetailForImportNote(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}
	
	

	@Override
	public DataListDTO doSearchGoodsDetailSerial(StockTransDetailSerialDTO obj) {
		// TODO Auto-generated method stub
		List<StockTransDetailSerialDTO> ls = stockTransDetailSerialDAO.doSearchGoodsDetailSerial(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}

	@Override
	public List<StockTransDetailSerialDTO> getGoodsInfoByCode(String code) {
		// TODO Auto-generated method stub
		return stockTransDetailSerialDAO.getGoodsInfoByCode(code);
	}

	@Override
	public DataListDTO doSearchGoodsDetailForExportNote() {
		// TODO Auto-generated method stub
		List<StockTransDetailSerialDTO> ls = stockTransDetailSerialDAO.doSearchGoodsDetailForExportNote();
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setStart(1);
		return data;
	}

	@Override
	public List<StockTransDetailSerialDTO> getGoodsDetail(StockTransDetailSerialDTO obj) {
		// TODO Auto-generated method stub
		return stockTransDetailSerialDAO.getGoodsDetail(obj);
	}

	/**
	 * Danh sach thong tin serial
	 */
	@Override
	public DataListDTO doSearchGoodsDetailForExportNote(StockTransDetailSerialDTO obj) {
		// TODO Auto-generated method stub
				List<StockTransDetailSerialDTO> ls = stockTransDetailSerialDAO.doSearchGoodsDetailForExportNote(obj);
				   DataListDTO data = new DataListDTO();
				   data.setData(ls);
				   data.setStart(1);
				return data;

	}
}
