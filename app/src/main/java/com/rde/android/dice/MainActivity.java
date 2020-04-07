package com.rde.android.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener{
    int aRollCounter = 0;
    ImageButton btnRoll;

    int activeDice = 6;

    ImageView[] dice = new ImageView[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRoll = findViewById(R.id.btnRoll);

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        TypedValue outValue = new TypedValue();
        getApplicationContext().getTheme().resolveAttribute(
                android.R.attr.selectableItemBackground, outValue, true);
        btnRoll.setBackgroundResource(outValue.resourceId);

        dice[0] = findViewById(R.id.imageView);
        dice[1] = findViewById(R.id.imageView2);
        dice[2] = findViewById(R.id.imageView3);
        dice[3] = findViewById(R.id.imageView4);
        dice[4] = findViewById(R.id.imageView5);
        dice[5] = findViewById(R.id.imageView6);

        for(int i = 0; i < dice.length; i++)
        {
            dice[i].setBackgroundResource(outValue.resourceId);
         }

        if(savedInstanceState != null)
        {
            String sactiveDice = savedInstanceState.getString("activeDice"); // Restoring theWord
            try {
                activeDice = Integer.parseInt(sactiveDice);
            }
            catch (Exception e)
            {
            }
            for(int i = 0; i < dice.length; i++)
            {
                dice[i].setTag(savedInstanceState.getInt(String.valueOf(i), 0 )  );
                setDiceFromTag(dice[i]);
            }
            hideDice();

            showDice(activeDice);

        }
    }

    private void rollDice() {
        hideDice();
        Random random = null;
        hideDice();
        for(int i= 0; i < dice.length; i++)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            random = new Random(aRollCounter++);
            int anum = 1 + random.nextInt(6);
            anum = ThreadLocalRandom.current().nextInt(1, 7);
            dice[i].setTag(anum);
            switch(anum){
                case 1:
                    dice[i].setImageResource(R.drawable.uno);
                    break;
                case 2:
                    dice[i].setImageResource(R.drawable.due);
                    break;
                case 3:
                    dice[i].setImageResource(R.drawable.tre);
                    break;
                case 4:
                    dice[i].setImageResource(R.drawable.quattro);
                    break;
                case 5:
                    dice[i].setImageResource(R.drawable.cinque);
                    break;
                case 6:
                    dice[i].setImageResource(R.drawable.sei);
                    break;

            }
        }
        showDice(activeDice);
        Toast.makeText(this,
                "Done!!!", Toast.LENGTH_SHORT).show();

    }

    private void clearDice()
    {

    }

    private void hideDice()
    {
        for(int i = 0; i < dice.length; i++)
        {
            dice[i].setVisibility(View.GONE);

        }
     }

    private void showDice(int qty)
    {
        for(int i = 0; i < qty; i++)
        {
            dice[i].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.noOfDice) {
            NumberPickerDialog newFragment = new NumberPickerDialog();
            newFragment.setValueChangeListener(this);
            newFragment.show(getSupportFragmentManager(), "Dice");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//        Toast.makeText(this,
//                "selected number " + picker.getValue(), Toast.LENGTH_SHORT).show();
        activeDice = picker.getValue();
        hideDice();
        showDice(activeDice);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("activeDice", String.valueOf(activeDice));
        for(int i = 0; i < dice.length; i++)
        {
            outState.putInt(String.valueOf(i), (int)(dice[i].getTag() == null ? 0 :  dice[i].getTag()));
        }
        super.onSaveInstanceState(outState);

       // outState.putString("theWord", theWord); // Saving the Variable theWord
       // outState.putStringArrayList("fiveDefns", fiveDefns); // Saving the ArrayList fiveDefns
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);

       String sactiveDice = savedInstanceState.getString("activeDice"); // Restoring theWord
        try {
           activeDice = Integer.parseInt(sactiveDice);
        }
        catch (Exception e)
        {
        }
        for(int i = 0; i < dice.length; i++)
        {
            dice[i].setTag(savedInstanceState.getInt(String.valueOf(i), 0 )  );
            setDiceFromTag(dice[i]);
        }
        hideDice();

        showDice(activeDice);

    }

    private void setDiceFromTag(ImageView dado)
    {
        int anum = dado.getTag() == null ? 0  : (int)(dado.getTag()) ;
        switch(anum){
            case 1:
                dado.setImageResource(R.drawable.uno);
                break;
            case 2:
                dado.setImageResource(R.drawable.due);
                break;
            case 3:
                dado.setImageResource(R.drawable.tre);
                break;
            case 4:
                dado.setImageResource(R.drawable.quattro);
                break;
            case 5:
                dado.setImageResource(R.drawable.cinque);
                break;
            case 6:
                dado.setImageResource(R.drawable.sei);
                break;

        }

    }
}
