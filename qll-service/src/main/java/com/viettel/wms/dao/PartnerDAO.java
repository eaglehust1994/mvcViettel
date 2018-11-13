package com.viettel.wms.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.wms.bo.PartnerBO;
import com.viettel.wms.dto.PartnerDTO;
import com.viettel.wms.utils.ValidateUtils;

@Repository("partnerDAO")
public class PartnerDAO extends BaseFWDAOImpl<PartnerBO, Long>{
	 public PartnerDAO() {
	        this.model = new PartnerBO();
	    }

	    public PartnerDAO(Session session) {
	        this.session = session;
	    }
	    
	    @SuppressWarnings("unchecked")
		public List<String> doSearchPartnerForAutoComplete(PartnerDTO po) {
	    	StringBuilder sql = new StringBuilder( "SELECT "
	    			+ "pat.PARTNER_ID partnerId, "
	    			+ " pat.CODE code, "
	    			+ " pat.NAME name "
					+ " FROM CAT_OWNER.PARTNER pat " 
					+ " WHERE 1=1");
	
	    	if(po != null){
	    		if(StringUtils.isNotEmpty(po.getCode())){
					sql.append(" AND upper(pat.CODE) LIKE upper(:code)  "
							 + " OR  upper(pat.NAME) LIKE upper(:code)  escape '&' ");
				}
			}
	    	
	    	
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
		
			query.addScalar("partnerId", new LongType());
			query.addScalar("code", new StringType());
			query.addScalar("name", new StringType());
			
			query.setResultTransformer(Transformers.aliasToBean(PartnerDTO.class));
			
			if(po != null){
	    		if(StringUtils.isNotEmpty(po.getCode())){
	    			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(po.getCode()) + "%");
				}
			}
			return query.list(); 
	    }

		public List<PartnerDTO> getForAutoCompleteII(String partnerCode) {
			StringBuilder sql = new StringBuilder( "SELECT pat.PARTNER_ID partnerId, "
	    			+ " pat.CODE code, "
	    			+ " pat.NAME name "
					+ " FROM CAT_OWNER.PARTNER pat " 
					+ " WHERE 1=1");
	
	    	if(StringUtils.isNotEmpty(partnerCode.trim()) && !partnerCode.trim().equals("[]") && !partnerCode.trim().equals("")){
				sql.append(" AND upper(pat.CODE) LIKE upper(:code)");
			}
	    	
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
			query.addScalar("partnerId", new LongType());
			query.addScalar("code", new StringType());
			query.addScalar("name", new StringType());
			
			query.setResultTransformer(Transformers.aliasToBean(PartnerDTO.class));
			
			if (StringUtils.isNotEmpty(partnerCode.trim()) && !partnerCode.trim().equals("[]") && !partnerCode.trim().equals("")) {
				query.setParameter("code", "%" + partnerCode + "%");
			}

			return query.list(); 
		}

		public PartnerDTO getPartnerByCode(String partnerCode) {
			StringBuilder sql = new StringBuilder( "SELECT pat.PARTNER_ID partnerId, "
	    			+ " pat.CODE code, "
	    			+ " pat.NAME name "
					+ " FROM CAT_OWNER.PARTNER pat " 
					+ " WHERE 1=1");
	
	    	if(StringUtils.isNotEmpty(partnerCode.trim()) && !partnerCode.trim().equals("[]") && !partnerCode.trim().equals("")){
				sql.append(" AND upper(pat.CODE) LIKE upper(:code)");
			}
	    	
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
			query.addScalar("partnerId", new LongType());
			query.addScalar("code", new StringType());
			query.addScalar("name", new StringType());
			
			query.setResultTransformer(Transformers.aliasToBean(PartnerDTO.class));
			
			if (StringUtils.isNotEmpty(partnerCode.trim()) && !partnerCode.trim().equals("[]") && !partnerCode.trim().equals("")) {
				query.setParameter("code", "%" + partnerCode + "%");
			}

			return (PartnerDTO) query.uniqueResult();
		}
		
		public PartnerDTO getPartnerById(Long partnerId) {
			StringBuilder sql = new StringBuilder( "SELECT pat.PARTNER_ID partnerId, "
	    			+ " pat.CODE code, "
	    			+ " pat.NAME name "
					+ " FROM CAT_OWNER.PARTNER pat " 
					+ " WHERE 1=1"
					+ " AND pat.PARTNER_ID = :partnerId");
	
	    	
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			
			query.addScalar("partnerId", new LongType());
			query.addScalar("code", new StringType());
			query.addScalar("name", new StringType());
			
			query.setResultTransformer(Transformers.aliasToBean(PartnerDTO.class));
			
			query.setParameter("partnerId", partnerId);

			return (PartnerDTO) query.uniqueResult();
		}
}
