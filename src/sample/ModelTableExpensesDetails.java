package sample;

public class ModelTableExpensesDetails {
    private String date, detail, amount;

    public ModelTableExpensesDetails(String date, String detail, String amount) {
        this.date = date;
        this.detail = detail;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
