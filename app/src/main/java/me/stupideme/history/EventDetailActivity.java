package me.stupideme.history;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailActivity extends AppCompatActivity {

    public static String content;
    public static Bitmap bitmap;
    private static TextView textView;
    private static ImageView imageView;
    public static MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        receiver = new MyReceiver();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = intent.getStringExtra("picUrl");
        Intent i = new Intent(EventDetailActivity.this, DownloadDetailService.class);
        i.putExtra("picUrl", url);
        i.putExtra("id", id);
        startService(i);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventDetailActivity.super.onBackPressed();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.backdrop);
        textView = (TextView) findViewById(R.id.des);

        IntentFilter filter = new IntentFilter();
        filter.addAction("me.stupidme.history.ACTION.LOAD_FINISHED");
        registerReceiver(receiver, filter);

    }

    public static class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (bitmap != null)
                imageView.setImageBitmap(bitmap);
            else
                imageView.setImageResource(R.drawable.nav_header);
            textView.setText(content);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
