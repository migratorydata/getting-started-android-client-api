package com.migratorydata.androidexampleapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.migratorydata.client.*;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private MigratoryDataClient client;

    private TextView textViewStatus;
    private TextView textViewMessage;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStatus = (TextView) findViewById(R.id.status_view_id);
        textViewMessage = (TextView) findViewById(R.id.message_view_id);

        client = new MigratoryDataClient();

        // Define the log listener and verbosity
        client.setLogListener(new MigratoryDataLogListener() {
            @Override
            public void onLog(String log, MigratoryDataLogLevel level) {
                Log.i("MigratoryDataExample", log);
            }
        }, MigratoryDataLogLevel.DEBUG);

        client.setEntitlementToken(Config.token);
        client.setListener(new MigratoryDataListener() {
            @Override
            public void onMessage(final MigratoryDataMessage message) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewMessage.setText(message.getSubject() + " - " + new String(message.getContent()));
                    }
                });
            }

            @Override
            public void onStatus(final String status, final String info) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textViewStatus.setText(status + " - " + info);
                    }
                });
            }
        });

        client.setEncryption(Config.encryption);
        client.setServers(Config.server);

        client.subscribe(Arrays.asList(Config.subject));

        client.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        client.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        client.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        client.disconnect();
    }
}
