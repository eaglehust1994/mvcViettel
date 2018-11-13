package com.viettel.erp.business;
 
import com.viettel.erp.bo.QualityCableMeaResultBO;
import com.viettel.erp.dao.QualityCableMeaResultDAO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("qualityCableMeaResultBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class QualityCableMeaResultBusinessImpl extends BaseFWBusinessImpl<QualityCableMeaResultDAO,QualityCableMeaResultDTO, QualityCableMeaResultBO> implements QualityCableMeaResultBusiness {

    @Autowired
    private QualityCableMeaResultDAO qualityCableMeaResultDAO;
    

     
    public QualityCableMeaResultBusinessImpl() {
        tModel = new QualityCableMeaResultBO();
        tDAO = qualityCableMeaResultDAO;
    }

    @Override
    public QualityCableMeaResultDAO gettDAO() {
        return qualityCableMeaResultDAO;
    }

    @Override
    public long count() {
        return qualityCableMeaResultDAO.count("QualityCableMeaResultBO", null);
    }
    
//    @Override
//    public Boolean insertListQualityResult(List<QualityCableMeaResultDTO> listObj){
//		ArrayList listQuality = new ArrayList<QualityCableMeaResultBO>();
//		for(int i=0;i<listObj.size();i++){
//			listQuality.add((QualityCableMeaResultBO) listObj.get(i).toModel());
//		}
//		return qualityCableMeaResultDAO.insertListQualityResult(listQuality);
//	}
    
}
