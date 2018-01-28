package com.example.samuel.quizapp;

/**
 * Created by Samuel on 1/26/2018.
 */

public class Questions {

    //keep track of wrong and right answers
    public static int correct = 0;
    public static int wrong = 0;

    //boolean to know if the user got the answer right.
    private boolean question = false;

    //set the question results
    public void setQuestion(boolean testResults) {
        this.question = testResults;
    }

    public boolean getQuestion(){
        return this.question;
    }



    //submit results calls this to find out if the answer was correct for questions to add the tally total.
    public void questionAnswerTest(){
        if(question) {
            correct++;
        } else {
            wrong++;
        }


    }


}

