package project.r;


import java.util.HashMap;

public class GetCash {
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