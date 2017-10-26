package com.fire.branden.prototype;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static DBAdapter myDb;
    public static DBCompletedAdapter myCompletedDb;
    public static Context context;
    public static int totalTime;
    public static ListView myList;
    public static TextView noEntry;
    public static SimpleCursorAdapter myCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = MainActivity.this;
        noEntry = (TextView) findViewById(R.id.no_entry_textview);
        openDB();
        openCompletedDB();
        populateListViewFromDB();
        registerListClickCallback();
        startService(new Intent(this, TimeService.class));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QueryActivity.class));
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_completed){
            startActivity(new Intent(MainActivity.this, CompletedActvity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }





    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();

    }

    private void openCompletedDB(){
        myCompletedDb = new DBCompletedAdapter(this);
        myCompletedDb.open();
    }




    public void populateListViewFromDB() {
        Cursor cursor = myDb.getAllRows();
        startManagingCursor(cursor);
        String[] fromFieldNames = new String[]{
                DBAdapter.KEY_NAME,
                DBAdapter.KEY_DESC,
                DBAdapter.KEY_TIME_STR,
                DBAdapter.KEY_CONSULTANT
        };

        final int[] toViewIDs = new int[]{
                R.id.name_textview,
                R.id.details_textview,
                R.id.time_textview,
                R.id.consultant_textview
        };

        myCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.list_item_queue,
                        cursor,
                        fromFieldNames,
                        toViewIDs
                );
        myList = (ListView) findViewById(R.id.queue_listview);
        myList.setAdapter(myCursorAdapter);
    }

    private void registerListClickCallback() {
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long idInDB) {

                Cursor cursor = myDb.getAllRows();
                int counter = 0;
                cursor.moveToFirst();
                while(counter++<position){
                    cursor.moveToNext();
                }
                int row = cursor.getInt(DBAdapter.COL_ROWID);
                startActivity(new Intent(MainActivity.this, DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, row + ""));
            }
        });
    }

}


