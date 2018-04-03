package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01.general.Constants;

public class PracticalTest01Var01SecondaryActivity extends AppCompatActivity {

    Button register, cancel;
    TextView outputText;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register:
                    setResult(RESULT_OK);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED);
                    break;
            }
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_secondary);

        register = findViewById(R.id.register);
        register.setOnClickListener(buttonClickListener);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(buttonClickListener);

        outputText = findViewById(R.id.textView);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.POINTSSTRING)) {
            String clicks = intent.getStringExtra(Constants.POINTSSTRING);
            outputText.setText(clicks);
        }
    }
}
