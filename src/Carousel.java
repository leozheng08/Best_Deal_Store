import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Carousel {

    public String carouselFeature(Utilities utility){

        ProductRecommenderUtility prodRecUtility = new ProductRecommenderUtility();
        HashMap<String,String> prodRecmMap = new HashMap<String,String>();
        prodRecmMap = prodRecUtility.readOutputFile();

        HashMap<String, TV> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String myCarousel = null;

        String name = null;
        String CategoryName = null;
        if(CategoryName==null){
            map=MySqlDataStoreUtilities.getTVs();;
            name = "";
        }


        int l =0;
        for(String user: prodRecmMap.keySet()){
            if(user.equals(utility.username())){
                sb.append("<a style='font-size: 24px;'>"+""+" Recommended Products</a>");
                String products = prodRecmMap.get(user);
                products=products.replace("[","");
                products=products.replace("]","");
                products=products.replace("\"", " ");
                ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(products.split(",")));
                int k = 0;
                for(String prod : productsList){
                    prod= prod.replace("'", "");
                    Product prodObj = null;
                    prodObj = ProductRecommenderUtility.getProduct(prod.trim());
                    sb.append("<h3>"+prodObj.getProductName()+"</h3>"+
                            "\n" +
                            "\n" +
                            "            <p><img src='images/"+prodObj.getProductType()+"/"+prodObj.getImage()+"' height='100' width='100'></p>\n" +
                            "            <ul class=\"styledlist\">\n" +
                            "                <li>"+prodObj.getProductPrice()+"</li>\n" +
                            "                <li>"+prodObj.getDiscount()+"</li>\n" +
                            "                <li>"+prodObj.getRetailer()+"</li>\n" +
                            "            </ul>\n" +
                            "\n" +
                            "            <a href='Cart?name="+prod.trim()+"&type="+prodObj.getProductType()+"&maker="+prodObj.getRetailer()+"&access= ' class=\"button\">Buy Now</a>\n" +
                            "            <a href='WriteView?name="+prod.trim()+"type="+prodObj.getProductType()+"&maker="+prodObj.getRetailer()+ "&price=" +prodObj.getProductPrice()+"&access= ' class=\"button\">WriteView</a>\n" +
                            "            <a href='ViewReview?name="+prod.trim()+"type="+prodObj.getProductType()+"&maker="+prodObj.getRetailer()+ "&price=\" +prodObj.getProductPrice()+\"&access= ' class=\"button\">ViewReview</a>");
                }
                sb.append("</article>");
            }
        }
        return sb.toString();
//        int l =0;
//        for (OrderItem oi : utility.getCustomerOrders()){
//            if (map.containsKey(oi.getName())){
//                myCarousel = "myCarousel"+l;
//                sb.append("<article><h3>"+oi.getName()+"Accessories</h3>");
//                TV tv1 = map.get(oi.getName());
//                System.out.print(oi.getName());
//                int k = 0; int size= map.size();
//                for(Map.Entry<String, String> acc:tv1.getAccessories().entrySet()){
//                    Accessory accessory= SaxParseStore.accessories.get(acc.getValue());
//                    sb.append("<h3>"+accessory.getName()+"</h3>"+
//                            "\n" +
//                            "\n" +
//                            "            <p><img src='images/Accessory/"+accessory.getImage()+"' height='100' width='100'></p>\n" +
//                            "            <ul class=\"styledlist\">\n" +
//                            "                <li>"+accessory.getPrice()+"</li>\n" +
//                            "                <li>"+accessory.getDiscount()+"</li>\n" +
//                            "                <li>"+accessory.getRetailer()+"</li>\n" +
//                            "            </ul>\n" +
//                            "\n" +
//                            "            <a href='Cart?name="+acc.getValue()+"&type=Accessory&maker="+CategoryName+"&access="+oi.getName()+"' class=\"button\">Buy Now</a>\n" +
//                            "            <a href=\"#\" class=\"button\">WriteView</a>\n" +
//                            "            <a href=\"#\" class=\"button\">ViewReview</a>");
//                }
//                sb.append("</article>");
//            }
//        }
//        return sb.toString();
    }
}
