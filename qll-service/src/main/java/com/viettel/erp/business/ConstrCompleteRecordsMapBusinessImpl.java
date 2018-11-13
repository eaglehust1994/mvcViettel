package com.viettel.erp.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.erp.bo.ConstrCompleteRecordsMapBO;
import com.viettel.erp.dao.CatFileInvoiceDAO;
import com.viettel.erp.dao.ConstrCompleteRecordsMapDAO;
import com.viettel.erp.dto.ApprovalSignManagementDTO;
import com.viettel.erp.dto.CatFileInvoiceDTO;
import com.viettel.erp.dto.ConstrCompleteRecordMapSubDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapCriteriaDTO;
import com.viettel.erp.dto.ConstrCompleteRecordsMapDTO;
import com.viettel.erp.dto.TheSignCADTO;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.service.base.business.BaseFWBusinessImpl;

@Service("constrCompleteRecordsMapBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConstrCompleteRecordsMapBusinessImpl
		extends BaseFWBusinessImpl<ConstrCompleteRecordsMapDAO, ConstrCompleteRecordsMapDTO, ConstrCompleteRecordsMapBO>
		implements ConstrCompleteRecordsMapBusiness {
	
	static Logger LOGGER = LoggerFactory.getLogger(ConstrCompleteRecordsMapBusinessImpl.class);

	@Autowired
	private ConstrCompleteRecordsMapDAO constrCompleteRecordsMapDAO;

	@Autowired
	private CatFileInvoiceDAO catFileInvoiceDAO;

	@Autowired
	ApprovalSignManagementBusinessImpl approvalSignManagementBusinessImpl;

	public ConstrCompleteRecordsMapBusinessImpl() {
		tModel = new ConstrCompleteRecordsMapBO();
		tDAO = constrCompleteRecordsMapDAO;
	}

	@Override
	public ConstrCompleteRecordsMapDAO gettDAO() {
		return constrCompleteRecordsMapDAO;
	}

	@Override
	public long count() {
		return constrCompleteRecordsMapDAO.count("ConstrCompleteRecordsMapBO", null);
	}

	public List<ConstrCompleteRecordsMapDTO> getByConstructionId(long constructionId) {
		return constrCompleteRecordsMapDAO.getByConstructionId(constructionId);
	}

	public List<ConstrCompleteRecordsMapDTO> filter(ConstrCompleteRecordsMapCriteriaDTO criteria) {
		return constrCompleteRecordsMapDAO.filter(criteria);
	}

	// ChuongNV
	@Override
	public void updateTotal(TheSignCADTO dto) {
		constrCompleteRecordsMapDAO.updateTotal(dto);
	}

	// End ChuongNV
	@Override
	public Long insert(Long constructId, String tableName, String tableId, Long tableIdValue, Long userId, String code)
			throws Exception {
		System.out.println("---------" + tableIdValue);
		ConstrCompleteRecordsMapDTO constrCompleteRecordsMap = new ConstrCompleteRecordsMapDTO();
		constrCompleteRecordsMap.setConstructionId(constructId);

		CatFileInvoiceDTO catInvoice = catFileInvoiceDAO.onlyFindByTableName(tableName);
		if (catInvoice == null) {
			throw new Exception("not-found-cat-invoice-table-name");
		}
		Long catFileInvoiceId = catInvoice.getCatFileInvoiceId();
		constrCompleteRecordsMap.setLevelOrder(1l);
		constrCompleteRecordsMap.setDataTableName(tableName);
		constrCompleteRecordsMap.setDataTableId(tableId);
		constrCompleteRecordsMap.setDataTableIdValue(tableIdValue);
		constrCompleteRecordsMap.setCreatedDate(new Date());
		constrCompleteRecordsMap.setCatFileInvoiceId(catFileInvoiceId);
		constrCompleteRecordsMap.setStatus(0l);
		constrCompleteRecordsMap.setCreatedUserId(userId);
		constrCompleteRecordsMap.setCode(code);

		return save(constrCompleteRecordsMap);
		// return
		// constrCompleteRecordsMapDAO.saveConstrCompleteRecordsMap(constrCompleteRecordsMap);
	}

	public List<ConstrCompleteRecordMapSubDTO> getListConstrCompleteRecordMapSubDTO(Long constrCompReMapId) {
		return constrCompleteRecordsMapDAO.getListConstrCompleteRecordMapSubDTO(constrCompReMapId);
	}

	@Override
	public Long check(String id) {
		return constrCompleteRecordsMapDAO.check(id);
	}

	@Override
	public Long checkUserId(String id) {
		return constrCompleteRecordsMapDAO.checkUserId(id);
	}

	@Override
	public List<Long> getApproId(Long constrCompReMapId) {
		return constrCompleteRecordsMapDAO.getApproId(constrCompReMapId);
	}

	@Override
	@Transactional
	public int updateTotalApproval(TheSignCADTO dto) {
		Long checkUserId = this.checkUserId(dto.getConstrCompReMapId());
		Long check = this.check(dto.getConstrCompReMapId());
		List<Long> arr = new ArrayList<>();
		// Kiểm tra bản ghi có phải là trình duyệt lại sau khi bị từ chối duyệt
		// hay không
		// nếu có thì trả về 1, ngược lại thì là 0
		if (check == 1l) {
			arr = this.getApproId(Long.valueOf(dto.getConstrCompReMapId()));
		}

		// Swap những employeeId nào trùng với người tạo hoặc người trình ký
		// Flag = No là không có ai trùng với người tạo hoặc người trình ký
		String[] word = dto.getStringEmployee().split(",");
		dto.setFlag("No");
		Boolean ktra = true;
		if (word.length == 0) {
			return -1;
		}
		// trường hợp có 2 người duyệt mà 1 người trùng người tạo, 1 người trùng
		// người trình duyệt thì flag = Two
		if (word.length == 2) {
			if (checkUserId == Long.parseLong(word[0]) && dto.getUserCurentId() == Long.parseLong(word[1])) {
				dto.setFlag("Two");
				ktra = false;
			} else if (checkUserId == Long.parseLong(word[1]) && dto.getUserCurentId() == Long.parseLong(word[0])) {
				dto.setFlag("Two");
				ktra = false;
			}
		}
		// chỉ có 1 người duyệt trùng với ng tạo hoặc ng trình duyệt thì flag =
		// OnePerson
		if (ktra) {
			if (word.length == 1 && checkUserId == Long.parseLong(word[0])) {
				dto.setFlag("OnePerson");
			} else if (word.length == 1 && dto.getUserCurentId() == Long.parseLong(word[0])) {
				dto.setFlag("OnePerson");
			} else {
				// Nhiều hơn 1 người duyệt thì ktra
				// Flag = one là có 1 người trùng
				// nguời tạo vs ng trình duyệt khác nhau thì ms có flag = Three
				// Flag = Three là nếu có 2 người trùng
				// Swap lại mảng employeeId
				
				
				int t = 0;
				for (int i = 0; i < word.length; i++) {
					
					// Nếu 1 ông trùng với người tạo thì swap lên đầu
					if (checkUserId == Long.parseLong(word[i])) {
						String temp;
						temp = word[i];
						String swap;
						swap = dto.getRoleId().get(i);
						// Xoa phần tử thứ i
						for (int j = i; j <= word.length - 2; j++) {
							word[j] = word[j + 1];
							dto.getRoleId().set(j, dto.getRoleId().get(j + 1));
						}
						// Them phan tu thu i vào đầu
						for (int j = word.length - 1; j > 0; j--) {
							word[j] = word[j - 1];
							dto.getRoleId().set(j, dto.getRoleId().get(j - 1));
						}
						word[0] = temp;
						dto.getRoleId().set(0, swap);
						dto.setFlag("One");
						t++;
						System.out.println(t + "a11111111111111111111111");
					}
					
					
					
					// Nếu 1 ông trùng với người trình ký thì swap lên đầu
					if (!dto.getUserCurentId().equals(checkUserId)) {
						if (dto.getUserCurentId() == Long.parseLong(word[i])) {
							String temp;
							temp = word[i];
							String swap;
							swap = dto.getRoleId().get(i);
							// Xoa phần tử thứ i
							for (int j = i; j <= word.length - 2; j++) {
								word[j] = word[j + 1];
								dto.getRoleId().set(j, dto.getRoleId().get(j + 1));
							}
							// Them phan tu thu i vào đầu
							for (int j = word.length - 1; j > 0; j--) {
								word[j] = word[j - 1];
								dto.getRoleId().set(j, dto.getRoleId().get(j - 1));
							}
							word[0] = temp;
							dto.getRoleId().set(0, swap);
							dto.setFlag("One");
							t++;
							System.out.println(t + "a2222222222222222");
						}
				
						
					}
					System.out.println(t + " a3333333333333333332");
					if (t == 2) {
						dto.setFlag("Three");
					}
				}
			}
		}
		// Update bản ghi bảng con vs bảng constr_comple_record_map
		
		System.out.println(dto.getFlag() +"--------------------------------------");
		
		this.updateTotal(dto);
		// insert hoặc update các bản ghi của approval
		
		
		for (int i = 0; i < word.length; i++) {
			
			System.out.println(word[i] + "PPPPPPPPPPPPPPP");
			
			int j = i + 1;
			ApprovalSignManagementDTO obj = new ApprovalSignManagementDTO();
			if (i != (word.length - 1)) {
				obj.setIsLast(0l);
			} else {
				obj.setIsLast(1l);
			}
			
			obj.setEmployeeId(Long.parseLong(word[i]));
			obj.setRoleid(Long.valueOf(dto.getRoleId().get(i)));
			obj.setConstrCompReMapId(Long.parseLong(dto.getConstrCompReMapId()));
			obj.setApprovalOrder(Long.valueOf(j));
			
			obj.setApprovalStatus(0l);
			if (checkUserId == Long.parseLong(word[i])) {
				obj.setApprovalStatus(1l);
				obj.setApprovalDate(new Date());
			}
			if (dto.getUserCurentId() == Long.parseLong(word[i])) {
				obj.setApprovalStatus(1l);
				obj.setApprovalDate(new Date());
			}
			
			if (check == 1l) {
				obj.setApprovalSignManaId(arr.get(i));
				approvalSignManagementBusinessImpl.update(obj);
				this.updateData(Long.valueOf(dto.getConstrCompReMapId()), dto.getFlag());
			} else {
				approvalSignManagementBusinessImpl.save(obj);
			}
			

		}
		return 1;
	}

	@Override
	public void updateData(Long constrCompReMapId, String flag) {
		constrCompleteRecordsMapDAO.updateData(constrCompReMapId, flag);
	}

	@Override
	public void choiseCertification(TheSignCADTO theSignCADTO) {
		try {
			constrCompleteRecordsMapDAO.choiseCertification(theSignCADTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
		}
	}

	public List<ConstrCompleteRecordsMapDTO> getNotify(Long userId) {
		return constrCompleteRecordsMapDAO.getNotify(userId);
	}

}
