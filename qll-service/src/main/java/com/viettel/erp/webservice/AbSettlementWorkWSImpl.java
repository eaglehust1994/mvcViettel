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
import com.viettel.erp.business.AbSettlementWorkBusinessImpl;
import com.viettel.erp.dto.AbSettlementWorkDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.erp.webservice.AbSettlementWorkWS")

public class AbSettlementWorkWSImpl implements AbSettlementWorkWS {

    @Autowired
    AbSettlementWorkBusinessImpl abSettlementWorkImpl;
    Logger logger = Logger.getLogger(AbSettlementWorkWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return abSettlementWorkImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<AbSettlementWorkDTO> getAll()  throws Exception {
    try{
        return abSettlementWorkImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public AbSettlementWorkBusinessImpl getAbSettlementWorkBusinessImpl()  {
        return abSettlementWorkImpl;
        
    }

    public void setAbSettlementWorkBusinessImpl(AbSettlementWorkBusinessImpl abSettlementWorkImpl) {
        this.abSettlementWorkImpl = abSettlementWorkImpl;
    }

    @Override
    public AbSettlementWorkDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (AbSettlementWorkDTO) this.abSettlementWorkImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(AbSettlementWorkDTO costCenterBO)  throws Exception {
    try{
        return this.abSettlementWorkImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(AbSettlementWorkDTO costCenterBO)  throws Exception {
    try{
        this.abSettlementWorkImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<AbSettlementWorkDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.abSettlementWorkImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<AbSettlementWorkDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.abSettlementWorkImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.abSettlementWorkImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.abSettlementWorkImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.abSettlementWorkImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.abSettlementWorkImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
