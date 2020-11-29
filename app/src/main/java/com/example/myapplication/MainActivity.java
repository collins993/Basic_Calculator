package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String input, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

   public void buttonClick(View view){
       Button button = (Button) view;
       String data = button.getText().toString();
       switch (data) {
           case "AC":
               input = "";
               break;
           case "*":
               Solve();
               input += "*" ;
               break;
           case "^":
               Solve();
               input += "^" ;
               break;
           case "=":
               Solve();
               answer = input;
               break;
           case "C":
               String newText = input.substring(0,input.length()-1);
               input = newText;
               break;
           default:
               if (input == null){
                   input = "";
               }
               if (data.equals("+") || data.equals("-") || data.equals("/")){
                   Solve();
               }
               input += data;
       }
       binding.screen.setText(input);
    }

    private void Solve() {
        if (input.split("\\*").length == 2) {
            String number[] = input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                input = mul + "";
            }
            catch (Exception e){

            }

        }
        else if (input.split("/").length == 2) {
            String number[] = input.split("/");
            try {
                double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                input = div + "";
            }
            catch (Exception e){

            }

        }

        else if (input.split("\\^").length == 2) {
            String number[] = input.split("\\^");
            try {
                double pow = Math.pow(Double.parseDouble(number[0]), Double.parseDouble(number[1]));
                input = pow + "";
            }
            catch (Exception e){

            }

        }

        else if (input.split("\\+").length == 2) {
            String number[] = input.split("\\+");
            try {
                double add = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                input = add + "";
            }
            catch (Exception e){

            }

        }

        else if (input.split("-").length > 1) {//this is only for subtraction that may have more than two split char
            String number[] = input.split("-");
            //to subtract from negative number
            if (number[0] == "" && number.length == 2){
                number[0] = 0 + "";
            }
            try {
                double sub = 0;
                if (number.length == 2){
                    sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                }
                else  if (number.length == 3){
                    sub = Double.parseDouble(number[1]) - Double.parseDouble(number[2]);
                }
                input = sub + "";
            }
            catch (Exception e){

            }

        }
        //to remove last digit.0....9 instead of 9.0
        String n[] = input.split("\\.");
        if (n.length > 1){
            if (n[1].equals("0")){
                input = n[0];
            }
        }
        binding.screen.setText(input);


    }
}