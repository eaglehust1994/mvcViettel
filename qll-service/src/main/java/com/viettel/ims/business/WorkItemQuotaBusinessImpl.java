package com.viettel.ims.business;
 
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.google.common.collect.Lists;
import com.viettel.ims.bo.WorkItemQuotaBO;
import com.viettel.ims.constant.Constants;
import com.viettel.ims.dao.CatConstructionTypeDAO;
import com.viettel.ims.dao.CatWorkItemTypeDAO;
import com.viettel.ims.dao.SysGroupDAO;
import com.viettel.ims.dao.WorkItemQuotaDAO;
import com.viettel.ims.dto.CatConstructionTypeDTO;
import com.viettel.ims.dto.CatWorkItemTypeDTO;
import com.viettel.ims.dto.SysGroupDTO;
import com.viettel.ims.dto.WorkItemQuotaDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;


@Service("workItemQuotaBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WorkItemQuotaBusinessImpl extends BaseFWBusinessImpl<WorkItemQuotaDAO,WorkItemQuotaDTO, WorkItemQuotaBO> implements WorkItemQuotaBusiness {

	static Logger LOGGER = LoggerFactory
			.getLogger(WorkItemQuotaBusinessImpl.class);
	
    @Autowired
    private WorkItemQuotaDAO workItemQuotaDAO;
    
    @Autowired
    private CatConstructionTypeDAO catConstructionTypeDAO;
    
    @Autowired
    private SysGroupDAO sysGroupDAO;
    
    @Autowired
    private CatWorkItemTypeDAO catWorkItemTypeDAO;
    
    
     
    public WorkItemQuotaBusinessImpl() {
        tModel = new WorkItemQuotaBO();
        tDAO = workItemQuotaDAO;
    }

    @Override
    public WorkItemQuotaDAO gettDAO() {
        return workItemQuotaDAO;
    }
	
	
	public WorkItemQuotaDTO findByUniqueKey(WorkItemQuotaDTO obj) {
		return workItemQuotaDAO.findByUniqueKey(obj);
	}

	@Override
	public List<WorkItemQuotaDTO> doSearch(WorkItemQuotaDTO obj) {
		return workItemQuotaDAO.doSearch(obj);
	}	
	
	@Override
	public List<WorkItemQuotaDTO> getForAutoComplete(WorkItemQuotaDTO query) {
		return workItemQuotaDAO.getForAutoComplete(query);
	}

	public String delete(List<Long> ids, String tableName, String tablePrimaryKey) {
		return workItemQuotaDAO.delete(ids, tableName, tablePrimaryKey);	
	}

	
	public WorkItemQuotaDTO getById(Long id) {
		return workItemQuotaDAO.getById(id);
	}


	@Override
	public List<WorkItemQuotaDTO> importWorkItemQuota(String fileInput, Long quotaType) throws Exception{
		List<WorkItemQuotaDTO> workLst = Lists.newArrayList();
		String error = "";
		try {
			File f = new File(fileInput);
			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter formatter = new DataFormatter();
			int count = 0;

			for (Row row : sheet) {
				boolean isValid = true;
				WorkItemQuotaDTO obj = new WorkItemQuotaDTO();
				count++;
				if (count >= 3) {
					for (Cell cell : row) { // lấy object từ row trong file excel
						if (cell.getColumnIndex() == 1) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							SysGroupDTO group = sysGroupDAO.findByCode(formatter.formatCellValue(cell));
							if(group != null){
								obj.setSysGroupId(group.getSysGroupId());
							}
						} else if (cell.getColumnIndex() == 2) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							CatConstructionTypeDTO cstrType = catConstructionTypeDAO.findByCode(formatter.formatCellValue(cell));
							if(cstrType != null){
								obj.setCatConstructionTypeId(cstrType.getCatConstructionTypeId());
							}
						} else if (cell.getColumnIndex() == 3) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							CatWorkItemTypeDTO wItemType = catWorkItemTypeDAO.findByCode(formatter.formatCellValue(cell));
							if(wItemType != null){
								obj.setCatWorkItemTypeId(wItemType.getCatWorkItemTypeId());
							}
						} else if (cell.getColumnIndex(	) == 4) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							obj.setPrice(Double.parseDouble(formatter.formatCellValue(cell))) ;
						} else if (cell.getColumnIndex() == 5) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							if(quotaType == 2L)
								obj.setDescription(formatter.formatCellValue(cell));
							else
								obj.setWorkDay(Double.parseDouble(formatter.formatCellValue(cell)));
						} else if (cell.getColumnIndex() == 6) {
							if(quotaType == 1L)
								obj.setDescription(formatter.formatCellValue(cell));
						} 
					}
					if(obj.getSysGroupId() == null){
						isValid = false;
						error = "Mã đơn vị chưa nhập";
					}
					if(obj.getCatConstructionTypeId() == null){
						isValid = false;
						error = "Mã loại công trình chưa nhập";
					}
					if(obj.getCatWorkItemTypeId() == null){
						isValid = false;
						error = "Mã hạng mục chưa nhập";
					}
					if(obj.getPrice() == null){
						isValid = false;
						error = quotaType == 1 ?  "Đơn giá chưa nhập" : "Giá trị công trình chưa nhập";
					}
					if(obj.getWorkDay() == null && quotaType == 1L){
						isValid = false;
						error = "Ngày công chưa nhập";
					}
					if (isValid) {
						if(isExist(obj)){
							error = "Hạng mục ở hàng thứ "+count+" đã tồn tại";
							throw new Exception(error);
						}
						workLst.add(obj);
					} else {
						workLst = null;
						error = "Thông tin ở hàng thứ "+count+" không hợp lệ " + error;
						throw new Exception(error);
//						break;
					}

				}

			}

			workbook.close();
			return workLst;
			
		} catch (

		NullPointerException pointerException) {
			// pointerException.printStackTrace();
			LOGGER.error(pointerException.getMessage(), pointerException);
			throw new Exception(error);
		} catch (Exception e) {
			// e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			throw new Exception(error);
		}		
	}
	
	public Boolean isExist(WorkItemQuotaDTO code) {
		WorkItemQuotaDTO obj = workItemQuotaDAO.findByUniqueKey(code);
		if (obj != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @overview return true if string is not null and not empty
	 * @param str
	 */
	public boolean validateString(String str){
		return (str != null && str.length()>0);
	}
}
