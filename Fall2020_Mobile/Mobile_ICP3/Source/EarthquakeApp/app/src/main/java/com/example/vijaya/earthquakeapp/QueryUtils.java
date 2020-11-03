package com.example.vijaya.earthquakeapp;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class QueryUtils {
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL url = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        // An int to store the response code from the http request
        int respCode;

        try {
            //TODO: 1. Create a URL from the requestUrl string and make a GET request to it

            // Create a URL from the requestUrl string
            url = new URL(requestUrl);

            // make a GET request to it
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* 10 secs */);
            urlConnection.setConnectTimeout(15000 /* 15 secs */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // if response to http request is not 200, see Logcat info
            respCode = urlConnection.getResponseCode();
            if(respCode != HttpURLConnection.HTTP_OK) {
                String respMsg = "HTTP response code: " + String.valueOf(respCode);
                Log.i("QueryUtils", respMsg);
            }

            //TODO: 2. Read from the Url Connection and store it as a string(jsonResponse)

            // Read from the Url Connection
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder readInput = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                readInput.append(inputLine);
            }
            in.close();

            // store it as a string(jsonResponse)
            jsonResponse = readInput.toString();

                /*TODO: 3. Parse the jsonResponse string obtained in step 2 above into JSONObject to extract the values of
                        "mag","place","time","url"for every earth quake and create corresponding Earthquake objects with them
                        Add each earthquake object to the list(earthquakes) and return it.
                */

            // Parse the jsonResponse string obtained in step 2 above into JSONObject
            JSONObject jsonInput = new JSONObject(jsonResponse);

            // extract the values of "mag","place","time","url"for every earth quake
                // https://www.tutorialspoint.com/android/android_json_parser.htm
                // https://earthquake.usgs.gov/earthquakes/feed/v1.0/geojson.php
            JSONArray equakesJSONArray = jsonInput.getJSONArray("features");
            for (int i=0; i<equakesJSONArray.length(); i++){    // loop thru all properties (for ea equake) in features
                JSONObject equake = (equakesJSONArray.getJSONObject(i)).getJSONObject("properties");
                Double equake_mag = equake.getDouble("mag");        // mag-Decimal
                String equake_place = equake.getString("place");    // place-String
                Long equake_time = equake.getLong("time");          // time-Long Integer
                String equake_url = equake.getString("url");        // url-String

            // create corresponding Earthquake objects with them
            // Add each earthquake object to the list(earthquakes)
                earthquakes.add(new Earthquake(equake_mag,equake_place,equake_time,equake_url));
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        }
        // Return the list of earthquakes
        return earthquakes;
    }
}
