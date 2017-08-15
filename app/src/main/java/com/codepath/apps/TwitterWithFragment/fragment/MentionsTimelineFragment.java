package com.codepath.apps.TwitterWithFragment.fragment;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.TwitterWithFragment.TwitterApplication;
import com.codepath.apps.TwitterWithFragment.TwitterClient;
import com.codepath.apps.TwitterWithFragment.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jonathan Dorvilier on 8/12/2017.
 */

public class MentionsTimelineFragment extends TweetsListFragment {

    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeline();
    }

    private void populateTimeline() {

        client.getMentionsTimeline(new JsonHttpResponseHandler(){
            // success


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                //super.onSuccess(statusCode, headers, json);
                Log.d("DEBUG",json.toString());

                //ArrayList<Tweet> tweets =Tweet.fromArray(json);
                addAll(Tweet.fromJSONArray(json));
            }


            // Faillure


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.d("DEBUG",errorResponse.toString());
            }
        });




    }
}
