package com.viettel.ktts2.dao;

import com.viettel.ktts2.dto.CSysGroupDto;
import com.viettel.ktts2.dto.CTreeModel;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("commonSysGroupDao")
public class CommonSysGroupDao extends BaseFWDAOImpl{
	/**
	 * TÌm kiếm đơn vị cho tree view
	 * @param id của parent_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CTreeModel> getSysGroupAjax(Long parentId){
		Session s=getSession();
		SQLQuery query= s.createSQLQuery("select a.group_id as value, a.name as text, NVL((select max(1) from sys_group b where b.parent_id=a.group_id and b.status =:status and the_Level>0),0) as hasChildren   from sys_group a where a.parent_id=:parentId and a.status=:status and the_Level>0 order by the_order");
		query.setResultTransformer(Transformers.aliasToBean(CTreeModel.class));
		query.setParameter("parentId", parentId);
		query.setParameter("status", 1);
		query.addScalar("value",StandardBasicTypes.STRING);
		query.addScalar("text",StandardBasicTypes.STRING);
		query.addScalar("hasChildren",StandardBasicTypes.BOOLEAN);
		return query.list();
		
	}
}
