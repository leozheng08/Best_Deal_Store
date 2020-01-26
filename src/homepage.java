import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "homepage")
public class homepage extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request,out);
        utility.printHtml("header.html");
//        utility.printHtml("Content.html");
        RequestDispatcher rd=request.getRequestDispatcher("DealMatchesUtilities");
        rd.include(request,response);
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");

    }
}
