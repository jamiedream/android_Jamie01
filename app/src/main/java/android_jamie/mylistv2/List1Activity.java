package android_jamie.mylistv2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class List1Activity extends AppCompatActivity {
    private ListView list1;
    private MyAdapter adapter;
    private String[] item = {"item1","item2","item3","item4","item5",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);
        list1 = (ListView)findViewById(R.id.list1);
        adapter = new MyAdapter();
        list1.setAdapter(adapter);
    }
    private class MyAdapter extends BaseAdapter{
        private LayoutInflater inflater;

        MyAdapter(){
            inflater = LayoutInflater.from(List1Activity.this);
        }

        @Override
        public int getCount() {
            return item.length;
        }

        @Override
        public Object getItem(int position) {
            return item[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if(view==null){
               view = inflater.inflate(R.layout.layout, viewGroup,false);
            }
            TextView title = (TextView)view.findViewById(R.id.itemv1_title);
            if(position%2 ==0){
                view.setBackgroundColor(Color.GRAY);
            }else{
                view.setBackgroundColor(Color.RED);
            }
            title.setText(item[position]);
            return view;
        }
    }
}
