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
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstrOrganizationPlanBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.business.TitAziConstrAcceptBusinessImpl;
import com.viettel.erp.dto.TitAziConstrAcceptDTO;
import com.viettel.erp.dto.TitAziConstrAcceptListDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.ktts2.common.UEncrypt;

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
public class TitAziConstrAcceptRsServiceImpl implements TitAziConstrAcceptRsService {
	
	private static final CompressionType TYPE = CompressionType.ZIP;
    private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
    
    static Logger LOGGER = LoggerFactory.getLogger(CompletionDrawingRsServiceImpl.class);
    
    @Value("${completionDrawing.attachType}")
	private Long attachType;

	@Value("${completionDrawing.folder}")
	private String folder;
	
	@Value("${folder_upload}")
	private String folder2Upload;
	
    @Autowired
    TitAziConstrAcceptBusinessImpl titAziConstrAcceptBusinessImpl;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
	
	@Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;
	
    @Override
    public Response getTitAziConstrAccept() {
        List<TitAziConstrAcceptDTO> ls = titAziConstrAcceptBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
    }
    
    @Override
    public Response getTitAziConstrAcceptById(Long id) {
        TitAziConstrAcceptDTO obj = (TitAziConstrAcceptDTO) titAziConstrAcceptBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateTitAziConstrAccept(TitAziConstrAcceptDTO obj) {
    	Date date = new Date();
    	obj.setCreatedDate(date);
        Long id = titAziConstrAcceptBusinessImpl.update(obj);
        String nameTable = "TIT_AZI_CONSTR_ACCEPT";
        if(obj.getStatusCa() == 0l){
        	qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getTitAziConstrAcceptId() , nameTable);
        }
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addTitAziConstrAccept(TitAziConstrAcceptDTO obj) {
    	Long constructionId = obj.getConstructId();
    	
    	Date date = new Date();
    	obj.setCreatedDate(date);
    	obj.setStatusCa(0l);
    	obj.setIsActive(1l);
    	
    	String code = titAziConstrAcceptBusinessImpl.autoGenCode();
    	obj.setCode(code);
    	Long id = titAziConstrAcceptBusinessImpl.save(obj);
    	
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	try {
        		
				 constrCompleteRecordsMapBusinessImpl.insert(constructionId, "TIT_AZI_CONSTR_ACCEPT",
							"TIT_AZI_CONSTR_ACCEPT_ID", id, obj.getCreatedUserId(),code);
				} catch (Exception e) {
					titAziConstrAcceptBusinessImpl.delete(obj);
					LOGGER.error(e.getMessage(), e);
				}
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteTitAziConstrAccept(Long id) {
        TitAziConstrAcceptDTO obj = (TitAziConstrAcceptDTO) titAziConstrAcceptBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            titAziConstrAcceptBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response findByConstructId(TitAziConstrAcceptDTO dto) {
		List<TitAziConstrAcceptDTO> list= titAziConstrAcceptBusinessImpl.findByConstructId(dto);
		if (list == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
             return Response.ok(list).build();
        }
	}

	

	@Override
	public Response deleteList(List<String> listId) {
		boolean ls = titAziConstrAcceptBusinessImpl.deleteList(listId);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	
	
	private String exportDoc(TitAziConstrAcceptDTO data) {
		try {
			TitAziConstrAcceptDTO exportTitAziDTO = titAziConstrAcceptBusinessImpl.getExportTitAzi(data);
			
			 List<TitAziConstrAcceptListDTO> listTitAzi = titAziConstrAcceptBusinessImpl.listById(data.getTitAziConstrAcceptId());
			 exportTitAziDTO.setTitAziConstrAcceptList(listTitAzi);

			 for (int j = 0; j < exportTitAziDTO.getTitAziConstrAcceptList().size(); j++) {
					exportTitAziDTO.getTitAziConstrAcceptList().get(j).setStt(j+1);
				}
			 
			exportTitAziDTO.setConclusionExport(exportTitAziDTO.getConclusion()==1 ? "Chấp nhận nghiệm thu công trình xây dựng để đưa vào sử dụng":"Không chấp nhận nghiệm thu");
			
			System.out.println("listTitAzi:"+listTitAzi);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-23.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign11");
            metadata.addFieldAsImage("sign21");
            metadata.addFieldAsImage("sign31");
            metadata.addFieldAsImage("sign41");
            metadata.addFieldAsImage("sign12");
            metadata.addFieldAsImage("sign22");
            metadata.addFieldAsImage("sign32");
            metadata.addFieldAsImage("sign42");
			metadata.load("bc", TitAziConstrAcceptListDTO.class, true);
			
			IContext context = report.createContext();
			context.put("item", exportTitAziDTO);
			
			context.put("bc", exportTitAziDTO.getTitAziConstrAcceptList());
			IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider sign3;
            IImageProvider sign4;
            // sign1
            if(StringUtils.isNotEmpty(exportTitAziDTO.getAdirectorPath())){
            	/*sign1 = new FileImageProvider(new File(folder2Upload + "/" + exportTitAziDTO.getAdirectorPath()));*/
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getAdirectorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            // sign2
            if(StringUtils.isNotEmpty(exportTitAziDTO.getAinchargemonitorPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getAinchargemonitorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            //sign3
            if(StringUtils.isNotEmpty(exportTitAziDTO.getBdirectorPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getBdirectorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign3 = new FileImageProvider(f);
                }else{
                     sign3 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            //sign4
            if(StringUtils.isNotEmpty(exportTitAziDTO.getBinchargeConstructPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getBinchargeConstructPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign4 = new FileImageProvider(f);
                }else{
                     sign4 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
            }
           
           System.out.println("====================" + folder2Upload + "/" + exportTitAziDTO.getAdirectorPath());
          
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(exportTitAziDTO.getStatusCa() == 2){
            	context.put("sign11", sign1);
                context.put("sign21", sign2);
                context.put("sign31", sign3);
                context.put("sign41", sign4);
                context.put("sign12", sign1);
                context.put("sign22", sign2);
                context.put("sign32", sign3);
                context.put("sign42", sign4);
                exportTitAziDTO.setAdirectorSignName(exportTitAziDTO.getAdirectorName());
            	exportTitAziDTO.setAinchargemonitorSignName(exportTitAziDTO.getAinchargemonitorName());
            	exportTitAziDTO.setBdirectorSignName(exportTitAziDTO.getBdirectorName());
            	exportTitAziDTO.setBinchargeConstructSignName(exportTitAziDTO.getBinchargeConstructName());
            } else {
            	exportTitAziDTO.setAdirectorSignName("");
            	exportTitAziDTO.setAinchargemonitorSignName("");
            	exportTitAziDTO.setBdirectorSignName("");
            	exportTitAziDTO.setBinchargeConstructSignName("");
            	context.put("sign11", none);
                context.put("sign21", none);
                context.put("sign31", none);
                context.put("sign41", none);
                context.put("sign12", none);
                context.put("sign22", none);
                context.put("sign32", none);
                context.put("sign42", none);
            }
			File fout = new File(folder2Upload+"/" + exportTitAziDTO.getTitAziConstrAcceptId() +"-BM-TCNT-23.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return exportTitAziDTO.getTitAziConstrAcceptId() + "-BM-TCNT-23.docx";

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public Response exportListDoc(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			TitAziConstrAcceptDTO dto = new TitAziConstrAcceptDTO();
			dto.setTitAziConstrAcceptId(l);
			String filename = exportDoc(dto);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "nghiemthuhoanthanh_tit." + TYPE.getExtension());
			
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("nghiemthuhoanthanh_tit." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	private String exportOne(TitAziConstrAcceptDTO data) {
		try {
			TitAziConstrAcceptDTO exportTitAziDTO = titAziConstrAcceptBusinessImpl.getExportTitAzi(data);
			
			 List<TitAziConstrAcceptListDTO> listTitAzi = titAziConstrAcceptBusinessImpl.listById(data.getTitAziConstrAcceptId());
			 exportTitAziDTO.setTitAziConstrAcceptList(listTitAzi);

			 for (int j = 0; j < exportTitAziDTO.getTitAziConstrAcceptList().size(); j++) {
					exportTitAziDTO.getTitAziConstrAcceptList().get(j).setStt(j+1);
				}
			 
			exportTitAziDTO.setConclusionExport(exportTitAziDTO.getConclusion()==1 ? "Chấp nhận nghiệm thu công trình xây dựng để đưa vào sử dụng":"Không chấp nhận nghiệm thu");
			
			System.out.println("listTitAzi:"+listTitAzi);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-23.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign11");
            metadata.addFieldAsImage("sign21");
            metadata.addFieldAsImage("sign31");
            metadata.addFieldAsImage("sign41");
            metadata.addFieldAsImage("sign12");
            metadata.addFieldAsImage("sign22");
            metadata.addFieldAsImage("sign32");
            metadata.addFieldAsImage("sign42");
			metadata.load("bc", TitAziConstrAcceptListDTO.class, true);
			
			IContext context = report.createContext();
			context.put("item", exportTitAziDTO);
			
			context.put("bc", exportTitAziDTO.getTitAziConstrAcceptList());
			IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider sign3;
            IImageProvider sign4;
            // sign1
            if(StringUtils.isNotEmpty(exportTitAziDTO.getAdirectorPath())){
            	/*sign1 = new FileImageProvider(new File(folder2Upload + "/" + exportTitAziDTO.getAdirectorPath()));*/
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getAdirectorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            // sign2
            if(StringUtils.isNotEmpty(exportTitAziDTO.getAinchargemonitorPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getAinchargemonitorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            //sign3
            if(StringUtils.isNotEmpty(exportTitAziDTO.getBdirectorPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getBdirectorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign3 = new FileImageProvider(f);
                }else{
                     sign3 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            //sign4
            if(StringUtils.isNotEmpty(exportTitAziDTO.getBinchargeConstructPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getBinchargeConstructPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign4 = new FileImageProvider(f);
                }else{
                     sign4 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
            }
           
           System.out.println("====================" + folder2Upload + "/" + exportTitAziDTO.getAdirectorPath());
          
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(exportTitAziDTO.getStatusCa() == 2){
            	context.put("sign11", sign1);
                context.put("sign21", sign2);
                context.put("sign31", sign3);
                context.put("sign41", sign4);
                context.put("sign12", sign1);
                context.put("sign22", sign2);
                context.put("sign32", sign3);
                context.put("sign42", sign4);
                exportTitAziDTO.setAdirectorSignName(exportTitAziDTO.getAdirectorName());
            	exportTitAziDTO.setAinchargemonitorSignName(exportTitAziDTO.getAinchargemonitorName());
            	exportTitAziDTO.setBdirectorSignName(exportTitAziDTO.getBdirectorName());
            	exportTitAziDTO.setBinchargeConstructSignName(exportTitAziDTO.getBinchargeConstructName());
            } else {
            	exportTitAziDTO.setAdirectorSignName("");
            	exportTitAziDTO.setAinchargemonitorSignName("");
            	exportTitAziDTO.setBdirectorSignName("");
            	exportTitAziDTO.setBinchargeConstructSignName("");
            	context.put("sign11", none);
                context.put("sign21", none);
                context.put("sign31", none);
                context.put("sign41", none);
                context.put("sign12", none);
                context.put("sign22", none);
                context.put("sign32", none);
                context.put("sign42", none);
            }
			File fout = new File(folder2Upload+"/" + exportTitAziDTO.getTitAziConstrAcceptId() +"-BM-TCNT-23.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return exportTitAziDTO.getTitAziConstrAcceptId() + "-BM-TCNT-23.pdf";

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response exportList(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			TitAziConstrAcceptDTO dto = new TitAziConstrAcceptDTO();
			dto.setTitAziConstrAcceptId(l);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "nghiemthuhoanthanh_tit." + TYPE.getExtension());
			
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("nghiemthuhoanthanh_tit." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public Response pheduyet(TitAziConstrAcceptDTO dto) {
    	
        boolean bl = titAziConstrAcceptBusinessImpl.pheduyet(dto);
        if (bl == false) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

	@Override
	public Response exportOneDoc(TitAziConstrAcceptDTO data) {
		
			try {
				TitAziConstrAcceptDTO exportTitAziDTO = titAziConstrAcceptBusinessImpl.getExportTitAzi(data);
				
				 List<TitAziConstrAcceptListDTO> listTitAzi = titAziConstrAcceptBusinessImpl.listById(data.getTitAziConstrAcceptId());
				 exportTitAziDTO.setTitAziConstrAcceptList(listTitAzi);

				 for (int j = 0; j < exportTitAziDTO.getTitAziConstrAcceptList().size(); j++) {
						exportTitAziDTO.getTitAziConstrAcceptList().get(j).setStt(j+1);
					}
				 
				exportTitAziDTO.setConclusionExport(exportTitAziDTO.getConclusion()==1 ? "Chấp nhận nghiệm thu công trình xây dựng để đưa vào sử dụng":"Không chấp nhận nghiệm thu");
				
				System.out.println("listTitAzi:"+listTitAzi);
				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				String filePath = classloader.getResource("../" + "doc-template").getPath();

				InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-23.docx"));
				IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

				FieldsMetadata metadata = report.createFieldsMetadata();
				
				metadata.addFieldAsImage("sign11");
	            metadata.addFieldAsImage("sign21");
	            metadata.addFieldAsImage("sign31");
	            metadata.addFieldAsImage("sign41");
	            metadata.addFieldAsImage("sign12");
	            metadata.addFieldAsImage("sign22");
	            metadata.addFieldAsImage("sign32");
	            metadata.addFieldAsImage("sign42");
				
				metadata.load("bc", TitAziConstrAcceptListDTO.class, true);
				
				
				IContext context = report.createContext();
				context.put("item", exportTitAziDTO);
				context.put("bc", exportTitAziDTO.getTitAziConstrAcceptList());
				
				IImageProvider sign1;
	            IImageProvider sign2;
	            IImageProvider sign3;
	            IImageProvider sign4;
	            // sign1
	            if(StringUtils.isNotEmpty(exportTitAziDTO.getAdirectorPath())){
	            	/*sign1 = new FileImageProvider(new File(folder2Upload + "/" + exportTitAziDTO.getAdirectorPath()));*/
	            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getAdirectorPath());
	            	if(f.exists() && !f.isDirectory()) { 
	                    sign1 = new FileImageProvider(f);
	                    
	                }else{
	                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	                     
	                }
	            } else{
	            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            // sign2
	            if(StringUtils.isNotEmpty(exportTitAziDTO.getAinchargemonitorPath())){
	            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getAinchargemonitorPath());
	            	if(f.exists() && !f.isDirectory()) { 
	                    sign2 = new FileImageProvider(f);
	                    
	                }else{
	                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	                     
	                }
	            } else{
	            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            //sign3
	            if(StringUtils.isNotEmpty(exportTitAziDTO.getBdirectorPath())){
	            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getBdirectorPath());
	            	if(f.exists() && !f.isDirectory()) { 
	                    sign3 = new FileImageProvider(f);
	                    
	                }else{
	                     sign3 = new FileImageProvider(new File(filePath + "/none.png"));
	                     
	                }
	            } else{
	            	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	            //sign4
	            if(StringUtils.isNotEmpty(exportTitAziDTO.getBinchargeConstructPath())){
	            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getBinchargeConstructPath());
	            	if(f.exists() && !f.isDirectory()) { 
	                    sign4 = new FileImageProvider(f);
	                    
	                }else{
	                     sign4 = new FileImageProvider(new File(filePath + "/none.png"));
	                     
	                }
	            } else{
	            	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
	            }
	           
	           System.out.println("====================" + folder2Upload + "/" + exportTitAziDTO.getAdirectorPath());
	          
	            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
	            if(exportTitAziDTO.getStatusCa() == 2){
	            	context.put("sign11", sign1);
	                context.put("sign21", sign2);
	                context.put("sign31", sign3);
	                context.put("sign41", sign4);
	                context.put("sign12", sign1);
	                context.put("sign22", sign2);
	                context.put("sign32", sign3);
	                context.put("sign42", sign4);
	                exportTitAziDTO.setAdirectorSignName(exportTitAziDTO.getAdirectorName());
	            	exportTitAziDTO.setAinchargemonitorSignName(exportTitAziDTO.getAinchargemonitorName());
	            	exportTitAziDTO.setBdirectorSignName(exportTitAziDTO.getBdirectorName());
	            	exportTitAziDTO.setBinchargeConstructSignName(exportTitAziDTO.getBinchargeConstructName());
	            } else {
	            	exportTitAziDTO.setAdirectorSignName("");
	            	exportTitAziDTO.setAinchargemonitorSignName("");
	            	exportTitAziDTO.setBdirectorSignName("");
	            	exportTitAziDTO.setBinchargeConstructSignName("");
	            	context.put("sign11", none);
	                context.put("sign21", none);
	                context.put("sign31", none);
	                context.put("sign41", none);
	                context.put("sign12", none);
	                context.put("sign22", none);
	                context.put("sign32", none);
	                context.put("sign42", none);
	            }
	            
				File fout = new File(folder2Upload+"/" +"BM-TCNT-23-"+ exportTitAziDTO.getTitAziConstrAcceptId() + ".docx");
				OutputStream out = new FileOutputStream(fout);
				report.process(context, out);
				out.flush();
				out.close();
				String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-23-"+ exportTitAziDTO.getTitAziConstrAcceptId() + ".docx");
				return Response.ok(Collections.singletonMap("fileName",path))
		        		.build();

			}catch ( IOException e )
	        {
				LOGGER.error(e.getMessage(), e);
	        }
	        catch ( XDocReportException e )
	        {
	        	LOGGER.error(e.getMessage(), e);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
	        	LOGGER.error(e.getMessage(), e);
			}
			return null;
		
	}

	@Override
	public Response exportOnePDF(TitAziConstrAcceptDTO data) {
		try {
			TitAziConstrAcceptDTO exportTitAziDTO = titAziConstrAcceptBusinessImpl.getExportTitAzi(data);
			
			 List<TitAziConstrAcceptListDTO> listTitAzi = titAziConstrAcceptBusinessImpl.listById(data.getTitAziConstrAcceptId());
			 exportTitAziDTO.setTitAziConstrAcceptList(listTitAzi);

			 for (int j = 0; j < exportTitAziDTO.getTitAziConstrAcceptList().size(); j++) {
					exportTitAziDTO.getTitAziConstrAcceptList().get(j).setStt(j+1);
				}
			 
			exportTitAziDTO.setConclusionExport(exportTitAziDTO.getConclusion()==1 ? "Chấp nhận nghiệm thu công trình xây dựng để đưa vào sử dụng":"Không chấp nhận nghiệm thu");
			
			System.out.println("listTitAzi:"+listTitAzi);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-23.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign11");
            metadata.addFieldAsImage("sign21");
            metadata.addFieldAsImage("sign31");
            metadata.addFieldAsImage("sign41");
            metadata.addFieldAsImage("sign12");
            metadata.addFieldAsImage("sign22");
            metadata.addFieldAsImage("sign32");
            metadata.addFieldAsImage("sign42");
			metadata.load("bc", TitAziConstrAcceptListDTO.class, true);
			
			IContext context = report.createContext();
			context.put("item", exportTitAziDTO);
			context.put("bc", exportTitAziDTO.getTitAziConstrAcceptList());
			
			IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider sign3;
            IImageProvider sign4;
         // sign1
            if(StringUtils.isNotEmpty(exportTitAziDTO.getAdirectorPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getAdirectorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign1 = new FileImageProvider(f);
                }else{
                     sign1 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            // sign2
            if(StringUtils.isNotEmpty(exportTitAziDTO.getAinchargemonitorPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getAinchargemonitorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign2 = new FileImageProvider(f);
                }else{
                     sign2 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            //sign3
            if(StringUtils.isNotEmpty(exportTitAziDTO.getBdirectorPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getBdirectorPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign3 = new FileImageProvider(f);
                }else{
                     sign3 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign3 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            //sign4
            if(StringUtils.isNotEmpty(exportTitAziDTO.getBinchargeConstructPath())){
            	File f = new File(folder2Upload + "/" + exportTitAziDTO.getBinchargeConstructPath());
            	if(f.exists() && !f.isDirectory()) { 
                    sign4 = new FileImageProvider(f);
                }else{
                     sign4 = new FileImageProvider(new File(filePath + "/none.png"));
                }
            } else{
            	sign4 = new FileImageProvider(new File(filePath + "/none.png"));
            }
           
           System.out.println("====================" + folder2Upload + "/" + exportTitAziDTO.getAdirectorPath());
          
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(exportTitAziDTO.getStatusCa() == 2){
            	context.put("sign11", sign1);
                context.put("sign21", sign2);
                context.put("sign31", sign3);
                context.put("sign41", sign4);
                context.put("sign12", sign1);
                context.put("sign22", sign2);
                context.put("sign32", sign3);
                context.put("sign42", sign4);
                exportTitAziDTO.setAdirectorSignName(exportTitAziDTO.getAdirectorName());
            	exportTitAziDTO.setAinchargemonitorSignName(exportTitAziDTO.getAinchargemonitorName());
            	exportTitAziDTO.setBdirectorSignName(exportTitAziDTO.getBdirectorName());
            	exportTitAziDTO.setBinchargeConstructSignName(exportTitAziDTO.getBinchargeConstructName());
            } else {
            	exportTitAziDTO.setAdirectorSignName("");
            	exportTitAziDTO.setAinchargemonitorSignName("");
            	exportTitAziDTO.setBdirectorSignName("");
            	exportTitAziDTO.setBinchargeConstructSignName("");
            	context.put("sign11", none);
                context.put("sign21", none);
                context.put("sign31", none);
                context.put("sign41", none);
                context.put("sign12", none);
                context.put("sign22", none);
                context.put("sign32", none);
                context.put("sign42", none);
            }
			File fout = new File(folder2Upload+"/" +"BM-TCNT-23-"+ exportTitAziDTO.getTitAziConstrAcceptId() + ".pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-23-"+ exportTitAziDTO.getTitAziConstrAcceptId() + ".pdf");
			return Response.ok(Collections.singletonMap("fileName",path))
	        		.build();

		}catch ( IOException e )
        {
			LOGGER.error(e.getMessage(), e);
        }
        catch ( XDocReportException e )
        {
        	LOGGER.error(e.getMessage(), e);
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response appro(approDTO obj) {
		Long result =titAziConstrAcceptBusinessImpl.appro(obj);
		if (result == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(result).build();
		}
	}
	
	@Override
	public Response downloadTempleKLXLHT(TitAziConstrAcceptDTO obj) throws Exception {
		String path  =   UEncrypt.encryptFileUploadPath("ImportKLXLHT_Temp.xlsx");
		return Response.ok(Collections.singletonMap("fileName", path)).build();
	}

	@Override
	public Response updateIsActive(Long titAziConstrAcceptId) {
		// TODO Auto-generated method stub
		titAziConstrAcceptBusinessImpl.updateIsActive(titAziConstrAcceptId);
		return null;
	}
}
