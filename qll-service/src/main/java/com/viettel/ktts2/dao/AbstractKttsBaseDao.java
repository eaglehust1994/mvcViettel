package com.viettel.ktts2.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.viettel.service.base.dao.BaseFWDAOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;
import com.viettel.util.ParamUtils;

public class AbstractKttsBaseDao<T extends BaseFWModelImpl, ID extends Serializable> extends BaseFWDAOImpl<T,ID> {
	
	/**
	 * Hanhls1
	 * Xóa list  bản ghi: tableName trong danh sách table -> truyền vào phải từ whitelisst
	 * tablePrimariKey 
	 */
	@Override	
	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ");
		sql.append(tableName);
		sql.append(" SET ");
		sql.append("IS_ACTIVE = 0 ");
		sql.append("WHERE ");
		sql.append(tablePrimaryKey);
		sql.append(" IN (");
		for (int i = 0; i < ids.size(); i++) {
			sql.append(ids.get(i));
			if (i != ids.size() - 1) {
				sql.append(",");
			}
		}
		sql.append(")");

		Query query = getSession().createQuery(sql.toString());
		query.executeUpdate();
		return ParamUtils.SUCCESS;
	}
	
}
