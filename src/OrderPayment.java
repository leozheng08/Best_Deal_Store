import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class OrderPayment implements Serializable {
    private static final long serialVersionUID = 8259590407715072561L;
    private int orderId;
    private String userName;
    private String orderName;
    private double orderPrice;
    private String userAddress;
    private String creditCardNo;
    private Date LastCancelDate;


    public OrderPayment(int orderId, String userName, String orderName, double orderPrice, String userAddress, String creditCardNo){
        this.orderId=orderId;
        this.userName=userName;
        this.orderName=orderName;
        this.orderPrice=orderPrice;
        this.userAddress=userAddress;
        this.creditCardNo=creditCardNo;
        this.LastCancelDate = Cacluate(new java.util.Date());


    }

    private Date Cacluate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int workingDaysToAdd = 5;
        for (int i = 0; i < workingDaysToAdd; i++){
            do {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            } while (!isWorkingDay(cal));
        }
        return cal.getTime();
    }

    private boolean isWorkingDay(Calendar cal) {
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY)
            return false;
        return true;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getLastCancelDate(){
        return LastCancelDate;
    }
}
