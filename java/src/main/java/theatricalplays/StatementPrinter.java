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
        result += String.format("You earned %s credits%n", aStatementData.totalVolumeCredits());
        return result;
    }

    private static int totalAmount(StatementData data) {
        var result = 0;
        for (var perf : data.performances()) {
            result += amountFor(perf, StatementData.playFor(data.plays(), perf));
        }
        return result;
    }

    private static String usd(int totalAmount) {
        return NumberFormat.getCurrencyInstance(Locale.US)
                .format(totalAmount/ 100);
    }

    private static int amountFor(Performance aPerformance, Play play) {
        PerformanceData performanceData = new PerformanceData(aPerformance, play);
        int result;
        switch (performanceData.play().type) {
            case "tragedy":
                result = 40000;
                if (performanceData.performance().audience > 30) {
                    result += 1000 * (performanceData.performance().audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (performanceData.performance().audience > 20) {
                    result += 10000 + 500 * (performanceData.performance().audience - 20);
                }
                result += 300 * performanceData.performance().audience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }

}
