package com.viettel.asset.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.viettel.asset.business.AuthenticateWsBusiness;
import com.viettel.asset.business.LongTermAssetBusiness;
import com.viettel.asset.dto.LongTermAssetHistoryDto;
import com.viettel.asset.dto.ResultInfo;
import com.viettel.asset.dto.UpdateDepreciationRequest;
import com.viettel.asset.dto.UpdateDepreciationResponse;
import com.viettel.asset.dto.UpdateLongTermAssetInfoRequest;
import com.viettel.asset.dto.UpdateLongTermAssetInfoResponse;
import org.apache.log4j.Logger;

@WebService(endpointInterface = "com.viettel.asset.webservice.LongTermAssetWs")
public class LongTermAssetWsImpl implements LongTermAssetWs {

    private Logger LOGGER = Logger.getLogger("LogLoi");
    @Autowired
    LongTermAssetBusiness longTermAssetBusiness;

    @Autowired
    AuthenticateWsBusiness authenticateWsBusiness;

    @Override
    public UpdateDepreciationResponse updateDepreciation(UpdateDepreciationRequest request) {
        UpdateDepreciationResponse response = new UpdateDepreciationResponse();
        try {
            authenticateWsBusiness.validateRequest(request);
            List<LongTermAssetHistoryDto> lstDto = new ArrayList<>();
            request.getLstDepreciationInfo().forEach(x -> {
                LongTermAssetHistoryDto dto = new LongTermAssetHistoryDto();
                dto.setDepreciatedMonthValue(x.getDepreciatedMonthValue());
                dto.setDepreciatedDate(x.getDepreciatedDate());
                dto.setDepreciatedMonth(x.getDepreciatedMonth());
                dto.setDepreciatedTime(x.getDepreciatedTime());
                dto.setDepreciatedValue(x.getDepreciatedValue());
                dto.setDepreciatedYear(x.getDepreciatedYear());
                dto.setLotaCode(x.getLotaCode());
                lstDto.add(dto);
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setStatus(ResultInfo.RESULT_OK);
                response.setResultInfo(resultInfo);
            });
            longTermAssetBusiness.updateDepreciationFromErp(lstDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setStatus(ResultInfo.RESULT_NOK);
            resultInfo.setMessage(e.getMessage());
            response.setResultInfo(resultInfo);
        }
        return response;
    }

    @Override
    public UpdateLongTermAssetInfoResponse updateLongTermAssetInfo(UpdateLongTermAssetInfoRequest request) {
        UpdateLongTermAssetInfoResponse response = new UpdateLongTermAssetInfoResponse();
        try {
            authenticateWsBusiness.validateRequest(request);
            longTermAssetBusiness.updateInfoFromErp(request.getLongTermAssetDto());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setStatus(ResultInfo.RESULT_NOK);
            resultInfo.setMessage(e.getMessage());
            response.setResultInfo(resultInfo);
        }
        return response;
    }

    @Override
    public UpdateLongTermAssetInfoResponse updateLongTermAssetFailFromErp(UpdateLongTermAssetInfoRequest request) {
        UpdateLongTermAssetInfoResponse response = new UpdateLongTermAssetInfoResponse();
        try {
            authenticateWsBusiness.validateRequest(request);
            longTermAssetBusiness.updateLongTermAssetFailFromErp(request.getLongTermAssetDto());
        } catch (Exception e){ 
            LOGGER.error(e.getMessage(), e);
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setStatus(ResultInfo.RESULT_NOK);
            resultInfo.setMessage(e.getMessage());
            response.setResultInfo(resultInfo);
        }
        return response;
    }
    @Override
    public  UpdateLongTermAssetInfoResponse updateLongTermAssetStatusFromErp(UpdateLongTermAssetInfoRequest request){
		UpdateLongTermAssetInfoResponse response = new UpdateLongTermAssetInfoResponse();
		try {
			authenticateWsBusiness.validateRequest(request);
			longTermAssetBusiness.updateLongTermAssetStatusFromErp(request.getLongTermAssetDto());
		} catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
			ResultInfo resultInfo = new ResultInfo();
			resultInfo.setStatus(ResultInfo.RESULT_NOK);
			resultInfo.setMessage(e.getMessage());
			response.setResultInfo(resultInfo);
		}
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setStatus(ResultInfo.RESULT_OK);		
		response.setResultInfo(resultInfo);
		return response;
		
	}

}
