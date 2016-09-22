package android_jamie.myflippertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper vf;
    private Animation animInLeftRight, animInRightLeft, animOutLeftRight, animOutRightLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animInLeftRight = AnimationUtils.loadAnimation(this,R.anim.inleftright);
        animOutLeftRight = AnimationUtils.loadAnimation(this,R.anim.outleftright);
        animInRightLeft = AnimationUtils.loadAnimation(this,R.anim.inrightleft);
        animOutRightLeft = AnimationUtils.loadAnimation(this,R.anim.outrightleft);

        vf = (ViewFlipper)findViewById(R.id.am);
        vf.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                vf.setInAnimation(animInLeftRight);
                vf.setOutAnimation(animOutLeftRight);
                vf.showNext();
//                vf.setInAnimation(animInRightLeft);
//                vf.setOutAnimation(animOutRightLeft);
//                vf.showPrevious();
                return false;
            }
        });

    }

}
