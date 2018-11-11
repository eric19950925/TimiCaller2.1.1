package home.com.timicaller20;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class AddNewEvent extends AppCompatActivity {
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    //AlarmCenter ac;
    private EditText etname;
    private EditText ethour;
    private EditText etmin;
    private EditText ethint;
    private EditText etphone;
    //private Bitmap etimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event);
        etname = findViewById(R.id.et_name);
        ethour = findViewById(R.id.et_hour);
        etmin = findViewById(R.id.et_min);
        ethint = findViewById(R.id.et_hint);
        etphone = findViewById(R.id.et_phone);
        //etimage = findViewById(R.id.imageView);


        }
    public void save(View view) {

        String name = etname.getText().toString();
        int hour = Integer.parseInt(ethour.getText().toString());
        int min = Integer.parseInt(etmin.getText().toString());
        String hint = ethint.getText().toString();
        String phone = etphone.getText().toString();
        //int s = 0;

        EventHelper helper = new EventHelper(this);
        ContentValues values = new ContentValues();
        //values.put("CPL_S",s);
        values.put("COL_NAME", name);
        values.put("COL_ACTIVE", true);
        values.put("COL_HOUR", hour);
        values.put("COL_MIN", min);
        values.put("COL_HINT", hint);
        values.put("COL_PHONE", phone);
        //values.put("COL_IMAGE",);
        long id = helper.getWritableDatabase().insert("EVENT", null, values);


        if (id > -1) {
            Toast.makeText(this, "Insert Successful", Toast.LENGTH_LONG).show();
        } else Toast.makeText(this, "Insert Failure", Toast.LENGTH_LONG).show();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long time;
        //Toast.makeText(this, "ALARM ON", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setAction("0925");
        intent.putExtra("NAME", name);
        intent.putExtra("PHONE", phone);
        pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
        if(System.currentTimeMillis()>time)
        {
            if (calendar.AM_PM == 0)
                time = time + (1000*60*60*12);
            else
                time = time + (1000*60*60*24);
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP, time+100, pendingIntent);

        Toast.makeText(this, 1+" ALARM ON", Toast.LENGTH_SHORT).show();

        Intent tomain = new Intent(AddNewEvent.this, MainActivity.class);
        startActivity(tomain);

    }
}
