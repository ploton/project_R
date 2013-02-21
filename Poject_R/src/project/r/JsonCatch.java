package project.r;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/*
 * Jsonから要素を取ってくるためのクラスです。
 * onCreateの直後に
 * Anime anites = JSON.decode(getj.takeJson(loadURL), Anime.class);
 * とか入れればおｋです。
 */


public class JsonCatch{
	
	public String takeJson(String u){
		HttpClient httpClient = new DefaultHttpClient();

		StringBuilder uri = new StringBuilder(u);
		HttpGet request = new HttpGet(uri.toString());
		HttpResponse httpResponse = null;
		
		// 返すdateの文字列
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
				data = outputStream.toString(); // JSONデータ
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