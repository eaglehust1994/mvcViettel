package com.viettel.ims.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.viettel.ims.bo.PurchaseOrderBO;
import com.viettel.ims.dto.PurchaseOrderDTO;
import com.viettel.service.base.dao.BaseFWDAOImpl;
//import com.viettel.utils.FilterUtilities;

/**
 * @author hailh10
 */
@Repository("purchaseOrderDAO")
public class PurchaseOrderDAO extends BaseFWDAOImpl<PurchaseOrderBO, Long> {

    public PurchaseOrderDAO() {
        this.model = new PurchaseOrderBO();
    }

    public PurchaseOrderDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<PurchaseOrderDTO> doSearch(PurchaseOrderDTO criteria) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE 1=1 ");
    	
    	if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			stringBuilder
					.append(" AND (UPPER(T1.NAME) like UPPER(:key) OR UPPER(T1.CODE) like UPPER(:key) escape '&')");
		}
		
		
		if (null != criteria.getCatPartnerId()) {
			stringBuilder.append("AND T1.CAT_PARTNER_ID = :catPartnerId ");
		}
		if (StringUtils.isNotEmpty(criteria.getSignerPartner())) {
			stringBuilder.append("AND UPPER(T1.SIGNER_PARTNER) LIKE UPPER(:signerPartner) ESCAPE '\\' ");
		}
		if (null != criteria.getSysGroupId()) {
			stringBuilder.append("AND T1.SYS_GROUP_ID = :sysGroupId ");
		}
		
		if (null != criteria.getSignDateFrom()) {
			stringBuilder.append("AND T1.SIGN_DATE >= :signDateFrom ");
		}
		if (null != criteria.getSignDateTo()) {
			stringBuilder.append("AND T1.SIGN_DATE <= :signDateTo ");
		}
		if (null != criteria.getPrice()) {
			stringBuilder.append("AND T1.PRICE = :price ");
		}
		if (StringUtils.isNotEmpty(criteria.getExpense())) {
			stringBuilder.append("AND UPPER(T1.EXPENSE) LIKE UPPER(:expense) ESCAPE '\\' ");
		}
		if (null != criteria.getStatus()) {
			stringBuilder.append("AND T1.STATUS = :status ");
		}		
	
		stringBuilder.append("ORDER BY T1.PURCHASE_ORDER_ID DESC");
		
    	StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

    	
		query.addScalar("purchaseOrderId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("catPartnerName", new StringType());
		query.addScalar("signerPartner", new StringType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("signerGroupName", new StringType());
		query.addScalar("signerGroupId", new LongType());
		query.addScalar("signDate", new DateType());
		query.addScalar("price", new DoubleType());
		query.addScalar("expense", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
//		query.addScalar("createdUserName", new StringType());
		query.addScalar("createdGroupId", new LongType());
//		query.addScalar("createdGroupName", new StringType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
//		query.addScalar("updatedUserName", new StringType());
		query.addScalar("updatedGroupId", new LongType());
//		query.addScalar("updatedGroupName", new StringType());
	
		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			query.setParameter("key", "%" + criteria.getKeySearch() + "%");
			queryCount.setParameter("key", "%" + criteria.getKeySearch() + "%");
		}
    	
		
		if (null != criteria.getCatPartnerId()) {
			query.setParameter("catPartnerId", criteria.getCatPartnerId());
			queryCount.setParameter("catPartnerId", criteria.getCatPartnerId());
		}
		
		if (null != criteria.getSysGroupId()) {
			query.setParameter("sysGroupId", criteria.getSysGroupId());
			queryCount.setParameter("sysGroupId", criteria.getSysGroupId());
		}
		
		if (null != criteria.getSignDate()) {
			query.setParameter("signDate", criteria.getSignDate());
			queryCount.setParameter("signDate", criteria.getSignDate());
		}
		if (null != criteria.getSignDateFrom()) {
			query.setTimestamp("signDateFrom", criteria.getSignDateFrom());
			queryCount.setTimestamp("signDateFrom", criteria.getSignDateFrom());
		}
		if (null != criteria.getSignDateTo()) {
			query.setTimestamp("signDateTo", criteria.getSignDateTo());
			queryCount.setTimestamp("signDateTo", criteria.getSignDateTo());
		}
		if (null != criteria.getPrice()) {
			query.setParameter("price", criteria.getPrice());
			queryCount.setParameter("price", criteria.getPrice());
		}
		
		
		if (null != criteria.getStatus()) {
			query.setParameter("status", criteria.getStatus());
			queryCount.setParameter("status", criteria.getStatus());
		}
		if (null != criteria.getCreatedDate()) {
			query.setParameter("createdDate", criteria.getCreatedDate());
		}
		if (null != criteria.getCreatedDateFrom()) {
			query.setTimestamp("createdDateFrom", criteria.getCreatedDateFrom());
		}
		if (null != criteria.getCreatedDateTo()) {
			query.setTimestamp("createdDateTo", criteria.getCreatedDateTo());
		}
		if (null != criteria.getCreatedUserId()) {
			query.setParameter("createdUserId", criteria.getCreatedUserId());
		}
		if (null != criteria.getCreatedGroupId()) {
			query.setParameter("createdGroupId", criteria.getCreatedGroupId());
		}
		if (null != criteria.getUpdatedDate()) {
			query.setParameter("updatedDate", criteria.getUpdatedDate());
		}
		if (null != criteria.getUpdatedDateFrom()) {
			query.setTimestamp("updatedDateFrom", criteria.getUpdatedDateFrom());
		}
		if (null != criteria.getUpdatedDateTo()) {
			query.setTimestamp("updatedDateTo", criteria.getUpdatedDateTo());
		}
		if (null != criteria.getUpdatedUserId()) {
			query.setParameter("updatedUserId", criteria.getUpdatedUserId());
		}
		if (null != criteria.getUpdatedGroupId()) {
			query.setParameter("updatedGroupId", criteria.getUpdatedGroupId());
		}
    	if (criteria.getMaxResult() != 0) {
			query.setFirstResult(criteria.getStart());
			query.setMaxResults(criteria.getMaxResult());
		}
    	query.setResultTransformer(Transformers
				.aliasToBean(PurchaseOrderDTO.class));
		List ls = query.list();
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1)
					* criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		criteria.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return ls;
	}  
	
	public PurchaseOrderDTO findByCode(String value) {
		StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE upper(T1.CODE ) = upper(:value)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	query.addScalar("purchaseOrderId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("catPartnerName", new StringType());
		query.addScalar("signerPartner", new StringType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("signerGroupName", new StringType());
		query.addScalar("signerGroupId", new LongType());
		query.addScalar("signDate", new DateType());
		query.addScalar("price", new DoubleType());
		query.addScalar("expense", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
//		query.addScalar("createdUserName", new StringType());
		query.addScalar("createdGroupId", new LongType());
//		query.addScalar("createdGroupName", new StringType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
//		query.addScalar("updatedUserName", new StringType());
		query.addScalar("updatedGroupId", new LongType());
//		query.addScalar("updatedGroupName", new StringType());
    	
    	
		query.setParameter("value", value);    	
		query.setResultTransformer(Transformers.aliasToBean(PurchaseOrderDTO.class));    	

		return (PurchaseOrderDTO) query.uniqueResult();
	}

	public List<PurchaseOrderDTO> getForAutoComplete(PurchaseOrderDTO obj) {
		StringBuilder stringBuilder = getSelectAllQuery();
		
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			stringBuilder.append(" AND (UPPER(T1.NAME) like UPPER(:key) OR UPPER(T1.CODE) like UPPER(:key) escape '&')");
		}		
		stringBuilder.append(" ORDER BY T1.NAME");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("purchaseOrderId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("catPartnerName", new StringType());
		query.addScalar("signerPartner", new StringType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("signerGroupName", new StringType());
		query.addScalar("signerGroupId", new LongType());
		query.addScalar("signDate", new DateType());
		query.addScalar("price", new DoubleType());
		query.addScalar("expense", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
	
		query.addScalar("createdGroupId", new LongType());

		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());

		query.addScalar("updatedGroupId", new LongType());
	
	
		query.setResultTransformer(Transformers.aliasToBean(PurchaseOrderDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("key", "%" + obj.getKeySearch() + "%"); 
		}
		
		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + obj.getName() + "%");
		}
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public PurchaseOrderDTO getById(Long id) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND T1.PURCHASE_ORDER_ID = :purchaseOrderId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("purchaseOrderId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("catPartnerName", new StringType());
		query.addScalar("signerPartner", new StringType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("signerGroupName", new StringType());
		query.addScalar("signerGroupId", new LongType());
		query.addScalar("signDate", new DateType());
		query.addScalar("price", new DoubleType());
		query.addScalar("expense", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdUserName", new StringType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("createdGroupName", new StringType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedUserName", new StringType());
		query.addScalar("updatedGroupId", new LongType());
		query.addScalar("updatedGroupName", new StringType());
    	
		query.setParameter("purchaseOrderId", id);
		query.setResultTransformer(Transformers.aliasToBean(PurchaseOrderDTO.class));
    	
		return (PurchaseOrderDTO) query.uniqueResult();
	}
	
	public StringBuilder getSelectAllQuery(){
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.PURCHASE_ORDER_ID purchaseOrderId ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.CAT_PARTNER_ID catPartnerId ");
		stringBuilder.append(",T1.SIGNER_PARTNER signerPartner ");
		stringBuilder.append(",T1.SYS_GROUP_ID sysGroupId ");
		stringBuilder.append(",T1.SIGNER_GROUP_NAME signerGroupName ");
		stringBuilder.append(",T1.SIGNER_GROUP_ID signerGroupId ");
		stringBuilder.append(",T1.SIGN_DATE signDate ");
		stringBuilder.append(",T1.PRICE price ");
		stringBuilder.append(",T1.EXPENSE expense ");
		stringBuilder.append(",T1.DESCRIPTION description ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.CREATED_USER_ID createdUserId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.UPDATED_USER_ID updatedUserId ");
		stringBuilder.append(",T1.UPDATED_GROUP_ID updatedGroupId ");
		stringBuilder.append(",T2.NAME catPartnerName ");
		stringBuilder.append(",T3.NAME sysGroupName ");
    	
    	stringBuilder.append("FROM PURCHASE_ORDER T1 "); 
    	stringBuilder.append("JOIN CTCT_CAT_OWNER.CAT_PARTNER T2 ON T2.CAT_PARTNER_ID = T1.CAT_PARTNER_ID ");
    	stringBuilder.append("JOIN CTCT_CAT_OWNER.SYS_GROUP T3 ON T3.SYS_GROUP_ID = T1.SYS_GROUP_ID "); 
    	return stringBuilder;
	}
	
}
