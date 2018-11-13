package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.qll.bo.TblUsersBO;
import com.viettel.qll.dao.TblUsersDAO;
import com.viettel.qll.dto.TblUsersDTO;
import com.viettel.qll.dto.checkRole;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("tblUsersBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblUsersBusinessImpl extends BaseFWBusinessImpl<TblUsersDAO, TblUsersDTO, TblUsersBO>
		implements TblUsersBusiness {

	protected final Logger log = Logger.getLogger(TblUsersBusiness.class);
	@Autowired
	private TblUsersDAO tblUsersDAO;

	public TblUsersBusinessImpl() {
		tModel = new TblUsersBO();
		tDAO = tblUsersDAO;
	}

	// public List<TblUsersDTO> getUserRoles(String code) {
	//
	// return tblUsersDAO.getUserRoles(code);
	// }

	@Override
	public List<TblUsersDTO> getForAutoCompleteNhanVien(TblUsersDTO obj) {
		return tblUsersDAO.getForAutoCompleteNV(obj);
	}
	
	@Override
	public List<TblUsersDTO> getForAutoCompleteNhanVienPtk(TblUsersDTO obj) {
		return tblUsersDAO.getForAutoCompleteNVPtk(obj);
	}
	@Override
	public List<TblUsersDTO> getRoles(TblUsersDTO obj) {
		return tblUsersDAO.getRoles(obj);
	}

	@Override
	public DataListDTO doSearch(TblUsersDTO obj, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String err = "";

		List<TblUsersDTO> ls = tblUsersDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public static boolean contains(List<TblUsersDTO> lst1, String item) {
		for (TblUsersDTO dto2 : lst1) {
			if (item.equals(dto2.getRoleCode())) {
				return true;
			}
		}
		return false;
	}

	public TblUsersDTO checkRole(String code) {
		log.info("userCode =:" + code);
		TblUsersDTO tblUsersDTO = new TblUsersDTO();
		checkRole checkRole = new checkRole();
		List<TblUsersDTO> lst = tblUsersDAO.getUserRoles(code);
		for (TblUsersDTO dto : lst) {
			if (dto.getRoleCode().equals("10")) {
				checkRole.setCheckRoleImport(true);
				checkRole.setCheckRoLeFullQuyenAll(true);
				checkRole.setCheckRoleGanPXK(true);
				checkRole.setCheckRoleGanPXKChoNV(true);
				checkRole.setCheckRoleNhapLieu(true);
				checkRole.setCheckRoleXacNhanThongTinftXuatBC(true);
				checkRole.setCheckRoleFullQuyenQLTS(true);
				checkRole.setCheckRoleFullQuyenQLL(true);
				checkRole.setCheckRoleQLTS(true);
				checkRole.setCheckRoLeQLL(true);
				checkRole.setCheckRoleSidebar1(true);
				checkRole.setCheckRoleSidebar2(true);
				checkRole.setCheckBCPTK(true);
				checkRole.setCheckNhapLieuDN(true);
				checkRole.setCheckThamDinhDN(true);
				checkRole.setCheckTPPTP(true);
				checkRole.setCheckPKH(true);
				checkRole.setCheckPTCLD(true);
				checkRole.setCheckPTK(true);
				break;
			}else {
				if (dto.getRoleCode().equals("1")) {
					checkRole.setCheckRoleImport(true);
				}

				if (dto.getRoleCode().equals("2")) {
					checkRole.setCheckRoleGanPXK(true);
				}

				if (dto.getRoleCode().equals("3")) {
					checkRole.setCheckRoleGanPXKChoNV(true);
				}

				if (dto.getRoleCode().equals("4")) {
					checkRole.setCheckRoleNhapLieu(true);
				}
				if (dto.getRoleCode().equals("5")) {
					checkRole.setCheckRoleXacNhanThongTinftXuatBC(true);
				}
				if (dto.getRoleCode().equals("6")) {
					checkRole.setCheckRoleFullQuyenQLTS(true);
				}
				
				 if (dto.getRoleCode().equals("7")) {
				 checkRole.setCheckRoleQLTS(true);
				 }
				 //
				 if (dto.getRoleCode().equals("8")) {
				 checkRole.setCheckRoLeQLL(true);
				 }
				
				if (dto.getRoleCode().equals("9")) {
					checkRole.setCheckRoleFullQuyenQLL(true);
				}
				//
				if (dto.getRoleCode().equals("10")) {
					checkRole.setCheckRoLeFullQuyenAll(true);
				}
				if(dto.getRoleCode().equals("11")){
					checkRole.setCheckBCPTK(true);
				}
				if (dto.getRoleCode().equals("12")) {
					checkRole.setCheckNhapLieuDN(true);
				}
				if (dto.getRoleCode().equals("13")) {
					checkRole.setCheckThamDinhDN(true);
					checkRole.setCheckPTK(true);
				}
				if (dto.getRoleCode().equals("14")) {
					checkRole.setCheckThamDinhDN(true);
					checkRole.setCheckTPPTP(true);
					checkRole.setCheckPTK(true);
				}
				if (dto.getRoleCode().equals("15")) {
					checkRole.setCheckPGD(true);
				}
				if(dto.getRoleCode().equals("16")) {
					checkRole.setCheckPKH(true);
				}
				if(dto.getRoleCode().equals("17")){
					checkRole.setCheckPTCLD(true);
				}
			}
			if (dto.getRoleCode().equals("9")) {
				checkRole.setCheckRoleImport(true);
				checkRole.setCheckRoleGanPXK(true);
				checkRole.setCheckRoleGanPXKChoNV(true);
				checkRole.setCheckRoleNhapLieu(true);
				checkRole.setCheckRoleXacNhanThongTinftXuatBC(true);
				checkRole.setCheckRoleFullQuyenQLL(true);
				// checkRole.setCheckRoleQLTS(true);
				checkRole.setCheckRoLeQLL(true);
				checkRole.setCheckRoleSidebar1(true);
				checkRole.setCheckRoleSidebar2(true);
//				break;
			} 
			if (dto.getRoleCode().equals("6")) {
				checkRole.setCheckRoleImport(true);
				checkRole.setCheckRoleGanPXK(true);
				checkRole.setCheckRoleGanPXKChoNV(true);
				checkRole.setCheckRoleNhapLieu(true);
				checkRole.setCheckRoleXacNhanThongTinftXuatBC(true);
//				checkRole.setCheckRoleFullQuyenQLL(true);
				checkRole.setCheckRoleFullQuyenQLTS(true);
				checkRole.setCheckRoleQLTS(true);
				// checkRole.setCheckRoLeQLL(true);
				checkRole.setCheckRoleSidebar1(true);
				checkRole.setCheckRoleSidebar2(true);
//				break;
			} 
		}
		tblUsersDTO.setCheckRole(checkRole);
		tblUsersDTO.setLstUser(lst);

		return tblUsersDTO;
	}

}
