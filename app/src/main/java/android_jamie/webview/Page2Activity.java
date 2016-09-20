package android_jamie.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Page2Activity extends AppCompatActivity {
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        wv = (WebView)findViewById(R.id.page2_wv);
        init();
    }
    private void init(){
        WebViewClient client = new WebViewClient();
        wv.setWebViewClient(client);

        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.loadUrl("file:///android_asset/page2.html");
    }
}
