package home.com.timicaller20;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    String edname,edhint,edphone;
    int edhour,edmin,edid;
    private EventHelper eventHelper;
    AlarmCenter ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


       Intent intent = getIntent();
//       edid = intent.getIntExtra("ID",0);
       edname = intent.getStringExtra("NAME");
       edhint = intent.getStringExtra("HINT");
       edphone = intent.getStringExtra("PHONE");
       edhour = intent.getIntExtra("HOUR",0);
       edmin = intent.getIntExtra("MIN",0);
       ((TextView) findViewById(R.id.name)).setText(edname);
       ((TextView) findViewById(R.id.hint)).setText(edhint);
       ((TextView) findViewById(R.id.phone)).setText(edphone);
       ((TextView)findViewById(R.id.hour)).setText(String.valueOf(edhour));
       ((TextView)findViewById(R.id.min)).setText(String.valueOf(edmin));
        }

    public void Delete(View view){
        eventHelper = new EventHelper(this);
//        ContentValues values = new ContentValues();
//        values.put("COL_NAME", edname);
//        values.put("COL_S", 1);
//        values.put("COL_ACTIVE", true);
//        values.put("COL_HOUR", edhour);
//        values.put("COL_MIN", edmin);
//        values.put("COL_HINT", edhint);
//        values.put("COL_PHONE", edphone);

//        long check = eventHelper.getWritableDatabase().update(
//                "EVENT", values, "COL_NAME =?",new String[]{edname});
        long check = eventHelper.getWritableDatabase().delete(
                "EVENT", "COL_NAME =?",new String[]{edname});
//
        PendingIntent sender;
        AlarmManager alarmManager;
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(this,AlarmReceiver.class);
        myIntent.setAction("1013");
//        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
//        if (alarmManager!= null) {
//            alarmManager.cancel(pendingIntent.getBroadcast(this, 1, myIntent, 0));
////當pendingintent的requestcode=1時，鬧鐘要停
//            AlarmManager.OnAlarmListener
//            alarmManager.cancel();
//        }
//https://stackoverflow.com/questions/17615986/how-to-stop-an-alarm-in-android


        sender = PendingIntent.getBroadcast(this.getApplicationContext(), -1, myIntent, PendingIntent.FLAG_UPDATE_CURRENT| Intent.FILL_IN_DATA);
        alarmManager.cancel(sender);


        Toast.makeText(this, 1+" ALARM OFF", Toast.LENGTH_SHORT).show();


        Intent tomain = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(tomain);
    }
    public void Edit(View view){
        //將該筆資料的鬧鐘關閉
        //轉跳


        Intent toedit = new Intent(DetailActivity.this, EditActivity.class);

//        toedit.putExtra("ID", edid);
        toedit.putExtra("NAME", edname);
        toedit.putExtra("HOUR",edhour);
        toedit.putExtra("MIN", edmin);
        toedit.putExtra("HINT", edhint);
        toedit.putExtra("PHONE", edphone);

        startActivity(toedit);
    }
}
