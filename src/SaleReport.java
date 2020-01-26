import java.io.Serializable;

public class SaleReport implements Serializable {
    private String productName;
    private double productPrice;
    private int count;
    private double totalSale;

    public SaleReport(String productName, double productPrice, int count){
        this.productName = productName;
        this.productPrice = productPrice;
        this.count = count;
        this.totalSale = productPrice*count;
    }

    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }

    public double getProductPrice(){
        return productPrice;
    }

    public int getCount(){
        return count;
    }

    public double getTotalSale(){
        return totalSale;
    }




}
