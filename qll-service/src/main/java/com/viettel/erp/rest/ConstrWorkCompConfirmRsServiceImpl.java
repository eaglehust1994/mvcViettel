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
import com.viettel.erp.business.CatFileInvoiceBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.business.ConstrWorkCompConfirmBusinessImpl;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstrWorkCompConfListLessDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmDTO;
import com.viettel.erp.dto.ConstrWorkCompConfirmExportDTO;
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
public class ConstrWorkCompConfirmRsServiceImpl implements ConstrWorkCompConfirmRsService {
	@Value("${folder_upload}")
	private String folder2Upload;

	private static final CompressionType TYPE = CompressionType.ZIP;
	private static final CompressionLevel LEVEL = ZipLevel.NORMAL;
	static Logger LOGGER = LoggerFactory.getLogger(ConstrWorkCompConfirmRsServiceImpl.class);
	@Autowired
	ConstrWorkCompConfirmBusinessImpl constrWorkCompConfirmBusinessImpl;
	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;
	@Autowired
	CatFileInvoiceBusinessImpl catFileInvoiceBusinessImpl;
	@Override
	public Response getConstrWorkCompConfirm() {
		List<ConstrWorkCompConfirmDTO> ls = constrWorkCompConfirmBusinessImpl.getAll();
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
	public Response getConstrWorkCompConfirmById(Long id) {
		ConstrWorkCompConfirmDTO obj = (ConstrWorkCompConfirmDTO) constrWorkCompConfirmBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateConstrWorkCompConfirm(ConstrWorkCompConfirmDTO obj) {
		Long id = constrWorkCompConfirmBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}

	}

	@Override
	public Response addConstrWorkCompConfirm(ConstrWorkCompConfirmDTO obj) {
		System.out.println("run this function");
        CatFileInvoiceDTO catInvoice = catFileInvoiceBusinessImpl.onlyFindByTableName("CONSTR_WORK_COMP_CONFIRM");
        Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		String code = constrWorkCompConfirmBusinessImpl.getCode("CONSTR_WORK_COMP_CONFIRM", "QLHC_BBXNKLHTCT");
		obj.setCode(code);
		ConstrCompleteRecordsMapDTO constrCompleteRecordMap = new ConstrCompleteRecordsMapDTO();
    	constrCompleteRecordMap.setDataTableName("CONSTR_WORK_COMP_CONFIRM");
    	constrCompleteRecordMap.setDataTableId("CONSTR_WORK_COMP_CONFIRM_ID");
    	constrCompleteRecordMap.setCatFileInvoiceId(catFileInvoiceId);
    	constrCompleteRecordMap.setCreatedDate(new Date());
    	constrCompleteRecordMap.setStatus(0L);
    	constrCompleteRecordMap.setCreatedUserId(obj.getCreatedUserId());
    	constrCompleteRecordMap.setConstructionId(obj.getConstructId());
    	obj.setCreatedDate(new Date());
    	obj.setConstrCompleteRecordMap(constrCompleteRecordMap);
    	constrCompleteRecordMap.setCode(code);
		
        Long id = constrWorkCompConfirmBusinessImpl.saveTable(obj);
		if (id == 0l) {
		return Response.status(Response.Status.BAD_REQUEST).build();
	}else{
		return Response.ok(Response.Status.CREATED).build();
	}
//		Long id = constrWorkCompConfirmBusinessImpl.save(obj);
//		if (id == 0l) {
//
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		} else {
//			try {
//				constrCompleteRecordsMapBusinessImpl.insert(obj.getConstructId(), "CONSTR_WORK_COMP_CONFIRM",
//						"CONSTR_WORK_COMP_CONFIRM_ID", id, 3444l);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				this.deleteConstrWorkCompConfirm(id);
//				LOGGER.error(e.getMessage(), e);
//				return Response.ok(Response.Status.BAD_REQUEST).build();
//
//			}
//			return Response.ok(Response.Status.CREATED).build();
//		}
	}

	@Override
	public Response deleteConstrWorkCompConfirm(Long id) {
		ConstrWorkCompConfirmDTO obj = (ConstrWorkCompConfirmDTO) constrWorkCompConfirmBusinessImpl.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			constrWorkCompConfirmBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response getConstrWorkCompConfirmByConstrId(Long id) {
		// TODO Auto-generated method stub
		List<ConstrWorkCompConfirmDTO> ls = constrWorkCompConfirmBusinessImpl.getCWorkCompleteByContrId(id);
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
	public Response deleteListConstrWorkCompConfirm(List<Long> obj) {
		boolean result = constrWorkCompConfirmBusinessImpl.deleteListEntity(obj);
		if (result) {
			return Response.ok(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	public File exportToFile(ConstrWorkCompConfirmExportDTO data, String reportCode) throws IOException, XDocReportException {
		for (int j = 0; j < data.getListConstrWorkCompConfLessDTO().size(); j++) {
			ConstrWorkCompConfListLessDTO dto = data.getListConstrWorkCompConfLessDTO().get(j);
			dto.setStt(new Long(j + 1));
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			// chưa có template ở đây
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-24.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			
			
			metadata.addFieldAsImage("sign11");
			metadata.addFieldAsImage("sign21");
			metadata.addFieldAsImage("sign31");
			metadata.addFieldAsImage("sign41");
			metadata.addFieldAsImage("sign12");
			metadata.addFieldAsImage("sign22");
			metadata.addFieldAsImage("sign32");
			metadata.addFieldAsImage("sign42");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			System.out.println(folder2Upload);
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			
			
			
			
			metadata.load("cvnts", ConstrWorkCompConfListLessDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getListConstrWorkCompConfLessDTO());

			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-10.docx" ) ); report.process( context,
			 * out );
			 */

			if (data.getStatusCa() == 2) {
				context.put("sign11", sign1);
				context.put("sign21", sign2);
				context.put("sign31", sign3);
				context.put("sign41", sign4);
				context.put("sign12", sign1);
				context.put("sign22", sign2);
				context.put("sign32", sign3);
				context.put("sign42", sign4);
				data.setaDirectorNameSign(data.getaDirectorName());
				data.setaInChargeMonitorNameSign(data.getaInChargeMonitorName());
				data.setbDirectorNameSign(data.getbDirectorName());
				data.setbInChargeConstructNameSign(data.getbInChargeConstructName());
			} else {
				context.put("sign11", none);
				context.put("sign21", none);
				context.put("sign31", none);
				context.put("sign41", none);
				context.put("sign12", none);
				context.put("sign22", none);
				context.put("sign32", none);
				context.put("sign42", none);
				data.setaDirectorNameSign("");
				data.setaInChargeMonitorNameSign("");
				data.setbDirectorNameSign("");
				data.setbInChargeConstructNameSign("");
			}
			
			// pdf
			File fout = new File(folder2Upload + "/BM-TCNT-24_" + reportCode + ".pdf");

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
			throw e;
		} catch (XDocReportException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}

	public ConstrWorkCompConfirmExportDTO initData(Long id) {
		// vào db rồi điền dữ liệu vào object
		// công trình
		// mã trạm
		// địa chỉ
		// số hợp đồng
		ConstrWorkCompConfirmExportDTO obj = constrWorkCompConfirmBusinessImpl.getConstructInfoById(id);
		System.out.println("+++++++++++++++" + obj.getaDirectorId());
		
		// -------------------------
		// tên đại diên chủ đầu tư
		obj.setaDirectorName(constrWorkCompConfirmBusinessImpl.getNameEmployee(obj.getaDirectorId()));
		obj.setaInChargeMonitorName(constrWorkCompConfirmBusinessImpl.getNameEmployee(obj.getaInChargeMonitorId()));
		// tên thành phần tham ra
		obj.setbDirectorName(constrWorkCompConfirmBusinessImpl.getNameEmployee(obj.getbDirectorId()));
		obj.setbInChargeConstructName(constrWorkCompConfirmBusinessImpl.getNameEmployee(obj.getbInChargeConstructId()));
		System.out.println("=== done thanh phan tham ra");
		// biên bản xác nhận khối lượng hoàn thành công trình
		ConstrWorkCompConfirmDTO constrWCC = (ConstrWorkCompConfirmDTO) constrWorkCompConfirmBusinessImpl
				.getOneById(id);
		obj.setCode(constrWCC.getCode());
		// địa điểm xác nhận
		obj.setConfirmPlace(constrWCC.getConfirmPlace() == null ? "" : constrWCC.getConfirmPlace());
		Date startTime = constrWCC.getConfirmFromDate();
		Date stopTime = constrWCC.getConfirmToDate();
		Date signTime = constrWCC.getSignDate();
		// địa điểm ký
		obj.setSignPlace(constrWCC.getSignPlace());
		
		
		
		Calendar c = Calendar.getInstance();
//		TimeZone tz = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
//		c.setTimeZone(tz);
		// thời gian ký
		c.setTime(signTime);

		obj.setSignDateInMonth(new Long(c.get(Calendar.DAY_OF_MONTH)));
		obj.setSignMonth(new Long(c.get(Calendar.MONTH)+1));
		obj.setSignYear(new Long(c.get(Calendar.YEAR)));
		// thời gian bắt đầu
		c.setTime(startTime);

		obj.setStartdate(new Long(c.get(Calendar.DAY_OF_MONTH)));
		obj.setStartMonth(new Long(c.get(Calendar.MONTH)+1));
		obj.setStartYear(new Long(c.get(Calendar.YEAR)));
		obj.setStartHour(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		// thời gian kết thúc
		
		c.setTime(stopTime);
		obj.setStopdate(new Long(c.get(Calendar.DAY_OF_MONTH)));
		obj.setStopMonth(new Long(c.get(Calendar.MONTH)+1));
		obj.setStopYear(new Long(c.get(Calendar.YEAR)));
		obj.setStopHour(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		// ý kiên khác
		obj.setOhterComments(constrWCC.getOhterComments());
		// kết luận
		obj.setConclusionText(constrWCC.getConclusion() == 1 ? "Đồng ý" : "Không đồng ý");
		// dữ liệu bản con
		List<Long> Listid = Lists.newArrayList();
		Listid.add(obj.getConstructId());
		Listid.add(id);
		List<ConstrWorkCompConfListLessDTO> list = constrWorkCompConfirmBusinessImpl
				.getListConstrWorkExistByConstrId(Listid);
		for (ConstrWorkCompConfListLessDTO chilObj : list) {
			chilObj.setStt(new Long(list.indexOf(chilObj) + 1));
			chilObj.setComments(chilObj.getComments() == null ? "" : chilObj.getComments());
		}

		System.out.println("----=-=-=-=-=-" + list.size());
		obj.setListConstrWorkCompConfLessDTO(list);
		return obj;

	}

	@Override
	public Response exportZipConstrWorkCompConfirm(List<Long> listid) {
		// get data from db
		List<File> listFile = Lists.newArrayList();
		for (Long id : listid) {
			ConstrWorkCompConfirmExportDTO tranferObj = initData(id);
			File fileTranfer;
			try {
				fileTranfer = exportToFile(tranferObj, id + "");
			} catch (IOException | XDocReportException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(), e);
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "bangiaokhoiluonghoanthanh." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("bangiaokhoiluonghoanthanh." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Response exportZipConstrWorkCompConfirmDOC(List<Long> listid) {
		// get data from db
		List<File> listFile = Lists.newArrayList();
		for (Long id : listid) {
			ConstrWorkCompConfirmExportDTO tranferObj = initData(id);
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
			fileCompressor.setCompressedPath(filePath + File.separatorChar + "bangiaokhoiluonghoanthanh." + TYPE.getExtension());
			fileCompressor.compress();
			String path  =   UEncrypt.encryptFileUploadPath("bangiaokhoiluonghoanthanh." + TYPE.getExtension());
			return Response.ok(java.util.Collections.singletonMap("fileName", path))
					.build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	public Response exportConstrWorkCompConfirm(Long id) {
		ConstrWorkCompConfirmExportDTO data = initData(id);
		System.out.println();
		for (int j = 0; j < data.getListConstrWorkCompConfLessDTO().size(); j++) {
			ConstrWorkCompConfListLessDTO dto = data.getListConstrWorkCompConfLessDTO().get(j);
			dto.setStt(new Long(j + 1));
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-24.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			
			
			metadata.addFieldAsImage("sign11");
			metadata.addFieldAsImage("sign21");
			metadata.addFieldAsImage("sign31");
			metadata.addFieldAsImage("sign41");
			metadata.addFieldAsImage("sign12");
			metadata.addFieldAsImage("sign22");
			metadata.addFieldAsImage("sign32");
			metadata.addFieldAsImage("sign42");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			System.out.println(folder2Upload);
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			
			
			
			
			metadata.load("cvnts", ConstrWorkCompConfListLessDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getListConstrWorkCompConfLessDTO());
			
			
			
			if (data.getStatusCa() == 2) {
				context.put("sign11", sign1);
				context.put("sign21", sign2);
				context.put("sign31", sign3);
				context.put("sign41", sign4);
				context.put("sign12", sign1);
				context.put("sign22", sign2);
				context.put("sign32", sign3);
				context.put("sign42", sign4);
				data.setaDirectorNameSign(data.getaDirectorName());
				data.setaInChargeMonitorNameSign(data.getaInChargeMonitorName());
				data.setbDirectorNameSign(data.getbDirectorName());
				data.setbInChargeConstructNameSign(data.getbInChargeConstructName());
			} else {
				context.put("sign11", none);
				context.put("sign21", none);
				context.put("sign31", none);
				context.put("sign41", none);
				context.put("sign12", none);
				context.put("sign22", none);
				context.put("sign32", none);
				context.put("sign42", none);
				data.setaDirectorNameSign("");
				data.setaInChargeMonitorNameSign("");
				data.setbDirectorNameSign("");
				data.setbInChargeConstructNameSign("");
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
			File fout = new File(folder2Upload + "/BM-TCNT-24.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-24.pdf");
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
	public Response signCA(Long id) throws Exception {
		// TODO Auto-generated method stub
		Long reId = constrWorkCompConfirmBusinessImpl.getconstrCompReMapId(id);
		ConstrWorkCompConfirmExportDTO obj = initData(id);
		File file;
		try {
			file = exportToFile(obj, id + "");
			List<String> listRs = Lists.newArrayList();
			listRs.add(reId + "");
			listRs.add(UEncrypt.encryptFileUploadPath(file.getPath()));
			listRs.add(UEncrypt.encryptFileUploadPath(file.getName()));
			return Response.ok(listRs).build();
		} catch (IOException | XDocReportException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

	}

	@Override
	public Response exportConstrWorkCompConfirmDoc(Long id) {
		ConstrWorkCompConfirmExportDTO data = initData(id);
		System.out.println();
		for (int j = 0; j < data.getListConstrWorkCompConfLessDTO().size(); j++) {
			ConstrWorkCompConfListLessDTO dto = data.getListConstrWorkCompConfLessDTO().get(j);
			dto.setStt(new Long(j + 1));
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();

			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-24.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			metadata.addFieldAsImage("sign11");
			metadata.addFieldAsImage("sign21");
			metadata.addFieldAsImage("sign31");
			metadata.addFieldAsImage("sign41");
			metadata.addFieldAsImage("sign12");
			metadata.addFieldAsImage("sign22");
			metadata.addFieldAsImage("sign32");
			metadata.addFieldAsImage("sign42");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			System.out.println(folder2Upload);
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			
			
			metadata.load("cvnts", ConstrWorkCompConfListLessDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getListConstrWorkCompConfLessDTO());

			if (data.getStatusCa() == 2) {
				context.put("sign11", sign1);
				context.put("sign21", sign2);
				context.put("sign31", sign3);
				context.put("sign41", sign4);
				context.put("sign12", sign1);
				context.put("sign22", sign2);
				context.put("sign32", sign3);
				context.put("sign42", sign4);
				data.setaDirectorNameSign(data.getaDirectorName());
				data.setaInChargeMonitorNameSign(data.getaInChargeMonitorName());
				data.setbDirectorNameSign(data.getbDirectorName());
				data.setbInChargeConstructNameSign(data.getbInChargeConstructName());
			} else {
				context.put("sign11", none);
				context.put("sign21", none);
				context.put("sign31", none);
				context.put("sign41", none);
				context.put("sign12", none);
				context.put("sign22", none);
				context.put("sign32", none);
				context.put("sign42", none);
				data.setaDirectorNameSign("");
				data.setaInChargeMonitorNameSign("");
				data.setbDirectorNameSign("");
				data.setbInChargeConstructNameSign("");
			}
			
			
			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-10.docx" ) ); report.process( context,
			 * out );
			 */
			File fout = new File(folder2Upload + "/BM-TCNT-24.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);

			// pdf
			// File fout = new File(folder2Upload + "/BM-TCNT-24.pdf");
			// OutputStream out = new FileOutputStream(fout);
			// Options options =
			// Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			// report.convert(context, options, out);
			out.flush();
			out.close();
			String path  =   UEncrypt.encryptFileUploadPath("BM-TCNT-24.docx");
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

	
	public File exportToFileDOC(ConstrWorkCompConfirmExportDTO data, String reportCode) {
		for (int j = 0; j < data.getListConstrWorkCompConfLessDTO().size(); j++) {
			ConstrWorkCompConfListLessDTO dto = data.getListConstrWorkCompConfLessDTO().get(j);
			dto.setStt(new Long(j + 1));
		}
		try {
			// 1) Load Docx file by filling Freemarker template engine and cache
			// it to the registry
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			// chưa có template ở đây
			InputStream in = new FileInputStream(new File(filePath + "/BM-TCNT-24.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);

			// 2) Create fields metadata to manage lazy loop ([#list Freemarker)
			// for foot notes.
			FieldsMetadata metadata = report.createFieldsMetadata();
			
			
			metadata.addFieldAsImage("sign11");
			metadata.addFieldAsImage("sign21");
			metadata.addFieldAsImage("sign31");
			metadata.addFieldAsImage("sign41");
			metadata.addFieldAsImage("sign12");
			metadata.addFieldAsImage("sign22");
			metadata.addFieldAsImage("sign32");
			metadata.addFieldAsImage("sign42");
			IImageProvider sign1;
			IImageProvider sign2;
			IImageProvider sign3;
			IImageProvider sign4;
			System.out.println(folder2Upload);
			IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign1 = new FileImageProvider(new File(folder2Upload + "/" + data.getaDirectorPath()));
			} else {
				sign1 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getaInChargeMonitorPath())) {
				sign2 = new FileImageProvider(new File(folder2Upload + "/" + data.getaInChargeMonitorPath()));
			} else {
				sign2 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbDirectorPath())) {
				sign3 = new FileImageProvider(new File(folder2Upload + "/" + data.getbDirectorPath()));
			} else {
				sign3 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			if (StringUtils.isNotEmpty(data.getbInChargeConstructPath())) {
				sign4 = new FileImageProvider(new File(folder2Upload + "/" + data.getbInChargeConstructPath()));
			} else {
				sign4 = new FileImageProvider(new File(filePath + "/none.png"));
			}
			
			
			metadata.load("cvnts", ConstrWorkCompConfListLessDTO.class, true);
			// 3) Create context Java model
			IContext context = report.createContext();

			context.put("item", data);
			context.put("cvnts", data.getListConstrWorkCompConfLessDTO());

			/*
			 * // 4) Generate report by merging Java model with the Docx
			 * //OutputStream out = new FileOutputStream( new File(
			 * "WebContent/WEB-INF/doc-result/BM-TCNT-10.docx" ) ); OutputStream
			 * out = new FileOutputStream( new File(
			 * "D:\\WORK\\Test\\BM-TCNT-10.docx" ) ); report.process( context,
			 * out );
			 */

			if (data.getStatusCa() == 2) {
				context.put("sign11", sign1);
				context.put("sign21", sign2);
				context.put("sign31", sign3);
				context.put("sign41", sign4);
				context.put("sign12", sign1);
				context.put("sign22", sign2);
				context.put("sign32", sign3);
				context.put("sign42", sign4);
				data.setaDirectorNameSign(data.getaDirectorName());
				data.setaInChargeMonitorNameSign(data.getaInChargeMonitorName());
				data.setbDirectorNameSign(data.getbDirectorName());
				data.setbInChargeConstructNameSign(data.getbInChargeConstructName());
			} else {
				context.put("sign11", none);
				context.put("sign21", none);
				context.put("sign31", none);
				context.put("sign41", none);
				context.put("sign12", none);
				context.put("sign22", none);
				context.put("sign32", none);
				context.put("sign42", none);
				data.setaDirectorNameSign("");
				data.setaInChargeMonitorNameSign("");
				data.setbDirectorNameSign("");
				data.setbInChargeConstructNameSign("");
			}
			
			
			
			// pdf
			File fout = new File(folder2Upload + "/BM-TCNT-24_" + reportCode + ".docx");
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
}
