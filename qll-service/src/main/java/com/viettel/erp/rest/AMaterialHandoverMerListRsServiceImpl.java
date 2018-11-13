/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.erp.business.AMaterialHandoverMerListBusinessImpl;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.AMaterialHandoverMerListDTO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import net.sf.jxls.transformer.XLSTransformer;

/**
 *
 * @author HungLQ9
 */
public class AMaterialHandoverMerListRsServiceImpl implements AMaterialHandoverMerListRsService {

	@Value("${folder_upload}")
	private String folder2Upload;

	@Autowired
	AMaterialHandoverMerListBusinessImpl aMaterialHandoverMerListBusinessImpl;

	@Override
	public Response getListAMaterialHandOverMerList(AMaterialHandoverDTO dto) {
		List<AMaterialHandoverMerListDTO> ls = aMaterialHandoverMerListBusinessImpl
				.getListAMaterialHandOverMerList(dto);
		for (int i = 0; i < ls.size(); i++) {
			ls.get(i).setSldabangiao(aMaterialHandoverMerListBusinessImpl.getSldabangiao(ls.get(i)));
		}
		return Response.ok(ls).build();

	}

	// @Override
	// public Response addListAMaterial(List<AMaterialHandoverMerListBO>
	// listBTVT) {
	// Long id =
	// aMaterialHandoverMerListBusinessImpl.addListAMaterial(listBTVT);
	// if (id == 0l) {
	// return Response.status(Response.Status.BAD_REQUEST).build();
	// } else {
	// return Response.ok(Response.Status.CREATED).build();
	// }
	// }

	@Override
	public Response getListAMaterial(List<String> listPXK) {
		List<AMaterialHandoverMerListDTO> ls = aMaterialHandoverMerListBusinessImpl.getListAMaterial(listPXK);
		for (int i = 0; i < ls.size(); i++) {
			ls.get(i).setSldabangiao(aMaterialHandoverMerListBusinessImpl.getSldabangiao(ls.get(i)));
		}
		return Response.ok(ls).build();
	}

	@Override
	public Response getAMaterialHandoverMerList() {
		List<AMaterialHandoverMerListDTO> ls = aMaterialHandoverMerListBusinessImpl.getAll();
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
	public Response getAMaterialHandoverMerListById(Long id) {
		AMaterialHandoverMerListDTO obj = (AMaterialHandoverMerListDTO) aMaterialHandoverMerListBusinessImpl
				.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateAMaterialHandoverMerList(AMaterialHandoverMerListDTO obj) {
		Long id = aMaterialHandoverMerListBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addAMaterialHandoverMerList(AMaterialHandoverMerListDTO obj) {
		Long id = aMaterialHandoverMerListBusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response deleteAMaterialHandoverMerList(Long id) {
		AMaterialHandoverMerListDTO obj = (AMaterialHandoverMerListDTO) aMaterialHandoverMerListBusinessImpl
				.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			aMaterialHandoverMerListBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response getAmaterialhandoverforcontruction(Long constructId) {
		List<AMaterialHandoverMerListDTO> ls = aMaterialHandoverMerListBusinessImpl
				.getAmaterialhandoverforcontruction(constructId);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getAmaterialhandoverforcontructionX(Long constructId) {
		List<AMaterialHandoverMerListDTO> ls = aMaterialHandoverMerListBusinessImpl
				.getAmaterialhandoverforcontructionX(constructId);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response exportMerList(AMaterialHandoverMerListDTO obj) throws Exception {

		Map beans = new HashMap();
		List<AMaterialHandoverMerListDTO> merlists = aMaterialHandoverMerListBusinessImpl
				.getAmaterialhandoverforcontructionX(obj.getConstructionId());

		for (int i = 0; i < merlists.size(); i++) {
			merlists.get(i).setRownum(i + 1);
		}

		// List<CompletionDrawingDTO> draw =
		// completionDrawingBusinessImpl.getCompletionDrawingSearch(drawdto);
		beans.put("merlists", merlists);
		// beans.put("drawing", draw);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream is = new BufferedInputStream(new FileInputStream(filePath + "merlist.xls"));
		XLSTransformer transformer = new XLSTransformer();
		long startTime = System.nanoTime();
		Workbook resultWorkbook = transformer.transformXLS(is, beans);
		long endTime = System.nanoTime();
		is.close();
		saveWorkbook(resultWorkbook, folder2Upload + "/" + obj.getConstructionId() + "-merlist.xls");
		System.out.println("Stress1 XLSX time (s): " + (endTime - startTime) / 1000000000);
		String path  =   UEncrypt.encryptFileUploadPath( obj.getConstructionId()+  "-merlist.xls");
		return Response.ok(Collections.singletonMap("fileName", path))
				.build();
	}

	private void saveWorkbook(Workbook resultWorkbook, String fileName) throws IOException {
		OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
		resultWorkbook.write(os);
		os.flush();
		os.close();
	}
}
