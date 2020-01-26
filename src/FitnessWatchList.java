import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FitnessWatchList")
public class FitnessWatchList extends HttpServlet {

//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, FitnessWatch> map = new HashMap<String, FitnessWatch>();

        if(CategoryName==null){
            map.putAll(SaxParseStore.fitnessWatches);
            name = "";
        }
        else{
            if(CategoryName.equals("Garmin"))
            {
                for(Map.Entry<String,FitnessWatch> entry : SaxParseStore.fitnessWatches.entrySet())
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
                for(Map.Entry<String,FitnessWatch> entry : SaxParseStore.fitnessWatches.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Samsung"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Samsung";
            }
            else if(CategoryName.equals("Apple"))
            {
                for(Map.Entry<String,FitnessWatch> entry : SaxParseStore.fitnessWatches.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Apple"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Apple";
            }

        }
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        for (Map.Entry<String, FitnessWatch> entry : map.entrySet()) {
            FitnessWatch fitnessWatch = entry.getValue();

            out.print("<h3>" + fitnessWatch.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/FitnessWatch/" + fitnessWatch.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + fitnessWatch.getPrice() + "</li>\n" +
                    "                <li>" + fitnessWatch.getDiscount() + "</li>\n" +
                    "                <li>" + fitnessWatch.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name="+entry.getKey()+"&type=FitnessWatch&maker="+CategoryName+"&access= ' class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name=" + entry.getKey() + "&type=FitnessWatch&maker=" + CategoryName + "&price=" + fitnessWatch.getPrice() + "&access= ' class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name=" + entry.getKey() + "&type=FitnessWatch&maker=" + CategoryName + "&price=" + fitnessWatch.getPrice() + "&access= ' class=\"button\">ViewReview</a>");


        }
        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
