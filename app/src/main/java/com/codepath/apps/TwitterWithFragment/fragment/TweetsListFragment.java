package com.codepath.apps.TwitterWithFragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.TwitterWithFragment.R;
import com.codepath.apps.TwitterWithFragment.TweetsArrayAdapter;
import com.codepath.apps.TwitterWithFragment.models.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Dorvilier on 8/11/2017.
 */

public class TweetsListFragment extends Fragment {

    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        lvTweets =(ListView) v.findViewById(R.id.lvTweets); // find the listview

        lvTweets.setAdapter(aTweets); // connect adapter to listview
        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tweets = new ArrayList<>(); // create the array list data source
        aTweets = new TweetsArrayAdapter(getActivity(), tweets); //construct the adapter in the listview

    }

    public void addAll(List<Tweet> tweets){
        aTweets.addAll(tweets);

    }
}
