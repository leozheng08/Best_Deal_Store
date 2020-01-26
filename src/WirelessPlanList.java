import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "WirelessPlanList")
public class WirelessPlanList extends HttpServlet {

//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, WirelessPlan> map = new HashMap<String, WirelessPlan>();

        if(CategoryName==null){
            map.putAll(SaxParseStore.wirelessPlans);
            name = "";
        }
        else{
            if(CategoryName.equals("Sprint"))
            {
                for(Map.Entry<String, WirelessPlan> entry : SaxParseStore.wirelessPlans.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Sprint"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Sprint";
            }
            else if(CategoryName.equals("MintMobile"))
            {
                for(Map.Entry<String, WirelessPlan> entry : SaxParseStore.wirelessPlans.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("MintMobile"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "MintMobile";
            }

        }
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        for (Map.Entry<String, WirelessPlan> entry : map.entrySet()) {
            WirelessPlan wirelessPlan = entry.getValue();

            out.print("<h3>" + wirelessPlan.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/WirelessPlan/" + wirelessPlan.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + wirelessPlan.getPrice() + "</li>\n" +
                    "                <li>" + wirelessPlan.getDiscount() + "</li>\n" +
                    "                <li>" + wirelessPlan.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name="+entry.getKey()+"&type=WirelessPlan&maker="+CategoryName+"&access= ' class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name="+entry.getKey()+"&type=WirelessPlan&maker="+CategoryName+ "&price=" +wirelessPlan.getPrice()+"&access= ' class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name="+entry.getKey()+"&type=WirelessPlan&maker="+CategoryName+ "&price=" +wirelessPlan.getPrice()+"&access= ' class=\"button\">ViewReview</a>");


        }
        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
