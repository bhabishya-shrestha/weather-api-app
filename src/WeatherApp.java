// get weather data from API

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherApp {

    // removes type safety warning for putting different types of weather info
    // objects into JSONObject weatherdata
    @SuppressWarnings("unchecked")
    // fetches weather data given location
    public static JSONObject getWeatherData(String locationName) {

        // get geo location via geolocation API
        JSONArray locationData = getLocationData(locationName);

        // get latitude and longitude
        JSONObject location = (JSONObject) locationData.get(0);

        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        // build API request URL using the geo location
        String urlString = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=" + latitude + "&longitude=" + longitude +
                "&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch";

        try {

            // get api response
            HttpURLConnection connection = fetchApiResponse(urlString);

            // check response status
            // 200: successful connection
            if (connection.getResponseCode() != 200) {
                System.out.println("Error: Couldn't connect to API here");
                return null;
            }

            // store json data if connection is successful
            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());

            while (scanner.hasNext()) {

                // read and store resulting data into string builder
                resultJson.append(scanner.nextLine());
            }

            // close scanner
            scanner.close();

            // close url connection
            connection.disconnect();

            // parse through data
            JSONParser parser = new JSONParser();
            JSONObject resultJsonObject = (JSONObject) parser.parse(String.valueOf(resultJson));

            // retrieve hourly weather data
            JSONObject hourly = (JSONObject) resultJsonObject.get("hourly");

            // current hour's data means retrieving index of current hour
            JSONArray time = (JSONArray) hourly.get("time");
            int index = findIndexOfCurrentTime(time);

            JSONArray temperatureData = (JSONArray) hourly.get("temperature_2m");
            double temperature = (double) temperatureData.get(index);

            // extract weather code
            JSONArray weathercode = (JSONArray) hourly.get("weather_code");
            String weatherCondition = convertWeatherCode((long) weathercode.get(index));

            // extract humidity
            JSONArray relativeHumidity = (JSONArray) hourly.get("relative_humidity_2m");
            long humidity = (long) relativeHumidity.get(index);

            // extract windspeed
            JSONArray windspeedData = (JSONArray) hourly.get("wind_speed_10m");
            double windspeed = (double) windspeedData.get(index);

            // build the weather json data object needed for front-end
            JSONObject weatherData = new JSONObject();
            weatherData.put("temperature", temperature);
            weatherData.put("weather_condition", weatherCondition);
            weatherData.put("humidity", humidity);
            weatherData.put("windspeed", windspeed);

            return weatherData;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // retrieves geo location given location name
    public static JSONArray getLocationData(String locationName) {

        // replaces whitespace in location name variable to + which works with API's
        // request format
        locationName = locationName.replaceAll(" ", "+");

        // builds API url with location as the parameter
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" + locationName
                + "&count=10&language=en&format=json";

        try {
            // calls api to get a response
            HttpURLConnection connection = fetchApiResponse(urlString);

            // checks response status
            if (connection.getResponseCode() != 200) {
                System.out.println("Error: Couldn't connect to API");
                return null;
            } else {
                // 200: success response status
                StringBuilder resultJson = new StringBuilder();

                // stores the API results
                Scanner scanner = new Scanner(connection.getInputStream());

                // reads and stores the resulting json data into string builder
                while (scanner.hasNext()) {
                    resultJson.append(scanner.nextLine());
                }

                // closes scanner
                scanner.close();

                // closes url connection
                connection.disconnect();

                // parses JSON string into a JSON object
                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObject = (JSONObject) parser.parse(String.valueOf(resultJson));

                // get list of location data generated by the API using the location parameter
                JSONArray locationData = (JSONArray) resultsJsonObject.get("results");

                // in javascript an array is denoted by [], an object is denoted by {}, and a
                // JSON is denoted by "{}"
                return locationData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // location couldn't be found
        return null;
    }

    // makes the actual api call
    private static HttpURLConnection fetchApiResponse(String urlString) {
        HttpURLConnection connection = null;
        try {

            // attempt creating connection
            URL url = new URI(urlString).toURL();
            connection = (HttpURLConnection) url.openConnection();

            // sets request method of get
            connection.setRequestMethod("GET");

            // establish connection with api
            connection.connect();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

        // failed attempt at creating connection
        return connection;
    }

    private static int findIndexOfCurrentTime(JSONArray timeList) {
        String currentTime = getCurrentTime();

        // iterate through time list to see one that matches local current time
        for (int i = 0; i < timeList.size(); i++) {
            String time = (String) timeList.get(i);
            if (time.equalsIgnoreCase(currentTime)) {
                // return index
                return i;
            }
        }
        return 0;
    }

    public static String getCurrentTime() {
        // get the current time and date
        LocalDateTime currentDateTime = LocalDateTime.now();

        // format date to be 2024-01-02T00:00 (how the api reads time)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");

        // format and print current time and date
        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;
    }

    // converts weathercode into readable data
    private static String convertWeatherCode(long weathercode) {
        String weatherCondition = "";
        if (weathercode == 0L) {

            // clear weather
            weatherCondition = "Clear";

        } else if (weathercode <= 3L && weathercode > 0L) {

            // cloudy weather
            weatherCondition = "Cloudy";

        } else if ((weathercode >= 51L && weathercode <= 67L)
                || (weathercode >= 80L && weathercode <= 99L)) {

            // rainy weather
            weatherCondition = "Rain";

        } else if (weathercode >= 71L && weathercode <= 77L) {

            // snowy weather
            weatherCondition = "Snow";
        }

        return weatherCondition;
    }
}
