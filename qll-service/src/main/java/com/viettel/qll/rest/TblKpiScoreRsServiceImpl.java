package com.viettel.qll.rest;

import java.util.List;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.service.base.dto.DataListDTO;



import com.viettel.qll.business.TblKpiScoreBusinessImpl;
import com.viettel.qll.dto.KqHdTkDTO;
import com.viettel.qll.dto.TblKpiScoreDTO;
import com.viettel.qll.dto.TblKpiScoreDTOResponse;
import com.viettel.qll.dto.TblTimeWorkDTO;
import com.viettel.asset.dto.ResultInfo;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.common.UFile;
import com.viettel.ktts2.common.UString;


/**
 * @author hailh10
 */

public class TblKpiScoreRsServiceImpl implements TblKpiScoreRsService {

	protected final Logger log = Logger.getLogger(TblKpiScoreRsService.class);
	@Autowired
	TblKpiScoreBusinessImpl tblKpiScoreBusinessImpl;
	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;
	
	@Value("${folder_upload2}")
	private String folderUpload;
	@Override
	public Response deleteObj(TblKpiScoreDTO obj) throws Exception {

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setStatus(ResultInfo.RESULT_OK);
		Response _response = null;
		try {
			long data = tblKpiScoreBusinessImpl.deleteObj(obj);
			if (data == 0l) {
				resultInfo.setStatus(ResultInfo.RESULT_NOK);
			}
			_response = Response.ok(resultInfo).build();

		} catch (IllegalArgumentException e) {
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			_response = Response.ok(resultInfo).status(Response.Status.BAD_REQUEST).build();
		}

		return _response;

	}

	@Override
	public Response updateObj(TblKpiScoreDTO obj) throws Exception {

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setStatus(ResultInfo.RESULT_OK);
		Response _response = null;
		try {
			long data = tblKpiScoreBusinessImpl.updateObj(obj);
			if (data == 0l) {
				resultInfo.setStatus(ResultInfo.RESULT_NOK);
			}
			_response = Response.ok(resultInfo).build();

		} catch (IllegalArgumentException e) {
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			_response = Response.ok(resultInfo).status(Response.Status.BAD_REQUEST).build();
		}

		return _response;
	}

	@Override
	public Response insertObj(TblKpiScoreDTO obj) throws Exception {

		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setStatus(ResultInfo.RESULT_OK);
		Response _response = null;
		try {
			long data = tblKpiScoreBusinessImpl.insertObj(obj);
			if (data == 0l) {
				resultInfo.setStatus(ResultInfo.RESULT_NOK);
			}
			_response = Response.ok(resultInfo).build();

		} catch (IllegalArgumentException e) {
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			_response = Response.ok(resultInfo).status(Response.Status.BAD_REQUEST).build();
		}

		return _response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getListAllDepartment(TblKpiScoreDTO obj) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		Response _response = null;
		try {
			TblKpiScoreDTOResponse newObj = new TblKpiScoreDTOResponse();
			DataListDTO data = tblKpiScoreBusinessImpl.getListAllDepartment(obj);
			newObj.setListData(data.getData());
			newObj.setTotal(data.getTotal());
			newObj.setSize(data.getSize());
			newObj.setStart(data.getStart());
			resultInfo.setStatus(ResultInfo.RESULT_OK);
			newObj.setResultInfo(resultInfo);
			_response = Response.ok(newObj).build();

		} catch (Exception e) {
			TblKpiScoreDTOResponse errObj = new TblKpiScoreDTOResponse();
			
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			errObj.setResultInfo(resultInfo);
			_response = Response.ok(errObj).status(Response.Status.BAD_REQUEST).build();
		}
		return _response;
	}

	@Override
	public Response getInfoDetailByDepartment(TblKpiScoreDTO obj) throws Exception {
		
		ResultInfo resultInfo = new ResultInfo();
		Response _response = null;
		try {
			TblKpiScoreDTOResponse newObj = new TblKpiScoreDTOResponse();

			List<TblKpiScoreDTO> listData = tblKpiScoreBusinessImpl.getInfoDetailByDepartment(obj);
			newObj.setListData(listData);
			
			resultInfo.setStatus(ResultInfo.RESULT_OK);
			newObj.setResultInfo(resultInfo);
			_response= Response.ok(newObj).build();

		} catch (Exception e) {
			TblKpiScoreDTOResponse errObj = new TblKpiScoreDTOResponse();
	
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			errObj.setResultInfo(resultInfo);
			_response= Response.ok(errObj).status(Response.Status.BAD_REQUEST).build();
		}
		return _response;
	}

	@Override
	public Response getInfoDetailByUseridcreated(TblKpiScoreDTO obj) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		Response _response = null;
		try {
			TblKpiScoreDTOResponse newObj = new TblKpiScoreDTOResponse();

			List<TblKpiScoreDTO> listData = tblKpiScoreBusinessImpl.getInfoDetailByUseridcreated(obj);
			newObj.setListData(listData);
		
			resultInfo.setStatus(ResultInfo.RESULT_OK);
			newObj.setResultInfo(resultInfo);
			_response= Response.ok(newObj).build();

		} catch (Exception e) {
			TblKpiScoreDTOResponse errObj = new TblKpiScoreDTOResponse();
		
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			errObj.setResultInfo(resultInfo);
			_response= Response.ok(errObj).status(Response.Status.BAD_REQUEST).build();
		}
		return _response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Response getInfoDetailByDoSearch(TblKpiScoreDTO obj) throws Exception {
		ResultInfo resultInfo = new ResultInfo();
		Response _response = null;
		try {
			TblKpiScoreDTOResponse newObj = new TblKpiScoreDTOResponse();

			DataListDTO data = tblKpiScoreBusinessImpl.getInfoDetailByDoSearch(obj);
			newObj.setListData(data.getData());
			newObj.setTotal(data.getTotal());
			newObj.setSize(data.getSize());
			newObj.setStart(data.getStart());
			resultInfo.setStatus(ResultInfo.RESULT_OK);
			newObj.setResultInfo(resultInfo);
			_response= Response.ok(newObj).build();

		} catch (Exception e) {
			TblKpiScoreDTOResponse errObj = new TblKpiScoreDTOResponse();
		
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			errObj.setResultInfo(resultInfo);
			_response= Response.ok(errObj).status(Response.Status.BAD_REQUEST).build();
		}
		return _response;
	}
	
	public Response getNotifyByDepartment(TblKpiScoreDTO obj) throws Exception {
		
		ResultInfo resultInfo = new ResultInfo();
		Response _response = null;
		try {
			TblKpiScoreDTOResponse newObj = new TblKpiScoreDTOResponse();
			
			Date d = obj.getModifieddate();
			List<TblKpiScoreDTO> listData = tblKpiScoreBusinessImpl.getNotifyByDepartment(obj);
			newObj.setListData(listData);
		
			resultInfo.setStatus(ResultInfo.RESULT_OK);
			newObj.setResultInfo(resultInfo);
			_response= Response.ok(newObj).build();

		} catch (Exception e) {
			TblKpiScoreDTOResponse errObj = new TblKpiScoreDTOResponse();
		
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			errObj.setResultInfo(resultInfo);
			_response= Response.ok(errObj).status(Response.Status.BAD_REQUEST).build();
		}
		return _response;
		
	}
	
	@Override
	public Response decidedImportFile(Attachment attachments, HttpServletRequest request) throws Exception {
		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
		String filePathReturn;
		String filePath;
		if (UString.isNullOrWhitespace(folderParam)) {
			folderParam = defaultSubFolderUpload;
		}
		// else {
		// if (!isFolderAllowFolderSave(folderParam)) {
		// throw new BusinessException("folder khong nam trong white list:
		// folderParam=" + folderParam);
		// }
		// }

		DataHandler dataHandler = attachments.getDataHandler();

		// get filename to be uploaded
		MultivaluedMap<String, String> multivaluedMap = attachments.getHeaders();
		String fileName = UFile.getFileName(multivaluedMap);

		// if (!isExtendAllowSave(fileName)) {
		// throw new BusinessException("File extension khong nam trong list duoc
		// up load, file_name:" + fileName);
		// }
		// write & upload file to server
		try (InputStream inputStream = dataHandler.getInputStream();) {
			filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
			filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
		} catch (Exception ex) {
			throw new BusinessException("Loi khi save file", ex);
		}

		try {

			try {
				String result = tblKpiScoreBusinessImpl.decidedImportFile(folderUpload + filePath,request);
				if (result.isEmpty()) {
					return Response.ok().build();
				} else {
					return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
				}
			} catch (Exception e) {
				System.out.println("Lỗi :"+e);
				// TODO Auto-generated catch block
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}

		} catch (IllegalArgumentException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}

	}
	
	
	@Override
	public Response decidedExportFile( TblKpiScoreDTO obj, HttpServletRequest request) throws Exception {

		try {
			String result = tblKpiScoreBusinessImpl.decidedExportFile(obj,request);
			if (result.isEmpty()) {
				return Response.ok().build();
			} else {
				return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
		@Override
		public Response generalExportFile( TblKpiScoreDTO obj, HttpServletRequest request) throws Exception {

			try {
				String result = tblKpiScoreBusinessImpl.generalExportFile(obj,request);
				if (result.isEmpty()) {
					return Response.ok().build();
				} else {
					return Response.ok().entity(Collections.singletonMap("fileName", result)).build();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		}
	
	@Override
	public Response getAutoDepartmentKPI(TblKpiScoreDTO obj) {
		List<TblKpiScoreDTO> ls = tblKpiScoreBusinessImpl.getAutoDepartmentKPI(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
	@Override
	public Response getAutoDepartmentCreatedKPI(TblKpiScoreDTO obj) {
		List<TblKpiScoreDTO> ls = tblKpiScoreBusinessImpl.getAutoDepartmentCreatedKPI(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}
}
