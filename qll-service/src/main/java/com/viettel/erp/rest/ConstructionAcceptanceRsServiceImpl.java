/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.viettel.erp.business.AMaterialHandoverMerListBusinessImpl;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.ConstrAcceptanceRequestBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstructionAcceptanceBusinessImpl;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dto.AMaterialHandoverMerListDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrAcceptMerListDTO;
import com.viettel.erp.dto.ConstrAcceptWorkListDTO;
import com.viettel.erp.dto.ConstrAcceptanceRequestDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstructionAcceptanceDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.XDocConverterException;
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
public class ConstructionAcceptanceRsServiceImpl implements ConstructionAcceptanceRsService {
	
	
	
	@Value("${folder_upload}")
	private String folder2Upload;
	@Value("${constructionAcceptance.folder}")
	private String folder;
	@Value("${constructionAcceptance.attachType}")
    private Long attachType;
	private static final CompressionType TYPE = CompressionType.ZIP;
    private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
    static Logger LOGGER = LoggerFactory.getLogger(ConstructionAcceptanceBusinessImpl.class);
    
    @Autowired
    ConstructionAcceptanceBusinessImpl constructionAcceptanceBusinessImpl;
    
	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
	
	@Autowired
	CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
	
    @Autowired
    AMaterialHandoverMerListBusinessImpl aMaterialHandoverMerListBusinessImpl;
    
    @Autowired
    ConstrAcceptanceRequestBusinessImpl constrAcceptanceRequestBusinessImpl;
    
    @Autowired
    UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;
    @Override
    public Response getConstructionAcceptance() {
        List<ConstructionAcceptanceDTO> ls = constructionAcceptanceBusinessImpl.getAll();
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
    public Response getConstructionAcceptancebyConstruct(ConstructionAcceptanceDTO obj) {
        List<ConstructionAcceptanceDTO> ls = constructionAcceptanceBusinessImpl.getAllC(obj );
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {

            return Response.ok(ls).build();
        }
    }
    @Override
    public Response getConstructionAcceptanceById(Long id) {

 
        ConstructionAcceptanceDTO obj = (ConstructionAcceptanceDTO) constructionAcceptanceBusinessImpl.getByOneId(id);

        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateConstructionAcceptance(ConstructionAcceptanceDTO obj) {
        Long id = constructionAcceptanceBusinessImpl.update(obj);
        if(obj.getStatusCa() == 2 || obj.getStatusCa() == 1){
        	return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        
    	if(obj.getAcceptToDate().getTime() < obj.getAcceptFromDate().getTime()){
    		return Response.status(Response.Status.BAD_REQUEST).build();
    	}
        
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addConstructionAcceptance(ConstructionAcceptanceDTO obj) throws Exception{
    	
    	
    	ConstrAcceptanceRequestDTO constracceptancerequest = new ConstrAcceptanceRequestDTO();
    	//ConstrConstructionsDTO constrconstructions  = new ConstrConstructionsDTO();
    	constracceptancerequest.setConstructId(obj.getConstructId());
    	
    	
    	List<ConstrAcceptanceRequestDTO> constracceptancerequestList = constrAcceptanceRequestBusinessImpl.listConstrAcceptanceReq(constracceptancerequest);
    	
    	if(constracceptancerequestList.size() <1){
    		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    	}
    	for(int i = 0 ; i<constracceptancerequestList.size() ; i++){
    		
    		
    		if (constracceptancerequestList.get(i).getStatusCa() != 2){
    			return Response.status(Response.Status.FORBIDDEN).build();
    		}
    		//else if(obj. constracceptancerequestList.get(i).getCreatedDate())
    	}
    	
    	if(obj.getAcceptToDate().getTime() < obj.getAcceptFromDate().getTime()){
    		return Response.status(Response.Status.BAD_REQUEST).build();
    	}
    	
    	ConstructionAcceptanceDTO obj_ = new ConstructionAcceptanceDTO();
    	obj_.setType(null);
    	obj_.setConstructId(obj.getConstructId());
    	obj_.setContractId(obj.getContractId());
    	List<ConstructionAcceptanceDTO> getAll = constructionAcceptanceBusinessImpl.getAllC(obj_);
    	
    	if(getAll.size() > 0){
    		return Response.status(Response.Status.CONFLICT).build();
    	}
    	 
        CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("CONSTRUCTION_ACCEPTANCE");
        Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
    	
        Date date = new Date();
    	obj.setCode(constructionAcceptanceBusinessImpl.autoGenCode());
    	ConstrCompleteRecordsMapDTO constrCompleteRecordsMapDTO = new ConstrCompleteRecordsMapDTO();
    	constrCompleteRecordsMapDTO.setConstructionId(obj.getConstructId());
    	constrCompleteRecordsMapDTO.setDataTableName("CONSTRUCTION_ACCEPTANCE");
    	constrCompleteRecordsMapDTO.setDataTableId("CONSTRUCTION_ACCEPTANCE_ID");
    	constrCompleteRecordsMapDTO.setCatFileInvoiceId(catFileInvoiceId);
    	constrCompleteRecordsMapDTO.setConstructionId(obj.getConstructId());
    	constrCompleteRecordsMapDTO.setLevelOrder(1l);
    	constrCompleteRecordsMapDTO.setStatus(0l);
    	constrCompleteRecordsMapDTO.setCreatedUserId(obj.getUserid());
    	constrCompleteRecordsMapDTO.setCreatedDate(date);
    	constrCompleteRecordsMapDTO.setCode(obj.getCode());
    	
    	obj.setConstrcompleterecordsmap(constrCompleteRecordsMapDTO);
    	obj.setCreatedDate(new Date());
    	obj.setCreatedUserId(obj.getUserid());
    	
    	
    	
    	
        Long id = constructionAcceptanceBusinessImpl.saveTable(obj);
        
        
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
			
            return Response.ok(id).build();
        }
    }

    @Override
    public Response deleteConstructionAcceptance(Long id) throws Exception {
    	
    	
    	
    	
        ConstructionAcceptanceDTO obj = (ConstructionAcceptanceDTO) constructionAcceptanceBusinessImpl.getByOneId(id);
     
        if(obj.getStatusCa() == 2 ||obj.getStatusCa() == 1){
        	return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }else{
        	constructionAcceptanceBusinessImpl.deleteTable(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
    }

	@Override
	public Response findByConstructId(Long constructId) {
        List<ConstructionAcceptanceDTO> ls = constructionAcceptanceBusinessImpl.findByConstructId(constructId);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

	@Override
	public Response getWorkItem(EstimatesWorkItemsDTO obj) {
	/*	List<EstimatesWorkItemsDTO> ls = constructionAcceptanceBusinessImpl.getWorkItem(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }*/
		return Response.ok(0).build();
	}

	@Override
	public Response exportFileConstructionAcceptance(Long id) throws Exception {
          String path = "";
			try {
				Long type =	exportFile(id, 2L);
				if(type == 1){
					path = UEncrypt.encryptFileUploadPath(id + "-BM-TCNT-15.pdf");
					return Response.ok(Collections.singletonMap("fileName",path)).build();
				}else if(type == 2){
					path = UEncrypt.encryptFileUploadPath(id + "-BM-TCNT-16.pdf");
					return Response.ok(Collections.singletonMap("fileName",path)).build();
				}
			} catch (XDocConverterException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(), e);
			} catch (XDocReportException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(), e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(), e);
			}

			return null;
			

	}
	@Override
	public Response exportFileDocConstructionAcceptance(Long id) throws Exception {
            
		    String path = "";
		 
			try {
				Long type =	exportFile(id, 1L);
				if(type == 1){
					path  = UEncrypt.encryptFileUploadPath(id + "-BM-TCNT-15.docx");
					System.out.println(path +"   nnnnnnnnnnnnnnnnn");
					return Response.ok(Collections.singletonMap("fileName",path)).build();
				}else if(type == 2){
					path  = UEncrypt.encryptFileUploadPath(id + "-BM-TCNT-16.docx");
					return Response.ok(Collections.singletonMap("fileName",path)).build();
				}
			} catch (XDocConverterException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(), e);
			} catch (XDocReportException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(), e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(), e);
			}

			return null;
			

	}
	
	public String exportFileConstructionAcceptanceString(Long id, Long typeFile) {

		try {
			
			
		if(typeFile == 1){
			Long typefordoc =	exportFile(id, 1L);
			 if(typefordoc == 1 ){
				 
				
				return id +"-BM-TCNT-15.docx";
			}else if(typefordoc == 2){
				return id +"-BM-TCNT-16.docx";
			}
			
		}else if(typeFile == 2){
			Long typeforpdf =	exportFile(id, 2L);
			if(typeforpdf == 1 ){
				return id +"-BM-TCNT-15.pdf";
			}else if(typeforpdf == 2 ){
				return id +"-BM-TCNT-16.pdf";
			}
		}	
			
		
		
		
		

			
		} catch (XDocConverterException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
			
		}

		return null;
		

}
	
	
	@Override
	public Response exportListConstructionAcceptance(List<Long> data) {
		return archiveZipFile(data,2l);
	}
	
	

	public Response archiveZipFile(List<Long> data,Long type) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			String filename = exportFileConstructionAcceptanceString(l,type);
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
            fileCompressor.setCompressedPath(filePath + File.separatorChar  +"nghiemthucongtrinh." + TYPE.getExtension());
            fileCompressor.compress();
            
            String  path  = UEncrypt.encryptFileUploadPath("nghiemthucongtrinh." + TYPE.getExtension());
            return Response.ok(java.util.Collections.singletonMap("fileName",path))
	        		.build();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
		return null;
	}
	
	
	public Long  exportFile(Long id,Long typeout) throws XDocConverterException, XDocReportException, IOException ,FileNotFoundException {
		ConstructionAcceptanceDTO obj =  constructionAcceptanceBusinessImpl.getByOneId(id);
		
		
		
		System.out.print(obj.getContractCode() + "xxxxxxxxxxxxxxxxxxxxx");
		
		   List<ConstrAcceptMerListDTO> AMaterial = obj.getConstracceptmerlist();
				for (int i = 0; i<AMaterial.size();i++){
					AMaterial.get(i).setStt(i+1);
					
					if(AMaterial.get(i).getHandoverQuantity() == null)
					{
					AMaterial.get(i).setHandoverQuantity(0D);
					}
					if(AMaterial.get(i).getExecuteQuantity() == null)
					{
					AMaterial.get(i).setExecuteQuantity(0D);
					}
					

			        }   
			List<ConstrAcceptWorkListDTO>  InContract = new ArrayList<ConstrAcceptWorkListDTO>(); 
			List<ConstrAcceptWorkListDTO>  OutContract = new ArrayList<ConstrAcceptWorkListDTO>(); 
			

			for (int i = 0; i<obj.getConstracceptworklist().size();i++){
				if(obj.getConstracceptworklist().get(i).getType() == 1){
					InContract.add(obj.getConstracceptworklist().get(i));
				}else if(obj.getConstracceptworklist().get(i).getType() == 2){
					OutContract.add(obj.getConstracceptworklist().get(i));
				}
			}
			for (int i = 0; i<InContract.size();i++){
				InContract.get(i).setRownum(i+1);
			}
			for (int i = 0; i<OutContract.size();i++){
				OutContract.get(i).setEvaluateQuantity(0D);
				OutContract.get(i).setRownum(i+1);
			}
			
         ClassLoader classloader = Thread.currentThread().getContextClassLoader();
     	String filePath = classloader.getResource("../" + "doc-template").getPath();
     	 InputStream in = null;
     	if(obj.getType() == 1){
     		in = new FileInputStream(new File(filePath + "/BM-TCNT-15.docx"));
     	}else if(obj.getType() == 2){
     		in = new FileInputStream(new File(filePath + "/BM-TCNT-16.docx"));
     	};
     	

         IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );

         FieldsMetadata metadata = report.createFieldsMetadata();
         metadata.addFieldAsImage("yes");
         metadata.addFieldAsImage("no");
         
         
         metadata.addFieldAsImage("sign1");
         metadata.addFieldAsImage("sign2");
         metadata.addFieldAsImage("sign3");
         metadata.addFieldAsImage("sign4");
         metadata.addFieldAsImage("sign5");
         metadata.addFieldAsImage("sign6");
         
         metadata.addFieldAsImage("sign7");
         metadata.addFieldAsImage("sign8");
         metadata.addFieldAsImage("sign9");
         metadata.addFieldAsImage("sign10");
         
         metadata.load( "InContract", ConstrAcceptWorkListDTO.class, true );
         metadata.load( "OutContract", ConstrAcceptWorkListDTO.class, true );
         metadata.load( "AMaterial", AMaterialHandoverMerListDTO.class, true );
         IContext context = report.createContext();
         
         context.put( "item", obj );
         context.put( "InContract", InContract );
         context.put( "OutContract", OutContract );
         context.put( "AMaterial", AMaterial );
         IImageProvider yes = new FileImageProvider(new File(filePath + "/yes.png"));
         IImageProvider no = new FileImageProvider(new File(filePath + "/no.png"));


         IImageProvider sign1;
         IImageProvider sign2;
         IImageProvider sign3;
         IImageProvider sign4;
         IImageProvider sign5;
         IImageProvider sign6;
         

         
         
         if(obj.getConclusion() == 0){
             context.put("yes", yes);
             context.put("no", no);
         } else {
         	context.put("yes", no);
             context.put("no", yes);
         }
         
         
         
         if(StringUtils.isNotEmpty(constructionAcceptanceBusinessImpl.getDocumentPath(obj.getAMonitorId()))){
        	 File f = new File(folder2Upload + "/" + constructionAcceptanceBusinessImpl.getDocumentPath(obj.getAMonitorId()));
        	 if(f.exists() && !f.isDirectory()) { 
        		 sign1 = new FileImageProvider(f);
        		}
        	 else{
        		 sign1 = new FileImageProvider(new File(filePath + "/none.png"));
        	 }
         } else{
         	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
         }
         if(StringUtils.isNotEmpty(constructionAcceptanceBusinessImpl.getDocumentPath(obj.getAInChargeMonitorId()))){
        	 File f = new File(folder2Upload + "/" + constructionAcceptanceBusinessImpl.getDocumentPath(obj.getAInChargeMonitorId()));
        	 if(f.exists() && !f.isDirectory()) { 
        		 sign2 = new FileImageProvider(f);
        		}
        	 else{
        		 sign2 = new FileImageProvider(new File(filePath + "/none.png"));
        	 }
         } else{
         	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
         }
         
         if(StringUtils.isNotEmpty(constructionAcceptanceBusinessImpl.getDocumentPath(obj.getBDirectorId()))){
        	 File f = new File(folder2Upload + "/" + constructionAcceptanceBusinessImpl.getDocumentPath(obj.getBDirectorId()));
        	 
        	 if(f.exists() && !f.isDirectory()) { 
        		 sign3 = new FileImageProvider(f);
        		}
        	 else{
        		 sign3 = new FileImageProvider(new File(filePath + "/none.png"));
        	 }
        	
         } else{
         	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
         }
         
         if(StringUtils.isNotEmpty(constructionAcceptanceBusinessImpl.getDocumentPath(obj.getBInChargeConstructId()))){
        	 
        	 File f = new File(folder2Upload + "/" + constructionAcceptanceBusinessImpl.getDocumentPath(obj.getBInChargeConstructId()));
        	 if(f.exists() && !f.isDirectory()) { 
        		 sign4 = new FileImageProvider(f);
        		}
        	 else{
        		 sign4 = new FileImageProvider(new File(filePath + "/none.png"));
        	 }
  
         } else{
         	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
         }
         
         if(StringUtils.isNotEmpty(constructionAcceptanceBusinessImpl.getDocumentPath(obj.getCDesignDirectionId()))){

       	 File f = new File(folder2Upload + "/" + constructionAcceptanceBusinessImpl.getDocumentPath(obj.getCDesignDirectionId()));
       	 if(f.exists() && !f.isDirectory()) { 
       		sign5 = new FileImageProvider(f);
       		}
       	 else{
       		sign5 = new FileImageProvider(new File(filePath + "/none.png"));
       	 }
        	
        	
         } else{
         	sign5 = new FileImageProvider(new File(filePath + "/none.png"));
         }
         
         if(StringUtils.isNotEmpty(constructionAcceptanceBusinessImpl.getDocumentPath(obj.getCDesignManagerId()))){

          	 File f = new File(folder2Upload + "/" + constructionAcceptanceBusinessImpl.getDocumentPath(obj.getCDesignManagerId()));
          	 System.out.print(folder2Upload + "/" + constructionAcceptanceBusinessImpl.getDocumentPath(obj.getCDesignManagerId()));
          	 
           	 if(f.exists() && !f.isDirectory()) { 
           		sign6 = new FileImageProvider(f);
           		}
           	 else{
           		sign6 = new FileImageProvider(new File(filePath + "/none.png"));
           	 }
        	
        	
         } else{
         	sign6 = new FileImageProvider(new File(filePath + "/none.png"));
         }
         
         IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
         if(obj.getStatusCa() == 2){
         	context.put("sign1", sign1);
             context.put("sign2", sign2);
             context.put("sign3", sign3);
             context.put("sign4", sign4);
             context.put("sign5", sign5);
             context.put("sign6", sign6);
             
             context.put("sign7", sign1);
             context.put("sign8", sign2);
             context.put("sign9", sign3);
             context.put("sign10", sign4);
         } else {
         	context.put("sign1", none);
            context.put("sign2", none);
          	context.put("sign3", none);
            context.put("sign4", none);
         	context.put("sign5", none);
            context.put("sign6", none);
            
            
            context.put("sign7", none);
            context.put("sign8", none);
            context.put("sign9", none);
            context.put("sign10", none);
         }
         
         
         
         File fout = null;
         
         OutputStream  out = null;
         
         if(typeout == 1){
         
      	if(obj.getType() == 1){
      		fout = new File(folder2Upload + "/" + obj.getConstructionAcceptanceId() +"-BM-TCNT-15.docx");
     	}else if(obj.getType() == 2){
     		fout = new File(folder2Upload + "/" + obj.getConstructionAcceptanceId() +"-BM-TCNT-16.docx");
     	}; 
         out = new FileOutputStream(fout);
         report.process( context, out );

        }else if(typeout ==2){
        	if(obj.getType() == 1){
          		fout = new File(folder2Upload + "/" + obj.getConstructionAcceptanceId() +"-BM-TCNT-15.pdf");
         	}else if(obj.getType() == 2){
         		fout = new File(folder2Upload + "/" + obj.getConstructionAcceptanceId() +"-BM-TCNT-16.pdf");
         	}; 
             out = new FileOutputStream(fout);
             Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
 			 report.convert(context, options, out);
        }
         if(out != null)
         {
        	out.flush();
         	out.close(); 
         }
			return obj.getType();
	}
	@Override
	public Response exportFiledoc(List<Long> data) {
		 return	archiveZipFile( data,1L);
	}
	@Override
	public Response CheckCA(ConstructionAcceptanceDTO obj) {
		
		
		

    	ConstrAcceptanceRequestDTO constracceptancerequest = new ConstrAcceptanceRequestDTO();
    	constracceptancerequest.setConstructId(obj.getConstructId());
    	
    	List<ConstrAcceptanceRequestDTO> constracceptancerequestList = constrAcceptanceRequestBusinessImpl.listConstrAcceptanceReq(constracceptancerequest);
    	
    	//Phieu yeu cau nghiem thu chua tao
    	if(constracceptancerequestList.size() <1){
    		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    	}
    	
    	//Phieu yeu cau nghiem thu chua ky
    	for(int i = 0 ; i<constracceptancerequestList.size() ; i++){
    		
    		
    		if (constracceptancerequestList.get(i).getStatusCa() != 2){
    			return Response.status(Response.Status.FORBIDDEN).build();
    		}
    		//else if(obj. constracceptancerequestList.get(i).getCreatedDate())
    	}
    	
    	
    	ConstructionAcceptanceDTO obj_ = new ConstructionAcceptanceDTO();
    	obj_.setType(null);
    	obj_.setConstructId(obj.getConstructId());
    	List<ConstructionAcceptanceDTO> getAll = constructionAcceptanceBusinessImpl.getAllC(obj_);
    	//Ton tai 1 ban ghi
    	if(getAll.size() > 0){
    		return Response.status(Response.Status.CONFLICT).build();
    	}
    	return Response.ok(true).build();
	}
	@Override
	public Response getFolder() {
		// TODO Auto-generated method stub
		return Response.ok().entity(java.util.Collections.singletonMap("folder", folder)).build();
	}
	@Override
	public Response addFileCalculate(ConstructionAcceptanceDTO obj) throws Exception {
		// TODO Auto-generated method stub
		for(int i = 0; i < obj.getListDocumentName().size(); i++){
        	String documentPath = UEncrypt.decryptFileUploadPath(obj.getListDocumentPath().get(i));
        	utilAttachedDocumentsBusinessImpl.insert(obj.getListDocumentName().get(i), obj.getConstructId(), documentPath, attachType);
        }
		return null;
	}
	@Override
	public Response updateIsActive(Long constrAcceptanceId) {
		// TODO Auto-generated method stub
		constructionAcceptanceBusinessImpl.updateIsActive(constrAcceptanceId);
		return null;
	}

}
