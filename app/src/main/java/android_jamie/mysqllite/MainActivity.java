package android_jamie.mysqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;
    private TextView mesg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDBHelper(this,"jamie",null,1);
        db = dbHelper.getReadableDatabase();
        mesg = (TextView)findViewById(R.id.mesg);

    }
    public void insert(View v){
        //insert into cust values(cname,tel,birthday) values ("cname","tel","birthday");
        ContentValues data = new ContentValues();
        data.put("cname", "haha");
        data.put("tel", "123123");
        data.put("birthday","1999-04-02");
        db.insert("cust",null,data);
        query(null);


    }
    public void delete(View v){
        //delete from cust  where id=? and cname=?;
        db.delete("cust", "id=? and cname = ?", new String[]{"1","brad"});
        query(null);

    }
    public void update(View v){
        //update cust set cname = yoyo, tel=0000 where id = 5;
        ContentValues data = new ContentValues();
        data.put("cname", "yoyo");
        data.put("tel", "0000");
        db.update("cust",data,"id = ?",new String[]{"5"});
        query(null);

    }
    public void query(View v){
            mesg.setText("");
            // SELECT * FROM cust
//        Cursor c = db.query("cust",null,null,null,null,null,null);

            // SELECT * FROM cust ORDER BY cname
            //Cursor c = db.query("cust",null,null,null,null,null,"cname,tel");

            // SELECT * FROM cust WHERE birthday > '1999-02-01' ORDER BY cname
//        Cursor c = db.query("cust",null,"birthday > ?",new String[]{"1999-02-01"},
//                null,null,"cname,tel");

            // SELECT cname, tel, birthday FROM cust WHERE birthday > '1999-02-01' ORDER BY cname
            Cursor c = db.query("cust",new String[]{"cname","tel","birthday"},
                    "birthday > ?",new String[]{"1999-01-01"},
                    null,null,"cname,tel");
        while(c.moveToNext()){
//            String id = c.getString( c.getColumnIndex("id"));
            String cname = c.getString( c.getColumnIndex("cname"));
            String tel = c.getString( c.getColumnIndex("tel"));
            String birthday = c.getString( c.getColumnIndex("birthday"));
//            mesg.append(id+":"+cname+":"+tel+":"+birthday+"\n");
            mesg.append(cname+":"+tel+":"+birthday+"\n");
        }

    }
}
