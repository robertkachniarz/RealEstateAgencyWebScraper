package pl.myproject.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebsiteDownloader {
    private String url;
    private Document doc;

    public WebsiteDownloader(String url) {
        try {
            this.doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Nie pobrało poprawnie strony");
        }
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
}
