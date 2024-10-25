package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tv_meal;
    private Button btn_select;

    private final ActivityResultLauncher<Intent> mStartForResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    // 如果 intent 不為 null 且 intent 的 extras 不為 null
                    if (intent != null && intent.getExtras() != null) {
                        Bundle b = intent.getExtras();
                        String str1 = b.getString("drink");
                        String str2 = b.getString("sugar");
                        String str3 = b.getString("ice");
                        tv_meal.setText(String.format("飲料: %s\n\n甜度: %s\n\n冰塊: %s", str1, str2, str3));
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_meal = findViewById(R.id.tv_meal);      // 連接 TextView 元件
        btn_select = findViewById(R.id.btn_choice); // 連接 Button 元件
        btn_select.setOnClickListener(view -> {     // Button 點擊事件
            mStartForResult.launch(
                    new Intent(this, MainActivity2.class)
            );  // 透過 Intent 切換至 MainActivity2
        });
    }
}
