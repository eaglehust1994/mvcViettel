package com.viettel.wms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.wms.bo.AttachmentBO;
import com.viettel.wms.dao.AttachmentDAO;
import com.viettel.wms.dto.AttachmentDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("attachmentBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AttachmentBusinessImpl extends BaseFWBusinessImpl<AttachmentDAO, AttachmentDTO, AttachmentBO>
		implements AttachmentBusiness {

	@Autowired
	private AttachmentDAO attachmentDAO;

	public AttachmentBusinessImpl() {
		tModel = new AttachmentBO();
		tDAO = attachmentDAO;
	}

	@Override
	public AttachmentDAO gettDAO() {
		return attachmentDAO;
	}

	@Override
	public long count() {
		return attachmentDAO.count("AttachmentBO", null);
	}

	public DataListDTO doSearchFile(AttachmentDTO criteria) {
		List<AttachmentDTO> ls = attachmentDAO.doSearchFile(criteria);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(criteria.getTotalRecord());
		data.setSize(criteria.getTotalRecord());
		data.setStart(1);
		return data;
	}
	
	public List<AttachmentDTO> doSearch(AttachmentDTO criteria) {
		return attachmentDAO.doSearch(criteria);
	}
	public List<AttachmentDTO> getById(AttachmentDTO criteria) {
		return attachmentDAO.getById(criteria);
	}
	public void deleteAtt(AttachmentDTO criteria){
		attachmentDAO.delete(criteria);
	}
}
