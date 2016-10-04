package android_jamie.myfragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class F1Fragment extends Fragment {
    private TextView tv;
    private MainActivity main;
    private Button b1;

    public F1Fragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f1, container, false);
        tv = (TextView)view.findViewById(R.id.f1_tv);
        tv.setText("Hello F1");
        main = (MainActivity) getActivity();
        b1 = (Button) view.findViewById(R.id.f1_b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main.getF2();
            }
        });

        return view;
    }

}
