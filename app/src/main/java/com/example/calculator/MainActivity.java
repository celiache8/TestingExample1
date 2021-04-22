package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;

    private EditText editText_one;
    private EditText editText_two;
    private TextView textView_result;
    private Button button_add, button_sub, button_mul, button_div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_one = findViewById(R.id.editText_one);
        editText_two = findViewById(R.id.editText_two);
        textView_result = findViewById(R.id.textView_result);
        button_add = findViewById(R.id.button_add);
        button_div = findViewById(R.id.button_div);
        button_mul = findViewById(R.id.button_mul);
        button_sub = findViewById(R.id.button_sub);

        calculator = new Calculator();

        button_add.setOnClickListener(v -> onAdd(v));
        button_sub.setOnClickListener(v -> onSub(v));
        button_mul.setOnClickListener(v -> onMul(v));
        button_div.setOnClickListener(v -> onDiv(v));
    }
    /**
     * OnClick method called when the add Button is pressed.
     */
    public void onAdd(View view) {
        compute(Calculator.Operator.ADD);
    }

    /**
     * OnClick method called when the subtract Button is pressed.
     */
    public void onSub(View view) {
        compute(Calculator.Operator.SUB);
    }
    /**
     * OnClick method called when the divide Button is pressed.
     */
    public void onDiv(View view) {
        try {
            compute(Calculator.Operator.DIV);
        } catch (IllegalArgumentException iae) {
            textView_result.setText("Error in computing.");
        }
    }

    /**
     * OnClick method called when the multiply Button is pressed.
     */
    public void onMul(View view) {
        compute(Calculator.Operator.MUL);
    }

    private void compute(Calculator.Operator operator) {
        double operandOne;
        double operandTwo;
        try {
            operandOne = getOperand(editText_one);
            operandTwo = getOperand(editText_two);
        } catch (NumberFormatException nfe) {
            textView_result.setText("Error in computing.");
            return;
        }

        String result;
        switch (operator) {
            case ADD:
                result = String.valueOf(
                        calculator.add(operandOne, operandTwo));
                break;
            case SUB:
                result = String.valueOf(
                        calculator.sub(operandOne, operandTwo));
                break;
            case DIV:
                result = String.valueOf(
                        calculator.div(operandOne, operandTwo));
                break;
            case MUL:
                result = String.valueOf(
                        calculator.mul(operandOne, operandTwo));
                break;
            default:
                result = "Error in computing.";
                break;
        }
        textView_result.setText(result);
    }

    /**
     * @return the operand value entered in an EditText as double.
     */
    private static Double getOperand(EditText operandEditText) {
        String operandText = getOperandText(operandEditText);
        return Double.valueOf(operandText);
    }

    /**
     * @return the operand text which was entered in an EditText.
     */
    private static String getOperandText(EditText operandEditText) {
        return operandEditText.getText().toString();
    }

}