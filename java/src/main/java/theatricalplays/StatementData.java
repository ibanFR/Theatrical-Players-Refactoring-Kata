package theatricalplays;

import java.util.List;

public record StatementData(Invoice invoice, java.util.Map<String, Play> plays) {
    Play playForPerformance(Performance perf) {
        return this.plays.get(perf.playID);
    }

    PerformanceData getPerformanceData(Performance perf) {
        return new PerformanceData(perf, playForPerformance(perf));
    }

    int totalAmount() {
        return performances().stream()
                .mapToInt(performance -> getPerformanceData(performance).amount())
                .sum();
    }

    int totalVolumeCredits() {
        return this.invoice().performances.stream()
                .mapToInt(performance -> getPerformanceData(performance).volumeCredits())
                .sum();
    }

    public String customer() {
        return invoice.customer;
    }

    public List<Performance> performances() {
        return invoice.performances;
    }
}
