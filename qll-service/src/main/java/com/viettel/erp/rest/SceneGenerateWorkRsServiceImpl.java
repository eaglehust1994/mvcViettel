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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
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
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstrGroundHandoverBusinessImpl;
import com.viettel.erp.business.SceneGenerateWorkBusinessImpl;
import com.viettel.erp.business.SceneGenerateWorkListBusinessImpl;
import com.viettel.erp.business.WorkItemsAcceptanceBusinessImpl;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.ConstrAcceptWorkListDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.SceneGenerateWorkListDTO;
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
import net.sf.jxls.transformer.XLSTransformer;

/**
 *
 * @author HungLQ9
 */
public class SceneGenerateWorkRsServiceImpl implements SceneGenerateWorkRsService {

	@Value("${folder_upload}")
	private String folder2Upload;
	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
	static Logger LOGGER = LoggerFactory.getLogger(SceneGenerateWorkBusinessImpl.class);

	@Autowired
	SceneGenerateWorkBusinessImpl sceneGenerateWorkBusinessImpl;

	@Autowired
	SceneGenerateWorkListBusinessImpl sceneGenerateWorkListBusinessImpl;

	@Autowired
	ConstrGroundHandoverBusinessImpl constrGroundHandoverBusinessImpl;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	// tungpv
	@Override
	public Response doSearchSceneGenerateWork(SceneGenerateWorkDTO obj) {
		List<SceneGenerateWorkDTO> ls = sceneGenerateWorkBusinessImpl.doSearchSceneGenerateWork(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	// minhpvn
	@Override
	public Response doSearchSceneGenerateWorkConstruction(SceneGenerateWorkDTO obj) {
		List<SceneGenerateWorkDTO> ls = sceneGenerateWorkBusinessImpl.doSearchSceneGenerateWorkConstruction(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getItemNameByConstrId(SceneGenerateWorkDTO obj) {
		List<SceneGenerateWorkDTO> ls = sceneGenerateWorkBusinessImpl.getItemNameByConstrId(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response exportFileSceneGenerateWork(SceneGenerateWorkDTO obj) {
		SceneGenerateWorkDTO data = sceneGenerateWorkBusinessImpl.getDataExport(obj);
		data.setBbhtpsklList(sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWorkList(obj));
		for (int j = 0; j < data.getBbhtpsklList().size(); j++) {
			SceneGenerateWorkListDTO dto = data.getBbhtpsklList().get(j);
			dto.setStt(j + 1);
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-11.docx"));

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			metadata.addFieldAsImage("sign5");
			metadata.addFieldAsImage("sign6");
			metadata.load("bbhtpskl", SceneGenerateWorkListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("bbhtpskl", data.getBbhtpsklList());

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider sign5;
			IImageProvider sign6;

			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign3 = new FileImageProvider(f);
				} else {
					sign3 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign3 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbInChargeConstructIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign4 = new FileImageProvider(f);
				} else {
					sign4 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign4 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignDirectionIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignDirectionIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign5 = new FileImageProvider(f);
				} else {
					sign5 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign5 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignDirectionIdPath()));
			} else {
				sign5 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignManagerIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignManagerIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign6 = new FileImageProvider(f);
				} else {
					sign6 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign6 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignManagerIdPath()));
			} else {
				sign6 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
				context.put("sign5", sign5);
				context.put("sign6", sign6);
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				data.setcDesignDirectionIdNameSign(data.getcDesignDirectionIdName());
				data.setcDesignManagerIdNameSign(data.getcDesignManagerIdName());

			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
				context.put("sign5", none);
				context.put("sign6", none);
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				data.setcDesignDirectionIdNameSign("");
				data.setcDesignManagerIdNameSign("");
			}

			// pdf
			File fout = new File(folder2Upload +"/"+ obj.getSceneGenerateWorkId()+ "-BM-TCNT-11.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(obj.getSceneGenerateWorkId() +"-BM-TCNT-11.pdf");
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

	@Override
	public Response exportFileDocSceneGenerateWork(SceneGenerateWorkDTO obj) {
		SceneGenerateWorkDTO data = sceneGenerateWorkBusinessImpl.getDataExport(obj);
		data.setBbhtpsklList(sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWorkList(obj));
//		List<SceneGenerateWorkDTO> InContract = new ArrayList<SceneGenerateWorkDTO>();
//		List<SceneGenerateWorkDTO> OutContract = new ArrayList<SceneGenerateWorkDTO>();

		
		for (int j = 0; j < data.getBbhtpsklList().size(); j++) {
			SceneGenerateWorkListDTO dto = data.getBbhtpsklList().get(j);
			dto.setStt(j + 1);
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-11.docx"));

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			metadata.addFieldAsImage("sign5");
			metadata.addFieldAsImage("sign6");
			metadata.load("bbhtpskl", SceneGenerateWorkListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("bbhtpskl", data.getBbhtpsklList());
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider sign5;
			IImageProvider sign6;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign3 = new FileImageProvider(f);
				} else {
					sign3 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign3 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbInChargeConstructIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign4 = new FileImageProvider(f);
				} else {
					sign4 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign4 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignDirectionIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignDirectionIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign5 = new FileImageProvider(f);
				} else {
					sign5 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign5 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignDirectionIdPath()));
			} else {
				sign5 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignManagerIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignManagerIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign6 = new FileImageProvider(f);
				} else {
					sign6 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign6 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignManagerIdPath()));
			} else {
				sign6 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
				context.put("sign5", sign5);
				context.put("sign6", sign6);
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				data.setcDesignDirectionIdNameSign(data.getcDesignDirectionIdName());
				data.setcDesignManagerIdNameSign(data.getcDesignManagerIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
				context.put("sign5", none);
				context.put("sign6", none);
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				data.setcDesignDirectionIdNameSign("");
				data.setcDesignManagerIdNameSign("");
			}
			File fout = new File(folder2Upload +"/"+obj.getSceneGenerateWorkId() +"-BM-TCNT-11.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(obj.getSceneGenerateWorkId() +"-BM-TCNT-11.docx");
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

	public String exportOneSceneGenerateWork(SceneGenerateWorkDTO obj) {
		SceneGenerateWorkDTO data = sceneGenerateWorkBusinessImpl.getDataExport(obj);
		data.setBbhtpsklList(sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWorkList(obj));
		for (int j = 0; j < data.getBbhtpsklList().size(); j++) {
			SceneGenerateWorkListDTO dto = data.getBbhtpsklList().get(j);
			dto.setStt(j + 1);
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-11.docx"));

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			metadata.addFieldAsImage("sign5");
			metadata.addFieldAsImage("sign6");
			metadata.load("bbhtpskl", SceneGenerateWorkListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("bbhtpskl", data.getBbhtpsklList());
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider sign5;
			IImageProvider sign6;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign3 = new FileImageProvider(f);
				} else {
					sign3 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign3 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbInChargeConstructIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign4 = new FileImageProvider(f);
				} else {
					sign4 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign4 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignDirectionIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignDirectionIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign5 = new FileImageProvider(f);
				} else {
					sign5 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign5 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignDirectionIdPath()));
			} else {
				sign5 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignManagerIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignManagerIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign6 = new FileImageProvider(f);
				} else {
					sign6 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign6 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignManagerIdPath()));
			} else {
				sign6 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
				context.put("sign5", sign5);
				context.put("sign6", sign6);
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				data.setcDesignDirectionIdNameSign(data.getcDesignDirectionIdName());
				data.setcDesignManagerIdNameSign(data.getcDesignManagerIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
				context.put("sign5", none);
				context.put("sign6", none);
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				data.setcDesignDirectionIdNameSign("");
				data.setcDesignManagerIdNameSign("");
			}
			// pdf
			File fout = new File(folder2Upload + "/" + obj.getSceneGenerateWorkId() + "-BM-TCNT-11.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return obj.getSceneGenerateWorkId() + "-BM-TCNT-11.pdf";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}
//minhpvn
	//sua
	public String exportOneSceneGenerateWorkCT(SceneGenerateWorkDTO obj) {
		SceneGenerateWorkDTO data = sceneGenerateWorkBusinessImpl.getDataExport(obj);
		data.setBbhtpsklList(sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWorkList(obj));

		List<SceneGenerateWorkListDTO>  InContract = new ArrayList<SceneGenerateWorkListDTO>(); 
		List<SceneGenerateWorkListDTO>  OutContract = new ArrayList<SceneGenerateWorkListDTO>(); 
		
		
		for (int i = 0; i<data.getBbhtpsklList().size();i++){
			if(data.getBbhtpsklList().get(i).getType() == 1){
				InContract.add(data.getBbhtpsklList().get(i));
				for (int j = 0; j<InContract.size();j++){
					InContract.get(j).setStt(j+1);
				}
				
			}else if(data.getBbhtpsklList().get(i).getType() == 2){
				OutContract.add(data.getBbhtpsklList().get(i));
				for (int j = 0; j<OutContract.size();j++){
					OutContract.get(j).setStt(j+1);
				}
			}
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-18.docx"));

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			metadata.addFieldAsImage("sign5");
			metadata.addFieldAsImage("sign6");
			//metadata.load("bbhtpskl", SceneGenerateWorkListDTO.class, true);
			metadata.load("InContract", SceneGenerateWorkListDTO.class, true);
			metadata.load("OutContract", SceneGenerateWorkListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			//context.put("bbhtpskl", data.getBbhtpsklList());
			context.put("InContract", InContract);
			context.put("OutContract", OutContract);

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider sign5;
			IImageProvider sign6;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign3 = new FileImageProvider(f);
				} else {
					sign3 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign3 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbInChargeConstructIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign4 = new FileImageProvider(f);
				} else {
					sign4 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign4 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignDirectionIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignDirectionIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign5 = new FileImageProvider(f);
				} else {
					sign5 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign5 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignDirectionIdPath()));
			} else {
				sign5 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignManagerIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignManagerIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign6 = new FileImageProvider(f);
				} else {
					sign6 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign6 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignManagerIdPath()));
			} else {
				sign6 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
				context.put("sign5", sign5);
				context.put("sign6", sign6);
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				data.setcDesignDirectionIdNameSign(data.getcDesignDirectionIdName());
				data.setcDesignManagerIdNameSign(data.getcDesignManagerIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
				context.put("sign5", none);
				context.put("sign6", none);
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				data.setcDesignDirectionIdNameSign("");
				data.setcDesignManagerIdNameSign("");
			}
			// pdf
			File fout = new File(folder2Upload + "/" + obj.getSceneGenerateWorkId() + "-BM-TCNT-11.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return obj.getSceneGenerateWorkId() + "-BM-TCNT-11.pdf";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}
	public String exportOneDocSceneGenerateWork(SceneGenerateWorkDTO obj) {
		SceneGenerateWorkDTO data = sceneGenerateWorkBusinessImpl.getDataExport(obj);
		data.setBbhtpsklList(sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWorkList(obj));
		for (int j = 0; j < data.getBbhtpsklList().size(); j++) {
			SceneGenerateWorkListDTO dto = data.getBbhtpsklList().get(j);
			dto.setStt(j + 1);
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-11.docx"));

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			metadata.addFieldAsImage("sign5");
			metadata.addFieldAsImage("sign6");
			metadata.load("bbhtpskl", SceneGenerateWorkListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("bbhtpskl", data.getBbhtpsklList());
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider sign5;
			IImageProvider sign6;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign3 = new FileImageProvider(f);
				} else {
					sign3 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign3 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbInChargeConstructIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign4 = new FileImageProvider(f);
				} else {
					sign4 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign4 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignDirectionIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignDirectionIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign5 = new FileImageProvider(f);
				} else {
					sign5 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign5 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignDirectionIdPath()));
			} else {
				sign5 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignManagerIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignManagerIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign6 = new FileImageProvider(f);
				} else {
					sign6 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign6 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignManagerIdPath()));
			} else {
				sign6 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
				context.put("sign5", sign5);
				context.put("sign6", sign6);
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				data.setcDesignDirectionIdNameSign(data.getcDesignDirectionIdName());
				data.setcDesignManagerIdNameSign(data.getcDesignManagerIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
				context.put("sign5", none);
				context.put("sign6", none);
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				data.setcDesignDirectionIdNameSign("");
				data.setcDesignManagerIdNameSign("");
			}
			File fout = new File(folder2Upload + "/" + obj.getSceneGenerateWorkId() + "-BM-TCNT-11.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return obj.getSceneGenerateWorkId() + "-BM-TCNT-11.docx";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}
//minhpvn
	
	public String exportOneDocSceneGenerateWorkCT(SceneGenerateWorkDTO obj) {
		SceneGenerateWorkDTO data = sceneGenerateWorkBusinessImpl.getDataExport(obj);
		data.setBbhtpsklList(sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWorkList(obj));
		
		List<SceneGenerateWorkListDTO>  InContract = new ArrayList<SceneGenerateWorkListDTO>(); 
		List<SceneGenerateWorkListDTO>  OutContract = new ArrayList<SceneGenerateWorkListDTO>(); 
		
		for (int i = 0; i<data.getBbhtpsklList().size();i++){
			if(data.getBbhtpsklList().get(i).getType() == 1){
				InContract.add(data.getBbhtpsklList().get(i));
				for (int j = 0; j<InContract.size();j++){
					InContract.get(j).setStt(j+1);
				}
				
			}else if(data.getBbhtpsklList().get(i).getType() == 2){
				OutContract.add(data.getBbhtpsklList().get(i));
				for (int j = 0; j<OutContract.size();j++){
					OutContract.get(j).setStt(j+1);
				}
			}
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-18.docx"));

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			metadata.addFieldAsImage("sign5");
			metadata.addFieldAsImage("sign6");
			//metadata.load("bbhtpskl", SceneGenerateWorkListDTO.class, true);
			metadata.load("InContract", SceneGenerateWorkListDTO.class, true);
			metadata.load("OutContract", SceneGenerateWorkListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			//context.put("bbhtpskl", data.getBbhtpsklList());
			context.put("InContract", InContract);
			context.put("OutContract", OutContract);
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider sign5;
			IImageProvider sign6;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign3 = new FileImageProvider(f);
				} else {
					sign3 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign3 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbInChargeConstructIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign4 = new FileImageProvider(f);
				} else {
					sign4 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign4 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignDirectionIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignDirectionIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign5 = new FileImageProvider(f);
				} else {
					sign5 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign5 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignDirectionIdPath()));
			} else {
				sign5 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignManagerIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignManagerIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign6 = new FileImageProvider(f);
				} else {
					sign6 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign6 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignManagerIdPath()));
			} else {
				sign6 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
				context.put("sign5", sign5);
				context.put("sign6", sign6);
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				data.setcDesignDirectionIdNameSign(data.getcDesignDirectionIdName());
				data.setcDesignManagerIdNameSign(data.getcDesignManagerIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
				context.put("sign5", none);
				context.put("sign6", none);
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				data.setcDesignDirectionIdNameSign("");
				data.setcDesignManagerIdNameSign("");
			}
			File fout = new File(folder2Upload + "/" + obj.getSceneGenerateWorkId() + "-BM-TCNT-11.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return obj.getSceneGenerateWorkId() + "-BM-TCNT-11.docx";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}
	@Override
	public Response exportListSceneGenerateWork(List<Long> data) {

		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			SceneGenerateWorkDTO dto = new SceneGenerateWorkDTO();
			dto.setSceneGenerateWorkId(l);
			String filename = exportOneSceneGenerateWork(dto);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "BBHTvsPSPDF." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("BBHTvsPSPDF." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response exportListDocSceneGenerateWork(List<Long> data) {

		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			SceneGenerateWorkDTO dto = new SceneGenerateWorkDTO();
			dto.setSceneGenerateWorkId(l);
			String filename = exportOneDocSceneGenerateWork(dto);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "BBHTvsPSKLDOC." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("BBHTvsPSKLDOC." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	// end tungpv

	@Override
	public Response getSceneGenerateWork() {
		List<SceneGenerateWorkDTO> ls = sceneGenerateWorkBusinessImpl.getAll();
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
	public Response getSceneGenerateWorkById(Long id) {
		SceneGenerateWorkDTO obj = (SceneGenerateWorkDTO) sceneGenerateWorkBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateSceneGenerateWork(SceneGenerateWorkDTO obj) {
		Long id = sceneGenerateWorkBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response updateIsActive(List<Long> id) {
		boolean ls = sceneGenerateWorkBusinessImpl.updateIsActive(id);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response addSceneGenerateWork(SceneGenerateWorkDTO obj) {
		String code = (String) constrGroundHandoverBusinessImpl.getCode("SCENE_GENERATE_WORK", "QLHC_BBHT_PSKL");
		if (StringUtils.isNotEmpty(code)) {
			obj.setCode(code);
			Long id = sceneGenerateWorkBusinessImpl.save(obj);
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(Response.Status.CREATED).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response deleteSceneGenerateWork(Long id) {
		SceneGenerateWorkDTO obj = (SceneGenerateWorkDTO) sceneGenerateWorkBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response appro(approDTO obj) {
		Long result = sceneGenerateWorkBusinessImpl.appro(obj);
		if (result == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}
	
	//minhpvn approval cong trinh
	@Override
	public Response approCT(approDTO obj) {
		Long result = sceneGenerateWorkBusinessImpl.approCT(obj);
		if (result == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}

	@Override
	public Response downloadTempleReportSceneGenerate(SceneGenerateWorkDTO obj) throws Exception {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
     	String filePath = classloader.getResource("../" + "doc-template").getPath();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath + "/importCongTrinh.xlsx")));
	      BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(folder2Upload + "/"  +"importCongTrinh.xlsx"));
	      byte[] buff = new byte[1024];
	     // byte[] bytes = src_File.getBytes();
	      
	      
	      while ((bis.read(buff)) != -1) {
	          bos.write(buff);
	          bos.flush();
	      }
	      bis.close();
	      bos.close();
		
	      String path  =   UEncrypt.encryptFileUploadPath("importCongTrinh.xlsx");
		return Response.ok(Collections.singletonMap("fileName", path)).build();
	}
	//minhpvn 
    private void saveWorkbook(Workbook resultWorkbook, String fileName) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
        resultWorkbook.write(os);
        os.flush();
        os.close();
    }
//minhpvn
	@Override
	public Response downloadBieuMau(SceneGenerateWorkDTO obj) throws Exception {
		// TODO Auto-generated method stub

		
//		CompletionDrawingDTO drawdto = new CompletionDrawingDTO();
//		drawdto.setConstructId(obj.getConstructId());
		
		
		    Map beans = new HashMap();
	        List<SceneGenerateWorkDTO> dataImport = sceneGenerateWorkBusinessImpl.getItemNameByConstrId(obj);
     
//	        for(int i = 0 ; i< dataImport.size() ; i++){
//	        	dataImport.get(i).setRownum(i+1);
//	        }        
	       // List<CompletionDrawingDTO> draw = completionDrawingBusinessImpl.getCompletionDrawingSearch(drawdto);
	        beans.put("dataImportIns", dataImport);

	      //  beans.put("drawing", draw);
	        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    	String filePath = classloader.getResource("../" + "doc-template").getPath(); 
	        InputStream is = new BufferedInputStream(new FileInputStream(filePath + "dataImportCT.xlsx"));
	        XLSTransformer transformer = new XLSTransformer();
	        long startTime = System.nanoTime();
	        Workbook resultWorkbook = transformer.transformXLS(is, beans);
	        long endTime = System.nanoTime();
	        is.close();
	        saveWorkbook(resultWorkbook, folder2Upload +"/"+ obj.getConstructId()+  "-dataImportCT.xlsx");
	        System.out.println("Stress1 XLSX time (s): " + (endTime - startTime)/1000000000);
	        String path  =   UEncrypt.encryptFileUploadPath("/"+ obj.getConstructId()+  "-dataImportCT.xlsx");
			return Response.ok(Collections.singletonMap("fileName", path))
	        		.build();
	
	}
//minhpvn export cong trinh
//da fix	
	@Override
	public Response exportFileSceneGenerateWorkCT(SceneGenerateWorkDTO obj) {
		SceneGenerateWorkDTO data = sceneGenerateWorkBusinessImpl.getDataExport(obj);
		data.setBbhtpsklList(sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWorkList(obj));
		List<SceneGenerateWorkListDTO>  InContract = new ArrayList<SceneGenerateWorkListDTO>(); 
		List<SceneGenerateWorkListDTO>  OutContract = new ArrayList<SceneGenerateWorkListDTO>(); 
			
		//for (int i = 0; i<OutContract.size();i++){
			
		//}
//		for (int j = 0; j < data.getBbhtpsklList().size(); j++) {
//			SceneGenerateWorkListDTO dto = data.getBbhtpsklList().get(j);
//			dto.setStt(j + 1);
//		}
		for (int i = 0; i<data.getBbhtpsklList().size();i++){
			if(data.getBbhtpsklList().get(i).getType() == 1){
				InContract.add(data.getBbhtpsklList().get(i));
				for (int j = 0; j<InContract.size();j++){
					InContract.get(j).setStt(j+1);
				}
				
			}else if(data.getBbhtpsklList().get(i).getType() == 2){
				OutContract.add(data.getBbhtpsklList().get(i));
				for (int j = 0; j<OutContract.size();j++){
					OutContract.get(j).setStt(j+1);
				}
			}
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-18.docx"));

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			metadata.addFieldAsImage("sign5");
			metadata.addFieldAsImage("sign6");
			//metadata.load("bbhtpskl", SceneGenerateWorkListDTO.class, true);
			metadata.load("InContract", SceneGenerateWorkListDTO.class, true);
			metadata.load("OutContract", SceneGenerateWorkListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
//			context.put("bbhtpskl", data.getBbhtpsklList());
			context.put("InContract", InContract);
			context.put("OutContract", OutContract);

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider sign5;
			IImageProvider sign6;

			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign3 = new FileImageProvider(f);
				} else {
					sign3 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign3 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbInChargeConstructIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign4 = new FileImageProvider(f);
				} else {
					sign4 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign4 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignDirectionIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignDirectionIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign5 = new FileImageProvider(f);
				} else {
					sign5 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign5 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignDirectionIdPath()));
			} else {
				sign5 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignManagerIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignManagerIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign6 = new FileImageProvider(f);
				} else {
					sign6 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign6 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignManagerIdPath()));
			} else {
				sign6 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
				context.put("sign5", sign5);
				context.put("sign6", sign6);
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				data.setcDesignDirectionIdNameSign(data.getcDesignDirectionIdName());
				data.setcDesignManagerIdNameSign(data.getcDesignManagerIdName());

			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
				context.put("sign5", none);
				context.put("sign6", none);
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				data.setcDesignDirectionIdNameSign("");
				data.setcDesignManagerIdNameSign("");
			}

			// pdf
			File fout = new File(folder2Upload +"/"+ obj.getSceneGenerateWorkId()+ "-BM-TCNT-TCNT.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(obj.getSceneGenerateWorkId() +"-BM-TCNT-TCNT.pdf");
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

	@Override
	public Response exportListSceneGenerateWorkCT(List<Long> data) {

		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			SceneGenerateWorkDTO dto = new SceneGenerateWorkDTO();
			dto.setSceneGenerateWorkId(l);
			String filename = exportOneSceneGenerateWorkCT(dto);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "BBHTvsPSCTPDF." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("BBHTvsPSCTPDF." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	//dang fix
	@Override
	public Response exportFileDocSceneGenerateWorkCT(SceneGenerateWorkDTO obj) {
		SceneGenerateWorkDTO data = sceneGenerateWorkBusinessImpl.getDataExport(obj);
		data.setBbhtpsklList(sceneGenerateWorkListBusinessImpl.doSearchSceneGenerateWorkList(obj));
		
		List<SceneGenerateWorkListDTO>  InContract = new ArrayList<SceneGenerateWorkListDTO>(); 
		List<SceneGenerateWorkListDTO>  OutContract = new ArrayList<SceneGenerateWorkListDTO>(); 
		
		
		
		for (int i = 0; i<data.getBbhtpsklList().size();i++){
			if(data.getBbhtpsklList().get(i).getType() == 1){
				InContract.add(data.getBbhtpsklList().get(i));
				for (int j = 0; j<InContract.size();j++){
					InContract.get(j).setStt(j+1);
				}
				
			}else if(data.getBbhtpsklList().get(i).getType() == 2){
				OutContract.add(data.getBbhtpsklList().get(i));
				for (int j = 0; j<OutContract.size();j++){
					OutContract.get(j).setStt(j+1);
				}
			}
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-18.docx"));

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			metadata.addFieldAsImage("sign5");
			metadata.addFieldAsImage("sign6");
//			metadata.load("bbhtpskl", SceneGenerateWorkListDTO.class, true);
			metadata.load("InContract", SceneGenerateWorkListDTO.class, true);
			metadata.load("OutContract", SceneGenerateWorkListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			//context.put("bbhtpskl", data.getBbhtpsklList());
			context.put("InContract", InContract);
			context.put("OutContract", OutContract);
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider sign5;
			IImageProvider sign6;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign1 = new FileImageProvider(f);
				} else {
					sign1 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign1 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign2 = new FileImageProvider(f);
				} else {
					sign2 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign2 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbDirectorIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign3 = new FileImageProvider(f);
				} else {
					sign3 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign3 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				File f = new File(folder2Upload + "/" + data.getbInChargeConstructIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign4 = new FileImageProvider(f);
				} else {
					sign4 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign4 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignDirectionIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignDirectionIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign5 = new FileImageProvider(f);
				} else {
					sign5 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign5 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignDirectionIdPath()));
			} else {
				sign5 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getcDesignManagerIdPath())) {
				File f = new File(folder2Upload + "/" + data.getcDesignManagerIdPath());
				if (f.exists() && !f.isDirectory()) {
					sign6 = new FileImageProvider(f);
				} else {
					sign6 = new FileImageProvider(new File(filePath + "/none.png"));
				}
				// sign6 = new FileImageProvider(new File(folder2Upload + "/" +
				// data.getcDesignManagerIdPath()));
			} else {
				sign6 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
				context.put("sign5", sign5);
				context.put("sign6", sign6);
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				data.setcDesignDirectionIdNameSign(data.getcDesignDirectionIdName());
				data.setcDesignManagerIdNameSign(data.getcDesignManagerIdName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
				context.put("sign5", none);
				context.put("sign6", none);
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				data.setcDesignDirectionIdNameSign("");
				data.setcDesignManagerIdNameSign("");
			}
			File fout = new File(folder2Upload +"/"+obj.getSceneGenerateWorkId() +"-BM-TCNT-18.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(obj.getSceneGenerateWorkId() +"-BM-TCNT-18.docx");
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

	@Override
	public Response exportListDocSceneGenerateWorkCT(List<Long> data) {

		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			SceneGenerateWorkDTO dto = new SceneGenerateWorkDTO();
			dto.setSceneGenerateWorkId(l);
			String filename = exportOneDocSceneGenerateWorkCT(dto);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "BBHTvsPSCTDOC." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("BBHTvsPSCTDOC." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
