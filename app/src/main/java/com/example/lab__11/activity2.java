package com.example.lab__11;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;


public class activity2 extends AppCompatActivity {

    TextView name, city, contact, BloodGroup;
    EditText T1, T2, T3, T4;
    Button b;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    Datainfo datainfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        b = (Button) findViewById(R.id.button4);
        name = (TextView) findViewById(R.id.textView);
        city = (TextView) findViewById(R.id.city);
        contact = (TextView) findViewById(R.id.Contact);
        BloodGroup = (TextView) findViewById(R.id.Blood);
        T1 = (EditText) findViewById(R.id.editTextTextPersonName);
        T2 = (EditText) findViewById(R.id.CityWrite);
        T3 = (EditText) findViewById(R.id.ContactWrite);
        T4 = (EditText) findViewById(R.id.BloodWrite);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Datainfo");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, Object> m = new HashMap<String, Object>();
                m.put("Name", T1.getText().toString());
                m.put("City", T2.getText().toString());
                m.put("Contact", T3.getText().toString());
                m.put("blood group", T4.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("User").setValue(m);

                // getting text from our edittext fields.
                String Name = name.getText().toString();
                String ContactNumber = contact.getText().toString();
                String City = city.getText().toString();
                String blood = BloodGroup.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(Name) && TextUtils.isEmpty(ContactNumber) && TextUtils.isEmpty(City)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(activity2.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(Name, ContactNumber, City, blood);
                }
            }
        });
    }
        private void addDatatoFirebase(String name, String contactNumber, String City, String blood) {
                datainfo.setName(name);
                datainfo.setContactNumber(contactNumber);
                datainfo.setCity(City);
                datainfo.setBloodGroup(blood);

                final ValueEventListener data_added = databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        databaseReference.setValue(datainfo);

                        Toast.makeText(activity2.this, "data added", Toast.LENGTH_SHORT).show();
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // if the data is not added or it is cancelled then
                    // we are displaying a failure toast message.
                    Toast.makeText(activity2.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                }
            });
    }
}
