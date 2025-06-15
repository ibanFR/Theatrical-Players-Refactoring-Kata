package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var statementData = new StatementData(invoice.customer,invoice.performances, invoice);
        return renderPlainText(statementData,invoice, plays);
    }

    private static String renderPlainText(StatementData data, Invoice invoice, Map<String, Play> plays) {
        var result = String.format("Statement for %s\n", data.customer());

        for (var perf : data.performances()) {

            // print line for this order
            result += String.format("  %s: %s (%s seats)%n",
                                    playFor(plays, perf).name,
                                    usd(amountFor(perf, playFor(plays, perf))),
                                    perf.audience);
        }

        result += String.format("Amount owed is %s%n", usd(totalAmount(invoice, plays)));
        result += String.format("You earned %s credits%n", totalVolumeCredits(invoice, plays));
        return result;
    }

    private static int totalAmount(Invoice invoice, Map<String, Play> plays) {
        var result = 0;
        for (var perf : invoice.performances) {
            result += amountFor(perf, playFor(plays, perf));
        }
        return result;
    }

    private static int totalVolumeCredits(Invoice invoice, Map<String, Play> plays) {
        var result = 0;
        for (var perf : invoice.performances) {

            // add volume credits
            result += volumeCreditsFor(perf, playFor(plays, perf));
        }
        return result;
    }

    private static String usd(int totalAmount) {
        return NumberFormat.getCurrencyInstance(Locale.US)
                .format(totalAmount/ 100);
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
