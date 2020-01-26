import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WriteView")
public class WriteView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    }

    protected void review(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            Utilities utility = new Utilities(request, out);
            if (!utility.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to Write a Review");
                response.sendRedirect("Login");
                return;
            }

            String productname=request.getParameter("name");
            String producttype=request.getParameter("type");
            String productmaker=request.getParameter("maker");
            String productprice=request.getParameter("price");

            utility.printHtml("header.html");
            out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                    "<div id=\"body\">\n" +
                    "    <section id=\"content\">\n" +
                    "\n" +
                    "        \t    <article>");
            out.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
            out.print("<a'>Review</a>");
            out.print("<table class='gridtable'>");
            out.print("<tr><td> Product Name: </td><td>");
            out.print(productname);
            out.print("<input type='hidden' name='productname' value='"+productname+"'>");
            out.print("</td></tr>");
            out.print("<tr><td> Product Type:</td><td>");
            out.print(producttype);
            out.print("<input type='hidden' name='producttype' value='"+producttype+"'>");
            out.print("</td></tr>");
            out.print("<tr><td> Product Price:</td><td>");
            out.print(productprice);
            out.print("<input type='hidden' name='productprice' value='"+productprice+"'>");
            out.print("</td></tr>");
            out.print("<tr><td> Product Maker: </td><td>");
            out.print(productmaker);
            out.print("<input type='hidden' name='productmaker' value='"+productmaker+"'>");
            out.print("</td></tr></table>");

            out.print("<table><tr></tr><tr></tr><tr><td> Review Rating: </td>");
            out.print("<td>");
            out.print("<select name='reviewrating'>");
            out.print("<option value='1' selected>1</option>");
            out.print("<option value='2'>2</option>");
            out.print("<option value='3'>3</option>");
            out.print("<option value='4'>4</option>");
            out.print("<option value='5'>5</option>");
            out.print("</td></tr>");

            out.print("<tr>");
            out.print("<td> Retailer Zip Code: </td>");
            out.print("<td> <input type='text' name='zipcode'> </td>");
            out.print("</tr>");


            out.print("<tr>");
            out.print("<td> Retailer City: </td>");
            out.print("<td> <input type='text' name='retailercity'> </td>");
            out.print("</tr>");

            out.print("<tr>");
            out.print("<td> Review Date: </td>");
            out.print("<td> <input type='date' name='reviewdate'> </td>");
            out.print("</tr>");

            out.print("<tr>");
            out.print("<td> Review Text: </td>");
            out.print("<td><textarea name='reviewtext' rows='4' cols='50'> </textarea></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></td></tr></table></form>");
            out.print("       \t\t</article>\n" +
                    "    </section>");
            utility.printHtml("leftNavigateBar.html");
            utility.printHtml("footer.html");
        }
        catch(Exception e){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility= new Utilities(request, pw);
        review(request, response);
    }
}
