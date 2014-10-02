package com.hound.vision.fragmentsexcersise;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MenuFragment extends ListFragment {
    OnMenuFragmentSelectedListener mCallback;
    //strings
    static String[] tasks = {
            "select me from this menu",
            "or me it is cool"
    };

    public interface OnMenuFragmentSelectedListener {
        public void onMenuFragmentSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //support of API 10 or lower
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        //create an array adapter from teh list view with tasks
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, tasks));
    }


    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.content_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnMenuFragmentSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "you must implement OnMenuFragmentSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onMenuFragmentSelected(position);

        //
        getListView().setItemChecked(position, true);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}