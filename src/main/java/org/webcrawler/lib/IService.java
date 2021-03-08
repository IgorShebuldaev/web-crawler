package org.webcrawler.lib;

import java.util.List;

public interface IService {
    void setLink(String link);
    String getLink();
    String getBody();
    List<String> getLinks();
    boolean successful();
}
