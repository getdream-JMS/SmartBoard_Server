package com.jms.dboard.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import com.jms.dboard.common.utils.CommonUtils;
import com.jms.dboard.manage.vo.AnnounceBoardDetailsVO;
import com.jms.dboard.manage.vo.NewsBoardDetailsVO;

@Component
public class PDFUtils {
	
	public static List<AnnounceBoardDetailsVO> pdf2Image(File inputFile, String destinationDir,int contentId) {
		List<AnnounceBoardDetailsVO> imageList = new ArrayList<AnnounceBoardDetailsVO>();
		PDDocument doc = null;
		try {

			//			System.out.println("isFile :"+convFile.isFile);
			File destinationFile = new File(destinationDir);
			
			AnnounceBoardDetailsVO imageInfo = null;
			
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("DESTINATION FOLDER CREATED -> " + destinationFile.getAbsolutePath());
			}else if(destinationFile.exists()){
				System.out.println("DESTINATION FOLDER ALLREADY CREATED!!!");
			}else{
				System.out.println("DESTINATION FOLDER NOT CREATED!!!");
			}
			if (inputFile != null) {
				System.out.println("newCreateFile :"+inputFile);
				doc = PDDocument.load(inputFile);
				PDFRenderer renderer = new PDFRenderer(doc);
				List<File> fileList = new ArrayList<File>();
				
				String newFileName = CommonUtils.padLeftZeros(contentId+"",10);
				String fileName = inputFile.getName().replace(".pdf", "");
				System.out.println("CONVERTER START.....");
			     
//				PDFParserTextStripper stripper = new PDFParserTextStripper(doc);
//		        stripper.setSortByPosition(true);
//		        for (int i=0;i<doc.getNumberOfPages();i++){
//		            stripper.stripPage(i);
//		        }
				PDFTextStripper stripper = new PDFTextStripper();

			    stripper.setSortByPosition(true);
			    stripper.setLineSeparator(" ");
				for (int i = 1; i <= doc.getNumberOfPages(); i++) {
					// default image files path: original file path
					// if necessary, file.getParent() + "/" => another path
					imageInfo = new AnnounceBoardDetailsVO();
					File fileTemp = new File(destinationDir +File.separator+ newFileName + "_" + i + ".jpg"); // jpg or png
					imageInfo.setContentId(contentId);
					imageInfo.setFileName(newFileName + "_" + i + ".jpg");
					imageInfo.setContIndex(i);
					
					BufferedImage image = renderer.renderImageWithDPI(i-1, 80);
//					BufferedImage image = renderer.renderImageWithDPI(i-1,80);
//					BufferedImage image = renderer.renderImage(i-1);
//					ImageIO.write(image, "JPEG", (new File(destinationDir +File.separator+ "origin.jpg"))); // JPE
//					BufferedImage sharpenImage = ImageFilterUtils.sharpen(image, 10);
//					ImageIO.write(sharpenImage, "JPEG", (new File(destinationDir +File.separator+ "sharpen.jpg"))); // JPE
//					BufferedImage removeNoizyImage  = ScanedImage.removeNoizy(sharpenImage);
//					ImageIO.write(image, "JPEG", (new File(destinationDir +File.separator+ "noizy.jpg"))); // JPE
					// 200 is dots per inch.
					// if necessary, change 200 into another integer.
					ImageIO.write(image, "JPEG", fileTemp); // JPEG or PNG
					
					
//					int newWidth = 424;                                  // 변경 할 넓이
//			        int newHeight = 600;                                 // 변경 할 높이
//			        String mainPosition = "W";    
//			        int imageWidth;
//			        int imageHeight;
//			        double ratio;
//			        int w;
//			        int h;
//			     // 원본 이미지 가져오기
//			        
////		            image = ImageIO.read(new File(destinationDir +File.separator+ newFileName + "_" + i + ".jpg"));
//		 
//		            // 원본 이미지 사이즈 가져오기
//		            imageWidth = removeNoizyImage.getWidth(null);
//		            imageHeight = removeNoizyImage.getHeight(null);
//		 
//		            if(mainPosition.equals("W")){    // 넓이기준
//		 
//		                ratio = (double)newWidth/(double)imageWidth;
//		                w = (int)(imageWidth * ratio);
//		                h = (int)(imageHeight * ratio);
//		 
//		            }else if(mainPosition.equals("H")){ // 높이기준
//		 
//		                ratio = (double)newHeight/(double)imageHeight;
//		                w = (int)(imageWidth * ratio);
//		                h = (int)(imageHeight * ratio);
//		 
//		            }else{ //설정값 (비율무시)
//		 
//		                w = newWidth;
//		                h = newHeight;
//		            }
//		 
//		            // 이미지 리사이즈
//		            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
//		            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
//		            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
//		            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
//		            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
//		            Image resizeImage = removeNoizyImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
//		 
//		            // 새 이미지  저장하기
//		            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//		            Graphics g = newImage.getGraphics();
//		            g.drawImage(resizeImage, 0, 0, null);
//		            g.dispose();
////		            ImageIO.write(newImage, "JPG", new File(destinationDir +File.separator+ newFileName + "_" + i + ".jpg"));
//		            ImageIO.write(newImage, "JPEG", new File(destinationDir +File.separator+ "output.jpg"));
//			        
//			        
					 fileTemp = new File(destinationDir +File.separator+ newFileName + "_" + i + "_b.jpg"); // jpg or png
					 BufferedImage image_b = renderer.renderImageWithDPI(i-1, 130);
					 ImageIO.write(image_b, "JPEG", fileTemp); // JPEG or PNG
//					doc.getPage(i);
					stripper.setStartPage(i);
				    //set the end page
					stripper.setEndPage(i);
					String pdfText = stripper.getText(doc);
					imageInfo.setTtsInfo(pdfText);
					System.out.println("pdfText :"+pdfText);
//					ITesseract instance = new Tesseract();
//					instance.setDatapath("D:\\MyWork");
//					instance.setLanguage("kor");
//					instance.setTessVariable("user_defined_dpi", "200");
//					System.out.println(fileTemp.getAbsolutePath()); 	
//				      try 
//				      {
//				         String imgText = instance.doOCR(fileTemp);
////				         System.out.println(imgText);
//				      } 
//				      catch (TesseractException e) 
//				      {
//				         e.printStackTrace(); 
//				      }
					imageList.add(imageInfo);
				}

				System.out.println("CONVERTER END.....");
				System.out.println("IMAGE SAVED AT -> " + destinationFile.getAbsolutePath());
				//	            return fileList;
			} else {
				System.err.println(inputFile.getName() + " FILE DOES NOT EXIST");
			}
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			if(doc != null) {
				try {
					doc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return imageList;
		}
	}
	
	public static List<AnnounceBoardDetailsVO> extractTextFromPDF(File inputFile, String destinationDir,int contentId) {
		List<AnnounceBoardDetailsVO> imageList = new ArrayList<AnnounceBoardDetailsVO>();
		try {

			//			System.out.println("isFile :"+convFile.isFile);
			File destinationFile = new File(destinationDir);
			
			AnnounceBoardDetailsVO imageInfo = null;
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("DESTINATION FOLDER CREATED -> " + destinationFile.getAbsolutePath());
			}else if(destinationFile.exists()){
				System.out.println("DESTINATION FOLDER ALLREADY CREATED!!!");
			}else{
				System.out.println("DESTINATION FOLDER NOT CREATED!!!");
			}

			if (inputFile != null) {
				System.out.println("newCreateFile :"+inputFile);
				PDDocument doc = PDDocument.load(inputFile);
				PDFRenderer renderer = new PDFRenderer(doc);
				List<File> fileList = new ArrayList<File>();
				
				String newFileName = CommonUtils.padLeftZeros(contentId+"",10);
				String fileName = inputFile.getName().replace(".pdf", "");
				System.out.println("CONVERTER START.....");
				PDFTextStripper pdfStripper = new PDFTextStripper();
				
				for (int i = 0; i < doc.getNumberOfPages(); i++) {
					// default image files path: original file path
					// if necessary, file.getParent() + "/" => another path
					imageInfo = new AnnounceBoardDetailsVO();
					File fileTemp = new File(destinationDir +File.separator+ newFileName + "_" + i + ".jpg"); // jpg or png
					imageInfo.setContentId(contentId);
					imageInfo.setFileName(newFileName + "_" + i + ".jpg");
					imageInfo.setContIndex(i);

					BufferedImage image = renderer.renderImageWithDPI(i, 200);
					// 200 is sample dots per inch.
					// if necessary, change 200 into another integer.
					ImageIO.write(image, "JPEG", fileTemp); // JPEG or PNG
					imageList.add(imageInfo);
//					doc.getPage(i);
					pdfStripper.setStartPage(i);
					pdfStripper.setEndPage(i);
					String pageText = pdfStripper.getText(doc);
					
					System.out.println(pageText);
				}
				doc.close();
				System.out.println("CONVERTER STOPTED.....");
				System.out.println("IMAGE SAVED AT -> " + destinationFile.getAbsolutePath());
				//	            return fileList;
			} else {
				System.err.println(inputFile.getName() + " FILE DOES NOT EXIST");
			}
			return imageList;
		} catch(Exception e) {
			e.printStackTrace();
			return imageList;
		}
	}
	
	public static List<NewsBoardDetailsVO> pdf2Image4News(File inputFile, String destinationDir,int contentId) {
		List<NewsBoardDetailsVO> imageList = new ArrayList<NewsBoardDetailsVO>();
		PDDocument doc = null;
		try {

			//			System.out.println("isFile :"+convFile.isFile);
			File destinationFile = new File(destinationDir);
			
			NewsBoardDetailsVO imageInfo = null;
			
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("DESTINATION FOLDER CREATED -> " + destinationFile.getAbsolutePath());
			}else if(destinationFile.exists()){
				System.out.println("DESTINATION FOLDER ALLREADY CREATED!!!");
			}else{
				System.out.println("DESTINATION FOLDER NOT CREATED!!!");
			}
			if (inputFile != null) {
				System.out.println("newCreateFile :"+inputFile);
				doc = PDDocument.load(inputFile);
				PDFRenderer renderer = new PDFRenderer(doc);
				List<File> fileList = new ArrayList<File>();
				
				String newFileName = CommonUtils.padLeftZeros(contentId+"",10);
				String fileName = inputFile.getName().replace(".pdf", "");
				System.out.println("CONVERTER START.....");
			     
//				PDFParserTextStripper stripper = new PDFParserTextStripper(doc);
//		        stripper.setSortByPosition(true);
//		        for (int i=0;i<doc.getNumberOfPages();i++){
//		            stripper.stripPage(i);
//		        }
				PDFTextStripper stripper = new PDFTextStripper();

			    stripper.setSortByPosition(true);
			    stripper.setLineSeparator(" ");
				for (int i = 1; i <= doc.getNumberOfPages(); i++) {
					// default image files path: original file path
					// if necessary, file.getParent() + "/" => another path
					imageInfo = new NewsBoardDetailsVO();
					File fileTemp = new File(destinationDir +File.separator+ newFileName + "_" + i + ".jpg"); // jpg or png
					imageInfo.setContentId(contentId);
					imageInfo.setFileName(newFileName + "_" + i + ".jpg");
					imageInfo.setContIndex(i);
					
					BufferedImage image = renderer.renderImageWithDPI(i-1, 80);
//					BufferedImage image = renderer.renderImageWithDPI(i-1,80);
//					BufferedImage image = renderer.renderImage(i-1);
//					ImageIO.write(image, "JPEG", (new File(destinationDir +File.separator+ "origin.jpg"))); // JPE
//					BufferedImage sharpenImage = ImageFilterUtils.sharpen(image, 10);
//					ImageIO.write(sharpenImage, "JPEG", (new File(destinationDir +File.separator+ "sharpen.jpg"))); // JPE
//					BufferedImage removeNoizyImage  = ScanedImage.removeNoizy(sharpenImage);
//					ImageIO.write(image, "JPEG", (new File(destinationDir +File.separator+ "noizy.jpg"))); // JPE
					// 200 is dots per inch.
					// if necessary, change 200 into another integer.
					ImageIO.write(image, "JPEG", fileTemp); // JPEG or PNG
					
					
//					int newWidth = 424;                                  // 변경 할 넓이
//			        int newHeight = 600;                                 // 변경 할 높이
//			        String mainPosition = "W";    
//			        int imageWidth;
//			        int imageHeight;
//			        double ratio;
//			        int w;
//			        int h;
//			     // 원본 이미지 가져오기
//			        
////		            image = ImageIO.read(new File(destinationDir +File.separator+ newFileName + "_" + i + ".jpg"));
//		 
//		            // 원본 이미지 사이즈 가져오기
//		            imageWidth = removeNoizyImage.getWidth(null);
//		            imageHeight = removeNoizyImage.getHeight(null);
//		 
//		            if(mainPosition.equals("W")){    // 넓이기준
//		 
//		                ratio = (double)newWidth/(double)imageWidth;
//		                w = (int)(imageWidth * ratio);
//		                h = (int)(imageHeight * ratio);
//		 
//		            }else if(mainPosition.equals("H")){ // 높이기준
//		 
//		                ratio = (double)newHeight/(double)imageHeight;
//		                w = (int)(imageWidth * ratio);
//		                h = (int)(imageHeight * ratio);
//		 
//		            }else{ //설정값 (비율무시)
//		 
//		                w = newWidth;
//		                h = newHeight;
//		            }
//		 
//		            // 이미지 리사이즈
//		            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
//		            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
//		            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
//		            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
//		            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
//		            Image resizeImage = removeNoizyImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
//		 
//		            // 새 이미지  저장하기
//		            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//		            Graphics g = newImage.getGraphics();
//		            g.drawImage(resizeImage, 0, 0, null);
//		            g.dispose();
////		            ImageIO.write(newImage, "JPG", new File(destinationDir +File.separator+ newFileName + "_" + i + ".jpg"));
//		            ImageIO.write(newImage, "JPEG", new File(destinationDir +File.separator+ "output.jpg"));
//			        
//			        
					 fileTemp = new File(destinationDir +File.separator+ newFileName + "_" + i + "_b.jpg"); // jpg or png
					 BufferedImage image_b = renderer.renderImageWithDPI(i-1, 130);
					 ImageIO.write(image_b, "JPEG", fileTemp); // JPEG or PNG
//					doc.getPage(i);
					stripper.setStartPage(i);
				    //set the end page
					stripper.setEndPage(i);
					String pdfText = stripper.getText(doc);
					imageInfo.setTtsInfo(pdfText);
					System.out.println("pdfText :"+pdfText);
//					ITesseract instance = new Tesseract();
//					instance.setDatapath("D:\\MyWork");
//					instance.setLanguage("kor");
//					instance.setTessVariable("user_defined_dpi", "200");
//					System.out.println(fileTemp.getAbsolutePath()); 	
//				      try 
//				      {
//				         String imgText = instance.doOCR(fileTemp);
////				         System.out.println(imgText);
//				      } 
//				      catch (TesseractException e) 
//				      {
//				         e.printStackTrace(); 
//				      }
					imageList.add(imageInfo);
				}

				System.out.println("CONVERTER END.....");
				System.out.println("IMAGE SAVED AT -> " + destinationFile.getAbsolutePath());
				//	            return fileList;
			} else {
				System.err.println(inputFile.getName() + " FILE DOES NOT EXIST");
			}
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			if(doc != null) {
				try {
					doc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return imageList;
		}
	}
	
	public static List<NewsBoardDetailsVO> extractTextFromPDF4News(File inputFile, String destinationDir,int contentId) {
		List<NewsBoardDetailsVO> imageList = new ArrayList<NewsBoardDetailsVO>();
		try {

			//			System.out.println("isFile :"+convFile.isFile);
			File destinationFile = new File(destinationDir);
			
			NewsBoardDetailsVO imageInfo = null;
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("DESTINATION FOLDER CREATED -> " + destinationFile.getAbsolutePath());
			}else if(destinationFile.exists()){
				System.out.println("DESTINATION FOLDER ALLREADY CREATED!!!");
			}else{
				System.out.println("DESTINATION FOLDER NOT CREATED!!!");
			}

			if (inputFile != null) {
				System.out.println("newCreateFile :"+inputFile);
				PDDocument doc = PDDocument.load(inputFile);
				PDFRenderer renderer = new PDFRenderer(doc);
				List<File> fileList = new ArrayList<File>();
				
				String newFileName = CommonUtils.padLeftZeros(contentId+"",10);
				String fileName = inputFile.getName().replace(".pdf", "");
				System.out.println("CONVERTER START.....");
				PDFTextStripper pdfStripper = new PDFTextStripper();
				
				for (int i = 0; i < doc.getNumberOfPages(); i++) {
					// default image files path: original file path
					// if necessary, file.getParent() + "/" => another path
					imageInfo = new NewsBoardDetailsVO();
					File fileTemp = new File(destinationDir +File.separator+ newFileName + "_" + i + ".jpg"); // jpg or png
					imageInfo.setContentId(contentId);
					imageInfo.setFileName(newFileName + "_" + i + ".jpg");
					imageInfo.setContIndex(i);

					BufferedImage image = renderer.renderImageWithDPI(i, 200);
					// 200 is sample dots per inch.
					// if necessary, change 200 into another integer.
					ImageIO.write(image, "JPEG", fileTemp); // JPEG or PNG
					imageList.add(imageInfo);
//					doc.getPage(i);
					pdfStripper.setStartPage(i);
					pdfStripper.setEndPage(i);
					String pageText = pdfStripper.getText(doc);
					
					System.out.println(pageText);
				}
				doc.close();
				System.out.println("CONVERTER STOPTED.....");
				System.out.println("IMAGE SAVED AT -> " + destinationFile.getAbsolutePath());
				//	            return fileList;
			} else {
				System.err.println(inputFile.getName() + " FILE DOES NOT EXIST");
			}
			return imageList;
		} catch(Exception e) {
			e.printStackTrace();
			return imageList;
		}
	}
	
//	public static List<AnnounceBoardDetailsVO> pdf2Svg(File inputFile, String destinationDir,int contentId) {
//		PDDocument document = PDDocument.load( inputFile );
//		DOMImplementation domImpl =  GenericDOMImplementation.getDOMImplementation();
//		PrinterJob printerJob = PrinterJob.getPrinterJob();
//		String svgNS = "http://www.w3.org/2000/svg";
//		Document svgDocument = domImpl.createDocument(svgNS, "svg", null);
//		SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(svgDocument);
//		ctx.setEmbeddedFontsOn(true);
//		String test  = "";
//		for(int i = 0 ; i < document.getNumberOfPages() ; i++){
//			String svgFName = destinationDir+"page"+i+".svg";
//			(new File(svgFName)).createNewFile();
//			SVGGraphics2D svgGenerator = new SVGGraphics2D(ctx,false);
//			Printable page  = document.getPrintable(i);
//			page.print(svgGenerator, new PDPageable(document, printerJob).getPageFormat(i), i);
//
//			Writer out = new StringWriter();
//			//Writer out = new OutputStreamWriter(new FileOutputStream(svgFName), "UTF-8"); 파일 저장
//			svgGenerator.stream(out, true);
//
//			System.out.println(out.toString()); //문자열 출력
//		}
//
//		return false;
//	}
}
