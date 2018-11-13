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
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

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
import com.viettel.erp.business.ConstrOrganizationPlanBusinessImpl;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dto.CatEmployeeDTO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.ConstrOrganizationPlanDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.MonitorMissionAssignDTO;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

/**
 *
 * @author HungLQ9
 */
public class ConstrOrganizationPlanRsServiceImpl implements ConstrOrganizationPlanRsService {
	
	private static final CompressionType TYPE = CompressionType.ZIP;
    private static final CompressionLevel LEVEL = ZipLevel.NORMAL;

	static Logger LOGGER = LoggerFactory.getLogger(ConstrOrganizationPlanRsServiceImpl.class);
	@Value("${folder_upload}")
	private String folder2Upload;
	
	@Value("${folder_upload2}")
	private String folder2Upload1;

	@Value("${constrOrganization.attachTypeKey}")
	private Long attachTypeKey;

	@Value("${constrOrganization.attachTypeValue}")
	private Long attachTypeValue;

	@Value("${constrOrganization.folder}")
	private String folder;

	@Autowired
	ConstrOrganizationPlanBusinessImpl constrOrganizationPlanBusinessImpl;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	@Autowired
	UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;

	// ChuongNV

	// get List all
	@Override
	public Response getConstrOrganizationPlan() {
		List<ConstrOrganizationPlanDTO> ls = constrOrganizationPlanBusinessImpl.getAll();
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

	// Delete Multi
	@Override
	public Response deleteConstrOrganizationPlan(List<String> listConstrOrgPlanId) {
		for(int i =listConstrOrgPlanId.size() - 1 ; i>=0;i-- ){
			if(constrOrganizationPlanBusinessImpl.checkStatusDatabase(listConstrOrgPlanId.get(i))){
				listConstrOrgPlanId.remove(i);
			}
		}
		if(listConstrOrgPlanId.size()>0){
			constrOrganizationPlanBusinessImpl.deleteConstrOrganizationPlan(listConstrOrgPlanId);
			return Response.ok(Response.Status.OK).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// Get Employee
	@Override
	public Response getEmployee(String contructID) {
		List<CatEmployeeDTO> ls = constrOrganizationPlanBusinessImpl.getEmployee(contructID);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response addConstrOrganizationPlan(ConstrOrganizationPlanDTO obj) throws Exception {
		Long id = constrOrganizationPlanBusinessImpl.addConstrOrganizationPlan(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			
			return Response.ok(id).build();

		}
	}

	// End ChuongNV
	@Override
	public Response getConstrOrganizationPlanById(Long id) {
		ConstrOrganizationPlanDTO obj = (ConstrOrganizationPlanDTO) constrOrganizationPlanBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateConstrOrganizationPlan(ConstrOrganizationPlanDTO obj) {
		Long id = constrOrganizationPlanBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response deleteConstrOrganizationPlan(Long id) {
		ConstrOrganizationPlanDTO obj = (ConstrOrganizationPlanDTO) constrOrganizationPlanBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			constrOrganizationPlanBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response getAllConstrOrganizationPlan(ConstrOrganizationPlanDTO dto) throws Exception {
		List<ConstrOrganizationPlanDTO> ls = constrOrganizationPlanBusinessImpl.getAllConstrOrganizationPlan(dto);
		for(ConstrOrganizationPlanDTO dto1 : ls){
			dto1.setDocumentPath(UEncrypt.encryptFileUploadPath(dto1.getDocumentPath()));
		}
		if (ls.size()==0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response exportFileListWorkOrganizationPlan(ConstrOrganizationPlanDTO data) {
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-05.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load("cvnts", EstimatesWorkItemsDTO.class, true);
			IContext context = report.createContext();

			context.put("item", data);
			File fout = new File(folder2Upload + "/BM-TCNT-05.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return Response.ok(Collections.singletonMap("fileName", "BM-TCNT-05.pdf")).build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response getFolder() {
		return Response.ok().entity(java.util.Collections.singletonMap("folder", folder)).build();
	}

	@Override
	public Response getAllConstrOrganizationPlanChild(ConstrOrganizationPlanDTO dto) throws Exception {
		List<ConstrOrganizationPlanDTO> ls = constrOrganizationPlanBusinessImpl.getAllConstrOrganizationPlanChild(dto);
		for(ConstrOrganizationPlanDTO dto1 : ls){
			dto1.setDocumentPath(UEncrypt.encryptFileUploadPath(dto1.getDocumentPath()));
		}
		if (ls.size()==0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response downloadFileZip(List<String> path, HttpServletRequest request) {	
		try {
			if (CollectionUtils.isEmpty(path)) {
            	LOGGER.warn("No files added!!");
            	return Response.status(Response.Status.BAD_REQUEST).build();
            }
			
            FileCompressor fileCompressor = new FileCompressor();
            File f = new File(folder2Upload1);
            if (!f.exists()) {
            	LOGGER.warn("{} folder is not exist!!", folder2Upload1);
            	return Response.status(Response.Status.BAD_REQUEST).build();
            }
            
        	String filePath = f.getCanonicalPath();
        	LOGGER.info("adding {} files", path.size());
        	
            for (String file : path) {
            	String srcPath = filePath + UEncrypt.decryptFileUploadPath(file);
            	String desPath = UEncrypt.decryptFileUploadPath(file).substring(UEncrypt.decryptFileUploadPath(file).lastIndexOf("\\")+1);
            	LOGGER.info("{} added", srcPath);
            	fileCompressor.add(srcPath, desPath);	
			}
            fileCompressor.setType(TYPE);
            fileCompressor.setLevel(LEVEL);
            fileCompressor.setCompressedPath(folder2Upload + File.separatorChar  +"PhuongAnToChucThiCong." + TYPE.getExtension());
            fileCompressor.compress();
            String path1  =   UEncrypt.encryptFileUploadPath("PhuongAnToChucThiCong." + TYPE.getExtension());
            
            return Response.ok(java.util.Collections.singletonMap("fileName",path1))
	        		.build();
        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}

	@Override
	public Response downloadFileZipParentChild(Long id, HttpServletRequest request) {
		List<UtilAttachedDocumentsDTO> ls = utilAttachedDocumentsBusinessImpl.getListByParentIdAndTypeOrgan(id);
		
		try {
			if (CollectionUtils.isEmpty(ls)) {
            	LOGGER.warn("No files added!!");
            	return Response.status(Response.Status.BAD_REQUEST).build();
            }
			
            FileCompressor fileCompressor = new FileCompressor();
            File f = new File(folder2Upload1);
            if (!f.exists()) {
            	LOGGER.warn("{} folder is not exist!!", folder2Upload1);
            	return Response.status(Response.Status.BAD_REQUEST).build();
            }
            
        	String filePath = f.getCanonicalPath();
        	LOGGER.info("adding {} files", ls.size());
        	
            for (UtilAttachedDocumentsDTO file : ls) {
            	String srcPath = filePath + file.getDocumentPath();
            	String desPath = file.getDocumentName();
            	LOGGER.info("{} added", srcPath);
            	fileCompressor.add(srcPath, desPath);	
			}
            fileCompressor.setType(TYPE);
            fileCompressor.setLevel(LEVEL);
            fileCompressor.setCompressedPath(folder2Upload + File.separatorChar  +"PhuongAnToChucThiCong." + TYPE.getExtension());
            fileCompressor.compress();
            String path1  =   UEncrypt.encryptFileUploadPath("PhuongAnToChucThiCong." + TYPE.getExtension());
            
            return Response.ok(java.util.Collections.singletonMap("fileName",path1))
	        		.build();
        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}

	@Override
	public Response getAttachTypeKey() {
		return Response.ok().entity(java.util.Collections.singletonMap("attachTypeKey", attachTypeKey)).build();
	}

	@Override
	public Response getAttachTypeValue() {
		return Response.ok().entity(java.util.Collections.singletonMap("attachTypeValue", attachTypeValue)).build();
	}

}
