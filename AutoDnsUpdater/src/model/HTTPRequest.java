package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HTTPRequest {
	
	public static String url(String address) throws IOException {
		URL url = new URL(address);
		URLConnection urlConnection = url.openConnection();
		BufferedReader line = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		StringBuilder response = new StringBuilder();
		String inputLine;
		while ((inputLine = line.readLine()) != null) {
			if (!inputLine.isEmpty()) {
				response.append(inputLine + "\n");
			}
		}
		return response.toString();
	}

}
