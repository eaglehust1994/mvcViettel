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
import com.viettel.erp.business.MonitorMissionAssignBusinessImpl;
import com.viettel.erp.dto.MonitorMissionAssignDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.erp.webservice.MonitorMissionAssignWS")

public class MonitorMissionAssignWSImpl implements MonitorMissionAssignWS {

    @Autowired
    MonitorMissionAssignBusinessImpl monitorMissionAssignImpl;
    Logger logger = Logger.getLogger(MonitorMissionAssignWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return monitorMissionAssignImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<MonitorMissionAssignDTO> getAll()  throws Exception {
    try{
        return monitorMissionAssignImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public MonitorMissionAssignBusinessImpl getMonitorMissionAssignBusinessImpl()  {
        return monitorMissionAssignImpl;
        
    }

    public void setMonitorMissionAssignBusinessImpl(MonitorMissionAssignBusinessImpl monitorMissionAssignImpl) {
        this.monitorMissionAssignImpl = monitorMissionAssignImpl;
    }

    @Override
    public MonitorMissionAssignDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (MonitorMissionAssignDTO) this.monitorMissionAssignImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(MonitorMissionAssignDTO costCenterBO)  throws Exception {
    try{
        return this.monitorMissionAssignImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(MonitorMissionAssignDTO costCenterBO)  throws Exception {
    try{
        this.monitorMissionAssignImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<MonitorMissionAssignDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.monitorMissionAssignImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<MonitorMissionAssignDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.monitorMissionAssignImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.monitorMissionAssignImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.monitorMissionAssignImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.monitorMissionAssignImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.monitorMissionAssignImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
