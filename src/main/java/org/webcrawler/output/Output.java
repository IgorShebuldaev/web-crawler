package org.webcrawler.output;

import org.webcrawler.data.Stats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Output {
    CSV csv;

    public Output(String filename) throws IOException {
        csv = new CSV(filename);
    }

    public void writeHeader(String[] headers) throws IOException {
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
