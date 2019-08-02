package pl.myproject;

import org.jsoup.nodes.Element;
import pl.myproject.model.RealEstateAgency;
import pl.myproject.model.WebsiteDownloader;
import pl.myproject.repository.AgencyRepository;
import pl.myproject.service.DataExtractService;
import pl.myproject.service.ExtractionFromTabelaOfertPlWebsite;
import pl.myproject.service.OutputPDF;
import pl.myproject.service.OutputXLSX;

import java.io.IOException;
import java.util.List;

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
