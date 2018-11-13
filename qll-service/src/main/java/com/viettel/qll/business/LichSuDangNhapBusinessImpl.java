package com.viettel.qll.business;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.qll.bo.LichSuDangNhapBO;
import com.viettel.qll.dao.LichSuDangNhapDAO;
import com.viettel.qll.dto.LichSuDangNhapDTO;
import com.viettel.qll.dto.LsThaoTacDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;


@Service("lichSuDangNhapBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LichSuDangNhapBusinessImpl extends BaseFWBusinessImpl<LichSuDangNhapDAO,LichSuDangNhapDTO, LichSuDangNhapBO> implements LichSuDangNhapBusiness {

    @Autowired
    private LichSuDangNhapDAO lichSuDangNhapDAO;
     
    public LichSuDangNhapBusinessImpl() {
        tModel = new LichSuDangNhapBO();
        tDAO = lichSuDangNhapDAO;
    }

    @Override
    public LichSuDangNhapDAO gettDAO() {
        return lichSuDangNhapDAO;
    }
    
    public Long insertLSDN(LichSuDangNhapDTO obj){
    	try {
    		long ids=save(obj);
    		return ids;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Xảy ra lỗi khi thêm mới lịch sử đăng nhập!!!");
		}
    }
    
    @Override
	public DataListDTO doSearch(LichSuDangNhapDTO obj) throws Exception {
		List<LichSuDangNhapDTO> ls = lichSuDangNhapDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
    
}
