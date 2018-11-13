package com.viettel.erp.business;

import java.util.List;

import com.viettel.erp.dto.SceneGenerateWorkDTO;
import com.viettel.erp.dto.approDTO;

public interface SceneGenerateWorkBusiness {

    long count();
    
    //tungpv
    List<SceneGenerateWorkDTO> doSearchSceneGenerateWork(SceneGenerateWorkDTO criteria);
    List<SceneGenerateWorkDTO> getItemNameByConstrId(SceneGenerateWorkDTO criteria);
    public boolean updateIsActive(List<Long> id);
    public Long appro(approDTO obj);
    //minhpvn aproval cong trinh
    public Long approCT(approDTO obj);
    //end tungpv
}
