package project.r;

import net.arnx.jsonic.JSON;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;


@TargetApi(9)
@SuppressLint({ "NewApi", "NewApi" })
public class TopPage extends Activity
{
	final static String loadURL = "http://nameless-taiga-3201.herokuapp.com/getAnimesJson";
	//JSONキャッチを使えるようにする
	JsonCatch getj = new JsonCatch();
	//Iconキャッチを使えるようにする
	//IconCatch geti = new IconCatch();

	//アニメのタイトルを入れる文字列箱
	String[] anitit = new String[50];
	
	// JSONICでやる
	Anime anites = JSON.decode(getj.takeJson(loadURL), Anime.class);



	public class TopAnime {
		String title;
		int number; //これはどれをクリックしたのか判別するための数字
		//int iconId;
		//Bitmap draw;

		TopAnime(String s, int n) { //本当は画像などの要素も入れたかったが今は割愛
			this.title = s;
			this.number = n;
			//this.draw = d;
			//this.iconId = id;  
		}  
	}

	static class ViewHolder {
		TextView textView;
		//ImageView imageView;
		//SmartImageView iCon;
	}

	//配列のクラス型を元にレイアウトを形作る
	public class MyAdapter extends ArrayAdapter<TopAnime> {
		private LayoutInflater inflater;
		private int layoutId;
		
		public MyAdapter(Context context, int layoutId, TopAnime[] objects) {
			super(context, 0, objects);
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
			this.layoutId = layoutId;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null)
			{
				convertView = inflater.inflate(layoutId, parent, false);
				holder = new ViewHolder();
				holder.textView = (TextView) convertView.findViewById(R.id.text_item);
				//holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
				//holder.iCon = (SmartImageView) convertView.findViewById(R.id.icon);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			TopAnime data = getItem(position);
			holder.textView.setText(data.title);
			
			//holder.imageView.setImageBitmap(data.draw);
			//holder.iCon.setImageUrl(data.iconId);
			
			final String str = Integer.toString(position);
			final String str2 = anites.getAnime().get(position).getHash_tag();
			holder.textView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO 自動生成されたメソッド・スタブ
					System.out.println("クリックしたよ");

					//キャッシュ
					GetCash.setText("anime_num", str); //anime_numに割り当てられたIDを入れる
					GetCash.setText("anime_hash", str2);
					
					//画面遷移する
					Intent intent = new Intent(TopPage.this, AnimePage.class);
					startActivity(intent);
				}
			});
			
			return convertView;  
		}
	}

	//画面の呼び出し
	/** Called when the activity is first created. */
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		setContentView(R.layout.top_page);

		//タイトルの文字列箱に実際の文字列をブチコム
		for(int i = 0; i < anites.getAnimeNumber(); i++)
		{
			anitit[i] = anites.getAnime().get(i).getTitle();
		}

		//画像を読み込んで当てはめる
		/*
		for(int i = 0; i < anites.getAnimeNumber(); i++)
		{
			animage[i] = "http://api.twitter.com/1/users/profile_image?screen_name=" + anites.getAnime().get(i).getHash_tag() + "&size=mini";
		}
		*/
		
		
		//TopAnimeクラスを生成し、中身をブチ込む。
		TopAnime mDatas[] = new TopAnime[anites.getAnimeNumber()];
		for(int i = 0; i < anites.getAnimeNumber(); i++)
		{
			mDatas[i] = new TopAnime(anitit[i], i);
		}
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new MyAdapter(this, R.layout.item, mDatas));

		/*
		//テキスト項目がクリックされた時の処理
		gridview.setClickable(true);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO 自動生成されたメソッド・スタブ
				GridView grid = (GridView)arg0;
				String str = (String)grid.getItemAtPosition(arg2);
				System.out.println(str);
			}
		});
		
		//テキスト項目が選択された時の処理
		gridview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO 自動生成されたメソッド・スタブ
				GridView grid = (GridView)arg0;
				String str = (String)grid.getItemAtPosition(arg2);
				System.out.println(str);
			}
			//何も選択されていないとき
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO 自動生成されたメソッド・スタブ
				System.out.println("セレクトされてないね");
			}
		});
		*/
	}
}
