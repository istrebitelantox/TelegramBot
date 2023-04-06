package ru.kptc.pojo;

public class Statistic {
    private int failed;
    private int broken;
    private int skipped;
    private int passed;
    private int unknown;
    private int total;

    @Override
    public String toString() {
        return "" +
                "\n\t\t❌failed=" + failed +
                "\n\t\t❗broken=" + broken +
                "\n\t\t⏭skipped=" + skipped +
                "\n\t\t✅passed=" + passed +
                "\n\t\t❓unknown=" + unknown +
                "\n\t\t💯total=" + total +
                "\n\t";
    }
}
