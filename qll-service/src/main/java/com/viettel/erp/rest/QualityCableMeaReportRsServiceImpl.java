/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.QualityCableMeaReportDTO;
import com.viettel.erp.dto.QualityCableMeaReportModelDTO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.erp.dto.SettlementRightDTO;
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
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author HungLQ9
 */
public class QualityCableMeaReportRsServiceImpl implements QualityCableMeaReportRsService {

	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;

	static Logger LOGGER = LoggerFactory.getLogger(QualityCableMeaResultRsServiceImpl.class);

	@Value("${completionDrawing.attachType}")
	private Long attachType;

	@Value("${completionDrawing.folder}")
	private String folder;

	@Value("${folder_upload}")
	private String folder2Upload;

	@Autowired
	CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;

	@Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;
	// QualityCableMeaResultBusinessImpl qualityCableMeaResultBusinessImpl;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	@Override
	public Response getQualityCableMeaReport() {
		List<QualityCableMeaReportDTO> ls = qualityCableMeaReportBusinessImpl.getAll();
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
	public Response getQualityCableMeaReportById(Long id) {
		QualityCableMeaReportDTO obj = (QualityCableMeaReportDTO) qualityCableMeaReportBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateQualityCableMeaReport(QualityCableMeaReportDTO obj) {
		Long id = qualityCableMeaReportBusinessImpl.update(obj);
		String nameTable = "QUALITY_CABLE_MEA_REPORT";
		if (obj.getStatusCa() == 0L) {
			qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getQualityCableMeaReportId(), nameTable);
		}
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}
	}

	@Override
	public Response addQualityCableMeaReport(QualityCableMeaReportDTO obj) {
		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String code = qualityCableMeaReportBusinessImpl.autoGenCode();
		CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("QUALITY_CABLE_MEA_REPORT");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();

		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
		constrCompleteRecordMap.setDataTableName("QUALITY_CABLE_MEA_REPORT");
		constrCompleteRecordMap.setDataTableId("QUALITY_CABLE_MEA_REPORT_ID");
		constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
		constrCompleteRecordMap.setCreatedDate(new Date());
		constrCompleteRecordMap.setStatus(0l);
		constrCompleteRecordMap.setLevelOrder(1l);
		constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
		constrCompleteRecordMap.setConstructionId(obj.getConstructId());
		constrCompleteRecordMap.setCode(code);
		obj.setConstrCompleteRecordMap(constrCompleteRecordMap);
		obj.setStatusCa(0L);
		obj.setIsActive(1L);

		obj.setCode(code);
		obj.setCreatedDate(date);
		Long id = qualityCableMeaReportBusinessImpl.saveTable(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response deleteQualityCableMeaReport(Long id) {
		QualityCableMeaReportDTO obj = (QualityCableMeaReportDTO) qualityCableMeaReportBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			qualityCableMeaReportBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response findByConstructId(QualityCableMeaReportModelDTO qualityCableMeaReportDTO) {
		List<QualityCableMeaReportModelDTO> ls = qualityCableMeaReportBusinessImpl
				.findByConstructId(qualityCableMeaReportDTO);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	// truong
	@Override
	public Response getListEmployeeByRole(SettlementRightDTO rightDTO) {
		List<CatEmployeeDTO> obj = qualityCableMeaReportBusinessImpl.getListEmployeeByRole(rightDTO);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response getListQualityResualt(Long qualityCableMeaReportId) {
		System.out.println(qualityCableMeaReportId);
		List<QualityCableMeaResultDTO> ls = qualityCableMeaReportBusinessImpl
				.getListQualityResualt(qualityCableMeaReportId);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response saveOrUpdate(QualityCableMeaReportDTO obj) {
		// TODO Auto-generated method stub
		boolean result = qualityCableMeaReportBusinessImpl.saveOrUpdate(obj);
		if (!result) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok("Updated").build();
		}
	}

	@Override
	public Response qualitySaveOrUpdate(QualityCableMeaReportDTO obj) {
		boolean result = qualityCableMeaReportBusinessImpl.qualitySaveOrUpdate(obj);
		if (!result) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok("Updated").build();
		}
	}

	@Override
	public Response deleteReport(List<String> listReportID) {
		boolean ls = qualityCableMeaReportBusinessImpl.deleteReport(listReportID);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response deleteResult(List<String> listString) {

		boolean ls = qualityCableMeaReportBusinessImpl.deleteResult(listString);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response exportFile(QualityCableMeaReportModelDTO data) {

		try {
			QualityCableMeaReportModelDTO meaReportModelDTO = qualityCableMeaReportBusinessImpl.getQualityReport(data);

			List<QualityCableMeaResultDTO> listResultDTO = qualityCableMeaReportBusinessImpl
					.getListQualityResualt(data.getQualityCableMeaReportId());
			meaReportModelDTO.setListResultDTO(listResultDTO);

			for (int j = 0; j < meaReportModelDTO.getListResultDTO().size(); j++) {
				meaReportModelDTO.getListResultDTO().get(j).setStt(j + 1);
			}
			Date singTime = meaReportModelDTO.getSignDate();
			Calendar c = Calendar.getInstance();
			c.setTime(singTime);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-26.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(meaReportModelDTO.getaMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + meaReportModelDTO.getaMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(meaReportModelDTO.getbConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + meaReportModelDTO.getbConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			metadata.load("bc", QualityCableMeaResultDTO.class, true);
			IContext context = report.createContext();

			context.put("item", meaReportModelDTO);
			context.put("bc", meaReportModelDTO.getListResultDTO());
			if (meaReportModelDTO.getStatusCa() == 2) {
				meaReportModelDTO.setAfullNameSign(meaReportModelDTO.getAfullName());
				meaReportModelDTO.setBfullNamesign(meaReportModelDTO.getBfullName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				meaReportModelDTO.setAfullNameSign("");
				meaReportModelDTO.setBfullNamesign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}

			File fout = new File(
					folder2Upload + "/" + meaReportModelDTO.getQualityCableMeaReportId() + "-BM-TCNT-26.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(meaReportModelDTO.getQualityCableMeaReportId() + "-BM-TCNT-26.pdf");
			return Response.ok(Collections.singletonMap("fileName",
					path)).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException x) {
			LOGGER.error(x.getMessage(), x);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

		return null;
	}

	@Override
	public Response exportList(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			QualityCableMeaReportModelDTO dto = new QualityCableMeaReportModelDTO();
			dto.setQualityCableMeaReportId(l);
			String filename = exportOne(dto);
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
					filePath + File.separatorChar + "baocaochatluongdotuyencap." + TYPE.getExtension());

			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("baocaochatluongdotuyencap." + TYPE.getExtension());
			return Response.ok(
					java.util.Collections.singletonMap("fileName", path))
					.build();
		}catch(IOException ioException){
			LOGGER.error(ioException.getMessage(),ioException);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	private String exportOne(QualityCableMeaReportModelDTO data) {
		try {
			System.out.println("run this method");
			QualityCableMeaReportModelDTO meaReportModelDTO = qualityCableMeaReportBusinessImpl.getQualityReport(data);

			List<QualityCableMeaResultDTO> listResultDTO = qualityCableMeaReportBusinessImpl
					.getListQualityResualt(data.getQualityCableMeaReportId());
			meaReportModelDTO.setListResultDTO(listResultDTO);

			for (int j = 0; j < meaReportModelDTO.getListResultDTO().size(); j++) {
				meaReportModelDTO.getListResultDTO().get(j).setStt(j + 1);
			}
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-26.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(meaReportModelDTO.getaMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + meaReportModelDTO.getaMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(meaReportModelDTO.getbConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + meaReportModelDTO.getbConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			metadata.load("bc", QualityCableMeaResultDTO.class, true);
			IContext context = report.createContext();

			context.put("item", meaReportModelDTO);
			context.put("bc", meaReportModelDTO.getListResultDTO());

			if (meaReportModelDTO.getStatusCa() == 2) {
				meaReportModelDTO.setAfullNameSign(meaReportModelDTO.getAfullName());
				meaReportModelDTO.setBfullNamesign(meaReportModelDTO.getBfullName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				meaReportModelDTO.setAfullNameSign("");
				meaReportModelDTO.setBfullNamesign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}

			File fout = new File(
					folder2Upload + "/" + meaReportModelDTO.getQualityCableMeaReportId() + "-BM-TCNT-26.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return meaReportModelDTO.getQualityCableMeaReportId() + "-BM-TCNT-26.pdf";
		}catch(IOException ioException){
			LOGGER.error(ioException.getMessage(),ioException);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response exportFileDoc(QualityCableMeaReportModelDTO data) {

		try {
			QualityCableMeaReportModelDTO meaReportModelDTO = qualityCableMeaReportBusinessImpl.getQualityReport(data);

			List<QualityCableMeaResultDTO> listResultDTO = qualityCableMeaReportBusinessImpl
					.getListQualityResualt(data.getQualityCableMeaReportId());
			meaReportModelDTO.setListResultDTO(listResultDTO);

			for (int j = 0; j < meaReportModelDTO.getListResultDTO().size(); j++) {
				meaReportModelDTO.getListResultDTO().get(j).setStt(j + 1);
			}
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-26.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(meaReportModelDTO.getaMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + meaReportModelDTO.getaMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(meaReportModelDTO.getbConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + meaReportModelDTO.getbConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			metadata.load("bc", QualityCableMeaResultDTO.class, true);
			IContext context = report.createContext();

			context.put("item", meaReportModelDTO);
			context.put("bc", meaReportModelDTO.getListResultDTO());
			if (meaReportModelDTO.getStatusCa() == 2) {
				meaReportModelDTO.setAfullNameSign(meaReportModelDTO.getAfullName());
				meaReportModelDTO.setBfullNamesign(meaReportModelDTO.getBfullName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				meaReportModelDTO.setAfullNameSign("");
				meaReportModelDTO.setBfullNamesign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}

			File fout = new File(
					folder2Upload + "/" + meaReportModelDTO.getQualityCableMeaReportId() + "-BM-TCNT-26.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(meaReportModelDTO.getQualityCableMeaReportId() + "-BM-TCNT-26.docx");
			return Response.ok(Collections.singletonMap("fileName",
					path)).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException doce) {
			LOGGER.error(doce.getMessage(),doce);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

		return null;
	}

	@Override
	public Response exportListDoc(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			QualityCableMeaReportModelDTO dto = new QualityCableMeaReportModelDTO();
			dto.setQualityCableMeaReportId(l);
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
			fileCompressor.setCompressedPath(
					filePath + File.separatorChar + "baocaochatluongdotuyencap." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("baocaochatluongdotuyencap." + TYPE.getExtension());
			return Response.ok(
					java.util.Collections.singletonMap("fileName", path))
					.build();
		}catch(IOException ioException){
			LOGGER.error(ioException.getMessage(),ioException);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	private String exportOneDoc(QualityCableMeaReportModelDTO data) {
		try {
			QualityCableMeaReportModelDTO meaReportModelDTO = qualityCableMeaReportBusinessImpl.getQualityReport(data);

			List<QualityCableMeaResultDTO> listResultDTO = qualityCableMeaReportBusinessImpl
					.getListQualityResualt(data.getQualityCableMeaReportId());
			meaReportModelDTO.setListResultDTO(listResultDTO);

			for (int j = 0; j < meaReportModelDTO.getListResultDTO().size(); j++) {
				meaReportModelDTO.getListResultDTO().get(j).setStt(j + 1);
			}
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-26.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(meaReportModelDTO.getaMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + meaReportModelDTO.getaMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(meaReportModelDTO.getbConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + meaReportModelDTO.getbConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			metadata.load("bc", QualityCableMeaResultDTO.class, true);
			IContext context = report.createContext();

			context.put("item", meaReportModelDTO);
			context.put("bc", meaReportModelDTO.getListResultDTO());
			if (meaReportModelDTO.getStatusCa() == 2) {
				meaReportModelDTO.setAfullNameSign(meaReportModelDTO.getAfullName());
				meaReportModelDTO.setBfullNamesign(meaReportModelDTO.getBfullName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				meaReportModelDTO.setAfullNameSign("");
				meaReportModelDTO.setBfullNamesign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}
			File fout = new File(
					folder2Upload + "/" + meaReportModelDTO.getQualityCableMeaReportId() + "-BM-TCNT-26.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return meaReportModelDTO.getQualityCableMeaReportId() + "-BM-TCNT-26.docx";

		} catch (IOException ioException) {
			LOGGER.error(ioException.getMessage(), ioException);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response getAllListEmployeeByRole(SettlementRightDTO rightDTO) {
		List<CatEmployeeDTO> ls = qualityCableMeaReportBusinessImpl.getAllListEmployeeByRole(rightDTO);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response appro(approDTO obj) {
		Long result = qualityCableMeaReportBusinessImpl.appro(obj);
		if (result != 0l) {
			return Response.ok(result).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response exportExcel() throws Exception {
		Map beans = new HashMap();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream is;
		try {
			is = new BufferedInputStream(new FileInputStream(filePath + "report.xls"));
			XLSTransformer transformer = new XLSTransformer();
			// long startTime = System.nanoTime();
			Workbook resultWorkbook = transformer.transformXLS(is, beans);
			// long endTime = System.nanoTime();
			is.close();
			saveWorkbook(resultWorkbook, folder2Upload + "/report.xls");
		} catch (FileNotFoundException fe) {
			LOGGER.error(fe.getMessage(), fe);
		} catch (ParsePropertyException pe) {
			LOGGER.error(pe.getMessage(),pe);
		} catch (InvalidFormatException formate) {
			LOGGER.error(formate.getMessage(), formate);
		} catch (IOException ioe) {
			LOGGER.error(ioe.getMessage(),ioe);
		}
		String path  =   UEncrypt.encryptFileUploadPath("report.xls");
		return Response.ok(Collections.singletonMap("fileName", path)).build();
	}

	private void saveWorkbook(Workbook resultWorkbook, String fileName) throws IOException {
		OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
		resultWorkbook.write(os);
		os.flush();
		os.close();
	}
}
