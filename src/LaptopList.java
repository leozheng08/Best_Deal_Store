import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LaptopList")
public class LaptopList extends HttpServlet {

//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, Laptop> map = new HashMap<String, Laptop>();

        if(CategoryName==null){
            map.putAll(SaxParseStore.laptops);
            name = "";
        }
        else{
            if(CategoryName.equals("Google"))
            {
                for(Map.Entry<String, Laptop> entry : SaxParseStore.laptops.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Google"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Google";
            }
            else if(CategoryName.equals("HP"))
            {
                for(Map.Entry<String, Laptop> entry : SaxParseStore.laptops.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("HP"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "HP";
            }
            else if(CategoryName.equals("Lenovo"))
            {
                for(Map.Entry<String, Laptop> entry : SaxParseStore.laptops.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Lenovo"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Lenovo";
            }

        }
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        for (Map.Entry<String, Laptop> entry : map.entrySet()) {
            Laptop laptop = entry.getValue();

            out.print("<h3>" + laptop.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/Laptop/" + laptop.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + laptop.getPrice() + "</li>\n" +
                    "                <li>" + laptop.getDiscount() + "</li>\n" +
                    "                <li>" + laptop.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name="+entry.getKey()+"type=Laptop&maker="+CategoryName+"&access= 'class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name="+entry.getKey()+"&type=Laptop&maker="+CategoryName+ "&price=" +laptop.getPrice()+"&access= ' class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name="+entry.getKey()+"&type=Laptop&maker="+CategoryName+ "&price=" +laptop.getPrice()+"&access= ' class=\"button\">ViewReview</a>");


        }
        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
