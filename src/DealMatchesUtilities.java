import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet( "/DealMatchesUtilities")
public class DealMatchesUtilities extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String line=null;
        String TOMCAT_HOME = System.getProperty("catalina.home");
        HashMap<String,Product> selectedproducts=new HashMap<String,Product>();
        pw.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");
        try {
            pw.print("<a href='#'>Welcome to BestDealStore </a></h2>");
            HashMap<String, Product> productmap = MySqlDataStoreUtilities.getData();
            for (Map.Entry<String, Product> entry : productmap.entrySet()) {
                if (selectedproducts.size() < 2 && !selectedproducts.containsKey(entry.getKey())) {
                    BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/zhengleo/IdeaProjects/BestDealStore_2/web/DealMatches.txt")));
                    line = reader.readLine().toLowerCase();
                    if (line == null) {
                        pw.print("<h2 align='center'>No Offers Found</h2>");
                        break;
                    } else {
                        do {

                            if (line.contains(entry.getKey())) {

                                pw.print("<h2>" + line + "</h2>");
                                pw.print("<br>");
                                selectedproducts.put(entry.getKey(), entry.getValue());
                                break;
                            }

                        }
                        while ((line = reader.readLine()) != null);

                    }
                }
            }


        }
        catch(Exception e)
        {
            pw.print("<h2 align='center'>No Offers Found</h2>");
        }
        pw.print("<a style='font-size: 24px;'>Deal Matches</a>");
        if(selectedproducts.size()==0)
        {
            pw.print("<h2 align='center'>No Deals Found</h2>");
        }
        else
        {
            for (Map.Entry<String, Product> entry : selectedproducts.entrySet()) {
                Product product = entry.getValue();

                pw.print("<h3>" + product.getProductName() + "</h3>\n" +
                        "\n" +
                        "\n" +
                        "            <p><img src='images/"+product.getProductType()+"/" + product.getImage() + "' height='100' width='100'></p>\n" +
                        "            <ul class=\"styledlist\">\n" +
                        "                <li>" + product.getProductPrice() + "</li>\n" +
                        "                <li>" + product.getDiscount() + "</li>\n" +
                        "                <li>" + product.getRetailer() + "</li>\n" +
                        "            </ul>\n" +
                        "\n" +
                        "            <a href='Cart?name="+entry.getKey()+"&type="+product.getProductType()+"&maker="+product.getRetailer()+"&access=' class=\"button\">Buy Now</a>\n" +
                        "            <a href='WriteView?name=" + entry.getKey() + "&type="+product.getProductType()+"&maker=" + product.getRetailer() + "&price=" + product.getProductPrice() + "&access=' class=\"button\">WriteView</a>\n" +
                        "            <a href='ViewReview?name=" + entry.getKey() + "&type="+product.getProductType()+"&maker=" + product.getRetailer() + "&price=" + product.getProductPrice() + "&access=' class=\"button\">ViewReview</a>");


            }
        }
        pw.print("</article>\n" +
                "    </section>");

    }

}
