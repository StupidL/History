package me.stupideme.history;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class YesterdayFragment extends Fragment {
    private static List<Event> mList;

    public YesterdayFragment() {
        // Required empty public constructor
    }

    public static YesterdayFragment newInstance(List<Event> list) {
        mList = new ArrayList<>();
        mList = list;
        YesterdayFragment fragment = new YesterdayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yesterday, container, false);
        ListView listView = (ListView) view.findViewById(R.id.yesterday_list_view);
        listView.setAdapter(new ListViewAdapter(mList));
        return view;
    }

}
