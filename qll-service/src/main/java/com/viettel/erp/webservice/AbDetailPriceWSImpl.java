/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.webservice;

/**
 *
 * @author hunglq9
 */
import com.viettel.erp.business.AbDetailPriceBusinessImpl;
import com.viettel.erp.dto.AbDetailPriceDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.erp.webservice.AbDetailPriceWS")

public class AbDetailPriceWSImpl implements AbDetailPriceWS {

    @Autowired
    AbDetailPriceBusinessImpl abDetailPriceImpl;
    Logger logger = Logger.getLogger(AbDetailPriceWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return abDetailPriceImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<AbDetailPriceDTO> getAll()  throws Exception {
    try{
        return abDetailPriceImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public AbDetailPriceBusinessImpl getAbDetailPriceBusinessImpl()  {
        return abDetailPriceImpl;
        
    }

    public void setAbDetailPriceBusinessImpl(AbDetailPriceBusinessImpl abDetailPriceImpl) {
        this.abDetailPriceImpl = abDetailPriceImpl;
    }

    @Override
    public AbDetailPriceDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (AbDetailPriceDTO) this.abDetailPriceImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(AbDetailPriceDTO costCenterBO)  throws Exception {
    try{
        return this.abDetailPriceImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(AbDetailPriceDTO costCenterBO)  throws Exception {
    try{
        this.abDetailPriceImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<AbDetailPriceDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.abDetailPriceImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<AbDetailPriceDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.abDetailPriceImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.abDetailPriceImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.abDetailPriceImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.abDetailPriceImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.abDetailPriceImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
