package pl.myproject.service;

import org.jsoup.nodes.Element;
import pl.myproject.model.RealEstateAgency;
import pl.myproject.model.WebsiteDownloader;
import pl.myproject.repository.AgencyRepository;

import java.util.List;

public class ExtractionFromTabelaOfertPlWebsite {
    private static String url = "https://tabelaofert.pl";

    public static AgencyRepository extractData(){
        AgencyRepository repository = new AgencyRepository();
        WebsiteDownloader downloader = new WebsiteDownloader(url + "/indeks-agencji");
        List<String> letters = DataExtractService.getTheSmallestTextFromSource(downloader.getDoc(),"paginator","a[href]","href");
        List<String> agenciesUrl;
        int counter = 0;

        for (String str:letters) {
            String url2 = url + str;
            WebsiteDownloader downloader2 = new WebsiteDownloader(url2);
            agenciesUrl = DataExtractService.getTheSmallestTextFromSource(downloader2.getDoc(),"firma-item","a","href");

            for (String addr:agenciesUrl) {
                String url3 = url + addr;
                WebsiteDownloader downloadAgencySite = new WebsiteDownloader(url3);
                try {
                    Element element = downloadAgencySite.getDoc().getElementsByClass("oddzial centrala").first();
                    String agencyName = element.select("h3").toString().split("<br>")[0].split("<h3>")[1];
                    String agencyAddress = element.select("h3").toString().split("<br>")[1].split("</h3>")[0].replace("&nbsp;", " ");
                    String agencyPhone = element.getElementsByClass("telefon-ukryty").attr("data-telefon");
                    RealEstateAgency realEstateAgency = new RealEstateAgency(agencyName, agencyAddress, agencyPhone);
                    repository.addAgency(realEstateAgency);
                    counter++;
                    System.out.println("Pobrano dane: " + counter + " agencji nieruchomości.");
                } catch (NullPointerException e){
                    System.out.println("Nie znalaziono elementu 'oddzial centrala' pod adresem " + url3);
                }
            }
        }
        return repository;
    }
}
