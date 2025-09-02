package jp.ac.meijou.android.s241205011;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205011.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;
    private int count = 0; // count をフィールド変数として宣言し、初期値を0に設定
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        prefDataStore = PrefDataStore.getInstance(this);
        String[] hello = {"＊","/","＋","-"};
        binding.button.setOnClickListener(view ->{
            int number1 = Integer.parseInt(binding.editText1.getText().toString());
            int number2 = Integer.parseInt(binding.editText2.getText().toString());
            float result;
            binding.textbox1.setText(hello[count % hello.length]); // 配列の長さを直接使用
            if(count == 0){
                result = number1 * number2;
                binding.textbox2.setText(String.valueOf(result));
            }
            else if(count == 1){
                if (number1 == 0) {
                    binding.textbox2.setText("エラー");
                }else{result = number2 / number1;
                    binding.textbox2.setText(String.valueOf(result));
                }
            }
            else if(count == 2){
                result = number1 + number2;
                binding.textbox2.setText(String.valueOf(result));
            }
            else{
                result = number2 - number1;
                binding.textbox2.setText(String.valueOf(result));
            }
            if(count == 3){
                count = 0;
            }else{count++;}
        });
        binding.savebutton.setOnClickListener(view -> {
            var text = binding.textbox2.getText().toString();
            prefDataStore.setString("name",text);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        prefDataStore.getString("name").ifPresent(name -> binding.textbox2.setText(name));
    }
}