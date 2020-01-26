import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SoundSystemList")
public class SoundSystemList extends HttpServlet {

//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, SoundSystem> map = new HashMap<>();

        if (CategoryName == null) {
            map.putAll(SaxParseStore.soundSystems);
            name = "";
        } else {
            if (CategoryName.equals("Klipsch")) {
                for (Map.Entry<String, SoundSystem> entry : SaxParseStore.soundSystems.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Klipsch")) {
                        map.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Klipsch";
            } else if (CategoryName.equals("LG")) {
                for (Map.Entry<String, SoundSystem> entry : SaxParseStore.soundSystems.entrySet()) {
                    if (entry.getValue().getRetailer().equals("LG")) {
                        map.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "LG";
            } else if (CategoryName.equals("Samsung")) {
                for (Map.Entry<String, SoundSystem> entry : SaxParseStore.soundSystems.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Samsung")) {
                        map.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Samsung";
            } else if (CategoryName.equals("Bose")) {
                for (Map.Entry<String, SoundSystem> entry : SaxParseStore.soundSystems.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Bose")) {
                        map.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Bose";
            }
        }

        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        for (Map.Entry<String, SoundSystem> entry : map.entrySet()) {
            SoundSystem soundSystem = entry.getValue();

            out.print("<h3>" + soundSystem.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/SoundSystem/" + soundSystem.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + soundSystem.getPrice() + "</li>\n" +
                    "                <li>" + soundSystem.getDiscount() + "</li>\n" +
                    "                <li>" + soundSystem.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name="+entry.getKey()+"&type=SoundSystem&maker="+CategoryName+"&access= ' class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name="+entry.getKey()+"&type=SoundSystem&maker="+CategoryName+ "&price=" +soundSystem.getPrice()+"&access= ' class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name="+entry.getKey()+"&type=SoundSystem&maker="+CategoryName+ "&price=" +soundSystem.getPrice()+"&access= ' class=\"button\">ViewReview</a>");


        }
        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
