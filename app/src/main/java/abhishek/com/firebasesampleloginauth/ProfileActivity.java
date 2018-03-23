package abhishek.com.firebasesampleloginauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abhishek.raj on 23-03-2018.
 */

public class ProfileActivity extends AppCompatActivity {


    Button submit;
    Button update;

    FirebaseDatabase firebaseDatabase;
    String tableNameUserProfile="User Profile";
    String userName="username";
    private RadioGroup sex;
    EditText name,age,height,weight;
    RadioButton male,female;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        submit=findViewById(R.id.btnSubmit);
        update=findViewById(R.id.deleteUpdate);


        sex =  findViewById(R.id.sex);

        male = findViewById(R.id.radio_btn_Male);
        female =  findViewById(R.id.radio_btn_Female);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        height =  findViewById(R.id.height);
        weight = findViewById(R.id.weight);

        firebaseDatabase=FirebaseDatabase.getInstance();

        if(getIntent().getExtras().getString("user")!=null)
        {
           userName=getIntent().getExtras().getString("user");
        }


        tableNameUserProfile= String.valueOf(System.currentTimeMillis());
        final DatabaseReference databaseReference =firebaseDatabase.getReference(tableNameUserProfile);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // databaseReference.child(tableNameUserProfile).setValue(name.getText().toString());

              //  databaseReference.child(tableNameUserProfile).setValue(age.getText().toString());
              //  databaseReference.child(tableNameUserProfile).setValue(height.getText().toString());
              //  databaseReference.child(tableNameUserProfile).setValue(weight.getText().toString());

                try{
                    insertProfileInCloud(userName,name.getText().toString(),age.getText().toString()
                            ,height.getText().toString(),weight.getText().toString(),databaseReference);
                }catch (Exception e){
                    e.printStackTrace();
                }

                finally {
                    startActivity(new Intent(ProfileActivity.this,MainActivity.class).
                            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }


            }
        });




        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    private void insertProfileInCloud(String userName,String name,String age,String height,String weight,DatabaseReference reference){
        Map<String,String> profileMap=new HashMap<>();
        profileMap.put("UserName",userName);
        profileMap.put("Name",name);
        profileMap.put("Age",age);
        profileMap.put("Height",height);
        profileMap.put("Weight",weight);


        reference.setValue(profileMap);

    }
}
