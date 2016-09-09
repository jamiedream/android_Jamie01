package jamie01.iii.org.tw.jamie1501;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button click;
    private TextView mesg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       click = (Button) findViewById(R.id.btnClick);
        mesg = (TextView)findViewById(R.id.mesg);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesg.setText("Lottery"+(int)(Math.random()*49+1));
            }
        });
    }
}
