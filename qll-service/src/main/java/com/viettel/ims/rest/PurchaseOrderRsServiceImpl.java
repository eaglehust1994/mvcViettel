package com.viettel.ims.rest;

import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.viettel.Common.CommonBussiness.UserRoleBusinessImpl;
import com.viettel.ims.bo.PurchaseOrderBO;
import com.viettel.ims.business.CatPartnerBusinessImpl;
import com.viettel.ims.business.PurchaseOrderBusinessImpl;
import com.viettel.ims.business.SysGroupBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.ims.dto.CatConstructionTypeDTO;
import com.viettel.ims.dto.CatPartnerDTO;
import com.viettel.ims.dto.CatWorkItemTypeDTO;
import com.viettel.ims.dto.PurchaseOrderDTO;
import com.viettel.ims.dto.SysGroupDTO;
import com.viettel.ims.dto.WorkItemQuotaDTO;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.common.UFile;
import com.viettel.ktts2.common.UString;
import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.ims.constant.ApplicationConstants;
//import com.viettel.service.base.dto.ActionListDTO;
//import com.viettel.utils.ExportExcel;
//import com.viettel.utils.FilterUtilities;
import com.viettel.service.base.utils.DateUtil;
import com.viettel.util.ParamUtils;

/**
 * @author hailh10
 */
 
public class PurchaseOrderRsServiceImpl implements PurchaseOrderRsService {

	protected final Logger log = Logger.getLogger(PurchaseOrderRsService.class);
	@Autowired
	PurchaseOrderBusinessImpl purchaseOrderBusinessImpl;
	
	@Autowired
	SysGroupBusinessImpl sysGroupBusinessImpl;
	
	@Autowired
	CatPartnerBusinessImpl catPartnerBusinessImpl;
	
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
	public Response doSearch(PurchaseOrderDTO obj) {
		List<PurchaseOrderDTO> ls = purchaseOrderBusinessImpl.doSearch(obj);
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
		PurchaseOrderDTO obj = (PurchaseOrderDTO) purchaseOrderBusinessImpl.getById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response update(PurchaseOrderDTO obj) {
		PurchaseOrderDTO originObj = (PurchaseOrderDTO) purchaseOrderBusinessImpl.getOneById(obj.getPurchaseOrderId());
		
		if (originObj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			
				if (!obj.getCode().equalsIgnoreCase(originObj.getCode())) {
					obj.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
					PurchaseOrderDTO check = purchaseOrderBusinessImpl.findByCode(obj.getCode());
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
	
	private Response doUpdate(PurchaseOrderDTO obj) {
		obj.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
//		obj.setUpdatedby(purchaseOrderBusinessImpl.getSessionInfo().getUserId());
		
		Long id = purchaseOrderBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response add(PurchaseOrderDTO obj) {
		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
		PurchaseOrderDTO existing = (PurchaseOrderDTO) purchaseOrderBusinessImpl.findByCode(obj.getCode());
		if (existing != null) {
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			obj.setCreatedUserId(objUser.getSysUserId());
			obj.setCreatedGroupId(objUser.getGroupId());
			obj.setStatus(1L);
	
			obj.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			Long id = purchaseOrderBusinessImpl.save(obj);
			obj.setPurchaseOrderId(id);
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(obj).build();
			}
		}
	}

	@Override
	public Response delete(Long id) {
		PurchaseOrderDTO obj = (PurchaseOrderDTO) purchaseOrderBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			obj.setStatus(0L);
			purchaseOrderBusinessImpl.update(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}
	
	@Override
	public Response deleteList(List<Long> ids){
		String result = purchaseOrderBusinessImpl.delete( ids, PurchaseOrderBO.class.getName() ,"PURCHASE_ORDER_ID");
		
		if(result ==  ParamUtils.SUCCESS ){
			 return Response.ok().build();
		} else {
			 return Response.status(Response.Status.EXPECTATION_FAILED).build();
		}
	}

	
	@Override
	public Response getForAutoComplete(PurchaseOrderDTO obj) {
		List<PurchaseOrderDTO> results = purchaseOrderBusinessImpl.getForAutoComplete(obj);
		return Response.ok(results).build();
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
		CatPartnerDTO cstSearch = new CatPartnerDTO();
		CatWorkItemTypeDTO wItemTypeSearch =  new CatWorkItemTypeDTO();
		List<SysGroupDTO> groupLst= sysGroupBusinessImpl.doSearch(groupSearch);
		List<CatPartnerDTO> partnerLst = catPartnerBusinessImpl.doSearch(cstSearch);
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
		for (CatPartnerDTO catConstructionTypeDTO : partnerLst) {
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
	public Response importPurchaseOrder(Attachment attachments,
			HttpServletRequest request) throws Exception {
		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
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
				java.util.List<PurchaseOrderDTO> result = purchaseOrderBusinessImpl.importPurchaseOrder(folderUpload + filePath);
				if(result != null && !result.isEmpty()){
						int rowNum = 1;
						for (PurchaseOrderDTO obj : result) {
							if(obj != null){
								PurchaseOrderDTO existing = (PurchaseOrderDTO) purchaseOrderBusinessImpl.findByCode(obj.getCode());
								if (existing != null) {
									return Response.status(Response.Status.CONFLICT).build();
								} 
								obj.setCreatedUserId(objUser.getSysUserId());
								
								obj.setStatus(1L);
								obj.setCreatedDate(new Timestamp(System.currentTimeMillis()));
								purchaseOrderBusinessImpl.save(obj);
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
