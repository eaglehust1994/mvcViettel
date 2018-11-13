package com.viettel.qll.business;
 
import java.util.List;
import com.viettel.qll.bo.TblDmMaLoiBO;
import com.viettel.qll.dao.TblDmMaLoiDAO;
import com.viettel.qll.dto.TblDmMaLoiDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("tblDmMaLoiBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblDmMaLoiBusinessImpl extends BaseFWBusinessImpl<TblDmMaLoiDAO,TblDmMaLoiDTO, TblDmMaLoiBO> implements TblDmMaLoiBusiness {

    @Autowired
    private TblDmMaLoiDAO tblDmMaLoiDAO;
     
    public TblDmMaLoiBusinessImpl() {
        tModel = new TblDmMaLoiBO();
        tDAO = tblDmMaLoiDAO;
    }

    @Override
    public TblDmMaLoiDAO gettDAO() {
        return tblDmMaLoiDAO;
    }
	
	
}
