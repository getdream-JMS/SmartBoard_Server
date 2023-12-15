package com.jms.dboard.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class PDFParserTextStripper extends PDFTextStripper {
	public PDFParserTextStripper(PDDocument pdd) throws IOException 
    {  
        super();
        document = pdd;
    }

    public void stripPage(int pageNr) throws IOException 
    {
        this.setStartPage(pageNr+1);
        this.setEndPage(pageNr+1);
        Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
        writeText(document,dummy); // This call starts the parsing process and calls writeString repeatedly.
    }

    @Override
    protected void writeString(String string,List<TextPosition> textPositions) throws IOException 
    {
        for (TextPosition text : textPositions) {
            System.out.println("String[" + text.getXDirAdj()+","+text.getYDirAdj()+" fs="+text.getFontSizeInPt()+" xscale="+text.getXScale()+" height="+text.getHeightDir()+" space="+text.getWidthOfSpace()+" width="+text.getWidthDirAdj()+" ] "+text.getUnicode());
        }
    }

    public static void extractText(InputStream inputStream)
    {
        PDDocument pdd = null;

        try 
        {
            pdd = PDDocument.load(inputStream);
            PDFParserTextStripper stripper = new PDFParserTextStripper(pdd);
            stripper.setSortByPosition(true);
            for (int i=0; i<pdd.getNumberOfPages(); i++)
            {
                stripper.stripPage(i);
            }
        } 
        catch (IOException e) 
        {
            // throw error
        } 
        finally 
        {
            if (pdd != null) 
            {
                try 
                {
                    pdd.close();
                } 
                catch (IOException e) 
                {

                }
            }
        }
    }
}