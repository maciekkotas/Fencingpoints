package com.fencingapp.android.fencingpoints;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public Button setUp;


    public void setNames() {
        setUp = (Button) findViewById(R.id.set_up);
        setUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent settingUp = new Intent(MainActivity.this, Maintwo.class);

                EditText fencerOneName = (EditText) findViewById(R.id.fencer_one);
                String nameFencerOne = fencerOneName.getText().toString();
                settingUp.putExtra("firstName", nameFencerOne);

                EditText fencerTwoName = (EditText) findViewById(R.id.fencer_two);
                String nameFencerTwo = fencerTwoName.getText().toString();
                settingUp.putExtra("secondName",nameFencerTwo);

                EditText clubeOne = (EditText) findViewById(R.id.club_one);
                String nameClubOne = clubeOne.getText().toString();
                settingUp.putExtra("firstClub", nameClubOne);

                EditText clubTwo = (EditText) findViewById(R.id.club_two);
                String nameClubTwo = clubTwo.getText().toString();
                settingUp.putExtra("secondClub",nameClubTwo);

                startActivity(settingUp);
            }});}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       setNames();}


}

