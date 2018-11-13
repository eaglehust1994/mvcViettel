package com.viettel.asset.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.asset.dao.SysGroupDao;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class SysGroupBusiness {
	@Autowired
	BusinessLogBusiness businessLogBusiness;
	@Autowired
	SysGroupDao sysGroupDao;
	
	
}
