package com.viettel.asset.webservice;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.asset.business.AuthenticateWsBusiness;
import com.viettel.asset.business.BusinessLogBusiness;
import com.viettel.asset.dto.CatAssetCodeHistoryRequest;
import com.viettel.asset.dto.CatAssetCodeHistoryResponse;
import com.viettel.asset.dto.ResultInfo;
import org.apache.log4j.Logger;


@WebService(endpointInterface = "com.viettel.asset.webservice.CatAssetCodeHistoryWs")
public class CatAssetCodeHistoryWsImpl implements CatAssetCodeHistoryWs {

    private Logger LOGGER=Logger.getLogger(CatAssetCodeHistoryWsImpl.class);
	@Autowired
	BusinessLogBusiness businessLogBusiness;
	
	@Autowired
	AuthenticateWsBusiness authenticateWsBusiness;

	@Override
	public CatAssetCodeHistoryResponse getCatAssetCodeHistory(CatAssetCodeHistoryRequest request) {
		CatAssetCodeHistoryResponse response = new CatAssetCodeHistoryResponse();
		try {
			authenticateWsBusiness.validateRequest(request);
			response.setLstCatAssetCodeHistory(businessLogBusiness
					.getListCatAssetCodeHistory(request.getLastUpdatedTime(), request.getCaacLevel()));
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setStatus(ResultInfo.RESULT_OK);
			response.setResultInfo(resultInfo);
		} catch (Exception e) {
                    LOGGER.error(e.getMessage(),e);
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			response.setResultInfo(resultInfo);
		}
		return response;
	}

}
