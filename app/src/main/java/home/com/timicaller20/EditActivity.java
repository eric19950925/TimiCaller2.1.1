package home.com.timicaller20;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {
//    AlarmCenter ac;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    String name,hint,phone;
    int hour,min,id;
    private TextView etname;
    private EditText ethour;
    private EditText etmin;
    private EditText ethint;
    private EditText etphone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
//
        name = intent.getStringExtra("NAME");
        hint = intent.getStringExtra("HINT");
        phone = intent.getStringExtra("PHONE");
        hour = intent.getIntExtra("HOUR",0);
        min = intent.getIntExtra("MIN",0);

        ((TextView) findViewById(R.id.tv_name)).setText(name);
        ((EditText) findViewById(R.id.et_hour)).setText(String.valueOf(hour));
        ((EditText) findViewById(R.id.et_min)).setText(String.valueOf(min));
        ((EditText) findViewById(R.id.et_hint)).setText(hint);
        ((EditText) findViewById(R.id.et_phone)).setText(phone);
        etname = findViewById(R.id.tv_name);
        ethour = findViewById(R.id.et_hour);
        etmin = findViewById(R.id.et_min);
        ethint = findViewById(R.id.et_hint);
        etphone = findViewById(R.id.et_phone);
        //alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }
    public void update(View view) {

        String name = etname.getText().toString();
        int hour = Integer.parseInt(ethour.getText().toString());
        int min = Integer.parseInt(etmin.getText().toString());
        String hint = ethint.getText().toString();
        String phone = etphone.getText().toString();

        EventHelper helper = new EventHelper(this);
        ContentValues values = new ContentValues();
        values.put("COL_NAME", name);
        //values.put("COL_S", 0);
        values.put("COL_ACTIVE", true);
        values.put("COL_HOUR", hour);
        values.put("COL_MIN", min);
        values.put("COL_HINT", hint);
        values.put("COL_PHONE", phone);

        long check = helper.getWritableDatabase().update(
                "EVENT", values, "COL_NAME =?",new String[]{name});


        if (check > -1) {
            Toast.makeText(this, "Update Successful", Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "Update Failure", Toast.LENGTH_LONG).show();


        long time;
        Toast.makeText(this, "ALARM ON", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setAction("0925");
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
        if(System.currentTimeMillis()>time)
        {
            if (calendar.AM_PM == 0)
                time = time + (1000*60*60*12);
            else
                time = time + (1000*60*60*24);
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);

        Intent tomain = new Intent(EditActivity.this, MainActivity.class);
        startActivity(tomain);

    }
}
