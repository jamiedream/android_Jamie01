package android_jamie.mywebstorm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

public class MyCamera2Activity extends AppCompatActivity {
    private Camera camera;
    private FrameLayout frame;
    private CameraPreview preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera2);
        checkPermission(Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int num = Camera.getNumberOfCameras();
        Log.d("jamie", "camera:" + num);

        camera = Camera.open();
        Camera.Parameters para = camera.getParameters();
        List<Camera.Size> sizes = para.getSupportedPictureSizes();
        for (Camera.Size size :sizes){
            Log.d("jamie", size.width + "x" +size.height);
        }

        para.setPictureSize(1280,720);
        camera.setParameters(para);

        frame = (FrameLayout)findViewById(R.id.my_camera);
        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 camera.takePicture(new MyShutter(),null,new MyJpeg());
            }
        });
        preview = new CameraPreview(this,camera);
        frame.addView(preview);


//        camera.release();

    }
    private class MyShutter implements Camera.ShutterCallback{

        @Override
        public void onShutter() {
            Log.d("jamie","OnShutter");

        }
    }
    private class MyJpeg implements Camera.PictureCallback{

        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            Intent it = new Intent();
            it.putExtra("pic", bytes);
            setResult(1,it);
            finish();
        }
    }
    private int checkCameraNum(){
        return Camera.getNumberOfCameras();

    }
    private void checkPermission(String...permissions){
        for(String permission : permissions){
            if (ContextCompat.checkSelfPermission(this,
                    permission) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        new String[]{permission},1);
            }
        }
    }

}
