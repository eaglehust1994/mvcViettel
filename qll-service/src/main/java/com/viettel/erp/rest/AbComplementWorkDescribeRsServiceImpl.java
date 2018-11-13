/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.viettel.erp.business.AbComplementWorkDescribeBusinessImpl;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.dto.AbComplementWorkDescribeDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

/**
 *
 * @author HungLQ9
 */
public class AbComplementWorkDescribeRsServiceImpl implements AbComplementWorkDescribeRsService {

	// protected final Logger log = Logger.getLogger(UserRsService.class);
	static Logger LOGGER = LoggerFactory.getLogger(AbComplementWorkDescribeRsServiceImpl.class);
	@Autowired
	AbComplementWorkDescribeBusinessImpl abComplementWorkDescribeBusinessImpl;

	@Autowired
	CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
	@Value("${folder_upload}")
	private String folder2Upload;

	@Override
	public Response getAbComplementWorkDescribe() {
		List<AbComplementWorkDescribeDTO> ls = abComplementWorkDescribeBusinessImpl.getAll();
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
	public Response getAbComplementWorkDescribeById(Long id) {
		AbComplementWorkDescribeDTO obj = (AbComplementWorkDescribeDTO) abComplementWorkDescribeBusinessImpl
				.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateAbComplementWorkDescribe(AbComplementWorkDescribeDTO obj) {
		Long id = abComplementWorkDescribeBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addAbComplementWorkDescribe(AbComplementWorkDescribeDTO obj) {
		Long id = abComplementWorkDescribeBusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response deleteAbComplementWorkDescribe(Long id) {
		AbComplementWorkDescribeDTO obj = (AbComplementWorkDescribeDTO) abComplementWorkDescribeBusinessImpl
				.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			abComplementWorkDescribeBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response addForm4(AbComplementWorkDescribeDTO obj) {
		// TODO Auto-generated method stub
		String code = abComplementWorkDescribeBusinessImpl.autoGenCode("AB_COMPLEMENT_WORK_DESCRIBE", "BBDDKLXLHT");
		obj.setCode(code);
		CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("AB_COMPLEMENT_WORK_DESCRIBE");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
		constrCompleteRecordMap.setDataTableName("AB_COMPLEMENT_WORK_DESCRIBE");
		constrCompleteRecordMap.setDataTableId("AB_COMPLEMENT_WORK_DESCRIBE_ID");
		constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
		constrCompleteRecordMap.setCode(code);
		constrCompleteRecordMap.setCreatedDate(obj.getCreatedDate());
		constrCompleteRecordMap.setStatus(0L);
		constrCompleteRecordMap.setLevelOrder(1L);
		System.out.println("-----" + obj.getCreatedUserId());
		constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
		constrCompleteRecordMap.setConstructionId(obj.getConstructId());
		obj.setConstrCompleteRecordMap(constrCompleteRecordMap);

		Long id = abComplementWorkDescribeBusinessImpl.saveTable(obj);
		if (id == 0l || id == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response getAbComplementWorkByConstId(Long id) {
		AbComplementWorkDescribeDTO obj = abComplementWorkDescribeBusinessImpl.getAbComplementWorkById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response signCA(EstimatesWorkItemsDTO dto) throws Exception {
		// // TODO Auto-generated method stub
		AbComplementWorkDescribeDTO obj = (AbComplementWorkDescribeDTO) abComplementWorkDescribeBusinessImpl
				.getAbComplementWorkById(dto.getConstructionId());

		Long reId ;
		if (obj != null) {
			reId = abComplementWorkDescribeBusinessImpl.getconstrCompReMapId(obj.getAbComplementWorkDescribeId());
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		File file = exportFilePDFDetailForm4(dto);
		if (file != null) {
			List<String> listRs = Lists.newArrayList();
			listRs.add(reId + "");
			listRs.add(file.getPath());
			listRs.add(UEncrypt.encryptFileUploadPath(file.getName()));
			return Response.ok(listRs).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	public File exportFilePDFDetailForm4(EstimatesWorkItemsDTO dto) {
		// System.out.println("run here");
		// EstimatesWorkItemsDTO exportbmaDTO =
		// abComplementWorkDescribeBusinessImpl.exportEstimateWorkItem(id);
		try {
			EstimatesWorkItemsDTO exportbmaDTO = abComplementWorkDescribeBusinessImpl.exportEstimateWorkItem(dto);
			System.out.println("++++++++++++++" + exportbmaDTO.getStation_code());
			List<EstimatesWorkItemsDTO> bmalist = abComplementWorkDescribeBusinessImpl.getBieu4(dto);

			for (int j = 0; j < bmalist.size(); j++) {
				bmalist.get(j).setStt(j + 1);
			}

			System.out.println(exportbmaDTO);
			System.out.println("bmalist:" + bmalist);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/QT-AB-4.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load("bc", EstimatesWorkItemsDTO.class, true);

			IContext context = report.createContext();
			context.put("item", exportbmaDTO);

			context.put("bc", bmalist);

			File fout = new File(folder2Upload + "/" + dto.getConstructId() + "-QT-AB-4.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			// ---
			out.flush();
			out.close();
			// return exportbmaDTO.getBmaterialAcceptanceId() +
			// "-BM-TCNT-07.docx";

			return fout;

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;

	}

}
