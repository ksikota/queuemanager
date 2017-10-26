package com.fire.branden.prototype;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class CompletedDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setView() {

        Intent intent = this.getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {

            int position = Integer.parseInt(intent.getStringExtra(Intent.EXTRA_TEXT));

            Cursor cursor = MainActivity.myCompletedDb.getRow(position);
            String name = cursor.getString(DBCompletedAdapter.COL_NAME);
            String consultant = cursor.getInt(DBCompletedAdapter.COL_CONSULTANT) + "";

            int[] box = new int[]{
                    cursor.getInt(DBCompletedAdapter.COL_SERVICE_A),
                    cursor.getInt(DBCompletedAdapter.COL_SERVICE_B),
                    cursor.getInt(DBCompletedAdapter.COL_SERVICE_C),
                    cursor.getInt(DBCompletedAdapter.COL_SERVICE_D),
                    cursor.getInt(DBCompletedAdapter.COL_SERVICE_E),
                    cursor.getInt(DBCompletedAdapter.COL_SERVICE_F),

                    cursor.getInt(DBCompletedAdapter.COL_SALE_1),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_2),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_3),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_4),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_5),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_6),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_7),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_8),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_9),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_10),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_11),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_12),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_13),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_14),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_15),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_16),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_17),
                    cursor.getInt(DBCompletedAdapter.COL_SALE_18)
            };

            String serviceDetails = "";
            String saleDetails = "";

            for (int i = 6; i < box.length; i++) {
                if (box[i] == 1) {
                    saleDetails += "Sale " + (i - 5) + "\n";
                }
            }
            for (int i = 0; i < 6; i++) {
                if (box[i] == 1) {
                    serviceDetails += "Service " + (char) (65 + i) + "\n";
                }
            }

            if(saleDetails.length() == 0){
                saleDetails = "None";
            }
            if(serviceDetails.length() == 0){
                serviceDetails = "None";
            }

            ((TextView) findViewById(R.id.completed_detail_name_textview))
                    .setText(name);
            ((TextView) findViewById(R.id.completed_detail_consultant_textview))
                    .setText(consultant);
            ((TextView) findViewById(R.id.completed_detail_service_textview))
                    .setText(serviceDetails);
            ((TextView) findViewById(R.id.completed_detail_sale_textview))
                    .setText(saleDetails);
        }


    }

}
