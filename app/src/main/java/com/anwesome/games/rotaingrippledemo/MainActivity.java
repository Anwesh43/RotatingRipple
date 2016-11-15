package com.anwesome.games.rotaingrippledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anwesome.games.rotaingripplebutton.RotatingRipple;

public class MainActivity extends AppCompatActivity {
    RotatingRipple button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (RotatingRipple)findViewById(R.id.hello);
        button.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Hello",Toast.LENGTH_LONG).show();
            }
        });
    }
}
