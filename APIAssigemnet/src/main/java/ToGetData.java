import okhttp3.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONObject;
public class ToGetData {


    ////////////////get data by oKHTTP method only get response////////////////////
    public static void getRequestAndGetResponse() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url("https://dummyjson.com/products")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println("response" + response);


    }

    ///////////////use HTTpConnection and strore the read response by  reader and store into treeSetForm///////////////
    public static void getRequestAndStoreResponseInTreeSetAndCheckTimeIsLessThanTwoSec() {
        try {
            // Create a URL object
            URL url = new URL("https://dummyjson.com/products");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            connection.setConnectTimeout(2000); // 2 seconds

            // Get the start time
            long startTime = System.currentTimeMillis();

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Get the end time
            long endTime = System.currentTimeMillis();

            // Calculate the time taken
            long timeTaken = endTime - startTime;

            // If the response code is HTTP_OK (200) and time taken is less than 2 seconds
            if (responseCode == HttpURLConnection.HTTP_OK && timeTaken < 2000) {

                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Process the JSON response and add data to TreeSet
                // Assuming response is in JSON format, you can parse it as needed
                // Here, I'm simply adding the whole response to the TreeSet
                TreeSet<String> responseSet = new TreeSet<>();
                responseSet.add(response.toString());

                // Print the TreeSet
                System.out.println(responseSet);
            } else {
                // If response code is not HTTP_OK
                System.out.println("Error: " + responseCode);
            }

            // Disconnect the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void validateToChechEveryProductRatingGreaterThanEqual(){
        try {
            // Create a URL object
            URL url = new URL("https://dummyjson.com/products");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Set a timeout for the connection
            connection.setConnectTimeout(2000); // 2 seconds

            // Get the start time
            long startTime = System.currentTimeMillis();

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Get the end time
            long endTime = System.currentTimeMillis();

            // Calculate the time taken
            long timeTaken = endTime - startTime;

            // If the response code is HTTP_OK (200) and time taken is less than 2 seconds
            if (responseCode == HttpURLConnection.HTTP_OK && timeTaken < 2000) {
                // Create a BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                // Read the response line by line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response
                JSONObject productsArray = new JSONObject(response.toString());
             JSONArray abc=  productsArray.getJSONArray("products");


                // Check if all products have a rating greater than or equal to 4.0
                boolean allProductsHaveGoodRating = true;
                for (int i = 0; i < abc.length(); i++) {
                    JSONObject product = abc.getJSONObject(i);
                    double rating = product.getDouble("rating");
                    if (rating < 4.0) {
                        allProductsHaveGoodRating = false;
                        break;
                    }
                }

                if (allProductsHaveGoodRating) {
                    System.out.println("All products have a rating greater than or equal to 4.0");
                } else {
                    System.out.println("Not all products have a rating greater than or equal to 4.0");
                }
            } else {
                // If response code is not HTTP_OK or time taken is more than 2 seconds
                System.out.println("Error: Response code " + responseCode + ", Time taken " + timeTaken + " ms");
            }

            // Disconnect the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String getResult[]) throws IOException {
        getRequestAndGetResponse();
       getRequestAndStoreResponseInTreeSetAndCheckTimeIsLessThanTwoSec();
        validateToChechEveryProductRatingGreaterThanEqual();
    }


}