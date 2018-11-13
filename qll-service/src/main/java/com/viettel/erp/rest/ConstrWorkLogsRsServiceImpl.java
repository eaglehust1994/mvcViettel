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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.espringtran.compressor4j.bean.CompressionLevel;
import com.espringtran.compressor4j.bean.CompressionType;
import com.espringtran.compressor4j.bean.ZipLevel;
import com.espringtran.compressor4j.compressor.FileCompressor;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstrWorkLogsBusinessImpl;
import com.viettel.erp.dto.ConstrWorkLogsDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
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
public class ConstrWorkLogsRsServiceImpl implements ConstrWorkLogsRsService {

	@Value("${folder_upload}")
	private String folder2Upload;

	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;

	static Logger LOGGER = LoggerFactory.getLogger(CompletionDrawingRsServiceImpl.class);
	@Autowired
	ConstrWorkLogsBusinessImpl constrWorkLogsBusinessImpl;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	// ChuongNV
	@Override
	public Response getAllConstrWorkLogs(ConstrWorkLogsDTO dto) {
		List<ConstrWorkLogsDTO> ls = constrWorkLogsBusinessImpl.getAllConstrWorkLogs(dto);
		this.getPath();
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	// Lấy thông tin tên + code hiện lên popup
	@Override
	public Response getEstimatesWork(String constructId) {
		List<EstimatesWorkItemsDTO> ls = constrWorkLogsBusinessImpl.getEstimatesWork(constructId);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	// Creat
	@Override
	@Transactional
	public Response addConstrWorkLogs(ConstrWorkLogsDTO obj) {
		Long id = constrWorkLogsBusinessImpl.addConstrWorkLogs(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(id).build();
		}
	}

	// Update
	@Override
	public Response updateConstrWorkLogs(ConstrWorkLogsDTO obj) {
		Date date = new Date();
		obj.setApprovalDate(date);
		obj.setIsActive(1l);
		Long id = constrWorkLogsBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	// Delete
	@Override
	public Response deleteConstrWorkLogs(List<String> listConstrWorkLogsId) {
		for (int i = listConstrWorkLogsId.size() - 1; i >= 0; i--) {
			if (constrWorkLogsBusinessImpl.checkStatusDatabase(listConstrWorkLogsId.get(i))) {
				listConstrWorkLogsId.remove(i);
			}
		}
		if (listConstrWorkLogsId.size() > 0) {
			constrWorkLogsBusinessImpl.deleteConstrWorkLogs(listConstrWorkLogsId);
			return Response.ok().build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// End ChuongNV

	@Override
	public Response getConstrWorkLogs() {
		List<ConstrWorkLogsDTO> ls = constrWorkLogsBusinessImpl.getAll();
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
	public Response getConstrWorkLogsById(Long id) {
		ConstrWorkLogsDTO obj = (ConstrWorkLogsDTO) constrWorkLogsBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response deleteConstrWorkLogs(Long id) {
		ConstrWorkLogsDTO obj = (ConstrWorkLogsDTO) constrWorkLogsBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			constrWorkLogsBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}
	
	public String exportOneFileConstrWorkLogs(ConstrWorkLogsDTO dtoConstr) {
		if (dtoConstr.getContractCode() == null) {
			dtoConstr.setContractCode("");
		}
		ConstrWorkLogsDTO data = constrWorkLogsBusinessImpl.findById(dtoConstr.getConstrWorkLogsId(),
				dtoConstr.getContractCode());
		Date singTime = data.getLogDate();
		Calendar c = Calendar.getInstance();
		c.setTime(singTime);
//		data.setCurentDate(c.get(Calendar.DAY_OF_MONTH));
//		data.setCurentMonth(c.get(Calendar.MONTH) + 1);
//		data.setCurentYear(c.get(Calendar.YEAR) + "");

		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-04.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			IContext context = report.createContext();

			FieldsMetadata metadata = report.createFieldsMetadata();
			// chan ky
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			// chan ky
			context.put("item", data);

			// begin chan ky

			IImageProvider sign1;
			IImageProvider sign2;

			if (StringUtils.isNotEmpty(data.getaMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getbConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaMonitorNameSign(data.getaMonitorName());
				data.setbConstructNameSign(data.getbConstructName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				data.setaMonitorNameSign("");
				data.setbConstructNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}
			// end chan ky

			File fout = new File(folder2Upload + "/"+data.getConstrWorkLogsId()+"-BM-TCNT-04.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return data.getConstrWorkLogsId()+"-BM-TCNT-04.pdf";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public Response exportList(List<String> list) {
		List<String> listFileName = new ArrayList<String>();
		for (int i = 1; i< list.size();i++) {
			long x = 0l;
			try {
				x = Long.parseLong(list.get(i));
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			ConstrWorkLogsDTO data = constrWorkLogsBusinessImpl.findById(x,
					list.get(0));
			String filename = exportOneFileConstrWorkLogs(data);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "NhatKiCongTrinh." + TYPE.getExtension());

			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("NhatKiCongTrinh." + TYPE.getExtension());

			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response exportFileConstrWorkLogs(ConstrWorkLogsDTO dtoConstr) throws Exception {
		if (dtoConstr.getContractCode() == null) {
			dtoConstr.setContractCode("");
		}
		ConstrWorkLogsDTO data = constrWorkLogsBusinessImpl.findById(dtoConstr.getConstrWorkLogsId(),
				dtoConstr.getContractCode());

		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
//		Calendar c = Calendar.getInstance();
//
//		data.setCurentDate(c.get(Calendar.DAY_OF_MONTH));
//		data.setCurentMonth(c.get(Calendar.MONTH) + 1);
//		data.setCurentYear(c.get(Calendar.YEAR) + "");
		Date singTime = data.getLogDate();
		Calendar c = Calendar.getInstance();
		c.setTime(singTime);

		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-04.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			IContext context = report.createContext();

			FieldsMetadata metadata = report.createFieldsMetadata();
			// chan ky
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			// chan ky
			context.put("item", data);

			// begin chan ky

			IImageProvider sign1;
			IImageProvider sign2;

			if (StringUtils.isNotEmpty(data.getaMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getbConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaMonitorNameSign(data.getaMonitorName());
				data.setbConstructNameSign(data.getbConstructName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				data.setaMonitorNameSign("");
				data.setbConstructNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}
			// end chan ky

			File fout = new File(folder2Upload + "/BM-TCNT-04.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-04.pdf");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response exportFileDoc(ConstrWorkLogsDTO dtoConstr) {
		if (dtoConstr.getContractCode() == null) {
			dtoConstr.setContractCode("");
		}
		ConstrWorkLogsDTO data = constrWorkLogsBusinessImpl.findById(dtoConstr.getConstrWorkLogsId(),
				dtoConstr.getContractCode());

		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
//		Calendar c = Calendar.getInstance();
//
//		data.setCurentDate(c.get(Calendar.DAY_OF_MONTH));
//		data.setCurentMonth(c.get(Calendar.MONTH) + 1);
//		data.setCurentYear(c.get(Calendar.YEAR) + "");
		Date singTime = data.getLogDate();
		Calendar c = Calendar.getInstance();
		c.setTime(singTime);
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-04.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			IContext context = report.createContext();
			FieldsMetadata metadata = report.createFieldsMetadata();
			// chan ky
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");

			context.put("item", data);

			// begin chan ky

			IImageProvider sign1;
			IImageProvider sign2;

			if (StringUtils.isNotEmpty(data.getaMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaMonitorNameSign(data.getaMonitorName());
				data.setbConstructNameSign(data.getbConstructName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				data.setaMonitorNameSign("");
				data.setbConstructNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}
			// end chan ky

			File fout = new File(folder2Upload + "/BM-TCNT-04.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-04.docx");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public String exportOneDocFileConstrWorkLogs(ConstrWorkLogsDTO dtoConstr) {
		if (dtoConstr.getContractCode() == null) {
			dtoConstr.setContractCode("");
		}
		ConstrWorkLogsDTO data = constrWorkLogsBusinessImpl.findById(dtoConstr.getConstrWorkLogsId(),
				dtoConstr.getContractCode());

//		Calendar c = Calendar.getInstance();
//
//		data.setCurentDate(c.get(Calendar.DAY_OF_MONTH));
//		data.setCurentMonth(c.get(Calendar.MONTH) + 1);
//		data.setCurentYear(c.get(Calendar.YEAR) + "");
		Date singTime = data.getLogDate();
		Calendar c = Calendar.getInstance();
		c.setTime(singTime);

		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-04.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			IContext context = report.createContext();

			FieldsMetadata metadata = report.createFieldsMetadata();
			// chan ky
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			// chan ky
			context.put("item", data);

			// begin chan ky

			IImageProvider sign1;
			IImageProvider sign2;

			if (StringUtils.isNotEmpty(data.getaMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getbConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaMonitorNameSign(data.getaMonitorName());
				data.setbConstructNameSign(data.getbConstructName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				data.setaMonitorNameSign("");
				data.setbConstructNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}
			// end chan ky

			File fout = new File(folder2Upload + "/"+data.getConstrWorkLogsId()+"-BM-TCNT-04.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return data.getConstrWorkLogsId()+"-BM-TCNT-04.docx";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public Response exportListDoc(List<String> list) {
		List<String> listFileName = new ArrayList<String>();
		for (int i = 1; i< list.size();i++) {
			long x = 0l;
			try {
				x = Long.parseLong(list.get(i));
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
			ConstrWorkLogsDTO data = constrWorkLogsBusinessImpl.findById(x,
					list.get(0));
			String filename = exportOneDocFileConstrWorkLogs(data);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "NhatKiCongTrinh." + TYPE.getExtension());

			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("NhatKiCongTrinh." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response appro(approDTO dto) {
		Long res = constrWorkLogsBusinessImpl.appro(dto);
		if (res == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(res).build();
		}
	}

	public Response getPath() {
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-04-HD.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			IContext context = report.createContext();

			File fout = new File(folder2Upload + "/BM-TCNT-04-HD.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response downloadInstruction() throws Exception {
		// TODO Auto-generated method stub
		String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-04-HD.docx");
		return Response.ok(Collections.singletonMap("fileName", path)).build();
	}

}
