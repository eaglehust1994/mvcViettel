package com.viettel.wms.business;
 
import com.viettel.wms.bo.ConfigSignVofficeBO;
import com.viettel.wms.dao.ConfigSignVofficeDAO;
import com.viettel.wms.dto.ConfigSignVofficeDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("configSignVofficeBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConfigSignVofficeBusinessImpl extends BaseFWBusinessImpl<ConfigSignVofficeDAO,ConfigSignVofficeDTO, ConfigSignVofficeBO> implements ConfigSignVofficeBusiness {

    @Autowired
    private ConfigSignVofficeDAO configSignVofficeDAO;
    
    @Autowired
    private CommonBusiness commonBusiness;
    

     
    public ConfigSignVofficeBusinessImpl() {
        tModel = new ConfigSignVofficeBO();
        tDAO = configSignVofficeDAO;
    }

    @Override
    public ConfigSignVofficeDAO gettDAO() {
        return configSignVofficeDAO;
    }

    @Override
    public long count() {
        return configSignVofficeDAO.count("ConfigSignVofficeBO", null);
    }

//    Tìm kiếm kho trong bảng STOCK -- TUANNB
    public DataListDTO doSearchStock(StockDTO obj) {
		List<StockDTO> ls = configSignVofficeDAO.doSearchStock(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

//    Lấy dữ liệu người ký theo từng kho -- TUANNB
	public DataListDTO getDataByID(ConfigSignVofficeDTO obj) {
		// TODO Auto-generated method stub
		List<ConfigSignVofficeDTO> ls = configSignVofficeDAO.getDataByID(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

}
