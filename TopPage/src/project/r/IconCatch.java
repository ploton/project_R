package project.r;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//@TargetApi(Build.VERSION_CODES.GINGERBREAD)
//@SuppressLint("NewApi")
public class IconCatch extends Activity {
    
	
	/*
	@SuppressLint("NewApi")
	public Drawable takeIcon(String id)
	{
        //StrictModeを設定 penaltyDeathを取り除く
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        
        //画像のURL
        String urlString = "http://api.twitter.com/1/users/profile_image?screen_name=" + id + "&size=mini";
        
        try {
        	System.out.println("try入りました");
            //URLクラス
            URL url = new URL(urlString);
            //入力ストリームを開く
            InputStream istream = url.openStream();

            //画像をDrawableで取得
            Drawable d = Drawable.createFromStream(istream, "");

            System.out.println("取得しました");
            //Drawable形式で返す
            return d;
            
            //入力ストリームを閉じる
            //istream.close();
            
            
            
        } catch (Exception e) {
            System.out.println("nuu: "+e);
            return null;
        }
    }
    */
    
	

	
	public Bitmap loadImage(String str)
	{
		/*
		Drawable d = null;
		try{
	        URL url = new URL("http://api.twitter.com/1/users/profile_image?screen_name=" + str + "&size=mini");
	        HttpURLConnection http = (HttpURLConnection)url.openConnection();
	        http.setRequestMethod("GET");
	        http.connect();
	        InputStream in = http.getInputStream();
	        d = Drawable.createFromStream(in, "a");
	                in.close();
	   }catch(Exception e){
	   }
	   d.setBounds(20, 20, 143, 59);
	   return d;
	   */
	
	
		
		String ulrStr = "http://api.twitter.com/1/users/profile_image?screen_name=" + str + "&size=mini";
		
		try { 
			System.out.println("try入ったよ");
			URL url = new URL(ulrStr); 
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 

			connection.setDoInput(true); 
			connection.connect(); 

			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input); 
			System.out.println("インプットしたよ");
			return myBitmap;
		} catch (IOException e) {
			//画像が大きすぎたりする場合
			e.printStackTrace(); 
			return null;
		}
		

	}
	
}