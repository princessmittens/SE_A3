package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Allows the user to create a new entry
 */
public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField, address, busNum, busType, province;
    private MyApplicationData appState;

    /**
     *Gets text fields for editing
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        busNum = (EditText) findViewById(R.id.num);
        busType = (EditText) findViewById(R.id.bustype);
        province = (EditText) findViewById(R.id.province);
        emailField = (EditText) findViewById(R.id.email);
    }

    /**
     * Creates new entry in Firebase and navigates back to MainActivity
     * @param v - view passed in
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String num = busNum.getText().toString();
        String primaryBus = busType.getText().toString();
        String add = address.getText().toString();
        String prov = province.getText().toString();
        String email = emailField.getText().toString();
        Contact person = new Contact(personID, name, num, primaryBus, add, prov, email);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
