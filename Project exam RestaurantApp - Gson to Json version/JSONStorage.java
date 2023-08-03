import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class JSONStorage {

    

    public static List <Product> productsList = new ArrayList<>();;


    public static List<Product> getELementAsProduct(File file) throws IOException {
        
        

        // Transform data into String ....

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder stringBuilder = new StringBuilder();
    
       
        String line ;
        
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
        }
        String stringData = stringBuilder.toString();
        System.out.println(stringData);
    

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
        Short quantity  = product.get("quantity").getAsShort();

        productsList.add(new Product(id, name, new Money(amount, currency), quantity));
        
        
        }
        return productsList;


    }


     public static void copyFile(String sourcePath, String destinationPath) throws IOException {

        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);
    
        // Check if the source file exists
        if (!sourceFile.exists() || !sourceFile.isFile()) {
            throw new IllegalArgumentException("Source file does not exist or is not a regular file.");
        }
    
        // Create the destination file if it doesn't exist
        if (!destinationFile.exists()) {
            destinationFile.createNewFile();
        }
    
        try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
             FileChannel destinationChannel = new FileOutputStream(destinationFile).getChannel()) {
    
            // Transfer the data from the source file to the destination file
            destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }
    }

    

   


    public static void createOrderJsonFile(JSONOrder order) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(order);
        

        try (FileWriter writer = new FileWriter("order.json")) {
            writer.write(String.valueOf(json));
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    
    }




    public static void updateProductsJsonFile(Stock stock) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(stock);

        try (FileWriter writer = new FileWriter("products.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





    

    

    

