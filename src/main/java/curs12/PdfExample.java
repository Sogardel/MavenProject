package curs12;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfExample {

	public static void main(String[] args) throws IOException {

		File file = new File("sample.pdf");
		
		PDDocument document  = PDDocument.load(file);
		PDFTextStripper pdfStrier = new PDFTextStripper();
		String text = pdfStrier.getText(document);
		System.out.println(text);
		
		document.close();
		
		System.out.println("---------------------------------------");
				
		System.out.println(readLineFromPdf("sample.pdf", "Oh", "stuff"));
	}

	public static String readLineFromPdf(String path, String inceput, String finish) throws IOException {
		String returnString = "";
		
		//readLineFromPdf("Sample.pdf", "Simple" )
		
		PDDocument document  = PDDocument.load(new File(path));
		
		PDFTextStripper pdfStriper =  new PDFTextStripper();
		String alltext = pdfStriper.getText(document);
		String strStart =  inceput;
		String strEnd = finish;
		
		int startIndex = alltext.indexOf(strStart);
		int endIndex =  alltext.indexOf(strEnd);
		
		returnString = alltext.substring(startIndex, endIndex)+strEnd;
		
		document.close();
		
		
		return returnString;
		
	}
	
	
}
