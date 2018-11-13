package com.viettel.erp.business;
import java.util.List;


import com.viettel.erp.dto.CompletionDrawingDTO;

public interface CompletionDrawingBusiness {

    long count();
    
  //ngoccx
    List<CompletionDrawingDTO> getCompletionDrawingSearch(CompletionDrawingDTO obj);
    public boolean updateIsActive(List<Long> completionDrawingId);
    List<CompletionDrawingDTO> getDrawById(List<String> completionDrawingId);
    CompletionDrawingDTO getPathById(Long completionDrawingId);
}
