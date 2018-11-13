package com.viettel.qll.business;

import java.util.List;
import com.viettel.qll.dto.TblDanhMucDTO;

/**
 * @author hailh10
 */

public interface TblDanhMucBusiness {

	List<TblDanhMucDTO> getDeptForAutocomplete(TblDanhMucDTO obj);

	List<TblDanhMucDTO> getDeptForAutocomplete1(TblDanhMucDTO obj);

	List<TblDanhMucDTO> getDeptForAutocomplete2(TblDanhMucDTO obj);

	List<TblDanhMucDTO> getForAutoCompleteDeptFor62DV(TblDanhMucDTO obj);

	List<TblDanhMucDTO> getDeptForAutocomplete3(TblDanhMucDTO obj);

	List<TblDanhMucDTO> getDeptForAutocomplete1Popup(TblDanhMucDTO obj);
}
