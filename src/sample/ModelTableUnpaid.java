package sample;

public class ModelTableUnpaid {
    String customerId, salesDate, subTotal, paid;

    public ModelTableUnpaid(String customerId, String salesDate, String subTotal, String paid) {
        this.customerId = customerId;
        this.salesDate = salesDate;
        this.subTotal = subTotal;
        this.paid = paid;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
