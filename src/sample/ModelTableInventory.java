package sample;

public class ModelTableInventory {
    private int productId;
    private String productName, productQty, productCategory, productPrice;

    public ModelTableInventory(int productId, String productName, String productQty, String productCategory, String productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productQty = productQty;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
