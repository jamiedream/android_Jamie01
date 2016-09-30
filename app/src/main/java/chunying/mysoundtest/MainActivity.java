package chunying.mysoundtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private int soundMusic, soundMusic2;
    private MediaRecorder recorder;
    private File sdroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sdroot = Environment.getExternalStorageDirectory();

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        soundMusic = soundPool.load(this,R.raw.mozart1,1);
        soundMusic2 = soundPool.load(this,R.raw.guitar,1);


        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        1);
            }
        }

    }
    public void test1(View v){
        soundPool.play(soundMusic,0.5f,0.5f,1,0,1);
    }
    public void test2(View v){
        soundPool.play(soundMusic2,0.5f,0.5f,1,0,1);
    }
    public void test3(View v){
//        Intent it = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//        startActivityForResult(it,1);
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(sdroot.getAbsolutePath()+"/jamie.mp3");
        try {
            recorder.prepare();
            recorder.start();
            Log.d("jamie","start");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void test4(View v){
        recorder.stop();
        recorder.release();
//        recorder.stop();     // stop recording
//        recorder.reset();    // set state to idle
//        recorder.release();  // release resources back to the system
//        recorder = null;
        Log.d("jamie","stop");

    }
    public void test5(View v){
        MediaPlayer player = new MediaPlayer();

        try {
            player.setDataSource(sdroot.getAbsolutePath()+"/jamie.mp3");
//            player.release();
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){

        }

    }
}
