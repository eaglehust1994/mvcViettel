package com.viettel.asset.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.viettel.asset.bo.CatAssetCode;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.asset.dto.CatAssetCodeDto;
import com.viettel.asset.dto.CatAssetCodeSearchDto;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.OrderInfo;
import com.viettel.ktts2.common.Page;
import com.viettel.ktts2.common.UString;

@Repository("catAssetCodeDao")
public class CatAssetCodeDao extends HibernateDao<CatAssetCode, Long> {

	@SuppressWarnings("unchecked")

	public List<CatAssetCode> search(CatAssetCodeSearchDto searchForm) {
		Criteria cri = createCriteriaForSearch(searchForm);
		OrderInfo order = searchForm.getOrderInfo();
		if (order != null) {
			if (UString.isEqual(order.getType(), OrderInfo.ASC)) {
				cri.addOrder(Order.asc(order.getAttribute()));
			} else {
				cri.addOrder(Order.desc(order.getAttribute()));
			}
		}
		return cri.list();
	}

	@SuppressWarnings("unchecked")

	public Page<CatAssetCode> searchPaginage(CatAssetCodeSearchDto searchForm) {
		Criteria cri = createCriteriaForSearch(searchForm);
		OrderInfo order = searchForm.getOrderInfo();
		if (order != null) {
			if (UString.isEqual(order.getType(), OrderInfo.ASC)) {
				cri.addOrder(Order.asc(order.getAttribute()));
			} else {
				cri.addOrder(Order.desc(order.getAttribute()));
			}
		}
		cri.setMaxResults(searchForm.getSize());
		cri.setFirstResult(searchForm.getSize() * searchForm.getPage());

		List<?> lst = cri.list();

		Page<CatAssetCode> page = new Page<>();
		page.setRows((List<CatAssetCode>) lst);
		page.setPage(searchForm.getPage());
		page.setSize(searchForm.getSize());

		cri = createCriteriaForSearch(searchForm);
		Integer totalResult = ((Number) cri.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		page.setTotalRow(totalResult);

		return page;
	}

	private Criteria createCriteriaForSearch(CatAssetCodeSearchDto searchForm) {
		Criteria cri = currentSession().createCriteria(CatAssetCode.class);
		if (searchForm.getCaacLevel() != null) {
			cri.add(Restrictions.eq(CatAssetCode.Columns.CAAC_LEVEL, searchForm.getCaacLevel()));
		}
		if (searchForm.getCaacParentId() != null) {
			cri.add(Restrictions.eq(CatAssetCode.Columns.CAAC_PARENT_ID, searchForm.getCaacParentId()));
		}
		if (UString.isNotNullAndWhitespace(searchForm.getQuickSearchQuery())) {
			String query = UString.trim(searchForm.getQuickSearchQuery());
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like(CatAssetCode.Columns.CAAC_CODE, query, MatchMode.START));
			disjunction.add(Restrictions.like(CatAssetCode.Columns.CAAC_FULL_CODE, query, MatchMode.START));
			disjunction.add(Restrictions.ilike(CatAssetCode.Columns.CAAC_NAME, query, MatchMode.ANYWHERE));
			cri.add(disjunction);
		}
		return cri;
	}

	public Integer getCurrentIndex(Long caacParentId) {
		Criteria cri = currentSession().createCriteria(CatAssetCode.class);
		if (caacParentId == null) {
			cri.add(Restrictions.eq(CatAssetCode.Columns.CAAC_LEVEL, CatAssetCode.Constants.CAAC_LEVEL_NHOM));
		} else {
			cri.add(Restrictions.eq(CatAssetCode.Columns.CAAC_PARENT_ID, caacParentId));
		}
		cri.setProjection(Projections.max(CatAssetCode.Columns.CAAC_INDEX));
		Object res = cri.uniqueResult();
		return res == null ? null : ((Number) res).intValue();
	}

	/**
	 * PhuongH1 - Tìm kiếm phục vụ autocomplete
	 * 
	 * @param searchForm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CatAssetCode> filterCatAssetCodeAutocomplete(CatAssetCodeSearchDto searchForm) {
		Criteria cri = currentSession().createCriteria(CatAssetCode.class);
		if (UString.isNotNullAndWhitespace(searchForm.getKeySearch())) {
			String keySearch = UString.trim(searchForm.getKeySearch());
			cri.add(Restrictions.ilike(CatAssetCode.Columns.CAAC_FULL_CODE, keySearch, MatchMode.START));
		}
		cri.add(Restrictions.eq(CatAssetCode.Columns.IS_ACTIVE, CatAssetCode.Constants.ACTIVE));
		if (searchForm.getCaacLevel() != null) {
			cri.add(Restrictions.eq(CatAssetCode.Columns.CAAC_LEVEL, searchForm.getCaacLevel()));
		}
		if (searchForm.getSuperParentId() != null) {
			CatAssetCode superParent = find(searchForm.getSuperParentId());
			if (superParent != null) {
				cri.add(Restrictions.ilike(CatAssetCode.Columns.CAAC_PATH, superParent.getCaacPath(), MatchMode.START));
			}
		}
		cri.setMaxResults(CatAssetCodeSearchDto.MAX_QUERY_AUTOCOMPLETE);
		cri.addOrder(Order.asc(CatAssetCode.Columns.CAAC_FULL_CODE));
		List<CatAssetCode> lst = cri.list();
		if (lst.size() < CatAssetCodeSearchDto.MAX_QUERY_AUTOCOMPLETE 
				&& UString.isNotNullAndWhitespace(searchForm.getKeySearch())) {
			cri = currentSession().createCriteria(CatAssetCode.class);
			
			if (UString.isNotNullAndWhitespace(searchForm.getKeySearch())) {
				
				String keySearch = UString.trim(searchForm.getKeySearch());
				cri.add(Restrictions
						.not(Restrictions.ilike(CatAssetCode.Columns.CAAC_FULL_CODE, keySearch, MatchMode.START)));
				Disjunction disjunction = Restrictions.disjunction();
				
				disjunction.add(Restrictions.ilike(CatAssetCode.Columns.CAAC_FULL_CODE, keySearch, MatchMode.ANYWHERE));
				disjunction.add(Restrictions.ilike(CatAssetCode.Columns.CAAC_NAME, keySearch, MatchMode.ANYWHERE));
				cri.add(disjunction);
			}
			cri.add(Restrictions.eq(CatAssetCode.Columns.IS_ACTIVE, CatAssetCode.Constants.ACTIVE));
			if (searchForm.getCaacLevel() != null) {
				cri.add(Restrictions.eq(CatAssetCode.Columns.CAAC_LEVEL, searchForm.getCaacLevel()));
			}
			if (searchForm.getSuperParentId() != null) {
				CatAssetCode superParent = find(searchForm.getSuperParentId());
				if (superParent != null) {
					cri.add(Restrictions.ilike(CatAssetCode.Columns.CAAC_PATH, superParent.getCaacPath(),
							MatchMode.START));
				}
			}
			cri.setMaxResults(CatAssetCodeSearchDto.MAX_QUERY_AUTOCOMPLETE - lst.size());
			cri.addOrder(Order.asc(CatAssetCode.Columns.CAAC_FULL_CODE));
			lst.addAll(cri.list());
		}
		return lst;
	}

	// filter tim kiem catCode
	@SuppressWarnings("unchecked")
	public List<CatAssetCode> filterCatAssetCodeType(AutocompleteSearchDto searchForm) {
		Criteria cri = currentSession().createCriteria(CatAssetCode.class);
		if (UString.isNotNullAndWhitespace(searchForm.getKeySearch())) {
			String keySearch = UString.trim(searchForm.getKeySearch()).toUpperCase();
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.ilike(CatAssetCode.Columns.CAAC_FULL_CODE, keySearch, MatchMode.ANYWHERE));
			cri.add(disjunction);
		}
		cri.add(Restrictions.eq(CatAssetCode.Columns.IS_ACTIVE, CatAssetCode.Constants.ACTIVE));
		cri.add(Restrictions.eq(CatAssetCode.Columns.CAAC_LEVEL, CatAssetCode.Constants.CAAC_LEVEL_LOAI));
		cri.setMaxResults(searchForm.getPageSize());
		cri.addOrder(Order.asc(CatAssetCode.Columns.CAAC_CODE));
		return cri.list();

	}
	
	public CatAssetCodeDto getDongTSByChildIdAndLevel(Long catAssetCodeId, Long caacLevel) {
		// TODO Auto-generated method stub
		String sql;
		if(caacLevel==4){
			 sql="Select dong.*  "
					+ " from  CAT_ASSET_CODES dong "
					+ " left join CAT_ASSET_CODES ten on ten.caac_Parent_Id = dong.cat_asset_code_id"
					+ " where ten.cat_asset_code_id=:catAssetCodeId and ten.caac_level=:caacLevel and ten.is_active=1 and dong.is_active=1";
		}else if(caacLevel==3){
			 sql="Select dong.* from CAT_ASSET_CODES dong "
						
						
						+ " where dong.cat_asset_code_id=:catAssetCodeId and nhom.caac_level=:caacLevel and dong.is_active=1";
		}else{
			throw new BusinessException("getNhomTSByChildIdAndLevel Khong ho tro tim kiem theo level=1,2");
		}
		SQLQuery query=currentSession().createSQLQuery(sql);
		query.addEntity(CatAssetCode.class);
		query.setParameter("caacLevel", caacLevel);
		query.setParameter("catAssetCodeId", catAssetCodeId);
		List<CatAssetCode>  lst=query.list();
		if(!lst.isEmpty()){
			return new CatAssetCodeDto(lst.get(0));
		}else{
			throw new BusinessException("getNhomTSByChildIdAndLevel khong tim thay nhom tai san voi, caacLevel="+caacLevel+", id="+catAssetCodeId);
		}
		
	}

	public CatAssetCodeDto getNhomTSByChildIdAndLevel(Long catAssetCodeId, Long caacLevel) {
		// TODO Auto-generated method stub
		String sql;
		if(caacLevel==4){
			 sql="Select nhom.* from CAT_ASSET_CODES nhom "
					+ " left join  CAT_ASSET_CODES dong on dong.caac_Parent_Id=nhom.cat_asset_code_id "
					+ " left join CAT_ASSET_CODES ten on ten.caac_Parent_Id = dong.cat_asset_code_id"
					+ " where ten.cat_asset_code_id=:catAssetCodeId and ten.caac_level=:caacLevel and nhom.is_active=1 and dong.is_active=1 and ten.is_active=1";
		}else if(caacLevel==3){
			 sql="Select nhom.* from CAT_ASSET_CODES nhom "
						+ " left join  CAT_ASSET_CODES dong on dong.caac_Parent_Id=nhom.cat_asset_code_id "
						
						+ " where dong.cat_asset_code_id=:catAssetCodeId and dong.caac_level=:caacLevel and nhom.is_active=1 and dong.is_active=1 ";
		}else if(caacLevel==2){
			 sql="Select nhom.* from CAT_ASSET_CODES nhom "
						
						
						+ " where nhom.cat_asset_code_id=:catAssetCodeId and nhom.caac_level=:caacLevel and nhom.is_active=1";
		}else{
			throw new BusinessException("getNhomTSByChildIdAndLevel Khong ho tro tim kiem theo level=1");
		}
		SQLQuery query=currentSession().createSQLQuery(sql);
		query.addEntity(CatAssetCode.class);
		query.setParameter("caacLevel", caacLevel);
		query.setParameter("catAssetCodeId", catAssetCodeId);
		List<CatAssetCode>  lst=query.list();
		if(!lst.isEmpty()){
			return new CatAssetCodeDto(lst.get(0));
		}else{
			throw new BusinessException("getNhomTSByChildIdAndLevel khong tim thay nhom tai san voi, caacLevel="+caacLevel+", id="+catAssetCodeId);
		}
		
	}

}
