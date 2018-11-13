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
import com.viettel.erp.business.AbComplementWorkBusinessImpl;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.erp.webservice.AbComplementWorkWS")

public class AbComplementWorkWSImpl implements AbComplementWorkWS {

    @Autowired
    AbComplementWorkBusinessImpl abComplementWorkImpl;
    Logger logger = Logger.getLogger(AbComplementWorkWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return abComplementWorkImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<AbComplementWorkDTO> getAll()  throws Exception {
    try{
        return abComplementWorkImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public AbComplementWorkBusinessImpl getAbComplementWorkBusinessImpl()  {
        return abComplementWorkImpl;
        
    }

    public void setAbComplementWorkBusinessImpl(AbComplementWorkBusinessImpl abComplementWorkImpl) {
        this.abComplementWorkImpl = abComplementWorkImpl;
    }

    @Override
    public AbComplementWorkDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (AbComplementWorkDTO) this.abComplementWorkImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(AbComplementWorkDTO costCenterBO)  throws Exception {
    try{
        return this.abComplementWorkImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(AbComplementWorkDTO costCenterBO)  throws Exception {
    try{
        this.abComplementWorkImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<AbComplementWorkDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.abComplementWorkImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<AbComplementWorkDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.abComplementWorkImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.abComplementWorkImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.abComplementWorkImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.abComplementWorkImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.abComplementWorkImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
