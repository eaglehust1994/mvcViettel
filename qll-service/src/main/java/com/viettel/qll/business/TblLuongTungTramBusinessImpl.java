package com.viettel.qll.business;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.viettel.ktts2.common.UEncrypt;
import com.viettel.qll.bo.TblLuongTungTramBO;
import com.viettel.qll.dao.TblDlDauVaoDayMayDAO;
import com.viettel.qll.dao.TblLuongTungTramDAO;
import com.viettel.qll.dto.ErrExcelDTO;
import com.viettel.qll.dto.TblDlDauVaoDayMayDTO;
import com.viettel.qll.dto.TblLuongNvTramDTO;
import com.viettel.qll.dto.TblLuongTungTramDTO;
import com.viettel.qll.dto.TblPhatXuLySuCoDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("tblLuongTungTramBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblLuongTungTramBusinessImpl extends BaseFWBusinessImpl<TblLuongTungTramDAO,TblLuongTungTramDTO, TblLuongTungTramBO> implements TblLuongTungTramBusiness {
	 @Autowired
	    private TblDlDauVaoDayMayDAO tblDlDauVaoDayMayDAO;
	    static Logger LOGGER = LoggerFactory.getLogger(TblLuongTungTramDTO.class);
		List<ErrExcelDTO> lstErrExcelDto;
		@Value("${folder_upload2}")
		private String folder2Upload;
    @Autowired
    private TblLuongTungTramDAO tblLuongTungTramDAO;
     
    public TblLuongTungTramBusinessImpl() {
        tModel = new TblLuongTungTramBO();
        tDAO = tblLuongTungTramDAO;
    }

    @Override
    public TblLuongTungTramDAO gettDAO() {
        return tblLuongTungTramDAO;
    }
    @Override
    public DataListDTO doSearch(TblLuongTungTramDTO obj) {
		// TODO Auto-generated method stub
		List<TblLuongTungTramDTO> ls = tblLuongTungTramDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
    public String exportExcelGrid(TblLuongTungTramDTO lst) throws Exception {
		// TODO Auto-generated method stub
		List<TblLuongTungTramDTO> obj = tblLuongTungTramDAO.doSearch(lst);
		String startExportTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream file = new BufferedInputStream(new FileInputStream(filePath + "LuongNhanVienTram_V2.0.xlsx"));
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
			int rownum = 8;
			for (int i = 0; i < obj.size(); i++) {
				StringBuilder err = new StringBuilder();
				XSSFRow row = sheet.createRow(rownum);
				XSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(i + 1);
				cell0.setCellStyle(style);
				rownum++;

				XSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(obj.get(i).getThang());

				XSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(obj.get(i).getTinh());

				XSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(obj.get(i).getHuyen());

				XSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(obj.get(i).getMaNv());

				XSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(obj.get(i).getHoTen());

				XSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(obj.get(i).getTram());

				XSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(obj.get(i).getVungLuong());

				XSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(obj.get(i).getDiaHinh());

				XSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(obj.get(i).getLoaiTram());

				XSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(obj.get(i).getDonGia());

				XSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(obj.get(i).getKpiTram());

				XSSFCell cell12 = row.createCell(12);
				cell12.setCellValue(obj.get(i).getKiDonVi());

				XSSFCell cell13 = row.createCell(13);
				cell13.setCellValue(obj.get(i).getHeSoDieuChinh());

				XSSFCell cell14 = row.createCell(14);
				cell14.setCellValue(obj.get(i).getNgayCongTinhLuong());

				XSSFCell cell15 = row.createCell(15);
				cell15.setCellValue(obj.get(i).getNgayCongCheDo());

				XSSFCell cell16 = row.createCell(16);
				cell16.setCellValue(obj.get(i).getLuongDuyTriTungTram());

				XSSFCell cell17 = row.createCell(17);
				cell17.setCellValue(obj.get(i).getPhatLoi1());
				
				XSSFCell cell18 = row.createCell(18);
				cell18.setCellValue(obj.get(i).getPhatLoi2());
				
				XSSFCell cell19 = row.createCell(19);
				cell19.setCellValue(obj.get(i).getPhatLoi3());
				
				XSSFCell cell20 = row.createCell(20);
				cell20.setCellValue(obj.get(i).getPhatLoi4());
				
				XSSFCell cell21 = row.createCell(21);
				cell21.setCellValue(obj.get(i).getPhatLoi5());
				
				XSSFCell cell22 = row.createCell(22);
				cell22.setCellValue(obj.get(i).getPhatLoi6());
				
				XSSFCell cell23 = row.createCell(23);
				cell23.setCellValue(obj.get(i).getLuong());
				
				
				

				cell0.setCellStyle(style);
				cell1.setCellStyle(style);
				cell2.setCellStyle(style);
				cell3.setCellStyle(style);
				cell4.setCellStyle(style);
				cell5.setCellStyle(style);
				cell6.setCellStyle(style);
				cell7.setCellStyle(style);
				cell8.setCellStyle(style);
				cell9.setCellStyle(style);
				cell10.setCellStyle(style);
				cell11.setCellStyle(style);
				cell12.setCellStyle(style);
				cell13.setCellStyle(style);
				cell14.setCellStyle(style);
				cell15.setCellStyle(style);
				cell16.setCellStyle(style);
				cell17.setCellStyle(style);
				cell18.setCellStyle(style);
				cell19.setCellStyle(style);
				cell20.setCellStyle(style);
				cell21.setCellStyle(style);
				cell22.setCellStyle(style);
				cell23.setCellStyle(style);
				
				

			}
			file.close();

			File out = new File(folder2Upload + File.separatorChar + "LuongNhanVienTram_V2.0" + startExportTime + ".xlsx");

			FileOutputStream outFile = new FileOutputStream(out);
			System.out.println(out.getCanonicalPath());
			workbook.write(outFile);
			workbook.close();
			outFile.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		// String path = UEncrypt.encryptFileUploadPath("BaoCaoThucXuatTheoKy" +
		// startExportTime +".xlsx");
		return UEncrypt.encryptFileUploadPath("LuongNhanVienTram_V2.0" + startExportTime + ".xlsx");
	}
    public String tinhluong(TblLuongTungTramDTO obj) {
		// TODO Auto-generated method stub
    	List<TblLuongTungTramDTO> kq = tblLuongTungTramDAO.tinhluong(obj);
    	//kq.toArray();
    	tblLuongTungTramDAO.deleteLuongnhatram(obj.getExThang(),obj.getExNam(),obj.getTinh());
    	for (TblLuongTungTramDTO tblLuongTungTramDTO : kq) {
    		tblLuongTungTramDTO.setXoa(0l);
    		tblLuongTungTramDTO.setHoatDong(1l);
    		if(tblLuongTungTramDTO.getPhatLoi1()==null)
    		{
    			tblLuongTungTramDTO.setPhatLoi1(0f);
    		}
    		if(tblLuongTungTramDTO.getPhatLoi2()==null)
    		{
    			tblLuongTungTramDTO.setPhatLoi2(0f);
    		}
    		if(tblLuongTungTramDTO.getPhatLoi3()==null)
    		{
    			tblLuongTungTramDTO.setPhatLoi3(0f);
    		}
    		if(tblLuongTungTramDTO.getPhatLoi4()==null)
    		{
    			tblLuongTungTramDTO.setPhatLoi4(0f);
    		}
    		if(tblLuongTungTramDTO.getPhatLoi5()==null)
    		{
    			tblLuongTungTramDTO.setPhatLoi5(0f);
    		}
    		if(tblLuongTungTramDTO.getPhatLoi6()==null)
    		{
    			tblLuongTungTramDTO.setPhatLoi6(0f);
    		}
    		if(tblLuongTungTramDTO.getKiDonVi()==null)
    		{
    			tblLuongTungTramDTO.setKiDonVi(0f);
    		}
    		if(tblLuongTungTramDTO.getKpiTram()==null)
    		{
    			tblLuongTungTramDTO.setKpiTram(0f);
    		}
    		if(tblLuongTungTramDTO.getLuongDuyTriTungTram()==null)
    		{
    			Float tam =tblLuongTungTramDTO.getDonGia()*(tblLuongTungTramDTO.getKpiTram()*80+20*tblLuongTungTramDTO.getKiDonVi())/100;
    			tblLuongTungTramDTO.setLuongDuyTriTungTram(tam);
    		}
    		if(tblLuongTungTramDTO.getLuong()==null)
    		{
    			Float tam = tblLuongTungTramDTO.getLuongDuyTriTungTram()+tblLuongTungTramDTO.getPhatLoi1()+tblLuongTungTramDTO.getPhatLoi2()+tblLuongTungTramDTO.getPhatLoi3()+tblLuongTungTramDTO.getPhatLoi4()+
    					tblLuongTungTramDTO.getPhatLoi5()+tblLuongTungTramDTO.getPhatLoi6();
    			tblLuongTungTramDTO.setLuong(tam);
    		}
    		tblLuongTungTramDTO.setHeSoDieuChinh(1f);
		}
    	saveList(kq);
		return "";
	}
}
