package com.londonappbrewery.destini;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.londonappbrewery.destini.R.string.T1_Ans1;
import static com.londonappbrewery.destini.R.string.T1_Ans2;
import static com.londonappbrewery.destini.R.string.T1_Story;
import static com.londonappbrewery.destini.R.string.T2_Ans1;
import static com.londonappbrewery.destini.R.string.T2_Ans2;
import static com.londonappbrewery.destini.R.string.T2_Story;
import static com.londonappbrewery.destini.R.string.T3_Ans1;
import static com.londonappbrewery.destini.R.string.T3_Ans2;
import static com.londonappbrewery.destini.R.string.T3_Story;
import static com.londonappbrewery.destini.R.string.T4_End;
import static com.londonappbrewery.destini.R.string.T5_End;
import static com.londonappbrewery.destini.R.string.T6_End;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    Button topButton;
    Button bottomButton;
    TextView storyTextView;
    int  storyText;
    int  topButtonText;
    int  bottomButtonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Restore Application Status (Story and Buttons Texts)
        if (savedInstanceState !=null) {
            storyText=savedInstanceState.getInt("StoryKey");
            topButtonText=savedInstanceState.getInt("TopButtonKey");
            bottomButtonText=savedInstanceState.getInt("BottomButtonKey");


        } else {
            storyText=T1_Story;
            topButtonText=T1_Ans1;
            bottomButtonText=T1_Ans2;

        }
        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        topButton=(Button)findViewById(R.id.buttonTop);
        bottomButton= (Button) findViewById(R.id.buttonBottom);
        storyTextView=(TextView) findViewById(R.id.storyTextView);
        updateStory();

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        topButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((storyText==T1_Story) ||  (storyText==T2_Story)) {
                    storyText=T3_Story;
                    topButtonText=T3_Ans1;
                    bottomButtonText=T3_Ans2;

                } else {
                     storyText=T6_End;
                     closeStory();
                    }
                updateStory();
                // update to next question
                // updatequestion();
            }
        });
        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        bottomButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storyText==T1_Story) {
                    storyText=T2_Story;
                    topButtonText=T2_Ans1;
                    bottomButtonText=T2_Ans2;

                } else if (storyText==T2_Story){
                    storyText=T4_End;
                    closeStory();
                } else {
                    storyText=T5_End;
                    closeStory();
                }
                updateStory();
                // update to next question
                // updatequestion();
            }
        });

    }
    // update display (Story and bottom texts)
    private void updateStory (){
        storyTextView.setText(storyText);
        topButton.setText(topButtonText);
        bottomButton.setText(bottomButtonText);
    }
    // Finish adventure and close application
    private void closeStory (){
        // Hide buttons
        topButton.setVisibility(View.GONE);
        bottomButton.setVisibility(View.GONE);
        // delay(10000);
        // Display Closing Dialogue
        AlertDialog.Builder malert = new AlertDialog.Builder(this);
        malert.setTitle("End of Adventure");
        malert.setCancelable(false);
        malert.setMessage("You Reached End of Adventure");
        malert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        malert.show();
    }
    // Try to delay before displaying closing dialog
//    private void delay(long mS){
//        new CountDownTimer(mS, 1000) {
//            int x=0;
//            public void onTick(long millisUntilFinished) {
//                x+=1;
//
//
//            }
//
//            public void onFinish() {
//                x=0;
//
//            }
//        }.start();
//    }
    // Store Application Status (Story and Buttons Texts)
    @Override
    public void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("StoryKey",storyText);
        outState.putInt("TopButtonKey",topButtonText);
        outState.putInt("BottomButtonKey",bottomButtonText);

    }

}
