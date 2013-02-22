package project.r;

import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Auth extends Activity {

	public static RequestToken requestToken = null;
	public static OAuthAuthorization oAuth = null;
	private static final String CONSUMER_KEY = "6xZj4hzdTWmz2dMHLsWRQ";	 
	private static final String CONSUMER_SECRET = "7ZFULbwsmPP60U8P7ZEspeDf7xXXwAPEmnF8QH4YRE";
	private static final String CALLBACK_URL = "oauth://com";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);
        
        Button btn = (Button)findViewById(R.id.oauth);
        btn.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
                executeOauth();
            }
        });
    }
    private void executeOauth(){
    	 // twitter4jの設定読み込み
        Configuration conf = ConfigurationContext.getInstance();
        oAuth = new OAuthAuthorization(conf);
         // OAuth認証オブジェクトにCONSUMER_KEY、CONSUMER_SECRETを追加
        oAuth.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        try {
           requestToken = oAuth.getOAuthRequestToken(CALLBACK_URL);
           Toast.makeText(this, "Please authorize this app!", Toast.LENGTH_LONG).show();
           this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}