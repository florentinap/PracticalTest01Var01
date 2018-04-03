package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01.general.Constants;
import practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01.service.PracticalTest01Var01Service;

public class PracticalTest01Var01MainActivity extends AppCompatActivity {

    Button north, east, south, west, navigate;
    TextView outputText;
    String output = "";
    int pointsNr = 0;

    private int serviceStatus = Constants.SERVICE_STOPPED;
    private IntentFilter intentFilter = new IntentFilter();

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.north:
                    output += north.getText().toString() + ", ";
                    pointsNr ++;
                    break;
                case R.id.east:
                    output += east.getText().toString() + ", ";
                    pointsNr ++;
                    break;
                case R.id.south:
                    output += south.getText().toString() + ", ";
                    pointsNr ++;
                    break;
                case R.id.west:
                    output += west.getText().toString() + ", ";
                    pointsNr ++;
                    break;
                case R.id.navigate:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01SecondaryActivity.class);
                    String op_second_activ = outputText.getText().toString();
                    intent.putExtra(Constants.POINTSSTRING, op_second_activ);
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
            outputText.setText(output);

            if (pointsNr >= Constants.PRAG && serviceStatus == Constants.SERVICE_STOPPED) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var01Service.class);
                intent.putExtra(Constants.SERVICESTRING, output);
                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;
            }
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[TEST]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);

        north = findViewById(R.id.north);
        north.setOnClickListener(buttonClickListener);
        east = findViewById(R.id.east);
        east.setOnClickListener(buttonClickListener);
        south = findViewById(R.id.south);
        south.setOnClickListener(buttonClickListener);
        west = findViewById(R.id.west);
        west.setOnClickListener(buttonClickListener);
        navigate = findViewById(R.id.navigate);
        navigate.setOnClickListener(buttonClickListener);

        outputText = findViewById(R.id.outputText);

        intentFilter.addAction(Constants.actionType);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(Constants.POINTSNR, String.valueOf(pointsNr));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("pointsNR")) {
            String points = savedInstanceState.getString("pointsNR");
            pointsNr = Integer.valueOf(points);
            Toast.makeText(this, points, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var01Service.class);
        stopService(intent);
        super.onDestroy();
    }

}
