package com.codepath.apps.TwitterWithFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.TwitterWithFragment.fragment.UserTimelineFragment;
import com.codepath.apps.TwitterWithFragment.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends ActionBarActivity {

TwitterClient client;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        client = TwitterApplication.getRestClient();
        //get the account info

        client.getUserInfo(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                user= User.fromJSON(response);

                // my current user information
                getSupportActionBar().setTitle("@" +user.getScreenName());

                populateProfileHeader(user);
            }
        });
        //get the screen name from the activyt
        String screenName=  getIntent().getStringExtra("screen_name");

        if (savedInstanceState == null){

            //create the user timeline frgament
            UserTimelineFragment fragmentUserTimeline = UserTimelineFragment.newInstance(screenName);
            //display the user fragment within the activity (dynamically)
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContainer,fragmentUserTimeline);
            ft.commit(); // change the fragments

        }
    }

    private void populateProfileHeader(User user) {

        TextView tvName =(TextView) findViewById(R.id.tvFullName);
        TextView tvFollowers =(TextView) findViewById(R.id.tvFollowers);
        TextView tvTagLine =(TextView) findViewById(R.id.tvTagLine);
        TextView tvFollowing =(TextView) findViewById(R.id.tvFollowing);
        ImageView ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);

        tvName.setText(user.getName());
        tvTagLine.setText(user.getTagLine());
        tvFollowers.setText(user.getFollowersCount() + "Followers");
        tvFollowing.setText(user.getFriendsCount() + "Following");

        Picasso.with(this).load(user.getProfileImageUrl()).into(ivProfileImage);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
