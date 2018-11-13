/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.espringtran.compressor4j.bean.CompressionLevel;
import com.espringtran.compressor4j.bean.CompressionType;
import com.espringtran.compressor4j.bean.ZipLevel;
import com.espringtran.compressor4j.compressor.FileCompressor;
import com.viettel.erp.business.ConstrWorkLogsLabelBusinessImpl;
import com.viettel.erp.dto.ConstrWorkLogsDTO;
import com.viettel.erp.dto.ConstrWorkLogsLabelDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.util.CollectionUtils;

/**
 *
 * @author HungLQ9
 */
public class ConstrWorkLogsLabelRsServiceImpl implements ConstrWorkLogsLabelRsService {

	@Value("${folder_upload}")
	private String folder2Upload;
	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
	static Logger LOGGER = LoggerFactory.getLogger(CompletionDrawingRsServiceImpl.class);

	@Autowired
	ConstrWorkLogsLabelBusinessImpl constrWorkLogsLabelBusinessImpl;

	@Override
	public Response getConstrWorkLogsLabel() {
		List<ConstrWorkLogsLabelDTO> ls = constrWorkLogsLabelBusinessImpl.getAll();
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
	public Response getConstrWorkLogsLabelById(Long id) {
		ConstrWorkLogsLabelDTO obj = (ConstrWorkLogsLabelDTO) constrWorkLogsLabelBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateConstrWorkLogsLabel(ConstrWorkLogsLabelDTO obj) {
		Long id = constrWorkLogsLabelBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addConstrWorkLogsLabel(ConstrWorkLogsLabelDTO obj) {
		Long id = constrWorkLogsLabelBusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response deleteConstrWorkLogsLabel(Long id) {
		ConstrWorkLogsLabelDTO obj = (ConstrWorkLogsLabelDTO) constrWorkLogsLabelBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			constrWorkLogsLabelBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response creat(ConstrWorkLogsLabelDTO dto) {
		Long id = constrWorkLogsLabelBusinessImpl.creat(dto);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(id).build();
		}
	}

	@Override
	public Response exportFilePdf(ConstrWorkLogsLabelDTO dto) throws Exception {
		if (dto.getContractCode() == null || "".equals(dto.getContractCode())) {
			dto.setContractCode("");
		}
		VConstructionHcqtDTO data = constrWorkLogsLabelBusinessImpl.findById(dto.getConstructId(),
				dto.getContractCode());
		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Calendar c = Calendar.getInstance();
		data.setCurrentYear(c.get(Calendar.YEAR) + "");

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		if(data.getSigned_date()!=null){
			data.setSignDateString(formatter.format(data.getSigned_date()));
		}else{
			data.setSignDateString("");
			data.setSigned_date(new Date());
		}
		

		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-04-BIA.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			// chan ky
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			// chan ky
			IContext context = report.createContext();

			context.put("item", data);

			// begin chan ky

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;

			if (StringUtils.isNotEmpty(data.getaDirectorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaMonitorPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbConstructPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbConstructPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getCaStatus() == null) {
				data.setCaStatus(-1l);
			}
			if (data.getCaStatus() == 2) {
				data.setaDirectorNameSign(data.getaDirectorName());
				data.setaMonitorNameSign(data.getaMonitorName());
				data.setbConstructNameSign(data.getbConstructName());
				data.setbDirectorNameSign(data.getbDirectorName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setaDirectorNameSign("");
				data.setaMonitorNameSign("");
				data.setbConstructNameSign("");
				data.setbDirectorNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}
			// end chan ky
			File fout = new File(folder2Upload + "/BM-TCNT-04-BIA.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-04-BIA.pdf");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@Override
	public Response exportFileDoc(ConstrWorkLogsLabelDTO dto) {
		VConstructionHcqtDTO data = constrWorkLogsLabelBusinessImpl.findById(dto.getConstructId(),
				dto.getContractCode());
		if (data == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Calendar c = Calendar.getInstance();
		data.setCurrentYear(c.get(Calendar.YEAR) + "");

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		data.setSignDateString(formatter.format(data.getSigned_date()));
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-04-BIA.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			FieldsMetadata metadata = report.createFieldsMetadata();
			// chan ky
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			// chan ky
			IContext context = report.createContext();
			context.put("item", data);

			// begin chan ky

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;

			if (StringUtils.isNotEmpty(data.getaDirectorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaMonitorPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbConstructPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbConstructPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getCaStatus() == null) {
				data.setCaStatus(-1l);
			}
			if (data.getCaStatus() == 2) {
				data.setaDirectorNameSign(data.getaDirectorName());
				data.setaMonitorNameSign(data.getaMonitorName());
				data.setbConstructNameSign(data.getbConstructName());
				data.setbDirectorNameSign(data.getbDirectorName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setaDirectorNameSign("");
				data.setaMonitorNameSign("");
				data.setbConstructNameSign("");
				data.setbDirectorNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}
			// end chan ky

			File fout = new File(folder2Upload + "/BM-TCNT-04-BIA.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-04-BIA.docx");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public String exportOneDoc(ConstrWorkLogsLabelDTO dto) {
		VConstructionHcqtDTO data = constrWorkLogsLabelBusinessImpl.findById(dto.getConstructId(),
				dto.getContractCode());
		Calendar c = Calendar.getInstance();
		data.setCurrentYear(c.get(Calendar.YEAR) + "");

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		data.setSignDateString(formatter.format(data.getSigned_date()));
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-04-BIA.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			FieldsMetadata metadata = report.createFieldsMetadata();
			// chan ky
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			// chan ky
			IContext context = report.createContext();
			context.put("item", data);

			// begin chan ky

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;

			if (StringUtils.isNotEmpty(data.getaDirectorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaMonitorPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbConstructPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbConstructPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getCaStatus() == null) {
				data.setCaStatus(-1l);
			}
			if (data.getCaStatus() == 2) {
				data.setaDirectorNameSign(data.getaDirectorName());
				data.setaMonitorNameSign(data.getaMonitorName());
				data.setbConstructNameSign(data.getbConstructName());
				data.setbDirectorNameSign(data.getbDirectorName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setaDirectorNameSign("");
				data.setaMonitorNameSign("");
				data.setbConstructNameSign("");
				data.setbDirectorNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}
			// end chan ky

			File fout = new File(folder2Upload + "/BM-TCNT-04-BIA.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return data.getContractCode()+"-BM-TCNT-04-BIA.docx";
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public Response exportListDoc(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			ConstrWorkLogsLabelDTO dto = new ConstrWorkLogsLabelDTO();
			dto.setConstrWoLogsLabId(l);
			String filename = exportOneDoc(dto);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "bianhatkycongtrinh." + TYPE.getExtension());

			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("bianhatkycongtrinh." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path)).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response checkBia(Long constructId) {
		boolean check = constrWorkLogsLabelBusinessImpl.checkBia(constructId);
		if (check) {
			// da tao bia nhat ky
			return Response.ok("{\"data\":\"OK\"}").build();
		} else {
			// Chua tao bia nhat ky
			return Response.ok("{\"data\":\"NULL\"}").build();
		}
	}

	@Override
	public Response getAllBia(Long constructId) {
		List<ConstrWorkLogsLabelDTO> list = constrWorkLogsLabelBusinessImpl.getAllBia(constructId);
		if (list == null || list.size() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(list).build();
		}
	}

	@Override
	public Response appro(approDTO dto) {
		Long res = constrWorkLogsLabelBusinessImpl.appro(dto);
		if (res == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(res).build();
		}
	}

	@Override
	public Response updateLabel(ConstrWorkLogsLabelDTO dto) {
		Long res = constrWorkLogsLabelBusinessImpl.updateLabel(dto);
		return Response.ok(res).build();
	}

}
