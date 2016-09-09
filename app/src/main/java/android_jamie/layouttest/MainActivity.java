package android_jamie.layouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private View guess;
    //在view內的物件都可被點選,button==view的一部分
    private EditText input;
    private TextView textView;
    private  String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer = getAnswer(3);
        input = (EditText) findViewById(R.id.input);

        textView = (TextView) findViewById(R.id.textView);

        guess = findViewById(R.id.guess);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doGuess();
            }
        });

    }
    protected void doGuess(){
        String strInput = input.getText().toString();
        String result = getResult(answer, strInput);
        textView.append(strInput+":"+result+"\n");
        input.setText("");
        Log.i("jamie", result);

    }
    static String getAnswer(int n){
        int[] poker = new int[n];
        for(int i=0; i<poker.length; i++){
            int temp;
            //check
            boolean isRepeat;
            do{
                temp = (int)(Math.random()*10);
                isRepeat = false;
                for(int j=0; j<i; j++){
                    if( temp == poker[j]){
                        isRepeat = true;
                        break;
                    }
                }
            }while(isRepeat);
            poker[i] = temp;
        }
        String ret = "";
        for(int p:poker)ret += p;
        return ret;

//		// poker[0] poker[1] poker[2] poker[3]
//		return "" + poker[0] + poker[1] + poker[2] + poker[3];

    }
    static String getResult(String a, String g){
        int A, B;
        A = B = 0;
        for(int i=0; i<g.length(); i++){
            if(g.charAt(i) == a.charAt(i)){
                A++;
            }else if(a.indexOf(g.charAt(i)) != -1){
                B++;
            }

        }
        return A + "A" + B + "B";
    }

}
