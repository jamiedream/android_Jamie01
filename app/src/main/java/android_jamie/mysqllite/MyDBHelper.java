package android_jamie.mysqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2016/9/21.
 */
public class MyDBHelper extends SQLiteOpenHelper{
    private String createtable =
            "CREATE TABLE cust (id INTEGER PRIMARY KEY AUTOINCREMENT, "+" cname TEXT, tel TEXT, birthday DATE)";
    public MyDBHelper(
            Context context,
            String name,SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
