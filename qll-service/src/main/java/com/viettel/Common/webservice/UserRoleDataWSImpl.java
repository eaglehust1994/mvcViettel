///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.webservice;
//
///**
// *
// * @author hunglq9
// */
//import com.viettel.wms.business.UserRoleDataBusinessImpl;
//import com.viettel.wms.dto.UserRoleDataDTO;
//import com.viettel.service.base.pojo.ConditionBean;
//import java.util.Date;
//import java.util.List;
//import javax.jws.WebService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.log4j.Logger;
//
//
//
//@WebService(endpointInterface = "com.viettel.wms.webservice.UserRoleDataWS")
//
//public class UserRoleDataWSImpl implements UserRoleDataWS {
//
//    @Autowired
//    UserRoleDataBusinessImpl userRoleDataImpl;
//    Logger logger = Logger.getLogger(UserRoleDataWSImpl.class);
//
//    @Override
//    public long getTotal()  throws Exception {
//    try{
//        return userRoleDataImpl.count();
//        } catch (Exception ex) {
//                   logger.error(ex);
//                   throw ex;
//               }
//    }
//
//@Override
//    public List<UserRoleDataDTO> getAll()  throws Exception {
//    try{
//        return userRoleDataImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//    public UserRoleDataBusinessImpl getUserRoleDataBusinessImpl()  {
//        return userRoleDataImpl;
//        
//    }
//
//    public void setUserRoleDataBusinessImpl(UserRoleDataBusinessImpl userRoleDataImpl) {
//        this.userRoleDataImpl = userRoleDataImpl;
//    }
//
//    @Override
//    public UserRoleDataDTO getOneById(Long costCenterId)  throws Exception {
//    try{
//        return (UserRoleDataDTO) this.userRoleDataImpl.getOneById(costCenterId);
//
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public Long save(UserRoleDataDTO costCenterBO)  throws Exception {
//    try{
//        return this.userRoleDataImpl.save(costCenterBO);
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public void delete(UserRoleDataDTO costCenterBO)  throws Exception {
//    try{
//        this.userRoleDataImpl.delete(costCenterBO);
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public List<UserRoleDataDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
//     throws Exception {
//    try{
//        return this.userRoleDataImpl.searchByHql(hql, conditionBeans);
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public List<UserRoleDataDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
//     throws Exception {
//    try{
//    return this.userRoleDataImpl.searchByHql(hql, conditionBeans);
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
//        try{        
//        return this.userRoleDataImpl.countByHql(hql, conditionBeans);
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
//    try{
//        return this.userRoleDataImpl.executeByHql(hql, conditionBeans);
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public Date getSysDate() throws Exception  {
//    try{
//        return this.userRoleDataImpl.getSysDate();
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//    @Override
//    public Long getNextValSequence(String sequense)  throws Exception {
//    try{
//        return this.userRoleDataImpl.getNextValSequence(sequense);
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw ex;
//        }
//    }
//
//}
