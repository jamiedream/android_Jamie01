package android_jamie.listtest1;
// List View
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list);
        initList();

    }
    private void initList() {
        LinkedList<HashMap<String,String>> data = new LinkedList<>();
        HashMap<String,String> data0 = new HashMap<>();
        data0.put("title","Jamie");
        data0.put("content","Jamie...1");
        data.add(data0);
        HashMap<String,String> data1 = new HashMap<>();
        data1.put("title","Judy");
        data1.put("content","Jamie...2");
        data.add(data1);
        HashMap<String,String> data2 = new HashMap<>();
        data2.put("title","Jimmy");
        data2.put("content","Jamie...3");
        data.add(data2);


        String[] from = {"title", "content"};
        int[] to = {R.id.item_title, R.id.item_content};
        SimpleAdapter adapter =
                new SimpleAdapter(
                        this,data,
                        R.layout.layout_item,
                        from,to);
        list.setAdapter(adapter);
    }

}
