package com.viettel.qll.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.qll.bo.LsThaoTacBO;
import com.viettel.qll.dao.LsThaoTacDAO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
import com.viettel.qll.dto.VpsUserToken;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("lsThaoTacBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LsThaoTacBusinessImpl extends BaseFWBusinessImpl<LsThaoTacDAO, LsThaoTacDTO, LsThaoTacBO>
		implements LsThaoTacBusiness {

	@Autowired
	private LsThaoTacDAO lsThaoTacDAO;
	
	protected static Logger log = Logger.getLogger(LsThaoTacBusinessImpl.class);

	@Context
	HttpServletRequest request;

	public LsThaoTacBusinessImpl() {
		tModel = new LsThaoTacBO();
		tDAO = lsThaoTacDAO;
	}

	public long insertLSTT(LsThaoTacDTO obj, HttpServletRequest request) {
		VpsUserToken token = (VpsUserToken) request.getSession().getAttribute("vpsUserToken");
		String ip = (String) request.getSession().getAttribute("VTS-IP");
		
		try {
			obj.setNgayThaoTac(new Date());
			obj.setIpAdd(ip);
			obj.setFullName(token.getFullName());
			obj.setUserCode(token.getUserName());
			long ids=lsThaoTacDAO.save1(obj);
			return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Xảy ra lỗi khi thêm mới lịch sử đăng nhập!!!");
		}catch (NullPointerException ex) {
				throw new IllegalArgumentException("không có session!!!");
			}
	}
	
	@Override
	public DataListDTO doSearch(LsThaoTacDTO obj) throws Exception {
		List<LsThaoTacDTO> ls = lsThaoTacDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	// @SuppressWarnings("unchecked")
	// public String saveList1(List<LsThaoTacDTO> lstCenterBO) {
	// @SuppressWarnings("rawtypes")
	// List lstModel = new ArrayList<>();
	// if (lstCenterBO != null) {
	// for (int i = 0; i < lstCenterBO.size(); i++) {
	// lstModel.add(((QllBaseDTO<LsThaoTacBO>) lstCenterBO.get(i)).toModel());
	// }
	// }
	// return lsThaoTacDAO.saveList1(lstModel);
	// }

//	@Transactional
//	public long save1(LsThaoTacDTO obj) {
//		try {
//			getSession().save(obj);
//			return 1l;
//		} catch (HibernateException he) {
//			log.error(he.getMessage(), he);
//		}
//		return 0l;
//	}

}
