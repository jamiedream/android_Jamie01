package android_jamie.mystopwatch;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ListView lapList;
    private TextView timeClock;
    private Button leftButton, rightButton;
    private boolean isRunning;
    //之後要修改btn內容,不可用view(view只能按)
    private int counter;
    private Timer timer;
    private UIHandler handler;
    private CountTask countTask;
    private SimpleAdapter adapter;
    private String[] from = {"title"};
    private int[] to = {R.id.lap_title};//將from資料指向to
    private LinkedList<HashMap<String, String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lapList = (ListView)findViewById(R.id.lapList);
        initlapList();
        timeClock = (TextView)findViewById(R.id.timeClock);

        leftButton = (Button)findViewById(R.id.leftButton);
        rightButton = (Button)findViewById(R.id.rightButton);
        timer = new Timer();
        handler = new UIHandler();
    }

    @Override
    public void finish() {
        timer.purge();//清除
        timer.cancel();
        timer = null;
        super.finish();
        //使用者按返回鍵即finish
    }

    private void initlapList() {
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this, data, R.layout.layout_lap, from, to);
        lapList.setAdapter(adapter);
    }

    // Lap / Reset
    public  void toLeft(View v){
        if(isRunning){
            doLap();
        }else{
            doReset();
        }
    }
    // Stop / Start
    public void toRight(View v){
        isRunning = !isRunning;
        rightButton.setText(isRunning?"Stop":"Start");
        leftButton.setText(isRunning?"Lap":"Reset");
        if(isRunning){
            doStart();
        }else{
            doStop();
        }

    }
    private void doStart() {
        countTask = new CountTask();
        timer.schedule(countTask, 0, 10);//從毫秒百位數開始
    }
    private void doStop() {
        if (countTask != null) {
            countTask.cancel();
            countTask = null;
        }
    }
    private void doLap() {
        HashMap<String, String> lap = new HashMap<>();
        lap.put(from[0],""+counter);// "title"
        data.add(0,lap);// 新加入的資料推到0列
        adapter.notifyDataSetChanged();//告知adapter資料變化
    }
    private void doReset() {
        counter = 0;
        handler.sendEmptyMessage(100);
        data.clear();
        adapter.notifyDataSetChanged();
    }

   private class CountTask extends TimerTask{
       @Override
       public void run() {
           handler.sendEmptyMessage(111);
           counter++;
       }
   }
    private class UIHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timeClock.setText(""+counter);

        }
    }

}
