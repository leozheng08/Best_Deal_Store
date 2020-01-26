import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "HeadPhoneList")
public class HeadPhoneList extends HttpServlet {

//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, HeadPhone> map = new HashMap<String, HeadPhone>();

        if(CategoryName==null){
            map.putAll(SaxParseStore.headPhones);
            name = "";
        }
        else{
            if(CategoryName.equals("Beats"))
            {
                for(Map.Entry<String, HeadPhone> entry : SaxParseStore.headPhones.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Beats"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Beats";
            }
            else if(CategoryName.equals("Sony"))
            {
                for(Map.Entry<String, HeadPhone> entry : SaxParseStore.headPhones.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Sony"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Sony";
            }
            else if(CategoryName.equals("Apple"))
            {
                for(Map.Entry<String, HeadPhone> entry : SaxParseStore.headPhones.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Apple"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Sony";
            }
        }
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        for (Map.Entry<String, HeadPhone> entry : map.entrySet()) {
            HeadPhone headPhone = entry.getValue();

            out.print("<h3>" + headPhone.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/HeadPhone/" + headPhone.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + headPhone.getPrice() + "</li>\n" +
                    "                <li>" + headPhone.getDiscount() + "</li>\n" +
                    "                <li>" + headPhone.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name="+entry.getKey()+"&type=HeadPhone&maker="+CategoryName+"&access= ' class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name=" + entry.getKey() + "&type=HeadPhone&maker=" + CategoryName + "&price=" + headPhone.getPrice() + "&access= ' class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name=" + entry.getKey() + "&type=HeadPhone&maker=" + CategoryName + "&price=" + headPhone.getPrice() + "&access= ' class=\"button\">ViewReview</a>");


        }
        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
