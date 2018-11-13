/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import com.viettel.erp.business.AbDetailPriceBusinessImpl;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.EstimatesWorkItemsBusinessImpl;
import com.viettel.erp.dto.AbDetailPriceDTO;
import com.viettel.erp.dto.AbDetailPriceNewDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author HungLQ9
 */
public class AbDetailPriceRsServiceImpl implements AbDetailPriceRsService {
	
   
	@Value("${folder_upload}")
	private String folder2Upload;
	
	
    @Autowired
    AbDetailPriceBusinessImpl abDetailPriceBusinessImpl;

    @Autowired
    EstimatesWorkItemsBusinessImpl estimatesWorkItemsBusinessImpl;
    
    @Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;

  
    
    
    @Override
    public Response getAbDetailPrice() {
        List<AbDetailPriceDTO> ls = abDetailPriceBusinessImpl.getAll();
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
    public Response getAbDetailPriceById(Long id) {
        AbDetailPriceDTO obj = (AbDetailPriceDTO) abDetailPriceBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateAbDetailPrice(AbDetailPriceDTO obj) {
        Long id = abDetailPriceBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addAbDetailPrice(AbDetailPriceDTO obj) {
    	
    	Long id = 0l;
    	AbDetailPriceDTO check = (AbDetailPriceDTO) abDetailPriceBusinessImpl.getById(obj.getConstructId());
    	
    	Date date = new Date();
    	obj.setCreatedDate(date);
    	
        if(check == null){
        	try {
        		String code = abDetailPriceBusinessImpl.autoGenCode();
        		obj.setCode(code);
        		CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("AB_DETAIL_PRICE");
    			Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
        		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
                constrCompleteRecordMap.setDataTableName("AB_DETAIL_PRICE");
                constrCompleteRecordMap.setDataTableId("AB_DETAIL_PRICE_ID");
                constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
                constrCompleteRecordMap.setCreatedDate(new Date());
                constrCompleteRecordMap.setStatus(0L);
                constrCompleteRecordMap.setCode(obj.getCode());
                constrCompleteRecordMap.setLevelOrder(1L);
                constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
                constrCompleteRecordMap.setConstructionId(obj.getConstructId());
                obj.setConstrCompleteRecordsMap(constrCompleteRecordMap);   	
    			/* id = */abDetailPriceBusinessImpl.saveTable(obj); 
    			return Response.ok().build();
    		} catch (Exception e) {			 
    			throw new RuntimeException(e); 
    			//return Response.status(Response.Status.BAD_REQUEST).build();
    		}
        }else{
        	AbDetailPriceDTO objDetail = abDetailPriceBusinessImpl.getById(obj.getConstructId());
        	if(objDetail != null){ 
        		objDetail.setCreatedUserId(obj.getCreatedUserId());
        		objDetail.setCreatedDate(obj.getCreatedDate());
        		objDetail.setCode(objDetail.getCode());
        		objDetail.setIsActive(obj.getIsActive());
        		objDetail.setAdirectorId(obj.getAdirectorId());
        		objDetail.setBdirectorId(obj.getBdirectorId());        	
        		objDetail.setStatusCa(obj.getStatusCa());
        		id = abDetailPriceBusinessImpl.update(objDetail);
        	}        	 
        	if (id == 0l) {
    			return Response.status(Response.Status.BAD_REQUEST).build();
    		} else {
    			return Response.ok().build();
    		}
        }
    }
    
    
   

    @Override
    public Response deleteAbDetailPrice(Long id) {
        AbDetailPriceDTO obj = (AbDetailPriceDTO) abDetailPriceBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            abDetailPriceBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
    
    /*@Override
	public Response exportPDFBieu5(AbDetailPriceDTO dto) {
    	
    	try {
    		AbDetailPriceDTO obj = abDetailPriceBusinessImpl.getThongtinchung(dto);
	    	List<AbDetailPriceNewDTO> abDetailPriceList = new ArrayList<AbDetailPriceNewDTO>();
	    	List<AbDetailPriceNewDTO> WorkItem = estimatesWorkItemsBusinessImpl.getExportBieu5(dto.getConstructId());
	    	
	    	AbDetailPriceNewDTO ab = new AbDetailPriceNewDTO();
	
	    	for(Iterator<AbDetailPriceNewDTO> interator =  WorkItem.iterator();interator.hasNext();){
	    		AbDetailPriceNewDTO wi = interator.next();
	    		
	
	    		if(ab.getEstimatesWorkItemId() == null){
	    			ab = new AbDetailPriceNewDTO();
	    			ab.setWorkItemCode(wi.getWorkItemCode());
	    			ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId());
	    			ab.setCostIngredientName(wi.getWorkItemName());
	    			ab.setCostIngredientCode(wi.getCostIngredientCode());
	    			ab.setUnit(wi.getUnit());
	    			ab.setType(wi.getType());
	    			ab.setType2(wi.getType2());
	    			ab.setUnitPrice(wi.getUnitPrice());
	    			ab.setNormIndex(wi.getNormIndex());
	    			ab.setTotalMoney(wi.getTotalMoney());
	    			ab.setCostIngredientCode(wi.getCostIngredientCode());
	    			
	    			ab.setWorkItemCode4(wi.getWorkItemCode4());
	    			ab.setWorkItemName4(wi.getWorkItemName4());
	    			ab.setUnit4(wi.getUnit4());
	    			ab.setType4(wi.getType4());
	    			ab.setUnitPrice4(wi.getUnitPrice4());
	    			ab.setNormIndex4(wi.getNormIndex4());
	    			ab.setTotalMoney4(wi.getTotalMoney4());
	    			ab.setCostIngredientName4(wi.getCostIngredientName4());
	    			abDetailPriceList.add(ab);
	
	    			
	   		    }    		
	     		if(ab.getEstimatesWorkItemId().compareTo(wi.getEstimatesWorkItemId())!=0){
	    			ab = new AbDetailPriceNewDTO();
	    			ab.setWorkItemCode(wi.getWorkItemCode());
	    			ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId());
	    			ab.setCostIngredientName(wi.getWorkItemName());
	    			ab.setCostIngredientCode(wi.getCostIngredientCode());
	    			ab.setUnit(wi.getUnit());	    		
	    			ab.setType2(wi.getType2());
	    			ab.setUnitPrice(wi.getUnitPrice());
	    			ab.setNormIndex(wi.getNormIndex());
	    			ab.setTotalMoney(wi.getTotalMoney());
	    			ab.setCostIngredientCode(wi.getCostIngredientCode());
	    			
	    			ab.setWorkItemCode4(wi.getWorkItemCode4());
	    			ab.setWorkItemName4(wi.getWorkItemName4());
	    			ab.setUnit4(wi.getUnit4());
	    			ab.setType4(wi.getType4());
	    			ab.setUnitPrice4(wi.getUnitPrice4());
	    			ab.setNormIndex4(wi.getNormIndex4());
	    			ab.setTotalMoney4(wi.getTotalMoney4());
	    			ab.setCostIngredientName4(wi.getCostIngredientName4());
	    			abDetailPriceList.add(ab);
	    		}
	    		if(ab.getEstimatesWorkItemId().compareTo(wi.getEstimatesWorkItemId())==0  ){  
	   		    	ab = new AbDetailPriceNewDTO();
	   		    	ab.setCostIngredientName(wi.getCostIngredientName()); 
	   		    	ab.setCostIngredientCode(wi.getCostIngredientCode());
	   		    	ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId());
	   		    	ab.setUnit(wi.getUnit());
	    			ab.setType4(wi.getType4());
	    			ab.setType2(wi.getType2());
	    			ab.setUnitPrice(wi.getUnitPrice());
	    			ab.setNormIndex(wi.getNormIndex());
	    			ab.setTotalMoney(wi.getTotalMoney());
	    			ab.setCostIngredientCode(wi.getCostIngredientCode());
	    			
	    			ab.setWorkItemCode4(wi.getWorkItemCode4());
	    			ab.setWorkItemName4(wi.getWorkItemName4());
	    			ab.setUnit4(wi.getUnit4());
	    			ab.setType4(wi.getType4());
	    			ab.setUnitPrice4(wi.getUnitPrice4());
	    			ab.setNormIndex4(wi.getNormIndex4());
	    			ab.setTotalMoney4(wi.getTotalMoney4());
	    			ab.setCostIngredientName4(wi.getCostIngredientName4());
	    			abDetailPriceList.add(ab);
	    		}
	    	}
    	
	    	for(int i = 0; i < abDetailPriceList.size(); i++){
	    		abDetailPriceList.get(i).setRownum(i+1);
	    	}
    	
			 
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/QT-AB-5.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load("abDetailPriceList", AbDetailPriceNewDTO.class, true);
	
			IContext context = report.createContext();
			context.put("ttc", obj);
			context.put("abDetailPriceList", abDetailPriceList);
			
			File fout = new File(folder2Upload+"/" +"QT-AB-5.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			
			//return Response.ok(abDetailPriceList).build();
			return Response.ok(Collections.singletonMap("fileName","QT-AB-5.pdf")).build();

		}catch ( IOException e )
        {
            e.printStackTrace();
        }
        catch ( XDocReportException e )
        {
            e.printStackTrace();
        }
		return null;
    	
	}*/
    public List<AbDetailPriceNewDTO> abDetailPriceList (Long ConstructId){
    	List<AbDetailPriceNewDTO> abDetailPriceList = new ArrayList<AbDetailPriceNewDTO>();
    	List<AbDetailPriceNewDTO> WorkItem = abDetailPriceBusinessImpl.getExportBieu5(ConstructId);
    	 DecimalFormat df = new DecimalFormat("0.###");
    	AbDetailPriceNewDTO ab = new AbDetailPriceNewDTO();
    	Integer numberic = 0;
    	for(Iterator<AbDetailPriceNewDTO> interator = WorkItem.iterator();interator.hasNext();){
    		AbDetailPriceNewDTO wi = interator.next();

    		if(ab.getEstimatesWorkItemId() == null){
    			ab = new AbDetailPriceNewDTO();
    			ab.setWorkItemCode2(wi.getWorkItemCode2());
    			ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId());
    			ab.setCostIngredientName2(wi.getWorkItemName2());
    			numberic += 1;
    			ab.setNumberic(numberic.toString());
    			abDetailPriceList.add(ab);

    			
   		    }    		
     		if(ab.getEstimatesWorkItemId().longValue() != wi.getEstimatesWorkItemId().longValue()){
    			ab = new AbDetailPriceNewDTO();
    			ab.setWorkItemCode2(wi.getWorkItemCode2());
    			ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId());
    			ab.setCostIngredientName2(wi.getWorkItemName2());
    			numberic += 1;
    			ab.setNumberic(numberic.toString());
    			abDetailPriceList.add(ab);	    			
    		}
    		if(ab.getEstimatesWorkItemId().equals(wi.getEstimatesWorkItemId())){  
               	ab = new AbDetailPriceNewDTO();
   		    	//ab.setWorkItemCode(wi.getWorkItemCode());
    			ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId());
    			//ab.setCostIngredientName2(wi.getWorkItemName2());
    			ab.setType2(wi.getType2());
    			ab.setUnit2(wi.getUnit2());
    			ab.setCostIngredientName2(wi.getCostIngredientName2());
    			

    			ab.setUnitPrice22(wi.getUnitPrice2()==null?"":df.format(wi.getUnitPrice2()));
    			ab.setNormIndex22(wi.getNormIndex2()==null?"":df.format(wi.getNormIndex2()));
    			ab.setCoefficient22(wi.getCoefficient2()==null? "":df.format(wi.getCoefficient2()));    			
    			ab.setTotalMoney22(wi.getTotalMoney2()==null?"":df.format(wi.getTotalMoney2()));
    			

    			ab.setUnitPrice44(wi.getUnitPrice4()==null?"":df.format(wi.getUnitPrice4()));
    			ab.setNormIndex44(wi.getNormIndex4()==null?"":df.format(wi.getNormIndex4()));
    			ab.setCoefficient44(wi.getCoefficient4()==null? "":df.format(wi.getCoefficient4()));
    			ab.setTotalMoney44(wi.getTotalMoney4()==null?"":df.format(wi.getTotalMoney4())); 
    			
    			Double temp  ;
    			if(wi.getTotalMoney2()!=null && wi.getTotalMoney4()!=null){
    				temp = wi.getTotalMoney4()-wi.getTotalMoney2();
    				ab.setDeviant(df.format(temp).toString());
    			}else if(wi.getTotalMoney2()!=null && wi.getTotalMoney4()==null){
    				temp = (-wi.getTotalMoney2());
    				ab.setDeviant(df.format(temp).toString());
    			}else if(wi.getTotalMoney2()==null && wi.getTotalMoney4()!=null){
    				ab.setDeviant(df.format(wi.getTotalMoney4()).toString());
    			}
    			
    			
    			abDetailPriceList.add(ab);
    		}
    	} 
    	
    	return abDetailPriceList;
    }
	@Override
	public Response exportDOCBieu5(AbDetailPriceDTO dto) {
		try {
			AbDetailPriceDTO obj = abDetailPriceBusinessImpl.getThongtinchung(dto);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/QT-AB-5.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load("abDetailPriceList", AbDetailPriceNewDTO.class, true);
			
			IContext context = report.createContext();
			context.put("ttc", obj);
			context.put("abDetailPriceList", abDetailPriceList(dto.getConstructId()));
			
			File fout = new File(folder2Upload+"/" +"QT-AB-5.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("QT-AB-5.docx");
			return Response.ok(Collections.singletonMap("fileName",path)).build();

		}catch ( Exception e )
        {
			throw new RuntimeException(e);
        }
        /*catch ( XDocReportException e )
        {
            e.printStackTrace();
        }
		return null;*/
	}

	@Override
	public Response exportPDFBieu5(AbDetailPriceDTO dto) {
		try {
			AbDetailPriceDTO obj = abDetailPriceBusinessImpl.getThongtinchung(dto);
		
	    	
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/QT-AB-5.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load("abDetailPriceList", AbDetailPriceNewDTO.class, true);
			
			IContext context = report.createContext();
			context.put("ttc", obj);
			context.put("abDetailPriceList", abDetailPriceList(dto.getConstructId()));
			
			File fout = new File(folder2Upload+"/" +"QT-AB-5.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("QT-AB-5.pdf");
			return Response.ok(Collections.singletonMap("fileName",path)).build();

		}catch ( Exception e )
        {
			throw new RuntimeException(e);
        }/*
        catch ( XDocReportException e )
        {
            e.printStackTrace();
        }
		return null;*/
	}

	@Override
	public Response checkSave5(Long constructId) {
		AbDetailPriceDTO obj = (AbDetailPriceDTO) abDetailPriceBusinessImpl.CheckEstimate5(constructId);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
           
            return Response.ok(obj).build();
        }
	}

	@Override
	public Response signCA(Long constructId) {
		AbDetailPriceDTO obj = (AbDetailPriceDTO) abDetailPriceBusinessImpl.getAmonitorSingCa(constructId);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
           
            return Response.ok(obj).build();
        }
	}

	@Override
	public Response getBieu5(Long id) {
		return Response.ok(abDetailPriceList(id)).build();
	}
    
}
