package com.viettel.asset.webservice;

import javax.jws.WebService;

import com.viettel.asset.dto.CatAssetCodeHistoryRequest;
import com.viettel.asset.dto.CatAssetCodeHistoryResponse;

@org.apache.cxf.feature.Features(features = "org.apache.cxf.feature.LoggingFeature")
@WebService(targetNamespace = "http://webservice.asset.viettel.com/")
public interface CatAssetCodeHistoryWs {

	CatAssetCodeHistoryResponse	getCatAssetCodeHistory(CatAssetCodeHistoryRequest request);
}
