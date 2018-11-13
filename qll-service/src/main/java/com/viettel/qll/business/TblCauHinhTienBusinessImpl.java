package com.viettel.qll.business;
 
import java.util.List;
import com.viettel.qll.bo.TblCauHinhTienBO;
import com.viettel.qll.dao.TblCauHinhTienDAO;
import com.viettel.qll.dto.TblCauHinhTienDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("tblCauHinhTienBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblCauHinhTienBusinessImpl extends BaseFWBusinessImpl<TblCauHinhTienDAO,TblCauHinhTienDTO, TblCauHinhTienBO> implements TblCauHinhTienBusiness {

    @Autowired
    private TblCauHinhTienDAO tblCauHinhTienDAO;
     
    public TblCauHinhTienBusinessImpl() {
        tModel = new TblCauHinhTienBO();
        tDAO = tblCauHinhTienDAO;
    }

    @Override
    public TblCauHinhTienDAO gettDAO() {
        return tblCauHinhTienDAO;
    }
	
	
}
