package com.viettel.wms.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.bo.ConstructionBO;
import com.viettel.wms.dto.ConstructionDTO;
import com.viettel.wms.dto.OrderDTO;
import com.viettel.wms.utils.ValidateUtils;

@Repository("constructionDAO")
public class ConstructionDAO extends BaseFWDAOImpl<ConstructionBO, Long>{
    public ConstructionDAO() {
        this.model = new ConstructionBO();
    }

    public ConstructionDAO(Session session) {
        this.session = session;
    }
    
    @SuppressWarnings("unchecked")
	public List<String> doSearchConstructionForAutoComplete(ConstructionDTO o) {
    	StringBuilder sql = new StringBuilder( "SELECT "
				+ " con.CODE code "
				+ " ,con.NAME name "
				+ " FROM CAT_OWNER.CONSTRUCTION con " 
				+ " WHERE 1=1");

    	
    	if(o != null){
    		if(StringUtils.isNotEmpty(o.getCode())){
			sql.append(" AND upper(con.CODE) LIKE upper(:code)"
					 + " OR upper(con.NAME) LIKE upper(:code) ");
    		}
		}
    	
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		
		
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
			
		query.setResultTransformer(Transformers.aliasToBean(ConstructionDTO.class));
	
		if(o != null){
		if(StringUtils.isNotEmpty(o.getCode()))
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(o.getCode()) + "%");
		}
		
		return query.list(); 
    }
    
    @SuppressWarnings("unchecked")
   	public List<ConstructionDTO> doSearchConstructionForAutoCompleteII(String code) {
       	StringBuilder sql = new StringBuilder( "SELECT con.NAME name, "
   				+ " con.CODE code "
   				+ " FROM CAT_OWNER.CONSTRUCTION con " 
   				+ " WHERE 1=1");

       	if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
   			sql.append(" AND upper(con.CODE) LIKE upper(:code)");
   		}
       	
   		SQLQuery query = getSession().createSQLQuery(sql.toString());
   		
   		
   		query.addScalar("code", new StringType());
   		query.addScalar("name", new StringType());
   			
   		query.setResultTransformer(Transformers.aliasToBean(ConstructionDTO.class));
   	
   		if (StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")) {
   			query.setParameter("code", "%" + code + "%");
   		}
   		
   		return query.list(); 
       }

	public ConstructionDTO getConstructionByCode(String code) {
		StringBuilder sql = new StringBuilder( "SELECT con.NAME name, "
   				+ " con.CODE code "
   				+ " FROM CAT_OWNER.CONSTRUCTION con " 
   				+ " WHERE 1=1");

       	if(StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")){
    		sql.append(" AND ((upper(con.CODE) LIKE upper(:code)) "
						+ " OR (upper(con.NAME) LIKE upper(:code)))");

   		}
       	
   		SQLQuery query = getSession().createSQLQuery(sql.toString());
   		query.addScalar("code", new StringType());
   		query.addScalar("name", new StringType());
   		
   		query.setResultTransformer(Transformers.aliasToBean(ConstructionDTO.class));
   		
   		if (StringUtils.isNotEmpty(code.trim()) && !code.trim().equals("[]") && !code.trim().equals("")) {
			query.setParameter("code", "%" + code + "%");
		}
   		
   		return (ConstructionDTO) query.uniqueResult();
   		
	}
}
