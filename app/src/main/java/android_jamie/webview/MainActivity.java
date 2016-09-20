package android_jamie.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private WebView wv;
    private EditText inputname;
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


//        String data = "<h1>123456</h1>";
//        wv.loadData(data, "text/html;charset=UTF-8",null);

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
