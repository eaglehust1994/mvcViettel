package com.viettel.ims.dao;

import java.util.ArrayList;
import java.util.List;

import com.viettel.ims.bo.CntContractBO;
import com.viettel.ims.dto.CatConstructionTypeDTO;
import com.viettel.ims.dto.CntContractDTO;
import com.viettel.ims.dto.PurchaseOrderDTO;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.viettel.service.base.dao.BaseFWDAOImpl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.ArrayType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.ListType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
 */
@Repository("cntContractDAO")
public class CntContractDAO extends BaseFWDAOImpl<CntContractBO, Long> {

    public CntContractDAO() {
        this.model = new CntContractBO();
    }

    public CntContractDAO(Session session) {
        this.session = session;
    }	
    
    @SuppressWarnings("unchecked")
	public List<CntContractDTO> doSearch(CntContractDTO criteria) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE 1=1 ");
    	
    	if(StringUtils.isNotEmpty(criteria.getKeySearch())){
    		stringBuilder.append(" AND (UPPER(T1.NAME) like UPPER(:key) OR UPPER(T1.CODE) like UPPER(:key) escape '&')");
		}
		
		if (StringUtils.isNotEmpty(criteria.getContractCodeKtts())) {
			stringBuilder.append("AND UPPER(T1.CONTRACT_CODE_KTTS) LIKE UPPER(:contractCodeKtts) ESCAPE '\\' ");
		}
		
		if (null != criteria.getSignDate()) {
			stringBuilder.append("AND T1.SIGN_DATE = :signDate ");
		}
		if (null != criteria.getSignDateFrom()) {
			stringBuilder.append("AND T1.SIGN_DATE >= :signDateFrom ");
		}
		if (null != criteria.getSignDateTo()) {
			stringBuilder.append("AND T1.SIGN_DATE <= :signDateTo ");
		}
	
		if (null != criteria.getCatPartnerId()) {
			stringBuilder.append("AND T1.CAT_PARTNER_ID = :catPartnerId ");
		}
	
		if (null != criteria.getSysGroupId()) {
			stringBuilder.append("AND T1.SYS_GROUP_ID = :sysGroupId ");
		}
		
		if (null != criteria.getStatus()) {
			stringBuilder.append("AND T1.STATUS = :status ");
		}
	
		if (null != criteria.getContractType()) {
			stringBuilder.append("AND T1.CONTRACT_TYPE = :contractType ");
		}
		
		stringBuilder.append("ORDER BY T1.CNT_CONTRACT_ID DESC");
	
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(stringBuilder.toString());
		sqlCount.append(")");

		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("cntContractId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("contractCodeKtts", new StringType());
		query.addScalar("content", new StringType());
		query.addScalar("signDate", new DateType());
		query.addScalar("startTime", new DateType());
		query.addScalar("endTime", new DateType());
		query.addScalar("price", new DoubleType());
		query.addScalar("appendixContract", new DoubleType());
		query.addScalar("numStation", new DoubleType());
		query.addScalar("biddingPackageId", new LongType());
		query.addScalar("biddingPackageName", new StringType());
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("catPartnerName", new StringType());
		query.addScalar("signerPartner", new StringType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("signerGroup", new LongType());
		query.addScalar("signerGroupName", new StringType());
		query.addScalar("supervisor", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("formal", new DoubleType());
		query.addScalar("contractType", new LongType());
		query.addScalar("cntContractParentId", new DoubleType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());	
		
		
		if (StringUtils.isNotEmpty(criteria.getKeySearch())) {
			query.setParameter("key", "%" + criteria.getKeySearch() + "%");
			queryCount.setParameter("key", "%" + criteria.getKeySearch() + "%");
		}
		if (null != criteria.getSignDate()) {
			query.setParameter("signDate", criteria.getSignDate());
		}
		if (null != criteria.getSignDateFrom()) {
			query.setTimestamp("signDateFrom", criteria.getSignDateFrom());
			queryCount.setTimestamp("signDateFrom", criteria.getSignDateFrom());
		}
		if (null != criteria.getSignDateTo()) {
			query.setTimestamp("signDateTo", criteria.getSignDateTo());
			queryCount.setTimestamp("signDateTo", criteria.getSignDateTo());
		}
		
		if (null != criteria.getCatPartnerId()) {
			query.setParameter("catPartnerId", criteria.getCatPartnerId());
			queryCount.setParameter("catPartnerId", criteria.getCatPartnerId());
		}
	
		if (null != criteria.getSysGroupId()) {
			query.setParameter("sysGroupId", criteria.getSysGroupId());
			queryCount.setParameter("sysGroupId", criteria.getSysGroupId());
		}
		
		if (null != criteria.getStatus()) {
			query.setParameter("status", criteria.getStatus());
			queryCount.setParameter("status", criteria.getStatus());
		}
		if (null != criteria.getFormal()) {
			query.setParameter("formal", criteria.getFormal());
			queryCount.setParameter("formal", criteria.getFormal());
		}
		if (null != criteria.getContractType()) {
			query.setParameter("contractType", criteria.getContractType());
			queryCount.setParameter("contractType", criteria.getContractType());
		}
		
		query.setResultTransformer(Transformers.aliasToBean(CntContractDTO.class));
		List ls = query.list();
		if (criteria.getPage() != null && criteria.getPageSize() != null) {
			query.setFirstResult((criteria.getPage().intValue() - 1)
					* criteria.getPageSize().intValue());
			query.setMaxResults(criteria.getPageSize().intValue());
		}
		criteria.setTotalRecord(((BigDecimal) queryCount.uniqueResult()).intValue());
		return ls;
	}  
	
	public CntContractDTO findByCode(String value) {
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CNT_CONTRACT_ID cntContractId ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.CONTRACT_CODE_KTTS contractCodeKtts ");
		stringBuilder.append(",T1.CONTENT content ");
		stringBuilder.append(",T1.SIGN_DATE signDate ");
		stringBuilder.append(",T1.START_TIME startTime ");
		stringBuilder.append(",T1.END_TIME endTime ");
		stringBuilder.append(",T1.PRICE price ");
		stringBuilder.append(",T1.APPENDIX_CONTRACT appendixContract ");
		stringBuilder.append(",T1.NUM_STATION numStation ");
		stringBuilder.append(",T1.BIDDING_PACKAGE_ID biddingPackageId ");
		stringBuilder.append(",T1.CAT_PARTNER_ID catPartnerId ");
		stringBuilder.append(",T1.SIGNER_PARTNER signerPartner ");
		stringBuilder.append(",T1.SYS_GROUP_ID sysGroupId ");
		stringBuilder.append(",T1.SIGNER_GROUP signerGroup ");
		stringBuilder.append(",T1.SUPERVISOR supervisor ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.FORMAL formal ");
		stringBuilder.append(",T1.CONTRACT_TYPE contractType ");
		stringBuilder.append(",T1.CNT_CONTRACT_PARENT_ID cntContractParentId ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.CREATED_USER_ID createdUserId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.UPDATED_USER_ID updatedUserId ");
		stringBuilder.append(",T1.UPDATED_GROUP_ID updatedGroupId ");
    	
    	stringBuilder.append("FROM CNT_CONTRACT T1 ");    	
    	stringBuilder.append("WHERE  upper(T1.CODE) = upper(:code)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("cntContractId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("contractCodeKtts", new StringType());
		query.addScalar("content", new StringType());
		query.addScalar("signDate", new DateType());
		query.addScalar("startTime", new DateType());
		query.addScalar("endTime", new DateType());
		query.addScalar("price", new DoubleType());
		query.addScalar("appendixContract", new DoubleType());
		query.addScalar("numStation", new DoubleType());
		query.addScalar("biddingPackageId", new LongType());
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("signerPartner", new StringType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("signerGroup", new LongType());
		query.addScalar("supervisor", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("formal", new DoubleType());
		query.addScalar("contractType", new LongType());
		query.addScalar("cntContractParentId", new DoubleType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
    	
		query.setParameter("code", value);    	
		query.setResultTransformer(Transformers.aliasToBean(CntContractDTO.class));    	

		return (CntContractDTO) query.uniqueResult();
	}

	public List<CntContractDTO> getForAutoComplete(CntContractDTO obj) {
		StringBuilder stringBuilder = getSelectAllQuery();
		
		stringBuilder.append(" Where STATUS = 1");
		stringBuilder.append(obj.getIsSize() ? " AND ROWNUM <=10" : "");
		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			stringBuilder.append(" AND (UPPER(NAME) like UPPER(:key) OR UPPER(CODE) like UPPER(:key) escape '&')");
		}
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("cntContractId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("contractCodeKtts", new StringType());
		query.addScalar("content", new StringType());
		query.addScalar("signDate", new DateType());
		query.addScalar("startTime", new DateType());
		query.addScalar("endTime", new DateType());
		query.addScalar("price", new DoubleType());
		query.addScalar("appendixContract", new DoubleType());
		query.addScalar("numStation", new DoubleType());
		query.addScalar("biddingPackageId", new LongType());
		query.addScalar("biddingPackageName", new StringType());
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("signerPartner", new StringType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("signerGroup", new LongType());
		query.addScalar("signerGroupName", new StringType());
		query.addScalar("supervisor", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("formal", new DoubleType());
		query.addScalar("contractType", new LongType());
		query.addScalar("cntContractParentId", new DoubleType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("createdUserId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedUserId", new LongType());
		query.addScalar("updatedGroupId", new LongType());
	
		query.setResultTransformer(Transformers.aliasToBean(CntContractDTO.class));

		if(StringUtils.isNotEmpty(obj.getKeySearch())){
			query.setParameter("key","%"+ obj.getKeySearch()+"%");
		}

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public CntContractDTO getById(Long id) {
    	StringBuilder stringBuilder = getSelectAllQuery();
    	stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND T1.CNT_CONTRACT_ID = :cntContractId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("cntContractId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("contractCodeKtts", new StringType());
		query.addScalar("content", new StringType());
		query.addScalar("signDate", new DateType());
		query.addScalar("startTime", new DateType());
		query.addScalar("endTime", new DateType());
		query.addScalar("price", new DoubleType());
		query.addScalar("appendixContract", new DoubleType());
		query.addScalar("numStation", new DoubleType());
		query.addScalar("biddingPackageId", new LongType());
		query.addScalar("biddingPackageName", new StringType());
		query.addScalar("catPartnerId", new LongType());
		query.addScalar("catPartnerName", new StringType());
		query.addScalar("signerPartner", new StringType());
		query.addScalar("sysGroupId", new LongType());
		query.addScalar("sysGroupName", new StringType());
		query.addScalar("signerGroup", new LongType());
		query.addScalar("signerGroupName", new StringType());
		query.addScalar("supervisor", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("formal", new DoubleType());
		query.addScalar("contractType", new LongType());
		query.addScalar("cntContractParentId", new DoubleType());
		query.addScalar("cntContractParentName", new StringType());
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
    	
		query.setParameter("cntContractId", id);
		query.setResultTransformer(Transformers.aliasToBean(CntContractDTO.class));
    	
		return (CntContractDTO) query.uniqueResult();
	}
	
	public StringBuilder getSelectAllQuery(){
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CNT_CONTRACT_ID cntContractId ");
		stringBuilder.append(",T1.CODE code ");
		stringBuilder.append(",T1.NAME name ");
		stringBuilder.append(",T1.CONTRACT_CODE_KTTS contractCodeKtts ");
		stringBuilder.append(",T1.CONTENT content ");
		stringBuilder.append(",T1.SIGN_DATE signDate ");
		stringBuilder.append(",T1.START_TIME startTime ");
		stringBuilder.append(",T1.END_TIME endTime ");
		stringBuilder.append(",T1.PRICE price ");
		stringBuilder.append(",T1.APPENDIX_CONTRACT appendixContract ");
		stringBuilder.append(",T1.NUM_STATION numStation ");
		stringBuilder.append(",T1.BIDDING_PACKAGE_ID biddingPackageId ");
		stringBuilder.append(",T1.CAT_PARTNER_ID catPartnerId ");
		stringBuilder.append(",T1.SIGNER_PARTNER signerPartner ");
		stringBuilder.append(",T1.SYS_GROUP_ID sysGroupId ");
		stringBuilder.append(",T1.SIGNER_GROUP signerGroup ");
		stringBuilder.append(",T1.SUPERVISOR supervisor ");
		stringBuilder.append(",T1.STATUS status ");
		stringBuilder.append(",T1.FORMAL formal ");
		stringBuilder.append(",T1.CONTRACT_TYPE contractType ");
		stringBuilder.append(",T1.CNT_CONTRACT_PARENT_ID cntContractParentId ");
		stringBuilder.append(",T1.CREATED_DATE createdDate ");
		stringBuilder.append(",T1.CREATED_USER_ID createdUserId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATED_DATE updatedDate ");
		stringBuilder.append(",T1.UPDATED_USER_ID updatedUserId ");
		stringBuilder.append(",T1.UPDATED_GROUP_ID updatedGroupId ");
		stringBuilder.append(",T2.NAME catPartnerName ");
		stringBuilder.append(",T3.NAME sysGroupName ");
		stringBuilder.append(",T4.NAME biddingPackageName ");
		stringBuilder.append(",T4.NAME biddingPackageName ");
		stringBuilder.append(",T5.FULL_NAME signerGroupName ");
    	stringBuilder.append("FROM CNT_CONTRACT T1 ");    
    	stringBuilder.append("JOIN CTCT_CAT_OWNER.CAT_PARTNER T2 ON T1.CAT_PARTNER_ID = T2.CAT_PARTNER_ID ");
    	stringBuilder.append("JOIN CTCT_CAT_OWNER.SYS_GROUP T3 ON T1.SYS_GROUP_ID = T3.SYS_GROUP_ID ");
    	stringBuilder.append("JOIN BIDDING_PACKAGE T4 ON T1.BIDDING_PACKAGE_ID = T4.BIDDING_PACKAGE_ID ");
    	stringBuilder.append("JOIN CTCT_VPS_OWNER.SYS_USER T5 ON T1.SIGNER_GROUP = T5.SYS_USER_ID ");
    	return stringBuilder;
	}
	
	public List<PurchaseOrderDTO> getOrder(Long contractId){
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
		
		stringBuilder.append("FROM PURCHASE_ORDER T1 ");
		stringBuilder.append("Where T1.PURCHASE_ORDER_ID IN("
				+ "SELECT T2.PURCHASE_ORDER_ID FROM CNT_CONTRACT_ORDER T2 WHERE T2.CNT_CONTRACT_ID = :cntContractId"
				+ ") ");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("purchaseOrderId", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("catPartnerId", new LongType());
	
		query.addScalar("signerPartner", new StringType());
		query.addScalar("sysGroupId", new LongType());

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
		
		query.setParameter("cntContractId", contractId);
		query.setResultTransformer(Transformers.aliasToBean(PurchaseOrderDTO.class));
		
		return query.list();
	}
	
	
}
