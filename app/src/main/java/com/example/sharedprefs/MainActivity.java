package com.example.sharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    EditText nameView;
    EditText passwordView;
    String nameString, passwordString;
    CheckBox checkBox;
    boolean checkBoxState;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameView = findViewById(R.id.name);
        passwordView = findViewById(R.id.password);
        checkBox = findViewById(R.id.checkbox);

        sharedPreferences = getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        if (sharedPreferences.contains("name")) {
            nameString = sharedPreferences.getString("name", "default");
            nameView.setText(nameString);
        }
        if (sharedPreferences.contains("password")) {
            passwordString = sharedPreferences.getString("password", "default");
            passwordView.setText(passwordString);
        }
        if (sharedPreferences.contains("checkBox")) {
            checkBoxState = sharedPreferences.getBoolean("checkBox", false);
            checkBox.setChecked(checkBoxState);
        }

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()) {
                    nameString = nameView.getText().toString();
                    passwordString = passwordView.getText().toString();
                    sharedPreferencesEditor = sharedPreferences.edit();
                    sharedPreferencesEditor.putString("name", nameString);
                    sharedPreferencesEditor.putString("password", passwordString);
                    sharedPreferencesEditor.putBoolean("checkBox", true);
                    sharedPreferencesEditor.commit();
                } else {
                    sharedPreferencesEditor = sharedPreferences.edit();
                    sharedPreferencesEditor.clear();
                    sharedPreferencesEditor.commit();
                }

            }
        });

    }

}
