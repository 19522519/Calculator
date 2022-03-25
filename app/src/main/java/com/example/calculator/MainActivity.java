package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNumber;
    private TextView tvResult;

    private Button btnNum0;
    private Button btnNum1;
    private Button btnNum2;
    private Button btnNum3;
    private Button btnNum4;
    private Button btnNum5;
    private Button btnNum6;
    private Button btnNum7;
    private Button btnNum8;
    private Button btnNum9;

    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivide;

    private Button btnEqual;
    private Button btnPoint;
    private Button btnDelete;
    private Button btnAllClear;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        setEventClickViews();
    }

    public void InitWidget() {
        edtNumber = (EditText)findViewById(R.id.edtNumber);
        tvResult = (TextView)findViewById(R.id.tvResult);

        btnNum0 = (Button)findViewById(R.id.btnNum0);
        btnNum1 = (Button)findViewById(R.id.btnNum1);
        btnNum2 = (Button)findViewById(R.id.btnNum2);
        btnNum3 = (Button)findViewById(R.id.btnNum3);
        btnNum4 = (Button)findViewById(R.id.btnNum4);
        btnNum5 = (Button)findViewById(R.id.btnNum5);
        btnNum6 = (Button)findViewById(R.id.btnNum6);
        btnNum7 = (Button)findViewById(R.id.btnNum7);
        btnNum8 = (Button)findViewById(R.id.btnNum8);
        btnNum9 = (Button)findViewById(R.id.btnNum9);

        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnMinus = (Button)findViewById(R.id.btnMinus);
        btnMultiply = (Button)findViewById(R.id.btnMultiply);
        btnDivide = (Button)findViewById(R.id.btnDivide);

        btnEqual = (Button)findViewById(R.id.btnEqual);
        btnPoint = (Button)findViewById(R.id.btnPoint);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnAllClear = (Button)findViewById(R.id.btnAllClear);
    }

    public void setEventClickViews() {
        btnNum0.setOnClickListener(this);
        btnNum1.setOnClickListener(this);
        btnNum2.setOnClickListener(this);
        btnNum3.setOnClickListener(this);
        btnNum4.setOnClickListener(this);
        btnNum5.setOnClickListener(this);
        btnNum6.setOnClickListener(this);
        btnNum7.setOnClickListener(this);
        btnNum8.setOnClickListener(this);
        btnNum9.setOnClickListener(this);

        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);

        btnEqual.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnAllClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnNum0:
                edtNumber.append("0");
                break;
            case R.id.btnNum1:
                edtNumber.append("1");
                break;
            case R.id.btnNum2:
                edtNumber.append("2");
                break;
            case R.id.btnNum3:
                edtNumber.append("3");
                break;
            case R.id.btnNum4:
                edtNumber.append("4");
                break;
            case R.id.btnNum5:
                edtNumber.append("5");
                break;
            case R.id.btnNum6:
                edtNumber.append("6");
                break;
            case R.id.btnNum7:
                edtNumber.append("7");
                break;
            case R.id.btnNum8:
                edtNumber.append("8");
                break;
            case R.id.btnNum9:
                edtNumber.append("9");
                break;
            case R.id.btnPlus:
                edtNumber.append("+");
                break;
            case R.id.btnMinus:
                edtNumber.append("-");
                break;
            case R.id.btnMultiply:
                edtNumber.append("*");
                break;
            case R.id.btnDivide:
                edtNumber.append("/");
                break;

            case R.id.btnEqual:
                DecimalFormat decimalFormat = new DecimalFormat("###.##########");
                double result = 0;

                AddOperation(edtNumber.getText().toString());
                AddNumber(edtNumber.getText().toString());

                for(int i = 0; i < arrNumber.size() - 1; i++) {
                    switch(arrOperation.get(i)) {
                        case "+":
                            if(i == 0) {
                                result = arrNumber.get(i) + arrNumber.get(i+1);
                            }
                            else {
                                result += arrNumber.get(i+1);
                            }
                            break;
                        case "-":
                            if(i == 0) {
                                result = arrNumber.get(i) - arrNumber.get(i+1);
                            }
                            else {
                                result -= arrNumber.get(i+1);
                            }
                            break;
                        case "*":
                            if(i == 0) {
                                result = arrNumber.get(i) * arrNumber.get(i+1);
                            }
                            else {
                                result *= arrNumber.get(i+1);
                            }
                            break;
                        case "/":
                            if(i == 0) {
                                result = arrNumber.get(i) / arrNumber.get(i+1);
                            }
                            else {
                                result /= arrNumber.get(i+1);
                            }
                            break;
                        default:
                            break;
                    }
                }
                tvResult.setText(decimalFormat.format(result) + "");
                //Log.d(TAG, "onClick: " +result);
                break;

            case R.id.btnPoint:
                edtNumber.append(".");
                break;
            case R.id.btnDelete:
                BaseInputConnection textFieldinputConnection = new BaseInputConnection(edtNumber, true);
                textFieldinputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
//                String TextAfterDelete = DeleteANumber(edtNumber.getText().toString());
//                edtNumber.setText(TextAfterDelete);
                break;
            case R.id.btnAllClear:
                edtNumber.setText("");
                tvResult.setText("");
                break;
            default:
                break;
        }
    }

//    public String DeleteANumber(String number) {
//        int length = number.length();
//        String temp = number.substring(0, length-1);
//        return temp;
//    }

    public ArrayList<String> arrOperation;  //Mảng chứa dấu phép tính
    public ArrayList<Double> arrNumber;     //Mảng chứa chữ số

    public int AddOperation (String input) {
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for(int i = 0; i <  cArray.length; i++) {
            switch(cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '*':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    public void AddNumber (String input) {
        arrNumber = new ArrayList<>();

        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(input);
        while (matcher.find()) {
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }

}