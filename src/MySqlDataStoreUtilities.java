import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MySqlDataStoreUtilities {
    public static Connection conn;
    static String message;
    private static Statement statement;
    private static ResultSet resultSet;
    private static String url = "jdbc:mysql://localhost:3306/exampledatabase?userTimezone=true&serverTimezone=UTC";
    private static String username = "root", pass = "5991308Zheng";

    public static String getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, pass);
            message="Successfull";
            return message;
        }
        catch(SQLException e)
        {
            message="unsuccessful";
            return message;
        }

        catch (Exception e) {
            message=e.getMessage();
            return message;
        }
    }

    public static void Insertproducts()
    {
        try{
            getConnection();
            String truncatetableacc = "delete from Product_accessories;";
            PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
            pstt.executeUpdate();

            String truncatetableprod = "delete from  Productdetails;";
            PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
            psttprod.executeUpdate();



            String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,ManufacturerRebate)" +
                    "VALUES (?,?,?,?,?,?,?,?,?);";
            int i=0;
            for(Map.Entry<String,Accessory> entry : SaxParseStore.accessories.entrySet())
            {
                System.out.println(SaxParseStore.accessories.size());
                System.out.println(entry.getValue().getId()+" "+entry.getValue().getName()+"....."+entry.getValue().getRetailer());
                String name = "Accessory";
                Accessory acc = entry.getValue();
                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,acc.getId());
                pst.setString(3,acc.getName());
                pst.setDouble(4,acc.getPrice());
                pst.setString(5,acc.getImage());
                pst.setString(6,acc.getRetailer());
                pst.setString(7,acc.getCondition());
                pst.setDouble(8,acc.getDiscount());
                pst.setDouble(9,acc.getRebate());
                pst.executeUpdate();
                i++;

            }
//
            for(Map.Entry<String,TV> entry : SaxParseStore.tvs.entrySet())
            {
                TV con = entry.getValue();
                String name = "TV";



                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,con.getId());
                pst.setString(3,con.getName());
                pst.setDouble(4,con.getPrice());
                pst.setString(5,con.getImage());
                pst.setString(6,con.getRetailer());
                pst.setString(7,con.getCondition());
                pst.setDouble(8,con.getDiscount());
                pst.setDouble(9,con.getRebate());

                pst.executeUpdate();
                try{
                    HashMap<String,String> acc = con.getAccessories();
                    for(Map.Entry<String,String> ele :acc.entrySet()){
                        System.out.println(ele.getValue()+" "+ele.getKey());
                    }
                    String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
                            "VALUES (?,?);";
                    for(Map.Entry<String,String> accentry : acc.entrySet())
                    {
                        PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
                        pstacc.setString(1,con.getId());
                        pstacc.setString(2,accentry.getValue());
                        pstacc.executeUpdate();
                    }
                }catch(Exception et){
                    et.printStackTrace();
                }
            }

            for(Map.Entry<String,FitnessWatch> entry : SaxParseStore.fitnessWatches.entrySet())
            {
                String name = "FitnessWatch";
                FitnessWatch fitnessWatch = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,fitnessWatch.getId());
                pst.setString(3,fitnessWatch.getName());
                pst.setDouble(4,fitnessWatch.getPrice());
                pst.setString(5,fitnessWatch.getImage());
                pst.setString(6,fitnessWatch.getRetailer());
                pst.setString(7,fitnessWatch.getCondition());
                pst.setDouble(8,fitnessWatch.getDiscount());
                pst.setDouble(9,fitnessWatch.getRebate());

                pst.executeUpdate();


            }
            for(Map.Entry<String,HeadPhone> entry : SaxParseStore.headPhones.entrySet())
            {
                String name = "HeadPhone";
                HeadPhone headPhone = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,headPhone.getId());
                pst.setString(3,headPhone.getName());
                pst.setDouble(4,headPhone.getPrice());
                pst.setString(5,headPhone.getImage());
                pst.setString(6,headPhone.getRetailer());
                pst.setString(7,headPhone.getCondition());
                pst.setDouble(8,headPhone.getDiscount());
                pst.setDouble(9,headPhone.getRebate());

                pst.executeUpdate();


            }
            for(Map.Entry<String,Laptop> entry : SaxParseStore.laptops.entrySet())
            {
                String name = "Laptop";
                Laptop laptop = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,laptop.getId());
                pst.setString(3,laptop.getName());
                pst.setDouble(4,laptop.getPrice());
                pst.setString(5,laptop.getImage());
                pst.setString(6,laptop.getRetailer());
                pst.setString(7,laptop.getCondition());
                pst.setDouble(8,laptop.getDiscount());
                pst.setDouble(9,laptop.getRebate());

                pst.executeUpdate();


            }
            for(Map.Entry<String,Phone> entry : SaxParseStore.phones.entrySet())
            {
                String name = "Phone";
                Phone phone = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,phone.getId());
                pst.setString(3,phone.getName());
                pst.setDouble(4,phone.getPrice());
                pst.setString(5,phone.getImage());
                pst.setString(6,phone.getRetailer());
                pst.setString(7,phone.getCondition());
                pst.setDouble(8,phone.getDiscount());
                pst.setDouble(9,phone.getRebate());

                pst.executeUpdate();


            }

            for(Map.Entry<String,SmartWatch> entry : SaxParseStore.smartWatches.entrySet())
            {
                String name = "SmartWatch";
                SmartWatch smartWatch = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,smartWatch.getId());
                pst.setString(3,smartWatch.getName());
                pst.setDouble(4,smartWatch.getPrice());
                pst.setString(5,smartWatch.getImage());
                pst.setString(6,smartWatch.getRetailer());
                pst.setString(7,smartWatch.getCondition());
                pst.setDouble(8,smartWatch.getDiscount());
                pst.setDouble(9,smartWatch.getRebate());

                pst.executeUpdate();


            }

            for(Map.Entry<String,SoundSystem> entry : SaxParseStore.soundSystems.entrySet())
            {
                String name = "SoundSystem";
                SoundSystem soundSystem = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,soundSystem.getId());
                pst.setString(3,soundSystem.getName());
                pst.setDouble(4,soundSystem.getPrice());
                pst.setString(5,soundSystem.getImage());
                pst.setString(6,soundSystem.getRetailer());
                pst.setString(7,soundSystem.getCondition());
                pst.setDouble(8,soundSystem.getDiscount());
                pst.setDouble(9,soundSystem.getRebate());

                pst.executeUpdate();


            }
            for(Map.Entry<String,VoiceAssistant> entry : SaxParseStore.voiceAssistants.entrySet())
            {
                String name = "VoiceAssistant";
                VoiceAssistant voiceAssistant = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,voiceAssistant.getId());
                pst.setString(3,voiceAssistant.getName());
                pst.setDouble(4,voiceAssistant.getPrice());
                pst.setString(5,voiceAssistant.getImage());
                pst.setString(6,voiceAssistant.getRetailer());
                pst.setString(7,voiceAssistant.getCondition());
                pst.setDouble(8,voiceAssistant.getDiscount());
                pst.setDouble(9,voiceAssistant.getRebate());

                pst.executeUpdate();


            }
            for(Map.Entry<String,WirelessPlan> entry : SaxParseStore.wirelessPlans.entrySet())
            {
                String name = "WirelessPlan";
                WirelessPlan wirelessPlan = entry.getValue();

                PreparedStatement pst = conn.prepareStatement(insertProductQurey);
                pst.setString(1,name);
                pst.setString(2,wirelessPlan.getId());
                pst.setString(3,wirelessPlan.getName());
                pst.setDouble(4,wirelessPlan.getPrice());
                pst.setString(5,wirelessPlan.getImage());
                pst.setString(6,wirelessPlan.getRetailer());
                pst.setString(7,"new");
                pst.setDouble(8,wirelessPlan.getDiscount());
                pst.setDouble(9,wirelessPlan.getRebate());

                pst.executeUpdate();


            }



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> getProducts(){
        ArrayList<Product> array = new ArrayList<Product>();
        try{
            getConnection();
            String selectProduct="SELECT productName,count(productName)as count FROM Productdetails group by productName;";
            PreparedStatement pst = conn.prepareStatement(selectProduct);
            ResultSet rs = pst.executeQuery();

            String selectProductPrice="SELECT distinct productName,ProductType,productPrice FROM Productdetails;";
            PreparedStatement pst2 = conn.prepareStatement(selectProductPrice);
            ResultSet rs2 = pst2.executeQuery();
            while(rs.next()&&rs2.next()){
                Product product = new Product(rs.getString("productName"),rs2.getString("ProductType"),rs2.getDouble("productPrice"),rs.getInt("count"));
                array.add(product);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<Product> getProductsOnsale(){
        ArrayList<Product> array = new ArrayList<Product>();
        try{
            getConnection();
            String selectProduct="SELECT * FROM Productdetails where productDiscount !=0";
            PreparedStatement pst = conn.prepareStatement(selectProduct);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Product product = new Product(rs.getString("Id"),rs.getString("productImage"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getDouble("ManufacturerRebate"),rs.getString("ProductType"));
                array.add(product);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<Product> getProductsOnRebate(){
        ArrayList<Product> array = new ArrayList<Product>();
        try{
            getConnection();
            String selectProduct="SELECT * FROM Productdetails where ManufacturerRebate!=0";
            PreparedStatement pst = conn.prepareStatement(selectProduct);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Product product = new Product(rs.getString("Id"),rs.getString("productImage"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getDouble("ManufacturerRebate"),rs.getString("ProductType"));
                array.add(product);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return array;
    }

    public static HashMap<String,TV> getTVs()
    {
        HashMap<String,TV> hm=new HashMap<String,TV>();
        try
        {
            getConnection();

            String selectConsole="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectConsole);
            pst.setString(1,"TV");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	TV con = new TV(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), con);
                con.setId(rs.getString("Id"));

                try
                {
                    String selectaccessory = "Select * from Product_accessories where productName=?";
                    PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
                    pstacc.setString(1,rs.getString("Id"));
                    ResultSet rsacc = pstacc.executeQuery();

                    HashMap<String,String> acchashmap = new HashMap<String,String>();
                    while(rsacc.next())
                    {
                        if (rsacc.getString("accessoriesName") != null){

                            acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
                            con.setAccessories(acchashmap);
                        }

                    }
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }

    public static HashMap<String,Accessory> getAccessories()
    {
        HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
        try
        {
            getConnection();

            String selectTablet="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"Accessory");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	Accessory accessory = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), accessory);
                accessory.setId(rs.getString("Id"));

            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }

    public static HashMap<String,FitnessWatch> getFitnessWatches()
    {
        HashMap<String,FitnessWatch> hm=new HashMap<String,FitnessWatch>();
        try
        {
            getConnection();

            String selectTablet="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"FitnessWatch");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	FitnessWatch fitnessWatch = new FitnessWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), fitnessWatch);
                fitnessWatch.setId(rs.getString("Id"));

            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }
    public static HashMap<String,HeadPhone> getHeadPhones()
    {
        HashMap<String,HeadPhone> hm=new HashMap<String,HeadPhone>();
        try
        {
            getConnection();

            String selectTablet="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"HeadPhone");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	HeadPhone headPhone = new HeadPhone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), headPhone);
                headPhone.setId(rs.getString("Id"));

            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }
    public static HashMap<String,Laptop> getLaptops()
    {
        HashMap<String,Laptop> hm=new HashMap<String,Laptop>();
        try
        {
            getConnection();

            String selectTablet="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"Laptop");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	Laptop laptop = new Laptop(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), laptop);
                laptop.setId(rs.getString("Id"));

            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }
    public static HashMap<String,Phone> getPhones()
    {
        HashMap<String,Phone> hm=new HashMap<String,Phone>();
        try
        {
            getConnection();

            String selectTablet="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"Phone");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	Phone phone = new Phone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), phone);
                phone.setId(rs.getString("Id"));

            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }
    public static HashMap<String,SmartWatch> getSmartWatches()
    {
        HashMap<String,SmartWatch> hm=new HashMap<String,SmartWatch>();
        try
        {
            getConnection();

            String selectTablet="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"SmartWatch");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	SmartWatch smartWatch = new SmartWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), smartWatch);
                smartWatch.setId(rs.getString("Id"));

            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }

    public static HashMap<String,SoundSystem> getSoundSystems()
    {
        HashMap<String,SoundSystem> hm=new HashMap<String,SoundSystem>();
        try
        {
            getConnection();

            String selectTablet="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"SoundSystem");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	SoundSystem soundSystem = new SoundSystem(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), soundSystem);
                soundSystem.setId(rs.getString("Id"));

            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }

    public static HashMap<String,VoiceAssistant> getVoiceAssistants()
    {
        HashMap<String,VoiceAssistant> hm=new HashMap<String,VoiceAssistant>();
        try
        {
            getConnection();

            String selectTablet="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"VoiceAssistant");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	VoiceAssistant voiceAssistant = new VoiceAssistant(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), voiceAssistant);
                voiceAssistant.setId(rs.getString("Id"));

            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }

    public static HashMap<String,WirelessPlan> getWirelessPlans()
    {
        HashMap<String,WirelessPlan> hm=new HashMap<String,WirelessPlan>();
        try
        {
            getConnection();

            String selectTablet="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectTablet);
            pst.setString(1,"WirelessPlan");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {	WirelessPlan wirelessPlan = new WirelessPlan(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"), null,rs.getDouble("productDiscount"));
                hm.put(rs.getString("Id"), wirelessPlan);
                wirelessPlan.setId(rs.getString("Id"));

            }
        }
        catch(Exception e)
        {
        }
        return hm;
    }



    public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,String prod)
    {
        String msg = "Product is added successfully";
        try{

            getConnection();
            String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
                    "VALUES (?,?,?,?,?,?,?,?);";

            String name = producttype;

            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setString(1,name);
            pst.setString(2,productId);
            pst.setString(3,productName);
            pst.setDouble(4,productPrice);
            pst.setString(5,productImage);
            pst.setString(6,productManufacturer);
            if(productCondition.isEmpty()) {
                pst.setString(7, "new");
            }else{
                pst.setString(7,productCondition);
            }
            pst.setDouble(8,productDiscount);

            pst.executeUpdate();
            try{
                if (!prod.isEmpty())
                {
                    String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
                            "VALUES (?,?);";
                    PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
                    pst1.setString(1,prod);
                    pst1.setString(2,productId);
                    pst1.executeUpdate();

                }
            }catch(Exception e)
            {
                msg = "Erro while adding the product";
                e.printStackTrace();

            }



        }
        catch(Exception e)
        {
            msg = "Erro while adding the product";
            e.printStackTrace();

        }
        return msg;
    }

    public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount)
    {
        String msg = "Product is updated successfully";
        try{

            getConnection();
            String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?;" ;



            PreparedStatement pst = conn.prepareStatement(updateProductQurey);

            pst.setString(1,productName);
            pst.setDouble(2,productPrice);
            pst.setString(3,productImage);
            pst.setString(4,productManufacturer);
            if(!productCondition.isEmpty()) {
                pst.setString(5, productCondition);
            }
            else{
                pst.setString(5, "new");
            }
            pst.setDouble(6,productDiscount);
            pst.setString(7,productId);
            pst.executeUpdate();



        }
        catch(Exception e)
        {
            msg = "Product cannot be updated";
            e.printStackTrace();

        }
        return msg;
    }

    public static String deleteproducts(String productId)
    {   String msg = "Product is deleted successfully";
        try
        {

            getConnection();
            String deleteproductsQuery ="Delete from Productdetails where Id=?";
            PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
            pst.setString(1,productId);

            pst.executeUpdate();
        }
        catch(Exception e)
        {
            msg = "Proudct cannot be deleted";
        }
        return msg;
    }



    public static void deleteOrder(int orderId, String orderName) {
        try {

            getConnection();
            String deleteOrderQuery = "Delete from Customerorders where OrderId=? and orderName=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1, orderId);
            pst.setString(2, orderName);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertOrder(int orderId, String userName, String orderName, double orderPrice, String userAddress, String creditCardNo) {
        try {

            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO CustomerOrders(OrderId,userName,orderName,orderPrice,userAddress,creditCardNo,Date ) "
                    + "VALUES (?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1, orderId);
            pst.setString(2, userName);
            pst.setString(3, orderName);
            pst.setDouble(4, orderPrice);
            pst.setString(5, userAddress);
            pst.setString(6, creditCardNo);
            java.util.Date myDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            pst.setDate(7,sqlDate );
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<DailyReport> selectDailyReport(){
        ArrayList<DailyReport> dailyReports = new ArrayList<>();
        try{
            getConnection();
            String selectDailyReports = "SELECT sum(orderPrice) as dailySale, Date FROM CustomerOrders group by Date; ";
            PreparedStatement pst1 = conn.prepareStatement(selectDailyReports);
            ResultSet rs1 = pst1.executeQuery();
            while(rs1.next()){
                DailyReport dailyReport  = new DailyReport(rs1.getDouble("dailySale"),new java.util.Date(rs1.getDate("Date").getTime()));
                dailyReports.add(dailyReport);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dailyReports;
    }


    public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder() {
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        try {
            getConnection();
            String selectOrderQuery = "select * from Customerorders";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList<OrderPayment> orderList = new ArrayList<OrderPayment>();
            while (rs.next()) {
                if (!orderPayments.containsKey(rs.getInt("OrderId"))) {
                    ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));
                System.out.println("data is" + rs.getInt("OrderId") + orderPayments.get(rs.getInt("OrderId")));

                OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("userName"), rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getString("userAddress"), rs.getString("creditCardNo"));
                listOrderPayment.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderPayments;
    }

    public static ArrayList<SaleReport> selectSalesReport(){
        ArrayList<SaleReport> salesReport = new ArrayList<>();
        try{
            getConnection();
            String selectSalesReport = "select orderName, count(orderName) as count from Customerorders group by orderName order by orderName desc ";
            PreparedStatement pst1 = conn.prepareStatement(selectSalesReport);
            ResultSet rs1 = pst1.executeQuery();
            String selectSalesReportPart = "select distinct orderName, orderPrice from Customerorders order by orderName desc ";
            PreparedStatement pst2 = conn.prepareStatement(selectSalesReportPart);
            ResultSet rs2 = pst2.executeQuery();
            while(rs1.next()&&rs2.next()){
                SaleReport saleReport = new SaleReport(rs1.getString("orderName"),rs2.getDouble("orderPrice"),rs1.getInt("count"));
                salesReport.add(saleReport);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return salesReport;
    }

    public static void insertUser(String username, String password, String repassword, String usertype) {
        try {

            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
                    + "VALUES (?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, repassword);
            pst.setString(4, usertype);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, User> selectUser() {
        HashMap<String, User> hm = new HashMap<String, User>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "select * from Registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
                hm.put(rs.getString("username"), user);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public static HashMap<String,Product> getData()
    {
        HashMap<String,Product> hm=new HashMap<String,Product>();
        try
        {
            getConnection();
            Statement stmt=conn.createStatement();
            String selectCustomerQuery="select * from  productdetails";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while(rs.next())
            {	 Product p = new Product(rs.getString("Id"),rs.getString("productImage"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getDouble("ManufacturerRebate"),rs.getString("ProductType"));
                hm.put(rs.getString("Id"), p);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return hm;
    }






}




