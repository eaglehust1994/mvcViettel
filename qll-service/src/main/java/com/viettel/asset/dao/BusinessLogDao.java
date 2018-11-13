package com.viettel.asset.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.viettel.asset.bo.BusinessLog;
import com.viettel.asset.dto.BusinessLogSearchDto;
import com.viettel.ktts2.common.OrderInfo;
import com.viettel.ktts2.common.UString;

@Repository("businessLogDao")
public class BusinessLogDao extends HibernateDao<BusinessLog, Long> {
	
	@SuppressWarnings("unchecked")
	public List<BusinessLog> search(BusinessLogSearchDto dto) {
		Criteria cri = currentSession().createCriteria(BusinessLog.class);
		if (UString.isNotNullAndWhitespace(dto.getDbTable())) {
			cri.add(Restrictions.eq(BusinessLog.Columns.DB_TABLE, dto.getDbTable()));
		}
		if (dto.getFromCreatedDate() != null) {
			cri.add(Restrictions.gt(BusinessLog.Columns.CREATED_DATE, dto.getFromCreatedDate()));
		}
		if (dto.getOrderInfo() != null) {
			OrderInfo orderInfo = dto.getOrderInfo();
			if (UString.isNotNullAndWhitespace(orderInfo.getAttribute())) {
				if (OrderInfo.ASC.equalsIgnoreCase(orderInfo.getType())) {
					cri.addOrder(Order.asc(orderInfo.getAttribute()));
				} else {
					cri.addOrder(Order.desc(orderInfo.getAttribute()));
				}
			}
		}
		return cri.list();
	}
}
