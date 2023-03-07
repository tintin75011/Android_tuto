package com.example.first_exercice.Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.first_exercice.R;
import com.example.first_exercice.model.User;

public class MainActivity extends AppCompatActivity {
    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;
    private int score;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO"; // data that represent the saving_file's name
    private static final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME"; // key to save the Name of the user
    private static final String SHARED_PREF_USER_INFO_SCORE = "SHARED_PREF_USER_INFO_SCORE"; // key to save the score of the user
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
           score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGreetingTextView = findViewById(R.id.main_WelcomeText);
        mNameEditText = findViewById(R.id.main_EditArea);
        mPlayButton = findViewById(R.id.main_ButtonValidate);
        mPlayButton.setEnabled(false);

        String firstName = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);
        String lastScore = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_SCORE, null);

        if(lastScore != null){
            mGreetingTextView.setText("welcome back"+firstName +" your last score is :"+lastScore+"will you do better this time??");
            mNameEditText.setText(firstName);
            mPlayButton.setEnabled(true);
        }

        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPlayButton.setEnabled(!s.toString().isEmpty());
            }
        });
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
                // updating printing


                // saving to XML file
                SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SHARED_PREF_USER_INFO_NAME, mNameEditText.getText().toString());
                editor.putString(SHARED_PREF_USER_INFO_SCORE, String.valueOf(score));
                editor.apply();

            }
        });

    }
}