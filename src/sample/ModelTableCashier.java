package sample;

public class ModelTableCashier {
    private String No, product, price, qty, total;

    public ModelTableCashier(String no, String product, String price, String qty, String total) {
        No = no;
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.total = total;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
