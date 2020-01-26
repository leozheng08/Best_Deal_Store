import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AjaxUtility {

    StringBuffer sb = new StringBuffer();
    boolean namesAdded = false;
    static Connection conn = null;
    static String message;

    public static String getConnection()
    {

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase?userTimezone=true&serverTimezone=UTC","root","5991308Zheng");
            message="Successfull";
            return message;
        }
        catch(SQLException e)
        {
            message="unsuccessful";
            return message;
        }
        catch(Exception e)
        {
            message="unsuccessful";
            return message;
        }
    }
    public static HashMap<String,Product> getData() {
        HashMap<String,Product> hm=new HashMap<String,Product>();
        try
        {
            getConnection();

            String selectproduct="select * from  Productdetails";
            PreparedStatement pst = conn.prepareStatement(selectproduct);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	Product p = new Product(rs.getString("Id"),rs.getString("productImage"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getDouble("ManufacturerRebate"),rs.getString("ProductType"));
                hm.put(rs.getString("Id"), p);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return hm;
    }

    public StringBuffer readdata(String searchId) {
        HashMap<String,Product> data;
        data=getData();

        Iterator it = data.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pi = (Map.Entry)it.next();
            if(pi!=null)
            {
                Product p=(Product)pi.getValue();
                if (p.getProductName().toLowerCase().startsWith(searchId))
                {
                    sb.append("<product>");
                    sb.append("<id>" + p.getId() + "</id>");
                    sb.append("<productName>" + p.getProductName() + "</productName>");
                    sb.append("</product>");
                }
            }
        }
        return sb;
    }
}
