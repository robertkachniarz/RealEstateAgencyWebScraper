package pl.myproject.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.myproject.model.RealEstateAgency;
import pl.myproject.repository.AgencyRepository;

import java.io.FileOutputStream;
import java.io.IOException;

public class OutputPDF {
    private static String FILE = "src/main/resources/output.pdf";
    private static BaseFont helvetica;
    private static BaseFont helveticaBold;

    static {
        try {
            helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            helveticaBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1250, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static Font font = new Font(helvetica, 12);
    private static Font fontBold = new Font(helveticaBold, 12);
    private static Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
    private static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

    public static void createFile(AgencyRepository repository){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            document.add(new Chunk());
            //document.add(new Paragraph("ąśćńółężź bla", font));
            addContent(document, repository);
            document.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void addContent(Document document, AgencyRepository repository) throws DocumentException {
        document.addTitle("Mój drugi PDF");
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Paragraph("Nazwa agencji", fontBold));
        table.addCell(c1);
        c1 = new PdfPCell(new Paragraph("Adres", fontBold));
        table.addCell(c1);
        c1 = new PdfPCell(new Paragraph("Telefon", fontBold));
        table.addCell(c1);

        for (RealEstateAgency agency:repository.getAgenciesRepository()) {
            table.addCell(new Paragraph(agency.getName(), font));
            table.addCell(new Paragraph(agency.getAddress(), font));
            table.addCell(new Paragraph(agency.getPhone(), font));
        }

        document.add(table);
    }
}
