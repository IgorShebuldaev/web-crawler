package org.webcrawler.parsers;

import org.webcrawler.utils.Config;

import java.util.Arrays;

public class CommandLine {
    private String[] args;
    private String flagFileName = "-f";
    private String flagLink = "-l";
    private String flagDepth = "-d";
    private String flagPageLimit = "-p";
    private String flagTerms = "-t";
    private Config config;

    public CommandLine(String[] args) {
        this.args = args;
        config = new Config();

        parseArguments();
    }

    public Config getConfig() {
        return config;
    }

    private void parseArguments() {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(flagFileName)) {
              config.setFileName(args[i + 1]);
            } else if (args[i].equals(flagLink)) {
                config.setLink(args[i + 1]);
            } else if (args[i].equals(flagDepth)) {
                config.setDepth(Integer.parseInt(args[i + 1]));
            } else if (args[i].equals(flagPageLimit)) {
                config.setPageLimit(Integer.parseInt(args[i + 1]));
            } else if (args[i].equals(flagTerms)) {
                config.setTerms(Arrays.copyOfRange(args, i + 1, args.length));
            }
        }
    }
}
