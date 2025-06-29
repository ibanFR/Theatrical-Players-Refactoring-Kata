package theatricalplays;

public record PerformanceData(Performance perf, Play play) {
    int volumeCreditsFor() {
        int result = 0;
        result += Math.max(perf().audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play().type))
            result += Math.floor(perf().audience / 5);
        return result;
    }
}