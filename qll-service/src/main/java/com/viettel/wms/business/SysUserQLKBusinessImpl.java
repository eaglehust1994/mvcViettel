package com.viettel.wms.business;

import com.viettel.wms.bo.SysUserQLKBO;
import com.viettel.wms.dao.SysUserQLKDAO;
//import com.viettel.wms.dto.GoodsDTO;
import com.viettel.wms.dto.OrderDTO;
import com.viettel.wms.dto.StockDTO;
import com.viettel.wms.dto.SysUserQLKDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("sysUserwmsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SysUserQLKBusinessImpl extends BaseFWBusinessImpl<SysUserQLKDAO, SysUserQLKDTO, SysUserQLKBO>
		implements SysUserQLKBusiness {

	@Autowired
	private SysUserQLKDAO sysUserQLKDAO;

	public SysUserQLKBusinessImpl() {
		tModel = new SysUserQLKBO();
		tDAO = sysUserQLKDAO;
	}

	@Override
	public SysUserQLKDAO gettDAO() {
		return sysUserQLKDAO;
	}

	@Override
	public long count() {
		return sysUserQLKDAO.count("SysUserwmsBO", null);
	}

	public List<SysUserQLKDTO> getForAutoComplete(SysUserQLKDTO obj) {
		return sysUserQLKDAO.getForAutoComplete(obj);
	}

	public DataListDTO doSearchUserInPopup(SysUserQLKDTO obj) {
		List<SysUserQLKDTO> ls = sysUserQLKDAO.doSearchUserInPopup(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getPageSize());
		data.setStart(1);
		return data;
	}

	public SysUserQLKDTO getUserInfoByLoginName(String loginName) {
		/* return sysUserQLKDAO.getUserInfoByLoginName(loginName); */
		return null;
	}
}
