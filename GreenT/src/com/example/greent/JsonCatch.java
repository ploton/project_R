package com.example.greent;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class JsonCatch{
	
	public String takeJson(String u){
		HttpClient httpClient = new DefaultHttpClient();

		StringBuilder uri = new StringBuilder(u);
		HttpGet request = new HttpGet(uri.toString());
		HttpResponse httpResponse = null;
		
		// ï‘Ç∑dateÇÃï∂éöóÒ
		String data = null;

		try {
			httpResponse = httpClient.execute(request);
		} catch (Exception e) {
			System.out.println("Error Execute");
			data = "error";
		}

		int status = httpResponse.getStatusLine().getStatusCode();

		if (HttpStatus.SC_OK == status) {
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				httpResponse.getEntity().writeTo(outputStream);
				data = outputStream.toString(); // JSONÉfÅ[É^
			} catch (Exception e) {
				System.out.println("Error");
				data = "error";
			}
		} else {
			System.out.println("Status" + status);
			data = "error";
		}
		return data;
	}
}
