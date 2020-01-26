import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckOut")
public class CheckOut extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities Utility = new Utilities(request, out);
        storeOrders(request, response);
    }

    private void storeOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request,out);
        if(!utility.isLoggedin())
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to add items to cart");
            response.sendRedirect("Login");
            return;
        }
        HttpSession session=request.getSession();
        String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");
        utility.printHtml("header.html");
        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");
        out.print("<form name ='CheckOut' action='Payment' method='post'>\n" +
                "                        <h3>Order</h3>\n" +
                "                        <table  class='gridtable'>\n" +
                "                            <tr>\n" +
                "                                <th>Customer Name:</th>\n" +
                "                                <th>"+userName+"</th>\n" +
                "                            </tr>");
        for (OrderItem oi : utility.getCustomerOrders()) {
            out.print("<tr><td> Product Purchased:</td><td>");
            out.print(oi.getName()+"</td></tr><tr><td>");
            out.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
            out.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
            out.print("Product Price:</td><td>"+ oi.getPrice());
            out.print("</td></tr>");
        }
        out.print("<tr><td>Total Order Cost:</td>\n" +
                "                                <td>"+orderTotal+
                "                                </td>\n" +
                "                            </tr>\n" +
                "                        </table>");
        out.print("<table>\n" +
                "                            <tr></tr>\n" +
                "                            <tr></tr>\n" +
                "                            <tr><td>Credit/accountNo</td>\n" +
                "                                <td><input type='text' name='creditCardNo'></td>\n" +
                "                            </tr>\n" +
                "                            <tr><td>Customer Address</td>\n" +
                "                                <td><input type='text' name='userAddress'></td></tr>" +
                "                            <tr><td></td><td><input type='submit' name='submit' class='btnbuy' value='Confirm'></td></table></form>");
        out.print("        \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
