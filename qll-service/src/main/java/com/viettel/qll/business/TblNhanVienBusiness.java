package com.viettel.qll.business;

import java.util.List;
import com.viettel.qll.dto.TblNhanVienDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 * @author hailh10
 */

public interface TblNhanVienBusiness {

	DataListDTO doSearchNhanVien(TblNhanVienDTO obj);

	//public long checkExistMaNvByMaNv(String maNv);
}
