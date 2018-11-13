package com.viettel.erp.business;
 
import com.viettel.erp.bo.CategoryAcceptanceBO;
import com.viettel.erp.dao.CategoryAcceptanceDAO;
import com.viettel.erp.dto.AbComplementWorkDTO;
import com.viettel.erp.dto.CategoryAcceptanceDTO;
import com.viettel.erp.dto.CategoryAcceptanceExtDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("categoryAcceptanceBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryAcceptanceBusinessImpl extends BaseFWBusinessImpl<CategoryAcceptanceDAO,CategoryAcceptanceDTO, CategoryAcceptanceBO> implements CategoryAcceptanceBusiness {
	private static Logger LOGGER = LoggerFactory.getLogger(CategoryAcceptanceBusinessImpl.class);
	
    @Autowired
    private CategoryAcceptanceDAO categoryAcceptanceDAO;
    

     
    public CategoryAcceptanceBusinessImpl() {
        tModel = new CategoryAcceptanceBO();
        tDAO = categoryAcceptanceDAO;
    }

    @Override
    public CategoryAcceptanceDAO gettDAO() {
        return categoryAcceptanceDAO;
    }

    @Override
    public long count() {
        return categoryAcceptanceDAO.count("CategoryAcceptanceBO", null);
    }

	@Override
	public List<CategoryAcceptanceExtDTO> getAllCategoryAcceptance() {
		// TODO Auto-generated method stub
//		LOGGER.info("Ma cong trinh: "+categoryAcceptanceDAO.getAllCategoryAcceptance().get(0).getConstructId());
		return categoryAcceptanceDAO.getAllCategoryAcceptance();
	}
	
	@Override	
	public boolean deleteCategoryAcceptanceList(List<Long> lisItemCode) {
		return categoryAcceptanceDAO.deleteCategoryAcceptanceList(lisItemCode);
	}

	public List<CategoryAcceptanceDTO> getCategoryAcceptanceById(Long constructId, Double contractId) {
		
		return categoryAcceptanceDAO.getCategoryAcceptanceById(constructId, contractId);
	}

	public CategoryAcceptanceDTO getCategoryAcceptanceByIdDetail(Long categoryAcceptanceId) {
		
		return categoryAcceptanceDAO.getCategoryAcceptanceByIdDetail(categoryAcceptanceId);
	}

	public CategoryAcceptanceDTO getAllCategoryAcceptanceExportFile(CategoryAcceptanceDTO obj) {		
		return categoryAcceptanceDAO.getAllCategoryAcceptanceExportFile(obj);
	}

	public String autoGenCode() {
		return categoryAcceptanceDAO.autoGenCode();
	}

	public Long appro(approDTO obj) {
		return categoryAcceptanceDAO.appro(obj);
	}

	public Long saveTable( CategoryAcceptanceDTO  completionDrawing) throws Exception{
        Long completionDrawingId = categoryAcceptanceDAO.saveTable(completionDrawing);
        return completionDrawingId;
	}

	

//	@Override
//	public String approvalCategoryAcceptanceList(List<Long> lisItemCode) {
//		// TODO Auto-generated method stub
//		return categoryAcceptanceDAO.approvalCategoryAcceptanceList(lisItemCode);
//	}
    
}
