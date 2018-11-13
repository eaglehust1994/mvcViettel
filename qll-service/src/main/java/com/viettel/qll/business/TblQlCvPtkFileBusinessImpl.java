package com.viettel.qll.business;
 
import java.util.List;
import com.viettel.qll.bo.TblQlCvPtkFileBO;
import com.viettel.qll.dao.TblQlCvPtkFileDAO;
import com.viettel.qll.dto.TblGanNhiemVuDTO;
import com.viettel.qll.dto.TblQlCvPtkDTO;
import com.viettel.qll.dto.TblQlCvPtkFileDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("tblQlCvPtkFileBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblQlCvPtkFileBusinessImpl extends BaseFWBusinessImpl<TblQlCvPtkFileDAO,TblQlCvPtkFileDTO, TblQlCvPtkFileBO> implements TblQlCvPtkFileBusiness {

    @Autowired
    private TblQlCvPtkFileDAO tblQlCvPtkFileDAO;
     
    public TblQlCvPtkFileBusinessImpl() {
        tModel = new TblQlCvPtkFileBO();
        tDAO = tblQlCvPtkFileDAO;
    }

    @Override
    public TblQlCvPtkFileDAO gettDAO() {
        return tblQlCvPtkFileDAO;
    }
	
	
	@Override
	public  DataListDTO doSearch(TblQlCvPtkFileDTO obj) {
		List<TblQlCvPtkFileDTO> ls = tblQlCvPtkFileDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}	
	
	@Override
	public long saveFile(TblQlCvPtkFileDTO obj) {
		try {
			long ids = tblQlCvPtkFileDAO.saveObject(obj.toModel());
			return ids;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
	public long deleteObj(Long id) {
		try {
			tblQlCvPtkFileDAO.deleteObj(id);
			return 1l;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0l;
	}
	
}
