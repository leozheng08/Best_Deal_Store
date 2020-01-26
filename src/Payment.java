import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "Payment")
public class Payment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        Utilities utility = new Utilities(request, out);
        if(!utility.isLoggedin())
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to Pay");
            response.sendRedirect("Login");
            return;
        }
        String userAddress=request.getParameter("userAddress");
        String creditCardNo=request.getParameter("creditCardNo");
        System.out.print("the user address is" +userAddress);
        System.out.print(creditCardNo);
        if(!userAddress.isEmpty() && !creditCardNo.isEmpty() )
        {


            int orderId = 0;
            try {
                orderId = utility.getOrderPaymentSize()+1;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            for (OrderItem oi : utility.getCustomerOrders()){
                utility.storePayment(orderId,oi.getName(),oi.getPrice(),userAddress,creditCardNo);
            }
            OrdersHashMap.orders.remove(utility.username());
            utility.printHtml("header.html");
            out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                    "<div id=\"body\">\n" +
                    "    <section id=\"content\">\n" +
                    "\n" +
                    "        \t    <article>");
            out.print("<h3>Your Order is stored as Order No:"+orderId+"</h3>");
//            Long date = System.currentTimeMillis()+14*24*60*60*1000;
//            Date deliveryDate = new Date(date);
            Date date = new java.util.Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.WEEK_OF_MONTH,2);
            Date deliveryDate = c.getTime();
            out.print("<hr><h3> the delivery date will be "+deliveryDate+"</h3>");
            out.print("   </article> </section>");
            utility.printHtml("leftNavigateBar.html");
            utility.printHtml("footer.html");
        }
        else
        {
            utility.printHtml("header.html");
            out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                    "<div id=\"body\">\n" +
                    "    <section id=\"content\">\n" +
                    "\n" +
                    "        \t    <article>");
            out.print("<h3YPlease enter valid address and creditcard number</h3>");
            out.print("   </article> </section>");
            utility.printHtml("leftNavigateBar.html");
            utility.printHtml("footer.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
