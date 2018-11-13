//package com.viettel.wms.business;
// 
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//
//import javax.ws.rs.core.Response;
//import javax.xml.ws.BindingProvider;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.google.common.collect.Lists;
//import com.viettel.asset.filter.session.UserSession;
//import com.viettel.erp.dao.ConstrCompleteRecordsMapDAO;
//import com.viettel.erp.dto.TheSignCADTO;
//import com.viettel.erp.utils.EncryptionUtils;
//import com.viettel.ktts2.dto.KttsUserSession;
//import com.viettel.security.PassTranformer;
//import com.viettel.service.base.business.BaseFWBusinessImpl;
//import com.viettel.util.PassWordUtil;
//import com.viettel.voffice.ws_autosign.service.FileAttachTranfer;
//import com.viettel.voffice.ws_autosign.service.KttsVofficeCommInpuParam;
//import com.viettel.voffice.ws_autosign.service.Vo2AutoSignSystemImpl;
//import com.viettel.voffice.ws_autosign.service.Vo2AutoSignSystemImplService;
//import com.viettel.voffice.ws_autosign.service.Vof2EntityUser;
//import com.viettel.wms.bo.SignVofficeBO;
//import com.viettel.wms.bo.SignVofficeDetailBO;
////import com.viettel.wms.dao.AppParamDAO;
//import com.viettel.wms.dao.SignVofficeDAO;
//import com.viettel.wms.dao.SignVofficeDetailDAO;
//import com.viettel.wms.dao.StockGoodsDAO;
//import com.viettel.wms.dao.StockGoodsSerialDAO;
//import com.viettel.wms.dao.StockGoodsTotalDAO;
//import com.viettel.wms.dao.StockTransDAO;
//import com.viettel.wms.dao.StockTransDetailDAO;
//import com.viettel.wms.dao.StockTransDetailSerialDAO;
//import com.viettel.wms.dto.AppParamDTO;
//import com.viettel.wms.dto.CommonDTO;
//import com.viettel.wms.dto.DepartmentDTO;
//import com.viettel.wms.dto.SignVofficeDTO;
//import com.viettel.wms.dto.StockDTO;
//import com.viettel.wms.dto.StockGoodsDTO;
//import com.viettel.wms.dto.StockGoodsSerialDTO;
//import com.viettel.wms.dto.StockGoodsTotalDTO;
//import com.viettel.wms.dto.StockTransDTO;
//import com.viettel.wms.dto.StockTransDetailDTO;
//import com.viettel.wms.dto.StockTransDetailSerialDTO;
//import com.viettel.wms.dto.VofficeUserDTO;
//import com.viettel.wms.rest.CommonRsServiceImpl.model;
//
//
//@Service("signVofficeBusinessImpl")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//public class SignVofficeBusinessImpl extends BaseFWBusinessImpl<SignVofficeDAO,SignVofficeDTO, SignVofficeBO> implements SignVofficeBusiness {
//	static Logger LOGGER = LoggerFactory.getLogger(ConstrCompleteRecordsMapDAO.class);
//    @Autowired
//    private SignVofficeDAO signVofficeDAO;
//    
//    @Autowired
//    private SignVofficeDetailDAO signVofficeDetailDAO;
//    
////    @Autowired
////    private AppParamDAO appParamDAO;
//    
//    @Autowired
//    private StockTransDAO stockTransDAO;
//    
//    @Autowired
//    private StockTransDetailDAO stockTransDetailDAO;
//    
//    
//    @Autowired
//    private StockTransDetailSerialDAO stockTransDetailSerialDAO;
//    
//    
//    @Autowired
//    private StockGoodsDAO stockGoodsDAO;
//    
//    
//    @Autowired
//    private StockGoodsSerialDAO stockGoodsSerialDAO;
//    
//    @Autowired
//    private StockGoodsTotalDAO stockGoodsTotalDAO;
//    
//    @Autowired
//    private StockGoodsBusinessImpl stockGoodsBusinessImpl;
//    
//    @Autowired
//    private StockTransBusinessImpl stockTransBusinessImpl;
//    
//    
//    @Autowired
//    private DepartmentBusinessImpl departmentBusinessImpl;
//    
////    @Autowired
////    private AppParamBusinessImpl appParamBusinessImpl;
//    
//    @Autowired
//    private StockBusinessImpl stockBusinessImpl;
//    
//    
//    @Value("${par_code}")
//	private String par_code;
//    
//    @Value("${par_type_ex}")
//	private String par_type_ex;
//    
//    @Value("${par_type_im}")
//	private String par_type_im;
//    
//    @Value("${ca_wsUrl}")
//	private String ca_wsUrl;
//    
//    @Value("${ca_appCode}")
//	private String ca_appCode;
//
//	@Value("${ca_appPass}")
//	private String ca_appPass;
//
//	@Value("${ca_sender}")
//	private String ca_sender;
//	
//	@Value("${ca_encrypt_key}")
//	private String ca_encrypt_key;
//     
//    public SignVofficeBusinessImpl() {
//        tModel = new SignVofficeBO();
//        tDAO = signVofficeDAO;
//    }
//
//    @Override
//    public SignVofficeDAO gettDAO() {
//        return signVofficeDAO;
//    }
//
//    @Override
//    public long count() {
//        return signVofficeDAO.count("SignVofficeBO", null);
//    }
//
//    private  HashMap<Long, String> mapStatus(){
//    HashMap<Long, String> statusVO = new HashMap<Long, String>();
//    statusVO.put(1l,"Trình kí thành công");
//    statusVO.put(3l,"File trình kí không đúng định dạng");
//    statusVO.put(4l,"Đã tồn tại mã giao dịch này.");
//    statusVO.put(5l,"Mã giao dịch null");
//    statusVO.put(6l,"Danh sách email người ký rỗng!");
//    statusVO.put(8l,"Danh sách file trình kí null");
//    statusVO.put(9l,"AppCode không đúng");
//    statusVO.put(11l,"Lỗi phía WebService");
//    statusVO.put(12l,"Thiếu thông tin trình kí");
//    statusVO.put(13l,"Account null");
//    statusVO.put(14l,"Mã đơn vị null");
//    statusVO.put(15l,"Danh sách người ký null");
//    statusVO.put(16l,"Mã nhân viên ban hành null");
//    statusVO.put(17l,"Lỗi không có thông tin tài khoản Voffice");
//    statusVO.put(18l,"Lỗi không truyền tham số tài khoản đăng nhập");
//    statusVO.put(20l,"Lỗi ATTT tên file đính kèm");
//    statusVO.put(22l,"Lỗi file đính kèm không có dung lượng");
//    statusVO.put(28l,"Lỗi không tài khoản Voffice có mail trong danh sách mail trình ký");
//    statusVO.put(102l,"Lỗi đăng nhập tài khoản tập trung SSO");
//    statusVO.put(103l,"Lỗi trình ký cho văn thư");
//    statusVO.put(104l,"Lấy sai đơn vị ban hành");
//    statusVO.put(105l,"Lỗi dữ liệu rỗng");
//    statusVO.put(106l,"Lỗi file đính kèm không hợp lệ");
//    statusVO.put(107l,"Lỗi mail trình ký không tồn tại trên hệ thống Voffice");
//    statusVO.put(108l,"Lỗi tiêu đề văn bản quá dài");
//    statusVO.put(109l,"Lỗi giải mã mật khẩu");
//    statusVO.put(110l,"Lỗi thiếu thông tin đơn vị hoặc Id người ký");
//
//	return statusVO;
//   
//    }
//    
//    
//    @Transactional
//	public String signVoffice(List<CommonDTO> list,KttsUserSession objUser) throws Exception {
//    	String err="";
//    	for(CommonDTO conmonDTO:list){
//    	HashMap<Long, String> statusVO=mapStatus();
//    	
//		// danh sach nguoi ky van ban
//    	
//		// thong tin trinh ky
//    	
//    	SignVofficeDTO vofficeDTO=signVofficeDAO.getPassWordByUserId(objUser.getSysUserId());
//		if (objUser.getSysUserId() != null && objUser.getSysUserId().equals(conmonDTO.getCreatedBy())) {
//			String code = conmonDTO.getObjectCode();
//
//			String type = "0";
//			
//			String date=(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
//			String[] arr =date.split(" ");
//			
//			String dmy=arr[0].replace("/", "");
//			String hms=arr[1].replace(":", "");
//			
//			String transactionCode=objUser.getSysUserId().toString()+"_"+conmonDTO.getObjectId()+"_"+dmy+"_"+hms;
//			// Lay userName, pass vOffice
//			//TODO
//			String vOfficeName = objUser.getUserName();
//			String vOfficePass = vofficeDTO.getVofficePass();
//			// transactionCode chinh la ID bang document_CA
//			Long status = 0L;
//
//			try {
//				// Goi service ben vOffice, chuyen cac tham so tuong ung
//				// Tao service ket noi
//				Vo2AutoSignSystemImplService sv = new Vo2AutoSignSystemImplService(new URL(ca_wsUrl));
//				Vo2AutoSignSystemImpl service = sv.getVo2AutoSignSystemImplPort();
//
//				// Set timeout params
//				int connectionTimeOutInMs = 20000; // Thoi gian timeout 10s
//
//				Map<String, Object> requestContext = ((BindingProvider) service).getRequestContext();
//				requestContext.put("com.sun.xml.internal.ws.connect.timeout", connectionTimeOutInMs);
//				requestContext.put("com.sun.xml.internal.ws.request.timeout", connectionTimeOutInMs);
//				requestContext.put("com.sun.xml.ws.request.timeout", connectionTimeOutInMs);
//				requestContext.put("com.sun.xml.ws.connect.timeout", connectionTimeOutInMs);
//
//				// Truyen cac tham so cho webservice Voffice
//				KttsVofficeCommInpuParam param = new KttsVofficeCommInpuParam();
//				String appCodeEnc = EncryptionUtils.encrypt(ca_appCode, EncryptionUtils.getKey());
//				param.setAppCode(appCodeEnc); // tên app account đăng nhập nhap
//												// tren giao dien trinh ky
//				String appPassEnc1 = PassWordUtil.getInstance().encrypt(ca_appPass);
//				String appPassEnc2 = EncryptionUtils.encrypt(appPassEnc1, EncryptionUtils.getKey());
//				param.setAppPass(appPassEnc2); // mật khẩu appaccount đăng nhập
//												// nhap tren giao dien trinh ky
//				param.setAccountName("vof_test_tp2");
//
//				PassTranformer.setInputKey(ca_encrypt_key);// set key to encrypt
//															// password voffice
//				param.setAccountPass(PassTranformer.encrypt("Asdfgh@123"));// ma
//																			// hoa
//																			// password
//				param.setTransCode(String.valueOf(transactionCode));
//				param.setSender(ca_sender); // tên hệ thống trình kí văn bản. ->
//											// QLDTKTTS
//				param.setRegisterNumber(code); // ma bien ban trinh ky = Ma BB
//												// ben HCQT
//				param.setDocTitle(code);
//				param.setIsCanVanthuXetduyet(false);// Khong can van thu xet
//													// duyet
//				param.setHinhthucVanban(479L); // Test
////				param.setHinhthucVanban(520L); //That
//				// Danh sach file
//				// Lay file export pdf (attach hoac export tu man hinh bien ban
//				// trinh ky)
//				List<FileAttachTranfer> lstFileAttach =conmonDTO.getLstFileAttach();
//				param.setLstFileAttach(lstFileAttach);
//				List<String> listEmail= Lists.newArrayList();
//				
//				// Lay danh sach user ky theo dinh nghia cua Voffice
//				List<Vof2EntityUser> vofficeUserLstParam = new ArrayList<Vof2EntityUser>();
//
//				List<SignVofficeDTO> signVofficeDTOs= conmonDTO.getListSignVoffice();
//				Long index=0l;
//				for(SignVofficeDTO signVofficeDTO:signVofficeDTOs){
//					List<Vof2EntityUser> listUser = service.getListVof2UserByMail(Arrays.asList(signVofficeDTO.getEmail()));
//					for(Vof2EntityUser entityUser2:listUser){
//						if(signVofficeDTO.getSysRoleId().equals(entityUser2.getSysRoleId())){
//							entityUser2.setSignImageIndex(index);
//							vofficeUserLstParam.add(entityUser2);
//						}
//					}
//					
//					listEmail.add(signVofficeDTO.getEmail());
//				}
//				
//				param.setEmailPublishGroup(listEmail.get(listEmail.size()-1));
//				//TODO
//				// truyen param danh sach user ky
//				param.setLstUserVof2(vofficeUserLstParam);
//				
//				// goi webservice trinh ky Voffice
//				status = service.vo2RegDigitalDocByEmail(param);
//				
//
//			} catch (Exception e) {
//				LOGGER.error(e.getMessage(), e);
//				return "Lỗi xảy ra trong quá trình trình ký";
//
//			}
//
//			// Sau khi trinh ky xong, update ban ghi vua trinh ky, set status_ca
//			// = 1
//			if (status == 1) {
//				/*update bang SIGN_VOFFICE*/ 
//				SignVofficeBO signVofficeBO=new SignVofficeBO();
//				signVofficeBO.setSysUserId(conmonDTO.getUserId());
//				signVofficeBO.setObjectId(conmonDTO.getObjectId());
//				signVofficeBO.setType(conmonDTO.getType());
//				signVofficeBO.setStatus("1");
//				signVofficeBO.setErrorCode(status.toString());
//				signVofficeBO.setTransCode(transactionCode);
//				Long signId=signVofficeDAO.saveObject(signVofficeBO);
//				/*update bang SIGN_VOFFICE_DETAIL*/ 
//				for(SignVofficeDTO dto:conmonDTO.getListSignVoffice()){
//				SignVofficeDetailBO signVofficeDetailBO=new SignVofficeDetailBO();
//				signVofficeDetailBO.setOdrer(dto.getOder());
//				signVofficeDetailBO.setOdrerName(dto.getOderName());
//				signVofficeDetailBO.setRoleId(dto.getSysRoleId());
//				signVofficeDetailBO.setRoleName(dto.getSysRoleName());
//				signVofficeDetailBO.setSignVofficeId(signId);
//				signVofficeDetailBO.setSysUserId(dto.getSysUserId());
//				signVofficeDetailDAO.saveObject(signVofficeDetailBO);
//				}
//				/*update bang goc*/
//				String tableName=MapTable.get(conmonDTO.getType());
//				String sql=SqlUpdate(tableName,"2");
//				signVofficeDAO.updateStatus(sql, conmonDTO);
//				
//			} else if(status==9l){
//				err=statusVO.get(status);
//			}
//			
//			else{
//				SignVofficeBO signVofficeBO=new SignVofficeBO();
//				
//				signVofficeBO.setSysUserId(conmonDTO.getUserId());
//				signVofficeBO.setObjectId(conmonDTO.getObjectId());
//				signVofficeBO.setType(conmonDTO.getType());
//				signVofficeBO.setStatus("0");
//				signVofficeBO.setErrorCode(status.toString());
//				signVofficeBO.setTransCode(transactionCode);
//				Long signId=signVofficeDAO.saveObject(signVofficeBO);
//				
//				for(SignVofficeDTO dto:conmonDTO.getListSignVoffice()){
//					SignVofficeDetailBO signVofficeDetailBO=new SignVofficeDetailBO();
//					signVofficeDetailBO.setOdrer(dto.getOder());
//					signVofficeDetailBO.setOdrerName(dto.getOderName());
//					signVofficeDetailBO.setRoleId(dto.getSysRoleId());
//					signVofficeDetailBO.setRoleName(dto.getSysRoleName());
//					signVofficeDetailBO.setSignVofficeId(signId);
//					signVofficeDetailBO.setSysUserId(dto.getSysUserId());
//					signVofficeDetailDAO.saveObject(signVofficeDetailBO);
//					}
//				
//				if(!StringUtils.isNotEmpty(err)){
//					err="Các bản ghi trình ký thất bại: "+conmonDTO.getObjectCode()+"lỗi -"+statusVO.get(status);
//				} else {
//					err=err+"; " +conmonDTO.getObjectCode()+"lỗi -"+statusVO.get(status);
//				}
//				
//			}
//
//		} else {
//			if(!StringUtils.isNotEmpty(err)){
//				err="Bạn không có quyền trình ký văn bản có mã :" +conmonDTO.getObjectCode();
//			} else {
//				err=err+";"+conmonDTO.getObjectCode();
//			}
//			
//		}
//		 
//	}
//    	
//    	return err;
//    }
//    
//    private String SqlUpdate(String tableName,String signState){
//    	String
//    		 sql="UPDATE "+"\""+tableName+"\""+" SET SIGN_STATE="+signState+" WHERE "+tableName+"_ID =:id";
//    	
//    	return sql;
//    }
//    
//    public static final  Map<String, String> MapTable =new HashMap<String, String>();
//    static{
//    	MapTable.put("01", "ORDER");
//    	MapTable.put("02", "ORDER");
//    	MapTable.put("03", "STOCK_TRANS");
//    	MapTable.put("04", "STOCK_TRANS");
//		MapTable.put("05", "ORDER_CHANGE_GOODS");
//    }
//    
//    private String createSQL(String tableName,String type){
//    	StringBuilder sql = new StringBuilder();
//    	if("03".equals(type) || "04".equals(type)){
//    		sql.append(" WITH A AS (SELECT SUM(NVL(TOTAL_PRICE,0)) totalPrice,STOCK_TRANS_ID FROM STOCK_TRANS_DETAIL GROUP BY STOCK_TRANS_ID)");
//    	}
//    	sql.append(" SELECT OD.").append(tableName).append("_ID objectId,");
//    	sql.append("OD.CODE objectCode,"
//				+ "BTC.BUSSINESS_TYPE type,"
//				+ "OD.STOCK_ID stockId,"
//				+ "BTC.NAME oderName,"
//				+ "BTC.ODER oder,"
//				+ "CSV.SYS_USER_ID sysUserId,"
//				+ "CSV.ROLE_ID sysRoleId,"
//				+ "CSV.ROLE_NAME sysRoleName,"
//				+ "CSV.VOFFICE_ADORGID adOrgId,"
//				+ "OD.CREATED_BY createdBy,"
//				+ " CASE WHEN US.EMAIL IS NULL THEN US.FULL_NAME ELSE CONCAT(US.FULL_NAME, CONCAT(' - ', US.EMAIL)) END fullName ,"
//				+ " US.EMAIL email,"
//				+ "OD.SIGN_STATE signState ");
//	    	if("03".equals(type) || "04".equals(type)){
//	    		sql.append(" ,A.totalPrice totalPrice ");
//	    	}	
//    			sql.append(" FROM  WMS_OWNER_KTTS.");
//    			sql.append("\""+tableName+"\"");
//    			sql.append(" OD JOIN  WMS_OWNER_KTTS.BUSSINESS_TYPE_CONFIG BTC ON :bussiness=BTC.BUSSINESS_TYPE"
//				+ " LEFT JOIN WMS_OWNER_KTTS.CONFIG_SIGN_VOFFICE CSV ON (CSV.BUSSINESS_TYPE_CONFIG_ID=BTC.BUSSINESS_TYPE_CONFIG_ID AND CSV.STOCK_ID =OD.STOCK_ID)"
//				+ " LEFT JOIN VPS_OWNER.SYS_USER US ON US.SYS_USER_ID=CSV.SYS_USER_ID");
//    			if("03".equals(type) || "04".equals(type)){
//    	    		sql.append(" JOIN A ON A.STOCK_TRANS_ID=OD.STOCK_TRANS_ID ");
//    	    	}		
//    			sql.append(" WHERE  OD.").append(tableName).append("_ID in :listId ");
//    			sql.append(" ORDER BY OD.").append(tableName).append("_ID DESC,BTC.ODER");
//    	
//    	return sql.toString();
//    }
//    
//	private String createSQLDetail(String tableName){
//		StringBuilder sql = new StringBuilder("SELECT ");
//    	sql.append("BTC.BUSSINESS_TYPE type,"
//				
//				+ "BTC.NAME oderName,"
//				+ "BTC.ODER oder,"
//				+ "SVD.SYS_USER_ID sysUserId,"
//				+ "SVD.ROLE_ID sysRoleId,"
//				+ "SVD.ROLE_NAME sysRoleName,"				
//				+ " CASE WHEN US.EMAIL IS NULL THEN US.FULL_NAME ELSE CONCAT(US.FULL_NAME, CONCAT(' - ', US.EMAIL)) END fullName,"
//				+ " US.EMAIL email "
//				+ " FROM  WMS_OWNER_KTTS.");
//    			sql.append("\""+tableName+"\"");
//    			sql.append(" ST JOIN BUSSINESS_TYPE_CONFIG BTC ON :bussinessType=BTC.BUSSINESS_TYPE "
//				+ " LEFT JOIN SIGN_VOFFICE SV ON SV.OBJECT_ID=ST.STOCK_TRANS_ID "
//				+ " LEFT JOIN SIGN_VOFFICE_DETAIL SVD ON SVD.SIGN_VOFFICE_ID=SV.SIGN_VOFFICE_ID AND BTC.ODER=SVD.ODRER"
//				+ " LEFT JOIN VPS_OWNER.SYS_USER US ON US.SYS_USER_ID=SVD.SYS_USER_ID ");
//    			sql.append(" WHERE  ST.").append(tableName).append("_ID = :id ");
//    			sql.append(" ORDER BY BTC.ODER");
//    	
//    	return sql.toString();
//	}
//	
//	/**
//	 * @param id
//	 * @param type
//	 * @return
//	 */
//	public List<SignVofficeDTO> getDetailSign(Long id,String type){
//		String tableName=MapTable.get(type);
//		String sql=createSQLDetail(tableName);
//		List<SignVofficeDTO> ls = signVofficeDAO.getDetailSign(sql,type,id);
//		return ls;
//	}
//	
//	
//    /**
//     * @param listId
//     * @param type
//     * @param reportName
//     * @return
//     * @throws Exception
//     */
//    public List<CommonDTO> getdataSign(List<Long> listId, String type,String reportName) throws IllegalArgumentException{
//		List<CommonDTO> listReturn= Lists.newArrayList();
//		String tableName=MapTable.get(type);
//		String sql=createSQL(tableName,type);
//		List<SignVofficeDTO> ls = signVofficeDAO.getdataSign(sql,listId, type);
//		String err="";
//		CommonDTO ab = new CommonDTO();
//		for (Iterator<SignVofficeDTO> interator = ls.iterator(); interator.hasNext();) {
//			SignVofficeDTO wi = interator.next();
//
//			if (ab.getObjectId() == null) {
//				ab = new CommonDTO();
//				ab.setObjectId(wi.getObjectId());
//				ab.setObjectCode(wi.getObjectCode());
//				ab.setReportName(reportName);
//				ab.setType(type);
//				ab.setTotalPrice(wi.getTotalPrice());
//				ab.setCreatedBy(wi.getCreatedBy());
//				if(!("1".equals(wi.getSignState()) || "4".equals(wi.getSignState()))){
//					err=StringUtils.isNotEmpty(err)? (err+";"+wi.getObjectCode()):("Bản ghi có mã: "+wi.getObjectCode());
//				} 
//				listReturn.add(ab);
//
//			}
//			if (ab.getObjectId().compareTo(wi.getObjectId()) != 0) {
//				ab = new CommonDTO();
//				ab.setObjectId(wi.getObjectId());
//				ab.setObjectCode(wi.getObjectCode());
//				ab.setReportName(reportName);
//				ab.setType(type);
//				ab.setTotalPrice(wi.getTotalPrice());
//				ab.setCreatedBy(wi.getCreatedBy());
//				if(!("1".equals(wi.getSignState()) || "4".equals(wi.getSignState()))){
//					err=StringUtils.isNotEmpty(err)? (err+";"+wi.getObjectCode()):("Bản ghi có mã: "+wi.getObjectCode());
//				} 
//				listReturn.add(ab);
//			}
//			if (ab.getObjectId().compareTo(wi.getObjectId()) == 0) {
//				ab.getListSignVoffice().add(wi);
//			}
//		}
//		
//		
//		if(StringUtils.isNotEmpty(err)){
//			throw new IllegalArgumentException(err+"không đúng trạng thái ký!");
//		}
//		
//		return listReturn;
//		
//	}
//    
//    /**
//     * @param lstEmail
//     * @return
//     */
//    public List<VofficeUserDTO> getRoleByEmail(List<String> lstEmail){
//    	Vo2AutoSignSystemImplService sv;
//    	List<VofficeUserDTO> vofficeUserDTOs=Lists.newArrayList();
//		try {
//			sv = new Vo2AutoSignSystemImplService(new URL(ca_wsUrl));
//			Vo2AutoSignSystemImpl service = sv.getVo2AutoSignSystemImplPort();
//			List<Vof2EntityUser> entityUsers=service.getListVof2UserByMail(lstEmail);
//			for(Vof2EntityUser entityUser:entityUsers){
//				VofficeUserDTO	vofficeUserDTO=new VofficeUserDTO();
//				vofficeUserDTO.setAdOrgId(entityUser.getAdOrgId());
//				vofficeUserDTO.setAdOrgName(entityUser.getAdOrgName());
//				vofficeUserDTO.setSysOrgId(entityUser.getSysOrgId());
//				vofficeUserDTO.setSysOrgName(entityUser.getSysOrgName());
//				vofficeUserDTO.setSysRoleId(entityUser.getSysRoleId());
//				vofficeUserDTO.setSysRoleName(entityUser.getJobTile()+"_"+entityUser.getAdOrgName());
//				vofficeUserDTO.setStrEmail(entityUser.getStrEmail());
//				vofficeUserDTOs.add(vofficeUserDTO);
//			}
//			return vofficeUserDTOs;
//		} catch (MalformedURLException e) {
//			return Lists.newArrayList();
//		}
//		
//    }
//    
//   
//    
//    
//    @Transactional
//    public void updateStatus(SignVofficeDTO dto,String statusVo) throws Exception{
//    	String tableName=MapTable.get(dto.getType());
//    	String sql =null;
//    	CommonDTO commonDTO=new CommonDTO();
//		commonDTO.setObjectId(dto.getObjectId());
//    	if("3".equals(statusVo) || "5".equals(statusVo)){
//    		 sql =SqlUpdate(tableName, "3");
//    		 if(tableName.equals("ORDER_CHANGE_GOODS")){
//    	    		updateOderChange(dto.getObjectId());
//    	    	}
//    	} else {
//    		 sql =SqlUpdate(tableName, "4");
//    	}
//    	
//    	signVofficeDAO.updateStatus(sql,commonDTO);
//    	
//    	
//    
//    }
//    
//    public SignVofficeDTO getByTransCode(String transCode){
//    	return signVofficeDAO.getByTransCode(transCode);
//    }
//    
//    @Transactional
//    public void updateOderChange(Long id) throws Exception{
//    	
//    	StockTransDTO stockTransDTO=signVofficeDAO.getListFromOderChange(id);
//    	
//    	stockTransDTO.setRealIeTransDate(new Date());
//    	stockTransDTO.setRealIeUserId(stockTransDTO.getCreatedBy().toString());
//    	stockTransDTO.setRealIeUserName(stockTransDTO.getCreatedByName());
//    	stockTransDTO.setStatus("2");
//    	stockTransDTO.setSignState("3");
//    	stockTransDTO.setCreatedDate(new Date());
//    	createNoteAndRealEx(stockTransDTO,id);
//    	createNoteAndRealIm(stockTransDTO,id);
//    }
//    
//    private void createNoteAndRealEx(StockTransDTO obj,Long id) throws Exception{
////    	AppParamDTO appParamDTO=appParamDAO.getbycodeAndParType(par_code, par_type_ex);
//    	AppParamDTO appParamDTO =new AppParamDTO();
//    	obj.setBussinessType(par_code);
//    	obj.setBussinessTypeName(appParamDTO.getName());
//    	obj.setType("2");
//    	CommonDTO objCommon =new CommonDTO();
//    	objCommon.setValue("PXK_");
//    	objCommon.setOrgId(obj.getCreatedDeptId());
//    	objCommon.setStockId(obj.getStockId());
//    	obj.setCode(genCode(objCommon));
//    	Long stockTransId=stockTransDAO.saveObject(obj.toModel());
//    	List<StockTransDetailDTO> ls = signVofficeDAO.getListGoodsFromOderChangeForEx(id);
//    	List<StockTransDetailDTO> stockTransDetailDTOs=groupData(ls);
//    	
//    	for(StockTransDetailDTO stockTransDetail :stockTransDetailDTOs){
//    		
//    		stockTransDetail.setStockTransId(stockTransId);
//    		stockTransDetail.setOrderId(obj.getOrderId());
//    		//Tao ban ghi STOCK_TRANS_DETAIL
//    	Long stockTransDetailId=stockTransDetailDAO.saveObject(stockTransDetail.toModel());
//    	stockTransDetail.setStockId(obj.getStockId());
//    	//update STOCK_GOODS voi hang hoa ko chi dinh SERIAL
//    		if(!"1".equals(stockTransDetail.getGoodsIsSerial())){
//    			
//    			stockGoodsBusinessImpl.updateNonSerialGoods(stockTransDetail);
//    		} 
//    		//INSERT STOCK_TRANS_DETAIL_SERIAL và update STOCK_GOODS_SERIAL voi hang hoa  chi dinh SERIAL
//    		else {
//    			for(StockTransDetailSerialDTO detailSerialDTO:stockTransDetail.getListDetailSerial()){
//    				detailSerialDTO.setStockTransId(stockTransId);
//    				detailSerialDTO.setStockTransDetailId(stockTransDetailId);
//    				stockTransDetailSerialDAO.saveObject(detailSerialDTO.toModel());
//    				stockTransBusinessImpl.updateSerialGoods(detailSerialDTO, obj.getOrderId(), obj.getStockId());
//    			}
//    		}
//    		
//    		//UPDATE STOCK_GOODS_TOTAL
//    		
//    		StockGoodsTotalDTO stockGoodsTotalDTO=stockGoodsTotalDAO.getAmountIssueGoodsTotal(stockTransDetail.getGoodsId(), obj.getStockId(), stockTransDetail.getGoodsState());
//    		if(stockGoodsTotalDTO!=null){
//    			Double amount = (stockGoodsTotalDTO.getAmount()!=null?stockGoodsTotalDTO.getAmount():0D)-(stockTransDetail.getAmount()!=null?stockTransDetail.getAmount():0D);
//    			Double newAmountIssue = (stockGoodsTotalDTO.getAmountIssue()!=null?stockGoodsTotalDTO.getAmountIssue():0D)-(stockTransDetail.getAmount()!=null?stockTransDetail.getAmount():0D);
//    			stockGoodsTotalDTO.setAmount(amount);
//    			stockGoodsTotalDTO.setAmountIssue(newAmountIssue);
//    			stockGoodsTotalDAO.updateStockGoodsTotal(stockGoodsTotalDTO);
//    		} else {
//    			throw new IllegalArgumentException("Lỗi!");
//    		}
//    		
//    		
//    	}
//    }
//    //Tao phieu va thuc nhap
//    private void createNoteAndRealIm(StockTransDTO obj,Long id) throws Exception{
////    	AppParamDTO appParamDTO=appParamDAO.getbycodeAndParType(par_code, par_type_im);
//    	AppParamDTO appParamDTO=new AppParamDTO();
//    	obj.setBussinessType(par_code);
//    	obj.setBussinessTypeName(appParamDTO.getName());
//    	obj.setType("1");
//    	CommonDTO objCommon =new CommonDTO();
//    	objCommon.setValue("PNK_");
//    	objCommon.setOrgId(obj.getCreatedDeptId());
//    	objCommon.setStockId(obj.getStockId());
//    	obj.setCode(genCode(objCommon));
//    	//Tao ban ghi STOCK_TRANS
//    	Long stockTransId=stockTransDAO.saveObject(obj.toModel());
//    	List<StockTransDetailDTO> ls = signVofficeDAO.getListGoodsFromOderChangeForIm(id);
//    	List<StockTransDetailDTO> stockTransDetailDTOs=groupData(ls);
//    	
//    	for(StockTransDetailDTO stockTransDetail :stockTransDetailDTOs){
//    		
//    		stockTransDetail.setStockTransId(stockTransId);
//    		//Tao ban ghi STOCK_TRANS_DETAIL
//    	Long stockTransDetailId=stockTransDetailDAO.saveObject(stockTransDetail.toModel());
//    	//INSERT STOCK_GOODS voi hang hoa ko chi dinh SERIAL
//    		if(!"1".equals(stockTransDetail.getGoodsIsSerial())){
//    			StockGoodsDTO stockGoodsDTO=new StockGoodsDTO();
//    			stockGoodsDTO.setGoodsId(stockTransDetail.getGoodsId());
//    			stockGoodsDTO.setGoodsType(stockTransDetail.getGoodsType()!=null?(new Long(stockTransDetail.getGoodsType())):null);
//    			stockGoodsDTO.setGoodsTypeName(stockTransDetail.getGoodsTypeName());
//    			stockGoodsDTO.setGoodsCode(stockTransDetail.getGoodsCode());
//    			stockGoodsDTO.setGoodsName(stockTransDetail.getGoodsName());
//    			stockGoodsDTO.setGoodsState(stockTransDetail.getGoodsState());
//    			stockGoodsDTO.setGoodsStateName(stockTransDetail.getGoodsStateName());
//    			stockGoodsDTO.setGoodsUnitId(stockTransDetail.getGoodsUnitId());
//    			stockGoodsDTO.setGoodsUnitName(stockTransDetail.getGoodsUnitName());
//    			stockGoodsDTO.setAmount(stockTransDetail.getAmountReal());
//    			stockGoodsDTO.setImportStockTransId(stockTransId);
//    			stockGoodsDTO.setImportDate(new Date());
//    			stockGoodsDTO.setStatus("1");
//    			stockGoodsDAO.saveObject(stockGoodsDTO.toModel());
//    			
//    		} 
//    		//INSERT STOCK_TRANS_DETAIL_SERIAL và STOCK_GOODS_SERIAL voi hang hoa  chi dinh SERIAL
//    		else {
//    			for(StockTransDetailSerialDTO detailSerialDTO:stockTransDetail.getListDetailSerial()){
//    				detailSerialDTO.setStockTransId(stockTransId);
//    				detailSerialDTO.setStockTransDetailId(stockTransDetailId);
//    				stockTransDetailSerialDAO.saveObject(detailSerialDTO.toModel());
//    				StockGoodsSerialDTO goodsSerialDTO=new StockGoodsSerialDTO();
//    				StockGoodsSerialDTO stockGoodsSerialDTO = stockGoodsSerialDAO.findBySerialAndGoodsId(detailSerialDTO.getSerial(), detailSerialDTO.getGoodsId());
//    				if(stockGoodsSerialDTO==null){
//    				goodsSerialDTO.setGoodsId(stockTransDetail.getGoodsId());
//    				goodsSerialDTO.setGoodsType(new Long(stockTransDetail.getGoodsType()));
//    				goodsSerialDTO.setGoodsTypeName(stockTransDetail.getGoodsTypeName());
//    				goodsSerialDTO.setGoodsCode(stockTransDetail.getGoodsCode());
//    				goodsSerialDTO.setGoodsName(stockTransDetail.getGoodsName());
//    				goodsSerialDTO.setGoodsState(stockTransDetail.getGoodsState());
//    				goodsSerialDTO.setGoodsStateName(stockTransDetail.getGoodsStateName());
//    				goodsSerialDTO.setGoodsUnitId(stockTransDetail.getGoodsUnitId());
//    				goodsSerialDTO.setGoodsUnitName(stockTransDetail.getGoodsUnitName());
//    				goodsSerialDTO.setAmount(1D);
//    				goodsSerialDTO.setImportStockTransId(stockTransId);
//    				goodsSerialDTO.setImportDate(new Date());
//    				goodsSerialDTO.setStatus("1");
//    				goodsSerialDTO.setSerial(detailSerialDTO.getSerial());
//    				stockGoodsSerialDAO.saveObject(goodsSerialDTO.toModel());
//    				} else if(!"1".equals(stockGoodsSerialDTO.getStatus())){
//    					stockGoodsSerialDTO.setGoodsId(stockTransDetail.getGoodsId());
//    					stockGoodsSerialDTO.setGoodsType(new Long(stockTransDetail.getGoodsType()));
//    					stockGoodsSerialDTO.setGoodsTypeName(stockTransDetail.getGoodsTypeName());
//    					stockGoodsSerialDTO.setGoodsCode(stockTransDetail.getGoodsCode());
//    					stockGoodsSerialDTO.setGoodsName(stockTransDetail.getGoodsName());
//    					stockGoodsSerialDTO.setGoodsState(stockTransDetail.getGoodsState());
//    					stockGoodsSerialDTO.setGoodsStateName(stockTransDetail.getGoodsStateName());
//    					stockGoodsSerialDTO.setGoodsUnitId(stockTransDetail.getGoodsUnitId());
//    					stockGoodsSerialDTO.setGoodsUnitName(stockTransDetail.getGoodsUnitName());
//    					stockGoodsSerialDTO.setAmount(1D);
//    					stockGoodsSerialDTO.setImportStockTransId(stockTransId);
//    					stockGoodsSerialDTO.setImportDate(new Date());
//    					stockGoodsSerialDTO.setStatus("1");
//    					stockGoodsSerialDTO.setSerial(detailSerialDTO.getSerial());
//    					stockGoodsSerialDAO.updateObject(stockGoodsSerialDTO.toModel());
//    				} else {
//    					throw new IllegalArgumentException("Đã tồn tại serial: "+detailSerialDTO.getSerial()+" có trạng thái là 1 trong kho hàng!");
//    				}
//    			}
//    		}
//    		
//    		//UPDATE OR INSERT STOCK_GOODS_TOTAL
//    		
//    		StockGoodsTotalDTO stockGoodsTotalDTO=stockGoodsTotalDAO.getAmountIssueGoodsTotal(stockTransDetail.getGoodsId(), obj.getStockId(), stockTransDetail.getGoodsState());
//    		if(stockGoodsTotalDTO!=null){
//    			Double amount = (stockGoodsTotalDTO.getAmount()!=null?stockGoodsTotalDTO.getAmount():0D)+(stockTransDetail.getAmountOrder()!=null?stockTransDetail.getAmountOrder():0D);
//    			Double newAmountIssue = (stockGoodsTotalDTO.getAmountIssue()!=null?stockGoodsTotalDTO.getAmountIssue():0D)+(stockTransDetail.getAmountReal()!=null?stockTransDetail.getAmountReal():0D);
//    			stockGoodsTotalDTO.setAmount(amount);
//    			stockGoodsTotalDTO.setAmountIssue(newAmountIssue);
//    			stockGoodsTotalDAO.updateStockGoodsTotal(stockGoodsTotalDTO);
//    		} else {
//    			StockDTO stock= (StockDTO) stockBusinessImpl.getOneById(obj.getStockId());
//    			stockGoodsTotalDTO=new StockGoodsTotalDTO();
//    			stockGoodsTotalDTO.setGoodsCode(stockTransDetail.getGoodsCode());
//    			stockGoodsTotalDTO.setGoodsName(stockTransDetail.getGoodsName());
//    			stockGoodsTotalDTO.setGoodsUnitId(stockTransDetail.getGoodsUnitId());
//    			stockGoodsTotalDTO.setGoodsUnitName(stockTransDetail.getGoodsUnitName());
//    			stockGoodsTotalDTO.setAmount(stockTransDetail.getAmountOrder());
//    			stockGoodsTotalDTO.setAmountIssue(stockTransDetail.getAmountReal());
//    			stockGoodsTotalDTO.setGoodsState(stockTransDetail.getGoodsState());
//    			stockGoodsTotalDTO.setGoodsStateName(stockTransDetail.getGoodsStateName());
//    			stockGoodsTotalDTO.setGoodsId(stockTransDetail.getGoodsId());
//    			stockGoodsTotalDTO.setStockId(obj.getStockId());
//    			stockGoodsTotalDTO.setChangeDate(new Date());
//    			stockGoodsTotalDTO.setStockCode(stock.getCode());
//    			stockGoodsTotalDTO.setStockName(stock.getName());
//    			stockGoodsTotalDAO.saveObject(stockGoodsTotalDTO.toModel());
//    		}
//    		
//    		
//    	}
//    }
//    
//    private List<StockTransDetailDTO>  groupData(List<StockTransDetailDTO> ls){
//    	List<StockTransDetailDTO> stockTransDetailDTOs= Lists.newArrayList();
//    	
//		
//    	StockTransDetailDTO ab = new StockTransDetailDTO();
//		for (Iterator<StockTransDetailDTO> interator = ls.iterator(); interator.hasNext();) {
//			StockTransDetailDTO wi = interator.next();
//
//			if (ab.getGoodsId() == null || !"1".equals(ab.getGoodsIsSerial())) {
//				ab = new StockTransDetailDTO();
//				ab.setGoodsId(wi.getGoodsId());
//				ab.setGoodsType(wi.getGoodsType());
//				ab.setGoodsTypeName(wi.getGoodsTypeName());
//				ab.setGoodsCode(wi.getGoodsCode());
//				ab.setGoodsName(wi.getGoodsName());
//				ab.setGoodsState(wi.getGoodsState());
//				ab.setGoodsStateName(wi.getGoodsStateName());
//				ab.setGoodsUnitId(wi.getGoodsUnitId());
//				ab.setGoodsUnitName(wi.getGoodsUnitName());
//				ab.setAmountOrder(wi.getAmount());
//				ab.setAmountReal(wi.getAmount());
//				ab.setGoodsIsSerial(wi.getGoodsIsSerial());
//				
//				stockTransDetailDTOs.add(ab);
//
//			}
//			if (ab.getGoodsId().compareTo(wi.getGoodsId()) != 0 || !"1".equals(ab.getGoodsIsSerial())) {
//				ab = new StockTransDetailDTO();
//				ab.setGoodsId(wi.getGoodsId());
//				ab.setGoodsType(wi.getGoodsType());
//				ab.setGoodsTypeName(wi.getGoodsTypeName());
//				ab.setGoodsCode(wi.getGoodsCode());
//				ab.setGoodsName(wi.getGoodsName());
//				ab.setGoodsState(wi.getGoodsState());
//				ab.setGoodsStateName(wi.getGoodsStateName());
//				ab.setGoodsUnitId(wi.getGoodsUnitId());
//				ab.setGoodsUnitName(wi.getGoodsUnitName());
//				ab.setAmountOrder(wi.getAmount());
//				ab.setAmountReal(wi.getAmount());
//				ab.setGoodsIsSerial(wi.getGoodsIsSerial());
//				stockTransDetailDTOs.add(ab);
//			}
//			
//			if (ab.getGoodsId().compareTo(wi.getGoodsId()) == 0 && "1".equals(ab.getGoodsIsSerial())) {
//				StockTransDetailSerialDTO detailSerialDTO=new StockTransDetailSerialDTO();
//				detailSerialDTO.setSerial(wi.getSerial());
//				detailSerialDTO.setGoodsId(wi.getGoodsId());
//				
//				ab.getListDetailSerial();
//			}
//		}
//		
//		return stockTransDetailDTOs;
//    }
//    
//    private final static String Config_Code_One="{VALUE}{STOCK}_{ORG}/{YY}/";
//    private final static String Config_Code_Two="{VALUE}_{STOCK}/{YY}/";
//    
//    private String genCode(CommonDTO obj) {
//    	String code=null;
//    	Calendar cal = Calendar.getInstance();
//    	model table=getTable(obj.getValue());
//    	String year=String.valueOf(cal.getTime().getYear()).substring(1);
//    	String param=table.getValue();
//    	param=param.replaceAll("\\{VALUE\\}", Matcher.quoteReplacement(obj.getValue()));
//    	if(StringUtils.isNotEmpty(obj.getStockValue())){
//    		param=param.replaceAll("\\{STOCK\\}", Matcher.quoteReplacement(obj.getStockValue()));
//    	}else if(obj.getStockId()!=null){
//    		StockDTO stock= (StockDTO) stockBusinessImpl.getOneById(obj.getStockId());
//    		param=param.replaceAll("\\{STOCK\\}", Matcher.quoteReplacement(stock.getCode()));
//    		
//    	}
//    	
//    	DepartmentDTO depat=(DepartmentDTO) departmentBusinessImpl.getOne(obj.getOrgId());
//    	param=param.replaceAll("\\{ORG\\}", Matcher.quoteReplacement(depat.getCode()));
//    		
//    	param=param.replaceAll("\\{YY\\}", year);
//    	String tableName=table.getTableName();
////    	String numberStr=appParamBusinessImpl.getCode(tableName, param);
//    	String numberStr=null;
//    	code=param+numberStr;
//    	return code;
//    }
//    
//    
//    
//    
//    private model getTable(String value){
//    	
//    	switch (value) {
//		case "YCNK":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"ORDER\"");
//			obj.setValue(Config_Code_One);
//			return obj;
//		}
//		case "YCXK":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"ORDER\"");
//			obj.setValue(Config_Code_One);
//			return obj;
//		}
//		case "PNK_":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"STOCK_TRANS\"");
//			obj.setValue(Config_Code_Two);
//			return obj;
//		}
//		case "PXK_":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"STOCK_TRANS\"");
//			obj.setValue(Config_Code_Two);
//			return obj;
//		}
//		case "YCTD":{
//			model obj= new model();
//			obj.setTableName("WMS_OWNER_KTTS.\"ORDER_CHANGE_GOODS\"");
//			obj.setValue(Config_Code_Two);
//			return obj;
//		}
//		default:
//			return null;
//		}
//    	
//    	
//    }
//
//    public class model{
//    	private String value;
//    	public String getValue() {
//			return value;
//		}
//		public void setValue(String value) {
//			this.value = value;
//		}
//		public String getTableName() {
//			return tableName;
//		}
//		public void setTableName(String tableName) {
//			this.tableName = tableName;
//		}
//		private String tableName;
//    }
//}
