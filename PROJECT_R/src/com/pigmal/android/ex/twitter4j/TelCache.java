//キャッシュ保存クラス　String:キー名, String:キャッシュ名

//メモリ保存の代わりとして活躍するもの。
//このコード自体は弄る必要なし

//呼び出し TelCache(キー名)
//書き込み TelCchhe(キー名, テキスト)
package com.pigmal.android.ex.twitter4j;

import java.util.HashMap;

public class TelCache {
    private static HashMap<String, String> cache = new HashMap<String,String>();
 
    //キャッシュよりテキストデータを取得
    public static String getText(String key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        //存在しない場合はNULLを返す
        return null;
    }
 
    //キャッシュにテキストデータを設定
    public static void setText(String key, String text) {
        cache.put(key, text);
    }
 
    //キャッシュの初期化（リスト選択終了時に呼び出し、キャッシュで使用していたメモリを解放する）
    public static void clearCache(){
        cache = null;
        cache = new HashMap<String,String>();
    }
}