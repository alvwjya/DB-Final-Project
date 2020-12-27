package sample;

public class ModelTableCashier {
    private int price;
    private int qty;
    private int total;
    private String product;

    public ModelTableCashier(String product, int price, int qty, int total) {
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.total = total;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
