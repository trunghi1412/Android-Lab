package com.example.easycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity  {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAc, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
//        resultTv = findViewById(R.id.result_tv);
//        solutionTv = findViewById(R.id.solution_tv);
//        assignId(buttonC,R.id.button_c);
//        assignId(buttonC,R.id.button_open_bracket);
//        assignId(buttonC,R.id.button_close_bracket);
//        assignId(buttonC,R.id.button_divide);
//        assignId(buttonC,R.id.button_multiply);
//        assignId(buttonC,R.id.button_plus);
//        assignId(buttonC,R.id.button_minus);
//        assignId(buttonC,R.id.button_equals);
//        assignId(buttonC,R.id.button_1);
//        assignId(buttonC,R.id.button_2);
//        assignId(buttonC,R.id.button_3);
//        assignId(buttonC,R.id.button_4);
//        assignId(buttonC,R.id.button_5);
//        assignId(buttonC,R.id.button_6);
//        assignId(buttonC,R.id.button_7);
//        assignId(buttonC,R.id.button_8);
//        assignId(buttonC,R.id.button_9);
//        assignId(buttonC,R.id.button_0);
//        assignId(buttonC,R.id.button_ac);
//        assignId(buttonC,R.id.button_dot);
//
//
//
//    }
//    void assignId(MaterialButton btn, int id){
//        btn = findViewById(id);
//        btn.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        MaterialButton button =(MaterialButton) view;
//        String buttonText = button.getText().toString();
//        String dataToCalculate = solutionTv.getText().toString();
//
//        if(buttonText.equals("AC")) {
//            solutionTv.setText("");
//            resultTv.setText("0");
//            return;
//        }
//        if(buttonText.equals("=")){
//            solutionTv.setText(resultTv.getText());
//            return;
//        }
//        if(buttonText.equals(("C"))) {
//            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
//        } else {
//            dataToCalculate = dataToCalculate+buttonText;
//        }
//
//        solutionTv.setText(dataToCalculate);
//
//        String finalResult = getResult(dataToCalculate);
//        if (!finalResult.equals("Error")) {
//            resultTv.setText(finalResult);
//        }
//    }
//    String getResult(String data) {
//        try {
//            Context context = Context.enter();
//            context.setOptimizationLevel(-1);
//            Scriptable scriptable = context.initStandardObjects();
//            String finalResult = context.evaluateString(scriptable, data, "Javascript",
//                    1, null).toString();
//            if(finalResult.endsWith(".0")) {
//                finalResult = finalResult.replace(".0", "");
//            }
//            return finalResult;
//        }catch (Exception e) {
//            return "Error";
//      }
//    }
