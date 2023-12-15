package com.jms.dboard.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@CrossOrigin("*")
@RestController
public class UploadFileController {

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public String uploadFile(@RequestPart("uploadFile") MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)
	{
		try {		
			//			CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFile;
			//		    FileItem fileItem = commonsMultipartFile.getFileItem();
			//		    DiskFileItem diskFileItem = (DiskFileItem) fileItem;
			//		    String absPath = diskFileItem.getStoreLocation().getAbsolutePath();
			//		    File inputFile = new File(absPath);
			String destinationDir = "D://DBoard_Test//"; // converted images from pdf document are saved here
			File newCreateFile = new File(destinationDir+multipartFile.getOriginalFilename());
			multipartFile.transferTo(newCreateFile);
			String fileType = Files.probeContentType(newCreateFile.toPath());
//			System.out.println(fileType);
			if(fileType.equals("application/pdf")) {			
				pdf2Image(newCreateFile);
			} else if(fileType.equals("application/msword") || fileType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {			
				doc2Image(newCreateFile);
			}
			//			return new ResponseEntity<>("", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			//			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return "/";
	} // getTransServerId()	

	private boolean pdf2Image(File inputFile) {
		try {
			String destinationDir = "D://DBoard_Test//"; // converted images from pdf document are saved here

			//			System.out.println("isFile :"+convFile.isFile);
			File destinationFile = new File(destinationDir);

			if (!destinationFile.exists()) {
				destinationFile.mkdir();
//				System.out.println("DESTINATION FOLDER CREATED -> " + destinationFile.getAbsolutePath());
			}else if(destinationFile.exists()){
//				System.out.println("DESTINATION FOLDER ALLREADY CREATED!!!");
			}else{
				System.out.println("DESTINATION FOLDER NOT CREATED!!!");
			}

			if (inputFile != null) {
				System.out.println("newCreateFile :"+inputFile);
				PDDocument doc = PDDocument.load(inputFile);
				PDFRenderer renderer = new PDFRenderer(doc);
				List<File> fileList = new ArrayList<File>();

				String fileName = inputFile.getName().replace(".pdf", "");
				System.out.println("CONVERTER START.....");

				for (int i = 0; i < doc.getNumberOfPages(); i++) {
					// default image files path: original file path
					// if necessary, file.getParent() + "/" => another path
					File fileTemp = new File(destinationDir + fileName + "_" + i + ".jpg"); // jpg or png
					BufferedImage image = renderer.renderImageWithDPI(i, 200);
					// 200 is sample dots per inch.
					// if necessary, change 200 into another integer.
					ImageIO.write(image, "JPEG", fileTemp); // JPEG or PNG
					fileList.add(fileTemp);
				}
				doc.close();
				System.out.println("CONVERTER STOPTED.....");
				System.out.println("IMAGE SAVED AT -> " + destinationFile.getAbsolutePath());
				//	            return fileList;
				return true;
			} else {
				System.err.println(inputFile.getName() + " FILE DOES NOT EXIST");
			}
			return false;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private boolean doc2Image(File inputFile) {
		//		File outputFile = new File("D:/Workspace/kbase-doc/target/classes/static/DATAS/1512561737109/" + Calendar.getInstance().getTimeInMillis() + ".docx");
		//		if (!outputFile.exists()){
		//			outputFile.createNewFile();
		//		}
		System.out.println("doc2Image");
		String destinationDir = "D://DBoard_Test//"; // converted images from pdf document are saved here

		//		System.out.println("isFile :"+convFile.isFile);
		File destinationFile = new File(destinationDir);

		if (!destinationFile.exists()) {
			destinationFile.mkdir();
			System.out.println("DESTINATION FOLDER CREATED -> " + destinationFile.getAbsolutePath());
		}else if(destinationFile.exists()){
			System.out.println("DESTINATION FOLDER ALLREADY CREATED!!!");
		}else{
			System.out.println("DESTINATION FOLDER NOT CREATED!!!");
		}
		long start = System.currentTimeMillis();
		try {
				InputStream is = new FileInputStream(inputFile);
				
			
			
				POIFSFileSystem fs = null;  
				Document document = new Document();

				try {  
					fs = new POIFSFileSystem(is);  

					HWPFDocument doc = new HWPFDocument(fs);  
					WordExtractor we = new WordExtractor(doc);  



					
					OutputStream out = new FileOutputStream(new File(destinationDir+"rdtschools-Docx2PdfConverted_PDF_File.pdf"));
					PdfWriter writer = PdfWriter.getInstance(document, out);  
		            we.close();
					Range range = doc.getRange();
					document.open();  
					writer.setPageEmpty(true);  
					document.newPage();  
					writer.setPageEmpty(true);  

					String[] paragraphs = we.getParagraphText();  
					for (int i = 0; i < paragraphs.length; i++) {  

						org.apache.poi.hwpf.usermodel.Paragraph pr = range.getParagraph(i);
						// CharacterRun run = pr.getCharacterRun(i);
						// run.setBold(true);
						// run.setCapitalized(true);
						// run.setItalic(true);
						paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n", "");  

						// add the paragraph to the document  
						document.add(new Paragraph(paragraphs[i]));  
					}  

				} catch (Exception e) {  
					e.printStackTrace();  
				} finally {  
					// close the document  
					document.close();  
				}  
			 
		} catch (Exception e) {
			try {
				InputStream is = new FileInputStream(inputFile);
				OutputStream out = new FileOutputStream(new File("rdtschools-Docx2PdfConverted_PDF_File.pdf"));
				System.out.print("Its not a .doc format");
				// 1) Load DOCX into XWPFDocument
				XWPFDocument document = new XWPFDocument(is);
				// 2) Prepare Pdf options
				PdfOptions options = PdfOptions.create();
				// 3) Convert XWPFDocument to Pdf
				PdfConverter.getInstance().convert(document, out, options);
				System.out.println("rdtschools-Docx2PdfConversion-word-sample.docx was converted to a PDF file in :: "
						+ (System.currentTimeMillis() - start) + " milli seconds");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}

	return true;
}

}
