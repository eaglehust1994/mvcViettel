package com.viettel.ktts2.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.time.DateFormatUtils;

public class UFile {
	
	/**
	 *
	 * @param multivaluedMap
	 * @return
	 */
	public static String getFileName(MultivaluedMap<String, String> multivaluedMap) {
		String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
				String exactFileName = name[1].trim().replaceAll("\"", "");

				DateFormatUtils.format(new Date(), "");
				return exactFileName;
			}
		}
		return "unknownFile";
	}
	
	public static String writeToFileServerATTT(InputStream inputStream, String fileName, String subFolder, String folder)
			throws Exception {

		String safeFileName = UString.getSafeFileName(fileName) ;//+ File.pathSeparator + UString.extractFileExt(fileName);
		String subFolderSafe=UString.getSafeFileName(subFolder);
		Calendar cal = Calendar.getInstance();
		String uploadPath = folder + File.separator + UFile.getSafeFileName(subFolder) + File.separator + cal.get(Calendar.YEAR)
				+ File.separator + (cal.get(Calendar.MONTH) + 1) + File.separator + cal.get(Calendar.DATE)
				+ File.separator + cal.get(Calendar.MILLISECOND);
		File udir = new File(uploadPath);
		if (!udir.exists()) {
			udir.mkdirs();
		}
		try (OutputStream out = new FileOutputStream(udir.getAbsolutePath() + File.separator + safeFileName)) {
			int bytesRead = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((bytesRead = inputStream.read(buffer, 0, 1024 * 8)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
		return uploadPath + File.separator + safeFileName;

	}
	
	public static String writeToFileServerATTT2(InputStream inputStream, String fileName, String subFolder, String folder)
			throws Exception {

		String safeFileName = UString.getSafeFileName(fileName) ;//+ File.pathSeparator + UString.extractFileExt(fileName);
		String subFolderSafe=UString.getSafeFileName(subFolder);
		Calendar cal = Calendar.getInstance();
		String uploadPath = folder + File.separator + UFile.getSafeFileName(subFolder) + File.separator + cal.get(Calendar.YEAR)
				+ File.separator + (cal.get(Calendar.MONTH) + 1) + File.separator + cal.get(Calendar.DATE)
				+ File.separator + cal.get(Calendar.MILLISECOND);
		String uploadPathReturn = File.separator + UFile.getSafeFileName(subFolder) + File.separator + cal.get(Calendar.YEAR)
		+ File.separator + (cal.get(Calendar.MONTH) + 1) + File.separator + cal.get(Calendar.DATE)
		+ File.separator + cal.get(Calendar.MILLISECOND);
		File udir = new File(uploadPath);
		if (!udir.exists()) {
			udir.mkdirs();
		}
		try (OutputStream out = new FileOutputStream(udir.getAbsolutePath() + File.separator + safeFileName)) {
			int bytesRead = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((bytesRead = inputStream.read(buffer, 0, 1024 * 8)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
		return uploadPathReturn + File.separator + safeFileName;

	}
	
	public static String getSafeFileName(String input) {
		StringBuilder sb = new StringBuilder();
		if (input != null) {
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				if (c != '/' && c != '\\' && c != 0 ) {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}
	
	/*public static String getFileNameATTT(MultivaluedMap<String, String> multivaluedMap) {
		String[] contentDisposition = multivaluedMap.getFirst("Content-Disposition").split(";");
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
				String exactFileName = name[1].trim();
				// encode file name
				String encodeFileName = Base64.getEncoder().encode(UString.extractFileNameNotExt(exactFileName).getBytes())
						+ UString.extractFileExt(exactFileName);
				return encodeFileName;
			}
		}
		return "";
	}*/
}
