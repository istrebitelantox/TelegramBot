package org.example.pojo;

import java.util.List;

public class Summary {
    private String reportName;
    private List<String> testRuns;
    private Statistic statistic;
    private Time time;

    @Override
    public String toString() {
        return "\n" +
                "\n\tstatistic:" + statistic.toString() +
                "\n\ttime:" + time.toString() +
                "\n";
    }
}
