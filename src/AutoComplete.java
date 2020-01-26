import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet( "/AutoComplete")
public class AutoComplete extends HttpServlet {

    private ServletContext context;

    String searchId=null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            searchId = request.getParameter("searchId");
            StringBuffer sb = new StringBuffer();
            if (searchId != null && action.equals("complete")) {
                searchId = searchId.trim().toLowerCase();
            }
            if (searchId == null) {
                context.getRequestDispatcher("/Error").forward(request, response);
            }
            boolean namesAdded = false;
            if (action.equals("complete")){
                if (!searchId.equals("")){
                    AjaxUtility a=new AjaxUtility();
                    sb=a.readdata(searchId);
                    if(sb!=null || !sb.equals(""))
                    {
                        namesAdded=true;
                    }
                    if (namesAdded)
                    {
                        response.setContentType("text/xml");
                        response.getWriter().write("<products>" + sb.toString() + "</products>");
                    }
                    else
                    {
                        //nothing to show
                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }
                }
            }
            if (action.equals("lookup"))
            {

                HashMap<String,Product> data=AjaxUtility.getData();
                if ((searchId != null) && data.containsKey(searchId.trim()))
                {
                    request.setAttribute("data",data.get(searchId.trim()));
                    RequestDispatcher rd=context.getRequestDispatcher("/ProductData");
                    rd.forward(request,response);
                }
            }
        }
        catch(Exception e){

        }
    }
}
