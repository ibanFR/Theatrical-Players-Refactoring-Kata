package theatricalplays;

import java.util.List;

public record StatementData(Invoice invoice, java.util.Map<String, Play> plays) {
    Play playFor(Performance perf) {
        return this.plays.get(perf.playID);
    }

    PerformanceData getPerformanceData(Performance perf) {
        return new PerformanceData(perf,
                                   playFor(
                                           perf));
    }

    int totalAmount() {
        var result = 0;
        for (var perf : performances()) {
            result += new PerformanceData(perf, playFor(perf)).amount();
        }
        return result;
    }

    int totalVolumeCredits() {
        var result = 0;
        for (var perf : invoice().performances) {

            // add volume credits
            result += new PerformanceData(perf, playFor(perf)).volumeCreditsFor();
        }
        return result;
    }

    public String customer() {
        return invoice.customer;
    }

    public List<Performance> performances() {
        return invoice.performances;
    }
}
