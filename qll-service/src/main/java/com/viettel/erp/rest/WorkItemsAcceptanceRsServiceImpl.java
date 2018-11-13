/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import com.espringtran.compressor4j.bean.CompressionLevel;
import com.espringtran.compressor4j.bean.CompressionType;
import com.espringtran.compressor4j.bean.ZipLevel;
import com.espringtran.compressor4j.compressor.FileCompressor;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstrGroundHandoverBusinessImpl;
import com.viettel.erp.business.EstimatesWorkItemsBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.business.WorkItemsAcceptanceBusinessImpl;
import com.viettel.erp.dao.CatFileInvoiceDAO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

/**
 *
 * @author HungLQ9
 */
public class WorkItemsAcceptanceRsServiceImpl implements WorkItemsAcceptanceRsService {

	@Value("${folder_upload}")
	private String folder2Upload;
	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
	static Logger LOGGER = LoggerFactory.getLogger(WorkItemsAcceptanceBusinessImpl.class);
	@Autowired
	WorkItemsAcceptanceBusinessImpl workItemsAcceptanceBusinessImpl;

	@Autowired
	ConstrGroundHandoverBusinessImpl constrGroundHandoverBusinessImpl;

	@Autowired
	EstimatesWorkItemsBusinessImpl estimatesWorkItemsBusinessImpl;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	@Autowired
	CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
	// tungpv

	@Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;

	@Override
	public Response doSearchWorkItemsAcceptance(WorkItemsAcceptanceDTO criteria) {
		List<WorkItemsAcceptanceDTO> ls = workItemsAcceptanceBusinessImpl.doSearchWorkItemsAcceptance(criteria);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response deleteWorkItemAcceptList(List<String> listString) {
		boolean ls = workItemsAcceptanceBusinessImpl.deleteWorkItemAcceptList(listString);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response addWorkItemAcceptList(WorkItemsAcceptanceDTO criteria) {
		boolean ls = workItemsAcceptanceBusinessImpl.addWorkItemAcceptList(criteria);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response updateIsActive(List<Long> listId) {
		boolean ls = workItemsAcceptanceBusinessImpl.updateIsActive(listId);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response exportFileWorkItemsAcceptance(WorkItemsAcceptanceDTO obj) throws Exception {
		WorkItemsAcceptanceDTO data = workItemsAcceptanceBusinessImpl.getDataExport(obj);
		data.setCvntList(estimatesWorkItemsBusinessImpl.doSearchByWorkItemsAcceptanceId(obj));
		for (int j = 0; j < data.getCvntList().size(); j++) {
			EstimatesWorkItemsDTO dto = data.getCvntList().get(j);
			dto.setStt(j + 1);
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-10.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();

			metadata.addFieldAsImage("yes");
			metadata.addFieldAsImage("no");
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");

			metadata.load("cvnts", EstimatesWorkItemsDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getCvntList());

			IImageProvider yes = new FileImageProvider(new File(filePath + "/yes.png"));
			IImageProvider no = new FileImageProvider(new File(filePath + "/no.png"));
			IImageProvider sign1;
			IImageProvider sign2;
			if (StringUtils.isNotEmpty(data.getMonitorIdNamePath())) {
				File f = new File(folder2Upload + "/" + data.getMonitorIdNamePath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getMonitorIdNamePath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getInChargeConstructIdNamePath())) {
				File f = new File(folder2Upload + "/" + data.getInChargeConstructIdNamePath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getInChargeConstructIdNamePath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getConclusion() == 1) {
				context.put("yes", yes);
				context.put("no", no);
			} else {
				context.put("yes", no);
				context.put("no", yes);
			}
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				data.setMonitorIdSign(data.getMonitorIdName());
				data.setInChargeConstructIdSign(data.getInChargeConstructIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				data.setMonitorIdSign("");
				data.setInChargeConstructIdSign("");
			}
			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-10.docx" ) ); report.process( context,
			 * out );
			 */

			// pdf
			File fout = new File(folder2Upload +"/"+ obj.getWorkItemsAcceptanceId() +"-BM-TCNT-10.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(obj.getWorkItemsAcceptanceId() + "-BM-TCNT-10.pdf");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public Response exportFileDocWorkItemsAcceptance(WorkItemsAcceptanceDTO obj) {
		WorkItemsAcceptanceDTO data = workItemsAcceptanceBusinessImpl.getDataExport(obj);
		data.setCvntList(estimatesWorkItemsBusinessImpl.doSearchByWorkItemsAcceptanceId(obj));
		for (int j = 0; j < data.getCvntList().size(); j++) {
			EstimatesWorkItemsDTO dto = data.getCvntList().get(j);
			dto.setStt(j + 1);
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-10.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("yes");
			metadata.addFieldAsImage("no");
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.load("cvnts", EstimatesWorkItemsDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getCvntList());
			IImageProvider yes = new FileImageProvider(new File(filePath + "/yes.png"));
			IImageProvider no = new FileImageProvider(new File(filePath + "/no.png"));
			IImageProvider sign1;
			IImageProvider sign2;
			if (StringUtils.isNotEmpty(data.getMonitorIdNamePath())) {
				File f = new File(folder2Upload + "/" + data.getMonitorIdNamePath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getMonitorIdNamePath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getInChargeConstructIdNamePath())) {
				File f = new File(folder2Upload + "/" + data.getInChargeConstructIdNamePath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getInChargeConstructIdNamePath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getConclusion() == 1) {
				context.put("yes", yes);
				context.put("no", no);
			} else {
				context.put("yes", no);
				context.put("no", yes);
			}
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				data.setMonitorIdSign(data.getMonitorIdName());
				data.setInChargeConstructIdSign(data.getInChargeConstructIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				data.setMonitorIdSign("");
				data.setInChargeConstructIdSign("");
			}
			// pdf
			File fout = new File(folder2Upload +"/"+ obj.getWorkItemsAcceptanceId() +"-BM-TCNT-10.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(obj.getWorkItemsAcceptanceId() +"-BM-TCNT-10.docx");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	public String exportOneWorkItemsAcceptance(WorkItemsAcceptanceDTO obj) {
		WorkItemsAcceptanceDTO data = workItemsAcceptanceBusinessImpl.getDataExport(obj);
		data.setCvntList(estimatesWorkItemsBusinessImpl.doSearchByWorkItemsAcceptanceId(obj));
		for (int j = 0; j < data.getCvntList().size(); j++) {
			EstimatesWorkItemsDTO dto = data.getCvntList().get(j);
			dto.setStt(j + 1);
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-10.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("yes");
			metadata.addFieldAsImage("no");
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.load("cvnts", EstimatesWorkItemsDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getCvntList());
			IImageProvider yes = new FileImageProvider(new File(filePath + "/yes.png"));
			IImageProvider no = new FileImageProvider(new File(filePath + "/no.png"));
			IImageProvider sign1;
			IImageProvider sign2;
			if (StringUtils.isNotEmpty(data.getMonitorIdNamePath())) {
				File f = new File(folder2Upload + "/" + data.getMonitorIdNamePath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getMonitorIdNamePath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getInChargeConstructIdNamePath())) {
				File f = new File(folder2Upload + "/" + data.getInChargeConstructIdNamePath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getInChargeConstructIdNamePath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getConclusion() == 1) {
				context.put("yes", yes);
				context.put("no", no);
			} else {
				context.put("yes", no);
				context.put("no", yes);
			}
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				data.setMonitorIdSign(data.getMonitorIdName());
				data.setInChargeConstructIdSign(data.getInChargeConstructIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				data.setMonitorIdSign("");
				data.setInChargeConstructIdSign("");
			}
			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-10.docx" ) ); report.process( context,
			 * out );
			 */

			// pdf
			File fout = new File(folder2Upload + "/" + obj.getWorkItemsAcceptanceId() + "-BM-TCNT-10.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return obj.getWorkItemsAcceptanceId() + "-BM-TCNT-10.pdf";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	public String exportOneDocWorkItemsAcceptance(WorkItemsAcceptanceDTO obj) {
		WorkItemsAcceptanceDTO data = workItemsAcceptanceBusinessImpl.getDataExport(obj);
		data.setCvntList(estimatesWorkItemsBusinessImpl.doSearchByWorkItemsAcceptanceId(obj));
		for (int j = 0; j < data.getCvntList().size(); j++) {
			EstimatesWorkItemsDTO dto = data.getCvntList().get(j);
			dto.setStt(j + 1);
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-10.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("yes");
			metadata.addFieldAsImage("no");
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.load("cvnts", EstimatesWorkItemsDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getCvntList());
			IImageProvider yes = new FileImageProvider(new File(filePath + "/yes.png"));
			IImageProvider no = new FileImageProvider(new File(filePath + "/no.png"));
			IImageProvider sign1;
			IImageProvider sign2;
			if (StringUtils.isNotEmpty(data.getMonitorIdNamePath())) {
				File f = new File(folder2Upload + "/" + data.getMonitorIdNamePath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getMonitorIdNamePath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getInChargeConstructIdNamePath())) {
				File f = new File(folder2Upload + "/" + data.getInChargeConstructIdNamePath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getInChargeConstructIdNamePath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getConclusion() == 1) {
				context.put("yes", yes);
				context.put("no", no);
			} else {
				context.put("yes", no);
				context.put("no", yes);
			}
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				data.setMonitorIdSign(data.getMonitorIdName());
				data.setInChargeConstructIdSign(data.getInChargeConstructIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				data.setMonitorIdSign("");
				data.setInChargeConstructIdSign("");
			}
			// pdf
			File fout = new File(folder2Upload + "/" + obj.getWorkItemsAcceptanceId() + "-BM-TCNT-10.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return obj.getWorkItemsAcceptanceId() + "-BM-TCNT-10.docx";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public Response exportListWorkItemsAcceptance(List<WorkItemsAcceptanceDTO> data) {

		List<String> listFileName = new ArrayList<String>();
		for (WorkItemsAcceptanceDTO l : data) {
			WorkItemsAcceptanceDTO dto = new WorkItemsAcceptanceDTO();
			dto.setWorkItemsAcceptanceId(l.getWorkItemsAcceptanceId());
			dto.setConstructId(l.getConstructId());
			String filename = exportOneWorkItemsAcceptance(dto);
			if (filename != null) {
				listFileName.add(filename);
			}
		}

		try {

			if (CollectionUtils.isEmpty(listFileName)) {
				LOGGER.warn("No files added!!");
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			FileCompressor fileCompressor = new FileCompressor();
			File f = new File(folder2Upload);
			if (!f.exists()) {
				LOGGER.warn("{} folder is not exist!!", folder2Upload);
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			String filePath = f.getCanonicalPath();
			LOGGER.info("adding {} files", listFileName.size());

			for (String file : listFileName) {
				String srcPath = filePath + File.separatorChar + file;
				String desPath = file;
				LOGGER.info("{} added", srcPath);
				fileCompressor.add(srcPath, desPath);
			}
			fileCompressor.setType(TYPE);
			fileCompressor.setLevel(LEVEL);
			fileCompressor.setCompressedPath(
					filePath + File.separatorChar + "NghiemThuCongViecXayDungPDF." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("NghiemThuCongViecXayDungPDF." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName",
					path)).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response exportListDocWorkItemsAcceptance(List<WorkItemsAcceptanceDTO> data) {

		List<String> listFileName = new ArrayList<String>();
		for (WorkItemsAcceptanceDTO l : data) {
			WorkItemsAcceptanceDTO dto = new WorkItemsAcceptanceDTO();
			dto.setWorkItemsAcceptanceId(l.getWorkItemsAcceptanceId());
			dto.setConstructId(l.getConstructId());
			String filename = exportOneDocWorkItemsAcceptance(dto);
			if (filename != null) {
				listFileName.add(filename);
			}
		}

		try {

			if (CollectionUtils.isEmpty(listFileName)) {
				LOGGER.warn("No files added!!");
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			FileCompressor fileCompressor = new FileCompressor();
			File f = new File(folder2Upload);
			if (!f.exists()) {
				LOGGER.warn("{} folder is not exist!!", folder2Upload);
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			String filePath = f.getCanonicalPath();
			LOGGER.info("adding {} files", listFileName.size());

			for (String file : listFileName) {
				String srcPath = filePath + File.separatorChar + file;
				String desPath = file;
				LOGGER.info("{} added", srcPath);
				fileCompressor.add(srcPath, desPath);
			}
			fileCompressor.setType(TYPE);
			fileCompressor.setLevel(LEVEL);
			fileCompressor.setCompressedPath(
					filePath + File.separatorChar + "NghiemThuCongViecXayDungDOC." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("NghiemThuCongViecXayDungDOC." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName",
					path)).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	// end tungpv

	@Override
	public Response getWorkItemsAcceptance() {
		List<WorkItemsAcceptanceDTO> ls = workItemsAcceptanceBusinessImpl.getAll();
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
	public Response getWorkItemsAcceptanceById(Long id) {
		WorkItemsAcceptanceDTO obj = (WorkItemsAcceptanceDTO) workItemsAcceptanceBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateWorkItemsAcceptance(WorkItemsAcceptanceDTO obj) {
		Long id = workItemsAcceptanceBusinessImpl.update(obj);
		if(obj.getStatusCa() == 0L){
			String nameTable = "WORK_ITEMS_ACCEPTANCE";
			qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getWorkItemsAcceptanceId(), nameTable);
		}
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addWorkItemsAcceptance(WorkItemsAcceptanceDTO obj) {
		String code = (String) constrGroundHandoverBusinessImpl.getCode("WORK_ITEMS_ACCEPTANCE", "QLHC_NTCVXD");
		if (StringUtils.isNotEmpty(code)) {
			obj.setCode(code);
			obj.setCreatedDate(new Date());

			CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("WORK_ITEMS_ACCEPTANCE");
			Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();

			ConstrCompleteRecordsMapDTO constrCompleteRecordsMap = new ConstrCompleteRecordsMapDTO();
			constrCompleteRecordsMap.setDataTableName("WORK_ITEMS_ACCEPTANCE");
			constrCompleteRecordsMap.setDataTableId("WORK_ITEMS_ACCEPTANCE_ID");
			constrCompleteRecordsMap.setCatFileInvoiceId(catFileInvoiceId);
			constrCompleteRecordsMap.setCreatedDate(new Date());
			constrCompleteRecordsMap.setStatus(0L);
			constrCompleteRecordsMap.setCreatedUserId(obj.getCreatedUserId());
			constrCompleteRecordsMap.setConstructionId(obj.getConstructId());
			constrCompleteRecordsMap.setCode(code);
			constrCompleteRecordsMap.setLevelOrder(1L);
			obj.setConstrCompleteRecordsMap(constrCompleteRecordsMap);

			try {
				Long id = workItemsAcceptanceBusinessImpl.saveTable(obj);
				return Response.ok(id).build();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response deleteWorkItemsAcceptance(Long id) {
		WorkItemsAcceptanceDTO obj = (WorkItemsAcceptanceDTO) workItemsAcceptanceBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			workItemsAcceptanceBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response appro(approDTO obj) {
		Long result = workItemsAcceptanceBusinessImpl.appro(obj);
		if (result == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}
}
