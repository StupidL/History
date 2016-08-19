package me.stupideme.history;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BeforeYesterdayFragment extends Fragment {

    private static List<Event> mList;
    public static ListViewAdapter adapter;
    public static final String ACTION = "me.stupidme.history.ACTION.BEFORE_YESTERDAY";

    public BeforeYesterdayFragment() {
        // Required empty public constructor
    }

    public static BeforeYesterdayFragment newInstance(List<Event> list) {
        mList = new ArrayList<>();
        mList = list;
        BeforeYesterdayFragment fragment = new BeforeYesterdayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        Intent intent = new Intent(getActivity(),DownloadService.class);
        intent.setAction(ACTION);
        getActivity().startService(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_before_yesterday, container, false);
        ListView listView = (ListView) view.findViewById(R.id.before_yesterday_list_view);

        adapter = new ListViewAdapter(mList,getActivity());
        listView.setAdapter(adapter);
        return view;
    }

}
