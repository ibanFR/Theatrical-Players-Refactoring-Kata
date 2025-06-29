package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var statementData = new StatementData(invoice, plays);
        return renderPlainText(statementData);
    }

    private static String renderPlainText(StatementData aStatementData) {
        var result = String.format("Statement for %s%n", aStatementData.customer());

        for (var perf : aStatementData.performances()) {

            // print line for this order
            result += String.format("  %s: %s (%s seats)%n",
                                    StatementData.playFor(aStatementData.plays(), perf).name,
                                    usd(amountFor(perf, StatementData.playFor(aStatementData.plays(), perf))),
                                    perf.audience);
        }

        result += String.format("Amount owed is %s%n", usd(totalAmount(aStatementData)));
        result += String.format("You earned %s credits%n", totalVolumeCredits(aStatementData));
        return result;
    }

    private static int totalAmount(StatementData data) {
        var result = 0;
        for (var perf : data.performances()) {
            result += amountFor(perf, StatementData.playFor(data.plays(), perf));
        }
        return result;
    }

    private static int totalVolumeCredits(StatementData data) {
        var result = 0;
        for (var perf : data.invoice().performances) {

            // add volume credits
            result += new PerformanceData(perf, StatementData.playFor(data.plays(), perf)).volumeCreditsFor();
        }
        return result;
    }

    private static String usd(int totalAmount) {
        return NumberFormat.getCurrencyInstance(Locale.US)
                .format(totalAmount/ 100);
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
