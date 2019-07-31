package pl.myproject;

import org.assertj.core.api.Assertions;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import pl.myproject.model.WebsiteDownloader;
import pl.myproject.service.DataExtractService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataExtractServiceTest {
    private Document document;

    @Before
    public void setup(){
        WebsiteDownloader websiteDownloader = new WebsiteDownloader("https://tabelaofert.pl/indeks-agencji");
        document = websiteDownloader.getDoc();
    }

    @Test
    public void shouldReturnListOfPagins() {
        //given
        String param = "paginator";

        //when
        Elements pagins = DataExtractService.getElementsByClass(document, param);

        //then
        Elements paginsCheck = document.getElementsByClass("paginator");
        Assertions.assertThat(pagins).isEqualTo(paginsCheck);
    }

    @Test
    public void shouldReturnElementsFromWebsiteFromOtherElements(){
        //given
        String param = "paginator";
        Elements pagins = DataExtractService.getElementsByClass(document, param);

        String param2 = "a[href]";

        //when
        Elements elements = DataExtractService.getElementsFromElements(pagins,param2);

        //then
        Assertions.assertThat(elements).isEqualTo(pagins.select("a[href]"));
    }

    @Test
    public void shouldReturnListOfStringsFromElements(){
        //given
        Elements pagins = DataExtractService.getElementsByClass(document, "paginator");
        Elements elements = DataExtractService.getElementsFromElements(pagins,"a[href]");

        //when
        List<String> letters = DataExtractService.getTextFromElements(elements);

        //then
        Assertions.assertThat(letters).isEqualTo(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "Ł", "M", "N", "O", "P", "R", "S", "Ś", "T", "U", "V", "W", "Y", "Z", "0-9"));
    }
}
