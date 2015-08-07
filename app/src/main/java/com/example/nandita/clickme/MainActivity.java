package com.example.nandita.clickme;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.SystemClock.sleep;
import static com.example.nandita.clickme.R.drawable.money;


public class MainActivity extends Activity implements View.OnClickListener {

    private Handler h = new Handler();
    Timer timer = new Timer();
    static int prevPos=0;
    int buttonIds[] = new int[]{R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,
            R.id.button6,R.id.button7,R.id.button8,R.id.button9,R.id.button10,R.id.button11};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int i;
        Button btn;
        Log.i("ONCLICK","oncreate");

        for (i=0;i<buttonIds.length;i++){
           btn = (Button)findViewById(buttonIds[i]);
           btn.setOnClickListener(this);
            Log.i("ONCLICK","Set onclick listenedr is null");

        }
        flashColorsOnButtons();

    }

    public void flashColorsOnButtons(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               Log.i("flashColoronButtons", "in the run block");
               h.post(myRunnable);
            }

        }, 0, 1000);

    }

    final Runnable myRunnable = new Runnable() {

        int colors[] = new int[] {Color.RED,Color.BLUE,Color.BLACK,Color.GREEN,Color.GRAY,
                                    Color.YELLOW,Color.CYAN,Color.MAGENTA};
        int i,pos,colorPos;
        Button b;
        Random rand = new Random();

        public void run() {
            Log.i("Runnable", "in the run block");

            for (i=0;i<buttonIds.length;i++){
                setColorImageToButton();
            }
          }

       void setColorImageToButton(){
            Button btn;
            btn=(Button) findViewById(buttonIds[prevPos]);
            btn.setBackgroundResource(0);
            btn.setTag(0);
            colorPos = rand.nextInt(colors.length);
            btn.setBackgroundColor(colors[colorPos]);
            pos = rand.nextInt(buttonIds.length);
            prevPos = pos;
            btn=(Button) findViewById(buttonIds[pos]);
            btn.setBackgroundResource(R.drawable.money);
            btn.setTag(R.drawable.money);

        }

    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        EditText et = (EditText) findViewById(R.id.editText);
        Button button = (Button)findViewById(v.getId());

        if((Integer)button.getTag()== R.drawable.money){
            et.setText("You have won Money !!!");
            Toast.makeText(this,"You Cick on The image",Toast.LENGTH_LONG).show();
        }
        else {
            et.setText("You lost !! Click and try again");
            Toast.makeText(this, "You Cick on emplty image", Toast.LENGTH_LONG).show();
        }

    }
}
