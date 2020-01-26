import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    private String errorMsg=null;
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        HashMap<String, User> map=new HashMap<String, User>();
        try{
            map=MySqlDataStoreUtilities.selectUser();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        User tempUser = map.get(username);
        if(tempUser==null){
            errorMsg="this User is not exist!";
        }
        else{
            String userPassword = tempUser.getPassword();
            if(!password.equals(userPassword)){
                errorMsg="the password is not correct";
            }
            else{
                HttpSession session = request.getSession(true);
                session.setAttribute("username", tempUser.getName());
                session.setAttribute("userType", tempUser.getUserType());
                response.sendRedirect("homepage");
                return;
            }
        }
        displayLogin(request, response, out, errorMsg);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        displayLogin(request, response, out, errorMsg);

    }

    private void displayLogin(HttpServletRequest request, HttpServletResponse response, PrintWriter out, String errorMsg) throws IOException {
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");
        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        <article>\n" +
                "\n" +
                "            <h3>Login</h3>\n" +
                "            <fieldset>\n" +
                "                <legend><a class='' href='Registration'>New User? Register here!</a></legend>");

        if(errorMsg!=null){
            out.print("<h3>Please check your username, password and user type!</h3>");
        }
        out.print("<form action=\"Login\" method=\"post\">\n" +
                "                    <p><label >username:</label>\n" +
                "                        <input name=\"username\" id=\"username\" value=\"\" type=\"text\" /></p>\n" +
                "                    <p><label >password:</label>\n" +
                "                        <input name=\"password\" id=\"password\" value=\"\" type=\"text\" /></p>\n" +
                "                    <p><label>userType:</label>\n" +
                "                    </p>\n" +
                "                    <select name=\"userType\">\n" +
                "                        <option value='customer'>Customer</option>\n" +
                "                        <option value='retailer'>Store Manager</option>\n" +
                "                        <option value='manager'>Salesman</option>\n" +
                "                    </select>\n" +
                "                    <p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"Login\" type=\"submit\" /></p>\n" +
                "                </form>\n" +
                "            </fieldset>\n" +
                "        </article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
