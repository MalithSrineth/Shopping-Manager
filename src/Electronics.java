public class Electronics extends Product{
    private String brandName;
    private String warrantyPeriod;

    public Electronics(String productID, String productName, double productPrice, int productQuantity, String brandName, String warrantyPeriod) {
        super(productID, productName, productPrice, productQuantity);
        this.brandName = brandName;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }
    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }



    
}
