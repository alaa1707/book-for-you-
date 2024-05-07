package com.example.bookStore.aspect;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetConnection {
    public static boolean isInternetAvailable() {
        try {
            // Try to connect to a well-known internet address
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(500); // Set a timeout to avoid waiting indefinitely
            urlConnection.connect();
            return urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            // An exception occurred, so the internet is not available
            return false;
        }
    }

}
