package android_jamie.lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Page2Activity extends AppCompatActivity {
    private TextView mesg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        Intent intent = getIntent();
        int stage = intent.getIntExtra("stage",0);
        String name = intent.getStringExtra("name");
        boolean sound = intent.getBooleanExtra("sound",true);
        int temp = intent.getIntExtra("temp",0);

        mesg = (TextView)findViewById(R.id.page2_mesg);
        mesg.setText("Name: " + name + "\n" +
                "Stage:" + stage + "\n" +
                "Sound: " + (sound?"On":"Off") + "\n" +
                "Temp: " + temp);

    }

    @Override
    public void finish() {
        Intent it = new Intent();
        it.putExtra("a",1);
        it.putExtra("b",2);
        setResult(99, it);//回給mainPage--onActivityResult,回報intent
        super.finish();
    }
}
