package theatricalplays;

import java.util.List;
import java.util.Map;

public record StatementData(Invoice invoice, java.util.Map<String, Play> plays) {
    static Play playFor(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.playID);
    }

    int totalAmount() {
        var result = 0;
        for (var perf : performances()) {
            result += new PerformanceData(perf, playFor(plays(), perf)).amount();
        }
        return result;
    }

    int totalVolumeCredits() {
        var result = 0;
        for (var perf : invoice().performances) {

            // add volume credits
            result += new PerformanceData(perf, playFor(plays(), perf)).volumeCreditsFor();
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
