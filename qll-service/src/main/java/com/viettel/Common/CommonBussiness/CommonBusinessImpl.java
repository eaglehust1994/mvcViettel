package com.viettel.Common.CommonBussiness;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.viettel.ktts2.common.UEncrypt;


@Service("commonBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CommonBusinessImpl implements CommonBusiness {
	 @Value("${folder_upload}")
		private String folder2Upload;


	
	
	public String exportExcelTemplate(String fileName) throws Exception {
		// TODO Auto-generated method stub
		
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream file = new BufferedInputStream(new FileInputStream(filePath + fileName +".xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		file.close();
		File out = new File(folder2Upload + File.separatorChar +fileName +".xlsx");
		
		FileOutputStream outFile = new FileOutputStream(out);
		workbook.write(outFile);
		workbook.close();
		outFile.close();
		
		String path = UEncrypt.encryptFileUploadPath(fileName +".xlsx");
		return path;
	}




	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
}
