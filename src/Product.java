import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String productName;
    private double productPrice;
    private int count;
    private String image;
    private String retailer;
    private String condition;
    private double discount;
    private double rebate;
    private String productType;

    public Product (String productName,String productType, double productPrice, int count){
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.count = count;
    }

    public Product (String id,String image,String productName, double productPrice,String retailer,String condition,double discount,double rebate,String productType){
        this.productName = productName;
        this.productPrice = productPrice;
        this.retailer = retailer;
        this.condition = condition;
        this.discount = discount;
        this.rebate = rebate;
        this.productType = productType;
        this.id = id;
        this.image = image;
    }

    public String getId()
    {
        return this.id;
    }

    public String getImage(){
        return this.image;
    }
    public String getProductName(){
        return this.productName;
    }

    public Double getProductPrice(){
        return this.productPrice;
    }

    public int getCount(){
        return this.count;
    }

    public String getRetailer() {
        return retailer;
    }
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getRebate() {
        return rebate;
    }
    public void setRebate(double rebate) {
        this.rebate = rebate;
    }

    public String getProductType(){return productType;}
    public void setProductType(String productType){this.productType = productType;}





}
