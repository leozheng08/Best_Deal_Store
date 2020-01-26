import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WirelessPlan")
public class WirelessPlan extends HttpServlet {
    private String id;
    private String name;
    private double price;
    private String image;
    private String retailer;
    private double discount;
    private double rebate;

    public WirelessPlan(String name, double price, String image, String retailer,String condition, double discount) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.discount = discount;
        this.retailer = retailer;
    }

    public WirelessPlan() {

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getRetailer() {
        return retailer;
    }
    public void setRetailer(String retailer) {
        this.retailer = retailer;
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}