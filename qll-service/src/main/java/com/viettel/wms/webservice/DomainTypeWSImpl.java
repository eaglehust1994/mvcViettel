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
import com.viettel.wms.business.DomainTypeBusinessImpl;
import com.viettel.wms.dto.DomainTypeDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.wms.webservice.DomainTypeWS")

public class DomainTypeWSImpl implements DomainTypeWS {

    @Autowired
    DomainTypeBusinessImpl domainTypeImpl;
    Logger logger = Logger.getLogger(DomainTypeWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return domainTypeImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<DomainTypeDTO> getAll()  throws Exception {
    try{
        return domainTypeImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public DomainTypeBusinessImpl getDomainTypeBusinessImpl()  {
        return domainTypeImpl;
        
    }

    public void setDomainTypeBusinessImpl(DomainTypeBusinessImpl domainTypeImpl) {
        this.domainTypeImpl = domainTypeImpl;
    }

    @Override
    public DomainTypeDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (DomainTypeDTO) this.domainTypeImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(DomainTypeDTO costCenterBO)  throws Exception {
    try{
        return this.domainTypeImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(DomainTypeDTO costCenterBO)  throws Exception {
    try{
        this.domainTypeImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<DomainTypeDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.domainTypeImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<DomainTypeDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.domainTypeImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.domainTypeImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.domainTypeImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.domainTypeImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.domainTypeImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
