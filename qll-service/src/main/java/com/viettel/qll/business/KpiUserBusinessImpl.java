package com.viettel.qll.business;
 
import java.util.List;
import com.viettel.qll.bo.KpiUserBO;
import com.viettel.qll.dao.KpiUserDAO;
import com.viettel.qll.dto.KpiDepartmentDTO;
import com.viettel.qll.dto.KpiUserDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("kpiUserBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class KpiUserBusinessImpl extends BaseFWBusinessImpl<KpiUserDAO,KpiUserDTO, KpiUserBO> implements KpiUserBusiness {

    @Autowired
    private KpiUserDAO kpiUserDAO;
     
    public KpiUserBusinessImpl() {
        tModel = new KpiUserBO();
        tDAO = kpiUserDAO;
    }

    @Override
    public KpiUserDAO gettDAO() {
        return kpiUserDAO;
    }
	
    @Override
	public List<KpiUserDTO> checkInfoUser(KpiUserDTO obj) {
		
    
		List<KpiUserDTO> listData = kpiUserDAO.checkInfoUser(obj);
		
		return listData;
	}
}
