package tsst.ajaxtest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDate {
	public String getData(String sUrl) {  
		/*
	    HttpClient objHttp = new DefaultHttpClient();  
	    HttpParams params = objHttp.getParams();  
	    HttpConnectionParams.setConnectionTimeout(params, 1000); //接続のタイムアウト  
	    HttpConnectionParams.setSoTimeout(params, 1000); //データ取得のタイムアウト  
	    String sReturn = "";  
	    try {
	        HttpGet objGet   = new HttpGet(sUrl);  
	        HttpResponse objResponse = objHttp.execute(objGet);  
	        if (objResponse.getStatusLine().getStatusCode() < 400){  
	            InputStream objStream = objResponse.getEntity().getContent();  
	            InputStreamReader objReader = new InputStreamReader(objStream);  
	            BufferedReader objBuf = new BufferedReader(objReader);  
	            StringBuilder objJson = new StringBuilder();  
	            String sLine;  
	            while((sLine = objBuf.readLine()) != null){  
	                objJson.append(sLine);  
	            }  
	            sReturn = objJson.toString();  
	            objStream.close();  
	        }  
	    } catch (IOException e) {  
	        return null;  
	    }     
	    return sReturn;
		 */

		HttpURLConnection http = null;
		InputStream in = null;
		String src = new String(); //最終的に当てはめる文字列
		src = "null";
		try{
			// URLにHTTP接続
			URL url = new URL("sUrl");
			http = (HttpURLConnection)url.openConnection();
			http.setRequestMethod("GET");
			http.connect();
			// データを取得
			in = http.getInputStream();

			
			byte[] line = new byte[1024];
			int size;
			while(true) {
				size = in.read(line);
				if(size <= 0)
					break;
				src += new String(line);
			}
			System.out.println("トライしたよ！");
		
		} catch(Exception e) {
			System.out.println("例外だよ～ん");
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
