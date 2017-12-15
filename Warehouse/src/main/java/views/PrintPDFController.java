package views;

import models.Warehouse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class PrintPDFController {
    public static void createBill(Warehouse warehouse, double received){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm");

        try{
            File file = new File("D:\\Project\\Project_SA_V2\\PDF\\Warehouse\\warehouse.pdf");
            PDDocument document = PDDocument.load(file);

            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
        }catch (IOException e){

        }
}
