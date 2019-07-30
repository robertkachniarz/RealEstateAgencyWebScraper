package pl.myproject;

import org.assertj.core.api.Assertions;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import pl.myproject.model.WebsiteDownloader;
import pl.myproject.service.DataExtractService;

public class DataExtractServiceTest {

    @Test
    public void shouldReturnListOfPagins() {
        //given
        String param = "paginator";
        WebsiteDownloader websiteDownloader = new WebsiteDownloader("https://tabelaofert.pl/indeks-agencji");
        Document document = websiteDownloader.getDoc();

        //when
        Elements pagins = DataExtractService.getElementsByClass(document, param);

        //then
        Elements paginsCheck = document.getElementsByClass("paginator");
        Assertions.assertThat(pagins).isEqualTo(paginsCheck);


    }
}
