package com.viettel.wms.business;
 
import com.viettel.wms.bo.OrderPatternGoodsBO;
import com.viettel.wms.dao.OrderPatternGoodsDAO;
import com.viettel.wms.dto.OrderPatternGoodsDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("orderPatternGoodsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderPatternGoodsBusinessImpl extends BaseFWBusinessImpl<OrderPatternGoodsDAO,OrderPatternGoodsDTO, OrderPatternGoodsBO> implements OrderPatternGoodsBusiness {

    @Autowired
    private OrderPatternGoodsDAO orderPatternGoodsDAO;
    
    @Override
	public DataListDTO doSearch(OrderPatternGoodsDTO obj) {
		// TODO Auto-generated method stub
		List<OrderPatternGoodsDTO> ls = orderPatternGoodsDAO.doSearch(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}
     
    public OrderPatternGoodsBusinessImpl() {
        tModel = new OrderPatternGoodsBO();
        tDAO = orderPatternGoodsDAO;
    }

    @Override
    public OrderPatternGoodsDAO gettDAO() {
        return orderPatternGoodsDAO;
    }

    @Override
    public long count() {
        return orderPatternGoodsDAO.count("OrderPatternGoodsBO", null);
    }

    public DataListDTO getPatternGoodsByOrderPatternId(OrderPatternGoodsDTO obj) {
		// TODO Auto-generated method stub
		List<OrderPatternGoodsDTO> ls = orderPatternGoodsDAO.getPatternGoodsByOrderPatternId(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}

    
}
