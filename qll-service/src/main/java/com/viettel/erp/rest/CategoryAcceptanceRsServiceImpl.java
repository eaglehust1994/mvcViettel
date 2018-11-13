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
import com.viettel.erp.business.CategoryAcceptanceBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.dto.AMaterialRecoveryListDTO;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.CategoryAcceptanceDTO;
import com.viettel.erp.dto.CategoryAcceptanceExtDTO;
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
public class CategoryAcceptanceRsServiceImpl implements CategoryAcceptanceRsService {

	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;

	@Value("${folder_upload}")
	private String folder2Upload;

	static Logger LOGGER = LoggerFactory.getLogger(CategoryAcceptanceRsServiceImpl.class);

	@Value("${completionDrawing.attachType}")
	private Long attachType;

	@Value("${completionDrawing.folder}")
	private String folder;

	@Autowired
	CategoryAcceptanceBusinessImpl categoryAcceptanceBusinessImpl;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
	
	@Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;
	
	@Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;

	@Override
	public Response getCategoryAcceptance() {
		List<CategoryAcceptanceDTO> ls = categoryAcceptanceBusinessImpl.getAll();
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
	public Response getCategoryById(Long constructId, Double contractId) {
		List<CategoryAcceptanceDTO> ls = categoryAcceptanceBusinessImpl.getCategoryAcceptanceById(constructId,contractId);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getCategoryAcceptanceById(Long id) {
		CategoryAcceptanceDTO obj = (CategoryAcceptanceDTO) categoryAcceptanceBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateCategoryAcceptance(CategoryAcceptanceDTO obj) {
		CategoryAcceptanceDTO ls = (CategoryAcceptanceDTO) categoryAcceptanceBusinessImpl.getOneById(obj.getCategoryAcceptanceId());
		obj.setCreatedDate(ls.getCreatedDate());
		obj.setCreatedUserId(ls.getCreatedUserId());
		Long id = categoryAcceptanceBusinessImpl.update(obj);
		if(obj.getStatusCa() == 0L){
			String nameTable = "CATEGORY_ACCEPTANCE";
			qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getCategoryAcceptanceId(), nameTable);
		}
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addCategoryAcceptance(CategoryAcceptanceDTO obj) {
		/* DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); */
		/*Long id = 0l;*/
		Boolean check = false;
		Date date = new Date();
		String code = categoryAcceptanceBusinessImpl.autoGenCode();
		obj.setCreatedDate(date);		
		obj.setCode(code);
		
		CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("CATEGORY_ACCEPTANCE");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		
		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
		constrCompleteRecordMap.setDataTableName("CATEGORY_ACCEPTANCE");
		constrCompleteRecordMap.setDataTableId("CATEGORY_ACCEPTANCE_ID");
		constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
		//constrCompleteRecordMap.setDataTableIdValue(id);
		constrCompleteRecordMap.setCreatedDate(date);
		constrCompleteRecordMap.setStatus(0L);		
		constrCompleteRecordMap.setLevelOrder(0L);
		constrCompleteRecordMap.setCode(obj.getCode());
		constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
		constrCompleteRecordMap.setConstructionId(obj.getConstructId());
		obj.setConstrCompleteRecordsMap(constrCompleteRecordMap);
		
		
		
		List<CategoryAcceptanceDTO> list = categoryAcceptanceBusinessImpl.getCategoryAcceptanceById(obj.getConstructId(), obj.getContractId());		
		
		for(CategoryAcceptanceDTO ls : list){
			if(obj.getEstimatesItemChildId().equals(ls.getEstimatesItemChildId())){
				check = true;
				break;
			}
		}
		
		if(!check){
			try {
				
				categoryAcceptanceBusinessImpl.saveTable(obj);
			
				return Response.ok().build();
			} catch (Exception ex) {				
				LOGGER.error(ex.getMessage(), ex);
				return Response.status(Response.Status.BAD_REQUEST).build();
			}				
		}else{
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}
	
		

		
	}

	@Override
	public Response deleteCategoryAcceptance(Long id) {
		CategoryAcceptanceDTO obj = (CategoryAcceptanceDTO) categoryAcceptanceBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			categoryAcceptanceBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response getAllCategoryAcceptance() {		
		List<CategoryAcceptanceExtDTO> ls = categoryAcceptanceBusinessImpl.getAllCategoryAcceptance();
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response deleteCategoryAcceptanceList(List<Long> lisItemCode) {
		boolean ls = categoryAcceptanceBusinessImpl.deleteCategoryAcceptanceList(lisItemCode);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response getCategoryAcceptanceByIdDetail(Long categoryAcceptanceId) {
		CategoryAcceptanceDTO obj = (CategoryAcceptanceDTO) categoryAcceptanceBusinessImpl.getCategoryAcceptanceByIdDetail(categoryAcceptanceId);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response deleteCMarketPrice(Long id) {
		CategoryAcceptanceDTO obj = (CategoryAcceptanceDTO) categoryAcceptanceBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			categoryAcceptanceBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}
	

	@Override
	public Response getTwoList(Long constructId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response exportFile(CategoryAcceptanceDTO categoryDTO) {
		try {
			// for (QualityCableMeaResultDTO e : data.getListResultDTO()) {
			// System.out.println(e);
			// }

			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-14.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			// NEW API
			metadata.load("bc", CategoryAcceptanceDTO.class, true);

			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", categoryDTO);

			File fout = new File(folder2Upload + "/BM-TCNT-14.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			System.out.println(fout.getCanonicalPath());
			  String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-14.pdf");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		} /*catch (XDocReportException e) {
			e.printStackTrace();
		}*/

		return null;
	}

	@Override
	public Response exportList(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			CategoryAcceptanceDTO dto = new CategoryAcceptanceDTO();
			dto.setCategoryAcceptanceId(l);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "nghiemthubophan." + TYPE.getExtension());

			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("nghiemthubophan." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	private String exportOne(CategoryAcceptanceDTO obj) {
		try {
			CategoryAcceptanceDTO data = categoryAcceptanceBusinessImpl.getAllCategoryAcceptanceExportFile(obj);

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-14.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			//FieldsMetadata metadata = report.createFieldsMetadata();
			//metadata.load("bc", CategoryAcceptanceDTO.class, true);
			IContext context = report.createContext();

			context.put("item", data);

			File fout = new File(folder2Upload + "/" + obj.getCategoryAcceptanceId() + "-BM-TCNT-14.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return obj.getCategoryAcceptanceId() + "-BM-TCNT-14.pdf";

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	@Override
	public Response approval(approDTO obj) {
		Long result = categoryAcceptanceBusinessImpl.appro(obj);
		if (result!=0l) {
			return Response.ok(result).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@Override
	public Response exportPDF(CategoryAcceptanceDTO dto) {
		try {
			CategoryAcceptanceDTO data = categoryAcceptanceBusinessImpl.getAllCategoryAcceptanceExportFile(dto);
			
			
			 
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-14.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("yes");
            metadata.addFieldAsImage("no");
            metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
			//metadata.load("data", EstimatesWorkItemsDTO.class, true);
			
			IContext context = report.createContext();			
			context.put("item", data);
			
			IImageProvider yes = new FileImageProvider(new File(filePath + "/yes.png"));
            IImageProvider no = new FileImageProvider(new File(filePath + "/no.png"));
            IImageProvider sign1;
            IImageProvider sign2;
            if(StringUtils.isNotEmpty(data.getAmonitorNamePath())){
            	File f = new File(folder2Upload + "/" + data.getAmonitorNamePath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getMonitorIdNamePath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBinChargeConstructNamePath())){
            	File f = new File(folder2Upload + "/" + data.getBinChargeConstructNamePath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getInChargeConstructIdNamePath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(data.getConclusion() == 1){
                context.put("yes", yes);
                context.put("no", no);
            } else {
            	context.put("yes", no);
                context.put("no", yes);
            }
            if(data.getStatusCa() == 2){
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
                data.setBinChargeConstructNameSign(data.getBinChargeConstructName());
                data.setBinChargeConstructNameSign(data.getBinChargeConstructName());
            } else {
            	context.put("sign1", none);
                context.put("sign2", none);
                data.setBinChargeConstructNameSign("");
                data.setBinChargeConstructNameSign("");
            }
			
			File fout = new File(folder2Upload+"/" +"BM-TCNT-14.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-14.pdf");
			return Response.ok(Collections.singletonMap("fileName",path)).build();

		}catch ( Exception ex )
        {
			LOGGER.error(ex.getMessage(), ex);
        }/*
        catch ( XDocReportException e )
        {
            e.printStackTrace();
        }*/
		return null;
	}

	@Override
	public Response exportDOC(CategoryAcceptanceDTO dto) {
		try {
			CategoryAcceptanceDTO data = categoryAcceptanceBusinessImpl.getAllCategoryAcceptanceExportFile(dto);
		
			 
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-14.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("yes");
            metadata.addFieldAsImage("no");
            metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
			//metadata.load("data", EstimatesWorkItemsDTO.class, true);
			
			IContext context = report.createContext();				
			
			context.put("item", data);
			
			IImageProvider yes = new FileImageProvider(new File(filePath + "/yes.png"));
            IImageProvider no = new FileImageProvider(new File(filePath + "/no.png"));
            IImageProvider sign1;
            IImageProvider sign2;
            if(StringUtils.isNotEmpty(data.getAmonitorNamePath())){
            	File f = new File(folder2Upload + "/" + data.getAmonitorNamePath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getMonitorIdNamePath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBinChargeConstructNamePath())){
            	File f = new File(folder2Upload + "/" + data.getBinChargeConstructNamePath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getInChargeConstructIdNamePath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(data.getConclusion() == 1){
                context.put("yes", yes);
                context.put("no", no);
            } else {
            	context.put("yes", no);
                context.put("no", yes);
            }
            if(data.getStatusCa() == 2){
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
                data.setBinChargeConstructNameSign(data.getBinChargeConstructName());
                data.setBinChargeConstructNameSign(data.getBinChargeConstructName());
            } else {
            	context.put("sign1", none);
                context.put("sign2", none);
                data.setBinChargeConstructNameSign("");
                data.setBinChargeConstructNameSign("");
            }
			
			File fout = new File(folder2Upload+"/" +"BM-TCNT-14.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-14.docx");
			return Response.ok(Collections.singletonMap("fileName",path))
	        		.build();

		}catch ( Exception ex )
        {
			LOGGER.error(ex.getMessage(), ex);
        }/*
        catch ( XDocReportException e )
        {
            e.printStackTrace();
        }*/
		return null;
	}
	
	 public String exportoneDOC(CategoryAcceptanceDTO obj){
		 CategoryAcceptanceDTO data = categoryAcceptanceBusinessImpl.getAllCategoryAcceptanceExportFile(obj);
	    	/*data.setCvntList(categoryAcceptanceBusinessImpl.doSearchByWorkItemsAcceptanceId(obj.getCategoryAcceptanceId()));
	    	for (int j = 0; j < data.getCvntList().size(); j++) {
	    		EstimatesWorkItemsDTO dto = data.getCvntList().get(j);
	    		dto.setStt(j + 1);
			}*/
	    	try
	        {
	            // 1) Load Docx file by filling Freemarker template engine and cache
	            // it to the registry
	    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	        	String filePath = classloader.getResource("../" + "doc-template").getPath();
	        	
	        	InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-14.docx"));
	            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );

	            // 2) Create fields metadata to manage lazy loop ([#list Freemarker) for foot notes.
	            FieldsMetadata metadata = report.createFieldsMetadata();
	            metadata.addFieldAsImage("yes");
	            metadata.addFieldAsImage("no");
	            metadata.addFieldAsImage("sign1");
	            metadata.addFieldAsImage("sign2");
	            //metadata.load( "cvnts", EstimatesWorkItemsDTO.class, true );
	            // 3) Create context Java model
	            IContext context = report.createContext();
	            
	            context.put( "item", data );
	            //context.put( "cvnts", data.getCvntList() );
	            IImageProvider yes = new FileImageProvider(new File(filePath + "/yes.png"));
	            IImageProvider no = new FileImageProvider(new File(filePath + "/no.png"));
	            IImageProvider sign1;
	            IImageProvider sign2;
	            if(StringUtils.isNotEmpty(data.getAmonitorNamePath())){
	            	File f = new File(folder2Upload + "/" + data.getAmonitorNamePath());
	            	if(f.exists() && !f.isDirectory()) { 
	                    sign1 = new FileImageProvider(f);
	                }else{
	                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	                }
	            	//sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getMonitorIdNamePath()));
	            } else{
	            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            if(StringUtils.isNotEmpty(data.getBinChargeConstructNamePath())){
	            	File f = new File(folder2Upload + "/" + data.getBinChargeConstructNamePath());
	            	if(f.exists() && !f.isDirectory()) { 
	                    sign2 = new FileImageProvider(f);
	                }else{
	                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	                }
	            	//sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getInChargeConstructIdNamePath()));
	            } else{
	            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
	            if(data.getConclusion() == 1){
	                context.put("yes", yes);
	                context.put("no", no);
	            } else {
	            	context.put("yes", no);
	                context.put("no", yes);
	            }
	            if(data.getStatusCa() == 2){
	            	context.put("sign1", sign1);
	                context.put("sign2", sign2);
	                data.setBinChargeConstructNameSign(data.getBinChargeConstructName());
	                data.setBinChargeConstructNameSign(data.getBinChargeConstructName());
	            } else {
	            	context.put("sign1", none);
	                context.put("sign2", none);
	                data.setBinChargeConstructNameSign("");
	                data.setBinChargeConstructNameSign("");
	            }
	            //pdf
	            File fout = new File(folder2Upload + "/" + obj.getCategoryAcceptanceId() +"-BM-TCNT-14.docx");
	            OutputStream out = new FileOutputStream(fout);
	            report.process( context, out );
				out.flush();
				out.close();
				return obj.getCategoryAcceptanceId()+"-BM-TCNT-14.docx";
	        }
	        catch ( Exception ex )
	        {
	        	LOGGER.error(ex.getMessage(), ex);
	        }/*
	        catch ( XDocReportException e )
	        {
	            e.printStackTrace();
	        }*/
	    	
	    	return null;
	    }

	@Override
	public Response exportListDocCategoryAcceptence(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			CategoryAcceptanceDTO dto = new CategoryAcceptanceDTO();
			dto.setCategoryAcceptanceId(l);
			String filename = exportoneDOC(dto);
			if(filename != null) {
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
            fileCompressor.setCompressedPath(filePath + File.separatorChar  +"nghiemthubophan." + TYPE.getExtension());
            fileCompressor.compress();
            
            String path  =   UEncrypt.encryptFileUploadPath("nghiemthubophan." + TYPE.getExtension());
            return Response.ok(java.util.Collections.singletonMap("fileName",path))
	        		.build();
        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}

	 public String exportonePDF(CategoryAcceptanceDTO obj){
		 CategoryAcceptanceDTO data = categoryAcceptanceBusinessImpl.getAllCategoryAcceptanceExportFile(obj);
	    	/*data.setCvntList(categoryAcceptanceBusinessImpl.doSearchByWorkItemsAcceptanceId(obj.getCategoryAcceptanceId()));
	    	for (int j = 0; j < data.getCvntList().size(); j++) {
	    		EstimatesWorkItemsDTO dto = data.getCvntList().get(j);
	    		dto.setStt(j + 1);
			}*/
	    	try
	        {
	            // 1) Load Docx file by filling Freemarker template engine and cache
	            // it to the registry
	    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	        	String filePath = classloader.getResource("../" + "doc-template").getPath();
	        	
	        	InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-14.docx"));
	            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );

	            // 2) Create fields metadata to manage lazy loop ([#list Freemarker) for foot notes.
	            FieldsMetadata metadata = report.createFieldsMetadata();
	            metadata.addFieldAsImage("yes");
	            metadata.addFieldAsImage("no");
	            metadata.addFieldAsImage("sign1");
	            metadata.addFieldAsImage("sign2");
	            //metadata.load( "cvnts", EstimatesWorkItemsDTO.class, true );
	            // 3) Create context Java model
	            IContext context = report.createContext();
	            
	            context.put( "item", data );
	            //context.put( "cvnts", data.getCvntList() );
	            IImageProvider yes = new FileImageProvider(new File(filePath + "/yes.png"));
	            IImageProvider no = new FileImageProvider(new File(filePath + "/no.png"));
	            IImageProvider sign1;
	            IImageProvider sign2;
	            if(StringUtils.isNotEmpty(data.getAmonitorNamePath())){
	            	File f = new File(folder2Upload + "/" + data.getAmonitorNamePath());
	            	if(f.exists() && !f.isDirectory()) { 
	                    sign1 = new FileImageProvider(f);
	                }else{
	                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	                }
	            	//sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getMonitorIdNamePath()));
	            } else{
	            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            if(StringUtils.isNotEmpty(data.getBinChargeConstructNamePath())){
	            	File f = new File(folder2Upload + "/" + data.getBinChargeConstructNamePath());
	            	if(f.exists() && !f.isDirectory()) { 
	                    sign2 = new FileImageProvider(f);
	                }else{
	                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	                }
	            	//sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getInChargeConstructIdNamePath()));
	            } else{
	            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
	            if(data.getConclusion() == 1){
	                context.put("yes", yes);
	                context.put("no", no);
	            } else {
	            	context.put("yes", no);
	                context.put("no", yes);
	            }
	            if(data.getStatusCa() == 2){
	            	context.put("sign1", sign1);
	                context.put("sign2", sign2);
	                data.setBinChargeConstructNameSign(data.getBinChargeConstructName());
	                data.setBinChargeConstructNameSign(data.getBinChargeConstructName());
	            } else {
	            	context.put("sign1", none);
	                context.put("sign2", none);
	                data.setBinChargeConstructNameSign("");
	                data.setBinChargeConstructNameSign("");
	            }
	            //pdf
	            File fout = new File(folder2Upload + "/" + obj.getCategoryAcceptanceId() +"-BM-TCNT-14.pdf");
	            OutputStream out = new FileOutputStream(fout);
	            Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
	            report.convert( context, options, out );
				out.flush();
				out.close();
				return obj.getCategoryAcceptanceId()+"-BM-TCNT-14.pdf";
	        }
	        catch ( Exception ex )
	        {
	        	LOGGER.error(ex.getMessage(), ex);
	        }/*
	        catch ( XDocReportException e )
	        {
	            e.printStackTrace();
	        }*/
	    	
	    	return null;
	    }
	
	@Override
	public Response exportListPDFCategoryAcceptence(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			CategoryAcceptanceDTO dto = new CategoryAcceptanceDTO();
			dto.setCategoryAcceptanceId(l);
			String filename = exportonePDF(dto);
			if(filename != null) {
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
            fileCompressor.setCompressedPath(filePath + File.separatorChar  +"nghiemthubophan." + TYPE.getExtension());
            fileCompressor.compress();
            
            String path  =   UEncrypt.encryptFileUploadPath("nghiemthubophan." + TYPE.getExtension());
            return Response.ok(java.util.Collections.singletonMap("fileName",path))
	        		.build();
        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}


}
