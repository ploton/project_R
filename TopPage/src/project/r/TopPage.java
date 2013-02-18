package project.r;

import net.arnx.jsonic.JSON;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;


@TargetApi(9)
@SuppressLint({ "NewApi", "NewApi" })
public class TopPage extends Activity
{
	private final static String loadURL = "http://nameless-taiga-3201.herokuapp.com/getAnimesJson";
	//JSONキャッチを使えるようにする
	JsonCatch getj = new JsonCatch();
	//Iconキャッチを使えるようにする
	//IconCatch geti = new IconCatch();

	//アニメのタイトルを入れる文字列箱
	String[] anitit = new String[50];
	//アニメ画像のURLを入れる箱
	String[] animage = new String[50];


	public class TopAnime {
		String iconId;
		String title;
		//Bitmap draw;

		TopAnime(String str, String s) {
			//this.draw = d;
			//this.iconId = id;  
			this.title = s;  
			this.iconId = str;
		}  
	}

	static class ViewHolder {
		TextView textView;
		ImageView imageView;
		SmartImageView iCon;
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
				holder.textView = (TextView) convertView.findViewById(R.id.textview);
				//holder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
				holder.iCon = (SmartImageView) convertView.findViewById(R.id.icon);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			TopAnime data = getItem(position);
			holder.textView.setText(data.title);
			//holder.imageView.setImageBitmap(data.draw);
			holder.iCon.setImageUrl(data.iconId);
			System.out.println("アイコンをholderに入れたぞー");
			
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

		// JSONICでやる
		Anime anites = JSON.decode(getj.takeJson(loadURL), Anime.class);
	
		//タイトルの文字列箱に実際の文字列をブチコム
		for(int i = 0; i < 5; i++)
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
		animage[0] = "http://api.twitter.com/1/users/profile_image?screen_name=jamjam_tqn&size=mini";
		animage[1] = "http://api.twitter.com/1/users/profile_image?screen_name=jamjam_hobby&size=mini";
		System.out.println("URLを書き込んだぞー");
		TopAnime[] mDatas = {
				new TopAnime(animage[0], anitit[0]),  
				new TopAnime(animage[1], anitit[1]),  
				//new TopAnime(android.R.drawable.ic_menu_add, anitit[2]),  
				//new TopAnime(android.R.drawable.ic_menu_add, anitit[3]),
				//new TopAnime(android.R.drawable.ic_menu_add, anitit[4]),
		};
		
		GridView gridview = (GridView) findViewById(R.id.gridview);  
		gridview.setAdapter(new MyAdapter(this, R.layout.item, mDatas));
		
	}
}
