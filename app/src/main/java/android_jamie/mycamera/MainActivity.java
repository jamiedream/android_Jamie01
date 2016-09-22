package android_jamie.mycamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private File sdroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView)findViewById(R.id.img);
        sdroot = Environment.getExternalStorageDirectory();

        //check permission
        boolean hasPermission =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED;
        //若沒有permission則request一個,設定值為10(自訂)
        if(!hasPermission){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);
        }
    }

    public void test1(View v){
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri output = Uri.fromFile(new File(sdroot, "jamie.jpg"));
        it.putExtra(MediaStore.EXTRA_OUTPUT, output);

        startActivityForResult(it,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
//            aftertakepic(data);
            aftertakepic2();
        }else if(resultCode == RESULT_CANCELED){

        }
    }
    private void aftertakepic(Intent data){
        Bitmap bmp = (Bitmap)data.getExtras().get("data");
        img.setImageBitmap(bmp);
    }
    private void aftertakepic2(){
        Bitmap bmp = BitmapFactory.decodeFile(sdroot.getAbsolutePath()+"/jamie.jpg");
        img.setImageBitmap(bmp);
    }
    public void test2(View v){}
}
