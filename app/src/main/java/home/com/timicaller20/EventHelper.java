package home.com.timicaller20;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventHelper extends SQLiteOpenHelper {
    public EventHelper(Context context){
        this(context,"Event",null,1);
    }
    private EventHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE EVENT(" +
                "COL_id INTEGER PRIMARY KEY autoincrement,"+
                "COL_S INTEGER NOT NULL,"+
                "COL_NAME VARCHAR NOT NULL,"+
                "COL_ACTIVE VARCHAR ,"+
                "COL_HOUR INTEGER NOT NULL,"+
                "COL_MIN INTEGER NOT NULL,"+
                "COL_HINT VARCHAR ,"+
                "COL_PHONE VARCHAR,"+
                "COL_IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
