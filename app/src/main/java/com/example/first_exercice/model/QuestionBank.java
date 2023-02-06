package com.example.first_exercice.model;


import java.util.Collections;
import java.util.List;
public class QuestionBank {

    public QuestionBank(List<Question> mQuestionList, int mQuestionIndex) {
        this.mQuestionList = mQuestionList;
        this.mQuestionIndex = mQuestionIndex;
    }

    private List<Question> mQuestionList;
    private int mQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;

        Collections.shuffle(mQuestionList);
    }

    public Question getCurrentQuestion() {
        return mQuestionList.get(mQuestionIndex);
    }

    public Question getNextQuestion() {
        mQuestionIndex++;
        return getCurrentQuestion();
    }


}


