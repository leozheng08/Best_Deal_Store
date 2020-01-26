import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "PhoneList")
public class PhoneList extends HttpServlet {

//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, Phone> map = new HashMap<String, Phone>();

        if(CategoryName==null){
            map.putAll(SaxParseStore.phones);
            name = "";
        }
        else{
            if(CategoryName.equals("Google"))
            {
                for(Map.Entry<String,Phone> entry : SaxParseStore.phones.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Google"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Google";
            }
            else if(CategoryName.equals("LG"))
            {
                for(Map.Entry<String,Phone> entry : SaxParseStore.phones.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("LG"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "LG";
            }
            else if(CategoryName.equals("Razer"))
            {
                for(Map.Entry<String,Phone> entry : SaxParseStore.phones.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Razer"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Razer";
            }
            else if(CategoryName.equals("Samsung"))
            {
                for(Map.Entry<String,Phone> entry : SaxParseStore.phones.entrySet())
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
                for(Map.Entry<String,Phone> entry : SaxParseStore.phones.entrySet())
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

        for (Map.Entry<String, Phone> entry : map.entrySet()) {
            Phone phone = entry.getValue();

            out.print("<h3>" + phone.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/Phone/" + phone.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + phone.getPrice() + "</li>\n" +
                    "                <li>" + phone.getDiscount() + "</li>\n" +
                    "                <li>" + phone.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name="+entry.getKey()+"&type=Phone&maker="+CategoryName+"&access= ' class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name="+entry.getKey()+"&type=Phone&maker="+CategoryName+ "&price=" +phone.getPrice()+"&access= ' class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name="+entry.getKey()+"&type=Phone&maker="+CategoryName+ "&price=" +phone.getPrice()+"&access= ' class=\"button\">ViewReview</a>");


        }
        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
