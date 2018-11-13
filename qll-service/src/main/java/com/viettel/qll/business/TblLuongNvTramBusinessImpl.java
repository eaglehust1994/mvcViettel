package com.viettel.qll.business;
 
import java.util.List;
import com.viettel.qll.bo.TblLuongNvTramBO;
import com.viettel.qll.dao.TblLuongNvTramDAO;
import com.viettel.qll.dto.TblLoiLapLaiDTO;
import com.viettel.qll.dto.TblLuongNvTramDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("tblLuongNvTramBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblLuongNvTramBusinessImpl extends BaseFWBusinessImpl<TblLuongNvTramDAO,TblLuongNvTramDTO, TblLuongNvTramBO> implements TblLuongNvTramBusiness {

    @Autowired
    private TblLuongNvTramDAO tblLuongNvTramDAO;
     
    public TblLuongNvTramBusinessImpl() {
        tModel = new TblLuongNvTramBO();
        tDAO = tblLuongNvTramDAO;
    }

    @Override
    public TblLuongNvTramDAO gettDAO() {
        return tblLuongNvTramDAO;
    }
	public DataListDTO doSearch(TblLuongNvTramDTO obj) {
		// TODO Auto-generated method stub
		List<TblLuongNvTramDTO> ls = tblLuongNvTramDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
	public String tinhluong() {
		// TODO Auto-generated method stub
		String data = tblLuongNvTramDAO.tinhluong();
		return data;
	}
	
}
