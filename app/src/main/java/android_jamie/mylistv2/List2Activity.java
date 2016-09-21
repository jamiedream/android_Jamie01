package android_jamie.mylistv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

public class List2Activity extends AppCompatActivity {
    private RecyclerView recycler;
    private String[] item = {"item1","item2","item3","item4","item5",};
    private LinkedList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        data = new LinkedList<>();
        for(String s:data){
            data.add(s);
        }
        recycler = (RecyclerView)findViewById(R.id.recyclerView);
    }
}
