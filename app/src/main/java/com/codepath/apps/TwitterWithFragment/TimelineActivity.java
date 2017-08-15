package com.codepath.apps.TwitterWithFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.TwitterWithFragment.fragment.HomeTimelineFragment;
import com.codepath.apps.TwitterWithFragment.fragment.MentionsTimelineFragment;


public class TimelineActivity extends ActionBarActivity {

    //private ArrayList<Tweet> tweets;
    //private TweetsArrayAdapter aTweets;
    //private ListView lvTweets;
    //private TwitterClient client;
    //private TweetsListFragment fragmentTweetsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //Get the viewpager
        ViewPager vpPager =(ViewPager) findViewById(R.id.viewpager);


        //Set the viewpager adapter for the pager
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));

        //Find the sliding

        PagerSlidingTabStrip tabStrip =(PagerSlidingTabStrip) findViewById(R.id.tabs);

        //Attach the tabstrip to the viewpager
    tabStrip.setViewPager(vpPager);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //lvTweets =(ListView) findViewById(lvTweets); // find the listview

        // tweets = new ArrayList<>(); // create the array list data source
        // aTweets = new TweetsArrayAdapter(this, tweets); //construct the adapter in the listview

        //lvTweets.setAdapter(aTweets); // connect adapter to listview


//get access to the fragment
        /*if (savedInstanceState == null) {
            fragmentTweetsList = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return  true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);

    }

    public void onProfileView(MenuItem mi){
        //lauch the profile view
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);


    }

    // return the order of the fragment in the view page
    public class TweetsPagerAdapter extends FragmentPagerAdapter{

        final int PAGE_COUNT =2;
        private String tabTitle[] = {"Home","Mentions"};

        // adapter gets the manager insert or remove from activity
        public TweetsPagerAdapter (FragmentManager fm){
            super(fm);


        }

        //The order and creation of fragment within the pager
        @Override
        public Fragment getItem(int position) {
            if(position ==0){
                return new HomeTimelineFragment();
            }else if (position ==1){

                return  new MentionsTimelineFragment();
            } else {
                return null;
            }
        }
//return the tabs title
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }

        //how many adapter
        @Override
        public int getCount() {
            return tabTitle.length;
        }
    }
}

