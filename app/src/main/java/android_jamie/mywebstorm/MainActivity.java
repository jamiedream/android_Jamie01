package android_jamie.mywebstorm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private File sdroot;
    private ProgressDialog pDialog;
    private UIHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sdroot = Environment.getExternalStorageDirectory();

        pDialog = new ProgressDialog(this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setTitle("Downloading...");

        handler = new UIHandler();
    }

    public void create(View v){
        File file = new File(sdroot.getAbsoluteFile() +"/jamie.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file);
            fout.write("Hello".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"Write OK", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upload(View v){
//        try {
//            URL url = new URL("");
//            HttpURLConnection conn = ()url.openConnection();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    MultipartUtility mu = new MultipartUtility("http://www.brad.tw/iii2003/upload.php","UTF-8");
                    mu.addFilePart("upload",new File(sdroot.getAbsoluteFile()+"/jamie.txt"));
                    List<String> lines = mu.finish();


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }
    public void test(View v){
        pDialog.show();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    MultipartUtility mu =
                            new MultipartUtility("http://data.coa.gov.tw/Service/OpenData/EzgoAttractions.aspx", "UTF-8");
                    List<String> ret = mu.finish();
                    for (String line : ret){
                        Log.v("jamie", line.length() + ":" + line);
                    }

                    handler.sendEmptyMessage(0);
                } catch (IOException e) {
                    handler.sendEmptyMessage(0);
                    e.printStackTrace();
                }
            }
        }.start();

    }
    private class UIHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            pDialog.dismiss();
        }
    }
    public void camera(View v){
        Intent it = new Intent(this,MyCamera2Activity.class);
        startActivity(it);
    }

}
