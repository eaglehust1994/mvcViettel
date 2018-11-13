/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.erp.business.QualityCableMeaResultBusinessImpl;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class QualityCableMeaResultRsServiceImpl implements QualityCableMeaResultRsService {

    @Autowired
    QualityCableMeaResultBusinessImpl qualityCableMeaResultBusinessImpl;

    @Override
    public Response getQualityCableMeaResult() {
        List<QualityCableMeaResultDTO> ls = qualityCableMeaResultBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            DataListDTO data = new DataListDTO();
            data.setData(ls);
            data.setTotal(ls.size());
            data.setSize(ls.size());
            data.setStart(1);
            return Response.ok(data).build();
        }
    }

    @Override
    public Response getQualityCableMeaResultById(Long id) {
        QualityCableMeaResultDTO obj = (QualityCableMeaResultDTO) qualityCableMeaResultBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateQualityCableMeaResult(QualityCableMeaResultDTO obj) {
        Long id = qualityCableMeaResultBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addQualityCableMeaResult(QualityCableMeaResultDTO obj) {
//    	QualityCableMeaResultDTO checkCode = (QualityCableMeaResultDTO) QualityCableMeaResultBusinessImpl.findByCode(obj.getQualityCableMeaReportId());
//		if (checkCode != null) {
//			return Response.status(Response.Status.CONFLICT).build();
//		}else{
        Long id = qualityCableMeaResultBusinessImpl.save(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(Response.Status.CREATED).build();
        }
//		}
    }

    @Override
    public Response deleteQualityCableMeaResult(Long id) {
        QualityCableMeaResultDTO obj = (QualityCableMeaResultDTO) qualityCableMeaResultBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            qualityCableMeaResultBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

//	@Override
//	public Response insertListQualityResult(List<QualityCableMeaResultDTO> obj) {
//		Boolean result = qualityCableMeaResultBusinessImpl.insertListQualityResult(obj);
//		if(!result){
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		}else{
//			return Response.ok(Response.Status.CREATED).build();
//		}
//	}
	
	public Response insertListQuality(List<QualityCableMeaResultDTO> obj) {
		long count = 0;
		if(obj.size() > 0){
			for(QualityCableMeaResultDTO meaResultDTO : obj){
				QualityCableMeaResultDTO dto = new QualityCableMeaResultDTO();
//				dto.setQualityCableMeaResultId(meaResultDTO.getQualityCableMeaResultId());
				dto.setObjectChecking(meaResultDTO.getObjectChecking());
				dto.setLength(meaResultDTO.getLength());
				dto.setAttenuationLength(meaResultDTO.getAttenuationLength());
				dto.setAttenuationDegree(meaResultDTO.getAttenuationDegree());
				dto.setAttenuationSum(meaResultDTO.getAttenuationSum());
				dto.setAttenuationAverage(meaResultDTO.getAttenuationAverage());
				dto.setNote(meaResultDTO.getNote());
				dto.setQualityCableMeaReportId(1l);
				
				qualityCableMeaResultBusinessImpl.save(dto);
				count+=1;
			}
		}
		if(0 == count){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}else{
			return  Response.ok(Response.Status.CREATED).build();
		}
	}

//	@Override
//	public Response insertListQualityResult(List<QualityCableMeaResultDTO> obj) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
