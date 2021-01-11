package org.webcrawler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CSV {
    Writer writer;

    CSV(String filename) throws IOException {
        writer = new FileWriter(filename);
    }

    public void write(String value) throws IOException {
        writer.append("\"");
        writer.append(value);
        writer.append("\"");
        writer.append(",");
    }

    public void newLine() throws IOException {
        writer.write("\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}
