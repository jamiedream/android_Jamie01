package android_jamie.listtest1;
// List View
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private LinkedList<HashMap<String,Object>> data;
    private String[] from = {"title", "content", "img"};
    private int[] to = {R.id.item_title, R.id.item_content, R.id.item_img};
    private EditText inputTitle;
    private SimpleAdapter adapter;
    private int[] c = {R.drawable.coffee1, R.drawable.coffee2, R.drawable.coffee3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTitle = (EditText)findViewById(R.id.inputTitle);
        list = (ListView)findViewById(R.id.list);
        initList();

    }
    private void initList() {
        data = new LinkedList<>();

        adapter =new SimpleAdapter(
                        this,data,
                        R.layout.layout_item,
                        from,to);
        list.setAdapter(adapter);
    }
    public void addItem(View v){
        String input = inputTitle.getText().toString();
        HashMap<String, Object> dd = new HashMap<>();
        dd.put(from[0], input);
        dd.put(from[1], "data....");
        dd.put(from[2], c[(int)(Math.random()*3)]);
        data.add(dd);
        adapter.notifyDataSetChanged();
        //將change傳給adapter
    }
}
