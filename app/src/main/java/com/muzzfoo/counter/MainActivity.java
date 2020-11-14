/*
 * Simple, no-frills counter app
 * 2020-11-10 mlf
 *
 * Changes:
 * 2020-11-11 mlf: added day/night mode handling
 * 2020-11-12 mlf: moved formatting and dark mode support to to styles.xml
 *                 ensured support for older versions of Android
 */

package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


// use appcompat to support older versions of Android
public class MainActivity extends AppCompatActivity {
    int count = 0;  // Integer.MAX_VALUE = 2,147,483,647

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // restore the value of count when onCreate is called after the device is rotated
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
        }

        // counter
        final TextView countTextView = findViewById(R.id.counterTextView);
        countTextView.setText(String.valueOf(count));
        countTextView.setOnClickListener(v -> {
            count++;
            countTextView.setText(String.valueOf(count));
        });

        // reset
        final TextView resetTextView = findViewById(R.id.resetTextView);
        resetTextView.setOnClickListener(v -> {
            count = 0;
            countTextView.setText(String.valueOf(count));
        });
    }

    // onCreate() is called whenever the device is rotated or day/night mode is changed.
    // This has the effect of resetting count to zero.  To work around that. this function
    // stores count in the saved instance state for later restoration.
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("count", count);
        super.onSaveInstanceState(savedInstanceState);
    }
}
