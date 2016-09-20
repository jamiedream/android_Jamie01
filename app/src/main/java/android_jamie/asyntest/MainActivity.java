package android_jamie.asyntest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private MyTask mt1;
    private TextView mesg;
    private ImageView img;
    private UIHandler handler;
    private Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mesg = (TextView)findViewById(R.id.mesg);
        img = (ImageView)findViewById(R.id.img);
        handler = new UIHandler();
    }
    public void test1(View v){
        mt1 = new MyTask();
        mt1.execute("Lucy","Angela","Judy");

    }
    public void test2(View v){
        if(mt1!=null && !mt1.isCancelled()){
            mt1.cancel(true);
        }

    }
    public void test3(View v){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.androidcentral.com/sites/androidcentral.com/files/styles/large/public/article_images/2015/03/podcast-ac-new_2.jpg?itok=XlmmELk7");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    bmp = BitmapFactory.decodeStream(conn.getInputStream());
                    handler.sendEmptyMessage(0);
                }catch(Exception e){
                    Log.d("jamie", e.toString());
                }
            }
        }.start();

    }
    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            img.setImageBitmap(bmp);

        }
    }
    private class MyTask extends AsyncTask<String,Object,String>{
        @Override
        protected String doInBackground(String... params) {
            Log.d("jamie","doInBackground");
            int i=0;boolean isCancel = false;
            for(String name:params){
                if (isCancelled()){
                    isCancel = true;
                    break;
                }


                Log.d("jamie","Hello, "+name);
                i++;
                publishProgress(i,name);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return isCancel?"Cancel!":"Game over";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("jamie","onPreExecute");
        }
//正常結束
        @Override
        protected void onPostExecute(String end) {
            super.onPostExecute(end);
            Log.d("jamie","onPostExecute"+end);
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
            Log.d("jamie","onProgressUpdate");
            mesg.setText(values[0]+":"+values[1]);
        }
//提早結束
        @Override
        protected void onCancelled(String end) {
            super.onCancelled(end);
            Log.d("jamie","onCancelled"+end);
        }
    }
}
