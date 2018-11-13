/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.viettel.service.base.pojo.ConditionBean;
import java.util.Date;
import java.util.List;
import com.viettel.wms.dto.OddCableDTO;
/**
 *
 * @author hunglq9
 */
@org.apache.cxf.feature.Features(features = "org.apache.cxf.feature.LoggingFeature")
@WebService(targetNamespace = "http://webservice.wms.viettel.com/")
public interface OddCableWS {

    @WebMethod(operationName = "getTotal")
    long getTotal()  throws Exception;
 

    @WebMethod(operationName = "getAll")
    public List<OddCableDTO> getAll()  throws java.lang.Exception;

    @WebMethod(operationName = "getOneById")
    OddCableDTO getOneById(Long costCenterId)  throws Exception;

    @WebMethod(operationName = "save")
    Long save(OddCableDTO costCenterBO) throws Exception;

    @WebMethod(operationName = "delete")
    void delete(OddCableDTO costCenterBO) throws Exception;

    @WebMethod(operationName = "searchByHql")
    List<OddCableDTO> searchByHql(String hql, List<ConditionBean> conditionBeans) throws Exception;

    @WebMethod(operationName = "searchByHql2")
    List<OddCableDTO> searchByHql(String hql, List<ConditionBean> conditionBeans, int startIdx, int endIdx)
    throws Exception;

    @WebMethod(operationName = "countByHql")
    Long countByHql(String hql, List<ConditionBean> conditionBeans) throws Exception;

        @WebMethod(operationName = "executeByHql")
    int executeByHql(String hql, List<ConditionBean> conditionBeans) throws Exception;

    @WebMethod(operationName = "getSysDate")
    Date getSysDate() throws Exception ;

    @WebMethod(operationName = "getNextValSequence")
    Long getNextValSequence(String sequense) throws Exception;
}
