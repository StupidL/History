package me.stupideme.history;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

public class TodayFragment extends Fragment {

    private static List<Event> mList;
    public static ListViewAdapter adapter;
    public static final String ACTION = "me.stupidme.history.ACTION.TODAY";

    public TodayFragment() {
        // Required empty public constructor
    }

    public static TodayFragment newInstance(List<Event> list) {
        // mList = new ArrayList<>();
        mList = list;
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        Intent intent = new Intent(getActivity(), DownloadService.class);
        intent.setAction(ACTION);
        getActivity().startService(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);
        ListView listView = (ListView) view.findViewById(R.id.today_list_view);
        adapter = new ListViewAdapter(mList, getActivity());
        listView.setAdapter(adapter);
        return view;
    }

}
