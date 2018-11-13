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
import java.util.HashMap;
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
import com.viettel.erp.business.AMaterialHandoverBusinessImpl;
import com.viettel.erp.business.AMaterialHandoverMerListBusinessImpl;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.CompletionDrawingBusinessImpl;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.AMaterialHandoverMerListDTO;
import com.viettel.erp.dto.BienBanBanGiaoAcapDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
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
public class AMaterialHandoverRsServiceImpl implements AMaterialHandoverRsService {
	
	private static final CompressionType TYPE = CompressionType.ZIP;
    private static final CompressionLevel LEVEL = ZipLevel.NORMAL;

    static Logger LOGGER = LoggerFactory.getLogger(AMaterialHandoverRsServiceImpl.class);
    @Autowired
    CompletionDrawingBusinessImpl completionDrawingBusinessImpl;
    
    @Autowired
    UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;
    
    @Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
    
    @Value("${completionDrawing.attachType}")
    private Long attachType;
    
    @Value("${completionDrawing.folder}")
	private String folder;

	 @Value("${folder_upload}")
		private String folder2Upload;
	 
    @Autowired
    AMaterialHandoverBusinessImpl aMaterialHandoverBusinessImpl;
    
    @Autowired
    AMaterialHandoverMerListBusinessImpl aMaterialHandoverMerlistBusinessImpl;
    
    @Override
	public Response getThoiGianBanGiao(AMaterialHandoverDTO dto) {
    	List<AMaterialHandoverDTO> ls = aMaterialHandoverBusinessImpl.getThoiGianBanGiao(dto);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
    
    
    @Override
	public Response exportList(List<Long> data) {
    	List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			AMaterialHandoverDTO dto = new AMaterialHandoverDTO();
			dto.setAmaterialHandoverId(l);
			String filename = exportOne(dto);
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
            fileCompressor.setCompressedPath(filePath + File.separatorChar  +"bangiaoacap." + TYPE.getExtension());
            fileCompressor.compress();
            String path  =   UEncrypt.encryptFileUploadPath("bangiaoacap." + TYPE.getExtension());
            
            return Response.ok(java.util.Collections.singletonMap("fileName",path))
	        		.build();
        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}
    
    private String exportOne(AMaterialHandoverDTO obj) {
    	try
        {
    		BienBanBanGiaoAcapDTO data = aMaterialHandoverBusinessImpl.getDataExport(obj);
    		String hstart = data.getHandoverFromDate().substring(11, 13)+data.getHandoverFromDate().substring(13, 16);
    		String dstart = data.getHandoverFromDate().substring(0, 2);
    		String mstart = data.getHandoverFromDate().substring(3, 5);
    		String ystart = data.getHandoverFromDate().substring(6, 10);
    		
    		String hend = data.getHandoverToDate().substring(11, 13)+data.getHandoverToDate().substring(13, 16);
    		String dend = data.getHandoverToDate().substring(0, 2);
    		String mend = data.getHandoverToDate().substring(3, 5);
    		String yend = data.getHandoverToDate().substring(6, 10);
    		
    		List<AMaterialHandoverMerListDTO> vttbList = aMaterialHandoverMerlistBusinessImpl.getListAMaterialHandOverMerList(obj);
    		List<AMaterialHandoverMerListDTO> vttbListAdd = new ArrayList<>();
    		for (int i = 0; i < vttbList.size(); i++) {
    			if(vttbList.get(i).getActualReceiveQuantity() != 0){
    				vttbListAdd.add(vttbList.get(i));
    			}
			}
    		data.setVttbList(vttbListAdd);
    		
    		data.setHstart(hstart);
    		data.setDstart(dstart);
    		data.setMstart(mstart);
    		data.setYstart(ystart);
    		data.setHend(hend);
    		data.setDend(dend);
    		data.setMend(mend);
    		data.setYend(yend);
    		
    		
    		for (int j = 0; j < data.getVttbList().size(); j++) {
    			data.getVttbList().get(j).setStt(j + 1);
    			if(data.getVttbList().get(j).getQualityStatus() == 1) {
    				data.getVttbList().get(j).setQualityStatusText("Tốt");
    			}else {
    				data.getVttbList().get(j).setQualityStatusText("Hỏng");
    			}
    		}
    		
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        	String filePath = classloader.getResource("../" + "doc-template").getPath();
        	
        	InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-08.docx"));
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );

            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
            metadata.addFieldAsImage("sign3");
            metadata.addFieldAsImage("sign4");
            metadata.load( "cvnts", AMaterialHandoverMerListDTO.class, true );
            IContext context = report.createContext();
            
            context.put( "item", data );
            context.put( "cvnts", data.getVttbList() );
            
            IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider sign3;
            IImageProvider sign4;
            if(StringUtils.isNotEmpty(data.getAdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getAdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getAdirectorIdPath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getAdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getAdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getAdirectorIdPath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getBdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign3 = new FileImageProvider(f);
                }else{
                     sign3 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getBdirectorIdPath()));
            } else{
            	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBreceivePersonIdPath())){
            	File f = new File(folder2Upload + "/" + data.getBreceivePersonIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign4 = new FileImageProvider(f);
                }else{
                     sign4 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getBreceivePersonIdPath()));
            } else{
            	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            
            if(data.getStatusCa() == 2){
            	data.setAperson1Sign(data.getAperson1());
            	data.setAperson2Sign(data.getAperson2());
            	data.setBperson1Sign(data.getBperson1());
            	data.setBperson2Sign(data.getBperson2());
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
                context.put("sign3", sign3);
                context.put("sign4", sign4);
            } else {
            	data.setAperson1Sign("");
            	data.setAperson2Sign("");
            	data.setBperson1Sign("");
            	data.setBperson2Sign("");
            	context.put("sign1", none);
                context.put("sign2", none);
                context.put("sign3", none);
                context.put("sign4", none);
            }
            
            String pathFile = folder2Upload + "/"+obj.getAmaterialHandoverId()+"-BM-TCNT-08.pdf";
            File fout = new File(pathFile);
            OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return obj.getAmaterialHandoverId()+"-BM-TCNT-08.pdf";
        }
        catch ( Exception ex )
        {
        	LOGGER.error(ex.getMessage(), ex);
        }/*
        catch ( XDocReportException e )
        {
            e.printStackTrace();
        }
    	return null;*/
		return null;
    }
   
    
    @Override
	public Response export(AMaterialHandoverDTO obj) {
    	try
        {
    		BienBanBanGiaoAcapDTO data = aMaterialHandoverBusinessImpl.getDataExport(obj);
    		String hstart = data.getHandoverFromDate().substring(11, 13)+data.getHandoverFromDate().substring(13, 16);
    		String dstart = data.getHandoverFromDate().substring(0, 2);
    		String mstart = data.getHandoverFromDate().substring(3, 5);
    		String ystart = data.getHandoverFromDate().substring(6, 10);
    		
    		String hend = data.getHandoverToDate().substring(11, 13)+data.getHandoverToDate().substring(13, 16);
    		String dend = data.getHandoverToDate().substring(0, 2);
    		String mend = data.getHandoverToDate().substring(3, 5);
    		String yend = data.getHandoverToDate().substring(6, 10);
    		
    		List<AMaterialHandoverMerListDTO> vttbList = aMaterialHandoverMerlistBusinessImpl.getListAMaterialHandOverMerList(obj);
    		List<AMaterialHandoverMerListDTO> vttbListAdd = new ArrayList<>();
    		for (int i = 0; i < vttbList.size(); i++) {
    			if(vttbList.get(i).getActualReceiveQuantity() != 0){
    				vttbListAdd.add(vttbList.get(i));
    			}
			}
    		data.setVttbList(vttbListAdd);
    		
    		data.setHstart(hstart);
    		data.setDstart(dstart);
    		data.setMstart(mstart);
    		data.setYstart(ystart);
    		data.setHend(hend);
    		data.setDend(dend);
    		data.setMend(mend);
    		data.setYend(yend);
    		
    		for (int j = 0; j < data.getVttbList().size(); j++) {
    			data.getVttbList().get(j).setStt(j + 1);
    			if(data.getVttbList().get(j).getQualityStatus() == 1) {
    				data.getVttbList().get(j).setQualityStatusText("Tốt");
    			}else {
    				data.getVttbList().get(j).setQualityStatusText("Hỏng");
    			}
    		}
    		
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        	String filePath = classloader.getResource("../" + "doc-template").getPath();
        	
        	InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-08.docx"));
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );

            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
            metadata.addFieldAsImage("sign3");
            metadata.addFieldAsImage("sign4");
            metadata.load( "cvnts", AMaterialHandoverMerListDTO.class, true );
            IContext context = report.createContext();
            
            context.put( "item", data );
            context.put( "cvnts", data.getVttbList() );
            
            IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider sign3;
            IImageProvider sign4;
            if(StringUtils.isNotEmpty(data.getAdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getAdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getAdirectorIdPath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getAdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getAdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getAdirectorIdPath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getBdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign3 = new FileImageProvider(f);
                }else{
                     sign3 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getBdirectorIdPath()));
            } else{
            	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBreceivePersonIdPath())){
            	File f = new File(folder2Upload + "/" + data.getBreceivePersonIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign4 = new FileImageProvider(f);
                }else{
                     sign4 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getBreceivePersonIdPath()));
            } else{
            	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            
            if(data.getStatusCa() == 2){
            	data.setAperson1Sign(data.getAperson1());
            	data.setAperson2Sign(data.getAperson2());
            	data.setBperson1Sign(data.getBperson1());
            	data.setBperson2Sign(data.getBperson2());
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
                context.put("sign3", sign3);
                context.put("sign4", sign4);
            } else {
            	data.setAperson1Sign("");
            	data.setAperson2Sign("");
            	data.setBperson1Sign("");
            	context.put("sign1", none);
                context.put("sign2", none);
                context.put("sign3", none);
                context.put("sign4", none);
            }
            File fout = new File(folder2Upload + "/BM-TCNT-08.pdf");
            OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-08.pdf");
			return Response.ok(Collections.singletonMap("fileName", path))
	        		.build();
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
	public Response exportDoc(AMaterialHandoverDTO obj) {
    	try
        {
    		BienBanBanGiaoAcapDTO data = aMaterialHandoverBusinessImpl.getDataExport(obj);
    		String hstart = data.getHandoverFromDate().substring(11, 13)+data.getHandoverFromDate().substring(13, 16);
    		String dstart = data.getHandoverFromDate().substring(0, 2);
    		String mstart = data.getHandoverFromDate().substring(3, 5);
    		String ystart = data.getHandoverFromDate().substring(6, 10);
    		
    		String hend = data.getHandoverToDate().substring(11, 13)+data.getHandoverToDate().substring(13, 16);
    		String dend = data.getHandoverToDate().substring(0, 2);
    		String mend = data.getHandoverToDate().substring(3, 5);
    		String yend = data.getHandoverToDate().substring(6, 10);
    		
    		List<AMaterialHandoverMerListDTO> vttbList = aMaterialHandoverMerlistBusinessImpl.getListAMaterialHandOverMerList(obj);
    		List<AMaterialHandoverMerListDTO> vttbListAdd = new ArrayList<>();
    		for (int i = 0; i < vttbList.size(); i++) {
    			if(vttbList.get(i).getActualReceiveQuantity() != 0){
    				vttbListAdd.add(vttbList.get(i));
    			}
			}
    		data.setVttbList(vttbListAdd);
    		
    		data.setHstart(hstart);
    		data.setDstart(dstart);
    		data.setMstart(mstart);
    		data.setYstart(ystart);
    		data.setHend(hend);
    		data.setDend(dend);
    		data.setMend(mend);
    		data.setYend(yend);
    		
    		for (int j = 0; j < data.getVttbList().size(); j++) {
    			data.getVttbList().get(j).setStt(j + 1);
    			if(data.getVttbList().get(j).getQualityStatus() == 1) {
    				data.getVttbList().get(j).setQualityStatusText("Tốt");
    			}else {
    				data.getVttbList().get(j).setQualityStatusText("Hỏng");
    			}
    		}
    		
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        	String filePath = classloader.getResource("../" + "doc-template").getPath();
        	
        	InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-08.docx"));
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );

            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
            metadata.addFieldAsImage("sign3");
            metadata.addFieldAsImage("sign4");
            metadata.load( "cvnts", AMaterialHandoverMerListDTO.class, true );
            IContext context = report.createContext();
            
            context.put( "item", data );
            context.put( "cvnts", data.getVttbList() );
            IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider sign3;
            IImageProvider sign4;
            if(StringUtils.isNotEmpty(data.getAdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getAdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getAdirectorIdPath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getAdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getAdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getAdirectorIdPath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getBdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign3 = new FileImageProvider(f);
                }else{
                     sign3 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getBdirectorIdPath()));
            } else{
            	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBreceivePersonIdPath())){
            	File f = new File(folder2Upload + "/" + data.getBreceivePersonIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign4 = new FileImageProvider(f);
                }else{
                     sign4 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getBreceivePersonIdPath()));
            } else{
            	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            
            if(data.getStatusCa() == 2){
            	data.setAperson1Sign(data.getAperson1());
            	data.setAperson2Sign(data.getAperson2());
            	data.setBperson1Sign(data.getBperson1());
            	data.setBperson2Sign(data.getBperson2());
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
                context.put("sign3", sign3);
                context.put("sign4", sign4);
            } else {
            	data.setAperson1Sign("");
            	data.setAperson2Sign("");
            	data.setBperson1Sign("");
            	data.setBperson2Sign("");
            	context.put("sign1", none);
                context.put("sign2", none);
                context.put("sign3", none);
                context.put("sign4", none);
            }
            File fout = new File(folder2Upload + "/BM-TCNT-08.docx");
            OutputStream out = new FileOutputStream(fout);
			report.process( context, out );
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-08.docx");
			return Response.ok(Collections.singletonMap("fileName", path))
	        		.build();
            
//            File fout = new File(folder2Upload + "/BM-TCNT-08.pdf");
//            OutputStream out = new FileOutputStream(fout);
//			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
//			report.convert(context, options, out);
//			out.flush();
//			out.close();
//			return Response.ok(Collections.singletonMap("fileName", "BM-TCNT-08.pdf"))
//	        		.build();
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
	public Response exportListDoc(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			AMaterialHandoverDTO dto = new AMaterialHandoverDTO();
			dto.setAmaterialHandoverId(l);
			String filename = exportOneDoc(dto);
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
            fileCompressor.setCompressedPath(filePath + File.separatorChar  +"bangiaoacap." + TYPE.getExtension());
            fileCompressor.compress();
            String path  =   UEncrypt.encryptFileUploadPath("bangiaoacap." + TYPE.getExtension());
            
            return Response.ok(java.util.Collections.singletonMap("fileName",path))
	        		.build();
        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}
    
    private String exportOneDoc(AMaterialHandoverDTO obj) {
    	try
        {
    		BienBanBanGiaoAcapDTO data = aMaterialHandoverBusinessImpl.getDataExport(obj);
    		String hstart = data.getHandoverFromDate().substring(11, 13)+data.getHandoverFromDate().substring(13, 16);
    		String dstart = data.getHandoverFromDate().substring(0, 2);
    		String mstart = data.getHandoverFromDate().substring(3, 5);
    		String ystart = data.getHandoverFromDate().substring(6, 10);
    		
    		String hend = data.getHandoverToDate().substring(11, 13)+data.getHandoverToDate().substring(13, 16);
    		String dend = data.getHandoverToDate().substring(0, 2);
    		String mend = data.getHandoverToDate().substring(3, 5);
    		String yend = data.getHandoverToDate().substring(6, 10);
    		
    		List<AMaterialHandoverMerListDTO> vttbList = aMaterialHandoverMerlistBusinessImpl.getListAMaterialHandOverMerList(obj);
    		List<AMaterialHandoverMerListDTO> vttbListAdd = new ArrayList<>();
    		for (int i = 0; i < vttbList.size(); i++) {
    			if(vttbList.get(i).getActualReceiveQuantity() != 0){
    				vttbListAdd.add(vttbList.get(i));
    			}
			}
    		data.setVttbList(vttbListAdd);
    		
    		data.setHstart(hstart);
    		data.setDstart(dstart);
    		data.setMstart(mstart);
    		data.setYstart(ystart);
    		data.setHend(hend);
    		data.setDend(dend);
    		data.setMend(mend);
    		data.setYend(yend);
    		
    		
    		for (int j = 0; j < data.getVttbList().size(); j++) {
    			data.getVttbList().get(j).setStt(j + 1);
    			if(data.getVttbList().get(j).getQualityStatus() == 1) {
    				data.getVttbList().get(j).setQualityStatusText("Tốt");
    			}else {
    				data.getVttbList().get(j).setQualityStatusText("Hỏng");
    			}
    		}
    		
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        	String filePath = classloader.getResource("../" + "doc-template").getPath();
        	
        	InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-08.docx"));
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Freemarker );

            FieldsMetadata metadata = report.createFieldsMetadata();
            metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
            metadata.addFieldAsImage("sign3");
            metadata.addFieldAsImage("sign4");
            metadata.load( "cvnts", AMaterialHandoverMerListDTO.class, true );
            IContext context = report.createContext();
            
            context.put( "item", data );
            context.put( "cvnts", data.getVttbList() );
            IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider sign3;
            IImageProvider sign4;
            if(StringUtils.isNotEmpty(data.getAdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getAdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getAdirectorIdPath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getAdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getAdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getAdirectorIdPath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBdirectorIdPath())){
            	File f = new File(folder2Upload + "/" + data.getBdirectorIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign3 = new FileImageProvider(f);
                }else{
                     sign3 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getBdirectorIdPath()));
            } else{
            	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            if(StringUtils.isNotEmpty(data.getBreceivePersonIdPath())){
            	File f = new File(folder2Upload + "/" + data.getBreceivePersonIdPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign4 = new FileImageProvider(f);
                }else{
                     sign4 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            	//sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getBreceivePersonIdPath()));
            } else{
            	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            
            if(data.getStatusCa() == 2){
            	data.setAperson1Sign(data.getAperson1());
            	data.setAperson2Sign(data.getAperson2());
            	data.setBperson1Sign(data.getBperson1());
            	data.setBperson2Sign(data.getBperson2());
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
                context.put("sign3", sign3);
                context.put("sign4", sign4);
            } else {
            	data.setAperson1Sign("");
            	data.setAperson2Sign("");
            	data.setBperson1Sign("");
            	data.setBperson2Sign("");
            	context.put("sign1", none);
                context.put("sign2", none);
                context.put("sign3", none);
                context.put("sign4", none);
            }
            File fout = new File(folder2Upload + "/"+obj.getAmaterialHandoverId()+ "-BM-TCNT-08.docx");
            OutputStream out = new FileOutputStream(fout);
			report.process( context, out );
			out.flush();
			out.close();
			return obj.getAmaterialHandoverId()+"-BM-TCNT-08.docx";
            
//            String pathFile = folder2Upload + "/"+obj.getAmaterialHandoverId()+"-BM-TCNT-08.pdf";
//            File fout = new File(pathFile);
//            OutputStream out = new FileOutputStream(fout);
//			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
//			report.convert(context, options, out);
//			out.flush();
//			out.close();
//			return obj.getAmaterialHandoverId()+"-BM-TCNT-08.pdf";
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
	public Response addListAMaterial(List<AMaterialHandoverDTO> listBTVT) {
    	Long id = aMaterialHandoverBusinessImpl.addListAMaterial(listBTVT);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
	}
    
    @Override
	public Response deleteAMaterialHandoverByCode(List<String> listCode) {
    	for(int i =listCode.size()-1;i>=0;i--){
    		if(aMaterialHandoverBusinessImpl.checkStatusDatabase(listCode.get(i))){
    			listCode.remove(i);
        	}
    	}
    	if(listCode.size()>0){
    		aMaterialHandoverBusinessImpl.deleteAMaterialHandoverByCode(listCode);
			return Response.ok(Response.Status.OK).build();
		}
    	return Response.status(Response.Status.BAD_REQUEST).build();
	}
    
    @Override
	public Response getAMaterialHandoverByCongTrinh(AMaterialHandoverDTO obj) {
    	List<AMaterialHandoverDTO> ls = aMaterialHandoverBusinessImpl.getAMaterialHandoverByCongTrinh(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}

    @Override
    public Response getAMaterialHandover() {
        List<AMaterialHandoverDTO> ls = aMaterialHandoverBusinessImpl.getAll();
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
    public Response getAMaterialHandoverById(Long id) {
        AMaterialHandoverDTO obj = (AMaterialHandoverDTO) aMaterialHandoverBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateAMaterialHandover(AMaterialHandoverDTO obj) {
        Long id = aMaterialHandoverBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addAMaterialHandover(AMaterialHandoverDTO obj) {
    	try { 		
    		Long id ;
    		// nếu đã có code 
    		if(obj.getCode() != null) {
    			// check status trình duyệt/ ký hay chưa
    			if(aMaterialHandoverBusinessImpl.checkStatusDatabase(obj.getAmaterialHandoverId()+"")){
    				// nếu đã ký hoặc trình duyệt thì ko làm gì cả
        			id = -2l;
        			return  Response.ok(id).build();
        		}
    			//nếu chưa trình duyệt/ ký thì xóa
//    			AMaterialHandoverDTO objdel = (AMaterialHandoverDTO) aMaterialHandoverBusinessImpl.getOneById(obj.getAmaterialHandoverId());
//        		aMaterialHandoverBusinessImpl.delete(objdel);
    			List<String> listCode =  new ArrayList<String>();
    			listCode.add(obj.getAmaterialHandoverId()+"");
    			aMaterialHandoverBusinessImpl.deleteForUpdateByCode(listCode);
    		}
    		// set data
    		AMaterialHandoverDTO objInsert = setDataObjectInsert(obj);
    		//insert data
            id = aMaterialHandoverBusinessImpl.saveTable(objInsert);
            HashMap<Integer, Long> hm = new HashMap<>();
            hm.put(1, id);
            if (id == 0l) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else {
                return Response.ok(hm).build();
            }
        	
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
    	
    	
    }

    private AMaterialHandoverDTO setDataObjectInsert(AMaterialHandoverDTO obj) {
    	if(obj.getCode() == null) {
    		String code = aMaterialHandoverBusinessImpl.autoGenCode();
    		obj.setCode(code);
    	}
    	CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("A_MATERIAL_HANDOVER");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
    	constrCompleteRecordMap.setDataTableName("A_MATERIAL_HANDOVER");
    	constrCompleteRecordMap.setDataTableId("A_MATERIAL_HANDOVER_ID");
    	constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
    	constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
    	constrCompleteRecordMap.setStatus(0L);
    	constrCompleteRecordMap.setLevelOrder(1L);
    	constrCompleteRecordMap.setCreatedDate(new Date());
    	constrCompleteRecordMap.setConstructionId(obj.getConstructId());
    	constrCompleteRecordMap.setCode(obj.getCode());
    	obj.setConstrCompleteRecordsMap(constrCompleteRecordMap);
    	obj.setCreateDate(new Date());
    	
    	return obj;
	}


	@Override
    public Response deleteAMaterialHandover(Long id) {
        AMaterialHandoverDTO obj = (AMaterialHandoverDTO) aMaterialHandoverBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            aMaterialHandoverBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
	
	@Override
	public Response getAmaterialhandoverforcontruction(AMaterialHandoverDTO obj) {
		 List<AMaterialHandoverDTO> ls = aMaterialHandoverBusinessImpl.getAmaterialhandoverforcontruction(obj);
	        if (ls == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	            return Response.ok(ls).build();
	        }
	}


	



	
	

	
}
