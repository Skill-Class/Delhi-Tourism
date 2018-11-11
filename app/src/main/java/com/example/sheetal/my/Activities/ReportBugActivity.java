package com.example.sheetal.my.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sheetal.my.R;

public class ReportBugActivity extends AppCompatActivity {

    ImageView backBtn;
    ListView listView;

    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bug);

        backBtn = findViewById(R.id.imageView9);

        listView = findViewById(R.id.listView);

        btnSubmit = findViewById(R.id.button3);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReportBugActivity.this, "Added. Thank you for your feedback.", Toast.LENGTH_SHORT).show();
            }
        });

        String[] items = {"What is Delhi Yatri?", "What are the privacy policies?", "Why should we use this app?", "Future scope?"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    Toast.makeText(ReportBugActivity.this, "Wait..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReportBugActivity.this, PrivacyActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
                }
                if (i == 0) {
                    Toast.makeText(ReportBugActivity.this, "Wait..", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(ReportBugActivity.this).create();
                    //  alertDialog.setContentView(R.layout.menudialog);
                    alertDialog.setTitle("Delhi Yatri");
                    alertDialog.setIcon(R.drawable.icon);
                    alertDialog.setCancelable(true);
                    alertDialog.setMessage("A huge part of the success of tourism and Travel has been played by Mobile Apps. As the partnership between the tourism industry and technology grows both of them have earned several grand slams. The days of guidebooks, maps, and other printed stuff have been replaced by Mobile Apps for Tourism and Travel Industry that come with functionalties. So We developed delhi yatri (An Andoroid app) to make your tourism experience better.Thank you. :) ");
                    alertDialog.setButton(alertDialog.BUTTON_POSITIVE, "Ok,Great.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.cancel();
                        }
                    });
                    alertDialog.show();
                }
                if (i == 2) {
                    Toast.makeText(ReportBugActivity.this, "Wait..", Toast.LENGTH_SHORT).show();
                    final AlertDialog alertDialog = new AlertDialog.Builder(ReportBugActivity.this).create();
                    //  alertDialog.setContentView(R.layout.menudialog);
                    alertDialog.setTitle("Delhi Yatri");
                    alertDialog.setIcon(R.drawable.icon);
                    alertDialog.setCancelable(true);
                    alertDialog.setMessage("This app has features like AI and panorama View. You can chat will others to know more about places. You can also add your favourite places to the list. Thank you. :) ");
                    alertDialog.setButton(alertDialog.BUTTON_POSITIVE, "Ok,Great.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.cancel();
                        }
                    });
                    alertDialog.show();
                }
                if (i == 3) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(ReportBugActivity.this).create();
                    //  alertDialog.setContentView(R.layout.menudialog);
                    alertDialog.setTitle("Delhi Yatri");
                    alertDialog.setIcon(R.drawable.icon);
                    alertDialog.setCancelable(true);
                    alertDialog.setMessage("For Now this app is limited till delhi. We would like to add more AI feature to make this app more advance and also we are thinking to add all possible cities of india. Thank you. :) ");
                    alertDialog.setButton(alertDialog.BUTTON_POSITIVE, "Ok,Great.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.cancel();
                        }
                    });
                    alertDialog.show();
                }
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportBugActivity.this, MainHomeScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });

    }
}
