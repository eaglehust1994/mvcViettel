package com.viettel.erp.business;
 
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.viettel.erp.bo.AMaterialHandoverBO;
import com.viettel.erp.dao.AMaterialHandoverDAO;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.BienBanBanGiaoAcapDTO;
import com.viettel.erp.dto.WorkItemsAcceptanceDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("aMaterialHandoverBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AMaterialHandoverBusinessImpl extends BaseFWBusinessImpl<AMaterialHandoverDAO,AMaterialHandoverDTO, AMaterialHandoverBO> implements AMaterialHandoverBusiness {

    @Autowired
    private AMaterialHandoverDAO aMaterialHandoverDAO;
    

     
    public AMaterialHandoverBusinessImpl() {
        tModel = new AMaterialHandoverBO();
        tDAO = aMaterialHandoverDAO;
    }

    @Override
    public AMaterialHandoverDAO gettDAO() {
        return aMaterialHandoverDAO;
    }

    @Override
    public long count() {
        return aMaterialHandoverDAO.count("AMaterialHandoverBO", null);
    }
    
    public Long saveTable( AMaterialHandoverDTO  obj) throws Exception {
	       Long id = aMaterialHandoverDAO.saveTable(obj);
	       return id;
	}
    

    public List<AMaterialHandoverDTO> getAMaterialHandoverByCongTrinh(AMaterialHandoverDTO obj) {
		return aMaterialHandoverDAO.getAMaterialHandoverByCongTrinh(obj);
	}
	
	public String deleteAMaterialHandoverByCode(List<String> listCode) {
		return aMaterialHandoverDAO.deleteAMaterialHandoverByCode(listCode);
	}
	

	

	public Long addListAMaterial(List<AMaterialHandoverDTO> listBTVT) {
		return aMaterialHandoverDAO.addListAMaterial(listBTVT);
	}

	public String export(List<AMaterialHandoverDTO> listBTVT) {
		//return aMaterialHandoverDAO.export(listBTVT);
		return null;
	}

	public String autoGenCode() {
		return aMaterialHandoverDAO.autoGenCode();
	}

	public List<AMaterialHandoverDTO> getThoiGianBanGiao(AMaterialHandoverDTO dto) {
		return aMaterialHandoverDAO.getThoiGianBanGiao(dto);
	}


    public List<AMaterialHandoverDTO> getAmaterialhandoverforcontruction(AMaterialHandoverDTO dto) {
 		return Lists.newArrayList(Iterables.transform(aMaterialHandoverDAO.getAmaterialhandoverforcontruction(dto), arg0 -> arg0.toDTO()));
 	}

	public BienBanBanGiaoAcapDTO getDataExport(AMaterialHandoverDTO obj) {
		return aMaterialHandoverDAO.getDataExport(obj);
	}

	@Override
	public boolean checkStatusDatabase(String amaterialHandoverId) {
		return aMaterialHandoverDAO.checkStatusDatabase(amaterialHandoverId);
	}

	public String deleteForUpdateByCode(List<String> listCode) {
		return aMaterialHandoverDAO.deleteForUpdateByCode(listCode);
		
	}


    
}
