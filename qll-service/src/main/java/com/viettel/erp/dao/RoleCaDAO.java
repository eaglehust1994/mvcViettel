/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.erp.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.viettel.erp.bo.RoleCaBO;
import com.viettel.erp.dto.RoleCaDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;

/**
 * @author TruongBX3
 * @version 1.0
 * @since 08-May-15 4:07 PM
 */
@Repository("roleCaDAO")
public class RoleCaDAO extends BaseFWDAOImpl<RoleCaBO, Long> {

    public RoleCaDAO() {
        this.model = new RoleCaBO();
    }

    public RoleCaDAO(Session session) {
        this.session = session;
    }
    
    public List<RoleCaDTO> getListChucVu(RoleCaDTO dto) {
            StringBuilder sql = new StringBuilder();
            sql.append("select ROLE_CA.ROLENAME rolename, ROLE_CA.ROLEID roleid "	
            		+ "from ROLE_CA "
            		+ " where ROLE_CA.GROUP_SIDE = :groupSide "
            		);
            
            SQLQuery query = getSession().createSQLQuery(sql.toString());
    		
    		query.addScalar("rolename", StringType.INSTANCE);
    		query.addScalar("roleid", LongType.INSTANCE);
    		if(dto.getGroupSide() != null) {
    			query.setParameter("groupSide", dto.getGroupSide());
    		}else {
    			query.setParameter("groupSide", 0);
    		}
    		
    		
    		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            query.setResultTransformer(Transformers.aliasToBean(RoleCaDTO.class));
            
            return query.list();
	}

    @SuppressWarnings("unchecked")
	public List<RoleCaBO> getRoleCaByType(Long type, Long groupSide) {
    	StringBuilder builder = new StringBuilder("from roleCa a where 1=1");
    	Map<String, Object> params = Maps.newHashMap();
		if (type != null) {
			builder.append(" and a.type = :type");
			params.put("type", type);
		}
		if (groupSide != null) {
			builder.append(" and a.groupSide = :groupSide");
			params.put("groupSide", groupSide);
		}

		Query query = getSession().createQuery(builder.toString());
		for (Map.Entry<String, Object> elm : params.entrySet()) {
			query.setParameter(elm.getKey(), elm.getValue());
		}

		return query.list();
    }

	
}
