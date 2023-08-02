import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class XMLStorage {

    

    public static List <Product> productsList = new ArrayList<>();;


    public static List<Product> getELementAsProduct(File file) throws IOException {
        
        

        // Transform data into String ....

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String stringData = "";
        String line = null;
        do { 
            line = br.readLine();
            stringData += line;
            
        } while (line !=null);


        //PARSE...
        JsonElement document = JsonParser.parseString(stringData);
        JsonArray products = document.getAsJsonArray();

        for (JsonElement productElement : products) {

        JsonObject product = productElement.getAsJsonObject();
        Byte id = product.get("id").getAsByte();
        String name = product.get("name").getAsString();
        JsonObject price = product.get("price").getAsJsonObject();
        int amount = price.get("amount").getAsInt();
        String currency = price.get("currency").getAsString();
        Short quantity  = product.get("available").getAsShort();

        productsList.add(new Product(id, name, new Money(amount, currency), quantity));
        
        
        }
        return productsList;


    }


    public static void copyFile(String sourcePath, String destinationPath) throws IOException {

   
    }

   


    public static void createOrderXmlFile(Order order) {
   
    
    }




    public static void updateProductsXmlFile(Stock stock)  {
   }
}




    

    

    

