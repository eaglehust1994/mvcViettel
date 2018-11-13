/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

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
import com.viettel.erp.business.AMaterialRecoveryMinutesBusinessImpl;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.dto.AMaterialRecoveryListDTO;
import com.viettel.erp.dto.AMaterialRecoveryListModelDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesDTO;
import com.viettel.erp.dto.AMaterialRecoveryMinutesModelDTO;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author HungLQ9
 */
public class AMaterialRecoveryMinutesRsServiceImpl implements AMaterialRecoveryMinutesRsService {
	
	private static final CompressionType TYPE = CompressionType.ZIP;
    private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
	
	@Value("${folder_upload}")
	private String folder2Upload;

	static Logger LOGGER = LoggerFactory.getLogger(AMaterialRecoveryMinutesRsServiceImpl.class);

	@Value("${completionDrawing.attachType}")
	private Long attachType;

	@Value("${completionDrawing.folder}")
	private String folder;

	@Autowired
	AMaterialRecoveryMinutesBusinessImpl aMaterialRecoveryMinutesBusinessImpl;
	
	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
	
	@Autowired
    CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
	 
//	@Autowired
//	CatFileInvoiceDAO catFileInvoiceDAO;

	@Override
	public Response getAMaterialRecoveryMinutes() {
		List<AMaterialRecoveryMinutesDTO> ls = aMaterialRecoveryMinutesBusinessImpl.getAll();
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
	public Response exportList(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			AMaterialRecoveryMinutesModelDTO dto = new AMaterialRecoveryMinutesModelDTO();
			dto.setAmaterialRecoveryMinutesId(l);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "thuhoiacapthua." + TYPE.getExtension());
			
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("thuhoiacapthua." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		}catch (Exception ex) {
        	LOGGER.error(ex.getMessage(), ex);
        }
		return null;
	}

	@Override
	public Response exportFileM(AMaterialRecoveryMinutesModelDTO obj) {

		try {
			AMaterialRecoveryMinutesModelDTO data = aMaterialRecoveryMinutesBusinessImpl.recoveryMinutesModelDTO(obj);

			List<AMaterialRecoveryListDTO> recoveryListDTO = aMaterialRecoveryMinutesBusinessImpl
					.updateRecoveryList(obj.getAmaterialRecoveryMinutesId());
			data.setRecoveryListDTO(recoveryListDTO);
			
			for(AMaterialRecoveryListDTO objectDTO : recoveryListDTO){
				objectDTO.setQualityStatusText(objectDTO.getQualityStatus()==1?"Hỏng":"Tốt");
			}

			for (int j = 0; j < data.getRecoveryListDTO().size(); j++) {
				data.getRecoveryListDTO().get(j).setStt(j + 1);
			}
			
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-13.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getbReceivePersonPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getbReceivePersonPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaHandoverPersonPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaHandoverPersonPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaDirectorPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			metadata.load("bc", AMaterialRecoveryListDTO.class, true);
			IContext context = report.createContext();

			context.put("item", data);
			context.put("bc", data.getRecoveryListDTO());
			if (data.getStatusCa() == 2) {
				data.setAhandoverPersonNameSign(data.getAhandoverPersonName());
				data.setBreceivePersonNameSign(data.getBreceivePersonName());
				data.setAdirectorNameSign(data.getAdirectorName());
				data.setBdirectorNameSign(data.getBdirectorName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setAhandoverPersonNameSign("");
				data.setBreceivePersonNameSign("");
				data.setAdirectorNameSign("");
				data.setBdirectorNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}

			File fout = new File(folder2Upload + "/BM-TCNT-13.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-13.pdf");
			return Response.ok(Collections.singletonMap("fileName", path)).build();

		}catch(IOException ioException){
			LOGGER.error(ioException.getMessage(), ioException);
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	private String exportOne(AMaterialRecoveryMinutesModelDTO obj) {
		try {
			AMaterialRecoveryMinutesModelDTO data = aMaterialRecoveryMinutesBusinessImpl.recoveryMinutesModelDTO(obj);

			List<AMaterialRecoveryListDTO> recoveryListDTO = aMaterialRecoveryMinutesBusinessImpl
					.updateRecoveryList(obj.getAmaterialRecoveryMinutesId());
			data.setRecoveryListDTO(recoveryListDTO);
			for(AMaterialRecoveryListDTO objectDTO : recoveryListDTO){
				objectDTO.setQualityStatusText(objectDTO.getQualityStatus()==1?"Hỏng":"Tốt");
			}

			for (int j = 0; j < data.getRecoveryListDTO().size(); j++) {
				data.getRecoveryListDTO().get(j).setStt(j + 1);
			}
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-13.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getbReceivePersonPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getbReceivePersonPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaHandoverPersonPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaHandoverPersonPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaDirectorPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			metadata.load("bc", AMaterialRecoveryListDTO.class, true);
			IContext context = report.createContext();

			context.put("item", data);
			context.put("bc", data.getRecoveryListDTO());
			if (data.getStatusCa() == 2) {
				data.setAhandoverPersonNameSign(data.getAhandoverPersonName());
				data.setBreceivePersonNameSign(data.getBreceivePersonName());
				data.setAdirectorNameSign(data.getAdirectorName());
				data.setBdirectorNameSign(data.getBdirectorName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setAhandoverPersonNameSign("");
				data.setBreceivePersonNameSign("");
				data.setAdirectorNameSign("");
				data.setBdirectorNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}

			File fout = new File(folder2Upload+"/" + obj.getAmaterialRecoveryMinutesId() +"-BM-TCNT-13.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return obj.getAmaterialRecoveryMinutesId() + "-BM-TCNT-13.pdf";

		}catch(IOException ioException){
			LOGGER.error(ioException.getMessage(), ioException);
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	@Override
	public Response getAMaterialRecoveryMinutesById(Long id) {
		AMaterialRecoveryMinutesDTO obj = (AMaterialRecoveryMinutesDTO) aMaterialRecoveryMinutesBusinessImpl
				.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateAMaterialRecoveryMinutes(AMaterialRecoveryMinutesDTO obj) {
		Long id = aMaterialRecoveryMinutesBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	public List<AMaterialRecoveryListModelDTO> getTwoListUpdate(Long constructId) {
		List<AMaterialRecoveryListModelDTO> devices = (List<AMaterialRecoveryListModelDTO>) aMaterialRecoveryMinutesBusinessImpl
				.device(constructId);
		List<AMaterialRecoveryListModelDTO> materials = (List<AMaterialRecoveryListModelDTO>) aMaterialRecoveryMinutesBusinessImpl
				.materials(constructId);
		
		List<AMaterialRecoveryListDTO> sum = aMaterialRecoveryMinutesBusinessImpl.checkSum(constructId);
		if(sum.size() > 0){
			for (int i = 0; i < materials.size(); i++) {
				for (int j = 0; j < sum.size(); j++) {
					if(materials.get(i).getMerID().longValue() == sum.get(j).getMerEntityId().longValue()){
						materials.get(i).setRecoveryQuantity((double) materials.get(i).getHandoverQuantity() - (double) materials.get(i).getAcceptQuantity() - sum.get(j).getSumRecoveryQuantity());
						break;
					}
				}
			}
		} else {
			for(int i = 0 ;i < materials.size() ; i++){
				materials.get(i).setRecoveryQuantity((double) materials.get(i).getHandoverQuantity() - (double)materials.get(i).getAcceptQuantity());
			}
		}
		for(int i = 0 ;i < devices.size() ; i++){
			devices.get(i).setRecoveryQuantity((double) devices.get(i).getHandoverQuantity() - (double) devices.get(i).getAcceptQuantity());
		}
		for (Iterator i = materials.iterator(); i.hasNext();) {
			AMaterialRecoveryListModelDTO dto = (AMaterialRecoveryListModelDTO) i.next();
			if(dto.getRecoveryQuantity() == 0){
				i.remove();
			}
		}
		for (Iterator i = devices.iterator(); i.hasNext();) {
			AMaterialRecoveryListModelDTO dto = (AMaterialRecoveryListModelDTO) i.next();
			for (Iterator j = sum.iterator(); j.hasNext();) {
				AMaterialRecoveryListDTO dto1 = (AMaterialRecoveryListDTO) j.next();
				if (dto.getMerEntityId().longValue() == dto1.getMerEntityId().longValue()) {
					i.remove();
				}
			}
		}
		List<AMaterialRecoveryListModelDTO> list = aMaterialRecoveryMinutesBusinessImpl.getTwoList(devices, materials);
		if (list == null) {
			return null;
		} else {
			return list;
		}
	}
	
	@Override
	public Response addAMaterialRecoveryMinutes(AMaterialRecoveryMinutesDTO obj){	
		Date date = new Date();
		CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("A_MATERIAL_RECOVERY_MINUTES");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		String code = aMaterialRecoveryMinutesBusinessImpl.autoGenCode();
		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
    	constrCompleteRecordMap.setDataTableName("A_MATERIAL_RECOVERY_MINUTES");
    	constrCompleteRecordMap.setDataTableId("A_MATERIAL_RECOVERY_MINUTES_ID");
    	constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
    	constrCompleteRecordMap.setCreatedDate(date);
    	constrCompleteRecordMap.setStatus(0L);
    	constrCompleteRecordMap.setLevelOrder(1L);
    	constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
    	constrCompleteRecordMap.setConstructionId(obj.getConstructId());
    	constrCompleteRecordMap.setCode(code);
    	obj.setConstrCompleteRecordMap(constrCompleteRecordMap);
		obj.setStatusCa(0L);
		obj.setIsActive(1L);
		obj.setCode(code);
		obj.setCreatedDate(date);
		
		Long id = aMaterialRecoveryMinutesBusinessImpl.saveTable(obj);
			if (id == 0l) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} else {
				return Response.ok(Response.Status.CREATED).build();
			}
	}

	@Override
	public Response deleteAMaterialRecoveryMinutes(Long id) {
		AMaterialRecoveryMinutesDTO obj = (AMaterialRecoveryMinutesDTO) aMaterialRecoveryMinutesBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			aMaterialRecoveryMinutesBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response findByConstructId(AMaterialRecoveryMinutesModelDTO recoveryMinutesModelDTO) {
		List<AMaterialRecoveryMinutesModelDTO> ls = aMaterialRecoveryMinutesBusinessImpl.findByConstructId(recoveryMinutesModelDTO);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response deleteAMaterialMinutes(List<String> listAMaterialRecoveryMinutesId) {
		boolean ls = aMaterialRecoveryMinutesBusinessImpl.deleteAMaterialMinutes(listAMaterialRecoveryMinutesId);
		if (ls) {
			return Response.ok(ls).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response getTwoList(Long constructId) {
		List<AMaterialRecoveryListModelDTO> devices = (List<AMaterialRecoveryListModelDTO>) aMaterialRecoveryMinutesBusinessImpl
				.device(constructId);
		List<AMaterialRecoveryListModelDTO> materials = (List<AMaterialRecoveryListModelDTO>) aMaterialRecoveryMinutesBusinessImpl
				.materials(constructId);
		
		List<AMaterialRecoveryListDTO> sum = aMaterialRecoveryMinutesBusinessImpl.checkSum(constructId);
		if(sum.size() > 0){
			for (int i = 0; i < materials.size(); i++) {
				for (int j = 0; j < sum.size(); j++) {
					if(materials.get(i).getMerID().longValue() == sum.get(j).getMerEntityId().longValue()){
						materials.get(i).setRecoveryQuantity((double) materials.get(i).getHandoverQuantity() - (double) materials.get(i).getAcceptQuantity() - (double)sum.get(j).getSumRecoveryQuantity());
						break;
					}
				}
			}
		} else {
			
			
			for(int i = 0 ;i < materials.size() ; i++){

				
				materials.get(i).setRecoveryQuantity((double) materials.get(i).getHandoverQuantity() - (double) materials.get(i).getAcceptQuantity());
			}
		}
		for(int i = 0 ;i < devices.size() ; i++){
			devices.get(i).setRecoveryQuantity((double) devices.get(i).getHandoverQuantity() - (double) devices.get(i).getAcceptQuantity());
		}
		for (Iterator i = materials.iterator(); i.hasNext();) {
			AMaterialRecoveryListModelDTO dto = (AMaterialRecoveryListModelDTO) i.next();
			if(dto.getRecoveryQuantity() == 0){
				i.remove();
			}
		}
		for (Iterator i = devices.iterator(); i.hasNext();) {
			AMaterialRecoveryListModelDTO dto = (AMaterialRecoveryListModelDTO) i.next();
			for (Iterator j = sum.iterator(); j.hasNext();) {
				AMaterialRecoveryListDTO dto1 = (AMaterialRecoveryListDTO) j.next();
				if (dto.getMerEntityId().longValue() == dto1.getMerEntityId().longValue()) {
					i.remove();
				}
			}
		}
		List<AMaterialRecoveryListModelDTO> list = aMaterialRecoveryMinutesBusinessImpl.getTwoList(devices, materials);
		if (list == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(list).build();
		}
	}
	
	@Override
	public Response getCheckTwoList(Long constructId) {
		List<AMaterialRecoveryListDTO> devices = (List<AMaterialRecoveryListDTO>) aMaterialRecoveryMinutesBusinessImpl
				.checkDevice(constructId);
		List<AMaterialRecoveryListDTO> materials = (List<AMaterialRecoveryListDTO>) aMaterialRecoveryMinutesBusinessImpl
				.checkMaterials(constructId);
		List<AMaterialRecoveryListDTO> list = aMaterialRecoveryMinutesBusinessImpl.getCheckTwoList(devices, materials);

		if (list == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(list).build();
		}
	}

	@Override
	public Response device(Long constructId) {
		List<AMaterialRecoveryListModelDTO> ls = aMaterialRecoveryMinutesBusinessImpl.device(constructId);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response materials(Long constructId) {
		List<AMaterialRecoveryListModelDTO> ls = aMaterialRecoveryMinutesBusinessImpl.materials(constructId);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response updateRecoveryList(Long amaterialRecoveryMinutesId) {
		List<AMaterialRecoveryListDTO> ls = aMaterialRecoveryMinutesBusinessImpl
				.updateRecoveryList(amaterialRecoveryMinutesId);
		List<AMaterialRecoveryListDTO> listSum = new ArrayList<>();
		if(ls.size() >0){
			listSum = aMaterialRecoveryMinutesBusinessImpl.checkSum(ls.get(0).getConstructId());
		}
		for (int i = 0; i < ls.size(); i++) {
			for (int j = 0; j < listSum.size(); j++) {
				if(ls.get(i).getMerEntityId().longValue() == listSum.get(j).getMerEntityId().longValue()){
					ls.get(i).setCheckQuantity(ls.get(i).getHandoverQuantity() - listSum.get(j).getSumRecoveryQuantity() 
							+ ls.get(i).getRecoveryQuantity());
				}
			}
		}
		if (ls.size() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response exportFile(AMaterialRecoveryMinutesModelDTO data) {

		for (int j = 0; j < data.getRecoveryListDTO().size(); j++) {
			AMaterialRecoveryListDTO dto = data.getRecoveryListDTO().get(j);
			dto.setStt(new Integer(j + 1));
		}

		try {
			// for (QualityCableMeaResultDTO e : data.getListResultDTO()) {
			// System.out.println(e);
			// }

			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-13.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			// NEW API
			metadata.load("bc", AMaterialRecoveryListDTO.class, true);

			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("bc", data.getRecoveryListDTO());

			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-10.docx" ) ); report.process( context,
			 * out );
			 */

			// pdf
			File fout = new File(folder2Upload + "/BM-TCNT-13.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			System.out.println(fout.getCanonicalPath());
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-13.pdf");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
		}catch(IOException ioException){
			LOGGER.error(ioException.getMessage(), ioException);
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}/* catch (XDocReportException e) {
			e.printStackTrace();
		}*/

		return null;
	}

	@Override
	public Response getExport(Long amaterialRecoveryMinutesId) {
		AMaterialRecoveryListModelDTO modelDTO = aMaterialRecoveryMinutesBusinessImpl
				.getExport(amaterialRecoveryMinutesId);
		if (modelDTO == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(modelDTO).build();
		}
	}
	
	/////export doc
	@Override
	public Response exportListDoc(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			AMaterialRecoveryMinutesModelDTO dto = new AMaterialRecoveryMinutesModelDTO();
			dto.setAmaterialRecoveryMinutesId(l);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "thuhoiacapthua." + TYPE.getExtension());
			
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("thuhoiacapthua." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		}catch(IOException ioException){
			LOGGER.error(ioException.getMessage(), ioException);
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	@Override
	public Response exportFileMDoc(AMaterialRecoveryMinutesModelDTO obj) {

		try {
			AMaterialRecoveryMinutesModelDTO data = aMaterialRecoveryMinutesBusinessImpl.recoveryMinutesModelDTO(obj);

			List<AMaterialRecoveryListDTO> recoveryListDTO = aMaterialRecoveryMinutesBusinessImpl
					.updateRecoveryList(obj.getAmaterialRecoveryMinutesId());
			data.setRecoveryListDTO(recoveryListDTO);
			
			for(AMaterialRecoveryListDTO objectDTO : recoveryListDTO){
				objectDTO.setQualityStatusText(objectDTO.getQualityStatus()==1?"Hỏng":"Tốt");
			}

			for (int j = 0; j < data.getRecoveryListDTO().size(); j++) {
				data.getRecoveryListDTO().get(j).setStt(j + 1);
			}
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-13.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getbReceivePersonPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getbReceivePersonPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaHandoverPersonPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaHandoverPersonPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaDirectorPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			metadata.load("bc", AMaterialRecoveryListDTO.class, true);
			IContext context = report.createContext();

			context.put("item", data);
			context.put("bc", data.getRecoveryListDTO());
			if (data.getStatusCa() == 2) {
				data.setAhandoverPersonNameSign(data.getAhandoverPersonName());
				data.setBreceivePersonNameSign(data.getBreceivePersonName());
				data.setAdirectorNameSign(data.getAdirectorName());
				data.setBdirectorNameSign(data.getBdirectorName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setAhandoverPersonNameSign("");
				data.setBreceivePersonNameSign("");
				data.setAdirectorNameSign("");
				data.setBdirectorNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}
			File fout = new File(folder2Upload +"/BM-TCNT-13.docx");
	        OutputStream out = new FileOutputStream(fout);
	        report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-13.docx");
			return Response.ok(Collections.singletonMap("fileName", path)).build();

		}catch(IOException ioException){
			LOGGER.error(ioException.getMessage(), ioException);
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

	private String exportOneDoc(AMaterialRecoveryMinutesModelDTO obj) {
		try {
			AMaterialRecoveryMinutesModelDTO data = aMaterialRecoveryMinutesBusinessImpl.recoveryMinutesModelDTO(obj);

			List<AMaterialRecoveryListDTO> recoveryListDTO = aMaterialRecoveryMinutesBusinessImpl
					.updateRecoveryList(obj.getAmaterialRecoveryMinutesId());
			data.setRecoveryListDTO(recoveryListDTO);
			for(AMaterialRecoveryListDTO objectDTO : recoveryListDTO){
				objectDTO.setQualityStatusText(objectDTO.getQualityStatus()==1?"Hỏng":"Tốt");
			}

			for (int j = 0; j < data.getRecoveryListDTO().size(); j++) {
				data.getRecoveryListDTO().get(j).setStt(j + 1);
				
				
			}
			
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-13.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getbReceivePersonPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getbReceivePersonPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaHandoverPersonPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaHandoverPersonPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaDirectorPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			metadata.load("bc", AMaterialRecoveryListDTO.class, true);
			IContext context = report.createContext();

			context.put("item", data);
			context.put("bc", data.getRecoveryListDTO());
			if (data.getStatusCa() == 2) {
				data.setAhandoverPersonNameSign(data.getAhandoverPersonName());
				data.setBreceivePersonNameSign(data.getBreceivePersonName());
				data.setAdirectorNameSign(data.getAdirectorName());
				data.setBdirectorNameSign(data.getBdirectorName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setAhandoverPersonNameSign("");
				data.setBreceivePersonNameSign("");
				data.setAdirectorNameSign("");
				data.setBdirectorNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}
			File fout = new File(folder2Upload +"/"+ data.getAmaterialRecoveryMinutesId()+ "-BM-TCNT-13.docx");
	         OutputStream out = new FileOutputStream(fout);
	         report.process(context, out);
			out.flush();
			out.close();
			return obj.getAmaterialRecoveryMinutesId() + "-BM-TCNT-13.docx";

		}catch(IOException ioException){
			LOGGER.error(ioException.getMessage(), ioException);
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return null;
	}

}
