/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.erp.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.erp.business.AbSettlementValueBusinessImpl;
import com.viettel.erp.business.ConstrAcceptWorkListBusinessImpl;
import com.viettel.erp.business.VConstructionHcqtBusinessImpl;
import com.viettel.erp.dao.AbSettlementValueDAO;
import com.viettel.erp.dto.AbSettlementValueDTO;
import com.viettel.erp.dto.ConstrAcceptWorkListDTO;
import com.viettel.erp.dto.QualityCableMeaResultDTO;
import com.viettel.erp.dto.VConstructionHcqtDTO;
import com.viettel.ktts2.common.UEncrypt;
import com.viettel.service.base.dto.DataListDTO;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
/**
 *
 * @author HungLQ9
 */
public class AbSettlementValueRsServiceImpl implements AbSettlementValueRsService {
	
	static Logger LOGGER = LoggerFactory.getLogger(AbSettlementValueRsServiceImpl.class);

    @Autowired
    AbSettlementValueBusinessImpl abSettlementValueBusinessImpl;

    @Autowired
    VConstructionHcqtBusinessImpl vConstructionHcqtBusinessImpl;
    
    @Autowired
    AbSettlementValueDAO abSettlementValueDAO;
    
    @Autowired
    ConstrAcceptWorkListBusinessImpl constrAcceptWorkListBusinessImpl;
    
    @Value("${folder_upload}")
  	private String folder2Upload;
    
    @Override
    public Response getAbSettlementValue() {
        List<AbSettlementValueDTO> ls = abSettlementValueBusinessImpl.getAll();
        if (ls == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            DataListDTO data = new DataListDTO();
            data.setData(ls);
            data.setTotal(ls.size());
            data.setSize(ls.size());
            data.setStart(1);
            return Response.ok(data).build();
        }
    }

    @Override
    public Response getAbSettlementValueById(Long id) {
        AbSettlementValueDTO obj = (AbSettlementValueDTO) abSettlementValueBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @Override
    public Response updateAbSettlementValue(AbSettlementValueDTO obj) {
        Long id = abSettlementValueBusinessImpl.update(obj);
        if (id == 0l) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok().build();
        }

    }

    @Override
    public Response addAbSettlementValue(AbSettlementValueDTO obj) {
    	Long id = 0l;
    	AbSettlementValueDTO isCheckData = (AbSettlementValueDTO) abSettlementValueBusinessImpl.getById(obj.getConstructId());
    	if(isCheckData == null){
    		try {
    		id = abSettlementValueBusinessImpl.saveTable(obj);
    		} catch (Exception ex) {
    			LOGGER.error(ex.getMessage(), ex);
    		}
            if (id == 0l) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else {
                return Response.ok(id).build();
            }
    	}else{
    		AbSettlementValueDTO objDetail = (AbSettlementValueDTO) abSettlementValueBusinessImpl.checkEstimateAB1(obj.getConstructId());
    		if(objDetail != null){
    			isCheckData.setCode(objDetail.getCode());
    			isCheckData.setCreatedDate(new Date());
    			isCheckData.setCreatedUserId(obj.getCreatedUserId());
    			isCheckData.setStatusCa(obj.getStatusCa());
    			isCheckData.setIsActive(obj.getIsActive());
    			isCheckData.setADirectorId(obj.getADirectorId());
        		isCheckData.setBDirectorId(obj.getBDirectorId());
        		isCheckData.setExportMaterialValue(obj.getExportMaterialValue());
        		isCheckData.setAcceptMaterialValue(obj.getAcceptMaterialValue());
        		isCheckData.setLostMaterialValue(obj.getLostMaterialValue());
        		isCheckData.setRecoveryMaterialValue(obj.getRecoveryMaterialValue());
        		isCheckData.setUnrecoveryMaterialValue(obj.getUnrecoveryMaterialValue());
        		isCheckData.setPaidValue(obj.getPaidValue());
        		isCheckData.setIsActive(1l);
        		abSettlementValueBusinessImpl.update(isCheckData);
        		id =abSettlementValueBusinessImpl.getConstrComReMapId(objDetail.getAbSettlementValueId());
    		}
        	if (id == 0l) {
    			return Response.status(Response.Status.BAD_REQUEST).build();
    		} else {
    			return Response.ok(id).build();
    		}
    	}
    }

    @Override
    public Response deleteAbSettlementValue(Long id) {
        AbSettlementValueDTO obj = (AbSettlementValueDTO) abSettlementValueBusinessImpl.getOneById(id);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            abSettlementValueBusinessImpl.delete(obj);
            return Response.ok(Response.Status.NO_CONTENT).build();
        }
    }

	@Override
	public Response checkDataSaveForm1(Long constructId) {
		AbSettlementValueDTO obj = (AbSettlementValueDTO) abSettlementValueBusinessImpl.checkEstimateAB1(constructId);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
	}

	@Override
	public Response getconstrCompReMapId(Long constructId) {
//		Long id = 0l;
		AbSettlementValueDTO objDetail = (AbSettlementValueDTO) abSettlementValueBusinessImpl.getById(constructId);
		Long	id = abSettlementValueBusinessImpl.getConstrComReMapId(objDetail.getAbSettlementValueId());
		return Response.ok(id).build();
	}

	@Override
	public Response exportFilePdfFromDB(Long abSettlementValueId) {
		AbSettlementValueDTO obj = null ;
		try {
			obj = getDataExport(abSettlementValueId);
			
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream in = new FileInputStream(new File(filePath +  "/QT-AB-1.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
            metadata.load("bc", AbSettlementValueDTO.class, true);
            
			IContext context = report.createContext();
            context.put("item", obj);
            context.put("bc", obj.getAcceptMaterialValue());
            
            IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(StringUtils.isNotEmpty(obj.getaDirectorIdPath())){
            	sign1 = new FileImageProvider(new File(folder2Upload + "/" + obj.getaDirectorIdPath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            
            if(StringUtils.isNotEmpty(obj.getbDirectorIdPath())){
            	sign2 = new FileImageProvider(new File(folder2Upload + "/" + obj.getbDirectorIdPath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
          //check status CA =  2 
            if(obj.getStatusCa() == 2){
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
            } else {
            	context.put("sign1", none);
                context.put("sign2", none);
            }
            
			File fout = new File(folder2Upload + "/QT-AB-1.pdf");
			OutputStream out = new FileOutputStream(fout);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			report.convert(context, options, out);
			out.flush();
			out.close();
			System.out.println("export chan ki done !");
			String path  =   UEncrypt.encryptFileUploadPath("QT-AB-1.pdf");
			return Response.ok(Collections.singletonMap("fileName", path)).build();
	    } catch ( Exception e ){
	    	throw new RuntimeException(e);
	    } 
		//return Response.ok(obj).build();
	}

	@Override
	public Response exportFileWordFromDB(Long abSettlementValueId) {
		AbSettlementValueDTO obj = null ;
		try {
			obj = getDataExport(abSettlementValueId);
			
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			String filePath = classloader.getResource("../" + "doc-template").getPath();
			InputStream in = new FileInputStream(new File(filePath + "/QT-AB-1.docx"));
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.addFieldAsImage("sign1");
            metadata.addFieldAsImage("sign2");
			metadata.load("bc", QualityCableMeaResultDTO.class, true);
			
			IContext context = report.createContext();
			context.put("item", obj);
			context.put("bc", obj.getAcceptMaterialValue());
			
			IImageProvider sign1;
            IImageProvider sign2;
            IImageProvider none = new FileImageProvider(new File(filePath + "/none.png"));
            if(StringUtils.isNotEmpty(obj.getaDirectorIdPath())){
            	sign1 = new FileImageProvider(new File(folder2Upload + "/" + obj.getaDirectorIdPath()));
            } else{
            	sign1 = new FileImageProvider(new File(filePath + "/none.png"));
            }
            
            if(StringUtils.isNotEmpty(obj.getbDirectorIdPath())){
            	sign2 = new FileImageProvider(new File(folder2Upload + "/" + obj.getbDirectorIdPath()));
            } else{
            	sign2 = new FileImageProvider(new File(filePath + "/none.png"));
            }
          //ki roi moi co anh chan ki 
            if(obj.getStatusCa() == 2){
            	context.put("sign1", sign1);
                context.put("sign2", sign2);
            } else {
            	context.put("sign1", none);
                context.put("sign2", none);
            }
            
			File fout = new File(folder2Upload + "/QT-AB-1.docx");
			OutputStream out = new FileOutputStream(fout);
			report.process(context, out);
			out.flush();
			out.close();
			System.out.println("export file QT-AB-1.doc tu DB done");
			String path  =   UEncrypt.encryptFileUploadPath("QT-AB-1.docx");
			   return Response.ok(Collections.singletonMap("fileName", path)).build();
	    } catch ( Exception e ){
	    	throw new RuntimeException(e);
	    } 
		//return Response.ok(obj).build();
	}

	private AbSettlementValueDTO getDataExport(Long abSettlementValueId){
		AbSettlementValueDTO obj = null ;
		try {
			obj = (AbSettlementValueDTO) abSettlementValueBusinessImpl.getByABSettlementId(abSettlementValueId);
			List<VConstructionHcqtDTO> obj2 = (List<VConstructionHcqtDTO>) vConstructionHcqtBusinessImpl.getContractTotalValueById(obj.getConstructId());
			Double d=new Double(obj2.get(0).getContract_total_value());
			long contractTotal = d.intValue();
			//I
			obj.setContractTotalValue(contractTotal);
			List<ConstrAcceptWorkListDTO> obj3 = (List<ConstrAcceptWorkListDTO>) constrAcceptWorkListBusinessImpl.getProposedSettlementById(obj.getConstructId());
			System.out.println("I) = " + contractTotal);
			//III.1 III.2
//			System.out.println("Gia tri hop dong 1 = " + obj3.get(0).getValueProposed());
//			System.out.println("Gia tri hop dong 2 = " + obj3.get(1).getValueProposed());
			Long value=0L;
			Long valueOut=0L;
			if(obj3.size()>0){
			obj.setValueProposed(obj3.get(0).getValueProposed());
				value=obj3.get(0).getValueProposed();
			} 
			
			if(obj3.size()>1){
			obj.setValueOutProposed(obj3.get(1).getValueProposed());
				valueOut=obj3.get(1).getValueProposed();
			} 
			obj.setValueFinalizationContractors(valueOut+value);
			//III
			System.out.println("Tong III = " + obj.getValueFinalizationContractors());
			obj.setSumSettlementConstruction((obj.getAcceptMaterialValue() != null ? obj.getAcceptMaterialValue():0l)  + (obj.getValueFinalizationContractors() != null ? obj.getValueFinalizationContractors() :0l));
			System.out.println("Tong IV = " + obj.getSumSettlementConstruction());
			//V, VI chua co data de gia tri la 0
			if(obj.getPaidValue() == null){
				obj.setPaidValue(0L);
			}
			obj.setValueDeductionSupplies(0L);
			//VII = III - V- VI
			Long tmpValueDeductionSupplies = 0L;
			if(obj.getValueDeductionSupplies() != null){
				tmpValueDeductionSupplies = obj.getValueDeductionSupplies();
			}/*else{
				 tmpValueDeductionSupplies = 0l;
			}*/
			obj.setValueResidual((obj.getValueFinalizationContractors() != null ? obj.getValueFinalizationContractors():0l) - 
					(obj.getPaidValue()!=null ? obj.getPaidValue():0l) - tmpValueDeductionSupplies);
			System.out.println("Tong VII = " + obj.getValueResidual());
			System.out.println("Tong V = " + obj.getPaidValue());
			
			Calendar c = Calendar.getInstance();
			obj.setDate(new Long(c.get(Calendar.DATE)));
			obj.setMonth(new Long(c.get(Calendar.MONTH)+1));
			String sYear = Long.toString(new Long(c.get(Calendar.YEAR))).replaceAll(",", "");
//			sYear.;
			obj.setYear(sYear);
	    } catch ( Exception e ){
	    	throw new RuntimeException(e);
	    } 
		return obj;
	}

	@Override
	public Response getStatusEvaluate(Long constrId) {
		AbSettlementValueDTO obj = (AbSettlementValueDTO) abSettlementValueDAO.getStatusEvaluate(constrId);
        if (obj == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(obj).build();
        }
	}
}
