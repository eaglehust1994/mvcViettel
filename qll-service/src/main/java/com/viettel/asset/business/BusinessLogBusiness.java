package com.viettel.asset.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Objects;
import com.google.gson.Gson;
import com.viettel.asset.bo.BusinessLog;
import com.viettel.asset.bo.CatAssetCode;
import com.viettel.asset.dao.BusinessLogDao;
import com.viettel.asset.dto.BusinessLogDto;
import com.viettel.asset.dto.BusinessLogSearchDto;
import com.viettel.asset.dto.CatAssetCodeHistory;
import com.viettel.ktts2.common.OrderInfo;
import com.viettel.ktts2.common.UString;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class BusinessLogBusiness {

    @Autowired
    BusinessLogDao businessLogDao;

    public void insert(BusinessLogDto model) throws Exception {
        //model.validate();
        BusinessLog entity = model.toEntity();
        businessLogDao.insert(entity);
    }

    public BusinessLog find(Long id) {
        return businessLogDao.find(id);
    }

    public List<CatAssetCodeHistory> getListCatAssetCodeHistory(
            Date lastUpdatedDate,
            Long caacLevel) {
        BusinessLogSearchDto dto = new BusinessLogSearchDto();
        dto.setDbTable(CatAssetCode.Constants.TABLE_NAME);
        dto.setFromCreatedDate(lastUpdatedDate);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAttribute(BusinessLog.Columns.CREATED_DATE);
        orderInfo.setType(OrderInfo.ASC);
        dto.setOrderInfo(orderInfo);
        List<BusinessLog> lst = businessLogDao.search(dto);
        List<CatAssetCodeHistory> lstResult = new ArrayList<>();
        Gson gson = new Gson();
        lst.forEach(x -> {
            CatAssetCode oldValue = null;
            CatAssetCode newValue = null;
            if (UString.isNotNullAndWhitespace(x.getOldValue())) {
                oldValue = gson.fromJson(x.getOldValue(), CatAssetCode.class);
            }
            if (UString.isNotNullAndWhitespace(x.getNewValue())) {
                newValue = gson.fromJson(x.getNewValue(), CatAssetCode.class);
            }
            if (caacLevel == null || (oldValue != null && Objects.equal(caacLevel, oldValue.getCaacLevel()))
                    || (newValue != null && Objects.equal(caacLevel, newValue.getCaacLevel()))) {
                CatAssetCodeHistory his = new CatAssetCodeHistory();
                String action;
                switch (x.getBulAction()) {
                    case BusinessLog.Constants.BulAction.INSERT:
                        action = BusinessLog.Constants.BulAction.INSERT;
                        break;
                    case BusinessLog.Constants.BulAction.DELETE:
                        action = BusinessLog.Constants.BulAction.DELETE;
                        break;
                    default:
                        action = BusinessLog.Constants.BulAction.UPDATE;
                        break;
                }
                his.setAction(action);
                his.setId(x.getMainId());
                his.setNewValue(newValue);
                his.setOldValue(oldValue);
                his.setUpdatedTime(x.getCreatedDate());
                lstResult.add(his);
            }

        });
        return lstResult;
    }
}
