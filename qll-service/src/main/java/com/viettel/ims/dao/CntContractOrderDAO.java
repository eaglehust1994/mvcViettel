package com.viettel.ims.dao;

import java.util.List;

import com.viettel.ims.bo.CntContractOrderBO;
import com.viettel.ims.dto.CntContractOrderDTO;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("cntContractOrderDAO")
public class CntContractOrderDAO extends BaseFWDAOImpl<CntContractOrderBO, Long> {

    public CntContractOrderDAO() {
        this.model = new CntContractOrderBO();
    }

    public CntContractOrderDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<CntContractOrderDTO> doSearch(CntContractOrderDTO criteria) {
    	StringBuilder stringBuilder = new StringBuilder("select ");
		stringBuilder.append("T1.CNT_CONTRACT_ORDER_ID cntContractOrderId ");
		stringBuilder.append(",T1.CNT_CONTRACT_ID cntContractId ");
		stringBuilder.append(",T1.PURCHASE_ORDER_ID purchaseOrderId ");
    	
    	stringBuilder.append("FROM CNT_CONTRACT_ORDER T1 ");    	
    	stringBuilder.append("WHERE 1=1 ");
    	
	
		
		if (null != criteria.getCntContractOrderId()) {
			stringBuilder.append("AND T1.CNT_CONTRACT_ORDER_ID = :cntContractOrderId ");
		}
		if (null != criteria.getCntContractId()) {
			stringBuilder.append("AND T1.CNT_CONTRACT_ID = :cntContractId ");
		}
		if (null != criteria.getPurchaseOrderId()) {
			stringBuilder.append("AND T1.PURCHASE_ORDER_ID = :purchaseOrderId ");
		}
		

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
//		query.addScalar("totalRecord", new IntegerType());
		query.addScalar("cntContractOrderId", new LongType());
		query.addScalar("cntContractId", new LongType());
//		query.addScalar("cntContractName", new StringType());
		query.addScalar("purchaseOrderId", new LongType());
//		query.addScalar("purchaseOrderName", new StringType());
    	
		if (null != criteria.getCntContractOrderId()) {
			query.setParameter("cntContractOrderId", criteria.getCntContractOrderId());
		}
		if (null != criteria.getCntContractId()) {
			query.setParameter("cntContractId", criteria.getCntContractId());
		}
		if (null != criteria.getPurchaseOrderId()) {
			query.setParameter("purchaseOrderId", criteria.getPurchaseOrderId());
		}
    	
		query.setResultTransformer(Transformers.aliasToBean(CntContractOrderDTO.class));    	

		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1) * criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		List ls = query.list();
		return ls;
	}  
	
	public CntContractOrderDTO findByValue(String value) {
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CNT_CONTRACT_ORDER_ID cntContractOrderId ");
		stringBuilder.append(",T1.CNT_CONTRACT_ID cntContractId ");
		stringBuilder.append(",T1.PURCHASE_ORDER_ID purchaseOrderId ");
    	
    	stringBuilder.append("FROM CNT_CONTRACT_ORDER T1 ");    	
    	stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND upper(T1.VALUE) = upper(:value)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("cntContractOrderId", new LongType());
		query.addScalar("cntContractId", new LongType());
		query.addScalar("purchaseOrderId", new LongType());
    	
		query.setParameter("value", value);    	
		query.setResultTransformer(Transformers.aliasToBean(CntContractOrderDTO.class));    	

		return (CntContractOrderDTO) query.uniqueResult();
	}

	public List<CntContractOrderDTO> getForAutoComplete(CntContractOrderDTO obj) {
		String sql = "SELECT CNT_CONTRACT_ORDER_ID cntContractOrderId"	
			+" ,NAME name"			
			+" ,VALUE value"
			+" FROM CNT_CONTRACT_ORDER"
			+" WHERE 1=1";			
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if (null != obj.getCntContractId()) {
			stringBuilder.append("AND T1.CNT_CONTRACT_ID = :cntContractId ");
		}
		if (null != obj.getPurchaseOrderId()) {
			stringBuilder.append("AND T1.PURCHASE_ORDER_ID = :purchaseOrderId ");
		}
		stringBuilder.append(" ORDER BY NAME");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("cntContractOrderId", new LongType());
		query.addScalar("name", new StringType());
		query.addScalar("value", new StringType());
	
		query.setResultTransformer(Transformers.aliasToBean(CntContractOrderDTO.class));

		if (null != obj.getCntContractId()) {
			query.setParameter("cntContractId", obj.getCntContractId());
		}
		if (null != obj.getPurchaseOrderId()) {
			query.setParameter("purchaseOrderId", obj.getPurchaseOrderId());
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public CntContractOrderDTO getById(Long id) {
    	StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CNT_CONTRACT_ORDER_ID cntContractOrderId ");
		stringBuilder.append(",T1.CNT_CONTRACT_ID cntContractId ");
		stringBuilder.append(",(SELECT CASE WHEN VALUE IS NULL THEN NAME ELSE (VALUE || ' - ' || NAME) END FROM CNT_CONTRACT WHERE CNT_CONTRACT_ID = T1.CNT_CONTRACT_ID) cntContractName  ");
		stringBuilder.append(",T1.PURCHASE_ORDER_ID purchaseOrderId ");
		stringBuilder.append(",(SELECT CASE WHEN VALUE IS NULL THEN NAME ELSE (VALUE || ' - ' || NAME) END FROM PURCHASE_ORDER WHERE PURCHASE_ORDER_ID = T1.PURCHASE_ORDER_ID) purchaseOrderName  ");

    	stringBuilder.append("FROM CNT_CONTRACT_ORDER T1 ");    	
    	stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND T1.CNT_CONTRACT_ORDER_ID = :cntContractOrderId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("cntContractOrderId", new LongType());
		query.addScalar("cntContractId", new LongType());
		query.addScalar("cntContractName", new StringType());
		query.addScalar("purchaseOrderId", new LongType());
		query.addScalar("purchaseOrderName", new StringType());
    	
		query.setParameter("cntContractOrderId", id);
		query.setResultTransformer(Transformers.aliasToBean(CntContractOrderDTO.class));
    	
		return (CntContractOrderDTO) query.uniqueResult();
	}
}
