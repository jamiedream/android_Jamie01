package android_jamie.mypainter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by user on 2016/9/13.
 */
public class MyView extends View {
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.MAGENTA);
//        setOnClickListener(new MyClickListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setStrokeWidth(4);
        canvas.drawLine(0,0,100,100,p);
    }
//    private class MyClickListener implements OnClickListener{
//        @Override
//        public void onClick(View view) {
//            Log.d("jamie","onClick");
//        }
//
//    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int ex = (int)event.getX(), ey =(int)event.getY();
        Log.d("jamie", ex+"x"+ey);
//        return super.onTouchEvent(event);         會觸發mainactivity的myView.setOnClickListener
        return true;//true會抓到touch的路徑
    }

//    @Override
//    public void setOnClickListener(OnClickListener l) {
//        super.setOnClickListener(l);
//    }
//    ==MyClickListener
}
