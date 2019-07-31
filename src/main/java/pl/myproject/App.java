package pl.myproject;

import org.jsoup.nodes.Element;
import pl.myproject.model.WebsiteDownloader;
import pl.myproject.service.DataExtractService;
import java.util.List;

public class App {
    public static void start() {
        String url = "https://tabelaofert.pl";
        WebsiteDownloader downloader = new WebsiteDownloader("https://tabelaofert.pl/indeks-agencji");
        List<String> letters = DataExtractService.getTheSmallestTextFromSource(downloader.getDoc(),"paginator","a[href]","href");
        List<String> agenciesUrl;

        for (String str:letters) {
            String url2 = url + str;
            WebsiteDownloader downloader2 = new WebsiteDownloader(url2);
            agenciesUrl = DataExtractService.getTheSmallestTextFromSource(downloader2.getDoc(),"firma-item","a","href");

            for (String addr:agenciesUrl) {
                String url3 = url + addr;
                WebsiteDownloader downloadAgencySite = new WebsiteDownloader(url3);
                try {
                    Element element = downloadAgencySite.getDoc().getElementsByClass("oddzial centrala").first();
                    System.out.println(element.select("h3").toString().split("<br>")[0].split("<h3>")[1]);
                    System.out.println(element.select("h3").toString().split("<br>")[1].split("</h3>")[0]);
                    System.out.println(element.getElementsByClass("telefon-ukryty").attr("data-telefon"));
                    System.out.println("###############################################################");
                } catch (NullPointerException e){
                    System.out.println("Nie znalaziono elementu 'oddzial centrala' pod adresem " + url3);
                    System.out.println("###############################################################");
                }
            }
        }

    }
}
