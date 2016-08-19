package me.stupideme.history;

import android.app.IntentService;
import android.content.Intent;

import java.util.Calendar;

public class DownloadService extends IntentService {

    public DownloadService() {
        super("DownloadService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.DATE, -1);
        int month2 = calendar.get(Calendar.MONTH) + 1;
        int day2 = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.DATE, -1);
        int month3 = calendar.get(Calendar.MONTH) + 1;
        int day3 = calendar.get(Calendar.DAY_OF_MONTH);

        String action = intent.getAction();
        if (action.contentEquals(TodayFragment.ACTION)) {
            JuheDemo.getRequest1(String.valueOf(month), String.valueOf(day), MainActivity.today);
            MainActivity.mHandler.sendEmptyMessage(0x100);
        }
        if (action.contentEquals(YesterdayFragment.ACTION)) {
            JuheDemo.getRequest1(String.valueOf(month2), String.valueOf(day2), MainActivity.yesterday);
            MainActivity.mHandler.sendEmptyMessage(0x200);
        }
        if (action.contentEquals(BeforeYesterdayFragment.ACTION)) {
            JuheDemo.getRequest1(String.valueOf(month3), String.valueOf(day3), MainActivity.beforeyesterday);
            MainActivity.mHandler.sendEmptyMessage(0x300);
        }


    }


}
