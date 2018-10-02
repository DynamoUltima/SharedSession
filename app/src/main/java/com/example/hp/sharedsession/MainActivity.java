package com.example.hp.sharedsession;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        /** this method indicates this is the first time this method is being called
         * we would later overwrite this method with a false boolean*/

        boolean firstStart = prefs.getBoolean("firstStart",true);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.commit();

            }
        });

        if (firstStart){
            showStartDialog();
        }
        

    }

    private void showStartDialog() {
        new AlertDialog.Builder(this)
        .setTitle("One Time Title")
        .setMessage("This Should only be shown once")
                .setPositiveButton("0k", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                })
                .create()
                .show();
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        /**when this method is called it now calls the show atart method as false
        meaning it isnt showing for the first time**/

        editor.putBoolean("firstStart",false);
        editor.apply();
    }
}
