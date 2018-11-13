package com.viettel.wms.business;
 
import com.viettel.wms.bo.ShipmentTaxBO;
import com.viettel.wms.dao.ShipmentTaxDAO;
import com.viettel.wms.dto.ShipmentTaxDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("shipmentTaxBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShipmentTaxBusinessImpl extends BaseFWBusinessImpl<ShipmentTaxDAO,ShipmentTaxDTO, ShipmentTaxBO> implements ShipmentTaxBusiness {

    @Autowired
    private ShipmentTaxDAO shipmentTaxDAO;
    

     
    public ShipmentTaxBusinessImpl() {
        tModel = new ShipmentTaxBO();
        tDAO = shipmentTaxDAO;
    }

    @Override
    public ShipmentTaxDAO gettDAO() {
        return shipmentTaxDAO;
    }

    @Override
    public long count() {
        return shipmentTaxDAO.count("ShipmentTaxBO", null);
    }

	public List<ShipmentTaxDTO> doSearchTAX(ShipmentTaxDTO obj) {
		return shipmentTaxDAO.doSearchTAX(obj);
	}

    
}
