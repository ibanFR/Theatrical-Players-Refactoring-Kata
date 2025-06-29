package theatricalplays;

public record PerformanceData(Performance performance, Play play) {
    int amountFor() {
        int result;
        switch (play().type) {
            case "tragedy":
                result = 40000;
                if (performance().audience > 30) {
                    result += 1000 * (performance().audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (performance().audience > 20) {
                    result += 10000 + 500 * (performance().audience - 20);
                }
                result += 300 * performance().audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }

    int volumeCreditsFor() {
        int result = 0;
        result += Math.max(performance().audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play().type))
            result += Math.floor(performance().audience / 5);
        return result;
    }
}