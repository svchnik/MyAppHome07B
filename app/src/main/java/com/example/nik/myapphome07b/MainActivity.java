package com.example.nik.myapphome07b;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String CUSTOM_MYINTENTA = "com.example.nik.myapphome07b.CUSTOM_MYINTENTA";
    private final static String CUSTOM_MYINTENTB = "com.example.nik.myapphome07b.CUSTOM_MYINTENTB";
    private static final int REQUEST_KOD_A = 16;
    private static final int REQUEST_KOD_B = 17;

    TextView textViewFirst;
    TextView textViewSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewFirst = (TextView) findViewById(R.id.textFirst);
        textViewSecond = (TextView) findViewById(R.id.textSecond);

    }

    public void OnClick (View v){
        Intent intent;
        switch (v.getId()){
            case R.id.buttonFirst:
                intent = new Intent(CUSTOM_MYINTENTA);
                intent.putExtra("KeyResA",R.string.strIntA);
                //startActivityForResult(intent, REQUEST_KOD_A, null);
                checkExistansActivity(intent, REQUEST_KOD_A);
                break;

            case R.id.buttonSecond:
                intent = new Intent(CUSTOM_MYINTENTB);
                intent.putExtra("KeyResB",R.string.strIntB);
                //startActivityForResult(intent, REQUEST_KOD_B, null);
                checkExistansActivity(intent, REQUEST_KOD_B);
                break;

            case R.id.buttonReset:
                textViewFirst.setText(R.string.strZero);
                textViewSecond.setText(R.string.strZero);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if(resultCode == RESULT_OK) {
                if(requestCode == REQUEST_KOD_A) {
                    String text = data.getStringExtra("Result_A");
                    textViewFirst.setText(text);
                }

                if(requestCode == REQUEST_KOD_B) {
                    String text = data.getStringExtra("Result_B");
                    textViewSecond.setText(text);
                }
            }

    }



    private void checkExistansActivity(Intent intent, int requestCode ){

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            startActivityForResult(intent, requestCode, null);
            Log.d("LOG", "-startActivityForResult-");
        } else {
            Toast.makeText(this, "Activity is not exists!", Toast.LENGTH_SHORT).show();
        }

    }

}
