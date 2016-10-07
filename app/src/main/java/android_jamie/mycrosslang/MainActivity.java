package android_jamie.mycrosslang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private boolean isOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        isOn = true;
    }
    public void b1(View v){
        isOn = !isOn;
        button.setText(isOn?R.string.button_on:R.string.button_off);

    }
}
