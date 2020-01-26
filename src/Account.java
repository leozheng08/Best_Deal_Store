import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Account")
public class Account extends HttpServlet {
    private String error_msg;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            displayAccount(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void displayAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request, out);
        if(!utility.isLoggedin())
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to add items to cart");
            response.sendRedirect("Login");
            return;
        }
        HttpSession session=request.getSession();
        utility.printHtml("header.html");
        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");
        out.print("<h3>Account</h3>");
        User user=utility.getUser();
        out.print("<table class='gridtable'>"+"<tr><th>User Name:</th><th>"+user.getName()+"</th></tr>"+
        "<tr><th>User Type:</th><th>\""+user.getUserType()+"\"</th></tr>");
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        try {
            orderPayments=MySqlDataStoreUtilities.selectOrder();
        }
        catch(Exception e)
        {

        }
        if(user.getUserType().equalsIgnoreCase("Customer")){
            int size=0;
            for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
            {
                for(OrderPayment od:entry.getValue())
                    if(od.getUserName().equals(user.getName()))
                        size= size+1;
            }
            if(size>0){
                out.print("<tr></tr><tr><td></td>");
                out.print("<td>OrderId:</td>");
                out.print("<td>UserName:</td>");
                out.print("<td>productOrdered:</td>");
                out.print("<td>productPrice:</td></tr>");
                for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
                {
                    for(OrderPayment oi:entry.getValue())
                        if(oi.getUserName().equals(user.getName()))
                        {
                            out.print("<form method='get' action='ViewOrder'>");
                            out.print("<tr>");
                            out.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");
                            out.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
                            out.print("<td><input type='hidden' name='cancelDate' value='"+oi.getLastCancelDate()+"'></td>");
                            out.print("<td><input type='hidden' name='orderId' value='"+oi.getOrderId()+"'></td>");
                            out.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
                            out.print("</tr>");
                            out.print("</form>");
                        }

                }
                out.print("</table>");
            }
            else{
                out.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
                out.print("</table>");
            }
        }
        if(user.getUserType().equalsIgnoreCase("manager")||user.getUserType().equalsIgnoreCase("retailer")){
            int size=0;
            for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
                for(OrderPayment od:entry.getValue()) {
                    size = size + 1;
                }
            }
            if(size>0){
                out.print("<tr></tr><tr><td></td>");
                out.print("<td>OrderId:</td>");
                out.print("<td>UserName:</td>");
                out.print("<td>productOrdered:</td>");
                out.print("<td>productPrice:</td></tr>");
                for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
                {
                    for(OrderPayment oi:entry.getValue()){
//                        if(oi.getUserName().equals(user.getName()))
                        //                        {
                            out.print("<form method='get' action='ViewOrder'>");
                            out.print("<tr>");
                            out.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");
                            out.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
                            out.print("<td><input type='hidden' name='orderId' value='"+oi.getOrderId()+"'></td>");
                            out.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
                            out.print("</tr>");
                            out.print("</form>");
                    }

                }
                out.print("</table>");
            }
            else{
                out.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
                out.print("</table>");
            }
        }

        out.print("        \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");

    }
}
