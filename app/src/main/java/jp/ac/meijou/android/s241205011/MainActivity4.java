package jp.ac.meijou.android.s241205011;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s241205011.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s241205011.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {
    private ActivityMain4Binding binding;
    private PrefDataStore prefDataStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        var editText = getIntent().getStringExtra("text");
        binding.textView4.setText(editText);
        Optional.ofNullable(getIntent().getStringExtra("text"))
                .ifPresent(text -> binding.textView4.setText(text));

        binding.button4.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", "OK");
            setResult(RESULT_OK, intent);
            finish();
        });

        binding.button6.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}