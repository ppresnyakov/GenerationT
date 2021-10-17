package com.example.generationt;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;

import com.example.generationt.databinding.ActivityMainBinding;

import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityMainBinding binding;
    private NumberPicker year_picker;
    private TextView ResultText;
    private TextView WelcomeText;
    private Button CheckDate;
    private SharedPreferences prefs;
    View dialogView;
    Date date = new Date();
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        prefs = this.getSharedPreferences("com.example.generationt", Context.MODE_PRIVATE);
        dialogView = View.inflate(this, R.layout.activity_main, null);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toast = Toast.makeText(getApplicationContext(), "Можем рассчитать только года 1946-2012", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.LEFT, 10, 10);

        CheckDate = (Button) findViewById(R.id.check_date);
        CheckDate.setOnClickListener(this);

        year_picker = findViewById(R.id.datePicker);
        year_picker.setMaxValue(2012);
        year_picker.setMinValue(1935);


        ResultText = (TextView) findViewById(R.id.ResultText);

        WelcomeText = (TextView) findViewById(R.id.WelcomeText);
        WelcomeText.setText("Выбери свою дату в календаре и нажми CHECK DATE");
        WelcomeText.setBackgroundResource(R.color.black);
        WelcomeText.setTextColor(Color.WHITE);

        ResultText.setText(prefs.getString("DateText", ""));
        year_picker.setValue(prefs.getInt("Year",2000));

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.check_date) {

            Integer year;
            year = year_picker.getValue();
            if (year> 1946 &&  year <= 1964) {
                ResultText.setText("Ты относишься к поколению бэби-бумеров");
            }
            else if(year >= 1965 &&  year <= 1980){
                ResultText.setText("Ты относишься к поколению X");
                }
            else if(year>= 1981 &&  year <= 1996){
                ResultText.setText("Ты относишься к поколению Y или миллениалов ");
                }
            else if(year >= 1997 &&  year <= 2012){
                ResultText.setText("Ты относишься к поколению Z или зумеров");
                }
            else{
                ResultText.setText("");
                toast.setGravity(Gravity.LEFT, 10, 10);
                toast.show();
                }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        prefs.edit().putString("DateText", ResultText.getText().toString()).apply();
        prefs.edit().putInt("Year", year_picker.getValue()).apply();

    }
}