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
   
        
        /*
        try {
			JSONObject jObject = new JSONObject(jsonStr);
			JSONObject anime = jObject.getJSONObject("anime");
			String title = anime.getString("title");
			String hash = anime.getString("hash_tag");
			
			//レイアウトに表示するテキストビュー
	        TextView web = new TextView(this);
	        web.setText(title + hash);
	        setContentView(web);
		} catch (JSONException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("例外だよ");
		}
        //sendj.postJson(postURL, postStr);
        */
    }
    
  //読み込みを実行する関数
    /*
  	public void readJson(String u){
  		String json;
  	    //GSONクラスを定義
  		Gson gson = new Gson();
  		json = getj.takeJson(u); //そのURLから読み込んだ文字列をjson変数にぶち込む
  		btooom = gson.fromJson(json.toString(), Anime.class); //GSONで文字列をJSON型に変換、pageに埋め込み！
  	}
  	*/

  	//ページを開くメソッド
  	public void openSite(){
  		WebView webView = new WebView(this);
  		webView.loadUrl(loadURL);

  		setContentView(webView);
  	}
}

