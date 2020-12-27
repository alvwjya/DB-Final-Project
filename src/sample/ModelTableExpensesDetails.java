package sample;

public class ModelTableExpensesDetails {
    private String date, detail, expenses;

    public ModelTableExpensesDetails(String date, String detail, String expenses) {
        this.date = date;
        this.detail = detail;
        this.expenses = expenses;
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

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }
}
