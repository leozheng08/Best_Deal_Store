import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "ProductCrud")
public class ProductCrud extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Utilities utility = new Utilities(request, out);
        String action = request.getParameter("button");
        String msg = "good";
        String producttype = "", productId = "", productName = "", productImage = "", productManufacturer = "", productCondition = "", prod = "";
        double productPrice = 0.0, productDiscount = 0.0;
        HashMap<String, TV> tvs = new HashMap<String, TV>();
        HashMap<String, Accessory> accessories = new HashMap<String, Accessory>();
        HashMap<String, FitnessWatch> fitnessWatches = new HashMap<String, FitnessWatch>();
        HashMap<String, HeadPhone> headPhones = new HashMap<String, HeadPhone>();
        HashMap<String, Laptop> laptops = new HashMap<String, Laptop>();
        HashMap<String, Phone> phones = new HashMap<String, Phone>();
        HashMap<String, SmartWatch> smartWatches = new HashMap<String, SmartWatch>();
        HashMap<String, SoundSystem> soundSystems = new HashMap<String, SoundSystem>();
        HashMap<String, VoiceAssistant> voiceAssistants = new HashMap<String, VoiceAssistant>();
        HashMap<String, WirelessPlan> wirelessPlans = new HashMap<String, WirelessPlan>();

        if (action.equals("add") || action.equals("update")) {
            producttype = request.getParameter("producttype");
            productId = request.getParameter("productId");
            productName = request.getParameter("productName");
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
            productImage = request.getParameter("productImage");
            productManufacturer = request.getParameter("productManufacturer");
            productCondition = request.getParameter("productCondition");
            productDiscount = Double.parseDouble(request.getParameter("productDiscount"));

        } else {
            productId = request.getParameter("productId");
        }
        utility.printHtml("header.html");
        out.print("<img class=\"header-image\" src=\"images/image.jpg\" alt=\"Buildings\" />\n" +
                "<div id=\"body\">\n" +
                "    <section id=\"content\">\n" +
                "\n" +
                "        \t    <article>");
        if (action.equals("add")) {
            if (producttype.equalsIgnoreCase("TV")) {
                tvs = MySqlDataStoreUtilities.getTVs();
                if (tvs.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equalsIgnoreCase("Accessory")) {
                if(!request.getParameter("product").isEmpty()){
                    prod = request.getParameter("product");
                    tvs = MySqlDataStoreUtilities.getTVs();
                    if(tvs.containsKey(prod)){
                        accessories = MySqlDataStoreUtilities.getAccessories();
                        if (accessories.containsKey(productId)) {
                            msg = "Product already available";
                        }
                    }
                    else{
                        msg = "The product related to accessories is not available";
                    }
                }else{
                    msg = "Please add the prodcut name";
                }
            } else if (producttype.equalsIgnoreCase("FitnessWatch")) {
                fitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
                if (fitnessWatches.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equalsIgnoreCase("HeadPhone")) {
                headPhones = MySqlDataStoreUtilities.getHeadPhones();
                if (headPhones.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equalsIgnoreCase("Laptop")) {
                laptops = MySqlDataStoreUtilities.getLaptops();
                if (laptops.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equalsIgnoreCase("Phone")) {
                phones = MySqlDataStoreUtilities.getPhones();
                if (phones.containsKey(productId)) {
                    msg = "Product already available";
                }
            }else if(producttype.equalsIgnoreCase("SmartWatch"))
            {
                smartWatches = MySqlDataStoreUtilities.getSmartWatches();
                if(smartWatches.containsKey(productId)){
                    msg = "Product already available";
                }
            }
            else if(producttype.equalsIgnoreCase("SoundSystem"))
            {
                soundSystems = MySqlDataStoreUtilities.getSoundSystems();
                if(soundSystems.containsKey(productId)){
                    msg = "Product already available";
                }
            }else if(producttype.equalsIgnoreCase("VoiceAssistant"))
            {
                voiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if(voiceAssistants.containsKey(productId)){
                    msg = "Product already available";
                }
            }else if(producttype.equalsIgnoreCase("WirelessPlan"))
            {
                wirelessPlans = MySqlDataStoreUtilities.getWirelessPlans();
                if(wirelessPlans.containsKey(productId)){
                    msg = "Product already available";
                }
            }
            if (msg.equals("good"))
            {
                try
                {
                    msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,prod);
                }
                catch(Exception e)
                {
                    msg = "Product cannot be inserted";
                }
                msg = "Product has been successfully added";
            }
        }
        else if(action.equals("update")){
            if(producttype.equalsIgnoreCase("TVs")){
                tvs = MySqlDataStoreUtilities.getTVs();
                if(!tvs.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            else if(producttype.equalsIgnoreCase("FitnessWatch"))
            {
                fitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
                if(!fitnessWatches.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            else if(producttype.equalsIgnoreCase("HeadPhone"))
            {
                headPhones = MySqlDataStoreUtilities.getHeadPhones();
                if(!headPhones.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            else if(producttype.equalsIgnoreCase("Laptop"))
            {
                laptops = MySqlDataStoreUtilities.getLaptops();
                if(!laptops.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            else if(producttype.equalsIgnoreCase("Phone"))
            {
                phones = MySqlDataStoreUtilities.getPhones();
                if(!phones.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            else if(producttype.equalsIgnoreCase("SmartWatch"))
            {
                smartWatches= MySqlDataStoreUtilities.getSmartWatches();
                if(!smartWatches.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            else if(producttype.equalsIgnoreCase("SoundSystem"))
            {
                soundSystems = MySqlDataStoreUtilities.getSoundSystems();
                if(!soundSystems.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            else if(producttype.equalsIgnoreCase("VoiceAssistant"))
            {
                voiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if(!voiceAssistants.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            else if(producttype.equalsIgnoreCase("WirelessPlan"))
            {
                wirelessPlans = MySqlDataStoreUtilities.getWirelessPlans();
                if(!wirelessPlans.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            else if (producttype.equals("Accessory"))
            {
                accessories = MySqlDataStoreUtilities.getAccessories();
                if(!accessories.containsKey(productId)){
                    msg = "Product not available";
                }
            }
            if (msg.equals("good"))
            {

                try
                {
                    msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount);
                }
                catch(Exception e)
                {
                    msg = "Product cannot be updated";
                }
                msg = "Product has been successfully updated";
            }
        }
        else if(action.equals("delete")){
            msg = "bad";
            tvs = MySqlDataStoreUtilities.getTVs();
            if(tvs.containsKey(productId)){
                msg = "good";

            }
            fitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
            if(fitnessWatches.containsKey(productId)){
                msg = "good";
            }
            headPhones = MySqlDataStoreUtilities.getHeadPhones();
            if(headPhones.containsKey(productId)){
                msg = "good";
            }
            laptops = MySqlDataStoreUtilities.getLaptops();
            if(laptops.containsKey(productId)){
                msg = "good";
            }
            phones = MySqlDataStoreUtilities.getPhones();
            if(phones.containsKey(productId)){
                msg = "good";
            }
            smartWatches = MySqlDataStoreUtilities.getSmartWatches();
            if(smartWatches.containsKey(productId)){
                msg = "good";
            }
            soundSystems = MySqlDataStoreUtilities.getSoundSystems();
            if(soundSystems.containsKey(productId)){
                msg = "good";
            }
            voiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
            if(voiceAssistants.containsKey(productId)){
                msg = "good";
            }
            wirelessPlans = MySqlDataStoreUtilities.getWirelessPlans();
            if(wirelessPlans.containsKey(productId)){
                msg = "good";
            }
            accessories = MySqlDataStoreUtilities.getAccessories();
            if(accessories.containsKey(productId)){
                msg = "good";
            }
            if (msg.equals("good"))
            {

                try
                {
                    msg = MySqlDataStoreUtilities.deleteproducts(productId);
                }
                catch(Exception e)
                {
                    msg = "Product cannot be deleted";
                }
                msg = "Product has been successfully deleted";
            }
            else{
                msg = "Product not available";
            }
        }
        out.print("<h3>"+msg+"</h3>");
        out.print("        \t\t</article>\n" +
                "    </section>");
        utility.printHtml("leftNavigateBar.html");
        utility.printHtml("footer.html");
    }
}
