package org.webcrawler.utils;

public class Config {
    private String fileName = "output.csv";
    private String link;
    private int depth = 8, pageLimit = 10000;
    private String[] terms;

    public String getFileName() {
        return fileName;
    }

    public String getLink() {
        return link;
    }

    public int getDepth() {
        return depth;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public String[] getTerms() {
        return terms;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName + ".csv";
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public void setTerms(String[] terms) {
        this.terms = terms;
    }


}
