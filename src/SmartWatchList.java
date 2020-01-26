import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SmartWatchList")
public class SmartWatchList extends HttpServlet {

//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, SmartWatch> map = new HashMap<String, SmartWatch>();

        if(CategoryName==null){
            map.putAll(SaxParseStore.smartWatches);
            name = "";
        }
        else{
            if(CategoryName.equals("Garmin"))
            {
                for(Map.Entry<String, SmartWatch> entry : SaxParseStore.smartWatches.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Garmin"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Garmin";
            }
            else if(CategoryName.equals("Samsung"))
            {
                for(Map.Entry<String, SmartWatch> entry : SaxParseStore.smartWatches.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Samsung"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Samsung";
            }


        }
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        for (Map.Entry<String, SmartWatch> entry : map.entrySet()) {
            SmartWatch smartWatch = entry.getValue();

            out.print("<h3>" + smartWatch.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/SmartWatch/" + smartWatch.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + smartWatch.getPrice() + "</li>\n" +
                    "                <li>" + smartWatch.getDiscount() + "</li>\n" +
                    "                <li>" + smartWatch.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name="+entry.getKey()+"&type=SmartWatch&maker="+CategoryName+"&access= ' class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name="+entry.getKey()+"&type=SmartWatch&maker="+CategoryName+ "&price=" +smartWatch.getPrice()+"&access= ' class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name="+entry.getKey()+"&type=SmartWatch&maker="+CategoryName+ "&price=" +smartWatch.getPrice()+"&access= ' class=\"button\">ViewReview</a>");


        }
        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
