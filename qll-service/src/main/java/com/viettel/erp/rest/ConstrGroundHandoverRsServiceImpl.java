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
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.erp.bo.ConstrGroundHandoverBO;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstrGroundHandoverBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.dto.ConstrGroundHandoverDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
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
public class ConstrGroundHandoverRsServiceImpl implements ConstrGroundHandoverRsService {
	private static Logger LOGGER = LoggerFactory.getLogger(ConstrGroundHandoverRsServiceImpl.class);
	
	@Value("${folder_upload}")
	private String folder2Upload;
	
	@Autowired
	ConstrGroundHandoverBusinessImpl constrGroundHandoverBusinessImpl;
	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
	@Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;
	// ChuongNV

	// get List all
	@Override
	public Response getAllConstrGroundHandover(ConstrGroundHandoverDTO dto) {
		List<ConstrGroundHandoverDTO> ls = constrGroundHandoverBusinessImpl.getAllConstrGroundHandover(dto);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	// End ChuongNV

	@Override
	public Response getConstrGroundHandover() {
		List<ConstrGroundHandoverDTO> ls = constrGroundHandoverBusinessImpl.getAll();
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
	public Response getConstrGroundHandoverById(Long id) {
		ConstrGroundHandoverDTO obj = (ConstrGroundHandoverDTO) constrGroundHandoverBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateConstrGroundHandover(ConstrGroundHandoverDTO obj) {
		Long id = constrGroundHandoverBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}
	}

	@Override
	public Response addorupdateConstrGroundHandover(ConstrGroundHandoverDTO obj) {
		if (obj.getConstrGroundHandoverId() != null) {
			Long id = constrGroundHandoverBusinessImpl.update(obj);
			if(obj.getStatusCa() == 0L){
				String nameTable = "CONSTR_GROUND_HANDOVER";
				qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getConstrGroundHandoverId(), nameTable);
			}
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(obj.getConstrGroundHandoverId()).build();
			}
		} else {
			List<ConstrGroundHandoverDTO> list=constrGroundHandoverBusinessImpl.getAllConstrGroundHandover(obj);
			if(list.size()==0L){
			String code= (String) constrGroundHandoverBusinessImpl.getCode("CONSTR_GROUND_HANDOVER", "QLHC_BGMBTC") ;
			if(StringUtils.isNotEmpty(code)){
				obj.setCode(code);
				obj.setCreatedDate(new Date());
			Long constructionId = obj.getConstructId();
			Long id = constrGroundHandoverBusinessImpl.save(obj);
			try {
				  constrCompleteRecordsMapBusinessImpl.insert(constructionId,
				 "CONSTR_GROUND_HANDOVER", "CONSTR_GROUND_HANDOVER_ID", id, obj.getCreatedUserId(), code);
				 } catch (Exception ex) {
					 LOGGER.error(ex.getMessage(), ex);
				 }
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(id).build();
			}
			} else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		} else{
			return Response.status(Response.Status.FOUND).build();
		}
	}
	}
	@Override
	public Response appro(approDTO obj) {
		Long res = constrGroundHandoverBusinessImpl.appro(obj);
		return Response.ok(res).build();
	}
	
	@Override
	public Response deleteConstrGroundHandover(Long id) {
		boolean checkValueDatabase = constrGroundHandoverBusinessImpl.checkStatusDatabase(id);
		if (checkValueDatabase) {
			return Response.status(Response.Status.FOUND).build();
		} else {
		ConstrGroundHandoverDTO obj = (ConstrGroundHandoverDTO) constrGroundHandoverBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			
			constrGroundHandoverBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
		}
	}
	
	@Override
	public Response deleteList(List<Long> ids){
    	for (Long id: ids){
    		boolean isUsingInOtherModel = constrGroundHandoverBusinessImpl.checkStatusDatabase(id);
    		if(isUsingInOtherModel) {
    			return Response.status(Response.Status.FOUND).build();   			
    		} 
    	}
		constrGroundHandoverBusinessImpl.delete( ids,ConstrGroundHandoverBO.class.getName(),"CONSTR_GROUND_HANDOVER_ID");
		return Response.ok().build();
	}
	
	
	@Override
    public Response exportPDF(ConstrGroundHandoverDTO obj){
    	try {
    		ConstrGroundHandoverDTO data = (ConstrGroundHandoverDTO) constrGroundHandoverBusinessImpl.getAllConstrGroundHandoverById(obj);
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        	String filePath = classloader.getResource("../" + "doc-template").getPath();
        	
        	InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-06.docx"));
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );
            // 2) Create fields metadata to manage lazy loop ([#list Freemarker) for foot notes.
            FieldsMetadata metadata = report.createFieldsMetadata();
            
            metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
            metadata.addFieldAsImage("sign3");
            metadata.addFieldAsImage("sign4");
            
            // 3) Create context Java model
            IContext context = report.createContext();
            
            context.put( "item", data );
            IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider sign3;
            IImageProvider sign4;
            if(StringUtils.isNotEmpty(data.getAdirectorNamePath())){
            	File f = new File(folder2Upload + "/" + data.getAdirectorNamePath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getAmonitorNamePath())){
            	File f = new File(folder2Upload + "/" + data.getAmonitorNamePath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBdirectorNamePath())){
            	File f = new File(folder2Upload + "/" + data.getBdirectorNamePath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign3 = new FileImageProvider(f);
                }else{
                     sign3 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBinChargeConstructNamePath())){
            	File f = new File(folder2Upload + "/" + data.getBinChargeConstructNamePath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign4 = new FileImageProvider(f);
                }else{
                     sign4 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(data.getStatusCa() == 2){
            	data.setAdirectorNameSign(data.getAdirectorName());
            	data.setAinChargeMonitorNameSign(data.getAinChargeMonitorName());
            	data.setAmonitorNameSign(data.getAmonitorName());
            	data.setBdirectorNameSign(data.getBdirectorName());
            	data.setBinChargeConstructNameSign(data.getBinChargeConstructName());
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
                context.put("sign3", sign3);
                context.put("sign4", sign4);
            } else {
            	data.setAdirectorNameSign("");
            	data.setAinChargeMonitorNameSign("");
            	data.setAmonitorNameSign("");
            	data.setBdirectorNameSign("");
            	data.setBinChargeConstructNameSign("");
            	context.put("sign1", none);
                context.put("sign2", none);
                context.put("sign3", none);
                context.put("sign4", none);
            }
            //pdf
            if(obj.getDocOrPdf()){
            	  File fout = new File(folder2Upload + "/BM-TCNT-06.doc");
                  OutputStream out = new FileOutputStream(fout);
      			report.process(context, out);
      			out.flush();
      			out.close();
      			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-06.doc");
      			return Response.ok(Collections.singletonMap("fileName", path))
      	        		.build();
            } else{
            File fout = new File(folder2Upload + "/BM-TCNT-06.pdf");
            OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-06.pdf");
			return Response.ok(Collections.singletonMap("fileName", path))
	        		.build();
            }
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
    	
//    	return Response.ok(Response.Status.CREATED).build();
	}

	@Override
	public Response deleteOne(Long id) {
	boolean result = constrGroundHandoverBusinessImpl.deleteOne(id);
		if(result){
			return Response.ok(Response.Status.NO_CONTENT).build();
		}else{
			return Response.ok(Response.Status.BAD_REQUEST).build();
		}
	}
}
