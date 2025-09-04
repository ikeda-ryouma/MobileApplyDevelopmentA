package jp.ac.meijou.android.s241205011;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.android.s241205011.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s241205011.databinding.ActivityMain5Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity5 extends AppCompatActivity {
    private ActivityMain5Binding binding;
    private PrefDataStore prefDataStore;
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    private String Uri_name = "https://placehold.jp/500x500.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.button7.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            var bgColor = binding.editTextText2.getText().toString();
            var textColor = binding.editTextText4.getText().toString();

            // 背景色の入力があればパラメータを追加
            if (!bgColor.isEmpty()) {
                Uri_name = String.format("https://placehold.jp/%s/500x500.png",bgColor);
            }

            // 文字色の入力があればパラメータを追加
            if (!textColor.isEmpty()) {
                Uri_name = String.format("https://placehold.jp/%s/500x500.png",textColor);
            }

            if (!textColor.isEmpty() && !bgColor.isEmpty()) {
                Uri_name = String.format("https://placehold.jp/%s/%s/500x500.png",bgColor,textColor);
            }
            // URLの作成
            Uri.Builder uriBuilder = Uri.parse(Uri_name)
                    .buildUpon()
                    .appendQueryParameter("text", text);

            var url = uriBuilder.build().toString();

            // 画像の取得開始
            getImage(url);
        });
    }

    /**
     * placehold.jpから画像を取得し、ImageViewを更新する
     *
     * @param url 画像のURL
     */
    private void getImage(String url) {
        // リクエスト先に画像URLを指定
        var request = new Request.Builder()
                .url(url)
                .build();

        // 非同期通信でリクエスト
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                // InputStreamをBitmapに変換
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                // UIスレッド以外で更新するとクラッシュするので、UIスレッド上で実行させる
                runOnUiThread(() -> binding.imageView.setImageBitmap(bitmap));
            }
        });
    }
}