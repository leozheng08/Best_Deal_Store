import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ViewOrder")
public class ViewOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Utilities utility = new Utilities(request, out);
        //check if the user is logged in
        if(!utility.isLoggedin()){
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to View your Orders");
            response.sendRedirect("Login");
            return;
        }
        User user = null;
        try {
            user = utility.getUser();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String username = utility.username();
        String userType=utility.usertype();




        utility.printHtml("header.html");
        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">");
        out.print("<h3>Order</h3>");
        out.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");
        if(request.getParameter("Order")==null){
            out.print("<table><tr><td>Enter OrderNo &nbsp&nbsp<input name='orderId' type='text'></td>");
            out.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy'></td></tr></table>");
        }
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();

        try {
            orderPayments=MySqlDataStoreUtilities.selectOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(request.getParameter("Order")!=null && request.getParameter("Order").equals("ViewOrder")){
            if (request.getParameter("orderId") != null && request.getParameter("orderId") != "" ) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                out.print("<input type='hidden' name='orderId' value='" + orderId + "'>");
                //get the order details from file
                try {
                    orderPayments=MySqlDataStoreUtilities.selectOrder();
                } catch (Exception e) {

                }
                int size = 0;
                if (orderPayments.get(orderId) != null) {
                    for (OrderPayment od : orderPayments.get(orderId))
                        if (od.getUserName().equals(username))
                            size = orderPayments.get(orderId).size();
                }
                if (size > 0) {
                    out.print("<table  class='gridtable'>");
                    out.print("<tr><th></th>");
                    out.print("<th>OrderId:</th>");
                    out.print("<th>UserName:</th>");
                    out.print("<th>productOrdered:</th>");
                    out.print("<th>productPrice:</th></tr>");
                    for (OrderPayment oi : orderPayments.get(orderId)) {
                        out.print("<tr>");
                        out.print("<td><input type='radio' name='orderName' value='" + oi.getOrderName() + "'></td>");
                        out.print("<td>" + oi.getOrderId() + ".</td><td>" + oi.getUserName() + "</td><td>" + oi.getOrderName() + "</td><td>Price: " + oi.getOrderPrice() + "</td>");
                        out.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
                        out.print("</tr>");

                    }
                    out.print("</table>");
                }
                else {
                    out.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
                }
            }
            else
                {
                    out.print("<h4 style='color:red'>Please enter the valid order number</h4>");
                }
        }
        if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder"))
        {
            if(request.getParameter("orderName") != null)
            {
                    String orderName=request.getParameter("orderName");
                    int orderId=0;
                    orderId= Integer.parseInt(request.getParameter("orderId"));
                    ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
                    //get the order from file
                    try {
                        orderPayments=MySqlDataStoreUtilities.selectOrder();

                    }
                    catch(Exception e)
                    {

                    }
                    //get the exact order with same ordername and add it into cancel list to remove it later
                    for (OrderPayment oi : orderPayments.get(orderId))
                    {
                        if(oi.getOrderName().equals(orderName)){
                            if(user.getUserType().equalsIgnoreCase("Customer")){
                                if(oi.getUserName().equals(username)){
                                    if(oi.getLastCancelDate().before(new java.util.Date())){
                                        out.print("<h4 style='color:red'>Your Order cannot be Cancelled after the last cancelling date </h4>");
                                    }
                                    else{
                                        try {
                                            MySqlDataStoreUtilities.deleteOrder(orderId, orderName);
                                        }
                                        catch(Exception e){

                                        }
                                        ListOrderPayment.add(oi);
                                        out.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
                                    }
                                }
                            }
                            if(user.getUserType().equalsIgnoreCase("manager")){
                                try {
                                    MySqlDataStoreUtilities.deleteOrder(orderId, orderName);
                                }
                                catch(Exception e){

                                }
                                ListOrderPayment.add(oi);
                                out.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
                            }

                        }

                    }
                    //remove all the orders from hashmap that exist in cancel list
                    orderPayments.get(orderId).removeAll(ListOrderPayment);
                    if(orderPayments.get(orderId).size()==0)
                    {
                        orderPayments.remove(orderId);
                    }

            }
            else
                {
                    out.print("<h4 style='color:red'>Please select any product</h4>");
                }
        }



        out.print("</form>");
        out.print("        \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
