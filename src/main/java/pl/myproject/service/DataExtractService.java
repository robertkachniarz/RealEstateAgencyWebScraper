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

    public static List<String> getAttributesFromElements(Elements elements, String attribute) {
        List<String> words = new ArrayList<>();
        for (Element e:elements) {
            words.add(e.attr(attribute));
        }
        return words;
    }

    public static List<String> getTheSmallestTextFromSource(Document document, String parameter1, String parameter2, String attribute){
        List<String> result = getAttributesFromElements(getElementsFromElements(getElementsByClass(document,parameter1),parameter2),attribute);
        return result;
    }
}
