package tsst.ajaxtest;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends Activity{

	//表示させるテキストとボタンを設定
	private TextView text;
	private Button button;

	//読み込むURLを定義
	public static final String loadURL = "http://www.javascriptkit.com/dhtmltutors/javascriptkit.json";	
	//読み込むためのクラスを準備
	HttpDate hpdate = new HttpDate();

	//BTOOOMクラスを定義
	// Anime btooom = new Anime();

	Page page = new Page();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		readJson();


		text = (TextView) findViewById(R.id.textView1);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				//Ajaxから何かを受信しますよ
				String anid = page.getTitle();
				
				text.setText(anid);
				//openSite();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	//URL先をダウンロードする
	//その中にある文字列を読み取って、文字列で返す
	private static void readUrl(String urlString) throws IOException{
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			//return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
	*/

	
	//読み込みを実行する関数
	public void readJson(){
		String json;
	    //GSONクラスを定義
		Gson gson = new Gson();
		json = hpdate.getData(loadURL); //そのURLから読み込んだ文字列をjson変数にぶち込む
		page = gson.fromJson(json, Page.class); //GSONで文字列をJSON型に変換、pageに埋め込み！
		
		System.out.println("読み込んだよ");
		System.out.println(json);
	}
	

	//ページを開くメソッド
	public void openSite(){
		WebView webView = new WebView(this);
		webView.loadUrl(loadURL);

		setContentView(webView);
	}
}



//JSONの値を入れ込むための型
class Item{
	private String title;
	private String link;
	private String description;

	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		this.title = t;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String l) {
		this.link = l;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String d) {
		this.description = d;
	}
}

class Page {
	private String title;
	private String link;
	private String description;
	private String language;
	private List<Item> items;
	
	Page(){
		setTitle("あいうえお");
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
