package abhishek.com.firebasesampleloginauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId=findViewById(R.id.userId);

        if(getIntent().getExtras().getString("user")!=null)
        {
            userId.setText("Welcome "+getIntent().getExtras().getString("user"));
        }


    }
}
