package com.pigmal.android.ex.twitter4j;

public class Const {
	// Application key
	/* Twitter developerで取得するconsumer keyの値 */
	static String CONSUMER_KEY = "XX3JtXuVvCbQGCQdn0qlIQ";
	// Application secret code
    /* Twitter developerで取得するconsumer secretの値 */
	static String CONSUMER_SECRET = "kypo9v6q1pLa6JWb6hvBso556jGfdLCq9y6N7pnbMU";

	static String PREFERENCE_NAME = "twitter_oauth";
	// 認証後パラメータOAuth token secret参照キー
	static final String PREF_KEY_SECRET = "oauth_token_secret";
	// Twitterアプリ承認時にコールバックされるURLの認証トークンパラメーター情報
	static final String PREF_KEY_TOKEN = "oauth_token";
	// Twitterアプリ承認時にコールバックされるURL
	static final String CALLBACK_URL = "oauth://t4jsample";

	static final String IEXTRA_AUTH_URL = "auth_url";
	// Twitterアプリ承認時にコールバックされるURLの認証立証パラメーター情報
	static final String IEXTRA_OAUTH_VERIFIER = "oauth_verifier";
	// 認証後パラメータOAuth token参照キー
	static final String IEXTRA_OAUTH_TOKEN = "oauth_token";
}