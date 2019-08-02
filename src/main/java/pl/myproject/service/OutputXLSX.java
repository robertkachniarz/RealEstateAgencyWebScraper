package pl.myproject.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.myproject.model.RealEstateAgency;
import pl.myproject.repository.AgencyRepository;

import java.io.FileOutputStream;
import java.io.IOException;

public class OutputXLSX {
    private static String FILE = "src/main/resources/output.xlsx";
    private static String[] headers = {"Nazwa agencji", "Adres", "Telefon"};

    public static void createFileXLSX(AgencyRepository repository) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Agencje Nieruchomości");

        Font headerFont = workbook.createFont();
        //headerFont.setFontHeight((short) 20);
        headerFont.setBold(true);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for(RealEstateAgency agency: repository.getAgenciesRepository()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(agency.getName());
            row.createCell(1).setCellValue(agency.getAddress());
            row.createCell(2).setCellValue(agency.getPhone());
        }

        for(int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(FILE);
        workbook.write(fileOut);
        fileOut.close();

        workbook.close();
    }
}
