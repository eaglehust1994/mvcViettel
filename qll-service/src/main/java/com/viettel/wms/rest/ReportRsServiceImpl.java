//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.viettel.wms.rest;
//
//import java.beans.BeanInfo;
//import java.beans.IntrospectionException;
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.ResponseBuilder;
//
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
//import net.sf.jasperreports.engine.export.JRPdfExporter;
//import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
//import net.sf.jasperreports.engine.export.JRXhtmlExporter;
//import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
//import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
//import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
//
//import org.apache.commons.lang3.StringUtils;
//import org.hibernate.Session;
//import org.hibernate.jdbc.Work;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.google.common.collect.Lists;
//import com.mchange.io.FileUtils;
//import com.viettel.asset.filter.session.UserSession;
//import com.viettel.ktts.vps.VpsPermissionChecker;
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.voffice.ws_autosign.service.FileAttachTranfer;
//import com.viettel.wms.business.CommonBusiness;
//import com.viettel.wms.business.SignVofficeBusinessImpl;
//import com.viettel.wms.business.StockTransBusinessImpl;
//import com.viettel.wms.business.UserRoleBusinessImpl;
//import com.viettel.wms.constant.Constants;
//import com.viettel.wms.dto.CommonDTO;
//import com.viettel.wms.dto.StockTransDTO;
//
//public class ReportRsServiceImpl implements ReportRsService {
//
//	// protected final Logger log = Logger.getLogger(UserRsService.class);
//	@Context HttpServletRequest request;
//	@Autowired
//    SignVofficeBusinessImpl signVofficeBusinessImpl;
//	@Autowired
//	CommonBusiness commonBusiness;
//	@Autowired
//	StockTransBusinessImpl stockTransBusinessImpl;
//	@Autowired
//	 UserRoleBusinessImpl userRoleBusinessImpl;
//	@Override
//	public Response exportPdf(CommonDTO obj) throws IntrospectionException,
//			IllegalAccessException, IllegalArgumentException,
//			InvocationTargetException {
//		
//		if("BienBanBanGiao".equals(obj.getReportName())){
//			StockTransDTO stockTrans = new StockTransDTO();
//			ArrayList<Long> listDeptReceiverId =new  ArrayList<Long>();
//			for(int i=0;i<obj.getListStockTransId().size();i++){
//				stockTrans = stockTransBusinessImpl.getStockTransDetail(obj.getListStockTransId().get(i));
//				if(stockTrans.getDeptReceiveId() != null){
//					listDeptReceiverId.add(stockTrans.getDeptReceiveId());
//					for(int j=0;j<listDeptReceiverId.size();j++){
//						if(stockTrans.getDeptReceiveId() != listDeptReceiverId.get(j)){
//							ResponseBuilder response = Response.ok(obj.getReportName());
//							response.header("error", "error1");
//							return response.build();
//						}
//							
//					}
//				}else{
//					ResponseBuilder response = Response.ok(obj.getReportName());
//					response.header("error", "error2");
//					return response.build();
//				}
//				
//			}
//		}
//		String err=validateData(obj);
//		
//		if(StringUtils.isNotEmpty(err)){
//			ResponseBuilder response = Response.ok(err);
//			response.header("error", err);
//			return response.build();
//			
//		}
//		
//		if(Constants.DASH_BROAD.equals(obj.getReportGroup())){
//			List<Long> list=commonBusiness.getListDomainData(Constants.OperationKey.VIEW, Constants.AdResourceKey.STOCK, request);
//			obj.setListStockId(list);
//		} 
//		
//		if(Constants.LISTREPORTNAMESTOCKNULL.contains(obj.getReportName()) && obj.getListStockId().size()==0){
//			List<Long> list=commonBusiness.getListDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK, request);
//			obj.setListStockId(list);
//		}
//		
//		Session session = signVofficeBusinessImpl.gettDAO().getSessionFactory()
//				.openSession();
//		
//		HashMap<String, Object> params = new HashMap<String, Object>();
//		BeanInfo info = Introspector.getBeanInfo(obj.getClass());
//		for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
//			Method reader = pd.getReadMethod();
//			if (reader != null)
//				params.put(pd.getName(), reader.invoke(obj));
//		}
//		String reportName = obj.getReportName();
//		List<String> reportNames=obj.getReportNames();
//		try {
//
//			String path = System.getProperty("java.io.tmpdir");
//			String prefix = makePrefix(reportName);
//			File file = null;
//			if ("PDF".equals(obj.getReportType())) {
//				file = File.createTempFile(prefix, ".pdf", new File(path));
//				generateReport(session, reportName, null, Constants.PDF_REPORT,
//						params, file, "ABC");
//			} else if ("EXCEL".equals(obj.getReportType())) {
//				file = File.createTempFile(prefix, ".xlsx", new File(path));
//				generateReport(session, reportName, null,
//						Constants.EXCEL_REPORT, params, file, "ABC");
//			} else if ("EXCEL_MUL".equals(obj.getReportType())) {
//				file = File.createTempFile(prefix, ".xlsx", new File(path));
//				generateReport2(session, reportNames, null,
//						Constants.EXCEL_REPORT, params, file, "ABC");
//			} else {
//				file = File.createTempFile(prefix, ".docx", new File(path));
//				generateReport(session, reportName, null, Constants.DOC_REPORT,
//						params, file, "ABC");
//			}
//			session.close();
//			if (file.exists()) {
//				ResponseBuilder response = Response.ok(file);
//				return response.build();
//			}
//
//			reportName = null;
//
//			return Response.status(Response.Status.NO_CONTENT).build();
//		} catch (Exception e) {
//			session.close();
//			return Response.status(Response.Status.NO_CONTENT).build();
//		}
//	}
//
//	private String makePrefix(String name) {
//		StringBuffer prefix = new StringBuffer();
//		char[] nameArray = name.toCharArray();
//		for (char ch : nameArray) {
//			if (Character.isLetterOrDigit(ch)) {
//				prefix.append(ch);
//			} else {
//				prefix.append("_");
//			}
//		}
//		return prefix.toString();
//	}
//
//	private JasperPrint jasperPrint = null;
//
//	private void generateReport(Session session, String reportName,
//			String filePath, int reportType, HashMap params, File file,
//			String title) {
//		session.doWork(new Work() {
//			@SuppressWarnings({ "unchecked", "unused" })
//			public void execute(Connection connection) throws SQLException {
//				try {
//					jasperPrint = null;
//					ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//					String reportPath = classloader.getResource("../" + "doc-template").getPath();
//					File fielRe = new File(reportPath + reportName + ".jasper");
//					InputStream reportStream = new FileInputStream(fielRe);
//
//					if (jasperPrint == null) {
//						System.out.println(" Begin fillReport  ");
//						jasperPrint = JasperFillManager.fillReport(
//								reportStream, params, connection);
//						System.out.println(" END fillReport ");
//						jasperPrint
//						.setProperty(
//								"net.sf.jasperreports.export.xls.remove.empty.space.between.rows",
//								"true");
//						
//					}
//
//					try {
//						connection.close();
//					} catch (SQLException e) {
//
//						e.printStackTrace();
//						;
//					}
//					connection = null;
//
//					switch (reportType) {
//					case Constants.PDF_REPORT:
//
//						JRPdfExporter exporterpdf = new JRPdfExporter();
//
//						exporterpdf.setParameter(
//								JRPdfExporterParameter.FORCE_LINEBREAK_POLICY,
//								Boolean.FALSE);
//						// exporterpdf.setParameter(JRPdfExporterParameter.FORCE_SVG_SHAPES,
//						// Boolean.TRUE);
//						exporterpdf.setParameter(
//								JRPdfExporterParameter.IS_COMPRESSED,
//								Boolean.TRUE);
//						exporterpdf.setParameter(
//								JRPdfExporterParameter.METADATA_AUTHOR,
//								"Dang Trang");
//						exporterpdf.setParameter(
//								JRPdfExporterParameter.METADATA_CREATOR,
//								"Dang Trang");
//						exporterpdf.setParameter(
//								JRPdfExporterParameter.METADATA_SUBJECT,
//								"ERP2.0 - VTSoft1");
//						exporterpdf.setParameter(
//								JRPdfExporterParameter.METADATA_TITLE, title);
//						
//						exporterpdf.setParameter(
//								JRExporterParameter.JASPER_PRINT, jasperPrint);
//						exporterpdf
//								.setParameter(
//										JRExporterParameter.CHARACTER_ENCODING,
//										"utf-8");
//						exporterpdf.setParameter(
//								JRExporterParameter.OUTPUT_FILE, file);
//
//						exporterpdf.exportReport();
//
//						break;
//					case Constants.EXCEL_REPORT:
//						JRXlsxExporter exporterXLS = new JRXlsxExporter();
//
//						String[] sheetNames = { "Sheet1" };
//
//						// Trangdd - 2016/12/16: Remove header except first page
//						// when export to Excel
//						if (jasperPrint == null) {
//							jasperPrint
//									.setProperty(
//											"net.sf.jasperreports.export.xls.exclude.origin.band.1",
//											"pageHeader");
//							// Remove the pageFooter from all the pages
//							jasperPrint
//									.setProperty(
//											"net.sf.jasperreports.export.xls.exclude.origin.band.2",
//											"pageFooter");
//							// Remove the columnHeader from pages except
//							// starting page
//							jasperPrint
//									.setProperty(
//											"net.sf.jasperreports.export.xls.exclude.origin.keep.first.band.1",
//											"columnHeader");
//							jasperPrint
//									.setProperty(
//											"net.sf.jasperreports.export.xls.remove.empty.space.between.rows",
//											"true");
//							jasperPrint
//									.setProperty(
//											"net.sf.jasperreports.export.xls.remove.empty.space.between.columns",
//											"true");
//						}
//
//						exporterXLS
//								.setParameter(
//										JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
//										Boolean.TRUE);
//						exporterXLS.setParameter(
//								JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
//								Boolean.FALSE);
//						exporterXLS.setParameter(
//								JRXlsExporterParameter.OFFSET_X, 0);
//						exporterXLS.setParameter(
//								JRXlsExporterParameter.OFFSET_Y, 0);
//						exporterXLS.setParameter(
//								JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
//								Boolean.TRUE);
//						exporterXLS
//								.setParameter(
//										JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
//										Boolean.FALSE);
//						exporterXLS.setParameter(
//								JRXlsExporterParameter.SHEET_NAMES, sheetNames);
//
//						exporterXLS.setParameter(
//								JRExporterParameter.JASPER_PRINT, jasperPrint);
//						exporterXLS
//								.setParameter(
//										JRExporterParameter.CHARACTER_ENCODING,
//										"utf-8");
//						exporterXLS.setParameter(
//								JRExporterParameter.OUTPUT_FILE, file);
//						exporterXLS.exportReport();
//
//						break;
//					case Constants.HTML_REPORT:
//						// JasperExportManager.exportReportToHtmlFile(jasperPrint,
//						// filePath);
//
//						Integer limitedPages = 80;
//
//						JRXhtmlExporter exporter = new JRXhtmlExporter();
//
//						exporter.setParameter(
//								JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,
//								Boolean.TRUE);
//						// trangdd Fix image not show in HTML issue
//						// exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,
//						// Executions.getCurrent().getDesktop().getSession().getWebApp().getRealPath("/images/"));
//						// HttpServletRequest request =
//						// (HttpServletRequest)Executions.getCurrent().getNativeRequest();
//						// exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
//						// request.getContextPath() + "/images/");
//						// trangdd
//						exporter.setParameter(
//								JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
//								Boolean.FALSE);
//						exporter.setParameter(
//								JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
//								Boolean.FALSE);
//						exporter.setParameter(
//								JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES,
//								Boolean.FALSE);
//						exporter.setParameter(
//								JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND,
//								Boolean.FALSE);
//						exporter.setParameter(
//								JRHtmlExporterParameter.IS_WRAP_BREAK_WORD,
//								Boolean.FALSE);
//						exporter.setParameter(
//								JRPdfExporterParameter.IS_COMPRESSED, true);
//
//						// trangdd Neu bao cao co so page vuot qua page gioi han
//						// (do cau hinh) thi se hien thi so page gioi han
//						if (jasperPrint.getPages().size() > limitedPages
//								&& limitedPages > 1) {
//							exporter.setParameter(
//									JRHtmlExporterParameter.END_PAGE_INDEX,
//									limitedPages - 1);
//						}
//						// End trangdd
//						exporter.setParameter(JRExporterParameter.JASPER_PRINT,
//								jasperPrint);
//						exporter.setParameter(
//								JRExporterParameter.CHARACTER_ENCODING, "utf-8");
//						exporter.setParameter(JRExporterParameter.OUTPUT_FILE,
//								file);
//
//						exporter.exportReport();
//
//						System.out.println(" exportReport done ");
//						// this.bSign.setVisible(false);
//
//						break;
//					default:
//						JRDocxExporter exporterDoc = new JRDocxExporter();
//						exporterDoc.setParameter(
//								JRExporterParameter.JASPER_PRINT, jasperPrint);
//						exporterDoc
//								.setParameter(
//										JRExporterParameter.CHARACTER_ENCODING,
//										"utf-8");
//						exporterDoc.setParameter(
//								JRExporterParameter.OUTPUT_FILE, file);
//
//						exporterDoc.exportReport();
//
//						break;
//
//					}
//
//				} catch (JRException e1) {
//					e1.printStackTrace();
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		});
//	}
//
//	private void generateReport2(Session session, List<String> reportNames,
//			String filePath, int reportType, HashMap params, File file,
//			String title) {
//		session.doWork(new Work() {
//			@SuppressWarnings({ "unchecked", "unused" })
//			public void execute(Connection connection) throws SQLException {
//				try {
//					
//					List<JasperPrint> jasperPrints= Lists.newArrayList();
//					jasperPrint = null;
//					ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//					String reportPath = classloader.getResource("../" + "doc-template").getPath();
//					
//					
//					
//					for(String reportName:reportNames ){
//						
//					File fielRe = new File(reportPath + reportName + ".jasper");
//					
//					InputStream reportStream = new FileInputStream(fielRe);
//
//						System.out.println(" Begin fillReport  ");
//						jasperPrint = JasperFillManager.fillReport(
//								reportStream, params, connection);
//						System.out.println(" END fillReport ");
//
//
//					
//
//
//					// when export to Excel
//					if (jasperPrint == null) {
//						jasperPrint
//								.setProperty(
//										"net.sf.jasperreports.export.xls.exclude.origin.band.1",
//										"pageHeader");
//						// Remove the pageFooter from all the pages
//						jasperPrint
//								.setProperty(
//										"net.sf.jasperreports.export.xls.exclude.origin.band.2",
//										"pageFooter");
//						// Remove the columnHeader from pages except starting
//						// page
//						jasperPrint
//								.setProperty(
//										"net.sf.jasperreports.export.xls.exclude.origin.keep.first.band.1",
//										"columnHeader");
//						jasperPrint
//								.setProperty(
//										"net.sf.jasperreports.export.xls.remove.empty.space.between.rows",
//										"true");
//						jasperPrint
//								.setProperty(
//										"net.sf.jasperreports.export.xls.remove.empty.space.between.columns",
//										"true");
//					}
//					
//					jasperPrints.add(jasperPrint);
//					
//					}
//					
//					try {
//						connection.close();
//					} catch (SQLException e) {
//
//						e.printStackTrace();
//						;
//					}
//					
//					connection = null;
//					String[] sheetNames={"ThongTinChung_HangHoa","DinhLuongKyThuat","DinhGia"};
//					JRXlsxExporter exporterXLS = new JRXlsxExporter();
//					exporterXLS
//							.setParameter(
//									JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
//									Boolean.TRUE);
//					exporterXLS.setParameter(
//							JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
//							Boolean.FALSE);
//					exporterXLS
//							.setParameter(JRXlsExporterParameter.OFFSET_X, 0);
//					exporterXLS
//							.setParameter(JRXlsExporterParameter.OFFSET_Y, 0);
//					exporterXLS.setParameter(
//							JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
//							Boolean.TRUE);
//					exporterXLS.setParameter(
//							JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
//							Boolean.FALSE);
//					exporterXLS.setParameter(
//							JRXlsExporterParameter.SHEET_NAMES, sheetNames);
//
//					exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
//							jasperPrints);
//					exporterXLS.setParameter(
//							JRExporterParameter.CHARACTER_ENCODING, "utf-8");
//					exporterXLS.setParameter(JRExporterParameter.OUTPUT_FILE,
//							file);
//					exporterXLS.exportReport();
//
//				} catch (JRException e1) {
//					e1.printStackTrace();
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		});
//	}
//
//
//	@Override
//	public Response signVoffice(List<CommonDTO> list) throws Exception {
//		KttsUserSession objUser=userRoleBusinessImpl.getUserSession(request);
//		
//		for(CommonDTO obj:list){
//			Session session = signVofficeBusinessImpl.gettDAO().getSessionFactory()
//					.openSession();
//			if("01".equals(obj.getType()) || "02".equals(obj.getType())){
//				obj.setOrderId(obj.getObjectId());
//			} else if("03".equals(obj.getType()) || "04".equals(obj.getType())){
//				obj.setStockTransId(obj.getObjectId());
//				
//			}
//			HashMap<String, Object> params = new HashMap<String, Object>();
//			BeanInfo info = Introspector.getBeanInfo(obj.getClass());
//			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
//				Method reader = pd.getReadMethod();
//				if (reader != null)
//					params.put(pd.getName(), reader.invoke(obj));
//			}
//			String reportName = obj.getReportName();
//			String path = System.getProperty("java.io.tmpdir");
//			String prefix = makePrefix(reportName);
//			File file = File.createTempFile(prefix, ".pdf", new File(path));
//			generateReport(session, reportName, null, Constants.PDF_REPORT,
//					params, file, reportName);
//			session.close();
//			FileAttachTranfer attach= new FileAttachTranfer();
//			if (file.exists()) {
//				byte[] arr=FileUtils.getBytes(file);
//				attach.setAttachBytes(arr);
//				attach.setFileName(reportName+".pdf");
//				attach.setFileSign(1l);
//			}
//			
//			obj.getLstFileAttach().add(attach);
//
//		}
//		String err =	signVofficeBusinessImpl.signVoffice(list,objUser);
//		return Response.ok().entity(Collections.singletonMap("error",err)).build();
//	}
//	
//	private String validateData(CommonDTO obj){
//	String	err="";
//		if(Constants.LISTREPORTNAME.contains(obj.getReportName())){
//			if(obj.getListStockId().size()>0){
//				for(Long id:obj.getListStockId()){
//					if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,id, request)){
//						err=StringUtils.isNotEmpty(err)? (err+";"+id):("Bạn không có quyền xem báo cáo tại kho "+id);
//					}
//					
//				}
//			} else if(obj.getStockId() != null) {
//				if(!VpsPermissionChecker.checkPermissionOnDomainData(Constants.OperationKey.REPORT, Constants.AdResourceKey.STOCK,obj.getStockId(), request)){
//					err=StringUtils.isNotEmpty(err)? (err+";"+obj.getStockId()):("Bạn không có quyền xem báo cáo tại kho "+obj.getStockId());
//				}
//			}
//		}
//		
//		return err;
//	}
//	
//	
//}
//
