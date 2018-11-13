package com.viettel.qll.business;
 
import java.util.List;
import com.viettel.qll.bo.TblPhatDutCapBO;
import com.viettel.qll.dao.TblPhatDutCapDAO;
import com.viettel.qll.dto.TblPhatDutCapDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("tblPhatDutCapBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblPhatDutCapBusinessImpl extends BaseFWBusinessImpl<TblPhatDutCapDAO,TblPhatDutCapDTO, TblPhatDutCapBO> implements TblPhatDutCapBusiness {

    @Autowired
    private TblPhatDutCapDAO tblPhatDutCapDAO;
     
    public TblPhatDutCapBusinessImpl() {
        tModel = new TblPhatDutCapBO();
        tDAO = tblPhatDutCapDAO;
    }

    @Override
    public TblPhatDutCapDAO gettDAO() {
        return tblPhatDutCapDAO;
    }
	
	
}
