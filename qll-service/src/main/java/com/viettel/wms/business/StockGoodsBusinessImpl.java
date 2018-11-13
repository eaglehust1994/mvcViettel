package com.viettel.wms.business;

import com.viettel.wms.bo.StockGoodsBO;
import com.viettel.wms.dao.StockGoodsDAO;
import com.viettel.wms.dto.ShipmentTaxDTO;
import com.viettel.wms.dto.StockGoodsDTO;
import com.viettel.wms.dto.StockTransDTO;
import com.viettel.wms.dto.StockTransDetailDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("stockGoodsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StockGoodsBusinessImpl extends
		BaseFWBusinessImpl<StockGoodsDAO, StockGoodsDTO, StockGoodsBO>
		implements StockGoodsBusiness {

	@Autowired
	private StockGoodsDAO stockGoodsDAO;

	public StockGoodsBusinessImpl() {
		tModel = new StockGoodsBO();
		tDAO = stockGoodsDAO;
	}

	@Override
	public StockGoodsDAO gettDAO() {
		return stockGoodsDAO;
	}

	@Override
	public long count() {
		return stockGoodsDAO.count("StockGoodsBO", null);
	}

	public List<StockGoodsDTO> getGoodsById(StockTransDetailDTO std) {
		return stockGoodsDAO.findGoodsFromStock(std);
	}

	@Transactional
	public boolean updateNonSerialGoods(StockTransDetailDTO std) {
		List<StockGoodsDTO> allGoods = getGoodsById(std);
		Double exportNumber = std.getAmountReal();
		Double remainExportNumber = exportNumber;
		StockGoodsDTO obj = new StockGoodsDTO();
			for (StockGoodsDTO goods : allGoods) {
				if (goods.getAmount() != null) {

					if (remainExportNumber >= goods.getAmount().doubleValue()) {
						goods.setStatus("3");
						goods.setChangeDate(new Date());
						goods.setOrderId(std.getOrderId());
						stockGoodsDAO.updateNonSerial(goods);
						remainExportNumber -= goods.getAmount().doubleValue();
					} else {
						Double amount2= goods.getAmount().doubleValue();
						obj = (StockGoodsDTO) goods.clone();
						obj.setStatus("3");
						obj.setAmount(remainExportNumber);
						obj.setChangeDate(new Date());
						goods.setOrderId(std.getOrderId());
						goods.setChangeDate(new Date());
						stockGoodsDAO.saveObject(obj.toModel());
						goods.setAmount(goods.getAmount().doubleValue() - remainExportNumber);
						stockGoodsDAO.updateNonSerial(goods);
						remainExportNumber =remainExportNumber- amount2;
						System.out.print(remainExportNumber);
						break;
					}

				}
			}
			
			if(remainExportNumber>0d){
				return false;
			} else {
			return true;
			}
	}
	
	public List<StockGoodsDTO> detailStockGoods(StockGoodsDTO obj) {
		return stockGoodsDAO.detailStockGoods(obj);
	}
	public List<StockGoodsDTO> detailStockGoodsSerial(StockGoodsDTO obj) {
		return stockGoodsDAO.detailStockGoodsSerial(obj);
	}

}
