/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;
import com.viettel.erp.bo.AbComplementWorkDescribeBO;
import com.viettel.erp.dto.AbComplementWorkDescribeDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("abComplementWorkDescribeDAO")
public class AbComplementWorkDescribeDAO extends BaseFWDAOImpl<AbComplementWorkDescribeBO, Long> {

    public AbComplementWorkDescribeDAO() {
        this.model = new AbComplementWorkDescribeBO();
    }

    public AbComplementWorkDescribeDAO(Session session) {
        this.session = session;
    }
    
    @Transactional
    public Long saveTable( AbComplementWorkDescribeDTO  completionDrawing) throws Exception{
           Session session = getSession();
           Long completionDrawingId = (Long) session.save(completionDrawing.toModel());
           completionDrawing.getConstrCompleteRecordMap().setDataTableIdValue(completionDrawingId);     
           session.save(completionDrawing.getConstrCompleteRecordMap().toModel());
           return completionDrawingId;
     }
    
    @SuppressWarnings("unchecked")
    public String autoGenCode(String tableName, String value) {
     SQLQuery query = getSession().createSQLQuery(
       Joiner.on("").join("select get_next_code('", tableName, "', 'CODE','", value, "',6) code from dual"));
     query.addScalar("code", StandardBasicTypes.STRING);

     return (String) query.uniqueResult();
    }
    
    public AbComplementWorkDescribeDTO getAbComplementWorkById(Long id){
    	Session session = getSession();
    	List listObj  =  session.createQuery("select u from abComplementWorkDescribe u where u.constructId = :id")
    			      .setParameter("id", id)
    			      .list();
    	if(listObj.size()>0){
    		AbComplementWorkDescribeBO reObj = (AbComplementWorkDescribeBO) listObj.get(0);
    		return reObj.toDTO();
    	}else{
    		return null;
    	}
    	
    }
    public Long getconstrCompReMapId(Long id){
    	  String sql = "select CONSTR_COMP_RE_MAP_ID constrCompReMapId from CONSTR_COMPLETE_RECORDS_MAP  where DATA_TABLE_ID_VALUE = :id AND DATA_TABLE_NAME = 'AB_COMPLEMENT_WORK_DESCRIBE'";
    	  SQLQuery query = getSession().createSQLQuery(sql);
    	  query.addScalar("constrCompReMapId", LongType.INSTANCE);
    	  query.setResultTransformer(Transformers.aliasToBean(ConstrCompleteRecordsMapDTO.class));
    	         System.out.println(id);
    	  query.setParameter("id", id);
    	  List<ConstrCompleteRecordsMapDTO> list = query.list();
    	  System.out.println(list.size());
    	  return  list.size()>0?list.get(0).getConstrCompReMapId():null ;
    	 }
    
}
