/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.Common.webservice;

/**
 *
 * @author hunglq9
 */
import com.viettel.Common.CommonBussiness.SysRoleBusinessImpl;
import com.viettel.Common.CommonDTO.SysRoleDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.wms.webservice.SysRoleWS")

public class SysRoleWSImpl implements SysRoleWS {

    @Autowired
    SysRoleBusinessImpl sysRoleImpl;
    Logger logger = Logger.getLogger(SysRoleWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return sysRoleImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<SysRoleDTO> getAll()  throws Exception {
    try{
        return sysRoleImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public SysRoleBusinessImpl getSysRoleBusinessImpl()  {
        return sysRoleImpl;
        
    }

    public void setSysRoleBusinessImpl(SysRoleBusinessImpl sysRoleImpl) {
        this.sysRoleImpl = sysRoleImpl;
    }

    @Override
    public SysRoleDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (SysRoleDTO) this.sysRoleImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(SysRoleDTO costCenterBO)  throws Exception {
    try{
        return this.sysRoleImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(SysRoleDTO costCenterBO)  throws Exception {
    try{
        this.sysRoleImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<SysRoleDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.sysRoleImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<SysRoleDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.sysRoleImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.sysRoleImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.sysRoleImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.sysRoleImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.sysRoleImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
