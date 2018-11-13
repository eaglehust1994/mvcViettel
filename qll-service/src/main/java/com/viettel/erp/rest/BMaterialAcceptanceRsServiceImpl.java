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
import java.util.ArrayList;
import java.util.Collections;
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
import com.viettel.erp.business.BMaterialAcceptanceBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.dto.BMaterialAcceptMerListDTO;
import com.viettel.erp.dto.BMaterialAcceptanceDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
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
public class BMaterialAcceptanceRsServiceImpl implements BMaterialAcceptanceRsService {

	private static final CompressionType TYPE = CompressionType.ZIP;
    private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
    
    static Logger LOGGER = LoggerFactory.getLogger(BMaterialAcceptanceRsServiceImpl.class);
    
    @Value("${completionDrawing.attachType}")
    private Long attachType;

    @Value("${completionDrawing.folder}")
 	private String folder;
 
	@Value("${folder_upload}")
	private String folder2Upload;
	
    @Autowired
    BMaterialAcceptanceBusinessImpl bMaterialAcceptanceBusinessImpl;
    
    @Autowired
    ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

    @Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;
    
    @Override
    public Response getBMaterialAcceptance() {
        List<BMaterialAcceptanceDTO> ls = bMaterialAcceptanceBusinessImpl.getAll();
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
    public Response getBMaterialAcceptanceById(Long id) {
        BMaterialAcceptanceDTO obj = (BMaterialAcceptanceDTO) bMaterialAcceptanceBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateBMaterialAcceptance(BMaterialAcceptanceDTO obj) {
        Long id = bMaterialAcceptanceBusinessImpl.update(obj);
        if(obj.getStatusCa() == 0L){
        	String nameTable = "B_MATERIAL_ACCEPTANCE";
        	qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getBmaterialAcceptanceId(), nameTable);
        }
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addBMaterialAcceptance(BMaterialAcceptanceDTO obj) {
    	Long constructionId = obj.getConstructId();	    	
    	String code = bMaterialAcceptanceBusinessImpl.autoGenCode();
    	obj.setCode(code);
        Long id = bMaterialAcceptanceBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	try {
				 constrCompleteRecordsMapBusinessImpl.insert(constructionId, "B_MATERIAL_ACCEPTANCE",
							"B_MATERIAL_ACCEPTANCE_ID", id, obj.getCreatedUserId(), code);
				} catch (Exception ex) {
					LOGGER.error(ex.getMessage(), ex);
					bMaterialAcceptanceBusinessImpl.delete(obj);
				}
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteBMaterialAcceptance(Long id) {
        BMaterialAcceptanceDTO obj = (BMaterialAcceptanceDTO) bMaterialAcceptanceBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            bMaterialAcceptanceBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
	@Override
	public Response findByConstructId(BMaterialAcceptanceDTO dto) {
        List<BMaterialAcceptanceDTO> ls = bMaterialAcceptanceBusinessImpl.findByConstructId(dto);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
	
	@Override
	public Response deleteResult(List<String> listString) {
		
		boolean ls = bMaterialAcceptanceBusinessImpl.deleteResult(listString);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
//	@Override
//	public Response getListEmployeeByRole(SettlementRightDTO rightDTO) {
//		List<CatEmployeeDTO> obj = bMaterialAcceptanceBusinessImpl.getListEmployeeByRole(rightDTO);
//	        if (obj == null) {
//	            return Response.status(Response.Status.BAD_REQUEST).build();
//	        } else {
//	            return Response.ok(obj).build();
//	        }
//	}
	
	  
//	    public Response exportFile(BMaterialAcceptanceDTO data){
//	    	
//	    	for (int j = 0; j < data.getBmaterialAcceptMerList().size(); j++) {
//	    		BMaterialAcceptMerListDTO dto = data.getBmaterialAcceptMerList().get(j);
//	    	      dto.setStt(new Integer(j + 1));
//	    	  }
//	    	
//	    	try
//	        {
////	    		for (QualityCableMeaResultDTO e : data.getListResultDTO()) {
////					System.out.println(e);
////				}
//	    		
//	            // 1) Load Docx file by filling Freemarker template engine and cache
//	            // it to the registry
//	    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//	        	String filePath = classloader.getResource("../" + "doc-template").getPath();
//	        	
//	        	InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-07.docx"));
//	            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );
//
//	            // 2) Create fields metadata to manage lazy loop ([#list Freemarker) for foot notes.
//	            FieldsMetadata metadata = report.createFieldsMetadata();
//	            // NEW API
//	            metadata.load( "bc", BMaterialAcceptanceDTO.class, true );
//	            
//	            // 3) Create context Java model
//	            IContext context = report.createContext();
//	            
//	            context.put( "item", data );
//	            context.put( "bc", data.getBmaterialAcceptMerList());
//
//	            /*// 4) Generate report by merging Java model with the Docx
//	            //OutputStream out = new FileOutputStream( new File( "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) );
//	            OutputStream out = new FileOutputStream( new File( "D:\\WORK\\Test\\BM-TCNT-10.docx" ) );
//	            report.process( context, out );*/
//	            
//	            //pdf
//	            File fout = new File(folder2Upload + "/BM-TCNT-07.pdf");
//	            OutputStream out = new FileOutputStream(fout);
//				Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
//				report.convert(context, options, out);
//				out.flush();
//				out.close();
//				System.out.println(fout.getCanonicalPath());
//				return Response.ok(Collections.singletonMap("fileName", "BM-TCNT-07.pdf"))
//		        		.build();
//	        }
//	        catch ( IOException e )
//	        {
//	            e.printStackTrace();
//	        }
//	        catch ( XDocReportException e )
//	        {
//	            e.printStackTrace();
//	        }
//	    	
//	    	return null;
//	    }
		
//	    public Response exportFileDoc(BMaterialAcceptanceDTO data){
//	    	
//	    	for (int j = 0; j < data.getBmaterialAcceptMerList().size(); j++) {
//	    		BMaterialAcceptMerListDTO dto = data.getBmaterialAcceptMerList().get(j);
//	    	      dto.setStt(new Integer(j + 1));
//	    	  }
//	    	
//	    	try
//	        {
////	    		for (QualityCableMeaResultDTO e : data.getListResultDTO()) {
////					System.out.println(e);
////				}
//	    		
//	            // 1) Load Docx file by filling Freemarker template engine and cache
//	            // it to the registry
//	    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//	        	String filePath = classloader.getResource("../" + "doc-template").getPath();
//	        	
//	        	InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-07.docx"));
//	            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );
//
//	            // 2) Create fields metadata to manage lazy loop ([#list Freemarker) for foot notes.
//	            FieldsMetadata metadata = report.createFieldsMetadata();
//	            // NEW API
//	            metadata.load( "bc", BMaterialAcceptanceDTO.class, true );
//	            
//	            // 3) Create context Java model
//	            IContext context = report.createContext();
//	            
//	            context.put( "item", data );
//	            context.put( "bc", data.getBmaterialAcceptMerList());
//
//	            /*// 4) Generate report by merging Java model with the Docx
//	            //OutputStream out = new FileOutputStream( new File( "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) );
//	            OutputStream out = new FileOutputStream( new File( "D:\\WORK\\Test\\BM-TCNT-10.docx" ) );
//	            report.process( context, out );*/
//	            
//	            //doc
//	            File fout = new File(folder2Upload + "/BM-TCNT-07.docx");
//	            OutputStream out = new FileOutputStream(fout);
//	            report.process(context, out);
//	            
//	            //pdf
////	            File fout = new File(folder2Upload + "/BM-TCNT-07.pdf");
////	            OutputStream out = new FileOutputStream(fout);
////				Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
////				report.convert(context, options, out);
//				out.flush();
//				out.close();
//				System.out.println(fout.getCanonicalPath());
//				return Response.ok(Collections.singletonMap("fileName", "BM-TCNT-07.docx"))
//		        		.build();
//	        }
//	        catch ( IOException e )
//	        {
//	            e.printStackTrace();
//	        }
//	        catch ( XDocReportException e )
//	        {
//	            e.printStackTrace();
//	        }
//	    	
//	    	return null;
//	    }
	    

	@Override
	public Response exportFilePDFDetail(BMaterialAcceptanceDTO data) {
		
  	  try {
  	   BMaterialAcceptanceDTO exportbmaDTO = bMaterialAcceptanceBusinessImpl.exportBMA(data);
         System.out.println("++++"+exportbmaDTO.getConclusion());
  	    List<BMaterialAcceptMerListDTO> bmalist = bMaterialAcceptanceBusinessImpl.getBMAMerList(data.getBmaterialAcceptanceId());
  	    exportbmaDTO.setBmaterialAcceptMerList(bmalist);

  	    for (int j = 0; j < exportbmaDTO.getBmaterialAcceptMerList().size(); j++) {
  	     exportbmaDTO.getBmaterialAcceptMerList().get(j).setStt(j+1);
  	    }
  	    
  	    exportbmaDTO.setConclusionString(exportbmaDTO.getConclusion()==1?"Đồng ý":"Từ chối");
  	       
  	   System.out.println(exportbmaDTO);
  	   System.out.println("bmalist:"+bmalist);
  	   ClassLoader classloader = Thread.currentThread().getContextClassLoader();
  	   String filePath = classloader.getResource("../" + "doc-template").getPath();

  	   InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-07.docx"));
  	   IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

  	   FieldsMetadata metadata = report.createFieldsMetadata();
  	   //chan ky
	   metadata.addFieldAsImage("sign1");
       metadata.addFieldAsImage("sign2");
       //chan ky
  	   metadata.load("bc", BMaterialAcceptMerListDTO.class, true);
  	   
  	   IContext context = report.createContext();
  	   context.put("item", exportbmaDTO);   
  	   context.put("bc", exportbmaDTO.getBmaterialAcceptMerList());
	   //begin chan ky
	   
		IImageProvider sign1;
        IImageProvider sign2;
        
        if(StringUtils.isNotEmpty(exportbmaDTO.getAmonitoridPath())){
        	sign1 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getAmonitoridPath()));
        } else{
        	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
        }
        if(StringUtils.isNotEmpty(exportbmaDTO.getBinchargeconstructidPath())){
        	sign2 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getBinchargeconstructidPath()));
        } else{
        	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
        }
      
        IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
        if(exportbmaDTO.getStatusCa() == 2){
        	context.put("sign1", sign1);
            context.put("sign2", sign2);
            exportbmaDTO.setAmonitoridSign(exportbmaDTO.getAfullName());
            exportbmaDTO.setBinchargeconstructidSign(exportbmaDTO.getBfullName());
        } else {
        	context.put("sign1", none);
            context.put("sign2", none);
            exportbmaDTO.setAmonitoridSign("");
            exportbmaDTO.setBinchargeconstructidSign("");
        }
	 
        //end chan ky
  	  
  	   

//  	   //---
  	   
  	   File fout = new File(folder2Upload+"/" + exportbmaDTO.getBmaterialAcceptanceId() +"-BM-TCNT-07.pdf");
	   OutputStream out = new FileOutputStream(fout);
	   Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
	   report.convert(context, options, out);
  	   //---
  	   out.flush();
  	   out.close();
//  	   return exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.docx";
  	 String path  =   UEncrypt.encryptFileUploadPath(exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.pdf");
  	 return Response.ok(Collections.singletonMap("fileName",path)).build();
//     		.build();
  	  } catch (Exception ex) {
  		LOGGER.error(ex.getMessage(), ex);
  	  }
  	  return null;
  	 
	}
	
	@Override
	public Response exportFileDOCDetail(BMaterialAcceptanceDTO data) {
		
	  	  try {
	  	   BMaterialAcceptanceDTO exportbmaDTO = bMaterialAcceptanceBusinessImpl.exportBMA(data);
	     
	  	    List<BMaterialAcceptMerListDTO> bmalist = bMaterialAcceptanceBusinessImpl.getBMAMerList(data.getBmaterialAcceptanceId());
	  	    exportbmaDTO.setBmaterialAcceptMerList(bmalist);

	  	    for (int j = 0; j < exportbmaDTO.getBmaterialAcceptMerList().size(); j++) {
	  	     exportbmaDTO.getBmaterialAcceptMerList().get(j).setStt(j+1);
	  	    }
	  	    
	  	    exportbmaDTO.setConclusionString(exportbmaDTO.getConclusion()==1?"Đồng ý":"Từ chối");
	  	       
	  	   System.out.println(exportbmaDTO);
	  	   System.out.println("bmalist:"+bmalist);
	  	   ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	  	   String filePath = classloader.getResource("../" + "doc-template").getPath();

	  	   InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-07.docx"));
	  	   IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);



	  	   
	  	   FieldsMetadata metadata = report.createFieldsMetadata();
		   metadata.addFieldAsImage("sign1");
	       metadata.addFieldAsImage("sign2");
	  	   metadata.load("bc", BMaterialAcceptMerListDTO.class, true);
	  	   
	  	   IContext context = report.createContext();
	  	   context.put("item", exportbmaDTO);	  	   
	  	   context.put("bc", exportbmaDTO.getBmaterialAcceptMerList());
    	   //begin chan ky
    	   
			IImageProvider sign1;
            IImageProvider sign2;
            
            if(StringUtils.isNotEmpty(exportbmaDTO.getAmonitoridPath())){
            	sign1 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getAmonitoridPath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(exportbmaDTO.getBinchargeconstructidPath())){
            	sign2 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getBinchargeconstructidPath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
          
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(exportbmaDTO.getStatusCa() == 2){
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
                exportbmaDTO.setAmonitoridSign(exportbmaDTO.getAfullName());
                exportbmaDTO.setBinchargeconstructidSign(exportbmaDTO.getBfullName());
            } else {
            	context.put("sign1", none);
                context.put("sign2", none);
                exportbmaDTO.setAmonitoridSign("");
                exportbmaDTO.setBinchargeconstructidSign("");
            }
    	 
            //end chan ky
	  	  
	  	   

//	  	   //---
	  	   
	  	   File fout = new File(folder2Upload+"/" + exportbmaDTO.getBmaterialAcceptanceId() +"-BM-TCNT-07.docx");
		   OutputStream out = new FileOutputStream(fout);
		   //Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
		   report.process(context, out);
	  	   //---
	  	   out.flush();
	  	   out.close();
//	  	   return exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.docx";
	  	 String path  =   UEncrypt.encryptFileUploadPath(exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.docx");
	  	 return Response.ok(Collections.singletonMap("fileName",path)).build();
//	     		.build();
	  	  } catch (Exception ex) {
	  		LOGGER.error(ex.getMessage(), ex);
	  	  }
	  	  return null;
	  	 
		}
	
	
	    private String exportOne(BMaterialAcceptanceDTO data) {
	    	 try {
	    	  	   BMaterialAcceptanceDTO exportbmaDTO = bMaterialAcceptanceBusinessImpl.exportBMA(data);
	    	         System.out.println("++++"+exportbmaDTO.getConclusion());
	    	  	    List<BMaterialAcceptMerListDTO> bmalist = bMaterialAcceptanceBusinessImpl.getBMAMerList(data.getBmaterialAcceptanceId());
	    	  	    exportbmaDTO.setBmaterialAcceptMerList(bmalist);

	    	  	    for (int j = 0; j < exportbmaDTO.getBmaterialAcceptMerList().size(); j++) {
	    	  	     exportbmaDTO.getBmaterialAcceptMerList().get(j).setStt(j+1);
	    	  	    }
	    	  	    
	    	  	    exportbmaDTO.setConclusionString(exportbmaDTO.getConclusion()==1?"Đồng ý":"Từ chối");
	    	  	       
	    	  	   System.out.println(exportbmaDTO);
	    	  	   System.out.println("bmalist:"+bmalist);
	    	  	   ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    	  	   String filePath = classloader.getResource("../" + "doc-template").getPath();

	    	  	   InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-07.docx"));
	    	  	   IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

	    	  	   FieldsMetadata metadata = report.createFieldsMetadata();
	    	  	   //chan ky
	    		   metadata.addFieldAsImage("sign1");
	    	       metadata.addFieldAsImage("sign2");
	    	       //chan ky
	    	  	   metadata.load("bc", BMaterialAcceptMerListDTO.class, true);
	    	  	   
	    	  	   IContext context = report.createContext();
	    	  	   context.put("item", exportbmaDTO);   
	    	  	   context.put("bc", exportbmaDTO.getBmaterialAcceptMerList());
	    		   //begin chan ky
	    		   
	    			IImageProvider sign1;
	    	        IImageProvider sign2;
	    	        
	    	        if(StringUtils.isNotEmpty(exportbmaDTO.getAmonitoridPath())){
	    	        	sign1 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getAmonitoridPath()));
	    	        } else{
	    	        	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	    	        }
	    	        if(StringUtils.isNotEmpty(exportbmaDTO.getBinchargeconstructidPath())){
	    	        	sign2 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getBinchargeconstructidPath()));
	    	        } else{
	    	        	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	    	        }
	    	      
	    	        IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
	    	        if(exportbmaDTO.getStatusCa() == 2){
	    	        	context.put("sign1", sign1);
	    	            context.put("sign2", sign2);
	    	            exportbmaDTO.setAmonitoridSign(exportbmaDTO.getAfullName());
		                exportbmaDTO.setBinchargeconstructidSign(exportbmaDTO.getBfullName());
	    	        } else {
	    	        	context.put("sign1", none);
	    	            context.put("sign2", none);
	    	            exportbmaDTO.setAmonitoridSign("");
		                exportbmaDTO.setBinchargeconstructidSign("");
	    	        }
	    		 
	    	        //end chan ky
	    	  	  
	    	  	   

//	    	  	   //---
	    	  	   
	    	  	   File fout = new File(folder2Upload+"/" + exportbmaDTO.getBmaterialAcceptanceId() +"-BM-TCNT-07.pdf");
	    		   OutputStream out = new FileOutputStream(fout);
	    		   Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
	    		   report.convert(context, options, out);
	    	  	   //---
	    	  	   out.flush();
	    	  	   out.close();
	    	  	   return exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.pdf";
//	    	  	   return exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.docx";
//	    	  	 return Response.ok(Collections.singletonMap("fileName",exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.pdf")).build();
//	    	     		.build();
	    	  	  } catch (Exception ex) {
	    	  		LOGGER.error(ex.getMessage(), ex);
	    	  	  }
	    	  	  return null;
	    	  	 
	    	 }
	    

	    private String exportOneDoc(BMaterialAcceptanceDTO data) {
	    	  try {
	   	  	   BMaterialAcceptanceDTO exportbmaDTO = bMaterialAcceptanceBusinessImpl.exportBMA(data);
	  	     
		  	    List<BMaterialAcceptMerListDTO> bmalist = bMaterialAcceptanceBusinessImpl.getBMAMerList(data.getBmaterialAcceptanceId());
		  	    exportbmaDTO.setBmaterialAcceptMerList(bmalist);

		  	    for (int j = 0; j < exportbmaDTO.getBmaterialAcceptMerList().size(); j++) {
		  	     exportbmaDTO.getBmaterialAcceptMerList().get(j).setStt(j+1);
		  	    }
		  	    
		  	    exportbmaDTO.setConclusionString(exportbmaDTO.getConclusion()==1?"Đồng ý":"Từ chối");
		  	       
		  	   System.out.println(exportbmaDTO);
		  	   System.out.println("bmalist:"+bmalist);
		  	   ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		  	   String filePath = classloader.getResource("../" + "doc-template").getPath();

		  	   InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-07.docx"));
		  	   IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);



		  	   
		  	   FieldsMetadata metadata = report.createFieldsMetadata();
			   metadata.addFieldAsImage("sign1");
		       metadata.addFieldAsImage("sign2");
		  	   metadata.load("bc", BMaterialAcceptMerListDTO.class, true);
		  	   
		  	   IContext context = report.createContext();
		  	   context.put("item", exportbmaDTO);	  	   
		  	   context.put("bc", exportbmaDTO.getBmaterialAcceptMerList());
	    	   //begin chan ky
	    	   
				IImageProvider sign1;
	            IImageProvider sign2;
	            
	            if(StringUtils.isNotEmpty(exportbmaDTO.getAmonitoridPath())){
	            	sign1 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getAmonitoridPath()));
	            } else{
	            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            if(StringUtils.isNotEmpty(exportbmaDTO.getBinchargeconstructidPath())){
	            	sign2 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getBinchargeconstructidPath()));
	            } else{
	            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	          
	            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
	            if(exportbmaDTO.getStatusCa() == 2){
	            	context.put("sign1", sign1);
	                context.put("sign2", sign2);
	                exportbmaDTO.setAmonitoridSign(exportbmaDTO.getAfullName());
	                exportbmaDTO.setBinchargeconstructidSign(exportbmaDTO.getBfullName());
	            } else {
	            	exportbmaDTO.setAmonitoridSign("");
	                exportbmaDTO.setBinchargeconstructidSign("");
	            	context.put("sign1", none);
	                context.put("sign2", none);
	            }
	    	 
	            //end chan ky
		  	  
		  	   

//		  	   //---
		  	   
		  	   File fout = new File(folder2Upload+"/" + exportbmaDTO.getBmaterialAcceptanceId() +"-BM-TCNT-07.docx");
			   OutputStream out = new FileOutputStream(fout);
			   //Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			   report.process(context, out);
		  	   //---
		  	   out.flush();
		  	   out.close();
//		  	   return exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.docx";
		  	 return exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.docx";
//		     		.build();
		  	  } catch (Exception ex) {
		  		LOGGER.error(ex.getMessage(), ex);
	    	  }
	    	  return null;
	    	 }
	    
	    //minhpvn export list pdf
	    @Override
	    public Response exportList(List<Long> data) {
	     List<String> listFileName = new ArrayList<String>();
	     for (Long l : data) {
	      BMaterialAcceptanceDTO dto = new BMaterialAcceptanceDTO();
	      dto.setBmaterialAcceptanceId(l);
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
	      fileCompressor.setCompressedPath(filePath + File.separatorChar + "nghiemthuchatluongvattubcap." + TYPE.getExtension());
	      
	      fileCompressor.compress();
	      String path  =   UEncrypt.encryptFileUploadPath("nghiemthuchatluongvattubcap." + TYPE.getExtension());
	      return Response.ok(java.util.Collections.singletonMap("fileName", path))
	        .build();
	     } catch (Exception ex) {
	    	 LOGGER.error(ex.getMessage(), ex);
	     }
	     return null;
	    }
	    
	    //minhpvn export list doc 
	    @Override
	    public Response exportListDoc(List<Long> data) {
	     List<String> listFileName = new ArrayList<String>();
	     for (Long l : data) {
	      BMaterialAcceptanceDTO dto = new BMaterialAcceptanceDTO();
	      dto.setBmaterialAcceptanceId(l);
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
	      fileCompressor.setCompressedPath(filePath + File.separatorChar + "nghiemthuchatluongvattubcap." + TYPE.getExtension());
	      
	      fileCompressor.compress();
	      String path  =   UEncrypt.encryptFileUploadPath("nghiemthuchatluongvattubcap." + TYPE.getExtension());
	      return Response.ok(java.util.Collections.singletonMap("fileName", path))
	        .build();
	     } catch (Exception ex) {
	    	 LOGGER.error(ex.getMessage(), ex);
	     }
	     return null;
	    }


		@Override
		public Response appro(approDTO obj) {
              Long id = bMaterialAcceptanceBusinessImpl.getconstrCompReMapId(obj.getBmaterialAcceptanceId());
			obj.setConstrCompReMapId(id);
			Long result =bMaterialAcceptanceBusinessImpl.appro(obj);
			if (result >=0) {
				return Response.ok(result).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		}

		@Override
		public Response deleteListEntity(List<Long> listLong) {
		Boolean result = 	bMaterialAcceptanceBusinessImpl.deleteListEntity(listLong);
		if(result == true){
			return Response.ok(result).build();
		}else{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		}

		@Override
		public Response downloadTempleBmaterial(BMaterialAcceptanceDTO obj) throws Exception {
			String path  =   UEncrypt.encryptFileUploadPath("ImportBmaterial_Temp.xlsx");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
		}
	    
}
