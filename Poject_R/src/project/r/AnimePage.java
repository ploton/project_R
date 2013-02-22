package project.r;

import net.arnx.jsonic.JSON;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AnimePage extends Activity{

	//画面の呼び出し
	/** Called when the activity is first created. */
	@SuppressLint("NewApi")
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anime_page);
		
		//JSONを使いたい
		JsonCatch json = new JsonCatch();
		// JSONICでやる
		Anime anites2 = JSON.decode(json.takeJson("http://nameless-taiga-3201.herokuapp.com/getAnimesJson"), Anime.class);
		
		//テキストビューの定義
		TextView title = (TextView) findViewById(R.id.text_title);
		TextView intro = (TextView) findViewById(R.id.text_intro);
		
		int anime_id = Integer.parseInt(GetCash.getText("anime_num"));
		
		title.setText(anites2.getAnime().get(anime_id).getTitle());
		intro.setText(anites2.getAnime().get(anime_id).getOutline());
		
		//ボタンのイベントつくる
		Button tl_button = (Button) findViewById(R.id.button_tl);
		tl_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				Intent intent = new Intent(AnimePage.this, Auth.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
