package com.codepath.apps.TwitterWithFragment.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jonathan Dorvilier on 8/3/2017.
 */

public class Tweet {

    private String body;
    private String createdAt;
    private User user;
    private  long uid; // unique id for the tweet
    private int favoritesCount;
    private boolean retweeted;


    public void setBody(String body) {
        this.body = body;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

    public User getUser() {
        return user;
    }


    public String getBody() {
        return body;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public long getUid() {
        return uid;
    }



    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();

        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("uid");
            tweet.createdAt = jsonObject.getString("created_at");
            //tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return tweet;
    }

    public  static ArrayList<Tweet> fromJSONArray(JSONArray json){

        ArrayList<Tweet> tweets = new ArrayList<>();
         for(int i = 0; i < json.length(); i++){
             try {
                    JSONObject tweetJson = json.getJSONObject(i);
                 Tweet tweet = Tweet.fromJSON(tweetJson);
                 if (tweet!=null){
                     tweets.addAll(tweets);
                 }

             } catch (JSONException e){

                 e.printStackTrace();
                 continue;
             }

         }
        return tweets;
    }

}
