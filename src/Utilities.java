import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Utilities")
public class Utilities extends HttpServlet {
    HttpServletRequest request;
    PrintWriter out;
    String url;
    HttpSession session;

    public Utilities(HttpServletRequest request, PrintWriter out){
        this.request = request;
        this.out = out;
        this.url = this.getFullUrl();
        this.session = this.request.getSession(true);
    }



    public void printHtml(String file) throws IOException {
        String result = HtmlToString(file);
        //to print the right navigation in header of username cart and logout etc
        if (file == "header.html") {
                if(session.getAttribute("username")!=null){
                    String username = (String)session.getAttribute("username");
                    result = result+
//                            "            <li class=\"start selected\"><a href=\"ViewOrder\">View Order</a></li>\n" +
                            "            <li class=\"\"><a>Hello,"+username+"</a></li>\n" +
                            "            <li class=\"\"><a href=\"Logout\">Logout</a></li>\n" +
//                            "            <li class=''><a href='Account'>Account</a></li>"+
                            "            <li class=\"end\"><a href='Cart?name="+null+"&type="+null+"&maker="+null+"&access="+null+"'>Cart("+CartCount()+")</a></li>\n";
                    if(session.getAttribute("userType").toString().equalsIgnoreCase("Retailer")){
                        result=result+" <li class=\"\"><a href='ProductCheck'>Products</a></li>\n";
                        result=result+" <li class=\"\"><a href='DataVisualization'>Trending</a></li>\n";
                        result=result+" <li class=\"\"><a href='Inventory'>Inventory</a></li>\n";
                        result=result+" <li class=\"\"><a href='SalesReport'>Sales Report</a></li>\n";

                    }
                    if(session.getAttribute("userType").toString().equalsIgnoreCase("Manager")){

                    }
                }
                else{
                    result = result+
//                            "            <li class=\"start selected\"><a href=\"ViewOrder\">View Order</a></li>\n" +
                            "            <li class=\"\"><a href=\"Login\">Login</a></li>\n" +
//                            "            <li><a href=\"Account\">Account</a></li>\n" +
                            "            <li class=\"end\"><a href=\"Cart\">Cart("+CartCount()+")</a></li>\n";
                }
                result = result +"<script type=\"text/javascript\" src=\"autoComplete.js\"></script>" +
                        "<div name=\"autofillform\">\n" +
                        "\t\t\t\t<input type=\"text\" name=\"searchId\" value=\"\" class=\"input\"\n" +
                        "\t\t\t\t\tid=\"searchId\" onkeyup=\"doCompletion()\" \n" +
                        "\t\t\t\t\tplaceholder=\"search Product..\" style=\"padding: 25px; font-size: 13px;\" />\n" +
                        "\t\t\t\t<div id=\"auto-row\">\n" +
                        "\t\t\t\t\t<table id=\"complete-table\" class=\"gridtable\"\n" +
                        "\t\t\t\t\t\tstyle=\"position: absolute; width: 315px;\"></table>\n" +
                        "\t\t\t\t</div>\n" +
                        "\t\t\t</div>";
                result=result+"</ul>\n" +
                        "</nav>";
                out.print(result);

        } else
            out.print(result);
    }

    private String HtmlToString(String file) throws IOException {
        String result = null;
        String webPage = url + file;
        URL url = new URL(webPage);
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);

        int numCharsRead;
        char[] charArray = new char[1024];
        StringBuffer sb = new StringBuffer();
        while ((numCharsRead = isr.read(charArray)) > 0) {
            sb.append(charArray, 0, numCharsRead);
        }
        result = sb.toString();
        return result;
    }

    private String getFullUrl() {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuffer url = new StringBuffer();
        url.append(scheme).append("://").append(serverName);

        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        url.append("/");
        return url.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void logout() {
        session.removeAttribute("username");
        session.removeAttribute("userType");
    }



    public String usertype(){
        if (session.getAttribute("usertype")!=null)
            return session.getAttribute("usertype").toString();
        return null;
    }

    public User getUser() throws IOException, ClassNotFoundException {
        String usertype = usertype();
        HashMap<String, User> hm=new HashMap<String, User>();
//        String TOMCAT_HOME = System.getProperty("catalina.home");
//        FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDealStore\\UserDetails.txt"));
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//        hm= (HashMap)objectInputStream.readObject();
//        User user = hm.get(username());
        try
        {
            hm=MySqlDataStoreUtilities.selectUser();
        }
        catch(Exception e)
        {
        }
        User user = hm.get(username());
        return user;
    }

    public String username(){
        if(session.getAttribute("username")!=null) {
            return session.getAttribute("username").toString();
        }
        else
            return null;
    }

    public void storeProduct(String name, String type, String maker, String access) {
        if(!OrdersHashMap.orders.containsKey(username())){
            ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
            OrdersHashMap.orders.put(username(),arr);
        }
        ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
        if(type.equals("TV")){
            TV tv;
            tv = SaxParseStore.tvs.get(name);
            OrderItem orderitem = new OrderItem(tv.getName(), tv.getPrice(), tv.getImage(), tv.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("SoundSystem")){
            SoundSystem soundSystem;
            soundSystem = SaxParseStore.soundSystems.get(name);
            OrderItem orderitem = new OrderItem(soundSystem.getName(), soundSystem.getPrice(), soundSystem.getImage(), soundSystem.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("Phone")){
            Phone phone;
            phone = SaxParseStore.phones.get(name);
            OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("Laptop")){
            Laptop laptop;
            laptop = SaxParseStore.laptops.get(name);
            OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("VoiceAssistant")){
            VoiceAssistant voiceAssistant;
            voiceAssistant = SaxParseStore.voiceAssistants.get(name);
            OrderItem orderitem = new OrderItem(voiceAssistant.getName(), voiceAssistant.getPrice(), voiceAssistant.getImage(), voiceAssistant.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("FitnessWatch")){
            FitnessWatch fitnessWatch;
            fitnessWatch = SaxParseStore.fitnessWatches.get(name);
            OrderItem orderitem = new OrderItem(fitnessWatch.getName(), fitnessWatch.getPrice(), fitnessWatch.getImage(), fitnessWatch.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("SmartWatch")){
            SmartWatch smartWatch;
            smartWatch = SaxParseStore.smartWatches.get(name);
            OrderItem orderitem = new OrderItem(smartWatch.getName(), smartWatch.getPrice(), smartWatch.getImage(), smartWatch.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("SmartWatch")){
            SmartWatch smartWatch;
            smartWatch = SaxParseStore.smartWatches.get(name);
            OrderItem orderitem = new OrderItem(smartWatch.getName(), smartWatch.getPrice(), smartWatch.getImage(), smartWatch.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("HeadPhone")){
            HeadPhone headPhone;
            headPhone = SaxParseStore.headPhones.get(name);
            OrderItem orderitem = new OrderItem(headPhone.getName(), headPhone.getPrice(), headPhone.getImage(), headPhone.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("WirelessPlan")){
            WirelessPlan wirelessPlan;
            wirelessPlan = SaxParseStore.wirelessPlans.get(name);
            OrderItem orderitem = new OrderItem(wirelessPlan.getName(), wirelessPlan.getPrice(), wirelessPlan.getImage(), wirelessPlan.getRetailer());
            orderItems.add(orderitem);
        }
        if(type.equals("Accessory")){
            Accessory accessory;
            accessory = SaxParseStore.accessories.get(name);
            OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer());
            orderItems.add(orderitem);
        }
    }

    public ArrayList<OrderItem> getCustomerOrders() {
        ArrayList<OrderItem> order = new ArrayList<OrderItem>();
        if(OrdersHashMap.orders.containsKey(username())){
            order= OrdersHashMap.orders.get(username());
        }
        return order;
    }

    public int CartCount() {
        if(isLoggedin())
            return getCustomerOrders().size();
        return 0;
    }

    public boolean isLoggedin() {
        if (session.getAttribute("username")==null)
            return false;
        return true;
    }

    public int getOrderPaymentSize() throws IOException, ClassNotFoundException {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
//        String TOMCAT_HOME = System.getProperty("catalina.home");
        try
        {
            orderPayments =MySqlDataStoreUtilities.selectOrder();
//            FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\BestDealStore\\PaymentDetails.txt"));
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            orderPayments = (HashMap)objectInputStream.readObject();
        }
        catch(Exception e)
        {

        }
        int size=0;
        for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
            size=size + 1;

        }
        return size;
    }

    public void storePayment(int orderId, String orderName, double orderPrice, String userAddress, String creditCardNo) {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        // get the payment details file
        try {
            orderPayments = MySqlDataStoreUtilities.selectOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (orderPayments == null) {
            orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        }
        if (!orderPayments.containsKey(orderId)) {
            ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
            orderPayments.put(orderId, arr);
        }
        ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);
        OrderPayment orderpayment = new OrderPayment(orderId, username(), orderName, orderPrice, userAddress, creditCardNo);
        listOrderPayment.add(orderpayment);

        try {
            MySqlDataStoreUtilities.insertOrder(orderId, username(), orderName, orderPrice, userAddress, creditCardNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String storeReview(String productname, String producttype, String productmaker, String reviewrating, String reviewdate, String reviewtext, String retailerpin, String productprice, String retailercity) {
        String message=MongoDBDataStoreUtilities.insertReview(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,retailerpin,productprice,retailercity);
        if(!message.equals("Successfull"))
        {
            return "UnSuccessfull";
        }
        else
        {
            HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
            try
            {
                reviews=MongoDBDataStoreUtilities.selectReview();
            }
            catch(Exception e)
            {

            }
            if(reviews==null)
            {
                reviews = new HashMap<String, ArrayList<Review>>();
            }
            // if there exist product review already add it into same list for productname or create a new record with product name

            if(!reviews.containsKey(productname)){
                ArrayList<Review> arr = new ArrayList<Review>();
                reviews.put(productname, arr);
            }
            ArrayList<Review> listReview = reviews.get(productname);
            Review review = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,retailerpin,productprice,retailercity);
            listReview.add(review);

            // add Reviews into database

            return "Successfull";
        }

    }
}
