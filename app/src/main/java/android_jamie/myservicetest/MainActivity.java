package android_jamie.myservicetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private MyReceiver receiver;
    private SeekBar seekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar = (SeekBar)findViewById(R.id.seekbar);
        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("jamiemp3");
        registerReceiver(receiver,filter);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    Intent it = new Intent(MainActivity.this,MyService.class);
                    it.putExtra("skip",progress);
                    startService(it);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void test1(View v){
        Intent it = new Intent(this, MyService.class);
        startService(it);

    }
    public void test2(View v){
        Intent it = new Intent(this, MyService.class);
        stopService(it);

    }
    public void test3(View v){

    }
    private class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int len = intent.getIntExtra("len",-1);
            int now = intent.getIntExtra("now",-1);
            if(len!=-1)seekbar.setMax(len);
            if(now!=-1)seekbar.setMax(now);
        }
    }
}
