package views;

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
import java.util.List;

public class PrintDistributedController {
    private static PrintDistributedController printDistributedController;
    private int number;

    private PrintDistributedController() {
        number = 0;
    }

    public static PrintDistributedController getInstant(){
        if (printDistributedController == null){
            printDistributedController = new PrintDistributedController();
        }

        return printDistributedController;
    }

    public void printPDF(List<Farmer> farmers, String seed, double amount,String unit) {
        System.out.println("in PrintPDF");
        System.out.println("number = " + number);
//        TabSearchView tabSearchView;
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm");

        try {
            File file = new File("./PDF/Manufactory/manufactory.pdf");
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
            contentStream.showText(String.format("ผลิต : %20s จำนวน : %,.2f %s",StringUtils.center(seed,20),amount,unit));
            contentStream.newLine();
            contentStream.showText("------------------------------"+"------------------------------"+
                    "------------------------------"+"------------------------------");
            contentStream.newLine();
            String id = "ID";
            String name = "ชื่อ";
            String capacity = "กำลังการผลิต";
            String group = "กลุ่ม";
            contentStream.showText(String.format("%15s%20s%40s%15s",StringUtils.center(group,15),
                    StringUtils.center(id,20), StringUtils.center(name,40),
                    StringUtils.center(capacity,10)
                    ));
            contentStream.newLine();
            contentStream.showText("------------------------------"+"------------------------------"+
                    "------------------------------"+"------------------------------");
            contentStream.newLine();
            for(int i = 0; i < farmers.size(); i++) {
                    String farmerID = String.valueOf(farmers.get(i).getFarmer_id());
                    String farmerName = farmers.get(i).getName();
                    String farmerCapacity = String.valueOf(farmers.get(i).getCapacity_area());
                    String farmerGroup = farmers.get(i).getGroup();
                    contentStream.showText(String.format("%15s%20s%-40s%15s", StringUtils.center(farmerGroup,15),
                            StringUtils.center(farmerID, 20),
                            farmerName, StringUtils.center(farmerCapacity, 10)));
                    contentStream.newLine();

            }
            contentStream.endText();
            System.out.println("Content added");
            contentStream.close();
            document.save(new File("./PDF/Manufactory/" + "DistributedList" + number +".pdf"));
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
