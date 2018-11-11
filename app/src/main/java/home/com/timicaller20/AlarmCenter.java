package home.com.timicaller20;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AlarmCenter  extends AppCompatActivity{
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

public AlarmCenter(){
    super();
}

    public static void startalarm() {
//        PendingIntent pendingIntent;
//        AlarmManager alarmManager;
//        long time;
//        //Toast.makeText(context, "ALARM ON", Toast.LENGTH_SHORT).show();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, hr);
//        calendar.set(Calendar.MINUTE, min);
//        Intent intent = new Intent(context, AlarmReceiver.class);
//        intent.setAction("0925");
//
//        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
//        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
//        if(System.currentTimeMillis()>time)
//        {
//            if (calendar.AM_PM == 0)
//                time = time + (1000*60*60*12);
//            else
//                time = time + (1000*60*60*24);
//        }
//        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        //Toast.makeText(getcontext,"start alarm",Toast.LENGTH_LONG);
}
    public static void cancelAlarm(Context context) {

        PendingIntent pendingIntent;
        AlarmManager alarmManager;
        Intent myIntent = new Intent(context, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        alarmManager.cancel(pendingIntent);
    }
}
