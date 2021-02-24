package org.webcrawler.report;

import org.webcrawler.data.Results;
import org.webcrawler.data.Stats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Output {
    private CSV csv;
    private List<String> headers;
    private Results results;

    public Output(String filename, String[] headers, Results results) throws IOException {
        this.csv = new CSV(filename);
        this.headers = Arrays.asList(headers);
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

    public void writeHeader(List<String> headers) throws IOException {
        for(var header : headers) {
            csv.write(header);
        }
        csv.newLine();
    }

    public void writeResults(ArrayList<Stats> results, int limit) throws IOException {
        writeResults(results.stream().limit(limit).collect(Collectors.toCollection(ArrayList::new)));
    }

    public void printTopResults(ArrayList<Stats> results, int limit) {
        ArrayList<Stats> stats = results.stream().limit(limit).collect(Collectors.toCollection(ArrayList::new));
        for (var links : stats) {
            System.out.print(links.url + " ");
            links.statistics.forEach((n) -> System.out.print(n + " "));
            System.out.println();
        }
    }

    public void writeResults(ArrayList<Stats> results) throws IOException {
        for (var result : results) {
            csv.write(result.url);

            for(var stats : result.statistics) {
                csv.write(stats.toString());
            }
            csv.newLine();
        }
    }

    public void finish() throws IOException {
        csv.close();
    }
}
