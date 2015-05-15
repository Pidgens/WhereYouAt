/*
 * Copyright (C) 2013 Code Here Now - A subsidiary of Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file 
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the 
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.codeherenow.sicalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

import static android.widget.SeekBar.OnSeekBarChangeListener;

public class SICalculatorActivity extends Activity implements OnSeekBarChangeListener {

    private TextView amt;
    private TextView rate;
    private float prinAmt;
    private float interestRate;
    private TextView seekBarValue;
    private SeekBar years;
    private Button calc;
    private int seekBarInt;
    private TextView textProgress;
    private float lengthTime;
    private TextView endText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sicalculator);
        endText = (TextView) findViewById(R.id.end);
        amt = (TextView) findViewById(R.id.amt);
        rate = (TextView) findViewById(R.id.rate);
        SeekBar years = (SeekBar) findViewById(R.id.seekBar);
        years.setOnSeekBarChangeListener(this);
        textProgress = (TextView)findViewById(R.id.time);
        seekBarInt = years.getProgress();
        calc = (Button) findViewById(R.id.calc);

        calc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                float y = goCalculate();
                endText.setText("The interest for " + +prinAmt + " at a rate of "+ +interestRate + " for " + +lengthTime + " is " + +y);
            }
        });
	}


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        textProgress.setText("In " + +i + " years");
        lengthTime = i;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private float goCalculate() {
        prinAmt = Float.parseFloat(amt.getText().toString());
        interestRate = Float.parseFloat(rate.getText().toString());

        float x = prinAmt * (interestRate / 100) * lengthTime;
        return x;
    }
}
