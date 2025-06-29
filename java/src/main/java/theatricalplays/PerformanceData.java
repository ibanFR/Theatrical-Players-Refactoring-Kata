package theatricalplays;

public record PerformanceData(Performance performance, Play play) {
    int volumeCreditsFor() {
        int result = 0;
        result += Math.max(performance().audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play().type))
            result += Math.floor(performance().audience / 5);
        return result;
    }
}