package views;

import controllers.MainController;
import models.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PrintController {
    private static PrintController printController;
    private MainController mainController;
    private int number;

    private PrintController(){
        number=0;
    }

    public static PrintController getInstant(){
        if (printController == null){
            printController = new PrintController();
        }

        return printController;
    }

    public void printManufactureScript(String productID,
                                 String productName,String productUnit,int productQuantity) {

        try {
            File file = new File("./PDF/Marketing/marketing.pdf");
            PDDocument document = PDDocument.load(file);

            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setLeading(14.5f);
            contentStream.setFont(PDType0Font.load(document,
                    new File("c:/windows/fonts/THsarabun.ttf")), 12);
            contentStream.newLineAtOffset(10, 700);

            LocalDateTime localDateTime = LocalDateTime.now();
            Instant instant = Instant.from(localDateTime.atZone(ZoneId.systemDefault()));

            contentStream.showText(String.format("%130s", StringUtils.center("LittleBearWarehouse", 130)));
            contentStream.newLine();

            contentStream.showText(String.format("%130s", StringUtils.center("ใบสั่งผลิต", 130)));
            contentStream.newLine();

            contentStream.showText(String.format("รหัสสินค้า : %20s%40s", productID,productName));
            contentStream.newLine();
            contentStream.showText(String.format("จำนวน : %20s%20s", productQuantity,productUnit));
            contentStream.newLine();

            DataToMarketing seedRatio = mainController.getSeedRatio(productID);
            int totalFather = seedRatio.getFatherQuantity();
            int totalMother = seedRatio.getMotherQuantity();
            String nameFather = seedRatio.getFatherName();
            String nameMother = seedRatio.getMotherName();
            System.out.println("nameMother = " + nameMother);
            System.out.println("nameFather = " + nameFather);
            System.out.println("totalMother = " + totalMother);
            System.out.println("nameFather = " + nameFather);
            contentStream.showText(String.format("-----------"+"-----------"+
                    "-----------"+"-----------"+"-----------"));
            contentStream.newLine();
            String nameType = "รายการสินค้า";
            String unitType = "หน่วย";
            String quantityType = "จำนวน";
            contentStream.showText(String.format("%40s%15s%10s",nameType, unitType,quantityType));
            contentStream.newLine();
            contentStream.showText(String.format("-----------"+"-----------"+
                    "-----------"+"-----------"+"-----------"));
            contentStream.newLine();
            contentStream.showText(String.format("%-40s%15s%10s",
                    nameFather, productUnit, totalFather));
            contentStream.newLine();
            contentStream.showText(String.format("%-40s%15s%10s",
                    nameMother,productUnit,totalMother));
            contentStream.newLine();

            contentStream.endText();
            contentStream.close();
            document.save(new File("./PDF/Marketing/" + "MarketingList" + number +".pdf"));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static class StringUtils {

        public static String center(String s, int size) {
            return center(s, size, ' ');
        }

        public static String center(String s, int size, char pad) {
            if (s == null || size <= s.length())
                return s;

            StringBuilder sb = new StringBuilder(size);
            for (int i = 0; i < (size - s.length()) / 2; i++) {
                sb.append(pad);
            }
            sb.append(s);
            while (sb.length() < size) {
                sb.append(pad);
            }
            return sb.toString();
        }

    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

