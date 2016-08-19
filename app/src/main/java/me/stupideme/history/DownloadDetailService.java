package me.stupideme.history;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadDetailService extends IntentService {

    public DownloadDetailService() {
        super("DownloadDetailService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String id = intent.getStringExtra("id");
        String picUrl = intent.getStringExtra("picUrl");
        try {
            URL url = new URL(picUrl);
            InputStream is = url.openStream();
            EventDetailActivity.bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EventDetailActivity.content = JuheDemo.getRequest2(id);
        Intent i = new Intent();
        i.setAction("me.stupidme.history.ACTION.LOAD_FINISHED");
        sendBroadcast(i);

    }


}
