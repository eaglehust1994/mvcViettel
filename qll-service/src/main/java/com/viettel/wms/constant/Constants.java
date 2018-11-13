package com.viettel.wms.constant;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class Constants {
	public static final int DOC_REPORT = 0;
	public static final int PDF_REPORT = 1;
	public static final int EXCEL_REPORT = 2;
	public static final int HTML_REPORT = 3;
	public static  class OperationKey{
		public static String CREATE="CREATE";
		public static String ESTIMATE="ESTIMATE";
		public static String VALUE="VALUE";
		public static String VIEW="VIEW";
		public static String CONFIG="CONFIG";
		public static String REPORT="REPORT";
		
	}
	
	public static class AdResourceKey{
		public static String STOCK_TRANS="IE_TRANSACTION";
		public static String CHANGE_ORDER="CHANGE_ORDER";
		public static String SHIPMENT="SHIPMENT";
		public static String STOCK="STOCK";
	}
	
	public static final List<String> LISTREPORTNAME= Arrays.asList("baocaokndu","BaocaoKPISoluong","BaocaoKPIThoigian","BaoCaoPhieuXuatKhoDangDiDuong","baocaotonkho","baocaoxuatnhap");
	
	public static final String DASH_BROAD="DASH_BROAD";
	
	
	public static final List<String> LISTREPORTNAMESTOCKNULL= Arrays.asList("BaocaoKPISoluong","BaocaoKPIThoigian","BaoCaoPhieuXuatKhoDangDiDuong");
}

