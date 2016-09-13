package android_jamie.mystopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ListView lapList;
    private TextView timeClock;
    private Button leftButton, rightButton;
    //之後要修改btn內容,不可用view(view只能按)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lapList = (ListView)findViewById(R.id.lapList);
        timeClock = (TextView)findViewById(R.id.timeClock);

        leftButton = (Button)findViewById(R.id.leftButton);
        rightButton = (Button)findViewById(R.id.rightButton);

    }
    public  void toLeft(View v){

    }
    public void toRight(View v){

    }


}
