import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SubmitReview")
public class SubmitReview extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility= new Utilities(request, out);
        storeReview(request, response);
    }

    protected void storeReview(HttpServletRequest request, HttpServletResponse response) {
        try
        {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request,pw);
            if(!utility.isLoggedin()){
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to add items to cart");
                response.sendRedirect("Login");
                return;
            }
            String productname=request.getParameter("productname");
            String producttype=request.getParameter("producttype");
            String productprice=request.getParameter("productprice");
            String productmaker=request.getParameter("productmaker");
            String reviewrating=request.getParameter("reviewrating");
            String reviewdate=request.getParameter("reviewdate");
            String reviewtext=request.getParameter("reviewtext");
            String retailerpin=request.getParameter("zipcode");
            String retailercity = request.getParameter("retailercity");
            String message=utility.storeReview(productname,producttype,productmaker,reviewrating,reviewdate,reviewtext,retailerpin,productprice,retailercity);

            utility.printHtml("header.html");
            pw.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                    "<div id=\"body\">\n" +
                    "    <section id=\"content\">\n" +
                    "\n" +
                    "        \t    <article>");
            pw.print("<form name ='Cart' action='CheckOut' method='post'>");
            pw.print("<h2>");
            pw.print("Review");
            pw.print("</h2>");
            if(message.equals("Successfull"))
                pw.print("<h2>Review for &nbsp"+productname+" Stored </h2>");
            else
                pw.print("<h2>Mongo Db is not up and running </h2>");
            pw.print("       \t\t</article>\n" +
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
    }
}
