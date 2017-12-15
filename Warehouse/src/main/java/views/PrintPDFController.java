package views;

import models.Warehouse;
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
import java.util.List;

import static javax.swing.text.html.HTML.Tag.HEAD;

public class PrintPDFController {
    public static void createBill(List<Warehouse> warehouses) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy  HH:mm");

        try {
            File file = new File("D:\\Project\\Project_SA_V2\\PDF\\Warehouse\\warehouse.pdf");
            PDDocument document = PDDocument.load(file);

            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setLeading(14.5f);
            contentStream.setFont(PDType0Font.load(document, new File("c:/windows/fonts/TH SarabunPSK.ttf")), 12);
            contentStream.newLineAtOffset(10, 700);

            LocalDateTime localDateTime = LocalDateTime.now();
            Instant instant = Instant.from(localDateTime.atZone(ZoneId.systemDefault()));
            contentStream.showText(String.format("%100s", StringUtils.center("LittleBearWarehouse", 100)));
            contentStream.newLine();
            int warehouseSize = warehouses.size();
            for (Warehouse wh:
                 ) {
                
            }

        } catch (IOException e) {

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
}
