package theatricalplays;

public record PerformanceData(Performance aPerformance, Play play) {
    int volumeCreditsFor() {
        int result = 0;
        result += Math.max(aPerformance().audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play().type))
            result += Math.floor(aPerformance().audience / 5);
        return result;
    }
}