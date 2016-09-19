package android_jamie.inputoutput;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private TextView info;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private File sdroot, app1root, app2root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView)findViewById(R.id.testInfo);
        sp = getSharedPreferences("gameTest", MODE_PRIVATE);
        editor = sp.edit();

        String state = Environment.getExternalStorageState();
        Log.d("jamie",state);
        if(isExternalStorageWritable()){
            Log.d("jamie","w");
        }
        if(isExternalStorageReadable()){
            Log.d("jamie","r");
        }
        sdroot = Environment.getExternalStorageDirectory();
//        Log.d("jamie",sdroot.getAbsolutePath());
        app1root = new File(sdroot,"jamie");
        app2root = new File(sdroot,"Android/data/"+getPackageName());
        if(!app1root.exists()){
            if(app1root.mkdirs()){
                Log.d("jamie","Create dir1 OK");
            }else{
                Log.d("jamie","Create dir1 Fail");
            }
        }else{
            Log.d("jamie","app1root exist");
        }

        if(!app2root.exists()){
            if(app2root.mkdirs()){
                Log.d("jamie","Create dir2 OK");
            }else{
                Log.d("jamie","Create dir2 Fail");
            }
        }else{
            Log.d("jamie","app2root exist");
        }
    }
    public void editor(View v){
        editor.putString("user","jamie");
        editor.putInt("stage",5);
        editor.putBoolean("sound",true);
        editor.commit();
        Toast.makeText(this,"save ok",Toast.LENGTH_SHORT).show();
    }
    public void sp(View v){
        int stage = sp.getInt("stage",0);
        String user = sp.getString("user","XXX");
        info.setText(stage+":"+user);
    }
    public void fout(View v){
        try {
            FileOutputStream fout = openFileOutput("file.txt", MODE_PRIVATE);
            fout.write("Hello\nWorld".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"Save test3 OK",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"Exception:"+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    public void fis(View v){
        info.setText("");
        try {
            FileInputStream fis = openFileInput("file.txt");
            int c;
            while((c =fis.read()) != -1){
                info.append(""+(char)c);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void app1(View v){
        File file = new File(app1root,"file1.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file);
            fout.write("app1".getBytes());
            fout.flush();
            fout.close();
        }catch (Exception e){
            Log.d("jamie","Exception:"+e.toString());
        }
    }
    public void app2(View v){
        File file = new File(app2root,"file2.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file);
            fout.write("app2".getBytes());
            fout.flush();
            fout.close();
        }catch (Exception e){
            Log.d("jamie","Exception:"+e.toString());
        }
    }
    public void app1r(View v){
        info.setText("");
        File filer = new File(app1root,"file1.txt");
        try {
            FileInputStream fis = new FileInputStream(filer);
            int c;
            while((c = fis.read()) != -1){
                info.append(""+(char)c);
            }
            fis.close();
        } catch (Exception e) {
            Log.d("jamie","Exception:"+e.toString());
        }
    }
    public void app2r(View v){
        info.setText("");
        File filer = new File(app2root,"file2.txt");
        try {
            FileInputStream fis = new FileInputStream(filer);
            int c;
            while((c = fis.read()) != -1){
                info.append(""+(char)c);
            }
            fis.close();
        } catch (Exception e) {
            Log.d("jamie","Exception:"+e.toString());
        }

    }
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
