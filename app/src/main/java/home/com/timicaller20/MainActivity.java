package home.com.timicaller20;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventHelper helper;
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddNewEvent.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        helper = new EventHelper(this);
        Cursor cursor = helper.getReadableDatabase()
                .query("EVENT",null,null,null,null,null,null);
        adapter = new EventAdapter(cursor);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Cursor cursor = helper.getReadableDatabase()
                .query("EVENT",null,null,null,null,null,null);
        adapter = new EventAdapter(cursor);
        recyclerView.setAdapter(adapter);
    }

    public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder>implements OnClickListener{
        Cursor cursor;

        public EventAdapter(Cursor cursor) {
            this.cursor=cursor;
        }

        @NonNull
        @Override
        public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.event_item,parent,false);
            return new EventHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final EventHolder holder, final int position) {
            cursor.moveToPosition(position);
            String name = cursor.getString(cursor.getColumnIndex("COL_NAME"));
            int hr = cursor.getInt(cursor.getColumnIndex("COL_HOUR"));
            int min = cursor.getInt(cursor.getColumnIndex("COL_MIN"));
            holder.nametext.setText(name);
            holder.hrtext.setText(String.valueOf(hr));
            holder.mintext.setText(String.valueOf(min));
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//
                    Intent todetais = new Intent(MainActivity.this, DetailActivity.class);
                    cursor.moveToPosition(position);
//                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("COL_NAME"));
                    int hour = cursor.getInt(cursor.getColumnIndex("COL_HOUR"));
                    int min = cursor.getInt(cursor.getColumnIndex("COL_MIN"));
                    String hint = cursor.getString(cursor.getColumnIndex("COL_HINT"));
                    String phone = cursor.getString(cursor.getColumnIndex("COL_PHONE"));

//                    todetais.putExtra("ID", 1);
                    todetais.putExtra("NAME", name);
                    todetais.putExtra("HOUR",hour);
                    todetais.putExtra("MIN", min);
                    todetais.putExtra("HINT", hint);
                    todetais.putExtra("PHONE", phone);
//
                    startActivity(todetais);
                }
            });

        }

        @Override
        public int getItemCount() {
            return cursor.getCount();
        }

        @Override
        public void onClick(View v) {

        }

        public class EventHolder extends RecyclerView.ViewHolder{
            TextView nametext;
            TextView hrtext;
            TextView mintext;
            Button bt;
            public EventHolder(View itemView) {
                super(itemView);
                nametext = itemView.findViewById(R.id.tv_name);
                hrtext = itemView.findViewById(R.id.tv_hour);
                mintext = itemView.findViewById(R.id.tv_min);

            }
        }
    }

}
