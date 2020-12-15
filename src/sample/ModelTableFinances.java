package sample;

public class ModelTableFinances {

    private String date, income, expenses;

    public ModelTableFinances(String date, String income, String expenses) {
        this.date = date;
        this.income = income;
        this.expenses = expenses;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }
}
