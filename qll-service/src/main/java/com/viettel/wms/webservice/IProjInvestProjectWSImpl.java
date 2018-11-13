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
import com.viettel.wms.business.IProjInvestProjectBusinessImpl;
import com.viettel.wms.dto.IProjInvestProjectDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.wms.webservice.IProjInvestProjectWS")

public class IProjInvestProjectWSImpl implements IProjInvestProjectWS {

    @Autowired
    IProjInvestProjectBusinessImpl iProjInvestProjectImpl;
    Logger logger = Logger.getLogger(IProjInvestProjectWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return iProjInvestProjectImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<IProjInvestProjectDTO> getAll()  throws Exception {
    try{
        return iProjInvestProjectImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public IProjInvestProjectBusinessImpl getIProjInvestProjectBusinessImpl()  {
        return iProjInvestProjectImpl;
        
    }

    public void setIProjInvestProjectBusinessImpl(IProjInvestProjectBusinessImpl iProjInvestProjectImpl) {
        this.iProjInvestProjectImpl = iProjInvestProjectImpl;
    }

    @Override
    public IProjInvestProjectDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (IProjInvestProjectDTO) this.iProjInvestProjectImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(IProjInvestProjectDTO costCenterBO)  throws Exception {
    try{
        return this.iProjInvestProjectImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(IProjInvestProjectDTO costCenterBO)  throws Exception {
    try{
        this.iProjInvestProjectImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<IProjInvestProjectDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.iProjInvestProjectImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<IProjInvestProjectDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.iProjInvestProjectImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.iProjInvestProjectImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.iProjInvestProjectImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.iProjInvestProjectImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.iProjInvestProjectImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
