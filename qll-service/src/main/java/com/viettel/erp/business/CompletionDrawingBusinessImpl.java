package com.viettel.erp.business;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.CompletionDrawingBO;
import com.viettel.erp.dao.CompletionDrawingDAO;
import com.viettel.erp.dto.CompletionDrawingDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("completionDrawingBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CompletionDrawingBusinessImpl extends BaseFWBusinessImpl<CompletionDrawingDAO,CompletionDrawingDTO, CompletionDrawingBO> implements CompletionDrawingBusiness {

    @Autowired
    private CompletionDrawingDAO completionDrawingDAO;
    

     
    public CompletionDrawingBusinessImpl() {
        tModel = new CompletionDrawingBO();
        tDAO = completionDrawingDAO;
    }

    @Override
    public CompletionDrawingDAO gettDAO() {
        return completionDrawingDAO;
    }

    @Override
    public long count() {
        return completionDrawingDAO.count("CompletionDrawingBO", null);
    }
    
    @Override
	public List<CompletionDrawingDTO> getCompletionDrawingSearch(CompletionDrawingDTO obj) {
		// TODO Auto-generated method stub
		return completionDrawingDAO.getCompletionDrawingSearch(obj);
	}


     public List<CompletionDrawingDTO> getCompletionDrawing(Long constructId) {
 		return Lists.newArrayList(Iterables.transform(completionDrawingDAO.getCompletionDrawing(constructId), arg0 -> arg0.toDTO()));
    }
     
     public Long saveTable( CompletionDrawingDTO  completionDrawing) throws Exception{
	      Long completionDrawingId = completionDrawingDAO.saveTable(completionDrawing);
	      return completionDrawingId;
}  


	@Override
	public boolean updateIsActive(List<Long> completionDrawingId) {
		// TODO Auto-generated method stub
		return completionDrawingDAO.updateIsActive(completionDrawingId);
	}

	@Override
	public List<CompletionDrawingDTO> getDrawById(List<String> completionDrawingId) {
		// TODO Auto-generated method stub
		return completionDrawingDAO.getDrawById(completionDrawingId);
	}

	@Override
	public CompletionDrawingDTO getPathById(Long completionDrawingId) {
		// TODO Auto-generated method stub
		return completionDrawingDAO.getPathById(completionDrawingId);
	}

	


    
}
