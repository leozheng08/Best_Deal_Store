import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

@WebServlet(name = "ProductOperation")
public class ProductOperation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("button");
        Utilities utility = new Utilities(request,out);
        utility.printHtml("header.html");
//        String category = request.getParameter("Category");
//        System.out.println(category);
        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");
        if(action.equals("Addproduct")){
            out.print("<form method='get' action='ProductCrud'>"
                    + "<table id='bestseller'><tr><td>"
                    + "<h3>Product Type</h3></td><td><select name='producttype' class='input'><option value='TV' selected>TV</option><option value='Accessory'>Accessory</option><option value='FitnessWatch'>FitnessWatch</option><option value='HeadPhone'>HeadPhone</option>" +
                    "<option value='Laptop'>Laptop</option><option value='Phone'>Phone</option><option value='SmartWatch'>SmartWatch</option><option value='SoundSystem'>SoundSystem</option><option value='VoiceAssistant'>VoiceAssistant</option><option value='WirelessPlan'>WirelessPlan</option></select>"
                    + "</td></tr><tr><td>"
                    +"<h3>Product</h3></td><td><input type='text' name='product' placeholder='Please mention product if adding accessories' value='' class='input'></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Price</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Manufacturer</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Condition</h3></td><td><input type='text' name='productCondition' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Discount</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productDiscount' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<input type='submit' class='btnbuy' name='button' value='add' style='float: right;'></input>"
                    + "</td></tr>"

                    + "</table>"
                    + "</form>");
        }
        else if (action.equals("Updateproduct")){
            out.print("<form method='get' action='ProductCrud'>"
                    + "<table id='bestseller'><tr><td>"
                    + "<h3>Product Type</h3></td><td><select name='producttype' class='input'><option value='TV' selected>TV</option><option value='Accessory'>Accessory</option><option value='FitnessWatch'>FitnessWatch</option><option value='HeadPhone'>HeadPhone</option>" +
                    "<option value='Laptop'>Laptop</option><option value='Phone'>Phone</option><option value='SmartWatch'>SmartWatch</option><option value='SoundSystem'>SoundSystem</option><option value='VoiceAssistant'>VoiceAssistant</option><option value='WirelessPlan'>WirelessPlan</option></select>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Price</h3></td><td><input type='number' step='any' name='productPrice' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Manufacturer</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Condition</h3></td><td><input type='text' name='productCondition' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<h3>Product Discount</h3></td><td><input type='number' step='any' name='productDiscount' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<input type='submit' class='btnbuy' name='button' value='update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
                    + "</td></tr>"

                    +"</table>"
                    + "</form>");
        }
        else{
            out.print("<form method='get' action='ProductCrud'>"
                    + "<table style='width:100%'><tr><td>"
                    + "<h3>ProductId</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
                    + "</td></tr><tr><td>"
                    + "<input type='submit' class='btnbuy' name='button' value='delete' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
                    + "</td></tr></table>"
                    + "</form>");
        }
//        if(request.getParameter("send")==null){
//
//            out.print("<h3>product operation</h3>");
//            out.print("<form action='ProductOperation' method='get'>");
//            out.print("<p><label for=\"Name\">Name:</label>" +
//                    "<input name=\"Name\" id=\"Name\" value=\"\" type=\"text\" /></p>" +
//                    "<p><label for=\"Price\">Price:</label>" +
//                    "<input name=\"Price\" id=\"Price\" value=\"\" type=\"text\" /></p>" +
//                    "<p><label for=\"Condition\">Condition:</label>" +
//                    "<input name=\"Condition\" id=\"Condition\" value=\"\" type=\"text\" /></p>" +
//                    "<p><label for=\"Discount\">Discount:</label>" +
//                    "<input name=\"Discount\" id=\"Discount\" value=\"\" type=\"text\" /></p>" +
//                    "<p><label for=\"Image\">Image:</label>" +
//                    "<input name=\"Image\" id=\"Image\" value=\"\" type=\"text\" /></p>" +
//                    "<input name='Category'  value='"+category+"' type='hidden'>"+
//                    "<p><label for=\"Retailer\">Retailer:</label>" +
//                    "<input name=\"Retailer\" id=\"Retailer\" value=\"\" type=\"text\" /></p>");
//
//            if (request.getParameter("Category").equalsIgnoreCase("TV")) {
//                out.print("<p><label for=\"Accessories\">Accessories:</label>" +
//                        "<input name=\"Accessories\" id=\"Accessories\" value=\"\" type=\"text\" /></p>");
//            } else {
//            }
//            out.print("<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"ADD\" type=\"submit\" /></p>" +
//                    "<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"UPDATE\" type=\"submit\" /></p>" +
//                    "<p><input name=\"send\" style=\"margin-left: 150px;\" class=\"formbutton\" value=\"DELETE\" type=\"submit\" /></p><</form>");
//        }
//        else {
//            if(request.getParameter("send").equalsIgnoreCase("ADD")) {
//                String itemName = request.getParameter("Name");
//                Double price = Double.parseDouble(request.getParameter("Price"));
//                String condition = request.getParameter("Condition");
//                Double discount = Double.parseDouble(request.getParameter("Discount"));
//                String image = request.getParameter("Image");
//                String retailer = request.getParameter("Retailer");
//                String accessories = request.getParameter("Accessories");
//                String TOMCAT_HOME = System.getProperty("catalina.home");
//                System.out.println(TOMCAT_HOME + "\\webapps\\BestDealStore\\ProductCatalog.xml");
//                File file = new File(TOMCAT_HOME + "\\webapps\\BestDealStore\\ADDProductCatalog.xml");
//
//                if (category.equalsIgnoreCase("TV")) {
//                    TV tv = new TV(itemName, price, image, retailer, condition, discount);
//                    tv.setId(itemName);
//                    HashMap<String, String> tvToAccessories = new HashMap<>();
//                    String[] accessoriesArray = accessories.split(";");
//                    for (String ele : accessoriesArray) {
//                        tvToAccessories.put(itemName, ele);
//                    }
//                    tv.setAccessories(tvToAccessories);
//                    SaxParseStore.tvs.put(tv.getName(),tv);
//
//                    jaxbTVToXML(tv, file);
//                }
//                else if (category.equalsIgnoreCase("SoundSystem")) {
//                    SoundSystem soundSystem = new SoundSystem(itemName, price, image, retailer, condition, discount);
//                    soundSystem.setId(itemName);
//                    SaxParseStore.soundSystems.put(soundSystem.getName(),soundSystem);
////                SaxParseStore.soundSystems.put(soundSystem.getId(),soundSystem);
//                    jaxbSoundSystemToXML(soundSystem, file);
//                }
//                else if (category.equalsIgnoreCase("Phone")) {
//                    Phone phone = new Phone(itemName, price, image, retailer, condition, discount);
//                    phone.setId(itemName);
//                    SaxParseStore.phones.put(phone.getName(),phone);
//                    jaxbPhoneToXML(phone, file);
//                }
//                else if (category.equalsIgnoreCase("Laptop")) {
//                    Laptop laptop = new Laptop(itemName, price, image, retailer, condition, discount);
//                    laptop.setId(itemName);
//                    SaxParseStore.laptops.put(laptop.getName(),laptop);
////                SaxParseStore.laptops.put(laptop.getId(),laptop);
//                    jaxbLaptopToXML(laptop, file);
//                }
//                else if (category.equalsIgnoreCase("VoiceAssistant")) {
//                    VoiceAssistant voiceAssistant = new VoiceAssistant(itemName, price, image, retailer, condition, discount);
//                    voiceAssistant.setId(itemName);
//                    SaxParseStore.voiceAssistants.put(voiceAssistant.getName(),voiceAssistant);
////                SaxParseStore.voiceAssistants.put(voiceAssistant.getId(),voiceAssistant);
//                    jaxVoiceAssistantToXML(voiceAssistant, file);
//                }
//                else if (category.equalsIgnoreCase("FitnessWatch")) {
//                    FitnessWatch fitnessWatch = new FitnessWatch(itemName, price, image, retailer, condition, discount);
//                    fitnessWatch.setId(itemName);
//                    SaxParseStore.fitnessWatches.put(fitnessWatch.getName(),fitnessWatch);
////                SaxParseStore.fitnessWatches.put(fitnessWatch.getId(),fitnessWatch);
//                    jaxFitnessWatchToXML(fitnessWatch, file);
//                }
//                else if (category.equalsIgnoreCase("SmartWatch")) {
//                    SmartWatch smartWatch = new SmartWatch(itemName, price, image, retailer, condition, discount);
//                    smartWatch.setId(itemName);
//                    SaxParseStore.smartWatches.put(smartWatch.getName(),smartWatch);
////                SaxParseStore.smartWatches.put(smartWatch.getId(),smartWatch);
//                    jaxSmartWatchToXML(smartWatch, file);
//                }
//                else if (category.equalsIgnoreCase("WirelessPlan")) {
//                    WirelessPlan wirelessPlan = new WirelessPlan(itemName, price, image, retailer, condition, discount);
//                    wirelessPlan.setId(itemName);
//                    SaxParseStore.wirelessPlans.put(wirelessPlan.getName(),wirelessPlan);
////                SaxParseStore.wirelessPlans.put(wirelessPlan.getId(),wirelessPlan);
//                    jaxWirelessPlanToXML(wirelessPlan, file);
//                }
//                else if (category.equalsIgnoreCase("Accessory")) {
//                    Accessory accessory = new Accessory(itemName, price, image, retailer, condition, discount);
//                    accessory.setId(itemName);
//                    SaxParseStore.accessories.put(accessory.getId(), accessory);
//
//                }
//
//                out.print("<h3>ADD successfully</h3>");
//            }
//        }
        out.print("        \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");

    }

//    private void jaxObjectToXML(File file) {
//        for(Map.Entry<String,TV> ele :SaxParseStore.tvs.entrySet()){
//            jaxbTVToXML(ele.getValue(),file);
//        }
//        for(Map.Entry<String,SoundSystem> ele :SaxParseStore.soundSystems.entrySet()){
//            jaxbSoundSystemToXML(ele.getValue(),file);
//        }
//        for(Map.Entry<String,Laptop> ele :SaxParseStore.laptops.entrySet()){
//            jaxbLaptopToXML(ele.getValue(),file);
//        }
//        for(Map.Entry<String,Phone> ele :SaxParseStore.phones.entrySet()){
//            jaxbPhoneToXML(ele.getValue(),file);
//        }
//        for(Map.Entry<String,FitnessWatch> ele :SaxParseStore.fitnessWatches.entrySet()){
//            jaxFitnessWatchToXML(ele.getValue(),file);
//        }
//        for(Map.Entry<String,SmartWatch> ele :SaxParseStore.smartWatches.entrySet()){
//            jaxSmartWatchToXML(ele.getValue(),file);
//        }
//        for(Map.Entry<String,VoiceAssistant> ele :SaxParseStore.voiceAssistants.entrySet()){
//            jaxVoiceAssistantToXML(ele.getValue(),file);
//        }
//        for(Map.Entry<String,WirelessPlan> ele :SaxParseStore.wirelessPlans.entrySet()){
//            jaxWirelessPlanToXML(ele.getValue(),file);
//        }
//        for(Map.Entry<String,Accessory> ele :SaxParseStore.accessories.entrySet()){
//            jaxAccessoryToXML(ele.getValue(),file);
//        }


//    }


    private void jaxAccessoryToXML(Accessory accessory, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Accessory.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("", "Accessory");
            JAXBElement<Accessory> root = new JAXBElement<Accessory>(qName, Accessory.class, accessory);
            jaxbMarshaller.marshal(root, file);

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    private void jaxWirelessPlanToXML(WirelessPlan wirelessPlan, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WirelessPlan.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("", "WirelessPlan");
            JAXBElement<WirelessPlan> root = new JAXBElement<WirelessPlan>(qName, WirelessPlan.class, wirelessPlan);
            jaxbMarshaller.marshal(root, file);

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

    }

    private void jaxSmartWatchToXML(SmartWatch smartWatch, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SmartWatch.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("", "SmartWatch");
            JAXBElement<SmartWatch> root = new JAXBElement<SmartWatch>(qName, SmartWatch.class, smartWatch);
            jaxbMarshaller.marshal(root, file);

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    private void jaxFitnessWatchToXML(FitnessWatch fitnessWatch, File file){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FitnessWatch.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("", "FitnessWatch");
            JAXBElement<FitnessWatch> root = new JAXBElement<FitnessWatch>(qName, FitnessWatch.class, fitnessWatch);
            jaxbMarshaller.marshal(root,file);

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    private void jaxbTVToXML(TV tv, File file)  {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TV.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("", "TV");
            JAXBElement<TV> root = new JAXBElement<TV>(qName, TV.class, tv);
            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(root, file);
            String xmlContent = sw.toString();
            System.out.println( xmlContent );
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    private void jaxVoiceAssistantToXML(VoiceAssistant voiceAssistant, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(VoiceAssistant.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("", "VoiceAssistant");
            JAXBElement<VoiceAssistant> root = new JAXBElement<VoiceAssistant>(qName, VoiceAssistant.class, voiceAssistant);
            jaxbMarshaller.marshal(root, file);

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    private void jaxbLaptopToXML(Laptop laptop, File file)  {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Laptop.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("", "Laptop");
            JAXBElement<Laptop> root = new JAXBElement<Laptop>(qName, Laptop.class, laptop);
            jaxbMarshaller.marshal(root, file);

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    private void jaxbPhoneToXML(Phone phone, File file)  {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Phone.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("", "Phone");
            JAXBElement<Phone> root = new JAXBElement<Phone>(qName, Phone.class, phone);
            jaxbMarshaller.marshal(root, file);

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

    private void jaxbSoundSystemToXML(SoundSystem soundSystem, File file)  {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SoundSystem.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            QName qName = new QName("", "SoundSystem");
            JAXBElement<SoundSystem> root = new JAXBElement<SoundSystem>(qName, SoundSystem.class, soundSystem);
            jaxbMarshaller.marshal(root, file);

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

}
