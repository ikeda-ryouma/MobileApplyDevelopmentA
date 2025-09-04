package jp.ac.meijou.android.s241205011;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205011.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s241205011.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {
    private ActivityMain3Binding binding;
    private PrefDataStore prefDataStore;
    private float[] number = new float[100];
    private int[] color = new int[100];
    private float in_number = 0;
    private int count = 0;
    private String operator = "";
    private String[] operator_list = {"÷", "×", "-", "+"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.button0.setOnClickListener(v -> {
            handleNumberInput(0);
        });
        binding.button1.setOnClickListener(v -> {
            handleNumberInput(1);
        });
        binding.button2.setOnClickListener(v -> {
            handleNumberInput(2);
        });
        binding.button3.setOnClickListener(v -> {
            handleNumberInput(3);
        });
        binding.button4.setOnClickListener(v -> {
            handleNumberInput(4);
        });
        binding.button5.setOnClickListener(v -> {
            handleNumberInput(5);
        });
        binding.button6.setOnClickListener(v -> {
            handleNumberInput(6);
        });
        binding.button7.setOnClickListener(v -> {
            handleNumberInput(7);
        });
        binding.button8.setOnClickListener(v -> {
            handleNumberInput(8);
        });
        binding.button9.setOnClickListener(v -> {
            handleNumberInput(9);
        });
        binding.button10.setOnClickListener(v -> {
            handleOperatorInput(1);
        });
        binding.button11.setOnClickListener(v -> {
            handleOperatorInput(2);
        });
        binding.button12.setOnClickListener(v -> {
            handleOperatorInput(3);
        });
        binding.button13.setOnClickListener(v -> {
            handleOperatorInput(4);
        });
        binding.button14.setOnClickListener(v -> {
            number[count] = in_number;
            for (int i = 0; i < count; i++) {
                if (color[i] == 1) {
                    number[i+1] = number[i] / number[i+1];
                    number[i] = 0;
                    if (i == 0){
                        color[i] = 4;
                    }else color[i] = color[i-1];
                } else if (color[i] == 2) {
                    number[i+1] = number[i] * number[i+1];
                    number[i] = 0;
                    if (i == 0){
                        color[i] = 4;
                    }else color[i] = color[i-1];
                }
            }
            float result = number[0];
            for (int i = 0; i < count; i++) {
                if (color[i] == 3) {
                    result -= number[i+1];
                } else if (color[i] == 4) {
                    result += number[i+1];
                }
            }
            clearArrays();
            binding.textView1.setText(String.valueOf(result));
            in_number = result;
            operator = String.valueOf(result);
            binding.textView2.setText(operator);
        });
        binding.button15.setOnClickListener(v -> {
            clearArrays();
            binding.textView1.setText(String.valueOf(in_number));
        });

    }

    private void handleNumberInput(int digit) {
        if (in_number == 0 && count != 0 && digit == 0) {
            if (color[count-1] == 1){ // 割り算の場合のエラー処理
                binding.textView1.setText("エラー");
                return;
            }
        }
        operator = operator + String.valueOf(digit);
        in_number = in_number * 10 + digit;
        binding.textView1.setText(String.valueOf(in_number));
        binding.textView2.setText(String.valueOf(operator));
    }

    private void handleOperatorInput(int operatorColor) {
        number[count] = in_number;
        color[count] = operatorColor;
        count++;
        in_number = 0;
        operator = operator + operator_list[operatorColor-1];
        binding.textView1.setText(String.valueOf(in_number));
        binding.textView2.setText(String.valueOf(operator));
    }

    private void clearArrays() {
        for (int i = 0; i < 100; i++) {
            number[i] = 0;
            color[i] = 0;
        }
        in_number = 0;
        count = 0;
        operator = "";
        binding.textView2.setText(String.valueOf(operator));
    }
}