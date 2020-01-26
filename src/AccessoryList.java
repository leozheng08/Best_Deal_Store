import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AccessoryList")
public class AccessoryList extends HttpServlet {
//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");
        String ConsoleName = request.getParameter("console");
        HashMap<String, Accessory> map = new HashMap<String, Accessory>();

        if(CategoryName==null){
            map.putAll(SaxParseStore.accessories);
            name = "";
        }
        else{

        if (CategoryName.equals("TCL")) {
            for (Map.Entry<String, Accessory> entry : SaxParseStore.accessories.entrySet()) {
                if (entry.getValue().getRetailer().equals("TCL")) {
                    map.put(entry.getValue().getId(), entry.getValue());
                }
            }
            name = "TCL";


        }

            if(CategoryName.equals("Samsung"))
            {
                if(SaxParseStore.accessories!=null) {
                    for (Map.Entry<String, Accessory> entry : SaxParseStore.accessories.entrySet()) {
                        if (entry.getValue().getRetailer().equals("Samsung")) {
                            map.put(entry.getValue().getId(), entry.getValue());
                        }
                    }
                    name = "Samsung";
                }

            }

        }
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");
//        if(SaxParseStore.accessories!=null){
//            out.print("none");
//        }
        for (Map.Entry<String, Accessory> entry : map.entrySet()) {
            Accessory accessory = entry.getValue();

            out.print("<h3>" + accessory.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/Accessory/" + accessory.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + accessory.getPrice() + "</li>\n" +
                    "                <li>" + accessory.getDiscount() + "</li>\n" +
                    "                <li>" + accessory.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name="+entry.getKey()+"&type=Accessory&maker="+CategoryName+"&access="+ConsoleName+"' class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name=" + entry.getKey() + "&type=Accessory&maker=" + CategoryName + "&price=" + accessory.getPrice() + "&access="+ConsoleName+" ' class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name=" + entry.getKey() + "&type=Accessory&maker=" + CategoryName + "&price=" + accessory.getPrice() + "&access=" +ConsoleName+"' class=\"button\">ViewReview</a>");


        }
        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
