package bw.com.day13_dome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        TextView tv3 = (TextView) findViewById(R.id.tv3);
        Intent intent = getIntent();
        Bean.ArrayBean ab = (Bean.ArrayBean) intent.getSerializableExtra("ab");
        tv2.setText(ab.getTitle());
        tv3.setText(ab.getContent());
    }
}
