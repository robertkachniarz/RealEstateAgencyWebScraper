package pl.myproject;

import org.assertj.core.api.Assertions;
import org.jsoup.nodes.Document;
import org.junit.Test;
import pl.myproject.model.WebsiteDownloader;

public class WebsiteDownloaderTest {
    @Test
    public void shouldDownloadWebsiteSource(){
        //given
        String websiteTitle = "Indeks agencji nieruchomości w Polsce";
        String url = "https://tabelaofert.pl/indeks-agencji";
        WebsiteDownloader websiteDownloader = new WebsiteDownloader(url);

        //when
        String title = websiteDownloader.getDoc().title();

        //then
        Assertions.assertThat(title).isEqualTo(websiteTitle);

    }
}
