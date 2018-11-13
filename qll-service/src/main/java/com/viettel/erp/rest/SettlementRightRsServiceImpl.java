/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.viettel.erp.business.ConstrConstructionsBusinessImpl;
import com.viettel.erp.business.SettlementRightBusinessImpl;
import com.viettel.erp.dto.AMaterialHandoverDTO;
import com.viettel.erp.dto.ConstrConstructionsDTO;
import com.viettel.erp.dto.RoleCaDTO;
import com.viettel.erp.dto.SettlementRightDTO;
import com.viettel.service.base.dto.DataListDTO;

/**
 *
 * @author HungLQ9
 */
public class SettlementRightRsServiceImpl implements SettlementRightRsService {

    @Autowired
    SettlementRightBusinessImpl settlementRightBusinessImpl;
    
    @Override
	public Response deleteMultipleSettlement(List<String> listID) {
    	String result = settlementRightBusinessImpl.deleteMultipleSettlement(listID);
    	HashMap<Integer, String> hmap = new HashMap<Integer, String>();
    	hmap.put(1, result);
    	if (result == null || "".equals(result)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(hmap).build();
        }
	}
    
    @Override
	public Response getSettlementRightByConstrt(ConstrConstructionsDTO obj) {
    	List<SettlementRightDTO> ls = settlementRightBusinessImpl.getSettlementRightByConstrt(obj);
    	for (int i = 0; i < ls.size(); i++) {
    		List<RoleCaDTO> listRole = settlementRightBusinessImpl.getRoleCaByGroupSide(ls.get(i));
    		ls.get(i).setListRole(listRole);
    		
		}
         return Response.ok(ls).build();
        
	}

    @Override
    public Response getSettlementRight() {
        List<SettlementRightDTO> ls = settlementRightBusinessImpl.getAll();
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
    public Response getSettlementRightById(Long id) {
        SettlementRightDTO obj = (SettlementRightDTO) settlementRightBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateSettlementRight(SettlementRightDTO obj) {
        Long id = settlementRightBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addSettlementRight(SettlementRightDTO obj) {
    	boolean checkUnique = settlementRightBusinessImpl.checkUnique(obj);
    	if(checkUnique) {
    		Long id = settlementRightBusinessImpl.save(obj);
    		if (id == 0l) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else {
                return Response.ok(Response.Status.CREATED).build();
            }
    	}else {
    		 HashMap<Integer, String> hmap = new HashMap<Integer, String>();
    	     hmap.put(1, "TRUNG");
    	     return Response.ok(hmap).build();
    	}
         
        
    }
    
    public int addSettlementRightNew(SettlementRightDTO obj) {
    	boolean checkUnique = settlementRightBusinessImpl.checkUnique(obj);
    	if(checkUnique) {
    		/*Long id = */settlementRightBusinessImpl.save(obj);
    		return 1;
            
    	}else {
    		 return 0;
    	}
         
        
    }

    @Override
    public Response deleteSettlementRight(Long id) {
        SettlementRightDTO obj = (SettlementRightDTO) settlementRightBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            settlementRightBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response getAllAMonitorOrBInChargeByConstructId(SettlementRightDTO obj) {
		// TODO Auto-generated method stub
		List<SettlementRightDTO> ls = settlementRightBusinessImpl.getAllAMonitorOrBInChargeByConstructId(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {            
            return Response.ok(ls).build();
        }
	}
//minhpvn : lay nguoi ky quyet toan A-b Form 6
	
	@Override
	public Response getAllAMonitorOrBInChargeByConstructIdForm6(SettlementRightDTO obj) {
		// TODO Auto-generated method stub
		List<SettlementRightDTO> ls = settlementRightBusinessImpl.getAllAMonitorOrBInChargeByConstructIdForm6(obj);
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {            
            return Response.ok(ls).build();
        }
	}
	//minhpvn end
	@Override
	public Response saveSettlementRight(List<SettlementRightDTO> listSettlement) {
		boolean checkAdd = false;
		int result = 0;
		int checkunique = -1;
		if(listSettlement.size() > 0 && listSettlement.get(0).getIsMornitor() != null) {
			ConstrConstructionsDTO obj = new ConstrConstructionsDTO();
			obj.setHiringMonitorConsult(listSettlement.get(0).getIsMornitor());
			obj.setConstructId(listSettlement.get(0).getConstructId());
			settlementRightBusinessImpl.updateHiringMonitorConsult(obj);
		}
		for (SettlementRightDTO settlementRightDTO : listSettlement) {
			checkAdd = true;
			if (settlementRightDTO.getSettlementRightId() == null) {
				checkunique = addSettlementRightNew(settlementRightDTO);
				if(checkunique == 1) {
					result++;
				}
				
	        } else {
	        	settlementRightBusinessImpl.update(settlementRightDTO);
	        	result++;
	        }
		}
		
		HashMap<Integer, Integer> hm = new HashMap<>();
		if(!checkAdd && result == 0) {
			result = 1;
		}
        hm.put(1, result);
        hm.put(2, checkunique);
        if (result == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(hm).build();
        }
	}
	
}
