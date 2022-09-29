package com.example.tipcalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    SeekBar bar;
    TextView percent, tip, total;
    EditText input;
    String tip_percentage = "0.15";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = findViewById(R.id.bar);
        percent = findViewById(R.id.percent);
        tip = findViewById(R.id.tip_amount);
        total = findViewById(R.id.total);
        input = findViewById(R.id.input);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percent.setText(i + "%");
                tip_percentage = Double.toString(i / 100.0);
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        input.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                calculate();
            }
            return false;
        });
    }

    private void calculate() {
        String temp = input.getText().toString();
        if (temp.length() > 0) {
            try {
                BigDecimal value = new BigDecimal(temp);
                BigDecimal tip_num = new BigDecimal(tip_percentage);
                BigDecimal result = value.multiply(tip_num);
                tip.setText("$" + String.format("%.02f",result));
                total.setText("$" + String.format("%.02f",value.add(result)));
            } catch (Exception ignored) {}
        }
    }
}
