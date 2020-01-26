import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "Registration")
public class Registration extends HttpServlet {
    private String errorMsg=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request, out);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String userType = request.getParameter("userType");
        System.out.println(username+","+password);
        HashMap<String, User> map = new HashMap<>();
        if(!password.equals(repassword))
        {
            errorMsg = "Passwords doesn't match!";
        }
        else{
            String message=MySqlDataStoreUtilities.getConnection();
            if(message.equals("Successfull")) {
                try {
                    map = MySqlDataStoreUtilities.selectUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (map.containsKey(username)) {
                    errorMsg = "Username already exist as " + userType;
                }
                else {
                    User user = new User(username, password, userType);
                    map.put(username, user);
                    try {
                        MySqlDataStoreUtilities.insertUser(username, password, repassword, userType);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loginMsg", "Your " + userType + " account has been created. Please login");
                    response.sendRedirect("Login");
                    return;
                }
            }
            else
            {
                errorMsg="MySql server is not up and running";
            }

        }
        displayRegistration(request, response, out, errorMsg);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        displayRegistration(request, response, out, errorMsg);
    }

    private void displayRegistration(HttpServletRequest request, HttpServletResponse response, PrintWriter out, String errorMsg) throws IOException {
        Utilities utility = new Utilities(request, out);
        utility.printHtml("header.html");
        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        <article>\n" +
                "\n" +
                "            <h3>Register</h3>\n" +
                "            <fieldset>\n" +
                "                <legend><a class='' href='Registration'>New User? Register here!</a></legend>\n");
        if(errorMsg!=null){
            out.print("<h3>"+errorMsg+"</h3>\n");
        }
        out.print("                <form action=\"Registration\" method=\"post\">\n" +
                "                    <p><label >username:</label>\n" +
                "                        <input name=\"username\" id=\"username\" value=\"\" type=\"text\" /></p>\n" +
                "                    <p><label >password:</label>\n" +
                "                        <input name=\"password\" id=\"password\" value=\"\" type=\"text\" /></p>\n" +
                "                    <p><label >repassword:</label>\n" +
                "                        <input name=\"repassword\" id=\"repassword\" value=\"\" type=\"text\" /></p>\n" +
                "                    <p><label>userType:</label>\n" +
                "                    </p>\n" +
                "                    <select name=\"userType\">\n" +
                "                        <option value='Customer'>Customer</option>\n" +
                "                        <option value='Retailer'>Store Manager</option>\n" +
                "                        <option value='Manager'>Salesman</option>\n" +
                "                    </select>\n" +
                "                    <p><input name=\"register\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"register\" type=\"submit\" /></p>\n" +
                "                </form>\n" +
                "            </fieldset>\n" +
                "        </article>\n" +
                "    </section>");

        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");

    }


}
