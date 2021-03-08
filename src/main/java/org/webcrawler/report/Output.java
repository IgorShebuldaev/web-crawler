package org.webcrawler.report;

import org.webcrawler.data.Results;
import org.webcrawler.data.Stats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Output {
    private CSV csv;
    private ArrayList<String> headers;
    private Results results;

    public Output(String filename, String[] headers, Results results) throws IOException {
        this.csv = new CSV(filename);
        this.headers = new ArrayList<>(Arrays.asList(headers));
        this.results = results;
    }

    public void makeReport() {
        try {
            headers.add(0, "Url");
            writeHeader(headers);
            writeResults(results.values());
            finish();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void writeHeader(ArrayList<String> headers) throws IOException {
        for(var header : headers) {
            csv.write(header);
        }
        csv.newLine();
    }

    public void writeResults(ArrayList<Stats> results, int limit) throws IOException {
        writeResults(results.stream().limit(limit).collect(Collectors.toCollection(ArrayList::new)));
    }

    public void writeResults(ArrayList<Stats> results) throws IOException {
        for (var result : results) {
            csv.write(result.getUrl());

            for(var stats : result.getStatistics()) {
                csv.write(stats.toString());
            }
            csv.newLine();
        }
    }

    public void finish() throws IOException {
        csv.close();
    }
}
