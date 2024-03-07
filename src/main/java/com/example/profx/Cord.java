package com.example.profx;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class Cord {
    public Cord() {
        try {
            // Define the Overpass query
            String overpassQuery = "[out:json];\n" +
                    "node(around:5000,35.244559,-3.931747)[\"tourism\"=\"hotel\"];\n" +
                    "out;";

            // Set Overpass API URL
            String overpassUrl = "https://overpass-api.de/api/interpreter";

            // Create a URL object
            URL url = new URL(overpassUrl);

            // Open a connection to the Overpass API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            // Encode and send the Overpass query
            String postData = "data=" + URLEncoder.encode(overpassQuery, StandardCharsets.UTF_8);
            try (var os = connection.getOutputStream()) {
                os.write(postData.getBytes(StandardCharsets.UTF_8));
            }

            // Check the HTTP response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and parse the response data
                try (var in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(response.toString());

                    // Get the "elements" array
                    JSONArray elementsArray = jsonResponse.getJSONArray("elements");

                    // Create a JSON array to store the information
                    JSONArray jsonArray = new JSONArray();

                    // Loop through each element in the array
                    for (int i = 0; i < elementsArray.length(); i++) {
                        JSONObject element = elementsArray.getJSONObject(i);

                        // Get the name, latitude, and longitude from the "tags" object
                        String name = element.getJSONObject("tags").optString("name", "");
                        double latitude = element.getDouble("lat");
                        double longitude = element.getDouble("lon");

                        // Create a JSON object for each hotel
                        JSONObject hotelObject = new JSONObject();
                        hotelObject.put("name", name);
                        hotelObject.put("latitude", latitude);
                        hotelObject.put("longitude", longitude);

                        // Add the hotel object to the JSON array
                        jsonArray.put(hotelObject);
                    }

                    // Now, you have the information in a JSON array
                    try (FileWriter file = new FileWriter("output.txt")) {
                        file.write(jsonArray.toString(2));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(jsonArray.toString(2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
