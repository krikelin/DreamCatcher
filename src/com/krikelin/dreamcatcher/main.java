package com.krikelin.dreamcatcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((Button)findViewById(R.id.button1)).setOnClickListener(new Button.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(main.this,Register.class);
				startActivity(i);
			}
        	
        });
        ((Button)findViewById(R.id.button2)).setOnClickListener(new Button.OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(main.this,DreamList.class);
				startActivity(i);
			}
        	
        });
    }
}