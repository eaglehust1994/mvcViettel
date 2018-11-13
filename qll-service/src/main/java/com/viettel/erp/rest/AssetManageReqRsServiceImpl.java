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
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.espringtran.compressor4j.bean.CompressionLevel;
import com.espringtran.compressor4j.bean.CompressionType;
import com.espringtran.compressor4j.bean.ZipLevel;
import com.viettel.erp.business.AssetManageReqBusinessImpl;
import com.viettel.erp.dto.AbSettlementValueDTO;
import com.viettel.erp.dto.AssetManageReqDTO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
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
public class AssetManageReqRsServiceImpl implements AssetManageReqRsService {
	
	private static final CompressionType TYPE = CompressionType.ZIP;
    private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
    
    static Logger LOGGER = LoggerFactory.getLogger(AssetManageReqRsServiceImpl.class);

    @Autowired
    AssetManageReqBusinessImpl assetManageReqBusinessImpl;
    
    @Value("${folder_upload}")
	private String folder2Upload;

    @Override
    public Response getAssetManageReq() {
        List<AssetManageReqDTO> ls = assetManageReqBusinessImpl.getAll();
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
    public Response getAssetManageReqById(Long id) {
        AssetManageReqDTO obj = (AssetManageReqDTO) assetManageReqBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateAssetManageReq(AssetManageReqDTO obj) {
        Long id = assetManageReqBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addAssetManageReq(AssetManageReqDTO obj) {
        Long id = assetManageReqBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteAssetManageReq(Long id) {
        AssetManageReqDTO obj = (AssetManageReqDTO) assetManageReqBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            assetManageReqBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getValueSupplies(Long id) {
		List<AssetManageReqDTO> obj = (List<AssetManageReqDTO>) assetManageReqBusinessImpl.getValueSuppliesById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
	}

	@Override
	public Response getTotalPrice(Long id) {
		List<AssetManageReqDTO> obj = (List<AssetManageReqDTO>) assetManageReqBusinessImpl.getTotalPriceById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
	}

	@Override
	public Response exportFilePdf(AssetManageReqDTO obj) {
		try {		//khong dung ham nay nua	
				Calendar c = Calendar.getInstance();
				obj.setDate(new Long(c.get(Calendar.DATE)));
				obj.setMonth(new Long(c.get(Calendar.MONTH)+1));
				//String sYear = Long.toString(new Long(c.get(Calendar.YEAR)));
                                int sYear=c.get(Calendar.YEAR);
				//sYear.replaceAll(",", "");
				obj.setYear(String.valueOf(sYear));
			    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	        	String filePath = classloader.getResource("../" + "doc-template").getPath();
	        	InputStream in = new FileInputStream(new File(filePath + "/QT-AB-1.docx"));
	        	IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
	        	FieldsMetadata metadata = report.createFieldsMetadata();
				metadata.addFieldAsImage("sign1");
	            metadata.addFieldAsImage("sign2");
	            metadata.load("bc", AbSettlementValueDTO.class, true);
	            
				IContext context = report.createContext();
	            context.put("item", obj);
	            context.put("bc", obj.getAcceptMaterialValue());
	            
	            IImageProvider sign1;
	            IImageProvider sign2;
	            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
	            if(StringUtils.isNotEmpty(obj.getaDirectorIdPath())){
	            	sign1 = new FileImageProvider(new File(folder2Upload + "/" + obj.getaDirectorIdPath()));
	            } else{
	            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            
	            if(StringUtils.isNotEmpty(obj.getbDirectorIdPath())){
	            	sign2 = new FileImageProvider(new File(folder2Upload + "/" + obj.getbDirectorIdPath()));
	            } else{
	            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	          //check status CA =  2 
	            if(obj.getStatusCa() == 1){
	            	context.put("sign1", sign1);
	                context.put("sign2", sign2);
	                System.out.println("==================" + obj.getStatusCa() + obj.getbDirectorIdPath());
	            } else {
	            	context.put("sign1", none);
	                context.put("sign2", none);
	            }
	            
				File fout = new File(folder2Upload + "/QT-AB-1.pdf");
				OutputStream out = new FileOutputStream(fout);
				Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
				report.convert(context, options, out);
				out.flush();
				out.close();
				System.out.println("export file QT-AB-1.pdf done");
				return Response.ok(Collections.singletonMap("fileName", "QT-AB-1.pdf")).build();
        } catch ( Exception ex ){
        	LOGGER.error(ex.getMessage(), ex);
        } 
    	
    	return null;
	}
	
	@Override
	public Response exportFileDoc(AssetManageReqDTO obj) {
		try {//khong dung ham nay nua
			   Calendar c = Calendar.getInstance();
			   obj.setDate(new Long(c.get(Calendar.DATE)));
			   obj.setMonth(new Long(c.get(Calendar.MONTH)+1));
			   int sYear = c.get(Calendar.YEAR);
//			   sYear.replaceAll(",", "");
			   obj.setYear(String.valueOf(sYear));
			   
			   ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			   String filePath = classloader.getResource("../" + "doc-template").getPath();
			   InputStream in = new FileInputStream(new File(filePath + "/QT-AB-1.docx"));
			   IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			   FieldsMetadata metadata = report.createFieldsMetadata();
				metadata.addFieldAsImage("sign1");
	            metadata.addFieldAsImage("sign2");
				metadata.load("bc", QualityCableMeaResultDTO.class, true);
				
				IContext context = report.createContext();
				context.put("item", obj);
				context.put("bc", obj.getAcceptMaterialValue());
				
				IImageProvider sign1;
	            IImageProvider sign2;
	            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
	            if(StringUtils.isNotEmpty(obj.getaDirectorIdPath())){
	            	sign1 = new FileImageProvider(new File(folder2Upload + "/" + obj.getaDirectorIdPath()));
	            } else{
	            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            
	            if(StringUtils.isNotEmpty(obj.getbDirectorIdPath())){
	            	sign2 = new FileImageProvider(new File(folder2Upload + "/" + obj.getbDirectorIdPath()));
	            } else{
	            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	          //check status CA =  2 
	            if(obj.getStatusCa() == 1){
	            	context.put("sign1", sign1);
	                context.put("sign2", sign2);
	            } else {
	            	context.put("sign1", none);
	                context.put("sign2", none);
	            }
	            
				File fout = new File(folder2Upload + "/QT-AB-1.docx");
				OutputStream out = new FileOutputStream(fout);
				report.process(context, out);
				out.flush();
				out.close();
			   System.out.println("export file QT-AB-1.doc done");
			   return Response.ok(Collections.singletonMap("fileName", "QT-AB-1.docx")).build();
			  } catch (Exception ex) {
				  LOGGER.error(ex.getMessage(), ex);
			  }
    	return null;
	}
}
