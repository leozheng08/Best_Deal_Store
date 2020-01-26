import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/ProductData")
public class ProductData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request,out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");
        Product data= (Product)request.getAttribute("data");
        out.print("<h3>" + data.getProductName() + "</h3>\n" +
                "\n" +
                "\n" +
                "            <p><img src='images/"+data.getProductType()+"/" + data.getImage() + "' height='100' width='100'></p>\n" +
                "            <ul class=\"styledlist\">\n" +
                "                <li>" + data.getProductPrice() + "</li>\n" +
                "                <li>" + data.getDiscount() + "</li>\n" +
                "                <li>" + data.getRetailer() + "</li>\n" +
                "            </ul>\n" +
                "\n" +
                "            <a href='Cart?name="+data.getId()+"&type="+data.getProductType()+"&maker="+data.getRetailer()+"&access= ' class=\"button\">Buy Now</a>\n" +
                "            <a href='WriteView?name=" + data.getId() + "&type="+data.getProductType()+"&maker=" + data.getRetailer() + "&price=" + data.getProductPrice() + "&access= ' class=\"button\">WriteView</a>\n" +
                "            <a href='ViewReview?name=" + data.getId() + "&type="+data.getProductType()+"&maker=" + data.getRetailer() + "&price=" + data.getProductPrice() + "&access= ' class=\"button\">ViewReview</a>");


        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
