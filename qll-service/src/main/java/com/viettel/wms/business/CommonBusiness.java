package com.viettel.wms.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.ktts.vps.VpsPermissionChecker;

@Service("CommonBusiness")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CommonBusiness {

	public List<Long> getListDomainData(String OperationKey,String AdResourceKey,HttpServletRequest request){
		String domainDataList= VpsPermissionChecker.getDomainDataItemIds(OperationKey, AdResourceKey, request);
		if(!StringUtils.isNotEmpty(domainDataList)){
			return Lists.newArrayList();
		}
		List<Long> listId= Lists.newArrayList();
		String[] arr=domainDataList.split(",");
		for(int i=0; i<arr.length;i++){
			listId.add(new Long(arr[i]));
		}
		
		return listId;
		
	}
	
}


