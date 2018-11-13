package com.viettel.qll.business;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.qll.bo.TblTypeABcThucXuatBO;
import com.viettel.qll.dao.TblTypeABcThucXuatDAO;
import com.viettel.qll.dto.TblTypeABcThucXuatDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("tblTypeABcThucXuatBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TblTypeABcThucXuatBusinessImpl extends BaseFWBusinessImpl<TblTypeABcThucXuatDAO,TblTypeABcThucXuatDTO, TblTypeABcThucXuatBO> implements TblTypeABcThucXuatBusiness {

    @Autowired
    private TblTypeABcThucXuatDAO tblTypeABcThucXuatDAO;
     
    public TblTypeABcThucXuatBusinessImpl() {
        tModel = new TblTypeABcThucXuatBO();
        tDAO = tblTypeABcThucXuatDAO;
    }

}
