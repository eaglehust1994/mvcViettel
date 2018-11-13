package com.viettel.ims.business;
 
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.viettel.ims.bo.PurchaseOrderBO;
import com.viettel.ims.dao.CatPartnerDAO;
import com.viettel.ims.dao.PurchaseOrderDAO;
import com.viettel.ims.dao.SysGroupDAO;
import com.viettel.ims.dao.SysUserDAO;
import com.viettel.ims.dto.CatConstructionTypeDTO;
import com.viettel.ims.dto.CatPartnerDTO;
import com.viettel.ims.dto.CatWorkItemTypeDTO;
import com.viettel.ims.dto.PurchaseOrderDTO;
import com.viettel.ims.dto.SysGroupDTO;
import com.viettel.ims.dto.PurchaseOrderDTO;
import com.viettel.ims.dto.PurchaseOrderDTO;
import com.viettel.ims.dto.SysUserDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;


@Service("purchaseOrderBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PurchaseOrderBusinessImpl extends BaseFWBusinessImpl<PurchaseOrderDAO,PurchaseOrderDTO, PurchaseOrderBO> implements PurchaseOrderBusiness {
	static Logger LOGGER = LoggerFactory
			.getLogger(PurchaseOrderBusinessImpl.class);
	
    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;
    
    @Autowired
    private SysGroupDAO sysGroupDAO;
    
    @Autowired
    private SysUserDAO sysUserDAO;
    
    @Autowired
    private CatPartnerDAO catPartnerDAO;
    
     
    public PurchaseOrderBusinessImpl() {
        tModel = new PurchaseOrderBO();
        tDAO = purchaseOrderDAO;
    }

    @Override
    public PurchaseOrderDAO gettDAO() {
        return purchaseOrderDAO;
    }
	
	@Override
	public PurchaseOrderDTO findByCode(String value) {
		return purchaseOrderDAO.findByCode(value);
	}

	@Override
	public List<PurchaseOrderDTO> doSearch(PurchaseOrderDTO obj) {
		return purchaseOrderDAO.doSearch(obj);
	}	
	
	@Override
	public List<PurchaseOrderDTO> getForAutoComplete(PurchaseOrderDTO query) {
		return purchaseOrderDAO.getForAutoComplete(query);
	}

	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		return purchaseOrderDAO.delete(ids, tableName, tablePrimaryKey);	
	}
	

	
	public PurchaseOrderDTO getById(Long id) {
		return purchaseOrderDAO.getById(id);
	}
	
	
	@Override
	public List<PurchaseOrderDTO> importPurchaseOrder(String fileInput) throws Exception{
		List<PurchaseOrderDTO> workLst = Lists.newArrayList();
		String error = "";
		try {
			File f = new File(fileInput);
			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter formatter = new DataFormatter();
			int count = 0;

			for (Row row : sheet) {
				boolean isValid = true;
				PurchaseOrderDTO obj = new PurchaseOrderDTO();
				count++;
				if (count >= 2) {
					for (Cell cell : row) { // lấy object từ row trong file excel
						if (cell.getColumnIndex() == 1) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							obj.setCode(formatter.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 2) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							obj.setName(formatter.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 3) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							CatPartnerDTO partner = catPartnerDAO.findByCode(formatter.formatCellValue(cell));
							if(partner != null){
								obj.setCatPartnerId(partner.getCatPartnerId());
							}
						} else if (cell.getColumnIndex(	) == 4) {
							obj.setSignerPartner(formatter.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 5) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							SysGroupDTO group = sysGroupDAO.findByCode(formatter.formatCellValue(cell));
							if(group != null){
								obj.setSysGroupId(group.getSysGroupId());
							}
						} else if (cell.getColumnIndex() == 6) {
							SysUserDTO user = sysUserDAO
									.findByEmployeeCode(formatter
											.formatCellValue(cell));
							if (user != null) {
								obj.setSignerGroupId(user.getId());
								obj.setSignerGroupName(user.getFullName());
							}
						} else if (cell.getColumnIndex() == 7) {
							SimpleDateFormat fmt = new SimpleDateFormat(
									"dd/MM/yyyy");
							String dateInString = formatter
									.formatCellValue(cell);
							try {
								Date date = fmt.parse(dateInString);
								obj.setSignDate(date);

							} catch (ParseException e) {
								isValid = false;
								error = "Ngày ký không hợp lệ";
								e.printStackTrace();
							}
						} else if (cell.getColumnIndex() == 8) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							obj.setPrice(Double.parseDouble(formatter.formatCellValue(cell)));
						} else if (cell.getColumnIndex() == 9) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							obj.setExpense(formatter.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 9) {
							
							obj.setDescription(formatter.formatCellValue(cell));
						} 
					}
					if(obj.getSysGroupId() == null){
						isValid = false;
						error = "Mã đơn vị chưa nhập";
					}
					if(obj.getCode() == null){
						isValid = false;
						error = "Mã đơn hàng chưa nhập";
					}
					if(obj.getName() == null){
						isValid = false;
						error = "Tên đơn hàng chưa nhập";
					}
					if(obj.getPrice() == null){
						isValid = false;
						error ="Giá trị đơn hàng";
					}
					if(obj.getCatPartnerId() == null){
						isValid = false;
						error = "Mã đối tác chưa nhập";
					}
					if(obj.getExpense() == null){
						isValid = false;
						error = "Nguồn kinh phí chưa nhập";
					}
					if (isValid) {
						if(isExist(obj)){
							error = "Đơn hàng ở hàng thứ "+count+" đã tồn tại";
							throw new Exception(error);
						}
						workLst.add(obj);
					} else {
						workLst = null;
						error = "Thông tin ở hàng thứ "+count+" không hợp lệ " + error;
						throw new Exception(error);
					}
				}
			}

			workbook.close();
			return workLst;
			
		} catch (

		NullPointerException pointerException) {
			LOGGER.error(pointerException.getMessage(), pointerException);
			throw new Exception(error);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception(error);
		}		
	}
	/**
	 * @overview return true if string is not null and not empty
	 * @param str
	 */
	public boolean validateString(String str){
		return (str != null && str.length()>0);
	}
	
	public Boolean isExist(PurchaseOrderDTO code) {
		PurchaseOrderDTO obj = purchaseOrderDAO.findByCode(code.getCode());
		if (obj != null) {
			return true;
		} else {
			return false;
		}
	}
}
