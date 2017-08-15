package com.codepath.apps.TwitterWithFragment.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jonathan Dorvilier on 8/3/2017.
 */

public class User {
    private String name;
    private long uid;
    private  String screenName;
    private String profileImageUrl;
    private String tagLine;
    private  int followersCount;
    private  int followingsCount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }


    public String getScreenName() {

        return screenName;
    }


    public String getTagLine() {
        return tagLine;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return followingsCount;
    }



    public static User fromJSON(JSONObject json){

        User u =  new User();

        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
            u.tagLine = json.getString("description");
            u.followersCount= json.getInt("followers_count");
            u.followingsCount = json.getInt("friends_count");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }

}
