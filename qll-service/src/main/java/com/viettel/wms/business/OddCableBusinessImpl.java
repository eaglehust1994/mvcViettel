package com.viettel.wms.business;
 
import com.viettel.wms.bo.OddCableBO;
import com.viettel.wms.dao.OddCableDAO;
import com.viettel.wms.dto.OddCableDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("oddCableBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OddCableBusinessImpl extends BaseFWBusinessImpl<OddCableDAO,OddCableDTO, OddCableBO> implements OddCableBusiness {

    @Autowired
    private OddCableDAO oddCableDAO;
    

     
    public OddCableBusinessImpl() {
        tModel = new OddCableBO();
        tDAO = oddCableDAO;
    }

    @Override
    public OddCableDAO gettDAO() {
        return oddCableDAO;
    }

    @Override
    public long count() {
        return oddCableDAO.count("OddCableBO", null);
    }

//    Hàm tìm kiếm trong bảng OddCable -- TUANNB
	@Override
	public DataListDTO doSearch(OddCableDTO obj) {
		// TODO Auto-generated method stub
		List<OddCableDTO> ls = oddCableDAO.doSearch(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}
//	end TUANNB
	
//	Hàm check mã hàng hóa trong bảng OddCable
	public Boolean checkCode(String goodsCode, String goodsName, Long oddCableId) {
		OddCableDTO obj = oddCableDAO.getbycode(goodsCode, goodsName);

		if (oddCableId == null) {
			if (obj == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (obj == null) {
				return true;
			} else if (obj != null && obj.getOddCableId().longValue() == oddCableId) {
				return true;
			} else {
				return false;
			}
		}
	}
//    end TUANNB

    
}
