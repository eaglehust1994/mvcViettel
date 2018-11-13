package com.viettel.qll.business;

import java.util.List;

import com.viettel.qll.dto.TblTypeAPxkDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblTypeAPxkBusiness {

	DataListDTO doSearchPXK(TblTypeAPxkDTO obj);

	List<TblTypeAPxkDTO> checkMaHdxl(TblTypeAPxkDTO obj);

	Long updateTypeA(TblTypeAPxkDTO obj, long index) throws Exception;

	Long updatePathImg(TblTypeAPxkDTO obj) throws Exception;

	List<TblTypeAPxkDTO> getForAutoCompleteTramNhan(TblTypeAPxkDTO obj);

	List<TblTypeAPxkDTO> getForAutoCompleteHDXL(TblTypeAPxkDTO obj);

	Long updateCheck(TblTypeAPxkDTO obj) throws Exception;

	Long updateTypeNV(TblTypeAPxkDTO obj) throws Exception;

}
