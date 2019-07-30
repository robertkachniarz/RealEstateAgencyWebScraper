package pl.myproject.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DataExtractService {
    public static Elements getElementsByClass(Document document, String param) {
        return document.getElementsByClass(param);
    }
    public static Elements getElementsFromElements(Elements elements, String param){
        return elements.select(param);
    }
    public static List<String> getTextFromElements(Elements elements){
        List<String> words = new ArrayList<>();
        for (Element e:elements) {
            words.add(e.text());
        }
        return words;
    }
}
