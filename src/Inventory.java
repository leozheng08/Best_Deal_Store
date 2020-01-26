import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "Inventory")
public class Inventory extends HttpServlet {

    ArrayList<Product> array = new ArrayList<>();
    ArrayList<Product> arrayOnsale = new ArrayList<>();
    ArrayList<Product> arrayOnRebate = new ArrayList<>();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            array = MySqlDataStoreUtilities.getProducts();
            String reviewJson = new Gson().toJson(array);
            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        array = MySqlDataStoreUtilities.getProducts();
        arrayOnsale = MySqlDataStoreUtilities.getProductsOnsale();
        arrayOnRebate = MySqlDataStoreUtilities.getProductsOnRebate();
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("header.html");

        pw.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        pw.print("<h2>");
        pw.print("All Products");
        pw.print("</h2><table id='Storage'>");
        Iterator itr2 = array.iterator();
        pw.print("<tr><td>product Name</td><td>productPrice</td><td>count number</td></tr>");
        while(itr2.hasNext()) {
            Product product = (Product)itr2.next();
            pw.print("<tr>");
            pw.print("<td>");
            pw.print(product.getProductName());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getProductPrice());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getCount());
            pw.print("</td>");
            pw.print("</tr>");
        }
        pw.print("</table>");
        pw.print("<h3><button id='DataForProducts'>View Chart</h3>");
        pw.println("<div id='chart_div'></div>");
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='DataVisualizationForProducts.js'></script>");



        pw.print("<h2>");
        pw.print("All Products on sale");
        pw.print("</h2><table id='on sale'>");
        pw.print("<tr><td>product Name</td><td>productPrice</td><td>manufacturer</td><td>condition</td><td>discount</td></tr>");
        Iterator itr3 = arrayOnsale.iterator();
        while(itr3.hasNext()) {
            Product product = (Product)itr3.next();
            pw.print("<tr>");
            pw.print("<td>");
            pw.print(product.getProductName());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getProductPrice());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getRetailer());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getCondition());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getDiscount());
            pw.print("</td>");
            pw.print("</tr>");
        }
        pw.print("</table>");

        pw.print("<h2>");
        pw.print("All Products on rebate");
        pw.print("</h2><table id='on Rebate'>");
        pw.print("<tr><td>product Name</td><td>productPrice</td><td>manufacturer</td><td>condition</td><td>discount</td><td>rebate</td></tr>");
        Iterator itr4 = arrayOnRebate.iterator();
        while(itr4.hasNext()) {
            Product product = (Product)itr4.next();
            pw.print("<tr>");
            pw.print("<td>");
            pw.print(product.getProductName());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getProductPrice());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getRetailer());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getCondition());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getDiscount());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(product.getRebate());
            pw.print("</td>");
            pw.print("</tr>");
        }
        pw.print("</table>");



        pw.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
