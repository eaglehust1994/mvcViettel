package com.viettel.ims.business;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.viettel.ims.bo.BiddingPackageBO;
import com.viettel.ims.constant.Constants;
import com.viettel.ims.dao.BiddingPackageDAO;
import com.viettel.ims.dto.BiddingPackageDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.ktts2.dto.KttsUserSession;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;
import com.viettel.Common.CommonBussiness.UserRoleBusinessImpl;

@Service("biddingPackageBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BiddingPackageBusinessImpl
		extends
		BaseFWBusinessImpl<BiddingPackageDAO, BiddingPackageDTO, BiddingPackageBO>
		implements BiddingPackageBusiness {
	static Logger LOGGER = LoggerFactory
			.getLogger(BiddingPackageBusinessImpl.class);
	@Value("${folder_upload}")
	private String folder2Upload;

	@Autowired
	private BiddingPackageDAO biddingPackageDAO;

	@Context
	HttpServletRequest request;
	@Autowired
	private UserRoleBusinessImpl userRoleBusinessImpl;

	public BiddingPackageBusinessImpl() {
		tModel = new BiddingPackageBO();
		tDAO = biddingPackageDAO;
	}

	@Override
	public BiddingPackageDAO gettDAO() {
		return biddingPackageDAO;
	}

	@Override
	public long getTotal() {
		return biddingPackageDAO.count("AdClientBO", null);
	}

	public DataListDTO doSearch(BiddingPackageDTO obj) {
		List<BiddingPackageDTO> ls = biddingPackageDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getPageSize());
		data.setStart(1);
		return data;
	}

	public Boolean checkCode(String code, Long appParamId) {
		BiddingPackageDTO obj = biddingPackageDAO.getByCode(code);

		if (appParamId == null) {
			if (obj == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (obj == null) {
				return true;
			} else if (obj != null && obj.getBiddingPackageId() == appParamId) {
				return true;
			} else {
				return false;
			}
		}

	}

	public Long updateAppParam(BiddingPackageDTO obj, KttsUserSession objUser)
			throws Exception {
		if (objUser.getSysUserId() != null) {
			if (!objUser.getSysUserId().equals(obj.getCreatedUserId())) {
				throw new IllegalArgumentException(
						"Người dùng hiện tại không có quyền sửa bản ghi này !");
			}
		}

		boolean check = checkCode(obj.getCode(), obj.getBiddingPackageId());
		if (!check) {
			throw new IllegalArgumentException(
					"Mã tham số và loại tham số đã đồng thời tồn tại !");
		}
		return biddingPackageDAO.updateObject(obj.toModel());
	}

	public Long createAppParam(BiddingPackageDTO obj) throws Exception {

		boolean check = checkCode(obj.getCode(), null);
		if (!check) {
			throw new IllegalArgumentException(
					"Mã tham số và loại tham số đã đồng thời tồn tại !");
		}
		return biddingPackageDAO.saveObject(obj.toModel());
	}

	public Long deleteAppParam(BiddingPackageDTO obj) {

		return biddingPackageDAO.updateObject(obj.toModel());
	}

	public DataListDTO getAllObject() {
		List<BiddingPackageDTO> ls = biddingPackageDAO.getAll();
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setStart(1);
		return data;
	}

	 public List<BiddingPackageDTO> getForAutoComplete(BiddingPackageDTO obj) {
	 return biddingPackageDAO.getForAutoComplete(obj);
	 }
	//
	// public List<AppParamDTO> getForComboBox(AppParamDTO obj) {
	// return biddingPackageDAO.getForComboBox(obj);
	// }
	// public List<AppParamDTO> getForComboBox1(AppParamDTO obj) {
	// return biddingPackageDAO.getForComboBox1(obj);
	// }

	@Override
	public List<BiddingPackageDTO> getFileDrop() {
		// Hieunn
		// get list filedrop form APP_PARAM with PAR_TYPE =
		// 'SHIPMENT_DOCUMENT_TYPE' and Status=1
		// return biddingPackageDAO.getFileDrop();
		return null;
	}

	// Khong thay AppParamRsServiceIml goi den
	// public String getCode(String tableName,String param){
	// return biddingPackageDAO.getCode(tableName, param);
	// }

	public String exportExcelTemplate(String fileName) throws Exception {
		ClassLoader classloader = Thread.currentThread()
				.getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template")
				.getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath
				+ fileName + ".xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		file.close();
		File out = new File(folder2Upload + File.separatorChar + fileName
				+ ".xlsx");

		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();

		String path = UEncrypt.encryptFileUploadPath(fileName + ".xlsx");
		return path;
	}

	@Override
	public List<BiddingPackageDTO> importBiddingPackage(String fileInput) {
		List<BiddingPackageDTO> workLst = Lists.newArrayList();
		try {
			File f = new File(fileInput);
			XSSFWorkbook workbook = new XSSFWorkbook(f);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter formatter = new DataFormatter();
			int count = 0;

			for (Row row : sheet) {
			
				boolean isValid = true;
				BiddingPackageDTO obj = new BiddingPackageDTO();
				count++;
				if (count >= 2) {
					for (Cell cell : row) { // lấy object từ row trong file excel
						if (cell.getColumnIndex() == 1) {
							boolean check = checkCode(obj.getCode(), null);
							if (!check) {
								throw new IllegalArgumentException("Gói thầu đã tồn tại !");
							}
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								throw new IllegalArgumentException("Chưa nhập mã gói thầu !");
							}
							obj.setCode(formatter.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 2) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								throw new IllegalArgumentException("Ch!");
							}
							obj.setName(formatter.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 3) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							String key = formatter.formatCellValue(cell)
									.toUpperCase();
							Long id = Constants.ProcurementForms.get(key);
							if (id != null)
								obj.setProcurementFormsId(id);
							else {
								isValid = false;
								break;
							}
						} else if (cell.getColumnIndex() == 4) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							String upperStr = formatter.formatCellValue(cell).toUpperCase();
							if (upperStr.compareToIgnoreCase(Constants.InvestmentOwnerType.DOI_TAC_NGOAI_VIETTEL_STR) == 0) {
								obj.setInvestmentOwnerType(Constants.InvestmentOwnerType.DOI_TAC_NGOAI_VIETTEL);
							} else if (upperStr.compareToIgnoreCase(Constants.InvestmentOwnerType.DOI_TAC_TRONG_VIETTEL_STR) == 0) {
								obj.setInvestmentOwnerType(Constants.InvestmentOwnerType.DOI_TAC_TRONG_VIETTEL);
							} else {
								isValid = false;
								break;
							}
						} else if (cell.getColumnIndex() == 5) {
							if(!validateString(formatter.formatCellValue(cell))){
								break;
							}
							SimpleDateFormat fmt = new SimpleDateFormat(
									"dd/MM/yyyy");
							String dateInString = formatter
									.formatCellValue(cell);
							try {
								Date date = fmt.parse(dateInString);
								obj.setSignDate(date);

							} catch (ParseException e) {
								isValid = false;
								e.printStackTrace();
							}
						} else if (cell.getColumnIndex() == 6) {
							if(!validateString(formatter.formatCellValue(cell))){
								isValid = false;
								break;
							}
							try {
								Long price = Long.parseLong(formatter
										.formatCellValue(cell));
								obj.setPrice(price);
							} catch (Exception e) {
								isValid = false;
							}
						} else if (cell.getColumnIndex() == 7) {
							obj.setContent(formatter.formatCellValue(cell));
						}

					}
					if (isValid) {
						workLst.add(obj);
					} else {
						workLst = null;
						break;
					}

				}

			}

			workbook.close();

		} catch (

		NullPointerException pointerException) {
			// pointerException.printStackTrace();
			LOGGER.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
		// return biddingPackageDAO.saveObject(obj.toModel());
		return workLst;
	}
	
	/**
	 * @overview return true if string is not null and not empty
	 * @param str
	 */
	public boolean validateString(String str){
		return (str != null && str.length()>0);
	}
}
