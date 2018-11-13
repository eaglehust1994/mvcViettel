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

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
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
import com.viettel.erp.business.MonitorMissionAssignBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.DistanceUnloadConstrMinutesDTO;
import com.viettel.erp.dto.DistanceUnloadListDTO;
import com.viettel.erp.dto.MonitorMissionAssignDTO;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.erp.utils.Folder;
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
public class MonitorMissionAssignRsServiceImpl implements MonitorMissionAssignRsService {

	@Value("${folder_upload}")
	private String folder2Upload;
	@Value("${monitorMissionAssign.folder}")
	private String folder;
	@Value("${monitorMissionAssign.attachType}")
	private Long attachTypeVal;
	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
	static Logger LOGGER = LoggerFactory.getLogger(CompletionDrawingRsServiceImpl.class);
    @Autowired
    MonitorMissionAssignBusinessImpl monitorMissionAssignBusinessImpl;
    
    @Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;
    
    @Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
    
    @Autowired
	UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;

    @Override
    public Response getMonitorMissionAssign() {
        List<MonitorMissionAssignDTO> ls = monitorMissionAssignBusinessImpl.getAll();
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
	public Response appro(approDTO dto) {
		Long id = monitorMissionAssignBusinessImpl.appro(dto);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(id).build();
        }
	}

    @Override
    public Response getMonitorMissionAssignById(Long id) {
        MonitorMissionAssignDTO obj = (MonitorMissionAssignDTO) monitorMissionAssignBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception {
    	obj.setIsActive(1L);
    	String documentPath = UEncrypt.decryptFileUploadPath(obj.getDocumentPath());
        Long id = monitorMissionAssignBusinessImpl.update(obj);
        utilAttachedDocumentsBusinessImpl.updateUtilByParentIdAndType(obj.getDocumentName(), obj.getMonitorMissionAssignId(), documentPath, attachTypeVal);
        if (obj.getStatusCa() == 0L) {
        	   qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getMonitorMissionAssignId(), "MONITOR_MISSION_ASSIGN");
        }
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception {
    	String code = monitorMissionAssignBusinessImpl.autoGenCode();
    	obj.setCreatedDate(new Date());
    	obj.setCode(code);
		String documentPath = UEncrypt.decryptFileUploadPath(obj.getDocumentPath());
		obj.setStatusCa(0L);
		obj.setIsActive(1l);
		//Long constructionId = obj.getConstructId();
		
		/*CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("MONITOR_MISSION_ASSIGN");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();*/
		
		CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("MONITOR_MISSION_ASSIGN");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
    	constrCompleteRecordMap.setDataTableName("MONITOR_MISSION_ASSIGN");
    	constrCompleteRecordMap.setDataTableId("MONITOR_MISSION_ASSIGN_ID");
    	constrCompleteRecordMap.setCode(code);
    	//constrCompleteRecordMap.setCatFileInvoiceId(1L);
    	constrCompleteRecordMap.setCreatedDate(new Date());
    	constrCompleteRecordMap.setStatus(0L);
    	constrCompleteRecordMap.setLevelOrder(1l);
    	constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
    	constrCompleteRecordMap.setConstructionId(obj.getConstructId());
    	constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
    	obj.setConstrCompleteRecordMap(constrCompleteRecordMap);
        Long id = monitorMissionAssignBusinessImpl.saveTable(obj);
        utilAttachedDocumentsBusinessImpl.insert(obj.getDocumentName(), id, documentPath, attachTypeVal);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteMonitorMissionAssign(Long id) {
        MonitorMissionAssignDTO obj = (MonitorMissionAssignDTO) monitorMissionAssignBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            monitorMissionAssignBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response exportMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception {
		MonitorMissionAssignDTO data = monitorMissionAssignBusinessImpl.getDataExport(obj);
		if(data.getConstrtAddress() == null) {
			data.setConstrtAddress("");
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-05-New.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);

			IImageProvider sign1;
			IImageProvider sign2;
			if (StringUtils.isNotEmpty(data.getaDirectorIdNamePath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorIdNamePath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaMonitorIdNamePath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorIdNamePath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaMonitorIdNameSign(data.getaMonitorIdName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				data.setaDirectorIdNameSign("");
				data.setaMonitorIdNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}
			//4
			// pdf
			File fout = new File(folder2Upload + "/" + obj.getMonitorMissionAssignId() + "-BM-TCNT-05.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("/"+ obj.getMonitorMissionAssignId() + "-BM-TCNT-05.pdf");
			return Response.ok(Collections.singletonMap("fileName",path ))
					.build();
		}catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }/* catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}*/

		return null;
	}

	public String exportOneMonitorMissionAssign(MonitorMissionAssignDTO obj) {
		MonitorMissionAssignDTO data = monitorMissionAssignBusinessImpl.getDataExport(obj);
		if(data.getConstrtAddress() == null) {
			data.setConstrtAddress("");
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-05-New.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
		
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			
			IImageProvider sign1;
			IImageProvider sign2;
			
			if (StringUtils.isNotEmpty(data.getaDirectorIdNamePath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorIdNamePath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaMonitorIdNamePath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorIdNamePath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaMonitorIdNameSign(data.getaMonitorIdName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				data.setaDirectorIdNameSign("");
				data.setaMonitorIdNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}
			
			// 4) Generate report by merging Java model with the Docx
			// pdf
			
			File fout = new File(folder2Upload + "/" + obj.getMonitorMissionAssignId() + "-BM-TCNT-05.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return obj.getMonitorMissionAssignId() + "-BM-TCNT-05.pdf";
		}catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        } /*catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}*/
		return null;
	}
	
	@Override
	public Response exportListMonitorMissionAssign(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			MonitorMissionAssignDTO dto = new MonitorMissionAssignDTO();
			dto.setMonitorMissionAssignId(l);
			String filename = exportOneMonitorMissionAssign(dto);
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
					filePath + File.separatorChar + "GiaoNhiemVuGS-PDF." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("GiaoNhiemVuGS-PDF." + TYPE.getExtension());
			return Response.ok(
					java.util.Collections.singletonMap("fileName",path ))
					.build();
		} catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}

	@Override
	public Response exportDocMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception {
		MonitorMissionAssignDTO data = monitorMissionAssignBusinessImpl.getDataExport(obj);
		if(data.getConstrtAddress() == null) {
			data.setConstrtAddress("");
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-05-New.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);

			IImageProvider sign1;
			IImageProvider sign2;
			if (StringUtils.isNotEmpty(data.getaDirectorIdNamePath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorIdNamePath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaMonitorIdNamePath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorIdNamePath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaMonitorIdNameSign(data.getaMonitorIdName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				data.setaDirectorIdNameSign("");
				data.setaMonitorIdNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}
			//4
			// pdf
			File fout = new File(folder2Upload + "/" + obj.getMonitorMissionAssignId() + "-BM-TCNT-05.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(obj.getMonitorMissionAssignId() + "-BM-TCNT-05.docx");
			return Response.ok(Collections.singletonMap("fileName", path))
					.build();
		}catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        } /*catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}*/

		return null;
	}

	public String exportOneDocMonitorMissionAssign(MonitorMissionAssignDTO obj) {
		MonitorMissionAssignDTO data = monitorMissionAssignBusinessImpl.getDataExport(obj);
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-05-New.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);

			IImageProvider sign1;
			IImageProvider sign2;
			if (StringUtils.isNotEmpty(data.getaDirectorIdNamePath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorIdNamePath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaMonitorIdNamePath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaMonitorIdNamePath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaDirectorIdNameSign(data.getaDirectorIdName());
				data.setaMonitorIdNameSign(data.getaMonitorIdName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
			} else {
				data.setaDirectorIdNameSign("");
				data.setaMonitorIdNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
			}
			//4
			// pdf
			File fout = new File(folder2Upload + "/" + obj.getMonitorMissionAssignId() + "-BM-TCNT-05.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return obj.getMonitorMissionAssignId() + "-BM-TCNT-05.docx";
		}catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }/* catch (IOException e) {
			e.printStackTrace();
		} catch (XDocReportException e) {
			e.printStackTrace();
		}*/

		return null;
	}
	
	@Override
	public Response exportListDocMonitorMissionAssign(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			MonitorMissionAssignDTO dto = new MonitorMissionAssignDTO();
			dto.setMonitorMissionAssignId(l);
			String filename = exportOneDocMonitorMissionAssign(dto);
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
					filePath + File.separatorChar + "GiaoNhiemVuGS-DOCX." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("GiaoNhiemVuGS-DOCX." + TYPE.getExtension());
			return Response.ok(
					java.util.Collections.singletonMap("fileName", path))
					.build();
		}catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}

	@Override
	public Response getMonitorMissionAssign(MonitorMissionAssignDTO obj) throws Exception {
		// TODO Auto-generated method stub
		List<MonitorMissionAssignDTO> ls = monitorMissionAssignBusinessImpl.getMonitorMissionAssign(obj);
		for(MonitorMissionAssignDTO dto : ls){
			dto.setDocumentPath(UEncrypt.encryptFileUploadPath(dto.getDocumentPath()));
		}
		/*if (ls.size()==0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {*/
            return Response.ok(ls).build();
        //}
		
	}

	@Override
	public Response getFolder() {
		return Response.ok().entity(java.util.Collections.singletonMap("folder", folder)).build();
	}

	@Override
	public Response updateIsActive(List<Long> monitorMissionAssignId) {
		boolean ls = monitorMissionAssignBusinessImpl.updateIsActive(monitorMissionAssignId);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
