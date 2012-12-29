package com.example.projectr;

import twitter4j.DirectMessage;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
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
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Callback extends Activity {

	private static final String CALLBACK_URL = "oauth://com";
    private String OAUTH_VERIFIER = "oauth_verifier";
    private static final String CONSUMER_KEY = "6xZj4hzdTWmz2dMHLsWRQ";	 
	private static final String CONSUMER_SECRET = "7ZFULbwsmPP60U8P7ZEspeDf7xXXwAPEmnF8QH4YRE";
	private static TwitterStream twitterStream;
	private TextView tweetText;
	private TextView tv;
	private ScrollView scrollView;
	String defoTEXT = "#jamjam_kill";
	private static SharedPreferences mSharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callback);
		AccessToken accessToken = null;
		//Twitter�̔F�؉�ʂ��甭�s�����Intent����Uri���擾
		Uri uri = getIntent().getData();
		if(uri != null && uri.toString().startsWith(CALLBACK_URL)){
            //oauth_verifier���擾����
            String verifier = uri.getQueryParameter(OAUTH_VERIFIER);
            try {
                //AccessToken�I�u�W�F�N�g���擾
                accessToken = Auth.oAuth.getOAuthAccessToken(Auth.requestToken, verifier);
             
            } catch (TwitterException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show(); 
            }
        }
		// �v���t�@�����X����ۑ������g�[�N����ǂݍ���
		mSharedPreferences = getPreferences(MODE_PRIVATE);
				// �v���t�@�����X�� �A�N�Z�X�g�[�N����ۑ�
		        // (String�^�ɂ��邽�߂�2�ɕ������Ă��܂��B)
				SharedPreferences.Editor editor = mSharedPreferences.edit();
		        editor.putString("KEY_TOKEN", accessToken.getToken());
		        editor.putString("KEY_TOKEN_SECRET", accessToken.getTokenSecret()); 
		        editor.commit();
		Button button = (Button)findViewById(R.id.tweet);
        button.setOnClickListener(new SendTweet());
        tweetText =(TextView)findViewById(R.id.tweetText);
        tv = (TextView) findViewById(R.id.TwitterTextCount);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		String oauthAccessToken = mSharedPreferences.getString("KEY_TOKEN", "");
		String oAuthAccessTokenSecret = mSharedPreferences.getString("KEY_TOKEN_SECRET", "");

		ConfigurationBuilder confbuilder = new ConfigurationBuilder();
		Configuration conf = confbuilder
							.setOAuthConsumerKey(CONSUMER_KEY)
							.setOAuthConsumerSecret(CONSUMER_SECRET)
							.setOAuthAccessToken(oauthAccessToken)
							.setOAuthAccessTokenSecret(oAuthAccessTokenSecret)
							.build();
		twitterStream = new TwitterStreamFactory(conf).getInstance();
		startStreamingTimeline();
	}


	public void startStreamingTimeline(){
		
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
			/*
			@Override
			public void onRetweet(User arg0, User arg1, Status arg2) {
				// TODO Auto-generated method stub
			}*/

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

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}
	    };
        twitterStream.addListener(listener);
     // �����p�̃t�B���^�[�����܂�
        FilterQuery filterQuery = new FilterQuery();
        // �������镶�����ݒ肵�܂��B �����ݒ肷�邱�Ƃ��o���āA�z��œn���܂�
        filterQuery.track(new String[] {defoTEXT});
        // �t�B���^�[���܂�
        twitterStream.filter(filterQuery);
        //����R�[�h�O�s���R�����g�A�E�g���Ă���ɖ߂��Ƃ����ǂ���̃��[�U�[��TL�擾�ɂȂ�
        //twitterStream.user();
	}
	public class SendTweet implements OnClickListener {
		//�n�b�V���^�O�ǉ��I
       
		@Override
        public void onClick(View v) {
          EditText edit= (EditText) findViewById(R.id.edit);
          //edit.setText(defoTEXT);
          edit.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(tv != null){
	                final int textColor;
	                int length = 140- s.length();
	                if(length < 0){
	                    textColor = Color.RED;
	                }else{
	                    textColor = Color.GRAY;
	                }
	                tv.setTextColor(textColor);
	                tv.setText(String.valueOf(length));
	            }
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
          String textstr = edit.getText().toString();
          
          //�A�N�Z�X�g�[�N���̃Z�b�g
          SharedPreferences pref = getPreferences(MODE_PRIVATE);
          String token = pref.getString("KEY_TOKEN", null);
          String tokenSecret = pref.getString("KEY_TOKEN_SECRET", null);
          AccessToken accessToken = new AccessToken(token, tokenSecret);
    
          Twitter twitter = new TwitterFactory().getInstance();
          //�R���V���[�}�[�L�[���Z�b�g
          twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
          twitter.setOAuthAccessToken(accessToken);
       
          	try {
            //�c�C�[�g�̑��M
            twitter.updateStatus(textstr);
            Toast.makeText(Callback.this, "�c�C�[�g�����I",
                Toast.LENGTH_SHORT).show();
          	} catch (TwitterException e) {
            Toast.makeText(Callback.this, "���M�ł��Ă��܂���c�c",
                Toast.LENGTH_SHORT).show();
            Toast.makeText(Callback.this, e.getErrorMessage(),
                Toast.LENGTH_LONG).show();
           /* Toast.makeText(Callback.this, e.getMessage(),
                Toast.LENGTH_LONG).show();*/
            e.printStackTrace();
          	}
        }
	}
}
