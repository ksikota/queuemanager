package com.fire.branden.prototype;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class QueryActivity extends AppCompatActivity {
    private int[] checkBox = new int[24];
    private int[] consultant = new int[6];
    private int time;
    private int consultantId;

    public void populateListViewFromDB() {
        Cursor cursor = MainActivity.myDb.getAllRows();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameET = (EditText) findViewById(R.id.editTextName);
                EditText cellET = (EditText) findViewById(R.id.editTextNumber);

                String name = nameET.getText().toString();
                String cell = cellET.getText().toString();
                if(name.length() != 0) {
                    if (isCellNumber(cell)) {
                        MainActivity.myDb.insertRow(name, cell, checkBox, getDetails(), calculateTime(), getStringTime(), getConsultantId());

                        MainActivity.noEntry.setText(null);
                        populateListViewFromDB();
                        String textMessage = name + "\n\n" +
                                "Your position in the queue has been saved, you will be served in " +
                                getStringTime();
                        SmsManager.getDefault().sendTextMessage(cell, null, textMessage, null, null);

                        finish();
                    }
                }else{
                    Toast.makeText(QueryActivity.this, "No name inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private boolean isCellNumber(String cell) {
        if (cell.length() == 0) {
            Toast.makeText(QueryActivity.this, "No number inserted", Toast.LENGTH_LONG).show();
            return false;
        }
        for (int i = 0; i < 10; i++) {
            if (!Character.isDigit(cell.charAt(0))) {
                Toast.makeText(QueryActivity.this, "No characters in cellnumber", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if (cell.length() != 10) {
            Toast.makeText(QueryActivity.this, "Cellnumber must be 10 numbers long", Toast.LENGTH_LONG).show();
            return false;
        }
        if (cell.charAt(0) != '0') {
            Toast.makeText(QueryActivity.this, "Cellnumber must start with 0", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private String getDetails() {
        String temp = "";
        int count = 0;
        while (temp.equals("") && count < 6) {
            if (checkBox[count++] == 1) {
                temp = "Services";
                count = 6;
            }
        }

        while ((temp.equals("") || temp.equals("Services")) && count < checkBox.length) {

            if (checkBox[count++] == 1) {
                if (temp.equals("")) {
                    return "Sales";
                } else {
                    return temp + " and Sales";
                }
            }
        }
        return temp;
    }

    private void process(int pos) {
        if (checkBox[pos] == 0) {
            checkBox[pos] = 1;
            time += 15;
        } else {
            checkBox[pos] = 0;
            time -= 15;
        }
    }

    public void Service(View view) {
        int id = view.getId();

        if (id == R.id.checkboxServiceA) {
            process(0);
        } else if (id == R.id.checkboxServiceB) {
            process(1);
        } else if (id == R.id.checkboxServiceC) {
            process(2);
        } else if (id == R.id.checkboxServiceD) {
            process(3);
        } else if (id == R.id.checkboxServiceE) {
            process(4);
        } else if (id == R.id.checkboxServiceF) {
            process(5);
        }
    }

    public void Sale(View view) {
        int id = view.getId();

        if (id == R.id.checkboxSale1) {
            process(6);
        } else if (id == R.id.checkboxSale2) {
            process(7);
        } else if (id == R.id.checkboxSale3) {
            process(8);
        } else if (id == R.id.checkboxSale4) {
            process(9);
        } else if (id == R.id.checkboxSale5) {
            process(10);
        } else if (id == R.id.checkboxSale6) {
            process(11);
        } else if (id == R.id.checkboxSale7) {
            process(12);
        } else if (id == R.id.checkboxSale8) {
            process(13);
        } else if (id == R.id.checkboxSale9) {
            process(14);
        } else if (id == R.id.checkboxSale10) {
            process(15);
        } else if (id == R.id.checkboxSale11) {
            process(16);
        } else if (id == R.id.checkboxSale12) {
            process(17);
        } else if (id == R.id.checkboxSale13) {
            process(18);
        } else if (id == R.id.checkboxSale14) {
            process(19);
        } else if (id == R.id.checkboxSale15) {
            process(20);
        } else if (id == R.id.checkboxSale16) {
            process(21);
        } else if (id == R.id.checkboxSale17) {
            process(22);
        } else if (id == R.id.checkboxSale18) {
            process(23);
        }
    }

    public int calculateTime() {

        MainActivity.totalTime = 0;
        Cursor cursor = MainActivity.myDb.getAllRows();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            consultant[cursor.getInt(DBAdapter.COL_CONSULTANT) - 1] = +cursor.getInt(DBAdapter.COL_TIME);
            cursor.moveToNext();
        }

        MainActivity.totalTime = consultant[0];
        setConsultantId(1);

        for (int i = 1; i < 6; i++) {
            if (consultant[i] < MainActivity.totalTime) {
                MainActivity.totalTime = consultant[i];
                setConsultantId(i + 1);

            }
        }
        return MainActivity.totalTime + time + 5;
    }


    public String getStringTime() {
        int time = calculateTime();
        int minute = time % 60;
        int count = 0;

        while (time > minute) {
            count++;
            time -= 60;
        }
        return count + "h" + minute + "m";
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }
}
