package com.viettel.qll.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.viettel.qll.dto.ImportErrDTO;
import com.viettel.qll.dto.TblQltsThucXuatTheoKyDTO;
import com.viettel.qll.dto.TblTypeAPxkDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblQltsThucXuatTheoKyBusiness {

	DataListDTO doSearch(TblQltsThucXuatTheoKyDTO obj, HttpServletRequest request) throws Exception;
//	String importFile(String fileInput) throws Exception;
//	String exportExcelError(List<ImportErrDTO> obj) throws Exception ;
//	Long updateChiNhanh(TblQltsThucXuatTheoKyDTO obj) throws Exception;
//	Long updateChiNhanh(TblTypeAPxkDTO obj) throws Exception;
	DataListDTO doSearchByPXK(TblQltsThucXuatTheoKyDTO obj, HttpServletRequest request) throws Exception;
//	Long updateSLByNV(TblQltsThucXuatTheoKyDTO obj) throws Exception;
//	Long updateNhanVien(TblTypeAPxkDTO obj) throws Exception;
	Long updateChiNhanh(TblTypeAPxkDTO obj, HttpServletRequest request) throws Exception;
	Long updateSLByNV(TblQltsThucXuatTheoKyDTO obj, HttpServletRequest request) throws Exception;
	Long updateNhanVien(TblTypeAPxkDTO obj, HttpServletRequest request) throws Exception;
}
