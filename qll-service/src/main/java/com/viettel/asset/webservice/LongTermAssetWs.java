package com.viettel.asset.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.viettel.asset.dto.ResultInfo;
import com.viettel.asset.dto.UpdateDepreciationRequest;
import com.viettel.asset.dto.UpdateDepreciationResponse;
import com.viettel.asset.dto.UpdateLongTermAssetInfoRequest;
import com.viettel.asset.dto.UpdateLongTermAssetInfoResponse;

@org.apache.cxf.feature.Features(features = "org.apache.cxf.feature.LoggingFeature")
@WebService(targetNamespace = "http://webservice.asset.viettel.com/")
public interface LongTermAssetWs {

	@WebMethod(operationName = "updateDepreciationz")
	UpdateDepreciationResponse updateDepreciation(UpdateDepreciationRequest request);
	
	@WebMethod(operationName = "updateLongTermAssetInfoz")
	UpdateLongTermAssetInfoResponse updateLongTermAssetInfo(UpdateLongTermAssetInfoRequest request);

	@WebMethod(operationName = "updateLongTermAssetFailFromErp")
	public UpdateLongTermAssetInfoResponse updateLongTermAssetFailFromErp(UpdateLongTermAssetInfoRequest request);
	@WebMethod(operationName = "updateLongTermAssetStatus")
	public  UpdateLongTermAssetInfoResponse updateLongTermAssetStatusFromErp(UpdateLongTermAssetInfoRequest request);
}
