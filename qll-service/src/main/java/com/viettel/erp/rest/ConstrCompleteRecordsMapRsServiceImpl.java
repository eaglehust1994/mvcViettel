/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.erp.business.ApprovalSignManagementBusinessImpl;
import com.viettel.erp.business.ConstrCompleteRecordsMapBusinessImpl;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.ConstrCompleteRecordMapSubDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapCriteriaDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.ConstructionDto;
import com.viettel.erp.dto.TheSignCADTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import net.sf.jxls.transformer.XLSTransformer;

/**
 *
 * @author HungLQ9
 */
public class ConstrCompleteRecordsMapRsServiceImpl implements ConstrCompleteRecordsMapRsService {
	
	static Logger LOGGER = LoggerFactory.getLogger(ConstrCompleteRecordsMapRsServiceImpl.class);

	@Autowired
	ConstrCompleteRecordsMapBusinessImpl constrCompleteRecordsMapBusinessImpl;

	@Autowired
	ApprovalSignManagementBusinessImpl approvalSignManagementBusinessImpl;

	private Workbook wb;

	@Override
	public Response getConstrCompleteRecordsMap() {
		List<ConstrCompleteRecordsMapDTO> ls = constrCompleteRecordsMapBusinessImpl.getAll();
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

	// ChuongNV
	// The Sign CA
	@Override
	public Response updateTotal(TheSignCADTO dto) {
		constrCompleteRecordsMapBusinessImpl.choiseCertification(dto);
		//constrCompleteRecordsMapBusinessImpl.updateTotal(dto);
		return Response.ok(Response.Status.OK).build();
	}

	// The Approval
	@Override
	public Response updateTotalApproval(TheSignCADTO dto) {
		int a = constrCompleteRecordsMapBusinessImpl.updateTotalApproval(dto);
		if(a==1){
			return Response.ok(Response.Status.OK).build();
		}else{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// End ChuongNV
	@Override
	public Response getConstrCompleteRecordsMapById(Long id) {
		ConstrCompleteRecordsMapDTO obj = (ConstrCompleteRecordsMapDTO) constrCompleteRecordsMapBusinessImpl
				.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(obj).build();
		}
	}

	@Override
	public Response updateConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO obj) {
		Long id = constrCompleteRecordsMapBusinessImpl.update(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok().build();
		}
	}

	@Override
	public Response addConstrCompleteRecordsMap(ConstrCompleteRecordsMapDTO obj) {
		Long id = constrCompleteRecordsMapBusinessImpl.save(obj);
		if (id == 0l) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(Response.Status.CREATED).build();
		}
	}

	@Override
	public Response deleteConstrCompleteRecordsMap(Long id) {
		ConstrCompleteRecordsMapDTO obj = (ConstrCompleteRecordsMapDTO) constrCompleteRecordsMapBusinessImpl
				.getOneById(id);
		if (obj == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			constrCompleteRecordsMapBusinessImpl.delete(obj);
			return Response.ok(Response.Status.NO_CONTENT).build();
		}
	}

	@Override
	public Response getByConstructionId(Long id) {
		List<ConstrCompleteRecordsMapDTO> ls = constrCompleteRecordsMapBusinessImpl.getByConstructionId(id.longValue());
		List<ConstrCompleteRecordsMapDTO> subls = new ArrayList<ConstrCompleteRecordsMapDTO>();
		
		List<ConstrCompleteRecordMapSubDTO> listSubDto = new ArrayList<ConstrCompleteRecordMapSubDTO>();
		if(ls.size() > 0) {
			Long mapid = ls.get(0).getConstrCompReMapId();
			for (int i = 0; i < ls.size(); i++) {
				ConstrCompleteRecordMapSubDTO subdto ;
				Long mapidNext = ls.get(i).getConstrCompReMapId();
				if (!mapid.equals(mapidNext)) {
					mapid = ls.get(i).getConstrCompReMapId();

					ls.get(i-1).setConstrCompleteRecordMapSubDTO(listSubDto);
					subls.add(ls.get(i-1));
					listSubDto = new ArrayList<ConstrCompleteRecordMapSubDTO>();
				}

				subdto = addSubDto(ls.get(i));
				listSubDto.add(subdto);
				
				if (i == ls.size()-1) {
					ls.get(i).setConstrCompleteRecordMapSubDTO(listSubDto);
					subls.add(ls.get(i));
				}
			}
		}
		return Response.ok(subls).build();
	}

	private ConstrCompleteRecordMapSubDTO addSubDto(ConstrCompleteRecordsMapDTO lsi) {
		ConstrCompleteRecordMapSubDTO subdto = new ConstrCompleteRecordMapSubDTO();
		subdto.setEmployeeFullname(lsi.getEmployeeFullname());
		subdto.setSignApprovalDate(lsi.getSignApprovalDate());
		
		if("0".equals(lsi.getSignApprovalStatus())) {
			subdto.setSignApprovalStatus("Trình duyệt/ký");
		}else if("1".equals(lsi.getSignApprovalStatus())) {
			subdto.setSignApprovalStatus("Đã duyệt/ký");
		}else if("2".equals(lsi.getSignApprovalStatus())) {
			subdto.setSignApprovalStatus("Từ chối");
		}else {
			subdto.setSignApprovalStatus("Khởi tạo");
		}
		subdto.setSignComments(lsi.getSignComments());
		subdto.setRoleName(lsi.getRoleName());
		return subdto;
	}

	@Override
	public Response filter(ConstrCompleteRecordsMapCriteriaDTO criteria) {
		List<ConstrCompleteRecordsMapDTO> ls = constrCompleteRecordsMapBusinessImpl.filter(criteria);
		List<ConstrCompleteRecordsMapDTO> subls = new ArrayList<ConstrCompleteRecordsMapDTO>();
		
		List<ConstrCompleteRecordMapSubDTO> listSubDto = new ArrayList<ConstrCompleteRecordMapSubDTO>();
		if(ls.size() > 0) {
			Long mapid = ls.get(0).getConstrCompReMapId();
			for (int i = 0; i < ls.size(); i++) {
				ConstrCompleteRecordMapSubDTO subdto;
				Long mapidNext = ls.get(i).getConstrCompReMapId();
				if (!mapid.equals(mapidNext)) {
					mapid = ls.get(i).getConstrCompReMapId();

					ls.get(i-1).setConstrCompleteRecordMapSubDTO(listSubDto);
					subls.add(ls.get(i-1));
					listSubDto = new ArrayList<ConstrCompleteRecordMapSubDTO>();;
				}
				subdto = addSubDto(ls.get(i));
				listSubDto.add(subdto);
				
				if (i == ls.size()-1) {
					ls.get(i).setConstrCompleteRecordMapSubDTO(listSubDto);
					subls.add(ls.get(i));
				}

			}
		}
		return Response.ok(subls).build();
	}

	@Value("${folder_upload}")
	private String folder2Upload;
	
	@SuppressWarnings("unchecked")
	public Response exportFileExcell(List<ConstrCompleteRecordsMapDTO> obj) {
		for(int i = 0; i < obj.size(); i++) {
			obj.get(i).setStt(i+1);
			List<ConstrCompleteRecordMapSubDTO> sublist = obj.get(i).getConstrCompleteRecordMapSubDTO();
			for(int j = 0; j < sublist.size(); j++) {
				if(sublist.get(j).getEmployeeFullname() == null) {
					sublist.get(j).setEmployeeFullname("");
				}
				if(sublist.get(j).getSignApprovalDate() == null) {
					sublist.get(j).setSignApprovalDate("");
				}
				if(sublist.get(j).getSignApprovalStatus() == null) {
					sublist.get(j).setSignApprovalStatus("");
				}
				if(sublist.get(j).getSignComments() == null) {
					sublist.get(j).setSignComments("");
				}
				if(sublist.get(j).getSignEmployee() == null) {
					sublist.get(j).setSignEmployee("");
				}
			}
			
			obj.get(i).setConstrCompleteRecordMapSubDTO(sublist);
			
		}
		try {
			Map beans = new HashMap();
			beans.put("obj", obj);
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    	String filePath = classloader.getResource("../" + "doc-template").getPath(); 
	        InputStream is = new BufferedInputStream(new FileInputStream(filePath + "danhsachhoso.xls"));
	        XLSTransformer transformer = new XLSTransformer();
	        long startTime = System.nanoTime();
	        Workbook resultWorkbook = transformer.transformXLS(is, beans);
	        long endTime = System.nanoTime();
	        is.close();
	        saveWorkbook(resultWorkbook, folder2Upload +"/"+  "danhsachhoso.xls");
	        System.out.println("Stress1 XLSX time (s): " + (endTime - startTime)/1000000000);
	        String path  =   UEncrypt.encryptFileUploadPath("/danhsachhoso.xls");
			return Response.ok(Collections.singletonMap("fileName", path))
	        		.build();
			
			
//			wb = new HSSFWorkbook();
//		    Sheet sheet = wb.createSheet("sheet");
//
//		    int rownum = 1;
//		    for(int i = 0; i < obj.size(); i++) {
//		    	ConstrCompleteRecordsMapDTO dtoi = obj.get(i);
//		    	Row row = sheet.createRow(rownum++);
//		    	Cell cell0 = row.createCell(0);
//                cell0.setCellValue(i+"");
//		    	Cell cell1 = row.createCell(1);
//                cell1.setCellValue((String)obj.get(i).getInvoiceName());
//		    	Cell cell2 = row.createCell(2);
//                cell2.setCellValue((String)obj.get(i).getCode());
//                Cell cell3 = row.createCell(3);
//                cell3.setCellValue((String)obj.get(i).getLoginName());
//                Cell cell4 = row.createCell(4);
//                cell4.setCellValue((String)obj.get(i).getCreatedDateStr());
//                Cell cell5 = row.createCell(5);
//                cell5.setCellValue((String)obj.get(i).getConstrCompleteRecordMapSubDTO().get(0).getEmployeeFullname());
//                for(int j = 1; j < dtoi.getConstrCompleteRecordMapSubDTO().size(); j++) {
//                    row = sheet.createRow(rownum++);
//                    Cell cell6 = row.createCell(5);
//                    cell6.setCellValue(dtoi.getConstrCompleteRecordMapSubDTO().get(j).getEmployeeFullname());
//                }
//                
//		    }
//
//		    // Write the output to a file
//		    FileOutputStream fileOut = new FileOutputStream("c:/java/workbook.xls");
//		    wb.write(fileOut);
//		    fileOut.close();
//		    return Response.ok(Collections.singletonMap("fileName", "/"+ "workbook.xls"))
//	        		.build();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	private void saveWorkbook(Workbook resultWorkbook, String fileName) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
        resultWorkbook.write(os);
        os.flush();
        os.close();
    }

	@Override
	public Response getNotify(Long userId) {
		List<ConstrCompleteRecordsMapDTO> ls = constrCompleteRecordsMapBusinessImpl.getNotify(userId);
		return Response.ok(ls).build();
	}

}
