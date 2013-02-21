package project.r;


/*
 * 使用していないクラスです。
 * アイコンの画像を取得するためのものですがうまく行かず頓挫。
 * しばらくしたら実装予定なので放置。
 */


/*
//@TargetApi(Build.VERSION_CODES.GINGERBREAD)
//@SuppressLint("NewApi")
public class IconCatch extends Activity {
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
    
	
	public Bitmap loadImage(String str)
	{
		String ulrStr = "http://api.twitter.com/1/users/profile_image?screen_name=" + str + "&size=mini";
		
		try { 
			URL url = new URL(ulrStr); 
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 

			connection.setDoInput(true); 
			connection.connect(); 

			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input); 
			return myBitmap;
		} catch (IOException e) {
			//画像が大きすぎたりする場合
			e.printStackTrace(); 
			return null;
		}
	}
}
*/