import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProductCheck")
public class ProductCheck extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");
        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n<div id=\"body\">\n    <section id=\"content\">\n\n        \t    <article>");
        out.print("<form method='get' action='ProductOperation'>"
                + "<table><tr><td>"
                + "<input type='submit' class='btnbuy' name='button' value='Addproduct'></input>"
                + "</td></tr><tr><td></td></tr><tr><td>"
                + "<input type='submit' class='btnbuy' name='button' value='Updateproduct'></input>"
                + "</td></tr><tr><td></td></tr><tr><td>"
                + "<input type='submit' class='btnbuy' name='button' value='Deleteproduct'></input>"
                + "</td></tr><tr><td></td></tr><tr><td>"
                + "<input type='submit' class='btnbuy' name='button' value='Trending'></input>"
                + "</td></tr></table>"
                + "</form>");
//        out.print("<form action='ProductOperation' method='get'>" +
//                "<p><label for=\"Category\">Category:</label><input name='Category' type='text'/>" +
//                "</p><p><input name=\"submit\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"submit\" type=\"submit\" /></p> " +
//                "<form>");
        out.print("        \t\t</article>\n    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
