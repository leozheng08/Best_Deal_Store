import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "TVList")
public class TVList extends HttpServlet {
//    private static final long serialVersionUID = 8259590407715072561L;

//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, TV> map = new HashMap<String, TV>();

        if (CategoryName == null) {
            map.putAll(SaxParseStore.tvs);
            name = "";
        } else {
            if (CategoryName.equals("TCL")) {
                for (Map.Entry<String, TV> entry : SaxParseStore.tvs.entrySet()) {
                    if (entry.getValue().getRetailer().equals("TCL")) {
                        map.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "TCL";
            } else if (CategoryName.equals("Samsung")) {
                for (Map.Entry<String, TV> entry : SaxParseStore.tvs.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Samsung")) {
                        map.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Samsung";
            } else if (CategoryName.equals("Insignia")) {
                for (Map.Entry<String, TV> entry : SaxParseStore.tvs.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Insignia")) {
                        map.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Insignia";
            }
        }

        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");
//        out.print("<div id='content'><div class='post'><h4 class='title meta'>");
//        out.print(name+"Consoles");
//        out.print("</h4><div class='entry'><table id='bestseller'>");
//        int i = 1; int size= map.size();
        for (Map.Entry<String, TV> entry : map.entrySet()) {
            TV tv = entry.getValue();

            out.print("<h3>" + tv.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/TV/" + tv.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + tv.getPrice() + "</li>\n" +
                    "                <li>" + tv.getDiscount() + "</li>\n" +
                    "                <li>" + tv.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name=" + entry.getKey() + "&type=TV&maker=" + CategoryName + "&access= 'class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name=" + entry.getKey() + "&type=TV&maker=" + CategoryName + "&price=" + tv.getPrice() + "&access= 'class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name=" + entry.getKey() + "&type=TV&maker=" + CategoryName + "&price=" + tv.getPrice() + "&access= ' class=\"button\">ViewReview</a>");
            out.print("       \t\t</article>\n" +
                    "    </section>");
            utility.printHtml("leftNavigateBar.html");
            utility.printHtml("footer.html");
        }
    }
}
