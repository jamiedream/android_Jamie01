package android_jamie.lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyApp myApp;


    public MainActivity(){
        Log.d("jamie", "MainActivity");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("jamie", "onCreate");

        myApp = (MyApp) getApplication();//透過getApplication取得MyApp
        Log.d("jamie", "myApp a:" + myApp.getA());
        Log.d("jamie", "myApp a:" + myApp.getB());
        myApp.setA(321);
        myApp.setB("haha");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("jamie", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("jamie", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("jamie", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("jamie", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("jamie", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("jamie", "onPause");
    }

    public void gotoPage2(View v){
        Intent intent = new Intent(this, Page2Activity.class);
        intent.putExtra("name", "jamie");
        intent.putExtra("stage", 4);
        intent.putExtra("sound",false);
        startActivity(intent);
    }
    public void gotoPage2v2(View v){
        Intent intent = new Intent(this, Page2Activity.class);
        intent.putExtra("name", "jamie");
        intent.putExtra("stage", 4);
        intent.putExtra("sound",false);

        startActivityForResult(intent,77);

    }
    public void gotoPage3v2(View v){
        Intent intent = new Intent(this, Page3Activity.class);
        startActivityForResult(intent,66);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("jamie","onActivityResult:"+resultCode);
        if(requestCode == 77){
            int a = data.getIntExtra("a",0);
            int b = data.getIntExtra("b",0);
            Log.d("jamie", a+":"+b);
        }else if(requestCode == 66){
            Log.d("jamie", "Page3 Back");
        }

    }

    public void exit(View v){
        finish();
        //強制從running到shut down
    }
    @Override
    public void finish(){
        super.finish();
        Log.d("jamie", "finish");
    }


}
