package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = String.format("Statement for %s\n", invoice.customer);

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (var perf : invoice.performances) {

            Play play = plays.get(perf.playID);
            // add volume credits
            volumeCredits += volumeCreditsFor(perf, play);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", playFor(plays, perf).name, frmt.format(
                    amountFor(perf, playFor(plays, perf)) / 100), perf.audience);
            totalAmount += amountFor(perf, playFor(plays, perf));
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private static int volumeCreditsFor(Performance perf, Play play) {
        int result = 0;
        result += Math.max(perf.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type))
            result += Math.floor(perf.audience / 5);
        return result;
    }

    private static Play playFor(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.playID);
    }

    private static int amountFor(Performance aPerformance, Play play) {
        int result;
        switch (play.type) {
            case "tragedy":
                result = 40000;
                if (aPerformance.audience > 30) {
                    result += 1000 * (aPerformance.audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.audience > 20) {
                    result += 10000 + 500 * (aPerformance.audience - 20);
                }
                result += 300 * aPerformance.audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }

}
