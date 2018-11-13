package com.viettel.asset.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.asset.business.AuthenticateWsBusiness;
import com.viettel.asset.business.BusinessLogBusiness;
import com.viettel.asset.dto.CatAssetCodeHistoryRequest;
import com.viettel.asset.dto.CatAssetCodeHistoryResponse;
import com.viettel.asset.dto.ResultInfo;
import org.apache.log4j.Logger;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Path("/service/")
public class CatAssetCodeHistoryWsRsService {
 private Logger LOGGER=Logger.getLogger(CatAssetCodeHistoryWsRsService.class);
	@Autowired
	BusinessLogBusiness businessLogBusiness;

	@Autowired
	AuthenticateWsBusiness authenticateWsBusiness;

	@POST
	@Path("/getCatAssetCodeHistory/")
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
