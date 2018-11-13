/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.common.UFile;
import com.viettel.ktts2.common.UString;
import com.viettel.wms.business.StockCellBusinessImpl;
import com.viettel.wms.dto.StockCellDTO;
import com.viettel.wms.dto.StockDeliveryConfigDTO;
import com.viettel.service.base.dto.DataListDTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author HungLQ9
 */
public class StockCellRsServiceImpl implements StockCellRsService {
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
	// protected final Logger log = Logger.getLogger(UserRsService.class);
	@Autowired
	StockCellBusinessImpl stockCellBusinessImpl;

	@Override
	public Response getStockCell() {
		List<StockCellDTO> ls = stockCellBusinessImpl.getAll();
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			DataListDTO data = new DataListDTO();
			data.setData(ls);
			data.setTotal(ls.size());
			data.setSize(ls.size());
			data.setStart(1);
			return Response.ok(data).build();
		}
	}

	@Override
	public Response getStockCellById(Long id) {
		StockCellDTO obj = (StockCellDTO) stockCellBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateStockCell(StockCellDTO obj) {
		Long id = stockCellBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addListStockCell(List<StockCellDTO> lst) {
		Long id = 0l;
		for (int i = 0; i < lst.size(); i++) {
			id = stockCellBusinessImpl.save(lst.get(i));
		}
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response deleteStockCell(Long id) {
		StockCellDTO obj = (StockCellDTO) stockCellBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			stockCellBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response doSearch(StockCellDTO obj) {
		DataListDTO data = stockCellBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response deleteStockCell(List<Long> ids) {
		if (ids.size() > 0) {
			for (int i = 0; i < ids.size(); i++) {
				StockCellDTO obj = (StockCellDTO) stockCellBusinessImpl.getOneById(ids.get(i));
				if (obj != null) {
					stockCellBusinessImpl.delete(obj);
				}
			}
		}
		return null;
	}

	@Override
	public Response importStockCell(Attachment attachments, HttpServletRequest request) throws Exception {
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
				return Response.ok(stockCellBusinessImpl.importStockCell(folderUpload + filePath)).build();
			} catch (Exception e) {
				// TODO Auto-generated catch block
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

	@Override
	public Response exportStockCellExcel() throws Exception {
		// TODO Auto-generated method stub
		try{
			String strReturn = stockCellBusinessImpl.exportStockCellExcel();
			return Response.ok(Collections.singletonMap("fileName", strReturn)).build();
		}catch (Exception e) {
		} 
		return null;
	}
}
