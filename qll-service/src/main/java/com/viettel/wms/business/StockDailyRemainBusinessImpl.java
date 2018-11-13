package com.viettel.wms.business;
 
import com.viettel.wms.bo.StockDailyRemainBO;
import com.viettel.wms.dao.StockDailyRemainDAO;
import com.viettel.wms.dto.CommonDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.wms.dto.StockDailyRemainDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("stockDailyRemainBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockDailyRemainBusinessImpl extends BaseFWBusinessImpl<StockDailyRemainDAO,StockDailyRemainDTO, StockDailyRemainBO> implements StockDailyRemainBusiness {

    @Autowired
    private StockDailyRemainDAO stockDailyRemainDAO;
    

     
    public StockDailyRemainBusinessImpl() {
        tModel = new StockDailyRemainBO();
        tDAO = stockDailyRemainDAO;
    }

    @Override
    public StockDailyRemainDAO gettDAO() {
        return stockDailyRemainDAO;
    }

    @Override
    public long count() {
        return stockDailyRemainDAO.count("StockDailyRemainBO", null);
    }

    public DataListDTO doSearch(StockDailyRemainDTO obj) {
		List<StockDailyRemainDTO> ls = stockDailyRemainDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

    public StockDailyRemainDTO getAmount(Date remainDate,StockDailyRemainDTO obj){
    	return stockDailyRemainDAO.getAmount(remainDate,obj);
    }
    
    
    public List<StockDailyRemainDTO> getListForExport(StockDailyRemainDTO obj,Long amount,Date startDate,Date endDate){
    	List<StockDailyRemainDTO> ls =stockDailyRemainDAO.getListForExport(obj,startDate,endDate);
    	Long amount1=amount;
    	if (ls.size() > 0) {
			for (Iterator<StockDailyRemainDTO> interator = ls.iterator(); interator.hasNext();) {
				StockDailyRemainDTO wi = interator.next();
				amount1= amount1+wi.getImAmount().longValue()-wi.getExAmount().longValue();
				wi.setAmount(amount1.doubleValue());
			}
		}
    	
    	return ls;
    }
    
}
