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
                                    usd(new PerformanceData(perf,
                                                            StatementData.playFor(aStatementData.plays(),
                                                                                             perf)).amount()),
                                    perf.audience);
        }

        result += String.format("Amount owed is %s%n", usd(totalAmount(aStatementData)));
        result += String.format("You earned %s credits%n", aStatementData.totalVolumeCredits());
        return result;
    }

    private static int totalAmount(StatementData data) {
        var result = 0;
        for (var perf : data.performances()) {
            result += new PerformanceData(perf, StatementData.playFor(data.plays(), perf)).amount();
        }
        return result;
    }

    private static String usd(int totalAmount) {
        return NumberFormat.getCurrencyInstance(Locale.US)
                .format(totalAmount/ 100);
    }

}
