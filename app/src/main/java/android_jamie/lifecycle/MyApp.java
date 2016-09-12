package android_jamie.lifecycle;

import android.app.Application;
import android.util.Log;

/**
 * Created by user on 2016/9/12.
 */
public class MyApp extends Application{
    private int a;
    private String b;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("jamie","MyApp");

        a=123;
        b="jamie";
    }
    int getA(){return a;}
    String getB(){return b;}

    public void setA(int a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }
    //ab屬性不+private不須get.set ;
}
