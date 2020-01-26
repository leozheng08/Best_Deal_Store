import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashMap;

public class SaxParseStore extends DefaultHandler {
    TV tv;
    SoundSystem soundSystem;
    Phone phone;
    Laptop laptop;
    VoiceAssistant voiceAssistant;
    FitnessWatch fitnessWatch;
    SmartWatch smartWatch;
    HeadPhone headPhone;
    WirelessPlan wirelessPlan;
    Accessory accessory;

    static HashMap<String, TV> tvs;
    static HashMap<String,SoundSystem> soundSystems;
    static HashMap<String, Phone> phones;
    static HashMap<String, Laptop> laptops;
    static HashMap<String, VoiceAssistant> voiceAssistants;
    static HashMap<String, FitnessWatch> fitnessWatches;
    static HashMap<String, SmartWatch> smartWatches;
    static HashMap<String, HeadPhone> headPhones;
    static HashMap<String, WirelessPlan> wirelessPlans;
    static HashMap<String, Accessory> accessories;

    String consoleXmlFileName;
    static HashMap<String, String> accessoryHashMap;
    String elementValueRead;
    String currentElement="";

    public SaxParseStore()
    {
    }

    public SaxParseStore(String consoleXmlFileName)
    {
        this.consoleXmlFileName = consoleXmlFileName;
        tvs = new HashMap<String, TV>();
        soundSystems=new HashMap<String, SoundSystem>();
        phones=new HashMap<String, Phone>();
        laptops=new HashMap<String, Laptop>();
        voiceAssistants=new HashMap<String, VoiceAssistant>();
        fitnessWatches=new HashMap<String, FitnessWatch>();
        smartWatches=new HashMap<String, SmartWatch>();
        headPhones=new HashMap<String, HeadPhone>();
        wirelessPlans=new HashMap<String, WirelessPlan>();
        accessories = new HashMap<String, Accessory>();
        accessoryHashMap=new HashMap<String, String>();
        parseDocument();
    }

    private void parseDocument() {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try
        {
            SAXParser parser = factory.newSAXParser();
            parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }



    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("TV"))
        {
            currentElement="TV";
            tv = new TV();
            tv.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("SoundSystem"))
        {
            currentElement="SoundSystem";
            soundSystem = new SoundSystem();
            soundSystem.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("Laptop"))
        {
            currentElement="Laptop";
            laptop= new Laptop();
            laptop.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("Phone"))
        {
            currentElement="Phone";
            phone= new Phone();
            phone.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("VoiceAssistant"))
        {
            currentElement="VoiceAssistant";
            voiceAssistant= new VoiceAssistant();
            voiceAssistant.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("FitnessWatch"))
        {
            currentElement="FitnessWatch";
            fitnessWatch= new FitnessWatch();
            fitnessWatch.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("SmartWatch"))
        {
            currentElement="SmartWatch";
            smartWatch= new SmartWatch();
            smartWatch.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("HeadPhone"))
        {
            currentElement="HeadPhone";
            headPhone= new HeadPhone();
            headPhone.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("WirelessPlan"))
        {
            currentElement="WirelessPlan";
            wirelessPlan= new WirelessPlan();
            wirelessPlan.setId(attributes.getValue("id"));
        }

        if (elementName.equals("accessory")&&!currentElement.equals("TV"))
        {
            currentElement="accessory";
            accessory=new Accessory();
            accessory.setId(attributes.getValue("id"));
        }


    }
    // when xml end element is parsed store the data into respective hashmap for console,games etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {

        if (element.equals("TV")) {
            tvs.put(tv.getId(),tv);
            return;
        }

        if (element.equals("SoundSystem")) {
            soundSystems.put(soundSystem.getId(),soundSystem);
            return;
        }
        if (element.equals("Phone")) {
            phones.put(phone.getId(),phone);
            return;
        }
        if (element.equals("Laptop")) {
            laptops.put(laptop.getId(),laptop);
            return;
        }
        if (element.equals("VoiceAssistant")) {
            voiceAssistants.put(voiceAssistant.getId(),voiceAssistant);
            return;
        }
        if (element.equals("FitnessWatch")) {
            fitnessWatches.put(fitnessWatch.getId(),fitnessWatch);
            return;
        }
        if (element.equals("SmartWatch")) {
            smartWatches.put(smartWatch.getId(),smartWatch);
            return;
        }
        if (element.equals("HeadPhone")) {
            headPhones.put(headPhone.getId(),headPhone);
            return;
        }
        if (element.equals("WirelessPlan")) {
            wirelessPlans.put(wirelessPlan.getId(),wirelessPlan);
            return;
        }

        if (element.equals("accessory")&&currentElement.equals("accessory")) {
            accessories.put(accessory.getId(),accessory);
            return;
        }
        if (element.equals("accessory") && currentElement.equals("TV"))
        {
            accessoryHashMap.put(elementValueRead,elementValueRead);
        }
        if (element.equalsIgnoreCase("accessories") && currentElement.equals("TV")) {
            tv.setAccessories(accessoryHashMap);
            accessoryHashMap= new HashMap<String, String>();
            return;
        }

        if (element.equalsIgnoreCase("image")) {
            if(currentElement.equals("TV"))
                tv.setImage(elementValueRead);
            if(currentElement.equals("SoundSystem"))
                soundSystem.setImage(elementValueRead);
            if(currentElement.equals("Phone"))
                phone.setImage(elementValueRead);
            if(currentElement.equals("Laptop"))
                laptop.setImage(elementValueRead);
            if(currentElement.equals("VoiceAssistant"))
                voiceAssistant.setImage(elementValueRead);
            if(currentElement.equals("FitnessWatch"))
                fitnessWatch.setImage(elementValueRead);
            if(currentElement.equals("SmartWatch"))
                smartWatch.setImage(elementValueRead);
            if(currentElement.equals("HeadPhone"))
                headPhone.setImage(elementValueRead);
            if(currentElement.equals("WirelessPlan"))
                wirelessPlan.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
                accessory.setImage(elementValueRead);
            return;
        }


        if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("TV"))
                tv.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("SoundSystem"))
                soundSystem.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("Phone"))
                phone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("Laptop"))
                laptop.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("VoiceAssistant"))
                voiceAssistant.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("FitnessWatch"))
                fitnessWatch.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("SmartWatch"))
                smartWatch.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("HeadPhone"))
                headPhone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("WirelessPlan"))
                wirelessPlan.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
                accessory.setDiscount(Double.parseDouble(elementValueRead));
            return;
        }

        if (element.equalsIgnoreCase("rebate")) {
            if(currentElement.equals("TV"))
                tv.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("SoundSystem"))
                soundSystem.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("Phone"))
                phone.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("Laptop"))
                laptop.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("VoiceAssistant"))
                voiceAssistant.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("FitnessWatch"))
                fitnessWatch.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("SmartWatch"))
                smartWatch.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("HeadPhone"))
                headPhone.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("WirelessPlan"))
                wirelessPlan.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
                accessory.setRebate(Double.parseDouble(elementValueRead));
            return;
        }


        if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("TV"))
                tv.setCondition(elementValueRead);
            if(currentElement.equals("SoundSystem"))
                soundSystem.setCondition(elementValueRead);
//            if(currentElement.equals("SoundSystem"))
//                SoundSystem.setCondition(elementValueRead);

            if(currentElement.equals("Phone"))
                phone.setCondition(elementValueRead);
            if(currentElement.equals("Laptop"))
                laptop.setCondition(elementValueRead);
            if(currentElement.equals("VoiceAssistant"))
                voiceAssistant.setCondition(elementValueRead);
            if(currentElement.equals("FitnessWatch"))
                fitnessWatch.setCondition(elementValueRead);
            if(currentElement.equals("SmartWatch"))
                smartWatch.setCondition(elementValueRead);
            if(currentElement.equals("HeadPhone"))
                headPhone.setCondition(elementValueRead);
//            if(currentElement.equals("WirelessPlan"))
//                wirelessPlan.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
                accessory.setCondition(elementValueRead);
            return;
        }

        if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("TV"))
                tv.setRetailer(elementValueRead);
            if(currentElement.equals("SoundSystem"))
                soundSystem.setRetailer(elementValueRead);
//            if(currentElement.equals("SoundSystem"))
//                SoundSystem.setRetailer(elementValueRead);
            if(currentElement.equals("Phone"))
                phone.setRetailer(elementValueRead);
            if(currentElement.equals("Laptop"))
                laptop.setRetailer(elementValueRead);
            if(currentElement.equals("VoiceAssistant"))
                voiceAssistant.setRetailer(elementValueRead);
            if(currentElement.equals("FitnessWatch"))
                fitnessWatch.setRetailer(elementValueRead);
            if(currentElement.equals("SmartWatch"))
                smartWatch.setRetailer(elementValueRead);
            if(currentElement.equals("HeadPhone"))
                headPhone.setRetailer(elementValueRead);
            if(currentElement.equals("WirelessPlan"))
                wirelessPlan.setRetailer(elementValueRead);
            if(currentElement.equals("accessory"))
                accessory.setRetailer(elementValueRead);
            return;
        }

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("TV"))
                tv.setName(elementValueRead);
            if(currentElement.equals("SoundSystem"))
            soundSystem.setName(elementValueRead);
            if(currentElement.equals("Phone"))
                phone.setName(elementValueRead);
            if(currentElement.equals("Laptop"))
                laptop.setName(elementValueRead);
            if(currentElement.equals("VoiceAssistant"))
                voiceAssistant.setName(elementValueRead);
            if(currentElement.equals("FitnessWatch"))
                fitnessWatch.setName(elementValueRead);
            if(currentElement.equals("SmartWatch"))
                smartWatch.setName(elementValueRead);
            if(currentElement.equals("HeadPhone"))
                headPhone.setName(elementValueRead);
            if(currentElement.equals("WirelessPlan"))
                wirelessPlan.setName(elementValueRead);
            if(currentElement.equals("accessory"))
                accessory.setName(elementValueRead);
            return;
        }

        if(element.equalsIgnoreCase("price")){
            if(currentElement.equals("TV"))
                tv.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("SoundSystem"))
                soundSystem.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("Phone"))
                phone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("Laptop"))
                laptop.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("VoiceAssistant"))
                voiceAssistant.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("FitnessWatch"))
                fitnessWatch.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("SmartWatch"))
                smartWatch.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("HeadPhone"))
                headPhone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("WirelessPlan"))
                wirelessPlan.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
                accessory.setPrice(Double.parseDouble(elementValueRead));
            return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

    public static void addHashMap() {
        String TOMCAT_HOME = System.getProperty("catalina.home");
        new SaxParseStore("/Users/zhengleo/IdeaProjects/BestDealStore_2/web/ProductCatalog.xml");
    }
}
