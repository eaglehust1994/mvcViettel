/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.webservice;

/**
 *
 * @author hunglq9
 */
import com.viettel.wms.business.StockDeliveryConfigBusinessImpl;
import com.viettel.wms.dto.StockDeliveryConfigDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.erp.webservice.StockDeliveryConfigWS")

public class StockDeliveryConfigWSImpl implements StockDeliveryConfigWS {

    @Autowired
    StockDeliveryConfigBusinessImpl stockDeliveryConfigImpl;
    Logger logger = Logger.getLogger(StockDeliveryConfigWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return stockDeliveryConfigImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<StockDeliveryConfigDTO> getAll()  throws Exception {
    try{
        return stockDeliveryConfigImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public StockDeliveryConfigBusinessImpl getStockDeliveryConfigBusinessImpl()  {
        return stockDeliveryConfigImpl;
        
    }

    public void setStockDeliveryConfigBusinessImpl(StockDeliveryConfigBusinessImpl stockDeliveryConfigImpl) {
        this.stockDeliveryConfigImpl = stockDeliveryConfigImpl;
    }

    @Override
    public StockDeliveryConfigDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (StockDeliveryConfigDTO) this.stockDeliveryConfigImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(StockDeliveryConfigDTO costCenterBO)  throws Exception {
    try{
        return this.stockDeliveryConfigImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(StockDeliveryConfigDTO costCenterBO)  throws Exception {
    try{
        this.stockDeliveryConfigImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<StockDeliveryConfigDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.stockDeliveryConfigImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<StockDeliveryConfigDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.stockDeliveryConfigImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.stockDeliveryConfigImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.stockDeliveryConfigImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.stockDeliveryConfigImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.stockDeliveryConfigImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
