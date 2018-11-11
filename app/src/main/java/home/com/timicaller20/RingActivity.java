package home.com.timicaller20;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RingActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CALL = 5;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        mediaPlayer = MediaPlayer.create(this, R.raw.wil);
        mediaPlayer.start();
        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("NAME");
        String phone = intent1.getStringExtra("PHONE");
        ((TextView) findViewById(R.id.tv_r_name)).setText(name);
        ((TextView) findViewById(R.id.tv_r_phone)).setText(phone);


    }

    public void call(View view) {
        mediaPlayer.stop();
        int premission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);
        if (premission == PackageManager.PERMISSION_GRANTED) {

            TextView textView = (TextView) findViewById(R.id.tv_r_phone);
            String phoneNumber = "tel:" + textView.getText().toString();
            makecall(phoneNumber);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL);
        }

    }

    private void makecall(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber)));
    }

    public void stop(View view){
        mediaPlayer.stop();
        finish();

        Intent intent3=new Intent(this,MainActivity.class);
        startActivity(intent3);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE_CALL){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                TextView textView = (TextView) findViewById(R.id.tv_r_phone);
                String phoneNumber = String.format("tel: %s", textView.getText().toString());
                makecall(phoneNumber);
            }
        }

    }
}
