package com.viettel.wms.business;
 
import com.viettel.wms.bo.StockDeliveryConfigBO;
import com.viettel.wms.dao.StockDeliveryConfigDAO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.wms.dto.StockDeliveryConfigDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("stockDeliveryConfigBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockDeliveryConfigBusinessImpl extends BaseFWBusinessImpl<StockDeliveryConfigDAO,StockDeliveryConfigDTO, StockDeliveryConfigBO> implements StockDeliveryConfigBusiness {

    @Autowired
    private StockDeliveryConfigDAO stockDeliveryConfigDAO;
    
    

     
    public StockDeliveryConfigBusinessImpl() {
        tModel = new StockDeliveryConfigBO();
        tDAO = stockDeliveryConfigDAO;
    }

    @Override
    public StockDeliveryConfigDAO gettDAO() {
        return stockDeliveryConfigDAO;
    }

    @Override
    public long count() {
        return stockDeliveryConfigDAO.count("StockDeliveryConfigBO", null);
    }

    public DataListDTO doSearch() {
		List<StockDeliveryConfigDTO> ls = stockDeliveryConfigDAO.doSearch();
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setStart(1);
		return data;
	}
    
    public StockDeliveryConfigDTO getStockDeliveryConfig(Long idStock)
    {
    	StockDeliveryConfigDTO stockdeliveryconfig = new StockDeliveryConfigDTO();
    	stockdeliveryconfig = stockDeliveryConfigDAO.getStockDeliveryConfig(idStock);
    	return stockdeliveryconfig;
    }

    
}
