/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import com.espringtran.compressor4j.bean.CompressionLevel;
import com.espringtran.compressor4j.bean.CompressionType;
import com.espringtran.compressor4j.bean.ZipLevel;
import com.espringtran.compressor4j.compressor.FileCompressor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.CompletionDrawingBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.UtilAttachedDocumentsBusinessImpl;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.UtilAttachedDocumentsDTO;
import com.viettel.erp.utils.Folder;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class CompletionDrawingRsServiceImpl implements CompletionDrawingRsService {
	
    private static final CompressionType TYPE = CompressionType.ZIP;
    private static final CompressionLevel LEVEL = ZipLevel.NORMAL;

    static Logger LOGGER = LoggerFactory.getLogger(CompletionDrawingRsServiceImpl.class);
    @Autowired
    CompletionDrawingBusinessImpl completionDrawingBusinessImpl;
    
    @Autowired
    UtilAttachedDocumentsBusinessImpl utilAttachedDocumentsBusinessImpl;
    
    @Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
    
    @Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
    
    @Value("${completionDrawing.attachType}")
    private Long attachType;
    
    @Value("${completionDrawing.folder}")
	private String folder;
    
    @Value("${folder_upload}")
    private String folder2Upload;
    
    @Value("${folder_upload2}")
    private String folder2Upload1;
    static{
        PdfReader.unethicalreading = true;
    }
    
    @Override
    public Response getCompletionDrawing() throws Exception {
        List<CompletionDrawingDTO> ls = completionDrawingBusinessImpl.getAll();
        for(CompletionDrawingDTO dto : ls){
			dto.setDocumentPath(UEncrypt.encryptFileUploadPath(dto.getDocumentPath()));
		}
        if (ls.size() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
        	return Response.ok(ls).build();
        }
    }

    @Override
    public Response getCompletionDrawingById(Long id) {
        CompletionDrawingDTO obj = (CompletionDrawingDTO) completionDrawingBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateCompletionDrawing(CompletionDrawingDTO obj) throws Exception {
		obj.setCreatedDate(obj.getCreatedDate());
		//String documentPath = Folder.getFolderSubfix(folder);
		for (UtilAttachedDocumentsDTO chilDTO : obj.getUtilAttachedDocuments()) {
			chilDTO.setAttachId(obj.getAttachId());
			chilDTO.setCreatedDate(new Date());
			chilDTO.setType(attachType);
			if(chilDTO.getDocumentName() != null){
			chilDTO.setDocumentPath(UEncrypt.decryptFileUploadPath(obj.getDocumentPath()));
			chilDTO.setDocumentName(chilDTO.getDocumentName());
			}else{
				chilDTO.setDocumentPath(UEncrypt.decryptFileUploadPath(obj.getDocumentPath()));
				chilDTO.setDocumentName(obj.getDocumentName());
			}
		}
        Long id = completionDrawingBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addCompletionDrawing(CompletionDrawingDTO obj) throws Exception {
		obj.setCreatedDate(new Date());
		//String documentPath = Folder.getFolderSubfix(folder);
		obj.setStatusCa(0L);
		obj.setIsActive(1l);
		//Long constructionId = obj.getConstructId();
		
		for (UtilAttachedDocumentsDTO chilDTO : obj.getUtilAttachedDocuments()) {
			chilDTO.setCreatedDate(new Date());
			chilDTO.setType(attachType);
			//get name of the file
//        	String name = FilenameUtils.getName(chilDTO.getDocumentName());      	
        	//append hh:mm:ss to file name
//        	String baseName = FilenameUtils.getBaseName(name);
//        	baseName += "_" + DateTimeFormat.forPattern("hhmmss").print(DateTime.now());
//        	name = baseName + "." + FilenameUtils.getExtension(name);
			chilDTO.setDocumentPath(obj.getDocumentPath());
		}
		
		CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("COMPLETION_DRAWING");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		
		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
    	constrCompleteRecordMap.setDataTableName("COMPLETION_DRAWING");
    	constrCompleteRecordMap.setDataTableId("COMPLETION_DRAWING_ID");
    	constrCompleteRecordMap.setCode(obj.getDrawCode());
    	constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
    	constrCompleteRecordMap.setCreatedDate(new Date());
    	constrCompleteRecordMap.setStatus(0L);
    	constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
    	constrCompleteRecordMap.setConstructionId(obj.getConstructId());
    	obj.setConstrCompleteRecordMap(constrCompleteRecordMap);
		
        Long id = completionDrawingBusinessImpl.saveTable(obj);
        
//        try {
//        	Long idcom = constrCompleteRecordsMapBusinessImpl.insert(constructionId, "COMPLETION_DRAWING", "COMPLETION_DRAWING_ID", id,3444l);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
    }

    @Override
    public Response deleteCompletionDrawing(Long id) {
        CompletionDrawingDTO obj = (CompletionDrawingDTO) completionDrawingBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            completionDrawingBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }
    @Override
	public Response getCompletionDrawingSearch(CompletionDrawingDTO obj) throws Exception {
		List<CompletionDrawingDTO> ls = completionDrawingBusinessImpl.getCompletionDrawingSearch(obj);
		for(CompletionDrawingDTO dto : ls){
			dto.setDocumentPath(UEncrypt.encryptFileUploadPath(dto.getDocumentPath()));
		}
        if (ls.size()==0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(ls).build();
        }
	}
	
	@Override
	public Response getCompletionDrawing(Long constructId) {
		 List<CompletionDrawingDTO> ls = completionDrawingBusinessImpl.getCompletionDrawing(constructId);
		 
	        if (ls == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	            return Response.ok(ls).build();
	        }
	}

	@Override
	public Response getFolder() {
		return Response.ok().entity(java.util.Collections.singletonMap("folder", folder)).build();
	}

	@Override
	public Response downloadFileZip(List<String> completionDrawingId, HttpServletRequest request) {
		List<CompletionDrawingDTO> ls = completionDrawingBusinessImpl.getDrawById(completionDrawingId);
		
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getStatusCa() == 2) {
				try {
					CompletionDrawingDTO childls = completionDrawingBusinessImpl.getPathById(ls.get(i).getCompletionDrawingId());
					String[] pathImg = {childls.getAmonitorPath(),childls.getBdirectorPath(),childls.getCreatorPath()};
					String path = ganChanky(ls.get(i).getDocumentPath(), ls.get(i).getCompletionDrawingId(), ls.get(i).getDocumentName(), pathImg);
					if(path != null) {
						ls.get(i).setDocumentPath(path);
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					LOGGER.error(ex.getMessage(), ex);
				}/* catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		}
		try {
			if (CollectionUtils.isEmpty(ls)) {
            	LOGGER.warn("No files added!!");
            	return Response.status(Response.Status.BAD_REQUEST).build();
            }
			
            FileCompressor fileCompressor = new FileCompressor();
            File f = new File(folder2Upload1);
            if (!f.exists()) {
            	LOGGER.warn("{} folder is not exist!!", folder2Upload1);
            	return Response.status(Response.Status.BAD_REQUEST).build();
            }
            
        	String filePath = f.getCanonicalPath();
        	LOGGER.info("adding {} files", ls.size());
        	
            for (CompletionDrawingDTO file : ls) {
            	
            	String srcPath = filePath + file.getDocumentPath();
            	String desPath = file.getDocumentName();
            	LOGGER.info("{} added", srcPath);
            	fileCompressor.add(srcPath, desPath);	
			}
            fileCompressor.setType(TYPE);
            fileCompressor.setLevel(LEVEL);
            fileCompressor.setCompressedPath(folder2Upload + File.separatorChar  +"BanVeHoanCong." + TYPE.getExtension());
            fileCompressor.compress();
            String path  =   UEncrypt.encryptFileUploadPath("BanVeHoanCong." + TYPE.getExtension());
            
            return Response.ok(java.util.Collections.singletonMap("fileName",path))
	        		.build();
        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}

	@Override
	public Response updateIsActive(List<Long> completionDrawingId) {
		boolean ls = completionDrawingBusinessImpl.updateIsActive(completionDrawingId);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response getDrawById(List<String> completionDrawingId) {
		List<CompletionDrawingDTO> ls = completionDrawingBusinessImpl.getDrawById(completionDrawingId);
		try {
			if (CollectionUtils.isEmpty(ls)) {
            	LOGGER.warn("No files added!!");
            	return Response.status(Response.Status.BAD_REQUEST).build();
            }
			
            FileCompressor fileCompressor = new FileCompressor();
            File f = new File(folder2Upload1);
            if (!f.exists()) {
            	LOGGER.warn("{} folder is not exist!!", folder2Upload1);
            	return Response.status(Response.Status.BAD_REQUEST).build();
            }
            
        	String filePath = f.getCanonicalPath();
        	LOGGER.info("adding {} files", ls.size());
        	
            for (CompletionDrawingDTO childls : ls) {
            	String srcPath = filePath + childls.getDocumentPath();
            	String desPath = childls.getDocumentName();
            	LOGGER.info("{} added", srcPath);
            	fileCompressor.add(srcPath, desPath);	
			}
            fileCompressor.setType(TYPE);
            fileCompressor.setLevel(LEVEL);
            fileCompressor.setCompressedPath(folder2Upload + File.separatorChar  +"BanVeHoanCong." + TYPE.getExtension());
            fileCompressor.compress();
            String path  =   UEncrypt.encryptFileUploadPath("BanVeHoanCong." + TYPE.getExtension());
            
            return Response.ok(java.util.Collections.singletonMap("fileName",path))
	        		.build();
        } catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}
	
	private static void insertImage(String pathImage, PdfArray rectArr, PdfContentByte pdfPage)
			throws BadElementException, MalformedURLException, IOException, DocumentException {
		int size = rectArr.size();
		float left = 0f;
		float top = 0f;
		// Xac dinh toa do cua comment
		if (size > 2) {
			// left corner
			PdfNumber obj = (PdfNumber) rectArr.getPdfObject(0);
			if (obj != null) {
				left = obj.floatValue();
			}
			// top corner
			PdfNumber obj1 = (PdfNumber) rectArr.getPdfObject(1);
			if (obj1 != null) {
				top = obj1.floatValue();
			}
		}
		// Add image
		if (left > 0 && top > 0) {
			Image image1 = Image.getInstance(pathImage);
			image1.setAbsolutePosition(left - 60, top - 70);
			image1.scaleToFit(135, 135);
			if (pdfPage != null) {
				pdfPage.addImage(image1);
			}
		}
	}
	
	public String ganChanky(String path, Long id, String name, String[] pathImage) throws DocumentException, IOException{
		// bo qua check password
		

		byte[] bytes = FileUtils.readFileToByteArray(new File(folder2Upload+ File.separatorChar + path));

		PdfReader pdfReader = new PdfReader(bytes);
		// Hiendv bo sung dieu kien check null
		int number_of_pages = pdfReader.getNumberOfPages();
		
		PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(new File(folder2Upload + "/" + name)));
		// Get the PdfContentByte type by pdfStamper.

		int i = 0;
		//String[] pathImage = {"http://www.pioneertradingcompany.co.uk/wp-content/uploads/2015/11/Signature-logo.png", "http://www.pioneertradingcompany.co.uk/wp-content/uploads/2015/11/Signature-logo.png"};
		while (i < number_of_pages) {
			i++;
			// 13-04-2014 tuantm18 begin Chen anh chu ky vao van ban
			PdfDictionary page = pdfReader.getPageN(i);
			PdfArray annotsArray;
			// Kiem tra tung trang mot xem co comment
			if (page.getAsArray(PdfName.ANNOTS) != null) {
				annotsArray = page.getAsArray(PdfName.ANNOTS);
				for (ListIterator iter = annotsArray.listIterator(); iter.hasNext();) {
					PdfDictionary annot = (PdfDictionary) PdfReader.getPdfObject((PdfObject) iter.next());
					
					PdfString content = (PdfString) PdfReader.getPdfObject(annot.get(PdfName.CONTENTS));
					PdfArray rectArr = (PdfArray) annot.get(PdfName.RECT);
					PdfContentByte pdfPage = null;
					if (pdfStamper != null && pdfStamper.getOverContent(i) != null) {
						pdfPage = pdfStamper.getOverContent(i);
					}
					if (content != null) {
						String strContent = content.toUnicodeString().replaceAll(" ", "").replaceAll("\r\n", "").trim();
						System.out.println("strContent = " + strContent);
						
						int j = NumberUtils.toInt(strContent);
						if(j > 0) {
							insertImage(pathImage[j - 1], rectArr, pdfPage);
						}
						annot.clear();
					}

				}
			}
			// 13-04-2014 tuantm18 end Chen anh chu ky vao van ban
		}
		pdfStamper.close();
		pdfReader.close();
		return name;
	}

	@Override
	public Response addListCompletionDrawing(List<CompletionDrawingDTO> obj) throws Exception {
		// TODO Auto-generated method stub
		
		for(CompletionDrawingDTO childobj : obj){
			childobj.setCreatedDate(new Date());
			childobj.setStatusCa(0l);
			childobj.setIsActive(1l);
			for (UtilAttachedDocumentsDTO chilDTO : childobj.getUtilAttachedDocuments()) {
				chilDTO.setCreatedDate(new Date());
				chilDTO.setType(attachType);
				//get name of the file
//	        	String name = FilenameUtils.getName(chilDTO.getDocumentName());      	
	        	//append hh:mm:ss to file name
//	        	String baseName = FilenameUtils.getBaseName(name);
//	        	baseName += "_" + DateTimeFormat.forPattern("hhmmss").print(DateTime.now());
//	        	name = baseName + "." + FilenameUtils.getExtension(name);
				chilDTO.setDocumentPath(UEncrypt.decryptFileUploadPath(childobj.getDocumentPath()));
				}
				
				CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("COMPLETION_DRAWING");
				Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
				
				ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
		    	constrCompleteRecordMap.setDataTableName("COMPLETION_DRAWING");
		    	constrCompleteRecordMap.setDataTableId("COMPLETION_DRAWING_ID");
		    	constrCompleteRecordMap.setLevelOrder(1l);
		    	constrCompleteRecordMap.setCode(childobj.getDrawCode());
		    	constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
		    	constrCompleteRecordMap.setCreatedDate(new Date());
		    	constrCompleteRecordMap.setStatus(0L);
		    	constrCompleteRecordMap.setCreatedUserId(childobj.getCreatedUserId());
		    	constrCompleteRecordMap.setConstructionId(childobj.getConstructId());
		    	childobj.setConstrCompleteRecordMap(constrCompleteRecordMap);
			//return completionDrawingBusinessImpl.addListCompletionDrawing(obj);
		    	completionDrawingBusinessImpl.saveTable(childobj);
		}
		return null;
	}

	@Override
	public Response deleteDraw(List<CompletionDrawingDTO> listdto) {
		// TODO Auto-generated method stub
		for(CompletionDrawingDTO dto:listdto){
		try{
			
    		File file = new File(folder2Upload + File.separatorChar +  dto.getDocumentPath());
    		//file.delete();
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println(dto.getDocumentPath() + "Delete operation is failed.");
    		}
			
    	}catch(Exception ex){

    		LOGGER.error(ex.getMessage(), ex);

    	}
		}
		return null;
	}
}
