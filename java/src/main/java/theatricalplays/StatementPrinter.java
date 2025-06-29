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
                                    aStatementData.playFor(perf).name,
                                    usd(aStatementData.getPerformanceData(perf).amount()),
                                    perf.audience);
        }

        result += String.format("Amount owed is %s%n", usd(aStatementData.totalAmount()));
        result += String.format("You earned %s credits%n", aStatementData.totalVolumeCredits());
        return result;
    }

    private static String usd(int totalAmount) {
        return NumberFormat.getCurrencyInstance(Locale.US)
                .format(totalAmount/ 100);
    }

}
