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
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.DistanceUnloadConstrMinutesBusinessImpl;
import com.viettel.erp.business.DistanceUnloadListBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.business.SceneGenerateWorkListBusinessImpl;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstructionAcceptanceDTO;
import com.viettel.erp.dto.DistanceUnloadConstrMinutesDTO;
import com.viettel.erp.dto.DistanceUnloadListDTO;
import com.viettel.erp.dto.SceneGenerateWorkListDTO;
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
@SuppressWarnings("unused")
public class DistanceUnloadConstrMinutesRsServiceImpl implements DistanceUnloadConstrMinutesRsService {

	@Value("${folder_upload}")
	private String folder2Upload;
	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
	static Logger LOGGER = LoggerFactory.getLogger(DistanceUnloadConstrMinutesBusinessImpl.class);
	@Autowired
	DistanceUnloadConstrMinutesBusinessImpl distanceUnloadConstrMinutesBusinessImpl;
	@Autowired
	DistanceUnloadListBusinessImpl distanceUnloadListBusinessImpl;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	@Autowired
	CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;

	@Autowired
	SceneGenerateWorkListBusinessImpl sceneGenerateWorkListBusinessImpl;

	@Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;

	@Override
	public Response getDistanceUnloadConstrMinutes() {
		List<DistanceUnloadConstrMinutesDTO> ls = distanceUnloadConstrMinutesBusinessImpl.getAll();
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
	public Response getDistanceUnloadConstrMinutesById(Long id) {
		DistanceUnloadConstrMinutesDTO obj = (DistanceUnloadConstrMinutesDTO) distanceUnloadConstrMinutesBusinessImpl
				.getListCR(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj) {
		Long id = distanceUnloadConstrMinutesBusinessImpl.update(obj);
		if (obj.getStatusCa() == 0L) {
			String nameTable = "DISTANCE_UNLOAD_CONSTR_MINUTES";
			qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getDisUnloadConsMinId(), nameTable);
		}
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj) {
		CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.findByTableName("DISTANCE_UNLOAD_CONSTR_MINUTES");
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		obj.setCode(distanceUnloadConstrMinutesBusinessImpl.autoGenCodeConstrOrganizationPlan());
		ConstrCompleteRecordsMapDTO constrCompleteRecordsMapDTO = new ConstrCompleteRecordsMapDTO();
		constrCompleteRecordsMapDTO.setConstructionId(obj.getConstructId());
		constrCompleteRecordsMapDTO.setDataTableName("DISTANCE_UNLOAD_CONSTR_MINUTES");
		constrCompleteRecordsMapDTO.setDataTableId("DIS_UNLOAD_CONS_MIN_ID");
		constrCompleteRecordsMapDTO.setCatFileInvoiceId(catFileInvoiceId);
		constrCompleteRecordsMapDTO.setCreatedUserId(obj.getCreatedUserId());
		constrCompleteRecordsMapDTO.setLevelOrder(1L);
		constrCompleteRecordsMapDTO.setStatus(0L);
		constrCompleteRecordsMapDTO.setCreatedDate(new Date());
		constrCompleteRecordsMapDTO
				.setCode(distanceUnloadConstrMinutesBusinessImpl.autoGenCodeConstrOrganizationPlan());
		obj.setConstrcompleterecordsmap(constrCompleteRecordsMapDTO);
		obj.setCreatedDate(new Date());
		try {
			distanceUnloadConstrMinutesBusinessImpl.saveTable(obj);
			return Response.ok(Response.Status.CREATED).build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response getListCR(Long constructId) {
		List<DistanceUnloadConstrMinutesDTO> ls = distanceUnloadConstrMinutesBusinessImpl.getListCR(constructId);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response getDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj) {
		List<DistanceUnloadConstrMinutesDTO> ls = distanceUnloadConstrMinutesBusinessImpl.getAllC(obj);
		if (ls == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(ls).build();
		}
	}

	@Override
	public Response exportDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj) {
		DistanceUnloadConstrMinutesDTO data = distanceUnloadConstrMinutesBusinessImpl.getDataExport(obj);
		data.setDistanceUnloadList(
				distanceUnloadListBusinessImpl.doSearchByDisUnloadConsMinId(obj.getDisUnloadConsMinId()));
		List<DistanceUnloadListDTO> listOto = new ArrayList<>();
		List<DistanceUnloadListDTO> listCKit = new ArrayList<>();
		List<DistanceUnloadListDTO> listBVac = new ArrayList<>();
		int i = 1;
		int k = 1;
		int m = 1;
		for (int j = 0; j < data.getDistanceUnloadList().size(); j++) {
			if (data.getDistanceUnloadList().get(j).getTransportType() == 1) {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttOto(i);
				if (dto.getRoadType() == 1) {
					dto.setRoadTypeName("Loại 1");
				} else if (dto.getRoadType() == 2) {
					dto.setRoadTypeName("Loại 2");
				} else if (dto.getRoadType() == 3) {
					dto.setRoadTypeName("Loại 3");
				} else if (dto.getRoadType() == 4) {
					dto.setRoadTypeName("Loại 4");
				} else if (dto.getRoadType() == 5) {
					dto.setRoadTypeName("Loại 5");
				} else {
					dto.setRoadTypeName("Khác");
				}
				i++;
				listOto.add(dto);
			} else if (data.getDistanceUnloadList().get(j).getTransportType() == 2) {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttCutKit(k);
				listCKit.add(dto);
				k++;
			} else {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttBocVac(m);
				listBVac.add(dto);
				m++;
			}
		}

		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-12.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load("oTo", DistanceUnloadListDTO.class, true);
			metadata.load("cutKit", DistanceUnloadListDTO.class, true);
			metadata.load("bocVac", DistanceUnloadListDTO.class, true);
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("oTo", listOto);
			context.put("cutKit", listCKit);
			context.put("bocVac", listBVac);

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaDirectorIdNameSign(data.getbDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}
			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-12.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-12.docx" ) ); report.process( context,
			 * out );
			 */

			// pdf
			File fout = new File(folder2Upload + "/" + obj.getDisUnloadConsMinId() + "-BM-TCNT-12.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(obj.getDisUnloadConsMinId() + "-BM-TCNT-12.pdf");
			return Response.ok(Collections.singletonMap("fileName", path))
					.build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	public String exportOneDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj) {
		DistanceUnloadConstrMinutesDTO data = distanceUnloadConstrMinutesBusinessImpl.getDataExport(obj);
		data.setDistanceUnloadList(
				distanceUnloadListBusinessImpl.doSearchByDisUnloadConsMinId(obj.getDisUnloadConsMinId()));
		List<DistanceUnloadListDTO> listOto = new ArrayList<>();
		List<DistanceUnloadListDTO> listCKit = new ArrayList<>();
		List<DistanceUnloadListDTO> listBVac = new ArrayList<>();
		int i = 1;
		int k = 1;
		int m = 1;
		for (int j = 0; j < data.getDistanceUnloadList().size(); j++) {
			if (data.getDistanceUnloadList().get(j).getTransportType() == 1) {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttOto(i);
				if (dto.getRoadType() == 1) {
					dto.setRoadTypeName("Loại 1");
				} else if (dto.getRoadType() == 2) {
					dto.setRoadTypeName("Loại 2");
				} else if (dto.getRoadType() == 3) {
					dto.setRoadTypeName("Loại 3");
				} else if (dto.getRoadType() == 4) {
					dto.setRoadTypeName("Loại 4");
				} else if (dto.getRoadType() == 5) {
					dto.setRoadTypeName("Loại 5");
				} else {
					dto.setRoadTypeName("Khác");
				}
				i++;
				listOto.add(dto);
			} else if (data.getDistanceUnloadList().get(j).getTransportType() == 2) {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttCutKit(k);
				listCKit.add(dto);
				k++;
			} else {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttBocVac(m);
				listBVac.add(dto);
				m++;
			}
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-12.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load("oTo", DistanceUnloadListDTO.class, true);
			metadata.load("cutKit", DistanceUnloadListDTO.class, true);
			metadata.load("bocVac", DistanceUnloadListDTO.class, true);
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("oTo", listOto);
			context.put("cutKit", listCKit);
			context.put("bocVac", listBVac);

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaDirectorIdNameSign(data.getbDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}
			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-10.docx" ) ); report.process( context,
			 * out );
			 */

			// pdf
			File fout = new File(folder2Upload + "/" + obj.getDisUnloadConsMinId() + "-BM-TCNT-12.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return obj.getDisUnloadConsMinId() + "-BM-TCNT-12.pdf";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public Response exportListDistanceUnloadConstrMinutes(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			DistanceUnloadConstrMinutesDTO dto = new DistanceUnloadConstrMinutesDTO();
			dto.setDisUnloadConsMinId(l);
			String filename = exportOneDistanceUnloadConstrMinutes(dto);
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
			fileCompressor.setCompressedPath(
					filePath + File.separatorChar + "XacNhanCuLyVanChuyenPDF." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("XacNhanCuLyVanChuyenPDF." + TYPE.getExtension());
			return Response.ok(
					java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response exportDocDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj) {
		DistanceUnloadConstrMinutesDTO data = distanceUnloadConstrMinutesBusinessImpl.getDataExport(obj);
		data.setDistanceUnloadList(
				distanceUnloadListBusinessImpl.doSearchByDisUnloadConsMinId(obj.getDisUnloadConsMinId()));
		List<DistanceUnloadListDTO> listOto = new ArrayList<>();
		List<DistanceUnloadListDTO> listCKit = new ArrayList<>();
		List<DistanceUnloadListDTO> listBVac = new ArrayList<>();
		int i = 1;
		int k = 1;
		int m = 1;
		for (int j = 0; j < data.getDistanceUnloadList().size(); j++) {
			if (data.getDistanceUnloadList().get(j).getTransportType() == 1) {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttOto(i);
				if (dto.getRoadType() == 1) {
					dto.setRoadTypeName("Loại 1");
				} else if (dto.getRoadType() == 2) {
					dto.setRoadTypeName("Loại 2");
				} else if (dto.getRoadType() == 3) {
					dto.setRoadTypeName("Loại 3");
				} else if (dto.getRoadType() == 4) {
					dto.setRoadTypeName("Loại 4");
				} else if (dto.getRoadType() == 5) {
					dto.setRoadTypeName("Loại 5");
				} else {
					dto.setRoadTypeName("Khác");
				}
				i++;
				listOto.add(dto);
			} else if (data.getDistanceUnloadList().get(j).getTransportType() == 2) {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttCutKit(k);
				listCKit.add(dto);
				k++;
			} else {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttBocVac(m);
				listBVac.add(dto);
				m++;
			}
		}

		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-12.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load("oTo", DistanceUnloadListDTO.class, true);
			metadata.load("cutKit", DistanceUnloadListDTO.class, true);
			metadata.load("bocVac", DistanceUnloadListDTO.class, true);
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("oTo", listOto);
			context.put("cutKit", listCKit);
			context.put("bocVac", listBVac);

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaDirectorIdNameSign(data.getbDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}
			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-12.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-12.docx" ) ); report.process(
			 * context,out );
			 */

			// pdf
			File fout = new File(folder2Upload + "/" + obj.getDisUnloadConsMinId() + "-BM-TCNT-12.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath(obj.getDisUnloadConsMinId() + "-BM-TCNT-12.docx");
			return Response.ok(Collections.singletonMap("fileName", path))
					.build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	public String exportOneDocDistanceUnloadConstrMinutes(DistanceUnloadConstrMinutesDTO obj) {
		DistanceUnloadConstrMinutesDTO data = distanceUnloadConstrMinutesBusinessImpl.getDataExport(obj);
		data.setDistanceUnloadList(
				distanceUnloadListBusinessImpl.doSearchByDisUnloadConsMinId(obj.getDisUnloadConsMinId()));
		List<DistanceUnloadListDTO> listOto = new ArrayList<>();
		List<DistanceUnloadListDTO> listCKit = new ArrayList<>();
		List<DistanceUnloadListDTO> listBVac = new ArrayList<>();
		int i = 1;
		int k = 1;
		int m = 1;
		for (int j = 0; j < data.getDistanceUnloadList().size(); j++) {
			if (data.getDistanceUnloadList().get(j).getTransportType() == 1) {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttOto(i);
				if (dto.getRoadType() == 1) {
					dto.setRoadTypeName("Loại 1");
				} else if (dto.getRoadType() == 2) {
					dto.setRoadTypeName("Loại 2");
				} else if (dto.getRoadType() == 3) {
					dto.setRoadTypeName("Loại 3");
				} else if (dto.getRoadType() == 4) {
					dto.setRoadTypeName("Loại 4");
				} else if (dto.getRoadType() == 5) {
					dto.setRoadTypeName("Loại 5");
				} else {
					dto.setRoadTypeName("Khác");
				}
				i++;
				listOto.add(dto);
			} else if (data.getDistanceUnloadList().get(j).getTransportType() == 2) {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttCutKit(k);
				listCKit.add(dto);
				k++;
			} else {
				DistanceUnloadListDTO dto = data.getDistanceUnloadList().get(j);
				dto.setSttBocVac(m);
				listBVac.add(dto);
				m++;
			}
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-12.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load("oTo", DistanceUnloadListDTO.class, true);
			metadata.load("cutKit", DistanceUnloadListDTO.class, true);
			metadata.load("bocVac", DistanceUnloadListDTO.class, true);
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			metadata.addFieldAsImage("sign3");
			metadata.addFieldAsImage("sign4");
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("oTo", listOto);
			context.put("cutKit", listCKit);
			context.put("bocVac", listBVac);

			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			if (StringUtils.isNotEmpty(data.getaDirectorIdPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorIdPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorIdPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorIdPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorIdPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorIdPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructIdPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructIdPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}

			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (data.getStatusCa() == 2) {
				data.setaDirectorIdNameSign(data.getbDirectorIdName());
				data.setaInChargeMonitorIdNameSign(data.getaInChargeMonitorIdName());
				data.setbDirectorIdNameSign(data.getbDirectorIdName());
				data.setbInChargeConstructIdNameSign(data.getbInChargeConstructIdName());
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				context.put("sign3", sign3);
				context.put("sign4", sign4);
			} else {
				data.setaDirectorIdNameSign("");
				data.setaInChargeMonitorIdNameSign("");
				data.setbDirectorIdNameSign("");
				data.setbInChargeConstructIdNameSign("");
				context.put("sign1", none);
				context.put("sign2", none);
				context.put("sign3", none);
				context.put("sign4", none);
			}
			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-10.docx" ) ); report.process(
			 * context,out );
			 */

			// pdf
			File fout = new File(folder2Upload + "/" + obj.getDisUnloadConsMinId() + "-BM-TCNT-12.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return obj.getDisUnloadConsMinId() + "-BM-TCNT-12.docx";
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public Response exportListDocDistanceUnloadConstrMinutes(List<Long> data) {
		List<String> listFileName = new ArrayList<String>();
		for (Long l : data) {
			DistanceUnloadConstrMinutesDTO dto = new DistanceUnloadConstrMinutesDTO();
			dto.setDisUnloadConsMinId(l);
			String filename = exportOneDocDistanceUnloadConstrMinutes(dto);
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
			fileCompressor.setCompressedPath(
					filePath + File.separatorChar + "XacNhanCuLyVanChuyenDOCX." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("XacNhanCuLyVanChuyenDOCX." + TYPE.getExtension());
			return Response.ok(
					java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response appro(approDTO dto) {
		Long res = distanceUnloadConstrMinutesBusinessImpl.appro(dto);
		if (res == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(res).build();
		}
	}

	@Override
	public Response deleteDistanceUnloadConstrMinutes(Long id) throws Exception {
		DistanceUnloadConstrMinutesDTO obj = (DistanceUnloadConstrMinutesDTO) distanceUnloadConstrMinutesBusinessImpl
				.getByOneId(id);
		if (obj.getStatusCa() == 2 || obj.getStatusCa() == 1) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}else {
			distanceUnloadConstrMinutesBusinessImpl.deleteTable(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response updateIsActive(List<Long> listId) {
		boolean ls = distanceUnloadConstrMinutesBusinessImpl.updateIsActive(listId);
		if (ls) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}