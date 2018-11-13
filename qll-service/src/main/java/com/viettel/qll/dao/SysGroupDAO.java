package com.viettel.qll.dao;

import java.util.List;
import com.viettel.qll.bo.SysGroupBO;

import com.viettel.qll.dto.SysGroupDTO;
import com.viettel.qll.dto.TblKpiScoreDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.utils.ValidateUtils;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("sysGroupDAO")
public class SysGroupDAO extends BaseFWDAOImpl<SysGroupBO, Long> {
	private final static String GROUP_LEVEL="3";
	private final static String GROUP_NAME_LEVEL="KCQ Cty Công trình";
    public SysGroupDAO() {
        this.model = new SysGroupBO();
    }

    public SysGroupDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<SysGroupDTO> getListDepartment(SysGroupDTO obj) {
    	StringBuilder stringBuilder = new StringBuilder("select  ");
		stringBuilder.append(" T1.SYS_GROUP_ID derpartmentId ");	
		stringBuilder.append(",T1.GROUP_NAME_LEVEL3 derpartmentName ");

    	stringBuilder.append("FROM SYS_GROUP T1 ");    	
    	stringBuilder.append("WHERE T1.STATUS =:status and T1.GROUP_NAME_LEVEL2=:groupNameLevel2 and T1.GROUP_LEVEL=:groupLevel  ");
		stringBuilder.append(" order by T1.SYS_GROUP_ID desc");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());

			query.addScalar("derpartmentId", new LongType());		
			query.addScalar("derpartmentName", new StringType());		
				
			query.setResultTransformer(Transformers.aliasToBean(SysGroupDTO.class));
			query.setParameter("groupLevel", obj.getGroupLevel());
			query.setParameter("groupNameLevel2", obj.getGroupNameLevel2());
			query.setParameter("status", obj.getStatus());
		
		return query.list();
	}  
    
    @SuppressWarnings("unchecked")
	public List<SysGroupDTO> getAutoDepartment(SysGroupDTO obj){
    	StringBuilder sql = new StringBuilder("select ");
    	sql.append(" T1.SYS_GROUP_ID derpartmentId ");
    	sql.append(",T1.GROUP_NAME_LEVEL3 derpartmentName ");
    	sql.append(" From SYS_GROUP T1 ");
    	sql.append(" where T1.GROUP_LEVEL=:groupLevel ");
    	sql.append(" and T1.GROUP_NAME_LEVEL2=:groupNameLevel2");
    	if(StringUtils.isNotEmpty(obj.getName())){
			sql.append(" AND (upper(T1.GROUP_NAME_LEVEL3) LIKE upper(:name) escape '&' )");
		}
    	sql.append(" AND ROWNUM <=10 ");
    	sql.append(" ORDER BY T1.NAME ");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("derpartmentName", new StringType());
		query.addScalar("derpartmentId", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(SysGroupDTO.class));
		query.setParameter("groupLevel", GROUP_LEVEL);
		query.setParameter("groupNameLevel2", GROUP_NAME_LEVEL);
		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}

		return query.list();
    }
    
	
}
