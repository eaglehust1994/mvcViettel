package com.viettel.wms.business;
 
import com.viettel.wms.bo.OrderGoodsDetailSerialBO;
import com.viettel.wms.dao.OrderGoodsDetailSerialDAO;
import com.viettel.wms.dto.OrderGoodsDetailSerialDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("orderGoodsDetailSerialBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderGoodsDetailSerialBusinessImpl extends BaseFWBusinessImpl<OrderGoodsDetailSerialDAO,OrderGoodsDetailSerialDTO, OrderGoodsDetailSerialBO> implements OrderGoodsDetailSerialBusiness {

    @Autowired
    private OrderGoodsDetailSerialDAO orderGoodsDetailSerialDAO;
    

     
    public OrderGoodsDetailSerialBusinessImpl() {
        tModel = new OrderGoodsDetailSerialBO();
        tDAO = orderGoodsDetailSerialDAO;
    }

    @Override
    public OrderGoodsDetailSerialDAO gettDAO() {
        return orderGoodsDetailSerialDAO;
    }

    @Override
    public long count() {
        return orderGoodsDetailSerialDAO.count("OrderGoodsDetailSerialBO", null);
    }

    

    
}
