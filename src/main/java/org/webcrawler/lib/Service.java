package org.webcrawler.lib;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service implements IService {
    private String link;
    private Document page;

    @Override
    public void setLink(String link) {
        this.link = link;
        page = null;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public String getBody() {
        return page.body().text();
    }

    @Override
    public List<String> getLinks() {
        List<String> linksList = new ArrayList<>();

        Elements links = page.select("a[href]");

        for (Element link : links) {
            linksList.add(link.attr("abs:href"));
        }

        return linksList;
    }

    @Override
    public boolean successful() {
        if (page == null) {
            try {
                page = Jsoup.connect(link).userAgent("Mozilla/5.0").followRedirects(true).get();
                return true;
            } catch (IOException | IllegalArgumentException ignored) {
                return false;
            }
        } else {
            return true;
        }
    }
}
