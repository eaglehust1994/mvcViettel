package com.viettel.wms.business;
 
import com.viettel.wms.bo.OrderPatternBO;
import com.viettel.wms.dao.OrderPatternDAO;
import com.viettel.wms.dto.OrderPatternDTO;
import com.viettel.wms.dto.OrderPatternGoodsDTO;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("orderPatternBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderPatternBusinessImpl extends BaseFWBusinessImpl<OrderPatternDAO,OrderPatternDTO, OrderPatternBO> implements OrderPatternBusiness {

    @Autowired
    private OrderPatternDAO orderPatternDAO;
    
    @Autowired
    OrderPatternGoodsBusinessImpl orderPatternGoodsBusinessImpl;
    
    public OrderPatternBusinessImpl() {
        tModel = new OrderPatternBO();
        tDAO = orderPatternDAO;
    }

    @Override
    public OrderPatternDAO gettDAO() {
        return orderPatternDAO;
    }

    @Override
    public long count() {
        return orderPatternDAO.count("OrderPatternBO", null);
    }

//  Tìm kiếm dữ liệu trong bảng ORDER_PATTERN -- TUANNB
	@Override
	public DataListDTO doSearch(OrderPatternDTO obj) {
		// TODO Auto-generated method stub
		List<OrderPatternDTO> ls = orderPatternDAO.doSearch(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}
	
//	Xem chi tiết bản ghi trong bảng ORDER_PATTERN -- TUANNB
	@Override
	public DataListDTO viewDetail(OrderPatternDTO obj) {
		// TODO Auto-generated method stub
		List<OrderPatternGoodsDTO> ls = orderPatternDAO.viewDetail(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setSize(obj.getTotalRecord());
		   data.setStart(1);
		   return data;
		   
	}
	
//	Kiểm tra trùng lặp OrderPatternId -- TUANNB
	public Boolean checkCode(String name, Long orderPatternId) {
		OrderPatternDTO obj = orderPatternDAO.getbyname(name);
		if (orderPatternId == null) {
			if (obj == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (obj == null) {
				return true;
			} else if (obj != null && obj.getOrderPatternId().longValue() == orderPatternId) {
				return true;
			} else {
				return false;
			}
		}

	}
	
//	Xoá dữ liệu 2 bảng ORDER_PATTERN và ORDER_PATTERN_GOODS
	public Long remove(OrderPatternDTO obj,KttsUserSession objUser)throws Exception {
		if(objUser.getVpsUserInfo().getSysUserId()!=null){
			if(!objUser.getVpsUserInfo().getSysUserId().equals(obj.getCreatedUserId())){
				throw new  IllegalArgumentException("Người dùng hiện tại không có quyền xóa bản ghi này !");
			}
		}
		return orderPatternDAO.deleteObj(obj);
	}

//	Xoá dữ liệu chi tiết
	public Long removeDetail(OrderPatternGoodsDTO obj) {
		return orderPatternDAO.deleteDetail(obj);
	}
	
//	Thêm mới dữ liệu vào 2 bảng ORDER_PATTERN và ORDER_PATTERN_GOODS
	@Transactional
	public Long savePattern(OrderPatternDTO obj) {
		boolean check = checkCode(obj.getName(), obj.getOrderPatternId());
		if(!check){
			throw new  IllegalArgumentException("Mẫu yêu cầu đã tồn tại !");
		}
		Long ids = save(obj);
		if (ids != null) {
			for (int i = 0; i < obj.patternGoodsListDTO().size(); i++) {
				obj.patternGoodsListDTO().get(i).setOrderPatternId(ids);
				orderPatternGoodsBusinessImpl.save(obj.patternGoodsListDTO().get(i));
			}
		}
		return ids;
	}
	
//	Sửa mới dữ liệu vào 2 bảng ORDER_PATTERN và ORDER_PATTERN_GOODS
	@Transactional
	public Long updatePattern(OrderPatternDTO obj,KttsUserSession objUser) throws Exception{
		if(objUser.getVpsUserInfo().getSysUserId()!=null){
			if(!objUser.getVpsUserInfo().getSysUserId().equals(obj.getCreatedUserId())){
				throw new  IllegalArgumentException("Người dùng hiện tại không có quyền sửa bản ghi này !");
			}
		}
		
		boolean check = checkCode(obj.getName(), obj.getOrderPatternId());
		if(!check){
			throw new  IllegalArgumentException("Mẫu yêu cầu đã tồn tại !");
		}
		
		if (obj.patternGoodsListDTO() != null){
			OrderPatternDTO objDelete = new OrderPatternDTO();
			objDelete.setOrderPatternId(obj.getOrderPatternId());
			List<OrderPatternGoodsDTO> listOrderPattern = orderPatternDAO.viewDetail(objDelete);
			for (int i=0;i<listOrderPattern.size();i++){
				orderPatternGoodsBusinessImpl.delete(listOrderPattern.get(i));
			}
		}
		for (int i=0;i<obj.patternGoodsListDTO().size();i++){
			obj.patternGoodsListDTO().get(i).setOrderPatternId(obj.getOrderPatternId());
			orderPatternGoodsBusinessImpl.save(obj.patternGoodsListDTO().get(i));
		}
		
		return orderPatternDAO.updateObject(obj.toModel());
	}
}


