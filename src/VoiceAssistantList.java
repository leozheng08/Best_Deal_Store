import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "VoiceAssistantList")
public class VoiceAssistantList extends HttpServlet {

//    SaxParseStore store = new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore/web/ProductCatalog.xml");
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, VoiceAssistant> map = new HashMap<String, VoiceAssistant>();

        if(CategoryName==null){
            map.putAll(SaxParseStore.voiceAssistants);
            name = "";
        }
        else{
            if(CategoryName.equals("Google"))
            {
                for(Map.Entry<String, VoiceAssistant> entry : SaxParseStore.voiceAssistants.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Google"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Google";
            }
            else if(CategoryName.equals("Amazon"))
            {
                for(Map.Entry<String, VoiceAssistant> entry : SaxParseStore.voiceAssistants.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Amazon"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Amazon";
            }
            else if(CategoryName.equals("iHome"))
            {
                for(Map.Entry<String, VoiceAssistant> entry : SaxParseStore.voiceAssistants.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("iHome"))
                    {
                        map.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "iHome";
            }

        }
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");

        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");

        for (Map.Entry<String, VoiceAssistant> entry : map.entrySet()) {
            VoiceAssistant voiceAssistant = entry.getValue();

            out.print("<h3>" + voiceAssistant.getName() + "</h3>\n" +
                    "\n" +
                    "\n" +
                    "            <p><img src='images/VoiceAssistant/" + voiceAssistant.getImage() + "' height='100' width='100'></p>\n" +
                    "            <ul class=\"styledlist\">\n" +
                    "                <li>" + voiceAssistant.getPrice() + "</li>\n" +
                    "                <li>" + voiceAssistant.getDiscount() + "</li>\n" +
                    "                <li>" + voiceAssistant.getRetailer() + "</li>\n" +
                    "            </ul>\n" +
                    "\n" +
                    "            <a href='Cart?name="+entry.getKey()+"&type=VoiceAssistant&maker="+CategoryName+"&access= ' class=\"button\">Buy Now</a>\n" +
                    "            <a href='WriteView?name="+entry.getKey()+"&type=VoiceAssistant&maker="+CategoryName+ "&price=" +voiceAssistant.getPrice()+"&access= ' class=\"button\">WriteView</a>\n" +
                    "            <a href='ViewReview?name="+entry.getKey()+"&type=VoiceAssistant&maker="+CategoryName+ "&price=" +voiceAssistant.getPrice()+"&access= ' class=\"button\">ViewReview</a>");


        }
        out.print("       \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
