package android_jamie.networktest;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {
    private ConnectivityManager mgr;
    private String data;
    private TextView mesg;
    private StringBuffer sb;
    private UIHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new UIHandler();
        mesg = (TextView)findViewById(R.id.mesg);

        mgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = mgr.getActiveNetworkInfo();
        if (info != null&&info.isConnected()) {
            try {
                Enumeration<NetworkInterface> eni = NetworkInterface.getNetworkInterfaces();
                while(eni.hasMoreElements()){
                  NetworkInterface nif = eni.nextElement();
                    Enumeration<InetAddress> ip = nif.getInetAddresses();
                    while(ip.hasMoreElements()){
                        InetAddress ia = ip.nextElement();
                        Log.d("jamie",ia.getHostAddress());
                    }
                }
            } catch (Exception e) {
                Log.d("jamie",e.toString());
            }
            Log.d("jamie", "Conneted");
        }else {
            Log.d("jamie", "Not Conneted");
        }
    }
    public void test(View v) {
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("http://data.coa.gov.tw/Service/OpenData/EzgoTravelFoodStay.aspx");
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.connect();
//                    BufferedReader reader =
//                            new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    data = reader.readLine();
//                    reader.close();
//                    parseJSON();
//                } catch (Exception e) {
//                    Log.d("jamie", e.toString());
//                }
//            }
//        }.start();
        mesg.setText("");

        MyTread mt1 = new MyTread();
        mt1.start();
    }

    private class MyTread extends Thread {
        @Override
        public void run() {
            try {
                URL url = new URL("http://m.coa.gov.tw/OpenData/FarmTransData.aspx");
                HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
                conn.connect();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(conn.getInputStream()));
                data = reader.readLine();
                reader.close();
                parseJSON();
            }catch(Exception ee){
                Log.d("brad", ee.toString());
            }
        }
    }


    private void parseJSON(){
        sb = new StringBuffer();
        try {
            JSONArray root = new JSONArray(data);
            for (int i=0; i<root.length(); i++){
                JSONObject row = root.getJSONObject(i);
                String name = row.getString("交易日期");
                String addr = row.getString("作物名稱");
                Log.d("brad", name + " -> " + addr);
                sb.append(name + " -> " + addr + "\n");
            }
            handler.sendEmptyMessage(0);
        }catch(Exception ee){
            Log.d("brad", ee.toString());
        }
    }
    public void test2(View v){

    }
    private class UIHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mesg.setText(sb);


        }
    }

}

