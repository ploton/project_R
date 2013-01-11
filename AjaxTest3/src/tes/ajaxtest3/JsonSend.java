package tes.ajaxtest3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JsonSend {
	public void postJson(String str, String str2)
	{
		String urlString = str; //入力するURL
		try {
			URL url = new URL(urlString);
			URLConnection uc = url.openConnection();
			uc.setDoOutput(true);//POST可能にする

			uc.setRequestProperty("User-Agent", "@IT java-tips URLConnection");// ヘッダを設定
			uc.setRequestProperty("Accept-Language", "ja");// ヘッダを設定
			OutputStream os = uc.getOutputStream();//POST用のOutputStreamを取得

			String postStr = str2; //POSTするデータ
			PrintStream ps = new PrintStream(os);
			ps.print(postStr);//データをPOSTする
			ps.close();

			InputStream is = uc.getInputStream();//POSTした結果を取得
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String s;
			while ((s = reader.readLine()) != null) {
				System.out.println(s);
			}
			reader.close();
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL format: " + urlString);
		} catch (IOException e) {
			System.err.println("Can't connect to " + urlString);
		}
	}
}