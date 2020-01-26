import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Cart")
public class Cart extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request, out);
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String maker = request.getParameter("maker");
        String access = request.getParameter("access");
        System.out.print("name" + name + "type" + type + "maker" + maker + "access" + access);
        utility.storeProduct(name, type, maker, access);
        displayCart(request, response);
    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request,out);
        Carousel carousel = new Carousel();
        if(!utility.isLoggedin()){
            HttpSession session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to add items to cart");
            response.sendRedirect("Login");
            return;
        }
        utility.printHtml("header.html");
        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" );

        out.print("<h3>Cart("+ utility.CartCount()+")</h3>");
        out.print("<form name ='payment' action='CheckOut' method='post'>");
        if(utility.CartCount()>0){
            out.print("<table  class='gridtable' cellspacing=\"0\">");
            int i = 1;
            double total = 0;
            out.print("<tr>\n" +
                    "                    <th>ID</th>\n" +
                    "                    <th>Name</th>\n" +
                    "                    <th>Price</th>\n" +
                    "                </tr>");
            for (OrderItem oi : utility.getCustomerOrders())
            {
                out.print("<tr>");
                out.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td>");
                out.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
                out.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
                out.print("</tr>");
                total = total +oi.getPrice();
                i++;
            }
            out.print("<input type='hidden' name='orderTotal' value='"+total+"'>");
                out.print("<tr><th></th><th>Total</th><th>:"+total+"</th></tr>");
            out.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td></tr>");
            out.print("</table>");

            out.print(carousel.carouselFeature(utility));
        }
        else
        {
            out.print("<article>");
            out.print("<h3>Your Cart is empty</h3>");
            out.print("</article>");
        }
        out.print("</section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
