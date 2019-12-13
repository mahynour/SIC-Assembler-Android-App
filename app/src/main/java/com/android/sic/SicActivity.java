package com.android.sic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import static android.widget.Toast.LENGTH_SHORT;
/***Copyright 2019, Mahynour , All rights reserved.***/


public class SicActivity extends AppCompatActivity {

    private EditText editText;
    private TextView Text;

    String start_adress;
    String errors;
    PassOne passOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sic_activity);
        Text = findViewById(R.id.text);
        editText = findViewById(R.id.editText1);

        start_adress =
                "ahmed  START  0000\n" +
                        "bgn    LDA    c\n" +
                        "       ADD    c\n" +
                        "       STA    s   \n" +
                        "s      RESW   1\n" +
                        "c      WORD   5\n" +
                        "       END    bgn\n";
        editText.setText(start_adress);
        Text.setText("OBJ CODE");



        Button buttonOne = (Button) findViewById(R.id.button1);
        buttonOne.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                passOne = new PassOne();
                if (!String.valueOf(editText.getText()).equals("") || !String.valueOf(editText.getText()).isEmpty() || editText.getText() != null)
                {   passOne.createObjCode(String.valueOf(editText.getText()));
                if(!passOne.isFaild())
                Text.setText(passOne.getobj());
                else {
                    for (String error : passOne.errors()) {
                        errors += error + "\n";
                    }
                    Text.setText(errors);

                }
            }
                  else
                Toast.makeText(getApplicationContext(), "wrong instructions", LENGTH_SHORT).show();


            }
        });


    }

}
