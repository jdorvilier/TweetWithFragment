package com.codepath.apps.MyFisrtTwitterJD;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.codepath.apps.MyFisrtTwitterJD.models.Tweet;


import java.util.ArrayList;
import java.util.List;


public class TimelineActivity extends AppCompatActivity implements TweetFragment.StatusUpdateListener {
    private TwitterClient client;
    private TweetsArrayAdapter aTweets;
    private List<Tweet> tweets;
    private ListView lvTweets;
    private SwipeRefreshLayout swipeContainer;
    private TweetFragment tweetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                tweetFragment = new TweetFragment();
                tweetFragment.show(fragmentManager, "COMPOSE_TWEET");
                tweetFragment.setListener(TimelineActivity.this);
            }
        });

        setupSwitchRefreshLayout();
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(this, tweets);
        lvTweets.setAdapter(aTweets);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                Long sinceId = getOldestTweetId();
                client.getOlderHomeTimeline(new TwitterClient.TimelineResponseHandler() {
                    @Override
                    public void onSuccess(List<Tweet> tweets) {
                        aTweets.addAll(tweets.isEmpty() ? tweets : tweets.subList(1, tweets.size()));
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        logError(error);
                    }
                }, sinceId);
                return true;
            }
        });
        client = TwitterApplication.getRestClient();
        populateTimeline();
    }

    private Long getOldestTweetId() {
        if (tweets.size() == 0) {
            return 1L;
        } else {
            Tweet tweet = tweets.get(tweets.size() - 1);
            return tweet.getUid();
        }
    }

    private void populateTimeline() {
        client.getHomeTimeline(new TwitterClient.TimelineResponseHandler() {
            @Override
            public void onSuccess(List<Tweet> tweets) {
                aTweets.clear();
                TimelineActivity.this.tweets.addAll(tweets);
                aTweets.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable error) {
                swipeContainer.setRefreshing(false);
                logError(error);
            }
        });
    }

    private void logError(Throwable error) {
        Log.d("TIMELINE", "Failed to retrieve tweets", error);
    }

    private void setupSwitchRefreshLayout() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateTimeline();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    @Override
    public void onStatusUpdated() {
        if (tweetFragment != null) {
            tweetFragment.dismiss();
        }
        lvTweets.smoothScrollToPosition(0);
        populateTimeline();
    }
}




/*public class TimelineActivity extends ActionBarActivity {

    private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private TwitterClient client;
    private ListView lvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        lvTweets =(ListView) findViewById(R.id.lvTweets); // find the listview

        tweets = new ArrayList<>(); // create the array list data source
        aTweets = new TweetsArrayAdapter(this, tweets); //construct the adapter in the listview

        lvTweets.setAdapter(aTweets); // connect adapter to listview

        client = TwitterApplication.getRestClient();
        populateTimeline();
    }

    private void populateTimeline() {

        client.getHomeTimeline(new JsonHttpResponseHandler(){
            // success


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                //super.onSuccess(statusCode, headers, json);
                Log.d("DEBUG",json.toString());

                //ArrayList<Tweet> tweets =Tweet.fromArray(json);
                aTweets.addAll(Tweet.fromJSONArray(json));
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
*/