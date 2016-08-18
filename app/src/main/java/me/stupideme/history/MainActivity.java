package me.stupideme.history;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import me.stupideme.history.view.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    public static Handler mHandler;
    public static List<Event> today;
    public static List<Event> yesterday;
    public static List<Event> beforeyesterday;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        mHandler = new Handler();
        today = new ArrayList<>();
        yesterday = new ArrayList<>();
        beforeyesterday = new ArrayList<>();
        runnable = new Runnable() {
            @Override
            public void run() {
                startService(new Intent(MainActivity.this, DownloadService.class));
            }
        };
        mHandler.postDelayed(runnable, 0);

        List<Fragment> list = new ArrayList<>();
        list.add(TodayFragment.newInstance(today));
        list.add(YesterdayFragment.newInstance(yesterday));
        list.add(BeforeYesterdayFragment.newInstance(beforeyesterday));


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        SlidingTabLayout slidingTabs = (SlidingTabLayout) findViewById(R.id.sliding_tab_layout);
        slidingTabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        slidingTabs.setSelectedIndicatorColors(Color.WHITE);
        slidingTabs.setCustomTabView(R.layout.item_sliding_tab, 0);//自定义tab布局，为了选项等宽布满tab
        slidingTabs.setDividerColors(getResources().getColor(R.color.colorPrimary));

        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(adapter);
        slidingTabs.setViewPager(pager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
