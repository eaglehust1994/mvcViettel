package com.viettel.ims.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.Common.CommonBussiness.UserRoleBusinessImpl;
//import com.viettel.wms.business.*;
import com.viettel.ims.bo.WorkItemQuotaBO;
import com.viettel.ims.business.CatConstructionTypeBusinessImpl;
import com.viettel.ims.business.CatWorkItemTypeBusinessImpl;
import com.viettel.ims.business.SysGroupBusinessImpl;
import com.viettel.ims.business.WorkItemQuotaBusinessImpl;
import com.viettel.ims.dto.BiddingPackageDTO;
import com.viettel.ims.dto.CatConstructionTypeDTO;
import com.viettel.ims.dto.CatWorkItemTypeDTO;
import com.viettel.ims.dto.SysGroupDTO;
import com.viettel.ims.dto.WorkItemQuotaDTO;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.common.UFile;
import com.viettel.ktts2.common.UString;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.util.ParamUtils;
//import com.viettel.erp.constant.ApplicationConstants;
//import com.viettel.service.base.dto.ActionListDTO;
//import com.viettel.erp.utils.ExportExcel;
//import com.viettel.erp.utils.FilterUtilities;

/**
 * @author hailh10
 */
 
public class WorkItemQuotaRsServiceImpl implements WorkItemQuotaRsService {

	protected final Logger log = Logger.getLogger(WorkItemQuotaRsService.class);
	@Autowired
	WorkItemQuotaBusinessImpl workItemQuotaBusinessImpl;
	
	@Autowired
	CatConstructionTypeBusinessImpl catConstructionTypeBusinessImpl;
	
	@Autowired
	CatWorkItemTypeBusinessImpl catWorkItemTypeBusinessImpl;
	
	@Autowired
	SysGroupBusinessImpl sysGroupBusinessImpl;
	
	@Value("${folder_upload}")
	private String folder2Upload;
	
	@Value("${folder_upload2}")
	private String folderUpload;

	@Value("${folder_upload}")
	private String folderTemp;

	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;

	@Value("${allow.file.ext}")
	private String allowFileExt;
	@Value("${allow.folder.dir}")
	private String allowFolderDir;

	
	@Context HttpServletRequest request;
	@Autowired
	private UserRoleBusinessImpl userRoleBusinessImpl;
	
	@Override
	public Response doSearch(WorkItemQuotaDTO obj) {
		List<WorkItemQuotaDTO> ls = workItemQuotaBusinessImpl.doSearch(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			DataListDTO data = new DataListDTO();
			data.setData(ls);
			data.setTotal(obj.getTotalRecord());
			data.setSize(obj.getPageSize());
			data.setStart(1);
			return Response.ok(data).build();
		}
	}
	
	@Override
	public Response getById(Long id) {
		WorkItemQuotaDTO obj = (WorkItemQuotaDTO) workItemQuotaBusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response update(WorkItemQuotaDTO obj) {
		WorkItemQuotaDTO originObj = (WorkItemQuotaDTO) workItemQuotaBusinessImpl.getOneById(obj.getWorkItemQuotaId());
		
		if (originObj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
				
				if (!(obj.getCatConstructionTypeId()==originObj.getCatConstructionTypeId())
						|| !(obj.getCatWorkItemTypeId()==originObj.getCatWorkItemTypeId())
						|| !(obj.getSysGroupId()==originObj.getSysGroupId())) {
					WorkItemQuotaDTO check = workItemQuotaBusinessImpl.findByUniqueKey(obj);
					if (check != null) {
						return Response.status(Response.Status.CONFLICT).build();
					} else {
						return doUpdate(obj);
					}
				} else {
					return doUpdate(obj);
				}
			
		}

	}
	
	private Response doUpdate(WorkItemQuotaDTO obj) {
		obj.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
//		obj.setUpdatedby(workItemQuotaBusinessImpl.getSessionInfo().getUserId());
		
		Long id = workItemQuotaBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response add(WorkItemQuotaDTO obj) {
		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
		WorkItemQuotaDTO existing = (WorkItemQuotaDTO) workItemQuotaBusinessImpl.findByUniqueKey(obj);
		if (existing != null) {
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			obj.setCreatedUserId(objUser.getSysUserId());
			obj.setCreatedGroupId(objUser.getGroupId());
			obj.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			obj.setStatus(1l);
			
			Long id = workItemQuotaBusinessImpl.save(obj);
			obj.setWorkItemQuotaId(id);
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(obj).build();
			}
		}
	}

	@Override
	public Response delete(Long id) {
		WorkItemQuotaDTO obj = (WorkItemQuotaDTO) workItemQuotaBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setStatus(0L);
//			obj.setUpdated(new Timestamp(System.currentTimeMillis()));
			workItemQuotaBusinessImpl.update(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}
	
	@Override
	public Response deleteList(List<Long> ids){
		String result = workItemQuotaBusinessImpl.delete( ids, WorkItemQuotaBO.class.getName() ,"WORK_ITEM_QUOTA_ID");
		
		if(result ==  ParamUtils.SUCCESS ){
			 return Response.ok().build();
		} else {
			 return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
	}


	
	@Override
	public Response findByAutoComplete(WorkItemQuotaDTO obj) {
		List<WorkItemQuotaDTO> results = workItemQuotaBusinessImpl.getForAutoComplete(obj);
		if (obj.getIsSize()){
			WorkItemQuotaDTO moreObject = new WorkItemQuotaDTO();
			moreObject.setWorkItemQuotaId(0l);;
			
			
			results.add(moreObject);
		}
		return Response.ok(results).build();
	}

	@Override
	public Response delete(WorkItemQuotaDTO obj) {
		WorkItemQuotaDTO check = (WorkItemQuotaDTO) workItemQuotaBusinessImpl.getOneById(obj.getWorkItemQuotaId());
		if (check == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			check.setStatus(0L);
//			obj.setUpdated(new Timestamp(System.currentTimeMillis()));
			workItemQuotaBusinessImpl.update(check);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}
	
	
	
	@Override
	public Response downloadFile(HttpServletRequest request) throws Exception {
		String fileName = UEncrypt.decryptFileUploadPath(request.getQueryString());
		File file = new File(folderTemp + File.separatorChar + fileName);
		InputStream ExcelFileToRead = new FileInputStream(folder2Upload + File.separatorChar + fileName);
		if (!file.exists()) {
			file = new File(folderUpload + File.separatorChar + fileName);
			if (!file.exists()) {
//				logger.warn("File {} is not found", fileName);
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

		}
		
		XSSFWorkbook  wb = new XSSFWorkbook (ExcelFileToRead);
		XSSFSheet  sheet=wb.getSheetAt(1);
		
		//get data
		SysGroupDTO groupSearch = new SysGroupDTO();
		CatConstructionTypeDTO cstSearch = new CatConstructionTypeDTO();
		CatWorkItemTypeDTO wItemTypeSearch =  new CatWorkItemTypeDTO();
		List<SysGroupDTO> groupLst= sysGroupBusinessImpl.doSearch(groupSearch);
		List<CatConstructionTypeDTO> cstTypeLst = catConstructionTypeBusinessImpl.doSearch(cstSearch);
//		List<CatWorkItemTypeDTO> wItemTypeLst = catWorkItemTypeBusinessImpl.doSearch(wItemTypeSearch);
		//20180224_hoanm1_start
		List<CatWorkItemTypeDTO> wItemTypeLst = catWorkItemTypeBusinessImpl.doSearchExport(wItemTypeSearch);
		//20180224_hoanm1_end
		//write susGroup data to excel file
		int rowOrder = 1;
		for (SysGroupDTO sysGroupDTO : groupLst) {
			if(sysGroupDTO != null){
				XSSFRow row = sheet.createRow(rowOrder);
				XSSFCell  nameCell = row.createCell(0);
				nameCell.setCellValue(sysGroupDTO.getName());
				XSSFCell  idCell = row.createCell(1);
				idCell.setCellValue(sysGroupDTO.getCode());
				rowOrder++;
			}
		}
//		write ConstructionType data to excel file
		rowOrder = 1;
		for (CatConstructionTypeDTO catConstructionTypeDTO : cstTypeLst) {
			if(catConstructionTypeDTO != null){
				XSSFRow row = sheet.getRow(rowOrder);
				if(row == null){
					row = sheet.createRow(rowOrder);
				}
				XSSFCell nameCell = row.createCell(3);
				nameCell.setCellValue(catConstructionTypeDTO.getName());
				XSSFCell idCell = row.createCell(4);
				idCell.setCellValue(catConstructionTypeDTO.getCode());
				rowOrder++;
			}
		}
//		write orkItemType data to excel file
		rowOrder = 1;
		for (CatWorkItemTypeDTO catWorkItemTypeDTO : wItemTypeLst) {
			if(catWorkItemTypeDTO != null){
				XSSFRow row = sheet.getRow(rowOrder);
				if(row == null){
					row = sheet.createRow(rowOrder);
				}
				XSSFCell nameCell = row.createCell(6);
				nameCell.setCellValue(catWorkItemTypeDTO.getName());
				XSSFCell idCell = row.createCell(7);
				idCell.setCellValue(catWorkItemTypeDTO.getCode());
				rowOrder++;
			}
		}
		FileOutputStream fileOut = new FileOutputStream(folder2Upload + File.separatorChar + fileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		
		int lastIndex = fileName.lastIndexOf(File.separatorChar);
		String fileNameReturn = fileName.substring(lastIndex + 1);

		return Response.ok((Object) file)
				.header("Content-Disposition", "attachment; filename=\"" + fileNameReturn + "\"").build();
	}
	
	@Override
	public Response importWorkItemQuota(Attachment attachments,
			HttpServletRequest request) throws Exception {
		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
		Long quotaType = Long.parseLong(UString.getSafeFileName(request.getParameter("quotaType")));
		String filePathReturn;
		String filePath;
		
		if (UString.isNullOrWhitespace(folderParam)) {
			folderParam = defaultSubFolderUpload;
		} else {
			if (!isFolderAllowFolderSave(folderParam)) {
				throw new BusinessException("folder khong nam trong white list: folderParam=" + folderParam);
			}
		}

		DataHandler dataHandler = attachments.getDataHandler();

		// get filename to be uploaded
		MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
		String fileName = UFile.getFileName(multivaluedMap);

		if (!isExtendAllowSave(fileName)) {
			throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
		}
		// write & upload file to server
		try (InputStream inputStream = dataHandler.getInputStream();) {
			filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
			filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
		} catch (Exception ex) {
			throw new BusinessException("Loi khi save file", ex);
		}

		try {

			try {
				java.util.List<WorkItemQuotaDTO> result = workItemQuotaBusinessImpl.importWorkItemQuota(folderUpload + filePath, quotaType);
				if(result != null && !result.isEmpty()){
						int rowNum = 1;
						for (WorkItemQuotaDTO obj : result) {
							if(obj != null){
								WorkItemQuotaDTO existing = (WorkItemQuotaDTO) workItemQuotaBusinessImpl.findByUniqueKey(obj);
								if (existing != null) {
									return Response.status(Response.Status.CONFLICT).build();
								} 
								obj.setCreatedUserId(objUser.getSysUserId());
								obj.setQuotaType(quotaType);
								obj.setStatus(1L);
								obj.setCreatedDate(new Timestamp(System.currentTimeMillis()));
								workItemQuotaBusinessImpl.save(obj);
								rowNum++;
							}
						}
					return Response.ok(result).build();
				}
				else{
					return Response.ok(null).build();
				}
			} catch (Exception e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}

		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	
	private boolean isFolderAllowFolderSave(String folderDir) {
		return UString.isFolderAllowFolderSave(folderDir, allowFolderDir);

	}
	
	private boolean isExtendAllowSave(String fileName) {
		return UString.isExtendAllowSave(fileName, allowFileExt);
	}


}
