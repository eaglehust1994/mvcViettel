package com.viettel.ims.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class Constants {
	public static final int DOC_REPORT = 0;
	public static final int PDF_REPORT = 1;
	public static final int EXCEL_REPORT = 2;
	public static final int HTML_REPORT = 3;

	public static final Map<String, Long> ProcurementForms;
		    static
		    {
		    	ProcurementForms = new HashMap<String, Long>();
		    	ProcurementForms.put("ĐẤU THẦU RỘNG RÃI", 0L);
		    	ProcurementForms.put("ĐẤU THẦU HẠN CHẾ", 1L);
		    	ProcurementForms.put("MUA SẮM TRỰC TIẾP", 2L);
		    	ProcurementForms.put("CHỈ ĐỊNH THẦU", 3L);
		    	ProcurementForms.put("CHÀO HÀNG CẠNH TRANH", 4L);
		    	ProcurementForms.put("TỰ ĐẤU THẦU", 5L);
		    	
		    }
		
	public interface InvestmentOwnerType{
		public static final long DOI_TAC_TRONG_VIETTEL = 0L;
		public static final long DOI_TAC_NGOAI_VIETTEL = 1L;
		public static final String DOI_TAC_TRONG_VIETTEL_STR = "ĐỐI TÁC TRONG VIETTEL";
		public static final String DOI_TAC_NGOAI_VIETTEL_STR = "ĐỐI TÁC NGOÀI VIETTEL";
	}
	
	
	
}

