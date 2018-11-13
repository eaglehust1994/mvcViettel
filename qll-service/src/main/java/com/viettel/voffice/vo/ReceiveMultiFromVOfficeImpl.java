//package com.viettel.voffice.vo;
//
//
//import java.util.logging.Level;
//
//import javax.jws.WebMethod;
//import javax.jws.WebParam;
//import javax.jws.WebService;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//
//
//
//
////import com.viettel.wms.business.SignVofficeBusinessImpl;
//import com.viettel.wms.dto.SignVofficeDTO;
///**
//*
//* @author 
//*/
//@WebService(serviceName = "ReceiveMultiFromVOfficeImpl")
//public class ReceiveMultiFromVOfficeImpl implements ReceiveFromVOfficeList 
//{
//	protected final static Logger log = Logger.getLogger(ReceiveMultiFromVOfficeImpl.class);
//	/** Cau hinh trang thai tu choi 	
//	 * 1- Van thu tu choi
//	 * 2- Lanh dao tu choi
//	 * 4- Nguoi trinh ky huy luong
//	 */
//	private final static String VOFFICE_STATUS_REJECT	= "VOFFICE_STATUS_REJECT";
//	/** Gia tri mac dinh	*/
//	private final static String VOFFICE_STATUS_REJECT_DEFAULT	= "1;2;4";
//	/** Cau hinh trang thai phe duyet
//	 * 	3- Phe duyet
//	 */
//	private final static String VOFFICE_STATUS_APPROVED	= "VOFFICE_STATUS_APPROVED";
//	/** Gia tri mac dinh	*/
//	private final static String VOFFICE_STATUS_APPROVED_DEFAULT	= "3";
//	
//	private final static String STATUS_APP	= "3";
//	private final static String STATUS_FAIL	= "4";
//	
//	private String vReject;
//	
//	private String vApproved;
//	
//	 @Autowired
//	    private SignVofficeBusinessImpl signVofficeBusinessImpl;
//	/**	Logger			*/
////	private static CLogger log = CLogger.getCLogger(ReceiveMultiFromVOfficeImpl.class);
//	
//    @WebMethod(operationName = "hello")
//    @Override
//    public String hello(@WebParam(name = "name") String txt) 
//    {
//        return "Hello " + txt + " !";
//    }
//    
//    @Override
//    public Long returnMultiSignReult(ResultObjList resultObjlst) 
//    {
//        return Long.valueOf(1L);
//    }
//    
//    private String toString (ResultObj resultObj) {
//    	StringBuilder ret_Val = new StringBuilder();
//    	ret_Val.append("ActionDate=" + resultObj.getActionDate());
//    	ret_Val.append(",AppCode=" + resultObj.getAppCode());
//    	ret_Val.append(",DocumentCode=" + resultObj.getDocumentCode());
//    	ret_Val.append(",LastSignEmail=" + resultObj.getLastSignEmail());
//    	ret_Val.append(",PublishDate=" + resultObj.getPublishDate());
//    	ret_Val.append(",PublishOganizationCode=" + resultObj.getPublishOganizationCode());
//    	ret_Val.append(",SignComment=" + resultObj.getSignComment());
//    	ret_Val.append(",SignStatus=" + resultObj.getSignStatus());
//    	ret_Val.append(",TransCode=" + resultObj.getTransCode());
//    	ret_Val.append(",Wsdl=" + resultObj.getWsdl());
//    	ret_Val.append(",VoTextId=" + resultObj.getVoTextId());
//    	
//    	return ret_Val.toString();
//    }
//
//    @Override
//    public Long returnSignReult(ResultObj resultObj) 
//    {
//        Long ret_Val;
//        if (resultObj == null) 
//        {
//            return Long.valueOf(2L);
//        }
//        
//        vReject =  VOFFICE_STATUS_REJECT_DEFAULT;
//        vApproved = VOFFICE_STATUS_APPROVED_DEFAULT;
//        
//        SignVofficeDTO docSign = signVofficeBusinessImpl.getByTransCode(resultObj.getTransCode());
//        
//        log.info("call sucsses");
//        	try {
//        		signVofficeBusinessImpl.updateStatus(docSign, resultObj.getSignStatus());
//            	ret_Val=1L;
//			} catch (Exception e) {
//				log.error(Level.SEVERE, e);
//				// TODO: handle exception
//				ret_Val=2L;
//			}
//        	
//        return ret_Val;
//    }
//
//	
//
//    
//}
//
