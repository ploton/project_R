package com.example.greent;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TweetActivity extends FragmentActivity {

    private EditText mInputText;
    private Twitter mTwitter;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_tweet);

        mTwitter = TwitterUtils.getTwitterInstance(this);
        tv = (TextView) findViewById(R.id.TwitterTextCount);
        mInputText = (EditText) findViewById(R.id.input_text);
        mInputText.addTextChangedListener(new TextWatcher() {
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
        
        
        

        findViewById(R.id.action_tweet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tweet();
            }
        });
    }

    private void tweet() {
        AsyncTask<String, Void, Boolean> task = new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                try {
                    mTwitter.updateStatus(params[0]);
                    return true;
                } catch (TwitterException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    showToast("ツイートが完了しました！");
                    finish();
                } else {
                    showToast("ツイートに失敗しました。。。");
                }
            }
        };
        task.execute(mInputText.getText().toString());
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
