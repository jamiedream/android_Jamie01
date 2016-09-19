package android_jamie.networktest;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                e.printStackTrace();
            }
            Log.d("jamie", "Conneted");
        }else {
            Log.d("jamie", "Not Conneted");
        }
    }
    public void test(View v){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("http://data.coa.gov.tw/Service/OpenData/EzgoTravelFoodStay.aspx");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    data = reader.readLine();
                    reader.close();

                } catch (Exception e) {
                    Log.d("jamie", e.toString());
                }
            }
        }.start();


    }
    private void parseJSON(View v){

    }
}

