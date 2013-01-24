package tes.ajaxtest3;

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


/*
public class JsonCatch {
	HttpURLConnection http; //接続するためのHTTPクラス
    InputStream in; //読み込んだ文字列を入れ込む型
    
    JsonCatch(){
    	http = null;
    	in = null;
    }

    public String takeJson(String u){
    	String src = new String(); //返す文字列
    	try {
    		URL url = new URL(u);
    		http = (HttpURLConnection)url.openConnection();
    		http.setRequestMethod("GET");
    		http.connect();
    		in = http.getInputStream();
    		
    		byte[] line = new byte[100]; //読み込む文字数を指定している
    		int size;
 
    		while(true) {
    			size = in.read(line);
    			if(size <= 0)
    				break;
    			src += new String(line);
    		}
    	} catch(Exception e) {
    		src = "error";
    	} finally {
    		try {
    			if(http != null)
    				http.disconnect();
    			if(in != null) 
    				in.close();
    		} catch (Exception e) {}
    	}
		return src;
    }
}
*/
