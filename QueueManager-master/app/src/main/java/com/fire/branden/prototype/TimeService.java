package com.fire.branden.prototype;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.SimpleCursorAdapter;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {


    public TimeService() {
        super();
    }

    DBAdapter myDb = MainActivity.myDb;

    public void populateListViewFromDB() {
        Cursor cursor = myDb.getAllRows();
        //startManagingCursor(cursor);
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

        MainActivity.myCursorAdapter =
                new SimpleCursorAdapter(
                        this,
                        R.layout.list_item_queue,
                        cursor,
                        fromFieldNames,
                        toViewIDs
                );
        MainActivity.myList.setAdapter(MainActivity.myCursorAdapter);
    }

    // constant
    public static final long NOTIFY_INTERVAL = 1 * 1000; // 2 seconds

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {

        // cancel if already existed
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
    }

    public class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                              @Override
                              public void run() {
                                  // display toast

                                  if (myDb != null) {
                                      if (myDb.getSize() != 0) {
                                          MainActivity.noEntry.setText(null);
                                          Cursor cursor = myDb.getAllRows();
                                          cursor.moveToFirst();
                                          while (!cursor.isAfterLast()) {

                                              String name = cursor.getString(DBAdapter.COL_NAME);
                                              String cell = cursor.getString(DBAdapter.COL_CELL);
                                              if (cursor.getInt(DBAdapter.COL_TIME) == 15) {
                                                  String textMessage = name + "\n\n" +
                                                          "you have 15 minutes remaining in the queue";
                                                  SmsManager.getDefault().sendTextMessage(cell, null, textMessage, null, null);

                                              } else if (cursor.getInt(DBAdapter.COL_TIME) == 10) {

                                                  String textMessage = name + "\n\n" +
                                                          "you have 10 minutes remaining in the queue";
                                                  SmsManager.getDefault().sendTextMessage(cell, null, textMessage, null, null);
                                              }

                                              if (cursor.getInt(DBAdapter.COL_TIME) <= 5) {
                                                  String textMessage = name + "\n\n" +
                                                          "you have 5 minutes remaining in the queue";
                                                  SmsManager.getDefault().sendTextMessage(cell, null, textMessage, null, null);

                                                  int[] box = new int[]{
                                                          cursor.getInt(DBAdapter.COL_SERVICE_A),
                                                          cursor.getInt(DBAdapter.COL_SERVICE_B),
                                                          cursor.getInt(DBAdapter.COL_SERVICE_C),
                                                          cursor.getInt(DBAdapter.COL_SERVICE_D),
                                                          cursor.getInt(DBAdapter.COL_SERVICE_E),
                                                          cursor.getInt(DBAdapter.COL_SERVICE_F),

                                                          cursor.getInt(DBAdapter.COL_SALE_1),
                                                          cursor.getInt(DBAdapter.COL_SALE_2),
                                                          cursor.getInt(DBAdapter.COL_SALE_3),
                                                          cursor.getInt(DBAdapter.COL_SALE_4),
                                                          cursor.getInt(DBAdapter.COL_SALE_5),
                                                          cursor.getInt(DBAdapter.COL_SALE_6),
                                                          cursor.getInt(DBAdapter.COL_SALE_7),
                                                          cursor.getInt(DBAdapter.COL_SALE_8),
                                                          cursor.getInt(DBAdapter.COL_SALE_9),
                                                          cursor.getInt(DBAdapter.COL_SALE_10),
                                                          cursor.getInt(DBAdapter.COL_SALE_11),
                                                          cursor.getInt(DBAdapter.COL_SALE_12),
                                                          cursor.getInt(DBAdapter.COL_SALE_13),
                                                          cursor.getInt(DBAdapter.COL_SALE_14),
                                                          cursor.getInt(DBAdapter.COL_SALE_15),
                                                          cursor.getInt(DBAdapter.COL_SALE_16),
                                                          cursor.getInt(DBAdapter.COL_SALE_17),
                                                          cursor.getInt(DBAdapter.COL_SALE_18),
                                                  };

                                                  long temp = System.currentTimeMillis();
                                                  Date d = new Date(temp);
                                                  //public long insertRow(String name, String cell, int[] check, String desc, String strTime, int consultant) {
                                                  MainActivity.myCompletedDb.insertRow(
                                                          name,
                                                          cell,
                                                          box,
                                                          cursor.getString(DBAdapter.COL_DESC),
                                                          d.getHours() + "h" + d.getMinutes() + "m",
                                                          cursor.getInt(DBAdapter.COL_CONSULTANT));
                                                  myDb.deleteRow(cursor.getLong(DBAdapter.COL_ROWID));
                                              } else {
                                                  //Getting values from cursor
                                                  int time = cursor.getInt(DBAdapter.COL_TIME) - 5;
                                                  myDb.updateTime(time, getStringTime(time), cursor.getLong(DBAdapter.COL_ROWID));
                                              }
                                              populateListViewFromDB();
                                              cursor.moveToNext();

                                          }
                                      } else {
                                          MainActivity.noEntry.setText("No Entry In Queue");
                                      }
                                  }
                              }
                          }
            );
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }


    @Override
    public boolean stopService(Intent name) {
        return false;
    }

    public String getStringTime(int paramInt) {
        int time = paramInt;
        int minute = time % 60;
        int count = 0;

        while (time > minute) {
            count++;
            time -= 60;
        }
        return count + "h" + minute + "m";
    }
}