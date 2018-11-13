package com.viettel.qll.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.qll.business.TblTypeABcThucXuatBusinessImpl;

/**
 * @author hailh10
 */
 
public class TblTypeABcThucXuatRsServiceImpl implements TblTypeABcThucXuatRsService {

	protected final Logger log = Logger.getLogger(TblTypeABcThucXuatRsService.class);
	@Autowired
	TblTypeABcThucXuatBusinessImpl tblTypeABcThucXuatBusinessImpl;
	
}
