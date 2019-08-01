package pl.myproject.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import pl.myproject.model.RealEstateAgency;
import pl.myproject.repository.AgencyRepository;

import java.io.FileOutputStream;

public class OutputPDF {
    private static String FILE = "src/main/resources/output.pdf";
    private static Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);

    public static void createFile(AgencyRepository repository){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            document.add(new Chunk());
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

        PdfPCell c1 = new PdfPCell(new Phrase("Nazwa agencji"));
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Adres"));
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Telefon"));
        table.addCell(c1);

        for (RealEstateAgency agency:repository.getAgenciesRepository()) {
            table.addCell(agency.getName());
            table.addCell(agency.getAddress());
            table.addCell(agency.getPhone());
        }

        document.add(table);
    }
}
