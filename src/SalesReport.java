import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet(name = "SalesReport")
public class SalesReport extends HttpServlet {
    ArrayList<SaleReport> array = new ArrayList<>();
    ArrayList<DailyReport> arrayForDailyReports = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            array = MySqlDataStoreUtilities.selectSalesReport();
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
        array = MySqlDataStoreUtilities.selectSalesReport();
        arrayForDailyReports = MySqlDataStoreUtilities.selectDailyReport();
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("header.html");

        pw.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        pw.print("<h2>");
        pw.print("Sales report");
        pw.print("</h2><table id='SalesReport'>");
        pw.print("<tr><td>product Name</td><td>productPrice</td><td>count number</td><td>total sale</td></tr>");
        Iterator itr = array.iterator();
        while(itr.hasNext()) {
            SaleReport saleReport = (SaleReport) itr.next();
            pw.print("<tr>");
            pw.print("<td>");
            pw.print(saleReport.getProductName());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(saleReport.getProductPrice());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(saleReport.getCount());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(saleReport.getTotalSale());
            pw.print("</td>");
            pw.print("</tr>");
        }
        pw.print("</table>");
        pw.print("<h3><button id='DataForSalesReport'>View Chart</h3>");
        pw.println("<div id='chart_div'></div>");
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='DataVisualizationForSalesReport.js'></script>");

        pw.print("<h2>");
        pw.print("Daily report");
        pw.print("</h2><table id='DailyReport'>");
        pw.print("<tr><td>date</td><td>daily sale</td></tr>");
        Iterator itr2 = arrayForDailyReports.iterator();
        while(itr2.hasNext()) {
            DailyReport dailyReport = (DailyReport) itr2.next();
            pw.print("<tr>");
            pw.print("<td>");
            pw.print(dailyReport.getDate());
            pw.print("</td>");
            pw.print("<td>");
            pw.print(dailyReport.getDailySale());
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
