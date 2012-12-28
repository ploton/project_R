package com.pigmal.android.ex.twitter4j;
 
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.view.WindowManager;
 
//ポップアップ表示させるコードです
//クリックリスナーのインターフェイスを使っています
public class PopupActivity extends Activity{
 
	//ポップアップウィンドウのクラス
    //PopupWindow popupWindow;
    //Handler mHandler = new Handler();
    //View view;
    
    //表示されるタイムラインを定める鍵です
    public String tlKey = "timeline";
    
    
    private Button[] button = new Button[30]; //ハッシュタグアニメのボタン配列
 
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
 
        setContentView(R.layout.popup);
 
        /*
        // LayoutInflaterインスタンスを取得
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // ポップアップ用のViewをpopupxmlから読み込む
        View popupView = (View)inflater.inflate(R.layout.popup, null);
        // レイアウトパラメータをセット
        popupView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        // PopupWindowを紐づけるViewのインスタンスを取得
        view = findViewById(R.id.magi);
        // viewに紐づけたPopupWindowインスタンスを生成
        popupWindow = new PopupWindow(view);
        // ポップアップ用のViewをpopupWindowにセットする
        popupWindow.setContentView(popupView);
        // サイズ(幅)を設定
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // サイズ(高さ)を設定
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        */
        
        // 切替ボタンにリスナーを設定
        button[0] = (Button) findViewById(R.id.hash0);
        button[1] = (Button) findViewById(R.id.hash1);
        button[2] = (Button) findViewById(R.id.hash2);
        
        

        button[0].setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		// TODO 自動生成されたメソッド・スタブ
        		hashClick(0);
        	}
        });
        button[1].setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		// TODO 自動生成されたメソッド・スタブ
        		hashClick(1);
        	}
        });
        button[2].setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		// TODO 自動生成されたメソッド・スタブ
        		hashClick(2);
        	}
        });
    }
    
    //選択されたハッシュをtlKeyにキャッシュ保存し、この画面を閉じる関数
    public void hashClick(int num){
    	//選択されたハッシュタグをキャッシュに保存します
		String hozon = button[num].getText().toString();
		
		TelCache.setText(tlKey, hozon);
		
		finish();
    }
    
 
    /*
    public void onClick(View v){
 
    	// 切替ボタン押下時にポップアップウィンドウの表示、非表示を切り替える
    	if(popupWindow.isShowing()){
 
    		popupWindow.dismiss();
    	}else{
    		popupWindow.showAsDropDown(view, 0, 0);
    	}
    }
    */
}

