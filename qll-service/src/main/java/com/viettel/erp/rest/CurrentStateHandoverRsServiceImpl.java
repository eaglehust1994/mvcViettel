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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
import com.espringtran.compressor4j.compressor.FileCompressor;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.CurrentStateHandoverListBO;
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.CurrentStateHandoverBusinessImpl;
import com.viettel.erp.business.QualityCableMeaReportBusinessImpl;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.CurrentStateHandoverDTO;
import com.viettel.erp.dto.CurrentStateHandoverExtraDTO;
import com.viettel.erp.dto.CurrentStateHandoverListDTO;
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
public class CurrentStateHandoverRsServiceImpl implements CurrentStateHandoverRsService {

	@Value("${folder_upload}")
	private String folder2Upload;

	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
	static Logger LOGGER = LoggerFactory.getLogger(CurrentStateHandoverRsServiceImpl.class);
	@Autowired
	CurrentStateHandoverBusinessImpl currentStateHandoverBusinessImpl;

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	@Autowired
	CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
	
	@Override
	public Response getCurrentStateHandover() {
		List<CurrentStateHandoverDTO> ls = currentStateHandoverBusinessImpl.getAll();
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
	public Response getCurrentStateHandoverById(Long id) {
		CurrentStateHandoverDTO obj = (CurrentStateHandoverDTO) currentStateHandoverBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}
	
	@Autowired
	QualityCableMeaReportBusinessImpl qualityCableMeaReportBusinessImpl;

	@Override
	public Response updateCurrentStateHandover(CurrentStateHandoverDTO obj) {
		Long id = currentStateHandoverBusinessImpl.update(obj);
		String nameTable = "CURRENT_STATE_HANDOVER";
		if(obj.getStatusCa() == 0L){
			qualityCableMeaReportBusinessImpl.getUpdateConstrCompleteRecod(obj.getCurrentStateHandoverId() , nameTable);
		}
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	
	
	@Override
	public Response addCurrentStateHandover(CurrentStateHandoverDTO obj) {
        CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("CURRENT_STATE_HANDOVER");
        Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		String genCode = currentStateHandoverBusinessImpl.autoGenCode("CURRENT_STATE_HANDOVER", "BBBGHT");
		obj.setCode(genCode);
		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
		constrCompleteRecordMap.setDataTableName("CURRENT_STATE_HANDOVER");
		constrCompleteRecordMap.setDataTableId("CURRENT_STATE_HANDOVER_ID");
		constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
		constrCompleteRecordMap.setCreatedDate(new Date());
		constrCompleteRecordMap.setStatus(0L);
		constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
		constrCompleteRecordMap.setConstructionId(obj.getConstructId());
		obj.setConstrCompleteRecordMap(constrCompleteRecordMap);
		obj.setCreatedDate(new Date());
		constrCompleteRecordMap.setCode(genCode);
		Long id = currentStateHandoverBusinessImpl.saveTable(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
		// Long id = currentStateHandoverBusinessImpl.save(obj);
		// if (id == 0l) {
		// return Response.status(Response.Status.BAD_REQUEST).build();
		// } else {
		// try {
		// constrCompleteRecordsMapBusinessImpl.insert(obj.getConstructId(),
		// "CURRENT_STATE_HANDOVER",
		// "CURRENT_STATE_HANDOVER_ID", id, 3444l);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// this.deleteCurrentStateHandover(id);
		// LOGGER.error(e.getMessage(), e);
		// return Response.ok(Response.Status.BAD_REQUEST).build();
		// }

		// }
	}

	@Override
	public Response deleteCurrentStateHandover(Long id) {
		CurrentStateHandoverDTO obj = (CurrentStateHandoverDTO) currentStateHandoverBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			currentStateHandoverBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response getListCurrentStateHandoverByContructId(Long id) {
		List<CurrentStateHandoverDTO> ls = currentStateHandoverBusinessImpl.getCurrentStateHandoverByContructId(id);
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

	public CurrentStateHandoverExtraDTO initdData(Long id) {
		CurrentStateHandoverExtraDTO obj = currentStateHandoverBusinessImpl.getCurrentStateHandoverExtraById(id);
		obj.setChargeMonitorName(obj.getaInChargeMonitorId() == null ? ""
				: currentStateHandoverBusinessImpl.getNameEmployee(obj.getaInChargeMonitorId()));
		obj.setChargeConstructName(obj.getbInChargeConstructId() == null ? ""
				: currentStateHandoverBusinessImpl.getNameEmployee(obj.getbInChargeConstructId()));
		obj.setConclusion(obj.getConclusion() == null ? "" : obj.getConclusion());
		obj.setOtherComments(obj.getOtherComments() == null ? "" : obj.getOtherComments());
		obj.setConclusion(obj.getConclusion().compareTo("0") == 0 ? "Không đồng ý" : "Đồng ý");
		Calendar c = Calendar.getInstance();
		// TimeZone tz = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
		// c.setTimeZone(tz);
		c.setTime(obj.getHandoverDate());
		obj.setDate(new Long(c.get(Calendar.DATE)));
		obj.setMonth(new Long(c.get(Calendar.MONTH) + 1));
		Long year = new Long(c.get(Calendar.YEAR));
		obj.setYear(new Long(year.toString().replaceAll(",", "")));
		List<CurrentStateHandoverListBO> list = currentStateHandoverBusinessImpl.getCurrentStateHandoverByListId(id);
		List<CurrentStateHandoverListDTO> listDTO = Lists.newArrayList();
		for (CurrentStateHandoverListBO objChil : list) {
			CurrentStateHandoverListDTO chilDTO = objChil.toDTO();
			chilDTO.setStt(list.indexOf(objChil) + 1);
			if (chilDTO.getConstructionRequest() == 0) {
				chilDTO.setRequestNo("Không thi công");
				chilDTO.setRequestYes(" ");
			} else {
				chilDTO.setRequestNo(" ");
				chilDTO.setRequestYes("Thi công");
			}
			listDTO.add(chilDTO);
		}
		obj.setListCurrentStateHandover(listDTO);
		return obj;
	}

	@Override
	public Response exportCurrentStateHandover(Long id) {
		CurrentStateHandoverExtraDTO data = initdData(id);
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-21.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			
			
			
			
			metadata.load("cvnts", CurrentStateHandoverListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getListCurrentStateHandover());
			
			
			
			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				data.setChargeMonitorNameSign(data.getChargeMonitorName());
				data.setChargeConstructNameSign(data.getChargeConstructName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				data.setChargeMonitorNameSign("");
				data.setChargeConstructNameSign("");
			}
			
			
			// 4) Generate report by merging Java model with the Docx
			// OutputStream out = new FileOutputStream( new File(
			// "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) );
			// OutputStream out = new FileOutputStream( new File(
			// "D:\\WORK\\Test\\BM-TCNT-10.docx" ) );
			// --------------------------------------------------------------
			// File fout = new File(folder2Upload + "/BM-TCNT-21.docx");
			// OutputStream out = new FileOutputStream(fout);
			// report.process( context,out );

			// pdf
			File fout = new File(folder2Upload + "/BM-TCNT-21.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-21.pdf");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
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

	@Override
	public Response deleteCurrentStateHandoverByListId(List<Long> obj) {
		boolean result = currentStateHandoverBusinessImpl.deleteListEntity(obj);
		if (!result) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.OK).build();
		}
	}

	@Override
	public Response exportCurrentStateHandoverList(List<Long> listId) {

		// get data from db
		List<File> listFile = Lists.newArrayList();
		for (Long id : listId) {
			CurrentStateHandoverExtraDTO tranferObj = initdData(id);
			File fileTranfer = exportToFile(tranferObj, id + "");
			listFile.add(fileTranfer);
			// return Response.ok(tranferObj).build();

		}
		try {
			// if (CollectionUtils.isEmpty(path)) {
			// LOGGER.warn("No files added!!");
			// return Response.status(Response.Status.BAD_REQUEST).build();
			// }

			FileCompressor fileCompressor = new FileCompressor();
			File f = new File(folder2Upload);
			if (!f.exists()) {
				// LOGGER.warn("{} folder is not exist!!", folder2Upload);
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			String filePath = f.getCanonicalPath();
			// LOGGER.info("adding {} files", path.size());
			// path sẽ là list file name
			for (File file : listFile) {
				String srcPath = filePath + File.separatorChar + file.getName();
				String desPath = file.getName();
				// LOGGER.info("{} added", srcPath);
				fileCompressor.add(srcPath, desPath);
			}
			fileCompressor.setType(TYPE);
			fileCompressor.setLevel(LEVEL);
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "bangiaohientrang." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("bangiaohientrang." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public File exportToFile(CurrentStateHandoverExtraDTO data, String reportCode) {
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			// chưa có template ở đây
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-21.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			
			metadata.load("cvnts", CurrentStateHandoverListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getListCurrentStateHandover());

			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				data.setChargeMonitorNameSign(data.getChargeMonitorName());
				data.setChargeConstructNameSign(data.getChargeConstructName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				data.setChargeMonitorNameSign("");
				data.setChargeConstructNameSign("");
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
			File fout = new File(folder2Upload + "/BM-TCNT-21_" + reportCode + ".pdf");

			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			return fout;
			// return Response.ok(Collections.singletonMap("fileName",
			// "BM-TCNT-24.pdf"))
			// .build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public Response theApproval(Long id) throws Exception {
		// TODO Auto-generated method stub
		Long reId = currentStateHandoverBusinessImpl.getconstrCompReMapId(id);
		CurrentStateHandoverExtraDTO obj = initdData(id);
		File file = exportToFile(obj, id + "");
		List<String> listRs = Lists.newArrayList();
		listRs.add(reId + "");
		listRs.add(UEncrypt.encryptFileUploadPath(file.getPath()));
		listRs.add(UEncrypt.encryptFileUploadPath(file.getName()));
		return Response.ok(listRs).build();
	}

	@Override
	public Response exportCurrentStateHandoverDoc(Long id) {
		CurrentStateHandoverExtraDTO data = initdData(id);
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-21.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			
			metadata.load("cvnts", CurrentStateHandoverListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getListCurrentStateHandover());

			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				data.setChargeMonitorNameSign(data.getChargeMonitorName());
				data.setChargeConstructNameSign(data.getChargeConstructName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				data.setChargeMonitorNameSign("");
				data.setChargeConstructNameSign("");
			}
			
			
			// 4) Generate report by merging Java model with the Docx
			// OutputStream out = new FileOutputStream( new File(
			// "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) );
			// OutputStream out = new FileOutputStream( new File(
			// "D:\\WORK\\Test\\BM-TCNT-10.docx" ) );
			// --------------------------------------------------------------
			File fout = new File(folder2Upload + "/BM-TCNT-21.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);

			// pdf
			// File fout = new File(folder2Upload + "/BM-TCNT-21.pdf");
			// OutputStream out = new FileOutputStream(fout);
			// Options options =
			// Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			// report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-21.docx");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
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

	@Override
	public Response appro(approDTO obj) {
		Long id = currentStateHandoverBusinessImpl.getconstrCompReMapId(obj.getCurrentStateHandoverId());
		obj.setConstrCompReMapId(id);
		Long result = currentStateHandoverBusinessImpl.appro(obj);
		if (result != 0) {
			return Response.ok(result).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// export to DOC
	public File exportToFileDOC(CurrentStateHandoverExtraDTO data, String reportCode) {
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			// chưa có template ở đây
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-21.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			metadata.addFieldAsImage("sign1");
			metadata.addFieldAsImage("sign2");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			
			metadata.load("cvnts", CurrentStateHandoverListDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getListCurrentStateHandover());

			if (data.getStatusCa() == 2) {
				context.put("sign1", sign1);
				context.put("sign2", sign2);
				data.setChargeMonitorNameSign(data.getChargeMonitorName());
				data.setChargeConstructNameSign(data.getChargeConstructName());
			} else {
				context.put("sign1", none);
				context.put("sign2", none);
				data.setChargeMonitorNameSign("");
				data.setChargeConstructNameSign("");
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
			File fout = new File(folder2Upload + "/BM-TCNT-21_" + reportCode + ".docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			return fout;
			// return Response.ok(Collections.singletonMap("fileName",
			// "BM-TCNT-24.pdf"))
			// .build();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public Response exportCurrentStateHandoverDOCList(List<Long> listId) {

		// get data from db
		List<File> listFile = Lists.newArrayList();
		for (Long id : listId) {
			CurrentStateHandoverExtraDTO tranferObj = initdData(id);
			System.out.println("++++++++++++++tes345435t " + id);
			File fileTranfer = exportToFileDOC(tranferObj, id + "");
			listFile.add(fileTranfer);
			// return Response.ok(tranferObj).build();

		}
		try {
			// if (CollectionUtils.isEmpty(path)) {
			// LOGGER.warn("No files added!!");
			// return Response.status(Response.Status.BAD_REQUEST).build();
			// }

			FileCompressor fileCompressor = new FileCompressor();
			File f = new File(folder2Upload);
			if (!f.exists()) {
				// LOGGER.warn("{} folder is not exist!!", folder2Upload);
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

			String filePath = f.getCanonicalPath();
			// LOGGER.info("adding {} files", path.size());
			// path sẽ là list file name
			for (File file : listFile) {
				String srcPath = filePath + File.separatorChar + file.getName();
				String desPath = file.getName();
				// LOGGER.info("{} added", srcPath);
				fileCompressor.add(srcPath, desPath);
			}
			fileCompressor.setType(TYPE);
			fileCompressor.setLevel(LEVEL);
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "bangiaohientrang." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("bangiaohientrang." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

}
