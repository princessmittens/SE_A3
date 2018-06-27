package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Allows user to edit or delete the entry
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, address, busType, province, busNum;
    Contact receivedPersonInfo;
    String id;
    Button updateButton, deleteButton;
    MyApplicationData appData = (MyApplicationData) getApplication();

    /**
     * Get bundles and calls methods for updating and deleting data
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appData = ((MyApplicationData) getApplicationContext());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact) getIntent().getSerializableExtra("Contact");

        updateButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        nameField = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        busNum = (EditText) findViewById(R.id.num);
        busType = (EditText) findViewById(R.id.bustype);
        province = (EditText) findViewById(R.id.province);
        emailField = (EditText) findViewById(R.id.email);

        if (receivedPersonInfo != null) {
            nameField.setText(receivedPersonInfo.name);
            address.setText(receivedPersonInfo.address);
            busNum.setText(receivedPersonInfo.number);
            busType.setText(receivedPersonInfo.primaryBus);
            province.setText(receivedPersonInfo.province);
            emailField.setText(receivedPersonInfo.email);
            id = receivedPersonInfo.uid;
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            eraseContact(v);
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateContact(v);
            }
        });
    }

    /**
     * updates the Contact and passes it Firebase and navigates back to MainActivity.class
     * @param v - view passed in
     */
        public void updateContact(View v) {

        receivedPersonInfo.name = nameField.getText().toString();
        receivedPersonInfo.address  = address.getText().toString();
        receivedPersonInfo.number = busNum.getText().toString();
        receivedPersonInfo.province = province.getText().toString();
        receivedPersonInfo.primaryBus = busType.getText().toString();
        receivedPersonInfo.email = emailField.getText().toString();
        appData.firebaseReference.child(receivedPersonInfo.uid).setValue(receivedPersonInfo);
            Toast.makeText(getApplicationContext(),"Successfully updated!",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
    }

    /**
     * Erases contact in Firebase and navigates back to MainActivity.java
     * @param v - view passed in
     */
    public void eraseContact(View v)
    {
        appData.firebaseReference.child(id).setValue(null);
        Toast.makeText(getApplicationContext(),"Successfully deleted!",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
