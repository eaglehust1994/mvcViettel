/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.ktts2.common;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ha
 */
public class UString {
//	HieuNC 05/10/2017
	private static String pattern = ",";
//	End
	/**
	 * Kiểm tra chuỗi ký tự có phải null hoặc toàn dấu whitespace, tab, enter...
	 *
	 * @param str
	 * @return true nếu str là null hoặc toàn dấu whitespace, tab, enter...
	 */
	public static boolean isNullOrWhitespace(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * Kiểm tra chuỗi ký tự có phải null hoặc toàn dấu whitespace, tab, enter...
	 *
	 * @param str
	 * @return false nếu str là null hoặc toàn dấu whitespace, tab, enter...
	 */
	public static boolean isNotNullAndWhitespace(String str) {
		return !(str == null || "".equals(str.trim()));
	}

	public static boolean isEqual(String str1, String str2) {
		if (str1 == null) {
			str1 = "";
		}
		if (str2 == null) {
			str2 = "";
		}

		return str1.equals(str2);
	}

	/**
	 * So sanh 2 chuoi, bao gom ca null, tu dong cat chuoi va bo qua chu
	 * hoa/thuong
	 *
	 * @param str1
	 * @param str2
	 * @return true nếu bằng nhau, false nếu không bằng.
	 */
	public static boolean compareIgnoreAll(String str1, String str2) {
		str1 = trim(str1);
		str2 = trim(str2);
		if (str1 == null) {
			str1 = "";
		}
		if (str2 == null) {
			str2 = "";
		}

		if (str1.equalsIgnoreCase(str2)) {
			return true;
		}
		return false;
	}

	/**
	 * Loai bo dau trang o 2 dau cua 1 String, neu null hoac '' chuyển thành
	 * null
	 *
	 * @param arg
	 * 
	 * @return String chuoi sau khi duoc loai bo dau trang o 2 dau
	 * @throws Exception
	 */
	public static String trim(String arg) {
		String result = null;
		if (arg != null && !"".equals(arg.trim())) {
			result = arg.trim();
		}
		return result;
	}

	/**
	 * Kiem tra xem String co bang true hay khong
	 *
	 * @param arg
	 *            tham số truy�?n vào kiểu String
	 * @return Boolean false neu null/empty , true neu khong
	 * @throws Exception
	 */
	public static boolean isTrue(String arg) {
		if (arg != null && "true".equalsIgnoreCase(arg.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Tách chuỗi ký tự thành các phần tử phân cách bởi regex
	 *
	 * @param <T>
	 *            kiểu dữ liệu của phần tử sau khi được tách
	 * @param str
	 *            chuỗi ký tự cần phân tách
	 * @param regex
	 *            chuỗi ký tự phân cách
	 * @param cls
	 *            kiểu dữ liệu của phần tử sau khi được tách
	 * @return
	 * @throws Exception
	 */
	public static List<String> split(String str, String regex) {
		List<String> result = new ArrayList<>();
		if (!UString.isNullOrWhitespace(str)) {
			String[] arr = str.split(regex);
			for (String ele : arr) {
				if (!isNullOrWhitespace(ele)) {
					result.add(ele.trim());
				}
			}
		}
		return result;
	}

	/**
	 * Ghep list thanh chuoi ky tu.
	 *
	 * @param list
	 *            danh sách cần ghép
	 * @param separator
	 *            chuỗi phân cách
	 * @param parser
	 *            hàm chuyển phần tử trong list thành chuỗi ký tự cần ghép
	 * @return
	 * @throws Exception
	 */
	public static String join(List<String> list, String separator) {
		StringBuilder str = new StringBuilder();
		if (list != null && !list.isEmpty()) {
			str.append(list.get(0));
			for (int i = 1; i < list.size(); i++) {
				str.append(separator);
				str.append(list.get(i));
			}
		}
		return str.toString();
	}

	/**
	 * Convert sang dữ liệu BigDecimal
	 *
	 * @param str
	 * @return null nếu empty string hoặc null
	 */
	public static BigDecimal parseToBigDecimal(String str) throws NumberFormatException {
		return isNullOrWhitespace(str) ? null : new BigDecimal(str);
	}

	/**
	 * Chuyen string sang mang byte
	 * 
	 * @param inputString
	 * @return Mang byte tuong ung voi chuoi string dau vao
	 */
	public static byte[] getBase64Bytes(String inputString) {
		return Base64.getDecoder().decode(inputString);
	}

	/**
	 * Cat string dai qua xuong dung do dai mong muon (da tinh them "..."), phan
	 * con thua de la "..."
	 * 
	 * @param inputString
	 * @param length
	 * @return ket qua sau khi cat giam
	 */
	public static String reduceLength(String inputString, int length) {
		if (inputString != null && inputString.length() > length) {
			inputString = inputString.substring(0, length - 3) + "...";
		}
		return inputString;
	}

	public static String toLikeString(String likeString) {
		if (likeString == null) {
			return "%";
		} else
			return "%" + likeString + "%";
	}

	public static String getSafeFileName(String input) {
		StringBuilder sb = new StringBuilder();
		if (input != null) {
			for (int i = 0; i < input.length(); i++) {
				char c = input.charAt(i);
				if (c != '/' && c != '\\' && c != 0) {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	public static String extractFileNameNotExt(String fileName) {
		fileName = UString.getSafeFileName(fileName);
		int dotPos = fileName.lastIndexOf(".");
		String fileNameNotExt = dotPos > 0 ? fileName.substring(0, dotPos) : fileName;

		return fileNameNotExt;
	}

	public static String extractFileNameFromPath(String fileFullPath) {
		// fileFullPath = UString.getSafeFileName(fileFullPath);
		int dotPos = fileFullPath.lastIndexOf(File.separatorChar);
		String fileNameNotExt = dotPos > 0 ? fileFullPath.substring(dotPos + 1) : fileFullPath;

		return fileNameNotExt;
	}

	public static String extractFileExt(String fileName) {
		int dotPos = fileName.lastIndexOf(".");
		return dotPos > 0 ? fileName.substring(dotPos) : "";
	}

	public static boolean isExtendAllowSave(String fileName, String fileExtAllowSave) {
		boolean check = false;
		if (fileName != null && !("").equals(fileName) && fileExtAllowSave != null) {
			String ext = UString.extractFileExt(fileName).trim();
			if (ext != null && !"".equals(ext)) {
				List<String> lstExtend = Arrays.asList(fileExtAllowSave.split(","));
				if (lstExtend.contains(ext)) {
					check = true;
				}
			} else {
				check = true;
			}
		}
		return check;
	}

	public static boolean isFolderAllowFolderSave(String folderDir, String folderAllowExt) {
		boolean check = false;

		if (folderDir != null && !("").equals(folderDir) && folderAllowExt != null) {
			String ext = folderDir.trim().toLowerCase();
			if (ext != null && !"".equals(ext)) {
				List<String> lstExtend = Arrays.asList(folderAllowExt.split(","));
				if (lstExtend.contains(ext)) {
					check = true;
				}
			}else{
				return true;
			}
		}
		return check;
	}
//	HieuNC 05/10/2017
	public static String getPattern() {
		return pattern;
	}

	public static void setPattern(String pattern) {
		UString.pattern = pattern;
	}
	/**
	 * Convert a Long array into a String contain one or more element separated by UString.pattern.
	 * The default pattern is "," but can change by call setPattern(String pattern) method
	 * For example, the method convert [1L,2L] into "1,2,"
	 * @param array
	 * Input array
	 * @return String
	 */
	public static String parseLongArrayToString(Long[] array){
		if(array == null || array.length==0){
			return "";
		}
		StringBuilder str = new StringBuilder();
		for (Long x : array){
			str.append(x.toString() + pattern);
		}
		return str.toString();
	}
	/**
	 * Convert a String may contain one or more element separate by UString.pattern into Long array where each element 
	 * converted from respectively element in String. For example "1,2,3," -> [1L,2L,3L]
	 * The default separator is "," but can change by setPattern(String pattern) method
	 * @param str
	 * Input String to be converted
	 * @return Long Array
	 */
	public static Long[] parseStringToLongArray(String str){
		if (StringUtils.isEmpty(str)){
			return null;
		}
		String[] strArray = str.split(pattern);
		Long[] result = new Long[strArray.length];
		if (strArray.length>0){
			for (int i=0; i<strArray.length; i++){
				result[i]=(StringUtils.isEmpty(strArray[i]))?-1:Long.valueOf(strArray[i]);
			}
		}
		return result;
	}
//	End
/**
 *
 * @author ha
 */

}