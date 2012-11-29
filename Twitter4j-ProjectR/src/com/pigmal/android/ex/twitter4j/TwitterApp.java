package com.pigmal.android.ex.twitter4j;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class TwitterApp extends Activity implements OnClickListener {
	private static final String TAG = "T4JSample";

	private Button buttonLogin;
	private Button getTweetButton;
	private TextView tweetText;
	private ScrollView scrollView;
	//twitter変数←超メイン
	private static Twitter twitter;
	private static RequestToken requestToken;
	//SharedPreferencesはアプリケーションの設定値などを簡単に保存/読込を行う
	private static SharedPreferences mSharedPreferences;
	private static TwitterStream twitterStream;
	private boolean running = false;
	
	//androidアプリの起動された時に呼び出されるので初期化みたいなもん
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//最初にmainのレイアウトを設定
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//メンバ変数の初期化//
		// プリファレンスから保存したトークンを読み込み
		mSharedPreferences = getSharedPreferences(Const.PREFERENCE_NAME, MODE_PRIVATE);
		//tweetを表示する
		scrollView = (ScrollView)findViewById(R.id.scrollView);
		tweetText =(TextView)findViewById(R.id.tweetText);
		//TLを取得するボタンのセット
		getTweetButton = (Button)findViewById(R.id.getTweet);
		getTweetButton.setOnClickListener(this);
		//ログインするボタンのセット
		buttonLogin = (Button) findViewById(R.id.twitterLogin);
		buttonLogin.setOnClickListener(this);
		
		/**
		 * Handle OAuth Callback
		 */
		// Twitterの認証画面から発行されるIntentからUriを取得
		//oauth://t4jsampleというurlを取得（Const.CALLBACK_URLのこと）
		Uri uri = getIntent().getData();
		if (uri != null && uri.toString().startsWith(Const.CALLBACK_URL)) {
			// oauth_verifierを取得する
			String verifier = uri.getQueryParameter(Const.IEXTRA_OAUTH_VERIFIER);
            try { 
            	// AccessTokeオブジェクトを取得.accessTokenをプリファレンスを使用し保存 
            	//リクエストトークンとoauth_verifierを使ってaccessTokenをツイッターからゲット 
                AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier); 
                Editor e = mSharedPreferences.edit();
                e.putString(Const.PREF_KEY_TOKEN, accessToken.getToken()); 
                e.putString(Const.PREF_KEY_SECRET, accessToken.getTokenSecret()); 
                e.commit();
	        } catch (Exception e) { 
	                Log.e(TAG, e.getMessage()); 
	                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show(); 
			}
        }		
	}
	//アプリが表示される時
	protected void onResume() {
		super.onResume();

		if (isConnected()) {
			//// トークンを取得
			String oauthAccessToken = mSharedPreferences.getString(Const.PREF_KEY_TOKEN, "144058574-wgjVVM0IROlWAWJgDJMFxZfN5jQ7dEIWxC26KY3i");
			// トークンシークレットを取得
			String oAuthAccessTokenSecret = mSharedPreferences.getString(Const.PREF_KEY_SECRET, "EmhTeeULUBNhjq2tfln6QLFrSaxDsiChxPXvkM94bQ8");
			// 認証処理
			ConfigurationBuilder confbuilder = new ConfigurationBuilder();
			Configuration conf = confbuilder
								.setOAuthConsumerKey(Const.CONSUMER_KEY)
								.setOAuthConsumerSecret(Const.CONSUMER_SECRET)
								.setOAuthAccessToken(oauthAccessToken)
								.setOAuthAccessTokenSecret(oAuthAccessTokenSecret)
								.build();
			twitterStream = new TwitterStreamFactory(conf).getInstance();
			
			buttonLogin.setText(R.string.label_disconnect);
			getTweetButton.setEnabled(true);
		} else {
			buttonLogin.setText(R.string.label_connect);
		}
	}

	/**
	 * check if the account is authorized
	 * @return
	 */
	private boolean isConnected() {
		return mSharedPreferences.getString(Const.PREF_KEY_TOKEN, null) != null;
	}

	private void askOAuth() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setOAuthConsumerKey(Const.CONSUMER_KEY);
		configurationBuilder.setOAuthConsumerSecret(Const.CONSUMER_SECRET);
		Configuration configuration = configurationBuilder.build();
		twitter = new TwitterFactory(configuration).getInstance();
		
		try {
			requestToken = twitter.getOAuthRequestToken(Const.CALLBACK_URL);
			Toast.makeText(this, "Please authorize this app!", Toast.LENGTH_LONG).show();
			this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Remove Token, Secret from preferences
	 */
	private void disconnectTwitter() {
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.remove(Const.PREF_KEY_TOKEN);
		editor.remove(Const.PREF_KEY_SECRET);

		editor.commit();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.twitterLogin:
			if (isConnected()) {
				disconnectTwitter();
				buttonLogin.setText(R.string.label_connect);
			} else {
				askOAuth();
			}
			break;
		case R.id.getTweet:
			if (running) {
				stopStreamingTimeline();
				running = false;
				getTweetButton.setText("start streaming");
			} else {
				startStreamingTimeline();
				running = true;
				getTweetButton.setText("stop streaming");
			}
			break;
		}
	}
	
	private void stopStreamingTimeline() {
		twitterStream.shutdown();
	}

	public void startStreamingTimeline() {
	    UserStreamListener listener = new UserStreamListener() {

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				System.out.println("deletionnotice");
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				System.out.println("scrubget");
			}

			@Override
			public void onStatus(Status status) {
				final String tweet = "@" + status.getUser().getScreenName() + " : " + status.getText() + "\n"; 
				System.out.println(tweet);
				tweetText.post(new Runnable() {
					@Override
					public void run() {
						tweetText.append(tweet);
						scrollView.fullScroll(View.FOCUS_DOWN);
					}
				});
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				System.out.println("trackLimitation");
			}

			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onBlock(User arg0, User arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onDeletionNotice(long arg0, long arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onDirectMessage(DirectMessage arg0) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void onFavorite(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFollow(User arg0, User arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onFriendList(long[] arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onRetweet(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUnblock(User arg0, User arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUnfavorite(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUserListCreation(User arg0, UserList arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUserListDeletion(User arg0, UserList arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUserListMemberDeletion(User arg0, User arg1,  UserList arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUserListUpdate(User arg0, UserList arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onUserProfileUpdate(User arg0) {
				// TODO Auto-generated method stub
			}
	    };
        twitterStream.addListener(listener);
        twitterStream.user();
	}
}
