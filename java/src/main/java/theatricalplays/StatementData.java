package theatricalplays;

import java.util.List;

public record StatementData(Invoice invoice, java.util.Map<String, Play> plays) {
    public String customer() {
        return invoice.customer;
    }

    public List<Performance> performances() {
        return invoice.performances;
    }
}
