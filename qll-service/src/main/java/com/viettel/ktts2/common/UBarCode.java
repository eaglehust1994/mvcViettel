//package com.viettel.ktts2.common;
//
//
//import java.awt.image.BufferedImage;
//import java.io.FileOutputStream;
//
//
//
//import org.apache.commons.io.output.ByteArrayOutputStream;
//import org.krysalis.barcode4j.impl.code128.Code128Bean;
//import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
//import org.krysalis.barcode4j.tools.UnitConv;
//
//
//public class UBarCode {
//	/*public static void createBarCode(String code){
//		BarcodeEAN codeEAN = new BarcodeEAN();
//		codeEAN.setCodeType(codeEAN.EAN13);
//		codeEAN.setCode("9780201615883");
//		Image imageEAN = codeEAN.cre(cb, null, null);
//	}*/
//	
//	public static void createBarcode(String pathImageBarCode, String noteCode) throws Exception {
//
//        //recomend to use code 128
//        Code128Bean bean = new Code128Bean();
//        final int dpi = 100;
//
//        //Configure the barcode generator
//        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
//        //width exactly one pixel
//        //System.out.println(UnitConv.in2mm(1.0f / dpi));
//        //bean.setWideFactor(3);
//        //bean.
//        bean.doQuietZone(false);
//        //bean.setBarHeight(10);
//        //set font size = 0 to hide text under barcode, set !=0 to show text
//        bean.setFontSize(0);
//        //height of image
//        bean.setHeight(10);
//        //write barcode to image
//
//        try (FileOutputStream os = new FileOutputStream(pathImageBarCode)) {
//            //Set up the canvas provider for monochrome JPEG output
//            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
//                    os, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
//
//            //Generate the barcode
//            bean.generateBarcode(canvas, noteCode);
//
//            //Signal end of generation
//            canvas.finish();
//        }
//    }
//
//    public static byte[] createBarcode(String noteCode) throws Exception {
//
//        //recomend to use code 128
//        Code128Bean bean = new Code128Bean();
//        final int dpi = 100;
//
//        //Configure the barcode generator
//        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
//        //width exactly one pixel
//        //System.out.println(UnitConv.in2mm(1.0f / dpi));
//        //bean.setWideFactor(3);
//        //bean.
//        bean.doQuietZone(false);
//        //bean.setBarHeight(10);
//        //set font size = 0 to hide text under barcode, set !=0 to show text
//        bean.setFontSize(0);
//        //height of image
//        bean.setHeight(10);
//        //write barcode to image
//
//        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
//            //Set up the canvas provider for monochrome JPEG output
//            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
//                    os, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
//
//            //Generate the barcode
//            bean.generateBarcode(canvas, noteCode);
//
//            //Signal end of generation
//            canvas.finish();
//            return os.toByteArray();
//        }
//    }
//}
