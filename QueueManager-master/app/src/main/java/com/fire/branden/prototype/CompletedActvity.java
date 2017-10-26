package com.fire.branden.prototype;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CompletedActvity extends AppCompatActivity {

    public ListView myList;
    public static TextView noEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_actvity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        noEntry = (TextView) findViewById(R.id.no_entry_completed_textview);
        if (MainActivity.myCompletedDb != null) {
            noEntry.setText("");
        }
        populateListViewFromDB();
        registerListClickCallback();
    }

    public void populateListViewFromDB() {
        Cursor cursor = MainActivity.myCompletedDb.getAllRows();
        startManagingCursor(cursor);
        String[] fromFieldNames = new String[]{
                DBCompletedAdapter.KEY_NAME,
                DBCompletedAdapter.KEY_DESC,
                DBCompletedAdapter.KEY_TIME_COMPLETED,
                DBCompletedAdapter.KEY_CONSULTANT
        };

        final int[] toViewIDs = new int[]{
                R.id.completed_name_textview,
                R.id.completed_details_textview,
                R.id.completed_time_textview,
                R.id.completed_consultant_textview
        };

        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.list_item_completed_queue,
                        cursor,
                        fromFieldNames,
                        toViewIDs
                );
        myList = (ListView) findViewById(R.id.queue_completed_listview);
        myList.setAdapter(myCursorAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_deleted) {
            MainActivity.myCompletedDb.deleteAll();
            populateListViewFromDB();
            ((TextView)findViewById(R.id.no_entry_completed_textview)).setText("No Entry In Query");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void registerListClickCallback() {
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long idInDB) {

                Cursor cursor = MainActivity.myCompletedDb.getAllRows();
                int counter = 0;
                cursor.moveToFirst();
                while(counter++<position){
                    cursor.moveToNext();
                }
                int row = cursor.getInt(DBAdapter.COL_ROWID);
                startActivity(new Intent(CompletedActvity.this, CompletedDetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, row + ""));
            }
        });
    }
}
