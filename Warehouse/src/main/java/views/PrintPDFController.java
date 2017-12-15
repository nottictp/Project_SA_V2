package views;

import models.Warehouse;
import models.WarehouseProduct;
import models.WarehouseSeed;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class PrintPDFController {
    private static PrintPDFController printPDFController;
    private int number;

     private PrintPDFController() {
        number = 0;
    }

    public static PrintPDFController getInstant(){
         if (printPDFController == null){
             printPDFController = new PrintPDFController();
         }

         return printPDFController;
    }

    public void printPDF(List<Warehouse> warehouses) {
        System.out.println("in PrintPDF");
        System.out.println("number = " + number);
//        TabSearchView tabSearchView;
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm");

        try {
            File file = new File("./PDF/Warehouse/warehouse.pdf");
            PDDocument document = PDDocument.load(file);

            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setLeading(14.5f);
            contentStream.setFont(PDType0Font.load(document, new File("c:/windows/fonts/THsarabun.ttf")), 12);
            contentStream.newLineAtOffset(10, 700);

            LocalDateTime localDateTime = LocalDateTime.now();
            Instant instant = Instant.from(localDateTime.atZone(ZoneId.systemDefault()));
            contentStream.showText(String.format("%130s", StringUtils.center("LittleBearWarehouse", 130)));
            contentStream.newLine();
            contentStream.showText("------------------------------"+"------------------------------"+
                    "------------------------------"+"------------------------------");
            contentStream.newLine();
            String id = "รหัสสินค้า";
            String name = "ชื่อสินค้า";
            String quantity = "จำนวน";
            String unit = "หน่วย";
            String docDate = "วันที่เอกสาร";
            contentStream.showText(String.format("%20s%40s%15s%15s%15s",StringUtils.center(id,20),
                    StringUtils.center(name,40),StringUtils.center(quantity,10),
                    StringUtils.center(unit,15),StringUtils.center(docDate,15)));
            contentStream.newLine();
            contentStream.showText("------------------------------"+"------------------------------"+
                    "------------------------------"+"------------------------------");
            contentStream.newLine();
            for(int i = 0; i < warehouses.size(); i++) {
                if (warehouses.get(i) instanceof WarehouseSeed){
                    WarehouseSeed whs = (WarehouseSeed) warehouses.get(i);
                    String whsID = whs.getSeedId();
                    String whsName = whs.getName();
                    int whsQuantity = whs.getQuantity();
                    String whsUnit = whs.getUnit();
                    String whsDocDate = whs.getDocDate();
                    contentStream.showText(String.format("%20s%-40s%10s%15s%15s", StringUtils.center(whsID, 20),
                            whsName, StringUtils.center(whsQuantity+"", 10),
                            StringUtils.center(whsUnit, 15), StringUtils.center(whsDocDate, 15)));
                    contentStream.newLine();
                }else {
                        WarehouseProduct whp = (WarehouseProduct) warehouses.get(i);
                        String whpID = whp.getProductId();
                        String whpName = whp.getName();
                        int whpQuantity = whp.getQuantity();
                        String whpUnit = whp.getUnit();
                        String whpDocDate = whp.getDocDate();
                        contentStream.showText(String.format("%20s%-40s%10s%15s%15s", StringUtils.center(whpID, 20),
//                                StringUtils.center(whpName, 10),
                                whpName,
                                StringUtils.center(whpQuantity+"", 10),
                                StringUtils.center(whpUnit,15), StringUtils.center(whpDocDate,15)));
                        contentStream.newLine();
                }
            }
            contentStream.endText();
            System.out.println("Content added");
            contentStream.close();
            document.save(new File("./PDF/Warehouse/" + "PackingList" + number +".pdf"));
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

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
