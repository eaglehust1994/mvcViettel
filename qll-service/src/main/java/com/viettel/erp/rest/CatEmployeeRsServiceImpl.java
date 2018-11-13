/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.erp.business.CatEmployeeBusinessImpl;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.erp.dto.TotalNumDTO;
import com.viettel.erp.utils.Folder;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class CatEmployeeRsServiceImpl implements CatEmployeeRsService {

	@Autowired
	CatEmployeeBusinessImpl catEmployeeBusinessImpl;

	@Autowired
	UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;

	@Value("${completionPartner.attachType}")
	private Long attachType;

	@Value("${completionPartner.folder}")
	private String folder;

	@Value("${folder_upload}")
	private String folderUpload;

	@Value("${completionEstimateAB.folder}")
	private String folderEstimate;

	////////////////////// Config role into
	////////////////////// config.properties////////////////////////
	////////// RoleID 109////////////////
	@Value("${employee_roleID_TVGS_GSTC}")
	private String employee_roleID_TVGS_GSTC;

	////////// RoleID 106////////////////
	@Value("${employee_roleID_DT_PTTC}")
	private String employee_roleID_DT_PTTC;

	////////// RoleID 103////////////////
	@Value("${employee_roleID_CDT_DDPN}")
	private String employee_roleID_CDT_DDPN;

	////////// RoleID 105////////////////
	@Value("${employee_roleID_DT_GDNT}")
	private String employee_roleID_DT_GDNT;

	////////// RoleID 102////////////////
	@Value("${employee_roleID_CDT_PTGST}")
	private String employee_roleID_CDT_PTGST;

	////////// RoleID 104////////////////
	@Value("${employee_roleID_DT_KTTC}")
	private String employee_roleID_DT_KTTC;

	////////// RoleID 107////////////////
	@Value("${employee_roleID_TVTK_DDTV}")
	private String employee_roleID_TVTK_DDTV;

	////////// RoleID 108////////////////
	@Value("${employee_roleID_TVTK_CNTK}")
	private String employee_roleID_TVTK_CNTK;

	////////// RoleID 110////////////////
	@Value("${employee_roleID_TVGS_PTGST}")
	private String employee_roleID_TVGS_PTGST;

	////////// RoleID 111////////////////
	@Value("${employee_roleID_TVGS_DDTVGS}")
	private String employee_roleID_TVGS_DDTVGS;

	////////// RoleID 101////////////////
	@Value("${employee_roleID_CDT_GSTC}")
	private String employee_roleID_CDT_GSTC;
	
	//////////RoleID 112////////////////
	@Value("${employee_roleID_CDT_DDDVSDCT}")
	private String employee_roleID_CDT_DDDVSDCT;

	///////////////////// END///////////////////////////////////////////////////////
	/*
	 * @Autowired CatEmployeeBusinessImpl catEmployeeBusinessImpl;
	 */

	//////////////////////// Add roleId////////////////////////////////////////
	@Override
	public Response getRoleId() {
		List<String> ls = new ArrayList<String>();
		ls.add(employee_roleID_TVGS_GSTC); // 0 - RoleID 109
		ls.add(employee_roleID_DT_PTTC); // 1 - RoleID 106
		ls.add(employee_roleID_CDT_DDPN); // 2 - RoleID 103
		ls.add(employee_roleID_DT_GDNT); // 3 - RoleID 105
		ls.add(employee_roleID_CDT_PTGST); // 4 - RoleID 102
		ls.add(employee_roleID_DT_KTTC); // 5 - RoleID 104
		ls.add(employee_roleID_TVTK_DDTV); // 6 - RoleID 107
		ls.add(employee_roleID_TVTK_CNTK); // 7 - RoleID 108
		ls.add(employee_roleID_TVGS_PTGST); // 8 - RoleID 110
		ls.add(employee_roleID_TVGS_DDTVGS);// 9 - RoleID 111
		ls.add(employee_roleID_CDT_GSTC); // 10 - RoleID 101
		ls.add(employee_roleID_CDT_DDDVSDCT); // 11 - RoleID 112
		return Response.ok(ls).build();
	}

	@Override
	public Response getRoleID(Long catEmployeeId) {
		CatEmployeeDTO obj = (CatEmployeeDTO) catEmployeeBusinessImpl.getRoleID(catEmployeeId);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	// tungpv
	@Override
	public Response getEmployeeIdByUserId(Long userId) {
		CatEmployeeDTO obj = (CatEmployeeDTO) catEmployeeBusinessImpl.getEmployeeIdByUserId(userId);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response getAutoData(CatEmployeeDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getAutoData(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getListEmployee(CatEmployeeDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getListEmployee(obj);
		if (ls == null || "".equals(ls)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getEmployeeNameRole(SettlementRightDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getEmployeeNameRole(obj);
		if (ls == null || "".equals(ls)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getEmployeeByRole(CatEmployeeDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getEmployeeByRole(obj);
		if (ls == null || "".equals(ls)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getCatEmployee() {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getAll();
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getCatEmployeeById(String id) {
		CatEmployeeDTO obj = (CatEmployeeDTO) catEmployeeBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateCatEmployee(CatEmployeeDTO obj) throws Exception {
		Long id;
		if (catEmployeeBusinessImpl.checkUpdateCMT(obj.getIdentifyNumber(), obj.getId())) {
			id = catEmployeeBusinessImpl.update(obj);
			//String documentPath = FileServiceImpl.fileUploadPath.substring(folderUpload.length() + 1);
			//obj.getUtilAttachedDocuments().setDocumentPath(documentPath);
			// System.out.println("update documentPath = " + documentPath);
			// System.out.println("update documentName = " +
			// FileServiceImpl.fileUploadName);
       		obj.getUtilAttachedDocuments().setDocumentPath(UEncrypt.decryptFileUploadPath(obj.getUtilAttachedDocuments().getDocumentPath()));
    		
			utilAttachedDocumentsBusinessImpl.updateUtilByParentIdAndType(obj.getUtilAttachedDocuments().getDocumentName(),
					Long.valueOf(obj.getId()), obj.getUtilAttachedDocuments().getDocumentPath(), attachType);
		} else {
			return Response.status(Response.Status.CONFLICT).build();
		}

		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addCatEmployee(CatEmployeeDTO obj) throws Exception{
		String id = catEmployeeBusinessImpl.saveStrId(obj);
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).entity(id).build();
		}
	}

	@Override
	public Response deleteCatEmployee(String id) {
		CatEmployeeDTO obj = (CatEmployeeDTO) catEmployeeBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setIsActive(0l);
			System.out.println(obj.getIsActive());
			catEmployeeBusinessImpl.update(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	// truong
	@Override
	public Response getListEmployeeByRole(SettlementRightDTO rightDTO) {
		List<CatEmployeeDTO> obj = catEmployeeBusinessImpl.getListEmployeeByRole(rightDTO);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response getEmployeeNameAndIdByRole(SettlementRightDTO rightDTO) {
		List<CatEmployeeDTO> obj = catEmployeeBusinessImpl.getEmployeeNameAndIdByRole(rightDTO);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response getListEmployeeByConstruction(Long constructId) {
		List<SettlementRightDTO> obj = catEmployeeBusinessImpl.getListEmployeeByConstruction(constructId);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	public Response getListEmployeeByCurrent(SettlementRightDTO rightDTO) {
		List<CatEmployeeDTO> obj = catEmployeeBusinessImpl.getListEmployeeByCurrent(rightDTO);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response doSearch(CatEmployeeDTO obj) throws Exception {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.doSearch(obj);
		if (ls.size() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			/*for(int i = 0; i<ls.size();i++){
				ls.get(i).setDocumentPath(UEncrypt.encryptFileUploadPath(ls.get(i).getDocumentPath()));
			}*/
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response doSearchEmployeeViettel(CatEmployeeDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.doSearchEmployeeViettel(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	// ChuongNV
	@Override
	public Response getEmployeeByListID(String stringEmployee) {
		List<CatEmployeeDTO> listObj = catEmployeeBusinessImpl.getEmployeeByListID(stringEmployee);
		if (listObj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(listObj).build();
		}
	}
	// End ChuongNV

	public Response getAllEmployee(CatEmployeeDTO dto) {
		TotalNumDTO totalnum = new TotalNumDTO(0);
		
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getAllEmployee(dto,totalnum);

		//String queryCount_ = vConstructionHcqtBusinessImpl.queryCount();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
           
            DataListDTO data = new DataListDTO();
            data.setData(ls);
            data.setTotal(totalnum.getNum());
            data.setSize(totalnum.getNum());
            data.setStart(1);
            return Response.ok(data).build();
        }
		
	}

	@Override
	public Response deleteListPartnerHr(List<String> ids) {
		boolean isDeleteList = false;
		for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			CatEmployeeDTO obj = (CatEmployeeDTO) catEmployeeBusinessImpl.getOneById(id);
			if (obj == null) {
				isDeleteList = false;
			} else {
				isDeleteList = true;
				obj.setIsActive((long) 0);
				catEmployeeBusinessImpl.update(obj);
			}
		}
		if (!isDeleteList) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response getFolder() {
		return Response.ok().entity(java.util.Collections.singletonMap("folder", folder)).build();
	}

	@Override
	public Response getListMonitorChangeConstruct(CatEmployeeDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getListMonitorChangeConstruct(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getFolderEstimate() {
		return Response.ok().entity(java.util.Collections.singletonMap("folder", folderEstimate)).build();
	}

	@Override
	public Response getListMonitorChangeConstructId(CatEmployeeDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getListMonitorChangeConstruct(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getAutoDataEmail(CatEmployeeDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getAutoDataEmail(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getAutoDataUnit(CatEmployeeDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getAutoDataUnit(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getAutoDataPartner(CatEmployeeDTO obj) {
		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.getAutoDataPartner(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response DoSearch(CatEmployeeDTO obj) {

		TotalNumDTO totalnum = new TotalNumDTO(0);

		List<CatEmployeeDTO> ls = catEmployeeBusinessImpl.DoSearch(obj, totalnum);

		// String queryCount_ = vConstructionHcqtBusinessImpl.queryCount();
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {

			DataListDTO data = new DataListDTO();
			data.setData(ls);
			data.setTotal(totalnum.getNum());
			data.setSize(totalnum.getNum());
			data.setStart(1);
			return Response.ok(data).build();
		}

	}
}
