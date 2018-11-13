package com.viettel.qll.business;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblGanNhiemVuBO;
import com.viettel.qll.dao.TblGanNhiemVuDAO;
import com.viettel.qll.dao.TblQlCvPtkDAO;
import com.viettel.qll.dto.TblGanNhiemVuDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("tblGanNhiemVuBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblGanNhiemVuBusinessImpl extends BaseFWBusinessImpl<TblGanNhiemVuDAO,TblGanNhiemVuDTO, TblGanNhiemVuBO> implements TblGanNhiemVuBusiness {
	@Value("${folder_upload2}")
	private String folder2Upload;
	protected final Logger log = Logger.getLogger(TblGanNhiemVuBusinessImpl.class);
    @Autowired
    private TblGanNhiemVuDAO tblGanNhiemVuDAO;
    @Autowired
	private TblQlCvPtkDAO tblQlCvPtkDAO;
    
    DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
    public TblGanNhiemVuBusinessImpl() {
        tModel = new TblGanNhiemVuBO();
        tDAO = tblGanNhiemVuDAO;
    }
    
    @Override
    public TblGanNhiemVuDAO gettDAO() {
        return tblGanNhiemVuDAO;
    }
	
	



    @Override
	public DataListDTO doSearch(TblGanNhiemVuDTO obj) throws Exception {
		List<TblGanNhiemVuDTO> ls = tblGanNhiemVuDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
    
   // tìm kiếm nhân viên theo id công việc 
    @Override
	public DataListDTO getEmpByWorkId(TblGanNhiemVuDTO obj) throws Exception {
		List<TblGanNhiemVuDTO> ls = tblGanNhiemVuDAO.getEmpByWorkId(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
    
    // xóa 1 bản ghi 
    public long deleteObj(TblGanNhiemVuDTO obj) {
		try {
			tblGanNhiemVuDAO.delete(obj.toModel());
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
    // gán 
    @Override
	public long saveGanNv(TblGanNhiemVuDTO obj) {
		try {
			TblGanNhiemVuDTO objSearch = new TblGanNhiemVuDTO();
			objSearch.setIdQlCvPtk(obj.getIdQlCvPtk());
			objSearch.setTenNhiemVu(obj.getTenNhiemVu());
			List<TblGanNhiemVuDTO> lstObj = tblGanNhiemVuDAO.doSearch(objSearch);
			if(lstObj.size()==0){
				long ids = tblGanNhiemVuDAO.saveObject(obj.toModel());	
				return ids;
			}else{
				long error = 404;
				return error;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
    @Override
	public List<TblGanNhiemVuDTO> getForAutoCompleteTenCv(TblGanNhiemVuDTO obj) {
		// TODO Auto-generated method stub
		return tblGanNhiemVuDAO.getForAutoCompleteTenCv(obj);
	}
    @Override
   	public List<TblQlCvPtkDTO> getAutoMaViTriForShd(TblQlCvPtkDTO obj) {
   		// TODO Auto-generated method stub
   		return tblQlCvPtkDAO.getAutoMaViTriForShd(obj);
   	}
    @Override
	public long saveAssignListTask(TblGanNhiemVuDTO obj) {
		try {
			for(int i =0; i<obj.getListIdQlCvPtk().size();i++){
				TblQlCvPtkDTO objSearch = new TblQlCvPtkDTO();
				objSearch.setTblQlCvPtkId(obj.getListIdQlCvPtk().get(i));
				objSearch.setTrangThai("Đã gán nhân viên");
				obj.setIdQlCvPtk(obj.getListIdQlCvPtk().get(i));
				obj.setTenCongViec(obj.getListTenCongViec().get(i));
				List<TblGanNhiemVuDTO> lstObj = tblGanNhiemVuDAO.doSearch(obj);
				if(lstObj.size()==0){
					tblGanNhiemVuDAO.saveObject(obj.toModel());
					tblQlCvPtkDAO.updateStatus(objSearch);
				}
				 
			}
			
			return 1l;		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
    @Override
	public long updateGNV(TblGanNhiemVuDTO obj) {
		try {
			long ids = tblGanNhiemVuDAO.updateObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
    @Override
	public long deleteListObj(TblGanNhiemVuDTO obj) {
		List<TblGanNhiemVuDTO> lst = tblGanNhiemVuDAO.doSearch(obj);
		List<Long> lstId = new ArrayList<>();
		int size = lst.size();
		int count = 0;
		int groupBatch = 0;
		try {
			for (TblGanNhiemVuDTO obj1 : lst) {
				count++;
				groupBatch++;
				lstId.add(obj1.getGanNvId());
				if (groupBatch == 999) {
					groupBatch = 0;
					tblGanNhiemVuDAO.deleteList(lstId);
					lstId.clear();
				}
				if (groupBatch < 999 && count == size) {

					tblGanNhiemVuDAO.deleteList(lstId);

				}
			}
			return 1l;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return 0l;
	}
    
	@SuppressWarnings("deprecation")
	public String DetailNV(TblGanNhiemVuDTO obj, HttpServletRequest request)  throws Exception {
    	
    	List<TblGanNhiemVuDTO> lstEmp = tblGanNhiemVuDAO.getEmployee(obj);
    	List<TblGanNhiemVuDTO> lstObj = new ArrayList<>();
    	String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    	
    	for(int i =0;i<lstEmp.size();i++){
    		TblGanNhiemVuDTO obj1 = new TblGanNhiemVuDTO();
    		List<TblGanNhiemVuDTO> objMission = new ArrayList<>();
			
    	
    		objMission = tblGanNhiemVuDAO.getMission(lstEmp.get(i));
    		
    		//đếm nhiệm vụ hoàn thành
    		long mission1 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã nhận tại PTK")&&x.getTrangThai().equals("Hoàn thành")).collect(Collectors.counting());
    		long mission2 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã nhận QTK")&&x.getTrangThai().equals("Hoàn thành")).collect(Collectors.counting());
    		long mission3 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã thẩm định QTK")&&x.getTrangThai().equals("Hoàn thành")).collect(Collectors.counting());
    		long mission4 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã lập đề nghị")&&x.getTrangThai().equals("Hoàn thành")).collect(Collectors.counting());
    		long mission5 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã chốt với CDT")&&x.getTrangThai().equals("Hoàn thành")).collect(Collectors.counting());
    		long missionTotal = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTrangThai().equals("Hoàn thành")).collect(Collectors.counting());
    		//đếm nhiệm vụ mới tạo
    		long newMission1 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã nhận tại PTK")&&x.getTrangThai().equals("Mới tạo")).collect(Collectors.counting());
    		long newMission2 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã nhận QTK")&&x.getTrangThai().equals("Mới tạo")).collect(Collectors.counting());
    		long newMission3 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã thẩm định QTK")&&x.getTrangThai().equals("Mới tạo")).collect(Collectors.counting());
    		long newMission4 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã lập đề nghị")&&x.getTrangThai().equals("Mới tạo")).collect(Collectors.counting());
    		long newMission5 = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTenNhiemVu().equals("Đã chốt với CDT")&&x.getTrangThai().equals("Mới tạo")).collect(Collectors.counting());
    		long newMissionTotal = objMission.stream().filter(x->x.getTenNhiemVu()!=null&&x.getTrangThai().equals("Mới tạo")).collect(Collectors.counting());
    	
    		
    		obj1.setMission1(mission1);
    		obj1.setMission2(mission2);
    		obj1.setMission3(mission3);
    		obj1.setMission4(mission4);
    		obj1.setMission5(mission5);
    		obj1.setMissionTotal(missionTotal);
    		
    		obj1.setNewMission1(newMission1);
    		obj1.setNewMission2(newMission2);
    		obj1.setNewMission3(newMission3);
    		obj1.setNewMission4(newMission4);
    		obj1.setNewMission5(newMission5);
    		obj1.setNewMissionTotal(newMissionTotal);
    		
    		obj1.setFullname(lstEmp.get(i).getFullname());
    		obj1.setIdUser(lstEmp.get(i).getIdUser());
    		lstObj.add(obj1);
    	}
    	
    	
    	try{
    		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "TkDsGanNhanVien.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
			XSSFCellStyle styleNumber = workbook.createCellStyle();
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
			DataFormat format = workbook.createDataFormat();
			
			
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
			styleNumber.setDataFormat(format.getFormat("#,##0"));
			styleNumber.setBorderBottom(BorderStyle.THIN);
			styleNumber.setBorderTop(BorderStyle.THIN);
			styleNumber.setBorderRight(BorderStyle.THIN);
			styleNumber.setBorderLeft(BorderStyle.THIN);
			styleNumber.setWrapText(true);
			styleNumber.setAlignment(CellStyle.ALIGN_RIGHT);
			
			
			XSSFRow row = null;
			int rownum=3;
			for(int i=0; i< lstObj.size();i++){
				
				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;
				
				
				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(lstObj.get(i).getIdUser());
				
				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(lstObj.get(i).getFullname());
				
				XSSFCell cell3 = row.createCell(3);
				cell3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getNewMission1() != 0) {
					cell3.setCellValue(lstObj.get(i).getNewMission1());
				} else {
					cell3.setCellValue("0");
				}
				
				XSSFCell cell4 = row.createCell(4);
				cell4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getMission1() != 0) {
					cell4.setCellValue(lstObj.get(i).getMission1());
				} else {
					cell4.setCellValue("0");
				}
				
				XSSFCell cell5 = row.createCell(5);
				cell5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getNewMission2() != 0) {
					cell5.setCellValue(lstObj.get(i).getNewMission2());
				} else {
					cell5.setCellValue("0");
				}
				
				XSSFCell cell6 = row.createCell(6);
				cell6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getMission2() != 0) {
					cell6.setCellValue(lstObj.get(i).getMission2());
				} else {
					cell6.setCellValue("0");
				}
				
				XSSFCell cell7 = row.createCell(7);
				cell7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getNewMission3() != 0) {
					cell7.setCellValue(lstObj.get(i).getNewMission3());
				} else {
					cell7.setCellValue("0");
				}
				
				XSSFCell cell8 = row.createCell(8);
				cell8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getMission3() != 0) {
					cell8.setCellValue(lstObj.get(i).getMission3());
				} else {
					cell8.setCellValue("0");
				}
				
				XSSFCell cell9 = row.createCell(9);
				cell9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getNewMission4() != 0) {
					cell9.setCellValue(lstObj.get(i).getNewMission4());
				} else {
					cell9.setCellValue("0");
				}
				
				XSSFCell cell10 = row.createCell(10);
				cell10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getMission4() != 0) {
					cell10.setCellValue(lstObj.get(i).getMission4());
				} else {
					cell10.setCellValue("0");
				}
				
				XSSFCell cell11 = row.createCell(11);
				cell11.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getNewMission5() != 0) {
					cell11.setCellValue(lstObj.get(i).getNewMission5());
				} else {
					cell11.setCellValue("0");
				}
				
				XSSFCell cell12 = row.createCell(12);
				cell12.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getMission5() != 0) {
					cell12.setCellValue(lstObj.get(i).getMission5());
				} else {
					cell12.setCellValue("0");
				}
				
				XSSFCell cell13 = row.createCell(13);
				cell13.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getNewMissionTotal() != 0) {
					cell13.setCellValue(lstObj.get(i).getNewMissionTotal());
				} else {
					cell13.setCellValue("0");
				}
				
				XSSFCell cell14 = row.createCell(14);
				cell14.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (lstObj.get(i).getMissionTotal() != 0) {
					cell14.setCellValue(lstObj.get(i).getMissionTotal());
				} else {
					cell14.setCellValue("0");
				}
				
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(styleNumber);
				cell4.setCellStyle(styleNumber);
				cell5.setCellStyle(styleNumber);
				cell6.setCellStyle(styleNumber);
				cell7.setCellStyle(styleNumber);
				cell8.setCellStyle(styleNumber);
				cell9.setCellStyle(styleNumber);
				cell10.setCellStyle(styleNumber);
				cell11.setCellStyle(styleNumber);
				cell12.setCellStyle(styleNumber);
				cell13.setCellStyle(styleNumber);
				cell14.setCellStyle(styleNumber);
				
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "TkDsGanNhanVien" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
			
    		
    	}catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} 
    	
    	return UEncrypt.encryptFileUploadPath("TkDsGanNhanVien" + startExportTime + ".xlsx");
    	
    }
	
	public String getDetailInfo(TblGanNhiemVuDTO obj, HttpServletRequest request)  throws Exception {
		List<TblGanNhiemVuDTO> listObj = tblGanNhiemVuDAO.getDetailInfo(obj);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try{
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "TkDsDetailGanNhanVien.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// set style
			XSSFCellStyle style = workbook.createCellStyle();
		
			XSSFFont hSSFFont = workbook.createFont();
			hSSFFont.setFontName("Times New Roman");
			style.setFont(hSSFFont);
		
			
			
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setWrapText(true);
		
			
			
			XSSFRow row = null;
			int rownum=2;
			for(int i =0;i<listObj.size();i++){
				row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;
				
				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(listObj.get(i).getFullname());
				
				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(listObj.get(i).getTenNhiemVu());
				
				XSSFCell cell3 = row.createCell(3);
				if (listObj.get(i).getNgayGiaoNv() != null) {
					cell3.setCellValue(dt.format(listObj.get(i).getNgayGiaoNv()));
				} else {
					cell3.setCellValue("");
				}
				
				XSSFCell cell4 = row.createCell(4);
				if (listObj.get(i).getNgayHoanThanh() != null) {
					cell4.setCellValue(dt.format(listObj.get(i).getNgayHoanThanh()));
				} else {
					cell4.setCellValue("");
				}
				
				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(listObj.get(i).getMttMaViTri());
				
				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(listObj.get(i).getSoHdCdt());
				
				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(listObj.get(i).getTrangThai());
				
				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(style);
				cell7.setCellStyle(style);
			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "TkDsDetailGanNhanVien" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
		}catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} 
		
		return UEncrypt.encryptFileUploadPath("TkDsDetailGanNhanVien" + startExportTime + ".xlsx");
	}

	
}
