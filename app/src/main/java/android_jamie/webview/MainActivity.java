package android_jamie.webview;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private WebView wv;
    private EditText inputname;
    private UIHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        wv = new WebView(this);
//        wv.loadUrl("http://www.iii.org.tw");
//        setContentView(wv);

        tv = (TextView)findViewById(R.id.tv);
        wv = (WebView)findViewById(R.id.wv);
        inputname = (EditText)findViewById(R.id.inputname);
        handler = new UIHandler();
        intiWebView();
    }

    private void intiWebView() {
        WebViewClient client = new WebViewClient();
        wv.setWebViewClient(client);
//        wv.loadUrl("http://www.iii.org.tw");

        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
            //ANDROID預設JS關閉
        wv.loadUrl("file:///android_asset/jamie.html");
//        wv.loadUrl("file:///android_asset/imgs/android.gif");

//        String data = "<h1>123456</h1>";
//        wv.loadData(data, "text/html;charset=UTF-8",null);
        wv.addJavascriptInterface(new MyJS(),"LUCY");

    }
    public class MyJS{
        @JavascriptInterface
        public void showMesg(String webmesg){
            Log.d("jamie",webmesg);
            Toast.makeText(MainActivity.this,webmesg,Toast.LENGTH_SHORT).show();

            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("mesg", webmesg);
            msg.setData(data);
            handler.sendMessage(msg);
        }
        @JavascriptInterface
        public void gotopage2(){
            Intent it = new Intent(MainActivity.this, Page2Activity.class);
            startActivity(it);
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv.setText(msg.getData().getString("mesg"));
        }
    }
    public void b1task(View v){
//        doPre();
        String name = inputname.getText().toString();
        wv.loadUrl("javascript:test2('" + name +"')");
    }
    public void b2task(View v){
//        doNext();
        int x=3,y=4;
        wv.loadUrl("javascript:test3(" + x + "," + y +")");
    }
    public void b3task(View v){
        doReload();
    }
    private void doPre(){
        wv.goBack();
    }
    private void doNext(){
        wv.goForward();
    }
    private void doReload(){
        wv.reload();
    }

//    @Override
//    public void finish() {
//        if(!doPre()) {
//            super.finish();
//        }
//    }
}
