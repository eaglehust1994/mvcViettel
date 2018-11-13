/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.business.AbComplementWorkBusinessImpl;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.EstimatesWorkItemsBusinessImpl;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import java.util.Date;

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

/**
 *
 * @author HungLQ9
 */
public class AbComplementWorkRsServiceImpl implements AbComplementWorkRsService {
	static Logger LOGGER = LoggerFactory.getLogger(AbComplementWorkRsServiceImpl.class);
	@Value("${folder_upload}")
	private String folder2Upload;
	
    @Autowired
    AbComplementWorkBusinessImpl abComplementWorkBusinessImpl;
    
    @Autowired
    EstimatesWorkItemsBusinessImpl estimatesWorkItemsBusinessImpl;
    
    @Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
    
    @Override
    public Response getAbComplementWork() {
        List<AbComplementWorkDTO> ls = abComplementWorkBusinessImpl.getAll();
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
    public Response getAbComplementWorkById(Long id) {
        AbComplementWorkDTO obj = (AbComplementWorkDTO) abComplementWorkBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateAbComplementWork(AbComplementWorkDTO obj) {
        Long id = abComplementWorkBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addAbComplementWork(AbComplementWorkDTO obj) {
    	Long id = 0l;
        AbComplementWorkDTO check = (AbComplementWorkDTO) abComplementWorkBusinessImpl.getByIdCC(obj.getConstructId());
    	
        Date date = new Date();
        obj.setCreatedDate(date);
        if(check == null){
        	try {
        		String code = abComplementWorkBusinessImpl.autoGenCode();
        		obj.setCode(code);
        		CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("AB_COMPLEMENT_WORK");
    			Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
        		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
                constrCompleteRecordMap.setDataTableName("AB_COMPLEMENT_WORK");
                constrCompleteRecordMap.setDataTableId("AB_COMPLEMENT_WORK_ID");
                constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
                constrCompleteRecordMap.setCreatedDate(new Date());
                constrCompleteRecordMap.setStatus(0L);
                constrCompleteRecordMap.setCode(obj.getCode());
                constrCompleteRecordMap.setLevelOrder(1L);
                constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
                constrCompleteRecordMap.setConstructionId(obj.getConstructId());
                obj.setConstrCompleteRecordsMap(constrCompleteRecordMap); 
    			 id = abComplementWorkBusinessImpl.saveTable(obj); 
    			return Response.ok(id).build();
    		} catch (Exception e) {			 
    			LOGGER.error(e.getMessage(), e);  
    			return Response.status(Response.Status.BAD_REQUEST).build();
    		}
        }else{
        	AbComplementWorkDTO objDetail = abComplementWorkBusinessImpl.getByIdCC(obj.getConstructId());
        	if(objDetail != null){   
        		objDetail.setCreatedUserId(obj.getCreatedUserId());
        		objDetail.setCreatedDate(obj.getCreatedDate());
        		objDetail.setCode(objDetail.getCode());
        		objDetail.setIsActive(obj.getIsActive());
        		objDetail.setAdirectorId(obj.getAdirectorId());
        		objDetail.setBdirectorId(obj.getBdirectorId());
        		objDetail.setCdesignDirectionId(obj.getCdesignDirectionId());
        		objDetail.setStatusCa(obj.getStatusCa());
        		objDetail.setIsActive(obj.getIsActive());
        		id = abComplementWorkBusinessImpl.update(objDetail);
        	}        	 
        	if (id == 0l) {
    			return Response.status(Response.Status.BAD_REQUEST).build();
    		} else {
    			return Response.ok().build();
    		}
        }
        
		
       
    }

    @Override
    public Response deleteAbComplementWork(Long id) {
        AbComplementWorkDTO obj = (AbComplementWorkDTO) abComplementWorkBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            abComplementWorkBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getThongtinchung(Long id) {
		AbComplementWorkDTO ls= abComplementWorkBusinessImpl.getThongtinchung(id);
		if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {            
            return Response.ok(ls).build();
        }
	}
	
	@Override
	public Response exportPDFBieu2(Long id) {
		try {
			AbComplementWorkDTO obj = abComplementWorkBusinessImpl.getThongtinchungBieu2(id);
			List<EstimatesWorkItemsDTO> data = estimatesWorkItemsBusinessImpl.getBieu2(id);
			
			List<EstimatesWorkItemsDTO>  data1 = new ArrayList<EstimatesWorkItemsDTO>(); 
			List<EstimatesWorkItemsDTO>  data2 = new ArrayList<EstimatesWorkItemsDTO>(); 
			
			for(EstimatesWorkItemsDTO i : data){
				if(i.getType() ==1){
					data1.add(i);
				}else if(i.getType() == 2){
					data2.add(i);
				}				
			}				
			
			for (int j = 0; j < data1.size(); j++) {
				data1.get(j).setStt(j+1);
			}
			
			for (int j = 0; j < data2.size(); j++) {
				data2.get(j).setStt(j+1);
			}
			
			// theo hợp đồng
			Double truocthueHD = 0d;
			for (int i = 0; i < data.size(); i++) {
				truocthueHD = truocthueHD + data.get(i).getThanhtien1();
			}
			obj.setTruocThueHD(truocthueHD);
			obj.setVATHD((truocthueHD*10)/100);
			obj.setSauThueHD(obj.getTruocThueHD() + obj.getVATHD());
			
			// theo quyết toán
			Double truocthueQT = 0d;
			for (int i = 0; i < data.size(); i++) {
				truocthueQT = truocthueQT + data.get(i).getThanhtien2();
			}
			obj.setTruocThueQT(truocthueQT);
			obj.setVATQT((truocthueQT*10)/100);
			obj.setSauThueQT(obj.getTruocThueQT() + obj.getVATQT());
			
			// Chênh lệch HĐ và QT
			for (int i = 0; i < data.size(); i++) {
					data.get(i).setDeviantAdvance((data.get(i).getThanhtien2() - data.get(i).getThanhtien1()));
			}
			
			 
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/QT-AB-bieu2.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
			metadata.load("data1", EstimatesWorkItemsDTO.class, true);
			metadata.load("data2", EstimatesWorkItemsDTO.class, true);
			
			IContext context = report.createContext();
			context.put("ttc", obj);
			context.put("data1", data1);
			context.put("data2", data2);
			
			IImageProvider sign1;
            IImageProvider sign2;
            
            /*if(StringUtils.isNotEmpty(obj.getAdirectorPath())){
            	sign1 = new FileImageProvider(new File(folder2Upload + "/" + obj.getAdirectorPath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(obj.getBdirectorPath())){
            	sign2= new FileImageProvider(new File(folder2Upload + "/" + obj.getBdirectorPath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }*/
           
            // sign1
               if(StringUtils.isNotEmpty(obj.getAdirectorPath())){
               	File f = new File(folder2Upload + "/" + obj.getAdirectorPath());
               	if(f.exists() && !f.isDirectory()) { 
                       sign1 = new FileImageProvider(f);
                   }else{
                        sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                   }
               } else{
               	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
               }
               // sign2
               if(StringUtils.isNotEmpty(obj.getBdirectorPath())){
               	File f = new File(folder2Upload + "/" + obj.getBdirectorPath());
               	if(f.exists() && !f.isDirectory()) { 
                       sign2 = new FileImageProvider(f);
                   }else{
                        sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                   }
               } else{
               	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
               }
               
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(obj.getStatusCa() == 2){
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
            } else {
            	context.put("sign1", none);
                context.put("sign2", none);
            }
            
			File fout = new File(folder2Upload+"/" +"QT-AB-bieu2.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("QT-AB-bieu2.pdf");
			return Response.ok(Collections.singletonMap("fileName",path))
	        		.build();

		}catch ( Exception e )
        {
			throw new RuntimeException(e);
        }
        
		//return null;
	}

	@Override
	public Response exportDOCBieu2(Long id) {
		try {
			AbComplementWorkDTO obj = abComplementWorkBusinessImpl.getThongtinchungBieu2(id);
			List<EstimatesWorkItemsDTO> data = estimatesWorkItemsBusinessImpl.getBieu2(id);
			
			List<EstimatesWorkItemsDTO>  data1 = new ArrayList<EstimatesWorkItemsDTO>(); 
			List<EstimatesWorkItemsDTO>  data2 = new ArrayList<EstimatesWorkItemsDTO>(); 
			
			for(EstimatesWorkItemsDTO i : data){
				if(i.getType() ==1){
					data1.add(i);
				}else if(i.getType() == 2){
					data2.add(i);
				}				
			}				
			
			for (int j = 0; j < data1.size(); j++) {
				data1.get(j).setStt(j+1);
			}
			
			for (int j = 0; j < data2.size(); j++) {
				data2.get(j).setStt(j+1);
			}
			
			// theo hợp đồng
			Double truocthueHD = 0d;
			for (int i = 0; i < data.size(); i++) {
				truocthueHD = truocthueHD + data.get(i).getThanhtien1();
			}
			obj.setTruocThueHD(truocthueHD);
			obj.setVATHD((truocthueHD*10)/100);
			obj.setSauThueHD(obj.getTruocThueHD() + obj.getVATHD());
			
			// theo quyết toán
			Double truocthueQT = 0d;
			for (int i = 0; i < data.size(); i++) {
				truocthueQT = truocthueQT + data.get(i).getThanhtien2();
			}
			obj.setTruocThueQT(truocthueQT);
			obj.setVATQT((truocthueQT*10)/100);
			obj.setSauThueQT(obj.getTruocThueQT() + obj.getVATQT());
			
			// Chênh lệch HĐ và QT
			for (int i = 0; i < data.size(); i++) {
					data.get(i).setDeviantAdvance((data.get(i).getThanhtien2() - data.get(i).getThanhtien1()));
			}
			
			 
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/QT-AB-bieu2.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
			metadata.load("data1", EstimatesWorkItemsDTO.class, true);
			metadata.load("data2", EstimatesWorkItemsDTO.class, true);
			
			IContext context = report.createContext();
			context.put("ttc", obj);
			context.put("data1", data1);
			context.put("data2", data2);
			
			IImageProvider sign1;
            IImageProvider sign2;
            
         // sign1
            if(StringUtils.isNotEmpty(obj.getAdirectorPath())){
            	File f = new File(folder2Upload + "/" + obj.getAdirectorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            // sign2
            if(StringUtils.isNotEmpty(obj.getBdirectorPath())){
            	File f = new File(folder2Upload + "/" + obj.getBdirectorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(obj.getStatusCa() == 2){
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
            } else {
            	context.put("sign1", none);
                context.put("sign2", none);
            }
			
			File fout = new File(folder2Upload+"/" +"QT-AB-bieu2.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("QT-AB-bieu2.docx");
			return Response.ok(Collections.singletonMap("fileName",path))
	        		.build();

		}catch ( Exception e )
        {
			throw new RuntimeException(e);
        }
	}

	@Override
	public Response exportPDFBieu3(Long id) {
		try {
			AbComplementWorkDTO obj = abComplementWorkBusinessImpl.getThongtinchung(id);
			List<EstimatesWorkItemsDTO> data = estimatesWorkItemsBusinessImpl.getByConstructionId(id);
			/*for(EstimatesWorkItemsDTO type1:data){
				
			}*/
			
			List<EstimatesWorkItemsDTO>  typee = new ArrayList<EstimatesWorkItemsDTO>(); 
			List<EstimatesWorkItemsDTO>  type = new ArrayList<EstimatesWorkItemsDTO>(); 
			
			for(EstimatesWorkItemsDTO i : data){
				if(i.getType() ==1){
					typee.add(i);
				}else if(i.getType() == 2){
					type.add(i);
				}				
			}				
			
			for (int j = 0; j < typee.size(); j++) {
				typee.get(j).setStt(j+1);
			}
			
			for (int j = 0; j < type.size(); j++) {
				type.get(j).setStt(j+1);
			}
			 
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/QT-AB-3.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load( "typee", EstimatesWorkItemsDTO.class, true );
	        metadata.load( "type", EstimatesWorkItemsDTO.class, true );
			//metadata.load("data", EstimatesWorkItemsDTO.class, true);
			
			IContext context = report.createContext();
			context.put("ttc", obj);
			context.put("typee", typee);
	        context.put("type", type);
			//context.put("data", data);
			
			File fout = new File(folder2Upload+"/" +"QT-AB-3.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("QT-AB-3.pdf");
			return Response.ok(Collections.singletonMap("fileName",path)).build();

		}catch ( Exception e )
        {
			throw new RuntimeException(e);
        }
  
	}

	@Override
	public Response exportDOCBieu3(Long id) {
		try {
			AbComplementWorkDTO obj = abComplementWorkBusinessImpl.getThongtinchung(id);
			List<EstimatesWorkItemsDTO> data = estimatesWorkItemsBusinessImpl.getByConstructionId(id);
			
			List<EstimatesWorkItemsDTO>  typee = new ArrayList<EstimatesWorkItemsDTO>(); 
			List<EstimatesWorkItemsDTO>  type = new ArrayList<EstimatesWorkItemsDTO>(); 
			for(EstimatesWorkItemsDTO i : data){
				if(i.getType() ==1){
					typee.add(i);
				}else if(i.getType() == 2){
					type.add(i);
				}				
			}				
			
			for (int j = 0; j < typee.size(); j++) {
				typee.get(j).setStt(j+1);
			}
			
			for (int j = 0; j < type.size(); j++) {
				type.get(j).setStt(j+1);
			}
			
			 
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/QT-AB-3.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load( "typee", EstimatesWorkItemsDTO.class, true );
	        metadata.load( "type", EstimatesWorkItemsDTO.class, true );
			//metadata.load("data", EstimatesWorkItemsDTO.class, true);
			
			IContext context = report.createContext();
			context.put("ttc", obj);
			context.put("typee", typee);
	        context.put("type", type);
			//context.put("data", data);
			
			File fout = new File(folder2Upload+"/" +"QT-AB-3.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("QT-AB-3.docx");
			return Response.ok(Collections.singletonMap("fileName",path))
	        		.build();

		}catch ( Exception e )
        {
			throw new RuntimeException(e);
        }
        
	}

	@Override
	public Response signCA(Long id) {
		AbComplementWorkDTO obj = (AbComplementWorkDTO) abComplementWorkBusinessImpl.getAmonitorSingCa(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
           
            return Response.ok(obj).build();
        }
	}

	@Override
	public Response checkSave3(Long constructId) {
		AbComplementWorkDTO obj = (AbComplementWorkDTO) abComplementWorkBusinessImpl.CheckEstimate3(constructId);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
           
            return Response.ok(obj).build();
        }
	}

	
}
