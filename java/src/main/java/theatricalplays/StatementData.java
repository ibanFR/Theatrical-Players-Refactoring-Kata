package theatricalplays;

import java.util.List;
import java.util.Map;

public record StatementData(Invoice invoice, java.util.Map<String, Play> plays) {
    static Play playFor(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.playID);
    }

    public String customer() {
        return invoice.customer;
    }

    public List<Performance> performances() {
        return invoice.performances;
    }
}
