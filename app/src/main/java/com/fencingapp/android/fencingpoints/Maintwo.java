package com.fencingapp.android.fencingpoints;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Random;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.fencingapp.android.fencingpoints.R.id.toast;

public class Maintwo extends AppCompatActivity {

    int scoreOne = 0;
    int scoreTwo = 0;
    long total = 180000;
    private CountDownTimer timer;
    private TextView time;
    private TextView startButton, pause;
    private TextView scoreViewOne, scoreViewTwo;
    //private Typeface face;
    private View toastLayout, toastLayoutTwo;
    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scorePicker();
        }
    };


    /* TODO: TOAST font
       TODO: layout design
       TODO: photos
       TODO: Buttons
       TODO: size
       TODO: text

*/
    private Vibrator vibrator;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        LayoutInflater inflater = getLayoutInflater();
        toastLayout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_view));
        LayoutInflater inf = getLayoutInflater();
        toastLayoutTwo = inf.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_view_two));




        displayTime(total);



        timer = new CountDownTimer(0, 5) {
            @Override
            public void onTick(long millisUntilFinished) {
                displayTime(millisUntilFinished);
            }

            @Override
            public void onFinish() {

            }
        };

        time = (TextView) findViewById(R.id.time);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        final ImageView yellowOneView = (ImageView) findViewById(R.id.yellow_one);
        final ImageView yellowTwoView = (ImageView) findViewById(R.id.yellow_two);

        scoreViewTwo = (TextView) findViewById(R.id.point_two);

        scoreViewOne = (TextView) findViewById(R.id.point_one);

        startButton = (TextView) findViewById(R.id.start_timer);

        pause = (TextView) findViewById(R.id.pause_timer);

        TextView plusOneMin = (TextView) findViewById(R.id.plus_one_min);
        TextView plusThreeMin = (TextView) findViewById(R.id.plus_three_min);
        Button priorityBtn = (Button) findViewById(R.id.priority);
        Button resetButton = (Button) findViewById(R.id.reset);
        Button addForOneButton = (Button) findViewById(R.id.point_for_one);
        Button addForTwoButton = (Button) findViewById(R.id.point_for_two);
        final ImageView yellowForOneButton = (ImageView) findViewById(R.id.yellow_for_one);
        ImageView redForOneButton = (ImageView) findViewById(R.id.red_for_one);
        final ImageView yellowForTwoButton = (ImageView) findViewById(R.id.yellow_for_two);
        ImageView redForTwoButton = (ImageView) findViewById(R.id.red_for_two);

        scoreViewOne.setOnClickListener(onClick);
        scoreViewTwo.setOnClickListener(onClick);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                pause.setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);
                timePicker();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCountDownTimer();
                pause.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.GONE);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                pause.setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);

            }
        });

        plusOneMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 60000;
                displayTime(total);
            }
        });

        plusThreeMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 180000;
                displayTime(total);

            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yellowOneView.setVisibility(View.GONE);
                yellowTwoView.setVisibility(View.GONE);
                scoreOne = 0;
                scoreTwo = 0;
                displayPoints(scoreTwo, scoreViewTwo, "two");
                displayPoints(scoreOne, scoreViewOne, "one");
                total = 0;
                displayTime(total);

            }
        });
        addForOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreOne = (scoreOne + 1);
                displayPoints(scoreOne, scoreViewOne, "one");
                vibrator.vibrate(100);
            }
        });

        addForTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTwo = (scoreTwo + 1);
                displayPoints(scoreTwo, scoreViewTwo, "two");
                vibrator.vibrate(100);

            }
        });

        yellowForOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yellowOneView.getVisibility() == View.VISIBLE) {
                    yellowOneView.setVisibility(View.GONE);
                } else {
                    timer.cancel();
                    pause.setVisibility(View.GONE);
                    startButton.setVisibility(View.VISIBLE);
                    yellowOneView.setVisibility(View.VISIBLE);
                }
            }
        });

        yellowForTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yellowTwoView.getVisibility() == View.VISIBLE) {
                    yellowTwoView.setVisibility(View.GONE);
                } else {
                    timer.cancel();
                    pause.setVisibility(View.GONE);
                    startButton.setVisibility(View.VISIBLE);
                    yellowTwoView.setVisibility(View.VISIBLE);
                }
            }
        });

        redForOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreTwo++;
                displayPoints(scoreTwo, scoreViewTwo, "two");
            }
        });

        redForTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreOne++;
                displayPoints(scoreOne, scoreViewOne, "one");

            }
        });

        priorityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int ans = r.nextInt(2);
                int a[] = new int[];
        int b = a.length;

                Toast.makeText(Maintwo.this, String.valueOf(ans), Toast.LENGTH_LONG);

                if (ans == 1) {
                    scoreViewOne.setText("Priority");
                } else {
                    scoreViewTwo.setText("Priority");
                }
            }
        });
    }

    public void displayPoints(int score, TextView textView, String fencer) {
        timer.cancel();
        pause.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);
        textView.setText(String.valueOf(score));
        if (score == 15) {
            TextView toastTxt = (TextView) findViewById(R.id.toast);
            toastTxt.setText(fencer);

            //Toast.makeText(getApplicationContext(), "Fencer " + fencer + " Wins!", Toast.LENGTH_LONG).show();
            Toast toast = new Toast(getApplicationContext());
            toast.setView(toastLayout);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0 );
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private void startCountDownTimer() {
        startButton.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);
        timer = new CountDownTimer(total, 5) {

            @Override
            public void onTick(long millisUntilFinished) {
                total = millisUntilFinished;
                displayTime(millisUntilFinished);

            }

            @Override
            public void onFinish() {
                vibrator.vibrate(1000);
                time.setText("done!");
                pause.setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);

            }

        };
        timer.start();
    }

    private void displayTime(long t) {
        time = (TextView) findViewById(R.id.time);
        int s = (int) (t / 1000) % 60;
        int m = (int) ((t / 1000) / 60) % 60;
        time.setText(String.format("%02d:%02d", m, s));
    }




    private void timePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Context context = Maintwo.this;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        final EditText min = new EditText(this);
        min.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_TIME);
        min.setHint("min");
        min.setSelectAllOnFocus(true);
        layout.addView(min);
        TextView text = new TextView(this);
        text.setText(" : ");
        layout.addView(text);


        final EditText sec = new EditText(this);
        sec.setSelectAllOnFocus(true);
        sec.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_TIME);
        sec.setHint("sec");
        layout.addView(sec);
        builder.setMessage("Time");
        builder.setView(layout);
        min.setText(String.valueOf(total / 1000 / 60 % 60));
        sec.setText(String.valueOf(total / 1000 % 60));


        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int m = Integer.parseInt(min.getText().toString());
                int s = Integer.parseInt(sec.getText().toString());
                total = (m * 60 * 1000) + (s * 1000);
                displayTime(total);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void scorePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Maintwo.this);
        Context context = Maintwo.this;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        final EditText pointForOne = new EditText(Maintwo.this);
        pointForOne.setSelectAllOnFocus(true);
        pointForOne.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(pointForOne);
        TextView text = new TextView(Maintwo.this);
        text.setText(" : ");
        layout.addView(text);

        final EditText pointForTwo = new EditText(Maintwo.this);
        pointForTwo.setInputType(InputType.TYPE_CLASS_NUMBER);
        pointForTwo.setSelectAllOnFocus(true);
        layout.addView(pointForTwo);
        pointForOne.setText(scoreViewOne.getText().toString());
        pointForTwo.setText(scoreViewTwo.getText().toString());

        builder.setMessage("Points");
        builder.setView(layout);
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                scoreOne = Integer.parseInt(pointForOne.getText().toString());
                scoreTwo = Integer.parseInt(pointForTwo.getText().toString());
                displayPoints(scoreOne, scoreViewOne, "one");
                displayPoints(scoreTwo, scoreViewTwo, "two");

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        displayPoints(scoreOne, scoreViewOne, "one");
        displayPoints(scoreTwo, scoreViewTwo, "two");
        alertDialog.show();

    }
}
