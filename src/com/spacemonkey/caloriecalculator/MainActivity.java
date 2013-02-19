package com.spacemonkey.caloriecalculator;

import java.util.Locale;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final EditText fieldCalories= (EditText) findViewById(R.id.text_calories);
        final EditText fieldPer= (EditText) findViewById(R.id.text_per);
        final EditText fieldGramsOfFood= (EditText) findViewById(R.id.text_grams_of_food);
        
        final TextView valueCaloriesPerHundredGrams =(TextView) findViewById(R.id.value_calories_per_hundred_grams);
        final TextView valueCaloriesTotal =(TextView) findViewById(R.id.value_calories_total);
        
        
        fieldPer.setText("100");
        
        
        OnKeyListener StuffDoer = new OnKeyListener() {
			
        	private float getFloat(EditText fieldToParse) {
        		String valueAsString = fieldToParse.getText().toString().trim();
        		if (valueAsString.equals("")) {
        			return 0;
        		}
        		return Float.parseFloat(valueAsString);
        	}
        	
        	private String format(float toFormat) {
        		
        		toFormat = Math.round(toFormat);
        		
        		return String.format(Locale.ENGLISH,"%2s", (int) toFormat);
        	}
        	
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				float valueOfFieldCalories = getFloat(fieldCalories);
				float valueOfFieldPer = getFloat(fieldPer);
				float valueOfFieldGramsOfFood = getFloat(fieldGramsOfFood);
				
				float perOneGram = valueOfFieldCalories/valueOfFieldPer;
				float perHundredGrams = perOneGram*100;
				float itemTotal = valueOfFieldGramsOfFood * perOneGram;
				
				valueCaloriesPerHundredGrams.setText(format(perHundredGrams));
				valueCaloriesTotal.setText(format(itemTotal));
			
				return false;
			
			}
		};
        
        fieldCalories.setOnKeyListener(StuffDoer);
        fieldPer.setOnKeyListener(StuffDoer);
        fieldGramsOfFood.setOnKeyListener(StuffDoer);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
}
