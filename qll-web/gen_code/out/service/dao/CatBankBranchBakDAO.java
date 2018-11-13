package com.viettel.ims.dao;

import java.util.List;
import com.viettel.ktts2.dao.AbstractKttsBaseDao;
import com.viettel.ims.bo.CatBankBranchBakBO;
import com.viettel.ims.dto.CatBankBranchBakDTO;
import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 * @author hailh10
* Customize by hanhls1@ for ktts2
 */
@Repository("catBankBranchBakDAO")

public class CatBankBranchBakDAO extends AbstractKttsBaseDao<CatBankBranchBakBO, Long> {
    public CatBankBranchBakDAO() {
        this.model = new CatBankBranchBakBO();
    }

    public CatBankBranchBakDAO(Session session) {
        this.session = session;
    }
	
	@SuppressWarnings("unchecked")
	public List<CatBankBranchBakDTO> getAll() {
    	StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CAT_BANK_BRANCH_ID catBankBranchId ");
		stringBuilder.append(",T1.CITAD_CODE citadCode ");
		stringBuilder.append(",T1.BANK_CODE bankCode ");
		stringBuilder.append(",T1.PROVINCE_CODE_IM provinceCodeIm ");
		stringBuilder.append(",T1.BRANCH_NAME branchName ");
		stringBuilder.append(",T1.PROVINCE_NAME provinceName ");
		stringBuilder.append(",T1.IS_ACTIVE isActive ");
		stringBuilder.append(",T1.LEVEL_GROUP levelGroup ");
		stringBuilder.append(",T1.ADDRESS address ");
		stringBuilder.append(",T1.CREATOR_DATE creatorDate ");
		stringBuilder.append(",T1.CREATOR_ID creatorId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATOR_ID updatorId ");
		stringBuilder.append(",T1.UPDATOR_DATE updatorDate ");
		stringBuilder.append(",T1.UPDATOR_GROUP_ID updatorGroupId ");

    	stringBuilder.append("FROM CAT_BANK_BRANCH_BAK T1 ");
		
		//todo: add join thêm các trường hợp ở đây
    	
    	stringBuilder.append("WHERE T1.IS_ACTIVE =1 ");
    	stringBuilder.append("ORDER BY T1.CAT_BANK_BRANCH_BAK_ID DESC ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("catBankBranchId", new LongType());			
		query.addScalar("citadCode", new StringType());			
		query.addScalar("bankCode", new StringType());			
		query.addScalar("provinceCodeIm", new StringType());			
		query.addScalar("branchName", new StringType());			
		query.addScalar("provinceName", new StringType());			
		query.addScalar("isActive", new LongType());			
		query.addScalar("levelGroup", new StringType());			
		query.addScalar("address", new StringType());			
		query.addScalar("creatorDate", new DateType());			
		query.addScalar("creatorId", new LongType());			
		query.addScalar("createdGroupId", new LongType());			
		query.addScalar("updatorId", new LongType());			
		query.addScalar("updatorDate", new DateType());			
		query.addScalar("updatorGroupId", new LongType());			
    	
		query.setResultTransformer(Transformers.aliasToBean(CatBankBranchBakDTO.class));
    	
		return query.list();
	}
    
    @SuppressWarnings("unchecked")
	public List<CatBankBranchBakDTO> doSearch(CatBankBranchBakDTO criteria) {
    	StringBuilder stringBuilder = new StringBuilder("select count(*) over() totalRecord, ");
		stringBuilder.append("T1.CAT_BANK_BRANCH_ID catBankBranchId ");
		stringBuilder.append(",T1.CITAD_CODE citadCode ");
		stringBuilder.append(",T1.BANK_CODE bankCode ");
		stringBuilder.append(",T1.PROVINCE_CODE_IM provinceCodeIm ");
		stringBuilder.append(",T1.BRANCH_NAME branchName ");
		stringBuilder.append(",T1.PROVINCE_NAME provinceName ");
		stringBuilder.append(",T1.IS_ACTIVE isActive ");
		stringBuilder.append(",T1.LEVEL_GROUP levelGroup ");
		stringBuilder.append(",T1.ADDRESS address ");
		stringBuilder.append(",T1.CREATOR_DATE creatorDate ");
		stringBuilder.append(",T1.CREATOR_ID creatorId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATOR_ID updatorId ");
		stringBuilder.append(",T1.UPDATOR_DATE updatorDate ");
		stringBuilder.append(",T1.UPDATOR_GROUP_ID updatorGroupId ");
    	
    	stringBuilder.append("FROM CAT_BANK_BRANCH_BAK T1 ");
		
    	//todo: add thêm dideuf kiện where ở đây
    	
    	stringBuilder.append("WHERE T1.IS_ACTIVE = 1 ");
    			
		
		if (null != criteria.getCatBankBranchId()) {
			stringBuilder.append("AND T1.CAT_BANK_BRANCH_ID = :catBankBranchId ");
		}
		if (StringUtils.isNotEmpty(criteria.getCitadCode())) {
			stringBuilder.append("AND UPPER(T1.CITAD_CODE) LIKE UPPER(:citadCode) ");
		}
		if (StringUtils.isNotEmpty(criteria.getBankCode())) {
			stringBuilder.append("AND UPPER(T1.BANK_CODE) LIKE UPPER(:bankCode) ");
		}
		if (StringUtils.isNotEmpty(criteria.getProvinceCodeIm())) {
			stringBuilder.append("AND UPPER(T1.PROVINCE_CODE_IM) LIKE UPPER(:provinceCodeIm) ");
		}
		if (StringUtils.isNotEmpty(criteria.getBranchName())) {
			stringBuilder.append("AND UPPER(T1.BRANCH_NAME) LIKE UPPER(:branchName) ");
		}
		if (StringUtils.isNotEmpty(criteria.getProvinceName())) {
			stringBuilder.append("AND UPPER(T1.PROVINCE_NAME) LIKE UPPER(:provinceName) ");
		}
		if (null != criteria.getIsActive()) {
			stringBuilder.append("AND T1.IS_ACTIVE = :isActive ");
		}
		if (StringUtils.isNotEmpty(criteria.getLevelGroup())) {
			stringBuilder.append("AND UPPER(T1.LEVEL_GROUP) LIKE UPPER(:levelGroup) ");
		}
		if (StringUtils.isNotEmpty(criteria.getAddress())) {
			stringBuilder.append("AND UPPER(T1.ADDRESS) LIKE UPPER(:address) ");
		}
		if (null != criteria.getCreatorDate()) {
			stringBuilder.append("AND T1.CREATOR_DATE = :creatorDate ");
		}
		if (null != criteria.getCreatorId()) {
			stringBuilder.append("AND T1.CREATOR_ID = :creatorId ");
		}
		if (null != criteria.getCreatedGroupId()) {
			stringBuilder.append("AND T1.CREATED_GROUP_ID = :createdGroupId ");
		}
		if (null != criteria.getUpdatorId()) {
			stringBuilder.append("AND T1.UPDATOR_ID = :updatorId ");
		}
		if (null != criteria.getUpdatorDate()) {
			stringBuilder.append("AND T1.UPDATOR_DATE = :updatorDate ");
		}
		if (null != criteria.getUpdatorGroupId()) {
			stringBuilder.append("AND T1.UPDATOR_GROUP_ID = :updatorGroupId ");
		}
		
    	if (StringUtils.isNotEmpty(criteria.getOrderBy())){
			stringBuilder.append("ORDER BY " + criteria.getOrderBy() + " " + criteria.getOrderType() + " ");
		} else {
			stringBuilder.append("ORDER BY T1.CAT_BANK_BRANCH_ID, T1.IS_ACTIVE ");
		}
    	    	
		
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
	
		
	
		query.addScalar("totalRecord", new IntegerType());
		query.addScalar("catBankBranchId", new LongType());		
		query.addScalar("citadCode", new StringType());		
		query.addScalar("bankCode", new StringType());		
		query.addScalar("provinceCodeIm", new StringType());		
		query.addScalar("branchName", new StringType());		
		query.addScalar("provinceName", new StringType());		
		query.addScalar("isActive", new LongType());		
		query.addScalar("levelGroup", new StringType());		
		query.addScalar("address", new StringType());		
		query.addScalar("creatorDate", new DateType());		
		query.addScalar("creatorId", new LongType());		
		query.addScalar("createdGroupId", new LongType());		
		query.addScalar("updatorId", new LongType());		
		query.addScalar("updatorDate", new DateType());		
		query.addScalar("updatorGroupId", new LongType());		
		
    	
		if (null != criteria.getCatBankBranchId()) {
			query.setParameter("catBankBranchId", criteria.getCatBankBranchId());
		}
		if (StringUtils.isNotEmpty(criteria.getCitadCode())) {
			query.setParameter("citadCode", "%" + criteria.getCitadCode().toUpperCase() + "%");
		}
		if (StringUtils.isNotEmpty(criteria.getBankCode())) {
			query.setParameter("bankCode", "%" + criteria.getBankCode().toUpperCase() + "%");
		}
		if (StringUtils.isNotEmpty(criteria.getProvinceCodeIm())) {
			query.setParameter("provinceCodeIm", "%" + criteria.getProvinceCodeIm().toUpperCase() + "%");
		}
		if (StringUtils.isNotEmpty(criteria.getBranchName())) {
			query.setParameter("branchName", "%" + criteria.getBranchName().toUpperCase() + "%");
		}
		if (StringUtils.isNotEmpty(criteria.getProvinceName())) {
			query.setParameter("provinceName", "%" + criteria.getProvinceName().toUpperCase() + "%");
		}
		if (null != criteria.getIsActive()) {
			query.setParameter("isActive", criteria.getIsActive());
		}
		if (StringUtils.isNotEmpty(criteria.getLevelGroup())) {
			query.setParameter("levelGroup", "%" + criteria.getLevelGroup().toUpperCase() + "%");
		}
		if (StringUtils.isNotEmpty(criteria.getAddress())) {
			query.setParameter("address", "%" + criteria.getAddress().toUpperCase() + "%");
		}
		if (null != criteria.getCreatorDate()) {
			query.setParameter("creatorDate", criteria.getCreatorDate());
		}
		if (null != criteria.getCreatorId()) {
			query.setParameter("creatorId", criteria.getCreatorId());
		}
		if (null != criteria.getCreatedGroupId()) {
			query.setParameter("createdGroupId", criteria.getCreatedGroupId());
		}
		if (null != criteria.getUpdatorId()) {
			query.setParameter("updatorId", criteria.getUpdatorId());
		}
		if (null != criteria.getUpdatorDate()) {
			query.setParameter("updatorDate", criteria.getUpdatorDate());
		}
		if (null != criteria.getUpdatorGroupId()) {
			query.setParameter("updatorGroupId", criteria.getUpdatorGroupId());
		}
    	if (criteria.getSize() != 0 ) {
			query.setFirstResult((criteria.getPage()-1)*criteria.getSize());
			query.setMaxResults(criteria.getSize());
		}
		query.setResultTransformer(Transformers.aliasToBean(CatBankBranchBakDTO.class));    	
		List ls = query.list();
		
		return ls;
	}  
	
	public CatBankBranchBakDTO findByValue(String value) {
		StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CAT_BANK_BRANCH_ID catBankBranchId ");
		stringBuilder.append(",T1.CITAD_CODE citadCode ");
		stringBuilder.append(",T1.BANK_CODE bankCode ");
		stringBuilder.append(",T1.PROVINCE_CODE_IM provinceCodeIm ");
		stringBuilder.append(",T1.BRANCH_NAME branchName ");
		stringBuilder.append(",T1.PROVINCE_NAME provinceName ");
		stringBuilder.append(",T1.IS_ACTIVE isActive ");
		stringBuilder.append(",T1.LEVEL_GROUP levelGroup ");
		stringBuilder.append(",T1.ADDRESS address ");
		stringBuilder.append(",T1.CREATOR_DATE creatorDate ");
		stringBuilder.append(",T1.CREATOR_ID creatorId ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		stringBuilder.append(",T1.UPDATOR_ID updatorId ");
		stringBuilder.append(",T1.UPDATOR_DATE updatorDate ");
		stringBuilder.append(",T1.UPDATOR_GROUP_ID updatorGroupId ");
    	
    	stringBuilder.append("FROM CAT_BANK_BRANCH_BAK T1 ");    
		//TODO: chỉnh sửa cột dữ liệ
    	//stringBuilder.append("WHERE T1.IS_ACTIVE=1 AND upper(T1.VALUE) = upper(:value)");	
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("catBankBranchId", new LongType());
		query.addScalar("citadCode", new StringType());
		query.addScalar("bankCode", new StringType());
		query.addScalar("provinceCodeIm", new StringType());
		query.addScalar("branchName", new StringType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("isActive", new LongType());
		query.addScalar("levelGroup", new StringType());
		query.addScalar("address", new StringType());
		query.addScalar("creatorDate", new DateType());
		query.addScalar("creatorId", new LongType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("updatorId", new LongType());
		query.addScalar("updatorDate", new DateType());
		query.addScalar("updatorGroupId", new LongType());
    	
		//todo: chình sửa kiểu dữ liệu
		//query.setParameter("value", value);    	
		query.setResultTransformer(Transformers.aliasToBean(CatBankBranchBakDTO.class));    	

		return (CatBankBranchBakDTO) query.uniqueResult();
	}

	public List<CatBankBranchBakBO> getForAutoComplete(CatBankBranchBakDTO obj) {
		String sql = "SELECT t.*"	
			
			+" FROM CAT_BANK_BRANCH_BAK t"
			+" WHERE IS_ACTIVE=1";			
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
		stringBuilder.append(" AND ROWNUM <=:page" );
		//todo Add thêm điều kiện tìm kiệm
		if(StringUtils.isNotEmpty(obj.getKeySearch())){	
			stringBuilder.append("upper(CAT_BANK_BRANCH_ID) like :keySearch");
		}
		
		
		stringBuilder.append(" ORDER BY CAT_BANK_BRANCH_ID desc");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addEntity(CatBankBranchBakBO.class);		
		
	
		

		
		query.setParameter("page",obj.getSize()>0?obj.getSize():10);
		
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + obj.getKeySearch().toUpperCase() + "%");
		}
		

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public CatBankBranchBakDTO getById(Long id) {
    	StringBuilder stringBuilder = new StringBuilder("SELECT ");
		stringBuilder.append("T1.CAT_BANK_BRANCH_ID catBankBranchId ");
		stringBuilder.append(",T1.CITAD_CODE citadCode ");
		stringBuilder.append(",T1.BANK_CODE bankCode ");
		stringBuilder.append(",T1.PROVINCE_CODE_IM provinceCodeIm ");
		stringBuilder.append(",T1.BRANCH_NAME branchName ");
		stringBuilder.append(",T1.PROVINCE_NAME provinceName ");
		stringBuilder.append(",T1.IS_ACTIVE isActive ");
		stringBuilder.append(",T1.LEVEL_GROUP levelGroup ");
		stringBuilder.append(",T1.ADDRESS address ");
		stringBuilder.append(",T1.CREATOR_DATE creatorDate ");
		stringBuilder.append(",T1.CREATOR_ID creatorId ");
		//todo: change Table name
		stringBuilder.append(",CASE WHEN xxx.VALUE IS NULL THEN xxx.NAME ELSE (xxx.VALUE || ' - ' || xxx.NAME) END creatorName ");
		stringBuilder.append(",T1.CREATED_GROUP_ID createdGroupId ");
		//todo: change Table name
		stringBuilder.append(",CASE WHEN xxx.VALUE IS NULL THEN xxx.NAME ELSE (xxx.VALUE || ' - ' || xxx.NAME) END createdGroupName ");
		stringBuilder.append(",T1.UPDATOR_ID updatorId ");
		//todo: change Table name
		stringBuilder.append(",CASE WHEN xxx.VALUE IS NULL THEN xxx.NAME ELSE (xxx.VALUE || ' - ' || xxx.NAME) END updatorName ");
		stringBuilder.append(",T1.UPDATOR_DATE updatorDate ");
		stringBuilder.append(",T1.UPDATOR_GROUP_ID updatorGroupId ");
		//todo: change Table name
		stringBuilder.append(",CASE WHEN xxx.VALUE IS NULL THEN xxx.NAME ELSE (xxx.VALUE || ' - ' || xxx.NAME) END updatorGroupName ");

    	stringBuilder.append("FROM CAT_BANK_BRANCH_BAK T1 ");
		
		//todo: change Table name
		stringBuilder.append("LEFT JOIN xxxTableName Txxx ON T1.CREATOR_ID = Txxx.CREATOR_ID ");
		//todo: change Table name
		stringBuilder.append("LEFT JOIN xxxTableName Txxx ON T1.CREATED_GROUP_ID = Txxx.CREATED_GROUP_ID ");
		//todo: change Table name
		stringBuilder.append("LEFT JOIN xxxTableName Txxx ON T1.UPDATOR_ID = Txxx.UPDATOR_ID ");
		//todo: change Table name
		stringBuilder.append("LEFT JOIN xxxTableName Txxx ON T1.UPDATOR_GROUP_ID = Txxx.UPDATOR_GROUP_ID ");
    	
    	stringBuilder.append("WHERE T1.IS_DELETED = 'N' AND T1.CAT_BANK_BRANCH_BAK_ID = :catBankBranchBakId ");
    	
    	SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
    	
		query.addScalar("catBankBranchId", new LongType());
		query.addScalar("citadCode", new StringType());
		query.addScalar("bankCode", new StringType());
		query.addScalar("provinceCodeIm", new StringType());
		query.addScalar("branchName", new StringType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("isActive", new LongType());
		query.addScalar("levelGroup", new StringType());
		query.addScalar("address", new StringType());
		query.addScalar("creatorDate", new DateType());
		query.addScalar("creatorId", new LongType());
		query.addScalar("creatorName", new StringType());
		query.addScalar("createdGroupId", new LongType());
		query.addScalar("createdGroupName", new StringType());
		query.addScalar("updatorId", new LongType());
		query.addScalar("updatorName", new StringType());
		query.addScalar("updatorDate", new DateType());
		query.addScalar("updatorGroupId", new LongType());
		query.addScalar("updatorGroupName", new StringType());
    	
		query.setParameter("catBankBranchBakId", id);
		query.setResultTransformer(Transformers.aliasToBean(CatBankBranchBakDTO.class));
    	
		return (CatBankBranchBakDTO) query.uniqueResult();
	}
}
