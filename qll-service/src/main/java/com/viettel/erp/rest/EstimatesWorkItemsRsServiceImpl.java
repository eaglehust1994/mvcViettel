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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.viettel.erp.business.CompletionDrawingBusinessImpl;
import com.viettel.erp.business.EstimatesWorkItemsBusinessImpl;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.ktts2.common.UEncrypt;

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
import net.sf.jxls.transformer.XLSTransformer;
/**
 *
 * @author HungLQ9
 */
public class EstimatesWorkItemsRsServiceImpl implements EstimatesWorkItemsRsService {

	
	@Value("${folder_upload}")
	private String folder2Upload;
	static Logger LOGGER = LoggerFactory.getLogger(EstimatesWorkItemsRsServiceImpl.class);
    @Autowired
    EstimatesWorkItemsBusinessImpl estimatesWorkItemsBusinessImpl;
    
    @Autowired
    CompletionDrawingBusinessImpl completionDrawingBusinessImpl;
    @Override
	public Response pauseWorkItem(List<Long> listWorkItem) {
    	Long l = estimatesWorkItemsBusinessImpl.pauseWorkItem(listWorkItem);
        if (l == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }
	}
    
    
    @Override
	public Response updateStatus(Long estimatesWorkItemId) {
    	estimatesWorkItemsBusinessImpl.updateStatus(estimatesWorkItemId);
		return Response.ok(Response.Status.OK).build();
	}
    
    @Override
	public Response getWorkItemNotDone(EstimatesWorkItemsDTO obj) {
    	List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getWorkItemNotDone(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
    
    @Override
	public Response getWorkItemDone(EstimatesWorkItemsDTO obj) {
    	List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getWorkItemDone(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

    //tungpv
    @Override
    public Response doSearchEstimatesWorkItems(List<EstimatesWorkItemsDTO> criteria) {
    	List<EstimatesWorkItemsDTO> obj=Lists.newArrayList();
    	for(EstimatesWorkItemsDTO criteri:criteria){
        List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.doSearchEstimatesWorkItems(criteri);
        
        if(ls.size()>0){
        	for(EstimatesWorkItemsDTO l:ls){
        		obj.add(l);
        	}
        	}
    	}
    	  return Response.ok(obj).build();
    }
    
    @Override
    public Response getAllEstimatesWorkItems(Long id) {
        List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getAllEstimatesWorkItems(id);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
    }

    @Override
	public Response doSearchByWorkItemsAcceptanceId(WorkItemsAcceptanceDTO dto) {
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.doSearchByWorkItemsAcceptanceId(dto);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
    //end tungpv
    
    @Override
    public Response getEstimatesWorkItems() {
        List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
    }

    @Override
    public Response getEstimatesWorkItemsById(Long id) {
        EstimatesWorkItemsDTO obj = (EstimatesWorkItemsDTO) estimatesWorkItemsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateEstimatesWorkItems(EstimatesWorkItemsDTO obj) {
        Long id = estimatesWorkItemsBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addEstimatesWorkItems(EstimatesWorkItemsDTO obj) {
        Long id = estimatesWorkItemsBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteEstimatesWorkItems(Long id) {
        EstimatesWorkItemsDTO obj = (EstimatesWorkItemsDTO) estimatesWorkItemsBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            estimatesWorkItemsBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }


	@Override
	public Response getEstimatesWorkItemsSearchAccept(EstimatesWorkItemsDTO obj) {
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getEstimatesWorkItemsSearchAccept(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Response getWorkItem(EstimatesWorkItemsDTO obj)  {
		
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getWorkItem(obj);
		if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
       
	}
    private void saveWorkbook(Workbook resultWorkbook, String fileName) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
        resultWorkbook.write(os);
        os.flush();
        os.close();
    }
    
    
    
    
	@Override
	public Response exportWorkItem(EstimatesWorkItemsDTO obj) throws InvalidFormatException, IOException,Exception {
		
		CompletionDrawingDTO drawdto = new CompletionDrawingDTO();
		drawdto.setConstructId(obj.getConstructionId());
		
		
		    Map beans = new HashMap();
	        List<EstimatesWorkItemsDTO> workitemin = estimatesWorkItemsBusinessImpl.getWorkItemIn(obj);
	        List<EstimatesWorkItemsDTO> workitemout = estimatesWorkItemsBusinessImpl.getWorkItemOut(obj);
	        
	        
	        for(int i = 0 ; i< workitemin.size() ; i++){
	        	workitemin.get(i).setRownum(i+1);
	        }
	        for(int i = 0 ; i< workitemout.size() ; i++){
	        	workitemout.get(i).setRownum(i+1);
	        }
	        
	       // List<CompletionDrawingDTO> draw = completionDrawingBusinessImpl.getCompletionDrawingSearch(drawdto);
	        beans.put("workitemins", workitemin);
	        beans.put("workitemouts", workitemout);
	      //  beans.put("drawing", draw);
	        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    	String filePath = classloader.getResource("../" + "doc-template").getPath(); 
	        InputStream is = new BufferedInputStream(new FileInputStream(filePath + "workitem.xls"));
	        XLSTransformer transformer = new XLSTransformer();
	        long startTime = System.nanoTime();
	        Workbook resultWorkbook = transformer.transformXLS(is, beans);
	        long endTime = System.nanoTime();
	        is.close();
	        saveWorkbook(resultWorkbook, folder2Upload +"/"+ obj.getConstructionId()+  "-WorkItem.xls");
	        System.out.println("Stress1 XLSX time (s): " + (endTime - startTime)/1000000000);
			
	         String path  =   UEncrypt.encryptFileUploadPath( obj.getConstructionId()+  "-WorkItem.xls");
	        
			return Response.ok(Collections.singletonMap("fileName", path))
	        		.build();
	}
    
	@Override
	public Response exportEstimateTable(EstimatesWorkItemsDTO obj) throws Exception {
		 Map beans = new HashMap();
	        List<EstimatesWorkItemsDTO> InsideContract = estimatesWorkItemsBusinessImpl.getAllEstimatesWorkInsideContractForEvaluate(obj);
	        List<EstimatesWorkItemsDTO> OutsideContract = estimatesWorkItemsBusinessImpl.getAllEstimatesWorkOutsideContractForEvaluate(obj);
	      System.out.println(InsideContract.size());
	        
	        for(int i = 0 ; i< InsideContract.size() ; i++){
	        	InsideContract.get(i).setRownum(i+1);
	        }
	        for(int i = 0 ; i< OutsideContract.size() ; i++){
	        	OutsideContract.get(i).setRownum(i+1);
	        }
	        beans.put("workitemins", InsideContract);
	        beans.put("workitemouts", OutsideContract);
	        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    	String filePath = classloader.getResource("../" + "doc-template").getPath(); 
	        InputStream is = new BufferedInputStream(new FileInputStream(filePath + "CongViecHopDong.xls"));
	        XLSTransformer transformer = new XLSTransformer();
	        Workbook resultWorkbook = transformer.transformXLS(is, beans);
	        is.close();
	        saveWorkbook(resultWorkbook, folder2Upload + "/CongViecHopDong.xls");
	        String filename= UEncrypt.encryptFileUploadPath("CongViecHopDong.xls");
			return Response.ok(Collections.singletonMap("fileName", filename))
	        		.build();
	}

	public Response getByConstructionId(Long id) {		
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getByConstructionId(id);

        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {            
            return Response.ok(ls).build();
        }
	}
			
	@Override
	public Response getAllEstimatesWorkInsideContract(EstimatesWorkItemsDTO obj) {		
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getAllEstimatesWorkInsideContract(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {    
        	for(EstimatesWorkItemsDTO l:ls){
        		if(null == l.getSettleUnitPrice()){
        			l.setSettleUnitPrice(l.getUnitPrice()==null?null:l.getUnitPrice().doubleValue());
        		}
        		
        	}
            return Response.ok(ls).build();
        }
	}
	
	@Override
	public Response getAllEstimatesWorkOutsideContract(EstimatesWorkItemsDTO obj) {		
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getAllEstimatesWorkOutsideContract(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else { 
        	for(EstimatesWorkItemsDTO l:ls){
        		if(null == l.getSettleUnitPrice()){
        			
        			if(l.getUnitPrice()!=null){
        				l.setSettleUnitPrice(l.getUnitPrice()==null?null:l.getUnitPrice().doubleValue());
        			}
        			
        			
        		}
        	}
            return Response.ok(ls).build();
        }
	}
	
	@Override
	public Response getBieu4(EstimatesWorkItemsDTO obj) {		
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.getBieu4(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {            
            return Response.ok(ls).build();
        }
	}
	
	public Response exportFileDOCDetailForm4(EstimatesWorkItemsDTO dto) {
//		System.out.println("run here");
//		EstimatesWorkItemsDTO exportbmaDTO = estimatesWorkItemsBusinessImpl.exportEstimateWorkItem(id);
	  	 try {
	  	   EstimatesWorkItemsDTO exportbmaDTO = estimatesWorkItemsBusinessImpl.exportEstimateWorkItem(dto);
	      System.out.println("++++++++++++++"+exportbmaDTO.getStation_code());
	  	List<EstimatesWorkItemsDTO> bmalist = estimatesWorkItemsBusinessImpl.getBieu4(dto);
	  	    
	  	    for (int j = 0; j < bmalist.size(); j++) {
	  	    	bmalist.get(j).setStt(j+1);
	  	    }
	  	    
	  	   System.out.println(exportbmaDTO);
	  	   System.out.println("bmalist:"+bmalist);
	  	   ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	  	   String filePath = classloader.getResource("../" + "doc-template").getPath();

	  	   InputStream in = new FileInputStream(new File(filePath + "/QT-AB-4.docx"));
	  	   IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

	  	   FieldsMetadata metadata = report.createFieldsMetadata();
	  	   metadata.addFieldAsImage("sign1");
	       metadata.addFieldAsImage("sign2");
	       metadata.addFieldAsImage("sign3");
	       metadata.addFieldAsImage("sign4");
	  	   metadata.load("bc", EstimatesWorkItemsDTO.class, true);
	  	   
	  	   IContext context = report.createContext();
	  	   context.put("item", exportbmaDTO);	  	   
	  	   context.put("bc", bmalist);
	  	//begin chan ky
    	   
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
           
		       if(StringUtils.isNotEmpty(exportbmaDTO.getaDirectoridPath())){
	        	   
		           	/*sign1 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getaDirectoridPath()));*/
		        	File f = new File(folder2Upload + "/" + exportbmaDTO.getaDirectoridPath());
		            if(f.exists() && !f.isDirectory()) { 
		                sign1 = new FileImageProvider(f);
		                }else{
		                	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
		                }
		           } else{
		           	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
		           }
		           
//		           if(StringUtils.isNotEmpty(exportbmaDTO.getbDirectoridPath())){
//		           	sign2 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getbDirectoridPath()));
//		           } else{
//		           	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
//		           }
		          
		           if(StringUtils.isNotEmpty(exportbmaDTO.getbDirectoridPath())){	        	   
			        	File f = new File(folder2Upload + "/" + exportbmaDTO.getbDirectoridPath());
			            if(f.exists() && !f.isDirectory()) { 
			                sign2 = new FileImageProvider(f);
			                }else{
			                	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			                }
			           } else{
			           	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			           }
		           if(StringUtils.isNotEmpty(exportbmaDTO.getaInchargeMonitoridPath())){		       
			        	File f = new File(folder2Upload + "/" + exportbmaDTO.getaInchargeMonitoridPath());
			            if(f.exists() && !f.isDirectory()) { 
			                sign3 = new FileImageProvider(f);
			                }else{
			                	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			                }
			           } else{
			           	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			           }
		           if(StringUtils.isNotEmpty(exportbmaDTO.getbInchargeConstructidPath())){	          
			        	File f = new File(folder2Upload + "/" + exportbmaDTO.getbInchargeConstructidPath());
			            if(f.exists() && !f.isDirectory()) { 
			                sign4 = new FileImageProvider(f);
			                }else{
			                	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			                }
			           } else{
			           	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			           }
         
           IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
           if(exportbmaDTO.getStatusCa() == 2){
           	context.put("sign1", sign1);
           	context.put("sign2", sign2);
           	context.put("sign3", sign3);
           	context.put("sign4", sign4);
           } else {
           	context.put("sign1", none);
            context.put("sign2", none);
        	context.put("sign3", none);
            context.put("sign4", none);
           }
   	 
           //end chan ky
	  	   
	  	 File fout = new File(folder2Upload+"/" + dto.getConstructId() +"-QT-AB-4.docx");
	  	  OutputStream out = new FileOutputStream(fout);
		  // Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
		   report.process(context, out);
	  	   //---
	  	   out.flush();
	  	   out.close();
//	  	   return exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.docx";
	  	 String path  =   UEncrypt.encryptFileUploadPath(dto.getConstructId() + "-QT-AB-4.docx");
	  	 return Response.ok(Collections.singletonMap("fileName", path)).build();
	  	   
	  	   
	  	   
	  	  } catch (Exception e) {
	  	   LOGGER.error(e.getMessage(), e);
	  	  }
	  	  return null;
	  	
		}
	@Override
	public Response getBieu2(Long id) {
		List<EstimatesWorkItemsDTO> ls= estimatesWorkItemsBusinessImpl.getBieu2(id);
		
		if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {            
            return Response.ok(ls).build();
        }
	}
	
	@Override
	public Response getBieu5(Long id) {
		List<EstimatesWorkItemsDTO> ls= estimatesWorkItemsBusinessImpl.getBieu5(id);
		if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {            
            return Response.ok(ls).build();
        }
	}
	
	public Response exportFilePDFDetailForm4(EstimatesWorkItemsDTO dto) {
//		System.out.println("run here");
//		EstimatesWorkItemsDTO exportbmaDTO = estimatesWorkItemsBusinessImpl.exportEstimateWorkItem(id);
	  	 try {
	  	   EstimatesWorkItemsDTO exportbmaDTO = estimatesWorkItemsBusinessImpl.exportEstimateWorkItem(dto);
	      System.out.println("++++++++++++++"+exportbmaDTO.getStation_code());
	  	List<EstimatesWorkItemsDTO> bmalist = estimatesWorkItemsBusinessImpl.getBieu4(dto);
	  	    
	  	    for (int j = 0; j < bmalist.size(); j++) {
	  	    	bmalist.get(j).setStt(j+1);
	  	    }
	  	    
	  	   System.out.println(exportbmaDTO);
	  	   System.out.println("bmalist:"+bmalist);
	  	   ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	  	   String filePath = classloader.getResource("../" + "doc-template").getPath();

	  	   InputStream in = new FileInputStream(new File(filePath + "/QT-AB-4.docx"));
	  	   IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

	  	   FieldsMetadata metadata = report.createFieldsMetadata();
	  	   metadata.addFieldAsImage("sign1");
	       metadata.addFieldAsImage("sign2");
	       metadata.addFieldAsImage("sign3");
	       metadata.addFieldAsImage("sign4");
	  	   metadata.load("bc", EstimatesWorkItemsDTO.class, true);
	  	   
	  	   IContext context = report.createContext();
	  	   context.put("item", exportbmaDTO);	  	   
	  	   context.put("bc", bmalist);
		  	//begin chan ky
    	   
				IImageProvider sign1;
				IImageProvider sign2;
				IImageProvider sign3;
				IImageProvider sign4;
	           
	           if(StringUtils.isNotEmpty(exportbmaDTO.getaDirectoridPath())){
	        	   
	           	/*sign1 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getaDirectoridPath()));*/
	        	File f = new File(folder2Upload + "/" + exportbmaDTO.getaDirectoridPath());
	            if(f.exists() && !f.isDirectory()) { 
	                sign1 = new FileImageProvider(f);
	                }else{
	                	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	                }
	           } else{
	           	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	           }
	           
//	           if(StringUtils.isNotEmpty(exportbmaDTO.getbDirectoridPath())){
//	           	sign2 = new FileImageProvider(new File(folder2Upload + "/" + exportbmaDTO.getbDirectoridPath()));
//	           } else{
//	           	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
//	           }
	          
	           if(StringUtils.isNotEmpty(exportbmaDTO.getbDirectoridPath())){	        	   
		        	File f = new File(folder2Upload + "/" + exportbmaDTO.getbDirectoridPath());
		            if(f.exists() && !f.isDirectory()) { 
		                sign2 = new FileImageProvider(f);
		                }else{
		                	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
		                }
		           } else{
		           	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
		           }
	           if(StringUtils.isNotEmpty(exportbmaDTO.getaInchargeMonitoridPath())){		       
		        	File f = new File(folder2Upload + "/" + exportbmaDTO.getaInchargeMonitoridPath());
		            if(f.exists() && !f.isDirectory()) { 
		                sign3 = new FileImageProvider(f);
		                }else{
		                	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
		                }
		           } else{
		           	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
		           }
	           if(StringUtils.isNotEmpty(exportbmaDTO.getbInchargeConstructidPath())){	          
		        	File f = new File(folder2Upload + "/" + exportbmaDTO.getbInchargeConstructidPath());
		            if(f.exists() && !f.isDirectory()) { 
		                sign4 = new FileImageProvider(f);
		                }else{
		                	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
		                }
		           } else{
		           	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
		           }
	         
	           IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
	           if(exportbmaDTO.getStatusCa() == 2){
	           	context.put("sign1", sign1);
	           	context.put("sign2", sign2);
	           	context.put("sign3", sign3);
	           	context.put("sign4", sign4);
	           } 
	           else {
	           	context.put("sign1", none);
	            context.put("sign2", none);
	        	context.put("sign3", none);
	            context.put("sign4", none);
	           }
	   	 
	           //end chan ky
	  	   
	  	   
	  	 File fout = new File(folder2Upload+"/" + dto.getConstructId() +"-QT-AB-4.pdf");
	  	  OutputStream out = new FileOutputStream(fout);
		   Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
		   report.convert(context, options,out);
	  	   //---
	  	   out.flush();
	  	   out.close();
//	  	   return exportbmaDTO.getBmaterialAcceptanceId() + "-BM-TCNT-07.docx";
	  	 String path  =   UEncrypt.encryptFileUploadPath(dto.getConstructId()  + "-QT-AB-4.pdf");
	  	 return Response.ok(Collections.singletonMap("fileName",path)).build();
	  	   
	  	   
	  	   
	  	  } catch (Exception e) {
	  	   LOGGER.error(e.getMessage(), e);
	  	  }
	  	  return null;
	  	
		}
	
	@Override
	public Response getWorkItemDetail(EstimatesWorkItemsDTO obj) {		
		List<EstimatesWorkItemsDTO> ls  = estimatesWorkItemsBusinessImpl.getWorkItemDetail(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {            
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getAllEstimatesWorkContract(EstimatesWorkItemsDTO obj) {
		List<EstimatesWorkItemsDTO> ls  = estimatesWorkItemsBusinessImpl.getAllEstimatesWorkContract(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {            
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getWorkItemCongTrinh(EstimatesWorkItemsDTO obj) {
		// TODO Auto-generated method stub

		
		List<EstimatesWorkItemsDTO> ls = estimatesWorkItemsBusinessImpl.doSearchSceneGenerateWorkListCongTrinh(obj);
		if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
       
	
	}
//minhpvn
	@Override
	public Response exportDataImport(EstimatesWorkItemsDTO obj) throws Exception {
		// TODO Auto-generated method stub

		
		CompletionDrawingDTO drawdto = new CompletionDrawingDTO();
		drawdto.setConstructId(obj.getConstructionId());
		
		
		    Map beans = new HashMap();
	        List<EstimatesWorkItemsDTO> dataImportIn = estimatesWorkItemsBusinessImpl.getDataImportIn(obj);
	        List<EstimatesWorkItemsDTO> dataImportOut = estimatesWorkItemsBusinessImpl.getDataImportOut(obj);
	        
	        
	        for(int i = 0 ; i< dataImportIn.size() ; i++){
	        	dataImportIn.get(i).setRownum(i+1);
	        }
	        for(int i = 0 ; i< dataImportOut.size() ; i++){
	        	dataImportOut.get(i).setRownum(i+1);
	        }
	        
	       // List<CompletionDrawingDTO> draw = completionDrawingBusinessImpl.getCompletionDrawingSearch(drawdto);
	        beans.put("dataImportIns", dataImportIn);
	        beans.put("dataImportOuts", dataImportOut);
	      //  beans.put("drawing", draw);
	        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    	String filePath = classloader.getResource("../" + "doc-template").getPath(); 
	        InputStream is = new BufferedInputStream(new FileInputStream(filePath + "dataImport.xlsx"));
	        XLSTransformer transformer = new XLSTransformer();
	        long startTime = System.nanoTime();
	        Workbook resultWorkbook = transformer.transformXLS(is, beans);
	        long endTime = System.nanoTime();
	        is.close();
	        saveWorkbook(resultWorkbook, folder2Upload +"/"+ obj.getConstructId()+  "-dataImport.xlsx");
	        System.out.println("Stress1 XLSX time (s): " + (endTime - startTime)/1000000000);
	        String path  =   UEncrypt.encryptFileUploadPath( obj.getConstructId()+  "-dataImport.xlsx");
			return Response.ok(Collections.singletonMap("fileName", path))
	        		.build();
	
	}
}
