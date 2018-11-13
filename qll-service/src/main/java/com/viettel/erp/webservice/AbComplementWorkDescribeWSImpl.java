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
import com.viettel.erp.business.AbComplementWorkDescribeBusinessImpl;
import com.viettel.erp.dto.AbComplementWorkDescribeDTO;
import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;



@WebService(endpointInterface = "com.viettel.erp.webservice.AbComplementWorkDescribeWS")

public class AbComplementWorkDescribeWSImpl implements AbComplementWorkDescribeWS {

    @Autowired
    AbComplementWorkDescribeBusinessImpl abComplementWorkDescribeImpl;
    Logger logger = Logger.getLogger(AbComplementWorkDescribeWSImpl.class);

    @Override
    public long getTotal()  throws Exception {
    try{
        return abComplementWorkDescribeImpl.count();
        } catch (Exception ex) {
                   logger.error(ex);
                   throw ex;
               }
    }

@Override
    public List<AbComplementWorkDescribeDTO> getAll()  throws Exception {
    try{
        return abComplementWorkDescribeImpl.getAll(); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public AbComplementWorkDescribeBusinessImpl getAbComplementWorkDescribeBusinessImpl()  {
        return abComplementWorkDescribeImpl;
        
    }

    public void setAbComplementWorkDescribeBusinessImpl(AbComplementWorkDescribeBusinessImpl abComplementWorkDescribeImpl) {
        this.abComplementWorkDescribeImpl = abComplementWorkDescribeImpl;
    }

    @Override
    public AbComplementWorkDescribeDTO getOneById(Long costCenterId)  throws Exception {
    try{
        return (AbComplementWorkDescribeDTO) this.abComplementWorkDescribeImpl.getOneById(costCenterId);

        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long save(AbComplementWorkDescribeDTO costCenterBO)  throws Exception {
    try{
        return this.abComplementWorkDescribeImpl.save(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public void delete(AbComplementWorkDescribeDTO costCenterBO)  throws Exception {
    try{
        this.abComplementWorkDescribeImpl.delete(costCenterBO);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<AbComplementWorkDescribeDTO> searchByHql(String hql, List<ConditionBean> conditionBeans)
     throws Exception {
    try{
        return this.abComplementWorkDescribeImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<AbComplementWorkDescribeDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
     throws Exception {
    try{
    return this.abComplementWorkDescribeImpl.searchByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long countByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
        try{        
        return this.abComplementWorkDescribeImpl.countByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public int executeByHql(String hql, List<ConditionBean> conditionBeans)  throws Exception {
    try{
        return this.abComplementWorkDescribeImpl.executeByHql(hql, conditionBeans);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Date getSysDate() throws Exception  {
    try{
        return this.abComplementWorkDescribeImpl.getSysDate();
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public Long getNextValSequence(String sequense)  throws Exception {
    try{
        return this.abComplementWorkDescribeImpl.getNextValSequence(sequense);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

}
