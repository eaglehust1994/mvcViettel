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
import com.viettel.Common.CommonBussiness.UserRoleBusinessImpl;
import com.viettel.Common.CommonDTO.UserRoleDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.wms.webservice.UserRoleWS")

public class UserRoleWSImpl implements UserRoleWS {

    @Autowired
    UserRoleBusinessImpl userRoleImpl;
    Logger logger = Logger.getLogger(UserRoleWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return userRoleImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<UserRoleDTO> getAll()  throws Exception {
    try{
        return userRoleImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public UserRoleBusinessImpl getUserRoleBusinessImpl()  {
        return userRoleImpl;
        
    }

    public void setUserRoleBusinessImpl(UserRoleBusinessImpl userRoleImpl) {
        this.userRoleImpl = userRoleImpl;
    }

    @Override
    public UserRoleDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (UserRoleDTO) this.userRoleImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(UserRoleDTO costCenterBO)  throws Exception {
    try{
        return this.userRoleImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(UserRoleDTO costCenterBO)  throws Exception {
    try{
        this.userRoleImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<UserRoleDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.userRoleImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<UserRoleDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.userRoleImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.userRoleImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.userRoleImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.userRoleImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.userRoleImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
