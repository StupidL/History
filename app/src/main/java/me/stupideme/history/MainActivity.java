package me.stupideme.history;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import me.stupideme.history.view.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    public static Handler mHandler;
    public static List<Event> today = new ArrayList<>();
    public static List<Event> yesterday = new ArrayList<>();
    public static List<Event> beforeyesterday = new ArrayList<>();
    //public static List<Event> mList = new ArrayList<>();
    List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("历史上的今天");
        setSupportActionBar(toolbar);


        list.add(TodayFragment.newInstance(today));
        list.add(YesterdayFragment.newInstance(yesterday));
        list.add(BeforeYesterdayFragment.newInstance(beforeyesterday));

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        SlidingTabLayout slidingTabs = (SlidingTabLayout) findViewById(R.id.sliding_tab_layout);
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        if (pager != null) {
            pager.setAdapter(adapter);
        }
        if (slidingTabs != null) {
            slidingTabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            slidingTabs.setSelectedIndicatorColors(Color.WHITE);
            slidingTabs.setCustomTabView(R.layout.item_sliding_tab, 0);//自定义tab布局，为了选项等宽布满tab
            slidingTabs.setDividerColors(getResources().getColor(R.color.colorPrimary));
            slidingTabs.setViewPager(pager);
        }


        mHandler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0x100:
                        TodayFragment.adapter.notifyDataSetChanged();
                        break;
                    case 0x200:
                        YesterdayFragment.adapter.notifyDataSetChanged();
                        break;
                    case 0x300:
                        BeforeYesterdayFragment.adapter.notifyDataSetChanged();
                        break;
                }

            }
        };


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

    @Override
    public void onDestroy(){
        super.onDestroy();
        today.clear();
        yesterday.clear();
        beforeyesterday.clear();
        list.clear();
    }
}
