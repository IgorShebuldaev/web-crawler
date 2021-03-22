package org.webcrawler.lib;

import java.util.List;

public interface IHTMLFetcher {
    boolean successful();
    String getLink();
    String getBody();
    List<String> getLinks();
    void setLink(String link);
}
