package android_jamie.mywebstorm;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by user on 2016/10/4.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
    private Camera mCamera;
    private SurfaceHolder holder;

    public CameraPreview(Context context, Camera camera) {
        super(context);

        mCamera = camera;
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if(holder.getSurface()==null)return;

        try {
            mCamera.stopPreview();
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            mCamera.release();
            mCamera=null;
    }
}
