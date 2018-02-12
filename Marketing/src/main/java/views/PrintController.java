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
    private int numberSuccess;
    private int numberFail;

    private PrintController(){
        numberSuccess=0;
        numberFail=0;
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

            contentStream.showText(String.format("%50s", StringUtils.center("บริษัท ทีเอสเอ จำกัด", 50)));
            contentStream.newLine();

            contentStream.showText(String.format("%50s", StringUtils.center("ใบสั่งผลิต", 50)));
            contentStream.newLine();

            contentStream.showText(String.format("รหัสสินค้า : %10s %30s", productID,productName));
            contentStream.newLine();
            contentStream.showText(String.format("จำนวน : %10s%10s", productQuantity,"กิโลกรัม"));
            contentStream.newLine();

            System.out.println(mainController);
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
                    "-----------"+"-----------"+"-----------"+"-----------"));
            contentStream.newLine();
            String nameType = "รายการสินค้า";
            String unitType = "หน่วย";
            String quantityType = "จำนวน";
            contentStream.showText(String.format("%25s%30s%10s",nameType, unitType,quantityType));
            contentStream.newLine();
            contentStream.showText(String.format("-----------"+"-----------"+
                    "-----------"+"-----------"+"-----------"+"-----------"));
            contentStream.newLine();
            contentStream.showText(String.format("%-40s%15s%10s",
                    nameFather, "กิโลกรัม", productQuantity));
            contentStream.newLine();
            contentStream.showText(String.format("%-40s%15s%10s",
                    nameMother,"กิโลกรัม",productQuantity));
            contentStream.newLine();

            contentStream.endText();
            contentStream.close();
            document.save(new File("./PDF/Marketing/" + "MarketingList" + numberSuccess +".pdf"));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printManufactureFailScript(String productID,
                                           String productName,int productQuantity,int checkMother,int checkFather){
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

            contentStream.showText(String.format("%50s", StringUtils.center("บริษัท ทีเอสเอ จำกัด", 50)));
            contentStream.newLine();

            contentStream.showText(String.format("%50s", StringUtils.center("ใบสั่งผลิต", 50)));
            contentStream.newLine();

            contentStream.showText(String.format("รหัสสินค้า : %10s %30s", productID,productName));
            contentStream.newLine();
            contentStream.showText(String.format("จำนวน : %10s%10s", productQuantity,"กิโลกรัม"));
            contentStream.newLine();

            System.out.println(mainController);
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
                    "-----------"+"-----------"+"-----------"+"-----------"));
            contentStream.newLine();
            String nameType = "รายการสินค้า";
            String unitType = "หน่วย";
            String quantityType = "จำนวน";
            contentStream.showText(String.format("%25s%30s%10s",nameType, unitType,quantityType));
            contentStream.newLine();
            contentStream.showText(String.format("-----------"+"-----------"+
                    "-----------"+"-----------"+"-----------"+"-----------"));
            contentStream.newLine();
            if (totalFather < checkFather && totalMother >= checkMother){
                contentStream.showText(String.format("%-35s%5s%15s%10s",
                        nameFather," ขาด ", "กิโลกรัม", checkFather-totalFather));
            }else if (totalFather >= checkFather && totalMother < checkMother){
                contentStream.showText(String.format("%-35s%5s%15s%10s",
                        nameMother," ขาด ","กิโลกรัม",checkMother-totalMother));
            }else{
                contentStream.showText(String.format("%-35s%5s%15s%10s",
                        nameFather," ขาด ", "กิโลกรัม", checkFather-totalFather));
                contentStream.newLine();
                contentStream.showText(String.format("%-35s%5s%15s%10s",
                        nameMother," ขาด ","กิโลกรัม",checkMother-totalMother));
            }

            contentStream.newLine();

            contentStream.endText();
            contentStream.close();
            document.save(new File("./PDF/Marketing/" + "MarketingFailList" + numberFail +".pdf"));
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
        System.out.println("set main");
        this.mainController = mainController;
    }

    public int getNumberSuccess() {
        return numberSuccess;
    }

    public void setNumberSuccess(int number) {
        this.numberSuccess = number;
    }

    public int getNumberFail() {
        return numberFail;
    }

    public void setNumberFail(int numberFail) {
        this.numberFail = numberFail;
    }
}

