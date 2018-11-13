package com.viettel.wms.business;
 
import com.viettel.wms.bo.StockDailyRemainDetailBO;
import com.viettel.wms.dao.StockDailyRemainDetailDAO;
import com.viettel.wms.dto.StockDailyRemainDetailDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("stockDailyRemainDetailBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockDailyRemainDetailBusinessImpl extends BaseFWBusinessImpl<StockDailyRemainDetailDAO,StockDailyRemainDetailDTO, StockDailyRemainDetailBO> implements StockDailyRemainDetailBusiness {

    @Autowired
    private StockDailyRemainDetailDAO stockDailyRemainDetailDAO;
    

     
    public StockDailyRemainDetailBusinessImpl() {
        tModel = new StockDailyRemainDetailBO();
        tDAO = stockDailyRemainDetailDAO;
    }

    @Override
    public StockDailyRemainDetailDAO gettDAO() {
        return stockDailyRemainDetailDAO;
    }

    @Override
    public long count() {
        return stockDailyRemainDetailDAO.count("StockDailyRemainDetailBO", null);
    }

    

    
}
