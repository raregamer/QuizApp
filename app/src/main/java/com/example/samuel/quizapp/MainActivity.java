package com.example.samuel.quizapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    //Previous Score keeper.
    private int previousScore = 0;
    // make question objects
    private Questions question1 = new Questions();
    private Questions question2 = new Questions();
    private Questions question3 = new Questions();

    private RadioGroup rGroup1;
    private RadioGroup rGroup2;
    private RadioGroup rGroup3;

    private EditText elvis;

    private EditText name;

    private CheckBox checkBoxTrue1;
    private CheckBox checkBoxFalse1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rGroup1 = (RadioGroup) findViewById(R.id.first_question_group);
        rGroup2 = (RadioGroup) findViewById(R.id.second_question_group);
        rGroup3 = (RadioGroup) findViewById(R.id.third_question_group);
        //allows answers to be changed and not effect the score
        rGroup1.setOnCheckedChangeListener(this);
        rGroup2.setOnCheckedChangeListener(this);
        rGroup3.setOnCheckedChangeListener(this);

        elvis = (EditText) findViewById(R.id.angram_question);
        name = (EditText) findViewById(R.id.player_name);

        checkBoxTrue1 = (CheckBox) findViewById(R.id.checkbox_true);
        checkBoxFalse1 = (CheckBox) findViewById(R.id.checkbox_false);






    }





    // Test if the user pressed the correct answer for RadioGroups.
    public void onCheckedChanged(RadioGroup radioGroup, int id) {


        switch (radioGroup.getId()) {

            case R.id.first_question_group:
                if (id == R.id.correct_answer1) {
                    question1.setQuestion(true);
                }else {
                    question1.setQuestion(false);
                }
                break;
            case R.id.second_question_group:
                if (radioGroup.getCheckedRadioButtonId() == R.id.correct_answer2) {
                    question2.setQuestion(true);
                }else {
                    question2.setQuestion(false);
                }
                break;
            case R.id.third_question_group:
                if (radioGroup.getCheckedRadioButtonId() == R.id.correct_answer3) {
                    question3.setQuestion(true);
                } else {
                    question3.setQuestion(false);
                }
                break;
        }


    }


    //used to diplay the users' score on screen.
    public void displayScore() {
        //get users name.


        Context context = getApplicationContext();
        CharSequence text = name.getText() + " got " + (Questions.correct) + "/5 correct.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        TextView previousScore = (TextView) findViewById(R.id.score_keeper);
        previousScore.setText(Integer.toString(Questions.correct));




    }
//checkbox results
    private void checkBoxResults() {


        if(checkBoxTrue1.isChecked() && !checkBoxFalse1.isChecked()){
            Questions.correct++;
        } else {
            Questions.wrong++;
        }
    }

    private void editTextResults(){
        if(elvis.getText().toString().trim().equalsIgnoreCase("lives")) {
            Questions.correct++;

        } else {
            Questions.wrong++;
        }

    }


    //used to submit tally results when button is pushed.
    public void submitResults(View view) {
        question1.questionAnswerTest();
        question2.questionAnswerTest();
        question3.questionAnswerTest();
        checkBoxResults();
        editTextResults();
        displayScore();
        clearQuiz();
    }


//clear quiz
    public void clearQuiz(){
        name.setText("");
        previousScore = 0;
        Questions.correct = 0;
        Questions.wrong = 0;
        rGroup1.clearCheck();
        rGroup2.clearCheck();
        rGroup3.clearCheck();
        elvis.setText("");
        checkBoxTrue1.setChecked(false);
        checkBoxFalse1.setChecked(false);



    }


    public void emailScore(View view) {
        question1.questionAnswerTest();
        question2.questionAnswerTest();
        question3.questionAnswerTest();
        checkBoxResults();
        editTextResults();
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_SUBJECT, name.getText() + " Quiz Score");
        email.putExtra(Intent.EXTRA_TEXT, name.getText() + " got " + (Questions.correct) + "/5 correct.");

        try{
            startActivity(email);
            finish();
        } catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this,"there is no email client", Toast.LENGTH_SHORT).show();
        }

    }
}
