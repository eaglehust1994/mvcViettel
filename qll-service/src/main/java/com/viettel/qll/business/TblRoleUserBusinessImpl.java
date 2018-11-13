package com.viettel.qll.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.qll.bo.TblRoleUserBO;
import com.viettel.qll.dao.TblRoleUserDAO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.qll.dto.QllBaseDTO;
import com.viettel.qll.dto.TblRoleUserDTO;
import com.viettel.qll.dto.TblUsersDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

@Service("tblRoleUserBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblRoleUserBusinessImpl extends BaseFWBusinessImpl<TblRoleUserDAO, TblRoleUserDTO, TblRoleUserBO>
		implements TblRoleUserBusiness {

	@Autowired
	private TblRoleUserDAO tblRoleUserDAO;
	
	LsThaoTacDTO lsThaoTacDTO=new LsThaoTacDTO();
	@Autowired
	TblQltsThucXuatTheoKyBusinessImpl businessImpl=new TblQltsThucXuatTheoKyBusinessImpl();

	public TblRoleUserBusinessImpl() {
		tModel = new TblRoleUserBO();
		tDAO = tblRoleUserDAO;
	}

	@Override
	@Transactional
	public String insertRoles(TblRoleUserDTO lst, HttpServletRequest request) throws Exception {
		try {
			List<String> lsrUserCode=new ArrayList<>();
			for (int i = 0; i < lst.getLstRolesUser().size(); i++) {
				lst.getLstRolesUser().get(i).setTblRolesId(lst.getTblRolesId());
				lsrUserCode.add(lst.getLstRolesUser().get(i).getTblUsersId().toString());
				if (tblRoleUserDAO.checkExist(lst.getLstRolesUser().get(i)).size() > 0) {
					tblRoleUserDAO.deleteObj(lst.getLstRolesUser().get(i));
				}
				
			}
			if (lst.getLstRolesUser().size() > 0) {
				lsThaoTacDTO.setChucNang("Thêm quyền cho tài khoản");
				lsThaoTacDTO.setMoTa("Thêm quyền cho danh sách id tài khoản :"+Arrays.toString(lsrUserCode.toArray()));
				businessImpl.insertLSTT(lsThaoTacDTO,request);
				return saveList1(lst.getLstRolesUser());
			} else {
				throw new IllegalArgumentException();
			}

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Danh sách thêm quyền trống hoặc đã tồn tại!");
		}

	}
	
	@Override
	@Transactional
	public long insertRoles8() throws Exception {
		try {
			
//			List<TblRoleUserDTO> lst=tblRoleUserDAO.getAllData();
			List<TblUsersDTO> lst=tblRoleUserDAO.getAllData11();
			
			List<TblRoleUserDTO> lst1=new ArrayList<>();
			for(TblUsersDTO dto:lst){
				TblRoleUserDTO dto2=new TblRoleUserDTO();
				dto2.setTblUsersId(dto.getTblUsersId());
				dto2.setTblRolesId((long)3);
//				dto2.setTblRolesId((long)7);
				lst1.add(dto2);
			}
			saveList1(lst1);
			return 1l;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Danh sách thêm quyền trống hoặc đã tồn tại!");
		}

	}
	

	@SuppressWarnings("unchecked")
	public String saveList1(List<TblRoleUserDTO> lstCenterBO) {
		@SuppressWarnings("rawtypes")
		List lstModel = new ArrayList<>();
		if (lstCenterBO != null) {
			for (int i = 0; i < lstCenterBO.size(); i++) {
				lstModel.add(((QllBaseDTO<TblRoleUserBO>) lstCenterBO.get(i)).toModel());
			}
		}
		return tblRoleUserDAO.saveList1(lstModel);
	}
}
