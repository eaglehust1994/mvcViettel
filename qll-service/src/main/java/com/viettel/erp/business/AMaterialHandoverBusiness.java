package com.viettel.erp.business;

public interface AMaterialHandoverBusiness {

    long count();
 
    boolean checkStatusDatabase(String amaterialHandoverId);
    
    String autoGenCode();
}
