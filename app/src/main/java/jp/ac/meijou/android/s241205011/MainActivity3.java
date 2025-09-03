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
            if (in_number == 0 && count != 0) {
                if (color[count-1] == 1){
                    binding.textView1.setText("エラー");
                    return;
                }
            }
            in_number = in_number*10;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button1.setOnClickListener(v -> {
            in_number = in_number*10+1;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button2.setOnClickListener(v -> {
            in_number = in_number*10+2;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button3.setOnClickListener(v -> {
            in_number = in_number*10+3;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button4.setOnClickListener(v -> {
            in_number = in_number*10+4;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button5.setOnClickListener(v -> {
            in_number = in_number*10+5;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button6.setOnClickListener(v -> {
            in_number = in_number*10+6;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button7.setOnClickListener(v -> {
            in_number = in_number*10+7;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button8.setOnClickListener(v -> {
            in_number = in_number*10+8;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button9.setOnClickListener(v -> {
            in_number = in_number*10+9;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button10.setOnClickListener(v -> {
            number[count] = in_number;
            color[count] = 1;
            count++;
            in_number = 0;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button11.setOnClickListener(v -> {
            number[count] = in_number;
            color[count] = 2;
            count++;
            in_number = 0;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button12.setOnClickListener(v -> {
            number[count] = in_number;
            color[count] = 3;
            count++;
            in_number = 0;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button13.setOnClickListener(v -> {
            number[count] = in_number;
            color[count] = 4;
            count++;
            in_number = 0;
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button14.setOnClickListener(v -> {
            number[count] = in_number;
            in_number = number[0];
            for (int i = 0; i < number.length-1; i++) {
                if (color[i] == 1) {
                    in_number = in_number/number[i+1];
                }
                if (color[i] == 2) {
                    in_number = in_number*number[i+1];
                }
                if (color[i] == 3) {
                    in_number = in_number-number[i+1];
                }
                if (color[i] == 4) {
                    in_number = in_number+number[i+1];
                }
            }
            count = 0;
            for (int i = 0; i < number.length; i++) {
                number[i] = 0;
            }
            for (int i = 0; i < color.length; i++) {
                color[i] = 0;
            }
            binding.textView1.setText(String.valueOf(in_number));
        });
        binding.button15.setOnClickListener(v -> {
            in_number = 0;
            count = 0;
            for (int i = 0; i < number.length; i++) {
                number[i] = 0;
            }
            for (int i = 0; i < color.length; i++) {
                color[i] = 0;
            }
            binding.textView1.setText(String.valueOf(in_number));
        });

    }
}