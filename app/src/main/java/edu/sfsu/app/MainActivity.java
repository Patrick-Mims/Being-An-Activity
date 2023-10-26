package edu.sfsu.app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import edu.sfsu.app.database.Contract;
import edu.sfsu.app.database.SQLiteHelper;

public class MainActivity extends AppCompatActivity {
    public static int counter = 0;
    private boolean running = false;
    private int seconds = 0;
    private final int sixty = 60;
    private final int thirty_six_hundred = 3600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String item = "";
        String itemTitle = "";
        TextView tv = findViewById(R.id.tvRecord);
        tv.setText("Hello Patrick");

        Log.v("LOG", "MainActivity.class: Before SQLiteHelper");

        /**
         * Create a Cursor
         */
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

        /**
         * Define a projection that specifies which columns from the database
         * you will actually use after this query.
         */
        String[] projection = {
                Contract.ContractEntry._ID,
                Contract.ContractEntry.COLUMN_NAME_TITLE,
                Contract.ContractEntry.COLUMN_NAME_SUBTITLE
        };

        String order = Contract.ContractEntry.COLUMN_NAME_SUBTITLE + " DESC";

        try {
            SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

            Cursor cursor = db.query(
                    Contract.ContractEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    order
            );

            Log.v("LOG", "Reference to SQLiteDatabase 2.0");

            if(cursor.moveToNext()) {
                item = cursor.getString(1);
                itemTitle = cursor.getString(2);

                tv.setText(item.concat(itemTitle));
            }

            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            tv.setText("Database is Unavailable 3.0");
            Log.v("LOG", "Catch SQLiteException");
        }

        Log.v("LOG", "MainActivity.class: After SQLiteHelper");

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            counter = savedInstanceState.getInt("counter");
        }

        loadImages();
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putInt("counter", counter);
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    ArrayList<Integer> images = new ArrayList<>();

    public void loadImages() {
        images.add(R.drawable.spindrift_can_hero_blood_orange);
        images.add(R.drawable.spindrift_can_hero_grapefruit);
        images.add(R.drawable.spindrift_can_hero_lime);
        images.add(R.drawable.spindrift_can_hero_pineapple);
        images.add(R.drawable.spindrift_can_hero_lemon);
    }
    public void swapImage() {
        Log.v("LOG", "Swapping...");
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageDrawable(getResources().getDrawable(images.get(counter)));

        if(counter == 5) {
            counter = 0;
        } else {
            counter++;
        }
    }

    private void runTimer() {
        final TextView timeView = findViewById(R.id.tvTime);

        Handler handler = new Handler();

        /* Causes the Runnable r to be added to the message queue.
           The runnable will be run on the thread to which this handler is attached to. */
        handler.post(new Runnable() {
            @Override
            public void run() {

                int h = (seconds / thirty_six_hundred);
                int m = (seconds % thirty_six_hundred)/sixty;
                int s = (seconds % sixty);

                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", h, m, s);

                timeView.setText(time);

                if(seconds == 5)
                    swapImage();

                if(running) seconds++;

                handler.postDelayed(this, 1000);
            }
        }); // end: handler.post();
    }
}
/**
 * Database implementation Index
 * 1. Contract Class
 * 2. Helper Class
 *
 * Step 1 - create a contract class that holds the constants that will be used throughout
 * Step 2 - create a SQL Helper Class
 * Step 3 - create an object and a cursor in MainActivity
 */