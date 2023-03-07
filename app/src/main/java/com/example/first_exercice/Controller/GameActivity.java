package com.example.first_exercice.Controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.first_exercice.R;
import com.example.first_exercice.model.Question;
import com.example.first_exercice.model.QuestionBank;

import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextQuestion;
    private Button mButtonAnswer1;
    private Button mButtonAnswer2;
    private Button mButtonAnswer3;
    private Button mButtonAnswer4;
    private int mScore;

    private int mRemainingQuestionCount;
    private Question mCurrentQuestion;
    private final QuestionBank mQuestionBank = GenerateQuestionBank();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        mTextQuestion = findViewById(R.id.game_activity_textview_question);
        mButtonAnswer1 = findViewById(R.id.game_activity_button_1);
        mButtonAnswer2 = findViewById(R.id.game_activity_button_2);
        mButtonAnswer3 = findViewById(R.id.game_activity_button_3);
        mButtonAnswer4 = findViewById(R.id.game_activity_button_4);

        mButtonAnswer1.setOnClickListener(this);
        mButtonAnswer2.setOnClickListener(this);
        mButtonAnswer3.setOnClickListener(this);
        mButtonAnswer4.setOnClickListener(this);

        mScore = 0;
        mRemainingQuestionCount = 3;
        mCurrentQuestion = mQuestionBank.getCurrentQuestion();
        displayQuestion(mQuestionBank.getCurrentQuestion());
    }
    private QuestionBank GenerateQuestionBank() {
        Question question1 = new Question(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        Question question2 = new Question(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );

        Question question3 = new Question(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );

        return new QuestionBank(Arrays.asList(question1, question2, question3));
    }

    private void displayQuestion(final Question question) {
// Set the text for the question text view and the four buttons
        List<String> questionList;

        questionList = question.getmChoiceList();
        mTextQuestion.setText(question.getmQuestion());
        mButtonAnswer1.setText(questionList.get(0));
        mButtonAnswer2.setText(questionList.get(1));
        mButtonAnswer3.setText(questionList.get(2));
        mButtonAnswer4.setText(questionList.get(3));

    }

    @Override
    public void onClick(View view) {
        int index;

        if (view == mButtonAnswer1) {
            index = 0;
        } else if (view == mButtonAnswer2) {
            index = 1;
        } else if (view == mButtonAnswer3) {
            index = 2;
        } else if (view == mButtonAnswer4) {
            index = 3;
        } else {
            throw new IllegalStateException("Unknown clicked view : " + view );
        }
        if(index == mQuestionBank.getCurrentQuestion().getmAnswerIndex()){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        }else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        mRemainingQuestionCount--;

        if (mRemainingQuestionCount > 0) {
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        } else {
            // No question left, end the game
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Well done!")
                    .setMessage("Your score is " + mScore)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create()
                    .show();

        }

    }

    public int getmRemainingQuestionCount() {
        return mRemainingQuestionCount;
    }

    public void setmRemainingQuestionCount(int mRemainingQuestionCount) {
        this.mRemainingQuestionCount = mRemainingQuestionCount;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }
}