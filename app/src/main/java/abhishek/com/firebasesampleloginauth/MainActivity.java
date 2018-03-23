package abhishek.com.firebasesampleloginauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    TextView userId;
    Button signOut;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId=findViewById(R.id.userId);
        signOut=findViewById(R.id.signOut);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(MainActivity.this,"sign Out Successful",Toast.LENGTH_SHORT).show();
                    finishAffinity();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"Unable to SignOut",Toast.LENGTH_SHORT).show();
                   e.printStackTrace();
                }

            }
        });




    }
}
