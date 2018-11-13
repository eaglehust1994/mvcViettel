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
import com.viettel.wms.business.DomainDataBusinessImpl;
import com.viettel.wms.dto.DomainDataDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.wms.webservice.DomainDataWS")

public class DomainDataWSImpl implements DomainDataWS {

    @Autowired
    DomainDataBusinessImpl domainDataImpl;
    Logger logger = Logger.getLogger(DomainDataWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return domainDataImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<DomainDataDTO> getAll()  throws Exception {
    try{
        return domainDataImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public DomainDataBusinessImpl getDomainDataBusinessImpl()  {
        return domainDataImpl;
        
    }

    public void setDomainDataBusinessImpl(DomainDataBusinessImpl domainDataImpl) {
        this.domainDataImpl = domainDataImpl;
    }

    @Override
    public DomainDataDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (DomainDataDTO) this.domainDataImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(DomainDataDTO costCenterBO)  throws Exception {
    try{
        return this.domainDataImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(DomainDataDTO costCenterBO)  throws Exception {
    try{
        this.domainDataImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<DomainDataDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.domainDataImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<DomainDataDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.domainDataImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.domainDataImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.domainDataImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.domainDataImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.domainDataImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
