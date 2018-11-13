package com.viettel.wms.business;
 
import com.viettel.wms.bo.GoodsBO;
import com.viettel.wms.dao.GoodsDAO;
import com.viettel.wms.dto.GoodsDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("goodsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GoodsBusinessImpl extends BaseFWBusinessImpl<GoodsDAO,GoodsDTO, GoodsBO> implements GoodsBusiness {

    @Autowired
    private GoodsDAO goodsDAO;
    

     
    public GoodsBusinessImpl() {
        tModel = new GoodsBO();
        tDAO = goodsDAO;
    }

    @Override
    public GoodsDAO gettDAO() {
        return goodsDAO;
    }

    @Override
    public long count() {
        return goodsDAO.count("GoodsBO", null);
    }

    public DataListDTO doSearchActiveGoods(GoodsDTO obj) {
		List<GoodsDTO> ls = goodsDAO.doSearchActiveGoods(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
    
    public List<GoodsDTO> getForAutoCompleteGoods(GoodsDTO obj) {
		return goodsDAO.getForAutoCompleteGoods(obj);
	}

	@Override
	public List<GoodsDTO> getGoodsForOrder(GoodsDTO obj) {
		return goodsDAO.getGoodsForOrder(obj);
	}

	@Override
	public GoodsDTO getGoodByCode(String goodcode) {
		return goodsDAO.getGoodByCode(goodcode);
	}


	@Override
	public GoodsDTO getGoodById(GoodsDTO obj) {
		
		Long id = obj.getId();
		return goodsDAO.getGoodById(id);
	}

}
