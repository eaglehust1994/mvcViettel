package com.viettel.erp.business;

//dodt: build loi doan nay....
/*import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
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
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.viettel.erp.bo.ConstrEstimateInfoBO;
import com.viettel.erp.bo.DetailSettlementEvaluateBO;
import com.viettel.erp.bo.EstimatesDetailAnalystBO;
import com.viettel.erp.bo.EstimatesItemsChildBO;
import com.viettel.erp.bo.EstimatesWorkItemsBO;
import com.viettel.erp.dao.ConstrEstimateInfoDAO;
import com.viettel.erp.dao.DetailSettlementEvaluateDAO;
import com.viettel.erp.dao.EstimatesDetailAnalystDAO;
import com.viettel.erp.dao.EstimatesItemsChildDAO;
import com.viettel.erp.dao.EstimatesWorkItemsDAO;
import com.viettel.erp.dto.ConstrAcceptWorkListDTO;
import com.viettel.erp.dto.ConstrEstimateInfoDTO;
import com.viettel.erp.dto.DetailSettlementEvaluateDTO;
import com.viettel.erp.dto.EstimatesDetailAnalystDTO;
import com.viettel.erp.dto.EstimatesItemsChildDTO;
import com.viettel.erp.dto.EstimatesWorkItemsDTO;
import com.viettel.erp.dto.approDTO;
import com.viettel.service.base.business.BaseFWBusinessImpl;

@Service("detailSettlementEvaluateBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DetailSettlementEvaluateBusinessImpl
		extends BaseFWBusinessImpl<DetailSettlementEvaluateDAO, DetailSettlementEvaluateDTO, DetailSettlementEvaluateBO>
		implements DetailSettlementEvaluateBusiness {
	@Value("${folder_upload}")
	private String folder2Upload;
	static Logger LOGGER = LoggerFactory.getLogger(DetailSettlementEvaluateBusinessImpl.class);
	@Autowired
	private DetailSettlementEvaluateDAO detailSettlementEvaluateDAO;

	@Autowired
	private EstimatesWorkItemsDAO dao;

	@Autowired
	private ConstrEstimateInfoDAO constrEstimateInfoDAO;

	@Autowired
	private EstimatesItemsChildDAO estimatesItemsChildDAO;

	@Autowired
	private EstimatesDetailAnalystDAO estimatesDetailAnalystDAO;

	public DetailSettlementEvaluateBusinessImpl() {
		tModel = new DetailSettlementEvaluateBO();
		tDAO = detailSettlementEvaluateDAO;
	}

	@Override
	public DetailSettlementEvaluateDAO gettDAO() {
		return detailSettlementEvaluateDAO;
	}

	@Override
	public long count() {
		return detailSettlementEvaluateDAO.count("DetailSettlementEvaluateBO", null);
	}

	@Override
	public List<DetailSettlementEvaluateDTO> getAllbyConstructId(DetailSettlementEvaluateDTO dto) {
		return detailSettlementEvaluateDAO.getAllbyConstructId(dto);
	}

	@Override
	public Long addManyTable(DetailSettlementEvaluateDTO Evaluate, List<EstimatesWorkItemsDTO> listAcc,
			List<EstimatesDetailAnalystDTO> listAna) throws Exception {
		DetailSettlementEvaluateBO obj = new DetailSettlementEvaluateBO();
		obj.setCreatedEvaluatePerId(Evaluate.getCreatedEvaluatePerId());
		obj.setCheckEvaluatePerId(Evaluate.getCheckEvaluatePerId());
		obj.setSendPersonId(Evaluate.getSendPersonId());
		obj.setBRepresentativeId(Evaluate.getBRepresentativeId());
		obj.setaDirectorId(Evaluate.getaDirectorId());
		obj.setCreatedDate(Evaluate.getCreatedDate());
		obj.setCreatedUserId(Evaluate.getCreatedUserId());
		obj.setApprovalDate(Evaluate.getApprovalDate());
		obj.setStatusCa(Evaluate.getStatusCa());
		obj.setIsActive(1L);
		obj.setConstructId(Evaluate.getConstructId());
		obj.setCode(Evaluate.getCode());
		obj.setStatusCa(0L);
		obj.setDetailSettlementEvaluateId(Evaluate.getDetailSettlementEvaluateId());
		List<EstimatesDetailAnalystBO> listDetail = Lists.newArrayList();
		for (EstimatesDetailAnalystDTO Ana : listAna) {
			listDetail.add(Ana.toModel());
		}
		List<Long> listEstimatesWorkItemId = Evaluate.getListEstimatesWorkItemId();

		return detailSettlementEvaluateDAO.addManyTable(obj, listAcc, listDetail, listEstimatesWorkItemId);
		
	}

	@Override
	public boolean checkStatusDatabase(Long detailSettlementEvaluateId) {
		return detailSettlementEvaluateDAO.checkStatusDatabase(detailSettlementEvaluateId);
	}

	
	public void fail(DetailSettlementEvaluateDTO obj) throws Exception {
		 detailSettlementEvaluateDAO.fail(obj);
	}

	public static boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	@Override
	public List<EstimatesWorkItemsBO> importCT(String filename, Long constrId) throws Exception {
		List<EstimatesWorkItemsBO> parentLst = Lists.newArrayList();
		boolean isValid = false;
		boolean isColumn4 = false;
		boolean isColumn5 = false;
		boolean isColumn6 = false;
		boolean isColumn7 = false;
		try {


			FileInputStream file = new FileInputStream(new File(filename));
			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

			boolean canRead = false;
			boolean isParent = false;
			EstimatesWorkItemsBO parent = null;
			EstimatesDetailAnalystBO child = null;



			DataFormatter formatter = new DataFormatter();
			Long type = null;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					if (cell.getColumnIndex() == 0 && cell.getCellType() == Cell.CELL_TYPE_STRING
							&& "STT".equals(cell.getStringCellValue())) {
						canRead = true;
						break;
					}
					if (!isRowEmpty(row)) {
						if (canRead) {
							String cellVal = formatter.formatCellValue(cell);
							switch (cell.getColumnIndex()) {
							case 0:// STT
								if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
									parent = new EstimatesWorkItemsBO();
									isParent = true;
									child = null;
								} else {
									isParent = false;
								}
								break;
							case 1:// Mã số
								if (cell.getCellType() == Cell.CELL_TYPE_STRING
										&& ("VL".equals(cell.getStringCellValue()) // a.)//Vậtliệu
												|| "NC".equals(cell.getStringCellValue()) // b.)Nhâncông
												|| "M".equals(cell.getStringCellValue())) /*
																							 * c.)
																							 * Máythicông
																							 */) {
									isValid = true;
									isParent = false;
								}

								if (isParent) {
									parent.setWorkItemCode(cellVal);
								} else {
									child = new EstimatesDetailAnalystBO();
									child.setCostIngredientCode(cellVal);
									child.setProgressType(4l);

									switch (cell.getStringCellValue()) {
									case "VL":
										type = 1l;
										child.setType(1l);
										break;
									case "NC":
										type = 2l;
										child.setType(2l);
										break;
									case "M":
										type = 3l;
										child.setType(3l);
										break;
									case "T":
									case "C":
									case "TL":
									case "G":
									case "GTGT":
									case "Gxdcpt":
									case "Gxdnt":
									case "Gxd":
										type = 4l;
										child.setType(4l);
										break;
									 default:
											break;
									}
								   

									child.setType(type);
								}

								break;
							case 2:// Thành phần hao phí
								if (isParent ) {								
								parent.setWorkItemName(cellVal != null ? cellVal: null);
								} else {
									if(child !=null){
										child.setCostIngredientName(cellVal);
									}
								}
								break;
							case 3:// Đơn vị
								if (isParent) {
									parent.setUnit(cellVal != null ? cellVal : null);
								} else {
									if(child !=null){
									child.setUnit(cellVal != null ? cellVal : null);
									}
								}
								break;
							case 4:// Định mức
								if (!isParent) {

									if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
											|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
										CellValue cellValue = evaluator.evaluate(cell);
										if (cellValue != null) {
											if(child !=null){
											child.setNormIndex(cellValue.getNumberValue());
											}
										}
									}

								}
								isColumn4 = true;
								break;
							case 5:// Đơn giá
								if (!isParent) {

									if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
											|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
										CellValue cellValue = evaluator.evaluate(cell);
										if (cellValue != null && child!=null) {
											child.setUnitPrice(cellValue.getNumberValue());
										}
									}

								}
								isColumn5 = true;
								break;
							case 6:// Hệ số
								if (!isParent) {

									if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
											|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
										CellValue cellValue = evaluator.evaluate(cell);
										if (cellValue != null && child!=null) {
											child.setCoefficient(cellValue.getNumberValue());
										}
									}

								}
								isColumn6 = true;
								break;
							case 7:// Thành tiền
								if (!isParent) {
									CellValue cellValue = evaluator.evaluate(cell);
									if (cellValue != null && child!=null) {
										child.setTotalMoney(cellValue.getNumberValue());
									}
									if (cell.getCellType() == Cell.CELL_TYPE_FORMULA && child!=null) {
										child.setTotalMoneyFormula(cell.getCellFormula());
									}
								}
								isColumn7 = true;
								break;

							default:
								break;
							}
						}
					}
				}
				if (isParent) {
					parentLst.add(parent);
				}
				if (child != null && parent != null) {
					parent.getEstimatesDetailAnalysts().add(child);
				}
			}

			workbook.close();
			file.close();
			
		} catch (NullPointerException nullPointerException) {
			LOGGER.error(nullPointerException.getMessage(), nullPointerException);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	
		if (!isValid) {
			throw new IllegalArgumentException("Không có đủ những đầu mục (Vật liệu, nhân công, máy thi công)");
		}

		else if (!(isColumn4 && isColumn5 && isColumn6 && isColumn7)) {
			throw new IllegalArgumentException("Dữ liệu không đầy đủ!");
		}
		if(parentLst.size()==0){
			throw new IllegalArgumentException("Không có công việc phù hợp với những công việc trên lưới dữ liệu");
		}
		return parentLst;
	}

	public List<ConstrAcceptWorkListDTO> importKL(String filename) throws Exception {

		List<ConstrAcceptWorkListDTO> workLst = Lists.newArrayList();
		boolean check = true;
		try {
			FileInputStream file = new FileInputStream(new File(filename));

			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			boolean canRead = false;
			DataFormatter formatter = new DataFormatter();
			ConstrAcceptWorkListDTO dto = null;
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				if (!isRowEmpty(row)) {
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 0 && cell.getCellType() == Cell.CELL_TYPE_STRING) {
							canRead = true;
							break;
						}

						if (canRead) {
							String cellValStr = formatter.formatCellValue(cell);
							switch (cell.getColumnIndex()) {
							case 0:
								dto = new ConstrAcceptWorkListDTO();
								break;
							case 1:
								if (null != cellValStr && dto != null) {
									dto.setWorkItemCode(cellValStr);
								}
								break;
							case 2:
								if (null != cellValStr && dto != null) {
									dto.setWorkItemName(cellValStr);
								}
								break;
							case 3:
								if (null != cellValStr && dto != null) {
									dto.setUnit(cellValStr);
								}
								break;
							case 4:
								// if(null != cellValStr){
								// dto.setWorkAmount(new
								// Long(cell.getNumericCellValue()));
								// }
								break;
							case 5:
								if (null != cellValStr && cell.getCellType() == Cell.CELL_TYPE_NUMERIC && dto != null) {
									dto.setExecuteQuantity(cell.getNumericCellValue());
								} else {
									check = false;
								}
								break;
							case 6:

								if (null != cellValStr && cell.getCellType() == Cell.CELL_TYPE_NUMERIC && dto != null) {
									dto.setEvaluateQuantity(cell.getNumericCellValue());
								} else {
									check = false;
								}
								break;
							case 7:
								// if(null != cellValStr){
								// dto.setComments(cellValStr);
								// }
								if (null != cellValStr && dto != null) {
									dto.setInstanceDrawCode(cellValStr);
								}
								break;
							case 8:
								if (null != cellValStr && dto != null) {
									dto.setEvaluateComments(cellValStr);
								} else {
									if(dto != null){
									dto.setEvaluateComments("");
								    }
								}
								break;
							default:
								break;
							}
						}
					}
				}
				if (dto != null) {
					workLst.add(dto);
				}
			}

			workbook.close();
			file.close();

			
		} catch (NullPointerException pointerException) {
			//pointerException.printStackTrace();
			LOGGER.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
		if(workLst.size()==0){
			throw new IllegalArgumentException("Không có công việc phù hợp với những công việc trên lưới dữ liệu");
		}
		

		if (!check) {			
		throw new IllegalArgumentException("Dữ liệu mục khối lượng rỗng hoặc không đúng định dạng!");			
		}else{
			return workLst;
		}
		
	}

	@Override
	public Long appro(approDTO obj) {
		return detailSettlementEvaluateDAO.appro(obj);
	}

	@Override
	public void deleteDetailSettlementEvaluate(Long id) throws Exception {
		EstimatesWorkItemsDTO obj = new EstimatesWorkItemsDTO();
		obj.setConstructionId(id);
		List<EstimatesWorkItemsDTO> inside = dao.getAllEstimatesWorkInsideContractForEvaluate(obj);
		List<EstimatesWorkItemsDTO> outside = dao.getAllEstimatesWorkOutsideContractForEvaluate(obj);
		inside.addAll(outside);
		detailSettlementEvaluateDAO.deleteDetailSettlementEvaluate(id, inside);
	}

	@Override
	public List<EstimatesWorkItemsDTO> exPortfull(Long id,String code) {
		List<EstimatesWorkItemsDTO> ListExport = new ArrayList<EstimatesWorkItemsDTO>();
		List<EstimatesWorkItemsDTO> WorkItem = dao.exPortfull(id,code);

		EstimatesWorkItemsDTO ab = new EstimatesWorkItemsDTO();
		for (Iterator<EstimatesWorkItemsDTO> interator = WorkItem.iterator(); interator.hasNext();) {
			EstimatesWorkItemsDTO wi = interator.next();

			if (ab.getEstimatesWorkItemId() == null) {
				ab = new EstimatesWorkItemsDTO();
				ab.setWorkItemCode(wi.getWorkItemCode() == null ? null : wi.getWorkItemCode());
				ab.setWorkItemName(wi.getWorkItemName() == null ? null : wi.getWorkItemName());
				ab.setEvaluateUnitPrice(wi.getEvaluateUnitPrice() == null ? null : wi.getEvaluateUnitPrice());
				ab.setEvaluateQuantity(wi.getEvaluateQuantity() == null ? null : wi.getEvaluateQuantity());
				ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId() == null ? null : wi.getEstimatesWorkItemId());
				ab.setType(wi.getType() == null ? null : wi.getType());
				ab.setUnit(wi.getUnit() == null ? null : wi.getUnit());
				ab.setWorkAmount(wi.getWorkAmount() == null ? null : wi.getWorkAmount());
				ab.setUnitPrice(wi.getUnitPrice() == null ? null : wi.getUnitPrice());
				ab.setExecuteQuantity(wi.getExecuteQuantity() == null ? null : wi.getExecuteQuantity());
				ab.setSettleUnitPrice(wi.getSettleUnitPrice() == null ? null : wi.getSettleUnitPrice());
				ab.setEstimatesItemChildId(wi.getEstimatesItemChildId() == null ? null : wi.getEstimatesItemChildId());
				ab.setItemName(wi.getItemName() == null ? null : wi.getItemName());
				ab.setItemsCode(wi.getItemsCode() == null ? null : wi.getItemsCode());
				ListExport.add(ab);

			}
			if (ab.getEstimatesWorkItemId().compareTo(wi.getEstimatesWorkItemId()) != 0) {
				ab = new EstimatesWorkItemsDTO();
				ab.setWorkItemCode(wi.getWorkItemCode() == null ? null : wi.getWorkItemCode());
				ab.setWorkItemName(wi.getWorkItemName() == null ? null : wi.getWorkItemName());
				ab.setEvaluateUnitPrice(wi.getEvaluateUnitPrice() == null ? null : wi.getEvaluateUnitPrice());
				ab.setEvaluateQuantity(wi.getEvaluateQuantity() == null ? null : wi.getEvaluateQuantity());
				ab.setEstimatesWorkItemId(wi.getEstimatesWorkItemId() == null ? null : wi.getEstimatesWorkItemId());
				ab.setType(wi.getType() == null ? null : wi.getType());
				ab.setUnit(wi.getUnit() == null ? null : wi.getUnit());
				ab.setWorkAmount(wi.getWorkAmount() == null ? null : wi.getWorkAmount());
				ab.setUnitPrice(wi.getUnitPrice() == null ? null : wi.getUnitPrice());
				ab.setExecuteQuantity(wi.getExecuteQuantity() == null ? null : wi.getExecuteQuantity());
				ab.setSettleUnitPrice(wi.getSettleUnitPrice() == null ? null : wi.getSettleUnitPrice());
				ab.setEstimatesItemChildId(wi.getEstimatesItemChildId() == null ? null : wi.getEstimatesItemChildId());
				ab.setItemName(wi.getItemName() == null ? null : wi.getItemName());
				ab.setItemsCode(wi.getItemsCode() == null ? null : wi.getItemsCode());
				ListExport.add(ab);
			}
			if (ab.getEstimatesWorkItemId().compareTo(wi.getEstimatesWorkItemId()) == 0) {
				EstimatesDetailAnalystDTO Ana = new EstimatesDetailAnalystDTO();
				Ana.setEstimatesWorkItemId(wi.getEstimatesWorkItemId());
				Ana.setType(wi.getType() == null ? null : wi.getType());
				Ana.setUnit(wi.getUnit4() == null ? null : wi.getUnit4());
				Ana.setUnitPrice(wi.getUnitPrice4() == null ? null : wi.getUnitPrice4().doubleValue());
				Ana.setNormIndex(wi.getNormIndexCT() == null ? null : wi.getNormIndexCT());
				Ana.setTotalMoney(wi.getTotalMoney() == null ? null : wi.getTotalMoney().doubleValue());
				Ana.setTotalMoneyFormula(wi.getTotalMoneyFormula() == null ? null : wi.getTotalMoneyFormula());
				Ana.setCostIngredientCode(wi.getCostIngredientCode() == null ? null : wi.getCostIngredientCode());
				Ana.setCostIngredientName(wi.getCostIngredientName() == null ? null : wi.getCostIngredientName());
				Ana.setTotalMoneyFormula(wi.getTotalMoneyFormula() == null ? null : wi.getTotalMoneyFormula());
				Ana.setCoefficient(wi.getCoefficient() == null ? null : wi.getCoefficient());

				ab.getEstimatesDetailAnalysts().add(Ana);
			}
		}

		return ListExport;
	}

	@Override
	public boolean deleteFromRecomap(long id) {
		detailSettlementEvaluateDAO.deleteFromRecomap(id);
		return false;
	}

	@Transactional
	public void importCTHD(List<EstimatesWorkItemsBO> list, Long constrId) throws Exception {
		long count = 0l;
		EstimatesWorkItemsDTO esdto = new EstimatesWorkItemsDTO();
		esdto.setConstructionId(constrId);
		List<EstimatesWorkItemsBO> listWork = dao.getWorkItem(esdto);

		for (EstimatesWorkItemsBO work : listWork) {
			for (EstimatesWorkItemsBO bo : list) {
				
				if(work.getWorkItemName()!= null && work.getWorkItemCode()!=null){
					
					System.out.println(bo.getWorkItemName() +"=================");
				if (bo.getWorkItemCode().equals(work.getWorkItemCode())
						&& bo.getWorkItemName().equals(work.getWorkItemName())) {
					bo.setEstimatesWorkItemId(work.getEstimatesWorkItemId());
					for (EstimatesDetailAnalystBO ana : bo.getEstimatesDetailAnalysts()) {
						if ("Gxd".equals(ana.getCostIngredientCode())) {
							work.setUnitPrice(ana.getTotalMoney().doubleValue());
						}
					}

					count++;
				}
				}
			}
		}

		if (count != 0l) {
			for (EstimatesWorkItemsBO work : listWork) {
				dao.updateObject(work);
			}
			for (EstimatesWorkItemsBO bo : list) {
				EstimatesDetailAnalystDTO estimatesDetailAnalystDTO = new EstimatesDetailAnalystDTO();
				estimatesDetailAnalystDTO.setEstimatesWorkItemId(bo.getEstimatesWorkItemId());
				List<EstimatesDetailAnalystBO> d = estimatesDetailAnalystDAO.getEstimatesDetailAnalystLst(estimatesDetailAnalystDTO);

				for (EstimatesDetailAnalystBO ana : bo.getEstimatesDetailAnalysts()) {
					EstimatesDetailAnalystDTO dto = ana.toDTO();
					dto.setEstimatesWorkItemId(bo.getEstimatesWorkItemId());
					dto.setProgressType(2L);
					if (d.size() > 0) {
						throw new IllegalArgumentException("chiết tính đã được import 1 lần, không thể import thêm");
					} else {
						estimatesDetailAnalystDAO.saveObject(dto.toModel());
					}

				}
			}
		} else {
			throw new IllegalArgumentException("Không có chiết tính nào phù hợp với các công việc trong hợp đồng!");
		}

	}

	@Transactional
	public List<EstimatesItemsChildDTO> getdataCVHD(String filename, Long constrId) {

		List<EstimatesItemsChildDTO> workLst = Lists.newArrayList();
		HashMap hm = new HashMap();

		try {
			FileInputStream file = new FileInputStream(new File(filename));
            System.out.println(filename +" ------------");
			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			boolean canRead = false;
			DataFormatter formatter = new DataFormatter();
			EstimatesWorkItemsDTO dto = null;
			EstimatesItemsChildDTO childdto = null;
			boolean check = true;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					if (cell.getColumnIndex() == 0 && cell.getCellType() == Cell.CELL_TYPE_STRING) {
						canRead = true;
						break;
					}

					if (canRead) {
						String cellValStr = formatter.formatCellValue(cell);
						switch (cell.getColumnIndex()) {
						case 0:
							dto = new EstimatesWorkItemsDTO();
							childdto = new EstimatesItemsChildDTO();
							break;
						case 1:
							if (null != cellValStr && childdto!=null) {
								childdto.setItemsCode(cellValStr);
							}
							break;
						case 2:
							if (null != cellValStr && childdto!=null) {
								childdto.setItemName(cellValStr);
							}
							break;

						case 3:
							if (null != cellValStr && dto!=null) {
								dto.setWorkItemCode(cellValStr);
							}
							break;
						case 4:
							if (null != cellValStr && dto!=null) {
								dto.setWorkItemName(cellValStr);
							}
							break;
						case 5:
							if (null != cellValStr && dto!=null) {
								dto.setUnit(cellValStr);
							}
							break;
						case 6:
							if (null != cellValStr && dto!=null) {
								
							
								dto.setWorkAmount((double)cell.getNumericCellValue());
							}
							break;
						case 7:
							if (null != cellValStr) {
								if ("Trong_HD".equals(cellValStr) && dto!=null) {
									dto.setType(1l);
								} else if ("Ngoai_HD".equals(cellValStr) && dto!=null) {
									dto.setType(2l);
								}
							} else {
								check = false;
							}
							break;
						default:
							break;
						}
					}
				}

				if (childdto != null) {
					dto.setConstructionId(constrId);

					if (hm.containsKey(childdto.getItemsCode())) {
						for (EstimatesItemsChildDTO estimatesItemsChildDTO : workLst) {
							if (estimatesItemsChildDTO.getItemsCode().equals(childdto.getItemsCode())) {
								estimatesItemsChildDTO.getEstimatesWorkItemsDTO().add(dto);
							}
						}
					} else {
						hm.put(childdto.getItemsCode(), childdto.getItemsCode());
						childdto.getEstimatesWorkItemsDTO().add(dto);
						workLst.add(childdto);
					}

				}
			}

			workbook.close();
			file.close();

			if (!check) {
				throw new IllegalArgumentException("Dữ liệu mục khối lượng rỗng hoặc không đúng định dạng!");
			}
		} catch (NullPointerException eNullPointerException) {
			LOGGER.error(eNullPointerException.getMessage(), eNullPointerException);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return workLst;

	}

	@Transactional
	public void importCVHD(List<EstimatesItemsChildDTO> parentLst, Long constrId) throws Exception {

		EstimatesItemsChildDTO estimatesItemsChildDTO_ = new EstimatesItemsChildDTO();
		estimatesItemsChildDTO_.setConstructId(constrId);
		List<EstimatesItemsChildBO> EstimatesItemsChildDBLst = estimatesItemsChildDAO
				.getAllandSearch(estimatesItemsChildDTO_);

		EstimatesWorkItemsDTO estimatesWorkItemsDTO_ = new EstimatesWorkItemsDTO();
		estimatesWorkItemsDTO_.setConstructId(constrId);
		List<EstimatesWorkItemsBO> EstimatesWorkItemsDBLst = dao.getAllandSearch(estimatesWorkItemsDTO_);
		ConstrEstimateInfoDTO info = new ConstrEstimateInfoDTO();
		info.setConstructionId(constrId);
		Long infoId ;
		List<ConstrEstimateInfoBO> constrEstimateInfoLst = constrEstimateInfoDAO.getAllandSearch(info);

		if (constrEstimateInfoLst.size() > 0) {
			infoId = constrEstimateInfoLst.get(0).getConstrEstimateInfoId();
		} else {
			infoId = constrEstimateInfoDAO.saveObject(info.toModel());
		}

		if (parentLst.size() > 0) {
			for (EstimatesItemsChildDTO dto : parentLst) {
				dto.setConstrEstimateInfoId(infoId);

				List<EstimatesWorkItemsDTO> estimatesWorkItemsLst = dto.getEstimatesWorkItemsDTO();
				Long idEstimatesItemsChild = null ;
				for (EstimatesItemsChildBO estimatesItemsChildBO : EstimatesItemsChildDBLst) {
					
					if(estimatesItemsChildBO.getItemsCode()!=null){
					if (estimatesItemsChildBO.getItemsCode().equals(dto.getItemsCode()) && estimatesItemsChildBO.getItemName().equals(dto.getItemName())) {
						idEstimatesItemsChild =	estimatesItemsChildBO.getEstimatesItemChildId();
						break;
					}}
				}
                if(idEstimatesItemsChild == null){
				idEstimatesItemsChild = estimatesItemsChildDAO.saveObject(dto.toModel());
                }
				for (EstimatesWorkItemsDTO estimatesWorkItemsDTO : estimatesWorkItemsLst) {

					for (EstimatesWorkItemsBO eEstimatesWorkItemsBO : EstimatesWorkItemsDBLst) {
						
						
						if(eEstimatesWorkItemsBO.getWorkItemName() != null && estimatesWorkItemsDTO.getWorkItemName() !=null){
							if (eEstimatesWorkItemsBO.getWorkItemName().equals(estimatesWorkItemsDTO.getWorkItemName())) {
								throw new IllegalArgumentException("Cồng trình có tên:"+eEstimatesWorkItemsBO.getWorkItemName()+" đã tồn tại");
							}							
						} else {
							throw new IllegalArgumentException("Tên của công việc phải tồn tại!");
						}
					}

					estimatesWorkItemsDTO.setEstimatesItemChildId(idEstimatesItemsChild);
					estimatesWorkItemsDTO.setProgressType(3l);
					estimatesWorkItemsDTO.setStatus(0l);
					estimatesWorkItemsDTO.setConstrEstimateInfoId(infoId);
					dao.saveObject(estimatesWorkItemsDTO.toModel());
				}

			}
		} else {
			throw new IllegalArgumentException("Dữ liệu rỗng hãy xem lại file import!");
		}
	}
	
	public DetailSettlementEvaluateDTO getsendPerson(Long id){
		return detailSettlementEvaluateDAO.getsendPerson(id);
	}
	
	
	public List<EstimatesItemsChildDTO> ConvertListItems(List<EstimatesWorkItemsDTO> WorkItem){
		List<EstimatesItemsChildDTO> ListExport = new ArrayList<EstimatesItemsChildDTO>();

		EstimatesItemsChildDTO ab = new EstimatesItemsChildDTO();
		EstimatesItemsChildDTO cd=new EstimatesItemsChildDTO();
		cd.setItemName("Công việc không nằm trong hạng mục");
		long count=0l;
		for (Iterator<EstimatesWorkItemsDTO> interator = WorkItem.iterator(); interator.hasNext();) {
			EstimatesWorkItemsDTO wi = interator.next();
			if(wi.getEstimatesItemChildId() == null){
				cd.getEstimatesWorkItemsDTO().add(wi);
				count++;
			} else {

				if (ab.getEstimatesItemChildId() == null) {
					ab = new EstimatesItemsChildDTO();
					ab.setItemName(wi.getItemName() == null ? null : wi.getItemName());
					ab.setItemsCode(wi.getItemsCode() == null ? null : wi.getItemsCode());
					ab.setEstimatesItemChildId(
							wi.getEstimatesItemChildId() == null ? null : wi.getEstimatesItemChildId());

					ListExport.add(ab);

				}
				if (ab.getEstimatesItemChildId().compareTo(wi.getEstimatesItemChildId()) != 0) {
					ab = new EstimatesItemsChildDTO();
					ab.setItemName(wi.getItemName() == null ? null : wi.getItemName());
					ab.setItemsCode(wi.getItemsCode() == null ? null : wi.getItemsCode());
					ab.setEstimatesItemChildId(
							wi.getEstimatesItemChildId() == null ? null : wi.getEstimatesItemChildId());

					ListExport.add(ab);
				}
				if (ab.getEstimatesItemChildId().compareTo(wi.getEstimatesItemChildId()) == 0) {

					ab.getEstimatesWorkItemsDTO().add(wi);
				}
			}
			
			
		}
		
		if(count!=0l){
			ListExport.add(cd);
		}
		return ListExport;
	}

	@Override
	public boolean updateIsActive(Long detailSettlementEvaluateId) {
		// TODO Auto-generated method stub
		detailSettlementEvaluateDAO.updateIsActive(detailSettlementEvaluateId);
		return false;
	}
}
