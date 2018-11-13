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
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.erp.business.CompletionDrawingBusinessImpl;
import com.viettel.erp.business.ConstrConstructionsBusinessImpl;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.PhieuYeuCauNghiemThuDTO;
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
public class ConstrConstructionsRsServiceImpl implements ConstrConstructionsRsService {

	private static Logger LOGGER = LoggerFactory.getLogger(ConstrConstructionsRsServiceImpl.class);

	@Autowired
	CompletionDrawingBusinessImpl completionDrawingBusinessImpl;

	@Autowired
	UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;

	@Value("${completionDrawing.attachType}")
	private Long attachType;

	@Value("${completionDrawing.folder}")
	private String folder;

	@Value("${folder_upload}")
	private String folder2Upload;

	@Autowired
	ConstrConstructionsBusinessImpl constrConstructionsBusinessImpl;

	@Override
	public Response getConstructions(ConstrConstructionsDTO obj) {
		List<ConstrConstructionsDTO> ls = constrConstructionsBusinessImpl.getConstructions(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			if (ls.size() > 0) {
				return Response.ok(ls.get(0)).build();
			} else {
				return Response.ok(ls).build();
			}

		}
	}

	@Override
	public Response getConstrConstructions() {
		List<ConstrConstructionsDTO> ls = constrConstructionsBusinessImpl.getAll();
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
	public Response getConstrConstructionsById(Long id) {
		ConstrConstructionsDTO obj = (ConstrConstructionsDTO) constrConstructionsBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateConstrConstructions(ConstrConstructionsDTO obj) {
		Long id = constrConstructionsBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addConstrConstructions(ConstrConstructionsDTO obj) {
		Long id = constrConstructionsBusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response deleteConstrConstructions(Long id) {
		ConstrConstructionsDTO obj = (ConstrConstructionsDTO) constrConstructionsBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			constrConstructionsBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response exportFile(PhieuYeuCauNghiemThuDTO data) {
		try {
			String fileType = data.getFileType();
			data = constrConstructionsBusinessImpl.getDataExportFile(data);
			if (data != null) {
				data = constrConstructionsBusinessImpl.getDataExportExtra(data);
				if (data != null && data.getSignStatus() == 1 && data.getReceiveDay() != null) {
					Calendar c = Calendar.getInstance();
					c.setTime(data.getReceiveDay());
					data.setReceiveDateInMonth(c.get(Calendar.DAY_OF_MONTH) + "");
					data.setReceiveMonth((c.get(Calendar.MONTH) + 1) + "");
					data.setReceiveYear((c.get(Calendar.YEAR)) + "");
				} else {
					if(data != null){
					data.setReceiveDateInMonth("...");
					data.setReceiveMonth("...");
					data.setReceiveYear("...");
					}
				}
			}
			LOGGER.info("Data to exported : {}", data);
			if(data != null){
			String hstart = data.getAcceptanceDate().substring(11, 13) + data.getAcceptanceDate().substring(13, 16);
			String dstart = data.getAcceptanceDate().substring(0, 2);
			String mstart = data.getAcceptanceDate().substring(3, 5);
			String ystart = data.getAcceptanceDate().substring(6, 10);
			data.setHstart(hstart);
			data.setDstart(dstart);
			data.setMstart(mstart);
			data.setYstart(ystart);
			
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../doc-template").getPath();

			File tmpl = new File(filePath + "/BM-TCNT-09.docx");
			InputStream in = new FileInputStream(tmpl);
			LOGGER.info("Template file: {}", tmpl.getCanonicalPath());

			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");

			File f1 = new File(folder2Upload + "/" + data.getSendPersonId());
			File f2 = new File(folder2Upload + "/" + data.getReceivePersonId());
			IImageProvider sign1;
			IImageProvider sign2;
			if (f1.exists() && !f1.isDirectory()) {
				sign1 = new FileImageProvider(f1);
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			if (f2.exists() && !f2.isDirectory()) {
				sign2 = new FileImageProvider(f2);
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));

			IContext context = report.createContext();
			if (data.getStatusca() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				data.setSendPersonNameSign(data.getSendPersonName());
				data.setReceivePersonNameSign(data.getReceivePersonName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				data.setSendPersonNameSign("");
				data.setReceivePersonNameSign("");
			}
			String path2File = folder2Upload + File.separatorChar + data.getConstAcceptanceRequestId() + "-BM-TCNT-09";
			context.put("item", data);

			OutputStream out;
			File f;
			if ("doc".equals(fileType)) {
				f = new File(path2File + ".docx");
				LOGGER.info("Exporting to {}", f.getCanonicalPath());
				out = new FileOutputStream(f);

				report.process(context, out);
				out.flush();
				out.close();
			} else {
				f = new File(path2File + ".pdf");
				LOGGER.info("Exporting to {}", f.getCanonicalPath());
				out = new FileOutputStream(f);

				Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
				report.convert(context, options, out);
				out.flush();
				out.close();
			}

			String path = UEncrypt.encryptFileUploadPath(f.getName());
			return Response.ok(java.util.Collections.singletonMap("fileName", path)).build();
			}else{
				return null;
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

}
