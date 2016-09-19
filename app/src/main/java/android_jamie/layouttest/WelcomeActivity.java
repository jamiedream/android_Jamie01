package android_jamie.layouttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {
    private View img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        img = findViewById(R.id.welcome_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                domain();
            }
        });

    }

    private void domain() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);

    }
}
