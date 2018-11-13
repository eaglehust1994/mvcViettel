package com.viettel.wms.business;
 
import com.viettel.wms.bo.StockGoodsSerialBO;
import com.viettel.wms.dao.StockGoodsSerialDAO;
import com.viettel.wms.dto.StockGoodsSerialDTO;
import com.viettel.wms.dto.StockTransDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("stockGoodsSerialBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockGoodsSerialBusinessImpl extends BaseFWBusinessImpl<StockGoodsSerialDAO,StockGoodsSerialDTO, StockGoodsSerialBO> implements StockGoodsSerialBusiness {

    @Autowired
    private StockGoodsSerialDAO stockGoodsSerialDAO;
    

     
    public StockGoodsSerialBusinessImpl() {
        tModel = new StockGoodsSerialBO();
        tDAO = stockGoodsSerialDAO;
    }

    @Override
    public StockGoodsSerialDAO gettDAO() {
        return stockGoodsSerialDAO;
    }

    @Override
    public long count() {
        return stockGoodsSerialDAO.count("StockGoodsSerialBO", null);
    }

//    Tìm kiếm serial trong bảng StockGoodsSerial - TUANNB
	@Override
	public DataListDTO doSearchFindSerial(StockGoodsSerialDTO obj) {
		// TODO Auto-generated method stub
		List<StockGoodsSerialDTO> ls = stockGoodsSerialDAO.doSearchFindSerial(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}

//	Tìm kiếm lịch sử 
	@Override
	public DataListDTO doSearchHistory(StockTransDTO obj) {
		// TODO Auto-generated method stub
		List<StockTransDTO> ls = stockGoodsSerialDAO.doSearchHistory(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}

	@Override
	public void updateBySerial(StockGoodsSerialDTO stockGoodsSerialDTO) {
		// TODO Auto-generated method stub
		stockGoodsSerialDAO.updateBySerial(stockGoodsSerialDTO);
	}
}
