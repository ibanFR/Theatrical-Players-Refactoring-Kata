package theatricalplays;

public record StatementData(Invoice invoice) {
    public String customer() {
        return invoice.customer;
    }
}
