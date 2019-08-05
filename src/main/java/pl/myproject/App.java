package pl.myproject;

import pl.myproject.repository.AgencyRepository;
import pl.myproject.service.ExtractionFromTabelaOfertPlWebsite;
import pl.myproject.service.OutputPDF;
import pl.myproject.service.OutputXLSX;

import java.io.IOException;

class App {
    static void start() {
        AgencyRepository repository = ExtractionFromTabelaOfertPlWebsite.extractData();

        OutputPDF.createFile(repository);
        try {
            OutputXLSX.createFileXLSX(repository);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
