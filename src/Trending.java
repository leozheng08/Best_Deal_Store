import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet(name = "Trending")
public class Trending extends HttpServlet {

    ArrayList<Mostsold> mostsold = new ArrayList <Mostsold> ();
    ArrayList <Mostsoldzip> mostsoldzip = new ArrayList <Mostsoldzip> ();
    ArrayList <Bestrating> bestrated = new ArrayList <Bestrating> ();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        mostsold = MongoDBDataStoreUtilities.mostsoldProducts();
        mostsoldzip = MongoDBDataStoreUtilities.mostsoldZip();
        bestrated      = MongoDBDataStoreUtilities.topProducts();

        String name = "Trending";
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("header.html");

        pw.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        pw.print("<h2>");
        pw.print("Best Products");
        pw.print("</h2><table id='bestseller'>");
        Iterator itr2 = bestrated.iterator();
        while(itr2.hasNext()) {
            Bestrating best = (Bestrating)itr2.next();
            pw.print("<tr>");
            pw.print("<td>");
            pw.print(best.getProductname());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(best.getRating());
            pw.print("</td>");
            pw.print("</tr>");
        }
        pw.print("</table>");

        pw.print("<h2>");
        pw.print("Most Sold Products by Zipcode");
        pw.print("</h2><table id='bestseller'>");
        Iterator itr1 = mostsoldzip.iterator();
        while(itr1.hasNext()) {
            Mostsoldzip mostzip = (Mostsoldzip)itr1.next();
            pw.print("<tr>");
            pw.println("<td border: 1px >");

            pw.println(mostzip.getZipcode());
            pw.println("</td>");
            pw.println("<td border: 1px >");
            pw.println(mostzip.getCount());
            pw.println("</td>");
            pw.println("</tr>");
        }
        pw.print("</table>");

        pw.print("<h2>");
        pw.print("Most Sold Products");
        pw.print("</h2><table id='bestseller'>");

        Iterator itr = mostsold.iterator();
        while(itr.hasNext()) {
            Mostsold most = (Mostsold)itr.next();
            pw.println("<tr>");
            pw.println("<td border: 1px >");
            pw.println(most.getProductname());
            pw.println("</td>");
            pw.println("<td border: 1px >");
            pw.println(most.getCount());
            pw.println("</td>");
            pw.println("</tr>");
        }
        pw.print("</table>");
        pw.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
