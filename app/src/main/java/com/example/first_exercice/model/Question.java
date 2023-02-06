package com.example.first_exercice.model;

import java.util.List;

public class Question {


    private String mQuestion;
    private final List<String> mChoiceList;
    private int mAnswerIndex;

    public Question() {
        mChoiceList = null;
    }

    public Question(String mQuestion, List<String> mChoiceList, int mAnswerIndex) {
        this.mQuestion = mQuestion;
        this.mChoiceList = mChoiceList;
        this.mAnswerIndex = mAnswerIndex;
    }

    public int getmAnswerIndex() {
        return mAnswerIndex;
    }

    public void setmAnswerIndex(int mAnswerIndex) {
        this.mAnswerIndex = mAnswerIndex;
    }

    public List<String> getmChoiceList() {
        return mChoiceList;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }
}
