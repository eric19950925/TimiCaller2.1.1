package home.com.timicaller20;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Intent intent2 = getIntent();
        String name = intent.getStringExtra("NAME");
        String phone = intent.getStringExtra("PHONE");
        if("0925".equals(intent.getAction())){
            Log.i("test"+name,"Alarm! Wake up! Wake up!");
//            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
            //跳轉到Activity

            Intent intent1=new Intent(context,RingActivity.class);
            //广播跳转到Activity  ，必须给Intent设置标志位Flags
            //Intent的组成部分6
            //Flags Catagroy(分组) Action  Data Extra ComponeName(组件名)
            intent1.putExtra("NAME", name);
            intent1.putExtra("PHONE", phone);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
        if("1013".equals(intent.getAction())){

        }
    }
}
