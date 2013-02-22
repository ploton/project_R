package tes.ajaxtest3;

import net.arnx.jsonic.JSON;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.webkit.WebView;

@TargetApi(9)
@SuppressLint({ "NewApi", "NewApi" })
public class MainActivity extends Activity {
    private final static String loadURL = "http://nameless-taiga-3201.herokuapp.com/getAnimesJson";
	JsonCatch getj = new JsonCatch();
	JsonSend sendj = new JsonSend();
	String postStr = "{score:5}"; //転送させるデータ
	//private final static String postURL = "http://nameless-taiga-3201.herokuapp.com/testPost";
	//Anime btooom = new Anime();
	//JSONObject btooom = new JSONObject();
    
    /** Called when the activity is first created. */
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        
        
        //読み込ませる
        //this.readJson(loadURL);
        String jsonStr = getj.takeJson(loadURL); //jsonStrに文字列を入れる
        String jsonStr2 = "{\"anime\":[{\"title\":\"title\",\"outline\":\"outline\",\"hash_tag\":\"#hashtag\"},{\"title\":\"title1\",\"outline\":\"outline2\",\"hash_tag\":\"hashtag2\"}]}";
        
        System.out.println(jsonStr);
        
        System.out.println(0);
        // JSONICでやる
        Anime anites = JSON.decode(jsonStr, Anime.class);
        
        System.out.println(1);
        
        System.out.println(anites.getAnime().get(0).getHash_tag());
   
  	//ページを開くメソッド
  	public void openSite(){
  		WebView webView = new WebView(this);
  		webView.loadUrl(loadURL);

  		setContentView(webView);
  	}
}

